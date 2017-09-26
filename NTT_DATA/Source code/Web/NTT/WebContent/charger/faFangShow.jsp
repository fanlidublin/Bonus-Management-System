<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.ntt.model.Admin" %>
<%@ page import="com.ntt.model.Charger" %>
<%@ page import="com.ntt.model.Staff" %>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目奖金管理系统</title>


<link href="${pageContext.request.contextPath}/style/dorm.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/dataTables.bootstra.css">
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/jquery.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/jquery.dataTables.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/bootstrap.min.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>



<style type="text/css">
body {
  	background-image: url('../images/c.jpg');
    background-position: center;
	background-repeat: no-repeat;
	background-attachment: fixed;
         	
}
.bs-docs-sidenav {
    background: rgba(0, 0, 0, 0.6);
    border-radius: 6px;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
    padding: 0;
    width: 100%;
}
.bs-docs-sidenav > li > a {
    border: 1px solid #A3A3A3;
    display: block;
    padding: 8px 14px;
    margin: 0 0 -1px;
}
.bs-docs-sidenav > li:first-child > a {
    border-radius: 6px 6px 0 0;
}
.bs-docs-sidenav > li:last-child > a {
    border-radius: 0 0 6px 6px;
}
.bs-docs-sidenav .icon-chevron-right {
    float: right;
    margin-right: -6px;
    margin-top: 2px;
    opacity: 0.25;
}
.bs-docs-sidenav > li > a:hover {
    background-color: #EEE0E5;
}
.bs-docs-sidenav a:hover .icon-chevron-right {
    opacity: 0.5;
}
.bs-docs-sidenav .active .icon-chevron-right, .bs-docs-sidenav .active a:hover .icon-chevron-right {
    background-image: url("../img/glyphicons-halflings-white.png");
    opacity: 1;
}
#footer{
	position:absolute; 
	bottom:0; 
	width:100%;
}
#round{
	border-top-left-radius: 150px;
    border-top-right-radius: 150px;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
}
</style>

</head>
<body>
<div class="container-fluid" style="padding-right: 0px;padding-left: 0px;">
	<div style="height: 72px;background-image: url('../images/top4.jpg')">
		<div align="left" style="width: 80%; height:60px; float: left; padding-top:30px; padding-left:30px;" >
			<font color="white" size="6" ><b>软件项目工程师奖金管理系统（NTT DATA）</b></font>
		</div>
		<div style="padding-top: 48px;padding-right: 20px;">
			<font size="3" color="white"><b>当前用户：</b></font>
			&nbsp;
			<font color="red" size="3"><b>${currentUser.userName}</b></font>
		</div>
	</div>
</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2 bs-docs-sidebar" >
				<ul class="nav nav-list bs-docs-sidenav" >
					<li><a href="../blank"><i class="icon-chevron-right"></i><font size="4" face="Microsoft YaHei">首页</font></a></li>
					<li><a href="../appliBonus?action=list"><i class="icon-chevron-right" ></i><font size="4" face="Microsoft YaHei">项目奖金总额申请</font></a></li>
					<li><a href="../zongZeModify"><i class="icon-chevron-right" ></i><font size="4" face="Microsoft YaHei">项目奖金额度划分</font></a></li>
					<li><a href="../faFang"><i class="icon-chevron-right" ></i><font size="4" face="Microsoft YaHei">项目奖金发放</font></a></li>
					<li><a href="../zhiQu"><i class="icon-chevron-right" ></i><font size="4" face="Microsoft YaHei">员工项目奖金支取</font></a></li>
					<li><a href="../baoXiao"><i class="icon-chevron-right" ></i><font size="4" face="Microsoft YaHei">经费报销</font></a></li>							
					<li><a href="../yuE"><i class="icon-chevron-right" ></i><font size="4" face="Microsoft YaHei">余额管理</font></a></li>	
					<li><a href="password?action=preChange"><i class="icon-chevron-right"></i><font size="4" face="Microsoft YaHei">修改密码</font></a></li>
					<li><a href="../login.jsp"><i class="icon-chevron-right"></i><font size="4" face="Microsoft YaHei">退出系统</font></a></li>
				</ul>
			</div>
				<div class="span8">
					<jsp:include page="${mainPage==null?'blank_2.jsp':mainPage}"></jsp:include>
				</div>
		</div>	
	</div>
	
	<div id="footer" >
		<div id="round" style="height: 100px;background-image: url('../images/top5.jpg')">
			<div align="center" style="float: center; padding-top:50px; padding-left:30px;" >
				<font color="white" size="4" ><b>© 浙江师范大学 -- PTFC 团队</b></font>
			</div>
		</div>
	</div>
</body>
</html>