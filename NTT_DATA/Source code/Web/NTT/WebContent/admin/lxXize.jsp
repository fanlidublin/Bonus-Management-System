<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function lxXizeDelete(centerId) {
		if(confirm("您确定要删除这条分配规则吗？")) {
			window.location="lxXize?action=delete&centerId="+centerId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>

<style type="text/css">
.data_list{
background-color:#;
}
</style>
<div class="data_list">
		<div class="data_list_title">
			（连携社员）项目奖金分配规则
		</div>
		<form name="myForm" class="form-search" method="post" action="lxXize?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='lxXize?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="centerName">中心</option>
					</select>
					&nbsp;
					<input id="s_lxXizeText" name="s_lxXizeText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_lxXizeText}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>开发中心</th>
					<th>中心共通经费</th>
					<th>本部经费</th>
					<th>部门经费</th>
					<th>项目经费</th>
					<th>小计</th>
					<th>操作</th>
				</tr>
				<c:forEach  varStatus="i" var="lxXize" items="${lxXizeList}">
					<tr>
						<td>${lxXize.centerId }</td>
						<td>${lxXize.centerName }</td>
						<td>${lxXize.zxFund }</td>
						<td>${lxXize.bbFund }</td>
						<td>${lxXize.bmFund }</td>
						<td>${lxXize.xmFund }</td>
						<td>${lxXize.sumFund }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='lxXize?action=preSave&centerId=${lxXize.centerId}'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="lxXizeDelete(${lxXize.centerId})">删除</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center"><font color="red">${error}</font></div>
		<div class="pagination pagination-right">			
				<ul>${pageCode}</ul>
		</div>
</div>