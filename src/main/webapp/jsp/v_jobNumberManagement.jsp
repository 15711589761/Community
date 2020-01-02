<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<title>工号管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path+"layui/layui.js"%>></script>
	<script src=<%=path+"js/venTool.js"%>></script>
	<style>
		.layui-table-cell{
			height:auto !important;
		}
	</style>
</head>
<body>
<div class="layui-form" style="text-align: center;">
	<br/>
	<br/>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">工号状态：</label>
				<div class="layui-input-inline">
					<select name="status" id="status" >
						<option id="" value="">工号状态选择</option>
						<option id="0" value="未分配" >未分配</option>
						<option id="1" value="已分配" >已分配</option>
					</select>
				</div>
			</div>
			<button title="查询" type="button" data-type="reload" class="layui-btn layui-btn-normal layui-icon layui-icon-search" id="search"></button>
			<button title="批量增加工号" class="layui-btn layui-btn-danger layui-icon layui-icon-add-1" data-type="reload" id="addNew"></button>
		</div>
</div>

<br/>
<table class="layui-hide" id="jobNumberData" lay-filter="jobNumberData"></table>

<script>
	layui.use(['table','form'],function(){
		var table=layui.table
			,$=layui.$
			,form=layui.form;

		table.render({
			elem: '#jobNumberData'
			,url:<%=path%>+'/getJobNumberData.action'
			,page:true
			,limit:10
			,limits:[5,10,15,20]
			,id:'jobNumberData'
			,title: '工号管理表'
			,cols: [[
				{field:'numberId',title: 'id', sort: true,align:'center'}
				,{field:'jobNumber', title: '工号',align:'center'}
				,{field:'status', title: '工号状态',align:'center',templet:'#statusTpl'}
				,{field:'attributable',title: '使用者',align:'center',templet:function (d) {
						if (d.attributable===null) {
							return '<button title="工号分配" class="layui-btn layui-btn-sm layui-icon layui-icon-set layui-btn-primary" lay-event="set"></button>' +
								'<button title="删除工号" class="layui-btn layui-btn-sm layui-icon layui-icon-delete layui-btn-danger" lay-event="del"></button>'
						}
						else {
							return d.attributable;
						}
					}}
			]]
		});


		$('#search').on('click', function () {

			table.reload('jobNumberData', {
				method: 'post',
				where: {
					'status':$("#status option:checked").attr('id')
				}, page: {
					curr: 1
				}
			})
		});


		$('#addNew').on('click', function () {

			var ob = {
				methodName:"getLastJobNumber.action"
			};
			layui.$.ajax({
				type: "post",
				url: <%=path%>+ob.methodName,
				data: ob,
				dataType: "json",
				async: true,
				success: function (msg) {
					if (msg.identify === "getLastJobNumber"){
						if (msg.resultMsg !== null)
						{
							layer.open({
								type: 2
								,title: '新增工号'
								,content: <%=path%>+'jsp/v_jobNumberAdd.jsp'
								,area: ['330px', '230px']
								, success : function(layero, index) {
									var body = layer.getChildFrame('body', index);
									body.find('#lastJobNumber').val(msg.resultMsg);
								}
							});
						}
						else {
							layer.msg("数据异常无法执行此操作，请联系维护人员");
						}
					}
				},
				error: function () {
					layer.msg("系统故障，请联系维护人员");
				}
			});



		});

		//监听事件
		table.on('ventool(jobNumberData)', function(obj){
			var data = obj.data;
			if(obj.event === 'set'){
				var ob = {
					methodName:"checkStaffJobNumber.action"
				};
				layui.$.ajax({
					type: "post",
					url: <%=path%>+ob.methodName,
					data: ob,
					dataType: "json",
					async: true,
					success: function (msg) {
						if (msg.identify === "checkStaffJobNumber"){
							if (msg.resultMsg === "1")
							{
								layer.open({
									type: 2
									,title: '工号分配'
									,content: <%=path%>+'toSetJobNumber'
									,area: ['350px', '300px']
									, success : function(layero, index) {
										var body = layer.getChildFrame('body', index);
										body.find('#nowJobNumber').val(data.jobNumber);
									}
								});
							} else if(msg.resultMsg === "0"){
								layer.alert("目前暂无可分配工号的职员！");
							}
						}
					},
					error: function () {
						layer.msg("系统故障，请联系维护人员");
					}
				})
			}
			else if(obj.event === 'del'){
				layer.confirm('真的执行此操作吗?', function(index){
					var theId = data.numberId;
					layer.msg("当前工号id为："+theId);
					var ob = {
						methodName:"delJobNumber.action",
						targetId:theId,
						operator:null,
						operatorId:null
					};
					doAjax(ob);
				});
			}

		});


		//doAjax
		function doAjax(ob) {
			layui.$.ajax({
				type: "post",
				url: <%=path%>+ob.methodName,
				data: ob,
				dataType: "json",
				async: true,
				success: function (msg) {
					if (msg.identify === "delJobNumber"){
						if (msg.resultMsg === "success")
						{
							layer.msg("删除成功！");
							table.reload("jobNumberData")

						} else {
							layer.msg("删除失败!请重新操作");
						}
					}

					else if (msg.identify === "checkStaffJobNumber"){
						if (msg.resultMsg === "1")
						{
							layer.open({
								type: 2
								,title: '工号分配'
								,content: <%=path%>+'toSetJobNumber'
								,area: ['350px', '300px']
								, success : function(layero, index) {
									var body = layer.getChildFrame('body', index);
									body.find('#nowJobNumber').val(data.jobNumber);
								}
							});
						} else if(msg.resultMsg === "0"){
							layer.alert("无未分配工号的职员！");
						}
					}
				},
				error: function () {
					layer.msg("系统故障，请联系维护人员");
				}
			})
		}

	});

</script>

<script type="text/html" id="statusTpl">

	{{#  if(d.status === 1){ }}
	<span style="color: #2e2e2e;">已分配</span>
	{{#  } else{ }}
	<span style="color: #f37719;">未分配</span>
	{{#  } }}

</script>


</body>
</html>
