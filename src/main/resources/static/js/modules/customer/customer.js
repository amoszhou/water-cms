$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 30, key: true },
            { label: '顾客名', name: 'name', width: 40,sortable:false },
            { label: '顾客编码', name: 'code', width: 40,sortable:false },
            { label: '电话', name: 'phone', width: 40, sortable:false/*, formatter: formatURL*/},
            { label: '座机', name: 'tel', width: 40, sortable:false/*, formatter: formatURL*/},
            { label: '身份证', name: 'idCard', width: 40, sortable:false/*, formatter: formatURL*/},
            { label: '地址', name: 'address', width: 40, sortable:false/*, formatter: formatURL*/},

            /*{ label: '电话', name: 'deleteStatus', width: 30, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">禁用</span>';
            },sortable:false},*/
            /*	{ label: '创建时间', name: 'createTime', index: "create_time", width: 70,formatter:formatDate},
                { label: '更新时间', name: 'modifyTime', index: "modify_time", width: 70,formatter:formatDate},*/
            { label: '所属水厂', name: 'factoryName', width: 40, sortable:false/*, formatter: formatURL*/},
            { label: '操作', width:40,formatter: operateMenu,sortable:false},
        ],
        viewrecords: true,
        height: screen.height * 0.55,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title:null,
        app:{
            id:'',
            address:'',
            archiveId:'',
            archiveName:'',
            areaId:'',
            areaName:'',
            code:'',
            createUser:'',
            createUserName:'',
            factoryId:'',
            factoryName:'',
            familyCount:'',
            frequency:'',
            hallId:'',
            hallName:'',
            idCard:'',
            name:'',
            phone:'',
            tel:'',
            updateUser:'',
            updateUserName:'',
            createTimeForHTML:'',
            updateTimeForHTML:'',

        },
        q:{
            id:'',
            address:'',
            archiveId:'',
            archiveName:'',
            areaId:'',
            areaName:'',
            code:'',
            createUser:'',
            createUserName:'',
            factoryId:'',
            factoryName:'',
            familyCount:'',
            frequency:'',
            hallId:'',
            hallName:'',
            idCard:'',
            name:'',
            phone:'',
            tel:'',
            updateUser:'',
            updateUserName:'',
            createTimeForHTML:'',
            updateTimeForHTML:'',
        },
        FactoryMessageList:[],
        AreaMessageList:[],
        HallMessageList:[],
        ArchiveMessageList:[],
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.app = {deleteStatus:0};
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getApp(id);
        },
        del: function () {
            var id = getSelectedRows();
            if(id == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.get(baseURL + "customer/"+id+"/del", function(r){
                    if (r.permissionCheck) {
                        alert(r.msg);
                        return;
                    }
                    if(r.code == 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                });
            });
        },
        saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }

            var url = vm.app.id == null ? "customer/addCustomer" : "customer/updateCustomer";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.app),
                success: function(r){
                    if (r.permissionCheck) {
                        alert(r.msg);
                        return;
                    }
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getApp: function(id){
            $.get(baseURL + "customer/"+id+"/info", function(r){
                if (r.permissionCheck) {
                    alert(r.msg);
                    return;
                }
                vm.app = r.customer;
                delete vm.app.createTime;
                delete vm.app.updateTime;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            if(vm.q.factoryId == -100)
                vm.q.factoryId = null
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'code': vm.q.code,'factoryId': vm.q.factoryId},
                page:page
            }).trigger("reloadGrid");
        },
        validator: function () {
            /*
            *    id:'',
            address:'',
            archiveId:'',
            archiveName:'',
            areaId:'',
            areaName:'',
            code:'',
            createUser:'',
            createUserName:'',
            factoryId:'',
            factoryName:'',
            familyCount:'',
            frequency:'',
            hallId:'',
            hallName:'',
            idCard:'',
            name:'',
            phone:'',
            tel:'',
            updateUser:'',
            updateUserName:'',
            createTimeForHTML:'',
            updateTimeForHTML:'',
            * */
              if(isBlank(vm.app.address)){
                  alert("顾客地址不能为空");
                  return true;
              }
              if(isBlank(vm.app.archiveId)){
                  alert("所属表册不能为空");
                  return true;
              }
              if(isBlank(vm.app.areaId)){
                  alert("所属片区不能为空");
                  return true;
              }
            if(isBlank(vm.app.code)){
                alert("顾客编码不能为空");
                return true;
            }
            if(isBlank(vm.app.factoryId)){
                alert("所属水厂不能为空");
                return true;
            }
            if(isBlank(vm.app.hallId)){
                alert("所属营业厅不能为空");
                return true;
            }
            if(isBlank(vm.app.idCard)){
                alert("身份证不能为空");
                return true;
            }
            if(isBlank(vm.app.name)){
                alert("顾客名字不能为空");
                return true;
            }
            if(isBlank(vm.app.phone)){
                alert("手机号码不能为空");
                return true;
            }
          /*  if(isBlank(vm.app.url)){
                alert("应用地址不能为空");
                return true;
            } if(isBlank(vm.app.url)){
                alert("应用地址不能为空");
                return true;
            } if(isBlank(vm.app.url)){
                alert("应用地址不能为空");
                return true;
            }*/
        }
    }
});

/**
 * DataGrid行操作
 * */
function operateMenu(cellvalue, options, rowObject) {
    var aid = rowObject.id;
    var result = "";
    var detailBtn = "<a class='btn btn-default' data-toggle='modal' data-target='#modal_app_info' onclick='openDetail(" + aid + ")'>详情</a>";
    result += detailBtn;
    return result;
}

function openDetail(aid) {
    vm.getApp(aid);
}

function formatDate(cellvalue, options, rowObject) {
    /*  cellvalue = cellvalue.replace("T"," ");*/
    return cellvalue;
}

function formatURL(value, options, rowObject) {
    var token = localStorage.getItem('token');
    var result = value + '?token=' + token + '&setToken=true';
    if(!(value.startsWith("http://") || (value.startsWith("https://"))) ) result="http://"+result;
    return '<a href="' + result + '" target="_blank">' + value + '</a>';
}

//页面加载时拿到所有的水厂信息
$.ajax({
    async: false, // 同步
    type: 'GET',
    url: "/hall/getFactoryMessage",
    dataType: "json",
    contentType: 'application/json',
    success: function (returnJsonData) {
        vm.FactoryMessageList = [];
        for(var i = 0 ; i < returnJsonData.length ; i ++){
            var tepm = {id:returnJsonData[i].id,name:returnJsonData[i].name,idAndName:returnJsonData[i].idAndName};
            vm.FactoryMessageList.push(tepm);
        }
        console.log(vm.FactoryMessageList);
    },error:function (returnJsonData) {

    }
});
//页面加载时拿到所有的片区信息
$.ajax({
    async: false, // 同步
    type: 'GET',
    url: "/archive/getAreaMessage",
    dataType: "json",
    contentType: 'application/json',
    success: function (returnJsonData) {
        vm.AreaMessageList = [];
        for(var i = 0 ; i < returnJsonData.length ; i ++){
            var tepm = {id:returnJsonData[i].id,name:returnJsonData[i].name,idAndName:returnJsonData[i].idAndName};
            vm.AreaMessageList.push(tepm);
        }
        console.log(vm.AreaMessageList);
    },error:function (returnJsonData) {

    }
});
//页面加载时拿到所有的营业厅信息
$.ajax({
    async: false, // 同步
    type: 'GET',
    url: "/archive/getHallMessage",
    dataType: "json",
    contentType: 'application/json',
    success: function (returnJsonData) {
        vm.HallMessageList = [];
        for(var i = 0 ; i < returnJsonData.length ; i ++){
            var tepm = {id:returnJsonData[i].id,name:returnJsonData[i].name,idAndName:returnJsonData[i].idAndName};
            vm.HallMessageList.push(tepm);
        }
        console.log(vm.HallMessageList);
    },error:function (returnJsonData) {

    }
});
//页面加载时拿到所有的表册信息
$.ajax({
    async: false, // 同步
    type: 'GET',
    url: "/archive/getArchiveMessage",
    dataType: "json",
    contentType: 'application/json',
    success: function (returnJsonData) {
        vm.ArchiveMessageList = [];
        for(var i = 0 ; i < returnJsonData.length ; i ++){
            var tepm = {id:returnJsonData[i].id,name:returnJsonData[i].name,idAndName:returnJsonData[i].idAndName};
            vm.ArchiveMessageList.push(tepm);
        }
        console.log(vm.ArchiveMessageList);
    },error:function (returnJsonData) {

    }
});