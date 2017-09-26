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
			项目奖金额度划分(细则)
					</div>
		<form name="myForm" class="form-search" method="post" action="xiZeModify2?action=search">
				
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="centerName">中心</option>
					</select>
					&nbsp;
					<input id="s_xiZeModifyText2" name="s_xiZeModifyText2" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_xiZeModifyText2}">
					&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th></th>
					<th>奖金总额</th>
					<th>中心</th>					
					<th>本部</th>
					<th>部门</th>
					<th>PM</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  varStatus="i" end="0" var="xiZeModify" items="${xiZeModifyList2}">
					<tr>
						<td>${xiZeModify.name}</td>
						<td>${xiZeModify.bonustotal }</td>
						<td>${xiZeModify.zhongxin }</td>						
						<td>${xiZeModify.benbu }</td>
						<td>${xiZeModify.bumen }</td>
						<td>${xiZeModify.pm }</td>
					</tr>
				</c:forEach>
				<c:forEach  varStatus="i" begin="1"  end="1" var="xiZeModify" items="${xiZeModifyList2}">
					<tr>
						<td>${xiZeModify.name}</td>
						<td>${xiZeModify.bonustotal }</td>
						<td><input type="text" id="zhongxin"  name="zhongxin" value="${xiZeModify.zhongxin}"  style="width:70px;height:25px" /></td>				
						<td><input type="text" id="benbu"  name="benbu" value="${xiZeModify.benbu}"  style="width:70px;height:25px" /></td>
						<td><input type="text" id="bumen"  name="bumen" value="${xiZeModify.bumen}"  style="width:70px;height:25px" /></td>
						<td><input type="text" id="pm"  name="pm" value="${xiZeModify.pm}"  style="width:70px;height:25px" /></td>
					</tr>
				</c:forEach>
				<c:forEach  varStatus="i" begin="2"  end="2" var="xiZeModify" items="${xiZeModifyList2}">
					<tr>
						<td>${xiZeModify.name}</td>
						<td>${xiZeModify.bonustotal }</td>
						<td>${xiZeModify.zhongxin }</td>
						<td>${xiZeModify.benbu }</td>						
						<td>${xiZeModify.bumen }</td>
						<td>${xiZeModify.pm }</td>			
					</tr>
				</c:forEach>
				
			</tbody>
			</table>
			<div align="right">
				<input style="margin-right:5%" type="submit" class="btn btn-primary" value="确定修改"/>
			</div>	
			<div align="right"></div>
				<button class="btn" type="button" onclick="javascript:history.back()">返回</button>
			</div>
			<div align="center"><font color="red">${error}</font></div>
			<div class="pagination pagination-right">			
				<ul>${pageCode}</ul>
			</div>
			
			</div>
	