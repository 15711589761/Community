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


	<title>仓库管理</title>
</head>
<body>
<br/><br/><br/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<div class="layui-form" style="text-align: center">
	<div class="layui-form-item">

		<div class="layui-inline">

			<input class="layui-input" name="manifest_number" id="manifest_number" placeholder="请输入订单号" autocomplete="off">
		</div>
		<div class="layui-inline">

			<input class="layui-input" name="manifest_name" id="manifest_name" placeholder="请输入货物名称" autocomplete="off">
		</div>

		<button class="layui-btn" data-type="reload">查询</button>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<button data-type="add" class="layui-btn layui-btn-normal"  >入库</button>


	</div>
</div>

<%--<div class="demoTable" style="text-align: center">--%>
<%--	<div class="layui-inline">--%>
<%--		<input class="layui-input" name="owsafe_event_titlener_name" id="safe_event_title" placeholder="请输入标题" autocomplete="off">--%>
<%--	</div>--%>
<%--	<button class="layui-btn" data-type="reload">查询</button>--%>
<%--	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%>
<%--	<button data-type="add" class="layui-btn layui-btn-normal"  >添加事件</button>--%>

<%--</div>--%>




<table id="demo" lay-filter="test"></table>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">入库</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">出库</a>
<%--	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
</script>
<script>
	layui.use('table', function () {
		var table = layui.table;

		table.render({
			elem: '#demo'
			, url: '/Community/warehouse.action'
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, page: true
			, limit: 5
			, limits: [5]
			, method: 'POST'
			, id: 'testReload'
			, cols: [[
				{field: 'manifest_id',title: '编号', sort: true}
				, {field: 'manifest_number', title: '货物单号'}
				, {field: 'manifest_name', title: '货物名称', sort: true}
				, {field: 'manifest_quantity', title: '货物数量'}
				, {title: '操作', width: 178, align: 'center', toolbar: '#barDemo'}

			]]
		});

		var $ = layui.$, active = {
			reload: function () {
				var manifest_number = $('#manifest_number');
				var manifest_name = $('#manifest_name');




				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
					    manifest_number: manifest_number.val(),
						manifest_name: manifest_name.val(),


					}
				}, 'data');
			},

			add: function () {
				layer.open({
					type: 2,
					title: '入库',
					content: 'jsp/wsy_addWarehouse.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['添加','关闭'],
					yes: function (index, layero) {
						var manifest_number = $(layero).find('iframe')[0].contentWindow.manifest_number.value;
						var manifest_name = $(layero).find('iframe')[0].contentWindow.manifest_name.value;
						var manifest_quantity = $(layero).find('iframe')[0].contentWindow.manifest_quantity.value;


						var ob = {
							manifest_number: manifest_number,
							manifest_name: manifest_name,
							manifest_quantity: manifest_quantity


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/addwarehouse.action",//提交的地址
							data: ob,
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {  //提交成功的方法， （）为返回的数据类型
								alert(tablebean)
								if (tablebean.msg == 1) {
									alert("添加成功！")
									table.reload('testReload');
									layer.close(index);
								} else {
									alert("添加失败！")
								}
							},

							error: function () {  //错误的方法
								alert("服务器正忙！");
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
			if (obj.event === 'detail') {
				//layer.msg('ID：' + data.id + ' 的查看操作');
				layer.open({
					type: 2,
					title: '入库',
					content: 'jsp/wsy_intoWarehouse.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['入库', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#manifest_number").val(data.manifest_number),
							body.find("#manifest_name").val(data.manifest_name)



					},
					yes: function (index, layero) {
						var manifest_number = $(layero).find('iframe')[0].contentWindow.manifest_number.value;
						var manifest_name = $(layero).find('iframe')[0].contentWindow.manifest_name.value;
						var manifest_quantity = $(layero).find('iframe')[0].contentWindow.manifest_quantity.value;
						var manifest_id = data.manifest_id;
						var ob = {
							manifest_id:manifest_id,
							manifest_number: manifest_number,
							manifest_name: manifest_name,
							manifest_quantity: manifest_quantity


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/updatewarehouse.action",//路径
							data: ob,//数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {//成功的方法 msg为返回数据

								if (tablebean.manifest_quantity ==1) {
									alert("入库成功");
									table.reload('testReload');
									layer.close(index);
								} else  {
									alert("出库失败")
								}
							},
							error: function () { //错误的方法
								alert("服务器正忙")
							}

						});
					}

				})



			} else if (obj.event === 'del') {
				var safe_event_id = data['safe_event_id'];
				layer.confirm('真的删除行么', function (index) {
					$.ajax({
						type: "POST",//提交方式
						url: "/Community/delSafeEvent.action?safe_event_id=" + safe_event_id,//提交的地址
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {  //提交成功的方法， （）为返回的数据类型
							if (msg.msg = "1") {
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

			} else if (obj.event === 'edit') {
				//var userid=data.userid;
				layer.open({
					type: 2,
					title: '出库',
					content: 'jsp/wsy_outWarehouse.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['出库', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#manifest_number").val(data.manifest_number),
							body.find("#manifest_name").val(data.manifest_name)


					},
					yes: function (index, layero) {
						var manifest_number = $(layero).find('iframe')[0].contentWindow.manifest_number.value;
						var manifest_name = $(layero).find('iframe')[0].contentWindow.manifest_name.value;
						var manifest_quantity = $(layero).find('iframe')[0].contentWindow.manifest_quantity.value;
						var manifest_id = data.manifest_id;
						var ob = {
							manifest_id:manifest_id,
							manifest_number: manifest_number,
							manifest_name: manifest_name,
							manifest_quantity: manifest_quantity


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/lesswarehouse.action",//路径
							data: ob,//数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {//成功的方法 msg为返回数据

								if (tablebean.manifest_quantity == 1) {
									alert("出库成功");
									table.reload('testReload');
									layer.close(index);
								} else  {
									alert("出库失败")
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
