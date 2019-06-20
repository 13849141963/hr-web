$(function() {
	$('#submit')
			.click(
					function() {
						// 获取已选的复选框的值
						var guideInfoArray = new Array();// 放已经选择的checkbox的value
						var guideObjArray = new Array();
						var entryProcedure = new Array();
						var customerCompany = new Array();
						var taskName = $("#taskName").val();
						if (taskName == '') {
							// alert("任务名称不能为空");
							layer.alert("任务名称不能为空", {
								icon : 6
							});
							return;
						}
						// 客户公司
						customerCompany.length = 0;
						$('[name=customerCompany]:checkbox:checked').each(
								function() {
									customerCompany.push($(this).val());
								});

						if (customerCompany.length == 0) {
							// alert("请选择客户公司");
							layer.alert("请选择客户公司", {
								icon : 6
							});
							return;
						}
						console.log("已选复选框的值：" + customerCompany + "\n");
						// 入职手续
						entryProcedure.length = 0;
						$('[name=entryProcedure]:checkbox:checked').each(
								function() {
									entryProcedure.push($(this).val());
								});
						if (entryProcedure.length == 0) {
							// alert("请选择入职手续");
							layer.alert("请选择入职手续", {
								icon : 6
							});
							return;
						}
						console.log("已选复选框的值：" + entryProcedure + "\n");
						// 指南信息
						guideInfoArray.length = 0;
						$('[name=guideInfo]:checkbox:checked').each(
								function() {
									var guideObj = {}
									guideObj.guideId = $(this).val();
									$.each(vm.taskCreate.guideInfo, function(j,
											k) {
										if (guideObj.guideId == k.guideId) {
											guideObj.name = k.guideName;
										}
										;
									})
									guideObj.content = $(this).siblings(
											".guideContentDiv").find(
											".guideContentDivInner").html();
									guideInfoArray.push($(this).val());
									guideObjArray.push(guideObj)

								});
						if (guideInfoArray.length == 0) {
							// alert("请选择指南信息");
							layer.alert("请选择指南信息", {
								icon : 6
							});
							return;
						}
						console.log("已选复选框的值：" + guideInfoArray + "\n");
						var data = {
							'taskName' : taskName,
							'checkedCustomerIds' : customerCompany,
							'guideList' : guideInfoArray,
							"guideObjList" : guideObjArray,
							'processList' : entryProcedure
						};
						var data = JSON.stringify(data);
						$
								.ajax({
									url : '../standard/addEntryTask?_'
											+ $.now(),
									type : "POST",
									// url: '../standard/addEntryTask?_' +
									// $.now(),
									// contentType:"application/x-www-form-urlencoded;
									// charset=UTF-8",
									traditional : true,
									data : data,
									dataType : 'json',
									success : function(data) {
										if (data.code == 0) {
											parent.layer.msg("入职任务创建成功！", {
												time : 2000,
												icon : 1
											});
											window.parent.location.hash = "#standard/standardQueryTask.html";
											// parent.document.getElementById("mainIframe").src="standard/standardQueryTask.html";
											parent.vm.main = "standard/standardQueryTask.html"
										} else {
											if (data.errorMsg) {
												parent.layer.msg(data.errorMsg,
														{
															time : 2000,
															icon : 1
														});
											} else {
												parent.layer.msg("创建入职任务失败!", {
													time : 2000,
													icon : 1
												});
											}

										}
									}
								});
					});
})
var vm = new Vue({
	el : '#rrapp',
	data : {
		taskCreate : {
			taskName : '',
			customerCompany : [],
			proceduresForEntry : [],
			guideInfo : [],
			entryCheckupServices : []
		},
		submitTaskName : {
			checkedProcedureNames : [],
			checkedGuideIds : [],
			checkedCustomerIds : []
		},
		contractSigning : false
	// 无用
	},
	methods : {	
		getEntryGuides : function() {
			// 办理指南信息
			$.ajax({
				type : "GET",
				url : '../standard/entryGuides?_' + $.now(),
				// url: '/entry-online-web/statics/mock_data/entryGuides.json?_'
				// + $.now(),
				dataType : 'json',
				success : function(data) {
					// var data = JSON.parse(data);
					if (data.code == 0) {
						vm.taskCreate.guideInfo = data.successResult;
					} else {
						layer.alert(data.errorMsg, {
							icon : 6
						});
					}
				}
			});
		},
	   guideCancelFn:function(event){
			var el = event.currentTarget;
			var jqel = $(el);
			jqel.attr("disabled", true);
			jqel.prev(".guideEnsureBtn").attr("disabled", true);
			jqel.siblings(".guideEditBtn").attr("disabled", false);
			jqel.siblings('.guideEditor').css("display", "none");
			jqel.siblings(".guideContentDivInner").show();			
		},
		guideEnsureFn : function(event) {
			var el = event.currentTarget;
			var jqel = $(el);
			jqel.attr("disabled", true);
			jqel.prev(".guideEditBtn").attr("disabled", false);
			jqel.siblings('.guideEditor').css("display", "none");
			jqel.siblings(".guideContentDivInner").show();
			var editorId = jqel.siblings('.guideEditor').attr("id");
			var html = window[editorId + "_ue"].getContent();
			jqel.siblings(".guideContentDivInner").html(html)
			// var guideId =editorId.split("_")[0]-0;
			// var data ={guideId:guideId,content:html}
			// data =JSON.stringify(data);
			// var _this = this;
			// $.ajax({
			// type: "POST",
			// url: '../standard/modifyGuide?_' + $.now(),
			// dataType:'json',
			// data:data,
			// contentType:"application/json; charset=UTF-8",
			// success: function(data){
			// if(data.code == 0){
			// parent.layer.msg("修改指南成功！",{ time:2000,icon:1});
			// vm.getEntryGuides();
			// }else{
			// parent.layer.msg(data.errorMsg,{ time:2000,icon:1});
			// //layer.alert(data.errorMsg);
			// }
			// }
			// });

		},
		guideEditFn : function(event) {
			var el = event.currentTarget;
			var jqel = $(el);
			jqel.attr("disabled", true);
			jqel.next(".guideEnsureBtn").attr("disabled", false);
			var editorId = jqel.siblings('.guideEditor').attr("id");
			var content = jqel.prev(".guideContentDivInner").html();
			jqel.siblings('.guideEditor').css("display", "block");
			jqel.siblings(".guideContentDivInner").hide()
			if (!window[editorId + "_ue"]) {
				window[editorId + "_ue"] = UE.getEditor(editorId, {
					// 这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
					toolbars : [ T.toolbars ],
					// 关闭字数统计
					wordCount : false,
					// 关闭elementPath
					elementPathEnabled : false,
					// 默认的编辑区域高度
					initialFrameHeight : 150,
					// 更多其他参数，请参考ueditor.config.js中的配置项
					serverUrl : '/server/ueditor/controller.php',
					fullscreen : false,
					saveInterval : 5000000000
				// enableAutoSave:false
				})
			}
			window[editorId + "_ue"].ready(function() {
				window[editorId + "_ue"].setContent(content)
			});
		},
		guideInpChangeFn : function(event) {
			var el = event.currentTarget;
			var jqel = $(el);
			if (el.checked) {
				jqel.siblings('.guideContentDiv').show()
			} else {
				jqel.siblings('.guideContentDiv').hide()
			}
		}

	},
	mounted : function() {
		// 入职手续
		$.ajax({
			type : "POST",
			url : '../standard/entryProcedures?_' + $.now(),
			dataType : 'json',
			success : function(data) {
				if (data.code == 0) {
					vm.taskCreate.proceduresForEntry = data.successResult;
				} else {
					// alert(data.errorMsg);
					layer.alert(data.errorMsg, {
						icon : 6
					});
				}
			}
		});
		// 办理指南信息
		this.getEntryGuides()
		// 请选择客户公司
		$.ajax({
			type : "POST",
			url : '../standard/customerCompany?_' + $.now(),
			dataType : 'json',
			success : function(data) {
				if (data.code == 0) {
					vm.taskCreate.customerCompany = data.successResult;
				} else {
					layer.alert(data.errorMsg, {
						icon : 6
					});
				}
			}
		});
	}
});
