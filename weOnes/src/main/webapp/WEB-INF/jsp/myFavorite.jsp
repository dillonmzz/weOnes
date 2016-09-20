<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
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
            <a href="/weOnes/meController" class="head-button head-button-left icon-back back-green"></a>
            <h1 class="title">我的收藏</h1>
        </header>
        <div class="wrap">
            <div class="my_orders my_message my_favorites">
                <ul id="container">
                <c:choose>
				  <c:when test="${!empty nullInfo}">
							${nullInfo}
				  </c:when>
				  <c:otherwise>
					<c:forEach items="${list}" var="myFavorite" varStatus="vs">
                    <li class="order_message" id="li${myFavorite.activityId}">
                        <a  href="/weOnes/activity/activityDetails?activityId=${myFavorite.activityId}">
                            <img src="${myFavorite.activity.thumbnails}" width="84" />
                        </a>
                        <dl>
                            <h1>
                                <a  href="/weOnes/activity/activityDetails?activityId=${myFavorite.activityId}">${myFavorite.activity.title}</a>
                            </h1>
                            <div class="sub-headin">
                                <span>${myFavorite.activity.subhead}</span>
                            </div>
                            <div class="optbtns">
                                <span class="orange">${myFavorite.activity.newPrice/100}￥</span>
                                <span style="float:right"> 
                                    <a href="javascript:void(0);"
                                     id="delMyFavorite${myFavorite.activityId}" class="opera">移除</a>
                                </span>
                            </div>
                        </dl>
                        <div class="clear"></div>
                    </li>
                    <script type="text/javascript">
										$("#delMyFavorite${myFavorite.activityId}")
												.click(
														function() {
															var activityId = "${myFavorite.activityId}";
															alert("del"+activityId);
															$
																	.ajax({
																		type : "POST",
																		dataType : "json",
																		url : "/weOnes/myFavorite/delMyFavoriteByActivityId",
																		data : {
																			activityId : activityId
																		},
																		success : function(
																				data) {
																			if (data.errorCode == "0") {
																				alert("删除成功");
																				$("#li${myFavorite.activityId}").remove();
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
																				alert("删除失败");
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
																			alert("删除失败");
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
        <div class="clear"></div>
    </div>
</body>
</html>