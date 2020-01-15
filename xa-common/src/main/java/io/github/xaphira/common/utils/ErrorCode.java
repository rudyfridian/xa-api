package io.github.xaphira.common.utils;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	ERR_XXAKEY(HttpStatus.UNAUTHORIZED),
	ERR_XXATIMESTAMP(HttpStatus.UNAUTHORIZED),
	ERR_XXASIGNATURE(HttpStatus.UNAUTHORIZED),
	ERR_UNAUTHORIZED(HttpStatus.UNAUTHORIZED),

	ERR_SYS0001(HttpStatus.NOT_FOUND),
	ERR_SYS0404(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SYS0500(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SYS0501(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SYS0502(HttpStatus.INTERNAL_SERVER_ERROR),
	
	ERR_SCR0001(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0002(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0003(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0004(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0005(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0006(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0007A(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0007B(HttpStatus.INTERNAL_SERVER_ERROR),
	ERR_SCR0008(HttpStatus.INTERNAL_SERVER_ERROR);

	private final HttpStatus status;

	ErrorCode(HttpStatus status) {
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
}
