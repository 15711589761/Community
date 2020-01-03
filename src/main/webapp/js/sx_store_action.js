function delAjax(obj)
{
	layui.$.ajax({
		type: "post",
		url: "delStore.action",
		data: obj,
		dataType: "json",
		async: true,
		success: function (msg) {
			if (msg){
				alert("商家数据被删除，如需恢复请联系管理员");
			} else {
				alert("信息操作失败");
			}
		},
		error: function () {
			console.log(obj);
			layer.msg("服务器连接失败");
		}
	})
}

function updateAjax(obj)
{
	layui.$.ajax({
		type: "post",
		url: "updateStore.action",
		data: obj,
		dataType: "json",
		async: true,
		success: function (msg) {
			if (msg){
				alert("商家数据已被修改");
			} else {
				alert("信息操作失败");
			}
		},
		error: function () {
			console.log(obj);
			layer.msg("服务器连接失败");
		}
	})
}