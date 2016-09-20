<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/tag.jsp" %>
<script src="/weOnes/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/cityResource/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/admin/js/citychoose.js"></script>
<script src="<%=request.getContextPath()%>/resources/admin/js/citymini.js"></script>

<style type="text/css">
.required {
	font-size: 14px;
	color: #ff7760;
	font-weight: 700;
}
</style>
<form name="thisform" id="from557982744" method="post"
	class="form-horizontal">

	<div class="row form-group" id="timeTypeDiv">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>举办日期：</label>
		<div class="col-sm-6 pl-0">
			<select class="form-control" id="timeType">
				<option value="kong" selected="selected">请选择</option>
				<option value="sin">单一日期</option>
				<option value="con">连续日期</option>
			</select>
		</div>
	</div>


	<!-- 关联主题 开始 -->
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">
		<span class="required">*</span>关联主题：
		</label>
		<div class="col-xs-2 pl-0" id="isAddSubject">
			<div class="radio-custom radio-class java.util.HashMap pt-5">
				<input type="radio" name="addSubject" value="yes" id="rad1971166885">
				<label for="rad1971166885" class="cursor-hand">是</label>
			</div>
			<div class="radio-custom radio-info pt-5">
				<input type="radio" name="addSubject" value="no" id="rad183398410"
					checked="checked"> <label for="rad183398410"
					class="cursor-hand">否</label>
			</div>
		</div>
		
	</div><!-- 关联主题 结束 -->
	
	<!-- 关联达人 开始 -->
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">
		<span class="required">*</span>关联达人：
		</label>
		<div class="col-xs-2 pl-0" id="isAddTalent">
			<div class="radio-custom radio-class java.util.HashMap pt-5">
				<input type="radio" name="addTalent" value="yes" id="rad19711668851">
				<label for="rad19711668851" class="cursor-hand">是</label>
			</div>
			<div class="radio-custom radio-info pt-5">
				<input type="radio" name="addTalent" value="no" id="rad1833984101"
					checked="checked"> <label for="rad1833984101"
					class="cursor-hand">否</label>
			</div>
		</div>
	</div><!-- 关联达人 结束 -->
	
	<!-- 关联场地 开始 -->
	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5">
		<span class="required">*</span>关联场地：
		</label>
		<div class="col-xs-2 pl-0" id="isAddPlace">
			<div class="radio-custom radio-class java.util.HashMap pt-5">
				<input type="radio" name="addPlace" value="yes" id="rad19711668852">
				<label for="rad19711668852" class="cursor-hand">是</label>
			</div>
			<div class="radio-custom radio-info pt-5">
				<input type="radio" name="addPlace" value="no" id="rad1833984102"
					checked="checked"> <label for="rad1833984102"
					class="cursor-hand">否</label>
			</div>
		</div>
	</div><!-- 关联场地 结束 -->


	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>举办地址：</label>
			<div class="col-xs-12 col-sm-6 pl-0">
			<div class="form-inline selectList">
				<select class="form-control province" id="province">
				</select>
				<select  class="form-control city" id="city">
				</select>
				<select  class="form-control area" id="area">
				</select>
			</div>
		</div>
	</div>
	
	<div class="row form-group">
		 <label class="col-sm-2 control-label  pl-0 pr-5">
		<span class="required">*</span>详细地址：</label> 
		<div class="col-xs-6 pl-0">
			<input type="text" class="form-control" placeholder="详细地址" name="address">
		</div>
	</div>

	<!-- 人数限制 开始 -->
	<div class="row form-group">
		<label class="col-sm-2 control-label  pl-0 pr-5">
		<span class="required">*</span>人数限制：
		</label>
		<div class="col-xs-3  pl-0">
			<input type="text" class="form-control" placeholder="最少人数"
				name="minPeople">
		</div>
		<div class="col-xs-3">
			<input type="text" class="form-control" placeholder="最多人数"
				name="maxPeople">
		</div>
	</div><!-- 人数限制 结束 -->


	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"></label>
		<div class="col-sm-6 pl-0">
			<a class="btn btn-info" id="submitId">保存</a>
			<button class="btn btn-success" id="goBackId">返回</button>
		</div>
		<div class="col-sm-3 valid-msg"></div>
	</div>

</form>

<script type="text/javascript">
	//选择日期类型后,根据日期类型异步请求查询出所有的时间表
	$("#timeType").change(function(){
		var optionVal = $(this).children('option:selected').val();
		if(optionVal=="kong"){$("#timeScheduleId").remove();return;}
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"collect/findTimeScheduleByTimeType",
		    data:{timeType:optionVal},
		    success:function(data){
		    	 if (data.errorCode == "0") {
		    		 $("#timeScheduleId").remove();
		    		 var appendHtml = "<div class='form-group normal-form' id='timeScheduleId'> "+
			    		"<label class='col-sm-2 control-label  pl-0 pr-5'><span "+
			    		"class='required'>*</span>选择日期：</label> "+
			    		"<div class='col-xs-4  pl-0' id='timeSelectDiv'> "+
			    		"<select class='form-control' id='timeSelect' name='timeId'>"+
			    		"<option value='kong' selected='selected'>请选择时间</option>";
			    	
		    		 var timeScheduleList = data.data;
		 			 var timeSchedule;
		 			 for(var i = 0;i<timeScheduleList.length;i++){
		 				timeSchedule = timeScheduleList[i];
		 				var timeScheduleId = timeSchedule.id;
		 				var timeDesc = timeSchedule.timeDesc;
		 				var startDate = new Date(timeSchedule.startDate);
		 				var endDate = new Date(timeSchedule.endDate);
		 				var startTime = timeSchedule.startTime;
		 				var endTime = timeSchedule.endTime;
		 				//startDate.toLocaleString()
		 				var startDateStr = startDate.getFullYear()+'/'+(startDate.getMonth()+1)+'/'+startDate.getDate();
		 				var endDateStr = endDate.getFullYear()+'/'+(endDate.getMonth()+1)+'/'+endDate.getDate();
		 				//appendHtml = appendHtml+ "<option value='"+timeScheduleId+"'>"+startDateStr+"-"+endDateStr+" "+startTime+"-"+endTime+"</option>";
		 				appendHtml = appendHtml+ "<option value='"+timeScheduleId+"'>"+timeDesc+"</option>";
		 			 }// ~ end for
		 			appendHtml = appendHtml+"</select></div><div class='col-xs-2 pl-0'><input type='text' name='price' class='form-control' placeholder='请输入价格'></div></div>";
		 			$("#timeTypeDiv").after(appendHtml);
		            } else {
		            	 layer.msg(data.msg,{icon: 2});
		            }
			},
			error: function() {
				layer.msg("系统出错了",{icon: 2});
			}
		});// ajax end
	});
	
	//主题关联
	$("input:radio[name='addSubject']").change(function (){
		var subjectRadioVal = $(this).val();
		if(subjectRadioVal=="no"){$("#subjectDiv").remove();return;}
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"collect/findSubjectByType",
		    data:{productType:'subject'},
		    success:function(data){
		    	 if (data.errorCode == "0") {
		    		 //$("#timeScheduleId").remove();
		    		 var appendHtml = "<div class='col-xs-4  pl-0' id='subjectDiv'> "+
		    			" <select class='form-control' id='subjectSelect' name='subjectId'> "+
		    		 " <option value='kong' selected='selected' disabled=''>请选择主题</option> ";
		    		 
		    		 var productList = data.data;
		 			 var product;
		 			 for(var i = 0;i<productList.length;i++){
		 				product = productList[i];
		 				var productId = product.id;
		 				var name = product.name;
		 				appendHtml = appendHtml+ "<option value='"+productId+"'>"+name+"</option>"; 
		 			 }// ~ end for
		 			appendHtml = appendHtml+"</select></div>";
		 			$("#isAddSubject").after(appendHtml);
		            } else {
		            	 layer.msg(data.msg,{icon: 2});
		            }
			},
			error: function() {
				layer.msg("系统出错了",{icon: 2});
			}
		});// ajax end
	});
	
	//达人 关联
	$("input:radio[name='addTalent']").change(function (){
		var talentRadioVal = $(this).val();
		if(talentRadioVal=="no"){$("#talentDiv").remove();return;}
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"collect/findSubjectByType",
		    data:{productType:'talent'},
		    success:function(data){
		    	 if (data.errorCode == "0") {
		    		 //$("#timeScheduleId").remove();
		    		 var appendHtml = "<div class='col-xs-4  pl-0' id='talentDiv'> "+
		    			" <select class='form-control' id='subjectSelect' name='talentId'> "+
		    		 " <option value='kong' selected='selected' disabled=''>请为该主题选择合适的达人</option> ";
		    		 
		    		 var productList = data.data;
		 			 var product;
		 			 for(var i = 0;i<productList.length;i++){
		 				product = productList[i];
		 				var talentId = product.id;
		 				var name = product.name;
		 				var title = product.title;
		 				appendHtml = appendHtml+ "<option value='"+talentId+"'>"+name+":"+title+"</option>"; 
		 			 }// ~ end for
		 			appendHtml = appendHtml+"</select></div>";
		 			$("#isAddTalent").after(appendHtml);
		            } else {
		            	 layer.msg(data.msg,{icon: 2});
		            }
			},
			error: function() {
				layer.msg("系统出错了",{icon: 2});
			}
		});// ajax end
	});
	
	//场地 关联
	$("input:radio[name='addPlace']").change(function (){
		var placeRadioVal = $(this).val();
		if(placeRadioVal=="no"){$("#placeDiv").remove();return;}
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"collect/findSubjectByType",
		    data:{productType:'place'},
		    success:function(data){
		    	 if (data.errorCode == "0") {
		    		 //$("#timeScheduleId").remove();
		    		 var appendHtml = "<div class='col-xs-4  pl-0' id='placeDiv'> "+
		    			" <select class='form-control' id='subjectSelect' name='placeId'> "+
		    		 " <option value='kong' selected='selected' disabled=''>请为该主题选择合适的场地</option> ";
		    		 
		    		 var productList = data.data;
		 			 var product;
		 			 for(var i = 0;i<productList.length;i++){
		 				product = productList[i];
		 				var placeId = product.id;
		 				var name = product.name;
		 				appendHtml = appendHtml+ "<option value='"+placeId+"'>"+name+"</option>"; 
		 			 }// ~ end for
		 			appendHtml = appendHtml+"</select></div>";
		 			$("#isAddPlace").after(appendHtml);
		            } else {
		            	 layer.msg(data.msg,{icon: 2});
		            }
			},
			error: function() {
				layer.msg("系统出错了",{icon: 2});
			}
		});// ajax end
	});

	
	$("#submitId").click(function() {
		var params = $("#from557982744").serialize();
		var cho_Province = $(".province").find("option:selected").text();
		var cho_City = $(".city").find("option:selected").text();
		var cho_Area = $(".area").find("option:selected").text();
		
		params = params+"&cho_Province="+cho_Province+"&cho_City="+cho_City+"&cho_Area="+cho_Area;
		console.log("params:" + params);
		$.ajax({
			type:"POST",
			dataType : "json",
			url:"collect/save",
		    data:params,
		    success:function(data){
		    	 if (data.errorCode == "0") {
		    		 layer.msg(data.msg,{icon: 1});
		    		 $("#submitId").attr('disabled',true);
		    		 $("#submitId").unbind();
		            } else {
		            	 layer.msg(data.msg,{icon: 2});
		            }
			},
			error: function() {
				layer.msg("系统出错了",{icon: 2});
			}
		});// ajax end
	});
</script>
