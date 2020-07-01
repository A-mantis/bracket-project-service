package com.mantis.bracket.service.impl;

import com.mantis.bracket.common.constant.CertificationModeEnum;
import com.mantis.bracket.common.constant.StaticProperties;
import com.mantis.bracket.common.exception.BracketAuthException;
import com.mantis.bracket.common.jwt.RsaUtil;
import com.mantis.bracket.common.property.SecurityProperties;
import com.mantis.bracket.common.utils.RequestUtil;
import com.mantis.bracket.service.AuthCertificationService;
import com.mantis.bracket.session.BracketSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/7/1 9:00
 * @history: 1.2020/7/1 created by wei.wang
 */
@Service
public class AuthCertificationServiceImpl implements AuthCertificationService {

    private static Logger logger = LoggerFactory.getLogger(AuthCertificationServiceImpl.class);

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 加载用户身份认证信息，重走一遍获取，如果获取的内容为空，就重新获取并缓存
     */
    @Override
    public void loadAccessAttribute() {
        getAccessAttribute();
    }

    /**
     * 获取用户身份认证信息
     */
    @Override
    public Map<String, Object> getAccessAttribute() {
        return Optional.ofNullable(BracketSession.getAttributes()).orElse(doGetAccessAttribute());
    }

    /**
     * 执行获取用户认证信息,并缓存
     */
    @Override
    public Map<String, Object> doGetAccessAttribute() {
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
        } catch (BracketAuthException e) {
            Map<String, Object> requestErrorMap = new HashMap<>(StaticProperties.INITIAL_CAPACITY);
            requestErrorMap.put(StaticProperties.REQUEST_ERROR_AUTH_CODE, e.toString());
            BracketSession.setRequestError(requestErrorMap);
            logger.info("Access Attribute Error {}", e.toString());
        }
        //设置缓存
        BracketSession.setAttributes(attributesMap);
        return attributesMap;
    }

    /**
     * JWKS权限认证
     *
     * @return
     */
    @Override
    public Map<String, Object> parseAccessToken() {
        try {
            return RsaUtil.parseAccessToken(RsaUtil.findPublicKeyByUrl(securityProperties.getPublicKeyUrl()), RsaUtil.getJwtToken(securityProperties.getAuthorizationHeader()));
        } catch (BracketAuthException e) {
            throw new BracketAuthException(e.getMessage());
        }
    }

    /**
     * 明文权限认证
     *
     * @return
     */
    @Override
    public Map<String, Object> parsePlainText() {
        Map<String, Object> userProfileMap = new HashMap<>(StaticProperties.INITIAL_CAPACITY);
        String currentUser = Optional.ofNullable(RequestUtil.getRequestHeader())
                .map(o -> o.get(securityProperties.getAuthorizationHeader()))
                .orElseThrow(() -> new BracketAuthException("用户权限认证失败！"));
        userProfileMap.put(securityProperties.getAuthorizationHeader(), StringUtils.isEmpty(currentUser) ? StaticProperties.ANONYMOUS_USER : currentUser);
        return userProfileMap;
    }
}
