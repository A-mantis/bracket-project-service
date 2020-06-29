package com.mantis.bracket.common.utils;


import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:字符串操作类
 * @author: wei.wang
 * @since: 2020/4/3 0:08
 * @history: 1.2020/4/3 created by wei.wang
 */
public class StringUtil {

    private StringUtil() {
    }

    private static Random random = new Random();

    private static final Pattern DB_NAME_TO_JAVA_NAME_PATTERN = Pattern.compile("(_[\\w])");

    /**
     * 获取UUID
     *
     * @return UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 移除字符串左边的0
     *
     * @param str
     * @return
     */
    public static String removeLeftZero(String str) {
        if (strEmpty(str)) {
            return null;
        }
        str = str.replaceAll("^(0*)", "");
        if (strEmpty(str)) {
            return null;
        }
        return str;
    }

    /**
     * 移除字符串右边的0
     *
     * @param str
     * @return
     */
    public static String removeRightZero(String str) {
        if (strEmpty(str)) {
            return null;
        }
        str = str.replaceAll("0*$", "");
        if (strEmpty(str)) {
            return null;
        }
        return str;
    }

    /**
     * 校验数字相等   2.0 2.00    2
     *
     * @param str
     * @param tar
     * @return
     */
    public static boolean numberEqual(String str, String tar) {
        try {
            return Double.parseDouble(str) == Double.parseDouble(tar);
        } catch (Exception e) {
            //如果抛出异常，返回False
            return false;
        }
    }

    /**
     * 移除字符串尾部的字符串
     *
     * @param target
     * @return
     */
    public static String removeTail(String target, int length) {
        if (strEmpty(target)) {
            return "";
        }
        if (target.length() < length) {
            return "";
        }
        if (length == 0) {
            return target;
        }
        return target.substring(0, target.length() - length);
    }

    /**
     * 判断字符是否为空
     *
     * @param value
     * @return
     */
    public static boolean strEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }


    /**
     * 获取随机指定长度字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        random.ints(length, 0, base.length()).forEach(x -> sb.append(base.charAt(x)));
        return sb.toString();
    }

    /**
     * 判断是否相等
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        return obj1 != null ? obj1.equals(obj2) : obj2 == null;
    }

    /**
     * 判断是否是数字
     *
     * @param obj
     * @return
     */
    public static boolean isNum(Object obj) {
        try {
            Integer.parseInt(obj.toString());
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    /**
     * 转换数据格式为int
     *
     * @param val
     * @return
     */
    public static Integer toInt(Object val) {
        if (val instanceof Double) {
            return ((Double) val).intValue();
        } else {
            return Integer.valueOf(val.toString());
        }
    }

    /**
     * 数据库字段名称转换为JAVA字段名称
     *
     * @param dbName
     * @return
     */
    public static String dbName2JavaName(String dbName) {
        dbName = dbName.toLowerCase();
        String s;
        for (Matcher m = DB_NAME_TO_JAVA_NAME_PATTERN.matcher(dbName); m.find(); dbName = dbName.replaceAll(s, s.substring(1).toUpperCase())) {
            s = m.group(0);
        }
        return dbName;
    }

    /**
     * 大写转下划线
     *
     * @param name
     * @return
     */
    public static String upper2Underline(String name) {
        return name.replaceAll("(?<=[a-z])(?=[A-Z])", "_");
    }

    /**
     * 删除后缀
     *
     * @param str
     * @param suffix
     * @return
     */
    public static String removeSuffix(String str, String suffix) {
        return !strEmpty(str) && !strEmpty(suffix) ? (str.endsWith(suffix) ? str.substring(0, str.length() - suffix.length()) : str) : str;
    }
}
