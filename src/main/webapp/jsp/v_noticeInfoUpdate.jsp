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
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path+"layui/layui.js"%>></script>
	<script src=<%=path+"js/venTool.js"%>></script>

</head>
<body>
	<div class="layui-row" lay-filter="layuiadmin-form-staffInfo" id="layuiadmin-form-staffInfo" style="padding: 20px 0 0 0;">

		<form class="layui-form" lay-filter="example">
			<input  type="hidden" id="targetId" name="targetId">
			<div class="layui-form-item">
				<label class="layui-form-label">公告标题:</label>
				<div class="layui-input-inline">
					<input type="text" name="noticeTitle" id="noticeTitle" lay-verify="noticeTitle"  autocomplete="off" class="layui-input"  >
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">公告内容:</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" name="noticeDetails" id="noticeDetails" lay-verify="noticeDetails" class="layui-textarea"></textarea>
				</div>
			</div>


			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit lay-filter="sure">提交</button>
					<button type="button" class="layui-btn layui-btn-normal" id="closeThis">取消</button>

				</div>
			</div>
		</form>
	</div>
	<script>


		layui.use(['form', 'layedit', 'laydate'], function () {

			var $ = layui.$;
			var ob;
			var isRepeat = false;
			var form = layui.form
				, layer = layui.layer
				, layedit = layui.layedit;

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');

			//自定义验证规则
			form.verify({

				noticeTitle: function (value) { //value：表单的值、item：表单的DOM对象
					if (value.length <= 0) {
						return '请输入公告标题！';
					}
				},
				noticeDetails:function(value){
					if (value.length <= 0) {
						return '请输入公告内容！';
					}
				},
				content: function (value) {
					layedit.sync(editIndex);
				}


			});


			//监听提交
			form.on('submit(sure)', function (data) {

				var nowDate=new Date();

				var ob = {
					noticeTitle:data.field.noticeTitle,
					noticeDetails:data.field.noticeDetails,
					issuedDate:getNowDate(nowDate),
					targetId:$('#targetId').val()
				};
				layui.$.ajax({
					type: "post",
					url: <%=path%>+"updateNotice.action",
					data: ob,
					dataType: "json",
					async: true,
					success: function (msg) {
						if (msg.identify==="updateNotice") {
							if (msg.resultMsg=== "done"){
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭
								parent.layer.msg("修改成功！");//修改成功后提示！
								parent.layui.table.reload('noticeData');//刷新父界面的表格数据
							}
							else{
								layer.msg("修改失败，请重试！如果多次操作还是失败，请联系维护人员");

							}
						}

					},
					error: function () {
						layer.msg("数据交互异常，请联系维护人员");
					}
				});

				return false;
			});


			layui.$('#closeThis').on('click', function () {

				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭

			});

		});


	</script>

</body>
</html>
