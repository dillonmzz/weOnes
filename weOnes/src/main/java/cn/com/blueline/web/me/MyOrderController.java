package cn.com.blueline.web.me;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blueline.dto.ProductCollectDto;
import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.entity.ProductCollectOrder;
import cn.com.blueline.service.ProductCollectService;


@Controller
@RequestMapping("/myorder")
public class MyOrderController {


	
	@Autowired private ProductCollectService service;

	/**
	 * 根据当前的openId查询用户订单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String myorderlist(Model model, HttpServletRequest request) {
		String openId = (String) request.getSession().getAttribute("openId");
		//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		List<ProductCollectOrder> orders = service.queryOrdersByOpenId(openId);
		System.out.println(openId+"你的订单数量="+orders.size());
		List<ProductCollectDto> tempOrders = new ArrayList<ProductCollectDto>();
		for (ProductCollectOrder productCollectOrder : orders) {
			ProductCollectDto productCollectDto = service.appQueryById(productCollectOrder.getCollectId());
			productCollectDto = this.productCollectProcess(productCollectDto);
			productCollectDto.setTimeId(productCollectOrder.getOutTradeNo());//订单编码
			productCollectDto.setTimeDesc(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(productCollectOrder.getCreateTime()));//下单时间
			productCollectDto.setPrice(productCollectOrder.getTotalFee().toString());//下单金额
			productCollectDto.setState(productCollectOrder.getState());//下单状态
			tempOrders.add(productCollectDto);
		}
		if (orders == null || orders.size()==0) {
			model.addAttribute("nullInfo", "您还没有订单");
		} else {
			model.addAttribute("list", tempOrders);
		}
		return "app/me/myOrders";
	}
	
	
	/**
	 * 根据当前的openId查询用户订单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteByOutTradeno", method = RequestMethod.POST)
	@ResponseBody
	public ResultWithJson deleteByOutTradeno(HttpServletRequest request) {
		String openId = (String) request.getSession().getAttribute("openId");
		//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		String outTradeNo = request.getParameter("outTradeNo");
		//System.out.println("outTradeNo:"+outTradeNo);
		int rowNum = service.updatetoInvalid(outTradeNo, openId);
		System.out.println(outTradeNo+"订单删除rowNum"+rowNum);
		if(rowNum>0){
			return new ResultWithJson("0", "ok");
		}else{
			return new ResultWithJson("1", "删除失败");
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
