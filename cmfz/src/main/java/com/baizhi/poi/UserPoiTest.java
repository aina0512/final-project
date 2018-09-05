package com.baizhi.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by aina on 2018/9/4.
 * Export导出
 */
public class UserPoiTest {
    public static void main(String[] args) {
        /* 创建工作簿*/
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet("user");
        //第一个参数表示第几列 第二个参数 列宽*256
        sheet.setColumnWidth(2,26*256);
        /*创建行 参数：行下标*/
        HSSFRow row = sheet.createRow(0);
        //修改日期样式
        HSSFDataFormat dataFormat = workbook.createDataFormat();//日期格式
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        HSSFCellStyle cellStyle = workbook.createCellStyle();//样式对象
        cellStyle.setDataFormat(format);
        //创建单元格样式
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        //居中
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        //修改字体
        HSSFFont font = workbook.createFont();
        font.setFontName("楷书");
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setItalic(true);
        cellStyle1.setFont(font);
        String[] strs={"编号","姓名","生日"};
       for(int i=0;i<strs.length;i++){
           HSSFCell cell = row.createCell(i);
           cell.setCellStyle(cellStyle1);
           cell.setCellValue(strs[i]);
       }
       //查询数据库 数据行

        try {
            workbook.write(new FileOutputStream(new File("D:/user.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
