package com.mantis.bracket.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/6/30 17:11
 * @history: 1.2020/6/30 created by wei.wang
 */
@Valid
@ConfigurationProperties(prefix = "spring.bracket.aop")
public class AopProperties {

    private Boolean open;

    @NotEmpty(message = "切点不能为空！")
    private String pointcut;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getPointcut() {
        return pointcut;
    }

    public void setPointcut(String pointcut) {
        this.pointcut = pointcut;
    }

    @Override
    public String toString() {
        return "AopProperties{" +
                "open=" + open +
                ", pointcut='" + pointcut + '\'' +
                '}';
    }
}
