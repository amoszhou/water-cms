$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'token/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 10, key: true },
            { label: '应用ID', name: 'clientId', width: 30, sortable:false},
            { label: '申请的权限ID集合', name: 'scopeList',  width: 50,formatter:Separate/*,formatter:formatDate*/},
            { label: '团贷网用户ID', name: 'tuandaiUserId', width:80/*,formatter: operateMenu*/,sortable:false},
            { label: '令牌状态', name: 'tokenStatus', width:20,formatter:YesOrNo/*,formatter: operateMenu*/,sortable:false},
            { label: '删除状态', name: 'deleteStatus', width:20 ,formatter: operateDeleteStatus/*,formatter: operateMenu*/,sortable:false},
            { label: '添加时间', name: 'createTime', width:40,formatter:formatDate/*,formatter: operateMenu*/,sortable:false},
            { label: '修改时间', name: 'modifyTime', width:40,formatter:formatDate/*,formatter: operateMenu*/,sortable:false},
            { label: '令牌过期时间', name: 'expiresIn', width: 40,formatter:formatDate/*,formatter:formatDate*/},
            { label: '访问令牌', name: 'accessToken', width: 25,sortable:false,formatter: operateMenu },
            { label: '刷新令牌', name: 'refreshToken', width: 25, sortable:false,formatter: operateMenu_refershToken/*, formatter: formatURL*/},
            { label: '重定向URI', name: 'redirectUri', width: 25 ,formatter:operateMenu_redirect_uri/*,formatter:formatDate*/},
            /*  { label: '状态', name: 'deleteStatus', width: 30, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">禁用</span>';
            },sortable:false},*/
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
          /*  timeTypeSelectOnChange();*/
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
            deleteStatus:0
        },
        modal : {
            title : "",
            details_title: "",
            details : "",
        },
        cliendId:null,
        tuandaiUserId:null,
        token:{
            accessToken : "",
            refreshToken : "",
            redirectUri : "",
            expiresIn : "",
            tokenStatus : "",
            clientId : "",
            id : "",
            tuandaiUserId : "",
            expiresInTwo: "",
        },

    },
    methods: {
        query: function () {
       /*     vm.cliendId = $("#cliendId").val();
            vm.tuandaiUserId = $("#tuandai_userId").val();*/
            if((vm.cliendId == ""&& vm.tuandaiUserId == "")||(vm.cliendId == null&& vm.tuandaiUserId == null)||(vm.cliendId == ""&& vm.tuandaiUserId == null)||(vm.cliendId == null&& vm.tuandaiUserId == "") )
                vm.reload();
            else {
                if(vm.cliendId == "")
                    vm.cliendId =null;
                if(vm.tuandaiUserId == "")
                    vm.tuandaiUserId = null;
                vm.showList = true;
                var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                $("#jqGrid").jqGrid('setGridParam', {
                    postData: {'tuandaiUserId': vm.tuandaiUserId, 'clientId': vm.cliendId},
                    page: page
                }).trigger("reloadGrid");

           }
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

            vm.getToken(id);
        },
        del: function () {
            var id = getSelectedRows();
            if(id == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.get(baseURL + "app/"+id+"/del", function(r){
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
            var url = "token/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.token),
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
            $.get(baseURL + "app/"+id+"/query", function(r){
                if (r.permissionCheck) {
                    alert(r.msg);
                    return;
                }
                vm.app = r.app;
            });
        },
        getToken: function(id){
            $.ajax({
                type: 'GET',
                url : "/token/selectTokenById",
                data : "id="+id,
                async: false,
                success : function (data) {
                    vm.token.accessToken = data.accessToken;
                    vm.token.refreshToken = data.refreshToken;
                    vm.token.redirectUri = data.redirectUri;
                    vm.token.expiresIn = data.expiresIn;
                    vm.token.expiresIn = formatDate(vm.token.expiresIn,null,null);
                    vm.token.expiresInTwo = data.expiresIn;
                    vm.token.tokenStatus = data.tokenStatus;
                    vm.token.clientId = data.clientId;
                    vm.token.id = data.id;
                    vm.token.tuandaiUserId = data.tuandaiUserId;
                }

            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'tuandaiUserId': null,'clientId' : null},
                page:page
            }).trigger("reloadGrid");
        },
        validator: function () {
            if(isBlank(vm.token.id)){
                alert("令牌Id不能为空");
                return true;
            }
            if(isBlank(vm.token.expiresIn)){
                alert("令牌过期时间不能为空");
                return true;
            }

       /*     if(isBlank(vm.token.token_status)){
                alert("令牌状态不能为空");
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
    var cell = cellvalue.substring(0,3);
    var result = "";
    var detailBtn = "<a class='btn btn-default' data-toggle='modal' data-target='#modal_app_info' onclick=\"openDetail(" + aid + ",1)\">详情</a>";
    result += detailBtn;
    return result;
}
function operateMenu_refershToken(cellvalue, options, rowObject) {
    var aid = rowObject.id;
    var cell = cellvalue.substring(0,3);
    var result = "";
    var detailBtn = "<a class='btn btn-default' data-toggle='modal' data-target='#modal_app_info' onclick=\"openDetail(" + aid + ",2)\">详情</a>";
    result += detailBtn;
    return result;
}
function operateMenu_redirect_uri(cellvalue, options, rowObject) {
    var aid = rowObject.id;
    var result = "";
    var detailBtn = "<a class='btn btn-default' data-toggle='modal' data-target='#modal_app_info' onclick=\"openDetail(" + aid + ",3)\">详情</a>";
    result += detailBtn;
    return result;
}

function openDetail(aid,choice) {

    vm.getToken(aid);
    if(choice == 1) {
        vm.modal.title = "令牌详情";
        vm.modal.details_title = "访问令牌详情:";
        vm.modal.details = vm.token.accessToken;

    }else if (choice == 2){
        vm.modal.title = "令牌详情";
        vm.modal.details_title = "访问令牌详情:";
        vm.modal.details = vm.token.refreshToken;
    }else {
        vm.modal.title = "重定向URI";
        vm.modal.details_title = "重定向URI详情:";
        vm.modal.details = vm.token.redirectUri;
    }


    /*      vm.getApp(aid);*/
}

function formatDate(cellvalue, options, rowObject) {
    cellvalue = cellvalue.replace("T"," ");
    return cellvalue;
}

function YesOrNo(cellvalue, options, rowObject) {
      if(cellvalue == 0)
          return "    有效" ;
      if(cellvalue == 1)
          return "    无效" ;
      return "未知状态" ;
}
function  operateDeleteStatus(cellvalue, options, rowObject) {
      if(cellvalue == 0)
          return "未删除";
      return "已删除";
}

function Separate(cellvalue, options, rowObject) {
    var strs= new Array();
    var str = "";
    strs=cellvalue.split(",");
    for (i=0;i<strs.length ;i++ )
    {
        str += strs[i]+"\n";
    }
    return str;
        }


function formatURL(value, options, rowObject) {
    var token = localStorage.getItem('token');
    var result = value + '?token=' + token + '&setToken=true';
    if(!(value.startsWith("http://") || (value.startsWith("https://"))) ) result="http://"+result;
    return '<a href="' + result + '" target="_blank">' + value + '</a>';
}

