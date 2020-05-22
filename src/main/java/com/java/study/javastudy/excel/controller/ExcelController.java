package com.java.study.javastudy.excel.controller;

import com.java.study.javastudy.excel.service.ExcelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname ExcelController
 * @Description
 * @Date 2020/5/21 16:04
 * @Author HXL
 */
@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @Resource
    private ExcelService excelService;

    @PostMapping("/importExcel")
    void importExcel(HttpServletRequest request){
        excelService.importExcel(request);
    }


    @RequestMapping("/exportExcel")
    void exportExcel(HttpServletResponse response){
        excelService.exportExcel(response);
    }



    @RequestMapping("/importExcel2")
    void importExcel2(HttpServletRequest request){
        excelService.importExcel2(request);
    }


    @RequestMapping("/exportExcel2")
    void exportExcel2(HttpServletResponse response){
        excelService.exportExcel2(response);
    }
}
