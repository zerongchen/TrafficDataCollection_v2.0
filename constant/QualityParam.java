package com.aotain.iqis.constant;

public enum QualityParam {
	FAILTIMES("失败次数");
	private String description;

	private QualityParam(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
