$(function () {
    $("#jqGrid").jqGrid({
        url: '../entryOnline/queryEntrySituation?_' + $.now(),
        datatype: "json",
        colModel: [{
            label: 'id',
            name: 'taskId',
            key: true,
            hidden: false,
            width: 80,
            //hidden: true
        }, {
            label: '姓名',
            name: 'empName'
        }, {
            label: '所在公司',
            name: 'company',
            formatter: function (value) {
                return mapCompanyNames(value);
            }
        }, {
            label: '身份证号',
            name: 'idCode'
        }, {
            label: '手机号',
            name: 'mobile'
        }, {
            label: '入职日期',
            name: 'entryTime'
        }, {
            label: '员工状态',
            name: 'employeeState',
            formatter: function (value) {
                return mapEmployeeState(value);
            }
        }, {
            label: '已预派业务客户',
            name: 'orderCompany'
        }, {
            label: '订单起始时间',
            name: 'orderTime',
        }, {
            label: '社保手续',
            name: 'shebaoProcedures',
            formatter: function (value) {
                return mapShebaoProcedures(value);
            }
        }, {
            label: '入职登记',
            name: 'entryRegister',
            formatter: function (value) {
                return mapEntryRegister(value);
            }
        }, {
            label: '档案手续',
            name: 'danganProcedures',
            formatter: function (value) {
                return mapDanganProcedures(value);
            }
        }, {
            label: '入职体检',
            name: 'entryTest',
            formatter: function (value) {
                return mapEntryTest(value);
            }
        }, {
            label: '劳动合同',
            name: 'laodongHetong',
            formatter: function (value) {
                return mapLaodongHetong(value);
            }
        }, {
            label: '入职申请确认',
            name: 'confirmStatusName',
        }, {
            label: '完成状态',
            name: 'syncMisFlag',
            formatter: function (value) {
                return mapSyncMisFlag(value);
            }
        }, {
            label: '备注',
            name: 'remark'
        }],
        viewrecords: true,
        height: 400,
        rowNum: 10,
        shrinkToFit:false,
        autoScroll: true,
        /*rownumbers: true,*/
        rownumWidth: 25,
        multiselect: true,
        autowidth: true,
        pginput: true,
        pgbuttons: true,
        rowList: [10, 50, 100, 500, 1000],
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
            rows: "rows"
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
                busiCustNos: [],
                taskStatus: "",
                intoChangeFlag: "",
                empStatus: "",
                jobType: "",
                empType: "",
                beginDate: '',
                endDate: '',
                handleMonth: '',
                SocialSecurityType: "",
                userInfoType: "",
                userInfoContent: "",
                //完成状态
                syncMisFlag:"",
            },
            //按公司名称检索
            companyNames: getCompanyName(),
            //按手续状态检索
            TaskStatus: getTaskStatus(),
            //是否需要转入变更
            IntoChangeFlag: getIntoChangeFlag(),
            //按员工状态检索
            EmpStatus: getEmpStatus(),
            //按员工类型检索
            JobTypes: getJobTypes(),
            //按员工职务检索
            EmpTypes: getEmpTypes(),
            //按社保手续检索
            SocialSecurityTypes: getSocialSecurityTypes(),
            //入职手续状态
            EntryStatus: getEntryStatus(),
            //社保手续状态
            SocialSecurityStatus: getSocialSecurityStatus(),
            //档案手续状态
            ProfileStatus: getProfileStatus(),
            //入职体检手续状态
            PhysicalExaminationStatus: getPhysicalExaminationStatus(),
            //劳动合同手续状态
            ContractStatus: getContractStatus(),
            staffMessage: [{itemCode: "1", itemName: "姓名"}, {itemCode: "2", itemName: "手机号"}, {
                itemCode: "3",
                itemName: "身份证号"
            }],
            CompleteStatus:getCompleteStatus(),
        },
        methods: {
            search: function () {
                $("#jqGrid").jqGrid('setGridParam', {
                    postData: vm.searchData,
                    page: 1
                }).trigger("reloadGrid");
            },
            changeSth: function () {
                alert(111)
            },
            changeTime1: function () {
                this.searchData.beginDate = $("#v-time1").val();
                /* var date =  new Date($("#v-time1").val());
                 date= dateAdd("h",-8,date);
                 var timestamp1 = Date.parse(date);
                 this.searchData.beginDate = timestamp1;
                 console.log(new Date(timestamp1).format('yyyy-MM-dd hh:mm:ss'));*/
            },
            changeTime2: function () {
                this.searchData.endDate = $("#v-time2").val();
                /* var date = new Date($("#v-time2").val());
                date = dateAdd("h", -8, date);
                date = dateAdd("h", 23, date);
                date = dateAdd("m", 59, date);
                date = dateAdd("s", 59, date);
                var timestamp2 = Date.parse(date);
                this.searchData.endDate = timestamp2;
                console.log(new Date(timestamp2).format('yyyy-MM-dd hh:mm:ss'));
                 */
            },
            changeTime3: function () {
                this.searchData.handleMonth = $("#v-time3").val();
                /*  var date = new Date($("#v-time3").val());
               date = dateAdd("h", -8, date);
               var timestamp3 = Date.parse(date);
               this.searchData.handleMonth = timestamp3;
               console.log(new Date(timestamp3).format('yyyy-MM-dd hh:mm:ss'))
                 */
            }
        }
    })
    ;


function getCompanyName() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryCompanyNamesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getTaskStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryTaskStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getIntoChangeFlag() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryIntoChangeFlagDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getEmpStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryEmpStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
            console.log(result);
        }
    });
    return result;
}

function getJobTypes() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryJobTypesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getEmpTypes() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryEmpTypesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getSocialSecurityTypes() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/querySocialSecurityTypesDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getEntryStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryEntryStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getSocialSecurityStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/querySocialSecurityStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getProfileStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryProfileStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

function getPhysicalExaminationStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryPhysicalExaminationStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}
function getContractStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryContractStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}
function getCompleteStatus() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,
        //url: '../entryOnline/entryProcedures?_' + $.now(),
        url: '../entryOnline/queryCompleteStatusDict?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = JSON.parse(data);
        }
    });
    return result;
}

//formatter


function mapSyncMisFlag(val) {
    var result = '';
    try {
        vm.CompleteStatus.forEach(function (value, index, array) {
            if (value.itemCode == val) {
                result = value.itemName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}
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
function mapEmployeeState(val) {
    var result = '';
    try {
        vm.EmpStatus.forEach(function (value, index, array) {
            if (value.itemCode == val) {
                result = value.itemName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}

function mapShebaoProcedures(val) {
    var result = '';
    try {
        vm.SocialSecurityStatus.forEach(function (value, index, array) {
            if (value.itemCode == val) {
                result = value.itemName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}

function mapEntryRegister(val) {
    var result = '';
    try {
        vm.EntryStatus.forEach(function (value, index, array) {
            if (value.itemCode == val) {
                result = value.itemName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}

function mapDanganProcedures(val) {
    var result = '';
    try {
        vm.ProfileStatus.forEach(function (value, index, array) {
            if (value.itemCode == val) {
                result = value.itemName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}

function mapEntryTest(val) {
    var result = '';
    try {
        vm.PhysicalExaminationStatus.forEach(function (value, index, array) {
            if (value.itemCode == val) {
                result = value.itemName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}

function mapLaodongHetong(val) {
    var result = '';
    try {
        vm.ContractStatus.forEach(function (value, index, array) {
            if (value.itemCode == val) {
                result = value.itemName;
                throw BreakException;
            }
        });
    } catch (e) {
    }
    return result;
}

$(function () {
    //加头
    $("#t_jqGrid").append("全选&nbsp<input id='checkAll' type='checkbox'><span>&nbsp&nbsp</span>反选&nbsp<input id='oppoSiteCheck' type='checkbox'><span>&nbsp&nbsp</span><span>&nbsp<button id='exportEntry' class='btn btn-primary btn-xs' type='button'>员工数据下载导出</button><span/><span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='downLoadShenfen' type='button'>批量下载身份证</button><span/><span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='downLoadHukou'type='button'>批量下载户口本</button><span/>" +
        "<span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='exportEmpInfos'type='button'>批量下载员工信息表</button><span/>" +
        "<span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='downloadLoadCunDangZhengMings'type='button'>批量下载存档证明</button><span/>" +
        "<span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='downloadLoadZhuanDangXieYis'type='button'>批量下载转档协议</button><span/>" +
        "<span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='downloadLoadNewInsPics'type='button'>批量下载社保照片</button><span/>" +
        "<span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='batchPrintJobNewIns'type='button'>批量下载工伤新参表</button><span/>" +
        "<span>&nbsp&nbsp</span><span>&nbsp<button class='btn btn-primary btn-xs' id='confirmPaperMaterial'type='button'>非全纸质材料确认</button><span/>");

    $("#cb_jqGrid").attr("style", "display:none;");
    $("#checkAll").click(function () {
        $("#cb_jqGrid").click();
        $("#oppoSiteCheck").prop("checked", false);
    });

    $("#oppoSiteCheck").click(function () {
        console.log("1111111111");
        //$("#jqGrid").resetSelection();
        var ids = $("#jqGrid").jqGrid('getDataIDs');
        ids.forEach(function (val) {
            $("#jqGrid").jqGrid("setSelection", val);
        });
        allchk();

        /*	 var chknum = $(".cbox.checkbox").size();
         console.log(chknum);
         $(".cbox.checkbox").each(function () {
         $(this).prop("checked", !$(this).prop("checked"));
         });
         allchk();*/

    });
    function allchk() {
        var chknum = $(".cbox.checkbox").size();
        var chk = $('#jqGrid').getGridParam('rows');
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


    $("#exportEntry").click(function () {
        var param = null;
        var validateUrl = "../entryOnline/validateExportEntryList?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/ExportEntryList?_" + $.now();
        temp_form.method = "post";
        document.body.appendChild(temp_form);
        var text = '';
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
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
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(",")},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });
        /* var a = confirm(text,"11");
         if(a == true){
         temp_form.submit();

         document.body.removeChild(temp_form);

         }else {
         document.body.removeChild(temp_form);
         return;
         }*/

    })


    // 下载身份证
    $("#downLoadShenfen").click(function () {
        var validateUrl = "../entryOnline/validateDownloadIdPics?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/downloadIdPics?_" + $.now();

        temp_form.method = "post";
        var param = null;


        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
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
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(",")},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });

    })

    // 下载户口本
    $("#downLoadHukou").click(function () {
        /*  	$.ajax({
         type: "POST",
         url: '../entryOnline/downloadLoadHouseholds?_' + $.now(),
         dataType:'json',
         data:{"empTaskIds":selectedIds},
         success: function(data){
         if(data.code == 0){
         alert("下载成功");
         }else{
         alert(data.errorMsg);
         }
         }
         });*/
        var validateUrl = "../entryOnline/validateDownloadLoadHouseholds?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
        var temp_form = document.createElement("form");

        var param = null;

        temp_form.action = "../entryOnline/downloadLoadHouseholds?_" + $.now();

        temp_form.method = "post";


        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
                layer.msg("无可下载数据", {time: 1000, icon: 5});
                document.body.removeChild(temp_form);
                return;
            }
            param = ids;
            input.value = ids;
            text = "确认下载当前页全部数据!"
        }
        // 将该输入框插入到 form 中
        temp_form.appendChild(input);
        //
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {
            $.ajax({
                type: "get",
                async: true,
                traditional:true,
                url: validateUrl + $.now(),
                data: {'empTaskIds':param},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });

    })

    // 批量下载员工信息表
    $("#exportEmpInfos").click(function () {
        var validateUrl = "../entryOnline/validateExportEmpInfos?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/ExportEmpInfos?_" + $.now();

        temp_form.method = "post";
        var param = null;


        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
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
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(",")},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });

    })

    // 批量下载工伤新参表
    $("#batchPrintJobNewIns").click(function () {
        var validateUrl = "../entryOnline/validatebatchPrintJobNewIns?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");


        if (vm.searchData.busiCustNos == '') {
            layer.msg("请选择公司名称!", {time: 2000, icon: 5});
            return;
        }

        if (vm.searchData.jobType == '') {
            layer.msg("请选择员工类型!", {time: 2000, icon: 5});
            return;
        }

        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/batchPrintJobNewIns?_" + $.now();

        temp_form.method = "post";
        var param = null;


        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
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
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(',')},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });

    })

    //批量下载存档证明
    $("#downloadLoadCunDangZhengMings").click(function () {
        var validateUrl = "../entryOnline/validateDownloadLoadCunDangZhengMings?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/downloadLoadCunDangZhengMings?_" + $.now();

        temp_form.method = "post";
        var param = null;


        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
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
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(",")},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });

    })


    //批量下载转档协议
    $("#downloadLoadZhuanDangXieYis").click(function () {
        var validateUrl = "../entryOnline/validateDownloadLoadZhuanDangXieYis?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/downloadLoadZhuanDangXieYis?_" + $.now();

        temp_form.method = "post";
        var param = null;


        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
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
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(",")},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });

    })

    //批量下载社保照片
    $("#downloadLoadNewInsPics").click(function () {
        var validateUrl = "../entryOnline/validateDownloadLoadNewInsPics?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
        var temp_form = document.createElement("form");
        temp_form.action = "../entryOnline/downloadLoadNewInsPics?_" + $.now();

        temp_form.method = "post";
        var param = null;


        document.body.appendChild(temp_form);

        var text = '';
        //
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "empTaskIds";
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            input.value = selectedIds;
            text = "确认下载选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
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
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(",")},
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
        }, function (index) {
            document.body.removeChild(temp_form);
            layer.close(index);
            return;
        });

    })


    //非纸质材料确认
    $("#confirmPaperMaterial").click(function () {
        var validateUrl = "../entryOnline/confirmPaperMaterial?_";
        var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");

        var param = null;

        var text = '';
        //
        if (selectedIds.length != 0) {
            //获取选中行
            param = selectedIds;
            text = "确认提交选中的 " + selectedIds.length + " 条数据!";
        } else {
            //获得所有行数据
            var ids = $("#jqGrid").jqGrid('getDataIDs');
            if (ids.length == 0) {
                layer.msg("无数据", {time: 2000, icon: 5});
                return;
            }

            if (vm.searchData.busiCustNos == '') {
                layer.msg("请选择公司名称!", {time: 2000, icon: 5});
                return;
            }

            if (vm.searchData.jobType == '') {
                layer.msg("请选择员工类型!", {time: 2000, icon: 5});
                return;
            }

            param = ids;
            text = "确认提交当前页全部数据!";
        }
        //
        var a = layer.confirm(text, {
            bth: ['确定', '取消'],
            icon: 3,
            title: '提示'
        }, function (index) {

            $.ajax({
                type: "get",
                async: true,
                url: validateUrl + $.now(),
                data: {"empTaskIds":param.join(',')},
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg("提交成功!", {time: 2000, icon: 6});
                        layer.close(index);
                    } else {
                        layer.msg(data.errorMsg, {time: 5000, icon: 5});
                        layer.close(index);
                    }
                }
            })
        }, function (index) {
            layer.close(index);
            return;
        });

    })

})


