package cn.com.blueline.web.goods;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.blueline.dto.GoodsAttrValInfo;
import cn.com.blueline.dto.GoodsInfo;
import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.Goods;
import cn.com.blueline.entity.GoodsAttrKey;
import cn.com.blueline.entity.GoodsAttrVal;
import cn.com.blueline.entity.GoodsSku;
import cn.com.blueline.service.GoodsService;















import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/goods" })
public class GoodsController {
	
	public GoodsController() {
		System.out.println("GoodsController 初始化成功...");
	}

	@Autowired
	private GoodsService service;

	/**
	 * 保存商品并关联保存商品属性和商品sku数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/saveGoods" }, method = { RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson saveGoods(HttpServletRequest request) {
		try {
			int rowNum = 0;
			String formJsonData = request.getParameter("data");
			System.out.println(formJsonData);
			String createUser = request.getParameter("createUser");
			service.saveGoods(formJsonData,createUser);
			return new ResultWithJson("0","ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
	/**
	 * 分页条件查询
	 * @param pageNum 当前页
	 * @return List<Goods> 数据
	 */
	@RequestMapping(value = { "/{pageNum}" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<Goods> searchByPage(@PathVariable("pageNum")Integer pageNum,HttpServletRequest request) {
		try {
			List<Goods> list = null;
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("price", "BETWEEN 50 AND 80");
			//map.put("price", "50");
			map.put("city","合肥市");
			map.put("district","瑶海区");
			list = service.search(pageNum, map);
			return new ResultWithJson<Goods>("0",""+list.size(),list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
	/**
	 * PC端首页展示城市列表
	 * @return
	 */
	@RequestMapping(value = { "/existCity" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<List<String>> showExistCity() {
		List<String> existCity = null;
		try {
			existCity = service.findExistCity();
			return new ResultWithJson<List<String>>("0","ok",existCity);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
	
	/**
	 * PC端首页Top商品展示
	 * @return
	 */
	@RequestMapping(value = { "" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<GoodsInfo> indexGoodsInfo() {
		try {
			List<GoodsInfo> list = null;
			list = service.indexGoodsInfo();
			return new ResultWithJson<GoodsInfo>("0",""+list.size(),list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
	/**
	 * 根据商品ID 查询商品信息+商品属性+商品sku信息
	 * @param goodsId 商品ID
	 * @return
	 */
	@RequestMapping(value = { "/detail/{id}" }, method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<GoodsInfo> goodsDetails(@PathVariable("id")Long goodsId) {
		try {
			GoodsInfo goodsInfo = null;
			goodsInfo = service.queryGoodsInfoById(goodsId);//查询商品信息
			List<GoodsAttrKey>  attrKeys = service.queryGoodsAttrKeyByGoodsId(goodsId);//查询商品属性信息
			goodsInfo.setAttrKeys(attrKeys);//设置一个商品对应多个属性key
			for (GoodsAttrKey attrKey : attrKeys) {
				Long attrKeyId = attrKey.getAttrKeyId();
				List<GoodsAttrVal> attrVals = service.queryGoodsSkuByGoodsIdWithAttrKeyId(goodsId, attrKeyId);
				attrKey.setAttrVals(attrVals);//设置一个属性key对应多个属性val
			}
			return new ResultWithJson<GoodsInfo>("0","ok",goodsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
	
	/**
	 * 根据symbol和goodsId 查询sku价格
	 * @param goodsId 商品ID
	 * @return 价格
	 */
	@RequestMapping(value = { "/skuprice" }, method = { RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson findSkuPrice(Long goodsId,Long symbol) {
		try {
			System.out.println(goodsId+":"+symbol);
			BigDecimal skuPrice = service.findSkuPriceByGoodsAndSymbol(goodsId,symbol);
			System.out.println(skuPrice.toString());
			return new ResultWithJson("0","ok",skuPrice);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResultWithJson("1", "服务器异常");
		}
	}
	
	
}