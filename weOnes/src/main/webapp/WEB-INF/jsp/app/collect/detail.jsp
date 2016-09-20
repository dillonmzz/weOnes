<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/jsp/common/tag.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html><head>
	<title>小面</title>
	<meta charset="utf-8">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="HandheldFriendly" content="true">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/common.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/detail.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/responsive.tabs.css">
	<style>[ms-controller],[ms-important]{display:none}</style>
	<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
	<script src="<%=request.getContextPath()%>/resources/app/js/responsive.tabs.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	      $('.tabs').respTabs();
	      $(".tjb-rich-text img").removeAttr("style");
	    });
	</script>
	</head>
	<body style="height: auto; overflow-y: auto;">
		<div id="loadAfter" style="display: none;">
			<p><img src=" " alt=""></p>
		</div>
		<div class="detail_mainK" style="opacity: 1;">
			<!--分享图标设置 end-->
			<div class="outSide" id="global_content" style="opacity: 1;">
				<div class="content-body" id="content-body" style="opacity: 1;">
					<div class="index_re_Top index_re_Top_detail">
						<p class="detailScroll">
							<a href="javascript:void(0)" class="re_top_return" onclick="javascript:history.go(-1)" style="">返回</a>
						</p>
					</div>
						<a id="re_top_collect" href="javascript:void(0)" onclick="_like._post('top')" title="收藏">
							<img src="http://cdn.hudongba.com/static_v4/images/detail/dt_like6_red.png">
						</a>
				</div>
						<!--活动海报-->
						<div class="poster_img_outside"><img src="${collect.coverPhoto}"></div>
						<!--活动标题、头像、分享数-->
						<div class="detail_title">
							<h2 class="detail_title_h1" id="dt_title">${collect.name }</h2>
						</div>
						<!-- <div class="detail_user hdMan"> 
							<div class="userPicA">
								<a href="#" ontouchstart=""><img src=" "></a>
							</div>
							<div class="hdman_r">
								<div class="yhName">
									<a href="#" class="subinfo_name" id="subinfo_name" ontouchstart="" style="max-width: 55px;">${collect.id }</a>
									<p class="fbTime">7月12日</p>
								</div>
								<div class="dt_review_item_count">
									<a class="info_share" id="info_share" style="color: rgb(153, 153, 153);">分享${collect.id }+</a><span id="info_hits">阅读 ${collect.id }</span>
								</div>
							</div>
							<input type="hidden" id="postUserId36" value="t06eg">
							<div id="maUrlcanvas" style="display:none;">
								<div id="canvasimage"></div>
							</div>
						</div> -->
						<!--时间、地址、报名数 start-->
						<div class="detail_time_attr_join">
							<div class="detail_time_attr_join_gray">
								<div class="detail_Time">
									<img src="<%=request.getContextPath()%>/resources/app/images/detail_time.png" class="icon_T">
									<div class="detail_Time_n">
										<p>
											${collect.timeDesc }<br>
										</p>
									</div>
								</div>
								<div class="detail_Attr">
									<a href="javascript:void(0);" class="dt_address_item" ontouchstart="">
										<div class="detail_Attr_K">
											<p class="addressP">${collect.city}.${collect.district}</p>
										</div>
									</a>
								</div>
								<div class="detail_Joinnum" id="detail_Joinnum">
									<div class="detail_Joinnum_t"><p>最少<span>${collect.minPeople}</span>人</p></div>
									<div class="detail_Joinnum_b">
										<p>
											限<span>${collect.maxPeople}</span>人报名
										</p>
									</div>
								</div>
							</div>
						</div>
						<!--时间、地址、报名数 end-->
						<!--活动详情 start
						<div id="dt_content" class="dt_content"></div>-->
						<c:choose>
							<%-- 如果主题、达人、场地都有,则显示全部内容  --%>
							<c:when test="${not empty collect.subjectId && not empty collect.talentId && not empty collect.placeId }">
								<div class="tjb-act-menu tjb-act-3 tabs" style="display: block">
									<ul class="tabs-list">
										<li class="active navigation1"><a href="javascript:void(0);"
											style="font-size: 14px;">主题详情</a></li>
										<li class="navigation2"><a href="javascript:void(0);"
											style="font-size: 14px;">达人详情</a></li>
										<li class="navigation3"><a href="javascript:void(0);"
											style="font-size: 14px;">场地详情</a></li>
									</ul>
									<div id="tjb-content" class="tabs-container">
										<div id="dt_content" class="dt_content tab-content tjb-rich-text">${collect.subjectDesc}</div>
										<div id="dt_field" class="dt_content tab-content tjb-rich-text">${collect.talentDesc}</div>
										<div id="dt_doyen" class="dt_content tab-content tjb-rich-text">${collect.placeDesc}</div>
									</div>
								</div>
							</c:when>
							
							<%-- 如果只有主题、达人,则显示主题、达人内容  --%>
							<c:when test="${not empty collect.subjectId && not empty collect.talentId}">
								<div class="tjb-act-menu tjb-act-2 tabs" style="display: block">
									<ul class="tabs-list">
										<li class="active navigation2"><a href="javascript:void(0);"
											class="two-row" style="font-size: 14px;">主题详情</a></li>
										<li class="navigation3"><a href="javascript:void(0);"
											class="two-row" style="font-size: 14px;">达人详情</a></li>
									</ul>
									<div id="tjb-contents" class="tabs-container">
										<div id="dt_contents" class="dt_content tab-content tjb-rich-text">${collect.subjectDesc}</div>
										<div id="dt_fields" class="dt_content tab-content tjb-rich-text">${collect.talentDesc}</div>
									</div>
								</div>
							</c:when>
							<%-- 如果只有达人,场地则显示达人,场地内容  --%>
							<c:when test="${empty collect.subjectId && not empty collect.placeId && not empty collect.talentId}">
								<div class="tjb-act-menu tjb-act-2 tabs" style="display: block">
									<ul class="tabs-list">
										<li class="active navigation2"><a href="javascript:void(0);"
											class="two-row" style="font-size: 14px;">达人详情</a></li>
										<li class="navigation3"><a href="javascript:void(0);"
											class="two-row" style="font-size: 14px;">场地详情</a></li>
									</ul>
									<div id="tjb-contents" class="tabs-container">
										<div id="dt_contents" class="dt_content tab-content tjb-rich-text">${collect.talentDesc}</div>
										<div id="dt_fields" class="dt_content tab-content tjb-rich-text">${collect.placeDesc}</div>
									</div>
								</div>
							</c:when>

							<%-- 如果只有主题,场地则显示主题,场地内容  --%>
							<c:when test="${empty collect.talentId && not empty collect.subjectId && not empty collect.placeId}">
								<div class="tjb-act-menu tjb-act-2 tabs" style="display: block">
									<ul class="tabs-list">
										<li class="active navigation2"><a href="javascript:void(0);"
											class="two-row" style="font-size: 14px;">主题详情</a></li>
										<li class="navigation3"><a href="javascript:void(0);"
											class="two-row" style="font-size: 14px;">场地详情</a></li>
									</ul>
									<div id="tjb-contents" class="tabs-container">
										<div id="dt_contents" class="dt_content tab-content tjb-rich-text">${collect.subjectDesc}</div>
										<div id="dt_fields" class="dt_content tab-content tjb-rich-text">${collect.placeDesc}</div>
									</div>
								</div>
							</c:when>
							
							<%-- 如果只有主题、达人、场地任何一个  --%>
							<c:when test="${not empty collect.subjectId or not empty collect.talentId or not empty collect.placeId}">
								<div class="tjb-act-menu tjb-act-1" style="display: block">
									<ul>
										<li class="active navigation1"><a href="javascript:void(0);"
											class="one-row" style="font-size: 14px;">详情介绍</a></li>
									</ul>
									<div id="dts_contents" class="dt_content tab-content tjb-rich-text">${collect.subjectDesc}${collect.talentDesc}${collect.placeDesc}</div>
								</div>
							</c:when>
			
						</c:choose>
						
						

						<!--关注-->
						<div class="detail_attent">
							<div class="detail_attent_K" onclick="">
								<div class="detail_attent_top">
									<span>主办方</span>
									<a href="tel:15557138322" class="butel">
										<img src=" ">
										<i>联系TA</i>
									</a>
								</div>
								<div class="detail_attent_content">
									<div class="detail_attent_pic">
										<a><img class="buimg" src="<%=request.getContextPath()%>/resources/app/images/business.jpg"></a>
									</div>
									<div class="detail_attent_con_c">
										<div class="detail_attent_Name"><p><a href="<%=request.getContextPath()%>/userinfo/gotobuinfo?userName=${collect.createUser}" class="subinfo_name" style="max-width: 55px;">北京游侠客</a></p></div>
										<div class="detail_attent_number">4场活动</div>
									</div>
									<div class="focus_Cz">
										<a id="attent_Btn" class="wgz" href="javascript:void(0)" ontouchstart="" onclick="_isFocus._add()" style="display: block;">+ 关注</a>
									</div>
								</div>
								<div class="detail_attent_sideR">
									<div class="detail_attent_con"><p class="buintroduction">重要的不是去哪，而是和谁一起去</p></div>
								</div>
							</div>
						</div>
						<div id="detail_telTA" style="display:none;">
							<a ontouchstart="" href="tel:15557138322">15557138322</a>
							<a ontouchstart="" href="javascript:void(0)" onclick="_tc._hide('detail_telTA')">取消</a>
						</div>
						<!--评论-->
						<div class="dt_review" id="dt_review" style="margin-bottom:10px;">
							<div  class="dt_review_top">
								<p id="dt_review_count" class="dt_review_topL">
									<span id="review_total">10</span>条评论
								</p>
								<p class="dt_review_topR" >
									<span>写评论</span>
										<a>
											<img src="<%=request.getContextPath()%>/resources/app/images/dt_review.png" title="评论">
										</a>
								</p>
								<a id="plWei" name="plWei"></a>
							</div>
							
						<!-- 显示评论内容 -->
						<ul style="display: block;" class="dt_review_main"
							id="dt_review_main">
							<li class="comment_after"></li>
							
						</ul><!-- 显示评论内容 结束-->
	
	
					<div class="dt_review_more" id="review_loading" style="display: none;">
								<a class="moreBtn" ontouchstart="">
									<span>展开更多评论</span>
								</a>
							</div>
						</div>

						<!--互动吧图标 start -->
						<!-- <div id="ba_Bj" class="ba_Bj">
							<a href="/" ontouchstart="">
								 <img title="找团建上小面" 
								src="<%=request.getContextPath()%>/resources/app/images/w.jpg" onload="$('#loadAfter').hide()">
							</a> 
						</div>-->
						<!--互动吧图标 end-->
						
					<!--评论开始-->
					<div id="dt_review_box" style="display: none;">
						<div id="dt_review_box_main">
							<div id="dt_review_box_input">
								<input type="text" placeholder="评论:" id="dt_review_form_content"
									class="input" maxlength="200">
							</div>
							<div id="dt_review_box_button">
								<button id="dt_review_form_post" class="button_1_disabled">发送</button>
							</div>
							<div class="clear"></div>
						</div>
					</div><!--评论结束-->
					
		</div>
				</div>
		<!--吸底按钮-->
		<div class="dt_join_bar" id="dt_join_bar" style="opacity: 0.98; bottom: 0px;">
			<div class="dt_join_bar_outside">
				<ul class="dt_join_bar_ul_app">
					<li class="l">
						<div>
				            <p class="bar_tit">
				              <span>￥${collect.price }</span>
				            </p>
				         </div>
					</li>
					<li class="c">
						<div>
							<a id="gotoFavorite" >
								<p class="bar_img">
									<span>
										<c:choose>
											<c:when test="${state==0 }">
												<img class="dt_join_bar_icon" data_state="1" src="<%=request.getContextPath()%>/resources/app/images/love.png">
											</c:when>
											<c:otherwise>
												<img class="dt_join_bar_icon" data_state="0" src="<%=request.getContextPath()%>/resources/app/images/love1.png">
											</c:otherwise>
										</c:choose>
									</span>
								</p>
								<p class="bar_tit">
									<span>收藏活动</span>
								</p>
							</a>
						</div>
					</li>
					<li class="r">
						<div id="dt_join_bar_title_mb">
							<a href="<%=request.getContextPath()%>/collect/tobook/${collect.id}" ontouchstart="" onclick="_payItemBox._show('party')">
								<p class="bar_img">
									<span>
										<img class="dt_join_bar_icon" src="<%=request.getContextPath()%>/resources/app/images/join.png">
									</span>
								</p>
								<p class="bar_tit">
									<span>我要参加</span>
								</p>
							</a>
						</div>
					</li>
				</ul>
			</div>
		</div>

<div id="shade"></div>
<div class="small-shells"></div>
<div class="append">关注成功</div>
<div class="small-shells1"></div>
<div class="cancel">取消关注</div>
<div class="shade" style="position:fixed;top: 0;right: 0;bottom: 0;left: 0;
background:#000;display:block;z-index:1000;opacity: 0.6;display:none;"></div>
	</body>
</html>

<script type="text/javascript">
			$("#gotoFavorite").click(function(){
				var state = $(".dt_join_bar_icon").attr("data_state");
				$.ajax({
					type:"GET",
	    			dataType : "json",
	    			url:"<%=request.getContextPath()%>/myProductCollectFavorite/favoriteOrCancel?productCollectId=${collect.id}&state="+state,
	                success:function(data){
	                	if(data.errorCode == "0"){
	                		if(state=="1"){
	                			$(".dt_join_bar_icon").attr("src","<%=request.getContextPath()%>/resources/app/images/love1.png");
	                			$(".dt_join_bar_icon").attr("data_state","0");
	                			$(".small-shells").show().delay(1500).hide(300);
	                			$(".append").show().delay(1500).hide(300);
	                			$("#shade").show().delay(1500).hide(300);
	        				}else{
	        					$(".dt_join_bar_icon").attr("src","<%=request.getContextPath()%>/resources/app/images/love.png");
	        					$(".dt_join_bar_icon").attr("data_state","1"); 
	        					$(".small-shells1").show().delay(1500).hide(300);
	        					$(".cancel").show().delay(1500).hide(300);
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

<script>
	$(function(){
		$(".dt_review_topR").on("click", function() {
			$(".shade").show();
			$("#dt_review_box").show();
		})
		$(".shade").on("click", function() {
			$("#dt_review_box").hide();
			$(this).hide();
		})
		//写评论并显示当前评论内容
		$("#dt_review_box_button").on("click", function() {
			var content = $("#dt_review_form_content").val();
			var netvalue = content.replace(/\ +/g, "");
			if (netvalue.length== 0) {
				$("#dt_review_form_content").val("");
				$("#dt_review_form_content").attr("placeholder","填写评论");
				return false;
			}
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "<%=request.getContextPath()%>/comment/save/${collect.id}",
				data :{commentText:content},
				success : function(data) {
					$("#dt_review_box").hide();
					$(".shade").hide();
					if(data.errorCode == "0"){
						//如果评论成功,则将本次评论的内容显示在页面中
						var appendHTML = "<li class='dt_review_main commentItem'> "+
							" <div class='dt_review_main_K'> "+
							" <div class='dt_review_icon'> "+
							" <a onclick='' href='javascript:void(0)'><img "+
							" class='default_img' src='"+data.datas[1]+"' alt='头像'> "+
							" </a></div> "+
							" <div class='dt_review_title dt_loadNew'> "+
							" <a class='review_user_Name' data-id='whyh1' "+
							" href='javascript:void(0)'>"+data.datas[0]+"</a> "+
							" </div><p class='dt_review_time'>"+data.datas[2]+"</p> "+
							" <div class='dt_review_body'>"+content+"</div> "+
							" </div></li>";
						$(".comment_after").after(appendHTML);
						//评论条数+1
						var commentObj = $("#review_total");
						var commentCount = Number(commentObj.html());
						commentObj.html(commentCount+1);
					}
				}
			})
		})// end
		
		//查询所有评论
		$("#dt_review_count").on("click",function(){
			alert("查看评论")
			$.ajax({
				type : "GET",
				dataType : "json",
				url : "<%=request.getContextPath()%>/comment/list/${collect.id}",
				success : function(data) {
					if(data.errorCode == "0"){
						//如果数据库返回成功,删除 li 元素class = commentItem 的元素
						$("li").remove(".commentItem");
						//设置评论条数
						$("#review_total").html(data.msg);
						//获取json数据遍历数据并显示
						var list = data.data;
						var item;
						var appendHTML;
						for(var i = 0;i<list.length;i++){
							item = list[i];
							appendHTML = "<li class='dt_review_main commentItem'> "+
							" <div class='dt_review_main_K'> "+
							" <div class='dt_review_icon'> "+
							" <a onclick='' href='javascript:void(0)'><img "+
							" class='default_img' src='"+item.weChatUser.headImgUrl+"' alt='头像'> "+
							" </a></div> "+
							" <div class='dt_review_title dt_loadNew'> "+
							" <a class='review_user_Name' data-id='whyh1' "+
							" href='javascript:void(0)'>"+item.weChatUser.nickName+"</a> "+
							" </div><p class='dt_review_time'>"+item.relativeDateFormat+"</p> "+
							" <div class='dt_review_body'>"+item.commentText+"</div> "+
							" </div></li>";
						$(".comment_after").after(appendHTML);
						}// for end
					}
				}
			})
		});
		
		//加载dom时候获取商家的信息
		$.ajax({
			type:"GET",
			dataType : "json",
			url:"<%=request.getContextPath()%>/userinfo/showbuinfo?userName=${collect.createUser}",
            success:function(data){
            	if(data.errorCode == "0"){
            		var obj = data.datas;
            		$(".subinfo_name").text(obj.nickName);
            		$(".buimg").attr('src',obj.imgurl);
            		$(".buintroduction").html(obj.introduction);
            		$(".butel").attr('href',obj.mobile); 
    			}
            },
            error:function(data, status, e){
            }
		});// ajax end
		
		
		
	});
</script>
