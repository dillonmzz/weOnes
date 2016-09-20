<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<title>订单详情</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
		<meta name="apple-moblie-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/orderPre.css">
	</head>
	<body>
		<header class="zw-fee-header">
			<center>
				<a class="zw-fee-back fleft zwback"></a>
				确认支付
				<a class="fright"></a>
			</center>
		</header>

		<div class="content">
			<div class="zw-fee-content">
				<div class="zw-fee-tip">
					请在1小时内（至<span class="pay_live_time">结束时间</span>）完成付款，逾期将取消订单
				</div>
				<div class="zw-fee-info">
					<p>预订人：
						<a href="javascript:void(0);" style="color:#aeaeae;">${phone}</a>
						<a href="javascript:void(0);" style="color:#aeaeae;">${nickname }</a>
					</p>
				</div>
				<div class="zw-fee-time">
					<em class="zw-fee-time-imgs" style="background-image:url(${coverPhoto});"></em>
					<div class="zw-fee-time-content">
						<div class="h3">${productName }</div>
						<p class="pf">${timeDesc }</p>
					</div>
				</div>
				<div class="zw-fee-info-detail">
					<ul>
						<li class="clearfix">
							<p class="fleft pt">
								单价
							</p>
							<p class="fright pf">
								¥<span>${price }</span>
							</p>
						</li>
						<li class="clearfix">
							<p class="fleft pt">
								数量
							</p>
							<p class="fright pf">
								<span class="icon icon-close" style="font-size:14px;font-weight:bold;"></span>
								<span>${quantity }</span>
							</p>
						</li>
						<li class="clearfix">
							<p class="fleft pt">
								支付金额
							</p>
							<p class="fright pf">
								<em>¥</em><span class="red">${totalFee}</span>
							</p>
						</li>
					</ul>
				</div>
				<div class="zw-fee-way">
					<p>微信</p>
				</div>
			</div>
		</div>
		<footer class＝"zw-fee-footer">
			<div id="payBt" class="zw-fee-submit">立即支付</div>
		</footer>
	</body>
</html>
<script type="text/javascript">

$(".zw-fee-submit").click(function() {
	$.ajax({
		type:"POST",
		dataType : "json",
		url:"<%=request.getContextPath()%>/collect/wexinpay/${outTradeNo}",
        success:function(data){
        	var data = data.datas;
        	alert(data.return_msg);
        	 if (data.return_msg == "OK") {
                   if (typeof WeixinJSBridge == "undefined") {
                	   alert("调用微信支付js api 失败");
       				if (document.addEventListener) {
       					document.addEventListener('WeixinJSBridgeReady',
       							onBridgeReady, false);
       				} else if (document.attachEvent) {
       					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
       					document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
       				}
       			} else {
       				alert("开始调用微信支付js api");
       				onBridgeReady(data);
       			}
                } else {
                   alert("下单失败");
                }
		}
		});
	});

	function onBridgeReady(data) {
		alert("进入支付js"+data.appid.toString());
		var appId = data.appid.toString();
		var timeStamp = data.timeStamp.toString();
		var nonceStr = data.nonce_str.toString();
		var prepay_id = "prepay_id="+data.prepay_id.toString();
		var signType = "MD5".toString();
		var paySign = data.sign.toString();
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
			"appId" : appId,//"wx2421b1c4370ec43b", //公众号名称，由商户传入   
			"timeStamp" : timeStamp,//"1395712654", //时间戳，自1970年以来的秒数  
			"nonceStr" : nonceStr,// 随机串   
			"package" : prepay_id,//"prepay_id=u802345jgfjsdfgsdg888",
			"signType" : signType,//微信签名方式  "MD5"
			"paySign" : paySign,//微信签名 
		}, function(res) {
			if (res.err_msg == "get_brand_wcpay_request:ok") {
				alert("支付成功o(∩_∩)o");
				//支付成功后，用户点击完成按钮后，会触发后台业务逻辑代码
			}
			if (res.err_msg == "get_brand_wcpay_request:cancel") {
				alert("交易取消");
				//若用户没有输入密码，取消支付，会出发后台业务逻辑代码
			}
			if (res.err_msg == "get_brand_wcpay_request:fail") {
				alert("支付失败");
			}
		});
	}
	
</script>