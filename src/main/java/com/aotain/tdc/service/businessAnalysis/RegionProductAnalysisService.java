package com.aotain.tdc.service.businessAnalysis;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.businessAnalysis.RegionProductAnalysisDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("regionProductAnalysisService")
public class RegionProductAnalysisService {
	
	@Autowired
	RegionProductAnalysisDao regionProductAnalysisDao;
	 
	@Autowired
	CommonCache commonCache;
	    
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(regionProductAnalysisDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("PROVINCE_NAME", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(result.get("PROVINCE_ID")+"")).getPositionName());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
}
