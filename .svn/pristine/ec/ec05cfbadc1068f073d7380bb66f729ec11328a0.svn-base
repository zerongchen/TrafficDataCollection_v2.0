package com.aotain.tdc.model.common;

import com.alibaba.fastjson.*;

/**
  * @ClassName: MsgBean
  * @Description: 消息类
  * @date 2015年11月17日 下午2:20:24
  *
  */
public class MsgBean {
	
	/**
	  * @Fields flag : 0-成功；-1-失败
	  */
	private int flag;
	/**
	  * @Fields msg : 返回的成功或失败信息
	  */
	private String msg;
	
	/**
	  * toJson
	  *
	  * @Title: toJson
	  * @Description: 转换为json格式
	  * @param 
	  * @return String    返回类型
	  * @throws
	  */
	public String toString(){
		return JSON.toJSONString(this);
	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
