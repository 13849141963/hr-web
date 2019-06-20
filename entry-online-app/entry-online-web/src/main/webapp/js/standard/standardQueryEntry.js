$(function () {
    $("#jqGrid").jqGrid({
   url: '../standard/queryEntryStatus?_' + $.now(),
  /// url: '/entry-online-web/statics/mock_data/queryEntryStatus.json?_' + $.now(),
        datatype: "json",
        colModel: [
        	{
                label: 'empTaskId',
                name: 'empTaskId',        
                key:true,
                width: 150,  
                hidden:true
            },{
            label: '任务编号',
            name: 'taskId',        
          //  key:true,
            width: 130,       
        }, {
            label: '雇员姓名',
            name: 'empName',
            width: 135,     
        }, {
            label: '所在公司',
            name: 'companyName',
            width:300
//            formatter: function (value) {
//             //   return mapCompanyNames(value);
//            }
        }, {
            label: '身份证号',
            name: 'idCode',
            width:160
        }, {
            label: '手机号',
            name: 'mobile',
            width:130
        },  {
            label: '员工状态',
            name: 'empStatus',
            width: 130, 
//            formatter: function (value) {
//                return mapEmployeeState(value);
//            }
        }, {
            label: '订单起始时间',
            name: 'myOrderStartTime',
            width:190
        }, {
            label: '入职登记',
            name: 'enrollStatus',
            width: 140, 
//            formatter: function (value) {
//                return mapEntryRegister(value);
//            }
        }, {
            label: '社保手续',
            name: 'insStatus',
            width: 140, 
//            formatter: function (value) {
//                return mapDanganProcedures(value);
//            }
        }, {
            label: '人事手续',
            name: 'personnelStatus',
            width: 140, 
//            formatter: function (value) {
//                return mapEntryTest(value);
//            }
        }],
        viewrecords: true,
        height: 400,
        rowNum: 10,
        shrinkToFit:false,
        autoScroll: true,
        rownumbers: true,
        rownumWidth: 25,
        multiselect: true,
        autowidth: true,     
        pginput: true,
        pgbuttons: true,
        rowList: [10, 20, 50,100,200,500],
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        postData:vm.searchData,
        prmNames: {
            page: "pageNo",
            rows: "pageSize"
        },
        toolbar: [true, "top"],
        gridComplete: function () {
            // 隐藏grid底部滚动条
           /* $("#jqGrid").closest(".ui-jqgrid-bdiv").css({
                "overflow-x": "hidden"
            });*/
        }
    });
});


var vm = new Vue({
        el: '#rrapp',
        data: {
            searchData: {
//                intoChangeFlag: "",               
            	taskId:"",  //  按任务编号检索
            	empName:"", //  按雇员姓名检索
            //	 busiCustNos: [], //公司名称
            	companyName:"",
            	 idCode:"",  //   按身份证号检索
            	 mobile:"",//    按手机号检索
                empStatus: "",   //员工状态
                orderStartTime:"",         //按订单开始日期
                orderEndTime:"",
                procedureType: "",  //手续状态检索
                procedureStatus:"" //办理状态
            },
            //按公司名称检索
          //  companyNames: getCompanyName(),
            //按手续列表
            ProceduresList: getProceduresList(),
            //按手续状态检索
            TaskStatus: getTaskStatus(),
            //是否需要转入变更
            //IntoChangeFlag: getIntoChangeFlag(),
            //按员工状态检索
            EmpStatus: getEmpStatus(),
        },
        methods: {
            search: function () {           	 
           	 var paramObj={};
              for( var key in vm.searchData){
            	  paramObj[key] = vm.searchData[key]
              }
            //  paramObj.empName=encodeURI(encodeURI(paramObj.empName));               
                $("#jqGrid").jqGrid('setGridParam', {
                    postData: paramObj,
                    page: 1
                }).trigger("reloadGrid");             
            },
            changeSth: function () {
                alert(111)
            },
            changeTime1: function () {
                this.searchData.orderStartTime = $("#v-time1").val();              
            },
            changeTime2: function () {
                this.searchData.orderEndTime = $("#v-time2").val();         
            }
        }
    })
    ;


function getCompanyName() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,       
        url: '../standard/customerCompany?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result =  data.successResult;
        }
    });
    return result;
}
//手续列表
function getProceduresList() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,       
        url: '../standard/getProceduresListDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {   
            result= data.successResult          
        }
    });
    return result;
}
function getTaskStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        url: '../standard/getProceduresStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {   
            result =  data.successResult;          
        }
    });
    return result;
}



function getEmpStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,        
        url: '../standard/getEmpStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {           
            result = data.successResult;
        }
    });
    return result;
}




//formatter
//function mapCompanyNames(val) {
//    var result = '';
//    try {
//        vm.companyNames.forEach(function (value, index, array) {
//            if (value.busiCustId == val) {
//                result = value.busiCustName;
//                throw BreakException;
//            }
//        });
//    } catch (e) {
//    }
//    return result;
//}
//function mapEmployeeState(val) {
//    var result = '';
//    try {
//        vm.EmpStatus.forEach(function (value, index, array) {
//            if (value.itemCode == val) {
//                result = value.itemName;
//                throw BreakException;
//            }
//        });
//    } catch (e) {
//    }
//    return result;
//}
//
//
//
//function mapEntryRegister(val) {
//    var result = '';
//    try {
//        vm.EntryStatus.forEach(function (value, index, array) {
//            if (value.itemCode == val) {
//                result = value.itemName;
//                throw BreakException;
//            }
//        });
//    } catch (e) {
//    }
//    return result;
//}
//
//function mapDanganProcedures(val) {
//    var result = '';
//    try {
//        vm.ProfileStatus.forEach(function (value, index, array) {
//            if (value.itemCode == val) {
//                result = value.itemName;
//                throw BreakException;
//            }
//        });
//    } catch (e) {
//    }
//    return result;
//}
//
//function mapEntryTest(val) {
//    var result = '';
//    try {
//        vm.PhysicalExaminationStatus.forEach(function (value, index, array) {
//            if (value.itemCode == val) {
//                result = value.itemName;
//                throw BreakException;
//            }
//        });
//    } catch (e) {
//    }
//    return result;
//}



$(function () {
    //加头
    $("#t_jqGrid").append("<span class='inputText'>全选</span>&nbsp<input id='checkAll' type='checkbox'><span>&nbsp&nbsp</span><span class='inputText'>反选</span>&nbsp<input id='oppoSiteCheck' type='checkbox'><span>&nbsp&nbsp</span><span>&nbsp<button id='exportEntry' class='btn btn-primary btn-xs' type='button' style='margin-top:-5px'>员工数据导出</button><span/>");
        

    $("#cb_jqGrid").attr("style", "display:none;");
    $("#checkAll").click(function () {
        $("#cb_jqGrid").click();      
        $(this).prop("checked", $("#cb_jqGrid")[0].checked);
        $("#oppoSiteCheck").prop("checked", false);
    });

    $("#oppoSiteCheck").click(function () {    
        //$("#jqGrid").resetSelection();
        var ids = $("#jqGrid").jqGrid('getDataIDs');
        ids.forEach(function (val) {
            $("#jqGrid").jqGrid("setSelection", val);
        });
        allchk();
    });
    function allchk() {
        var chknum = $(".cbox.checkbox").size();  
        var chk = $('#jqGrid').jqGrid("getGridParam","pageSize");
        //选项总个数
        $(".cbox.checkbox").each(function () {
            if ($(this).prop("checked") == true) {
                chk++;
            }
        });   
        if (chknum == chk) {//全选
            $("#checkAll").prop("checked", true);
            $("#cb_jqGrid").click();
        } else {//不全选
            $("#checkAll").prop("checked", false);
        }
    }  
   $(document).on("change","td .cbox.checkbox",function(){ 	  
	     allchk()
        $("#checkAll").prop("checked", $("#cb_jqGrid")[0].checked);
   })
    $("#exportEntry").click(function () {
        var param = null;
        var validateUrl = "../standard/export?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow"); 
        var text ="";
        if (selectedIds.length != 0) {//        	
            //获取选中行   
//        	param =[];
//        	$.each(selectedIds,function(i,v){
//             	var rowData = $("#jqGrid").jqGrid('getRowData',v);
//        		param.push(rowData.empTaskId)      
//        	}) 
            param = selectedIds;       
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
                layer.msg("无可下载数据", {time: 1000, icon: 5});              
                return;
            } 
            param = ids;    
            text = "确认下载当前页全部数据!";
        }    
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {
        	var data = JSON.stringify({"empTaskIds":param});     
        	var xmlhttp;
        	xmlhttp=new XMLHttpRequest();	
        	xmlhttp.open("POST",validateUrl + $.now(),true);
        	xmlhttp.responseType = "blob";
        	xmlhttp.onreadystatechange=function(){     
            if(xmlhttp.readyState==4){
            	if(xmlhttp.status==200){
            		 var blob = this.response;
            	     var elink = document.createElement('a');            	     
                     elink.download = "员工数据.xls";
                     elink.style.display = 'none';
                     //var blob = new Blob(this.response);
                     elink.href = URL.createObjectURL(blob);                 
                     document.body.appendChild(elink);
                     elink.click();
                     document.body.removeChild(elink); 	
                     layer.close(index);
            	}else if(xmlhttp.status==500){
            		layer.alert("服务器异常！请稍后再试！", {icon: 5});
            	}
                   
              }
            }
        	xmlhttp.send(data);        
        }, function (index) {           
            layer.close(index);
            return;
        });

    })


})  

