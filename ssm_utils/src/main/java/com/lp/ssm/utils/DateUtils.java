package com.lp.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * @author LiuPei
 * @date 2022/3/7 22:18
 */
public class DateUtils {

    //日期转化成字符串
    public static String dateToString(Date date, String patt) {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    //字符串转化成日期
    public static Date stringToDate(String str, String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }



}
