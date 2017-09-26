<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var centerName = document.getElementById("centerName").value;
		var zhengshiNorm = document.getElementById("zhengshiNorm").value;
		var bpNorm = document.getElementById("bpNorm").value;
		var lianxieNorm = document.getElementById("lianxieNorm").value;
		var tuizhiNorm = document.getElementById("tuizhiNorm").value;
		if(centerName==""||zhengshiNorm==""||bpNorm==""||lianxieNorm==""||tuizhiNorm==""){
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
			<c:when test="${bonusNorm.centerId!=null}">
				修改项目奖金标准
			</c:when>
			<c:otherwise>
				添加项目奖金标准
			</c:otherwise>
		</c:choose>
		</div>
		<form action="bonusNorm?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="centerId" name="centerId" value="${bonusNorm.centerId}"/>
					<div align="center">
					<table>
						<tr>
							<td><font color="red">*</font>中心名称</td>
							<td><input type="text" id="centerName"  name="centerName" value="${bonusNorm.centerName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>正式标准</td>
							<td><input type="text" id="zhengshiNorm"  name="zhengshiNorm" value="${bonusNorm.zhengshiNorm }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>bp标准</td>
							<td><input type="text" id="bpNorm"  name="bpNorm" value="${bonusNorm.bpNorm}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>连携标准</td>
							<td><input type="text" id="lianxieNorm"  name="lianxieNorm" value="${bonusNorm.lianxieNorm}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>退职标准</td>
							<td><input type="text" id="tuizhiNorm"  name="tuizhiNorm" value="${bonusNorm.tuizhiNorm}"  style="margin-top:5px;height:30px;" /></td>
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