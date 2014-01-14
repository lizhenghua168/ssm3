package com.worlk.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by XSF on 13-12-19.
 */
public class CompanyListExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> stringObjectMap,
                                      HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {
        //Excel文档名称必须编码为iso8859-1，否则会显示乱码
        httpServletResponse.setHeader("Content-Disposition","inline;filename="+
                new String("公司列表".getBytes(), "iso8859-1"));
        HSSFSheet sheet = hssfWorkbook.createSheet("users");
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("公司名称");
        header.createCell(1).setCellValue("公司地址");
        header.createCell(2).setCellValue("公司资质");
    }
}
