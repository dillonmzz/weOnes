package cn.com.blueline.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.blueline.dao.GoodsDao;
import cn.com.blueline.dto.GoodsAttrValInfo;
import cn.com.blueline.dto.GoodsInfo;
import cn.com.blueline.entity.Goods;
import cn.com.blueline.entity.GoodsAttrKey;
import cn.com.blueline.entity.GoodsAttrVal;
import cn.com.blueline.entity.GoodsSku;
import cn.com.blueline.service.GoodsService;
import cn.com.blueline.utils.PageQueryUtil;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsDao dao;

	@Override
	@Transactional
	public int saveGoods(String formJsonData,String createUser) {
		int rowNum  =0;
		try {
			JSONObject jsonObj = new JSONObject(formJsonData);
			JSONArray goodsAttrsArry = jsonObj.getJSONArray("goodsAttrs");
			Goods goods = generateGoodsObj(jsonObj);
			goods.setCreateUser(createUser);
			System.out.println(goods.toString());
			rowNum = dao.save(goods);//保存商品成功后,保存商品attrVal和商品sku
			Long goodsId = goods.getId();
			System.out.println("保存goods成功,返回的goodsId="+goodsId+"继续保存商品属性值(GoodsAttrVal)和商品sku表(GoodsSku)");
			List<GoodsAttrVal> goodsAttrVals = (List<GoodsAttrVal>) generateGoodsAttrVal(goodsAttrsArry,goodsId)[0];
			List<GoodsSku> goodsSkus = (List<GoodsSku>) generateGoodsAttrVal(goodsAttrsArry,goodsId)[1];
			System.out.println("商品属性共有"+goodsAttrVals.size()+"条");
			dao.saveGoodsAttrVal(goodsAttrVals);//保存商品属性值(GoodsAttrVal)
			dao.saveGoodsSku(goodsSkus);//保存商品sku表(GoodsSku)
			return rowNum;
		} catch (Exception e) {
			e.printStackTrace();
			return rowNum;
		}
	}
	
	/**
	 * 根据json数组,解析GoodsAttrVal和GoodsSku
	 * @param goodsAttrsArry  json数组
	 * @param goodsId 保存的商品ID
	 * @return  GoodsAttrVal和GoodsSku 对象
	 */
	//public List<GoodsAttrVal> generateGoodsAttrVal(JSONArray goodsAttrsArry,Long goodsId){
	public Object[] generateGoodsAttrVal(JSONArray goodsAttrsArry,Long goodsId){
		//List<GoodsAttrVal> list = null;
		Object[] objArry = new Object[2];
		try {
			List<GoodsAttrVal> attrList = new ArrayList<GoodsAttrVal>();
			List<GoodsSku> skuList = new ArrayList<GoodsSku>();
			System.out.println("goodsAttrsArry:"+goodsAttrsArry.length());
			for (int i = 0; i < goodsAttrsArry.length(); i++) {
				JSONObject goodsAttrs = goodsAttrsArry.getJSONObject(i);
				Long attrKeyId = goodsAttrs.getLong("attrKey");
				System.out.println(attrKeyId);
				JSONArray attrVals =goodsAttrs.getJSONArray("attrVals");
				int attrValsCount = attrVals.length();
				for (int j = 0; j < attrValsCount; j++) {
					
					JSONObject attrObj = attrVals.getJSONObject(j);
					Long symbol = attrObj.getLong("symbol");
					String keyVal = attrObj.getString("attrVal");
					Long newPrice  = attrObj.getLong("newPrice");
					//Long oldPrice  = attrObj.getLong("oldPrice");TODO
					
					GoodsAttrVal attrVal = new GoodsAttrVal();
					attrVal.setGoodsId(goodsId);
					attrVal.setAttrKeyId(attrKeyId);
					attrVal.setSymbol(symbol);
					attrVal.setAttrValue(keyVal);
					
					GoodsSku sku = new GoodsSku();
					sku.setGoodsId(goodsId);
					sku.setAttrSymbolPath(symbol.toString());
					sku.setNewPrice(new BigDecimal(newPrice));
					//sku.setOldPrice(new BigDecimal(oldPrice));TODO
					
					System.out.println("商品属性值表:"+attrVal.toString());
					System.out.println("商品sku表:"+sku.toString());
					attrList.add(attrVal);
					skuList.add(sku);
				}
			}
			objArry[0] =attrList;
			objArry[1] =skuList;
			return objArry;
		} catch (Exception e) {
			e.printStackTrace();
			return objArry;
		}
	}
	
		
	/**
	 * 根据json字符串处理转换成Goods对象
	 * @param jsonObj json字符串
	 * @return Goods对象
	 */
	public Goods generateGoodsObj(JSONObject jsonObj) {
		Goods goods = null;
		try {
			goods = new Goods();
			Long categoryId = jsonObj.getLong("categoryId");
			goods.setCategoryId(categoryId);
			Long categoryChildId = jsonObj.getLong("categoryChildId");
			goods.setCategoryChildId(categoryChildId);
			String name = jsonObj.getString("name");
			goods.setName(name);
			String title = jsonObj.getString("title");
			goods.setTitle(title);
			// String goodsPhotos = jsonObj.getString("goodsPhotos"); TODO
			// goods.setGoodsPhotos(goodsPhotos);TODO
			String province = jsonObj.getString("province");
			goods.setProvince(province);
			String city = jsonObj.getString("city");
			goods.setCity(city);
			String district = jsonObj.getString("district");
			goods.setDistrict(district);
			String address = jsonObj.getString("address");
			goods.setAddress(address);
			String introduction = jsonObj.getString("introduction");
			goods.setIntroduction(introduction);
			Long fixedPrice  = jsonObj.getLong("fixedPrice");
			goods.setPrice(new BigDecimal(fixedPrice));//一口价
			String goodsPreviewPhoto = jsonObj.getString("goodsPreviewPhoto");
			goods.setGoodsPreviewPhoto(goodsPreviewPhoto);
			String isPurchase = jsonObj.getString("isPurchase");
			if (isPurchase.equals("Y")) {// 如果限制购买
				Long purchaseQuantity = jsonObj.getLong("purchaseQuantity");// 购买数量
				goods.setIsPurchase("Y");
				goods.setPurchaseQuantity(purchaseQuantity);
			}else{
				goods.setIsPurchase("N");
			}
			String isNewGoods = jsonObj.getString("isNewGoods");
			goods.setIsNewGoods(isNewGoods);
			String isBookGoods = jsonObj.getString("isBookGoods");
			goods.setIsBookGoods(isBookGoods);
			goods.setCreateTime(new Date());
			goods.setStateCode(100L);
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Goods> search(int currentPageNum,Map<String, Object> queryParameters){
		List<Goods> list = null;
		try {
			Integer startRowIndex = (currentPageNum-1)*PageQueryUtil.DEFAULT_PAGESIZE;
			//list = dao.search(startRowIndex, PageQueryUtil.DEFAULT_PAGESIZE, queryParameters);
			queryParameters.put("offset", startRowIndex);
			queryParameters.put("rows", PageQueryUtil.DEFAULT_PAGESIZE);
			list = dao.search(queryParameters);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<GoodsInfo> indexGoodsInfo() {
		List<GoodsInfo> list = null;
		try {
			list = dao.indexGoodsInfo();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public GoodsInfo queryGoodsInfoById(Long id) {
		GoodsInfo goodsInfo = null;
		try {
			goodsInfo = dao.queryGoodsInfoById(id);
			return goodsInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<GoodsAttrKey> queryGoodsAttrKeyByGoodsId(Long id) {
		List<GoodsAttrKey> list = null;
		try {
			list = dao.queryGoodsAttrKeyByGoodsId(id);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<GoodsAttrVal> queryGoodsSkuByGoodsIdWithAttrKeyId(Long id,Long attrKeyId) {
		List<GoodsAttrVal> list = null;
		try {
			list = dao.queryGoodsSkuByGoodsIdWithAttrKeyId(id,attrKeyId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BigDecimal findSkuPriceByGoodsAndSymbol(Long goodsId, Long symbol) {
		BigDecimal skuPrice = null;
		try {
			skuPrice = dao.findSkuPriceByGoodsAndSymbol(goodsId,symbol);
			return skuPrice;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> findExistCity() {
		List<String> existCity = null;
		try {
			existCity = dao.findExistCity();
			return existCity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
