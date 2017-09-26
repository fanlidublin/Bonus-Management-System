<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="../dist/datepicker.min.css">
<script type="text/javascript">
	function checkForm(){
		var financialId = document.getElementById("financialId").value;
		var projectName = document.getElementById("projectName").value;
		var orderPerformance = document.getElementById("orderPerformance").value;
		var requestPerformance = document.getElementById("requestPerformance").value;
		var supportPerformance = document.getElementById("supportPerformance").value;
		var projectStartDate = document.getElementById("projectStartDate").value;
		var projectEndDate = document.getElementById("projectEndDate").value;
		if(financialId==""||projectName==""||orderPerformance==""||requestPerformance==""||supportPerformance==""||projectStartDate==""||projectEndDate==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} 
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
	
</script>





<div>
		<div class="data_list_title">
		<c:choose>
			<c:when test="${appliBonus.projectId!=null}">
				修改项目奖金标准
			</c:when>
			<c:otherwise>
				添加项目奖金标准
			</c:otherwise>
		</c:choose>
		</div>
		<form action="appliBonus?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="projectId" name="projectId" value="${appliBonus.projectId}"/>
					<div align="center">
					<table>
						<tr>
							<td><font color="red">*</font>财务ID</td>
							<td><input type="text" id="financialId"  name="financialId" value="${appliBonus.financialId }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目名称</td>
							<td><input type="text" id="projectName"  name="projectName" value="${appliBonus.projectName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>受注实绩</td>
							<td><input type="text" id="orderPerformance"  name="orderPerformance" value="${appliBonus.orderPerformance}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>请求实绩</td>
							<td><input type="text" id="requestPerformance"  name="requestPerformance" value="${appliBonus.requestPerformance}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>连携实绩</td>
							<td><input type="text" id="supportPerformance"  name="supportPerformance" value="${appliBonus.supportPerformance}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目开始日</td>
							<td><input type="text" id="projectStartDate"  name="projectStartDate" value="${appliBonus.projectStartDate}"  style="margin-top:5px;height:30px;" />
							</td>
							<td><font color="red">请以YYYY-MM-DD的方式输入(例如2016-08-06)</font></td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目结束日</td>
							<td><input type="text" id="projectEndDate"  name="projectEndDate" value="${appliBonus.projectEndDate}"  style="margin-top:5px;height:30px;" /></td>
							<td><font color="red">请以YYYY-MM-DD的方式输入(例如2016-08-06)</font></td>
						</tr>
						
					</table>

					</div>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error}</font>
					</div>
			</div>
		</form>
</div>



