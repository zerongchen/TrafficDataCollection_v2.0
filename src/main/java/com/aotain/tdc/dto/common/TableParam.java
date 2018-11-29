package com.aotain.tdc.dto.common;

public class TableParam {
	
	private String tableName;
	
	private Integer operID;
	
	private String column_value;

	public TableParam() {
	}

	public TableParam(String tableName, Integer operID) {
		this.tableName = tableName;
		this.operID = operID;
	}

	public TableParam(String tableName, String column_value) {
		super();
		this.tableName = tableName;
		this.column_value = column_value;
	}

	public String getColumn_value() {
		return column_value;
	}

	public void setColumn_value(String column_value) {
		this.column_value = column_value;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getOperID() {
		return operID;
	}

	public void setOperID(Integer operID) {
		this.operID = operID;
	}

}
