$(function() {

	$('#btnApplyLeave').linkbutton({
		plain : false
	});

	$('#tblRemainingTask').datagrid(
			{
				url : gContextPath + '/remainingTask',
				queryParams : {
					userName : 'staff'
				},
				method : 'get',
				fit : false,
				fitColumns : false,
				rownumbers : true,
				border : true,
				singleSelect : false,
				selectOnCheck : true,
				checkOnSelect : true,
				pagination : true,
				columns : [ [
						{
							field : 'taskName',
							title : '名称',
							width : 150
						},
						{
							field : 'assignee',
							title : '处理人',
							width : 150
						},
						{
							field : 'operation',
							title : '操作',
							width : 190,
							halign : 'center',
							formatter : function(value, rowData, rowIndex) {
								return '<a href="javascript:void(0);" onclick="handleTask(\'' + rowData.taskId + '\', \'' + rowData.taskDefinitionKey
										+ '\', \'' + rowData.assignee + '\');" >处理</a>&nbsp;' + '<a href="javascript:void(0);" onclick="cancelTask('
										+ rowData.taskId + ');" >取消</a>&nbsp;' + '<a href="javascript:void(0);" onclick="showProcessImg(' + rowData.taskId
										+ ');" >流程图</a>';
							}
						} ] ]
			});

	$('#tblCompletedTask').datagrid({
		url : gContextPath + '/completedTask',
		queryParams : {
			userName : 'staff'
		},
		method : 'get',
		fit : false,
		fitColumns : false,
		rownumbers : true,
		border : true,
		singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true,
		pagination : true,
		columns : [ [ {
			field : 'taskName',
			title : '名称',
			width : 150
		}, {
			field : 'assignee',
			title : '处理人',
			width : 150
		}, {
			field : 'operation',
			title : '操作',
			width : 190,
			halign : 'center',
			formatter : function(value, rowData, rowIndex) {
				return '<a href="javascript:void(0);" onclick="showProcessImg(' + rowData.taskId + ');" >流程图</a>';
			}
		} ] ]
	});

	$('#tblRemainingTaskHr').datagrid(
			{
				url : gContextPath + '/remainingTask',
				queryParams : {
					userName : 'hr'
				},
				method : 'get',
				fit : false,
				fitColumns : false,
				rownumbers : true,
				border : true,
				singleSelect : false,
				selectOnCheck : true,
				checkOnSelect : true,
				pagination : true,
				columns : [ [
						{
							field : 'taskName',
							title : '名称',
							width : 150
						},
						{
							field : 'assignee',
							title : '处理人',
							width : 150
						},
						{
							field : 'operation',
							title : '操作',
							width : 190,
							halign : 'center',
							formatter : function(value, rowData, rowIndex) {
								return '<a href="javascript:void(0);" onclick="handleTask(\'' + rowData.taskId + '\', \'' + rowData.taskDefinitionKey
										+ '\', \'' + rowData.assignee + '\');" >处理</a>&nbsp;' + '<a href="javascript:void(0);" onclick="showProcessImg('
										+ rowData.taskId + ');" >流程图</a>';
							}
						} ] ]
			});

	$('#tblCompletedTaskHr').datagrid({
		url : gContextPath + '/completedTask',
		queryParams : {
			userName : 'hr'
		},
		method : 'get',
		fit : false,
		fitColumns : false,
		rownumbers : true,
		border : true,
		singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true,
		pagination : true,
		columns : [ [ {
			field : 'taskName',
			title : '名称',
			width : 150
		}, {
			field : 'assignee',
			title : '处理人',
			width : 150
		}, {
			field : 'operation',
			title : '操作',
			width : 190,
			halign : 'center',
			formatter : function(value, rowData, rowIndex) {
				return '<a href="javascript:void(0);" onclick="showProcessImg(' + rowData.taskId + ');" >流程图</a>';
				;
			}
		} ] ]
	});

	$('#tblRemainingTaskManager').datagrid(
			{
				url : gContextPath + '/remainingTask',
				queryParams : {
					userName : 'manager'
				},
				method : 'get',
				fit : false,
				fitColumns : false,
				rownumbers : true,
				border : true,
				singleSelect : false,
				selectOnCheck : true,
				checkOnSelect : true,
				pagination : true,
				columns : [ [
						{
							field : 'taskName',
							title : '名称',
							width : 150
						},
						{
							field : 'assignee',
							title : '处理人',
							width : 150
						},
						{
							field : 'operation',
							title : '操作',
							width : 190,
							halign : 'center',
							formatter : function(value, rowData, rowIndex) {
								return '<a href="javascript:void(0);" onclick="handleTask(\'' + rowData.taskId + '\', \'' + rowData.taskDefinitionKey
										+ '\', \'' + rowData.assignee + '\');" >处理</a>&nbsp;' + '<a href="javascript:void(0);" onclick="showProcessImg('
										+ rowData.taskId + ');" >流程图</a>';
							}
						} ] ]
			});

	$('#tblCompletedTaskManager').datagrid({
		url : gContextPath + '/completedTask',
		queryParams : {
			userName : 'manager'
		},
		method : 'get',
		fit : false,
		fitColumns : false,
		rownumbers : true,
		border : true,
		singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true,
		pagination : true,
		columns : [ [ {
			field : 'taskName',
			title : '名称',
			width : 150
		}, {
			field : 'assignee',
			title : '处理人',
			width : 150
		}, {
			field : 'operation',
			title : '操作',
			width : 190,
			halign : 'center',
			formatter : function(value, rowData, rowIndex) {
				return '<a href="javascript:void(0);" onclick="showProcessImg(' + rowData.taskId + ');" >流程图</a>';
			}
		} ] ]
	});

	$('#dlgFormData').dialog({
		closed : true
	});

	$('#tt').tabs({
		tabPosition : 'left',
		fit : true,
		onSelect : function(title, index) {
			if (title == '员工') {
				$('#tblRemainingTask').datagrid('load', {
					userName : 'staff'
				});

				$('#tblCompletedTask').datagrid('load', {
					userName : 'staff'
				});
			}

			if (title == '人力') {
				$('#tblRemainingTaskHr').datagrid('load', {
					userName : 'hr'
				});

				$('#tblCompletedTaskHr').datagrid('load', {
					userName : 'hr'
				});
			}

			if (title == '部门主管') {
				$('#tblRemainingTaskManager').datagrid('load', {
					userName : 'manager'
				});

				$('#tblCompletedTaskManager').datagrid('load', {
					userName : 'manager'
				});
			}
		}
	});

});

function applyLeave(processName, applyLeaveUser) {
	$('#dlgFormData').dialog({
		title : '填写请假申请',
		width : 390,
		height : 260,
		cache : false,
		modal : true,
		onOpen : function() {
			getStartForm.call(this, processName);
		},
		buttons : [ {
			text : 'Ok',
			iconCls : 'icon-ok',
			handler : function() {
				blockScreen($('#tt')[0]);
				$('#formTaskFormData').form({
					url : gContextPath + '/startProcess',
					ajax : true,
					onSubmit : function(param) {
						param.processName = 'Leave process';
						param.startUserName = 'staff';
					},
					success : function(data) {
						releaseScreen();
						$('#tblRemainingTask').datagrid('load', {
							userName : 'staff'
						});

						$('#tblCompletedTask').datagrid('load', {
							userName : 'staff'
						});

						refreshProcessImg();
					},
					error : function(json) {
						xval.remove();
					}
				}).submit();

				$('#dlgFormData').dialog('close');
			}
		} ]
	}).dialog('open');
}

function getStartForm(processName) {
	var dialog = this;

	$.ajax({
		type : "GET",
		url : gContextPath + '/startForm',
		dataType : "text",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			processName : processName
		},
		success : function(json) {
			var form = $.parseJSON(json);
			$(dialog).find('[id="dialogContentFormData"]').empty().html(form.startForm);
			$('#textLeaveDayNumber').numberbox({
				min : 1,
				precision : 0
			});
		},
		error : function(json) {
			alert('failed');
			alert("json=" + json);
			return false;
		}
	});
}

function handleTask(taskId, taskDefinitionKey, userName) {

	var configuration = createTaskFormConfiguration(taskDefinitionKey);
	$('#dlgFormData').dialog({
		title : configuration.dialogTitle,
		width : configuration.dialogWidth,
		height : configuration.dialogHeight,
		cache : false,
		modal : true,
		onOpen : function() {
			var dialog = this;
			getTaskForm.call(this, dialog, taskId, configuration.formDataParser);
		},
		buttons : [ {
			text : 'Ok',
			iconCls : 'icon-ok',
			handler : function() {
				configuration.formSubmitHandler(taskId, userName);
			}
		} ]
	}).dialog('open');
}

function createTaskFormConfiguration(taskDefinitionKey) {
	var configuration = null;
	if ("uerTaskFillApplication" == taskDefinitionKey) {
		configuration = new TaskFormConfiguration("填写请假申请", 390, 450, parseFillLeaveApplication, handleFillLeaveApplication);
	} else if ("userTaskApprovalHr" == taskDefinitionKey) {
		configuration = new TaskFormConfiguration("审批请假申请", 390, 450, parseTaskFormApproveByHr, handleApproveByHr);
	} else if ("userTaskApprovalManager" == taskDefinitionKey) {
		configuration = new TaskFormConfiguration("审批请假申请", 390, 450, parseTaskFormApproveByManager, handleApproveByManager);
	} else if ("userTaskLeaveReturn" == taskDefinitionKey) {
		configuration = new TaskFormConfiguration("销假", 390, 600, parseTaskFormLeaveReturn, handleLeaveReturn);
	}

	return configuration;
}

function TaskFormConfiguration(dialogTitle, dialogWidth, dialogHeight, formDataParser, formSubmitHandler) {
	this.dialogTitle = dialogTitle;
	this.dialogWidth = dialogWidth;
	this.dialogHeight = dialogHeight;
	this.formDataParser = formDataParser;
	this.formSubmitHandler = formSubmitHandler;
}

function getTaskForm(dialog, taskId, parser) {

	$.ajax({
		type : "GET",
		url : gContextPath + '/taskForm',
		dataType : "text", // ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			taskId : taskId
		},
		success : function(json) {
			parser(json, dialog);
		},
		error : function(json) {
			alert('failed');
			alert("json=" + json);
			return false;
		}
	});
}

function parseTaskFormApproveByHr(json, dialog) {
	var form = $.parseJSON(json);
	$(dialog).find('[id="dialogContentFormData"]').empty().html(form.taskForm);
	$('#textLeaveDayNumber').val(form.leaveDayNumber);
	$('#textareaLeaveReason').val(form.leaveReason);
}

function handleApproveByHr(taskId, userName) {
	$('#formTaskFormData').form({
		url : gContextPath + '/handleTask',
		ajax : true,
		onSubmit : function(param) {
			param.taskId = taskId;
			param.userName = userName;
		},
		success : function(data) {
			$('#tblRemainingTaskHr').datagrid('load', {
				userName : 'hr'
			});

			$('#tblCompletedTaskHr').datagrid('load', {
				userName : 'hr'
			});

			refreshProcessImg();
		}
	}).submit();

	$('#dlgFormData').dialog('close');
}

function parseTaskFormApproveByManager(json, dialog) {
	// 目前部门经理和人力的处理逻辑暂时一致
	parseTaskFormApproveByHr(json, dialog);
}

function handleApproveByManager(taskId, userName) {
	$('#formTaskFormData').form({
		url : gContextPath + '/handleTask',
		ajax : true,
		onSubmit : function(param) {
			param.taskId = taskId;
			param.userName = userName;
		},
		success : function(data) {
			$('#tblRemainingTaskManager').datagrid('load', {
				userName : 'manager'
			});

			$('#tblCompletedTaskManager').datagrid('load', {
				userName : 'manager'
			});

			refreshProcessImg();
		}
	}).submit();

	$('#dlgFormData').dialog('close');
}

function handleLeaveReturn(taskId, userName) {
	$('#formTaskFormData').form({
		url : gContextPath + '/handleTask',
		ajax : true,
		onSubmit : function(param) {
			param.taskId = taskId;
			param.userName = userName;
		},
		success : function(data) {
			$('#tblRemainingTask').datagrid('load', {
				userName : 'staff'
			});

			$('#tblCompletedTask').datagrid('load', {
				userName : 'staff'
			});

			refreshProcessImg();
		}
	}).submit();

	$('#dlgFormData').dialog('close');
}

function parseTaskFormLeaveReturn(json, dialog) {
	var form = $.parseJSON(json);
	$(dialog).find('[id="dialogContentFormData"]').empty().html(form.taskForm);
	$('#textLeaveDayNumber').val(form.leaveDayNumber);
	$('#textareaLeaveReason').val(form.leaveReason);
	$('#textareaApprovalOpinion').val(form.approvalOpinion);
	if ('yes' == form.isApproved) {
		$('#radioIsApprovedYes').attr("checked", "checked");
	} else {
		$('#radioIsApprovedNo').attr("checked", "checked");
	}

	date = new Date();
	dateString = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
	$('#dateBoxReturnDate').datebox({
		required : true
	}).datebox('setValue', dateString);
}

function handleFillLeaveApplication(taskId, userName) {
	// 目前的处理逻辑暂时一致
	handleLeaveReturn(taskId, userName);
}

function parseFillLeaveApplication(json, dialog) {
	var form = $.parseJSON(json);
	$(dialog).find('[id="dialogContentFormData"]').empty().html(form.taskForm);
	$('#textLeaveDayNumber').val(form.leaveDayNumber);
	$('#textareaLeaveReason').val(form.leaveReason);
	$('#textLeaveDayNumber').numberbox({
		min : 1,
		precision : 0
	});
	$('#textareaApprovalOpinion').val(form.approvalOpinion);
	if ('yes' == form.isApproved) {
		$('#radioIsApprovedYes').attr("checked", "checked");
	} else {
		$('#radioIsApprovedNo').attr("checked", "checked");
	}
}

function showProcessImg(taskId) {
	var imgTag = '<img src="' + gContextPath + '/process/image?taskId=' + taskId + '" />';
	$("#imgProcess").attr('src', gContextPath + '/process/image?taskId=' + taskId);
}

function refreshProcessImg() {
	$('#layout').layout('panel', 'east').panel('refresh', gContextPath + '/leaveProcessMgt/east');
}
