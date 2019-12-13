package com.zuoy.tools;


import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 不可逆加密工具类
 *
 * @author zuoy
 * @date 2019-08-14
 */
public class IrreversibleCypherUtil {

    /**
     * 根据加密次数进行循环加密，奇数循环次数为base64，偶数循环次数为md5
     * 加密次数：取得加密字符串的hash值的前两位，再根据hash前两位除2取绝对值 + 1
     *
     * @param encodeToString 加密字符串
     * @return 加密后的结果
     */
    public static String encodeToString(String encodeToString) {
        // 获取加密次数
        int count = getEncodeCount(encodeToString);
        for (int i = 0; i <= count; i++) {
            if ((i & 1) == 1) {
                // 循环次数奇数 base64加密
                encodeToString = base64EncodeToString(encodeToString);
            } else {
                // 循环次数偶数 md5加密
                encodeToString = md5EncodeToString(encodeToString);
            }
        }
        return encodeToString;
    }

    /**
     * base64加密
     *
     * @param encodeToString 加密字符串
     * @return
     */
    private static String base64EncodeToString(String encodeToString) {
        try {
            return new String(Base64Utils.encode(encodeToString.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            return encodeToString;
        }
    }

    /**
     * md5加密
     *
     * @param encodeToString 加密字符串
     * @return
     */
    private static String md5EncodeToString(String encodeToString) {
        try {
            return DigestUtils.md5DigestAsHex(encodeToString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return encodeToString;
        }
    }

    /**
     * 获取加密次数
     * 取得加密字符串的hash值的前两位
     * 再根据hash前两位除2取绝对值+1，
     *
     * @param encodeToString
     * @return
     */
    private static Integer getEncodeCount(String encodeToString) {
        // 获取hash前两位
        String hash = String.valueOf(encodeToString.hashCode());
        String tempHash = hash.length() == 1 ? hash.substring(1) : hash.substring(0, 2);
        // 返回加密次数
        return Math.abs(Integer.parseInt(tempHash) / 2) + 1;
    }
}
