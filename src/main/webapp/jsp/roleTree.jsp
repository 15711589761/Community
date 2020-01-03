<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
%>
<html>
<head>
	<title>添加角色</title>
	<script src=<%=path+"/js/jQuery.js"%>></script>

	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>

</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-role" id="layuiadmin-form-role" style="padding: 20px 30px 0 0;">
	<div class="layui-form-item">
<%--		<label class="layui-form-label">拥有权限</label>--%>
		<div class="layui-input-block">
			<div class="demo-tree-more" id="test"></div>
		</div>
	</div>
	<div class="layui-form-item layui-hide">
		<button class="layui-btn" lay-submit lay-filter="LAY-user-role-submit" id="LAY-user-role-submit">提交</button>
	</div>
</div>
<script src=<%=path+"/layuiadmin/layui/layui.js"%>></script>
<script>
	layui.config({
		base: '../layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'form'], function () {
		var $ = layui.$
		, form = layui.form;
	});

</script>
<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
<script>

	layui.use(['tree', 'util'], function () {
		var tree = layui.tree;
		//从父层获取值，json是父层的全局js变量。eval是将该string类型的json串变为标准的json串
		var parent_json = eval('('+parent.json+')');
		$.post("/Community/findAllMenu?roleId="+parent_json.roleId, function (data) {//请求数据
			tree.render({
				elem: '#test'
				, data: data
				, showCheckbox: true  //是否显示复选框
				, id: 'demoId1'
				, isJump: false //是否允许点击节点时弹出新窗口跳转
			});
		});
	});
</script>
</body>
</html>
