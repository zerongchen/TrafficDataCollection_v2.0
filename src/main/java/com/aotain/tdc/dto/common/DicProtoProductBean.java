package com.aotain.tdc.dto.common;

public class DicProtoProductBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private long productId;
	private String productName;
	private long classId;
	private long catalogId;
	private long serverBuildId;
	private long serverRoomId;
	public long getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public long getClassId() {
		return classId;
	}
	public long getCatalogId() {
		return catalogId;
	}
	public long getServerBuildId() {
		return serverBuildId;
	}
	public long getServerRoomId() {
		return serverRoomId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public void setCatalogId(long catalogId) {
		this.catalogId = catalogId;
	}
	public void setServerBuildId(long serverBuildId) {
		this.serverBuildId = serverBuildId;
	}
	public void setServerRoomId(long serverRoomId) {
		this.serverRoomId = serverRoomId;
	}
}
