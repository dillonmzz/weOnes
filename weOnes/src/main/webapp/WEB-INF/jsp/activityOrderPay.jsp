<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=yes">
<meta name="description" content="玩天下 | 精选 · 美食 · 玩法" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="robots" content="noodp,noydir">
<title>玩天下</title>
</head>
<body>
	<span>金额:0.01元</span>
	<br>
	<a href="javascript:callPay();" id="btnOrder">点击支付</a>
	<script src="/weOnes/js/jquery-2.2.0.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		function onBridgeReady() {
			var appId = "${resData.appid}".toString();
			var timeStamp = "${resData.timeStamp}".toString();
			var nonceStr = "${resData.nonce_str}".toString();
			var prepay_id = "prepay_id=${resData.prepay_id}".toString();
			var signType = "MD5".toString();
			var paySign = "${resData.sign}".toString();
			alert("appId="+appId+"&timeStamp="+timeStamp+"&nonceStr="+nonceStr+"&package="+prepay_id+"&signType="+signType+"&paySign="+paySign);

			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : appId,//"wx2421b1c4370ec43b", //公众号名称，由商户传入   
				"timeStamp" : timeStamp,//"1395712654", //时间戳，自1970年以来的秒数  
				"nonceStr" : nonceStr,//"e61463f8efa94090b1f366cccfbbb444", //随机串   
				"package" : prepay_id,//"prepay_id=u802345jgfjsdfgsdg888",
				"signType" : signType,//"MD5", //微信签名方式 
				"paySign" : paySign,//微信签名 
			}, function(res) {
				alert(res.err_msg);
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					alert("支付成功");
				}
				if (res.err_msg == "get_brand_wcpay_request:cancel") {
					alert("交易取消");
				}
				if (res.err_msg == "get_brand_wcpay_request:fail") {
					alert("支付失败");
				}
			});
		}

		
		
		function callPay() {
			alert("微信签名:${resData.sign}");
			if (typeof WeixinJSBridge == "undefined") {
				if (document.addEventListener) {
					document.addEventListener('WeixinJSBridgeReady',
							onBridgeReady, false);
				} else if (document.attachEvent) {
					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
					document
							.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				}
			} else {
				onBridgeReady();
			}
		}
	</script>
</body>
</html>