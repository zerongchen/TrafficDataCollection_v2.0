package com.aotain.tdc.dto.common;

public class DicBizConfig {
	private long id;
	private long bizId;
	private int confType;
	private String ip;
	private int mask;
	private int port;
	private String url;
	private int resourceMa;
	//1新增　2修改
	private int flag;
	public long getId() {
		return id;
	}
	public long getBizId() {
		return bizId;
	}
	public int getConfType() {
		return confType;
	}
	public String getIp() {
		return ip;
	}
	public int getMask() {
		return mask;
	}
	public int getPort() {
		return port;
	}
	public String getUrl() {
		return url;
	}
	public int getResourceMa() {
		return resourceMa;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setBizId(long bizId) {
		this.bizId = bizId;
	}
	public void setConfType(int confType) {
		this.confType = confType;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMask(int mask) {
		this.mask = mask;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setResourceMa(int resourceMa) {
		this.resourceMa = resourceMa;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
