function sendData(workDate,actionId,workId) {
	console.log(workDate,actionId,workId);
	var ob =
		{
			workTime:workId,
			workDate:workDate,
			getId:actionId
		};
	var $ = layui.$;
	$.ajax({
		type: 'post',
		url: 'deleteSaferFull.action',
		data: ob,
		dataType: 'json',
		async: true,
		success : function(msg) {
			if (msg){
				alert("删除成功");
			} else {
				alert("删除失败");
			}
			location.reload();
			layer.close();

		},
		error : function() {
			alert('服务器出错');
		}

	});
}



//转换日期格式：yyyy-MM-dd
function fmtDate(inputTime) {
	var date = new Date(inputTime);
	var y = 1900 + date.getYear();
	var m = "0" + (date.getMonth() + 1);
	var d = "0" + date.getDate();
	return y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);
}

//上一周
function upWeek(id) {
	var $ = layui.$;
	var date = $("#mon").text();
	var d1 = new Date(date);
	d1.setDate(d1.getDate() - 2);
	date = fmtDate(d1);
	$("#appoint").attr("action",$("#appoint").attr("action")+"?getId="+id+"&&getDate="+date);
	$("#appoint").submit();
}

//下一周
function nextWeek(id) {
	var $ = layui.$;
	var date = $("#sun").text();
	var d1 = new Date(date);
	d1.setDate(d1.getDate() + 1);
	date = fmtDate(d1);
	$("#appoint").attr("action",$("#appoint").attr("action")+"?getId="+id+"&&getDate="+date);
	$("#appoint").submit();
}