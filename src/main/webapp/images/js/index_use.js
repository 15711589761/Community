
   function Slider(slip,li,a,i,left,animatetime){
    this.$slip = slip;
    this.$li = li;
    this.$a = a;
    this.$i = i;
    this.$left=left;
    this.$animatetime=animatetime;
    var _this = this;
    this.dingwei = function () {
        this.$slip.stop().animate({
            width: _this.$a.width() + 'px',
            left: parseInt(_this.$a.position().left) + parseInt(_this.$left) + 'px'
        }, _this.$animatetime);
    };
    this.bindevent = function () {
        var _this = this;
        this.$li.hover(function () {
            _this.$slip.stop().animate({
                width: $(this).width() + 'px',
                left: parseInt($(this).position().left) + parseInt(_this.$left) + 'px'
            }, _this.$animatetime);
        }, function () {
            _this.dingwei();
        });
    };
    this.intr=function(){
        this.dingwei();
        this.bindevent();
    };
    this.intr();
}

/*3d轮播*/
function moveJzt(IdBox,LtBtn,RtBtn){
    var IdBox=$('#'+IdBox+'');
    var  LtBtn=$('#'+LtBtn+'');
    var  RtBtn=$('#'+RtBtn+'');

    var time=new Date();
    var $li=IdBox.find('.list').length;
    var $num= IdBox.find('.list');
    function banner(arrrow){
        if(new Date-time>500){
            time = new Date();
            var arrWidth =[],arrHeight = [],arrTop = [], arrLeft=[], arrIndex=[],arrOpacity=[],arrBorder=[],arrP=[];
            for(i=0; i<$li; i++)
            {
                arrWidth[i] = $num.eq(i).css('width');
                arrHeight[i] =$num.eq(i).css('height');
                arrTop[i] = $num.eq(i).css('top');
                arrLeft[i] = $num.eq(i).css('left');
                arrIndex[i] = $num.eq(i).css('z-index');
                arrP[i] = $num.eq(i).find('p').css('display');
                arrOpacity[i] = $num.eq(i).find('img').css('opacity');
                arrBorder[i] = $num.eq(i).css('border');
            }

            for(i=0; i<$li; i++){
                if(arrrow==0){
                    var a=i-1;
                    if(a<0)
                        a= $li-1;
                }else{
                    var a=i+1;
                    if(a>$li-1)
                        a=0;
                }
                $num.eq(i).find('img').css({'opacity':arrOpacity[a]});//设置图片透明度
                $num.eq(i).find('p').css({'display':arrP[a]});
                $num.eq(i).css({'border':arrBorder[a]});
                $num.eq(i).css('z-index',arrIndex[a]).animate({     //设置层级
                    'width':arrWidth[a],
                    'height':arrHeight[a],
                    'top':arrTop[a],
                    'left':arrLeft[a]
                });
            }
        }
    }
    //自动轮播
    var autoplay = setInterval(banner,2000);

    //右按钮
      RtBtn.click(function(){
      // clearInterval(autoplay);
        banner(0);
    })
    //左按钮
      LtBtn.click(function(){
        banner(1)
    })
    //鼠标放在按钮上停止自动播放
     $num.hover(function(){
        clearInterval(autoplay);
    },function(){
        autoplay = setInterval(banner,2000);
    })

      RtBtn.hover(function(){
          clearInterval(autoplay);
      },function(){
          autoplay = setInterval(banner,2000);
      })

      LtBtn.hover(function(){
          clearInterval(autoplay);
      },function(){
          autoplay = setInterval(banner,2000);
      })
  }
