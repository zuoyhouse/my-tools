package com.zuoy.tools;

import com.zuoy.tools.enums.DatePatternEnum;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期帮助类
 *
 * @author zuoy 2019-07-19
 */
public class DateUtils {

    /**
     * LocalDate 转 Date
     *
     * @param localDate
     * @return
     */
    public static Date dateFrom(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date dateFrom(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * Date转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * Date转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone).toLocalDate();
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static Date nowDate() {
        return dateFrom(LocalDate.now());
    }

    /**
     * 获取当前时间
     *
     * @return hh:mm:ss
     */
    public static Date nowTime() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd hh:mm:ss
     */
    public static Date nowDateTime() {
        return dateFrom(LocalDateTime.now());
    }

    /**
     * 格式化日期
     *
     * @return yyyyMMdd
     */
    public static String nowDateUnSeparator() {
        return formatDate(DateTime.now().toDate(), DatePatternEnum.YYYYMMDD);
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String nowDateFormat() {
        return formatDate(DateTime.now().toDate(), DatePatternEnum.YYYY_MM_DD);
    }

    /**
     * 获取当前时间
     *
     * @return HH:mm:ss
     */
    public static String nowTimeFormat() {
        return formatDate(DateTime.now().toDate(), DatePatternEnum.HH_MM_SS);
    }

    /**
     * 获取当前日期
     *
     * @return 字符串yyyy-MM-dd HH:mm:ss
     */
    public static String nowDateTimeFormat() {
        return formatDate(DateTime.now().toDate(), DatePatternEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 根据日期格式，获取当前时间
     *
     * @param pattern 日期格式<br>
     *                <li>yyyy</li>
     *                <li>yyyy-MM-dd</li>
     *                <li>yyyy-MM-dd HH:mm:ss</li>
     *                <li>HH:mm:ss</li>
     * @return
     */
    public static String nowPatternFormat(DatePatternEnum pattern) {
        return formatDate(DateTime.now().toDate(), pattern);
    }

    /**
     * 根据日期格式，获取当前时间
     *
     * @param pattern 日期格式<br>
     *                <li>yyyy</li>
     *                <li>yyyy-MM-dd</li>
     *                <li>yyyy-MM-dd HH:mm:ss</li>
     *                <li>HH:mm:ss</li>
     * @return
     */
    public static String nowPatternFormat(String pattern) {
        DateTime dateTime = DateTime.now();
        return dateTime.toString(DateTimeFormat.forPattern(pattern));
    }

    /**
     * 格式化日期
     *
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return formatDate(date, DatePatternEnum.YYYY_MM_DD);
    }

    /**
     * 格式化时间
     *
     * @return HH:mm:ss
     */
    public static String formatTime(Date date) {
        return formatDate(date, DatePatternEnum.HH_MM_SS);
    }

    /**
     * 格式化日期
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DatePatternEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化日期
     *
     * @return yyyyMMdd
     */
    public static String formatDateUnSeparator(Date date) {
        return formatDate(date, DatePatternEnum.YYYYMMDD);
    }

    /**
     * 格式化日期
     *
     * @return HHmmss
     */
    public static String formatTimeUnSeparator(Date date) {
        return formatDate(date, DatePatternEnum.HHMMSS);
    }

    /**
     * 格式化日期
     *
     * @return yyyyMMddHHmmss
     */
    public static String formatDateTimeUnSeparator(Date date) {
        return formatDate(date, DatePatternEnum.YYYYMMDDHHMMSS);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy年MM月dd日
     */
    public static String formatDateChinese(Date date) {
        return formatDate(date, DatePatternEnum.YYYY_MM_DD_ZH);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy年MM月dd日HH时mm分
     */
    public static String formatDateTimeChinese(Date date) {
        return formatDate(date, DatePatternEnum.YYYY_MM_DD_HH_MM_SS_ZH);
    }

    /**
     * 格式化时间
     *
     * @return HH时mm分ss秒
     */
    public static String formatTimeChinese(Date date) {
        return formatDate(date, DatePatternEnum.HH_MM_SS_ZH);
    }

    /**
     * 格式化指定格式的日期，并返回已格式化的日期字符串。
     *
     * @param date    日期
     * @param pattern 日期字符串格式
     * @return
     */
    public static String formatDate(Date date, DatePatternEnum pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(DateTimeFormat.forPattern(pattern.getPattern()));
    }

    /**
     * 格式化指定格式的日期，并返回已格式化的日期字符串。
     *
     * @param date    日期
     * @param pattern 日期字符串格式
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(DateTimeFormat.forPattern(pattern));
    }

    /**
     * 解析yyyyMMdd格式日期
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static Date parseDateUnSeparator(String dateStr) {
        return parseDate(dateStr, DatePatternEnum.YYYYMMDD);
    }

    /**
     * 解析yyyy-MM-dd格式日期
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DatePatternEnum.YYYY_MM_DD);
    }

    /**
     * 解析HHmmss日期
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static Date parseTimeUnSeparator(String dateStr) {
        return parseDate(dateStr, DatePatternEnum.HHMMSS);
    }

    /**
     * 解析yyyy-MM-dd hh:mm:ss日期
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static Date parseDateTime(String dateStr) {
        return parseDate(dateStr, DatePatternEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 解析日期
     *
     * @param dateStr 日期字符串
     * @param pattern 日期字符串格式
     * @return
     */
    public static Date parseDate(String dateStr, DatePatternEnum pattern) {
        return DateTime.parse(dateStr, DateTimeFormat.forPattern(pattern.getPattern())).toDate();
    }

    /**
     * 解析日期
     *
     * @param dateStr 日期字符串
     * @param pattern 日期字符串格式
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        return DateTime.parse(dateStr, DateTimeFormat.forPattern(pattern)).toDate();
    }

    /**
     * 获取xxxx-xx-xx的日
     *
     * @param d
     * @return
     */
    public static int getDay(Date d) {
        DateTime dateTime = new DateTime(d);
        return dateTime.getDayOfMonth();
    }

    /**
     * 获取月份，1-12月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.getMonthOfYear();
    }

    /**
     * 获取19xx,20xx形式的年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.getYear();
    }

    /**
     * 去掉指定日期时间的时间，返回日期。
     *
     * @param date
     * @return yyyy-MM-dd
     * @author fangyb
     */
    public static Date getDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * 在传入时间基础上加一定天数
     *
     * @param date 日期
     * @param day  增加的天数,正数表示增加，负数表示减少
     * @return 日期的增加天数后的结果
     */
    public static Date addDay(Date date, int day) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusDays(day);
        return dateTime.toDate();
    }

    /**
     * 在传入时间基础上加一定月份
     *
     * @param date  日期
     * @param month 增加的月份,正数表示增加，负数表示减少
     * @return 日期的增加月份后的结果
     */
    public static Date addMonth(Date date, int month) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusMonths(month);
        return dateTime.toDate();
    }

    /**
     * 在传入时间基础上加一定年份
     *
     * @param date 日期
     * @param year 增加的年份,正数表示增加，负数表示减少
     * @return 日期的增加年份后的结果
     */
    public static Date addYear(Date date, int year) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusYears(year);
        return dateTime.toDate();
    }

    /**
     * 计算两个日期的相隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getBetweenDay(Date startDate, Date endDate) {
        DateTime startDay = new DateTime(startDate);
        DateTime endDay = new DateTime(endDate);
        return Days.daysBetween(startDay, endDay).getDays();
    }

    /**
     * 得到月的第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.dayOfMonth().withMinimumValue();
        return dateTime.toDate();

    }

    /**
     * 得到月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getMonthLastDay(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.dayOfMonth().withMaximumValue();
        return dateTime.toDate();
    }

    /**
     * 得到季度的第一天
     *
     * @param date
     * @return
     */
    public static Date getSeasonFirstDay(Date date) {
        DateTime dateTime = new DateTime(date);
        int curMonth = dateTime.getMonthOfYear();
        if (curMonth >= DateTimeConstants.JANUARY && curMonth <= DateTimeConstants.MARCH) {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.JANUARY);
        } else if (curMonth >= DateTimeConstants.APRIL && curMonth <= DateTimeConstants.JUNE) {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.APRIL);
        } else if (curMonth >= DateTimeConstants.JULY && curMonth <= DateTimeConstants.SEPTEMBER) {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.JULY);
        } else {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.OCTOBER);
        }
        dateTime = dateTime.dayOfMonth().withMinimumValue();
        return dateTime.toDate();
    }

    /**
     * 得到季度的最后一天
     *
     * @param date
     * @return
     */
    public static Date getSeasonLastDay(Date date) {
        DateTime dateTime = new DateTime(date);
        int curMonth = dateTime.getMonthOfYear();
        if (curMonth >= DateTimeConstants.JANUARY && curMonth <= DateTimeConstants.MARCH) {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.MARCH);
        } else if (curMonth >= DateTimeConstants.APRIL && curMonth <= DateTimeConstants.JUNE) {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.JUNE);
        } else if (curMonth >= DateTimeConstants.JULY && curMonth <= DateTimeConstants.SEPTEMBER) {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.SEPTEMBER);
        } else {
            dateTime = dateTime.withMonthOfYear(DateTimeConstants.DECEMBER);
        }
        dateTime = dateTime.dayOfMonth().withMaximumValue();
        return dateTime.toDate();
    }

    /**
     * 获取年第一天日期
     *
     * @return Date
     */
    public static Date getYearFirstDay(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.dayOfYear().withMinimumValue();
        return dateTime.toDate();
    }

    /**
     * 获取年最后一天日期
     *
     * @return Date
     */
    public static Date getYearLastDay(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.dayOfYear().withMaximumValue();
        return dateTime.toDate();
    }


}
