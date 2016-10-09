package com.test.activiti.model;

import java.io.Serializable;

public class ProcessRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String applicant;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

}
