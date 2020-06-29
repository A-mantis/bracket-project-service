package com.mantis.brac.config;

import com.mantis.brac.common.property.BracProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/28 14:43
 * @history: 1.2020/6/28 created by wei.wang
 */
@Configuration
@EnableConfigurationProperties(BracProperties.class)
public class BracAutoConfiguration {

}
