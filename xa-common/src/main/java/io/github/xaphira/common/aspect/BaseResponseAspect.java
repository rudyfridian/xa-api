package io.github.xaphira.common.aspect;

import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.utils.SuccessCode;

@Aspect
@Component
public class BaseResponseAspect {

	protected Log LOGGER = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MessageSource messageSource;
	
	@AfterReturning(value = "@annotation(io.github.xaphira.common.aspect.ResponseSuccess)", returning="result")
	public void afterExecutionService(JoinPoint joinPoint, Object result) throws Throwable {
		try {
			final Signature signature = joinPoint.getSignature();
		    if(signature instanceof MethodSignature){
		        final MethodSignature ms = (MethodSignature) signature;
		        final Method method = ms.getMethod();
				RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
				SuccessCode status = method.getAnnotation(ResponseSuccess.class).value();
				HttpServletRequest request = (HttpServletRequest) attrs.resolveReference(RequestAttributes.REFERENCE_REQUEST);
				Locale locale = Locale.getDefault();
				if(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE) != null)
					locale = Locale.forLanguageTag(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE));
				ApiBaseResponse response = new ApiBaseResponse();
				response.setRespStatusCode(status.name());
				response.getRespStatusMessage().put(response.getRespStatusCode(), messageSource.getMessage(status.name(), null, locale));
				result = new ResponseEntity<ApiBaseResponse>(response, status.getStatus());
		    }
		} catch (NullPointerException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
