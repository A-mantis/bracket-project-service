package com.mantis.bracket.common.exception;

/**
 * @Description:业务异常，如果发生，直接抛出
 * @author: wei.wang
 * @since: 2020/4/4 16:50
 * @history: 1.2020/4/4 created by wei.wang
 */
public class BracketBusinessException extends RuntimeException {

    /**
     * 自定义异常，状态码
     */
    public static final int STATUS_CODE=700;

    public int getStatusCode() {
        return STATUS_CODE;
    }

    public BracketBusinessException(){

    }
    public BracketBusinessException(String message){
        super(message);
    }
}
