package com.aotain.tdc.dto.common;


/**
 * 数据错误信息
 * @author yinzf 
 * @createtime 2014年11月21日 下午4:40:07
 */
public class DataErrorInfo {
	
	private int sheet; //工作表
	
	private int row; //行
	
	private int column; //列
	
	private String errorType; //错误类型
	
	@SuppressWarnings("unused")
	private Boolean isCorrect; //列数是否正确
	
	private int actualCount; //获取的列数
	
	private int count; //正确的列数
	

	public DataErrorInfo() {
		
	}

	public DataErrorInfo(int sheet, int row, int column, String errorType) {
		super();
		this.sheet = sheet;
		this.row = row;
		this.column = column;
		this.errorType = errorType;
	}

	public DataErrorInfo(int sheet, int actualCount, int count) {
		super();
		this.sheet = sheet;
		this.actualCount = actualCount;
		this.count = count;
	}

	public int getSheet() {
		return sheet + 1;
	}

	public int getRow() {
		return row + 1;
	}

	public int getColumn() {
		return column + 1;
	}

	public String getErrorType() {
		return errorType;
	}

	public Boolean getIsCorrect() {
		return actualCount >= count;
	}

	public int getActualCount() {
		return actualCount;
	}

	public int getCount() {
		return count;
	}

	public void setSheet(int sheet) {
		this.sheet = sheet;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public void setActualCount(int actualCount) {
		this.actualCount = actualCount;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
