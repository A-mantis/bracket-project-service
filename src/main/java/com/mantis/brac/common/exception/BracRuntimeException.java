package com.mantis.brac.common.exception;

import com.mantis.brac.common.http.Response;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:15
 * @history: 1.2020/4/4 created by wei.wang
 */
public class BracRuntimeException extends RuntimeException {

    private Response r;

    public BracRuntimeException(Response r) {
        this.r = r;
    }

    public BracRuntimeException(String msg) {
        super(msg);
    }
}
