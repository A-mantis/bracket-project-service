package com.mantis.bracket.common.utils;

import com.mantis.bracket.common.constant.StaticProperties;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:IP操作类
 * @author: wei.wang
 * @since: 2020/5/26 0:08
 * @history: 1.2020/5/26 created by wei.wang
 */
public class AddressUtil {

    /**
     * @Description:获取IP地址
     * @author: wei.wang
     * @since: 2020/5/26 15:13
     * @history: 1.2020/5/26 created by wei.wang
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !StaticProperties.UNKNOWN_STR.equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !StaticProperties.UNKNOWN_STR.equalsIgnoreCase(ip)) {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        if (ip == null || ip.length() == 0 || StaticProperties.UNKNOWN_STR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || StaticProperties.UNKNOWN_STR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || StaticProperties.UNKNOWN_STR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || StaticProperties.UNKNOWN_STR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || StaticProperties.UNKNOWN_STR.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}  
