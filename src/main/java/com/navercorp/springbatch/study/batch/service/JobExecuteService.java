package com.navercorp.springbatch.study.batch.service;

import java.util.Map;

import com.navercorp.springbatch.study.batch.model.JobResult;

public interface JobExecuteService {
	JobResult execute(String jobName, Map<String, Object> jobParameterMap);
}