package com.mantis.brac.adapter;

import com.mantis.brac.common.profile.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/29 8:17
 * @history: 1.2020/6/29 created by wei.wang
 */
@Component
public class JWKSUserProfileImpl implements JWKSUserProfile {

    @Override
    public UserProfile certification(Map<String, Object> originMap) {
        String uid = (String) originMap.get("upn");
        String username = (String) originMap.get("unique_name");
        UserProfile userProfile = new UserProfile(uid.toLowerCase().substring(0, uid.indexOf("@")), false);
        userProfile.setUserName(username);
        return userProfile;
    }
}
