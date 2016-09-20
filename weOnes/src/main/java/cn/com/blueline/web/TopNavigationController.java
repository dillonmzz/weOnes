package cn.com.blueline.web;

import java.util.ArrayList;
import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





import cn.com.blueline.dto.ProductCollectDto;
import cn.com.blueline.dto.ResultWithJson;
import cn.com.blueline.service.CitySwitchService;
import cn.com.blueline.service.ProductCollectService;
import cn.com.blueline.service.TopNavigationService;

/**
 * 首页顶部导航栏控制
 * @author Zhaozhi
 *
 */
@Controller
@RequestMapping("/topnav")
public class TopNavigationController {
	
	
	@Autowired private TopNavigationService service;
	
	@RequestMapping(value = "/list/{city}/{type}/{page}", method = RequestMethod.GET, 
					produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultWithJson<ProductCollectDto> productCollectList(@PathVariable("city")String city, @PathVariable("type")String type,@PathVariable("page")int page){
		try {
			System.out.println("城市:"+city);
			System.out.println("类别:"+type);
			List<ProductCollectDto> list = null;
			int  currentPageNum = page;
			//list= service.queryByCity(currentPageNum, city);
			list= service.queryByCityAndType(currentPageNum, city, type);
			List<ProductCollectDto> tempList = new ArrayList<ProductCollectDto>();
			for (ProductCollectDto productCollectDto : list) {
				productCollectDto = this.productCollectProcess(type,productCollectDto);
				tempList.add(productCollectDto);
			}
			int tempListSize = tempList.size();
			if(tempListSize>0){
				return new ResultWithJson<ProductCollectDto>("0", ""+tempListSize,tempList);
			}else{
				return new ResultWithJson<ProductCollectDto>("1", "error");
			}
		} catch (Exception e) {
			return new ResultWithJson<ProductCollectDto>("1", "error");
		}
		
	}
	
	private ProductCollectDto productCollectProcess(String type,ProductCollectDto productCollectDto){
		
		if(type.equals("place")){//如果查询的是场地,封面显示场地的信息
			productCollectDto.setCoverPhoto(productCollectDto.getPlacePhoto());
			productCollectDto.setName(productCollectDto.getPlaceName());
			productCollectDto.setTitle(productCollectDto.getPlaceTitle());//场地设备
			return productCollectDto;
		}else if(type.equals("talent")){//如果查询的是达人,封面显示达人的信息
			productCollectDto.setCoverPhoto(productCollectDto.getTalentPhoto());
			productCollectDto.setName(productCollectDto.getTalentName());
			productCollectDto.setTitle(productCollectDto.getTalentTitle());
			return productCollectDto;
		}else{
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
	
	


}
