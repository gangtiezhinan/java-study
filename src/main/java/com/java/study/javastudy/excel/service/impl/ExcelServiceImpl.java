package com.java.study.javastudy.excel.service.impl;

import com.java.study.javastudy.excel.entity.Student;
import com.java.study.javastudy.excel.entity.User;
import com.java.study.javastudy.excel.service.ExcelService;
import com.java.study.javastudy.excel.utils.ExcelReader;
import com.java.study.javastudy.excel.utils.ExcelUtil;
import com.java.study.javastudy.excel.utils.ExcelWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname ExcelServiceImpl
 * @Description
 * @Date 2020/5/21 16:15
 * @Author HXL
 */
@Service
public class ExcelServiceImpl implements ExcelService {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void importExcel(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile excel = multipartRequest.getFile("filename");

        try {
            List<Student> excelData = ExcelUtil.readExcelObject(excel, Student.class);
            //检查每列数据
            for (int i = 0; i < excelData.size(); i++) {
                excelData.get(i).setStatus(1);
                Date time = new Date();
                excelData.get(i).setCreateTime(time);
                excelData.get(i).setUpdateTime(time);
            }
            //批量导入
           logger.info("insert:     " +  excelData.toString());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
            // 1.读取Excel模板
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/export.xlsx");
            InputStream in = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(in);
            // 2.读取模板里面的所有Sheet
            XSSFSheet sheet = wb.getSheetAt(0);
            // 3.设置公式自动读取
            sheet.setForceFormulaRecalculation(true);
            // 4.向相应的单元格里面设置值
            XSSFRow row;

            // 5.得到第二行
            row = sheet.getRow(1);
            // 6.设置单元格属性值和样式
            row.getCell(1).setCellValue("张三");
            row.getCell(3).setCellValue("18");
            row.getCell(6).setCellValue("本科");
            row.getCell(8).setCellValue(new Date());

            row = sheet.getRow(2);
            row.getCell(1).setCellValue("1511xxxx234");
            row.getCell(3).setCellValue("广东");
            row.getCell(6).setCellValue("本科");

            row = sheet.getRow(3);
            row.getCell(1).setCellValue("180");
            row.getCell(3).setCellValue("已婚");
            row.getCell(6).setCellValue("深圳");
            row.getCell(8).setCellValue("2");

            row = sheet.getRow(4);
            row.getCell(1).setCellValue("60");
            row.getCell(3).setCellValue("中国");
            row.getCell(6).setCellValue("其它");
            row.getCell(8).setCellValue("备注");

            //单元格合并
            row = sheet.getRow(6);
            row.getCell(0).setCellValue("合并列");
            CellRangeAddress region = new CellRangeAddress(6, 6, 0, 5);
            sheet.addMergedRegion(region);

            //单元格设置背景色
            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            row.getCell(0).setCellStyle(style);

            //设置单元格边框
            row = sheet.getRow(8);
            XSSFCellStyle style2 = wb.createCellStyle();
            style2.setBorderBottom(BorderStyle.DOUBLE);
            style2.setBorderRight(BorderStyle.DOUBLE);
            style2.setBorderLeft(BorderStyle.DOUBLE);
            style2.setBorderTop(BorderStyle.DOUBLE);
            style2.setBottomBorderColor(IndexedColors.SKY_BLUE.getIndex());
            style2.setRightBorderColor(IndexedColors.SKY_BLUE.getIndex());
            row.getCell(0).setCellStyle(style2);

            // 7.设置sheet名称和单元格内容
            wb.setSheetName(0, "测试");

            String fileName = new String(("export-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                    .getBytes(), "UTF-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "No-cache");
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void importExcel2(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");
        // 检查前台数据合法性
        if (null == file || file.isEmpty()) {
            logger.warn("上传的Excel商品数据文件为空！上传时间：" + new Date());
            return;
        }

        try {
            // 解析Excel
            List<User> parsedResult = ExcelReader.readExcel(file);
            // todo 进行业务操作
            System.out.println(parsedResult);
        } catch (Exception e) {
            logger.warn("上传的Excel商品数据文件为空！上传时间：" + new Date());
            e.printStackTrace();
        }
    }

    @Override
    public void exportExcel2(HttpServletResponse response) {
        Workbook workbook = null;
        OutputStream out = null;
        try {


            // 生成Excel工作簿对象并写入数据
            List<User> users = new ArrayList<>();
            User user1 = new User();
            user1.setName("罗亚平");
            user1.setAge(25);
            user1.setJob("程序员");
            user1.setLocation("杭州");
            User user2 = new User();
            user2.setName("汪玉冰");
            user2.setAge(28);
            user2.setJob("清洁工");
            user2.setLocation("东北");
            users.add(user1);
            users.add(user2);
            workbook = ExcelWriter.exportData(users);

            // 写入Excel文件到前端
            String excelName = "示例Excel导出";
            String fileName = excelName + ".xlsx";
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8),"iso8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/x-download");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.flushBuffer();
            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (Exception e) {
            logger.warn("写入Excel过程出错！错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                logger.warn("关闭workbook或outputStream出错！");
            }
        }
    }
}
