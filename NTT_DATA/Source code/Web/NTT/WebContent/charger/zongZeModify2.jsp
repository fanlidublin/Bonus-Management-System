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
			<h3>项目奖金额度划分(总则)</h3>
		</div>
		<form name="myForm" class="form-search" method="post" action="zongZeModify2?action=search">
				
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="centerName">中心</option>
					</select>
					&nbsp;
					<input id="s_zongZeModifyText2" name="s_zongZeModifyText2" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_zongZeModifyText2}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th></th>
					<th>项目经费比例上限</th>
					<th>项目经费金额上限</th>					
					<th>项目经费推荐比例</th>
					<th>项目经费金额（推荐）</th>
					<th>项目奖金比例下限</th>
					<th>项目奖金金额下限</th>
					<th>中心共通经费</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  varStatus="i" end="0" var="zongZeModify" items="${zongZeModifyList2}">
					<tr>
						<td>${zongZeModify.name}</td>
						<td>${zongZeModify.proMoneyRatioMax }</td>
						<td>${zongZeModify.proMoneyMax }</td>						
						<td>${zongZeModify.proMoneyRatioMin }</td>
						<td>${zongZeModify.proMoneyMin }</td>
						<td>${zongZeModify.proBonusRatioMin }</td>
						<td>${zongZeModify.proBonusMin }</td>
						<td>${zongZeModify.zhongxinMoney }</td>
					</tr>
				</c:forEach>
				
			</tbody>
			</table>
		</div>
		<button class="btn" type="button" onclick="javascript:history.back()">返回</button>
		<div align="center"><font color="red">${error}</font></div>
		<div class="pagination pagination-right">			
				<ul>${pageCode}</ul>
		</div>
</div>