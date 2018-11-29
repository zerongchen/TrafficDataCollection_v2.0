package com.aotain.iqis.constant.userManagement;

/**
 * 用户管理类型
 * @author yinzf 
 * @createtime 2015年8月17日 上午10:35:29
 */
public enum GridType {
	
	AREA("区域"),
	TOWN("镇区"),
	GRID("网格"),
	BRAS("上联设备"),
	COMMUNITY("小区"),
	OLT("OLT");
	
	private String description;

	private GridType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
}
