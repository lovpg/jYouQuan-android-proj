package com.lbsm.kdang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * date: 2016/10/12.
 */

public class StringUtil {

    //不用的方法 参见 TextUtils.isEmpty()
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }


    public static String getMonthEnglish(String month) {
        if (month == null) {
            throw new NullPointerException("month string must not be null");
        }
        String monthEnglish = "Jan";
        if ("1".equals(month) || "01".equals(month)) {
            monthEnglish = "Jan";
        } else if ("2".equals(month) || "02".equals(month)) {
            monthEnglish = "Feb";
        } else if ("3".equals(month) || "03".equals(month)) {
            monthEnglish = "Mar";
        } else if ("4".equals(month) || "04".equals(month)) {
            monthEnglish = "Apr";
        } else if ("5".equals(month) || "05".equals(month)) {
            monthEnglish = "May";
        } else if ("6".equals(month) || "06".equals(month)) {
            monthEnglish = "Jun";
        } else if ("7".equals(month) || "07".equals(month)) {
            monthEnglish = "July";
        } else if ("8".equals(month) || "08".equals(month)) {
            monthEnglish = "Aug";
        } else if ("9".equals(month) || "09".equals(month)) {
            monthEnglish = "Sep";
        } else if ("10".equals(month)) {
            monthEnglish = "Oct";
        } else if ("11".equals(month)) {
            monthEnglish = "Nov";
        } else if ("12".equals(month)) {
            monthEnglish = "Dec";
        } else {
            throw new IllegalArgumentException("month string error");
        }
        return monthEnglish;

    }

    public static boolean checkEmail(String email) {
        boolean flag;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean checkMobile(String mobile) {
//        if (mobile == null) {
//            return false;
//        }
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    public static boolean isHttp(String url) {
        if (url == null) {
            return false;
        }
        if (url.startsWith("http") || url.startsWith("https") || url.startsWith("Https") || url.startsWith("Http")) {
            return true;
        }
        return false;
    }

}








