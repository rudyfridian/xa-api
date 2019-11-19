package io.github.xaphira.common.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import io.github.xaphira.common.http.ApiErrorResponse;
import io.github.xaphira.common.utils.ErrorCode;

public class BaseControllerException {

	protected Log LOGGER = LogFactory.getLog(this.getClass());
	
	@Autowired
	protected ApiErrorResponse errorResponse;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleException(HttpServletRequest request, Exception exception) {
		LOGGER.error(stackTrace(exception));
		
		Locale locale = null;
		String acceptLanguage = request.getHeader("Accept-Language");
		if(acceptLanguage != null)
			locale = new Locale(acceptLanguage);
		Map<String, String> respStatusMessage = new HashMap<String, String>();
		respStatusMessage.put(ErrorCode.ERR_SYS0500.name(), errorResponse.errorDescriptionResponse(ErrorCode.ERR_SYS0500, locale));
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setRespStatusCode("failure");
		baseResponse.setRespStatusMessage(respStatusMessage);
		return new ResponseEntity<BaseResponse>(baseResponse,
				ErrorCode.ERR_SYS0500.getStatus());
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<BaseResponse> handleMissingServletRequestPartException(HttpServletRequest request, MissingServletRequestPartException exception) {
		LOGGER.error(stackTrace(exception));
		
		Locale locale = null;
		String acceptLanguage = request.getHeader("Accept-Language");
		if(acceptLanguage != null)
			locale = new Locale(acceptLanguage);
		Map<String, String> respStatusMessage = new HashMap<String, String>();
		respStatusMessage.put(ErrorCode.ERR_SYS0404.name(), errorResponse.errorDescriptionResponse(ErrorCode.ERR_SYS0404, locale));
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setRespStatusCode("failure");
		baseResponse.setRespStatusMessage(respStatusMessage);
		return new ResponseEntity<BaseResponse>(baseResponse,
				ErrorCode.ERR_SYS0404.getStatus());
	}
	
	@ExceptionHandler(SystemErrorException.class)
	public ResponseEntity<BaseResponse> handleSystemException(HttpServletRequest request, SystemErrorException exception) {
		LOGGER.error(stackTrace(exception));
		
		Locale locale = null;
		String acceptLanguage = request.getHeader("Accept-Language");
		if(acceptLanguage != null)
			locale = new Locale(acceptLanguage);	
		Map<String, String> respStatusMessage = new HashMap<String, String>();
		if(exception.getParams() != null) {
			String err = errorResponse.errorResponse(exception.getErrorCode(), locale, exception.getParams());
			respStatusMessage.put(exception.getErrorCode().name(), err);
		} else
			respStatusMessage.put(exception.getErrorCode().name(), errorResponse.errorDescriptionResponse(exception.getErrorCode(), locale));
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setRespStatusCode("failure");
		baseResponse.setRespStatusMessage(respStatusMessage);
		return new ResponseEntity<BaseResponse>(baseResponse,
				exception.getErrorCode().getStatus());
	}
	
	private String stackTrace(Exception exception) {
		StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
	private class BaseResponse {
		private String respStatusCode = "success";
		private Map<String, String> respStatusMessage;

		@SuppressWarnings("unused")
		public String getRespStatusCode() {
			return respStatusCode;
		}

		public void setRespStatusCode(String respStatusCode) {
			this.respStatusCode = respStatusCode;
		}

		@SuppressWarnings("unused")
		public Map<String, String> getRespStatusMessage() {
			return respStatusMessage;
		}

		public void setRespStatusMessage(Map<String, String> respStatusMessage) {
			this.respStatusMessage = respStatusMessage;
		}
	}
}
