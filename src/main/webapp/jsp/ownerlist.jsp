<%--
  Created by IntelliJ IDEA.
  User: 魏书源
  Date: 2019/12/17
  Time: 14:30
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
	<title>客户管理</title>
</head>
<body>
<br/><br/><br/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

<div class="demoTable" style="text-align: center">
	<div class="layui-inline">
		<input class="layui-input" name="owner_name" id="demoReload" placeholder="请输入业主姓名" autocomplete="off">
	</div>
	&nbsp&nbsp&nbsp&nbsp
	<div class="layui-inline">
		<input class="layui-input" name="owner_tel" id="demoReload1" placeholder="请输入手机号码" autocomplete="off">
	</div>
	&nbsp&nbsp&nbsp&nbsp
	<div class="layui-inline">
		<input class="layui-input" name="owner_status" id="demoReload2" placeholder="请输入房间状态" autocomplete="off">
	</div>
	&nbsp&nbsp&nbsp&nbsp
	<div class="layui-inline">
		<input class="layui-input" name="owner_identity" id="demoReload3" placeholder="请输入业主身份证号码" autocomplete="off">
	</div>
	&nbsp&nbsp&nbsp&nbsp
	<button class="layui-btn" data-type="reload">搜索</button>
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	<button data-type="add" class="layui-btn layui-btn-normal">增加业主</button>

</div>


<table id="demo" lay-filter="test"></table>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
	layui.use('table', function () {
		var table = layui.table;

		table.render({
			elem: '#demo'
			, url: '/Community/ownerlist.action'
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, page: true
			, limit: 5
			, limits: [5]
			, method: 'POST'
			, id: 'testReload'
			, cols: [[
				{field: 'owner_id', title: 'ID', sort: true}
				, {field: 'owner_name', title: '姓名'}
				, {field: 'owner_room', title: '楼栋号', sort: true}
				, {field: 'owner_tel', title: '联系电话'}
				, {field: 'owner_affiliation', title: '户主'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
				, {field: 'owner_status', title: '状态', sort: true}
				, {field: 'owner_identity', title: '身份证', sort: true}
				, {title: '操作', align: 'center', toolbar: '#barDemo'}

			]]
		});

		var $ = layui.$, active = {
			reload: function () {
				var owner_name = $('#owner_name');
				var owner_tel = $('#owner_tel');
				var owner_status = $('#owner_status');
				var owner_identity = $('#owner_identity');
				var demoReload = $('#demoReload');
				var demoReload1 = $('#demoReload1');
				var demoReload2 = $('#demoReload2');
				var demoReload3 = $('#demoReload3');


				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						owner_name: owner_name.val(),
						owner_tel: owner_tel.val(),
						owner_status: owner_status.val(),
						owner_identity: owner_identity.val(),
						owner_name: demoReload.val(),
						owner_tel: demoReload1.val(),
						owner_status: demoReload2.val(),
						owner_identity: demoReload3.val()

					}
				}, 'data');
			},

			add: function () {
				layer.open({
					type: 2,
					title: '增加业主',
					content: 'jsp/addOwner.jsp',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['确定', '取消'],
					yes: function (index, layero) {
						var owner_name = $(layero).find('iframe')[0].contentWindow.owner_name.value;
						var owner_room = $(layero).find('iframe')[0].contentWindow.owner_room.value;
						var owner_tel = $(layero).find('iframe')[0].contentWindow.owner_tel.value;
						var owner_affiliation = $(layero).find('iframe')[0].contentWindow.owner_affiliation.value;
						var owner_status = $(layero).find('iframe')[0].contentWindow.owner_status.value;
						var owner_identity = $(layero).find('iframe')[0].contentWindow.owner_identity.value;
						var ob = {
							owner_name: owner_name,
							owner_room: owner_room,
							owner_tel: owner_tel,
							owner_affiliation: owner_affiliation,
							owner_status: owner_status,
							owner_identity: owner_identity
						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/addOwner.action",//提交的地址
							data: ob,
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {  //提交成功的方法， （）为返回的数据类型
								alert(tablebean)
								if (tablebean.msg == 1) {
									alert("增加成功！")
									table.reload('testReload');
									layer.close(index);
								} else {
									alert("增加失敗！")
								}
							},

							error: function () {  //错误的方法
								alert("服务器正忙！");
							}
						})
					}
				});
			},


		};

		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';

		});


		//监听工具条
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			if (obj.event === 'detail') {
				layer.msg('ID：' + data.id + ' 的查看操作');
			} else if (obj.event === 'del') {
				var owner_id = data['owner_id'];
				layer.confirm('真的删除行么', function (index) {

					$.ajax({
						type: "POST",//提交方式
						url: "/Community/delOwner.action?owner_id=" + owner_id,//提交的地址
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (tablebean) {  //提交成功的方法， （）为返回的数据类型
							if (tablebean.msg = "1") {
								table.reload('testReload');
								//obj.del();
								layer.close(index);
								alert("删除成功！")

							} else {
								alert("删除失敗！")
							}
						},

						error: function () {  //错误的方法
							alert("服务器正忙！");
						}
					});
				})

			} else if (obj.event === 'edit') {
				layer.open({
					type: 2,
					title: '编辑',
					content: 'jsp/updateOwner.jsp',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['确定', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#owner_name").val(data.owner_name);
						body.find("#owner_room").val(data.owner_room);
						body.find("#owner_tel").val(data.owner_tel);
						body.find("#owner_affiliation").val(data.owner_affiliation);
						body.find("#owner_status").val(data.owner_status);
						body.find("#owner_identity").val(data.owner_identity);

					},
					yes: function (index, layero) {
						var owner_name = $(layero).find('iframe')[0].contentWindow.owner_name.value;
						var owner_room = $(layero).find('iframe')[0].contentWindow.owner_room.value;
						var owner_tel = $(layero).find('iframe')[0].contentWindow.owner_tel.value;
						var owner_affiliation = $(layero).find('iframe')[0].contentWindow.owner_affiliation.value;
						var owner_status = $(layero).find('iframe')[0].contentWindow.owner_status.value;
						var owner_identity = $(layero).find('iframe')[0].contentWindow.owner_identity.value;
						var owner_id = data.owner_id;
						var ob = {
							owner_id: owner_id,
							owner_name: owner_name,
							owner_room: owner_room,
							owner_tel: owner_tel,
							owner_affiliation: owner_affiliation,
							owner_status: owner_status,
							owner_identity: owner_identity
						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/updateOwner.action",//路径
							data: ob,//数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {//成功的方法 msg为返回数据

								if (tablebean.msg == 1) {
									alert("修改成功");
									table.reload('testReload');
									layer.close(index);
								} else {
									alert("修改失败")
								}
							},
							error: function () { //错误的方法
								alert("服务器正忙")
							}

						});

					}


				})
			}
		});

	});

</script>


</body>
</html>
