<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>玩天下</title>
    <link href="/weOnes/css/common.css"  rel="stylesheet" type="text/css" />
    <link href="/weOnes/css/style.css" rel="stylesheet" type="text/css" />
    <script src="/weOnes/js/jquery-2.2.0.min.js"></script>
</head>
<body>
    <div id="page">
        <div class="wrap">
           <div class="pageBack">
               <div class="iconBack">
                    <a href="javascript:revert();" class="head-button head-button-left icon-back back-green"></a>
                </div>
            </div>
            <div class="pageBack-right">
                <div class="iconBack">
                   <!-- <a href="#" class="openlink head-button head-button-right icon-star"></a> --> 
                    <c:choose>
				<c:when test="${activityState==0}">
					<a data_state="0" id="gotoFavorite" class="openlink head-button head-button-right icon-star"></a>
				</c:when>
				<c:otherwise>
					<a data_state="1" id="gotoFavorite" class="openlink head-button head-button-right icon-star2"></a>
				</c:otherwise>
			</c:choose>
			<script type="text/javascript">
			$("#gotoFavorite").click(function(){
				var currentState = $(this).attr("data_state");
				if(currentState=="0"){
					currentState="1";
				}else{
					currentState="0";
				}
				$.ajax({
					type:"GET",
	    			dataType : "json",
	    			url:"/weOnes/activity/favorite?activityId=${activity.activityId}&state="+currentState,
	                success:function(data){
	                	if(data.errorCode == "0"){
	                		if(currentState=="0"){
	                			$("#gotoFavorite").attr("class","openlink head-button head-button-right icon-star");
	                			$("#gotoFavorite").attr("data_state","0");
	                			$(".small-shells1").show().delay(1500).hide(300);
	                			$(".cancel").show().delay(1500).hide(300);
	                			$("#shade").show().delay(1500).hide(300);
	        				}else{
	        					$("#gotoFavorite").attr("class","openlink head-button head-button-right icon-star2");
	        					$("#gotoFavorite").attr("data_state","1");
	        					$(".small-shells").show().delay(1500).hide(300);
	        					$(".append").show().delay(1500).hide(300);
	                			$("#shade").show().delay(1500).hide(300);
	        				}
	                	}else{
	                		
	                	}
	                },
	                error:function(data, status, e){
	                	
	                }
				});
			});
			</script>
                </div>
            </div>
            <div class="pageBack-right-2">
                <div class="iconBack">
                    <a href="#" class="openlink head-button head-button-right icon-star3"></a>
                </div>
            </div>
            
            <div class="global_con-slide">
                <img src="${activity.thumbnails}" />
            </div>    
            <div class="tjb-block pt15" style="margin-bottom:10px;">
                <p class="tjb-h3 tips1" style="margin-bottom:5px;">${activity.subhead}</p>
                <div class="tjb-disney-server">
                    <div class="tjb-tiltle clearfix">
                        <a href="将来链接到周边户外分类页面">
                            <span class="fleft stit">周边&amp;户外</span>
                            <p class="fleft pf">
                                这个活动有2人感兴趣
                            </p>
                        </a>
                    </div>
                    <div class="tjb-tips-con clearfix down">
                        <ul class="clearfix tjb-tips-more" id="tjb-tips-more">
                            <li>认证商户</li>
                            <li>天天客服</li>
                        </ul>
                    </div>
                </div>
                <div class="tjb-ul-list checkDate">
                    <i class="tjb-icon tjb-icon-date"></i>
                    <ul>
                        <li class="tips1">
                        <!-- 	  -->
                        <c:forEach items="${timeManagements}" var="timeManagement" >
                        	<fmt:formatDate value="${timeManagement.startDate }" pattern="yyyy-MM-dd"/>-
                        	<fmt:formatDate value="${timeManagement.endDate }" pattern="yyyy-MM-dd"/>
                        	 ${timeManagement.startTime}-${timeManagement.endTime}、
                        </c:forEach>
                        </li>
                    </ul>
                </div>
                <div class="tjb-ul-list checkMap">
                    <i class="tjb-icon tjb-icon-poi"></i>
                    <ul>
                        <li class="tips1">${activity.address}
                            <span class="tips3">(路线提示)</span>
                        </li>
                    </ul>
                </div>
                <div class="tjb-ul-list checkPrice">
                    <i class="tjb-icon tjb-icon-price"></i>
                    <ul>
                        <li class="tips1">
                            <span class="fee price-show">${activity.newPrice}</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="tjb-block" style="padding:15px 15px 0;">
                <div class="tjb-profile-wrap" style="border-bottom: none;">
                    <a style="display: block" href="/m/shop/details?id=30526">
                        <div class="tjb-profile large tjb-va-top">
                            <img src=" " class="res" onerror="handleImageError(this)">
                        </div>
                    </a>
                    <div class="tjb-profile-wrap-dib">
                        <p class="tjb-client-name tjb-shop-name" style="margin-bottom:0;">组织者：tjb</p>
                        <p class="tjb-recommend" style="font-size: 13px;"> </p>
                    </div>

                    <div class="tjb-shop-authened">
                        <img src="/weOnes/images/verified-fb4f6376eb.png" width="25">
                    </div>
                </div>
            </div>
            <div class="tjb-act-menu">
                <ul>
                    <li class="active">
                    <a href="javascript:void(0)" style="font-size:14px;">活动详情</a></li>
                    <li><a href="#notices" style="font-size:14px;">注意事项</a></li>
                    <li><a href="#consult" style="font-size:14px;">咨询&评价</a></li>
                </ul>
            </div>
            <div class="tjb-rich-text">
               <!--  “一窥香港的自然风貌，麦理浩径观海天一色”
                <br>麦理浩径
                <br>在香港有一条著名的徒步线路———麦理浩径，这条线路是1979年10月26日启用，全长100公里。以西贡北潭涌为起点，绕过万宜水库，由东向西横贯新界，以屯门为终点，共分为十段。每段的长度由5至16公里不等，大部分约长10公里。它从北潭涌开始，绕过西贡半岛，转向西面，到达九龙群山，再从城门开始往北走到达大帽山，然后往西走，以屯门为终点。
                <br>
                <img data-src="" src="/images/loading_dots-4a649a0b3f.gif"> -->
                ${activity.detailsContent}
            </div>
            <div class="tjb-card">
                <div class="tjb-card-head"><a id="notices" class="tips1">注意事项</a></div>
                <div class="tjb-card-item">
                    <img src="/weOnes/images/wifi_none.png" width="20" class="v-middle"> 无WiFi
                    <span style="display:inline-block;width: 50px;"></span>
                    <img src="/weOnes/images/park_none.png" width="20" class="v-middle"> 无停车位
                    <span style="display:inline-block;width: 50px;"></span>
                </div>    
                <div class="tjb-card-item">
                    ·活动结束前均可购买<br>·参与方式：直接出示周末码消费；<br>·成团要求：10人成团，建议发团前1天和商家确认<br>·年龄限制：无限制
                </div>



                <div class="tjb-card-item">
                    ·该活动参加时间开始前 2 天可退款，保险信息：包含保险<br>·费用包含：1）去程直通大巴车票 2）30万个人意外险 3）药品损耗（领队会带上常备外伤药） 4 ) 领队组织策划费<br>·自备物品：不需要
                </div>
                <div class="tjb-card-item">
                    ·如需预约或有其他疑问请致电小面<a href="tel:400-6543-179" style="text-decoration:none;color: #FBB03B;">400-xxxx-xxx</a>
                </div>
            </div>
        </div>
        <div style="clear:both;margin-bottom:50px;"></div>
        <div class="global_con-total global_con-total-union">
            <a href="/weOnes/wxpay/orderprocess?activityId=${activity.activityId}" >
                <img src="/weOnes/images/icon-jf.png" height="22">快速下单
            </a>
            <a href="tel:" class="tel tel2">
                <img src="/weOnes/images/icon-teln-1.png" height="25">
                <span>欢迎来电</span>
            </a>                    
        </div>
        <div id="shade"></div>
        <div class="small-shells"></div>
        <div class="append">添加关注成功</div>
        <div class="small-shells1"></div>
        <div class="cancel">取消关注成功</div>
    </div>
    <script>
        $(function(){
            $(".tjb-act-menu ul>li").on("click",function(){
                $(".tjb-act-menu ul>li").eq($(this).index()).addClass("active").siblings().removeClass('active'); 
            })
        })
    </script>
    <script>
            function revert(){
            document.referrer === '' ?
                window.location.href = '/weOnes/activity/list' :
            window.history.go(-1);
        }
    </script>
</body>
</html>