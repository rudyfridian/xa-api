package io.github.xaphira.common.exceptions;

import io.github.xaphira.common.utils.ErrorCode;

public class SystemErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972880939809113925L;
	private Object[] params;
	private ErrorCode errorCode;

	public SystemErrorException() {
		super();
	}

	public SystemErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemErrorException(String message) {
		super(message);
	}

	public SystemErrorException(ErrorCode errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
	}

	public SystemErrorException(ErrorCode errorCode, Object[] params) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.params = params;
	}

	public SystemErrorException(Throwable cause) {
		super(cause);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public Object[] getParams() {
		return params;
	}

}
