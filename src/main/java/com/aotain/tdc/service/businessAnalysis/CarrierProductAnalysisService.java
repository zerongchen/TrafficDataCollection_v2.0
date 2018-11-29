package com.aotain.tdc.service.businessAnalysis;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.businessAnalysis.CarrierProductAnalysisDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("carrierProductAnalysisService")
public class CarrierProductAnalysisService {
	
	@Autowired
	CarrierProductAnalysisDao carrierProductAnalysisDao;
	 
	@Autowired
	CommonCache commonCache;
	    
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(carrierProductAnalysisDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("CARRIER_NAME", commonCache.getDicCarrierBeanCache().get(Long.parseLong(result.get("CARRIER_ID")+"")).getCarrierName());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
}
