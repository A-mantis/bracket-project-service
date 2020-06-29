package com.mantis.brac.common.exception;

import com.mantis.brac.common.http.ExceptionResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:15
 * @history: 1.2020/4/4 created by wei.wang
 */
@RestControllerAdvice
public class BracExceptionHandler {

    @ExceptionHandler({BaseException.class})
    @ResponseBody
    public ExceptionResponse businessExceptionHandler(HttpServletResponse response, BaseException ex) {
        response.setStatus(ex.getStatusCode());
        return ExceptionResponse.error(ex.getMessage());
    }
}
