//package com.mantis.brac.common.http;
//
//import com.mantis.brac.common.feign.FeignConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * @Description:
// * @author: wei.wang
// * @since: 2020/4/11 18:28
// * @history: 1.2020/4/11 created by wei.wang
// */
//@Component
//public class Request {
//
//    @Autowired
//    FeignConfig feignConfig;
//
//    private String uri;
//    private Map<String, Object> header;
//    private Map<String, Object> request;
//    private RequestEnum requestType;
//
//    public static class Builder {
//        String method;
//        String uri;
//        Map<String, Object> header;
//        Map<String, Object> request;
//
//        public Builder() {
//            this.method = "GET";
//        }
//
//        public String getMethod() {
//            return method;
//        }
//
//        public Request.Builder method(String method) {
//            this.method = method;
//            return this;
//        }
//
//        public String getUri() {
//            return uri;
//        }
//
//        public Request.Builder uri(String uri) {
//            this.uri = uri;
//            return this;
//        }
//
//        public Map<String, Object> getHeader() {
//            return header;
//        }
//
//        public Request.Builder header(Map<String, Object> header) {
//            this.header = header;
//            return this;
//        }
//
//        public Map<String, Object> getRequest() {
//            return request;
//        }
//
//        public Request.Builder request(Map<String, Object> request) {
//            this.request = request;
//            return this;
//        }
//    }
//
//
//    public void get() {
//        this.setRequestType(RequestEnum.GET);
//    }
//
//    public void post(Map<String, Object> request) {
//        this.setRequestType(RequestEnum.POST);
//        this.setRequest(request);
//    }
//
//
//    public String getUri() {
//        return uri;
//    }
//
//    public Request setUri(String uri) {
//        this.uri = uri;
//        return this;
//    }
//
//    public Map<String, Object> getHeader() {
//        return header;
//    }
//
//    public Request setHeader(Map<String, Object> header) {
//        this.header = header;
//        return this;
//    }
//
//    public Map<String, Object> getRequest() {
//        return request;
//    }
//
//    public Request setRequest(Map<String, Object> request) {
//        this.request = request;
//        return this;
//    }
//
//    public RequestEnum getRequestType() {
//        return requestType;
//    }
//
//    public void setRequestType(RequestEnum requestType) {
//        this.requestType = requestType;
//    }
//}
