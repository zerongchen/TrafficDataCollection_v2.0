package com.aotain.tdc.dto.common;

public class LogDTO {
	
	private String actionTime; // 操作时间
	
	private Long userId; // 用户ID

	private String userName; // 登录用户名

	private String realName; // 登录真实用户名

	private String ipAddressPort; // 客户端IP地址及端口

	private String serverName; // 登录服务器名称

	private int actionType; // 操作类型

	private String opModule; // 操作模块

	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIpAddressPort() {
		return ipAddressPort;
	}

	public void setIpAddressPort(String ipAddressPort) {
		this.ipAddressPort = ipAddressPort;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public String getOpModule() {
		return opModule;
	}

	public void setOpModule(String opModule) {
		this.opModule = opModule;
	}
	
}
