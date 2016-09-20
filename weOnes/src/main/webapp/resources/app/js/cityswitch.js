$(function(){
	var currentPage = 1;
	var flag = true;
/*首页点击城市弹出城市列表*/
$(".re_top_attr").on("click",function(){
$(".content").remove();
var appendHTML = "<div class='content' style='display: none'> "+
"<header class='bar bar-nav'><a class='icon icon-left-nav city-cancel "+
"pull-left zwback' style='color: #eb6409'></a> "+
"<h1 class='title'>切换城市</h1></header> "+
"<div id='citylist-wrap'><div><ul> "+
"<li class='citylist-title'>定位城市</li> "+
"<li class='citylist'><a data-city-id='10898' data-pinyin=''>北京</a> "+
"</li></ul><ul><li class='citylist-title'>热门城市</li> <li class='citylist'><a data-city-id='10000' data-pinyin='quanguo'>全国</a></li>"+
"<li class='citylist'><a data-city-id='10898' "+
"data-pinyin='beijing'>北京</a></li> "+
"<li class='citylist'><a data-city-id='10896' "+
"data-pinyin='shanghai'>上海</a></li> "+
"<li class='citylist'><a data-city-id='10894' "+
"data-pinyin='guangzhou'>广州</a></li> "+
"<li class='citylist'><a data-city-id='10893' "+
"data-pinyin='shenzhen'>深圳</a></li> "+
"<li class='citylist'><a data-city-id='2269' "+
"data-pinyin='nanjing'>南京</a></li> "+
"<li class='citylist'><a data-city-id='7502' "+
"data-pinyin='hangzhou'>杭州</a></li> "+
"<li class='citylist'><a data-city-id='2267' "+
"data-pinyin='suzhou'>苏州</a></li> "+
"<li class='citylist'><a data-city-id='10891' "+
"data-pinyin='chengdu'>成都</a></li> "+
"<li class='citylist'><a data-city-id='10824' "+
"data-pinyin='chongqing'>重庆</a></li> "+
"<li class='citylist'><a data-city-id='10897' "+
"data-pinyin='qingdao'>青岛</a></li> "+
"<li class='citylist'><a data-city-id='2268' "+
"data-pinyin='xiamen'>厦门</a></li> "+
"<li class='citylist'><a data-city-id='10889' "+
"data-pinyin='wuhan'>武汉</a></li> "+
"<li class='citylist'><a data-city-id='10821' "+
"data-pinyin='zhengzhou'>郑州</a></li> "+
"<li class='citylist'><a data-city-id='10895' "+
"data-pinyin='xian'>西安</a></li> "+
"<li class='citylist'><a data-city-id='10892' "+
"data-pinyin='tianjin'>天津</a></li> "+
"<li class='citylist'><a data-city-id='1070' "+
"data-pinyin='jinan'>济南</a></li> "+
"</ul></div></div></div>";
$(".index_re_Top").after(appendHTML);
$(".content").show();
});
/*选中城市列表 ,
 * 获取城市数据,
 * 影藏弹出框,
 * 删除 index_re_Ul下li所有元素 ,
 * ajax请求服务器返回json,清空首页的数据并遍历json数据*/	
$(".index_re").on("click",".citylist",function(){
	currentPage=1;
	flag = true;
	var that = $(this);
	var city = that.find("a").text();
	$(".re_top_attr").text(city);
	var type = $(".nav-t.active").find("a").attr("name");//获取当前产品类别
	if(city=='全国'){
		city = "allcity";
		var url = "/weOnes/cityswitch/list/"+city+"/"+type+"/1";
	}else{
		var url = "/weOnes/cityswitch/list/"+city+"市/"+type+"/1";
	}
	console.log(url);
	$(".content").hide();
	$(".index_re_Ul > li").remove();
	 //加载动画
	$(".loadingDiv").show();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : url,
		success : function(data) {
			if(data.errorCode == "0"){
				var list = data.data;
				var collect;
				var appendHTML;
				for(var i = 0;i<list.length;i++){
					collect = list[i];
					var timeDesc =  new Date(collect.timeDesc);
            		var timeStr = timeDesc.getFullYear()+'-'+(timeDesc.getMonth()+1)+'-'+timeDesc.getDate();
					appendHTML ="<li class='index_re_List'> "+
					"<div class='t'><a href='#'><div class='index_re_List_Tx'><fieldset avalonctrl='lazy_load_controll'>  "+
					"<img height='182' src='/weOnes/resources/app/images/business.jpg' alt='北京游侠客'> "+
					"</fieldset></div><span class='index_re_List_Name'>"+collect.id+"</span></a><a href='#'></a></div> "+
					"<div class='c'><a class='index_re_List_Pic dt_content_pic' href='/weOnes/collect/detail/"+collect.id+"'> "+
					"<fieldset avalonctrl='lazy_load_controll'><img height='182' src='"+collect.coverPhoto+"' ></fieldset></a></div> "+
					"<div class='b'><a class='index_re_List_Txt' href='/weOnes/collect/detail/"+collect.id+"'> "+
					"<h4 class='index_re_List_Tittle'>"+collect.name+"</h4><div class='index_re_List_timeQu'> "+
					"<p class='index_re_List_time'>"+timeStr.substr(5,5)+"</p> "+
					"<p class='index_re_List_join'><span>￥"+collect.price+"</span></p></div> "+
					"<div class='index_re_List_Attr'><p>"+collect.city+"."+collect.district+"</p></div></a></div> "+
					"</li>";
					$(".index_re_Ul").append(appendHTML);
				}
			}else{
				$(".loadingDiv").hide();
				alert("当前城市没有数据");
			}
		},
		complete:function(){
			$(".loadingDiv").hide();
        }
	})
});
/*城市列表取消按钮*/
$(".index_re").on("click",".city-cancel",function(){
	$(".content").hide();
});

/*首页查看更多活动*/
//$(".index_re_More").on("click",function(){
function readMore(){
	if(!flag){//如果flag是false 不执行
		return false;
	}
	var city = $(".re_top_attr").text();
	++currentPage;
	var type = $(".nav-t.active").find("a").attr("name");//获取当前产品类别
	if(city=='全国'){
		city = "allcity";
		var url = "/weOnes/cityswitch/list/"+city+"/"+type+"/"+currentPage;
	}else{
		var url = "/weOnes/cityswitch/list/"+city+"市/"+type+"/"+currentPage;
	}
	console.log(url);
	$.ajax({
		type : "GET",
		dataType : "json",
		url : url,
		beforeSend: function(){
		},
		success : function(data) {
			if(data.errorCode == "0"){
				var list = data.data;
				var collect;
				var appendHTML;
				for(var i = 0;i<list.length;i++){
					collect = list[i];
					appendHTML ="<li class='index_re_List'> "+
					"<div class='t'><a href='#'><div class='index_re_List_Tx'><fieldset avalonctrl='lazy_load_controll'>  "+
					"<img height='182' src='/weOnes/resources/app/images/business.jpg' alt='北京游侠客'> "+
					"</fieldset></div><span class='index_re_List_Name'>"+collect.id+"</span></a><a href='#'></a></div> "+
					"<div class='c'><a class='index_re_List_Pic dt_content_pic' href='/weOnes/collect/detail/"+collect.id+"'> "+
					"<fieldset avalonctrl='lazy_load_controll'><img height='182' src='"+collect.coverPhoto+"' ></fieldset></a></div> "+
					"<div class='b'><a class='index_re_List_Txt'  href='/weOnes/collect/detail/"+collect.id+"'> "+
					"<h4 class='index_re_List_Tittle'>"+collect.name+"</h4><div class='index_re_List_timeQu'> "+
					"<p class='index_re_List_time'>"+collect.timeDesc.substr(5,5)+"</p> "+
					"<p class='index_re_List_join'><span>￥"+collect.price+"</span></p></div> "+
					"<div class='index_re_List_Attr'><p>"+collect.city+"."+collect.district+"</p></div></a></div> "+
					"</li>";
					$("li.index_re_List:last-child").after(appendHTML);
				}
			}else{
				alert("无数据");
				flag = false;
			}
		}
	})
//});
	}

/*首页导航栏切换场地达人*/
$(".nav-t a").on("click",function(){
	currentPage=1;
	flag = true;
	var that = $(this);
	var type = that.attr("name");
	var city = $(".re_top_attr").text();
	if(type=='near'){//如果点击附件
		city = $(".mylocation").val();//获取用户的当前地区
		var url = "/weOnes/cityswitch/list/"+city+"/"+type+"/"+currentPage;
	}else{
		if(city=='全国'){
			city = "allcity";
			var url = "/weOnes/cityswitch/list/"+city+"/"+type+"/1";
		}else{
			var url = "/weOnes/cityswitch/list/"+city+"市/"+type+"/1";
		}
	}
	
	console.log(url);
	$(".content").hide();
	$(".index_re_Ul > li").remove();
	 //加载动画
	$(".loadingDiv").show();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : url,
		beforeSend: function(){
		},
		success : function(data) {
			if(data.errorCode == "0"){
				var list = data.data;
				var collect;
				var appendHTML;
				for(var i = 0;i<list.length;i++){
					collect = list[i];
					var timeDesc =  new Date(collect.timeDesc);
            		var timeStr = timeDesc.getFullYear()+'-'+(timeDesc.getMonth()+1)+'-'+timeDesc.getDate();
					appendHTML ="<li class='index_re_List'> "+
					"<div class='t'><a href='#'><div class='index_re_List_Tx'><fieldset avalonctrl='lazy_load_controll'>  "+
					"<img height='182' src='/weOnes/resources/app/images/business.jpg' alt='北京游侠客'> "+
					"</fieldset></div><span class='index_re_List_Name'>"+collect.id+"</span></a><a href='#'></a></div> "+
					"<div class='c'><a class='index_re_List_Pic dt_content_pic' href='/weOnes/collect/detail/"+collect.id+"'> "+
					"<fieldset avalonctrl='lazy_load_controll'><img height='182' src='"+collect.coverPhoto+"' ></fieldset></a></div> "+
					"<div class='b'><a class='index_re_List_Txt' href='/weOnes/collect/detail/"+collect.id+"'> "+
					"<h4 class='index_re_List_Tittle'>"+collect.name+"</h4><div class='index_re_List_timeQu'> "+
					"<p class='index_re_List_time'>"+timeStr.substr(5,5)+"</p> "+
					"<p class='index_re_List_join'><span>￥"+collect.price+"</span></p></div> "+
					"<div class='index_re_List_Attr'><p>"+collect.city+"."+collect.district+"</p></div></a></div> "+
					"</li>";
					$(".index_re_Ul").append(appendHTML);
				}
			}else{
				$(".loadingDiv").hide();
				alert("当前城市没有数据");
			}
		},
		complete:function(){
			$(".loadingDiv").hide();
        }
	})
});
/*底部加载更多自动刷新*/
$(window).scroll(function(){
    var viewH =$(window).height(),//可见高度
      contentH =$(document.body).height(),//内容高度
      scrollTop =$(window).scrollTop();//滚动高度
//      console.log("可见高度="+viewH);
//      console.log("内容高度="+contentH);
//      console.log("滚动高度="+scrollTop);
    if(contentH - viewH - scrollTop <= 100) {
    	readMore();
    	return false;
    }
  });


});