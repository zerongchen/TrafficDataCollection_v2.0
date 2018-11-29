package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 模块类型
 * @author yinzf
 * @createtime 2015年7月21日 下午5:31:40
 */
public enum ModuleType {
	LINKFLOW_MONITOR("链路实时流速监控"),
	STATLINKFLOW_MONITOR("链路流速分析"),
	FLOW_MONITOR("实时流量监控"),
	TOTAL_FLOW("总流量占比分析"),
	ONLINE_USER("在线用户监控"),
	DIAL_COUNT("实时拨号统计"),
	DEVICE_MONITOR("设备指标监控"),
	DEVICE_DNSMONITOR("设备DNS指标监控"),
	IMPORTENT_MONITOR("重点监控"),
	MONITOR_DEFINE("监控定义"),
	DNS_ALARM("DNS告警数据查看"),
	DIAL_ALARM("拨号告警数据查看"),
	APP_QUALITY("应用质量分析"),
	DIAL_FAIL("拨号失败分析"),
	FLOW_ANALYSE("业务流量分析"),
	APP_ANALYSE("业务应用分析"),
	APPUSER_ANALYSE("应用用户分析"),
	WEBSITE_ANALYSE("业务网站分析"),
	GRID_FLOW_ANALYSE("网格流量分析"),
	USER_LOGIN_ANALYSE("用户登录分析"),
	ONLINE_TIME_ANALYSE("在线时长分析"),
	USER_DIAL_ANALYSE("用户拨号记录分析"),
	COUNTRY_DIRECTION("国家流向分析"),
	PROVINCE_DIRECTION("省份流向分析"),
	CITY_DIRECTION("地市流向分析"),
	OPERATORS_FLOW("运营商流向分析"),
	SELF_DEFINE_FLOW("自定义流向分析"),
	FLOW_AREA_MAN("流向区域组管理"),
	WEBSITE_ICP("ICP网站分析"),
	WEBSITE_ICP_CLICK("ICP网站点击分析"),
	WEBSITE_CUSTOM("自定义网站分析"),
	WEBSITE_CUSTOM_CLICK("自定义网站点击分析"),
	WEBSITE_MAG("网站管理"),
	DOMAIN_MAG("域名管理"),
	ORIGINAL_BILL_SEARCH("原始话单查询"),
	PERSONAL_USER_MAG("专线用户管理"),
	HOME_USER_MAG("家宽用户管理"),
	WLAN_USER_MAG("WLAN用户管理"),
	IPSEG_USER_MAG("IP段用户管理"),
	APP_CATEGORY("应用分类管理"),
	IPSEG_REPOSITORY("IP地址库管理"),
	CLOG_DEFINE("CLOG定义配置"),
	CHART_MAG("报表管理"),
	SYSTEM_PARAM("系统参数配置"),
	PHONENUMBER_MAG("号码配置"),
	ROLE_PHONENUMBER_SEARCH("外网号码分析");
	private String description;
	
	private ModuleType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static List<KeyValueDTO> getModuleTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(ModuleType item : ModuleType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey(item.ordinal() + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
}
