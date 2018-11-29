package com.aotain.tdc.dto.common;

public class TableColumns {
	private String field;
	private String title;
	private String align = "center";
	private String valign = "bottom";
	private boolean sortable = true;
	private boolean visible = true;
	private boolean switchable = true;
	private int order;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public TableColumns() {
	}
	
	public TableColumns(String field, String title) {
		this.field = field;
		this.title = title;
	}
	
	public TableColumns(String field, String title, String align,
			String valign) {
		this.field = field;
		this.title = title;
		this.align = align;
		this.valign = valign;
	}
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isSwitchable() {
		return switchable;
	}

	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}
	
}
