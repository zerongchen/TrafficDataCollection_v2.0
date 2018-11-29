package com.aotain.tdc.constant;

public enum UserLoginAnalyseParam {
	LOGIN_COUNT(1,"登录次数","LOGIN_COUNT","次"),
	LOGINFAIL_COUNT(2,"失败次数","FAIL_COUNT","次"),
	LOGINSUC_RATE(3,"登录成功率","LOGINSUC_RATE","%"),
	TRAFFIC_TOTAL(4,"总流量","TRAFFIC_TOTAL","MB"),
	ONLINE_TIME(5,"在线时长","ONLINE_TIME","小时");;
	
	private int index;
	private String description;
	private String field;
	private String unit;
	
	private UserLoginAnalyseParam(int index, String description, String field,String unit) {
		this.index = index;
		this.description = description;
		this.field = field;
		this.unit = unit;
	}

	public static UserLoginAnalyseParam getChartParam(int value) {
		for (UserLoginAnalyseParam userLoginAnalyseParam : UserLoginAnalyseParam.values()) {
			if (value == userLoginAnalyseParam.getIndex()) {
				return userLoginAnalyseParam;
			}
		}
		return null;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
