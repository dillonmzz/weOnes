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
import cn.com.blueline.entity.ProductCollectFavorite;
import cn.com.blueline.service.ProductCollectService;


@Controller
@RequestMapping("/myProductCollectFavorite")
public class MyProductCollectFavoriteController {
	
	
	@Autowired
	private ProductCollectService  collectService;
	

	/*
	 * 查询我的收藏列表页面
	 */
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String list(Model model,HttpServletRequest request) {
		String openId = (String)request.getSession().getAttribute("openId");
		
		//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
		System.out.println("当前用户("+openId+")正在查看我的收藏列表");
		List<ProductCollectFavorite> list = collectService.queryAllByOpenId(openId);
		if (list == null || list.size()==0) {
			model.addAttribute("nullInfo", "您还没有关注任何产品");
		} else {
			List<ProductCollectDto> tempList = new ArrayList<ProductCollectDto>();
			for (ProductCollectFavorite favorite : list) {
				ProductCollectDto productCollectDto = collectService.appQueryById(favorite.getProductCollectId());
				productCollectDto = this.productCollectProcess(productCollectDto);
				productCollectDto.setId(favorite.getId());//关注ID
				productCollectDto.setTimeId(favorite.getProductCollectId().toString());//产品ID
				productCollectDto.setTimeDesc(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(favorite.getCreateTime()));//关注时间
				tempList.add(productCollectDto);
			}
			model.addAttribute("list", tempList);
		}
		return "app/me/myFavorite";
	}
	
	
	/**
	 * 根据用户ID产品ID 收藏或取消关注
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/favoriteOrCancel", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody ResultWithJson favoriteOrCancelByOpenIdAndProductCollectId(HttpServletRequest request) {
		try {
			String openId = (String) request.getSession().getAttribute("openId"); 
			//openId = "o9bmjsy3prTRGYuK6mqxm21E_vdg";
			Long productCollectId = Long.parseLong(request.getParameter("productCollectId"));
		    Integer state = Integer.parseInt(request.getParameter("state"));
		    System.out.println("productCollectId"+productCollectId);
		    System.out.println("state"+state);
			if(openId ==null || productCollectId == null){
				return new ResultWithJson("1", "error");
			}
			int rowNum = collectService.favoriteOrCancel(openId, productCollectId, state);
			if (rowNum > 0) {
				 return new ResultWithJson("0", "ok");
			} else {
				return new ResultWithJson("1", "error");
			}
		} catch (Exception e) {
			System.out.println("移除收藏过程出现异常,可能是当前会话中(session)无openId");
			return new ResultWithJson("1", "移除失败");
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
