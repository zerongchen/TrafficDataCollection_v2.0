package com.aotain.tdc.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.tdc.model.common.KeyValueBean;

/**
 * table名参数值设置
 * @author jason
 *
 */
public enum TableDict {
	DIC_TABLECOLUMN("DIC_TABLECOLUMN","报表列基础表"),
	WEB_COLUMNUSER("WEB_COLUMNUSER","用户列隐藏表"),
	FLOW_DIRECTION("FLOW_DIRECTION","地域来源流量表(地域+运营商+五级业务)"),
	FLOW_DIRECTION_H("FLOW_DIRECTION_H","地域来源流量小时表(地域+运营商+五级业务)"),
	FLOW_DIRECTION_D("FLOW_DIRECTION_D","地域来源流量天表(地域+运营商+五级业务)"),
	FLOW_DIRECTION_M("FLOW_DIRECTION_M","地域来源流量月表(地域+运营商+五级业务)"),
	PROTOCOL_FLOW_STA("PROTOCOL_FLOW_STA","协议+三级业务统计"),
	SERVERBUILD_FLOW("SERVERBUILD_FLOW","流量监控-机房流量");
	
	private String tableName;
	
	private String description;

	private TableDict(String tableName, String description) {
		this.tableName = tableName;
		this.description = description;
	}

	public String getTableName() {
		return tableName;
	}

	public String getDescription() {
		return description;
	}

	public static List<KeyValueBean> getTableDicts(){
		List<KeyValueBean> keyValueDTOs = new ArrayList<KeyValueBean>();
		for(TableDict item : TableDict.values()){
			KeyValueBean keyValueDTO = new KeyValueBean();
			keyValueDTO.setKey(item.getTableName());
			keyValueDTO.setValue(item.getDescription());
			keyValueDTOs.add(keyValueDTO);
		}
		return keyValueDTOs;
	}

}
