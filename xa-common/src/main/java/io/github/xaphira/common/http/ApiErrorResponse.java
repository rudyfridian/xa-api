package io.github.xaphira.common.http;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.common.utils.JsonUtils;

@Component
public class ApiErrorResponse {
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JsonUtils jsonUtils;
	
	@Value("${xa.locale}")
	private String locale;
	
	public String errorResponse(ErrorCode errorCode, Locale locale) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		Map<String, Object> response = new TreeMap<>();
		response.put("error", errorCode);
		response.put("error_description", messageSource.getMessage(errorCode.name(), null, locale));
		return jsonUtils.objToJson(response);
	}
	
	public String errorResponse(ErrorCode errorCode, Locale locale, Object[] params) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		Map<String, Object> response = new TreeMap<>();
		response.put("error", errorCode);
		response.put("error_description", messageSource.getMessage(errorCode.name(), params, locale));
		return jsonUtils.objToJson(response);
	}
	
	public String errorDescriptionResponse(ErrorCode errorCode, Locale locale) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		return messageSource.getMessage(errorCode.name(), null, locale);
	}
	
	public String errorDescriptionResponse(ErrorCode errorCode, Locale locale, Object[] params) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		return messageSource.getMessage(errorCode.name(), params, locale);
	}
	
	public String getErrorMessage(ErrorCode errorCode,Locale locale)
	{
		return messageSource.getMessage(errorCode.name(), null, locale);
	}
    
}
