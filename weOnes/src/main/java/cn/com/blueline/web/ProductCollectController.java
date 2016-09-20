package cn.com.blueline.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cn.com.blueline.dto.ProductCollectDto;
import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.dto.WxPayReturnData;
import cn.com.blueline.dto.WxPaySentData;
import cn.com.blueline.entity.Product;
import cn.com.blueline.entity.ProductCollect;
import cn.com.blueline.entity.ProductCollectOrder;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.service.ProductCollectService;
import cn.com.blueline.service.ProductService;
import cn.com.blueline.service.TimeScheduleService;
import cn.com.blueline.service.UnifiedOrderService;
import cn.com.blueline.utils.CommonUtils;
import cn.com.blueline.utils.WxPayConfig;
import cn.com.blueline.utils.WxSign;

@Controller
@RequestMapping("/collect")
public class ProductCollectController {
	
	
	@Autowired private ProductCollectService service;
	
	@Autowired private TimeScheduleService timeScheduleService;
	
	@Autowired private ProductService productService;
	
	@Autowired
	private UnifiedOrderService unifiedOrderService;
	
	/**
	 * 后台管理系统:
	 * 根据当前用户查询发布的产品列表
	 * @param model
	 * @param request
	 * @return 活动列表json串
	 */
	@RequestMapping(value = "/adminList", method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
	public String adminListGet(Model model,HttpServletRequest request) {
		try {
			String currentUserId = "dillon";//TODO 当前用户由前台传来
			List<ProductCollect> list = service.findProductCollectByUser(currentUserId);
			model.addAttribute("list", list);
		} catch (Exception e) {
		     throw new RuntimeException("查询数据发生错误",e);
		}
		return "admin/collect/list";
	}
	
	@RequestMapping(value = "/toadd", method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
	public String toadd(Model model,HttpServletRequest request) {
		//
		return "admin/collect/add";
	}
	
	/**
	 * 后台管理系统
	 * 根据当前用户、时间类型查询时间表
	 * @param timeType 事件类型
	 * @return 结果
	 */
	@RequestMapping(value = "/findTimeScheduleByTimeType", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson<TimeSchedule> findTimeScheduleByTimeType(String timeType){
		
		ResultWithJson<TimeSchedule> resultWithJson;
		try {
			String createUser = "dillon";//TODO session 中的ID
			//String timeType = request.getParameter("timeType");
			List<TimeSchedule> list = timeScheduleService.findTimeScheduleByTimeType(timeType, createUser);
			resultWithJson = new ResultWithJson<TimeSchedule>("0", "OK",list);
		} catch (Exception e) {
			resultWithJson = new ResultWithJson<TimeSchedule>("1", "error");
			return resultWithJson;
		}
		return resultWithJson;
	}
	
	/**
	 * 后台管理系统
	 * 根据当前用户、产品类别查询产品集合
	 * @param productType 产品类别
	 * @return 结果
	 */
	@RequestMapping(value = "/findSubjectByType", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson<Product> findSubjectByType(String productType){
		ResultWithJson<Product> resultWithJson;
		try {
			String createUser = "dillon";//TODO session 中的ID
			//String timeType = request.getParameter("timeType");
			List<Product> list = productService.findProductByUser(productType, createUser);
			resultWithJson = new ResultWithJson<Product>("0", "OK",list);
		} catch (Exception e) {
			resultWithJson = new ResultWithJson<Product>("1", "error");
			return resultWithJson;
		}
		return resultWithJson;
	}
	
	
	/**
	 * 添加产品
	 * @param request
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson save(String params,ProductCollect productCollect,HttpServletRequest request){
		ResultWithJson resultWithJson;
		try {
			productCollect.setProvince(request.getParameter("cho_Province"));
			productCollect.setCity(request.getParameter("cho_City"));
			productCollect.setDistrict(request.getParameter("cho_Area"));
			String priceY = productCollect.getPrice().toString();
			BigDecimal priceF = new BigDecimal(priceY).multiply(
					new BigDecimal(WxPayConfig.WXAMOUNTCONVERSION)).setScale(0);
			productCollect.setPrice(priceF);
			productCollect.setState("0");
			productCollect.setCreateUser("dillon");//TODO 当前用户
			productCollect.setCreateTime(new Date());
			System.out.println("productCollect参数\r\n"+productCollect);
			service.save(productCollect);
			resultWithJson = new ResultWithJson("0", "OK");
		} catch (Exception e) {
			resultWithJson = new ResultWithJson("1", "保存数据时出错");
			return resultWithJson;
		}
		return resultWithJson;
	}
	
	/**
	 * 后台管理系统
	 * 管理员角色
	 * 管理员查看用户已提交的产品
	 * @param state 状态0 表示已提交单位审批
	 * @param model 
	 * @return 返回的页面
	 */
	@RequestMapping(value = "/myApproval", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String myApproval(String state,Model model){
		List<ProductCollect> list = service.findProductCollectByState(state);
		model.addAttribute("list", list);
		return "admin/collect/list";
	}
	
	/**
	 * 后台管理系统
	 * 管理员角色
	 * 管理员审批用户提交的产品
	 * @param state 1:拒绝 2:通过
	 * @param id 产品ID
	 * @return
	 */
	@RequestMapping(value = "/updateStateById", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson updateStateById(String state,Long id){
		ResultWithJson resultWithJson;
		try {
			service.updateStateById(id, state);
			resultWithJson = new ResultWithJson("0", "ok");
		} catch (Exception e) {
			resultWithJson = new ResultWithJson("1", "error");
			return resultWithJson;
		}
		return resultWithJson;
	}
	
	
	/**
	 * 移动端
	 * 移动端分页查看产品列表集合
	 * @param page 当前页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String productCollectList(@PathVariable("page")int page,Model model,HttpServletRequest request){
		// 查询已经审批通过的产品列表,state=2,每页显示20条
		List<ProductCollectDto> list = null;
		int  currentPageNum = page;
		list= service.appQueryByPage(currentPageNum);
		List<ProductCollectDto> tempList = new ArrayList<ProductCollectDto>();
		for (ProductCollectDto productCollectDto : list) {
			String subjectId = productCollectDto.getSubjectId();
			String talentId = productCollectDto.getTalentId();
			if(subjectId!=null){//如果存在主题
				productCollectDto.setCoverPhoto(productCollectDto.getSubjectPhoto());
				productCollectDto.setName(productCollectDto.getSubjectName());
				productCollectDto.setTitle(productCollectDto.getSubjectTitle());
			}else if(subjectId==null && talentId!=null){//如果不存在主题,存在达人
				productCollectDto.setCoverPhoto(productCollectDto.getTalentPhoto());
				productCollectDto.setName(productCollectDto.getTalentName());
				productCollectDto.setTitle(productCollectDto.getTalentTitle());
			}else{//如果主题、达人都不存在 则仅显示场地信息
				productCollectDto.setCoverPhoto(productCollectDto.getPlacePhoto());
				productCollectDto.setName(productCollectDto.getPlaceName());
				productCollectDto.setTitle(productCollectDto.getPlaceTitle());//场地设备
			}
			tempList.add(productCollectDto);
			//System.out.println(productCollectDto.toString());
		}
		model.addAttribute("list", tempList);
		//request.getSession().setAttribute("openId", "o9bmjsy3prTRGYuK6mqxm21E_vdg");
		return "app/collect/index";
	}
	
	
	
	/**
	 * 移动端
	 * 根据产品ID查询产品详情
	 * @param id 产品id
	 * @param model
	 * @return 详情页面
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String productCollectList(@PathVariable("id")Long id,Model model,HttpServletRequest request){
		ProductCollectDto productCollectDto = null;
		System.out.println(id);
		productCollectDto = service.appQueryById(id);
		String subjectId = productCollectDto.getSubjectId();
		String talentId = productCollectDto.getTalentId();
		if(subjectId!=null){//如果存在主题
			productCollectDto.setCoverPhoto(productCollectDto.getSubjectPhoto());
			productCollectDto.setName(productCollectDto.getSubjectName());
			productCollectDto.setTitle(productCollectDto.getSubjectTitle());
		}else if(subjectId==null && talentId!=null){//如果不存在主题,存在达人
			productCollectDto.setCoverPhoto(productCollectDto.getTalentPhoto());
			productCollectDto.setName(productCollectDto.getTalentName());
			productCollectDto.setTitle(productCollectDto.getTalentTitle());
		}else{//如果主题、达人都不存在 则仅显示场地信息
			productCollectDto.setCoverPhoto(productCollectDto.getPlacePhoto());
			productCollectDto.setName(productCollectDto.getPlaceName());
			productCollectDto.setTitle(productCollectDto.getPlaceTitle());//场地设备
		}
		String openId = (String) request.getSession().getAttribute("openId");
		//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		Integer state = service.queryStateByOpenIdAndProductCollectId(openId, id);
		System.out.println("当前关注状态"+state);
		if(state!=null){
			model.addAttribute("state", state);
		}else{
			model.addAttribute("state", 0);
		}
		model.addAttribute("collect", productCollectDto);
		return "app/collect/detail";
	}
	
	/**
	 * 移动端
	 * 用户在详情页面中点击"我要参加"按钮,根据ID 获取产品信息并显示到预定页面中
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tobook/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String tobook(@PathVariable("id")Long id,Model model){
		ProductCollectDto productCollectDto = null;
		System.out.println(id);
		productCollectDto = service.appQueryById(id);
		productCollectDto = this.productCollectProcess(productCollectDto);
		//System.out.println(productCollectDto.toString());
		model.addAttribute("collect", productCollectDto);
		return "app/collect/book";
	}
	
	
	/**
	 * 移动端
	 * 用户预定订单,将预订单数据插入到数据库中
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bookproduct", method = RequestMethod.POST)
	@ResponseBody
	public ResultWithJson<ProductCollectOrder> bookproduct(Model model,HttpServletRequest request){
		ProductCollectOrder order = new ProductCollectOrder();
		String openId = (String)request.getSession().getAttribute("openId");
		//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		System.out.println("当前微信用户:"+openId+"正在预定下单");
		order.setOpenId(openId);//TODO 获取当前session中的openId
		String userPhone = (String)request.getParameter("phone");// TODO 页面的手机号码
		order.setUserPhone(userPhone);
		//获取到用户购买的产品ID
		Long collectId = Long.parseLong(request.getParameter("collectId"));// TODO 获取页面中的productCollectId
		System.out.println("要预订支付的collectId="+collectId);
		order.setCollectId(collectId);
		//获取到用户选择产品里面的时间ID
		String timeId = "5620c2a94b4e4265bffd80df54b4c0c9";// TODO
		timeId= request.getParameter("timeId");
		order.setTimeId(timeId);
		//获取到用户选择时间里面的期次
		Integer timeSection = 2;// TODO
		timeSection = Integer.parseInt(request.getParameter("timeSection"));
		order.setSection(timeSection);
		//获取到用户购买的数量
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));//TODO 获取页面购买数量
		order.setQuantity(quantity);
		//根据产品ID和时间ID查询出该产品的单价
		Integer price = service.queryPriceById(collectId);
		order.setPrice(new BigDecimal(price));
		//计算总价 = 单价*数量
		int totalFee = new BigDecimal(price).multiply(new BigDecimal(quantity)).intValue();
		order.setTotalFee(new BigDecimal(totalFee));
		order.setCreateTime(new Date());
		order.setState(0);//下订单了但未支付
		String outTradeNo = CommonUtils.getCurrentTimetoString();
		order.setOutTradeNo(outTradeNo);
		//将订单数据存储到产品订单明细表中
		int rowNum = service.saveBook(order);
		if(rowNum>=1){//下定单成功后,通过微信平台推送给产品发布者
			String ToUserName = "o9bmjsy3prTRGYuK6mqxm21E_vdg";//发送给谁
			String FromUserName = "o9bmjsy3prTRGYuK6mqxm21E_vdg";//发送者
			Long CreateTime = CommonUtils.getCurrentTimetoLong();//消息创建时间 （整型）
			String MsgType = "nes";
			int ArticleCount = 1;//图文消息个数，限制为10条以内
			String title ="您有1条未处理的订单,请及时相应";// title
			String description="点击处理订单";//描述
			String picUrl = "http://imgstore.cdn.sogou.com/app/a/100540002/714860.jpg";
			String url = "http://localhost:8080/weOnes/collect/list/1";//点击图片跳转路径
			//将上面的内容转换成xml格式
			//主动发送给微信用户
			ResultWithJson<ProductCollectOrder> resultWithJson = new ResultWithJson<ProductCollectOrder>("0", "ok", order);
			return resultWithJson;
		}else{
			return new ResultWithJson<ProductCollectOrder>("1", "预定失败");
		}
		
	}
	
	
	/**
	 * 移动端
	 * 根据timeId 和日期 选择
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTimeByDate", method = RequestMethod.POST)
	@ResponseBody
	public ResultWithJson<TimeSchedule> queryTimeByDate(HttpServletRequest request){
		String timeId = request.getParameter("timeId");//时间ID
		String startDate = request.getParameter("selectDate");//用户选择的日期
		List<TimeSchedule> list = timeScheduleService.queryTimeByDate(timeId, startDate);
		ResultWithJson<TimeSchedule> resultWithJson = new ResultWithJson<TimeSchedule>("0", "ok",list);
		return resultWithJson;
	}
	
	
	
	/**
	 * 移动端
	 * 跳转至支付页面 用来展示预定产品的详细信息
	 * @param outTradeNo 商家订单号
	 * @param model
	 * @return 确认支付页面
	 */
	@RequestMapping(value = "/topay/{outTradeNo}", method = RequestMethod.GET)
	public String topay(@PathVariable("outTradeNo")String outTradeNo,Model model,HttpServletRequest request){
		ProductCollectOrder order = service.queryOrderById(outTradeNo);
		ProductCollectDto productCollectDto = null;
		productCollectDto = service.appQueryById(order.getCollectId());
		productCollectDto = this.productCollectProcess(productCollectDto);
		//System.out.println(productCollectDto.toString());
		model.addAttribute("phone", order.getUserPhone());
		model.addAttribute("nickname", (String)request.getSession().getAttribute("nickname"));//TODO 微信昵称
		model.addAttribute("productName", productCollectDto.getName());
		model.addAttribute("timeDesc", productCollectDto.getTimeDesc());
		model.addAttribute("coverPhoto", productCollectDto.getCoverPhoto());
		model.addAttribute("quantity", order.getQuantity());
		model.addAttribute("price", order.getPrice());
		model.addAttribute("totalFee", order.getTotalFee());
		model.addAttribute("outTradeNo", outTradeNo);
		//model.addAttribute("collect", productCollectDto);
		return "app/collect/pay";
	}
	
	
	/**
	 * 确认支付页面，支付发起的ajax post 请求
	 * @param outTradeNo 商家订单号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/wexinpay/{outTradeNo}", 
					method = RequestMethod.POST,
					produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<WxPayReturnData> wexinpay(@PathVariable("outTradeNo")String outTradeNo,HttpServletRequest request){
		
		try {
			System.out.println("即将支付的订单号"+outTradeNo);
			//根据商家订单号查询支付的总金额
			ProductCollectOrder collectOrder = service.queryOrderById(outTradeNo);
			int totalFee  = collectOrder.getTotalFee().intValue();
			String nonceStr = WxSign.getNonceStr();
			WxPaySentData data = new WxPaySentData();
			data.setAppid(WxPayConfig.APPID);
			data.setAttach("weixinpay_v2.0");
	        data.setBody("活动商品");// 商品名称
	        data.setMch_id(WxPayConfig.MCHID);
	        data.setNotify_url(WxPayConfig.NOTIFYURL);
	        data.setOut_trade_no(outTradeNo);//商户系统内部的订单号,32个字符内、可包含字母
	        data.setTotal_fee(totalFee);//单位：分
	        data.setTrade_type("JSAPI");
	        data.setSpbill_create_ip("192.168.1.1");//TODO 获取IP
	        data.setOpenid((String)request.getSession().getAttribute("openId"));
	        //data.setOpenid("o9bmjsy3prTRGYuK6mqxm21E_vdg");// TODO 获取当前session中的openId
	        data.setNonce_str(nonceStr);//随机字符串，不长于32位
	        String returnXml = unifiedOrderService.unifiedOrder(data,WxPayConfig.KEY);
	        // XML 转 Object
	        WxPayReturnData unifiedOrderResponseData = new WxPayReturnData();
	        XStream xs = new XStream(new DomDriver());
	        xs.alias("xml", WxPayReturnData.class);
	        unifiedOrderResponseData = (WxPayReturnData) xs.fromXML(returnXml);
	        String timeStamp = WxSign.getTimeStamp();
	        System.out.println("调用统一下单API微信返回的结果"+unifiedOrderResponseData.toString());
	        SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
	        signMap.put("appId", unifiedOrderResponseData.getAppid()); 
	        signMap.put("timeStamp", timeStamp);
	        signMap.put("nonceStr", unifiedOrderResponseData.getNonce_str());
	        signMap.put("package", "prepay_id="+unifiedOrderResponseData.getPrepay_id());
	        signMap.put("signType", "MD5");
	        unifiedOrderResponseData.setSign(WxSign.createSign(signMap,WxPayConfig.KEY));
	        unifiedOrderResponseData.setTimeStamp(timeStamp);
	        System.out.println("返回结果再次签名结果数据:"+unifiedOrderResponseData.toString());
	        if(unifiedOrderResponseData.getReturn_code().equals("SUCCESS" )
	       		 && unifiedOrderResponseData.getReturn_msg().equals("OK")
	       		 && unifiedOrderResponseData.getResult_code().equals("SUCCESS")){
	        	return new ResultWithJson<WxPayReturnData>("0","ok",unifiedOrderResponseData);
			}else{
				return new ResultWithJson<WxPayReturnData>("0","ok",unifiedOrderResponseData);
			}
		} catch (Exception e) {
			return new ResultWithJson<WxPayReturnData>("1","支付请求失败");
		}
		
		
	}
	
	
	
	
	
	private ProductCollectDto productCollectProcess(ProductCollectDto productCollectDto){
		String subjectId = productCollectDto.getSubjectId();
		String talentId = productCollectDto.getTalentId();
		if(subjectId!=null){//如果存在主题
			productCollectDto.setCoverPhoto(productCollectDto.getSubjectPhoto());
			productCollectDto.setName(productCollectDto.getSubjectName());
			productCollectDto.setTitle(productCollectDto.getSubjectTitle());
		}else if(subjectId==null && talentId!=null){//如果不存在主题,存在达人
			productCollectDto.setCoverPhoto(productCollectDto.getTalentPhoto());
			productCollectDto.setName(productCollectDto.getTalentName());
			productCollectDto.setTitle(productCollectDto.getTalentTitle());
		}else{//如果主题、达人都不存在 则仅显示场地信息
			productCollectDto.setCoverPhoto(productCollectDto.getPlacePhoto());
			productCollectDto.setName(productCollectDto.getPlaceName());
			productCollectDto.setTitle(productCollectDto.getPlaceTitle());//场地设备
		}
		return productCollectDto;
	}
	
	


}
