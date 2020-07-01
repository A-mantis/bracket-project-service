package com.mantis.bracket.common.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/7/1 10:20
 * @history: 1.2020/7/1 created by wei.wang
 */
public class BracketAuthException extends RuntimeException {

    private static final int STATUS_CODE = HttpServletResponse.SC_UNAUTHORIZED;

    public int getStatusCode() {
        return STATUS_CODE;
    }

    public BracketAuthException() {

    }

    public BracketAuthException(String message) {
        super(message);
    }
}
