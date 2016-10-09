<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/jquery-easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/jquery-easyui-1.5/themes/demo.css">
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/activiti/leaveProcessMgt.js"></script>
<script type="text/javascript">
    window.gContextPath='<%=request.getContextPath()%>';
</script>
<title>请假流程</title>
</head>
<body>
	<div id="layout" style="width: 1200px; height: 800px;"></div>
</body>
</html>