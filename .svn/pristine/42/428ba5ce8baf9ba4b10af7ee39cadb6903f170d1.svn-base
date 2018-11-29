package com.aotain.tdc.service.businessAnalysis;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.dao.businessAnalysis.BusinessTrendDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("businessTrendService")
public class BusinessTrendService {
	
	@Autowired
	BusinessTrendDao businessTrendDao;
	@Autowired
	CommonCache commonCache;
	    
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(businessTrendDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("R_STATTIME", StringUtil.formatDateString(result.get("R_STATTIME").toString()));
				result.put("FLOW",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/1024/1024/1024));
				result.put("PAGE_VIEW",String.format("%.2f",Double.parseDouble(result.get("PAGE_VIEW").toString())/10000));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(businessTrendDao.getTableColumnsTotalCounts(dto));
	}
		
}
