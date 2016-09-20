<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta name="robots" content="noodp,noydir">
<title>ME</title>
<link rel="stylesheet" href="/weOnes/css/common.css">
<link rel="stylesheet" href="/weOnes/css/style.css">
</head>
<body>
	<div id="page">
		<div class="wrap personer_center">
			<div class="user-head">
				<div class="user-head-portrait">
					<img src="${headimgurl}">
				</div>
				<div class="user-head-name"></div>
			</div>
			<div class="user-list">
				<ul>
					<li><a href="/collection.html">消息中心 <span
							class="icon-more"></span>
					</a></li>
					<li><a href="wxPayOrder/orderList">我的订单 <span class="icon-more"></span>
					</a></li>
					<li><a href="myFavorite/list">我的收藏 <span class="icon-more"></span>
					</a></li>
					<li><a href="/integral.html">我的积分 <span class="icon-more"></span>
					</a></li>
					<li><a href="/coupon.html">我的优惠券 <span class="icon-more"></span>
					</a></li>
				</ul>
			</div>
			<div class="user-list">
				<ul>
					<li><a href="/personal.html">个人信息 <span class="icon-more"></span>
					</a></li>
				</ul>
			</div>
			<div class="user-list">
				<ul>
					<li><a href="/set-up.html">设置 <span class="icon-more"></span>
					</a></li>
				</ul>
			</div>
			<div class="toolbar fixbottom">
				<a href="/weOnes/discovery.html" class="col2">
                    <img src="/weOnes/images/faxian-1-1.PNG" alt="">发现
                </a>
				<a href="/weOnes/activity/list" class="col2 active" style="color: #000"> <img
					src="/weOnes/images/icon-1-3.PNG" />首页
				</a> <a href="/weOnes/meController" class="col2 active"> <img
					src="/weOnes/images/icon-1-2.PNG" /> 我
				</a>
			</div>
		</div>
	</div>
</body>
</html>