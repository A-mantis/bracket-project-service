//package com.mantis.brac.aspect;
//
//import com.alibaba.fastjson.JSON;
//import com.mantis.brac.common.http.Response;
//import com.mantis.brac.common.utils.DataUtil;
//import com.mantis.brac.config.StaticProperties;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Description:
// * @author: wei.wang
// * @since: 2020/4/4 13:47
// * @history: 1.2020/4/4 created by wei.wang
// */
//
//
//@Aspect
//@Component
//public class WebLogAspect {
//
//    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
//
//    /**
//     * 定义切点，切点为com.mantis.brac.controller包和子包里任意方法的执行和service层所有方法的执行
//     */
//    @Pointcut("execution(public * com.mantis.brac.controller..*.*(..))")
//    public void webLog() {
//    }
//
//    /**
//     * 环绕通知
//     *
//     * @param pjp
//     */
//    @Around("webLog()")
//    public Object around(ProceedingJoinPoint pjp) {
//        Object responseObject = null;
//        try {
//            logger.info("AOP Aronud before......");
//            Object object = pjp.getArgs()[0];
//            HttpServletRequest request = DataUtil.getHttpServletRequest();
//            logger.info("Service Request Target : {}", pjp.getTarget().getClass().getName());
//            //打印header
//            DataUtil.findRequestHeader(request);
//            //打印请求地址
//            DataUtil.printRequestPath(request);
//            //打印请求参数
//            logger.info("Service Request Params : {}", object instanceof HttpServletRequest ? DataUtil.getReqBody(request) :
//                    JSON.toJSONString(object));
//
//            responseObject = pjp.proceed();
//            //打印返回参数
//            logger.info("Service Response: {}", JSON.toJSONString(responseObject));
//            logger.info("AOP Aronud after......");
//        } catch (Throwable e) {
//            logger.info("pjp proceed exception : {}", e.getMessage());
//            return new Response(StaticProperties.RESPONSE_CODE_ERROR, e.getMessage());
//        }
//        return responseObject;
//    }
//
//    /**
//     * 异常日志
//     *
//     * @param joinPoint
//     * @param e
//     */
//    @AfterThrowing(pointcut = "webLog()", throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
//                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
//    }
//
//
//}
