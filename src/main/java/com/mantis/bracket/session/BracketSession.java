package com.mantis.bracket.session;

import com.mantis.bracket.common.constant.StaticProperties;
import com.mantis.bracket.common.profile.RequestProfile;
import com.mantis.bracket.common.profile.UserProfile;
import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/7 23:03
 * @history: 1.2020/4/7 created by wei.wang
 */
public class BracketSession {

    private BracketSession() {
    }

    private static ThreadLocal<RequestProfile> session = new ThreadLocal<>();

    /**
     * 获取请求信息
     *
     * @param requestProfile
     */
    public static void setRequestProfile(RequestProfile requestProfile) {
        session.set(requestProfile);
    }

    public static RequestProfile getRequestProfile() {
        return Optional.ofNullable(session.get()).orElse(new RequestProfile());
    }

    /**
     * 获取用户信息
     *
     * @param userProfile
     */
    public static void setUserProfile(UserProfile userProfile) {
        getRequestProfile().setUserProfile(userProfile);
    }

    public static UserProfile getUserProfile() {
        return Optional.ofNullable(getRequestProfile().getUserProfile())
                .orElse(new UserProfile());
    }


    public static HttpMethod getRequestType() {
        return getRequestProfile().getRequestType();
    }

    public static void setRequestType(HttpMethod requestType) {
        getRequestProfile().setRequestType(requestType);
    }

    public static String getUrlPath() {
        return getRequestProfile().getUrlPath();
    }

    public static void setUrlPath(String urlPath) {
        getRequestProfile().setUrlPath(urlPath);
    }

    public static Map<String, String> getHeader() {
        return getRequestProfile().getHeader();
    }

    public static void setHeader(Map<String, String> header) {
        getRequestProfile().setHeader(header);
    }

    public static String getRequestParam() {
        return getRequestProfile().getRequestParam();
    }

    public static void setRequestParam(String requestParam) {
        getRequestProfile().setRequestParam(requestParam);
    }

    public static Map<String, Object> getAttributes() {
        return getUserProfile().getAttributes();
    }

    public static void setAttributes(Map<String, Object> attributes) {
        getRequestProfile().setUserProfile(getUserProfile().setAttributes(attributes));
    }

    public static String getUid() {
        return getUserProfile().getUid();
    }

    public static void setUid(String uid) {
        getRequestProfile().setUserProfile(getUserProfile().setUid(uid));
    }

    public static String getUserName() {
        return getUserProfile().getUserName();
    }

    public static void setUserName(String userName) {
        getRequestProfile().setUserProfile(getUserProfile().setUserName(userName));
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static String getCurrentUser() {
        return Optional.of(getUserProfile()).map(UserProfile::getUid).orElse(StaticProperties.ANONYMOUS_USER);
    }

    /**
     * 获取当前用户名称
     *
     * @return
     */
    public static String getCurrentUserName() {
        return Optional.of(getUserProfile()).map(UserProfile::getUserName).orElse(StaticProperties.ANONYMOUS_USER_NAME);
    }
}
