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
			<h3>项目奖金总额查看</h3>
		</div>
		<form name="myForm" class="form-search" method="post" action="appliBonus2?action=search">
				
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="projectName">项目</option>
					</select>
					&nbsp;
					<input id="s_appliBonusText2" name="s_appliBonusText2" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_appliBonusText2}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>财务ID</th>
					<th>项目名称</th>
					<th>部门名称</th>
					<th>申请人姓名</th>					
					<th>正式员工人月数</th>
					<th>BP人月数</th>
					<th>连携人月数</th>
					<th>离职人月数</th>
					<th>申请对象期间</th>
					<th>申请日期</th>	
					<th>奖金总额</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  varStatus="i" var="appliBonus2" items="${appliBonusList2}">
					<tr>
						<td>${appliBonus2.financialId}</td>
						<td>${appliBonus2.projectName}</td>
						<td>${appliBonus2.depName}</td>
						<td>${appliBonus2.employName}</td>						
						<td>${appliBonus2.zhengshiMonth}</td>
						<td>${appliBonus2.BPMonth}</td>
						<td>${appliBonus2.lianxieMonth}</td>
						<td>${appliBonus2.lizhiMonth}</td>
						<td>${appliBonus2.applyDate}</td>
						<td>${appliBonus2.applyDate2}</td>
						<td>${appliBonus2.bonustotal}</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error}</font></div>	
		<div class="pagination pagination-right">				
				<ul>${pageCode}</ul>
				<br><br>
				<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
		</div>
</div>