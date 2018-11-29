package com.aotain.tdc.constant;

/**
 * 质量指标类型
 * @author 凯哥
 *
 */
public enum KPIType {
	AREA_KPI("地域质量指标"),
	CARRIER_KPI("运营商质量指标"),
	ROOM_KPI("机房质量指标"),
	PRODUCT_KPI("业务质量指标"),
	IP_KPI("IP地址质量指标");
	private String type;
	private KPIType(String type){
		this.setType(type);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
