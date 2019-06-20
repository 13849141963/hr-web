$(function() {
	$("#jqGrid").jqGrid({
		url : '../reimb/demo/list?_' + $.now(),
		datatype : "json",
		colModel : [ {
			label : '单号1',
			name : 'id',
			width : 100,
			key : true,
			hidden : true
		}, {
			label : '单号',
			name : 'orderno',
			width : 100
		}, {
			label : '唯一号',
			name : 'hno',
			width : 75
		}, {
			label : '姓名',
			name : 'username',
			width : 70
		}, {
			label : '提交日期',
			name : 'submitDate',
			width : 70
		}, {
			label : '费用类型',
			name : 'feeType',
			width : 70
		}, {
			label : '报销人员',
			name : 'reimber',
			width : 70
		}, {
			label : '报销比例',
			name : 'reimbProp',
			width : 70
		}, {
			label : '报销类型',
			name : 'reimbType',
			width : 70
		}, {
			label : '当前状态',
			name : 'orderSta',
			width : 70
		} ],
		viewrecords : true,
		height : 400,
		rowNum : 10,
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		pginput:false,
		pgbuttons:false,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "rows"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});

var vm = new Vue({
	el : '#rrapp',
	data : {},
	methods : {
		detail : function() {
			var allIDs = getAllIDs();
			if (allIDs == null) {
				return;
			}
			var id = allIDs.pop();
			location.href = "demo_detail.html?orderId="+id;
		},
		check : function() {
			var allIDs = getAllIDs();
			if (allIDs == null) {
				return;
			}
			var id = allIDs.pop();
			location.href = "demo_check.html?orderId="+id;
		}
	}
});

