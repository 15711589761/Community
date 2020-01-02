<%--
  Created by IntelliJ IDEA.
  User: 魏书源
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
	<title>上传合同</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href='<%=path+"layui/css/layui.css"%>'>
	<script src='<%=path+"layui/layui.js"%>'></script>
</head>
<body>

<form>
	<div class="layui-form" lay-filter="layui-btn-file" id="layui-btn-file" style="padding: 20px 0 0 0;">
		<div class="layui-form-item" style="text-align: center">
			<label class="layui-form-label">合同名称:</label>
			<div class="layui-input-inline">
				<input type="text"  name="ontract_name" id="ontract_name" lay-verify="required" placeholder="请输入合同名称" autocomplete="off" class="layui-input">
				<input id ="name" type="hidden" name="name" value="<%=request.getSession().getAttribute("username")%>">
			</div>
		</div>

		<div class="layui-form-item" style="text-align: center">
			<label class="layui-form-label">上传时间:</label>
			<div class="layui-input-inline">
				<input type="date" id="ontract_time" name="ontract_time" lay-verify="required"  placeholder="请输入职业" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item " >
			<div class="layui-upload"></div>
			<input type="button" class="layui-btn layui-btn-normal" id="test8" value="选择文件">
		</div>





		<div class="layui-form-item " style="text-align: center">
			<div class="layui-input-inline">
				<input type="button" class="layui-btn" id="test9" value="开始上传">
			</div>
		</div>
	</div>


	<script src='<%=path+"layui/layui.js"%>'></script>

	<script>
		layui.use('upload', function() {
			var $ = layui.jquery
				, upload = layui.upload;
			upload.render({
				elem: '#test8',
				height: 312,
				url: '/Community/insertOntract.action',
				accept: 'file',
				exts: 'doc|csv|txt|docx|zip|rar|xls|xlsx|pdf',
				auto: false,
				bindAction: "#test9",
				before: function () {
					this.data = {
						ontract_name: $('#ontract_name').val(),
						ontract_uploadPath: $('#ontract_uploadPath').val(),
						ontract_time: $('#ontract_time').val()
						//name:$('#name').val()
					}
				},
				done: function (res) {
					alert("上传成功");

				}

			})
		})
	</script>




	<script>
		layui.use('form', function(){
			var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

			//……

			//但是，如果你的HTML是动态生成的，自动渲染就会失效
			//因此你需要在相应的地方，执行下述方法来进行渲染
			form.render();
		});
	</script>
</form>
</body>
</html>
