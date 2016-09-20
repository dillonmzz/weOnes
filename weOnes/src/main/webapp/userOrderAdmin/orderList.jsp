<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="s-tableform" action="" method="get" class="form form-inline">
	<div class="well well-sm">
		用户名:<input type="text" name="username" value=""
			class="form-control input-sm" size="10"> 订单号:<input
			type="text" name="orderId" value="" class="form-control input-sm"
			size="10">消费城市:<input type="text" name="orderId" value=""
			class="form-control input-sm" size="10">开始
		<div class="input-group form-inline" id="sid535267215-rap">
			<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
			<input type="text" name="date1" id="sid535267215"
				class="form-control" value="" nullmsg="日期不能为空" datatype="*"
				dateformat="YYYY-MM-DD HH:mm:ss" style="width: 200px"><span
				style="padding: 6px; float: left">结束</span> <input type="text"
				name="date2" id="sid535267215-2" class="form-control" value=""
				nullmsg="日期不能为空" datatype="*" dateformat="YYYY-MM-DD HH:mm:ss"
				style="width: 200px">
		</div>
		<input type="submit" class="btn btn-primary btn-sm" value="搜索">
		<input type="reset" class="btn btn-primary btn-sm" value="重置">
		<script type="text/javascript">
			$(function() {
				var dateFormat = 'YYYY-MM-DD HH:mm:ss';
				dateFormat = dateFormat ? dateFormat : 'YYYY-MM-DD';
				showTime = false;
				var s = dateFormat.toLowerCase();
				if (s.indexOf("h") > -1) {
					showTime = true;
				}
				var datestr = "" ? "" : moment().format(dateFormat);
				var datestr2 = "" ? "" : moment().format(dateFormat);
				$("#sid535267215-rap").daterangepicker({
					timePicker : showTime,
					timePickerIncrement : 1,
					timePicker12Hour : false,
					timePickerSeconds : true,
					startDate : datestr,
					endDate : datestr2,
					format : dateFormat
				}).on(
						"apply.daterangepicker",
						function(e, _this) {
							$("#sid535267215").val(
									_this.startDate.format(dateFormat))
									.trigger("blur change").siblings(
											"input:first").val(
											_this.endDate.format(dateFormat))
									.trigger("blur change");
						});
			});
		</script>
	</div>
</form>

<div class="tinyheaderTools form-inline">
	<span class="r"> 每页显示条数 <select class="form-control" name=""
		id="s-tablepageSize">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option value="100000000">全部</option>
	</select>
	</span> <span id="delall-s-table"><a href="javascript:;"
		class="btn btn-danger btn-sm">批量删除</a></span>
	<script type="text/javascript">
		$(function() {
			$("#delall-s-table").on(
					"click",
					function() {
						var checklist = [];
						$("#s-table>tbody>tr>td input[type=checkbox]:checked")
								.each(function(e) {
									checklist.push($(this).val());
								});
						if (checklist.length == 0) {
							layer.msg("请选择记录", {
								icon : 2
							})
							return false;
						}
						layer.confirm("请确认?", function() {
							var url = "test.html";
							//alert(url);
							$.ajax({
								url : url,
								data : {
									ids : checklist.join(","),
									itemId : checklist,
									action : "delall",
									ddd : "eee",
									name : "test"
								},
								dataType : "json",
								success : function(data) {
									if (data.status == "success"
											|| data.status == "y") {
										if (data.url) {
											var url = data.url;
											if (url.indexOf("http://") == -1) {
												url = contextPath + url;
											}
											window.location.href = url;
										} else {
											layer.msg(data.info, {
												icon : 1
											}, function() {
												$("#s-table")
														.trigger("initnav")
											});
										}
									} else {
										layer.msg(data.info, {
											icon : 2
										});
									}
								},
								traditional : true,
								error : function() {
									layer.msg("系统出错，请确认你的请求地址", {
										icon : 2
									});
								}
							})
						});
						//return false;
					})
		});
	</script>
</div>

<div class="table-responsive">
	<table class="dataTable tinytable table table-hover" id="s-table" >
		<thead>
			<tr>
				<th><div style="white-space: nowrap; margin-right: 5px;">
						<input type="checkbox" name="allck" value="">
					</div></th>
				<th rel="sortname" class="sorting sorting_desc"><div
						style="white-space: nowrap; margin-right: 5px;">交易编号</div></th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;"></div>订单编号</th>
				<th rel="sortname"><div
						style="white-space: nowrap; margin-right: 5px;">活动名称</div></th>
				<th rel="sortname" class="sorting"><div
						white-space: nowrap; margin-right: 5px;">手机号码</div></th>
				<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">下单数量</div></th>
				<th rel="sortname" class="sorting"><div
						style="white-space: nowrap; margin-right: 5px;">下单总额</div></th>
				<th><div style="white-space: nowrap; margin-right: 5px;">下单时间</div></th>
				<th><div style="white-space: nowrap; margin-right: 5px;">支付状态</div></th>
			</tr>
		</thead>
		<tbody>
			<input type="hidden" name="records" value="52">
		<c:forEach items="${list}" var="order" varStatus="vs">
			<tr>
				<td><input type="checkbox" value="1" name="idCheck"></td>
				<td>${order.outTradeNo }</td>
				<td>${order.transactionId}</td>
				<td>${order.activity.title}</td>
				<td>${order.userPhone}</td>
				<td>${order.quantity}</td>
				<td>￥${order.totalFee}</td>
				<td><fmt:formatDate value="${order.timeEnd}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><c:choose>
							<c:when test="${order.state==0}">
								<span>待支付</span>
							</c:when>
							<c:when test="${order.state==1}">
								<span>已支付</span>
							</c:when>
							<c:otherwise>
								<span>已处理</span>
							</c:otherwise>
						</c:choose></td>
				</tr>
			
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="tinytableNav">
    <div class="pagination-sm r" id="s-table-nav"><div><ul class="pagination"><li class="first disabled" data-page="1"><a href="javascript:void(0);">首页</a></li><li class="prev disabled" data-page="1"><a href="javascript:void(0);">上一页</a></li><li class="page active" data-page="1"><a href="javascript:void(0);">1</a></li><li class="page" data-page="2"><a href="javascript:void(0);">2</a></li><li class="page" data-page="3"><a href="javascript:void(0);">3</a></li><li class="page" data-page="4"><a href="javascript:void(0);">4</a></li><li class="page" data-page="5"><a href="javascript:void(0);">5</a></li><li class="page" data-page="6"><a href="javascript:void(0);">6</a></li><li class="next" data-page="2"><a href="javascript:void(0);">下一页</a></li><li class="last" data-page="6"><a href="javascript:void(0);">末页</a></li></ul></div></div>
    显示 <span id="s-table-currentItemInfo">1-10</span> 总共有 <span id="s-table-allnum">${list.size()}</span> 条目 <span id="s-table-msgInfo">附加信息</span>
</div>
<script type="text/javascript">
    $(function(){
        $("#s-table").on("initnav refresh init",function(){//currentPage
            var url=$('#s-table').attr("data-url");
            $("#s-tablepageSize").attr("name","");
            if($("#s-tableform").size()>0){
                                var url=url+(url.indexOf("?")>0?"&":"?")+$("#s-tableform").formSerialize();
            }
            $('#s-table').attr("data-url-real",url);
            getInfo(1,1);
        });
        $("#s-table").on("change:url",function(e,url){
            $(this).attr("data-url",url);
        });
        function getInfo(page,initnav){
            var order;
            var ordertype;
            var pageSize=$("#s-tablepageSize").val();
            if(!pageSize) pageSize=10;
            if($("#s-table th.sorting_desc").size()>0){
                order=$("#s-table th.sorting_desc").eq(0).attr("rel");
                ordertype="desc";
            }else if($("#s-table th.sorting_asc").size()>0){
                order=$("#s-table th.sorting_asc").eq(0).attr("rel");
                ordertype="asc";
            }
            $.ajax({
                type: "GET",
                url: $('#s-table').attr("data-url-real"),
                data:{ page: page,pagesize:pageSize,pageSize:pageSize,order:order,ordertype:ordertype,orderType:ordertype,limit:pageSize,start:(page-1)*pageSize,tinytableid:"s-table"},
                dataType: "html",
                cache:false,
                success: function(a) {
                    var $tpl=$("<div></div>").html(a);
                    var msgInfo=$tpl.children("div.tb-msg-box").html();
                    $("#s-table-msgInfo").html(msgInfo);
                    var nums=$tpl.children("input[name=records]").val();
                    $("#s-table>tbody").remove();
                    var firstTagName=$tpl.children(":first").get(0).tagName.toLowerCase();
                    if (firstTagName == "meta" || firstTagName == "html" || firstTagName == "body") {
                        a = "<div style='text-align:center'>系统错误</div>";
                    }
                    if(!nums&&firstTagName!="<tr>"){
                        $('#s-table-nav').parent().hide();
                        $("#s-table").append("<tbody><tr><td colspan='20'>"+a+"</td><tr></tbody>");
                    }else{
                        $('#s-table-nav').parent().show();
                        $("#s-table").append("<tbody>"+a+"</tbody>");
                    }
                    if(initnav!=undefined){
                        $('#s-table').attr("data-num",nums);
                        $("#s-table-allnum").html($('#s-table').attr("data-num"));
                        setPageList(nums,pageSize,page)
                    }
                    var pageMaxNum=page*pageSize;
                    if(page*pageSize>parseInt($('#s-table').attr("data-num"))){
                        pageMaxNum=$('#s-table').attr("data-num");
                    }
                    var pageMinNum=(page-1)*pageSize+1;
                    if(nums==0){
                        pageMinNum=pageMaxNum=0;
                    }
                    $("#s-table-currentItemInfo").html(pageMinNum+"-"+pageMaxNum);
                }
            });
        }
        function setPageList(nums,pageSize,currentPage){
            var showcontent=false;
            $('#s-table-nav').html("<div></div>");
            if(nums==0) return false;
            if(!currentPage) currentPage=1;
            var totalPages=Math.ceil(nums/pageSize);
            $('#s-table-nav div').twbsPagination({
                totalPages: totalPages,
                visiblePages: totalPages>7?7:totalPages,
                startPage:currentPage,
                onPageClick:function(event, page) {
                    if(!showcontent){
                        showcontent=true;
                        return;
                    }
                    getInfo(page);
                },
                hrefVariable:"pagenum",
                version: '1.1'
            });
        }
        $("#s-table").trigger("initnav");
        $("#s-tablepageSize").on("change",function(){
            $("#s-table").trigger("initnav");
        });
        $("#s-table").on("click","th.sorting",function(){
            var hasdesc=false;
            if($(this).hasClass("sorting_desc")){
                hasdesc=true;
            }
            $("#s-table th").removeClass("sorting_desc sorting_asc");
            if(hasdesc){
                $(this).addClass("sorting_asc");
            }else{
                $(this).addClass("sorting_desc");
            }
            $("#s-table").trigger("initnav");
        });
    });
</script>
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
		$("#s-table").on("click", ".Item-Remove", function () {
			var that = this;
			layer.confirm('确定要删除该记录？', function (index) {
				$.ajax({
					url:$(that).attr("data-url"),
					data:{id:$(that).attr("data-id")},
					dataType:"json",
					success:function(data){
						if(data.status=="success"||data.status=="y"){
							layer.msg(data.info,{icon: 1},function(){
                                $("#s-table").trigger("refresh");
                            });
						}else{
							var msg=data.info?data.info:"系统出错";
							layer.msg(msg,{icon: 2});
						}
					},
					error: function() {
						layer.msg("系统出错",{icon: 2});
					}
				});

			});
	  }).on("click",">tbody>tr>td",function(e){
		if($(this).find("a,input,button").size()==0){
			$(this).parent().find("input[type=checkbox]").trigger("click");
		}
	  });
	});
</script>
