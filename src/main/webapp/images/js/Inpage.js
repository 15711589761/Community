
// 实拍鉴赏轮播；
$(function(){
    var Num1=0;
    var Num2=0;
    var LiWidth1=$(".gyscPictures .mainCon .conBox li").width();
    var Length1=$(".gyscPictures .mainCon .conBox li").length;
    var Length2=Length1+1;
    

    var clone=$(".gyscPictures .mainCon .conBox li").first().clone();
    $(".gyscPictures .mainCon .conBox ul").append(clone);
    $(".gyscPictures .mainCon .conBox ul").width(LiWidth1*Length2);
    $(".gyscPictures .mainCon .Number .all").html(Length1);
    $(".gyscPictures .mainCon .Number .Num").html(1);

    

     /*鼠标悬停事件*/

    $(".gyscPictures .mainCon").hover(function () {
        clearInterval(t);//鼠标悬停时清除定时器
    }, function () {
        t = setInterval(function () { Num1++; move(); }, 2000); //鼠标移出时清除定时器
    });

     /*向左按钮*/
    $(".gyscPictures .mainCon .next").click(function () {
        Num1++;
        move();
    })
    
    /*向右按钮*/
    $(".gyscPictures .mainCon .prev").click(function () {
        Num1--;
        if(Num1<0)
        {
            Num1=Length1-1;
        }
        move();
    })

    function move() {
        if (Num1 == Length2) {
            Num1 = 1;
            $(".gyscPictures .mainCon .conBox ul").css({ left: 0 });
        }
        if(Num1==Length1)
        {
            Num2=1;
        }
        $(".gyscPictures .mainCon .conBox ul").stop().animate({ left: -Num1* LiWidth1 }, 500);
        Num2=Num1+1;
        if(Num1>=Length1){
            Num2=1;
        }
        $(".gyscPictures .mainCon .Number .Num").html(Num2);

    };
    var t = setInterval(function () { Num1++; move();},2000);



})


$(function(){
    $(".InpageSideBarNav1 span").click(function () {
            $('body,html').animate({ scrollTop: 0 }, 800);
            return false;
    });
})


// 完善配套大城轮播；
$(function () {
    var Num1 = 0;
    var Num2 = 0;
    var LiWidth1 = $(".fcsAssort .mainCon .conBox li").innerWidth();
    var Length1 = $(".fcsAssort .mainCon .conBox li").length;
    var Length2 = Length1 + 1;

    var clone = $(".fcsAssort .mainCon .conBox li").first().clone();
    $(".fcsAssort .mainCon .conBox ul").append(clone);
    $(".fcsAssort .mainCon .conBox ul").width(LiWidth1 * Length2);
    $(".fcsAssort .mainCon .Number .all").html(Length1);
    $(".fcsAssort .mainCon .Number .Num").html(1);



    /*鼠标悬停事件*/

    $(".fcsAssort .mainCon").hover(function () {
        clearInterval(t); //鼠标悬停时清除定时器
    }, function () {
        t = setInterval(function () { Num1++; move(); }, 2000); //鼠标移出时清除定时器
    });

    /*向左按钮*/
    $(".fcsAssort .mainCon .next").click(function () {
        Num1++;
        move();
    })

    /*向右按钮*/
    $(".fcsAssort .mainCon .prev").click(function () {
        Num1--;
        if (Num1 < 0) {
            Num1 = Length1 - 1;
        }
        move();
    })

    function move() {
        if (Num1 == Length2) {
            Num1 = 1;
            $(".fcsAssort .mainCon .conBox ul").css({ left: 0 });
        }
        if (Num1 == Length1) {
            Num2 = 1;
        }
        $(".fcsAssort .mainCon .conBox ul").stop().animate({ left: -Num1 * LiWidth1 }, 500);
        Num2 = Num1 + 1;
        if (Num1 >= Length1) {
            Num2 = 1;
        }
        $(".fcsAssort .mainCon .Number .Num").html(Num2);

    };
    var t = setInterval(function () { Num1++; move(); }, 2000);

})


// 置业需求
$(function(){
    var Num1=0;
    var Num2=0;
    var LiWidth1=$(".slytyzxq .mainCon .conBox li").innerWidth();
    var Length1=$(".slytyzxq .mainCon .conBox li").length;
    var Length2=Length1+1;

    var clone=$(".slytyzxq .mainCon .conBox li").first().clone();
    $(".slytyzxq .mainCon .conBox ul").append(clone);
    $(".slytyzxq .mainCon .conBox ul").width(LiWidth1*Length2);
    $(".slytyzxq .mainCon .Number .all").html("0"+Length1);
    $(".slytyzxq .mainCon .Number .Num").html("0"+1);

    

     /*鼠标悬停事件*/

    $(".slytyzxq .mainCon").hover(function () {
        clearInterval(t);//鼠标悬停时清除定时器
    }, function () {
        t = setInterval(function () { Num1++; move(); }, 2000); //鼠标移出时清除定时器
    });

     /*向左按钮*/
    $(".slytyzxq .mainCon .next").click(function () {
        Num1++;
        // $(".fcsAssort .mainCon .conBox .btnList span").eq(Num1).addClass("cur").siblings().removeClass("cur");
        move();
    })
    
    /*向右按钮*/
    $(".slytyzxq .mainCon .prev").click(function () {
        Num1--;
        if(Num1<0)
        {
            Num1=Length1-1;
        }
        // $(".fcsAssort .mainCon .conBox .btnList span").eq(Num1).addClass("cur").siblings().removeClass("cur");
        move();
    })

    function move() {
        if (Num1 == Length2) {
            Num1 = 1;
            $(".slytyzxq .mainCon .conBox ul").css({ left: 0 });
        }
        if(Num1==Length1)
        {
            Num2=1;
        }
        $(".slytyzxq .mainCon .conBox ul").stop().animate({ left: -Num1* LiWidth1 }, 500);
        $(".slytyzxq .mainCon .box .boxItem").eq(Num1).addClass("cur").siblings().removeClass("cur");
        Num2=Num1+1;
        if(Num1>=Length1){
            Num2=1;
        }
        $(".slytyzxq .mainCon .Number .Num").html("0"+Num2);
        $(".slytyzxq .mainCon .btnList span").stop().animate({left:(Num2-1)*45},300);

    };
    var t = setInterval(function () { Num1++; move();},3000);

})









