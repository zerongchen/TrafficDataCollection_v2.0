package com.aotain.tdc.dto.common;

public class DicServerBuildBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private long serverBuildId;
	private String serverBuildName;
	public long getServerBuildId() {
		return serverBuildId;
	}
	public String getServerBuildName() {
		return serverBuildName;
	}
	public void setServerBuildId(long serverBuildId) {
		this.serverBuildId = serverBuildId;
	}
	public void setServerBuildName(String serverBuildName) {
		this.serverBuildName = serverBuildName;
	}
}
