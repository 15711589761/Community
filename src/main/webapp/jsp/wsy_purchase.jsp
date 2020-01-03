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
				<input type="date" class="layui-input" id="startDate" placeholder="dd/MM/yyyy">
			</div>
		</div>
		~
		<div class="layui-inline">
			<label class="layui-form-label">结束日期</label>
			<div class="layui-input-inline">
				<input type="date" class="layui-input" id="endDate" placeholder="dd/MM/yyyy">
			</div>

		</div>
		&nbsp&nbsp&nbsp&nbsp
		<div class="layui-inline">
           <input class="layui-input" name="purchase_name" id="purchase_name" placeholder="请输入商品名" autocomplete="off">
		</div>
		&nbsp&nbsp&nbsp&nbsp
		<div class="layui-inline">
           <input class="layui-input" name="purchase_model" id="purchase_model" placeholder="请输入商品型号" autocomplete="off">
		</div>
		&nbsp&nbsp&nbsp&nbsp
		<button class="layui-btn" data-type="reload">查询</button>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<button data-type="add" class="layui-btn layui-btn-normal"  >申请</button>


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
			, url: '/Community/selectPurchase.action'
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, page: true
			, limit: 5
			, limits: [5]
			, method: 'POST'
			, id: 'testReload'
			, cols: [[
				{field: 'purchase_id',title: '编号', sort: true}
				, {field: 'purchase_name', title: '商品名'}
				, {field: 'purchase_model', title: '型号', sort: true}
				, {field: 'purchase_quantity', title: '数量'}
				, {field: 'purchase_price', title: '价格'}
				, {field: 'applicant', title: '申请人'}
				, {field: 'applicant_time', title: '申请时间'}
				, {field: 'reviewer', title: '审核人'}
				, {field: 'reviewer_status', title: '审核时间'}
				, {title: '操作', width: 178, align: 'center', toolbar: '#barDemo'}

			]]
		});

		var $ = layui.$, active = {
			reload: function () {
				var startDate = $('#startDate');
				var endDate = $('#endDate');
				var purchase_name = $('#purchase_name');
				var purchase_model = $('#purchase_model');


				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						startDate: startDate.val(),
						endDate: endDate.val(),
						purchase_name: purchase_name.val(),
						purchase_model: purchase_model.val()

					}
				}, 'data');
			},

			add: function () {
				layer.open({
					type: 2,
					title: '申请',
					content: 'jsp/wsy_addpurchase.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['提交申请','关闭'],
					yes: function (index, layero) {
						var purchase_name = $(layero).find('iframe')[0].contentWindow.purchase_name.value;
						var purchase_model = $(layero).find('iframe')[0].contentWindow.purchase_model.value;
						var purchase_quantity = $(layero).find('iframe')[0].contentWindow.purchase_quantity.value;
						var purchase_price = $(layero).find('iframe')[0].contentWindow.purchase_price.value;
						var applicant = $(layero).find('iframe')[0].contentWindow.applicant.value;
						var applicant_time = $(layero).find('iframe')[0].contentWindow.applicant_time.value;

						var ob = {
							purchase_name: purchase_name,
							purchase_model: purchase_model,
							purchase_quantity: purchase_quantity,
							purchase_price: purchase_price,
							applicant: applicant,
							applicant_time: applicant_time

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
					title: '查看',
					content: 'jsp/wsy_lookpurchase.jsp',
					maxmin: true,
					area: ['500px', '550px'],
					btn: ['关闭'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#purchase_name").val(data.purchase_name),
							body.find("#purchase_model").val(data.purchase_model),
							body.find("#purchase_quantity").val(data.purchase_quantity),
							body.find("#purchase_price").val(data.purchase_price),
							body.find("#applicant").val(data.applicant),
							body.find("#applicant_time").val(data.applicant_time),
							body.find("#reviewer").val(data.reviewer),
							body.find("#reviewer_status").val(data.reviewer_status)

					}
				})



			} else if (obj.event === 'del') {
				var purchase_id = data['purchase_id'];
				layer.confirm('真的删除行么', function (index) {
					$.ajax({
						type: "POST",//提交方式
						url: "/Community/delPurchase.action?purchase_id=" + purchase_id,//提交的地址
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
					content: 'jsp/wsy_updatepurchase.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['修改', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#purchase_name").val(data.purchase_name),
						body.find("#purchase_model").val(data.purchase_model),
						body.find("#purchase_quantity").val(data.purchase_quantity),
						body.find("#purchase_price").val(data.purchase_price),
						body.find("#applicant").val(data.applicant),
							body.find("#applicant_time").val(data.applicant_time)


					},
					yes: function (index, layero) {
						var purchase_name = $(layero).find('iframe')[0].contentWindow.purchase_name.value;
						var purchase_model = $(layero).find('iframe')[0].contentWindow.purchase_model.value;
						var purchase_quantity = $(layero).find('iframe')[0].contentWindow.purchase_quantity.value;
						var purchase_price = $(layero).find('iframe')[0].contentWindow.purchase_price.value;
						var applicant = $(layero).find('iframe')[0].contentWindow.applicant.value;
						var applicant_time = $(layero).find('iframe')[0].contentWindow.applicant_time.value;
						var purchase_id = data.purchase_id;
						var ob = {
							purchase_id:purchase_id,
							purchase_name: purchase_name,
							purchase_model: purchase_model,
							purchase_quantity: purchase_quantity,
							purchase_price: purchase_price,
							applicant: applicant,
							applicant_time: applicant_time


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/updatePurchase.action",//路径
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
