package com.mantis.brac.common.feign;

import com.mantis.brac.common.exception.BracBusinessException;
import feign.Feign;
import feign.Response;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
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
public class CustomerFeignConfig {

    private CustomerFeignClient bracFeignClient;
    private String uri;
    private Enum requestType;
    private Map<String, Object> header;
    private Map<String, Object> request;

    /**
     * 在构造函数中用 @Autowired 引入自定义参数和编解码器
     *
     * @param encoder
     * @param decoder
     */
    @Autowired
    public CustomerFeignConfig(Encoder encoder, Decoder decoder) {
        bracFeignClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(CustomerFeignClient.class));
    }

    public CustomerFeignConfig get() {
        this.setRequestType(HttpMethod.GET);
        return this;
    }

    public CustomerFeignConfig post() {
        this.setRequestType(HttpMethod.POST);
        return this;
    }

    /**
     * 执行调用方法
     *
     * @return
     */
    public Response execute() {
        if (HttpMethod.GET.equals(getRequestType())) {
            return bracFeignClient.get(findUri(getUri()), getHeader(), getRequest());
        } else if (HttpMethod.POST.equals(getRequestType())) {
            return bracFeignClient.post(findUri(getUri()), getHeader(), getRequest());
        }
        throw new BracBusinessException("请选择调用方式！");
    }


    public String getUri() {
        return uri;
    }

    public CustomerFeignConfig setUri(String uri) {
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

    public CustomerFeignConfig setRequest(Map<String, Object> request) {
        this.request = request;
        return this;
    }

    public Enum getRequestType() {
        return requestType;
    }

    public void setRequestType(Enum requestType) {
        this.requestType = requestType;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public CustomerFeignConfig setHeader(Map<String, Object> header) {
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
