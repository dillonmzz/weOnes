package cn.com.blueline.web.php;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import cn.com.blueline.dto.ProductCollectDto;
import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.Product;
import cn.com.blueline.entity.ProductCollect;
import cn.com.blueline.entity.ProductCollectOrder;
import cn.com.blueline.entity.TimeSchedule;
import cn.com.blueline.service.ProductCollectService;
import cn.com.blueline.service.ProductService;
import cn.com.blueline.service.TimeScheduleService;
import cn.com.blueline.service.UnifiedOrderService;
import cn.com.blueline.utils.WxPayConfig;

@Controller
@RequestMapping("/productcollectphp")
public class ProductCollectPHP {
	
	
	@Autowired private ProductCollectService service;
	
	@Autowired private TimeScheduleService timeScheduleService;
	
	@Autowired private ProductService productService;
	
	@Autowired
	private UnifiedOrderService unifiedOrderService;
	
	/**
	 * 查看产品列表集合
	 * @param page 当前页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson<ProductCollectDto> productCollectList(HttpServletRequest request){
		List<ProductCollectDto> list = null;
		try {
			String createUser = request.getParameter("createUser");
			String state = request.getParameter("state");// 0表示未发布 2表示已发布
			list= service.queryByUser(createUser,state);
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
			}
			if(tempList!=null && tempList.size()>0){
				return new ResultWithJson<ProductCollectDto>("0", "ok", tempList);
			}else{
				return new ResultWithJson("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson("1", "error");
		}
		
	}
	
	
	/**
	 * 后台管理系统
	 * 根据当前用户、时间类型查询时间表
	 * @param timeType 时间类型
	 * @return 结果
	 */
	@RequestMapping(value = "/findTimeScheduleByTimeType", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson<TimeSchedule> findTimeScheduleByTimeType(HttpServletRequest request){
		ResultWithJson<TimeSchedule> resultWithJson;
		try {
			String createUser = request.getParameter("createUser");
			String timeType = request.getParameter("timeType");
			List<TimeSchedule> list = timeScheduleService.findTimeScheduleByTimeType(timeType, createUser);
			resultWithJson = new ResultWithJson<TimeSchedule>("0", "OK",list);
		} catch (Exception e) {
			resultWithJson = new ResultWithJson<TimeSchedule>("1", "error");
			return resultWithJson;
		}
		return resultWithJson;
	}
	
	/**
	 * 根据当前用户、产品类别查询产品集合
	 * @param productType 产品类别
	 * @return 结果
	 */
	@RequestMapping(value = "/findSubjectByType", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson<Product> findSubjectByType(HttpServletRequest request){
		ResultWithJson<Product> resultWithJson;
		try {
			String createUser = request.getParameter("createUser");
			String productType = request.getParameter("productType");
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
			System.out.println(productCollect.toString());
			//productCollect.setProvince(request.getParameter("cho_Province"));
			//productCollect.setCity(request.getParameter("cho_City"));
			//productCollect.setDistrict(request.getParameter("cho_Area"));
			String priceY = productCollect.getPrice().toString();
			BigDecimal priceF = new BigDecimal(priceY).multiply(
					new BigDecimal(WxPayConfig.WXAMOUNTCONVERSION)).setScale(0);
			productCollect.setPrice(priceF);
			productCollect.setState("0");
			String createUser = request.getParameter("createUser");
			productCollect.setCreateUser(createUser);
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
	 * 根据商家用户查看当前的订单信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOrdersByUser", method = RequestMethod.POST)
	public @ResponseBody ResultWithJson<ProductCollectDto> findOrdersByUser(HttpServletRequest request) {
		try {
			String createUser = request.getParameter("createUser");
			List<ProductCollectOrder> orders = service.queryOrderByUser(createUser);
			List<ProductCollectDto> tempOrders = new ArrayList<ProductCollectDto>();
			for (ProductCollectOrder productCollectOrder : orders) {
				ProductCollectDto productCollectDto = service.appQueryById(productCollectOrder.getCollectId());
				productCollectDto = this.productCollectProcess(productCollectDto);
				productCollectDto.setTimeId(productCollectOrder.getOutTradeNo());//订单编码
				productCollectDto.setTimeDesc(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(productCollectOrder.getCreateTime()));//下单时间
				productCollectDto.setPrice(productCollectOrder.getTotalFee().divide(new BigDecimal(100)).setScale(2).toString());//下单金额
				productCollectDto.setState(productCollectOrder.getState());//下单状态
				productCollectDto.setPhone(productCollectOrder.getUserPhone());
				productCollectDto.setQuantity(productCollectOrder.getQuantity());
				tempOrders.add(productCollectDto);
			}
			if (orders != null && orders.size()>0) {
				return new ResultWithJson<ProductCollectDto>("0", "msg", tempOrders);
			} else {
				return new ResultWithJson("0", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson("0", "error");
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
