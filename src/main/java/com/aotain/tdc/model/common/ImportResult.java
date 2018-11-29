package com.aotain.tdc.model.common;

import com.aotain.tdc.dto.common.DataErrorInfo;

public class ImportResult {
	
	private int count; //成功导入数据的行数
	
	private String fileType; //文件类型
	
	private DataErrorInfo dataErrorInfo; //错误信息

	public int getCount() {
		return count;
	}

	public String getFileType() {
		return fileType;
	}

	public DataErrorInfo getDataErrorInfo() {
		return dataErrorInfo;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setDataErrorInfo(DataErrorInfo dataErrorInfo) {
		this.dataErrorInfo = dataErrorInfo;
	}
	
}
