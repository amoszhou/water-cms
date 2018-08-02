$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customerAccount/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 30, key: true },
            { label: '顾客名', name: 'customerName', width: 40,sortable:false },
            { label: '余额', name: 'balance', width: 40, sortable:false/*, formatter: formatURL*/},
            /*{ label: '电话', name: 'deleteStatus', width: 30, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">禁用</span>';
            },sortable:false},*/
            /*	{ label: '创建时间', name: 'createTime', index: "create_time", width: 70,formatter:formatDate},
                { label: '更新时间', name: 'modifyTime', index: "modify_time", width: 70,formatter:formatDate},*/

            { label: '更新人', name: 'updateUser', width:40/*,formatter: operateMenu*/,sortable:false},
            { label: '更新时间', name: 'updateTimeForHTML', width:40/*,formatter: operateMenu*/,sortable:false},
        /*    { label: '操作', width:40,formatter: operateMenu,sortable:false},*/
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
            balance:'',
            custId:'',
            updateUser:'',
            customerName:'',
            updateTimeForHTML:'',
            raiseMoney:'',
            factoryId:'',
        },
        q:{
            id:'',
            balance:'',
            custId:'',
            updateUser:'',
            customerName:'',
            updateTimeForHTML:'',
            raiseMoney:'',
            factoryId:'',
        },
        CustomerMessageList:[],
        FactoryMessageList:[],
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
                $.get(baseURL + "customerAccount/"+id+"/del", function(r){
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

            var url = vm.app.id == null ? "customerAccount/addCustomerAccount" : "customerAccount/updateCustomerAccount";
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
            $.get(baseURL + "customerAccount/"+id+"/info", function(r){
                if (r.permissionCheck) {
                    alert(r.msg);
                    return;
                }

                vm.app = r.customerAccount;
                delete vm.app.updateTime;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name,'factoryId':vm.q.factoryId},
                page:page
            }).trigger("reloadGrid");
        },
        validator: function () {
            /*  if(isBlank(vm.app.appId)){
                  alert("应用标识不能为空");
                  return true;
              }
              if(isBlank(vm.app.name)){
                  alert("应用名称不能为空");
                  return true;
              }

              if(isBlank(vm.app.url)){
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

//页面加载时拿到所有的顾客信息
$.ajax({
    async: false, // 同步
    type: 'GET',
    url: "/customerAccount/getCustomerMessageList",
    dataType: "json",
    contentType: 'application/json',
    success: function (returnJsonData) {
        vm.CustomerMessageList = [];
        for(var i = 0 ; i < returnJsonData.length ; i ++){
            var tepm = {id:returnJsonData[i].id,name:returnJsonData[i].name,idAndName:returnJsonData[i].idAndName};
            vm.CustomerMessageList.push(tepm);
        }
        console.log(vm.CustomerMessageList);
    },error:function (returnJsonData) {

    }
});
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
