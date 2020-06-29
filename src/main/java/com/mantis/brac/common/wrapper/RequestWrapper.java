package com.mantis.brac.common.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/5/26 16:19
 * @history: 1.2020/5/26 created by wei.wang
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private byte[] cachedBody;

    private static Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

    /**
     * 获取HttpServletRequest内容
     *
     * @param request
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            InputStream requestInputStream = request.getInputStream();
            this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);
        } catch (IOException ex) {
            logger.info("RequestWrapper error : {}", ex.getMessage());
        }
    }

    @Override
    public ServletInputStream getInputStream(){
        return new ServletInputStreamCopier(this.cachedBody);
    }

    @Override
    public BufferedReader getReader(){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }
}
