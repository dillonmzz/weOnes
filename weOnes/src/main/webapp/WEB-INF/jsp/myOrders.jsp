<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<link rel="stylesheet" href="/weOnes/css/common.css">
<link rel="stylesheet" href="/weOnes/css/style.css">
<script src="/weOnes/js/jquery-2.2.0.min.js"></script>
</head>
<body>
	<div id="shade"></div>
	<div class="small-shells"></div>
	<div class="append">删除成功</div>
	<div class="small-shells1"></div>
	<div class="cancel">删除失败</div>
	<div id="page">
		<header class="no-green">
			<a href="<%=request.getContextPath()%>/myController/v2"
				class="head-button head-button-left icon-back back-green"></a>
			<h1 class="title">我的订单</h1>
		</header>
		<div class="wrap">
			<div class="main nomargin">
				<div class="my_orders">
					<ul id="container">
						<c:choose>
							<c:when test="${!empty nullInfo}">
							${nullInfo}
						</c:when>
							<c:otherwise>
								<c:forEach items="${list}" var="order" varStatus="vs">
									<li id="li${order.transactionId}">
										<h2>
											<a href="/index-1.html">${order.activity.title}</a>
											<c:choose>
												<c:when test="${order.state==0}">
													<span>待支付</span>
												</c:when>
												<c:when test="${order.state==1}">
													<span>已支付</span>
												</c:when>
												<c:otherwise>
													<span>已处理</span>
												</c:otherwise>
											</c:choose>
										</h2> <a href="/weOnes/activity/activityDetails?activityId=${order.activityId}">
											<div class="order_detail">
												<img src="${order.activity.thumbnails}" width="70"
													height="70" />
												<h3>
													出发时间：
													<fmt:formatDate value="${order.activity.startTime}"
														pattern="yyyy-MM-dd HH:mm" />
													<br> 订单编号：${order.outTradeNo}<br>
												</h3>
											</div>
									</a>
										<div class="order_money">
											订单金额：${order.totalFee} <span class="orange"></span>元
										</div>
										<div class="order_pay">
											<a href="javascript:void(0);"
												id="delOrder${order.transactionId}">删除订单</a>
										</div>
									</li>
									<script type="text/javascript">
										$("#delOrder${order.transactionId}")
												.click(
														function() {
															var transactionId = "${order.transactionId}";
															$
																	.ajax({
																		type : "POST",
																		dataType : "json",
																		url : "/weOnes/wxPayOrder/delOrderByTransactionId",
																		data : {
																			transactionId : transactionId
																		},
																		success : function(
																				data) {
																			if (data.errorCode == "0") {
																				
																				$("#li${order.transactionId}").remove();
																				$(
																						".small-shells")
																						.show()
																						.delay(
																								1500)
																						.hide(
																								300);
																				$(
																						".append")
																						.show()
																						.delay(
																								1500)
																						.hide(
																								300);
																				$(
																						"#shade")
																						.show()
																						.delay(
																								1500)
																						.hide(
																								300);
																			} else {
																				
																				$(
																						".small-shells1")
																						.show()
																						.delay(
																								1500)
																						.hide(
																								300);
																				$(
																						".cancel")
																						.show()
																						.delay(
																								1500)
																						.hide(
																								300);
																				$(
																						"#shade")
																						.show()
																						.delay(
																								1500)
																						.hide(
																								300);
																			}
																		},
																		error : function(
																				data,
																				status,
																				e) {
																			
																			$(
																					".small-shells1")
																					.show()
																					.delay(
																							1500)
																					.hide(
																							300);
																			$(
																					".cancel")
																					.show()
																					.delay(
																							1500)
																					.hide(
																							300);
																			$(
																					"#shade")
																					.show()
																					.delay(
																							1500)
																					.hide(
																							300);
																		}
																	});
														});
									</script>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</ul>

				</div>
			</div>
		</div>
	</div>
</body>
</html>