package com.mantis.bracket.common.profile;

import com.mantis.bracket.common.constant.StaticProperties;

import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/7 22:55
 * @history: 1.2020/4/7 created by wei.wang
 */
public class UserProfile {

    /**
     * 用户id
     */
    private String uid;
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 请求地址
     */
    private String urlPath;

    /**
     * 请求头
     */
    private Map<String, Object> header;

    /**
     * 请求体
     */
    private Object requestBody;

    /**
     * 额外属性
     */
    private Map<String, Object> attributes;


    /**
     * 标识该用户是否是匿名登录(也就是未登录)
     */
    private boolean isAnonymous = true;

    public static final UserProfile ANONYMOUS_OBJ = new UserProfile(StaticProperties.ANONYMOUS_USER, true);

    public UserProfile() {
    }

    public UserProfile(String uid, boolean isAnonymous) {
        this.uid = uid;
        this.isAnonymous = isAnonymous;
    }

    public UserProfile(String uid, Map<String, Object> attributes, boolean isAnonymous) {
        this.uid = uid;
        this.attributes = attributes;
        this.isAnonymous = isAnonymous;
    }

    public UserProfile(String uid, String userName, Map<String, Object> attributes, boolean isAnonymous) {
        this.uid = uid;
        this.userName = userName;
        this.attributes = attributes;
        this.isAnonymous = isAnonymous;
    }

    public String getUid() {
        return uid;
    }

    public UserProfile setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserProfile setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public UserProfile setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public UserProfile setHeader(Map<String, Object> header) {
        this.header = header;
        return this;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public UserProfile setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public UserProfile setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public UserProfile setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
        return this;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "uid='" + uid + '\'' +
                ", userName='" + userName + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", header=" + header +
                ", requestBody=" + requestBody +
                ", attributes=" + attributes +
                ", isAnonymous=" + isAnonymous +
                '}';
    }
}
