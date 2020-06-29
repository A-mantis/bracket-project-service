package com.mantis.brac.common.exception;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:50
 * @history: 1.2020/4/4 created by wei.wang
 */
public class BracBusinessException extends BaseException {

    /**
     * 自定义异常，状态码
     */
    public static final int STATUS_CODE=701;

    public BracBusinessException(){

    }
    public BracBusinessException(String message){
        super(message);
    }
}
