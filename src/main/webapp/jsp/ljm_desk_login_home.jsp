<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/20
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>智慧社区服务平台</title>
	<link rel="stylesheet" type="text/css" href=<%=path + "css/deskbase.css"%>>
	<link rel="stylesheet" type="text/css" href=<%=path + "css/main.css"%>/>
	<link href="css/css.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src=<%=path + "js/jquery-1.8.3.min.js"%>></script>
	<script type="text/javascript" src=<%=path+"js/jquery-1.7.1.min.js"%>></script>
	<link rel="stylesheet" type="text/css" href=<%=path + "css/jquery.slideBox.css"%>/>
	<script type="text/javascript" src=<%=path + "js/jquery.slideBox.min.js"%>></script>
	<script type="text/javascript" src=<%=path + "js/nav.js"%>></script>
</head>
<body>
<div class="header">
	<div class="container">
		<div class="toptxt">
			<span>您好，欢迎使用智慧社区综合服务网</span>
			<c:if test="${requestScope.loginSuccess!=null}">
				<input type="hidden" id="desk_owners_message" value="${requestScope.loginSuccess}">
			</c:if>
		</div>
	</div>
</div>
<div class="log-nav">
	<div class="logo">
		<a href="#"><img src="<%=path%>image/logo.jpg"/></a>
	</div>
	<div class="nav">
		<ul class="" id="navul">
			<li class="active"><a href="<%=path%>deskLogin.action">首页</a></li>
			<c:if test="${requestScope.menu!=null}">
				<c:forEach items="${requestScope.menu}" begin="0" step="1" var="i">
					<li><a href="<%=path%>${i.menuUrl}?parentMenuId=${i.menuId}">${i.menuName}</a></li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	<div class="clearfix"></div>
</div>
<div class="flexslider">
	<ul class="slides">
		<li style="background:url(<%=path%>image/banner.jpg) 50% 0 no-repeat;"></li>
		<li style="background:url(<%=path%>image/banner.jpg) 50% 0 no-repeat;"></li>
	</ul>
</div>

<div class="pageny">
	<div class="container">
		<div class="lujing">您当前所在位置：
			<a href="<%=path%>deskLogin.action">首页</a>>
			<c:if test="${requestScope.selectedMenu!=null}">
				<a href="javascript:;">${requestScope.selectedMenu}</a>
			</c:if>
		</div>
		<div class="clearfix"></div>
		<div class="page-left">
			<div class="fllist">
				<ul>
					<c:if test="${requestScope.selectedMenu!=null}">
						<li><a href="javascript:;">${requestScope.selectedMenu}</a></li>
					</c:if>
					<c:if test="${requestScope.childMenu!=null}">
						<c:forEach items="${requestScope.childMenu}" begin="0" step="1" var="j">
							<li><a href="<%=path%>${j.menuUrl}" target="page-view">${j.menuName}</a></li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="page-right">
			<iframe src="" name="page-view" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<div class="foot">
	<div class="ft-menu">
		<div class="container">
			<div class="links">
				<h1>友情链接</h1>
				<div class="list">
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
				</div>
			</div>
			<div class="fttel">
				<h1>0372-12345</h1>
				<p>服务时间：周一至周日07:00-21:00</p>
				<p>非工作时间请使用：</p>
				<p>12345短信：106 3505 12345</p>
				<p>话音留言：0372-123456</p>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>

<div class="livechat-girl animated"> <img class="girl" src="images/en_3.png">
	<div class="livechat-hint rd-notice-tooltip rd-notice-type-success rd-notice-position-left single-line show_hint">
		<div class="rd-notice-content">嘿，我来帮您！</div>
	</div>
	<div class="animated-circles">
		<div class="circle c-1"></div>
		<div class="circle c-2"></div>
		<div class="circle c-3"></div>
	</div>
</div>
<!--轮播-->
<script type="text/javascript" src="<%=path%>js/jquery.flexslider-min.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$('.flexslider').flexslider({
			directionNav: true,
			pauseOnAction: false
		});
	});
</script>
<!--分页-->
<script src="<%=path%>js/jquery.page.js"></script>
<script>
	$(".tcdPageCode").createPage({
		pageCount: 100,
		current: 1,
		backFn: function (p) {
			//console.log(p);
		}
	});
</script>

<script>

	$(function () {
		setInterval(function(){
			if($(".animated-circles").hasClass("animated")){
				$(".animated-circles").removeClass("animated");
			}else{
				$(".animated-circles").addClass('animated');
			}
		},3000);
		var wait = setInterval(function(){
			$(".livechat-hint").removeClass("show_hint").addClass("hide_hint");
			clearInterval(wait);
		},4500);
		$(".livechat-girl").hover(function(){
			clearInterval(wait);
			$(".livechat-hint").removeClass("hide_hint").addClass("show_hint");
		},function(){
			$(".livechat-hint").removeClass("show_hint").addClass("hide_hint");
		}).click(function(){
			//当前人员在线显示弹窗
			alert("开始聊天")
		});
	});

</script>
</body>
</html>
