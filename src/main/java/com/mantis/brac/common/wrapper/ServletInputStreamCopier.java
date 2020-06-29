package com.mantis.brac.common.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/5/29 11:19
 * @history: 1.2020/5/29 created by wei.wang
 */
public class ServletInputStreamCopier extends ServletInputStream {

    private InputStream cachedBodyInputStream;

    public ServletInputStreamCopier(byte[] cachedBody) {
        this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public int read() throws IOException {
        return cachedBodyInputStream.read();
    }

    @Override
    public boolean isFinished() {
        try {
            return cachedBodyInputStream.available() == 0;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }
}