$(function() {
	$("#jqGrid").jqGrid({
		url : '../sys/log/getLogs?_' + $.now(),
		datatype : "json",
		colModel : [ {
			label : 'logId',
			name : 'logId',
			key : true,
			hidden : true
		},{
			label : 'logId',
			name : 'logId'
		}, {
			label : '时间',
			name : 'logTime',
			formatter : function(value) {
				return new Date(value).toLocaleString() ;
			}
		}, {
			label : '唯一号',
			name : 'uniqNo'
		}, {
			label : '入参',
			name : 'inputParameter'
		}, {
			label : '出参',
			name : 'outputParameter'
		}, {
			label : '方法名',
			name : 'methodName'
		}, {
			label : '客户端',
			name : 'dataSource',
			formatter : function(value) {
				return value!=2?'微信':'APP';
			}
		}, {
			label : '备注',
			name : 'remark',
			formatter : function(value) {
				return value?value:'';
			}
		} ],
		viewrecords : true,
		height : 400,
		rowNum : 10,
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pginput : true,
		pgbuttons : true,
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
var timestamp1 = null,
	timestamp2 = null;
var vm = new Vue({
	el : '#rrapp',
	data : {
		searchData : {
			beginDate : '',
			endDate : '',
			uniqNo : '',
			inputParameter : '',
			outputParameter : '',
			methodName : '',
			dataSource : '',
			logId : '',
			remark : '',
			page : 1
		},
		clients : [ {
			'id' : '1',
			'name' : '微信'
		}, {
			'id' : '2',
			'name' : 'APP'
		} ]
	},
	methods : {
		search : function() {
			var begin = $('.applyDateBegin').val(), end = $('.applyDateEnd')
					.val();
			if (!begin || !end || begin == '' || end == '') {
				vm.searchData.beginDate = '';
				vm.searchData.endDate = '';
				timestamp1 = null;
				timestamp2 = null;
			} else {
				vm.searchData.beginDate = begin;
				vm.searchData.endDate = end;
				begin += " 00:00:00";
				end += " 23:59:59";
				timestamp1 = Date.parse(new Date(begin));
				timestamp2 = Date.parse(new Date(end));
				
			}
			var searchDatas ={
					beginDate:timestamp1,
					dataSource:vm.searchData.dataSource,
					endDate:timestamp2,
					inputParameter:vm.searchData.inputParameter,
					logId:vm.searchData.logId,
					methodName:vm.searchData.methodName,
					outputParameter:vm.searchData.outputParameter,
					page:vm.searchData.page,
					remark:vm.searchData.remark,
					uniqNo:vm.searchData.uniqNo
			};
			$("#jqGrid").jqGrid('setGridParam', {
			    page:1,
				postData : searchDatas
			}).trigger("reloadGrid");
		}
	}
});
