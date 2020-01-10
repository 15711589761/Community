<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/11/17
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>智慧社区管理平台</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<link href="css/css.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href=<%=path+"css/socketChart.css"%>>
	<script type="text/javascript" src=<%=path+"js/jquery-1.7.1.min.js"%>></script>
	<script src=<%=path + "layui/layui.js"%>></script>
	<script src="<%=path + "js/md5.js"%>"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="livechat-girl animated"> <img class="girl" src="images/en_3.png">
			<div class="livechat-hint rd-notice-tooltip rd-notice-type-success rd-notice-position-left single-line show_hint">
				<div class="rd-notice-content">嘿，我来帮您！</div>
			</div>
			<div class="animated-circles">
				<div class="circle c-1"></div>
				<div class="circle c-2"></div>
				<div class="circle c-3"></div>
			</div>
		</div>
		<div class="layui-logo">物业管理平台</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					${requestScope.staff.staffName}
					<input type="hidden" id="staff_in_view" value="${requestScope.staff}"/>
				</a>
				<dl class="layui-nav-child">
					<dd><a href="javascript:;" onclick="myMessage()">基本资料</a></dd>
					<dd><a href="javascript:;" onclick="upPass()">修改密码</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href=<%=path + "toBackstageLogin.view"%>>退出</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree" lay-filter="test">
				<c:if test="${requestScope.menu!=null}">
					<c:forEach items="${requestScope.menu}" var="x" step="1" begin="0">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="">${x.key}</a>
							<dl class="layui-nav-child">
								<c:forEach items="${x.value}" var="y" begin="0" step="1">
									<dd><a href="<%=path%>${y.toUrl}" target="page-view">${y.menuName}</a></dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<iframe src="" name="page-view" frameborder="0" width="100%" height="100%"></iframe>
	</div>

	<%-- -----------------------------------------------------------%>
	<%-- ---------------------在线人数显示弹窗--------------------------------------%>
	<div id="mytalk" style="display: none">
		<div id="hz-group">
			<%--		<input type="hidden" id="talks" value="${sessionScope.pName}">--%>
			<input type="hidden" id="talks" value="${requestScope.staff.staffName}">
			<%--		<div style="color: #0C0C0C; font-size:20px">登录用户：--%>
			<%--			<span id="talks">${sessionScope.pName}</span></div>--%>
			<%--		<br/>--%>
			<div style="color: #0C0C0C; font-size:15px">当前在线人数:<span id="onlineCount">0</span></div>
			<!-- 主体 -->
			<div id="hz-group-body">

			</div>
		</div>
	</div>
	<%-- -----------------------------------------------------------%>
	<%-- -----------------------------------------------------------%>

	<%-- -----------------------------------------------------------%>
	<%-- ----------------------聊天窗口-------------------------------------%>
	<div id="hz-message" style="display: none">
		<!-- 头部 -->
		<div style="display: none;">正在与<span id="toUserName"></span>聊天</div>
		<hr style="margin: 0px;"/>
		<!-- 主体 -->
		<div id="hz-message-body">
		</div>
		<!-- 功能条 -->
		<div id="">
			<button>表情</button>
			<button>图片</button>
			<button id="videoBut">视频</button>
			<button onclick="send()" style="float: right;">发送</button>
		</div>
		<!-- 输入框 -->
		<div contenteditable="true" id="hz-message-input">
		</div>
	</div>
	<%-- -----------------------------------------------------------%>
	<%-- -----------------------------------------------------------%>

	<div class="layui-footer">
	</div>
</div>

<script src=<%=path + "layui/layui.js"%>></script>
<script>

	$(function () {
		setInterval(function(){
			if($(".animated-circles").hasClass("animated")){
				$(".animated-circles").removeClass("animated");
			}else{
				$(".animated-circles").addClass('animated');
			}
		},3000);
		var wait = setInterval(function(){
			$(".livechat-hint").removeClass("show_hint").addClass("hide_hint");
			clearInterval(wait);
		},4500);
		$(".livechat-girl").hover(function(){
			clearInterval(wait);
			$(".livechat-hint").removeClass("hide_hint").addClass("show_hint");
		},function(){
			$(".livechat-hint").removeClass("show_hint").addClass("hide_hint");
		}).click(function(){
			//当前人员在线显示弹窗
			layui.use('layer',function () {
				var layer = layui.layer;
				var loginName = $("#talks").val();
				layer.open({
					title: loginName
					, type: 1
					, content: $('#mytalk')
					, shade: 0
					, maxmin: true
					, offset: 'r'
					, min: function (layero) {
						setTimeout(function () {
							layero.css({
								left: 'auto'
								, right: 0
								, bottom: 0
							})
						}, 0);
					}
					, max: function (layerro) {
						$(window).resize(function () {
							$(".layui-layer-maxmin").parents(".layui-layer").width($(window).width()).height($(window).height());
						});
					}
				});
			});

		});
	});


	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;
	});




	function upPass() {
		layui.use('layer',function () {



			var $ = layui.jquery;
			layer.open({
				type:2,
				content:"<%=path%>toUpdatePassWord.view",
				offset: ['10%', '40%'],
				area: ['300px','350px'],
				title: '修改密码',
				btn: ['修改', '关闭'],
				btn1: function (index,layero) {
					var oldPass = $(layero).find('iframe')[0].contentWindow.old_password.value;
					var newPass = $(layero).find('iframe')[0].contentWindow.new_password.value;
					var repeatPass = $(layero).find('iframe')[0].contentWindow.repeat_password.value;
					if (oldPass.length>0&&newPass.length>0&&repeatPass.length>0)
					{
						if (newPass === repeatPass)
						{
							oldPass = md5(oldPass);
							newPass = md5(newPass);
							var upPass={newPass:newPass,oldPass:oldPass};
							$.ajax({
								url:"<%=path%>updateBackstagePassWord.action", //请求的url地址
								dataType:"json", //返回格式为json
								async:true,//请求是否异步，默认为异步，这也是ajax重要特性
								data:upPass, //参数值
								type:"post", //请求方式
								success:function(msg){
									layer.closeAll();
									layer.msg(msg.msg);
									table.reload('test');
								},
								error:function () {
									alert("系统忙请重试");
									table.reload('test');
								}
							})
						} else {
							alert('两次输入密码不同');
						}
					} else {
						alert("请补全修改信息");
					}

				},
				btn2: function () {
					layer.closeAll();
				}
			})
		});
	}

	function myMessage() {
		layui.use('layer',function () {
			layer.open({
				type:2,
				content:"<%=path%>toLookPersonMessage.view",
				offset: ['10%', '40%'],
				area: ['300px','500px'],
				title: '基本信息',
				btn: ['关闭'],
				btn1: function () {
					layer.closeAll();
				}
			})
		});
	}


</script>
<script src=<%=path + "/js/socketChart.js"%>></script>
</body>
</html>
