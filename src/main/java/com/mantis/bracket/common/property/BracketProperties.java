package com.mantis.bracket.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/7 23:11
 * @history: 1.2020/4/7 created by wei.wang
 */
@Valid
@ConfigurationProperties(prefix = "spring.bracket.security")
public class BracketProperties {

    /**
     * 是否开启安全认证
     */
    private boolean open;

    /**
     * 认证模式
     */
    @Pattern(regexp = "^PLAIN_TEXT$|^JWKS$|^CUSTOMER$", message = "请选择认证模式：PLAIN_TEXT、JWKS、CUSTOMER")
    private String mode;
    /**
     * 公钥地址
     */
    private String publicKeyUrl;
    /**
     * 用户身份认证header
     */
    private String authorizationHeader = "uid";

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getPublicKeyUrl() {
        return publicKeyUrl;
    }

    public void setPublicKeyUrl(String publicKeyUrl) {
        this.publicKeyUrl = publicKeyUrl;
    }

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public void setAuthorizationHeader(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "BracProperties{" +
                "open=" + open +
                ", mode='" + mode + '\'' +
                ", publicKeyUrl='" + publicKeyUrl + '\'' +
                ", authorizationHeader='" + authorizationHeader + '\'' +
                '}';
    }
}
