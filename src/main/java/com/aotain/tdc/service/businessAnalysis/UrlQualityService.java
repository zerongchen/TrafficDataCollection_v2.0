package com.aotain.tdc.service.businessAnalysis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.KPIUtil;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.constant.CacheType;
import com.aotain.tdc.constant.KPIType;
import com.aotain.tdc.dao.businessAnalysis.UrlQualityDao;
import com.aotain.tdc.dao.quality.IpQualityDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.KPIBean;
import com.aotain.tdc.service.common.CommonServiceImpl;

@Service("urlQualityService")
public class UrlQualityService {
	
    @Autowired
    UrlQualityDao urlQualityDao;
    
    @Autowired
    CommonCache commonCache;

	public void getTableColumns(BaseDTO dto) {		
		dto.setResultList(urlQualityDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("RESPONSE_DELAY", String.format("%.6f", Double.parseDouble(result.get("RESPONSE_DELAY")+"") / Double.parseDouble(result.get("CONNECT_COUNT")+"")) );
				result.put("SERVER_DELAY", String.format("%.6f", Double.parseDouble(result.get("SERVER_DELAY")+"") / Double.parseDouble(result.get("CONNECT_COUNT")+"")) );
				result.put("CLIENT_DELAY", String.format("%.6f", Double.parseDouble(result.get("CLIENT_DELAY")+"") / Double.parseDouble(result.get("CONNECT_COUNT")+"")) );
				//result.put("CONNECTTIMEOUT_COUNT", String.format("%.6f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("CONNECTTIMEOUT_COUNT", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+""));
				//result.put("RESPFAIL_COUNT", String.format("%.6f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.6f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				
				result.put("RESPFAIL_COUNT", Double.parseDouble(result.get("RESPFAIL_COUNT")+""));
				//result.put("CONNECT_COUNT", Double.parseDouble(result.get("CONNECT_COUNT")+""));
				
				
				/**
				 * 计算质量分数 start
				 
				KPIBean kpiBean = new KPIBean();
				kpiBean.setClientDelay(Float.parseFloat(result.get("CLIENT_DELAY").toString()));
				kpiBean.setDownRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_DN_RATE").toString()));
				kpiBean.setRespDelay(Float.parseFloat(result.get("RESPONSE_DELAY").toString()));
				kpiBean.setServerDelay(Float.parseFloat(result.get("SERVER_DELAY").toString()));
				kpiBean.setSuccessPercent(Float.parseFloat(result.get("SUCCESSCONNECT_RATE").toString()));
				kpiBean.setTimeoutPercent(Float.parseFloat(result.get("CONNECTTIMEOUT_RATE").toString()));
				kpiBean.setUpRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_UP_RATE").toString()));
				
				result.put("QUALITY_SCORE", String.format("%.2f", KPIUtil.computeScore(kpiBean, KPIType.ROOM_KPI, commonCache.getDicArrayListCache().get(CacheType.KPICONFIG))));
				
				 * 计算质量分数 end
				 */
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(urlQualityDao.getTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
}
