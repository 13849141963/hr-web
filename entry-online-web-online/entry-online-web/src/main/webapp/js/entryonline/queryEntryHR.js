$(function() {
	var searchDataStr = sessionStorage.getItem("searchData_data");
    if(searchDataStr != '' && searchDataStr != null){
        vm.searchData = JSON.parse(searchDataStr);
    }
	$("#jqGrid").jqGrid({
		url : '../entryOnline/queryEntrySituationHR?_'+ $.now(),
		datatype : "json",
		colModel : [ {
			label : 'id',
			name : 'taskId',
			key : true,
			hidden : false,
            width : 80,
			//hidden : true
		}, {
			label : '姓名',
			name : 'empName'
		}, {
			label : '品牌',
			name : 'brand',
            formatter: function (value) {
                return mapCompanyNames(value);
            }
		}, {
			label : '店号',
			name : 'empDepName'
		}, {
			label : '职务',
			name : 'zhiwu',
            formatter: function (value) {
                return mapEmpType(value);
            }
		}, {
			label : '用工类型',
			name : 'yonggongType',
            formatter: function (value) {
                return mapJobType(value);
            }
		}, {
			label : '身份证号',
			name : 'idCode',
		}, {
			label : '手机号',
			name : 'mobile',
		}, {
			label : '社保手续',
			name : 'shebaoProcedures',
            formatter: function (value) {
                return mapShebaoProcedures(value);
            }
		}, {
			label : '入职登记',
			name : 'entryRegister',
            formatter: function (value) {
                return mapEntryRegister(value);
            }
		}, {
			label : '人事档案',
			name : 'personalRecords',
            formatter: function (value) {
                return mapDanganProcedures(value);
            }
		},{
            label: '入职申请确认',
            name: 'confirmStatusName',
        }],
		viewrecords : true,
		height : 400,
		rowNum : 10,
		rownumbers : false,
		multiselect:true,
		rownumWidth : 25,
		autowidth : true,
		pginput : true,
		pgbuttons : true,
        rowList: [10,50,100,500,1000],
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
        postData:vm.searchData,
		prmNames : {
			page : "page",
			rows : "rows"
		},
		toolbar: [true, "top"],
		gridComplete : function() {
			// 隐藏grid底部滚动条
		/*	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});*/
		}
	});


});



var vm = new Vue({
    el : '#rrapp',
    data : {
        searchData : {
            busiCustNos: [],
            taskStatus: "",
            intoChangeFlag: "",
            empStatus: "",
            jobType: "",
            empType: "",
            beginDate:"",
            endDate:"",
            SocialSecurityType:"",
            userInfoType:"",
            userInfoContent:"",
        },
        //按公司名称检索
        companyNames : getCompanyName(),
        //门店名list
        storeNames : getStoreNames(),
        //按手续状态检索
        TaskStatus:getTaskStatus(),
        //是否需要转入变更
        IntoChangeFlag:getIntoChangeFlag(),
        //按员工状态检索
        EmpStatus:getEmpStatus(),
        //按员工类型检索
        JobTypes:getJobTypes(),
        //按员工职务检索
        EmpTypes:getEmpTypes(),
        //按社保手续检索
        SocialSecurityTypes:getSocialSecurityTypes(),
        //入职手续状态
        EntryStatus:getEntryStatus(),
        //社保手续状态
        SocialSecurityStatus:getSocialSecurityStatus(),
        //档案手续状态
        ProfileStatus:getProfileStatus(),
        //入职体检手续状态
        PhysicalExaminationStatus:getPhysicalExaminationStatus(),
        //劳动合同手续状态
        ContractStatus:getContractStatus(),
        staffMessage:[{itemCode: "1", itemName: "姓名"}, {itemCode: "2", itemName: "手机号"}, {
            itemCode: "3",
            itemName: "身份证号"
        }],
    },
    methods : {
        search : function() {
			 $("#jqGrid").jqGrid('setGridParam', {
			 postData : vm.searchData,
			 page : 1
			 }).trigger("reloadGrid");
        },
        changeSth : function() {
            alert(111)
        }
    }
});




function getCompanyName() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryCompanyNamesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getStoreNames() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryStoreNamesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getTaskStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryTaskStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
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
        url: '../entryOnline/queryIntoChangeFlagDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getEmpStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryEmpStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
            console.log(result);
        }
    });
    return result;
}

function getJobTypes() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryJobTypesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getEmpTypes() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryEmpTypesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getSocialSecurityTypes() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/querySocialSecurityTypesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getEntryStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryEntryStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getSocialSecurityStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/querySocialSecurityStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getProfileStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryProfileStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

function getPhysicalExaminationStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryPhysicalExaminationStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}
function getContractStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async:false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryContractStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result= JSON.parse(data);
        }
    });
    return result;
}

//formatter

function mapCompanyNames(val) {
    var result = '';
    try {
        vm.companyNames.forEach(function (value, index, array) {
            if (value.busiCustId == val) {
                result = value.busiCustName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}


function mapEmpType(val) {
    var result = '';
    try{
        vm.EmpTypes.forEach(function(value, index, array) {
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
        vm.JobTypes.forEach(function(value, index, array) {
            if(value.itemCode == val){
                result = value.itemName;
                throw BreakException;
            }
        });
    }catch(e){
    }
    return result;
}

function mapShebaoProcedures(val) {
    var result = '';
    try{
        vm.SocialSecurityStatus.forEach(function(value, index, array) {
            if(value.itemCode == val){
                result = value.itemName;
                throw BreakException;
            }
        });
    }catch(e){
    }
    return result;
}

function mapEntryRegister(val) {
    var result = '';
    try{
        vm.EntryStatus.forEach(function(value, index, array) {
            if(value.itemCode == val){
                result = value.itemName;
                throw BreakException;
            }
        });
    }catch(e){
    }
    return result;
}

function mapDanganProcedures(val) {
    var result = '';
    try{
        vm.ProfileStatus.forEach(function(value, index, array) {
            if(value.itemCode == val){
                result = value.itemName;
                throw BreakException;
            }
        });
    }catch(e){
    }
    return result;
}

function mapStoreNames(val) {
    var result = '';
    try{
        vm.storeNames.forEach(function(value, index, array) {
            if(value.orgId == val){
                result = value.orgName;
                throw BreakException;
            }
        });
    }catch(e){
    }
    return result;
}



function getSeleteRowIDs() {
    var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
}

$(function () {
    $("#t_jqGrid").append("全选&nbsp<input class='form-check-input' id='checkAll' type='checkbox'><span>&nbsp&nbsp</span>反选&nbsp<input id='oppoSiteCheck' type='checkbox'><span>&nbsp&nbsp&nbsp</span><span><button id = 'downLoadEntryExcel' class='btn btn-primary btn-xs' type='button'>员工数据下载导出</button><span/>");
    $("#cb_jqGrid").attr("style","display:none;");
    $("#checkAll").click(function(){
    	$("#cb_jqGrid").click();
    	$("#oppoSiteCheck").prop("checked",false);
    });
    
     $("#oppoSiteCheck").click(function(){
    	 console.log("1111111111");
    	 //$("#jqGrid").resetSelection();
    	   var ids = $("#jqGrid").jqGrid('getDataIDs');
    	   ids.forEach(function(val){
    		 	 $("#jqGrid").jqGrid("setSelection",val);
    	   });
    	   allchk();
    	 
    /*	 var chknum = $(".cbox.checkbox").size();
    	 console.log(chknum);
    	 $(".cbox.checkbox").each(function () {  
    	        $(this).prop("checked", !$(this).prop("checked"));  
    	    });
    		allchk();*/
    	
    });
    function allchk(){
    	var chknum = $(".cbox.checkbox").size();
    	var chk = $('#jqGrid').getGridParam('rows');
    	//选项总个数
    	$(".cbox.checkbox").each(function () {  
            if($(this).prop("checked")==true){
    			chk++;
    		}
        });
    	if(chknum==chk){//全选
    		$("#checkAll").prop("checked",true);
    		$("#cb_jqGrid").click();
    	}else{//不全选
    		$("#checkAll").prop("checked",false);
    	}
    }
    
    
    $("#downLoadEntryExcel").click(function(){

        var validateUrl ="../entryOnline/validateExportEntryListHr?_";

        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");

        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/ExportEntryListHr?_" + $.now();

        temp_form.method = "post";

        var param = null;

        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if(selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 "+selectedIds.length+" 条数据!";
        }else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if(ids.length == 0){
                layer.msg("无可下载数据", {time: 1000, icon: 5});
                document.body.removeChild(temp_form);
                return;
            }
            param = ids;
            input.value = ids;
            text = "确认下载当前页全部数据!";
        }
        // 将该输入框插入到 form 中
        temp_form.appendChild(input);
        //
        layer.confirm(text,{
            bth:['确定','取消'],
            icon: 3,
            title:'提示'
        },function(index){
            var rowsid = input.value;
            var arr=new Array();

            for(var i=0; i<rowsid.length; i++){
                arr[i]= rowsid[i];
            }
            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data:{"empTaskIds":param},
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                        temp_form.submit();
                        document.body.removeChild(temp_form);
                        layer.close(index);
                    } else {
                        layer.msg(data.errorMsg, {time: 2000, icon: 5});
                        document.body.removeChild(temp_form);
                        layer.close(index);
                    }
                }
            })
        },function(index){
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });
      /*  var a = confirm(text);
        if(a == true){
            temp_form.submit();

            document.body.removeChild(temp_form);
        }else {
            document.body.removeChild(temp_form);
            return;
        }*/

    })
})
