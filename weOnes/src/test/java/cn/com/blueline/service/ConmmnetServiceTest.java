package cn.com.blueline.service;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.blueline.entity.ProductCollectComment;
import cn.com.blueline.entity.ProductCollectOrder;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.utils.UUIDGenerate;


/**
 * @author Zhaozhi
 */
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class ConmmnetServiceTest {
	 
	 @Autowired
	 private ProductCollectConmmentService service;
	 
	 @Test
	 public void save() throws Exception{
		 ProductCollectComment comment = new ProductCollectComment();
		 comment.setProductCollectId(1021L);
		 comment.setOpenId("o9bmjsy3prTRGYuK6mqxm21E_vdg");
		 comment.setCommentText("很好的一次体验活动,达人很棒");
		 comment.setCreateTime(new Date());
		 int rowNum  = service.save(comment);
		 System.out.println(rowNum);
	}
	 
	 @Test
	 public void  queryCommentByProductCollectId(){
		 List<ProductCollectComment> list = service.queryCommentByProductCollectId(1021L);
		 for (ProductCollectComment productCollectComment : list) {
			System.out.println(productCollectComment.toString());
			System.out.println(productCollectComment.getWeChatUser().getNickName());
		}
	 }
	 
	
	

}
