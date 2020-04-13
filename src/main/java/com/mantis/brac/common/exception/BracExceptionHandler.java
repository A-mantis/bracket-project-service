package com.mantis.brac.common.exception;

import com.alibaba.fastjson.JSON;
import com.mantis.brac.common.http.Response;
import com.mantis.brac.common.utils.DataUtil;
import com.mantis.brac.config.StaticProperties;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:15
 * @history: 1.2020/4/4 created by wei.wang
 */
@RestControllerAdvice
public class BracExceptionHandler {


    /**
     * 拦截业务异常，可自定义data
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BracRuntimeException.class)
    protected Response bracRuntimeException(BracRuntimeException ex) {
        return Response.error(ex.getMessage());
    }


    /**
     * 拦截业务异常，可自定义data
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BracBusinessException.class)
    protected Response bracBusinessException(BracBusinessException ex) {
        return Response.error(ex.getMessage());
    }

    /**
     * 拦截系统异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    protected Response bracException(Exception ex) {
        return Response.error(ex.getMessage());
    }
}
