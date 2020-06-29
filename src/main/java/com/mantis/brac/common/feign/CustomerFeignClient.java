package com.mantis.brac.common.feign;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.Map;


/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/10 15:10
 * @history: 1.2020/4/10 created by wei.wang
 */
@FeignClient(name = "CustomerFeignClient")
public interface CustomerFeignClient {

    @RequestLine("GET")
    Response get(URI baseUri, @HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> requestMap);

    @RequestLine("POST")
    Response post(URI baseUri, @HeaderMap Map<String, Object> headerMap, @RequestBody Map<String, Object> request);
}

