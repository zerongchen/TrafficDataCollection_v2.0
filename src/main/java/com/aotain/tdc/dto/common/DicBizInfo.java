package com.aotain.tdc.dto.common;

import java.util.List;

public class DicBizInfo {
	private int bizId;
	private String bizIds;
	private String bizName;
	private int parentId;	
	private int deleteFlag;
	private int orderId;
	private int resourcemark;
	private String serverbuildroom;
	//1新增　2修改
	private int flag;	
	private List<DicBizConfig> bizConfigs;
	public int getBizId() {
		return bizId;
	}
	public String getBizName() {
		return bizName;
	}
	public int getParentId() {
		return parentId;
	}
	
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<DicBizConfig> getBizConfigs() {
		return bizConfigs;
	}
	public void setBizConfigs(List<DicBizConfig> bizConfigs) {
		this.bizConfigs = bizConfigs;
	}
	
	public String getBizIds() {
		return bizIds;
	}
	public void setBizIds(String bizIds) {
		this.bizIds = bizIds;
	}
	public int getResourcemark() {
		return resourcemark;
	}
	public void setResourcemark(int resourcemark) {
		this.resourcemark = resourcemark;
	}
	public String getServerbuildroom() {
		return serverbuildroom;
	}
	public void setServerbuildroom(String serverbuildroom) {
		this.serverbuildroom = serverbuildroom;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
