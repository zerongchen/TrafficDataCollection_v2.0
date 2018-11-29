package com.aotain.iqis.constant.log;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 操作日志类型
 * @author yinzf
 * @createtime 2015年7月21日 下午5:39:38
 */
public enum ActionLogType {
	CREATE("新增"),
	READ("查询"),
	UPDATE("修改"),
	DELETE("删除"),
	IMPORT("导入"),
	EXPORT("导出");
	
	private String description;
	

	private ActionLogType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getActionLogTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(ActionLogType item : ActionLogType.values()){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey(item.ordinal() + "");
			dto.setValue(item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
}
