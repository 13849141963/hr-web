var vm = new Vue({
	el : '#rrapp',
	data : {
		taskCreate : {
			taskName : '',
			customerCompany:[],
			proceduresForEntry:[],
			guideInfo:[],
			entryCheckupServices:[]
		},
		submitTaskName:{
			checkedProcedureNames:[],
			checkedGuideIds:[],
			checkedCustomerIds:[]
		}
	},
	methods : {
	},
	 mounted:function(){
		 	//入职手续
			$.ajax({
				type: "POST",
			    url: '../entryOnline/entryProcedures?_' + $.now(),
			    dataType:'json',
			    success: function(data){
			    	if(data.code == 0){
			    		vm.taskCreate.proceduresForEntry = data.successResult;
					}else{
						//alert(data.errorMsg);
                        layer.alert(data.errorMsg, {icon: 6});
					}
				}
			});
			//办理指南信息
			$.ajax({
				type: "POST",
			    url: '../entryOnline/entryGuides?_' + $.now(),
			    dataType:'json',
			    success: function(data){
			    	if(data.code == 0){
			    		vm.taskCreate.guideInfo = data.successResult;
					}else{
                        layer.alert(data.errorMsg, {icon: 6});
					}
				}
			});
			
			//请选择客户公司
			$.ajax({
				type: "POST",
			    url: '../entryOnline/customerCompany?_' + $.now(),
			    dataType:'json',
			    success: function(data){
			    	if(data.code == 0){
			    		vm.taskCreate.customerCompany = data.successResult;
					}else{
                        layer.alert(data.errorMsg, {icon: 6});
					}
				}
			});
			
		/*	//获取入职套餐列表
			$.ajax({
				type: "POST",
			    url: '../entryOnline/listEntryCheckupServices?_' + $.now(),
			    dataType:'json',
			    success: function(data){
			    	if(data.code == 0){
			    		vm.taskCreate.entryCheckupServices = data.successResult;
					}else{
						alert(data.errorMsg);
					}
				}
			});*/
	 }
});
