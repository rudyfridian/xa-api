package io.github.xaphira.common.utils;

public enum ResourceCode {

	PROFILE("profile"),
	SECURITY("security"),
	MASTER("master"),
	NOTIFICATION("notification"),
	GENERAL("general"),
	FILE("file"),
	REPORT("report");
	
	private final String resourceId;

	ResourceCode(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getResourceId() {
		return this.resourceId;
	}
	
}
