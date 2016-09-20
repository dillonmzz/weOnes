package cn.com.blueline.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 图片处理
 * 参考:http://blog.csdn.net/hu_shengyang/article/details/7433988
 * @author Dillon
 *
 */
public class OperateImageUtil {

    
    /**
     * 
     * @param srcPath 源图片地址
     * @param toPath   裁剪后的图片地址
     * @param x   裁剪图片的起点x轴坐标
     * @param y	     裁剪图片的起点y轴坐标
     * @param width 裁剪图片的宽度
     * @param height 裁剪图片的高度
     * @param readImageFormat 读取源图片的格式
     * @param writeImageFormat 裁剪后图片的格式
     * @return 是否裁剪成功
     * @throws IOException
     */
    public static  boolean cutImage(String srcPath,String toPath,int x,int y,int width,int height,  
            String readImageFormat,String writeImageFormat)  throws IOException {
    	boolean flag = false;
    	 FileInputStream fis = null ; 
         ImageInputStream iis =null ;  
         try{
             //读取图片文件  
             fis = new FileInputStream(srcPath);   
             Iterator it = ImageIO.getImageReadersByFormatName(readImageFormat);   
             ImageReader reader = (ImageReader) it.next();   
             //获取图片流   
             iis = ImageIO.createImageInputStream(fis);    
             reader.setInput(iis,true) ;  
             ImageReadParam param = reader.getDefaultReadParam();   
             //定义一个矩形  
             Rectangle rect = new Rectangle(x, y, width, height);   
             //提供一个 BufferedImage，将其用作解码像素数据的目标。   
             param.setSourceRegion(rect);  
             BufferedImage bi = reader.read(0,param);                  
             //保存新图片   
             ImageIO.write(bi, writeImageFormat, new File(toPath)); 
             flag = true;
         }finally{  
             if(fis!=null)  
                 fis.close();         
             if(iis!=null)  
                iis.close();   
         }
         
         return flag;
    }
    
    
    public static boolean base64ToImage(String base64Str,File file){
    	boolean flag = false;
    	try {
    		 byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(base64Str);
	   		 
	   		 for (int i = 0; i < bytes.length; ++i) {
	   			 if (bytes[i] < 0) {// 调整异常数据
	                       bytes[i] += 256;
	              }
	   		 }
	   		 // 生成jpeg图片
	   		 OutputStream out = new FileOutputStream(file);
	   		 out.write(bytes);
	   		 out.flush();
	   		 out.close();
    		flag = true;
		} catch (Exception e) {
			flag = false;
		}
    	return flag;
    }
    
}
