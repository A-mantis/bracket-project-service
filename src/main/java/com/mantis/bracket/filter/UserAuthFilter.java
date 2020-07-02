package com.mantis.bracket.filter;

import com.mantis.bracket.adapter.ParseUserInfo;
import com.mantis.bracket.common.constant.StaticProperties;
import com.mantis.bracket.common.http.ExceptionResponse;
import com.mantis.bracket.common.profile.RequestProfile;
import com.mantis.bracket.common.property.SecurityProperties;
import com.mantis.bracket.common.utils.JsonUtil;
import com.mantis.bracket.common.utils.StringUtil;
import com.mantis.bracket.common.wrapper.RequestWrapper;
import com.mantis.bracket.common.wrapper.ResponseWrapper;
import com.mantis.bracket.service.AuthCertificationService;
import com.mantis.bracket.service.impl.AuthCertificationServiceImpl;
import com.mantis.bracket.session.BracketSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:19
 * @history: 1.2020/4/4 created by wei.wang
 */
@Component
public class UserAuthFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(UserAuthFilter.class);

    private final SecurityProperties securityProperties;

    private final ParseUserInfo parseUserInfo;

    private final AuthCertificationService authCertificationService;

    private final static List<String> PUBLIC_ROUTE_LIST = Arrays.asList(
            "/druid/*",
            "/swagger-ui.html/*",
            "/swagger-resources/*",
            "/webjars/*",
            "/v2/api-docs/*"
    );


    public UserAuthFilter(SecurityProperties securityProperties, ParseUserInfo parseUserInfo, AuthCertificationService authCertificationService) {
        this.securityProperties = securityProperties;
        this.parseUserInfo = parseUserInfo;
        this.authCertificationService = authCertificationService;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        ServletRequest requestWrapper = null;
        ResponseWrapper responseWrapper = null;
        BracketSession.setRequestProfile(new RequestProfile());
        if (urlInPublicRouteList(BracketSession.getUrlPath())) {
            //加载用户身份认证信息
            authCertificationService.loadAccessAttribute();
            //是否存在加载身份认证信息错误
            if (BracketSession.hasRequestCertificationError()) {
                certificationError(servletResponse);
                return;
            }
            //解析用户身份认证信息
            BracketSession.setUserProfile(parseUserInfo.parse());
            //设置完用户信息后还是匿名用户
            if (securityProperties.isOpen() && BracketSession.getUserProfile().isAnonymous()) {
                unAuthorized(servletResponse, "");
                return;
            }
        }
        try {
            if (servletRequest instanceof HttpServletRequest) {
                requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
            }
            if (servletResponse instanceof HttpServletResponse) {
                responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
            }
            if (requestWrapper != null && responseWrapper != null) {
                filterChain.doFilter(requestWrapper, responseWrapper);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 身份认证异常
     *
     * @param servletResponse
     */
    private void certificationError(ServletResponse servletResponse) {
        unAuthorized(servletResponse, String.valueOf(BracketSession.getRequestError().get(StaticProperties.REQUEST_ERROR_AUTH_CODE)));
    }

    /**
     * 未授权的处理
     *
     * @param servletResponse
     */
    private void unAuthorized(ServletResponse servletResponse, String errMsg) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().write(JsonUtil.doObjectToJson(ExceptionResponse.error(StringUtil.strEmpty(errMsg)
                    ? "Unauthorized" : errMsg)).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否拦截
     *
     * @param url
     * @return
     */
    private boolean urlInPublicRouteList(String url) {
        List<String> publicRouteList = Arrays.asList(securityProperties.getPublicFilter().split(StaticProperties.SPLIT_FLAG_COMMA));
        publicRouteList.addAll(PUBLIC_ROUTE_LIST);
        for (String s : publicRouteList) {
            if (url.contains(s)) {
                return false;
            }
        }
        return true;
    }
}