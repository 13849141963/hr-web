//生成菜单
var menuItem = Vue.extend({
	name: 'menu-item',
	props:{item:{}},
	template:[
	          '<li>',
	          '<a v-if="item.type === 0" href="javascript:;">',
	          '<i v-if="item.icon != null" :class="item.icon"></i>',
	          '<span>{{item.name}}</span>',
	          '<i class="fa fa-angle-left pull-right"></i>',
	          '</a>',
	          '<ul v-if="item.type === 0" :class="item.open===0?\'closeul\':\'openul\'" class="treeview-menu">',
	          '<menu-item :item="item" v-for="item in item.list"></menu-item>',
	          '</ul>',
	          '<a v-if="item.type === 1" :href="\'#\'+item.url" :id = "item.menuId+\'_menuId\'"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
	          '</li>'
	].join('')
});
//注册菜单组件
Vue.component('menuItem',menuItem);
var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{},
		menuList:{},
		main:"sys/main.html",
		password:'',
		newPassword:'',
        navTitle:"",
        userInfo:{}
	},
	methods: {
		getUserInfo:function(){
			$.getJSON("/entry-online-web/standard/getUser?_"+$.now(), function(r){
				vm.userInfo = r.successResult;			
			});
		},
		getMenuList: function (event) {
			$.getJSON("base/menu/user?_"+$.now(), function(r){
				vm.menuList = r.menuList;
				//console.log(JSON.stringify(vm.menuList));
			});
			/*$.getJSON("js/menu.json?_"+$.now(), function(data){
				//console.log(JSON.stringify(data));
				vm.menuList = data;			
			});*/
		},
	/*	getUser: function(){
			$.getJSON("sys/user/info?_"+$.now(), function(r){
				vm.user = r.user;
			});
		},*/
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					var data = "password="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
					    url: "sys/user/password",
					    data: data,
					    dataType: "json",
					    success: function(result){
							if(result.code == 0){
								layer.close(index);
								layer.alert('修改成功', function(index){
									location.reload();
								});
							}else{
								layer.alert(result.msg);
							}
						}
					});
	            }
			});
		}
	},
	created: function(){
		this.getMenuList();
		this.getUserInfo();
	},
	updated: function(){
		//路由	
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
		var url = window.location.hash;		
		//替换iframe的url
	    vm.main = url.replace('#', '');
	}
});



function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 0){
			routerList(router, menu.list);
		}else if(menu.type == 1){	
			//console.dir("menu:"+JSON.stringify(menu))
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}

var viewer = null;
$.extend({
	showView:function(imgSrc){
		var container = $('#imageViewContainer');
		container.show();	
		container.append('<div class="sonContainer" id="sonContainer"><img id="temp"style="display:none;" class="temp" src="'+imgSrc+'"/></div>');
		viewer = new Viewer(document.getElementById('sonContainer') , {
		    inline:true,
		    fullscreen:false,
		    scalable:false
		});
		container.find('.viewer-button').removeClass('viewer-fullscreen').addClass('viewer-close');
		window.scrollTo(0, 0);
	}
});

$('body').on('click','.viewer-button',function(e){
	viewer.destroy();
	$('.sonContainer').remove();
	$('.imageViewContainer').hide();
	$('.viewer-open').css('padding','0');
});
// iframe自适应
$(window).on('resize', function() {
	var $content = $('.content-wrapper .content');
	$content.height($(this).height() - 119);
    $content.find('iframe').each(function() { 
    	$(this).height($content.height());     	
    });
}).resize();