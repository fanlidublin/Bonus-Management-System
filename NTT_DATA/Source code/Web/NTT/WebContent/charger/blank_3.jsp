<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function faFangDelete(centerId) {
		if(confirm("您确定要删除这条发放规则吗？")) {
			window.location="zhiQu?action=delete&centerId="+centerId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(4)").addClass("active");
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
			<h4 style = "color:black">项目奖金支取</h4>
		</div>
		<form name="myForm" class="form-search" method="post" action="zhiQu?action=search">			
				<span>
					请输入员工ID：
					<input  type="text"  style="width:120px;height: 30px;" class="input-medium search-query">
					&nbsp;
					请输入金额：
					<input  type="text" style="width:120px;height: 30px;" class="input-medium search-query">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">添加</button>
				</span>	
			
           			 
            		<div class="toleft">
            		<input class="input-file" id="fileInput" type="file"><br>
            		 	<button class="btn btn-success" type="button" >导出excel到文件</button>	
         		 	</div>		
		</form>
		<br>
		<div>
			<table class="table table-hover table-bordered">
				<tr>
					<th width="13%">NOA申请ID</th>
					<th>员工ID</th>
					<th>员工姓名</th>
					<th>申请支取金额</th>
					<th>申请日期</th>
					<th>操作</th>
				</tr>
			
					<tr>
						<td>1</td>
						<td>SW010011601</td>
						<td>小周</td>
						<td>1000</td>
						<td>2016/08/03</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='zhiQu?action=preSave&centerId=${zhiQu.centerId}'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="zsXizeDelete(${zhiQu.centerId})">删除</button>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>SW010011602</td>
						<td>小林</td>
						<td>2000</td>
						<td>2016/08/03</td>
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