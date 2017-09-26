<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var centerName = document.getElementById("centerName").value;
		var bpRate = document.getElementById("bpRate").value;
		var projectRateUp = document.getElementById("projectRateUp").value;
		var projectRateDown = document.getElementById("projectRateDown").value;
		var shareBonusRateUp = document.getElementById("shareBonusRateUp").value;
		var shareBonusRateDown = document.getElementById("shareBonusRateDown").value;
		var tuijianRate = document.getElementById("tuijianRate").value;
		if(centerName==""||bpRate==""||projectRateUp==""||projectRateDown==""||shareBonusRateUp==""||shareBonusRateDown==""||tuijianRate==""){
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
			<c:when test="${zongZe.centerId!=null}">
				修改总则
			</c:when>
			<c:otherwise>
				添加总则
			</c:otherwise>
		</c:choose>
		</div>
		<form action="zongZe?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="centerId" name="centerId" value="${zongZe.centerId}"/>
					<div align="center">
					<table>
						<tr>
							<td><font color="red">*</font>中心名称</td>
							<td><input type="text" id="centerName"  name="centerName" value="${zongZe.centerName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>bp率</td>
							<td><input type="text" id="bpRate"  name="bpRate" value="${zongZe.bpRate}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目经费比例（上限）</td>
							<td><input type="text" id="projectRateUp"  name="projectRateUp" value="${zongZe.projectRateUp}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目经费比例（下限）</td>
							<td><input type="text" id="projectRateDown"  name="projectRateDown" value="${zongZe.projectRateDown}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>分配用项目奖金（上限）</td>
							<td><input type="text" id="shareBonusRateUp"  name="shareBonusRateUp" value="${zongZe.shareBonusRateUp}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>分配用项目奖金（下限）</td>
							<td><input type="text" id="shareBonusRateDown"  name="shareBonusRateDown" value="${zongZe.shareBonusRateDown}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>推荐比例</td>
							<td><input type="text" id="shareBonusRateDown"  name="tuijianRate" value="${zongZe.tuijianRate}"  style="margin-top:5px;height:30px;" /></td>
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