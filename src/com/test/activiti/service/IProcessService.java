package com.test.activiti.service;

import javax.servlet.http.HttpServletResponse;

public interface IProcessService {

	public void startProcess(String processName, String startUserName,
			Integer leaveDayNumber, String leaveReason);

	public String getStartForm(String processName);

	public void exportProcessImg(String taskId, HttpServletResponse response);
}
