package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 统计指标
 * @author yinzf 
 * @createtime 2015年7月25日 下午7:33:42
 */
public enum StaIndicator {
	
	TRAFFIC_TOTAL(1,"总流量","traffic_total","GB"),
	
	TRAFFIC_UP(2,"上行流量","traffic_up","GB"),
	
	TRAFFIC_DN(3,"下行流量","traffic_dn","GB"),
	
	TCPCONNECT_COUNT(4,"TCP连接次数","tcpconnect_count","万次"),
	
	TCPSUCCESSCONNECT_RATE(5,"TCP连接成功率","tcpsuccessconnect_rate","%"),
	
	TCP_RETRANSMIT_RATE(6,"TCP数据包重传率","tcp_retransmit_rate","%"),
	
	SHAKEHANDS_DELAY(7,"握手时延","shakehands_delay","ms"),
	
	SERVER_DELAY(8,"服务端时延","server_delay","ms"),
	
	CLIENT_DELAY(9,"客户端时延","client_delay","ms"),
	
	HTTP_DELAY(10,"HTTP时延","http_delay","ms"),	
	
	DNS_DELAY(11,"DNS解析时延","dns_delay","ms"),
	
	FIRSTBYTE_LOADINGTIME(12,"首字节加载时长","firstbyte_loadingtime","ms"),
	
	LOADING_TIME(13,"加载时长","loading_time","ms"),
	
	VISITSUCCESS_RATE(14,"网站访问成功率","visitsuccess_rate","%"),	
	
	DNSANALY_RATE(15,"DNS解析成功率","dnsanaly_rate","%"),
	
	HTTPGET_RATE(16,"HTTP GET成功率","httpget_rate","%"),
	
	HTTPPOST_RATE(17,"HTTP POST成功率","httppost_rate","%"),
	
	PAGE_VIEW(18,"访问量","page_view","万次"),
	
	SERVER_ERROR_RATE(19,"服务端错误率","server_error_rate","%"),
	
	CLICK_TIMES(20,"点击次数","click_times","万次"),
	
	RESPONSE_TIMES(21,"响应次数","response_times","万次"),
	
	RESPONSE_RATE(22,"响应率","response_rate","%"),
	
	RESPONSE_SUC_RATE(23,"响应成功率","response_suc_rate","%"),
	
	NORMAL_RESPONSE_SUC_RATE(24,"正常响应成功率","normal_response_suc_rate","%"),
	
	REDIRECT_RATE(25,"重定向率","redirect_rate","%"),
	
	TCPSUCCESSCONNECT_COUNT(26,"TCP连接成功次数","tcpsuccessconnect_count","万次"),
	TCPDATAPACKAGE_COUNT(27,"数据包数","tcpsuccessconnect_count","万次"),
	TCPRETRANSMIT_COUNT(28,"TCP数据包重传次数","tcpretransmit_count","万次"),
	HTTPGETTIMES(29,"HTTP GET次数","httpgettimes","万次"),
	HTTPGETSUCTIMES(30,"HTTP GET成功次数","httpgetsuctimes","万次"),
	HTTPPOSTTIMES(31,"HTTP POST次数","httpposttimes","万次"),
	HTTPPOSTSUCTIMES(32,"HTTP POST成功次数","httppostsuctimes","万次"),
	RESPONSESUCTIMES(33,"响应成功次数","responsesuctimes","万次"),
	RESPONSEERRORTIMES(34,"响应错误次数","responseerrortimes","万次"),
	RESPONSEREDIRECTTIMES(35,"重定向次数","responseredirecttimes","万次"),
	DNSRESPONSETIMES(36,"DNS响应次数","dnsresponsetimes","万次"),
	DNSRESPONSE_RATE(37,"DNS响应成功率","dnsresponse_rate","%"),
	
	
	SPEED_TOTAL(38,"总流速","speed_total","Gb/s"),
	SPEED_UP(39,"上行流速","speed_up","Gb/s"),
	SPEED_DN(40,"下行流速","speed_dn","Gb/s");
	
	private int value;
	
	private String description;
	
	private String field;
	
	private String unit;

	private StaIndicator(int value) {
		this.value = value;
	}

	private StaIndicator(int value, String description, String field,
			String unit) {
		this.value = value;
		this.description = description;
		this.field = field;
		this.unit = unit;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public String getField() {
		return field;
	}

	public String getUnit() {
		return unit;
	}

	public static StaIndicator getChartParam(int value) {
		for (StaIndicator app : StaIndicator.values()) {
			if (value == app.getValue()) {
				return app;
			}
		}
		return null;
	}
	
	public static StaIndicator getChartParamByDesc(String desc) {
		for (StaIndicator app : StaIndicator.values()) {
			if (app.getDescription().equals(desc)) {
				return app;
			}
		}
		return null;
	}
	
	public static String getDesc(int value){
		for (StaIndicator app : StaIndicator.values()) {
			if (value == app.getValue()) {
				return app.getDescription();
			}
		}
		return "";
	}
	
	public static String getField(int value){
		for (StaIndicator app : StaIndicator.values()) {
			if (value == app.getValue()) {
				return app.getField();
			}
		}
		return "";
	}
	
	//ICP网站分析所需统计指标
	public static List<StaIndicator> getStaIndicatorsForWebsiteICP(){
		List<StaIndicator> staIndicators = new ArrayList<StaIndicator>();
		staIndicators.add(StaIndicator.TRAFFIC_TOTAL);
		staIndicators.add(StaIndicator.TRAFFIC_UP);
		staIndicators.add(StaIndicator.TRAFFIC_DN);
		staIndicators.add(StaIndicator.TCPCONNECT_COUNT);
		staIndicators.add(StaIndicator.PAGE_VIEW);
		staIndicators.add(StaIndicator.SHAKEHANDS_DELAY);
		staIndicators.add(StaIndicator.SERVER_DELAY);
		staIndicators.add(StaIndicator.CLIENT_DELAY);
		staIndicators.add(StaIndicator.FIRSTBYTE_LOADINGTIME);
		staIndicators.add(StaIndicator.LOADING_TIME);
		staIndicators.add(StaIndicator.DNS_DELAY);
		staIndicators.add(StaIndicator.TCPSUCCESSCONNECT_RATE);
		staIndicators.add(StaIndicator.TCP_RETRANSMIT_RATE);
		staIndicators.add(StaIndicator.VISITSUCCESS_RATE);
		staIndicators.add(StaIndicator.DNSANALY_RATE);
		staIndicators.add(StaIndicator.HTTPGET_RATE);
		staIndicators.add(StaIndicator.HTTPPOST_RATE);
		return staIndicators;
	}
	
	//ICP网站点击分析所需统计指标
	public static List<StaIndicator> getStaIndicatorsForWebsiteICPClick(){
		List<StaIndicator> staIndicators = new ArrayList<StaIndicator>();
		staIndicators.add(StaIndicator.CLICK_TIMES);
		staIndicators.add(StaIndicator.RESPONSE_TIMES);
		staIndicators.add(StaIndicator.DNS_DELAY);
		staIndicators.add(StaIndicator.RESPONSE_RATE);
		staIndicators.add(StaIndicator.RESPONSE_SUC_RATE);
		staIndicators.add(StaIndicator.NORMAL_RESPONSE_SUC_RATE);
		staIndicators.add(StaIndicator.REDIRECT_RATE);
		staIndicators.add(StaIndicator.SERVER_ERROR_RATE);
		staIndicators.add(StaIndicator.DNSANALY_RATE);
		staIndicators.add(StaIndicator.HTTPGET_RATE);
		staIndicators.add(StaIndicator.HTTPPOST_RATE);
		return staIndicators;
	}
	
	//ICP网站分析所需统计指标
	public static Map<String, String> mapForWebsiteICP(){
		Map<String, String> map = new HashMap<String, String>();
		List<StaIndicator> staIndicators = getStaIndicatorsForWebsiteICP();
		for(StaIndicator item : staIndicators){
			map.put(item.getDescription(), item.getUnit());
		}
		return map;
	}
	
	//ICP网站点击分析所需统计指标
	public static Map<String, String> mapForWebsiteICPClick(){
		Map<String, String> map = new HashMap<String, String>();
		List<StaIndicator> staIndicators = getStaIndicatorsForWebsiteICPClick();
		for(StaIndicator item : staIndicators){
			map.put(item.getDescription(), item.getUnit());
		}
		return map;
	}
	
	public static StaIndicator getStaIndicatorByField(String field) {
		for (StaIndicator sd : StaIndicator.values()) {
			if (field.equalsIgnoreCase(sd.getField())) {
				return sd;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否为DNS相关指标
	 * @param field
	 * @return
	 */
	public static boolean isDNSQuota(String field){
		if(field.equalsIgnoreCase(StaIndicator.DNS_DELAY.getField()) || field.equalsIgnoreCase(StaIndicator.DNSANALY_RATE.getField())|| field.equalsIgnoreCase(StaIndicator.DNSRESPONSE_RATE.getField())){
			return true;
		}
		return false;
	}
	
	//业务流量分析所需指标
	public static List<StaIndicator> getServiceFlowOfStaIndicator(){
		List<StaIndicator> staIndicators = new ArrayList<StaIndicator>();
		staIndicators.add(StaIndicator.TRAFFIC_TOTAL);
		staIndicators.add(StaIndicator.TRAFFIC_UP);
		staIndicators.add(StaIndicator.TRAFFIC_DN);
		staIndicators.add(StaIndicator.TCPCONNECT_COUNT);
		staIndicators.add(StaIndicator.SHAKEHANDS_DELAY);
		staIndicators.add(StaIndicator.CLIENT_DELAY);
		staIndicators.add(StaIndicator.SERVER_DELAY);
		staIndicators.add(StaIndicator.HTTP_DELAY);
		staIndicators.add(StaIndicator.TCPSUCCESSCONNECT_RATE);
		staIndicators.add(StaIndicator.TCP_RETRANSMIT_RATE);
		return staIndicators;
	}
	
	//业务流量分析所需指标
	public static Map<String, String> mapForServiceFlow(){
		Map<String, String> map = new HashMap<String, String>();
		List<StaIndicator> staIndicators = getServiceFlowOfStaIndicator();
		for(StaIndicator item : staIndicators){
			map.put(item.getDescription(), item.getUnit());
		}
		return map;
	}
	
	//国家流向分析所需指标
	public static List<StaIndicator> getCountryDirectOfStaIndicator(){
		List<StaIndicator> staIndicators = new ArrayList<StaIndicator>();
		staIndicators.add(StaIndicator.TRAFFIC_TOTAL);
		staIndicators.add(StaIndicator.TRAFFIC_UP);
		staIndicators.add(StaIndicator.TRAFFIC_DN);
		staIndicators.add(StaIndicator.TCPCONNECT_COUNT);
		staIndicators.add(StaIndicator.SHAKEHANDS_DELAY);
		staIndicators.add(StaIndicator.CLIENT_DELAY);
		staIndicators.add(StaIndicator.SERVER_DELAY);
		staIndicators.add(StaIndicator.HTTP_DELAY);
		staIndicators.add(StaIndicator.TCPSUCCESSCONNECT_RATE);
		return staIndicators;
	}
	
	//国家分析所需指标
	public static Map<String, String> mapForCountryDirect(){
		Map<String, String> map = new HashMap<String, String>();
		List<StaIndicator> staIndicators = getCountryDirectOfStaIndicator();
		for(StaIndicator item : staIndicators){
			map.put(item.getDescription(), item.getUnit());
		}
		return map;
	}
	
	/**
	 * 针对设备监控的指标参数
	 * @return
	 */
	public static List<KeyValueDTO> listForDeviceMonitor(){
		List<KeyValueDTO>  deviceList = new ArrayList<KeyValueDTO>();
		deviceList.add(new KeyValueDTO(StaIndicator.TRAFFIC_TOTAL.getValue()+"",StaIndicator.TRAFFIC_TOTAL.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.TRAFFIC_UP.getValue()+"",StaIndicator.TRAFFIC_UP.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.TRAFFIC_DN.getValue()+"",StaIndicator.TRAFFIC_DN.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.TCPCONNECT_COUNT.getValue()+"",StaIndicator.TCPCONNECT_COUNT.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.FIRSTBYTE_LOADINGTIME.getValue()+"",StaIndicator.FIRSTBYTE_LOADINGTIME.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.SHAKEHANDS_DELAY.getValue()+"",StaIndicator.SHAKEHANDS_DELAY.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.CLIENT_DELAY.getValue()+"",StaIndicator.CLIENT_DELAY.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.SERVER_DELAY.getValue()+"",StaIndicator.SERVER_DELAY.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.HTTP_DELAY.getValue()+"",StaIndicator.HTTP_DELAY.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.TCPSUCCESSCONNECT_RATE.getValue()+"",StaIndicator.TCPSUCCESSCONNECT_RATE.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.TCP_RETRANSMIT_RATE.getValue()+"",StaIndicator.TCP_RETRANSMIT_RATE.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.HTTPGET_RATE.getValue()+"",StaIndicator.HTTPGET_RATE.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.HTTPPOST_RATE.getValue()+"",StaIndicator.HTTPPOST_RATE.getDescription()));
		deviceList.add(new KeyValueDTO(StaIndicator.NORMAL_RESPONSE_SUC_RATE.getValue()+"",StaIndicator.NORMAL_RESPONSE_SUC_RATE.getDescription()));
		return deviceList;
	}
	
	/**
	 * 针对设备DNS指标数据的监控
	 * @return
	 */
	public static List<KeyValueDTO> listForDeviceDNSMonitor(){
		List<KeyValueDTO>  deviceDNSList = new ArrayList<KeyValueDTO>();
		deviceDNSList.add(new KeyValueDTO(StaIndicator.DNS_DELAY.getValue()+"",StaIndicator.DNS_DELAY.getDescription()));
		deviceDNSList.add(new KeyValueDTO(StaIndicator.DNSANALY_RATE.getValue()+"",StaIndicator.DNSANALY_RATE.getDescription()));
		deviceDNSList.add(new KeyValueDTO(StaIndicator.DNSRESPONSE_RATE.getValue()+"",StaIndicator.DNSRESPONSE_RATE.getDescription()));
		return deviceDNSList;
	}

	public static List<KeyValueDTO> getStatLinkFlowParam() {
		List<KeyValueDTO>  statLinkFlowList = new ArrayList<KeyValueDTO>();
		statLinkFlowList.add(new KeyValueDTO(StaIndicator.SPEED_TOTAL.getValue()+"",StaIndicator.SPEED_TOTAL.getDescription()));
		statLinkFlowList.add(new KeyValueDTO(StaIndicator.SPEED_UP.getValue()+"",StaIndicator.SPEED_UP.getDescription()));
		statLinkFlowList.add(new KeyValueDTO(StaIndicator.SPEED_DN.getValue()+"",StaIndicator.SPEED_DN.getDescription()));
		return statLinkFlowList;
	}
}
