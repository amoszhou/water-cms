$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'employee/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 30, key: true },
            { label: '用户名', name: 'username', width: 40, sortable:false},
            { label: '所属水厂名', name: 'factoryName', width: 40, sortable:false/*,formatter: funFactory */},
            { label: '真实姓名', name: 'realName', width: 40,sortable:false },
            { label: '用户类别', name: 'userType', width: 40,sortable:false,formatter: funUserType },
            /*{ label: '电话', name: 'deleteStatus', width: 30, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">禁用</span>';
            },sortable:false},*/
            /*	{ label: '创建时间', name: 'createTime', index: "create_time", width: 70,formatter:formatDate},
                { label: '更新时间', name: 'modifyTime', index: "modify_time", width: 70,formatter:formatDate},*/
            { label: '电话', name: 'telPhone', width:40/*,formatter: operateMenu*/,sortable:false},
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
        q:{
            name: null
        },
        showList: true,
        title:null,
        app:{
            id:'',
            factoryId:'',
          /*  password:'',*/
            realName:'',
            username:'',
            hallId:'',
            telPhone:'',
            factoryName:'',
            hallName:'',
            userType:''
        },
        q:{
            id:'',
            factoryId:'',
            hallId:'',
          /*  password:'',*/
            realName:'',
            username:'',
            telPhone:'',
            factoryName:'',
            hallName:'',
            userType:''
        },
        FactoryMessageList:[]
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
                $.get(baseURL + "employee/"+id+"/del", function(r){
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

            var url = vm.app.id == null ? "employee/addEmployee" : "employee/updateEmployee";
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
            $.get(baseURL + "employee/"+id+"/info", function(r){
                if (r.permissionCheck) {
                    alert(r.msg);
                    return;
                }
                vm.app = r.employee;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'realName': vm.q.realName,'hallName':vm.q.hallName},
                page:page
            }).trigger("reloadGrid");
        },
        validator: function () {
            /*
            *     id:'',
            factoryId:'',
            password:'',
            realName:'',
            username:'',
            hallId:'',
            telPhone:'',
            factoryName:'',
            hallName:'',
            userType:''
            * */
              if(isBlank(vm.app.factoryId) && vm.app.userType == 1){
                  alert("所属水厂不能为空");
                  return true;
              }
             /* if(isBlank(vm.app.password)){
                  alert("密码不能为空");
                  return true;
              }*/
            if(isBlank(vm.app.realName)){
                alert("真实姓名不能为空");
                return true;
            }
            if(isBlank(vm.app.username)){
                alert("用户名不能为空");
                return true;
            }
         /*   if(isBlank(vm.app.hallId)){
                alert("营业厅不能为空");
                return true;
            }*/
            if(isBlank(vm.app.telPhone)){
                alert("电话不能为空");
                return true;
            }
          /*  if(isBlank(vm.app.password)){
                alert("密码不能为空");
                return true;
            }*/
          console.log(vm.app.userType);
            if(isBlank(vm.app.userType) && vm.app.userType !=0 ){
                alert("用户类别不能为空");
                return true;
            }

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

function funUserType(cellvalue, options, rowObject) {
   if(cellvalue == 0)
       return "超级管理员"
    else if(cellvalue == 1)
        return "普通管理员"
    return cellvalue;
}

/*function funFactory(cellvalue, options, rowObject) {
    if(cellvalue == 0)
        return "超级管理员"
    else if(cellvalue == 1)
        return "普通管理员"
    return cellvalue;
}*/



//页面加载时拿到所有的水厂编码
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
    },error:function (returnJsonData) {

    }
});