<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<legend>${productType.name}列表</legend>
<div class="tinyheaderTools form-inline">
	
	<span id="delall-s-table">
		<a href="javascript:void(0);" name="addProduct" class="btn btn-info btn-sm">新增${productType.name}</a>
	</span>
	<script type="text/javascript">
		$("[name='addProduct']").click(function(){
			$("#tinypagecontent").load("product/toadd?type=${productType.productType}");
		});
	</script>
</div>
<div class="table-responsive">
	<table class="dataTable tinytable table table-hover" id="s-table"
		data-url="" data-url-real="" data-num="52">
		<thead>
			<tr>
				
				<th rel="sortname" class="sorting sorting_desc"><div
						style="white-space: nowrap; margin-right: 5px;">达人类型</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">达人姓名</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">达人特色</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">达人头像</div></th>
				<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">达人城市</div></th>
				<th>
				<div style="white-space: nowrap; ">操作</div></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item" varStatus="vs">
			<tr><!-- --><%-- --%>
				<td>${item.subType}</td>
				<td>${item.name}</td>
				<td>${item.title}</td>
				<td><img alt="" src="${item.productPhoto}" height="40" width="80"></td>
				<td>${item.city}</td>
				<td class="sorting_1" id="${timeSchedule.id}"> 
					<a title="编辑"  class="Item-Editor" href="javascript:void(0);" 
					   data-url="activity/updateActivityById" data-id="${item.id}">
						<i class="fa fa-edit"></i>
					</a>
								
					<a title="删除" class="Item-Remove" href="javascript:void(0);" 
						data-url="activity/updateActivityStateById" data-id="${item.id}" data-state="9">
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