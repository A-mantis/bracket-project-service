package com.mantis.brac.common.exception;

import com.mantis.brac.common.http.Response;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:50
 * @history: 1.2020/4/4 created by wei.wang
 */
public class BracBusinessException extends RuntimeException {

    private Response response;

    public BracBusinessException(String message){
        super(message);
    }

    /**
     * 直接返回Response
     *
     * @param response
     */
    public BracBusinessException(Response response) {
        this.response = response;
    }

    /**
     * 自定义code和data
     *
     * @param code
     * @param message
     * @param data
     */
    public BracBusinessException(String code, String message, Object data) {
        this.response = new Response(code, message, data);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
