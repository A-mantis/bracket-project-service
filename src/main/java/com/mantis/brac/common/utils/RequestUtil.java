package com.mantis.brac.common.utils;


import com.mantis.brac.common.constant.StaticProperties;
import com.mantis.brac.common.exception.BracBusinessException;
import com.mantis.brac.common.profile.RequestProfile;
import com.mantis.brac.common.wrapper.ResponseWrapper;
import com.mantis.brac.session.BracSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:请求操作类
 * @author: wei.wang
 * @since: 2020/5/26 0:08
 * @history: 1.2020/5/26 created by wei.wang
 */
public class RequestUtil {

    private RequestUtil() {
    }

    private static Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 获取request
     * Spring对一些（如RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder等）
     * 中非线程安全状态的bean采用ThreadLocal进行处理使之成为线程安全的状态
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        //获取RequestAttributes
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest).orElse(null);
    }

    /**
     * 获取response
     *
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getResponse).orElse(null);
    }

    /**
     * 获取Header
     */
    public static Map<String, String> getRequestHeader(HttpServletRequest request) {
        return Optional.ofNullable(BracSession.getHeader()).orElse(doGetRequestHeader(request));
    }

    /**
     * 获取请求路径
     *
     * @param request
     * @return
     */
    public static String getRequestPath(HttpServletRequest request) {
        return Optional.ofNullable(BracSession.getUrlPath()).orElse(doGetRequestPath(request));
    }

    /**
     * 执行获取请求路径
     *
     * @param request
     * @return
     */
    public static String doGetRequestPath(HttpServletRequest request) {
        String urlPath = request.getRequestURI();
        //设置缓存
        BracSession.getRequestProfile().setUrlPath(urlPath);
        return urlPath;
    }


    /**
     * 获取请求类型
     *
     * @param request
     * @return
     */
    public static HttpMethod getRequestType(HttpServletRequest request) {
        return Optional.ofNullable(BracSession.getRequestType()).orElse(doGetRequestType(request));
    }

    /**
     * 执行获取请求类型
     *
     * @param request
     * @return
     */
    public static HttpMethod doGetRequestType(HttpServletRequest request) {
        HttpMethod httpMethod = HttpMethod.resolve(request.getMethod());
        //设置缓存
        BracSession.getRequestProfile().setRequestType(httpMethod);
        return httpMethod;
    }


    /**
     * 执行获取Header
     *
     * @param request
     * @return
     */
    public static Map<String, String> doGetRequestHeader(HttpServletRequest request) {
        HashMap<String, String> requestHeaderMap = new HashMap<>(StaticProperties.INITIAL_CAPACITY);
        Enumeration<String> headNames = request.getHeaderNames();
        while (headNames.hasMoreElements()) {
            String headName = headNames.nextElement();
            requestHeaderMap.put(headName, request.getHeader(headName));
        }
        //设置缓存
        BracSession.getRequestProfile().setHeader(requestHeaderMap);
        return requestHeaderMap;
    }

    /**
     * 返回调用参数
     *
     * @return ReqBody
     */
    public static String getReqBody(HttpServletRequest request) {
        HttpMethod method = getRequestType(request);
        if (HttpMethod.POST.equals(method)
                || HttpMethod.PUT.equals(method)
                || HttpMethod.PATCH.equals(method)
                || HttpMethod.DELETE.equals(method)) {
            return getReqBodyParam(request);
        } else if (HttpMethod.GET.equals(method)) {
            return getReqUrlParam(request);
        }
        return "get Request Parameter Error";
    }

    /**
     * 获取POST请求数据
     *
     * @param request
     * @return 返回POST参数
     */
    public static String getReqBodyParam(HttpServletRequest request) {
        try (InputStream inputStream = request.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            logger.info("Post Request Parameter Error : {}", e.getMessage());
        }
        return "Post Request Parameter Error";
    }

    /**
     * 获取GET请求数据
     *
     * @param request
     * @return
     */
    public static String getReqUrlParam(HttpServletRequest request) {
        try {
            Enumeration<String> enumeration = request.getParameterNames();
            Map<String, String> parameterMap = new HashMap<>(16);
            while (enumeration.hasMoreElements()) {
                String parameter = enumeration.nextElement();
                parameterMap.put(parameter, request.getParameter(parameter));
            }
            return parameterMap.toString();
        } catch (Exception e) {
            logger.info("Get Request Parameter Error : {}", e.getMessage());
        }
        return "Get Request Parameter Error";
    }

    /**
     * 获取返回内容
     *
     * @param response
     * @return
     */
    public static String getResponseBody(HttpServletResponse response) {
        if (response instanceof ResponseWrapper) {
            ResponseWrapper responseWrapper = (ResponseWrapper) response;
            return responseWrapper.getStrResponse();
        }
        return "Get Response Error";
    }


    /**
     * 校验请求参数
     *
     * @param args
     * @return
     */
    public static void checkRequestParam(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof BeanPropertyBindingResult && ((BeanPropertyBindingResult) arg).hasErrors()) {
                String errMsg = Optional.ofNullable(((BeanPropertyBindingResult) arg)
                        .getFieldError()).map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .orElse(StaticProperties.REQUEST_ERROR_MSG);
                throw new BracBusinessException(errMsg);
            }
        }
    }


    /**
     * 返回请求参数
     *
     * @param request
     * @return
     */
    public static String getRequestParam(HttpServletRequest request) {
        return Optional.ofNullable(BracSession.getRequestParam()).orElse(doGetRequestParam(request));
    }

    /**
     * 返回请求参数
     *
     * @param request
     * @return
     */
    public static String doGetRequestParam(HttpServletRequest request) {
        String requestParam = RequestUtil.getReqBody(request);
        //设置缓存
        BracSession.getRequestProfile().setRequestParam(requestParam);
        return requestParam;
    }

    /**
     * 返回请求参数
     *
     * @param args
     * @param request
     * @return
     */
    public static String getRequestParam(Object[] args, HttpServletRequest request) {
        return Optional.ofNullable(BracSession.getRequestParam()).orElse(doGetRequestParam(args, request));
    }


    /**
     * 返回请求参数
     *
     * @param args
     * @param request
     * @return
     */
    public static String doGetRequestParam(Object[] args, HttpServletRequest request) {
        String requestParam = null;
        List<Object> argList = new ArrayList<>();
        if (args != null && args.length > 0) {
            argList = Arrays.stream(args)
                    .filter(arg -> (!(arg instanceof HttpServletResponse) && !(arg instanceof BeanPropertyBindingResult)))
                    .collect(Collectors.toList());
        }
        for (Object arg : argList) {
            if (arg instanceof HttpServletRequest) {
                requestParam = RequestUtil.getReqBody(request);
                break;
            } else {
                requestParam = JsonUtil.objectToJson(arg);
            }
        }
        //设置缓存
        BracSession.setRequestParam(requestParam);
        return requestParam;
    }


    /**
     * 后置打印，根据具体项目重写该方法以实现对应的逻辑，比如日志等
     *
     * @param proceedObject
     */
    public static void afterPrint(Object proceedObject) {
        //打印方法返回结果
        if (proceedObject != null) {
            logger.info("Service proceedObject : {}", JsonUtil.objectToJson(proceedObject));
        }
        //打印HttpServletResponse
        HttpServletResponse response = RequestUtil.getHttpServletResponse();
        logger.info("Service HttpServletResponse Response : {}", RequestUtil.getResponseBody(response));
    }

    public static void beforePrint(Object[] args) {
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        //打印header
        logger.info("Service Request Header : {}", RequestUtil.getRequestHeader(request));
        //打印请求地址
        logger.info("Service Request Path : {}", RequestUtil.getRequestPath(request));
        //打印请求参数
        logger.info("Service HttpServletRequest Params : {}", RequestUtil.getRequestParam(args, request));
    }


}
