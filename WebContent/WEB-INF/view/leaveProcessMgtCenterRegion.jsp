<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=path%>/js/activiti/leaveProcessMgtCenterRegion.js"></script>
<script type="text/javascript" src="<%=path%>/js/3rd/cvi_busy_lib.js"></script>
<script type="text/javascript" src="<%=path%>/js/screen_busy.js"></script>
</head>

<div id="tt">
	<div title="员工" style="padding: 10px">

		<a href="javascript:applyLeave('Leave process', 'staff')"
			id="btnApplyLeave" style="float: right">请假申请</a>

		<div style="border: 1px solid GhostWhite; margin: 30px 0 0 0;">
			<p style="font-size: 14px">待办任务</p>
			<table id="tblRemainingTask" style="height: 200px">
				<thead>
					<tr>
						<th width="100">名称</th>
						<th width="100">操作</th>
					</tr>
				</thead>
			</table>
		</div>

		<div style="border: 1px solid GhostWhite; margin: 30px 0 0 0;">
			<p style="font-size: 14px">已办任务</p>
			<table id="tblCompletedTask" style="height: 200px">
				<thead>
					<tr>
						<th width="150">名称</th>
						<th width="150">处理人</th>
						<th width="200">操作</th>
					</tr>
				</thead>
			</table>
		</div>

	</div>

	<div title="人力" style="padding: 10px">

		<div style="border: 1px solid GhostWhite; margin: 30px 0 0 0;">
			<p style="font-size: 14px">待办任务</p>
			<table id="tblRemainingTaskHr" style="height: 200px">
				<thead>
					<tr>
						<th width="100">名称</th>
						<th width="100">操作</th>
					</tr>
				</thead>
			</table>
		</div>

		<div style="border: 1px solid GhostWhite; margin: 30px 0 0 0;">
			<p style="font-size: 14px">已办任务</p>
			<table id="tblCompletedTaskHr" style="height: 200px">
				<thead>
					<tr>
						<th width="100">名称</th>
						<th width="100">操作</th>
					</tr>
				</thead>
			</table>
		</div>

	</div>

	<div title="部门主管" style="padding: 10px">

		<div style="border: 1px solid GhostWhite; margin: 30px 0 0 0;">
			<p style="font-size: 14px">待办任务</p>
			<table id="tblRemainingTaskManager" style="height: 200px">
				<thead>
					<tr>
						<th width="100">名称</th>
						<th width="100">操作</th>
					</tr>
				</thead>
			</table>
		</div>

		<div style="border: 1px solid GhostWhite; margin: 30px 0 0 0;">
			<p style="font-size: 14px">已办任务</p>
			<table id="tblCompletedTaskManager" style="height: 200px">
				<thead>
					<tr>
						<th width="100">名称</th>
						<th width="100">操作</th>
					</tr>
				</thead>
			</table>
		</div>

	</div>

	<div id="dlgFormData">
		<form id="formTaskFormData">
			<div id="dialogContentFormData">
				<span class="ui-loading">正在加载表单……</span>
			</div>
		</form>
	</div>

</div>