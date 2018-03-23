package com.navercorp.springbatch.study.batch;

import java.util.Map;

public interface JobConfig {
	Map<String, Object> createAndGetJobParameters(Map<String, Object> jobParam);
}