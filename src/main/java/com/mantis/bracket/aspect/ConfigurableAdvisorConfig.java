package com.mantis.bracket.aspect;

import com.mantis.bracket.adapter.LogAspect;
import com.mantis.bracket.common.property.AopProperties;
import com.mantis.bracket.common.property.SecurityProperties;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/29 13:05
 * @history: 1.2020/6/29 created by wei.wang
 */
@Configuration
public class ConfigurableAdvisorConfig {

    @Autowired(required = false)
    private AopProperties aopProperties;

    @Autowired(required = false)
    private LogAspect logAspect;

    @Bean
    public AspectJExpressionPointcutAdvisor configurableAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
//        //是否开启AOP
//        if (aopProperties.getOpen()) {
            advisor.setExpression(aopProperties.getPointcut());
            advisor.setAdvice(new WebLogAspect(logAspect));
//        }
        return advisor;
    }
}
