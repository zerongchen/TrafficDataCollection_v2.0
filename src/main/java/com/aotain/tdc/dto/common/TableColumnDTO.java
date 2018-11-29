package com.aotain.tdc.dto.common;

import java.io.Serializable;

public class TableColumnDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -3092432685810330746L;
	private String field;
	private String title;
	private int column_type;
	private int usercolumn_type;
	private int order_type;
	private int switchable; //是否参与显示隐藏切换（1-是、2-否）
	private String align = "center";
	private String valign = "bottom";
	private boolean sortable = true;
	private int column_order;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getValign() {
		return valign;
	}

	public void setValign(String valign) {
		this.valign = valign;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public int getColumn_type() {
		return column_type;
	}

	public void setColumn_type(int column_type) {
		this.column_type = column_type;
	}

	public int getUsercolumn_type() {
		return usercolumn_type;
	}

	public void setUsercolumn_type(int usercolumn_type) {
		this.usercolumn_type = usercolumn_type;
	}

	public int getOrder_type() {
		return order_type;
	}

	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}

	public int getSwitchable() {
		return switchable;
	}

	public void setSwitchable(int switchable) {
		this.switchable = switchable;
	}

	public int getColumn_order() {
		return column_order;
	}

	public void setColumn_order(int column_order) {
		this.column_order = column_order;
	}

}
