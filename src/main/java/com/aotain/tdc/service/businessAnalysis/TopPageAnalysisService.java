package com.aotain.tdc.service.businessAnalysis;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.businessAnalysis.TopPageAnalysisDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("topPageAnalysisService")
public class TopPageAnalysisService {
	
	@Autowired
	TopPageAnalysisDao topPageAnalysisDao;
	@Autowired
	CommonCache commonCache;
	    
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(topPageAnalysisDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("FLOW",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/1024/1024/1024));
				result.put("PAGE_VIEW",String.format("%.2f",Double.parseDouble(result.get("PAGE_VIEW").toString())/10000));
				result.put("PRODUCTNAME", commonCache.getDicPageProductBeanCache().get(Long.parseLong(result.get("PRODUCTID")+"")).getPageProductName());
				result.put("MODULENAME", commonCache.getDicPageModuleBeanCache().get(Long.parseLong(result.get("MODULEID")+"")).getPageModuleName());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
//		dto.setTotalCounts(topPageAnalysisDao.getTableColumnsTotalCounts(dto));
	}
		
}
