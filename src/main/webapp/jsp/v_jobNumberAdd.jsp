<%--
  Created by IntelliJ IDEA.
  User: Ven
  Date: 2019/12/25
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath() + "/";
	String JsPath = path + "js/";
	String cssPath = path + "css/";
%>
<html>
<head>
	<title>工作人员管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
	<script src=<%=JsPath + "jquery-3.4.1.min.js"%>></script>
	<script src=<%=path + "js/venTool.js"%>></script>
</head>
<body>
<div class="layui-row" style="padding: 20px 0 0 0;">

	<form class="layui-form layui-form-pane" lay-filter="example">

		<div class="layui-form-item">
			<label class="layui-form-label">最新工号:</label>
			<div class="layui-input-block">
				<input style="color: #f37719" readonly="readonly" type="text" name="lastJobNumber" id="lastJobNumber"  autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">请输入数量:</label>
			<div class="layui-input-block">
				<input style="color: #f37719"  type="text" name="quantity" id="quantity" lay-verify="quantity" autocomplete="off" class="layui-input" oninput="this.value=this.value.replace(/[^\d?]/g,'') ">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="submit" class="layui-btn" lay-submit lay-filter="add">确认</button>
				<button type="reset" class="layui-btn layui-btn-primary" id="cancel">取消</button>
			</div>
		</div>


	</form>
</div>
<script>
	layui.use(['form', 'layedit', 'laydate'], function () {

		var $ = layui.$;
		var form = layui.form, layedit = layui.layedit;
		// //自定义验证规则
		form.verify({

			quantity:function(value){
				if (value.length<=0) {
					return "请输入数量！"
				}
			},
			content: function (value) {
				layedit.sync(editIndex);
			}

		});

		//监听提交
		form.on('submit(add)', function (data) {

			var theIndex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			var startNumber=$('#lastJobNumber').val();
			var quantity=data.field.quantity;

			var ob = {
				startNumber:startNumber,
				quantity:quantity
			};
			layui.$.ajax({
				type: "post",
				url: <%=path%>+"addJobNumber.action",
				data: ob,
				dataType: "json",
				async: true,
				success: function (msg) {
					if (msg.resultMsg==="done"){
						parent.layer.close(theIndex); //再执行关闭
						// parent.layui.table.reload("jobNumberData");
						parent.layer.msg("新增："+msg.quantity+"个工号");
						parent.layui.table.reload('jobNumberData');//刷新父界面的表格数据

					}
					else {
						parent.layer.close(theIndex); //再执行关闭
						parent.layer.msg("新增工号失败")

					}
				},
				error: function () {
					layer.msg("系统故障，请联系维护人员");
				}
			});

			return false;
		});


		layui.$('#cancel').on('click', function () {

			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭

		});


	});


</script>

</body>
</html>
