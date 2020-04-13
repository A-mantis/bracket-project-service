package com.mantis.brac.common.adapter;

import com.mantis.brac.common.exception.BracBusinessException;
import com.mantis.brac.common.profile.UserProfile;

import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/7 22:55
 * @history: 1.2020/4/7 created by wei.wang
 */
public interface BracPaasSSOAuthentication {
    UserProfile auth(String userName, String password, Map<String, String> attributes) throws BracBusinessException;
}
