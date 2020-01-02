<%--
  Created by IntelliJ IDEA.
  User: 魏书源
  Date: 2019/12/18
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


	<title>参数查看</title>
</head>
<body>
<br/><br/><br/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<div class="layui-form" style="text-align: center">
	<div class="layui-form-item">
		<div class="layui-inline">

			<input class="layui-input" name="parameter_name" id="parameter_name" placeholder="请输入参数名"
			       autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload">查询</button>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<button data-type="add" class="layui-btn layui-btn-normal">增加参数</button>


	</div>
</div>


<table id="demo" lay-filter="test"></table>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
	layui.use('table', function () {
		var table = layui.table;

		table.render({
			elem: '#demo'
			, url: '/Community/parmeterlist.action'
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, page: true
			, limit: 5
			, limits: [5]
			, method: 'POST'
			, id: 'testReload'
			, cols: [[
				{field: 'parameter_id', title: '编号', sort: true}
				, {field: 'parameter_name', title: '参数名'}
				, {field: 'parameter_remark', title: '参数注释', sort: true}
				, {title: '操作', width: 178, align: 'center', toolbar: '#barDemo'}

			]]
		});

		var $ = layui.$, active = {
			reload: function () {

				var parameter_name = $('#parameter_name');


				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {

						"searchName": parameter_name.val()

					}
				}, 'data');
			},

			add: function () {
				layer.open({
					type: 2,
					title: '添加参数',
					content: 'jsp/wsy_addParmeter.jsp',
					maxmin: true,
					area: ['500px', '300px'],
					btn: ['增加', '关闭'],
					yes: function (index, layero) {
						var parameter_name = $(layero).find('iframe')[0].contentWindow.parameter_name.value;
						var parameter_remark = $(layero).find('iframe')[0].contentWindow.parameter_remark.value;


						var ob = {
							parameter_name: parameter_name,
							parameter_remark: parameter_remark


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/addParmeter.action",//提交的地址
							data: ob,
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {  //提交成功的方法， （）为返回的数据类型
								alert(tablebean)
								if (tablebean.msg == 1) {
									table.reload('testReload');
									alert("添加成功！")
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
					title: '编辑',
					content: 'jsp/wsy_lookSafeEvent.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['修改', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#safe_event_title").val(data.safe_event_title),
							body.find("#safe_event_context").val(data.safe_event_context)
						body.find("#safe_event_date").val(data.safe_event_date),
							body.find("#safe_event_recorder").val(data.safe_event_recorder)

					}
				})


			} else if (obj.event === 'del') {
				var parameter_id = data['parameter_id'];
				layer.confirm('真的删除行么', function (index) {
					$.ajax({
						type: "POST",//提交方式
						url: "/Community/delParmeter.action?parameter_id=" + parameter_id,//提交的地址
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (tablebean) {  //提交成功的方法， （）为返回的数据类型
							if (tablebean.msg = "1") {
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
					title: '编辑',
					content: 'jsp/wsy_updateParmeter.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['确认修改', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#parameter_name").val(data.parameter_name),
							body.find("#parameter_remark").val(data.parameter_remark)


					},
					yes: function (index, layero) {
						var parameter_name = $(layero).find('iframe')[0].contentWindow.parameter_name.value;
						var parameter_remark = $(layero).find('iframe')[0].contentWindow.parameter_remark.value;

						var parameter_id = data.parameter_id;
						var ob = {
							parameter_id: parameter_id,
							parameter_name: parameter_name,
							parameter_remark: parameter_remark


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/updateParameter.action",//路径
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
