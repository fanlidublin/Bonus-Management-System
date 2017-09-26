<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function confirmed() {
		if(confirm("您确定要导入数据吗？")) {
			window.location = "charger/zhiQuShow.jsp";
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
			<h3>员工项目奖金支取</h3>
		</div>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		
		<div class="control-group">
          <label class="control-label"> <h5>方式一：导入（从NOA系统导出的数据）</h5></label>

          <div class="controls">
            <input class="input-file" id="fileInput" type="file">
            <button type="submit" class="btn btn-info" onclick="confirmed()">确认</button>
          </div>   
        </div>
		
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		<h5>方式二：录入（通过界面查看和调整数据）</h5>
<div class="show">
		<form name="myForm" class="form-search" method="post" action="charger/zhiQuShow.jsp">			
					<div style="margin:10;width:100px;">
						<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">点击进入</button>
					</div>
		</form>			
</div>
<br><br><br><br>
<hr style="height:1px;border:none;border-top:1px solid #555555;" />