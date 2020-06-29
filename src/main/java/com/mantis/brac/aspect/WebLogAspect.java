package com.mantis.brac.aspect;

import com.mantis.brac.adapter.LogAspect;
import com.mantis.brac.common.exception.BracBusinessException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/29 13:04
 * @history: 1.2020/6/29 created by wei.wang
 */
public class WebLogAspect implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private LogAspect logAspect;

    public WebLogAspect() {
    }

    public WebLogAspect(LogAspect logAspect) {
        this.logAspect = logAspect;
    }

    @Override
    public Object invoke(MethodInvocation invocation) {
        logger.info("AOP Around before......");
        Object[] args = invocation.getArguments();
        //前置操作
        logAspect.beforeOperate(args);
        Object proceedObject = null;
        try {
            //执行
            proceedObject = invocation.proceed();
        } catch (Throwable e) {
            logger.error("AOP Proceed Error {}", e.toString());
            throw new BracBusinessException(e.getMessage());
        }
        //后置操作
        logAspect.afterOperate(proceedObject);
        logger.info("AOP Around after......");
        return proceedObject;
    }
}
