package com.aotain.tdc.dto.common;

public class DicPageModuleBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private long pageProductId;
	private long pageModuleId;
	private String pageModuleName;
	public long getPageProductId() {
		return pageProductId;
	}
	public void setPageProductId(long pageProductId) {
		this.pageProductId = pageProductId;
	}
	public long getPageModuleId() {
		return pageModuleId;
	}
	public void setPageModuleId(long pageModuleId) {
		this.pageModuleId = pageModuleId;
	}
	public String getPageModuleName() {
		return pageModuleName;
	}
	public void setPageModuleName(String pageModuleName) {
		this.pageModuleName = pageModuleName;
	}
	
	
}
