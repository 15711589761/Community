<%--
  Created by IntelliJ IDEA.
  User: wsy
  Date: 2020/1/5
  Time: 19:51
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


	<title>支出记账</title>
</head>
<body>
<br/><br/><br/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<div class="layui-form">
	<div class="layui-form-item">
		&nbsp&nbsp&nbsp&nbsp
		<label class="layui-form-label">付款类型:</label>
		<div class="layui-inline">
			<input class="layui-input" name="payment_type" id="payment_type" placeholder="付款类型查找" autocomplete="off">
		</div>
		&nbsp&nbsp&nbsp&nbsp
		<div class="layui-inline">
			<label class="layui-form-label">付款时间:</label>
			<div class="layui-input-inline">
				<input type="month" class="layui-input" id="payment_time" placeholder="MM/yyyy">
			</div>

		</div>
		&nbsp&nbsp&nbsp&nbsp
		<div class="layui-inline">
			<input class="layui-input" name="payment_remarks" id="payment_remarks" placeholder="备注查询" autocomplete="off">
		</div>

		&nbsp&nbsp&nbsp&nbsp
		<button class="layui-btn" data-type="reload">查询</button>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<button data-type="add" class="layui-btn layui-btn-normal">添加付款</button>


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
			, url: '/Community/paymentTable.action'
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, page: true
			, limit: 5
			, limits: [5]
			, method: 'POST'
			, id: 'testReload'
			, cols: [[
				{field: 'payment_id',title: '编号', sort: true}
				, {field: 'payment_name', title: '付款人'}
				, {field: 'payment_money', title: '付款金额(元)', sort: true}
				, {field: 'payment_type', title: '付款类型'}
				, {field: 'payment_time', title: '付款时间'}
				, {field: 'payment_remarks', title: '备注'}
				, {title: '操作', width: 178, align: 'center', toolbar: '#barDemo'}

			]]
		});

		var $ = layui.$, active = {
			reload: function () {
				var payment_type = $('#payment_type');
				var payment_time = $('#payment_time');
				var payment_remarks = $('#payment_remarks');



				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						payment_type: payment_type.val(),
						payment_time: payment_time.val(),
						payment_remarks: payment_remarks.val()
					}
				}, 'data');
			},

			add: function () {
				layer.open({
					type: 2,
					title: '添加付款',
					content: 'jsp/wsy_add_payment.jsp',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['确定','关闭'],
					yes: function (index, layero) {
						var payment_name = $(layero).find('iframe')[0].contentWindow.payment_name.value;
						var payment_money = $(layero).find('iframe')[0].contentWindow.payment_money.value;
						var payment_type = $(layero).find('iframe')[0].contentWindow.payment_type.value;
                        var payment_remarks = $(layero).find('iframe')[0].contentWindow.payment_remarks.value;

						//对账
						var accountMoney = $(layero).find('iframe')[0].contentWindow.accountMoney.value;
						var accountType = $(layero).find('iframe')[0].contentWindow.accountType.value;
						var accountRemark = $(layero).find('iframe')[0].contentWindow.accountRemark.value;


						var ob = {
							payment_name: payment_name,
							payment_money: payment_money,
							payment_type: payment_type,
                            payment_remarks: payment_remarks,

							accountMoney: accountMoney,
							accountType: accountType,
							accountRemark: accountRemark

						};
						$.ajax({
							type: "POST",//提交方式
							url: "<%=path%>addPayment.action",//提交的地址
							data: ob,
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (tablebean) {  //提交成功的方法， （）为返回的数据类型

								if (tablebean.msg == 1) {
									alert("添加成功！");
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
					content: 'jsp/wsy_update_payment.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['修改', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#payment_name").val(data.payment_name),
							body.find("#payment_money").val(data.payment_money),
						body.find("#payment_type").val(data.payment_type),
							body.find("#payment_remarks").val(data.payment_remarks)

					}
				})



			} else if (obj.event === 'del') {
				var payment_id = data['payment_id'];
				layer.confirm('真的删除行么', function (index) {
					$.ajax({
						type: "POST",//提交方式
						url: "/Community/deletePayment.action?payment_id=" + payment_id,//提交的地址
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (tableBean) {  //提交成功的方法， （）为返回的数据类型
							if (tableBean.msg = "1") {
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
					content: 'jsp/wsy_update_payment.jsp',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['修改', '取消'],
					success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#payment_name").val(data.payment_name),
						body.find("#payment_money").val(data.payment_money),
						body.find("#payment_type").val(data.payment_type),
						body.find("#payment_remarks").val(data.payment_remarks)


					},
					yes: function (index, layero) {
						var payment_name = $(layero).find('iframe')[0].contentWindow.payment_name.value;
						var payment_money = $(layero).find('iframe')[0].contentWindow.payment_money.value;
						var payment_type = $(layero).find('iframe')[0].contentWindow.payment_type.value;
						var payment_remarks = $(layero).find('iframe')[0].contentWindow.payment_remarks.value;
						var payment_id = data.payment_id;
						var ob = {
							payment_id:payment_id,
							payment_name: payment_name,
							payment_money: payment_money,
							payment_type: payment_type,
							payment_remarks: payment_remarks


						};
						$.ajax({
							type: "POST",//提交方式
							url: "/Community/updatePayment.action",//路径
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
