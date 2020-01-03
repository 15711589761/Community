<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/11/18
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href='<%=path+"layui/css/layui.css"%>'>
	<script src='<%=path+"layui/layui.js"%>'></script>


	<title>合同管理</title>
</head>
<body>
<br/><br/><br/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<div class="layui-form">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">起始日期</label>
			<div class="layui-input-inline">
				<input type="date" class="layui-input" id="startDate" placeholder="yyyy年MM月dd日">
			</div>
		</div>
		~
		<div class="layui-inline">
			<label class="layui-form-label">结束日期</label>
			<div class="layui-input-inline">
				<input type="date" class="layui-input" id="endDate" placeholder="dd/MM/yyyy">
			</div>

		</div>

		<div class="layui-inline">

			<input class="layui-input" name="ontract_name" id="ontract_name" placeholder="请输入合同名" autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload">查询</button>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<button data-type="add" class="layui-btn layui-btn-normal">上传合同</button>


	</div>
</div>

<table id="demo" lay-filter="test"></table>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" id="detail">查看</a>
	<a class="layui-btn layui-btn-xs" lay-event="download">下载</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>

	layui.use('table', function () {
		var table = layui.table;

		table.render({
			elem: '#demo'
			, url: '/Community/ontractTable.action'
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, page: true
			, limit: 5
			, limits: [5]
			, method: 'POST'
			, id: 'testReload'
			, cols: [[
				{field: 'ontract_id',title: '编号', sort: true}
				, {field: 'ontract_name', title: '合同名'}
				, {field: 'ontract_uploadPath', title: '上传路径', sort: true}
			    , {field: 'ontract_time', title: '上传时间'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
				, {title: '操作', width: 178, align: 'center', toolbar: '#barDemo'}

			]]
		});

		var $ = layui.$, active = {
			reload: function () {
				var startDate = $('#startDate');
				var endDate = $('#endDate');
				var ontract_name = $('#ontract_name');


				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						startDate: startDate.val(),
						endDate: endDate.val(),
						ontract_name: ontract_name.val()

					}
				}, 'data');
			},

			add: function () {
				layer.open({
					type: 2,
					title: '上传文件',
					content: 'jsp/wsy_uploadOntract.jsp',
					maxmin: true,
					area: ['400px', '450px'],
					//btn: ['关闭'],
					yes: function (index, layero) {
						var ontract_name = $(layero).find('iframe')[0].contentWindow.ontract_name.value;
						var ontract_uploadPath = $(layero).find('iframe')[0].contentWindow.ontract_uploadPath.value;
						var ontract_time = $(layero).find('iframe')[0].contentWindow.ontract_time.value;


						var ob = {
							ontract_name: ontract_name,
							ontract_uploadPath: ontract_uploadPath,
							ontract_time: ontract_time


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/insertOntract.action",//提交的地址
							data: ob,
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (layero,index) {  //提交成功的方法， （）为返回的数据类型
							var body = layero.getChildFrame('body',index);
							body.find("ontract_name").val(date,ontract_name);
								body.find("ontract_uploadPath").val(date,ontract_uploadPath);
								body.find("ontract_time").val(date,ontract_time);

							}


						})
					}
				});
			}
		};

		$('.layui-form .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';

		});


		//监听工具条
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			if (obj.event === 'download') {
				var fileName = data['ontract_name'] + "." + "doc";
				var url = "downFile.action?fileName=" + fileName;
				var xmlResqust = new XMLHttpRequest();
				xmlResqust.open("POST", url, true);
				xmlResqust.setRequestHeader("Content-type", "application/json");
				xmlResqust.setRequestHeader("Authorization", "Bearer 6cda86e3-ba1c-4737-972c-f815304932ee");
				xmlResqust.responseType = "blob";
				xmlResqust.onload = function (ev) {
					var content = xmlResqust.response;
					var elink = document.createElement('a');
					elink.download = fileName;
					elink.style.display = 'none';
					var blob = new Blob([content]);
					elink.href = URL.createObjectURL(blob);
					document.body.appendChild(elink);
					elink.click();
					document.body.removeChild(elink);

				};
				xmlResqust.send();

				//table.on('tool(test)', function (obj) {
				//	var data = obj.data;

				//})
			}else if (obj.event === 'detail') {
				var ontract_id = data['ontract_id'];
				window.open("/Community/devDoc.action?ontract_id=" + ontract_id, "_blank", "top=100,left=100,height=600,width=1000,status=yes,toolbar=1,menubar=no,location=no,scrollbars=yes");
				} else if (obj.event === 'del') {
				var ontract_id = data['ontract_id'];
				layer.confirm('真的删除行么', function (index) {
					$.ajax({
						type: "POST",//提交方式
						url: "/Community/delOntract.action?ontract_id=" + ontract_id,//提交的地址
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (tablebean) {  //提交成功的方法， （）为返回的数据类型
							if (tablebean.msg == "1") {
								table.reload('test');
								//obj.del();
								layer.close(index);
								alert("刪除成功！")

							} else {
								alert("刪除失敗！")
							}
						},

						error: function () {  //错误的方法
							alert("服务器正忙！");
						}
					});
				})

			}

				});

	});

</script>

<%--<script>--%>
<%--	function view(id){--%>
<%--		window.open("<%=path%>Community/devDoc.action?id="+id, "_blank","top=100,left=100,height=600,width=1000,status=yes,toolbar=1,menubar=no,location=no,scrollbars=yes");--%>

<%--	}--%>

<%--</script>--%>

</body>
</html>
