package com.mantis.brac.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 13:00
 * @history: 1.2020/4/4 created by wei.wang
 */
public class FndLookupTypeRequest {
    @NotBlank(message = "值列表类型不能为空")
    private String lookupType;

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }
}
