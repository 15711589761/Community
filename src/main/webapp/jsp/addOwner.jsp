<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/19
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>layuiAdmin 网站用户 iframe 框</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href='<%=path+"layui/css/layui.css"%>'>
	<%--	<link rel="stylesheet" href='<%=path+"userlist.css"%>'>--%>
	<script src='<%=path+"layui/layui.js"%>'></script>
	<%--	<script src='<%=path+"js/index.js"%>'></script>--%>
</head>
<body>


<div class="layui-form" lay-filter="layui-btn-normal" id="layui-btn-normal" style="padding: 20px 0 0 0;">
	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">业主姓名:</label>
		<div class="layui-input-inline">
			<input type="text" name="owner_name" id="owner_name" lay-verify="required" placeholder="请输入用户名"
			       autocomplete="off" class="layui-input">
		</div>
	</div>


	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">楼栋号</label>
		<div class="layui-input-inline">
			<input type="text" id="owner_room" name="owner_room" lay-verify="car" placeholder="请输入楼栋号"
			       autocomplete="off" class="layui-input">
		</div>
	</div>


	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">手机号码:</label>
		<div class="layui-input-inline">
			<input type="text" name="owner_tel" id="owner_tel" maxlength=11 lay-verify="phone" placeholder="请输入号码"
			       autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">是否是户主:</label>
		<div class="layui-inline">
			<select name="owner_affiliation" id="owner_affiliation" lay-filter="LAY-user-adminrole-type">
				<option value="是">是</option>
				<option value="否">否</option>

			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">房屋状态:</label>
		<div class="layui-inline">
			<select name="owner_status" id="owner_status" lay-filter="LAY-user-adminrole-type">
				<option value="居住">居住</option>
				<option value="出租">出租</option>

			</select>
		</div>
	</div>


	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">身份证:</label>
		<div class="layui-input-inline">
			<input type="text" name="owner_identity" lay-verify="owner_identity" id="owner_identity"
			       placeholder="请输入身份证" autocomplete="off" maxlength=18 class="layui-input">
		</div>
	</div>

	<div class="layui-inline">
		<button class="layui-btn" data-type="demo" id="demo">业主人脸</button>
	</div>


	<div class="layui-form-item layui-hide" style="text-align: center">
		<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
	</div>
</div>

<script src="<%=path+"layui/layui.js"%>"></script>
<script>
	layui.use(['layer'], function() {
		var  layer = layui.layer;
		var object;
		var $ = layui.$, active = {

		};
		$('#demo').on('click', function () {
			alert("录入");
			layer.open({
				type: 2,
				title: '业主人脸录入',
				content: '/Community/add_owner.action',
				maxmin: true,
				area: ['500px', '400px'],
				btn: ['关闭']

			});
		});

	});
	layui.use('form', function () {
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功


		form.render();
	});
</script>

</body>
</html>
