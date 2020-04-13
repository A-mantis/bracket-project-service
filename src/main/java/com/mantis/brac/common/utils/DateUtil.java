package com.mantis.brac.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/3 0:08
 * @history: 1.2020/4/3 created by wei.wang
 */
public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DataUtil.class);

    /**
     * 格林时间：Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间) 格式转换
     *
     * @param datdString
     * @return
     */
    public static String parseTime(String datdString) {
        datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date dateTrans = null;
        try {
            dateTrans = format.parse(datdString);
            return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(dateTrans).replace("-", "/");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datdString;
    }

    /**
     * 获取当前时间
     *
     * @param dateFormat
     * @return
     */
    public static String getCurrentDate(String dateFormat) {
        //当前时间
        Date dNow = new Date();
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋给日历
        calendar.setTime(dNow);
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(dNow);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime());
        } catch (Exception e) {
            logger.info("date2TimeStamp {}", e.getMessage());
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }


}
