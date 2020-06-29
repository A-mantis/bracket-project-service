package com.mantis.bracket.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:运行时异常，如果发生，打印日志，不直接抛出
 * @author: wei.wang
 * @since: 2020/4/4 16:15
 * @history: 1.2020/4/4 created by wei.wang
 */
public class BracketRuntimeException extends RuntimeException {

    private static Logger logger = LoggerFactory.getLogger(BracketRuntimeException.class);

    public BracketRuntimeException() {

    }

    public BracketRuntimeException(String message) {
        logger.info(message);
    }
}
