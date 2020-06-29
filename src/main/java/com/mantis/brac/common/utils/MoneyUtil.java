package com.mantis.brac.common.utils;


import com.mantis.brac.common.exception.BracBusinessException;

/**
 * @Description:金额转换工具类，只支持到分
 * @author: wei.wang
 * @since: 2020/5/20 12:39
 * @history: 1.2020/5/20 created by wei.wang
 */
public class MoneyUtil {

    private MoneyUtil() {
    }

    /**
     * 金额转大写
     *
     * @param money
     * @return
     */
    public static String toChinaUpper(String money) {
        boolean lessZero = false;
        if (money.startsWith("-")) {
            money = money.substring(1);
            lessZero = true;
        }
        moneyFormatValid(money);

        String[] part = money.split("\\.");
        String decimalData = part.length > 1 ? part[1] : "";
        //替换前置0
        String integerData = replaceLeftZero(part[0]);


        StringBuilder integer = new StringBuilder();
        for (int i = 0; i < integerData.length(); i++) {
            char perchar = integerData.charAt(i);
            integer.append(upperNumber(perchar));
            integer.append(upperNumber(integerData.length() - i - 1));
        }
        StringBuilder decimal = new StringBuilder();
        if (part.length > 1 && !"00".equals(decimalData)) {
            int length = Math.min(decimalData.length(), 2);
            for (int i = 0; i < length; i++) {
                char perchar = decimalData.charAt(i);
                decimal.append(upperNumber(perchar));
                if (i == 0) {
                    decimal.append('角');
                }
                if (i == 1) {
                    decimal.append('分');
                }
            }
        }
        String result = integer.toString() + decimal.toString();
        result = dispose(result);
        if (lessZero && !"零圆整".equals(result)) {
            result = "负" + result;
        }
        return result;
    }

    /**
     * 移除左侧的0
     *
     * @param integerData
     * @return
     */
    private static String replaceLeftZero(String integerData) {
        if (integerData.matches("^0+$")) {
            return "0";
        } else if (integerData.matches("^0+(\\d+)$")) {
            return integerData.replaceAll("^0+(\\d+)$", "$1");
        }
        return integerData;
    }

    /**
     * 校验金额格式
     *
     * @param money
     */
    private static void moneyFormatValid(String money) {
        if (!money.matches("^[0-9]*$|^0+\\.[0-9]+$|^[1-9]+[0-9]*$|^[1-9]+[0-9]*.[0-9]+$")) {
            throw new BracBusinessException("金额格式错误！");
        }
    }

    /**
     * 数据转大写
     *
     * @param number
     * @return
     */
    private static char upperNumber(char number) {
        switch (number) {
            case '0':
                return '零';
            case '1':
                return '壹';
            case '2':
                return '贰';
            case '3':
                return '叁';
            case '4':
                return '肆';
            case '5':
                return '伍';
            case '6':
                return '陆';
            case '7':
                return '柒';
            case '8':
                return '捌';
            case '9':
                return '玖';
            default:
                return '0';
        }
    }

    private static char upperNumber(int index) {
        int realIndex = index % 9;
        if (index > 8) {//亿过后进入回归,之后是拾,佰...
            realIndex = (index - 9) % 8;
            realIndex = realIndex + 1;
        }
        switch (realIndex) {
            case 0:
                return '圆';
            case 1:
                return '拾';
            case 2:
                return '佰';
            case 3:
                return '仟';
            case 4:
                return '万';
            case 5:
                return '拾';
            case 6:
                return '佰';
            case 7:
                return '仟';
            case 8:
                return '亿';
            default:
                return '0';
        }
    }

    private static String dispose(String result) {
        result = result.replaceAll("0", "");//处理
        result = result.replaceAll("零仟零佰零拾|零仟零佰|零佰零拾|零仟|零佰|零拾", "零");
        result = result.replaceAll("零+", "零").replace("零亿", "亿");
        result = result.matches("^.*亿零万[^零]仟.*$") ? result.replace("零万", "零") : result.replace("零万", "万");
        result = result.replace("亿万", "亿");
        //处理小数
        result = result.replace("零角", "零").replace("零分", "");
        result = result.replaceAll("(^[零圆]*)(.+$)", "$2");
        result = result.replaceAll("(^.*)([零]+圆)(.+$)", "$1圆零$3");

        //处理整数单位
        result = result.replaceAll("圆零角零分|圆零角$|圆$|^零$|圆零$|零圆$", "圆整");
        result = result.replaceAll("^圆整$", "零圆整");
        result = result.replaceAll("零+", "零");

        return result;
    }

}