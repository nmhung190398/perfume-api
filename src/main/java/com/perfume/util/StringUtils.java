package com.perfume.util;

import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class StringUtils {
    public static String format(String str, Map<String, String> param) {
        StrSubstitutor sub = new StrSubstitutor(param, "{{", "}}");
        String result = sub.replace(str);
        return result;
    }

    public static String URLEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
