var vm = new Vue({
    el: '#rrapp',
    data: {
        searchData: {
            insStatus: "",
            jobType: "",
            intoChangeFlag: "",
            userInfoType: "",
            userInfoContent: "",
            empStatus:"",
            companyName: ["CUST003", "CUST001"],
        },
        checkStatus: getInsStatus(),
        jobTypes :getJobType(),
        empStatus : getmpStatus(),
        companyNames : getCompanyName(),
        switchStatus : getIntoChangeFlag(),
        userInfoTypes: [{itemCode: "1", itemName: "姓名"}, {itemCode: "2", itemName: "手机号"}, {
            itemCode: "3",
            itemName: "身份证号"
        }],
        empTypes: getEmpTypes(),
        socialecurityTypes :[{itemCode: "1", itemName: "新参"},{itemCode: "2", itemName: "转入"}],
    },
    methods: {
        search: function () {
            /*    var postData = vm.searchData;
             postData.applyDateBegin = $('.applyDateBegin').val();
             postData.applyDateEnd = $('.applyDateEnd').val();
             postData.reimbStartTime = $('.reimbStartTime').val();
             postData.reimbEndTime = $('.reimbEndTime').val();
             for (d in postData) {
             postData[d] = $.trim(postData[d]);
             }*/
           /* sessionStorage.setItem("searchData_data", JSON.stringify(postData));*/
            $("#jqGrid").jqGrid('setGridParam', {
                postData: vm.searchData,
                page: 1
            }).trigger("reloadGrid");
        },
        changeSth: function () {
            alert(111);
        }
    },
    mounted: function () {
    }

});

function getEmpTypes() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryDictForShebao?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data.empTypes);
        }
    });
    return result;
}

function getInsStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryDictForShebao?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data.insStatus);
        }
    });
    console.log(result);
    return result;
}

function getJobType() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryDictForShebao?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data.jobType);
        }
    });
    return result;
}

function getIntoChangeFlag() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryDictForShebao?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data.intoChangeFlag);

        }
    });
    return result;
}

function getmpStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryDictForShebao?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result =  JSON.parse(data.empStatus);
        }
    });
    return result;
}


function getCompanyName() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryDictForShebao?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data.companyName);
        }
    });
    return result;
}


$(function () {
    var searchDataStr = sessionStorage.getItem("searchData_data");
    if (searchDataStr != '' && searchDataStr != null) {
        vm.searchData = JSON.parse(searchDataStr);
    }
    $("#jqGrid").jqGrid({
        url: '../entryOnline/queryShebao?',
        datatype: "json",
        colModel: [{
            label: 'id',
            name: 'busiCustNo',
            key: true,
            hidden: true
        }, {
            label: '姓名',
            name: 'empName'
        }, {
            label: '所在公司',
            name: 'companyName',
        }, {
            label: '身份证号',
            name: 'idCode'
        }, {
            label: '手机号',
            name: 'mobile'
        }, {
            label: '员工状态',
            name: 'empStatus'
        }, {
            label: '审核进度',
            name: 'insStatus',
            formatter: function (value) {
                return mapInsStatus(value);
            }
        }, {
            label: '社保类型',
            name: 'insAddType',
            formatter: function (value) {
                return mapInsAddType(value);
            }
        }, {
            label: '缴费人员类别',
            name: 'payFeeType',
            formatter: function (value) {
                return mapPayFeeType(value);
            }
        }, {
            label: '员工类型',
            name: 'jobType',
            formatter: function (value) {
                return mapJobType(value);
            }
        }, {
            label: '是否转入变更',
            name: 'intoChangeFlag',
            formatter: function (value) {
                return mapIntoChangeFlag(value);
            }
        }, {
            label: '身份证照片',
            name: 'empTaskId',
        }, {
            label: '社保新参表',
            name: 'empTaskId',
        }, {
            label: '社保照片',
            name: 'empTaskId',
        }, {
            label: '审核失败原因',
            name: 'insFailReason',

        }],
        viewrecords: true,
        mtype: "post",
        height: 400,
        rowNum: 10,
        rownumbers: true,
        rownumWidth: 25,
        multiselect: true,
        autowidth: true,
        pginput: true,
        rowList: [10, 15, 30],
        pgbuttons: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "result",
            page: "page",
            total: "page",
            records: "page"
        },
        datatype: "json",
        postData: vm.searchData,
        prmNames: {
            page: "page",
            rows: "rows",
        },
        toolbar: [true, "top"],
        gridComplete: function () {
            // 隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({
                "overflow-x": "hidden"
            });
        }
    });


});
function mapInsStatus(val) {
    var result = '';
    try{
    vm.checkStatus.forEach(function(value, index, array) {
        if(value.itemCode == val){
            result = value.itemName;
            throw BreakException;
        }
    });
    }catch(e){
    }
    return result;
}
function mapInsAddType(val) {
    var result = '';
    try{
    vm.socialecurityTypes.forEach(function(value, index, array) {
        if(value.itemCode == val){
            result = value.itemName;
            throw BreakException;
        }
    });
    }catch(e){
    }
    return result;
}
function mapPayFeeType(val) {
    var result = '';
    try{
    vm.empTypes.forEach(function(value, index, array) {
        if(value.itemCode == val){
            result = value.itemName;
            throw BreakException;
        }
    });
    }catch(e){
    }
    return result;
}
function mapJobType(val) {
    var result = '';
    try{
    vm.jobTypes.forEach(function(value, index, array) {
        if(value.itemCode == val){
            result = value.itemName;
            throw BreakException;
        }
    });
    }catch(e){
    }
    return result;
}
function mapIntoChangeFlag(val) {
    var result = '';
    try{
    vm.switchStatus.forEach(function(value, index, array) {
        if(value.itemCode == val){
            result = value.itemName;
            throw BreakException;
        }
    });
    }catch(e){
    }
    return result;
}
$(function () {
    $("#t_jqGrid").append("正选<input type='checkbox'>反选<input type='checkbox'><span><button type='button'>批量下载身份证</button><span/><span><button type='button'>批量下载户口本</button><span/><span><button type='button'>批量打印新参表</button><span/>");
})