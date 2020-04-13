package com.mantis.brac.common.profile;

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
     * 属性
     */
    private Map<String, Object> attributes;
    /**
     * 标识该用户是否是匿名登录(也就是未登录)
     */
    private boolean isAnonymous = true;

    public UserProfile(String uid, String userName, Map<String, Object> attributes, boolean isAnonymous) {
        this.uid = uid;
        this.userName = userName;
        this.attributes = attributes;
        this.isAnonymous = isAnonymous;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public UserProfile setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
        return this;
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

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public UserProfile setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }
}
