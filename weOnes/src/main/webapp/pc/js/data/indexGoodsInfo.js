$(function(){
	$.ajax({
		type:"GET",
		dataType : "json",
		url:"/weOnes/goods",
	    success:function(data){
	    	 if (data.errorCode == "0") {
	    		 var goodsInfoList = data.data;
	 			 //var goosdInfo;
	 			 //获取精彩活动推荐div元素
	 			 var index_activitys = $(".index_activity_8");
 				 //alert(index_activitys.length);
	 			 for(var i = 0;i<8;i++){//精彩活动推荐
	 				var goosdInfo = goodsInfoList[i];
	 				//index_activitys.eq(i).find("a").attr("href","/weOnes/goods/detail/"+goosdInfo.id);
	 				index_activitys.eq(i).find("a").attr("href","activityDetail.jsp?goodsId="+goosdInfo.id);
	 				index_activitys.eq(i).find("img").attr("src",goosdInfo.goodsPreviewPhoto);
	 				index_activitys.eq(i).find(".index_activity_title_8").html(goosdInfo.name);
	 				index_activitys.eq(i).find(".index_activity_introduction_8").html(goosdInfo.title);
	 			}
	 			var index_spaces = $(".index_space_6");
	 			for(var i = 8;i<14;i++){//我的地盘
	 				var goosdInfo = goodsInfoList[i];
	 				index_spaces.eq(8-i).find("a").attr("href","/weOnes/goods/detail/"+goosdInfo.id);
	 				index_spaces.eq(8-i).find("img").attr("src",goosdInfo.goodsPreviewPhoto);
	 				index_spaces.eq(8-i).find(".index_space_title_6").html(goosdInfo.name);
	 				index_spaces.eq(8-i).find(".index_space_area_6").html(goosdInfo.city+"."+goosdInfo.district);
	 			 }
	 			
	 			var index_peoples = $(".index_people_4");
	 			for(var i = 14;i<18;i++){//我带你High吧
	 				var goosdInfo = goodsInfoList[i];
	 				index_peoples.eq(14-i).find("a").attr("href","/weOnes/goods/detail/"+goosdInfo.id);
	 				index_peoples.eq(14-i).find(".new_head").attr("src",goosdInfo.goodsPreviewPhoto);
	 				index_peoples.eq(14-i).find(".index_people_name_4").html(goosdInfo.name);
	 				index_peoples.eq(14-i).find(".index_people_tag_4").html(goosdInfo.categoryChildName);
	 				index_peoples.eq(14-i).find(".index_people_content_4").html(goosdInfo.title);
	 			 }
	 			var index_goods = $(".index_goods_6");
	 			for(var i = 18;i<24;i++){//福利和优惠
	 				var goosdInfo = goodsInfoList[i];
	 				index_goods.eq(18-i).find("a").attr("href","/weOnes/goods/detail/"+goosdInfo.id);
	 				index_goods.eq(18-i).find("img").attr("src",goosdInfo.goodsPreviewPhoto);
	 				index_goods.eq(18-i).find(".index_goods_title_6").html(goosdInfo.name);
	 				index_goods.eq(18-i).find(".index_goods_price_6").html(goosdInfo.price);
	 			 }
	            } else {
	            	 layer.msg(data.msg,{icon: 2});
	            }
		},
		error: function() {
			
		}
	});// ajax end
	
	/**
	 * 获取数据库中的城市数据http://localhost:8080/weOnes/goods/existCity
	 */
	 $.get("/weOnes/goods/existCity",function(data){
			    //设置城市内容
		 console.log(data.datas);
			}, "json");
	
});