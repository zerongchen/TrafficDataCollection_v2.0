package com.aotain.common.util;

import java.math.BigDecimal;
/**
 * 数据中文值获取工具类
 * @author huangjb
 *
 */
public class DataCodeUtil {
	public static String getCtrlType(BigDecimal ctrlType){
		String ret="";
		if(ctrlType.toString().equals("0")){
			ret="不做控制";
		}else if(ctrlType.toString().equals("1")){
			ret="强制页面";
		}else if(ctrlType.toString().equals("2")){
			ret="服务阻断";
		}else if(ctrlType.toString().equals("3")){
			ret="强制和阻断";
		}else if(ctrlType.toString().equals("4")){
			ret="并发连接数控制";
		}
		return ret;
	}
	
	public static String getUserStatus(BigDecimal userStatus){
		String ret="";
		if(userStatus.toString().equals("1")){
			ret="白名单";
		}else if(userStatus.toString().equals("2")){
			ret="黑名单";
		}else if(userStatus.toString().equals("3")){
			ret="检测";
		}
		return ret;
	}
}
