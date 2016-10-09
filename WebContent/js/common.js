$(function() {
	
});

// 全局通用
function showMessage(msg) {
	$.messager.show({
		title : '提示',
		msg : msg,
		showType : 'fade',
		timeout : 2000,
		style : {
			right : '',
			bottom : ''
		}
	});
}