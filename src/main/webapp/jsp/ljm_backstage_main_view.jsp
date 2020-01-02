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
	<script src="<%=path + "js/md5.js"%>"></script>
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
					<dd><a href="javascript:;" onclick="myMessage()">基本资料</a></dd>
					<dd><a href="javascript:;" onclick="upPass()">修改密码</a></dd>
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

	function upPass() {
		layui.use('layer',function () {
			var $ = layui.jquery;
			layer.open({
				type:2,
				content:"<%=path%>toUpdatePassWord.view",
				offset: ['10%', '40%'],
				area: ['300px','350px'],
				title: '修改密码',
				btn: ['修改', '关闭'],
				btn1: function (index,layero) {
					var oldPass = $(layero).find('iframe')[0].contentWindow.old_password.value;
					var newPass = $(layero).find('iframe')[0].contentWindow.new_password.value;
					var repeatPass = $(layero).find('iframe')[0].contentWindow.repeat_password.value;
					if (oldPass.length>0&&newPass.length>0&&repeatPass.length>0)
					{
						if (newPass === repeatPass)
						{
							oldPass = md5(oldPass);
							newPass = md5(newPass);
							var upPass={newPass:newPass,oldPass:oldPass};
							$.ajax({
								url:"<%=path%>updateBackstagePassWord.action", //请求的url地址
								dataType:"json", //返回格式为json
								async:true,//请求是否异步，默认为异步，这也是ajax重要特性
								data:upPass, //参数值
								type:"post", //请求方式
								success:function(msg){
									layer.closeAll();
									layer.msg(msg.msg);
									table.reload('test');
								},
								error:function () {
									alert("系统忙请重试");
									table.reload('test');
								}
							})
						} else {
							alert('两次输入密码不同');
						}
					} else {
						alert("请补全修改信息");
					}

				},
				btn2: function () {
					layer.closeAll();
				}
			})
		});
	}

	function myMessage() {
		layui.use('layer',function () {
			layer.open({
				type:2,
				content:"<%=path%>toLookPersonMessage.view",
				offset: ['10%', '40%'],
				area: ['300px','500px'],
				title: '基本信息',
				btn: ['关闭'],
				btn1: function () {
					layer.closeAll();
				}
			})
		});
	}
</script>
</body>
</html>
