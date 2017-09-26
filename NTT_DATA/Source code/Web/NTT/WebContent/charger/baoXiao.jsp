<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">	
function confirmed() {
	if(confirm("您确定要导入数据吗？")) {
		window.location = "charger/baoXiaoShow.jsp";
	}
}
	$(document).ready(function(){
		$("ul li:eq(5)").addClass("active");
	});
</script>

<style type="text/css">
.data_list{
	background-color:#;
}
.show{
	position:absolute; 
	top:30%; 
	left:35%; 
	margin:-50px 0 0 -100px; 
	width:600px; 
	height:200px;  
}
</style>

		<div>		
			<h3>经费报销</h3>	
		</div>
		
		<div class="show">
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
          <label class="control-label"> <h5>导入（从财务系统导出的数据）</h5></label>

          <div class="controls">
            <input class="input-file" id="fileInput" type="file">
            <button type="submit" class="btn btn-info" onclick="confirmed()">确认</button>
          </div>    
           <hr style="height:1px;border:none;border-top:1px solid #555555;" />
        </div>
       
     