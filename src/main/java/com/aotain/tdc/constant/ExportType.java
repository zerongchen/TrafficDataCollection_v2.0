package com.aotain.tdc.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.tdc.model.common.KeyValueBean;

/**
 * 导出类型
 * @author yinzf 
 * @createtime 2015年9月2日 下午3:21:03
 */
public enum ExportType {
	
	EXCEL(".xlsx"),WORD(".doc"),PDF(".pdf");
	
	private String suffix;
	
	private ExportType(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

	public static List<KeyValueBean> getExportTypes(){
		List<KeyValueBean> dtoes = new ArrayList<KeyValueBean>();
		for(ExportType item : ExportType.values()){
			KeyValueBean dto = new KeyValueBean();
			dto.setKey("" + item.ordinal());
			dto.setValue(item.name());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
	public static List<KeyValueBean> getExcelOfExportTypes(){
		List<KeyValueBean> dtoes = new ArrayList<KeyValueBean>();
		for(ExportType item : ExportType.values()){
			if(item.equals(ExportType.EXCEL)){
				KeyValueBean dto = new KeyValueBean();
				dto.setKey("" + item.ordinal());
				dto.setValue(item.name());
				dtoes.add(dto);
			}
		}
		return dtoes;
	}
	
	/**
	 * 根据ordinal值获取枚举对象
	 * @param ordinal
	 * @return SystemActionLogType
	 */
	public static ExportType valueOf(int ordinal) {
		ExportType[] exportTypes = ExportType.values();
        if (ordinal < 0 || ordinal >= exportTypes.length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return exportTypes[ordinal];
    }
}
