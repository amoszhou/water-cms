$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'archive/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 30, key: true },
            { label: '表册名', name: 'name', width: 40, sortable:false},
            { label: '编码', name: 'code', width: 40, sortable:false},
            { label: '片区名', name: 'areaName', width: 40, sortable:false},
            { label: '营业厅名', name: 'hallName', width: 40, sortable:false},
            /*{ label: '电话', name: 'deleteStatus', width: 30, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">禁用</span>';
            },sortable:false},*/
            /*	{ label: '创建时间', name: 'createTime', index: "create_time", width: 70,formatter:formatDate},
                { label: '更新时间', name: 'modifyTime', index: "modify_time", width: 70,formatter:formatDate},*/
           /* { label: '记录人', name: 'recordUser', width:40/!*,formatter: operateMenu*!/,sortable:false},*/
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
            areaId:'',
            code:'',
            hallId:'',
            name:'',
            recordUser:'',
            hallName:'',
            areaName:'',
            createTimeForHtml:'',
            factoryId:'',
        },
        q:{
            id:'',
            areaId:'',
            code:'',
            hallId:'',
            name:'',
            recordUser:'',
            hallName:'',
            areaName:'',
            createTimeForHtml:'',
            factoryId:'',
        },
        AreaMessageList:[],
        HallMessageList:[],
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
                $.get(baseURL + "archive/"+id+"/del", function(r){
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
            delete vm.app.createTime;
            console.log( vm.app);
            var url = vm.app.id == null ? "archive/addArchive" : "archive/updateArchive";
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
            $.get(baseURL + "archive/"+id+"/info", function(r){
                if (r.permissionCheck) {
                    alert(r.msg);
                    return;
                }
                vm.app = r.archive;
               /* vm.app.dateForHTML=     vm.app.dateForHTML.toString().replace("T"," ");*/

            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            if(vm.q.factoryId == -100)
                vm.q.factoryId = null
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name,'factoryId':vm.q.factoryId},
                page:page
            }).trigger("reloadGrid");
        },
        validator: function () {
            /*
            *  id:'',
            areaId:'',
            code:'',
            code:'',
            hallId:'',
            name:'',
            recordUser:'',
            hallName:'',
            areaName:'',
            createTimeForHtml:'',
            factoryId:'',
            * */
              if(isBlank(vm.app.areaId)){
                  alert("片区不能为空");
                  return true;
              }
              if(isBlank(vm.app.code)){
                  alert("code不能为空");
                  return true;
              }
              if(isBlank(vm.app.hallId)){
                  alert("营业厅不能为空");
                  return true;
              }
            if(isBlank(vm.app.name)){
                alert("表册名不能为空");
                return true;
            }
            if(isBlank(vm.app.factoryId)){
                alert("所属水厂不能为空");
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
    cellvalue = cellvalue.replace("T"," ");
    /* console.log(cellvalue);*/
    return cellvalue;
}

function formatURL(value, options, rowObject) {
    var token = localStorage.getItem('token');
    var result = value + '?token=' + token + '&setToken=true';
    if(!(value.startsWith("http://") || (value.startsWith("https://"))) ) result="http://"+result;
    return '<a href="' + result + '" target="_blank">' + value + '</a>';
}
//页面加载时拿到所有的片区ID，Name
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
    },error:function (returnJsonData) {

    }
});
//页面加载时拿到所有的营业厅ID，Name
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

/*
AreaMessageList:[],
    HallMessageList:[],*/
