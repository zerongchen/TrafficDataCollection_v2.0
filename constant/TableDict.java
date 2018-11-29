package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * table名参数值设置
 * @author jason
 *
 */
public enum TableDict {
	LINKFLOWMONITOR_TABLENAME("REPORT_LINKFLOW_MIN","链路实时流速统计表(以一分钟为统计粒度)"),
	LINKFLOWSTAT_TABLENAME("REPORT_LINKFLOWSTAT","链路流速分析统计表(统计粒度小时，天等)"),
	FLOWMINITOR_TABLENAME("REPORT_FLOW_MONITOR_MIN", "实时流量监控表(以5分钟为统计粒度)"),
	ONLINEUSER_TABLENAME("REPORT_FLOW_USERONLINE_H", "在线用户数统计(以1小时为统计粒度)"),
	TOTALFLOW_TABLENAME("REPORT_FLOW_TOTALFLOW", "总流量模块辅助表 用来做列的隐藏功能"),
	REALTIMEDIAL_TABLENAME("REPORT_FLOW_REALTIMEDIAL", "实时拨号统计表"),
	DEVICEQUOTA_TABLENAME("REPORT_FLOW_DEVICEQUOTA","设备指标监控"),
	DEVICEDNSQUOTA_TABLENAME("REPORT_FLOW_DEVICEDNS","设备DNS指标监控"),
	DIALFAIL_TABLENAME("REPORT_QUALITY_DIAL_FAIL_D", "拨号失败分析，按天统计的数据"),
	APPQUALITY_TABLENAME("REPORT_QUALITY_APP_MIN", "应用质量分析表"),
	FLOWANALYSE_TABLENAME("REPORT_SERVICE_FLOW_H", "业务分析下流量分析表(以小时为统计粒度)"),
	APPANALYSE_TABLENAME("REPORT_SERVICE_APP_H", "业务分析下的应用统计表(以小时为统计粒度)"),
	APPUSERANALYSE_TABLENAME("REPORT_SERVICE_APPUSER_H", "业务分析下的应用用户统计表(作为列隐藏的辅助表)"),
	WEBSITEANALYSE_TABLENAME("REPORT_SERVICE_WEBSITE_H", "业务分析下的网站统计表(以小时为统计粒度)"),
	USERLOGINANALYSE_TABLENAME("REPORT_SERVICE_USERLOGIN_D", "业务分析下的用户登录统计表(以天为统计粒度)"),
	SERVICE_ONLINETIME_D("REPORT_SERVICE_ONLINETIME_D", "业务分析下的在线时长统计表(以天为统计粒度)"),
	USERDIALANALYSE_TABLENAME("HBASE_RADIUSBILL","业务分析下的用户拨号记录分析"),
	REPORT_WEBSITE_ICP("REPORT_WEBSITE_ICP", "ICP网站分析表"),
	ONLINETIMEANALYSE_TABLENAME("REPORT_SERVICE_ONLINETIME_D","用户在线时长分析(以天为统计粒度)"),
	GRIDFLOWANALYSE_TABLENAME("REPORT_SERVICE_GRIDFLOW_D","业务分析下的网格流量分析(以天为统计粒度)"),
	COUNTRYDIRECTION_TABLENAME("REPORT_DIRECTION_COUNTRY_H","流向分析下的国家流向分析"),
	PROCITYDIRECTION_TABLENAME("REPORT_DIRECTION_PROCITY_H","流向分析下的省份流向分析"),
	SUPPLIERDIRECTION_TABLENAME("REPORT_DIRECTION_SUPPLIER_H","流向分析下的运营商流向分析"),
	DEFINEDIRECTION_TABLENAME("REPORT_DIRECTION_DEFINE_H","流向分析下的自定义流向分析"),
	CITYDIRECTION_TABLENAME("REPORT_DIRECTION_CITY_H","流向分析下的地市流向分析(作为列隐藏的辅助表)"),
	REPORT_WEBSITE_ICPCLICK("REPORT_WEBSITE_ICPCLICK","ICP网站点击分析"),
	REPORT_WEBSITE_CUSTOM("REPORT_WEBSITE_CUSTOM", "自定义网站分析"),
	REPORT_WEBSITE_CUSTOM_CLICK("REPORT_WEBSITE_CUSTOM_CLICK", "自定义网站点击分析"),
	ROLE_PHONENUMBER_SEARCH("ROLE_PHONENUMBER_SEARCH","外网号码分析"),
	REPORT_WEBSITE_ICPUSER("REPORT_WEBSITE_ICPUSER","ICP网站用户分析");
	
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

	public static List<KeyValueDTO> getTableDicts(){
		List<KeyValueDTO> keyValueDTOs = new ArrayList<KeyValueDTO>();
		for(TableDict item : TableDict.values()){
			KeyValueDTO keyValueDTO = new KeyValueDTO();
			keyValueDTO.setKey(item.getTableName());
			keyValueDTO.setValue(item.getDescription());
			keyValueDTOs.add(keyValueDTO);
		}
		return keyValueDTOs;
	}

}
