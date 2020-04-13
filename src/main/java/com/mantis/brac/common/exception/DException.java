package com.mantis.brac.common.exception;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/12 1:37
 * @history: 1.2020/4/12 created by wei.wang
 */
public class DException extends Exception
{
    public DException(Throwable throwable)
    {
        super(throwable);
    }

    public DException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public DException(String string) {
        super(string);
    }

    public DException()
    {
    }
}