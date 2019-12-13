package com.zuoy.tools.enums;

/**
 * 日期格式枚举类
 *
 * @author zuoy
 */
public enum DatePatternEnum {
    // CPD-Ignore-On
    YYYY_MM("yyyy-MM"),
    YYYYMM("yyyyMM"),
    YYYY_MM_ZH("yyyy年MM月"),

    YYYY_MM_DD("yyyy-MM-dd"),
    YYYYMMDD("yyyyMMdd"),
    YYYY_MM_DD_ZH("yyyy年MM月dd日"),

    HH_MM_SS("HH:mm:ss"),
    HHMMSS("HHmmss"),
    HH_MM_SS_ZH("HH时mm分ss秒"),

    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    YYYY_MM_DD_HH_MM_SS_ZH("yyyy年MM月dd日 HH时mm分ss秒"),

    YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
    YYYYMMDDHHMMSSS("yyyyMMddHHmmSSS"),

    YYYY_MM_DD_HH_MM_ZH("yyyy年MM月dd日HH时mm分"),

    YYMMDD("yyMMdd"),

    // CPD-Ignore-Off
    ;
    private String pattern;

    DatePatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
