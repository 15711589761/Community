<%--
  Created by IntelliJ IDEA.
  User: 93419
  Date: 2019/11/17
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String Path = request.getContextPath()+"/layui/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>文档管理平台</title>
	<link rel="stylesheet" href=<%=Path+"css/layui.css"%>>
	<script type="text/javascript" src=<%=Path+"layui.js"%>></script>
</head>
<body>
<div class="demoTable">
	<div class="layui-inline">
		文件名：
		<div class="layui-input-inline">
			<input type="text" id="cocutitle" autocomplete="off" class="layui-input">
		</div>
		状态：
		<div class="layui-input-inline">
			<input type="text" id="docustauts" autocomplete="off" class="layui-input">
		</div>
		<div class="layui-upload">
			<button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
			<button type="button" class="layui-btn layui-btn-normal" id="test9">开始上传</button>
		</div>
	</div>
</div>

<table class="layui-table" lay-data="{url:'checkQuery.action', page:true, id:'idTest'}" lay-filter="demo">
	<thead>
	<tr>
		<th lay-data="{field:'facilityId', sort: true, fixed: true}">ID</th>
		<th lay-data="{field:'checkDate'}">检查日期</th>
		<th lay-data="{field:'examineName'}">设备名称</th>
		<th lay-data="{field:'checkStatus'}">检查状态</th>
		<th lay-data="{field:'checkPrincipal'}">负责人</th>

		<th lay-data="{fixed: 'right',width:200,align:'center', toolbar: '#barDemo'}"></th>
	</tr>
	</thead>
</table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="download">下载模板</a>
</script>
<script>
	layui.use('upload', function(){
		var $ = layui.jquery,
			upload = layui.upload;

		upload.render({
			elem: '#test8'
			,url: 'upload.action'
			,auto: false
			,accept: 'file' //普通文件
			,bindAction: '#test9'
			,done: function(json){
				//如果上传失败
				if(json.code === 0){
					return layer.msg('上传失败');
				}
				//上传成功
				if(json.code > 0) {
					return layer.msg('上传成功');
				}
			}
		});
	});
</script>
<script>
	layui.use('table', function () {
		var table = layui.table;
		//监听工具条
		table.on('tool(demo)', function (obj) {

			if  (obj.event === 'download' ) {

				//请求下载的路径
				var url="download.action";
				var xmlResquest = new XMLHttpRequest();
				xmlResquest.open("POST",url, true);
				xmlResquest.setRequestHeader("Content-type", "application/json");
				xmlResquest.setRequestHeader("Authorization", "Bearer 6cda86e3-ba1c-4737-972c-f815304932ee");
				xmlResquest.responseType = "blob";
				xmlResquest.onload = function (oEvent) {
					var content = xmlResquest.response;
					var elink = document.createElement('a');
					elink.download = "model.xlsx";
					elink.style.display = 'none';
					var blob = new Blob([content]);
					elink.href = URL.createObjectURL(blob);
					document.body.appendChild(elink);
					elink.click();
					document.body.removeChild(elink)
				};
				xmlResquest.send();

			}
		});

		var $ = layui.$, active = {};

		$('#form-search').on('click', function () {
			var send_title = $("#cocutitle").val();
			var send_type = $("#docutype").val();
			var send_status = $("#docustauts").val();
			table.reload('idTest', {
				method: 'post'
				, where: {
					'send_title': send_title,
					'send_type': send_type,
					'send_status': send_status
				}
				, page: {
					curr: 1
				}
			});
		});


	});
</script>
</body>
</html>
