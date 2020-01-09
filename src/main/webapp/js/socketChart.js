//消息对象数组
var msgObjArr = new Array();

var websocket = null;

//判断当前浏览器是否支持WebSocket， springboot是项目名

//登入的用户名
var username;


if ('WebSocket' in window) {


	username = $("#talks").val();

	websocket = new WebSocket("ws://localhost:8081/Community/websocket/"+username);

} else {
	console.error("不支持WebSocket");
}

//连接发生错误的回调方法
websocket.onerror = function (e) {
	console.error("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
	// alert("开始链接onopen");
	//获取所有在线用户
	$.ajax({
		type: 'post',
		url:  "/Community/websocket/getOnlineList",
		contentType: 'application/json;charset=utf-8',
		dataType: 'json',
		data: {username:username},
		success: function (data) {
			if (data.length) {
				//列表
				for (var i = 0; i < data.length; i++) {
					var userName = data[i];
					$("#hz-group-body").append("<div class=\"hz-group-list\"><span><span class='hz-group-list-username' style='color: #0C0C0C; font-size: 15px'>" + userName + "</span><span id=\"" + userName + "-status\" style='color: #00FF00; font-size: 15px'>[在线]</span><div id=\"hz-badge-" + userName + "\" class='hz-badge'>0</div></div>");
				}
				//在线人数
				$("#onlineCount").text(data.length);
			}
		},
		error: function (xhr, status, error) {
			console.log("ajax错误！");
		}
	});
};

//接收到消息的回调方法
websocket.onmessage = function (event) {
	//将服务器上传输的数据进行转换成json的方法
	var messageJson = eval("(" + event.data + ")");

	// alert(messageJson.type);
	// alert("接收端message-------------："+messageJson.message);
	// alert("发送端srcUser-------------："+messageJson.srcUser);
	// alert("发送端tarUser-------------："+messageJson.tarUser);



	//普通消息(私聊)
	if (messageJson.type == "1") {
		//来源用户
		var srcUser = messageJson.srcUser;
		//目标用户
		var tarUser = messageJson.tarUser;
		//消息
		var message = messageJson.message;

		//最加聊天数据
		setMessageInnerHTML(srcUser.username,srcUser.username, message);
	}
	//普通消息(群聊)
	if (messageJson.type == "2"){
		//来源用户
		var srcUser = messageJson.srcUser;
		//目标用户
		var tarUser = messageJson.tarUser;
		//消息
		var message = messageJson.message;

		//最加聊天数据
		setMessageInnerHTML(username,tarUser.username, message);
	}

	//对方不在线
	if (messageJson.type == "0"){
		//消息
		var message = messageJson.message;

		$("#hz-message-body").append(
			"<div class=\"hz-message-list\" style='text-align: center;'>" +
			"<div class=\"hz-message-list-text\">" +
			"<span>" + message + "</span>" +
			"</div>" +
			"</div>");
	}

	//在线人数
	if (messageJson.type == "onlineCount") {
		//取出username
		var onlineCount = messageJson.onlineCount;
		var userName = messageJson.username;
		var oldOnlineCount = $("#onlineCount").text();

		//新旧在线人数对比
		if (oldOnlineCount < onlineCount) {
			if($("#" + userName + "-status").length > 0){
				$("#" + userName + "-status").text("[在线]");
				$("#" + userName + "-status").css("color","#00FF00");
			}else{
				$("#hz-group-body").append("<div class=\"hz-group-list\"><span><img src='../image/header.png' alt='' style='width: 20px;height: 20px'> <span class='hz-group-list-username' style='color: #0C0C0C; font-size: 15px'>" + userName + "</span><span id=\"" + userName + "-status\" style='color: #00FF00; font-size: 15px'>[在线]</span><div id=\"hz-badge-" + userName + "\" class='hz-badge'>0</div></div>");
			}
		} else {
			//有人下线
			$("#" + userName + "-status").text("[离线]");
			$("#" + userName + "-status").css("color",'red');
		}
		$("#onlineCount").text(onlineCount);
	}
};

//连接关闭的回调方法
websocket.onclose = function () {
	//alert("WebSocket连接关闭");
};

//将消息显示在对应聊天窗口    对于接收消息来说这里的toUserName就是来源用户，对于发送来说则相反
function setMessageInnerHTML(srcUserName,msgUserName, message) {
	//判断
	var childrens = $("#hz-group-body").children(".hz-group-list");
	var isExist = false;
	for (var i = 0; i < childrens.length; i++) {
		var text = $(childrens[i]).find(".hz-group-list-username").text();
		//判断来源用户是否在列表中
		if (text == srcUserName) {
			isExist = true;
			break;
		}
	}
	if (!isExist) {
		//追加聊天对象
		msgObjArr.push({
			toUserName: srcUserName,
			message: [{username: msgUserName, message: message, date: NowTime()}]//封装数据
		});
		$("#hz-group-body").append("<div class=\"hz-group-list\"><span><img src='../image/header.png' alt='' style='width: 20px;height: 20px'> <span class='hz-group-list-username' style='color: #0C0C0C; font-size: 15px'>" + userName +"</span><span id=\"" + userName + "-status\" style='color: #00FF00; font-size: 15px'>[在线]</span><div id=\"hz-badge-" + userName + "\" class='hz-badge'>0</div></div>");
	} else {
		//取出对象
		var isExist = false;
		for (var i = 0; i < msgObjArr.length; i++) {
			var obj = msgObjArr[i];
			if (obj.toUserName == srcUserName) {
				//保存最新数据
				obj.message.push({username: msgUserName, message: message, date: NowTime()});
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			//追加聊天对象
			msgObjArr.push({
				toUserName: srcUserName,
				message: [{username: msgUserName, message: message, date: NowTime()}]//封装数据
			});
		}
	}

	// 对于接收消息来说这里的toUserName就是来源用户，对于发送来说则相反
	var username = $("#toUserName").text();

	//刚好打开的是对应的聊天页面
	if (srcUserName == username) {
		$("#hz-message-body").append(
			"<div class=\"hz-message-list\">" +
			"<p class='hz-message-list-username'>"+msgUserName+'<br>'+NowTime()+"</p>" +
			"<div class=\"hz-message-list-text left\">" +
			"<span>" + message + "</span>" +
			"</div>" +
			"<div style=\" clear: both; \"></div>" +
			"</div>");
	} else {
		//小圆点++
		var conut = $("#hz-badge-" + srcUserName).text();
		$("#hz-badge-" + srcUserName).text(parseInt(conut) + 1);
		$("#hz-badge-" + srcUserName).css("opacity", "1");
	}
}

//发送消息
function send() {
	//消息
	var message = $("#hz-message-input").html();
	//目标用户名
	var tarUserName = $("#toUserName").text();
	//登录用户名
	var srcUserName = $("#talks").val();

	// alert("发送端message-------------："+message);
	// alert("发送端tarUserName-------------："+tarUserName);
	// alert("发送端srcUserName-------------："+srcUserName);


	//发送消息到websocket
	websocket.send(JSON.stringify({
		"type": "1",
		"tarUser": {"username": tarUserName},
		"srcUser": {"username": srcUserName},
		"message": message
	}));
	//信息框显示我方信息
	$("#hz-message-body").append(
		"<div class=\"hz-message-list\">" +
		"<p class='hz-message-list-usernamer'>我："+NowTime()+"</p>"+
		"<div class=\"hz-message-list-text right\">" +
		//显示发送人和发送时间
		"<span>" + message + "</span>" +
		"</div>" +
		"</div>");
	//输入框的内容制空
	$("#hz-message-input").html("");
	//取出对象
	if (msgObjArr.length > 0) {
		var isExist = false;
		for (var i = 0; i < msgObjArr.length; i++) {
			var obj = msgObjArr[i];
			if (obj.toUserName == tarUserName) {
				//保存最新数据
				obj.message.push({username: srcUserName, message: message, date: NowTime()});
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			//追加聊天对象
			msgObjArr.push({
				toUserName: tarUserName,
				message: [{username: srcUserName, message: message, date: NowTime()}]//封装数据[{username:huanzi,message:"你好，我是欢子！",date:2018-04-29 22:48:00}]
			});
		}
	} else {
		//追加聊天对象
		msgObjArr.push({
			toUserName: tarUserName,
			message: [{username: srcUserName, message: message, date: NowTime()}]//封装数据[{username:huanzi,message:"你好，我是欢子！",date:2018-04-29 22:48:00}]
		});
	}
}

//监听点击用户
$("body").on("click", ".hz-group-list", function () {
	$(".hz-group-list").css("background-color", "");
	$(this).css("background-color", "whitesmoke");

	$("#toUserName").text($(this).find(".hz-group-list-username").text());
	var talkTitle=$("#toUserName").html();
	layer.open({
		title:'正在与'+talkTitle+'聊天...'
		,type: 1,
		content: $('#hz-message')
		,shade: 0
		,maxmin:true
		// , closeBtn :0
		,area:['500px','550px']
		,offset: [ //为了演示，随机坐标
			Math.random()*($(window).height()-300)
			,Math.random()*($(window).width()-390)
		]
		,resize:false
		,zIndex: layer.zIndex //重点1
		// ,success: function(layero){
		// 	layer.setTop(layero); //重点2
		// }


		,min: function(layero){
			setTimeout(function(){
				layero.css({
					left:'auto'
					,right: 0
					,bottom: 0
				})},0);
		}
		,max:function (layerro) {
			$(window).resize(function () {
				$(".layui-layer-maxmin").parents(".layui-layer").width($(window).width()).height($(window).height());
			});
		}

	});



	//清空旧数据，从对象中取出并追加
	$("#hz-message-body").empty();
	$("#hz-badge-" + $("#toUserName").text()).text("0");
	$("#hz-badge-" + $("#toUserName").text()).css("opacity", "0");
	if (msgObjArr.length > 0) {
		for (var i = 0; i < msgObjArr.length; i++) {
			var obj = msgObjArr[i];
			if (obj.toUserName == $("#toUserName").text()) {
				//追加数据
				var messageArr = obj.message;
				if (messageArr.length > 0) {
					for (var j = 0; j < messageArr.length; j++) {
						var msgObj = messageArr[j];
						var leftOrRight = "right";
						var message = msgObj.message;
						var msgUserName = msgObj.username;
						var toUserName = $("#toUserName").text();

						//当聊天窗口与msgUserName的人相同，文字在左边（对方/其他人），否则在右边（自己）
						if (msgUserName == toUserName) {
							leftOrRight = "left";
						}

						//但是如果点击的是自己，群聊的逻辑就不太一样了
						if (username == toUserName && msgUserName != toUserName) {
							leftOrRight = "left";
						}

						if (username == toUserName && msgUserName == toUserName) {
							leftOrRight = "right";
						}

						var magUserName = leftOrRight == "left" ? "<p class='hz-message-list-username'>"+msgUserName+"：</p>" : "";

						$("#hz-message-body").append(
							"<div class=\"hz-message-list\">" +
							"<p class='hz-message-list-username'>"+msgUserName+'<br>'+NowTime()+"</p>" +
							// magUserName+
							"<div class=\"hz-message-list-text " + leftOrRight + "\">" +
							"<span>" + message + "</span>" +
							"</div>" +
							"<div style=\" clear: both; \"></div>" +
							"</div>");
					}
				}
				break;
			}
		}
	}
});

//获取当前时间
function NowTime() {
	var time = new Date();
	var year = time.getFullYear();//获取年
	var month = time.getMonth() + 1;//或者月
	var day = time.getDate();//或者天
	var hour = time.getHours();//获取小时
	var minu = time.getMinutes();//获取分钟
	var second = time.getSeconds();//或者秒
	var data = year + "-";
	if (month < 10) {
		data += "0";
	}
	data += month + "-";
	if (day < 10) {
		data += "0"
	}
	data += day + " ";
	if (hour < 10) {
		data += "0"
	}
	data += hour + ":";
	if (minu < 10) {
		data += "0"
	}
	data += minu + ":";
	if (second < 10) {
		data += "0"
	}
	data += second;
	return data;
}