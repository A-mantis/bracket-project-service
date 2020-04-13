package com.mantis.brac.session;

import com.mantis.brac.common.profile.UserProfile;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/7 23:03
 * @history: 1.2020/4/7 created by wei.wang
 */
public class BracSession {
    private static ThreadLocal<UserProfile> session = new ThreadLocal<UserProfile>();

    public static void setUserProfile(UserProfile userProfile) {
        session.set(userProfile);
    }

    public static UserProfile getUserProfile() {
        return session.get();
    }

    public static String getCurrentUser() {
        return session.get() == null ? "anonymous" : session.get().getUid();
    }
}
