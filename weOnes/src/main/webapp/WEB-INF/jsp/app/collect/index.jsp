<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<style id="avalonStyle">
	.avalonHide{ display: none!important }
</style>
	<title>小面</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="HandheldFriendly" content="true">
	<link rel="apple-touch-icon" href=" ">
	<link rel="icon" type="image/x-icon" href="/favicon.ico" size="16x16 24x24 32x32">
	<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/common.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/other.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/cityswitch.css">
	<link href="<%=request.getContextPath()%>/resources/app/css/swiper.min.css" rel="stylesheet" type="text/css">
	
	<style>
		[ms-controller],[ms-important]{display:none}
		.tjb-toggle{
			width:100%;
			height:auto;
			background:#fff;
			overflow: hidden;
		}
		.nav-t{
			width:25%;
			float:left;
			text-align: center;
			padding:10px 0 10px 0;
		}
		.active{
			border-bottom: 2px solid #eb6409;
			color:#eb6409!important
		}
		.active a{color:#eb6409!important}
	</style>
	<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
	<script src="<%=request.getContextPath()%>/resources/app/js/cityswitch.js"></script>
	<script>
		$(document).ready(function() {
			$("#re_top_btn_member").click(function(){
				$(".re_top_btn_member_Ul").toggle();
			});
			$(".index_re_outSide").click(function(){
				$(".re_top_btn_member_Ul").hide();
			});
		});
	</script>
	</head>
	<body class="index_re">
		<div class="index_re_Top" avalonctrl="index_recommend_controller">
			<a id="re_top_search" href="" >
				<span>${nickname}</span>
				<div class="logo-1">
					<img src="${headimgurl}" alt="头像">
				</div>
			</a>
			<a href="javascript:void(0)" class="re_top_attr" >全国</a>
			<ul class="tjb-toggle">
				<li class="nav-t active"><a href="javascript:void(0);" name="delicate">精选</a></li>
				<li class="nav-t"><a href="javascript:void(0);" name="place">场地</a></li>
				<li class="nav-t"><a href="javascript:void(0);" name="talent">达人</a></li>
				<li class="nav-t"><a href="javascript:void(0);" name="near">附近</a></li>
			</ul>
		</div>
		
		
		<!--专题-->
		<div class="swiper-container swiper-container-horizontal swiper-container-android" avalonctrl="index_recommend_controller">
			<div class="swiper-wrapper" style="padding-top:40px">
				<!--repeat859530337155:start-->
				<div class="index_re_Zt swiper-slide swiper-slide-active" data-repeat-rendered="repeatCallback" style="width: 334px;">
					<a ontouchstart="" class="dt_content_pic" href="#"> 
						<img style="width:100%;" data-src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/01468823168680_cms0.jpg" src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/01468823168680_cms0.jpg" alt="北京-学英语">
					</a> 
				</div><!--repeat859530337155-->
				<div class="index_re_Zt swiper-slide swiper-slide-next" data-repeat-rendered="repeatCallback" style="width: 334px;">
					<a ontouchstart="" class="dt_content_pic" href="#"> 
					<img style="width:100%;" data-src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/21468810788084_cms2.jpg" src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/21468810788084_cms2.jpg" alt="北京-去骑马">
				</a> 
				</div><!--repeat859530337155-->
				<div class="index_re_Zt swiper-slide" data-repeat-rendered="repeatCallback" style="width: 334px;">
					<a ontouchstart="" class="dt_content_pic" href="#"> 
						<img style="width:100%;" data-src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/71468807939041_cms7.jpg" src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/71468807939041_cms7.jpg" alt="北京-国画">
					</a> 
				</div>				<!--repeat859530337155-->
				<div class="index_re_Zt swiper-slide" data-repeat-rendered="repeatCallback" style="width: 334px;">
					<a ontouchstart="" class="dt_content_pic" href="#"> 
						<img style="width:100%;" data-src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/91468805614685_cms9.jpg" src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/91468805614685_cms9.jpg" alt="北京-沉香">
					</a> 
				</div><!--repeat859530337155-->
				<div class="index_re_Zt swiper-slide" data-repeat-rendered="repeatCallback" style="width: 334px;">
					<a ontouchstart="" class="dt_content_pic" href="http://www.hdb.com/recommend/ao-all.html"> 
						<img style="width:100%;" data-src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/71468807196726_cms7.jpg" src="http://cdn.hudongba.com/upload/_oss/usercmsimg/201607/18/71468807196726_cms7.jpg" alt="北京-琴瑟和鸣">
					</a> 
				</div><!--repeat859530337155--><!--repeat859530337155:end-->
			</div>
			<div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
				<span class="swiper-pagination-bullet swiper-pagination-bullet-active"></span>
				<span class="swiper-pagination-bullet"></span>
				<span class="swiper-pagination-bullet"></span>
				<span class="swiper-pagination-bullet"></span>
				<span class="swiper-pagination-bullet"></span>
			</div>
		</div>
		<!--活动-->
		<div class="index_re_Hd" avalonctrl="index_recommend_controller">
			<ul class="index_re_Ul">
			<!--  for begin -->
			<c:forEach items="${list}" var="item" varStatus="vs">
				<li class="index_re_List">
					<div class="t">
						<a ontouchstart="" href="#">
							<div class="index_re_List_Tx">
								<fieldset avalonctrl="lazy_load_controll">
									<!--头像为空的时候加载默认图片-->
									<!--ms-if-->
									<img height="182" src="<%=request.getContextPath()%>/resources/app/images/business.jpg" alt="北京游侠客">
								</fieldset>
							</div>
							<span class="index_re_List_Name">${item.id}</span>
						</a>
						<a ontouchstart="" href="#"></a>
					</div>
					<div class="c">
						<a ontouchstart="" class="index_re_List_Pic dt_content_pic" href="<%=request.getContextPath()%>/collect/detail/${item.id}">
							<fieldset avalonctrl="lazy_load_controll">
								<!--头像为空的时候加载默认图片-->
								<img height="182" src="${item.coverPhoto}" >
							</fieldset>
						</a>
					</div>
					<div class="b">
						<a class="index_re_List_Txt" ontouchstart="" href="<%=request.getContextPath()%>/collect/detail/${item.id}">
							<h4 class="index_re_List_Tittle">${item.name }</h4>
							<div class="index_re_List_timeQu">
								<p class="index_re_List_time">${fn:substring(item.timeDesc,5,10)}</p>
								<p class="index_re_List_join"><span>￥${item.price }</span></p>
							</div>
							<div class="index_re_List_Attr"><p>${item.city}.${item.district}</p></div>
						</a>
					</div>
				</li>
			</c:forEach>
				<!--  for end -->
				
				
			</ul>
		</div>
		<a href="javascript:void(0);" class="index_re_More"  avalonctrl="index_recommend_controller">
			<span>查看更多活动</span>
		</a>
		<!--底部悬浮 -->
		<%@ include file="../../common/foot.jsp" %>
		<div id="cover2" style="display: none;"></div>
		<div id="toast" class="toast"></div>
		<div class="loadingDiv" id="loadingDiv" style="display:none;">
			<p class="pImg">
				<img src=" ">
			</p>
			<p class="pTxt"></p>
		</div>
		<script>
			$(".nav-t").on("click",function(){
				$(".nav-t").eq($(this).index()).addClass("active").siblings().removeClass('active'); 
			})
		</script>
   		<script>
			$(".user-boot ul li").on("click",function(){
				$(".user-boot ul li").eq($(this).index()).addClass("on").siblings().removeClass('on'); 
			})
		</script>
		
    </body>
</html>