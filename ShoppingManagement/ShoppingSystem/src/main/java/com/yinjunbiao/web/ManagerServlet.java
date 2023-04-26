package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.pojo.GoodsConsultations;
import com.yinjunbiao.pojo.GoodsReply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
import com.yinjunbiao.service.ManagerService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import com.yinjunbiao.util.UploadUtil;
import io.jsonwebtoken.Claims;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/manager/*")
@Scope("singleton")
@MultipartConfig
public class ManagerServlet extends BaseServlet{

    private GoodsService goodsService = ((GoodsService) ApplicationUtil.getApplicationContext().getBean("goodsService"));

    private ManagerService managerService = ((ManagerService) ApplicationUtil.getApplicationContext().getBean("managerService"));

    /**
     * 管理员入口
     * @param request
     * @param response
     * @throws IOException
     */
    public void entrance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        try {
            Claims claims = JwtUtil.parseJWT(token);
            Integer identify = (Integer) claims.get("identify");
            ResultSet resultSet = null;
            if (identify == 2){
                resultSet = ResultSet.success();
            }else {
                resultSet = ResultSet.error();
            }
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 删除商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Goods goods = JSON.parseObject(s, Goods.class);
        ResultSet resultSet = goodsService.deleteGoods(goods.getId());
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 删除回复
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteReply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        GoodsReply goodsReply = JSON.parseObject(s, GoodsReply.class);
        ResultSet resultSet = goodsService.deleteReply(goodsReply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 删除评论
      * @param request
     * @param response
     * @throws IOException
     */
    public void deleteAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        GoodsConsultations goodsConsultations = JSON.parseObject(s, GoodsConsultations.class);
        ResultSet resultSet = goodsService.deleteConsultations(goodsConsultations);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 查找店铺申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void shopApply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultSet resultSet = managerService.selectShopApply();
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 同意店铺申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void agreeShop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Apply apply = JSON.parseObject(s, Apply.class);
        ResultSet resultSet = managerService.agreeShopApply(apply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 拒绝店铺申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void disagreeShop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Apply apply = JSON.parseObject(s, Apply.class);
        ResultSet resultSet = managerService.disagreeShopApply(apply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 查看商品商家申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void pushApply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultSet resultSet = managerService.selectPushApply();
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 同意推送商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void agreeGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        PushGood pushGood = JSON.parseObject(s, PushGood.class);
        ResultSet resultSet = managerService.agreePushGoods(pushGood);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 拒绝推送商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void disagreeGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        PushGood pushGood = JSON.parseObject(s, PushGood.class);
        ResultSet resultSet = managerService.disagreePushGoods(pushGood);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }



    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{

            Part imageUrl = request.getPart("file");
            InputStream inputStream = imageUrl.getInputStream();
            String upload = UploadUtil.upload(inputStream);
            System.out.println(upload);

//            OutputStream os = new FileOutputStream("C:\\Users\\28246\\Desktop\\source\\a.jpg");
//            byte[] bytes = new byte[1024];
//            int read;
//            while ((read = inputStream.read(bytes)) != -1){
//                os.write(bytes,0,read);
//            }
//            os.close();
            inputStream.close();

        }catch (Exception e){

        }
    }
   /* private void FileProcess(Part part) throws IOException {
        if(part.getName().equals("fileUploader")){
            String cd = part.getHeader("Content-Disposition");
            String[] cds = cd.split(";");
            String filename = cds[2].substring(cds[2].indexOf("=")+1).substring(cds[2].lastIndexOf("//")+1).replace("\"", "");
            String ext = filename.substring(filename.lastIndexOf(".")+1);


            InputStream is = part.getInputStream();

            if(Arrays.binarySearch(ImageIO.getReaderFormatNames(),ext) >= 0){
                imageProcess(filename, ext, is);
            }
            else{
                commonFileProcess(filename, is);
            }
        }
    }
    private void commonFileProcess(String filename, InputStream is) {
        FileOutputStream fos = null;
        try{
            fos=new FileOutputStream(new File(getClass().getResource("/").getPath()+filename));
            int b = 0;
            while((b = is.read())!=-1){
                fos.write(b);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void imageProcess(String filename, String ext, InputStream is) throws IOException {
        Iterator<ImageReader> irs = ImageIO.getImageReadersByFormatName(ext);
        ImageReader ir = irs.hasNext()?irs.next():null;
        if(ir == null){
            return;
        }
        ir.setInput(ImageIO.createImageInputStream(is));//必须转换为ImageInputStream，否则异常

        ImageReadParam rp = ir.getDefaultReadParam();
        Rectangle rect = new Rectangle(0,0,200,200);
        rp.setSourceRegion(rect);

        int imageNum = ir.getNumImages(true);//allowSearch必须为true，否则有些图片格式imageNum为-1。

        for(int imageIndex = 0;imageIndex < imageNum;imageIndex++){
            BufferedImage bi = ir.read(imageIndex,rp);
            ImageIO.write(bi, ext, new File(getClass().getResource("/").getPath()+filename));
        }
    }*/


}
