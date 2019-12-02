package io.github.xaphira.common.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6956862312103005998L;

	public CustomOauthException(String msg) {
        super(msg);
    }
}