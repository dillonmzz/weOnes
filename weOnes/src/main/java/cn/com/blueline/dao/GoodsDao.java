package cn.com.blueline.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.blueline.dto.GoodsInfo;
import cn.com.blueline.entity.Goods;
import cn.com.blueline.entity.GoodsAttrKey;
import cn.com.blueline.entity.GoodsAttrVal;
import cn.com.blueline.entity.GoodsSku;


public interface GoodsDao {
	
	/**
	 * 保存goods
	 * @param goods
	 * @return
	 */
	int save(Goods goods);
	
	/**
	 * 保存商品属性值(GoodsAttrVal)
	 * 可能是多条记录
	 * @param GoodsAttrVal集合
	 * @return 1成功 0 失败
	 */
	int saveGoodsAttrVal(List<GoodsAttrVal> list);
	
	/**
	 * 保存商品sku(GoodsSku)
	 * 可能是多条记录
	 * @param GoodsSku集合
	 * @return 1成功 0 失败
	 */
	int saveGoodsSku(List<GoodsSku> list);
	
	/**
	 * 分页多条件查询
	 * @param startIdex 当前页的起点索引
	 * @param rows  显示条数
	 * @param queryParameters 查询参数
	 * @return
	 */
	List<Goods> search(Integer startIdex,Integer rows,Map<String, String> queryParameters);
	
	/**
	 * 分页多条件查询
	 * @param startIdex 当前页的起点索引
	 * @param rows  显示条数
	 * @param queryParameters 查询参数
	 * @return
	 */
	List<Goods> search(Map<String, Object> queryParameters);
	
	/**
	 * 首页查看TOP数据
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
	List<GoodsAttrKey> queryGoodsAttrKeyByGoodsId(@Param("id")Long id);
	
	/**
	 * 根据商品ID和商品属性key查询商品属性
	 * @param id 商品ID
	 * @param attrKeyId 商品属性key
	 * @return  List<GoodsAttrVal>商品属性
	 */
	List<GoodsAttrVal> queryGoodsSkuByGoodsIdWithAttrKeyId(@Param("id")Long id,@Param("attrKeyId")Long attrKeyId);

	/**
	 * 根据symbol和goodsId 查询sku价格
	 * @param goodsId 商品ID
	 * @param symbol 序号
	 * @return sku价格
	 */
	BigDecimal findSkuPriceByGoodsAndSymbol(@Param("goodsId")Long goodsId, @Param("symbol")Long symbol);

	/**
	 * 查询数据库中已发布的城市列表
	 * @return
	 */
	List<String> findExistCity();
	
	
	
	
}
