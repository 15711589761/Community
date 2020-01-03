$(function(){
    win = $(window);
    innav()
    // 导航栏定位
    function showNav(){
        var urlstr = location.href;
        var urlstatus=false;
        $(".box2 li a").each(function () {
            if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!='') {
              $(this).parent().addClass('curr'); urlstatus = true;      
            } else {
              $(this).parent().removeClass('curr');urlstatus = false;
            }
        });
    };
    //头部滑块
    var slip2=$(".navline"),li2=$(".box2 li"),a2=$(".box2 li.curr"),i2="",left2="28",animatetime2=300;
    var Slider2 =new Slider(slip2,li2,a2,i2,left2,animatetime2);
    function innav(){
        var li = $('.box2 ul li'),
            sideli = $('.sideNav'),
            innavbg = $('.innavbg');
        li.hover(function(){
            index = $(this).index();
            $(this).find(sideli).stop().fadeIn(200);
            inshow(index);
        },function(){
            $(this).find(sideli).stop().fadeOut(200);
            innavbg.stop().fadeOut(200);
        });
        function inshow(index) {
            if (index == 2) {
                innavbg.stop().fadeOut(0);
            } else if (index > 0 && index < 9 && index != 3  && index != 8) {
                innavbg.stop().fadeIn(0);
            } else {
                innavbg.stop().fadeOut(0);
            };
        };
    };
})