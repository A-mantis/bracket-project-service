package com.mantis.bracket.adapter;


import com.mantis.bracket.common.profile.UserProfile;

/**
 * @Description:解析用户信息
 * @author: wei.wang
 * @since: 2020/6/28 16:06
 * @history: 1.2020/6/28 created by wei.wang
 */
public interface ParseUserInfo {

    /**
     * 通用用户身份认证，解析包含用户信息的对象，根据具体项目重写该方法以实现对应的逻辑
     *
     * @return
     */
    UserProfile parse();
}
