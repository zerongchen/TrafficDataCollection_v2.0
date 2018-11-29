package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 导入类型
 * @author yinzf 
 * @createtime 2015年7月16日 下午10:29:24
 */
public enum ImportType {
	
	FULL_INSERT("全量"),
	INSERT("增量"),
	UPDATE("更新"),
	DELETE("删除");
	
	private String description;
	
	private ImportType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getImportTypes(){
		List<KeyValueDTO> keyValueDTOs = new ArrayList<KeyValueDTO>();
		for(ImportType importType : ImportType.values()){
			if(importType.ordinal() < 2){
				KeyValueDTO keyValueDTO = new KeyValueDTO();
				keyValueDTO.setKey(importType.ordinal() + "");
				keyValueDTO.setValue(importType.getDescription());
				keyValueDTOs.add(keyValueDTO);
			}
		}
		return keyValueDTOs;
	}
	
	/**
	 * 根据ordinal值获取枚举对象
	 * @param ordinal
	 * @return SystemActionLogType
	 */
	public static ImportType valueOf(int ordinal) {
		ImportType[] importTypes = ImportType.values();
        if (ordinal < 0 || ordinal >= importTypes.length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return importTypes[ordinal];
    }
}
