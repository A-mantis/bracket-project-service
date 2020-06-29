package com.mantis.brac.common.annotation;

import com.mantis.brac.config.BracAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/3 0:50
 * @history: 1.2020/4/3 created by wei.wang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({BracAutoConfiguration.class})
public @interface EnableBracket {
}
