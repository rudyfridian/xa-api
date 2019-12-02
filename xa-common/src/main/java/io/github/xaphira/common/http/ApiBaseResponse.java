package io.github.xaphira.common.http;

import java.util.Map;
import java.util.TreeMap;

public class ApiBaseResponse {

	private String respStatusCode = "success";
	private Map<String, String> respStatusMessage = new TreeMap<String, String>();

	public String getRespStatusCode() {
		return respStatusCode;
	}

	public void setRespStatusCode(String respStatusCode) {
		this.respStatusCode = respStatusCode;
	}

	public Map<String, String> getRespStatusMessage() {
		return respStatusMessage;
	}

	public void setRespStatusMessage(Map<String, String> respStatusMessage) {
		this.respStatusMessage = respStatusMessage;
	}

}
