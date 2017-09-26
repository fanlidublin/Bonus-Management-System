<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function bonusNormDelete(centerId) {
		if(confirm("您确定要删除这条奖金标准吗？")) {
			window.location="bonusNorm?action=delete&centerId="+centerId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(2)").addClass("active");
	});
</script>

<style type="text/css">
.data_list{
	background-color:#;
}
#navshow{
	color:red;
}
</style>

<div class="data_list">
		<div class="data_list_title">
			项目奖金标准设定
		</div>
		<form name="myForm" class="form-search" method="post" action="bonusNorm?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='bonusNorm?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="centerName">中心</option>
					</select>
					&nbsp;
					<input id="s_bonusNormText" name="s_bonusNormText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_bonusNormText}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>开发中心</th>
					<th>正式标准</th>
					<th>bp标准</th>
					<th>连携标准</th>
					<th>退职标准</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  varStatus="i" var="bonusNorm" items="${bonusNormList}">
					<tr>
						<td>${i.count+(page-1)*pageSize}</td>
						<td>${bonusNorm.centerName }</td>
						<td>${bonusNorm.zhengshiNorm }</td>
						<td>${bonusNorm.bpNorm }</td>
						<td>${bonusNorm.lianxieNorm }</td>
						<td>${bonusNorm.tuizhiNorm }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='bonusNorm?action=preSave&centerId=${bonusNorm.centerId }'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="bonusNormDelete(${bonusNorm.centerId})">删除</button>
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