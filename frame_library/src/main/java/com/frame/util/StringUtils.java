package com.frame.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim()) || "null".equals(str)) {
            return false;
        }
        return true;
    }

	
	public static String getMonthEnglish(String month){
		String monthEnglish = "Jan";		
		if ("1".equals(month) || "01".equals(month)) {
			monthEnglish = "Jan";
		}else if("2".equals(month) || "02".equals(month)){
			monthEnglish = "Feb";
		}else if("3".equals(month) || "03".equals(month)){
			monthEnglish = "Mar";
		}else if("4".equals(month) || "04".equals(month)){
			monthEnglish = "Apr";
		}else if("5".equals(month) || "05".equals(month)){
			monthEnglish = "May";
		}else if("6".equals(month) || "06".equals(month)){
			monthEnglish = "Jun";
		}else if("7".equals(month) || "07".equals(month)){
			monthEnglish = "July";
		}else if("8".equals(month) || "08".equals(month)){
			monthEnglish = "Aug";
		}else if("9".equals(month) || "09".equals(month)){
			monthEnglish = "Sep";
		}else if("10".equals(month)){
			monthEnglish = "Oct";
		}else if("11".equals(month)){
			monthEnglish = "Nov";
		}else if("12".equals(month)){
			monthEnglish = "Dec";
		}
		return monthEnglish;
		
	}

    public static boolean checkEmail(String email){
        boolean flag;
        try{
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
    public static boolean isHttp(String url) {
        if (url.startsWith("http") || url.startsWith("https") || url.startsWith("Https") || url.startsWith("Http")) {
            return false;
        }
        return true;
    }

}
