
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>物业缴费</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%> media="all">
	<script type="text/javascript" src=<%=path + "/layui/layui.js"%>></script>
</head>
<body>
<br>
<br>
<form class="layui-form layui-form-pane" action="wyalipay" id="alipayForm" >
	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">房号:</label>
		<div class="layui-input-inline">
			<input type="text" name="room_number" id="room_number" lay-verify="required" placeholder="请输入内容"
			       autocomplete="off" class="layui-input">
		</div>
	</div>

	<label class="layui-form-label">缴费类型:</label>
	<div class="layui-inline">
		<select name="receivables_type"  id="receivables_type" lay-filter="LAY-user-adminrole-type">
			<option value=""></option>
			<option value="水电费">水电费</option>
			<option value="物业费">物业费</option>
		</select>
		<br/>
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
		var $ = layui.$
			, form = layui.form
			, layer = layui.layer
			, upload = layui.upload;


			//监听提交
		$('#btn').on('click', function () {
			var room_number = $("#room_number").val();
			var money = $("#money").val();
			if (room_number!=""&&room_number.length>0) {
				if (money!=""&money.length>0){
					$("#alipayForm").attr("action", $("#alipayForm").attr("action") + "?money=")+money;
					$("#alipayForm").submit();
				}else{
					layer.msg("请输入缴费金额")
				}
			}else{
				layer.msg("请重试")
			}
		});
	})
</script>
</html>
