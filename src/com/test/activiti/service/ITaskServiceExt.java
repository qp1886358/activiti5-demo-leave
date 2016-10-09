package com.test.activiti.service;

import java.util.List;
import java.util.Map;

import com.test.activiti.model.Paging;
import com.test.activiti.model.TaskExt;
import com.test.activiti.model.TaskForm;

public interface ITaskServiceExt {
	
	public Long getRemainingTaskCount(String userName);
	
	public List<TaskExt> getRemainingTask(String userName, Paging paging);
	
	public Long getCompletedTaskCount(String userName);
	
	public List<TaskExt> getCompletedTask(String userName, Paging paging);

	public TaskForm getTaskForm(String taskId);
	
	public void handleTask(String taskId, Map<String, String> parameterMap);
}
