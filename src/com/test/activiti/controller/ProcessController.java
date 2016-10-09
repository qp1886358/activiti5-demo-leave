package com.test.activiti.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.activiti.service.IProcessService;

@Controller
public class ProcessController {

	@Autowired(required = true)
	@Qualifier("processService")
	IProcessService processService;

	@RequestMapping(value = "/startProcess")
	@ResponseBody
	public Map<String, Object> startProcess(String processName, String startUserName, Integer leaveDayNumber, String leaveReason) {

		processService.startProcess(processName, startUserName, leaveDayNumber, leaveReason);

		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("startProcessResult", "success");

		return mapResult;
	}

	@RequestMapping(value = "/startForm")
	@ResponseBody
	public Map<String, Object> getStartForm(String processName) {
		String startForm = processService.getStartForm(processName);
		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("startForm", startForm);
		return mapResult;
	}

	@RequestMapping(value = "/process/image", produces = "application/json;charset=utf-8")
	@ResponseBody
	public void exportProcessImg(String taskId, HttpServletResponse response) {
		processService.exportProcessImg(taskId, response);
	}
}
