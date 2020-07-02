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
public class SecurityProperties {

    /**
     * 是否开启安全认证
     */
    private boolean open;

    /**
     * 认证模式
     */
    @Pattern(regexp = "^PLAIN_TEXT$|^JWK$|^CUSTOMER$", message = "请选择认证模式：PLAIN_TEXT、JWK、CUSTOMER")
    private String mode;
    /**
     * 公钥地址
     */
    private String publicKeyUrl;
    /**
     * 默认用户身份认证header
     */
    private String authorizationHeader = "uid";

    /**
     *不被拦截的请求路径
     */
    private String publicFilter;

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

    public String getPublicFilter() {
        return publicFilter;
    }

    public void setPublicFilter(String publicFilter) {
        this.publicFilter = publicFilter;
    }

    @Override
    public String toString() {
        return "SecurityProperties{" +
                "open=" + open +
                ", mode='" + mode + '\'' +
                ", publicKeyUrl='" + publicKeyUrl + '\'' +
                ", authorizationHeader='" + authorizationHeader + '\'' +
                ", publicFilter='" + publicFilter + '\'' +
                '}';
    }
}
