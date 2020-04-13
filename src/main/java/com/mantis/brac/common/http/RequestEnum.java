package com.mantis.brac.common.http;

import com.mantis.brac.config.StaticProperties;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/11 18:05
 * @history: 1.2020/4/11 created by wei.wang
 */

public enum RequestEnum {
    GET(StaticProperties.REQUEST_GET), POST(StaticProperties.REQUEST_POST);

    private String requestType;

    // 构造方法
    private RequestEnum(String requestType) {
        this.requestType = requestType;
    }

}
