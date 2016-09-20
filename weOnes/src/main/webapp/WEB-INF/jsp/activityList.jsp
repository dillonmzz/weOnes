<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<title>玩天下</title>
	<link href="<%=request.getRealPath("/")%>weOnes/css/weiui.min.css" media="screen" rel="stylesheet" type="text/css" />
	<link href="/weOnes/css/jquery-weiui.min.css" media="screen" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/weOnes/css/common.css">
	<link href="/weOnes/css/style.css" media="screen" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/weOnes/css/flexslider.css">
</head>
<div id="loading" class="loading"></div>
	<div id="pageContainer" class="page">
		<header class="bar bar-nav">
			<div style="width:20%;float:left;padding-top:0;">
				<a href="/weOnes/City-switch.html" style="color:#FBB03B"><span style="float:left;width:60%;white-space:nowrap;margin-top:0;overflow:hidden;text-overflow:ellipsis;text-align:center;">北京</span><span class="icon icon-down-nav" style="font-size:10px;"></span></a>
			</div>
			<form action="/" method="GET" id="mainSearch" >
				<div class="tjb-order-search-wrap" style="float:left;width:80%;">
					<label for="tjb-act-search">
						<input type="search" id="tjb-act-search" class="tjb-order-search" placeholder="搜索精彩活动" utocapitalize="off" autocorrect="off" autocomplete="off" spellcheck="false" data-tjb>
						<span class="tjb-icon tjb-icon-search-grey"></span>
					</label>
					<span class="cancel-search-mode">取消</span>
				</div>
			</form>
			<div class="logo-1">
				<a href="/weOnes/meController"><img src="${headimgurl}"></a>
			</div>
		</header>
		<div id="container">
			<div class="flexslider">
				<ul class="slides">
					<li>
						<img src="/weOnes/images/1.jpg" />
					</li>
					<li>
						<img src="/weOnes/images/2.jpg" />
					</li>
					<li>
						<img src="/weOnes/images/3.jpg" />
					</li>
					<li>
						<img src="/weOnes/images/4.JPG" />
					</li>
				</ul>
			</div>
			<div class="xiaomo-wrap">
				<div class="xiaomo-head">推荐类别</div>
					<div class="xiaomo-list-wrap">
					<div class="xiaomo-list clearfix" style="width:792px;">
						<a href="">
							<div class="xiaomo-pic" style="background-image:url(/weOnes/images/1.jpg);">
							</div>
						</a>
						<a href="">
							<div class="xiaomo-pic" style="background-image:url(/weOnes/images/2.jpg);"></div>
						</a>
						<a href="">
							<div class="xiaomo-pic" style="background-image:url(/weOnes/images/3.jpg);"></div>
						</a>
						<a href="">
							<div class="xiaomo-pic" style="background-image:url(/weOnes/images/4.JPG);"></div>
						</a>
						<a href="">
							<div class="xiaomo-pic" style="background-image:url(/weOnes/images/5.JPG);" ></div>
						</a>
						<a href="" >
							<div class="xiaomo-pic" style="background-image:url(/weOnes/images/6.JPG);" ></div>
						</a>
						<a href="/zhuanti/1155">
							<div class="xiaomo-pic" style="background-image:url(/weOnes/images/7.JPG);" ></div>
						</a>
					</div>
				</div>
			</div>
			
			<div id="goods_list" class="goods_list bgWhite">
			<!--列表页-->
			<c:forEach items="${list}" var="activity" varStatus="vs">
				<div class="act first" data-id="107789" >
					<div class="act-pic" style="background-image: url();">
						<a href="activityDetails?activityId=${activity.activityId}">
							<img class="res" src="${activity.thumbnails}">
						</a>
						<div class="act-topic-tag">今日新品</div>
					</div>
					<div class="act-intro">
						<div class="act-title">${activity.title}</div>
						<span></span>
						<p class="act-price clearfix">
							<span></span>
							<span>￥${activity.newPrice}起</span>
						</p>
					</div>
					<div class="act-poi-name">
						<span class="zw-icon zw-icon-location"></span>
						<span class="act-poi-title">
							<span> </span>
							<span >${activity.subhead}</span>
							<span > </span>
					</span>
						<span></span>
						<span class="zw-icon zw-icon-clock" style="margin-left:10px;margin-right:2px;"></span>
						<span class="act-date-tag">
							<span > </span>
							<span >进行中</span>
						</span>
					</div>
				</div>
			</c:forEach>	
			<!--列表页结束-->
			   <div style="margin-bottom:30px;"></div>
				<div class="toolbar fixbottom">
					<a href="/weOnes/discovery.html" class="col2">
						<img src="/weOnes/images/faxian-1-1.PNG" alt=""> 发现
					</a>
					<a href="/weOnes/activity/list" class="col2 active"><img src="/weOnes/images/ios-2.PNG" />首页</a>
					<a href="/weOnes/meController" class="col2"><img src="/weOnes/images/ios-1.PNG" />我的</a>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div>
			<a href=""></a>
		</div>
	</footer>
	<script src="/weOnes/js/jquery-2.2.0.min.js" type="text/javascript"></script>
	<script src="/weOnes/js/jquery.flexslider.js"></script>
	<script>
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation: "slide",
				slideshow: true,
				directionNav: false,
				slideshowSpeed: 4000,
				animationSpeed: 2000,
			});
		});
	</script>
</body>
</html>