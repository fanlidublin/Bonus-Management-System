<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="com.ntt.model.Admin" %>
<%@ page import="com.ntt.model.Charger" %>
<%@ page import="com.ntt.model.Staff" %>

<%
	 if(request.getAttribute("user")==null){
		String userName=null;
		String password=null;
		String userType=null;
		String remember=null;
		
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("nttuser")){
				userName=cookies[i].getValue().split("-")[0];
				password=cookies[i].getValue().split("-")[1];
				userType=cookies[i].getValue().split("-")[2];
				remember=cookies[i].getValue().split("-")[3];
			}
		}
		
		if(userName==null){
			userName="";
		}
		
		if(password==null){
			password="";
		}
		
		if(userType==null){
			userType="";
		} else if("admin".equals(userType)){
			pageContext.setAttribute("user", new Admin(userName,password));
			pageContext.setAttribute("userType", 1);
		} else if("charger".equals(userType)) {
			pageContext.setAttribute("user", new Charger(userName,password));
			pageContext.setAttribute("userType", 2);
		} else if("staff".equals(userType)) {
			pageContext.setAttribute("user", new Staff(userName,password));
			pageContext.setAttribute("userType", 3);
		}
		
		if("yes".equals(remember)) {
			pageContext.setAttribute("remember", 1);
		}
		
	} 
%>

<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目奖金管理系统登录</title>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	function checkForm() {
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		var userTypes = document.getElementsByName("userType");
		var userType = null;
		for(var i=0;i<userTypes.length;i++) {
			if(userTypes[i].checked) {
				userType=userTypes[i].value;
				break;
			}
		}
		if (userName == null || userName == "") {
			document.getElementById("error").innerHTML = "用户名不能为空";
			return false;
		}
		if (password == null || password == "") {
			document.getElementById("error").innerHTML = "密码不能为空";
			return false;
		}
		if (userType == null || userType == "") {
			document.getElementById("error").innerHTML = "请选择用户类型";
			return false;
		}
		return true;
	}
</script>

<style type="text/css">
	  body {
        padding-top: 250px;
        padding-bottom: 40px;
        background-image: url('images/background.jpg');
       	background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
      }
      
      .radio {
      	padding-top: 10px;
       	padding-bottom:10px;
      }
      
      .form-signin-heading{
      	text-align: center;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 0px;
        margin: 0 auto 20px;
        background: rgba(0, 0, 0, 0.7);
        border: 1px solid #000000; 
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
      
      .right_bt{
    	top:30px;
    	right:100px;

    	position: fixed;
    	float:right;
        margin: 0 auto 20px;
        background: rgba(0, 0, 0, 0.7);
        border: 1px solid #000000; 
    	-webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
	  }
	  .right_bt .language {
	  	margin: 6px;
	  	font-color:white;
	  }
</style>
</head>

<body>

<div class="container">
      <form name="myForm" class="form-signin" action="login" method="post" onsubmit="return checkForm()">
        <h2 class="form-signin-heading"><font color="#D3D3D3">NTT DATA <br>项目奖金管理系统</font></h2>
        <input id="userName" name="userName" value="${user.userName }" type="text" class="input-block-level" placeholder="用户名...">
        <input id="password" name="password" value="${user.password }" type="password" class="input-block-level" placeholder="密码..." >
        <label class="radio inline">
      	  	<input id="admin" type="radio" name="userType" value="admin"  checked/> <font color="#FFFFFF">系统管理员</font>
		</label>
		<label class="radio inline">
			<input id="charger" type="radio" name="userType" value="charger" ${userType==2?'checked':''} /> <font color="#FFFFFF">管理担当</font>
		</label>
		<label class="radio inline">
			<!--  <input id="staff" type="radio" name="userType" value="staff"  ${userType==3?'checked':''}/> <font color="#FFFFFF">员工</font> -->
		</label>
        <label class="checkbox">
          <input id="remember" name="remember" type="checkbox" value="remember-me" ${remember==1?'checked':''}><font color="#FFFFFF">记住我</font> &nbsp;&nbsp;&nbsp;&nbsp; <font id="error" color="red">${error }</font>  
        </label>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-large btn-primary" type="submit">登录</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-large btn-primary" type="reset" >重置</button>
		<p align="center" style="padding-top: 20px;"> <font color="#EEE0E5"> © PTFC 团队 </font></p>
      </form>
</div>

</body>
</html>