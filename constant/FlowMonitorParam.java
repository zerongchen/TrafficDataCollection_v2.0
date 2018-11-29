package com.aotain.iqis.constant;

/**
 * 流量监控下的相关参数名设置
 * 
 * @author jason
 * 
 */
public enum FlowMonitorParam {
	FLOW_TOTAL("总流量(GB)"), TRAFFIC_UP("上行流量(GB)"), TRAFFIC_DN("下行流量(GB)"), SPEED_TOTAL(
			"总流速(Gb/秒)"), SPEED_TOTAL_AVG("平均总流速(Gb/s)"),SPEED_TOTAL_MAX("最大总流速(Gb/s)"),
			SPEED_TOTAL_MIN("最小总流速(Gb/s)"),SPEED_UP("上行流速(Gb/秒)"), SPEED_UP_AVG("平均上行流速(Gb/秒)"),
			SPEED_UP_MAX("最大上行流速(Gb/秒)"),SPEED_UP_MIN("最小上行流速(Gb/秒)"),
			SPEED_DN("下行流速(Gb/秒)"),SPEED_DN_AVG("平均下行流速(Gb/秒)"), SPEED_DN_MAX("最大下行流速(Gb/秒)"),SPEED_DN_MIN("最小下行流速(Gb/秒)"),
			ONLINEUSER_COUNT("在线用户数"), ACTIVEUSER_COUNT("活跃用户数"),WHOLE_NETWORK("全网"),WHOLE_LINK("全部");
	private String description;

	private FlowMonitorParam(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
