package com.aotain.tdc.constant;


/**
 * 错误类型
 * @author yinzf 
 * @createtime 2014年11月21日 下午4:40:41
 */
public enum ErrorType {
	
	NOT_NULLABLE("不能为空"),
	DATA_TYPE_ERROR("数据类型错误"),
	ILLEGAL("非法"),
	REPEAT("重复"),
	EXIST("已存在"),
	NOT_EXIST("不存在");
	
	private String description;

	private ErrorType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
