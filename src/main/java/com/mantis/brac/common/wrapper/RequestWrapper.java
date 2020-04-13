package com.mantis.brac.common.wrapper;

import com.alibaba.fastjson.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:19
 * @history: 1.2020/4/4 created by wei.wang
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    private static Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

    /**
     * 获取HttpServletRequest内容
     *
     * @param request
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, IOUtils.UTF8))) {
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            logger.info("RequestWrapper error : {}", ex.getMessage());
        }
        body = stringBuilder.toString();
    }

    /**
     * 获取输入流
     *
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes(IOUtils.UTF8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), IOUtils.UTF8));
    }
}
