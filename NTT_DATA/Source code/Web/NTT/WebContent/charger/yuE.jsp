<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("ul li:eq(6)").addClass("active");
	});
</script>

<style type="text/css">
.data_list{
	background-color:#;
}
.show{
	position:absolute; 
	top:50%; 
	left:40%; 
	margin:-50px 0 0 -100px; 
	width:400px; 
	height:200px;  
}
</style>

		<div>		
			<h3>余额管理</h3>
			<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		</div>
		
		
		<div class="control-group">
          <form name="myForm" class="form-search" method="post" action="">			
				<span>
					请选择中心：
					<select id="searchType" name="searchType" style="width: 80px;">
						<option>北京</option>
						<option>无锡</option>
						<option>天津</option>
						<option>长春</option>
						<option>西安</option>
					</select>
					&nbsp;&nbsp;&nbsp;	
					请选择项目：
					<select id="searchType" name="searchType" style="width: 80px;">
						<option>项目1</option>
						<option>项目2</option>
						<option>项目3</option>
					</select>
					&nbsp;
					<div style="margin:20 auto;width:80px;">
						<button type="submit" class="btn btn-info">查看</button>
					</div>
				</span>
		</form>			  
        </div>
        <hr style="height:1px;border:none;border-top:1px solid #555555;" />
   <!--  
         <div>
			<table class="table table-hover table-bordered">
				<tr>
					<th width="13%">项目ID</th>
					<th>奖金总额</th>
					<th>中心</th>
					<th>本部</th>
					<th>部门</th>
					<th>PM</th>
					<th>操作</th>
				</tr>
			
					<tr>
						<td>5W211011606</td>
						<td>25120</td>
						<td>2500</td>
						<td>1221</td>
						<td>3488</td>
						<td>3221</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='zhiQu?action=preSave&centerId=${zhiQu.centerId}'">修改更新</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="zsXizeDelete(${zhiQu.centerId})">删除</button>
						</td>
					</tr>
			</table>
		</div>
	-->
		
