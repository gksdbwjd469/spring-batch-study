package com.navercorp.springbatch.study.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PingController {
	private ResponseEntity<?> OK = new ResponseEntity<>(HttpStatus.OK);

	@RequestMapping(value = "ping", method = RequestMethod.HEAD)
	public ResponseEntity<?> ping() {
		return OK;
	}
}