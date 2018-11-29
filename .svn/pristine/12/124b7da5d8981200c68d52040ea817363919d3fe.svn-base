package com.aotain.iqis.constant;

public enum AppQualityChartParam {
	APPTRAFFIC_TOTAL(1, "总流量","APPTRAFFIC_TOTAL","GB"), APPTRAFFIC_UP(2,"上行流量","APPTRAFFIC_UP","GB"), APPTRAFFIC_DN(3,"下行流量","APPTRAFFIC_DN","GB"), TCPCONNECT_COUNT(
			4,"TCP连接次数","TCPCONNECT_COUNT","万次"), TCPSUCCESSCONNECT_RATE(5,"TCP连接成功率","TCPSUCCESSCONNECT_RATE","%"), TCP_RETRANSMIT_RATE(6,"TCP重传率","TCP_RETRANSMIT_RATE","%"), SHAKEHANDS_DELAY(
			7,"握手时延","SHAKEHANDS_DELAY","ms"), SERVER_DELAY(8,"服务端时延","SERVER_DELAY","ms"), CLIENT_DELAY(9,"客户端时延","CLIENT_DELAY","ms"),HTTP_DELAY(10,"HTTP时延","HTTP_DELAY","ms");
	private int value;
	private String description;
	private String field;
	private String unit;

	private AppQualityChartParam(int value) {
		this.value = value;
	}

	private AppQualityChartParam(int value, String description, String field, String unit) {
		this.value = value;
		this.description = description;
		this.field = field;
		this.unit = unit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static AppQualityChartParam getChartParam(int value) {
		for (AppQualityChartParam app : AppQualityChartParam.values()) {
			if (value == app.getValue()) {
				return app;
			}
		}
		return null;
	}

	public static String getDesc(int value){
		for (AppQualityChartParam app : AppQualityChartParam.values()) {
			if (value == app.getValue()) {
				return app.getDescription();
			}
		}
		return "";
	}
	
	public static String getField(int value){
		for (AppQualityChartParam app : AppQualityChartParam.values()) {
			if (value == app.getValue()) {
				return app.getField();
			}
		}
		return "";
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
