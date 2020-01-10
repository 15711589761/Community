<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    String jsPath = application.getContextPath() + "/shops_model/js/";
    String imgPath = application.getContextPath() + "/shops_model/img/";
    String cssPath = application.getContextPath() + "/shops_model/css/";
    String Path = application.getContextPath()+"/shops_model/";
%>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>社区商店</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicons -->
    <link rel="shortcut icon" href=<%=imgPath+"favicon.ico"%> type="image/x-icon">
    <link rel="apple-touch-icon" href=<%=imgPath+"icon.png"%>>

    <!-- ************************* CSS Files ************************* -->

    <!-- Vendor CSS -->
    <link rel="stylesheet" href=<%=cssPath+"vendor.css"%>>

    <!-- style css -->
    <link rel="stylesheet" href=<%=cssPath+"main.css"%>>
</head>

<body>

<!-- Preloader Start -->
<div class="ft-preloader active">
    <div class="ft-preloader-inner h-100 d-flex align-items-center justify-content-center">
        <div class="ft-child ft-bounce1"></div>
        <div class="ft-child ft-bounce2"></div>
        <div class="ft-child ft-bounce3"></div>
    </div>
</div>
<!-- Preloader End -->

<!-- Main Wrapper Start -->
<div class="wrapper">
    <!-- Header Start -->
    <header class="header header-default site-header header-transparent">
        <div class="header__outer">
            <div class="header__inner header--fixed">
                <div class="container">
                    <div class="header__main">
                        <div class="header__col header__left">
                            <a href="index.html" class="logo">
                                <figure class="logo--normal">
                                    <img src=<%=imgPath+"logo/logo.png"%> alt="Logo">
                                </figure>
                                <figure class="logo--transparency">
                                    <img src=<%=imgPath+"logo/logo.png"%> alt="Logo">
                                </figure>
                            </a>
                        </div>
                        <div class="header__col header__center">
                            <nav class="main-navigation d-none d-lg-block">
                                <ul class="mainmenu">
                                    <li class="mainmenu__item menu-item-has-children position-relative">
                                        <a href="index.html" class="mainmenu__link">Home</a>
                                        <div class="inner-menu">
                                        </div>
                                    </li>
                                    <li class="mainmenu__item menu-item-has-children position-static">
                                        <a href="toShopItems.view" class="mainmenu__link">Shop</a>

                                    </li>
                                    <li class="mainmenu__item menu-item-has-children position-relative">
                                        <a href="#" class="mainmenu__link">Pages</a>
                                        <div class="inner-menu">
                                            <ul class="sub-menu">
                                                <li>
                                                    <a href="my-account.html">My Account</a>
                                                </li>
                                                <li>
                                                    <a href="checkout.html">Checkout</a>
                                                </li>
                                                <li>
                                                    <a href="cart.html">Cart</a>
                                                </li>
                                                <li>
                                                    <a href="compare.html">Compare</a>
                                                </li>
                                                <li>
                                                    <a href="order-tracking.html">Track Order</a>
                                                </li>
                                                <li>
                                                    <a href="wishlist.html">Wishlist</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                        <div class="header__col header__right">
                            <div class="toolbar-item d-none d-lg-block">
                                <c:choose>
                                    <c:when test="${sessionScope.user !=null }" >

                                            <span>欢迎你：${sessionScope.user}</span>

                                    </c:when>
                                    <c:otherwise>
                                        <a href="toShopLogin.view" class="toolbar-btn">
                                            <span>Login</span>
                                        </a>
                                    </c:otherwise>

                                </c:choose>


                            </div>
                            <div class="toolbar-item d-block d-lg-none">
                                <a href="#offcanvasnav" class="hamburger-icon js-toolbar menu-btn">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </a>
                            </div>
                            <div class="toolbar-item">
                                <a href="wishlist.html" class="toolbar-btn">
                                    <i class="flaticon-heart"></i>
                                </a>
                            </div>
                            <div class="toolbar-item mini-cart-btn">
                                <a href="#miniCart" class="toolbar-btn js-toolbar">
                                        <span class="mini-cart-btn__icon">
                                            <i class="flaticon-bag"></i>
                                        </span>
                                    <sup class="mini-cart-btn__count">
                                        0
                                    </sup>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="header-sticky-height"></div>
        </div>
    </header>
    <!-- Header End -->

    <!-- Main Content Wrapper Start -->
    <main class="main-content-wrapper">
        <!-- Slider area Start -->
        <section class="homepage-slider mb--11pt5">
            <div class="element-carousel slick-right-bottom" data-slick-options='{
                    "slidesToShow": 1,
                    "dots": true
                }'>
                <c:forEach var="x" step="1" begin="0" items="${requestScope.viewShops}">
                <div class="item">
                    <div class="single-slide d-flex align-items-center bg-image"
                         data-bg-image=<%=imgPath+"slider/slider-bg-01.jpg"%>>
                        <div class="container">
                            <div class="row align-items-center no-gutters w-100">
                                <div class="col-lg-6 col-md-8">
                                    <div class="slider-content">
                                        <div class="slider-content__text mb--95 md-lg--80 mb-md--40 mb-sm--15">
                                            <h3 class="text-uppercase font-weight-light" data-animation="fadeInUp"
                                                data-duration=".3s" data-delay=".3s">潮流开学季！</h3>
                                            <h1 class="heading__primary mb--40 mb-md--20" data-animation="fadeInUp"
                                                data-duration=".3s" data-delay=".3s">买它，就现在！</h1>
                                            <p class="font-weight-light" data-animation="fadeInUp"
                                               data-duration=".3s" data-delay=".3s">${x.goodsContext}</p>
                                        </div>
                                        <div class="slider-content__btn">
                                            <a href="shop.html" class="btn-link" data-animation="fadeInUp"
                                               data-duration=".3s" data-delay=".6s">Shop Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 offset-lg-2 col-md-4">
                                    <figure class="slider-image d-none d-md-block">

                                        <img src="${x.goodsImage}" alt="Slider Image">
                                    </figure>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </section>
        <!-- Slider area End -->

        <!-- Featured Product Area Start -->
        <section class="featured-product-area mb--10pt8">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h2 class="sr-only">Featured Product</h2>
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col-md-6 mb-sm--50">
                        <div class="featured-product">
                            <div class="featured-product__inner info-center">
                                <figure class="featured-product__image">
                                    <img src=<%=imgPath+"products/product-01-500x466.jpg"%> alt="Featured Product">
                                </figure>
                                <div class="featured-product__info wow pbounceInLeft" data-wow-delay=".3s" data-wow-duration="1s">
                                    <div class="featured-product__info-inner">
                                        <h4 class="featured-product__text">潮流前线，引爆全场！</h4>
                                        <h2 class="featured-product__name">${requestScope.hotOne.goodsName}</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="featured-product">
                            <div class="featured-product__inner info-left-bottom">
                                <figure class="featured-product__image">
                                    <img src=<%=imgPath+"products/product-02-500x575.jpg"%> alt="Featured Product">
                                </figure>
                                <div class="featured-product__info wow pbounceInDown" data-wow-duration="1s">
                                    <div class="featured-product__info-inner rotated-info">
                                        <h5 class="featured-product__text">冰点折扣，震撼来袭！ <strong>${requestScope.hotTwo.goodsOff}%</strong> Off</h5>
                                        <h2 class="featured-product__name">${requestScope.hotTwo.goodsName}</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Featured Product Area End -->


        <!-- Countdown Product Area Start -->
        <div class="limited-product-area mb--11pt5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 mb-md--40 mb-sm--45">
                        <div class="limited-product__image">
                            <div class="limited-product__title">
                                <h2>Backpack</h2>
                            </div>
                            <div class="limited-product__large-image">
                                <div class="element-carousel main-slider" data-slick-options='{
                                        "slidesToShow": 1,
                                        "asNavFor": ".nav-slider"
                                    }'>
                                    <div class="item">
                                        <figure>
                                                <img src="${requestScope.offShops.goodsImage}"
                                                 alt="Countdown Product">
                                        </figure>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-5 offset-xl-1 col-lg-6">
                        <div class="limited-product__info">
                            <h2 class="limited-product__name">
                                <a href="product-details.html">${requestScope.offShops.goodsName}</a>
                            </h2>
                            <p class="limited-product__desc">Neque porro quisquam est, qui dolorem ipsum quia dolor
                                ipisci velit, sed quia non numquam eius modi </p>
                            <div class="d-flex align-items-center">
                                <div class="limited-product__price">
                                    <span class="sign">￥</span>
                                    <span class="money">${requestScope.offShops.goodsPrice}</span>

                                </div>
                                <span class="limited-product__rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </span>
                            </div>
                            <h3 class="limited-product__subtitle">BEST DEAL, LIMITED TIME OFFER GET YOUR’S NOW!</h3>
                            <div class="limited-product__countdown">
                                <div class="countdown-wrap">
                                    <div class="countdown" data-countdown="2020/02/01" data-format="short">
                                        <div class="countdown__item">
                                            <span class="countdown__time daysLeft"></span>
                                            <span class="countdown__text daysText"></span>
                                        </div>
                                        <div class="countdown__item">
                                            <span class="countdown__time hoursLeft"></span>
                                            <span class="countdown__text hoursText"></span>
                                        </div>
                                        <div class="countdown__item">
                                            <span class="countdown__time minsLeft"></span>
                                            <span class="countdown__text minsText"></span>
                                        </div>
                                        <div class="countdown__item">
                                            <span class="countdown__time secsLeft"></span>
                                            <span class="countdown__text secsText"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a href="shop.html" class="btn-link">Shop Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Countdown Product Area End -->

        <!-- Featured Product Area Start -->
        <section class="featured-product-area mb--11pt5">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h2 class="sr-only">Featured Product</h2>
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col-md-6 mb-sm--50">
                        <div class="featured-product">
                            <div class="featured-product__inner info-right-bottom">
                                <figure class="featured-product__image">
                                    <img src=<%=imgPath+"products/product-14-500x575.jpg"%> alt="Featured Product">
                                </figure>
                                <div class="featured-product__info wow pbounceInDown" data-wow-delay=".6s" data-wow-duration=".8s">
                                    <div class="featured-product__info-inner rotated-info">
                                        <h4 class="featured-product__text">Special Offer <strong>${requestScope.hotThree.goodsOff}%</strong> Off</h4>
                                        <h2 class="featured-product__name">${requestScope.hotThree.goodsName}</h2>
                                    </div>
                                </div>
                                <span class="featured-product__badge badge-top-left">${requestScope.hotThree.goodsOff}% off</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="featured-product">
                            <div class="featured-product__inner info-center">
                                <figure class="featured-product__image">
                                    <img src=<%=imgPath+"products/product-15-500x466.jpg"%> alt="Featured Product">
                                </figure>
                                <div class="featured-product__info wow pbounceInLeft" data-wow-delay=".3s" data-wow-duration=".8s">
                                    <div class="featured-product__info-inner">
                                        <h4 class="featured-product__text">办公销售之冠</h4>
                                        <h2 class="featured-product__name">${requestScope.hotFour.goodsName}</h2>
                                    </div>
                                </div>
                                <span class="featured-product__badge">${requestScope.hotFour.goodsOff}% off</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="row ptb--20">
            <div class="col-12 text-center">
                <p class="copyright-text">Payne &copy; 2019 all rights reserved</p>
                <div class="social space-10">
                    <a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer" class="social__link">
                        <i class="fa fa-facebook"></i>
                        <span class="sr-only">Facebook</span>
                    </a>
                    <a href="https://www.twitter.com" target="_blank" rel="noopener noreferrer" class="social__link">
                        <i class="fa fa-twitter"></i>
                        <span class="sr-only">Twitter</span>
                    </a>
                    <a href="https://www.linkedin.com" target="_blank" rel="noopener noreferrer" class="social__link">
                        <i class="fa fa-linkedin"></i>
                        <span class="sr-only">Linkedin</span>
                    </a>
                    <a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer" class="social__link">
                        <i class="fa fa-instagram"></i>
                        <span class="sr-only">Instagram</span>
                    </a>
                </div>
            </div>
        </div>

    </main>
</div>

<!-- ************************* JS Files ************************* -->

<!-- jQuery JS -->
<script src=<%=jsPath+"vendor.js"%>></script>

<!-- Main JS -->
<script src=<%=jsPath+"main.js"%>></script>
</body>

</html>