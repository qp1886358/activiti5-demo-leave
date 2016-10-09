package com.test.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.activiti.model.Paging;
import com.test.activiti.model.TaskExt;
import com.test.activiti.model.TaskForm;
import com.test.activiti.service.ITaskServiceExt;

@Controller
public class TaskController {

	@Autowired(required = true)
	@Qualifier("taskServiceExt")
	ITaskServiceExt taskServiceExt;

	@RequestMapping(value = "/remainingTask")
	@ResponseBody
	public Map<String, Object> getRemainingTask(String userName, @ModelAttribute Paging paging) {

		List<TaskExt> taskList = taskServiceExt.getRemainingTask(userName, paging);

		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("total", taskServiceExt.getRemainingTaskCount(userName));
		mapResult.put("rows", taskList);

		return mapResult;
	}

	@RequestMapping(value = "/completedTask")
	@ResponseBody
	public Map<String, Object> getCompletedTask(String userName, @ModelAttribute Paging paging) {

		List<TaskExt> taskList = taskServiceExt.getCompletedTask(userName, paging);

		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("total", taskServiceExt.getCompletedTaskCount(userName));
		mapResult.put("rows", taskList);

		return mapResult;
	}

	@RequestMapping(value = "/taskForm")
	@ResponseBody
	public Map<String, Object> getTaskForm(String taskId) {
		TaskForm taskForm = taskServiceExt.getTaskForm(taskId);

		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("taskForm", taskForm.getFormContent());
		mapResult.putAll(taskForm.getFormData());

		return mapResult;
	}

	@RequestMapping(value = "/handleTask")
	@ResponseBody
	public Map<String, Object> handleTask(HttpServletRequest request) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		Map<String, String[]> parameters = request.getParameterMap();
		for (Entry<String, String[]> entry : parameters.entrySet()) {
			if (0 != entry.getValue().length && "taskId" != entry.getKey()) {
				parameterMap.put(entry.getKey(), entry.getValue()[0]);
			}
		}

		taskServiceExt.handleTask(request.getParameter("taskId"), parameterMap);

		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("handleTaskResult", "success");

		return mapResult;
	}
}
