package com.mantis.bracket.common.annotation;

import com.mantis.bracket.config.BracketAutoConfiguration;
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
@Import({BracketAutoConfiguration.class})
public @interface EnableBracket {
}
