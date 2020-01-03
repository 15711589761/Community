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


	<title>治安时间管理</title>
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

			<input class="layui-input" name="owsafe_event_titlener_name" id="safe_event_title" placeholder="请输入标题" autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload">查询</button>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<button data-type="add" class="layui-btn layui-btn-normal"  >添加事件</button>


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
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
	layui.use('table', function () {
		var table = layui.table;

		table.render({
			elem: '#demo'
			, url: '/Community/safeEvent.action'
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, page: true
			, limit: 5
			, limits: [5]
			, method: 'POST'
			, id: 'testReload'
			, cols: [[
				{field: 'safe_event_id',title: '编号', sort: true}
				, {field: 'safe_event_title', title: '标题'}
				, {field: 'safe_event_context', title: '内容', sort: true}
				, {field: 'safe_event_date', title: '发生时间'}
				, {field: 'safe_event_recorder', title: '记录人'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
				, {title: '操作', width: 178, align: 'center', toolbar: '#barDemo'}

			]]
		});

		var $ = layui.$, active = {
			reload: function () {
				var startDate = $('#startDate');
				var endDate = $('#endDate');
				var safe_event_title = $('#safe_event_title');


				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						startDate: startDate.val(),
						endDate: endDate.val(),
						safe_event_title: safe_event_title.val()

					}
				}, 'data');
			},

			add: function () {
				layer.open({
					type: 2,
					title: '添加治安事件',
					content: 'jsp/wsy_addStafeEvent.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['添加','关闭'],
					yes: function (index, layero) {
						var safe_event_title = $(layero).find('iframe')[0].contentWindow.safe_event_title.value;
						var safe_event_context = $(layero).find('iframe')[0].contentWindow.safe_event_context.value;
                        var safe_event_date = $(layero).find('iframe')[0].contentWindow.safe_event_date.value;
						var safe_event_recorder = $(layero).find('iframe')[0].contentWindow.safe_event_recorder.value;

					var ob = {
						safe_event_title: safe_event_title,
						safe_event_context: safe_event_context,
						safe_event_date: safe_event_date,
						safe_event_recorder: safe_event_recorder

						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/insertSafeEvent.action",//提交的地址
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
					title: '编辑',
					content: 'jsp/wsy_addStafeEvent.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['修改', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#safe_event_title").val(data.safe_event_title),
						body.find("#safe_event_context").val(data.safe_event_context)
						body.find("#safe_event_date").val(data.safe_event_date),
						body.find("#safe_event_recorder").val(data.safe_event_recorder)

					},
					yes: function (index, layero) {
						var safe_event_title = $(layero).find('iframe')[0].contentWindow.safe_event_title.value;
						var safe_event_context = $(layero).find('iframe')[0].contentWindow.safe_event_context.value;
						 var safe_event_date = $(layero).find('iframe')[0].contentWindow.safe_event_date.value;
						 var safe_event_recorder = $(layero).find('iframe')[0].contentWindow.safe_event_recorder.value;
						 var safe_event_id = data.safe_event_id;
						var ob = {
							 safe_event_id:safe_event_id,
							safe_event_title: safe_event_title,
							safe_event_context: safe_event_context,
							safe_event_date: safe_event_date,
							safe_event_recorder: safe_event_recorder

						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/updateSafeEvent.action",//路径
							data: ob,//数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {//成功的方法 msg为返回数据

								if (tablebean.msg ==1) {
									alert("修改成功");
									table.reload('testReload');
									layer.close(index);
								} else  {
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
