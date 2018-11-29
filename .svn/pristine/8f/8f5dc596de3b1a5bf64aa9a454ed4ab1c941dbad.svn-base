package com.aotain.iqis.constant.chartManagement;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 显示与隐藏切换
 * @author yinzf 
 * @createtime 2015年9月2日 上午11:22:49
 */
public enum Switchable {
	
	TRUE("参与"),
	FALSE("不参与");
	
	private String description;

	private Switchable(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getSwitchables(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(Switchable item : Switchable.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey((item.ordinal() + 1) + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
}
