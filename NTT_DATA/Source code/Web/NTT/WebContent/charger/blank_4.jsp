<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("ul li:eq(5)").addClass("active");
	});
</script>

<style type="text/css">
.data_list{
background-color:#;
}
.toleft{
float:right;
}
</style>
<div class="data_list">
		<div class="data_list_title">
			<h4 style = "color:black">项目奖金报销</h4>
		</div>
	
		<br>
		<div>
			<table class="table table-hover table-bordered">
				<tr>
					<th width="13%">日期</th>
					<th>项目编码</th>
					<th>项目名称</th>
					<th>报销金额</th>
					<th>操作</th>
				</tr>
			
					<tr>
						<td>2016/3/21</td>
						<td>5B23220001</td>
						<td>5B00-1501-NSW</td>
						<td>319.0</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='zhiQu?action=preSave&centerId=${zhiQu.centerId}'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="zsXizeDelete(${zhiQu.centerId})">删除</button>
						</td>
					</tr>
					<tr>
						<td>2016/3/22</td>
						<td>5B23220002</td>
						<td>5B00-1501-信中市场</td>
						<td>456.0</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='zhiQu?action=preSave&centerId=${zhiQu.centerId}'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="zsXizeDelete(${zhiQu.centerId})">删除</button>
						</td>
					</tr>
			</table>
		</div>
		<div align="center"><font color="red">${error}</font></div>
		<div class="pagination pagination-right">			
				<ul>${pageCode}</ul>
		</div>
</div>