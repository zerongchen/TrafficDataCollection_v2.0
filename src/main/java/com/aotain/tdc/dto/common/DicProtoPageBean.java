package com.aotain.tdc.dto.common;

public class DicProtoPageBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private long pageId;
	private String pageName;
	private long moduleId;
	private long productId;
	private long classId;
	private long cataLogId;
	private String pageUrl;
	private String descInfo;
	public long getPageId() {
		return pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public long getModuleId() {
		return moduleId;
	}
	public long getProductId() {
		return productId;
	}
	public long getClassId() {
		return classId;
	}
	public long getCataLogId() {
		return cataLogId;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public String getDescInfo() {
		return descInfo;
	}
	public void setPageId(long pageId) {
		this.pageId = pageId;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public void setCataLogId(long cataLogId) {
		this.cataLogId = cataLogId;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}
}
