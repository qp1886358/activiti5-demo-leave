package com.test.activiti.model;

public class TaskExt {
	private String taskId;
	private String taskName;
	private String assignee;
	private String executionId;
	private String taskDefinitionKey;
	private String previousTaskId;
	private String previousTaskDefinitionKey;
	private String businessKey;

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getPreviousTaskDefinitionKey() {
		return previousTaskDefinitionKey;
	}

	public void setPreviousTaskDefinitionKey(String previousTaskDefinitionKey) {
		this.previousTaskDefinitionKey = previousTaskDefinitionKey;
	}

	public String getPreviousTaskId() {
		return previousTaskId;
	}

	public void setPreviousTaskId(String previousTaskId) {
		this.previousTaskId = previousTaskId;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
}
