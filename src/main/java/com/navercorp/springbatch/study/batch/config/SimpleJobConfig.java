package com.navercorp.springbatch.study.batch.config;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class SimpleJobConfig extends AbstractJobConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")
				.start(simpleStep())
				.build();
	}

	@Bean
	public Step simpleStep() {
		return stepBuilderFactory.get("simpleStep")
				.tasklet(simpleTasklet())
				.build();
	}

	@Bean
	@StepScope
	public Tasklet simpleTasklet() {
		return (contribution, chunkContext) -> {
			Map<String, Object> jobParam = chunkContext.getStepContext().getJobParameters();
			log.debug("simpleTasklet start. jobParam : {}", jobParam);

			return RepeatStatus.FINISHED;
		};
	}
}