package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.KeyValueDTO;

/**
 * 网站类型
 * @author yinzf 
 * @createtime 2015年8月9日 下午5:20:07
 */
public enum WebsiteType {
	
	ICP("ICP网站"),
	CUSTOM("自定义网站");
	
	private String description;

	private WebsiteType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<KeyValueDTO> getWebsiteTypes(){
		List<KeyValueDTO> dtoes = new ArrayList<KeyValueDTO>();
		for(WebsiteType item : WebsiteType.values()){
			KeyValueDTO dto = new KeyValueDTO((item.ordinal() + 1) + "", item.getDescription());
			dtoes.add(dto);
		}
		return dtoes;
	}
	
	/**
	 * 根据ordinal值获取枚举对象
	 * @param ordinal
	 * @return WebsiteType
	 */
	public static WebsiteType valueOf(int ordinal) {
		WebsiteType[] websiteTypes = WebsiteType.values();
        if (ordinal < 0 || ordinal >= websiteTypes.length) {
            return null;
        }
        return websiteTypes[ordinal];
    }
	
}
