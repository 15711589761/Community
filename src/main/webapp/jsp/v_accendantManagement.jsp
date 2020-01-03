<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<title>维修人员管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<div class="layui-form" style="text-align: center;">
	<br/>
	<br/>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">性别：</label>
			<div class="layui-input-inline">
				<select name="accendantSex" id="accendantSex">
					<option id="" value="">性别选择</option>
					<option id="1" value="男">男</option>
					<option id="0" value="女">女</option>
				</select>
			</div>
			<label class="layui-form-label">姓名：</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="userName" name="userName" placeholder="请输入名称">
			</div>
		</div>
		<button type="button" data-type="reload" class="layui-btn layui-icon layui-icon-search" id="search"></button>
		<button title="新增维修人员" class="layui-btn layui-btn-danger layui-icon layui-icon-add-1" data-type="reload" id="addNew"></button>

	</div>
</div>
<br/>
<table class="layui-hide" id="accendantData" lay-filter="accendantData"></table>
<script>
	layui.use(['table', 'form'], function () {
		var table = layui.table
			, $ = layui.$
			, form = layui.form;
		table.render({
			elem: '#accendantData'
			, url: <%=path%>+'/getAccendantData.action'
			, page: true
			, limit: 10
			, limits: [5, 10, 15, 20]
			, id: 'accendantData'
			, title: '工号管理表'
			, cols: [[
				{field: 'accendantId', title: 'id', sort: true, align: 'center'}
				, {field: 'accendantName', title: '姓名', align: 'center'}
				, {
					field: 'accendantSex', title: '性别', align: 'center', templet: function (d) {
						if (d.accendantSex === 1) {
							return '<span style="color: #2e2e2e;">男</span>';
						} else {
							return '<span style="color: #d720bb;">女</span>';
						}
					}
				}
				, {field: 'accendantAge', title: '年龄', align: 'center'}
				, {field: 'accendantTel', title: '联系电话', align: 'center'}
				, {fixed: 'right', width: '20%', title: '操作', align: 'center', toolbar: '#barDemo'}

			]]
		});

		$('#search').on('click', function () {

			table.reload('accendantData', {
				method: 'post',
				where: {
					'userSex': $("#accendantSex option:checked").attr('id'),
					'userName': $("#userName").val()
				}, page: {
					curr: 1
				}
			})
		});

		$('#addNew').on('click', function () {

			layer.open({
				type: 2
				, title: '新增工号'
				, content: <%=path%>+'jsp/v_accendantAdd.jsp'
				, area: ['350px', '350px']
				, success: function (layero, index) {

				}

			});
		});

		//监听事件
		table.on('tool(accendantData)', function (obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				layer.open({
					type: 2
					, title: '维修人员信息修改'
					, content: <%=path%>+'jsp/v_accendantInfo.jsp'
					, area: ['350px', '350px']
					, success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#theId").val(data.accendantId);
						body.find("#userName").val(data.accendantName);
						body.find("#userAge").val(data.accendantAge);
						body.find("#userTel").val(data.accendantTel);
						body.find(":radio[name='sex'][value='" + data.accendantSex + "']").attr("checked", true);
					}
				});
			} else if (obj.event === 'del') {
				layer.confirm('真的执行此操作吗?', function (index) {
					var theId = data.accendantId;
					layer.msg("当前工号id为：" + theId);
					var ob = {
						accendantId: theId,
						operator: null,
						operatorId: null
					};
					layui.$.ajax({
						type: "post",
						url: <%=path%>+"delAccendant.action",
						data: ob,
						dataType: "json",
						async: true,
						success: function (msg) {
							if (msg.resultMsg === "done") {
								layer.msg("删除成功！");
								table.reload("accendantData")

							} else {
								layer.msg("删除失败!请重新操作");
							}


						},
						error: function () {
							layer.msg("系统故障，请联系维护人员");
						}
					})
				});
			}

		});


	});

</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

</body>
</html>
