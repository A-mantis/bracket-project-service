package com.mantis.brac.pojo;

import java.util.List;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/13 17:16
 * @history: 1.2020/4/13 created by wei.wang
 */
public class UserInfo {

    private String id;

    private List<User> userList;

    private Long roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
