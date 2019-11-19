package io.github.xaphira.common.utils;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	ERR_SYS0001(HttpStatus.NOT_FOUND),
	ERR_SYS0404(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SYS0500(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SYS0501(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SYS0502(HttpStatus.INTERNAL_SERVER_ERROR),
	
	ERR_SCR0001(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0002(HttpStatus.INTERNAL_SERVER_ERROR),
	
	ERR_WFL0011(HttpStatus.INTERNAL_SERVER_ERROR);

	private final HttpStatus status;

	ErrorCode(HttpStatus status) {
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
}
