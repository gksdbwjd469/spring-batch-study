package com.navercorp.springbatch.study.batch.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JobResult {
	SUCCESS("SUCCESS", true),
	FAIL("FAIL", false);

	private final String desc;
	private final boolean result;
}