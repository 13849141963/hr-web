//jqGrid的配置信息
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

// 工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
};
T.p = url;
T.toolbars=[ 	
	'source',
    'undo', //撤销
    'redo', //重做
    'bold', //加粗
    'indent', //首行缩进   
    'formatmatch', //格式刷            
    'selectall', //全选
    'fontfamily', //字体
    'fontsize', //字号            
    'searchreplace', //查询替换
    'justifyleft', //居左对齐
    'justifyright', //居右对齐
    'justifycenter', //居中对齐
    'justifyjustify', //两端对齐
    'forecolor', //字体颜色
    'backcolor', //背景色
    'insertorderedlist', //有序列表
//    'insertunorderedlist', //无序列表
   // 'fullscreen', //全屏          
//    'rowspacingtop', //段前距
//    'rowspacingbottom', //段后距   
//    'lineheight', //行间距         
    //'background', //背景  
    ]
// layer扩展皮肤
if (parent && parent.layer) {
    parent.layer.config({
        skin: 'layui-layer-lan'
    });
}

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
};

// 全局配置
$.ajaxSetup({
    dataType: "json",
    contentType: "application/json",
    cache: false
});

// 重写alert
window.alert = function(msg, callback) {
    parent.layer.alert(msg, function(index) {
        parent.layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
};

window.msg = function(msg){
	parent.layer.msg(msg,{icon: 6,time: 2000});
};

window.loading = function(){
	parent.layer.load(1,{shade: [0.3,'#000']});
};

window.closeAll = function(){
	parent.layer.closeAll();
};

// 重写confirm式样框
window.confirm = function(msg, callback) {
    parent.layer.confirm(msg, {
        btn: ['确定', '取消']
    }, function() { // 确定事件
        if (typeof(callback) === "function") {
            callback("1");
        }
    });
};

// 选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只能选择一条记录");
        return;
    }
    return selectedIDs[0];
}

//
function getSelectedRowData() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }
    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只能选择一条记录");
        return;
    }
    var rowData = $("#jqGrid").jqGrid("getRowData",rowKey);
    return rowData;
}

// 选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    return grid.getGridParam("selarrrow");
}

// 选择所有记录
function getAllIDs() {
    var grid = $("#jqGrid");
    var arrIDs = grid.getDataIDs();
    if (!arrIDs || arrIDs.length == 0) {
        alert("当前没有数据");
        return;
    }
    return arrIDs;
}

// 获取订单状态
function getBillStatus(status) {
	var result = '',
		billStatuses = sessionStorage.getItem('billStatuses');
	if(!billStatuses){
		$.ajax({
		   type: "GET",
		   async:false,
		   url: "../reimb/dict/billstatus",
		   data: null,
		   success: function(data){
			   sessionStorage.setItem('billStatuses',JSON.stringify(data));
			   billStatuses = data;
		   }
		});
	}else{
		billStatuses = eval(billStatuses);
	}
	
	for(var billStatus in billStatuses){
		if(billStatuses[billStatus].nameKey == status){
			result = billStatuses[billStatus].nameValue;
			break;
		}
	}
	return result;
}

//费用类型
function getChargeSort(status) {
	var result = '',
		chargeSorts = sessionStorage.getItem('chargeSorts');
	if(!chargeSorts){
		$.ajax({
		   type: "GET",
		   async:false,
		   url: "../reimb/dict/chargesorts",
		   data: null,
		   success: function(data){
			   sessionStorage.setItem('chargeSorts',JSON.stringify(data));
			   chargeSorts = data;
		   }
		});
	}else{
		chargeSorts = eval(chargeSorts);
	}
	
	for(var chargeSort in chargeSorts){
		if(chargeSorts[chargeSort].nameKey == status){
			result = chargeSorts[chargeSort].nameValue;
			break;
		}
	}
	return result;
}

//报销对象
function getApplyer(status) {
	var result = '',
		applyers = sessionStorage.getItem('applyers');
	if(!applyers){
		$.ajax({
		   type: "GET",
		   async:false,
		   url: "../reimb/dict/applyers",
		   data: null,
		   success: function(data){
			   sessionStorage.setItem('applyers',JSON.stringify(data));
			   applyers = data;
		   }
		});
	}else{
		applyers = eval(applyers);
	}
	
	for(var applyer in applyers){
		if(applyers[applyer].nameKey == status){
			result = applyers[applyer].nameValue;
			break;
		}
	}
	return result;
}

//获取报销类型
function getApplySort(status) {
	var result = '',
		applys = sessionStorage.getItem('applySorts');
	if(!applys){
		$.ajax({
		   type: "GET",
		   async:false,
		   url: "../reimb/dict/applysorts",
		   data: null,
		   success: function(data){
			   sessionStorage.setItem('applySorts',JSON.stringify(data));
			   applys = data;
		   }
		});
	}else{
		applys = eval(applys);
	}
	
	for(var apply in applys){
		if(applys[apply].nameKey == status){
			result = applys[apply].nameValue;
			break;
		}
	}
	return result;
}


//退单方式
function getFenReturnType(status) {
	var result = '',
		fenReturnTypes = sessionStorage.getItem('fenReturnTypes');
	if(!fenReturnTypes){
		$.ajax({
		   type: "GET",
		   async:false,
		   url: "../reimb/dict/fenreturntypes",
		   data: null,
		   success: function(data){
			   sessionStorage.setItem('fenReturnTypes',JSON.stringify(data));
			   fenReturnTypes = data;
		   }
		});
	}else{
		fenReturnTypes = eval(fenReturnTypes);
	}
	
	for(var fenReturnType in fenReturnTypes){
		if(fenReturnTypes[fenReturnType].nameKey == status){
			result = fenReturnTypes[fenReturnType].nameValue;
			break;
		}
	}
	return result;
}

//社保已结
function getClearingSign(status) {
	var result = '',
		clearingSigns = sessionStorage.getItem('clearingSigns');
	if(!clearingSigns){
		$.ajax({
		   type: "GET",
		   async:false,
		   url: "../reimb/dict/clearingsigns",
		   data: null,
		   success: function(data){
			   sessionStorage.setItem('clearingSigns',JSON.stringify(data));
			   clearingSigns = data;
		   }
		});
	}else{
		clearingSigns = eval(clearingSigns);
	}
	
	for(var clearingSign in clearingSigns){
		if(clearingSigns[clearingSign].nameKey == status){
			result = clearingSigns[clearingSign].nameValue;
			break;
		}
	}
	return result;
}


function getCustomerCompanys() {
    return [{
        'id': '0',
        'name': '111奇虎360公司'
    }, {
        'id': '1',
        'name': 'AAAAA北京时间财富公司'
    }, {
        'id': '2',
        'name': '甲骨文软件有限公司'
    }];

}
/*function getProcedures() {
	var procedures = [];
	$.ajax({
		type: "POST",
	    url: '../entryOnline/entryProcedures?_' + $.now(),
	    dataType:'json',
	    success: function(data){
	    	if(data.code == 0){
	    		procedures = data.successResult;
			}else{
				parent.layer.msg(data.errorMsg,{ time:1000,icon:1});
			}
		}
	});
	
	return procedures;
}*/
//报销类型列表
function getApplySortList() {
    return [{
        'id': '1',
        'name': '大库垫付'
    }, {
        'id': '2',
        'name': '内单立户（返款外企）'
    }, {
        'id': '3',
        'name': '内单立户（不垫付）'
    }, {
        'id': '4',
        'name': '大库单社'
    }, {
        'id': '5',
        'name': '单立户单社'
    }, {
        'id': '6',
        'name': '全国补医保'
    }, {
        'id': '7',
        'name': '外单立户'
    }, {
        'id': '8',
        'name': '外企退休'
    }, {
        'id': '9',
        'name': '内单立户（不返款外企）'
    }, {
        'id': '10',
        'name': '社会退休'
    }];
}

/*
 * 获取报销比例
 * billId : Es_Medicare_Bill表主键
 * chargeSort：费用类型
 */
function getProportion(billId, chargeSort) {
	var result = '';
	$.ajax({
	   type: "GET",
	   async:false,
	   url: "../reimb/common/getProportion",
	   data: {billId:billId, chargeSort:chargeSort},
	   success: function(data){
		   if(data.code == 0){
			   result = parseFloat(data.result) * 100 + "%";
		   }
	   }
	});
	return result;
}

function getOptions (jsonOptions) {
    var dataList = $('#json-list');
    dataList.html('');
    
    if(!jsonOptions){
    	return ;
    }
    // Loop over the JSON array.
   jsonOptions.forEach(function(item) {
		// Create a new <option> element.
		var option = ' <option value="'+item.full_name+'" key="'+item.value+'">';
		// Add the <option> element to the <datalist>.
		dataList.append(option);
   });
}
$(function() {
    top.scrollTo(0, 0);

    $('body').on('focus','.datalist',function(event){
    	event.preventDefault();
    	var current = $(event.currentTarget);
    	current.attr('id','ajax');
    	current.attr('list','json-list');
    	current.after('<datalist id="json-list"><datalist>');
    }).on('blur','.datalist',function(event){
    	event.preventDefault();
    	var current = $(event.currentTarget);
    	current.removeAttr('id');
    	current.removeAttr('list');
    	current.parent().find('datalist').remove();
    });
});

function downloadUtil(url,ids){

    var xhh = new XMLHttpRequest();

    page_url = url;
    xhh.open("post", page_url);

    xhh.responseType = 'blob';
    xhh.onreadystatechange = function () {
        if (xhh.readyState === 4 && xhh.status === 200) {
            var name = xhh.getResponseHeader("Content-disposition");
            var filename = name;
            var blob = new Blob([xhh.response], {type: 'text/plain'});
            var csvUrl = URL.createObjectURL(blob);
            var link = document.createElement('a');
            link.href = csvUrl;
            link.download = filename;
            link.click();

        }else if (xhh.readyState === 4 && xhh.status === 555){
			alert(xhh.responseText);
		}

    };
    xhh.send(ids);
}

function dateAdd(interval, number, date) {
    switch (interval) {
        case "y": {
            date.setFullYear(date.getFullYear() + number);
            return date;
            break;
        }
        case "q": {
            date.setMonth(date.getMonth() + number * 3);
            return date;
            break;
        }
        case "M": {
            date.setMonth(date.getMonth() + number);
            return date;
            break;
        }
        case "w": {
            date.setDate(date.getDate() + number * 7);
            return date;
            break;
        }
        case "d": {
            date.setDate(date.getDate() + number);
            return date;
            break;
        }
        case "h": {
            date.setHours(date.getHours() + number);
            return date;
            break;
        }
        case "m": {
            date.setMinutes(date.getMinutes() + number);
            return date;
            break;
        }
        case "s": {
            date.setSeconds(date.getSeconds() + number);
            return date;
            break;
        }
        default: {
            date.setDate(d.getDate() + number);
            return date;
            break;
        }
    }


}
$.ajaxSetup({
    complete:function(XMLHttpRequest,textStatus){
        if(textStatus=="parsererror"){
            layer.alert('身份失效!请重新登录!', {icon: 5,yes:function(){
                window.parent.location.href = '../login.html';
            }});
        } else if(textStatus=="error"){
            layer.alert("服务器异常！请稍后再试！", {icon: 5});
        }
    }
})
