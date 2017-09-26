<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var centerName = document.getElementById("centerName").value;
		var zxFund = document.getElementById("zxFund").value;
		var bbFund = document.getElementById("bbFund").value;
		var bmFund = document.getElementById("bmFund").value;
		var xmFund = document.getElementById("xmFund").value;
		var sumFund = document.getElementById("sumFund").value;
		if(centerName==""||zxFund==""||bbFund==""||bmFund==""||xmFund==""||sumFund==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} 
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>
<div>
		<div class="data_list_title">
		<c:choose>
			<c:when test="${lxXize.centerId!=null}">
				修改（连携社员）项目奖金分配比例规则
			</c:when>
			<c:otherwise>
				添加（连携社员）项目奖金分配比例规则
			</c:otherwise>
		</c:choose>
		</div>
		<form action="lxXize?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="centerId" name="centerId" value="${lxXize.centerId}"/>
					<div align="center">
					<table>
						<tr>
							<td><font color="red">*</font>中心名称</td>
							<td><input type="text" id="centerName"  name="centerName" value="${lxXize.centerName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>中心共通经费</td>
							<td><input type="text" id="zxFund"  name="zxFund" value="${lxXize.zxFund }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>本部经费</td>
							<td><input type="text" id="bbFund"  name="bbFund" value="${lxXize.bbFund}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>部门经费</td>
							<td><input type="text" id="bmFund"  name="bmFund" value="${lxXize.bmFund}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目经费</td>
							<td><input type="text" id="xmFund"  name="xmFund" value="${lxXize.xmFund}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>小计</td>
							<td><input type="text" id="sumFund"  name="sumFund" value="${lxXize.sumFund}"  style="margin-top:5px;height:30px;" /></td>
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