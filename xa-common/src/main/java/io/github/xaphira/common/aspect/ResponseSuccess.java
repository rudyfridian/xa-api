package io.github.xaphira.common.aspect;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.github.xaphira.common.utils.SuccessCode;

@Retention(RUNTIME)
@Target(METHOD)
public @interface ResponseSuccess {
	
	SuccessCode value() default SuccessCode.OK_DEFAULT;

}
