package com.mantis.bracket.config;

import com.mantis.bracket.common.property.AopProperties;
import com.mantis.bracket.common.property.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/28 14:43
 * @history: 1.2020/6/28 created by wei.wang
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class, AopProperties.class})
@ComponentScan(basePackages = {"com.mantis.bracket"})
public class BracketAutoConfiguration {
}
