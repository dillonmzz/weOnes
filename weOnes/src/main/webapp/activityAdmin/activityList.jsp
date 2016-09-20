<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<legend>活动列表</legend>
<div class="tinyheaderTools form-inline">
	<span class="r"> 每页显示条数 <select class="form-control" id="s-tablepageSize">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option value="100000000">全部</option>
	</select>
	</span> 
	<span id="delall-s-table">
		<a href="javascript:;" class="btn btn-danger btn-sm">批量删除</a>
	</span>
	<span id="delall-s-table">
		<a href="javascript:void(0);" name="addActivity" class="btn btn-info btn-sm">新增活动</a>
	</span>
	<script type="text/javascript">
		$("[name='addActivity']").click(function(){
			$("#tinypagecontent").load("activityAdmin/addActivity.html?time="+Date.parse(new Date()));
		});
	</script>
</div>
<div class="table-responsive">
	<table class="dataTable tinytable table table-hover" id="s-table"
		data-url="" data-url-real="" data-num="52">
		<thead>
			<tr>
				<th><div style="white-space: nowrap; margin-right: 5px;">
						<input type="checkbox" name="allck" value="">
					</div></th>
				<th rel="sortname" class="sorting sorting_desc"><div
						style="white-space: nowrap; margin-right: 5px;">活动标题</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">活动分类</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">创建人</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">活动价格</div></th>
				<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">创建日期</div></th>
				<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">活动状态</div></th>
				<th>
				<div style="white-space: nowrap; ">操作</div></th>
			</tr>
		</thead>
		<tbody>
			<input type="hidden" name="records" value="52">
			<c:forEach items="${list}" var="activity" varStatus="vs">
			<tr>
				<td><input type="checkbox"  value="${activity.activityId}" name="idCheck"></td>
				<td>${activity.title}</td>
				<td>${activity.type}</td>
				<td>admin管理员</td>
				<td>￥${activity.newPrice}</td>
				<td><fmt:formatDate value="${activity.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td class="sorting_1" id="state${activity.activityId}"> 
					<c:choose>
							<c:when test="${activity.state=='0'}">
								<span>未发布</span>
							</c:when>
							<c:when test="${activity.state=='1'}">
								<span>已发布</span>
							</c:when>
							<c:otherwise>
								<span>无效</span>
							</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
							<c:when test="${activity.state=='0'}">
								<a title="编辑"  class="Item-State" href="javascript:void(0);" id="Item-State${activity.activityId}"
								   data-url="activity/updateActivityStateById" data-id="${activity.activityId}"
								   data-state="1">
								<i class="fa fa-toggle-off"></i>
								</a>
								<a title="编辑"  class="Item-Editor" href="javascript:void(0);" 
					data-url="activity/updateActivityById" data-id="${activity.activityId}">
						<i class="fa fa-edit"></i>
					</a>
								<a title="删除" class="Item-Remove" href="javascript:void(0);" 
					data-url="activity/updateActivityStateById" data-id="${activity.activityId}" data-state="9">
						<i class="fa fa-remove"></i>
					</a>
							</c:when>
							<c:when test="${activity.state=='1'}">
								<a  class="Item-State" href="javascript:void(0);" id="Item-State${activity.activityId}"
								   data-url="activity/updateActivityStateById" data-id="${activity.activityId}"
								   data-state="0">
								<i class="fa fa-toggle-on"></i>
								</a>
								
								<a title="编辑"  class="Item-Editor" href="javascript:void(0);" 
									data-url="activity/updateActivityById" data-id="${activity.activityId}">
								<i class="fa fa-edit"></i>
								</a>
								
								<a title="删除" class="Item-Remove" href="javascript:void(0);" 
									data-url="activity/updateActivityStateById" data-id="${activity.activityId}" data-state="9">
								<i class="fa fa-remove"></i>
								</a>
							</c:when>
							<c:otherwise>
								<a title="编辑"  class="Item-Editor" href="javascript:void(0);" 
									data-url="activity/updateActivityById" data-id="${activity.activityId}">
								<i class="fa fa-edit"></i>
								</a>
							</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<script type="text/javascript">
	$(function(){
		
		$("#s-table th input:checkbox").on("click" , function(){
			var that = this;
			$(this).closest("table").find("tr > td:first-child input:checkbox").each(function(){
				if (this.disabled){
					return;
				}
				this.checked = that.checked;
			});
		});
		// 删除记录
		$("#s-table").on("click", ".Item-Remove", function () {
			var that = this;
			layer.confirm('确定要删除该记录？', function (index) {
				//alert($(that).attr("data-url"));
				//alert($(that).attr("data-id"));
				
				$.ajax({
					type : "POST",
					url:$(that).attr("data-url"),
					data:{activityId:$(that).attr("data-id"),state:$(that).attr("data-state")},
					dataType:"json",
					success:function(data){
						if(data.errorCode=="0"){
							layer.msg(data.msg,{icon: 1},function(){
                                $("#s-table").trigger("refresh");
                            });
						}else{
							var msg=data.info?data.info:"系统出错...";
							layer.msg(msg,{icon: 2});
						}
					},
					error: function() {
						layer.msg("系统出错了",{icon: 2});
					}
				});

			});
	  })
	  
	//发布、取消发布
		$("#s-table").on("click", ".Item-State", function () {
			var that = this;
			layer.confirm('确定发布或取消发布？', function (index) {
				$.ajax({
					type : "POST",
					url:$(that).attr("data-url"),
					data:{activityId:$(that).attr("data-id"),state:$(that).attr("data-state")},
					dataType:"json",
					success:function(data){
						if(data.errorCode=="0"){
							if($(that).attr("data-state")=='1'){
								$("#Item-State${activity.activityId}").children("i").removeClass().addClass("fa fa-toggle-on");
								$("#state${activity.activityId}").children("span").html("已发布");
								console.log($("#Item-State${activity.activityId}").children("i").attr("class"));
								console.log($("#state${activity.activityId}").children("span").text());
							}else{
								$("#Item-State${activity.activityId}").children("i").removeClass().addClass("fa fa-toggle-off");
								$("#state${activity.activityId}").children("span").html("未发布");
								console.log($("#Item-State${activity.activityId}").children("i").attr("class"));
								console.log($("#state${activity.activityId}").children("span").text());
							}
							
							layer.msg(data.msg,{icon: 1},function(){
                                $("#s-table").trigger("refresh");
                            });
						}else{
							var msg=data.info?data.info:"系统出错...";
							layer.msg(msg,{icon: 2});
						}
					},
					error: function() {
						layer.msg("系统出错了",{icon: 2});
					}
				});

			});
	  })
 //发布、取消发布结束
 
 /*
 //修改活动 开始
		$("#s-table").on("click", ".Item-Editor", function () {
			var that = this;
			//layer.confirm('确定发布或取消发布？', function (index) {
				$.ajax({
					type : "POST",
					url:$(that).attr("data-url"),
					data:{activityId:$(that).attr("data-id")},
					dataType:"json",
					success:function(data){
						if(data.errorCode=="0"){
							layer.msg(data.msg,{icon: 1},function(){
                                $("#s-table").trigger("refresh");
                            });
						}else{
							var msg=data.info?data.info:"系统出错...";
							layer.msg(msg,{icon: 2});
						}
					},
					error: function() {
						layer.msg("系统出错了",{icon: 2});
					}
				});

			//});
	  })*/
 //修改活动结束
 $("#s-table").on("click",".Item-Editor",function(){
	 var that = this;
	 layer.confirm('确定修改吗?',function(index){
	 $.ajax({
			//url:$(that).attr("data-url"),
			url:"<%=request.getContextPath()%>/activityAdmin/activityEdit.jsp?activityId="+$(that).attr("data-id"),
			data:{activityId:$(that).attr("data-id")},
			dataType:"html",
			type:"get",
			cache:true,
			success:function(data){
				//alert("请求正常");
				layer.msg("",{icon: 1});
				$("#tinypagecontent").html(data);
			},
			error:function(){
				layer.msg("错误",{icon: 1});
				alert("请求异常");
			}
		});
	});
 });
 
 
 
 
 
 
	
	});
</script>
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
					<li class="page" data-page="4"><a href="javascript:void(0);">4</a></li>
					<li class="page" data-page="5"><a href="javascript:void(0);">5</a></li>
					<li class="page" data-page="6"><a href="javascript:void(0);">6</a></li>
					<li class="next" data-page="2"><a href="javascript:void(0);">下一页</a></li>
					<li class="last" data-page="6"><a href="javascript:void(0);">末页</a></li>
				</ul>
			</div>
		</div>
		显示 <span id="s-table-currentItemInfo">1-10</span> 总共有 <span
			id="s-table-allnum">${list.size()}</span> 条目 <span id="s-table-msgInfo">附加信息</span>
	</div>
</div>
