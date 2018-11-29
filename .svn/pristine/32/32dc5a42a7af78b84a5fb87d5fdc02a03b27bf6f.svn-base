package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.DictEntity;

/**
 * 业务类型参数值设置
 * @author jason
 *
 */
public enum ServiceType {
	UNKNOW(0,"未知"),ZHUANXIAN(1, "专线"), JIAKUAN(2, "家宽"), WLAN(3, "WLAN"),IPSECTION(4,"IP段"),WHOLE_NETWORK(10,"全网");
	private int index;
	private String text;

	private ServiceType(int index, String text) {
		this.index = index;
		this.text = text;
	}

	public static String getServiceTypeName(int value, String defaultName) {
		for (ServiceType type : ServiceType.values()) {
			if (type.getIndex() == value) {
				return type.getText();
			}
		}
		return defaultName;
	}

	public static ServiceType getServiceType(int value) {
		for (ServiceType type : ServiceType.values()) {
			if (type.getIndex() == value) {
				return type;
			}
		}
		return null;
	}

	public static List<DictEntity> getServiceTypeList(int callType) {
		List<DictEntity> serviceType = new ArrayList<DictEntity>();
		// 实时流量监控，空代表的全网
		if (callType == 1) {
			serviceType.add(new DictEntity("", "全网"));
			serviceType.add(new DictEntity(ServiceType.ZHUANXIAN.getIndex()+"", ServiceType.ZHUANXIAN.getText()));
			serviceType.add(new DictEntity(ServiceType.JIAKUAN.getIndex()+"", ServiceType.JIAKUAN.getText()));
			serviceType.add(new DictEntity(ServiceType.WLAN.getIndex()+"", ServiceType.WLAN.getText()));
		}
		else if(callType == 2){
			serviceType.add(new DictEntity(ServiceType.WHOLE_NETWORK.getIndex()+"", "全网"));
			serviceType.add(new DictEntity(ServiceType.ZHUANXIAN.getIndex()+"", ServiceType.ZHUANXIAN.getText()));
			serviceType.add(new DictEntity(ServiceType.JIAKUAN.getIndex()+"", ServiceType.JIAKUAN.getText()));
			serviceType.add(new DictEntity(ServiceType.WLAN.getIndex()+"", ServiceType.WLAN.getText()));
			serviceType.add(new DictEntity(ServiceType.IPSECTION.getIndex()+"", ServiceType.IPSECTION.getText()));
		}
		else if(callType == 3){
			serviceType.add(new DictEntity("", "全网"));
			serviceType.add(new DictEntity(ServiceType.ZHUANXIAN.getIndex()+"", ServiceType.ZHUANXIAN.getText()));
			serviceType.add(new DictEntity(ServiceType.JIAKUAN.getIndex()+"", ServiceType.JIAKUAN.getText()));
		}
		else if(callType == 4){
			serviceType.add(new DictEntity(ServiceType.ZHUANXIAN.getIndex()+"", ServiceType.ZHUANXIAN.getText()));
			serviceType.add(new DictEntity(ServiceType.JIAKUAN.getIndex()+"", ServiceType.JIAKUAN.getText()));
		}
		return serviceType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
