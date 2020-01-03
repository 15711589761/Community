<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String Path = application.getContextPath()+"/";
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>轮播图</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<link rel="stylesheet" type="text/css" href=<%=Path+"store_order_pack/css/swiper.min.css"%> />
	<link rel="stylesheet" type="text/css" href=<%=Path+"store_order_pack/css/style.css"%>/>
</head>

<body>
<form action="/Community/alipay.action" id="storeBuy" method="post">
<div class="swiper-container">
	<div class="swiper-wrapper">
		<div class="swiper-slide">
			<div class="info">
				<h3>商铺介绍</h3>
				<h2>Barrio</h2>
				<p>incredible opportunity to purchase this elegant,bright,and spacious duplex-up penthouse</p>
				<a href="#">预购下单</a>
				<a href="#" onclick="document.getElementById('storeBuy').submit();return false;">预购下单</a>
			</div>
			<div class="img">
				<img src=<%=Path+"store_order_pack/images/banner.jpg"%>/>
			</div>
		</div>
		<div class="swiper-slide">
			<div class="info">
				<h3>商铺信息</h3>
				<h2>Metalico</h2>
				<p>incredible opportunity to purchase this elegant,bright,and spacious duplex-up penthouse</p>
			</div>
			<div class="img">
				<img src=<%=Path+"store_order_pack/images/banner.jpg"%>/>
			</div>
		</div>
		<div class="swiper-slide">
			<div class="info">
				<h3>商铺使用</h3>
				<h2>Barrio Metalico</h2>
				<p>incredible opportunity to purchase this elegant,bright,and spacious duplex-up penthouse</p>
			</div>
			<div class="img">
				<img src=<%=Path+"store_order_pack/images/banner.jpg"%>/>
			</div>
		</div>
	</div>

	<!-- 分页 -->
	<div class="swiper-pagination"></div>
	<!-- 按钮 -->
	<div class="swiper-button-next swiper-button-black"></div>
	<div class="swiper-button-prev swiper-button-black"></div>
</div>
</form>
<script src=<%=Path+"store_order_pack/js/swiper.min.js"%> type="text/javascript" charset="utf-8"></script>

<script>
	var swiper = new Swiper('.swiper-container', {
		loop: true, //是否循环，false不循环
		effect : 'cube',//切换效果，不要可删除
		pagination: {
			el: '.swiper-pagination',
			type: 'fraction',
		},
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
	});
</script>

</body>
</html>
