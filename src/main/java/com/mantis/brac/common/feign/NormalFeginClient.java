package com.mantis.brac.common.feign;

import com.mantis.brac.pojo.User;
import feign.HeaderMap;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/13 17:00
 * @history: 1.2020/4/13 created by wei.wang
 */
@FeignClient(name = "NormalFeignClient", url = "")
public interface NormalFeginClient {
    @GetMapping("userValidGet")
    Response normalGet(@RequestParam("userId") String userId, @RequestParam("userName") String userName);

    @GetMapping("userValidGet")
    Response mapGet(@HeaderMap Map<String, Object> headerMap, @SpringQueryMap Map<String, Object> requestMap);

    @GetMapping("userValidGet")
    Response pojoGet(@HeaderMap Map<String, Object> headerMap, @SpringQueryMap User user);

    @PostMapping("testAspectUserValidPost")
    Response normalPost(@RequestBody Map<String, Object> request);

    @PostMapping("testAspectUserValidPost")
    Response pojoPost(@HeaderMap Map<String, Object> headerMap, @SpringQueryMap User user);
}
