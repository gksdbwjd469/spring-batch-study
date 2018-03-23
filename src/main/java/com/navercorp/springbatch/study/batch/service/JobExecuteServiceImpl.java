package com.navercorp.springbatch.study.batch.service;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.navercorp.springbatch.study.batch.JobConfig;
import com.navercorp.springbatch.study.batch.model.JobResult;

@Slf4j
@Service
public class JobExecuteServiceImpl implements JobExecuteService {
	private JobOperator jobOperator;
	private ApplicationContext applicationContext;
	private JobExplorer jobExplorer;

	@Autowired
	public JobExecuteServiceImpl(JobOperator jobOperator, JobExplorer jobExplorer, ApplicationContext applicationContext) {
		this.jobOperator = jobOperator;
		this.jobExplorer = jobExplorer;
		this.applicationContext = applicationContext;
	}

	@Override
	public JobResult execute(String jobName, Map<String, Object> jobParam) {
		try {
			String jobParameters = createAndGetJobParameters(jobName, jobParam);
			Long executionId = jobOperator.start(jobName, jobParameters);

			if (isJobSuccess(executionId)) {
				return JobResult.SUCCESS;
			}
		} catch (Exception e) {
			log.error("job execute error", e);
		}

		return JobResult.FAIL;
	}

	private boolean isJobSuccess(Long executionId) {
		return StringUtils.equals(ExitStatus.COMPLETED.getExitCode(), getJobStatus(executionId).getExitCode());
	}

	private ExitStatus getJobStatus(Long executionId) {
		JobExecution jobExecution = jobExplorer.getJobExecution(executionId);
		if (jobExecution != null) {
			return jobExecution.getExitStatus();
		} else {
			return ExitStatus.FAILED;
		}
	}

	private String createAndGetJobParameters(String jobName, Map<String, Object> jobParam) {
		JobConfig jobConfig = (JobConfig) applicationContext.getBean(jobName + "Config");
		jobParam = jobConfig.createAndGetJobParameters(jobParam);

		return convertMapToString(jobParam);
	}

	private String convertMapToString(Map<String, Object> jobParam) {
		StringBuilder stringBuilder = new StringBuilder();

		jobParam.keySet().stream()
			.forEach((key) -> {
				String value = MapUtils.getString(jobParam, key);
				stringBuilder.append(String.format("%s=%s\n", key, value));
			});

		return stringBuilder.toString();
	}
}