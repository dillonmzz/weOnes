package cn.com.blueline.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.dto.GoodsAttrValInfo;
import cn.com.blueline.dto.GoodsInfo;
import cn.com.blueline.entity.Goods;
import cn.com.blueline.entity.GoodsAttrKey;
import cn.com.blueline.entity.GoodsAttrVal;
import cn.com.blueline.entity.GoodsSku;


public interface GoodsService {
	
	
	/**
	 * 保存
	 * @param goods
	 * @return
	 */
	int saveGoods(String formJsonData, String createUser);
	
	/**
	 * 分页条件查询
	 * @param currentPageNum 当前页
	 * @param queryParameters 条件参数
	 * @return 商品信息集合
	 */
	List<Goods> search(int currentPageNum,Map<String, Object> queryParameters);
	
	/**
	 * 首页展示信息
	 * @return
	 */
	List<GoodsInfo> indexGoodsInfo();
	
	/**
	 * 根据ID 查询商品数据
	 * @param id 商品ID
	 * @return
	 */
	GoodsInfo queryGoodsInfoById(Long id);
	
	/**
	 * 根据商品ID查询商品属性key
	 * @param id 商品ID
	 * @return List<GoodsAttrKey>商品属性
	 */
	List<GoodsAttrKey> queryGoodsAttrKeyByGoodsId(Long id);
	
	/**
	 * 根据商品ID和商品属性key查询商品属性
	 * @param id 商品ID
	 * @param attrKeyId 商品属性key
	 * @return  List<GoodsAttrVal>商品属性
	 */
	List<GoodsAttrVal> queryGoodsSkuByGoodsIdWithAttrKeyId(Long id,Long attrKeyId);
	

	/**
	 * 根据symbol和goodsId 查询sku价格
	 * @param goodsId 商品ID
	 * @param symbol 序号
	 * @return sku价格
	 */
	BigDecimal findSkuPriceByGoodsAndSymbol(Long goodsId, Long symbol);

	/**
	 * 查询数据库中已发布的城市列表
	 * @return
	 */
	List<String> findExistCity();
	
	
	
}
