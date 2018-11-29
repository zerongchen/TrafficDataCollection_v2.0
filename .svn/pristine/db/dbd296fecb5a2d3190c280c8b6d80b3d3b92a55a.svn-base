package com.aotain.iqis.constant.chartManagement;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 显示类型
 * @author yinzf
 * @createtime 2015年7月20日 下午2:35:50
 */
public enum ShowType {
	
	SHOW("显示"),
	HIDE("隐藏");
	
	private String description;

	private ShowType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getShowTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(ShowType item : ShowType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey((item.ordinal() + 1) + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
}
