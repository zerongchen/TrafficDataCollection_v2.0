package com.aotain.tdc.dto.common;

public class DicSysPositionBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private long positionId;
	private String positionName;
	private long parentId;
	private long positionType;
	public long getPositionId() {
		return positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public long getParentId() {
		return parentId;
	}
	public long getPositionType() {
		return positionType;
	}
	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public void setPositionType(long positionType) {
		this.positionType = positionType;
	}
}
