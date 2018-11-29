package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.DictEntity;


public class Dict {
	private static List<DictEntity> dtStatType = new ArrayList<DictEntity>();
	private static List<DictEntity> serviceType = new ArrayList<DictEntity>();
	public static List<DictEntity> GetStatTypeList(int callType) {
		dtStatType.clear();
		if (callType == 1) {
			if (dtStatType.size() == 0) {
				dtStatType.add(new DictEntity("1", "分钟"));
				dtStatType.add(new DictEntity("2", "小时"));
				dtStatType.add(new DictEntity("3", "日"));
			}
		}
		if (callType == 2) {
			if (dtStatType.size() == 0) {
				dtStatType.add(new DictEntity("2", "小时"));
				dtStatType.add(new DictEntity("3", "日"));
			}
		}
		if (callType == 3) {
			if (dtStatType.size() == 0) {
				dtStatType.add(new DictEntity("3", "日"));
				dtStatType.add(new DictEntity("4", "周"));
				dtStatType.add(new DictEntity("5", "月"));
			}
		}
		return dtStatType;
	}
	public static List<DictEntity> getServiceTypeList(int callType) {
		serviceType.clear();
		//实时流量监控，0代表的全网
		if (callType == 1) {
			if (serviceType.size() == 0) {
				serviceType.add(new DictEntity("", "全网"));
				serviceType.add(new DictEntity("1", "专线"));
				serviceType.add(new DictEntity("2", "家宽"));
				serviceType.add(new DictEntity("3", "WLAN"));
			}
		}
		return serviceType;
	}
}
