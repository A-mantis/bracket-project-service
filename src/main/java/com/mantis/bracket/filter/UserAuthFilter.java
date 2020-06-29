package com.mantis.bracket.filter;

import com.mantis.bracket.adapter.JwksUserProfile;
import com.mantis.bracket.common.constant.CertificationModeEnum;
import com.mantis.bracket.common.constant.StaticProperties;
import com.mantis.bracket.common.exception.BracketBusinessException;
import com.mantis.bracket.common.jwt.RsaUtil;
import com.mantis.bracket.common.profile.RequestProfile;
import com.mantis.bracket.common.property.BracketProperties;
import com.mantis.bracket.common.utils.RequestUtil;
import com.mantis.bracket.common.wrapper.RequestWrapper;
import com.mantis.bracket.common.wrapper.ResponseWrapper;
import com.mantis.bracket.session.BracketSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
//@WebFilter(urlPatterns = "/*", filterName = "filter")
public class UserAuthFilter implements Filter {

    @Autowired
    private BracketProperties bracketProperties;

    @Autowired
    private JwksUserProfile jwksUserProfile;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        BracketSession.setRequestProfile(new RequestProfile());
        ServletRequest requestWrapper = null;
        ResponseWrapper responseWrapper = null;
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
        //设置请求信息
        setRequestProfile();
        //用户权限认证
        authCertification();
    }

    /**
     * 设置请求属性，请求路径，请求header，请求类型
     */
    private void setRequestProfile() {
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        RequestUtil.getRequestPath(request);
        RequestUtil.getRequestHeader(request);
        RequestUtil.getRequestType(request);
        RequestUtil.getRequestParam(request);
    }


    @Override
    public void destroy() {

    }

    /**
     * 用户权限认证
     */
    private void authCertification() {
        String mode = bracketProperties.getMode();
        Map<String, Object> attributesMap = new HashMap<>(StaticProperties.INITIAL_CAPACITY);
        switch (CertificationModeEnum.valueOf(mode)) {
            case PLAIN_TEXT:
                //明文权限认证
                attributesMap = plainTextProperty();
                break;
            case JWKS:
                //JWKS权限认证
                attributesMap = findJwtTokenProperty();
                break;
            case CUSTOMER:
                //自定义权限认证
                break;
            default:
                return;
        }
        //设置缓存
        BracketSession.setAttributes(attributesMap);
        BracketSession.setUserProfile(jwksUserProfile.certification(attributesMap));
    }

    /**
     * JWKS权限认证
     *
     * @return
     */
    public Map<String, Object> findJwtTokenProperty() {
        return RsaUtil.parseAccessToken(RsaUtil.findPublicKeyByUrl(bracketProperties.getPublicKeyUrl()), RsaUtil.findJwtToken(bracketProperties.getAuthorizationHeader()));
    }

    /**
     * 明文权限认证
     *
     * @return
     */
    public Map<String, Object> plainTextProperty() {
        Map<String, Object> userProfileMap = new HashMap<>(StaticProperties.INITIAL_CAPACITY);
        String currentUser = Optional.ofNullable(BracketSession.getHeader())
                .map(o -> o.get(bracketProperties.getAuthorizationHeader()))
                .orElseThrow(() -> new BracketBusinessException("用户权限认证失败！"));
        userProfileMap.put(bracketProperties.getAuthorizationHeader(), StringUtils.isEmpty(currentUser) ? StaticProperties.ANONYMOUS_USER : currentUser);
        return userProfileMap;
    }
}