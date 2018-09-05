package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;

import com.baizhi.entity.UserDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by aina on 2018/9/5.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Integer> queryUserNum() {
        List<Integer> list= Arrays.asList(7,15,30,90,183,365);
        List<Integer> list1=new ArrayList<>();
        for (Integer userNumber : list) {
            Integer integer = userDao.queryUerNum(userNumber);
            list1.add(integer);
        }
        return  list1;
    }

    @Override
    public Map<String, Object> queryUserMap() {
        Map<String, Object> map=new HashMap<>();
        List<UserDto> man = userDao.queryUserDto("男");
        map.put("man",man);
        List<UserDto> women = userDao.queryUserDto("女");
        map.put("women",women);
        return map;
    }

    @Override
    public void export(String titles, String params, HttpServletResponse response) {
        Workbook workbook = new HSSFWorkbook();
        //字体样式
        DataFormat dataFormat = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));
        /*标题行*/
        Sheet sheet = workbook.createSheet("user");
        Row row = sheet.createRow(0);
        String[] strs = titles.split(",");
        for (int i = 0; i < strs.length; i++) {
            row.createCell(i).setCellValue(strs[i]);
        }
        /*数据行*/
        String[] fileds = params.split(",");
        List<User> users = userDao.queryAll();
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            User user=users.get(i);
            /*获取类对象*/
            Class<? extends User> empClass = user.getClass();
            for (int j = 0; j < fileds.length; j++) {
                Cell cell = row1.createCell(j);
                /*获得方法名*/
                String methodName = "get" + fileds[j].substring(0, 1).toUpperCase() + fileds[j].substring(1);
                /*获取调用的方法对象*/
                try {
                    Method method = empClass.getMethod(methodName, null);
                    Object invoke = method.invoke(user, null);
                    if (invoke instanceof Date) {
                        sheet.setColumnWidth(j,21*256);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue((Date) invoke);
                    } else if(invoke instanceof Integer){
                        cell.setCellValue((int) invoke);
                    } else {
                        cell.setCellValue((String) invoke);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                 /*创建单元格并且填充内容*/
            }
        }
        UserServiceImpl.downFile(workbook,response);
    }
    public static void downFile(Workbook workbook ,HttpServletResponse response){
        //指定下载的文件名
        String a = new Date().getTime() + "userExcel.xls";
        String newName = null;
        try {
            //格式
            newName = new String(a.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置响应头
        response.setHeader("content-disposition", "attachment;filename=" + newName);
        //设置响应格式
        response.setContentType("application/vnd.ms-excel");
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void exportAll(HttpServletResponse response) throws Exception {
        /*创建工作薄对象*/
        Workbook workbook = new HSSFWorkbook();
        /*创建工作表对象*/
        Sheet sheet = workbook.createSheet("user");
        /* 第一个参数 第几列  第二个参数  列宽*/
        sheet.setColumnWidth(2, 26 * 256);
        /*创建行   参数：行下标*/
        Row row = sheet.createRow(0);
        /*修改日期样式*/
        DataFormat dataFormat = workbook.createDataFormat();
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(dataFormat.getFormat("yyyy年MM月dd天"));
        /*创建单元格样式*/
        CellStyle cellStyle = workbook.createCellStyle();
        /*居中*/
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
         /*修改字体*/
        Font font = workbook.createFont();
        font.setFontName("楷体");
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setItalic(true);
        cellStyle.setFont(font);
        String[] strs = {"编号", "头像","姓名","法名","性别","省份","城市","签名","手机号","密码","私盐","状态","注册日期"};
        for (int i = 0; i < strs.length; i++) {
            row.createCell(i).setCellValue(strs[i]);
        }
        /*数据行  查询数据库*/
        String[] args= {"id", "photoImg","name","dharamName","sex","province","city","sign","phoneNum","password","salt","status","regisDate"};
        final List<User> users = userDao.queryAll();
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            User user=users.get(i);
            /*获取类对象*/
            Class<? extends User> userClass = user.getClass();
            for (int j = 0; j < args.length; j++) {
                Cell cell = row1.createCell(j);
                /*获得方法名*/
                String methodName = "get" +args[j].substring(0, 1).toUpperCase() + args[j].substring(1);
                /*获取调用的方法对象*/
                Method method = userClass.getMethod(methodName, null);
                /*创建单元格并且填充内容*/
                Object invoke = method.invoke(user, null);
                if (invoke instanceof Date) {
                    sheet.setColumnWidth(j,21*256);
                    //日期格式的转换
                    cell.setCellStyle(cellStyle1);
                    cell.setCellValue((Date) invoke);
                } else if(invoke instanceof Integer){
                    cell.setCellValue((Integer) invoke);
                } else {
                    cell.setCellValue((String) invoke);
                }
            }
        }
        UserServiceImpl.downFile(workbook,response);
    }
}
