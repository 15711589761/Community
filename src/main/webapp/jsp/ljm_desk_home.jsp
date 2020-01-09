<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/19
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>智慧社区服务平台</title>
	<link rel="stylesheet"  type="text/css" href=<%=path + "css/deskbase.css"%>>
	<link rel="stylesheet" type="text/css" href=<%=path + "css/main.css"%>  />
	<link href="css/css.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href=<%=path+"css/socketChart.css"%>>
	<script type="text/javascript" src=<%=path + "js/jquery-1.8.3.min.js"%>></script>
	<script type="text/javascript" src=<%=path+"js/jquery-1.7.1.min.js"%>></script>
	<link rel="stylesheet" type="text/css" href=<%=path + "css/jquery.slideBox.css"%>  />
	<script type="text/javascript" src=<%=path + "js/jquery.slideBox.min.js"%>></script>
	<script type="text/javascript"  src=<%=path + "js/nav.js"%>></script>
	<script src=<%=path + "layui/layui.js"%>></script>
	<script>
		jQuery(function($){
			$('#newspic').slideBox({
				duration : 0.3,//滚动持续时间，单位：秒
				easing : 'linear',//swing,linear//滚动特效
				delay : 5,//滚动延迟时间，单位：秒
				hideClickBar : false,//不自动隐藏点选按键
				clickBarRadius : 10
			});
		});
	</script>
</head>
<body>
<div class="header">
	<div class="container">
		<div class="toptxt">
			<c:choose>
				<c:when test="${requestScope.loginSuccess!=null}">
					<span>您好，欢迎使用智慧社区综合服务平台</span>
					<input type="hidden" id="desk_owners_message" value="${requestScope.loginSuccess}">
				</c:when>
				<c:otherwise>
					<span>您好，欢迎来到智慧社区综合服务平台</span>
					<a href="<%=path%>toDeskLogin.view">登录</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<div class="log-nav">
	<div class="logo">
		<c:choose>
			<c:when test="${requestScope.menu!=null}">
				<a href="<%=path%>deskLogin.action"><img src="<%=path%>image/logo.jpg"/></a>
			</c:when>
			<c:otherwise>
				<a href="<%=path%>toDeskHome.view"><img src="<%=path%>image/logo.jpg"/></a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="nav">
		<ul class="" id="navul">
			<c:choose>
				<c:when test="${requestScope.menu!=null}">
					<li class="active"><a href="<%=path%>deskLogin.action">首页</a></li>
					<c:forEach items="${requestScope.menu}" begin="0" step="1" var="i">
						<li><a href="<%=path%>${i.menuUrl}?parentMenuId=${i.menuId}">${i.menuName}</a></li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li class="active"><a href="<%=path%>toDeskHome.view">首页</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div class="clearfix"></div>
</div>
<div class="flexslider">
	<ul class="slides">
		<li style="background:url(<%=path%>image/banner.jpg) 50% 0 no-repeat;"></li>
		<li style="background:url(<%=path%>image/banner.jpg) 50% 0 no-repeat;"></li>
	</ul>
</div>
<%--滚动公告--%>
<div class="zxgg">
	<div class="BreakingNewsController easing" id="breakingnews2">
		<div class="bn-title"></div>
		<ul id="abc">
			<li><a href="#">最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告</a></li>
			<li><a href="#">最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告</a></li>
			<li><a href="#">最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告最新公告</a></li>
		</ul>
		<div class="bn-arrows"><span class="bn-arrows-left"></span><span class="bn-arrows-right"></span></div>
	</div>
</div>
<div class="container container_col">
	<div class="news-notice">
		<div class="indnews">
			<div class="news-pic">
				<div id="newspic" class="slideBox">
					<ul class="items">
						<li><a href="#" title="本会召开第一届第一次主任会议和委员会议1"><img src=""></a></li>
						<li><a href="#" title="本会召开第一届第一次主任会议和委员会议2"><img src=""></a></li>
						<li><a href="#" title="本会召开第一届第一次主任会议和委员会议3"><img src=""></a></li>
						<li><a href="#" title="本会召开第一届第一次主任会议和委员会议4"><img src=""></a></li>
						<li><a href="#" title="本会召开第一届第一次主任会议和委员会议5"><img src=""></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="news-txt" id="news">
			<div class="news-title">
				<div class="news-name tab-nav j-tab-nav">
					<a href="javascript:void(0);" class="current">新闻动态<i></i></a>
					<a href="javascript:void(0);" class="">通知公告<i></i></a>
				</div>
				<a href="#" class="more">更多 >></a>
			</div>
			<div class="tab-con">
				<div class="j-tab-con">
					<div class="tab-con-item news-con" style="display: block;">
						<div class="hotnews">
							<h1>1本会召开第一届第一次主任会议和委员会议</h1>
							<div class="hotcon"><a href="#">2017年2月20日，中卫仲裁委员会召开了中卫仲裁委员会第一届第一次主任会议和第一届第一次委员会议。会议由中卫市委常委、副市长、中卫仲裁委员会主任袁诗鸣主持。副主任李斌、郝正智、姜丽丽，委员盛建宁、张学文、俞正荣、李宝庆、王占强等出席了会议...</a></div>
						</div>
						<ul class="newslist">
							<li><a href="#">本会开展加强仲裁工作推进会议</a><span>09-10</span></li>
							<li><a href="#">本会召开第一届仲裁员聘任会议</a><span>09-10</span></li>
							<li><a href="#">本会与市中级人民法院就建立仲裁与诉讼衔接机制召开座谈会</a><span>09-10</span></li>
							<li><a href="#">宁夏仲裁工作座谈会在我市召开</a><span>09-10</span></li>
							<li><a href="#">“一带一路”仲裁发展交流会在我市召开</a><span>09-10</span></li>
						</ul>
					</div>
					<div class="tab-con-item news-con" style="display: none;">
						<div class="hotnews">
							<h1>2本会召开第一届第一次主任会议和委员会议</h1>
							<div class="hotcon"><a href="#">2017年2月20日，中卫仲裁委员会召开了中卫仲裁委员会第一届第一次主任会议和第一届第一次委员会议。会议由中卫市委常委、副市长、中卫仲裁委员会主任袁诗鸣主持。副主任李斌、郝正智、姜丽丽，委员盛建宁、张学文、俞正荣、李宝庆、王占强等出席了会议...</a></div>
						</div>
						<ul class="newslist">
							<li><a href="#">本会开展加强仲裁工作推进会议</a><span>09-10</span></li>
							<li><a href="#">本会召开第一届仲裁员聘任会议</a><span>09-10</span></li>
							<li><a href="#">本会与市中级人民法院就建立仲裁与诉讼衔接机制召开座谈会</a><span>09-10</span></li>
							<li><a href="#">宁夏仲裁工作座谈会在我市召开</a><span>09-10</span></li>
							<li><a href="#">"一带一路"仲裁发展交流会在我市召开</a><span>09-10</span></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<div class="foot">
	<div class="ft-menu">
		<div class="container">
			<div class="links">
				<h1>友情链接</h1>
				<div class="list">
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
					<a href="#" target="_blank">县人民政府</a>
				</div>
			</div>
			<div class="fttel">
				<h1>0372-12345</h1>
				<p>服务时间：周一至周日07:00-21:00</p>
				<p>非工作时间请使用：</p>
				<p>12345短信：106 3505 12345</p>
				<p>话音留言：0372-123456</p>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>

<div class="livechat-girl animated" >
	<img class="girl" src="images/en_3.png">
	<div class="livechat-hint rd-notice-tooltip rd-notice-type-success rd-notice-position-left single-line show_hint">
		<div class="rd-notice-content">嘿，我来帮您！</div>
	</div>
	<div class="animated-circles">
		<div class="circle c-1"></div>
		<div class="circle c-2"></div>
		<div class="circle c-3"></div>
	</div>
</div>


<%-- -----------------------------------------------------------%>
<%-- ---------------------在线人数显示弹窗--------------------------------------%>
<div id="mytalk" style="display: none">
	<div id="hz-group">
		<%--		<input type="hidden" id="talks" value="${sessionScope.pName}">--%>
		<input type="hidden" id="talks" value="${requestScope.room}">
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

<!--轮播-->
<script type="text/javascript" src="<%=path%>js/jquery.flexslider-min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.flexslider').flexslider({
			directionNav: true,
			pauseOnAction: false
		});
	});
</script>
<!--新闻文字切换-->
<script src="<%=path%>js/Tabs.js"></script>
<script type="text/javascript">
	$(function() {
		$("#link").rTabs({
			bind : 'hover',
			animation : 'fadein',
			auto:false
		});
	})
</script>
<!--新闻图片切换-->
<script type="text/javascript">
	$(function() {
		$("#news").rTabs({
			bind : 'hover',
			animation : 'fadein',
			auto:false
		});
	})
</script>
<!--滚动通知-->
<script type="text/javascript" src="<%=path%>js/BreakingNews.js"></script>
<script type="text/javascript">
	$(function(){
		$('#breakingnews2').BreakingNews({
			title: '最新公告',
			timer: 3000,
			effect: 'slide'
		});
	});
</script>

<script>

	$(function () {

		var userName=$('#talks').val();

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

				if (userName.length>0){

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

				}
				else {
					alert("请先登录！");

				}
			});



	});

</script>
<script src=<%=path + "/js/socketChart.js"%>></script>

</body>
</html>
