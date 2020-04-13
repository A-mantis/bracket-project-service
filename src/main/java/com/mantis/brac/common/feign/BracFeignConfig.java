package com.mantis.brac.common.feign;

import com.mantis.brac.common.exception.BracBusinessException;
import com.mantis.brac.common.http.RequestEnum;
import feign.Contract;
import feign.Feign;
import feign.Response;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/11 15:42
 * @history: 1.2020/4/11 created by wei.wang
 */
@Component
@Import(FeignClientsConfiguration.class)
public class BracFeignConfig {

    private BracFeignClient bracFeignClient;
    private String uri;
    private RequestEnum requestType;
    private Map<String, Object> header;
    private Map<String, Object> request;

    @Autowired
    public BracFeignConfig(Encoder encoder, Decoder decoder) {
        bracFeignClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(BracFeignClient.class));
    }

    public BracFeignConfig get() {
        this.setRequestType(RequestEnum.GET);
        return this;
    }

    public BracFeignConfig post(Map<String, Object> request) {
        this.setRequestType(RequestEnum.POST);
        this.setRequest(request);
        return this;
    }

    /**
     * 执行调用方法
     *
     * @return
     */
    public Response execute() {
        switch (getRequestType()) {
            case GET:
                return bracFeignClient.get(findUri(getUri()), getHeader(), getRequest());
            case POST:
                return bracFeignClient.post(findUri(getUri()), getHeader(), getRequest());
            default:
                throw new BracBusinessException("请选择调用方式！");
        }
    }



    public String getUri() {
        return uri;
    }

    public BracFeignConfig setUri(String uri) {
        if (uri == null) {
            throw new BracBusinessException("uri == null");
        } else {
            this.uri = uri;
        }
        return this;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public BracFeignConfig setRequest(Map<String, Object> request) {
        this.request = request;
        return this;
    }

    public RequestEnum getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestEnum requestType) {
        this.requestType = requestType;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public BracFeignConfig setHeader(Map<String, Object> header) {
        this.header = header;
        return this;
    }

    /**
     * 获取请求URI
     *
     * @param uri
     * @return
     */
    private URI findUri(String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        throw new BracBusinessException("获取请求地址失败！");
    }

}
