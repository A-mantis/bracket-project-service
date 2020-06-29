package com.mantis.brac.common.http;

import java.io.Serializable;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/12 12:32
 * @history: 1.2020/6/12 created by wei.wang
 */
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String error;


    public static ExceptionResponse error(String error) {
        return new ExceptionResponse(error);
    }

    public ExceptionResponse() {

    }

    public ExceptionResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
