package com.zuoy.tools;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * SQL脚本工具类
 *
 * @author zuoy
 * @since 1.0
 */
public class SqlUtil {

    /**
     * 构造in()查询条件，如果in()中长度超过1000则自动or in()附加 <br>
     * values为null 返回1&lt;&gt;1 valus为{null,null} 返回1&lt;&gt;1
     *
     * @param valueList 值集合
     * @param fieldName 字段名
     * @return (fieldName in ( 1, 2, 3, 4)) 或者 (fieldName in(1,2) or fieldName in(3,4))
     * @since 2016-08-24
     */
    public static String getInSql(List valueList, String fieldName) {
        Object[] values = null;
        if (!CollectionUtils.isEmpty(valueList)) {
            values = valueList.toArray();
        }
        return getInSql(values, fieldName);
    }

    /**
     * 构造not in()查询条件，如果not in()中长度超过1000则自动and not in()附加 <br>
     * values为null 返回1=1 values为{null,null} 返回1=1
     *
     * @param valueList 值集合
     * @param fieldName 字段名
     * @return (fieldName not in ( 1, 2, 3, 4)) 或者 (fieldName not in(1,2) and fieldName not in(3,4))
     * @since 2016-08-24
     */
    public static String getNotInSql(List valueList, String fieldName) {
        Object[] values = null;
        if (!CollectionUtils.isEmpty(valueList)) {
            values = valueList.toArray();
        }
        return getNotInSql(values, fieldName);
    }

    /**
     * 构造in()查询条件，如果in()中长度超过1000则自动or in()附加 <br>
     * values为null 返回1&lt;&gt;1 values为{null,null} 返回1&lt;&gt;1
     *
     * @param values    值集合
     * @param fieldName 字段名
     * @return (fieldName not in ( 1, 2, 3, 4)) 或者 (fieldName not in(1,2) and fieldName not in(3,4))
     * @since 2016-08-24
     */
    public static String getInSql(Object[] values, String fieldName) {
        return getInOrNotInSql(values, fieldName, false);
    }

    /**
     * 构造in()查询条件，如果in()中长度超过1000则自动or in()附加 <br>
     * values为null 返回1=1 values为{null,null} 返回1=1
     *
     * @param values    值集合
     * @param fieldName 字段名
     * @return (fieldName not in ( 1, 2, 3, 4)) 或者 (fieldName not in(1,2) and fieldName not in(3,4))
     * @since 2016-08-24
     */
    public static String getNotInSql(Object[] values, String fieldName) {
        return getInOrNotInSql(values, fieldName, true);
    }

    /**
     * 构造in()查询条件，如果in()中长度超过1000则自动or in()附加 <br>
     * values 返回1&lt;&gt;1 values{null,null} 返回1&lt;&gt;1
     *
     * @param values    值集合
     * @param fieldName 字段名
     * @param isNotIn   sql是否notIn
     * @return (fieldName in ( 1, 2, 3, 4)) 或者 (fieldName in(1,2) or fieldName in(3,4)) 或者 (fieldName not in(1,2,3,4)) 或者 (fieldName not in(1,2) and fieldName not in(3,4))
     * @since 2016-08-24
     */
    private static String getInOrNotInSql(Object[] values, String fieldName, boolean isNotIn) {
        Assert.hasText(fieldName, "SqlUtil fieldName param is null");
        String otherSql = isNotIn ? " 1=1 " : " 1<>1 ";
        if (values == null || values.length == 0) {
            return otherSql;
        }
        StringBuilder sql = new StringBuilder();
        String inOrNotIn = isNotIn ? " not in " : " in ";
        String concatenationOperator = isNotIn ? " and " : " or ";
        sql.append("( ").append(fieldName).append(inOrNotIn).append("( ");
        int num = 1;
        boolean flag = false;
        for (Object value : values) {
            if (value == null) {
                continue;
            }
            if (num == 1000) {
                sql.append(")").append(concatenationOperator).append(fieldName).append(inOrNotIn).append("( ");
                num = 1;
            }
            sql.append(num == 1 ? "" : ",");
            if (value instanceof Character) {
                sql.append("'").append(value).append("'");
            } else {
                sql.append(value);
            }
            num++;
            flag = true;
        }
        sql.append(" ))");
        return flag ? sql.toString() : otherSql;
    }
}
