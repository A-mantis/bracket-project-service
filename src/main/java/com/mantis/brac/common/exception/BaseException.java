package com.mantis.brac.common.exception;


/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:50
 * @history: 1.2020/4/4 created by wei.wang
 */
public abstract class BaseException extends RuntimeException {

    private static final int STATUS_CODE = 700;

    public int getStatusCode() {
        return STATUS_CODE;
    }


    public BaseException() {

    }

    public BaseException(String message) {
        super(message);
    }


}
