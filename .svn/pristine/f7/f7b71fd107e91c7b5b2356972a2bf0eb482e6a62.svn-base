package com.aotain.iqis.constant.log;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 登录类型
 * @author yinzf 
 * @createtime 2015年7月21日 下午10:31:09
 */
public enum LoginType {
	
	LOGIN_IN("登录"),
	LOGIN_OUT("退出");
	
	private String description;

	private LoginType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getLoginTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(LoginType item : LoginType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey(item.ordinal() + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
}
