package com.mantis.bracket.common.exception;

import com.mantis.bracket.common.http.ExceptionResponse;
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
public class BracketExceptionHandler {

    @ExceptionHandler({BracketBusinessException.class})
    @ResponseBody
    public ExceptionResponse businessExceptionHandler(HttpServletResponse response, BracketBusinessException ex) {
        response.setStatus(ex.getStatusCode());
        return ExceptionResponse.error(ex.getMessage());
    }


}
