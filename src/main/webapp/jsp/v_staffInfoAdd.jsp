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
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.min.js"%>></script>
	<script src=<%=path+"js/venTool.js"%>></script>
	<style>
		#video {
			width: auto;
			height: 150px;
			border-radius: 50%;
		}
	</style>
</head>
<body>
	<div class="layui-row" lay-filter="layuiadmin-form-staffInfo" id="layuiadmin-form-staffInfo" style="padding: 20px 0 0 0;">

		<form class="layui-form" lay-filter="example">

			<div class="layui-form-item">
				<label class="layui-form-label">姓名:</label>
				<div class="layui-input-inline">
					<input type="text" name="staffName" id="staffName" lay-verify="staffName"  autocomplete="off" class="layui-input" placeholder="请输入姓名" >
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别：</label>
				<div class="layui-input-block">
					<input type="radio" name="staffSex" value="男" title="男" checked="">
					<input type="radio" name="staffSex" value="女" title="女">
				</div>
				<input type="hidden" id="staffSex" value="男" >
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出生日期:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="staffBirthday" lay-verify="staffBirthday" name="staffBirthday" placeholder="请选择出生日期">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">联系电话:</label>
				<div class="layui-input-inline">
					<input type="text" name="staffTel" id="staffTel" lay-verify="staffTel" placeholder="请输入联系电话" autocomplete="off" class="layui-input" oninput="this.value=this.value.replace(/[^\d\-?]/g,'') "
					       >
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">住址:</label>
				<div class="layui-input-inline">
					<input type="text" name="staffAddress" id="staffAddress" lay-verify="staffAddress" placeholder="请输入地址" autocomplete="off" class="layui-input"  >
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">入职日期:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="joinDate" name="joinDate" lay-verify="joinDate"  placeholder="请选择入职日期">
				</div>
			</div>
			<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">请选择职位:</label>
				<div class="layui-input-inline">
					<select name="roleBean" id="roleBean" lay-verify="roleBean" >
						<option value="">请选择职位</option>
						<c:forEach items="${sessionScope.roleBeans}" begin="0" step="1" var="t">
							<option id="${t.roleId}" value="${t.roleName}" >${t.roleName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">人脸录入:</label>
				<div id="media">
				<video id="video" autoplay></video>
				<canvas id="canvas" width="400" height="300" style="display: none"></canvas>
			</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit lay-filter="addNewUser">添加</button>
					<button type="button" class="layui-btn layui-btn-normal" id="closeThis">取消</button>

				</div>
			</div>
		</form>
	</div>
	<script>
		layui.use(['form', 'layedit', 'laydate'], function () {

			//var 是定义变量
			var video = document.getElementById("video"); //获取video标签
			var context = canvas.getContext("2d");
			var con  ={
				audio:false,
				video:{
					width:1980,
					height:1024
				}
			};

			//导航 获取用户媒体对象
			navigator.mediaDevices.getUserMedia(con)
				.then(function(stream){
					video.srcObject = stream;
					video.onloadmetadate = function(e){
						video.play();
					}
				});

			var $ = layui.$;
			var ob;
			var isRepeat = false;
			var form = layui.form
				, layer = layui.layer
				, layedit = layui.layedit
				, laydate = layui.laydate;


			form.on('radio', function(data){

				if(data.value==="男"){
					$("#sex").val("男")
				}else if (data.value==="女") {
					$("#sex").val("女")
				}

			});

			//时间选择器
			laydate.render({
				elem: '#staffBirthday'
				,trigger: 'click'
			});
			laydate.render({
				elem: '#joinDate'
				,trigger: 'click'
			});

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');

			// //自定义验证规则
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
				roleBean:function(value){
					if (value.length<=0) {
						return "请选择职位！"
					}
				},
				content: function (value) {
					layedit.sync(editIndex);
				}


			});

			function getBase64() {
				var imgSrc = document.getElementById("canvas").toDataURL(
					"image/png");
				return imgSrc.split("base64,")[1];

			}


			//监听提交
			form.on('submit(addNewUser)', function (data) {
				// var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				// parent.layer.close(index); //再执行关闭

				var roleNode=$("#roleBean option:checked");//定义select选择的节点

				var staffAge=getAge($('#staffBirthday').val());

				context.drawImage(video,0,0,400,300);

				var base = getBase64();

				var newStaffOb = {
					staffName:data.field.staffName,
					staffSex:data.field.staffSex,
					staffBirthday:data.field.staffBirthday,
					staffAge:staffAge,
					staffTel:data.field.staffTel,
					staffAddress:data.field.staffAddress,
					entryDate:data.field.joinDate,
					roleId:roleNode.attr("id"),
					faceCode:base

				};
				layui.$.ajax({
					type: "post",
					url: <%=path%>+"addStaff.action",
					data: newStaffOb,
					dataType: "json",
					async: true,
					success: function (msg) {
						if (msg.resultMsg=== "新增成功"){
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(index); //再执行关闭
							parent.layer.msg("职员信息登记成功！");
							parent.layui.table.reload("staffData");

						}
					},
					error: function () {
						layer.msg("系统故障，请联系维护人员");
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
