<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String jspath = request.getContextPath() + "/js/";
	String cssPath = request.getContextPath() + "/css/";
	String Path = application.getContextPath()+"/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>旺铺销售界面</title>
	<link href=<%=Path+"images/css/Common.css"%> rel="stylesheet" type="text/css" />
	<link href=<%=Path+"images/css/Index.css"%> rel="stylesheet" type="text/css" />
	<link type="text/css" href=<%=cssPath+"style.css"%> rel="stylesheet" />
<%--	<script type="text/javascript" src=<%=Path+"layui.js"%>></script>--%>
	<script type="text/javascript" src=<%=jspath + "js.js"%>></script>
	<script type="text/javascript" src=<%=jspath + "tools.js"%>></script>
</head>
<body>
<script src=<%=jspath+"images/js/header.js"%>></script>
<div class="header">
	<div class="headerTop">
		<div class="container clearfix">
			<ul class="clearfix">
				<li class="ma"><a>关注微信</a><div class="erm"></div></li>
				<li><script type="text/javascript" src=<%=jspath+"images/js/language.js"%>></script></li>
				<li><a href="javascript:addBookmark()" title="收藏本站">收藏本站</a></li>
				<li><a href="#" title="联系我们">联系我们</a></li>
				<li><a href="http://wpa.qq.com/msgrd?v=3&site=&menu=yes&uin=1131680521" title="在线咨询" target="_blank">在线咨询</a></li>
				<li class="last"> <img src="images/header_phone.png" width="18" height="20" > 12345678901</li>
			</ul>
		</div>
	</div>

</div>
<script>
	$(".headerCenter li:first").addClass("curr");
</script>

<div class="wrap" id="wrap">
	<ul class="content"></ul>
	<a href="javascript:;" class="prev">&#60;</a>
	<a href="javascript:;" class="next">&#62;</a>
</div>

<!-- hotSale 热销-->
<div class="hotSale">
	<div class="Title">
		<h3><a href="javascript:;" title="热销商铺" style="cursor:default; text-decoration:none;">热销商铺</a></h3>
		<p>典范造就经典</p>
		<i></i>
	</div>
	<form action="/Community/toStoreOrder.view" id="toOrder" method="post">
	<div class="container">
		<div class="mainCon">
			<ul class="clearfix">
				<c:forEach var="x" step="1" begin="0" items="${requestScope.list}">
					<li><a href="" onclick="document.getElementById('toOrder').submit();return false;" class="Pic">
						<img src="${x.storeImg}" alt="" width="365" height="243"></a>
						<h3>
								${x.storeAddress}</h3>
						<p class="p1">
							总价：${x.sellPrice}|占地面积：${x.storeSize}平方米
						</p>
						<p class="p2">
							<em>${x.sellPrice/x.storeSize}</em> 元/平米
						</p>

						<a href="" class="details" onclick="document.getElementById('toOrder').submit();return false;">了解详情</a>
						<i class="hotimg"><img src="images/ihoticon.png" alt="hoticon"></i>
					</li>
				</c:forEach>4
			</ul>
		</div>
	</div>
	</form>
</div>

<script type="text/javascript">
	$(".choose .mainCon .conBox .con").eq(0).show().siblings().hide();
	$(".choose .mainCon .menu dl").hover(function () {
		$(this).addClass("cur").siblings().removeClass("cur");
		$(".choose .mainCon .conBox .con").eq($(this).index()).fadeIn().siblings().hide();
	})
</script>

<script>
	mswMove("counselor", "counselorBtn01", "counselorBtn02", "", true, "left", true, 347, 1000, 3000, 3);
</script>



</body>
</html>
