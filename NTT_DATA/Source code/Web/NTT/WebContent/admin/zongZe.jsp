<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function zongZeDelete(centerId) {
		if(confirm("您确定要删除这条总则吗？")) {
			window.location="zongZe?action=delete&centerId="+centerId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>

<style type="text/css">
.data_list{
background-color:#;
}
</style>
<div class="data_list">
		<div class="data_list_title">
			项目总则设定
		</div>
		<form name="myForm" class="form-search" method="post" action="zongZe?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='zongZe?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="centerName">中心</option>
					</select>
					&nbsp;
					<input id="s_zongZeText" name="s_zongZeText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_zongZeText}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>开发中心</th>
					<th>BP率上限</th>
					<th>项目经费比例（上限）</th>
					<th>项目经费比例（下限）</th>
					<th>分配用项目奖金（上限）</th>
					<th>分配用项目奖金（下限）</th>
					<th>项目奖金推荐比例</th>
					<th width="20%">操作</th>
				</tr>
				<c:forEach  varStatus="i" var="zongZe" items="${zongZeList}">
					<tr>
						<td>${i.count+(page-1)*pageSize}</td>
						<td>${zongZe.centerName }</td>
						<td>${zongZe.bpRate }</td>
						<td>${zongZe.projectRateUp }</td>
						<td>${zongZe.projectRateDown }</td>
						<td>${zongZe.shareBonusRateUp }</td>
						<td>${zongZe.shareBonusRateDown }</td>
						<td>${zongZe.tuijianRate }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='zongZe?action=preSave&centerId=${zongZe.centerId }'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="zongZeDelete(${zongZe.centerId})">删除</button>
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