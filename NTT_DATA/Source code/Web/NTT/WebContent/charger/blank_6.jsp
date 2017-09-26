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
	<h3>修改项目奖金标准</h3>
		<form action="appliBonus?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="projectId" name="projectId" />
					<div align="center">
					<table>
						<tr>
							<td><font color="red"></font>当前奖金金额：</td>
							<td>6000</td>
						</tr>	
						<tr>
							<td><font color="red"></font>修改奖金金额：</td>
							<td><input type="text" id="supportPerformance"  name="supportPerformance_2"  style="margin-top:5px;height:30px;" /></td>
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
