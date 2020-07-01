package com.mantis.bracket.service;

import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/7/1 12:18
 * @history: 1.2020/7/1 created by wei.wang
 */
public interface AuthCertificationService {

    /**
     * 加载用户身份认证信息，重走一遍获取，如果获取的内容为空，就重新获取并缓存
     */
    void loadAccessAttribute();

    /**
     * 获取用户身份认证信息
     * @return
     */
    Map<String, Object> getAccessAttribute();

    /**
     * 执行获取用户认证信息,并缓存
     * @return
     */
    Map<String, Object> doGetAccessAttribute();

    /**
     * JWKS权限认证
     *
     * @return
     */
    Map<String, Object> parseAccessToken();

    /**
     * 明文权限认证
     *
     * @return
     */
    Map<String, Object> parsePlainText();
}
