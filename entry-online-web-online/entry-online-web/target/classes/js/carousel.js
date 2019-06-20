var n__ = 0 // count__;
/*
 * $(document).ready( function() { //count__ = $(".banner_list a").length;
 * $(".banner_list a:not(:first-child)").hide(); $(".banner li").click(
 * function() { var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4 //n__ = i; if (i >=
 * count__) return; $(".banner_list
 * a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
 * $(this).toggleClass("on"); $(this).siblings().removeAttr("class");
 * $("div[id^=imgAttr]").hide(); $("div[id^=imgCost]").hide();
 * $("#imgAttr"+$(this).text()).show(); $("#imgCost"+$(this).text()).show(); } ); } )
 */
/*$(document).ready(function() {
	$(".banner_list a:not(:first-child)").hide();
});*/
var carousel = function(obj) {
	var i = $(obj).text() - 1;// 获取Li元素内的值，即1，2，3，4
	var $img = $(obj).parent('ul').next('.banner_list').children('a').eq(i).children('img').eq(0);
	var imgId = $img.attr('_imgId');
	var imgType = $img.attr('_imgType');
	if($img.attr('src')==''){
		$img.attr('src',$img.attr('tempsrc'));
	}
	$(obj).parent('ul').next('.banner_list').children('a').filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
	$(obj).toggleClass("on");
	$(obj).siblings().removeAttr("class");
	$("div[id^=imgAttr_"+imgType+"]").hide();
	$("div[id^=imgCost_"+imgType+"]").hide();
	$("#imgAttr_" + imgType + "_" + imgId).show();
	$("#imgCost_" + imgType + "_" + imgId).show();
	$('body').find(".fapiao_code").val('') ;
}