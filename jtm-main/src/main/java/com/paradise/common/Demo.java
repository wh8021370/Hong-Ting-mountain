package com.paradise.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
    public static void main(String[] args) {
        String s1 = "{guid=123456}";
        String regex = "^\\{.*=\\w*}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =  pattern.matcher(s1);
        System.out.println(matcher.matches());
        matcher.start();
        matcher.end();
        while (matcher.find()){
            System.out.println(matcher.group());
        }

    }
}
