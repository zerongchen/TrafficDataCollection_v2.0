package com.aotain.tdc.model.common;

import java.io.OutputStream;
import java.util.List;

/**
 * 导出公共类
 * @author yinzf 
 * @createtime 2015年9月5日 下午10:13:21
 */
public class BaseModel<T> {
	
	private int chartsType; //报表类型
	
	private int exportType; //导出类型@see ExportType
	
    private String fileName; //文件名称
    
    private String[] headers; //表头名称
    
    private String[] fields; //字段名称
    
    private float[] colWidths; //列宽
    
    private List<T> datas; //数据集
    
    private OutputStream os; //文件输出流x
    
    private List<String> dataURLs; //报表Base64图片dataURL
    
	public int getChartsType() {
		return chartsType;
	}

	public int getExportType() {
		return exportType;
	}

	public String getFileName() {
		return fileName;
	}

	public String[] getHeaders() {
		return headers;
	}

	public String[] getFields() {
		return fields;
	}

	public float[] getColWidths() {
		return colWidths;
	}

	public List<T> getDatas() {
		return datas;
	}

	public OutputStream getOs() {
		return os;
	}
	
	public List<String> getDataURLs() {
		return dataURLs;
	}

	public void setChartsType(int chartsType) {
		this.chartsType = chartsType;
	}

	public void setExportType(int exportType) {
		this.exportType = exportType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public void setColWidths(float[] colWidths) {
		this.colWidths = colWidths;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public void setDataURLs(List<String> dataURLs) {
		this.dataURLs = dataURLs;
	}

}
