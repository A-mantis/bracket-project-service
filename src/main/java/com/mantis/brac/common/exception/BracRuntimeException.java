package com.mantis.brac.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 16:15
 * @history: 1.2020/4/4 created by wei.wang
 */
public class BracRuntimeException extends BaseException {

    private static Logger logger = LoggerFactory.getLogger(BracRuntimeException.class);

    /**
     * 自定义异常，状态码
     */
    public static final int STATUS_CODE=702;

    public BracRuntimeException() {

    }

    public BracRuntimeException(String message) {
        logger.info(message);
    }
}
