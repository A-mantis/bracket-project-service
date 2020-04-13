package com.mantis.brac.common.feign;//package com.mantis.brac.common.feign;

import com.mantis.brac.common.exception.BracBusinessException;
import feign.Response;
import feign.Util;

import java.io.IOException;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/11 19:46
 * @history: 1.2020/4/11 created by wei.wang
 */
public class FeignUtil {

    public static String feignResponseBody(Response response) {
        try {
            return Util.toString(response.body().asReader());
        } catch (IOException e) {
            throw new BracBusinessException("获取返回ResponseBody失败！");
        }
    }

}
