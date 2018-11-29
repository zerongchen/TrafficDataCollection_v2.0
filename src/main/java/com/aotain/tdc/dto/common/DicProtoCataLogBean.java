package com.aotain.tdc.dto.common;

public class DicProtoCataLogBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private long cataLogId;
	private String cataName;
	private int orderId;
	public long getCataLogId() {
		return cataLogId;
	}
	public String getCataName() {
		return cataName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setCataLogId(long cataLogId) {
		this.cataLogId = cataLogId;
	}
	public void setCataName(String cataName) {
		this.cataName = cataName;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
