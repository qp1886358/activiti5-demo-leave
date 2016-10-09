package com.test.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.spring.dao.ITestSpringDao;
import com.test.spring.service.ITestSpringService;

@Service("testSpringService")
public class TestSpringService implements ITestSpringService {

	@Autowired(required = true)
	@Qualifier("testSpringDao")
	private ITestSpringDao testSpringDao;

	@Override
	public String getMessage() {
		return testSpringDao.getMessage();
	}
}
