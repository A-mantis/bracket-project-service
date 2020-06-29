package com.mantis.bracket.common.constant;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/5/21 17:33
 * @history: 1.2020/5/26 created by wei.wang
 */
public enum CertificationModeEnum {
    /**
     * 明文模式，用户直接写在header上
     */
    PLAIN_TEXT,

    /**
     * jwks模式，使用JWKS认证
     */
    JWKS,

    /**
     * 自定义模式
     */
    CUSTOMER
}
