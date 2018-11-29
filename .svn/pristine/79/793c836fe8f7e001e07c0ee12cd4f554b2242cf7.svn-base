package com.aotain.tdc.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.tdc.model.common.KeyValueBean;

/**
 * 模块类型
 * @author yinzf
 * @createtime 2015年7月21日 下午5:31:40
 */
public enum ModuleType {

	QUALITY_AREAQUALITY_STATISTIC("质量统计报表"),
	
	QUALITY_AREAQUALITY_TREND("质量趋势报表"),
	
	QUALITY_CARRIERQUALITY_STATISTIC("运营商质量统计报表"),
	
	QUALITY_CARRIERQUALITY_TREND("运营商质量趋势报表"),
	
	QUALITY_REGIONCARRIERQUALITY_STATISTIC("地域运营商质量统计报表"),
	
	QUALITY_REGIONCARRIERQUALITY_TREND("地域运营商质量趋势报表"),
	
	QUALITY_PRODUCTQUALITY_TREND("业务质量趋势报表"),
	
	QUALITY_PRODUCTQUALITY_STATISTIC("业务质量统计报表"),
	
	TRAFFIC_PRODUCTQUALITY_TREND("业务流向趋势报表"),
	
	TRAFFIC_PRODUCTQUALITY_STATISTIC("业务流向统计报表"),

	TRAFFIC_ROOMTRAFFIC_STATISTIC("机房流量趋势报表"),
	
	TRAFFIC_ROOMTRAFFIC_TREND("机房流量占比报表"),
	
	QUALITY_PROTOCOLTRAFFIC_FLOW_STATISTIC("协议流量统计报表"),
	
	QUALITY_PROTOCOLTRAFFIC_FLOW_TREND("协议流量趋势报表"),
	
	QUALITY_REGION_FLOW_STATISTIC("地域流量统计报表"),
	
	QUALITY_REGION_FLOW_TREND("地域流量趋势报表"),
	
    QUALITY_CARRIER_FLOW_STATISTIC("运营商流量统计报表"),
    
    QUALITY_ERRORCODE_STATISTIC("错误码分布统计报表"),
    
    QUALITY_ERRORCODE_TREND("错误码分布统计报表"),
	
	QUALITY_CARRIER_FLOW_TREND("运营商流量趋势报表"),
	
	QUALITY_REGIONCARRIER_FLOW_STATISTIC("地域运营商流量统计报表"),
		
	QUALITY_REGIONCARRIER_FLOW_TREND("地域运营商流量趋势报表"),
	
	QUALITY_BASERESPOOL_FLOW_STATISTIC("基地资源池流量统计报表"),
		
	QUALITY_BASERESPOOL_FLOW_TREND("基地资源池趋势报表"),
	
	QUALITY_IPADDRESS_FLOW_STATISTIC("IP地址流量统计报表"),
	
	QUALITY_IPADDRESS_FLOW_TREND("IP地址趋势报表"),
	
	QUALITY_IPADDRESS_STATISTIC("IP地址质量统计报表"),
	
	QUALITY_IPADDRESS_TREND("IP地址质量趋势报表"),
	
	BUSINESSANALYSIS_TOPBUSINESSANALYSIS("TOP业务分析报表"),
	
	BUSINESSANALYSIS_BUSINESSTREND("业务发展趋势报表"),
	
	BUSINESSANALYSIS_TOPPAGEANALYSIS("TOP页面分析报表"),
	
	QUALITY_CALLBILL("话单回溯"),
	
	WEB_MODULE_QUOTA("阈值管理"),
	
	POLICY_INTERFACE_ACCOUT("接口账户管理"),
	
	POLICY_FTPSERVER_ACCOUT("FTP服务器管理"),
	
	POLICY_FLOW_COLLECTION_STRATEGY("流量采集管理"),
	
	POLICY_FLOW_RECOVERY_STRATEGY("流量还原管理"),
	
	WEB_SERVICEINFO("业务信息管理"),
	
	REGIONPRODUCT_BUSINESS_ANALYSIS("业务地域分布"),
	
	CARRIERPRODUCT_BUSINESS_ANALYSIS("业务运营商分布"),
	
	DATA_REUSE("数据复用监控"),
	
	PRODUCT_ANALYSIS_URL_STATISTIC("热门url统计报表");

	private String description;
	
	private ModuleType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static List<KeyValueBean> getModuleTypes(){
		List<KeyValueBean> dtoes = new ArrayList<KeyValueBean>();
		for(ModuleType item : ModuleType.values()){
			KeyValueBean dto = new KeyValueBean();
			dto.setKey(item.ordinal() + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
}
