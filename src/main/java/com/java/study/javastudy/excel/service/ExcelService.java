package com.java.study.javastudy.excel.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname ExcelService
 * @Description
 * @Date 2020/5/21 16:15
 * @Author HXL
 */
public interface ExcelService {

    void  importExcel(HttpServletRequest request);


    void exportExcel(HttpServletResponse response);


    void  importExcel2(HttpServletRequest request);


    void exportExcel2(HttpServletResponse response);
}
