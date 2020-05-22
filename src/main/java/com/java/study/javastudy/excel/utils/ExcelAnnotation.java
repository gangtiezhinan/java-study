package com.java.study.javastudy.excel.utils;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author HXL
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {

    /**
     * 列索引
     *
     * @return
     */
     int columnIndex() default 0;

    /**
     * 列名
     *
     * @return
     */
     String columnName() default "";

}
