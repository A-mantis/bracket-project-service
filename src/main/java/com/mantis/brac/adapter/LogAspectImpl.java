package com.mantis.brac.adapter;

import com.mantis.brac.common.utils.RequestUtil;
import org.springframework.stereotype.Component;

/**
 * @Description: AOP方法
 * @author: wei.wang
 * @since: 2020/6/28 16:06
 * @history: 1.2020/6/28 created by wei.wang
 */
@Component
public class LogAspectImpl implements LogAspect {

    @Override
    public void beforeOperate(Object[] args) {
        //前置校验参数
        RequestUtil.checkRequestParam(args);
        //前置打印
        RequestUtil.beforePrint(args);
    }

    @Override
    public void afterOperate(Object object) {
        //后置打印
        RequestUtil.afterPrint(object);
    }
}
