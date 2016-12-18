package com.kaishengit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 将ISO8859-1的字符串转换为UTF-8
     */

    public static String isoToUtf8(String str){
        try {
            return new String(str.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("转换字符串{}时异常",str);
            throw new RuntimeException("转换字符串"+str+"时异常",e);
        }
    }
}
