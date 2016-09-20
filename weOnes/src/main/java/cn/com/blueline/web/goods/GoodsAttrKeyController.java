package cn.com.blueline.web.goods;

import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.GoodsAttrKey;
import cn.com.blueline.service.GoodsCategoryChildService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/goodsAttr" })
public class GoodsAttrKeyController {
	
	

	public GoodsAttrKeyController() {
		System.out.println("GoodsAttrKeyController 初始化成功...");
	}

	@Autowired
	private GoodsCategoryChildService service;

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<GoodsAttrKey> findByCategoryId() {
		try {
			List<GoodsAttrKey>	 list = service.findAllGoodsAttrKey();
			if (list.size() > 0)
				return new ResultWithJson<GoodsAttrKey>("0", "success", list);
			else {
				return new ResultWithJson("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
}