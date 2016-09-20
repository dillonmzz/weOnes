<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<legend>时间列表</legend>
<div class="tinyheaderTools form-inline">
	
	<span id="delall-s-table">
		<a href="javascript:void(0);" name="addTimeSchedule" class="btn btn-info btn-sm">新增时间</a>
	</span>
	<script type="text/javascript">
		$("[name='addTimeSchedule']").click(function(){
			$("#tinypagecontent").load("timemgr/toaddTimeSchedule");
		});
	</script>
</div>
<div class="table-responsive">
	<table class="dataTable tinytable table table-hover" id="s-table"
		data-url="" data-url-real="" data-num="52">
		<thead>
			<tr>
				
				<th rel="sortname" class="sorting sorting_desc"><div
						style="white-space: nowrap; margin-right: 5px;">时间描述</div></th>
				
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">期次总数</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">开始日期</div></th>
				<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">结束日期</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">开始时间</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">结束时间</div></th>
				<th>
				<div style="white-space: nowrap; ">操作</div></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="timeSchedule" varStatus="vs">
			<tr>
				<td>${timeSchedule.timeDesc}</td>
				<td>${timeSchedule.section}</td>
				<td><fmt:formatDate value="${timeSchedule.startDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${timeSchedule.endDate}" pattern="yyyy-MM-dd"/></td>
				<td>${timeSchedule.startTime}</td>
				<td>${timeSchedule.endTime}</td>
				<td class="sorting_1" id="${timeSchedule.id}"> 
					<a title="编辑"  class="Item-Editor" href="javascript:void(0);" 
					   data-url="activity/updateActivityById" data-id="${timeSchedule.id}">
						<i class="fa fa-edit"></i>
					</a>
								
					<a title="删除" class="Item-Remove" href="javascript:void(0);" 
						data-url="activity/updateActivityStateById" data-id="${timeSchedule.id}" data-state="9">
					<i class="fa fa-remove"></i>
					</a>
				</td>
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="tinytableNav">
		<div class="pagination-sm r" id="s-table-nav">
			<div>
				<ul class="pagination">
					<li class="first disabled" data-page="1"><a
						href="javascript:void(0);">首页</a></li>
					<li class="prev disabled" data-page="1"><a
						href="javascript:void(0);">上一页</a></li>
					<li class="page active" data-page="1"><a
						href="javascript:void(0);">1</a></li>
					<li class="page " data-page="2"><a href="javascript:void(0);">2</a></li>
					<li class="page" data-page="3"><a href="javascript:void(0);">3</a></li>
					<li class="next" data-page="2"><a href="javascript:void(0);">下一页</a></li>
					<li class="last" data-page="6"><a href="javascript:void(0);">末页</a></li>
				</ul>
			</div>
		</div>
		显示 <span id="s-table-currentItemInfo">1-10</span> 总共有${list.size()}<span id="s-table-allnum"></span>条目
	</div>
</div>
<!-- 
<script type="text/javascript">
//加载页面后ajax请求数据,通过jquery的方法对dom元素赋值
$(function(){
	
	$.ajax({
		//url:"<%=request.getContextPath()%>/timemgr/adminList",
		//data:{activityId:activityId},
		type:"get",
		success:function(data){
			var timeScheduleList = data.data;
			var timeSchedule;
			
			for(var i = 0;i<timeScheduleList.length;i++){
				timeSchedule = timeScheduleList[i];	
				
				var trObj = $("li",$("#timeSchedules")).clone();				 
				$(".timeType", trObj).text(timeSchedule.timeType);
				$(".effdt", trObj).text(timeSchedule.effdt);
				$(".section", trObj).text(timeSchedule.section);
				$(".startDate", trObj).text(timeSchedule.startDate);
				$(".endDate", trObj).text(timeSchedule.endDate);
				$(".operation", trObj).text(" <a  class='Item-Editor' href='javascript:void(0);' data-url='activity/updateActivityById' data-id='${activity.activityId}'><i class='fa fa-edit'></i></a>");
				trObj.appendTo("#trs");	
			}// ~ end for			
		

		},
		error:function(){

		}
	});//ajax end
	
});	
</script>
 -->