package io.github.xaphira.feign.decoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
			case 400:
			  ByteArrayOutputStream output = new ByteArrayOutputStream();
				try {
					StreamUtils.copy(response.body().asInputStream(), output);
				} catch (IOException e) {
					e.printStackTrace();
				}
				LOGGER.error("Status code " + response.status() + ", reason = " + new String(output.toByteArray()));
				break;
			case 401:
				LOGGER.error("Status code " + response.status() + ", methodKey = " + methodKey);
				break;
			case 404:
				LOGGER.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), "<You can add error message description here>");
			default:
				break;
		}
		return new Exception(HttpStatus.valueOf(response.status()).toString());
	}

}
