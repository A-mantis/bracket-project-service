package com.mantis.bracket.common.profile;

import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/28 21:28
 * @history: 1.2020/6/28 created by wei.wang
 */
public class RequestProfile {
    /**
     * 用户信息
     */
    private UserProfile userProfile;

    /**
     * 请求方式
     */
    private HttpMethod requestType;

    /**
     * 请求地址
     */
    private String urlPath;

    /**
     * 请求头
     */
    private Map<String, String> header;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 额外属性
     */
    private Map<String, Object> attributes;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public RequestProfile setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public HttpMethod getRequestType() {
        return requestType;
    }

    public void setRequestType(HttpMethod requestType) {
        this.requestType = requestType;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public RequestProfile setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public RequestProfile setHeader(Map<String, String> header) {
        this.header = header;
        return this;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public RequestProfile setRequestParam(String requestParam) {
        this.requestParam = requestParam;
        return this;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public RequestProfile setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    @Override
    public String toString() {
        return "RequestProfile{" +
                "userProfile=" + userProfile +
                ", requestType=" + requestType +
                ", urlPath='" + urlPath + '\'' +
                ", header=" + header +
                ", requestParam=" + requestParam +
                ", attributes=" + attributes +
                '}';
    }
}
