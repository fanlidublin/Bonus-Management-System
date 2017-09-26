<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function tzXizeDelete(centerId) {
		if(confirm("您确定要删除这条分配规则吗？")) {
			window.location="tzXize?action=delete&centerId="+centerId;
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
			（退职社员）项目奖金分配规则
		</div>
		<form name="myForm" class="form-search" method="post" action="tzXize?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='tzXize?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="centerName">中心</option>
					</select>
					&nbsp;
					<input id="s_tzXizeText" name="s_tzXizeText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_tzXizeText}">
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
				<c:forEach  varStatus="i" var="tzXize" items="${tzXizeList}">
					<tr>
						<td>${tzXize.centerId }</td>
						<td>${tzXize.centerName }</td>
						<td>${tzXize.zxFund }</td>
						<td>${tzXize.bbFund }</td>
						<td>${tzXize.bmFund }</td>
						<td>${tzXize.xmFund }</td>
						<td>${tzXize.sumFund }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='tzXize?action=preSave&centerId=${tzXize.centerId}'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="tzXizeDelete(${tzXize.centerId})">删除</button>
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