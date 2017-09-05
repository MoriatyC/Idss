package com.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtiles {
    public static String splitByField(String s, String field) {
        String regex = field + "=(\\S+),";
        String regex2 = field + "=(\\S+)\\)";
        Pattern p = Pattern.compile(regex);
        Pattern p2 = Pattern.compile(regex2);
        Matcher m = p.matcher(s);
        Matcher m2 = p2.matcher(s);
        if (m.find())
        {
            return m.group(1);
        }
        if (m2.find()) {
            return m2.group(1);
        }
        return "fail";
    }
}
