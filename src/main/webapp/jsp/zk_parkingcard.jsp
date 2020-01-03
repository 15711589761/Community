<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/25
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>停车卡缴费</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%> media="all">
	<script type="text/javascript" src=<%=path + "/layui/layui.js"%>></script>
</head>
<body>
<form class="layui-form layui-form-pane" action="alipay" id="alipayForm">
	<div class="layui-form-item">
		<label class="layui-form-label">车牌</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" id="carNum" readonly="readonly" lay-verify="required" placeholder="请识别车牌"
			       autocomplete="off">
		</div>
		<div class="layui-upload">
			<button type="button" class="layui-btn layui-btn-normal" id="test8">选择车牌图片</button>
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">缴费金额</label>
		<div class="layui-input-block">
			<input type="text" name="money" id="money" lay-verify="required|number" placeholder="请输入缴费金额" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button type="button" class="layui-btn" id="btn">缴费</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<form class="layui-form layui-form-pane" action="deskLogin.action">
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn">返回首页</button>
		</div>
	</div>
</form>
</body>
<script>
	layui.use(['upload', 'form', 'layer'], function () {
		var $ = layui.jquery
			, form = layui.form
			, layer = layui.layer
			, upload = layui.upload;
		upload.render({
			elem: '#test8'
			, url: '/Community/License plate recognition'
			, accept: 'file'
			, exts: 'jpg|jpeg|png|bmp'
			//, auto: false
			//,multiple: true
			, bindAction: '#test9'
			, done: function (res) {
				console.log(res);
				if (res.code === 200) {
					$("#carNum").val(res.msg);
					layer.msg("识别成功")
				}
				if (res.code === 500) {
					layer.msg("识别失败，请重试")
				}
			}
			, error: function (res) {
				layer.msg("识别失败，请重试")
			}
		});
		//监听提交
		$('#btn').on('click', function () {
			var carNum = $("#carNum").val();
			var money = $("#money").val();
			if (carNum!=null&&carNum.length>0) {
				if (money!=null&money.length>0){
				$("#alipayForm").attr("action", $("#alipayForm").attr("action") + "?money=")+money;
				$("#alipayForm").submit();
				}else{
					layer.msg("请输入缴费金额")
				}
			}else{
				layer.msg("请先识别车牌")
			}
		});
	})
</script>
</html>
