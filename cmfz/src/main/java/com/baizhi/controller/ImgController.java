package com.baizhi.controller;

import com.baizhi.util.ImageUtil;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by aina on 2018/8/28.
 */
@Controller
@RequestMapping("/img")
public class ImgController {
    @RequestMapping("/img")
    public void img(HttpSession session, HttpServletResponse response){
        ImageUtil img=new ImageUtil();
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedImage buffImg = img.getBuffImg();
            String code = img.getCode();
            session.setAttribute("code",code);
            ImageIO.write(buffImg,"png",outputStream);
            System.out.println(code);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/image")
    public void image(HttpServletResponse response,HttpSession session){
        //1.获取验证码随机数
        String securityCode = SecurityCode.getSecurityCode();
        /**
         * 将验证码随机数保存到session中
         */
        session.setAttribute("serverCode", securityCode);
        //2.生成验证码图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        //3.响应到客户端
        try {
            OutputStream out = response.getOutputStream();
            /**
             * 参数一 ： 验证码图片对象
             * 参数二： 响应内容的格式
             * 参数三：输出字节流
             */
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
