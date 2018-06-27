//生成菜单
var menuItem = Vue.extend({
    name: 'menu-item',
    props:{item:{},index:0},
    template:[
        '<li :class="{active: (item.urls==null && index === 0)}">',
        '<a v-if="item.urls==null" href="javascript:;" :id = "\'m_\'+item.id">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span>{{item.name}}</span>',
        '<i class="fa fa-angle-left pull-right"></i>',
        '</a>',
        '<ul v-if="item.urls==null" class="treeview-menu">',
        '<menu-item :item="item" :index="index" v-for="(item, index) in item.children"></menu-item>',
        '</ul>',
        '<a v-if="item.urls!=null" :href="\'#\'+item.urls" :data-pid = "\'m_\'+item.parentId">' +
        '<i v-if="item.icon != null" :class="item.icon"></i>' +
        '<i v-else class="fa fa-circle-o"></i> {{item.name}}' +
        '</a>',
        '</li>'
    ].join('')
});

//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

//注册菜单组件
Vue.component('menuItem',menuItem);

var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{
			"username":"LJD",
		},
		menuList:[ {
            "id": 442,
            "appId": 33,
            "name": "基础信息管理",
            "createTime": "2017-11-10T17:16:25",
            "modifyTime": "2017-11-10T17:16:25",
            "link": false,
            "menu": true,
            "parentId": 440,
            "children": [{
                "id": 443,
                "appId": 33,
                "name": "水厂管理",
                "createTime": "2017-11-10T17:16:25",
                "modifyTime": "2018-05-09T09:44:34",
                "link": false,
                "menu": true,
                "urls": "/factory.html",
                "parentId": 442
            }, {
                "id": 447,
                "appId": 33,
                "name": "片区管理",
                "createTime": "2017-11-10T17:16:25",
                "modifyTime": "2018-04-10T15:30:49",
                "link": false,
                "menu": true,
                "urls": "/app.html",
                "parentId": 442
            }, {
                "id": 452,
                "appId": 33,
                "name": "表册管理",
                "createTime": "2017-11-10T17:16:25",
                "modifyTime": "2018-04-10T15:30:49",
                "link": false,
                "menu": true,
                "urls": "/tableBook.html",
                "parentId": 442
            }, {
                "id": 452,
                "appId": 33,
                "name": "水费管理",
                "createTime": "2017-11-10T17:16:25",
                "modifyTime": "2018-04-10T15:30:49",
                "link": false,
                "menu": true,
                "urls": "/waterCharge.html",
                "parentId": 442
            }, {
                "id": 452,
                "appId": 33,
                "name": "滞纳金管理",
                "createTime": "2017-11-10T17:16:25",
                "modifyTime": "2018-04-10T15:30:49",
                "link": false,
                "menu": true,
                "urls": "/lateFee.html",
                "parentId": 442
            }]
        },{
            "id": 443,
            "appId": 33,
            "name": "用户管理",
            "createTime": "2017-11-10T17:16:25",
            "modifyTime": "2017-11-10T17:16:25",
            "link": false,
            "menu": true,
            "parentId": 440,
            "children": [{
                "id": 443,
                "appId": 33,
                "name": "基本管理",
                "createTime": "2017-11-10T17:16:25",
                "modifyTime": "2018-05-09T09:44:34",
                "link": false,
                "menu": true,
                "urls": "/waterFactory.html",
                "parentId": 442
            }, {
                "id": 447,
                "appId": 33,
                "name": "账户管理",
                "createTime": "2017-11-10T17:16:25",
                "modifyTime": "2018-04-10T15:30:49",
                "link": false,
                "menu": true,
                "urls": "/area.html",
                "parentId": 442
            }]
        }],
		main:"welcome",
		oldPassword:'',
		newPassword:'',
		twicePassword:'',
        preNavTitle:"",
        navTitle:"欢迎"
	},
	methods: {
		getMenuList: function () {
			/*$.getJSON(baseURL + "app/nav", function(r){*/
				/*vm.menuList = */
				var list = vm.menuList;
                window.permissions =  ["/employee/freeze", "/role/getAllRole",
                    "/role/getAllRoleNames", "/employee.html", "/app/listByEmpInfo", "/app/select", "/role/list", "/app/nav",
                    "/app/add", "/employee/add", "/console.html", "/permission/saveOrUpdate", "/role.html", "/group/queryAllGroup",
                    "/employee/checkIsAdmin", "/employee/del", "/employee/getEmployeeImportList", "/role/resources/*/*",
                    "/employee/downLoadTemplate", "/employee/modifyMyPassword", "/employee/*/getEmployeeInfo", "/role/*/info", "/app/*/del",
                    "/role/add", "/app/update", "/app/*/query", "/app/*/resources", "/app.html", "/role/update",
                    "/employee/getEmployeeFailInfoList", "/role/delete", "/employee/list", "/index.html", "/employee/update",
                    "/employee/modifyPassword", "/app/list", "/employee/import"];
			/*r.permissions;*/
                //路由
                var router = new Router();
                routerList(router, /*vm.menuList*/list);
                router.start();
		/*	});*/
		},
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '300px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					var data = "oldPassword="+vm.oldPassword+"&newPassword="+vm.newPassword+"&twicePassword="+vm.twicePassword;
					$.ajax({
						type: "POST",
					    url: baseURL + "employee/modifyMyPassword",
					    data: data,
					    dataType: "json",
					    success: function(r){
							if(r.code == 0){
								layer.close(index);
								layer.alert('修改成功', function(){
									location.reload();
								});
							}else{
								layer.alert(r.msg);
							}
						}
					});
	            },
				btn2:function (index, layero) {
                    vm.oldPassword = null;
                    vm.newPassword = null;
                    vm.twicePassword = null;
                }
			});
		},
        logout: function () {
		    alert("点击退出");
		/*	//删除本地token
            localStorage.removeItem("token");
            //跳转到登录页面
            location.href = baseURL + 'uas/logout';*/
        }
	},
	/*created: function(){
	    a();
		this.getMenuList();
	}*/
});

//这个函数不能放在上面的created()里面!!!!
vm.getMenuList();

function routerList(router, menuList){
	for(var key in menuList){
		var menuOne = menuList[key];
		if(menuOne.urls == null){
			routerList(router, menuOne.children);
		}else if(menuOne.urls != null){
			router.add('#'+menuOne.urls, function() {
				var url = window.location.hash;
				console.log(url);
				//替换iframe的url
			/*    vm.main = url.replace('#', '');*/
               /* var myArray=new Array();
                myArray = url.split("/");*/
                vm.main =    url.split("/")[1].split(".")[0];
			   /* vm.main = url.substr(2,4);*/
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
                $(".sidebar-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
                var pid = $("a[href='"+url+"']").data("pid");
			    vm.preNavTitle = $("#" + pid + " span").text();
			    vm.navTitle = $("a[href='"+url+"']").text();
                console.log(vm.navTitle);
			});
		}
	}
}
