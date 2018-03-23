package com.navercorp.springbatch.study.batch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import com.navercorp.springbatch.study.batch.model.JobResult;
import com.navercorp.springbatch.study.batch.service.JobExecuteService;

@Slf4j
@RestController
public class JobExecuteController {
	@Autowired
	private JobExecuteService jobExecuteService;

	@PostMapping("/job/{jobName}")
	public String launch(@PathVariable String jobName, @RequestBody(required = false) Map<String, Object> jobParam) {
		log.info("jobName : {}, jobParam: {}", jobName, jobParam);

		JobResult jobResult = jobExecuteService.execute(jobName, jobParam);
		return jobResult.getDesc();
	}

}