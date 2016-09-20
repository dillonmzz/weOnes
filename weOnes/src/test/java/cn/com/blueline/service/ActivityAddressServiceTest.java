package cn.com.blueline.service;
/*作者:Dillon
 *日期:2016年5月31日
 **/
 
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.blueline.entity.ActivityAddress;
import cn.com.blueline.utils.UUIDGenerate;


 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class ActivityAddressServiceTest {
	 
	 @Autowired
	 private ActivityAddressService addressService;
	 
	 /*
	 @Test
	 public void save(){
		ActivityAddress address = new ActivityAddress();
		//测试结果表明:若数据库表中的ID字段是自动生成的话，指定实体Id值会报错。
		//可将实体ID不赋值.在mapper中获取的ID为null,数据库执行sql时，仍采用自动生成的方式
			 address.setAddressId(UUIDGenerate.getUUID());
			 address.setEffdt(new Date());
			 address.setStatus("A");
			 address.setTitle("测试");
			 address.setProvince("安徽省");
			 address.setCity("合肥");
			 address.setAddressDesc("xxxxxxxx");
			 address.setMaxPeople(100);
			 address.setDevice("wifi,空调,电视");
			 address.setCreateUser("MZZ");
			 address.setCreateTime(new Date());
			 System.out.println(addressService.save(address));
	 } 
	
	 @Test
	 public void queryActivityAddressBycreateUser(){
		 List<ActivityAddress> list = addressService.queryActivityAddressBycreateUser("DILLON");
		 System.out.println(list.size());
		 for (ActivityAddress address : list) {
			System.out.println(address.toString());
		}
	 }
	 
	 @Test
	 public void updateToInvalidTest(){
		 int rowNum = addressService.updateToInvalid("A", 1001L);
		 System.out.println(rowNum);
	 } */
	 
	 @Test
	 public void updateActivityAddressTest(){
		 ActivityAddress address = new ActivityAddress();
		 address.setAddressId("91ea050031a644c982fa40030a6098ed");
		 address.setEffdt(new Date());
		 address.setStatus("A");
		 address.setTitle("测试新");
		 address.setProvince("安徽省");
		 address.setCity("合肥");
		 address.setAddressDesc("xxxxxxxx");
		 address.setMaxPeople(100);
		 address.setDevice("wifi,空调,电视");
		 address.setCreateUser("MZZ");
		 address.setCreateTime(new Date());
		 
		 int rowNum = addressService.updateActivityAddress(address);
		 System.out.println(rowNum);
	 }
	 
	 
	
	

}
