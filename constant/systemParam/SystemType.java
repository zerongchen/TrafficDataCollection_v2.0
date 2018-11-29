package com.aotain.iqis.constant.systemParam;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 系统类型
 * @author yinzf 
 * @createtime 2015年7月18日 下午11:46:09
 */
public enum SystemType {
	
	IQIS("互联网质量提升系统");
	
	private String description;

	private SystemType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getModuleTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(SystemType item : SystemType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey(item.ordinal() + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
}
