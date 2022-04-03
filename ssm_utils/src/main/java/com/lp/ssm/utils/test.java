package com.lp.ssm.utils;

import java.util.Date;

/**
 * @author LiuPei
 * @date 2022/3/7 22:37
 */
public class test {

    public static void main(String[] args) {
        //创建时间对象
        Date date = new Date();
        System.out.println(date);

        //时间转字符串
        DateUtils dateUtils = new DateUtils();
        String dateString1 = DateUtils.dateToString(date, "yyyy-MM-dd");

        String dateString2 = DateUtils.dateToString(date, "yyyy-MM-dd");
        //打印出时间字符串
        System.out.println(dateString1);
        System.out.println(dateString2);
    }
}
