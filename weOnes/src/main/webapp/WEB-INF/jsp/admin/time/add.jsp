<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<style type="text/css">
.required {
	font-size: 14px;
	color: #ff7760;
	font-weight: 700;
}
</style>
<legend>添加时间表</legend>
<form name="thisform" id="timeSchedule" method="post"
	class="form-horizontal">

	<!-- 时间期次,默认为1,若添加多期次,1++ -->
	<input type="hidden" name="section" id="sectionvalue" value="1">
	<div class="row form-group" id="timeTypeDiv">
		<label class="col-sm-2 control-label  pl-0 pr-5"><span
			class="required">*</span>日期分类：</label>
		<div class="col-sm-6 pl-0">
			<select class="form-control" name="timeType" id="timeTypeSelect">
				<option value="kong" selected="selected" disabled>请选择一个日期类型</option>
				<option value="sin">单一日期</option>
				<option value="con">连续日期</option>
			</select>
		</div>
	</div>
	
	

	<div class="form-group normal-form">
		<label class="col-sm-2 control-label  pl-0 pr-5"></label>
		<div class="col-sm-6 pl-0">
			<a class="btn btn-info btn-sm" id="submitId">保存</a>
			<button class="btn btn-info btn-sm" id="submitId">保存</button>
		</div>
	</div>
	
	<script type="text/javascript">
		//根据日期分类 动态添加
		$('#timeTypeSelect').change(function() {
			 $("#sectionvalue").val(1);
			var optionVal = $(this).children('option:selected').val();//获取id为timeTypeSelect下拉框的值
			$(".sectionBlock,.addSection,.removeSection").remove();//删除所有元素的内容
			var appendHtml = "<div class='sectionBlock'><div class='form-group nomal-form' id='dataId'> "+
			 "<label class='col-sm-2 control-label  pl-0 pr-5'><span "+
			 "class='required'>*</span>日期选择：</label> "+
			 "<div class='col-xs-5  input-group form-inline' id='sid535267215-rap'> "+
			 "	<input type='text' name='startDate' placeholder='开始日期' "+
			 "	class='form-control date-select'> "+
			 "	<span class='input-group-addon'><i class='fa fa-calendar'></i></span> ";
			 if (optionVal == "con") {//如果是连续日期,则显示如下内容
				 appendHtml = appendHtml+ " <input type='text' name='endDate' placeholder='结束日期' "+
				 " class='form-control date-select'> "+
				 " <span class='input-group-addon'><i class='fa fa-calendar'></i></span> ";
			 }
			 appendHtml = appendHtml+" </div></div> "+
			 " <div class='form-group nomal-form' id='timeId'> "+
			 " <label class='col-sm-2 control-label  pl-0 pr-5'><span "+
			 " class='required'>*</span>时间选择：</label> "+
			 " <div class='input-group form-inline' id='sid535267215-rap'> "+
			 " <span data-toggle='clockpicker' "+
			 " class='clockpicker input-group time-select' data-align='left' "+
			 " data-default='now' data-placement='bottom' data-autoclose='true' "+
			 " id='cp933380479' data-donetext='完成'> <input type='text' "+
			 " name='startTime' placeholder='开始时间' class='form-control ' "+
			 " style='width: 200px'> <span class='input-group-addon'> "+
			 " <span class='fa fa-clock-o'></span> "+
			 " </span></span> 到 <span data-toggle='clockpicker' "+
			 " class='clockpicker input-group time-select' data-align='left' "+
			 " data-default='now' data-placement='bottom' data-autoclose='true' "+
			 " id='cp933380479' data-donetext='完成'> <input type='text' "+
			 " name='endTime' placeholder='结束时间' class='form-control ' "+
			 " style='width: 200px'> <span class='input-group-addon'> "+
			 " 	<span class='fa fa-clock-o'></span> "+
			 " </span></span></div></div> </div>"+
			 " <a class='btn btn-info btn-sm addSection'>添加时间</a><a class='btn btn-info btn-sm removeSection'>删除时间</a>";
			$("#timeTypeDiv").after(appendHtml);
			
		})
		/*添加时间期次*/
		$(".form-horizontal").on("click",".addSection",function(){
			$(".sectionBlock:last").after($(".sectionBlock:last").clone());
			var sectionObj = $("#sectionvalue");
			sectionObj.val(Number(sectionObj.val())+1);//input框value+1
		});
		
		/*删除时间期次*/
		$(".form-horizontal").on("click",".removeSection",function(){
			var sectionObj = $("#sectionvalue");
			if(sectionObj.val()==1){//如果当前value=1则不允许继续操作
				return false;
			}
			sectionObj.val(Number(sectionObj.val())-1);//input框value-1
			$(".sectionBlock:last").remove();
		});
		
		
		$(function() {
			var dateFormat = 'YYYY-MM-DD';
			dateFormat = dateFormat ? dateFormat : 'yyyy-MM-dd';// HH:mm:ss
			dateFormat = dateFormat.replace(/Y/g, "y").replace("ii", "mm")
					.replace(/D/g, "d");
			$(document).on("click",".date-select",function(){
				
			//});
			//$(".date-select").on("click", function() {
				WdatePicker({
					startDate : '%y-%M-01 00:00:00',
					dateFmt : dateFormat,
					onpicked : function() {
						$("input[name='startDate']").trigger('blur change')
					}
				})
			});
		});


		//form表单提交
		$("#submitId").click(function() {
			var params = $("#timeSchedule").serialize();
			console.log("params:" + params);
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "timemgr/save",
				data : params,
				success : function(data) {
					if (data.errorCode == "0") {
						layer.msg(data.msg, {icon : 1});
						$("#submitId").attr('disabled', true);
						$("#submitId").unbind();
					} else {
						layer.msg(data.msg, {icon : 2});
					}
				},
				error : function() {
					layer.msg("系统出错了", {
						icon : 2
					});
				}
			});// ajax end
		});
	</script>
</form>
