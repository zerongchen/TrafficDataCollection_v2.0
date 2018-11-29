package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 匹配模式
 * @author yinzf 
 * @createtime 2015年8月10日 上午1:49:12
 */
public enum MatchModel {
	
	 VAGUE("模糊"),
	 ACCURACY("精确");
	 
	 private String description;

	private MatchModel(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getMatchModels(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(MatchModel item : MatchModel.values()){
			KeyValueDTO dto = new KeyValueDTO((item.ordinal() + 1) + "", item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
	/**
	 * 根据ordinal值获取枚举对象
	 * @param ordinal
	 * @return MatchModel
	 */
	public static MatchModel valueOf(int ordinal) {
		MatchModel[] MatchModels = MatchModel.values();
        if (ordinal < 0 || ordinal >= MatchModels.length) {
            return null;
        }
        return MatchModels[ordinal];
    }
}
