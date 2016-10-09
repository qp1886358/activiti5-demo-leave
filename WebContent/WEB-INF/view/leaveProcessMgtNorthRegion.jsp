<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<div style="text-align: left;">
	<img id="imgLogo"
		src="<%=path%>/image/activiti-logo.png"
		style="margin : 10px 10px 10px 10px; float : left;" />
	<div style="float : left;margin : 20px 0 0 50px">
		<h2>Activiti5 工作流整合Spring示例</h2>
		<p>员工填写请假申请(&lt;5天)--人力审批--员工返岗销假<br>员工填写请假申请(&gt;=5天)--部门主管审批--员工返岗销假</p>
		<p></p>
	</div>

</div>