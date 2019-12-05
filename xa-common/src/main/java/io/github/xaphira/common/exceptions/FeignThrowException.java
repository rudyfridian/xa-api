package io.github.xaphira.common.exceptions;

import org.springframework.http.HttpStatus;

import io.github.xaphira.common.utils.ErrorCode;

public class FeignThrowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972880939809113925L;
	private Object[] params;
	private ErrorCode errorCode;
	private HttpStatus status;

	public FeignThrowException() {
		super();
	}

	public FeignThrowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FeignThrowException(String message) {
		super(message);
	}

	public FeignThrowException(ErrorCode errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
	}


	public FeignThrowException(HttpStatus status) {
		super(status.name());
		this.status = status;
	}

	public FeignThrowException(ErrorCode errorCode, Object[] params) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.params = params;
	}

	public FeignThrowException(Throwable cause) {
		super(cause);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public Object[] getParams() {
		return params;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
