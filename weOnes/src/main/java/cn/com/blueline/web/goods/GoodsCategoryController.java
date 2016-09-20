package cn.com.blueline.web.goods;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.GoodsCategoryChild;
import cn.com.blueline.service.GoodsCategoryChildService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/goodsCategory" })
public class GoodsCategoryController {
	
	

	public GoodsCategoryController() {
		System.out.println("GoodsCategoryController 初始化成功...");
	}

	@Autowired
	private GoodsCategoryChildService service;

	@RequestMapping(value = { "/{categoryId}" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<GoodsCategoryChild> findByCategoryId(@PathVariable("categoryId") String categoryId,HttpServletRequest request) {
		try {
			List<GoodsCategoryChild> list;
			if(categoryId.equals("all")){
				System.out.println("categoryId:"+categoryId);
				list = this.service.findAll();
			}else{
				Long parentId = Long.parseLong(categoryId);
				list = this.service.findByCategoryId(parentId);
			}
			if (list.size() > 0)
				return new ResultWithJson<GoodsCategoryChild>("0", "success", list);
			else {
				return new ResultWithJson("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
//	@RequestMapping(value = { "/" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
//	@ResponseBody
//	public ResultWithJson<GoodsCategoryChild> findByCategoryId(
//			HttpServletRequest request) {
//		try {
//			List<GoodsCategoryChild> list = service.findAll();
//			if (list.size() > 0)
//				return new ResultWithJson<GoodsCategoryChild>("0", "success", list);
//			else {
//				return new ResultWithJson("1", "error");
//			}
//		} catch (Exception e) {
//			return new ResultWithJson("1", "保存数据时出错");
//		}
//	}
	
}