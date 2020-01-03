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
			<input  type="hidden" id="staffId" name="staffId">
			<div class="layui-form-item">
				<label class="layui-form-label">姓名:</label>
				<div class="layui-input-inline">
					<input type="text" name="staffName" id="staffName" lay-verify="staffName"  autocomplete="off" class="layui-input"  >
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别:</label>
				<div class="layui-input-block">
					<input type="radio" name="sex" value="男" title="男">
					<input type="radio" name="sex" value="女" title="女">
				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出生日期:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="staffBirthday" lay-verify="staffBirthday" name="staffBirthday" placeholder="选择出生日期">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">联系电话:</label>
				<div class="layui-input-inline">
					<input type="text" name="staffTel" id="staffTel" lay-verify="staffTel"  autocomplete="off" class="layui-input" oninput="this.value=this.value.replace(/[^\d\-?]/g,'') "
					       >
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">住址:</label>
				<div class="layui-input-inline">
					<input type="text" name="staffAddress" id="staffAddress" lay-verify="staffAddress"  autocomplete="off" class="layui-input"  >
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">入职日期:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="joinDate" name="joinDate" lay-verify="joinDate"  placeholder="请选择入职日期">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit lay-filter="sureChange">确认修改</button>
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
				, layedit = layui.layedit
				, laydate = layui.laydate;

			//时间选择器
			laydate.render({
				elem: '#staffBirthday'
				,trigger: 'click'
			});
			laydate.render({
				elem: '#joinDate'
				,trigger: 'click'
			});

			$(function () {
				setTimeout(function () {
					form.render();
				}, 50)
			});

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');

			//自定义验证规则
			form.verify({

				staffName: function (value) { //value：表单的值、item：表单的DOM对象
					if (value.length <= 0) {
						return '请输入姓名！';
					}
				},
				staffBirthday:function(value){
					if (value.length <= 0) {
						return '请选择出生日期！';
					}
					else{
						var theDate=$('#staffBirthday').val();

						var theNum=getAge(theDate);

						if (theNum<18){
							return "未满18周岁！请重新选择出生日期！"
						}

					}
				},
				staffTel:function(value){
					if (value.length <= 0) {
						return '请输入联系电话！';
					}
					else if(value.length>0){
						if (checkNumber(value)===false){
							return "请输入正确的联系方式"
						}
					}
				},
				staffAddress:function(value){
					if (value.length<=0){
						return "请输入地址！"
					}
				},
				joinDate:function(value){
					if (value.length<=0){
						return "请选择入职日期！"
					}
					else {

						if (dayIsExceed($('#joinDate').val())===true) {
							return "入职日期大于当前日期，请重新选择"
						}
					}
				},
				content: function (value) {
					layedit.sync(editIndex);
				}


			});


			//监听提交
			form.on('submit(sureChange)', function (data) {

				var staffAge=getAge($('#staffBirthday').val());

				var sex=$('input[name="sex"]:checked').val();

				var staffOb = {
					staffName:data.field.staffName,
					staffSex:sex,
					staffBirthday:data.field.staffBirthday,
					staffAge:staffAge,
					staffTel:data.field.staffTel,
					staffAddress:data.field.staffAddress,
					entryDate:data.field.joinDate,
					staffId:$('#staffId').val()
				};
				layui.$.ajax({
					type: "post",
					url: <%=path%>+"updateStaffInfo.action",
					data: staffOb,
					dataType: "json",
					async: true,
					success: function (msg) {
						if (msg.resultMsg=== "修改成功"){
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(index); //再执行关闭
							parent.layer.msg("修改成功！");//修改成功后提示！
							parent.layui.table.reload('staffData');//刷新父界面的表格数据
						}
						else{
							layer.msg("修改失败，请重试！如果多次操作还是失败，请联系维护人员");

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
