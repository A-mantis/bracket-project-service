package com.mantis.brac.adapter;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/29 10:55
 * @history: 1.2020/6/29 created by wei.wang
 */
public interface LogAspect {

    /**
     * 前置操作，根据具体项目重写该方法以实现对应的逻辑，比如日志等
     *
     * @param args
     */
    void beforeOperate(Object[] args);

    /**
     * 后置操作，根据具体项目重写该方法以实现对应的逻辑，比如日志等
     *
     * @param object
     */
    void afterOperate(Object object);
}
