package com.mantis.brac.common.http;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mantis.brac.common.utils.DataUtil;
import com.mantis.brac.common.utils.StringUtil;
import com.mantis.brac.config.StaticProperties;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 12:15
 * @history: 1.2020/4/4 created by wei.wang
 */
public class Response implements Serializable {
    /**
     * 操作结果编码 ok/error
     */
    private String code = StaticProperties.RESPONSE_CODE_OK;
    /**
     * 记录错误信息或者需要提示给用户的信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 本次请求记录UUID
     */
    private String requestid;


    public Response(String code) {
        this(code, null, null);
    }

    public Response(String code, String message) {
        this(code, message, null);
    }

    public Response(String code, String message, Object data) {

        this.code = code;
        this.message = message;
        this.data = data;
        this.requestid = findCurrentRequestId();
        //this.tmpData = JSON.toJSONString(DataUtil.findHttpServletRequest());
    }

    /**
     * 成功返回，没有message
     *
     * @return
     */
    public static Response ok() {
        return new Response(StaticProperties.RESPONSE_CODE_OK);
    }

    /**
     * 成功返回，带message
     *
     * @param message
     * @return
     */
    public static Response ok(String message) {
        return new Response(StaticProperties.RESPONSE_CODE_OK, message);
    }

    /**
     * 失败返回
     *
     * @param message
     * @return
     */
    public static Response error(String message) {
        return new Response(StaticProperties.RESPONSE_CODE_ERROR, message);
    }

    /**
     * 获取当前请求ID
     * 在AOP中会拦截请求，并生成UUID，如果没有配置AOP，则生成新的UUID
     *
     * @return
     */
    private String findCurrentRequestId() {
        return StringUtil.getUuid();
    }

    public String getCode() {
        return code;
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }
}
