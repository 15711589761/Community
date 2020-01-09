<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/19
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>智慧社区综合服务平台</title>
	<script type="text/javascript" src="<%=path%>js/md5.js"></script>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		body{
			font-family: "微软雅黑";
			font-size: 14px;
			background: url(<%=path%>image/56a46b7590f5672af5b8.jpg) fixed center center;
		}
		.logo_box{
			width: 280px;
			height: 490px;
			padding: 35px;
			color: #EEE;
			position: absolute;
			left: 50%;
			top:100px;
			margin-left: -175px;
		}
		.logo_box h3{
			text-align: center;
			height: 20px;
			font: 20px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei",sans-serif;
			color: #FFFFFF;
			height: 20px;
			line-height: 20px;
			padding:0 0 35px 0;
		}
		.forms{
			width: 280px;
			height: 485px;
		}
		.logon_inof{
			width: 100%;
			min-height: 450px;
			padding-top: 35px;
			position: relative;
		}
		.input_outer{
			height: 46px;
			padding: 0 5px;
			margin-bottom: 20px;
			border-radius: 50px;
			position: relative;
			border: rgba(255,255,255,0.2) 2px solid !important;
		}
		.u_user{
			width: 25px;
			height: 25px;
			background: url(<%=path%>image/login_ico.png);
			background-position:  -125px 0;
			position: absolute;
			margin: 12px 13px;
		}
		.us_uer{
			width: 25px;
			height: 25px;
			background-image: url(<%=path%>image/login_ico.png);
			background-position: -125px -34px;
			position: absolute;
			margin: 12px 13px;
		}
		.l-login{
			position: absolute;
			z-index: 1;
			left: 50px;
			top: 0;
			height: 46px;
			font: 14px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei";
			line-height: 46px;
		}
		label{
			color: rgb(255, 255, 255);
			display: block;
		}
		.text{
			width: 220px;
			height: 46px;
			outline: none;
			display: inline-block;
			font: 14px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei";
			margin-left: 50px;
			border: none;
			background: none;
			line-height: 46px;
		}
		/*///*/
		.mb2{
			margin-bottom: 20px
		}
		.mb2 a{
			text-decoration: none;
			outline: none;
		}
		.submit {
			padding: 15px;
			margin-top: 20px;
			display: block;
		}
		.act-but{
			width: 280px;
			height: 46px;
			line-height: 20px;
			text-align: center;
			font-size: 20px;
			border-radius: 50px;
			background: #0096e6;
		}
		/*////*/
		.check{
			width: 280px;
			height: 22px;
		}
		.clearfix::before{
			content: "";
			display: table;
		}
		.clearfix::after{
			display: block;
			clear: both;
			content: "";
			visibility: hidden;
			height: 0;
		}
		.login-rm{
			float: left;
		}
		.login-fgetpwd {
			float: right;
		}
		.check{
			width: 18px;
			height: 18px;
			background: #fff;
			border: 1px solid #e5e6e7;
			margin: 0 5px 0 0;
			border-radius: 2px;
		}
		.check-ok{
			background-position: -128px -70px;
			width: 18px;
			height: 18px;
			display: inline-block;
			border: 1px solid #e5e6e7;
			margin: 0 5px 0 0;
			border-radius: 2px;
			vertical-align: middle
		}
		.checkbox{
			vertical-align: middle;
			margin: 0 5px 0 0;
		}
	</style>
</head>
<body>

<script src="<%=path%>js/jquery-3.4.1.min.js" type="text/javascript"></script>

<div class="logo_box">
	<h3>智慧社区欢迎你</h3>
	<c:if test="${requestScope.loginFail!=null}">
		<input type="hidden" id="login_fail" value="${requestScope.loginFail}">
	</c:if>
	<form action="<%=path%>deskLogin.action" method="post" onsubmit="return deskLoginSub()">
		<div class="input_outer">
			<span class="u_user"></span>
			<input name="loginName" id="desk_login_name" class="text" onFocus=" if(this.value=='输入您的楼栋号进行登录') this.value=''" onBlur="if(this.value=='') this.value='输入您的楼栋号进行登录'" value="输入您的楼栋号进行登录" style="color: #FFFFFF !important" type="text">
		</div>
		<div class="input_outer">
			<span class="us_uer"></span>
			<label class="l-login login_password" style="color: rgb(255, 255, 255);display: block;">输入密码</label>
			<input type="hidden" name="imageCode" value="1"/>
			<input name="loginPassWord" id="desk_login_password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;" onFocus="$('.login_password').hide()" onBlur="if(this.value=='') $('.login_password').show()" value="" type="password">
		</div>
		<div class="mb2"><button class="act-but submit" style="color: #FFFFFF">登录</button></div>
		<input name="savesid" value="0" id="check-box" class="checkbox" type="checkbox"><span>记住用户名</span>
	</form>
</div>
<script>
	$(function(){
		var loginFail = $("#login_fail").val();
		if(loginFail.length>0)
		{
			alert("账号密码错误或是您还没有到物业处登记使用");
		}
	});
	function deskLoginSub ()
	{
		var deskLoginName = $("#desk_login_name").val();
		var deskLoginPassWord = $("#desk_login_password").val();
		var res = /[0-9a-zA-Z]{3,4}/;
		var resp = /[0-9a-zA-Z]{6,11}/;
		if (deskLoginName.length > 0 && res.test(deskLoginName))
		{
			if (deskLoginPassWord.length > 0 && resp.test(deskLoginPassWord))
			{
				$("#desk_login_password").val(md5(deskLoginPassWord));
				return true
			} else {
				alert("请输入正确的密码格式进行登录");
			}
		} else {
			alert("输入您的楼栋号进行登录");
		}
		return false;
	}
</script>

</body>
</html>
