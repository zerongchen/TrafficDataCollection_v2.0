package com.aotain.iqis.constant.userManagement;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 专线类型
 * @author yinzf 
 * @createtime 2015年8月17日 上午11:31:02
 */
public enum PersonalLineType {
	
	DATA_LINE("数据专线");
	
	private String description;

	private PersonalLineType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getPersonalLineTypes(){
		List<KeyValueDTO> keyValueDTOs = new ArrayList<KeyValueDTO>();
		for(PersonalLineType item: PersonalLineType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey((item.ordinal() + 1) + ""); 
			dto.setValue(item.getDescription());
			keyValueDTOs.add(dto);
		}
		return keyValueDTOs;
	}
	
	public static PersonalLineType getByDescription(String desc){
		for(PersonalLineType item : PersonalLineType.values()){
			if(item.getDescription().equals(desc)){
				return item;
			}
		}
		return null;
	}
	
}
