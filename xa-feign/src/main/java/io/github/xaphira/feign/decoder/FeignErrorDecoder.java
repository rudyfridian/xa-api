package io.github.xaphira.feign.decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.RequestTemplate;
import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
	
	private RequestTemplate requestTemplate;

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
			case 400:
				LOGGER.error("Error took place when using Feign client to send HTTP Request. Status code : {} , methodKey : {}", response.status(), methodKey);
				break;
			case 401:
				LOGGER.error("Error took place when using Feign client to send HTTP Request. Status code : {} , methodKey : {}", response.status(), methodKey);
				// return new RetryableException(response.reason(), response.request().httpMethod(), new Date());
			case 404:
				LOGGER.error("Error took place when using Feign client to send HTTP Request. Status code : {} , methodKey : {}", response.status(), methodKey);
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.reason());
			case 500:
				LOGGER.error("Error took place when using Feign client to send HTTP Request. Status code : {} , reason : {}", response.status(), response.reason());
				break;
			default:
				break;
		}
		return new Exception(HttpStatus.valueOf(response.status()).name());
	}

}
