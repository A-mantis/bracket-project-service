package com.mantis.bracket.filter;

import com.mantis.bracket.adapter.ParseUserInfo;
import com.mantis.bracket.common.constant.CertificationModeEnum;
import com.mantis.bracket.common.constant.StaticProperties;
import com.mantis.bracket.common.exception.BracketBusinessException;
import com.mantis.bracket.common.http.ExceptionResponse;
import com.mantis.bracket.common.jwt.RsaUtil;
import com.mantis.bracket.common.profile.RequestProfile;
import com.mantis.bracket.common.profile.UserProfile;
import com.mantis.bracket.common.property.AopProperties;
import com.mantis.bracket.common.property.SecurityProperties;
import com.mantis.bracket.common.utils.JsonUtil;
import com.mantis.bracket.common.utils.RequestUtil;
import com.mantis.bracket.common.wrapper.RequestWrapper;
import com.mantis.bracket.common.wrapper.ResponseWrapper;
import com.mantis.bracket.session.BracketSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:19
 * @history: 1.2020/4/4 created by wei.wang
 */
@Component
public class UserAuthFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(UserAuthFilter.class);


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ParseUserInfo parseUserInfo;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        ServletRequest requestWrapper = null;
        ResponseWrapper responseWrapper = null;
        BracketSession.setRequestProfile(new RequestProfile());
        //设置用户信息
        setUserProfile();
        //设置完用户信息后还是匿名用户
        if (securityProperties.isOpen() && BracketSession.getUserProfile().isAnonymous()) {
            unAuthorized(servletResponse);
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
     * 设置用户信息
     */
    private void setUserProfile() {
        //获取header信息
        RequestUtil.getRequestHeader();
        //获取用户信息
        setAccessAttribute();
        BracketSession.setUserProfile(parseUserInfo.parse());
    }


    /**
     * 获取用户认证信息
     */
    private void setAccessAttribute() {
        Map<String, Object> userProfileMap = BracketSession.getAttributes();
        if (userProfileMap == null || userProfileMap.isEmpty()) {
            doSetAccessAttribute();
        }
    }

    /**
     * 执行获取用户认证信息,并缓存
     */
    private void doSetAccessAttribute() {
        String mode = securityProperties.getMode();
        Map<String, Object> attributesMap = new HashMap<>(StaticProperties.INITIAL_CAPACITY);
        try {
            switch (CertificationModeEnum.valueOf(mode)) {
                case PLAIN_TEXT:
                    //解析明文
                    attributesMap = parsePlainText();
                    break;
                case JWK:
                    //解析JSON Web Token
                    attributesMap = parseAccessToken();
                    break;
                case CUSTOMER:
                    //自定义权限认证
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.info("Access Attribute Error {}", e.toString());
        }
        //设置缓存
        BracketSession.setAttributes(attributesMap);
    }

    /**
     * 未授权的处理
     *
     * @param servletResponse
     */
    private void unAuthorized(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().write(JsonUtil.doObjectToJson(ExceptionResponse.error("Unauthorized")).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * JWKS权限认证
     *
     * @return
     */
    public Map<String, Object> parseAccessToken() {
        return RsaUtil.parseAccessToken(RsaUtil.findPublicKeyByUrl(securityProperties.getPublicKeyUrl()), RsaUtil.getJwtToken(securityProperties.getAuthorizationHeader()));
    }

    /**
     * 明文权限认证
     *
     * @return
     */
    public Map<String, Object> parsePlainText() {
        Map<String, Object> userProfileMap = new HashMap<>(StaticProperties.INITIAL_CAPACITY);
        String currentUser = Optional.ofNullable(RequestUtil.getRequestHeader())
                .map(o -> o.get(securityProperties.getAuthorizationHeader()))
                .orElseThrow(() -> new BracketBusinessException("用户权限认证失败！"));
        userProfileMap.put(securityProperties.getAuthorizationHeader(), StringUtils.isEmpty(currentUser) ? StaticProperties.ANONYMOUS_USER : currentUser);
        return userProfileMap;
    }

}