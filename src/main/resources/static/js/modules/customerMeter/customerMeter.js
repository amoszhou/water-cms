$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customerMeter/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 30, key: true },
            { label: '编码', name: 'code', width: 40, sortable:false},
            { label: '顾客code', name: 'custCode', width: 40,sortable:false },
            { label: '顾客名称', name: 'customerName', width: 40,sortable:false },
            { label: '水费价格', name: 'waterFee', width: 40, sortable:false/*, formatter: formatURL*/},
            { label: '污水费价格', name: 'sewageFee', width: 40, sortable:false/*, formatter: formatURL*/},
            { label: '水表名', name: 'meterName', width: 40, sortable:false/*, formatter: formatURL*/},
            { label: '所属水厂名', name: 'factoryName', width: 40, sortable:false/*, formatter: formatURL*/},
             /*{ label: '电话', name: 'deleteStatus', width: 30, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-success">正常</span>' :
                    '<span class="label label-danger">禁用</span>';
            },sortable:false},*/
            /*	{ label: '创建时间', name: 'createTime', index: "create_time", width: 70,formatter:formatDate},
                { label: '更新时间', name: 'modifyTime', index: "modify_time", width: 70,formatter:formatDate},*/
            { label: '有效时间', name: 'enableDateForHtml', width:40/*,formatter: operateMenu*/,sortable:false},
            /*{ label: '操作', width:40,formatter: operateMenu,sortable:false},*/
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
            code:'',
            custCode:'',
            custId:'',
            enableDate:'',
            isDelete:'',
            priceType:'',
            size:'',
            customerName:'',
            meterName:'',
            factoryName:'',
            waterFee:'',
            sewageFee:'',
            enableDateForHtml:'',
            validDate:'',
            factoryName:'',
        },
        q:{
            id:'',
            code:'',
            custCode:'',
            custId:'',
            enableDate:'',
            isDelete:'',
            priceType:'',
            size:'',
            customerName:'',
            meterName:'',
            factoryName:'',
            waterFee:'',
            sewageFee:'',
            enableDateForHtml:'',
            validDate:'',
            factoryName:'',
        },
        FactoryMessageList:[],
        CustomerMessageList:[],
        PriceTypeMessageList:[],
        MeterMessageList:[],
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
                $.get(baseURL + "customerMeter/"+id+"/del", function(r){
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

            var url = vm.app.id == null ? "customerMeter/addCustomerMeter" : "customerMeter/updateCustomerMeter";
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
            $.get(baseURL + "customerMeter/"+id+"/info", function(r){
                if (r.permissionCheck) {
                    alert(r.msg);
                    return;
                }

                vm.app = r.customerMeter;
              /*  delete vm.app.createTime;*/
                vm.app.enableDate  =  vm.app.enableDateForHtml;
                $("#meterCustomerCode").attr("readonly","readonly");
                $("#meterCustomerCustCode").attr("readonly","readonly");


            });
        },
        reload: function () {
            $("#meterCustomerCode").removeAttr("readonly");
            $("#meterCustomerCustCode").removeAttr("readonly");
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            if(vm.q.factoryId == -100)
                vm.q.factoryId = null
            if(vm.q.validDate == 0)
                vm.q.validDate = null
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'custCode': vm.q.custCode,'validDate':vm.q.validDate,'factoryId':vm.q.factoryId},
                page:page
            }).trigger("reloadGrid");
        },
        validator: function () {
            /*
            *  id:'',
            code:'',
            custCode:'',
            custId:'',
            enableDate:'',
            isDelete:'',
            priceType:'',
            size:'',
            customerName:'',
            meterName:'',
            factoryName:'',
            waterFee:'',
            sewageFee:'',
            enableDateForHtml:'',
            validDate:'',
            factoryName:'',
            * */
              if(isBlank(vm.app.code)){
                  alert("用户水表关系编码不能为空");
                  return true;
              }
              if(isBlank(vm.app.custCode)){
                  alert("顾客编码不能为空");
                  return true;
              }

              if(isBlank(vm.app.enableDate)){
                  alert("有效期不能为空");
                  return true;
              }
            if(isBlank(vm.app.priceType)){
                alert("价格类型不能为空");
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
    },error:function (returnJsonData) {

    }
});
//页面加载时拿到所有的顾客编码
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
//页面加载时拿到所有的水表编码
$.ajax({
    async: false, // 同步
    type: 'GET',
    url: "/getIdAndName/getMeterMessage",
    dataType: "json",
    contentType: 'application/json',
    success: function (returnJsonData) {
        vm.MeterMessageList = [];
        for(var i = 0 ; i < returnJsonData.length ; i ++){
            var tepm = {id:returnJsonData[i].id,name:returnJsonData[i].name,idAndName:returnJsonData[i].idAndName};
            vm.MeterMessageList.push(tepm);
        }
        console.log(vm.MeterMessageList);
    },error:function (returnJsonData) {

    }
});
//页面加载时拿到所有的priceType编码
$.ajax({
    async: false, // 同步
    type: 'GET',
    url: "/getIdAndName/getPriceTypeMessage",
    dataType: "json",
    contentType: 'application/json',
    success: function (returnJsonData) {
        vm.PriceTypeMessageList = [];
        for(var i = 0 ; i < returnJsonData.length ; i ++){
            var tepm = {id:returnJsonData[i].id,name:returnJsonData[i].name,idAndName:returnJsonData[i].idAndName};
            vm.PriceTypeMessageList.push(tepm);
        }
        console.log(vm.PriceTypeMessageList);
    },error:function (returnJsonData) {
    }
});