$(function() {

	$('#layout').layout({
		
	}).layout('add', {
		region : 'north',
		fit : false,
		split:true,
		height: 120,
		href : gContextPath + '/leaveProcessMgt/north'
	}).layout('add', {
		region : 'south',
		fit : false,
		split:true,
		height: 60,
		href : gContextPath + '/leaveProcessMgt/south'
	}).layout('add', {
		region : 'center',
		title : '任务',
		fit : false,
		split:true,
		width : 700,
		href : gContextPath + '/leaveProcessMgt/center'
	}).layout('add', {
		region : 'east',
		title : '流程图',
		fit : false,
		split:true,
		width: 500,
		href : gContextPath + '/leaveProcessMgt/east'
	});
	
});
