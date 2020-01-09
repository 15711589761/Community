<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/20
  Time: 10:12
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
	<title>车辆录入</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%> media="all">
	<script type="text/javascript" src=<%=path + "/layui/layui.js"%>></script>
</head>
<body>
<br>
<br>
<form class="layui-form layui-form-pane" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">车牌</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" id="carNum" readonly="readonly" lay-verify="required"
			       autocomplete="off">
		</div>
		<div class="layui-upload">
			<button type="button" class="layui-btn layui-btn-normal" id="test8">选择车牌图片</button>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">楼栋号</label>
		<%--		<div class="layui-input-block">--%>
		<%--			<input type="text" class="layui-input" id="roomNum" lay-verify="required" autocomplete="off">--%>
		<%--		</div>--%>
		<select class="layui-select" id="roomNum" lay-verify="required" lay-filter="lay-filter">
			<option value=""></option>
			<c:forEach items="${requestScope.roomNum}" begin="0" step="1" var="roomNum">
				<option value="${roomNum.roomNum}">${roomNum.roomNum}</option>
			</c:forEach>
		</select>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">业主</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" id="oName" readonly="readonly" lay-verify="required"
			       autocomplete="off">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button type="button" class="layui-btn" lay-submit="" lay-filter="demo" id="sub">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<script>
	layui.use(['upload', 'form', 'layer'], function () {
		var $ = layui.jquery
			, form = layui.form
			, layer = layui.layer
			, upload = layui.upload;

		form.on('select(lay-filter)', function (data) {
			var roomNum = $("#roomNum").val();
			if (roomNum != null && roomNum.length > 0) {
				$.ajax({
					type: "POST",
					url: "/Community/getOwnerName",
					data: "msg=" + roomNum,
					dataType: "json",
					async: true,
					success: function (res) {
						if (res != null) {
							$("#oName").val(res.ownerName);
							layer.msg(roomNum + "的业主为：" + res.ownerName)
						}
					},
					error: function () {
						layer.msg("服务器正忙")
					}
				});
			}
		});
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
					// layer.msg("上传成功")
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
		$('#sub').on('click', function () {
			var carNum = $("#carNum").val();
			var roomNum = $("#roomNum").val();
			var ownerName = $("#oName").val();
			var ob = {carNum: carNum, name: ownerName, roomNum: roomNum};
			if (carNum != null && roomNum != null && ownerName != null && carNum.length > 0 && roomNum.length > 0 && ownerName.length > 0) {
				$.ajax({
					type: "POST",
					url: "/Community/addOwnerCar",
					data: ob,
					dataType: "json",
					async: true,
					success: function (res) {
						var num = parseInt(res);
						if (num > 0) {
							alert("添加成功");
							//重新加载当前页面
							table.reload();
						}
					},
					error: function () {
						layer.msg("服务器正忙")
					}
				});
			}
		});
	})
</script>
</body>
</html>
