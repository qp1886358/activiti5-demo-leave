package com.test.activiti.model;

import java.util.Map;

public class TaskForm {
	private String formContent;
	private Map<String, Object> formData;

	public TaskForm(String formContent, Map<String, Object> formData) {
		this.formContent = formContent;
		this.formData = formData;
	}

	public String getFormContent() {
		return formContent;
	}

	public void setFormContent(String formContent) {
		this.formContent = formContent;
	}

	public Map<String, Object> getFormData() {
		return formData;
	}

	public void setFormData(Map<String, Object> formData) {
		this.formData = formData;
	}

}
