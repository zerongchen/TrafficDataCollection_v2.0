package com.aotain.iqis.constant.systemParam;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 输入框类型
 * @author yinzf 
 * @createtime 2015年7月18日 下午11:29:29
 */
public enum InputType {
	
	INPUT("输入框"),
	SELECT("下拉框"),
	RADIO("单选框"),
	CHECKBOX("复选框");
	
	private String description;

	private InputType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getInputTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(InputType item : InputType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey(item.ordinal() + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
}
