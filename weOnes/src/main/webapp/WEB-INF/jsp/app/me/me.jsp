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
<title>面墩儿</title>
<link rel="stylesheet" href="/weOnes/css/common.css">
<link rel="stylesheet" href="/weOnes/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/common.css">
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
					<li>
						<a href="#/collection.html">消息中心 
							<span class="icon-more"></span>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/myorder/list">我的订单 
							<span class="icon-more"></span>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/myProductCollectFavorite/list">我的收藏 
							<span class="icon-more"></span>
						</a>
					</li>
					<li>
						<a href="#/integral.html">我的积分 
							<span class="icon-more"></span>
						</a>
					</li>
					<li>
						<a href="#/coupon.html">我的优惠券 
							<span class="icon-more"></span>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/feedbackController/index">意见反馈
							<span class="icon-more"></span>
						</a>
					</li>
				</ul>
			</div>
			<div class="user-list">
				<ul>
					<li>
						<a href="<%=request.getContextPath()%>/myController/index">个人信息 
							<span class="icon-more"></span>
						</a>
					</li>
				</ul>
			</div>
			<div class="user-list">
				<ul>
					<li>
						<a href="#/set-up.html">设置 
							<span class="icon-more"></span>
						</a>
					</li>
				</ul>
			</div>
			<%@ include file="../../common/foot.jsp" %>
		</div>
	</div>
</body>
</html>