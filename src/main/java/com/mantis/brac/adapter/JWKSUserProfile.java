package com.mantis.brac.adapter;


import com.mantis.brac.common.profile.UserProfile;

import java.util.Map;

/**
 * @Description:用户认证
 * @author: wei.wang
 * @since: 2020/6/28 16:06
 * @history: 1.2020/6/28 created by wei.wang
 */
public interface JWKSUserProfile {

    /**
     * 通用用户身份认证，解析包含用户信息的Map对象，根据具体项目重写该方法以实现对应的逻辑
     *
     * @param originMap
     * @return
     */
    public UserProfile certification(Map<String, Object> originMap);
}
