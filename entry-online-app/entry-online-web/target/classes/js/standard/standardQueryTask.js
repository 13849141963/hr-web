var vm = new Vue({
    el: '#rrapp',
    data: {
        //公司名称
        companyNames: getCompanyName(),
        //入职手续
        entryProcedures: getEntryProcedures(),
        entryGuides:getEntryGuides()
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
        url: '../standard/customerCompany?_' + $.now(),
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
        url: '../standard/entryProcedures?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = data.successResult;
        }
    });
    return result;
}

function getEntryGuides() {
    var result = '';
    $.ajax({
        type: "POST",
        async: false,        
        url: '../standard/entryGuides?_' + $.now(),
        dataType: 'json',
        success: function (data) {
            result = data.successResult;
        }
    });
    return result;
}


$(function() { 	
    $("#jqGrid").jqGrid({
      url: '../standard/queryEntryTask?_' + $.now(),  
     // mtype:"POST",
    //  url: '/entry-online-web/statics/mock_data/queryEntryTask.json?_' + $.now(),
        datatype: "json",
        colModel: [{
            label: 'id',
            name: 'creator',        
            hidden: true
        }, {
            label: '任务编号',
            name: 'taskId',
            key: true,
            width : 170
        }, {
            label: '任务名称',
            name: 'taskName',
            width : 200
        }, {
            label: '适用公司',
            name: 'busiCusts',
            width : 250,
            formatter: function (value) {
                return mapBusiCustNames(value);
            }
            
        }, {
            label: '手续',
            name: 'procedures',
            width : 280,
            formatter: function (value) {
                return mapEntryProcedures(value);
            }
        }, 
      {
          label: '办理指南',
          name: 'guidesList',
          width : 250,
          formatter: function (cellvalue, options, rowObject) {         	  
        	  var result ="";        	
              $.each(cellvalue,function(i,value){            	  
            	// result += value.name+"&nbsp;<a href='#' onclick='editGuides(\""+value.taskGuideId+"\","+value.guideId+",\""+value.content+"\",this)' class='querytEeidtGuide'>修改</a><br>";
            	  value.content =value.content.split('&quot;').join("^^")
            	  result += value.name+"&nbsp;<a href='#'   class='querytEeidtGuide' data-cellvalue=\'"+JSON.stringify(value).replace(/\'/g,"&#39")+"\'>修改</a><br>";   
            	 // result += value.name+"&nbsp;<a href='#' onclick='queryEditGuideFn("+JSON.stringify(value).replace(/\'/g,"&#39")+")'   class='querytEeidtGuide' >修改</a><br>";            	
              })
              return result
          }
      },
        {
			label : '入职操作',
			name : '',
			width : 180,
			index : 'operate',
			align : 'center',
			formatter : function(cellvalue, options, rowObject) {				
//				if(rowObject.taskStatus=="4"){
//					var detail = '<button disabled onclick="editInfo(\''+rowObject.taskId+'\',\''+rowObject.pcEntryLink+'\')"   class="btn btn-primary btn-xs">查看下载二维码</button>';
//				}else {
					var detail = '<button onclick="editInfo(\''+rowObject.taskId+'\',\''+rowObject.pcEntryLink+'\')" class="btn btn-primary btn-xs">查看下载二维码</button>';
//				}				
				return detail;
			}
         },  
         {
 			label : '任务状态',
 			name : 'taskStatus',
 			width : 110, 			
 			formatter : function(cellvalue, options, rowObject) {
 				if(cellvalue=="2"){
 					return "已开启"
 				}else if(cellvalue=="4"){
 					return "已关闭"
 				}else{
 					return ""
 				}
 			}
          },  
         {
 			label : '任务操作',
 			name : '',
 			width : 190,
 			index : 'operate',
 			align : 'center',
 			formatter : function(cellvalue, options, rowObject) { 			
 				if(rowObject.taskStatus=="2"){
 					var detail ='<button  onclick="deleteTask(\''+rowObject.taskId+'\')" class="btn btn-primary btn-xs">删除</button>&nbsp;'+
 	 				'<button onclick="openTask(\''+rowObject.taskId+'\',this)" class="btn btn-primary btn-xs openTask" disabled>开启</button>&nbsp;'+
 	 				'<button onclick="closeTask(\''+rowObject.taskId+'\',this)" class="btn btn-primary btn-xs closeTask">关闭</button>';
 	 				return detail;
 				}else if(rowObject.taskStatus=="4"){
 					var detail ='<button  onclick="deleteTask(\''+rowObject.taskId+'\')" class="btn btn-primary btn-xs">删除</button>&nbsp;'+
 	 				'<button onclick="openTask(\''+rowObject.taskId+'\',this)" class="btn btn-primary btn-xs openTask" >开启</button>&nbsp;'+
 	 				'<button onclick="closeTask(\''+rowObject.taskId+'\',this)" class="btn btn-primary btn-xs closeTask" disabled>关闭</button>';
 	 				return detail;
 				}else{
 					var detail ='<button  onclick="deleteTask(\''+rowObject.taskId+'\')" class="btn btn-primary btn-xs">删除</button>&nbsp;'+
 	 				'<button onclick="openTask(\''+rowObject.taskId+'\',this)" class="btn btn-primary btn-xs openTask">开启</button>&nbsp;'+
 	 				'<button onclick="closeTask(\''+rowObject.taskId+'\',this)" class="btn btn-primary btn-xs closeTask">关闭</button>';
 	 				return detail;
 				}
 				
 			}
          }
        ],        
        viewrecords: true,
        height: 520,
        rowNum: 10,
        rownumbers: true,
        rownumWidth: 25,
        multiselect: false,
       autowidth: true,
       shrinkToFit:false,
       autoScroll:true,
       width:"100%",
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
//            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({
//                "overflow-x": "hidden"
//            });
        }
    });
    $("#gotoTaskCreate").click(function () {    
       window.parent.location.hash="#standard/standardTaskCreate.html"; 
	    parent.vm.main="standard/standardTaskCreate.html"
    }) 

})
function editInfo(taskId,pcEntryLink) {
	var data = JSON.stringify({'taskId':taskId,"pcEntryLink":pcEntryLink});	
	var xmlhttp;
	xmlhttp=new XMLHttpRequest();	
	xmlhttp.open("POST",'../standard/generateCode?'+'_' + $.now(),true);
	xmlhttp.responseType = "blob";
	xmlhttp.onreadystatechange=function(){     
    if(xmlhttp.readyState==4){
    	if(xmlhttp.status==200){
    		 var blob = this.response;
 	        var img = document.getElementById("pcImg");
 	        var erWeiMaA = document.getElementById("erWeiMaA")
 	        img.onload = function(e) {
 	            window.URL.revokeObjectURL(img.src); 
 	        };
 	        img.src = window.URL.createObjectURL(blob);	
 	        erWeiMaA.href=window.URL.createObjectURL(blob)
 	        erWeiMaA.download="二维码.png"; 	   
    	}else if(xmlhttp.status==500){
    		layer.alert("服务器异常！请稍后再试！", {icon: 5});
    	}
           
      }
    }
    xmlhttp.send(data);
	$('#myModal').modal('show');
}
$(document).on("click",".querytEeidtGuide",function(){	
	var cellvalue = $(this).attr("data-cellvalue");
	cellvalue=JSON.parse(cellvalue);	
	cellvalue.content=cellvalue.content.split("^^").join('&quot;');
	editGuides(cellvalue.taskGuideId,cellvalue.guideId,cellvalue.content)
})
function editGuides(taskId,guidedId,guideContent) {		
	$('#editGuides').modal('show');
	$("#querytGuideSureBtn").attr("data-taskid",taskId);
	$("#querytGuideSureBtn").attr("data-guidedid",guidedId);
	if(!window.ue){
		window.ue=  UE.getEditor("querytGuideEditor",{
	        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
	        toolbars:[T.toolbars],  
	        //关闭字数统计
	        wordCount:false,
	        //关闭elementPath
	        elementPathEnabled:false,
	        //默认的编辑区域高度
	        initialFrameHeight:200,	
	       // scaleEnabled:false,
	        //更多其他参数，请参考ueditor.config.js中的配置项
	       //serverUrl: '/server/ueditor/controller.php',
	        fullscreen:false,
	       // enableAutoSave:false,
	        saveInterval:5000000000
	   
	    })	
	}
	window.ue.ready(function() {
	window.ue.setContent(guideContent)
		 });
}
$(document).on("click","#querytGuideSureBtn",function(){
	var html = window.ue.getContent();
	var taskId = $(this).attr("data-taskid")-0;
	var guidedId = $(this).attr("data-guidedid")-0;
	var data ={
			taskGuideId:taskId,
			content:html,
			guidedId:guidedId
	}
	data=JSON.stringify(data)
    $.ajax({
    type: "POST",
    url: '../standard/modifyTaskGuide?_' + $.now(),
    dataType:'json',
   // contentType:"application/x-www-form-urlencoded; charset=UTF-8",
    data: data,
    success: function(data){
    	layer.alert('修改成功!', {icon: 6});
    	$('#editGuides').modal('hide');
    	$("#jqGrid").jqGrid().trigger("reloadGrid");    	
   }
    });
})
function deleteTask(obj){
  layer.confirm('确定删除吗？', {
		  btn: ['确定','取消'], //按钮
		   icon: 3,
           title: '提示'
		}, function(){
			var dataArr=[];
			dataArr.push(obj);
		    var data = {'taskIds':dataArr};
		    data =JSON.stringify(data); 
		    $.ajax({
		    type: "POST",
		    url: '../standard/deleteTask?_' + $.now(),
		    dataType:'json',
		    traditional: true,
		    data: data,
		    success: function(data){
		    	layer.alert('删除成功!', {icon: 6});
		    	var page=$("#jqGrid").jqGrid('getGridParam', 'page');
		    	var rowNum=$("#jqGrid").jqGrid('getGridParam', 'rowNum');
		    	var data = $("#jqGrid").jqGrid('getCol',"taskId", 'rowNum');
		    	if(data.length==1){
		    		page--;    		
		    	}
		    	$("#jqGrid").jqGrid('setGridParam',{page:page}).trigger("reloadGrid");
		   }
		    });
		}, function(){
		
		});

}
function openTask(obj,_this){
    var data = {'taskId':obj};
     data =JSON.stringify(data);     
    $.ajax({
    type: "POST",
    url: '../standard/openTask?_' + $.now(),
    dataType:'json',
    data:data,
    success: function(data){
    	layer.alert('开启成功!', {icon: 6});    
      	$("#jqGrid").jqGrid().trigger("reloadGrid");
   }  
    });
}
function closeTask(obj,_this){
    var data = {'taskId':obj};
    data =JSON.stringify(data);
    $.ajax({
    type: "POST",
    url: '../standard/closeTask?_' + $.now(),
    dataType:'json',
    data: data,
    success: function(data){
       	layer.alert('关闭成功!', {icon: 6});   
    	$("#jqGrid").jqGrid().trigger("reloadGrid");
   }
    });
}
function mapBusiCustNames(val) {
    var result = '';
    try{
        val.forEach(function(value, index, array) {
                result += value.busiCustName+"<br>";
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
                    result += value.procedureName+"<br>";
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

//function mapEntryGuides(val,rowObject) {
//	//var rowObject=JSON.stringify(rowObject);	
//    var result = '';
//    var list = val.split(",");
//    try{
//        list.forEach(function (val1, ind, arr) {
//            try{
//            vm.entryGuides.forEach(function(value, index, array) {
//                if(value.guideId == val1){
//                    result += value.guideName+"&nbsp;<a href='#' onclick='editGuides(\""+rowObject.taskId+"\","+value.guideId+",this)' class='querytEeidtGuide'>修改</a><br>";
//                    throw BreakException;
//                }
//            });
//            }catch(e){
//            }
//        });
//
//    }catch(e){
//    }
//    return result.substr(0,result.length-1);
//}