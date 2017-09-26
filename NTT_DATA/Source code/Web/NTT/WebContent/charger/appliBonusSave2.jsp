<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="../dist/datepicker.min.css">




<div>
		<div class="data_list_title">
		<c:choose>
			<c:when test="${appliBonus2.projectId!=null}">
				修改申请项目奖金
			</c:when>
			<c:otherwise>
				申请项目奖金
			</c:otherwise>
		</c:choose>
		</div>
		<form action="appliBonus2?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="projectId" name="projectId" value="${appliBonus2.projectId}"/>
					<div align="center">
					<table>
						<tr>
							<td><font color="red">*</font>员工ID</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>员工姓名</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>部门ID</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>部门名称</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>正式员工人月数</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>BP人月数</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" />
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>BP使用率</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
							<td><font color="red">[BP使用率]=[BP人月数]/[人月总数]*100</font></td>
						</tr>
						<tr>
							<td><font color="red">*</font>连携人月数</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>离职人月数</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>申请对象期间</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
							<td><font color="red">请以X-X的方式输入(例如1-3)</font></td>
						</tr>
						<tr>
							<td><font color="red">*</font>申请日期</td>
							<td><input type="text" id=""  name="" value=""  style="margin-top:5px;height:30px;" /></td>
							<td><font color="red">请以YYYY-MM-DD的方式输入(例如2016-08-06)</font></td>
						</tr>
					</table>

					</div>
					<div align="center">
						<button class="btn btn-primary" type="button" onclick="javascript:history.back()"/>申请</button>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error}</font>
					</div>
			</div>
		</form>
</div>



