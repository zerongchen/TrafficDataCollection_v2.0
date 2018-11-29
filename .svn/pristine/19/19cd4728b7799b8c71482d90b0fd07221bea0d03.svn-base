package com.aotain.tdc.service.businessAnalysis;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.businessAnalysis.TopBusinessAnalysisDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("topBusinessAnalysisService")
public class TopBusinessAnalysisService {
	
	@Autowired
	TopBusinessAnalysisDao topBusinessAnalysisDao;
	 
	@Autowired
	CommonCache commonCache;
	    
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(topBusinessAnalysisDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				//switch (dto.getQueryGroupByColumnName()){
					//case "CATALOGID":result.put("P_NAME", commonCache.getDicProtoCataLogBeanCache().get(Long.parseLong(result.get("P_ID")+"")).getCataName());break;
					//case "CLASSID":result.put("P_NAME", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(result.get("P_ID")+"")).getClassName());break;
					//case "PRODUCTID":result.put("P_NAME", commonCache.getDicProtoProductBeanCache().get(Long.parseLong(result.get("P_ID")+"")).getProductName());break;
					//case "MODULEID":result.put("P_NAME", commonCache.getDicProtoModuleBeanCache().get(Long.parseLong(result.get("P_ID")+"")).getModuleName());break;
				//}
				result.put("P_NAME", commonCache.getDicBizInfo().get(Long.parseLong(result.get("P_ID")+"")).getBizName());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
}
