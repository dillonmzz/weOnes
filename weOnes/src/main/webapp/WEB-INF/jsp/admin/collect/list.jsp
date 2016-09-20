<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/tag.jsp" %>
<div class="table-responsive">
	<table class="dataTable tinytable table table-hover" id="s-table" data-num="52">
		<thead>
			<tr>
				<th rel="sortname" class="sorting sorting_desc"><div
						style="white-space: nowrap; margin-right: 5px;">举办日期</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">价格</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">主题</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">参与达人</div></th>
			<!-- 	<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">场地</div></th> -->
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">创建人</div></th>
				<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">人数限制</div></th>
				<th>
				<div style="white-space: nowrap; ">操作</div></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item" varStatus="vs">
			<tr class="${item.id}"><!-- --><%-- 
				<td><input type="checkbox"  value="${item.id}" name="idCheck"></td>--%>
				<td>${item.timeId}</td>
				<td>${item.price}</td>
				<td>${item.subjectId}</td>
				<td>${item.talentId}</td>
				<!-- <td>${item.placeId}</td> -->
				<td>${item.createUser}</td>
				<td>${item.minPeople}-${item.maxPeople}</td>
				<td class="sorting_1">
					<a title="同意" class="item_update"  href="javascript:void(0);" 
					   data-url="collect/updateStateById?state=2" data-id="${item.id}">
						<i class="fa fa-check"></i>
					</a>|
					<a title="拒绝" class="item_update" href="javascript:void(0);"
						data-url="collect/updateStateById?state=1" data-id="${item.id}">
					<i class="fa fa-close"></i>
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
<!--  -->
<script type="text/javascript">
//同意或拒绝
$("#s-table").on("click", ".item_update", function () {
			var that = this;
			var id = $(that).attr("data-id");
			$.ajax({
				type : "POST",
				url:$(that).attr("data-url"),
				data:{id:id},
				dataType:"json",
				success:function(data){
					if(data.errorCode=="0"){
						$("tr."+id).remove();
						layer.msg(data.msg,{icon: 1});
						
					}else{
						layer.msg("系统出错了",{icon: 2});
					}
				},
				error: function() {
					layer.msg("系统出错了",{icon: 2});
				}
			});

	  });
</script>
