package cn.com.blueline.web;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.utils.CommonUtils;
import cn.com.blueline.utils.OperateImageUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/fileUpload"})
public class FileUploadController
{
  @RequestMapping(value={"/up"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
  @ResponseBody
  public ResultWithJson up(@RequestParam("imgurl") MultipartFile thumbnails, HttpServletRequest request)
  {
    try
    {
      if (thumbnails == null) {
        return new ResultWithJson("1", "文件不能为空");
      }
      if (thumbnails.getSize() > 4096000L) {
        return new ResultWithJson("1", "文件不能超过4M");
      }
      String suffix = thumbnails.getOriginalFilename().substring(
        thumbnails.getOriginalFilename().lastIndexOf(".") + 1);
      if (!suffix.equals("jpg")) {
        return new ResultWithJson("1", "文件格式必须为JPG");
      }
      BufferedImage bi = ImageIO.read(thumbnails.getInputStream());

      String filPath = request.getServletContext().getRealPath("/") + 
        "uploads/";
      File tempFilePath = new File(filPath);
      if (!tempFilePath.exists()) {
        tempFilePath.mkdir();
      }
      String currentTime = CommonUtils.getCurrentTimetoString();
      String fileName = currentTime + "." + suffix;
      File tempFile = new File(filPath + fileName);
      System.out.println(tempFile);
      thumbnails.transferTo(tempFile);
      return new ResultWithJson("0", "http://www.fusingpoint.com/weOnes/uploads/" + currentTime + "." + suffix);
    } catch (IOException e) {
      e.printStackTrace();
    }return new ResultWithJson("1", "error");
  }
  @RequestMapping(value={"/upload"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
  @ResponseBody
  public ResultWithJson doUpload(@RequestParam("uploadFile") MultipartFile thumbnails, HttpSession session, HttpServletRequest request, HttpServletResponse response) { System.out.println("************进入上传方法********************************");
    int x1 = Integer.parseInt(request.getParameter("x1"));
    int y1 = Integer.parseInt(request.getParameter("y1"));

    int w = Integer.parseInt(request.getParameter("w"));
    int h = Integer.parseInt(request.getParameter("h"));

    int divWidth = Integer.parseInt(request.getParameter("divWidth"));

    ResultWithJson resultWithJson = null;
    try
    {
      BufferedImage bi = ImageIO.read(thumbnails.getInputStream());
      int width = bi.getWidth();
      int height = bi.getHeight();
      System.out.println("图片的高度:" + height);

      double bili = (double)width / divWidth;
      System.out.println("前端压缩图片比例:" + width + "/" + divWidth + "=" + bili);
      x1 = (int)(x1 * bili);
      y1 = (int)(y1 * bili);
      w = (int)(w * bili);
      h = (int)(h * bili);
      String suffix = thumbnails.getOriginalFilename().substring(thumbnails.getOriginalFilename().lastIndexOf(".") + 1);
      String filPath = request.getServletContext().getRealPath("/") + "uploads/";
      File tempFilePath = new File(filPath);
      if (!tempFilePath.exists()) {
        tempFilePath.mkdir();
      }
      String currentTime = CommonUtils.getCurrentTimetoString();
      String fileName = currentTime + "." + suffix;
      File tempFile = new File(filPath + fileName);
      System.out.println(tempFile);
      thumbnails.transferTo(tempFile);

      String readImageFormat = "jpg";
      String writeImageFormat = "jpg";
      currentTime = CommonUtils.getCurrentTimetoString();
      String toPath = filPath + currentTime + "." + suffix;
      if (OperateImageUtil.cutImage(filPath + fileName, toPath, x1, y1, w, h, readImageFormat, writeImageFormat)) {
        System.out.println("图片裁剪成功:" + toPath);
        List dataList = new ArrayList();
        dataList.add("<script>document.domain ='www.wesaas.com';</script>");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        resultWithJson = new ResultWithJson("0", "http://www.fusingpoint.com/weOnes/uploads/" + currentTime + "." + suffix, dataList);
        return resultWithJson;
      }
      resultWithJson = new ResultWithJson("1", "后台裁剪图片失败");
      return resultWithJson;
    }
    catch (IOException e) {
      resultWithJson = new ResultWithJson("1", "上传图片过程中失败");
      e.printStackTrace();
    }return resultWithJson; } 
  @RequestMapping(value={"/uploadPhp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
  @ResponseBody
  public ResultWithJson doUploadPhp(HttpServletRequest request, HttpServletResponse response) throws IOException { ResultWithJson resultWithJson = null;
    System.out.println("************php 进入上传方法********************************");
    int x1 = Integer.parseInt(request.getParameter("x1"));
    int y1 = Integer.parseInt(request.getParameter("y1"));
    int w = Integer.parseInt(request.getParameter("w"));
    int h = Integer.parseInt(request.getParameter("h"));

    int divWidth = Integer.parseInt(request.getParameter("divWidth"));
    String suffix = request.getParameter("extension");

    String base64Image = request.getParameter("base64Image");
    String filPath = request.getServletContext().getRealPath("/") + "uploads/";
    if ((base64Image == null) || (base64Image.equals(""))) {
      resultWithJson = new ResultWithJson("1", "图片base64为空");
      return resultWithJson;
    }
    File tempFilePath = new File(filPath);
    if (!tempFilePath.exists()) {
      tempFilePath.mkdir();
    }
    String currentTime = CommonUtils.getCurrentTimetoString();
    String fileName = currentTime + "." + suffix;
    File tempFile = new File(filPath + fileName);
    if (OperateImageUtil.base64ToImage(base64Image, tempFile))
    {
      BufferedImage bi = ImageIO.read(tempFile);
      int width = bi.getWidth();

      double bili = (double)width / divWidth;
      System.out.println("前端压缩图片比例:" + width + "/" + divWidth + "=" + bili);
      x1 = (int)(x1 * bili);
      y1 = (int)(y1 * bili);
      w = (int)(w * bili);
      h = (int)(h * bili);

      String readImageFormat = "jpg";
      String writeImageFormat = "jpg";
      currentTime = CommonUtils.getCurrentTimetoString();
      String toPath = filPath + currentTime + "." + suffix;
      if (OperateImageUtil.cutImage(filPath + fileName, toPath, x1, y1, w, h, readImageFormat, writeImageFormat)) {
        System.out.println("图片裁剪成功:" + toPath);
        resultWithJson = new ResultWithJson("0", "http://www.fusingpoint.com/weOnes/uploads/" + currentTime + "." + suffix);
        return resultWithJson;
      }
      resultWithJson = new ResultWithJson("1", "后台裁剪图片失败");
      return resultWithJson;
    }
    resultWithJson = new ResultWithJson("1", "后台裁剪图片失败");
    return resultWithJson;
  }
}