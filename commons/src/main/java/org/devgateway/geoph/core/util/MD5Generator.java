package org.devgateway.geoph.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author dbianco
 *         created on jul 28 2016.
 */
public class MD5Generator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Generator.class);
    private static final String MD5 = "MD5";

    public static String getMD5(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(MD5);
            byte[] array = md.digest(normalize(input).getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            LOGGER.error("error: " + e.getMessage());
        }
        return null;
    }

    private static String normalize(String str){
        String ret = "";
        if(StringUtils.isNotBlank(str)) {
            try {
                ret = URLDecoder.decode(str.replaceAll("&.{2,6};", "").replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8")
                        .replaceAll("<(.|\n)*?>", "")
                        .toUpperCase()
                        .replaceAll("\t|\r|\n", "")
                        .replaceAll(" ", "")
                        .trim();
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("error: " + e.getMessage());
            }
        }
        return ret;
    }
}
