package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 运营商枚举
 * 
 * @author jason
 * 
 */
public enum SupplierType {
	MOBILE(1, "移动"), TELECOM(2, "电信"), UNICOM(3, "联通"), RAILCOM(4, "铁通"), OTHERSUPPLIER(
			5, "其它"); //EDUCATION(6, "教育");
	private int index;
	private String name;

	private SupplierType(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getSupplierTypeNameByIndex(Integer index,
			String defaultName) {
		if (null == index) {
			return defaultName;
		}
		for (SupplierType type : SupplierType.values()) {
			if (type.getIndex() == index)
				return type.getName();
		}
		return defaultName;
	}

	public static List<KeyValueDTO> getSupplierTypeList() {
		List<KeyValueDTO> supplierType = new ArrayList<KeyValueDTO>();
		for (SupplierType type : SupplierType.values()) {
			supplierType.add(new KeyValueDTO(type.getIndex() + "", type
					.getName()));
		}
		return supplierType;
	}
	
	/**
	 * 根据ordinal值获取枚举对象
	 * @param ordinal
	 * @return MatchModel
	 */
	public static SupplierType valueOf(int ordinal) {
		SupplierType[] supplierTypes = SupplierType.values();
        if (ordinal < 0 || ordinal >= supplierTypes.length) {
            return null;
        }
        return supplierTypes[ordinal];
    }
	
	public static List<String> getNamesOfSupplierType(){
		List<String> names = new ArrayList<String>();
		for(SupplierType item : SupplierType.values()){
			names.add(item.getName());
		}
		return names;
	}
}
