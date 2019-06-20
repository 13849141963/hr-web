var vm = new Vue({
    el: '#rrapp',
    data: {
        //公司名称
        companyNames: getCompanyName(),
        //入职手续
        entryProcedures: getEntryProcedures(),
    },
    methods: {
        search: function () {

        }
    }
});

function getCompanyName() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/customerCompany?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = data.successResult;
        }
    });
    return result;
}

function getEntryProcedures() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/entryProcedures?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = data.successResult;
        }
    });
    return result;
}



$(function() {
    $("#jqGrid").jqGrid({
        url: '../entryOnline/queryEntryTask?_' + $.now(),
        datatype: "json",
        colModel: [{
            label: 'id',
            name: 'creator',
            key: true,
            hidden: true
        }, {
            label: '任务编号',
            name: 'taskId',
            width : 50
        }, {
            label: '任务名称',
            name: 'taskName',
            width : 50
        }, {
            label: '适用公司',
            name: 'busiCusts',
            width : 80,
            formatter: function (value) {
                return mapBusiCustNames(value);
            }
            
        }, {
            label: '关联手续',
            name: 'procedures',
            width : 150,
            formatter: function (value) {
                return mapEntryProcedures(value);
            }
        }, {
            label: '关联注册员工',
            name: 'registerNum',
            width : 50
        }, {
            label: '关联导入员工',
            name: 'importNum',
            width : 50
        }, {
			label : '操作',
			name : '',
			width : 80,
			index : 'operate',
			align : 'center',
			formatter : function(cellvalue, options, rowObject) {
				/*var detail ='<a data-toggle="modal" href="">生成链接、二维码</a>';*/
				var detail = '<button onclick="editInfo(\''+rowObject.taskName+'\')" class="btn btn-primary btn-xs">生成链接、二维码</button>';
				return detail;
			}
         }
        ],
        viewrecords: true,
        height: 400,
        rowNum: 10,
        rownumbers: true,
        rownumWidth: 25,
        multiselect: false,
        autowidth: true,
        pginput: true,
        rowList: [10, 50, 100,500,1000],
        pgbuttons: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
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

    $("#gotoTaskCreate").click(function () {
        window.parent.document.getElementById("11_menuId").click();
    })
   
    
})

function editInfo(obj) {

     var data = {'taskName':obj};
     $.ajax({
     type: "POST",
     url: '../entryOnline/getPcEntryLink?_' + $.now(),
     dataType:'json',
     data: data,
     success: function(data){
    // $('#pcImg').attr("src","../entryOnline/erweiCode?pcEntryLink="+data);
    //     vm.pcImg = "../entryOnline/erweiCode?pcEntryLink=www.baidu.com";
    // $("#pcUrl").val("666666");
    //     vm.pcUrl = "66666";
         document.getElementById("pcImg").src="../entryOnline/erweiCode?pcEntryLink=https://"+data;
         $("#pcUrl").html(data);
     }
     });

    $('#myModal').modal('show');

}

function mapBusiCustNames(val) {
    var result = '';
    try{
        val.forEach(function(value, index, array) {
                result += value.busiCustName+",";
        });
    }catch(e){
    }
    return result.substr(0,result.length-1);
}

function mapEntryProcedures(val) {
    var result = '';
    var list = val.split(",");
    try{
        list.forEach(function (val1, ind, arr) {
            try{
            vm.entryProcedures.forEach(function(value, index, array) {
                if(value.procedureId == val1){
                    result += value.procedureName+",";
                    throw BreakException;
                }
            });
            }catch(e){
            }
        });

    }catch(e){
    }
    return result.substr(0,result.length-1);
}
