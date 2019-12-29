<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/11/17
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>智慧社区管理平台</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">物业管理平台</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					${requestScope.staff.staffName}
					<input type="hidden" id="staff_in_view" value="${requestScope.staff}"/>
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
					<dd><a href="">修改密码</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href=<%=path + "toBackstageLogin.view"%>>退出</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree" lay-filter="test">
				<c:if test="${requestScope.menu!=null}">
					<c:forEach items="${requestScope.menu}" var="x" step="1" begin="0">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="">${x.key}</a>
							<dl class="layui-nav-child">
								<c:forEach items="${x.value}" var="y" begin="0" step="1">
									<dd><a href="<%=path%>${y.toUrl}" target="page-view">${y.menuName}</a></dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<iframe src="" name="page-view" frameborder="0" width="100%" height="100%"></iframe>
	</div>

	<div class="layui-footer">
	</div>
</div>
<script src=<%=path + "layui/layui.js"%>></script>
<script>
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;

	});
</script>
</body>
</html>
