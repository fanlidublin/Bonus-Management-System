<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("ul li:eq(2)").addClass("active");
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
		<form name="myForm" class="form-search" method="post" action="">
				
				<span class="data_search">
					中心：
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="projectName">无锡</option>
						<option value="projectName">北京</option>
					</select>
					&nbsp;
					<input id="s_appliBonusText2" name="s_appliBonusText2" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_appliBonusText2}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
					&nbsp;
				</span>
				
				<button class="btn btn-info" >批处理</button>
		</form>

		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th width="5%">全选<input name=tt type=checkbox onclick=fuck(this)></th>
					<th>财务ID</th>
					<th>项目名称</th>				
					<th>正式员工人月数</th>
					<th>BP人月数</th>
					<th>连携人月数</th>
					<th>离职人月数</th>
					<th>申请对象期间</th>
					<th>申请时间</th>
					<th>奖金总额</th>
					<th width="10%">是否划分</th>
					<th>金额划分调整</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  varStatus="i" var="appliBonus2" items="${appliBonusList3}">
					<tr>
						<td><input name=tt type=checkbox onclick=fuck(this)></td>
					    <td>${appliBonus2.financialId}</td>
						<td>${appliBonus2.projectName}</td>				
						<td>${appliBonus2.zhengshiMonth}</td>
						<td>${appliBonus2.BPMonth}</td>
						<td>${appliBonus2.lianxieMonth}</td>
						<td>${appliBonus2.lizhiMonth}</td>
						<td>${appliBonus2.applyDate}</td>
						<td>${appliBonus2.applyDate2}</td>
						<td>${appliBonus2.bonustotal}</td>
						<td>${appliBonus2.bonushuafen}</td>
						<td>
							<button class="btn btn-mini" type="button" onclick="javascript:window.location='zongZeModify2?action=list'">总则划分</button>
							&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='xiZeModify2?action=list'">按细则划分修改</button>
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