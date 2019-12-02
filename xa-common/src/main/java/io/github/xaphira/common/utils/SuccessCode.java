package io.github.xaphira.common.utils;

import org.springframework.http.HttpStatus;

public enum SuccessCode {

	OK_DEFAULT(HttpStatus.OK),
	OK_SCR001(HttpStatus.OK);

	private final HttpStatus status;

	SuccessCode(HttpStatus status) {
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
}
