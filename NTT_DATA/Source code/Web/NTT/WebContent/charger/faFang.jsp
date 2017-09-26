<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function confirmed() {
		if(confirm("您确定要导入数据吗？")) {
			window.location = "charger/faFangShow.jsp";
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
.show{
	width:1000px;  
}
</style>
		<div>		
			<h3>项目奖金发放</h3>
		</div>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		
		<div class="control-group">
          <label class="control-label"> <h5>方式一：导入（从NOA系统导出的数据）</h5></label>
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
         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<input class="input-file" id="fileInput" type="file">
     		<button type="submit" class="btn btn-info" onclick="confirmed()">确认</button>
        </div>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		
		<h5>方式二：录入（通过界面查看和调整数据）</h5>
	<div class="show">
		<form name="myForm" class="form-search" method="post" action="charger/faFangShow.jsp">			
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
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					请选择经费类型：
					<select id="searchType" name="searchType" style="width: 80px;">
						<option>PM</option>
						<option>部门</option>
						<option>本部</option>
						<option>中心</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">查看</button>
				</span>
		</form>			
</div>
<hr style="height:1px;border:none;border-top:1px solid #555555;" />