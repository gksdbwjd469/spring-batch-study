package com.navercorp.springbatch.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBatchApplication {
	@Value("${name}")
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Bean
	public CommandLineRunner hello() {
		return (args) -> {
			log.debug("hello {}~", name);
		};
	}
}