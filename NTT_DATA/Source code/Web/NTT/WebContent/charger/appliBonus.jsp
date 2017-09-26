<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function appliBonusDelete(projectId) {
		if(confirm("您确定要删除该项目吗？")) {
			window.location="appliBonus?action=delete&projectId="+projectId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>

<style type="text/css">

#navshow{
	color:red;
}

</style>

<div class="data_list">
		<div class="data_list_title">
			<h3>项目奖金总额申请</h3>
		</div>
		<form name="myForm" class="form-search" method="post" action="appliBonus?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='appliBonus?action=preSave'">添加</button>
				<span class="data_search">
				中心：
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="projectName">无锡</option>
					</select>
					&nbsp;
					<input id="s_appliBonusText" name="s_appliBonusText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_appliBonusText}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
					<div align="right">
						<font size="3"><b>导入奖金总额申请信息：</b></font>
						<input class="input-file" id="fileInput" type="file">
            			<button type="submit" class="btn btn-info" onclick="confirmed()">确认</button>
            		</div>
            		<br>
            		<div align="right">
            			<font size="3"><b>导入项目信息：</b></font>
						<input class="input-file" id="fileInput" type="file">
            			<button type="submit" class="btn btn-info" onclick="confirmed()">确认</button>
            		</div>	
				</span>
		</form>
		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>财务ID</th>
					<th>项目名称</th>
					<th>受注实绩</th>
					<th>请求实绩</th>
					<th>连携实绩</th>
					<th>项目开始日</th>
					<th>项目结束日</th>
					<th>操作</th>
					<th>奖金申请</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  varStatus="i" var="appliBonus" items="${appliBonusList}">
					<tr>
						<td>${appliBonus.financialId }</td>
						<td>${appliBonus.projectName }</td>
						<td>${appliBonus.orderPerformance }</td>
						<td>${appliBonus.requestPerformance }</td>
						<td>${appliBonus.supportPerformance }</td>
						<td>${appliBonus.projectStartDate }</td>
						<td>${appliBonus.projectEndDate }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='appliBonus?action=preSave&projectId=${appliBonus.projectId}'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="appliBonusDelete(${appliBonus.projectId})">删除</button>
						</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='appliBonus2?action=preSave&projectId=${appliBonus2.projectId}'">奖金申请</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="javascript:window.location='appliBonus2?action=list&projectId=${appliBonus.projectId}'">查询</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error}</font></div>
		<div class="pagination pagination-right">			
				<ul>${pageCode}</ul>
		</div>
</div>