package com.aotain.tdc.dto.common;

import java.util.ArrayList;
import java.util.List;

public class PageResult<T> {

	private long total;
	
	private List<T> rows = new ArrayList<T>();
	
	public PageResult() {
		
	}
	
	public PageResult(List<T> rows, long total) {
		this.rows = rows;
		this.total = total;
	}
	
	public long getTotal() {
		return total;
	}

	
	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
