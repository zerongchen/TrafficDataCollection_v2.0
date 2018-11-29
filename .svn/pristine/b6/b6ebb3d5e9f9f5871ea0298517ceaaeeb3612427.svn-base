package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;
import com.aotain.iqis.utils.CommonUtils;

public enum MonitorType {
	APP(1,"应用"),LINEUSER(2,"专线用户"),QUOTA(3,"指标"),GRIDDIAL(4,"网格拨号");
	private int index;
	private String description;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private MonitorType(int index, String description) {
		this.index = index;
		this.description = description;
	}
	
	public static List<KeyValueDTO> getMonitoTypes(){
		String gridDial = CommonUtils.getSystemparammap().get("GRID_DIAL");
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(MonitorType item : MonitorType.values()){
			if(!"1".equals(gridDial)){
				if(item == GRIDDIAL)continue;
			}
			KeyValueDTO dto = new KeyValueDTO(item.getIndex() + "", item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
	public static MonitorType getMonitorTypeByIndex(int index){
		for(MonitorType item : MonitorType.values()){
			if(item.getIndex() == index){
				return item;
			}
		}
		return null;
	}
}
