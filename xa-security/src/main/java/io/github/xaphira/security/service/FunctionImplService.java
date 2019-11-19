package io.github.xaphira.security.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.github.xaphira.security.dao.FunctionRepo;

@Service("functionService")
public class FunctionImplService {

	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("functionRepo")
	private FunctionRepo functionRepo;
	
}