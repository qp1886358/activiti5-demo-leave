package com.test.activiti.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import com.test.activiti.model.Paging;
import com.test.activiti.model.TaskExt;
import com.test.activiti.model.TaskForm;
import com.test.activiti.service.ITaskServiceExt;

@Service("taskServiceExt")
public class TaskServiceExt implements ITaskServiceExt {

	@Autowired(required = true)
	@Qualifier("taskService")
	TaskService taskService;

	@Autowired(required = true)
	@Qualifier("historyService")
	HistoryService historyService;

	@Autowired(required = true)
	@Qualifier("formService")
	FormService formService;

	@Override
	public List<TaskExt> getRemainingTask(String userName, Paging paging) {
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName).orderByTaskCreateTime().asc()
				.listPage(paging.getPage() - 1, paging.getRows());
		List<TaskExt> taskExtList = new ArrayList<TaskExt>();
		for (Task task : taskList) {
			if (task.getAssignee().equals(userName)) {
				TaskExt taskExt = new TaskExt();
				taskExt.setTaskName(task.getName());
				taskExt.setAssignee(task.getAssignee());
				taskExt.setTaskId(task.getId());
				taskExt.setExecutionId(task.getExecutionId());
				taskExt.setTaskDefinitionKey(task.getTaskDefinitionKey());
				
				Map<String, Object> taskVarivales = taskService.getVariables(task.getId());
				taskExt.setPreviousTaskId(taskVarivales.get("previousTaskId").toString());
				taskExt.setPreviousTaskDefinitionKey(taskVarivales.get("previousTaskDefinitionKey").toString());
				taskExt.setBusinessKey(taskVarivales.get("businessKey").toString());
				
				taskExtList.add(taskExt);
			}
		}

		return taskExtList;
	}

	@Override
	public List<TaskExt> getCompletedTask(String userName, Paging paging) {

		List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery().taskAssignee(userName).orderByHistoricTaskInstanceEndTime()
				.asc().listPage(paging.getPage() - 1, paging.getRows());
		List<TaskExt> taskExtList = new ArrayList<TaskExt>();
		for (HistoricTaskInstance task : taskList) {
			if (task.getAssignee().equals(userName) && "completed".equals(task.getDeleteReason())) {
				TaskExt taskExt = new TaskExt();
				taskExt.setTaskName(task.getName());
				taskExt.setAssignee(task.getAssignee());
				taskExt.setTaskId(task.getId());
				taskExt.setExecutionId(task.getExecutionId());
				taskExt.setTaskDefinitionKey(task.getTaskDefinitionKey());
				taskExtList.add(taskExt);
			}
		}

		return taskExtList;
	}

	@Override
	public Long getRemainingTaskCount(String userName) {
		Long total = taskService.createTaskQuery().taskAssignee(userName).count();
		return total;
	}

	@Override
	public Long getCompletedTaskCount(String userName) {
		Long total = historyService.createHistoricTaskInstanceQuery().taskAssignee(userName).count();
		return total;
	}

	@Override
	public TaskForm getTaskForm(String taskId) {

		String taskFormContent = formService.getRenderedTaskForm(taskId).toString();
		Map<String, Object> taskVarivales = taskService.getVariables(taskId);

		TaskForm taskForm = new TaskForm(taskFormContent, taskVarivales);

		return taskForm;
	}

	@Override
	public void handleTask(String taskId, Map<String, String> parameterMap) {

		Map<String, String> formProperties = new HashMap<String, String>();
		formProperties.putAll(parameterMap);

		Map<String, Object> taskVarivales = taskService.getVariables(taskId);
		String leaveApplicant = String.valueOf(taskVarivales.get("leaveApplicant"));
		formProperties.put("leaveApplicant", leaveApplicant);
		formProperties.put("previousTaskId", taskId);
		String businessKey = String.valueOf(taskVarivales.get("businessKey"));
		formProperties.put("businessKey", businessKey);
		
		HistoricTaskInstance hisTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		formProperties.put("previousTaskDefinitionKey", hisTask.getTaskDefinitionKey());

		formService.submitTaskFormData(taskId, formProperties);

		List<Task> taskList = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
		for (Task task : taskList) {
			if ("userTaskLeaveReturn".equals(task.getTaskDefinitionKey())) {
				taskService.claim(task.getId(), leaveApplicant);
			} else if ("uerTaskFillApplication".equals(task.getTaskDefinitionKey())) {
				taskService.claim(task.getId(), leaveApplicant);
			} else if ("userTaskApprovalHr".equals(task.getTaskDefinitionKey())) {
				taskService.claim(task.getId(), "hr");
			} else if ("userTaskApprovalManager".equals(task.getTaskDefinitionKey())) {
				taskService.claim(task.getId(), "manager");
			}
		}
	}

}
