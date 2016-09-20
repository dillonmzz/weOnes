<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/resources/app/js/jquery-1.8.3.js"></script>
<div class="user-boot">
	<ul>
		<li class="on"><a href="<%=request.getContextPath()%>/collect/list/1" class="boot_index"><i></i>首页</a></li>
		<li><a href="#" class="boot_cn"><i></i>搜索</a></li>
		<li><a href="<%=request.getContextPath()%>/myController/v2" class="boot_my"><i></i>我的</a></li>
	</ul>
</div>

<script>
$(".user-boot ul li").on("click",function(){
	
	$(".user-boot ul li").eq($(this).index()).addClass("on").siblings().removeClass('on'); 
	// 获取到li这个对象
	//var that = this;
	//$(that).addClass("on");


})
</script>