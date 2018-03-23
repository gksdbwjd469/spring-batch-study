package com.navercorp.springbatch.study.batch.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.navercorp.springbatch.study.batch.JobConfig;

public abstract class AbstractJobConfig implements JobConfig {
	@Override
	public Map<String, Object> createAndGetJobParameters(Map<String, Object> jobParameterMap) {
		if (jobParameterMap == null) {
			jobParameterMap = new HashMap<>();
		}

		jobParameterMap.put("runId", UUID.randomUUID().toString());

		return setJobParameters(jobParameterMap);
	}

	// 필요하면 오버라이딩해서 사용하세요
	public Map<String, Object> setJobParameters(Map<String, Object> jobParameterMap) {
		return jobParameterMap;
	}
}