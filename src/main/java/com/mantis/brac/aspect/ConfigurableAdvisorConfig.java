package com.mantis.brac.aspect;

import com.mantis.brac.adapter.LogAspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${bracket.aspect.pointcut}")
    private String pointcut;

    @Autowired
    private LogAspect logAspect;

    @Bean
    public AspectJExpressionPointcutAdvisor configurableAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(pointcut);
        advisor.setAdvice(new WebLogAspect(logAspect));
        return advisor;
    }
}
