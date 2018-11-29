package com.aotain.tdc.dto.common;

public class DicProtoClassBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private long classId;
	private String className;
	private long cataLogId;
	public long getClassId() {
		return classId;
	}
	public String getClassName() {
		return className;
	}
	public long getCataLogId() {
		return cataLogId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setCataLogId(long cataLogId) {
		this.cataLogId = cataLogId;
	}
}
