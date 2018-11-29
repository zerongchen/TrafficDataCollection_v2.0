package com.aotain.tdc.service.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.strategy.ReUseDao;
import com.aotain.tdc.dto.common.BaseDTO;

@Service
public class ReUseService {
	
	@Autowired
	private CommonCache commonCache;
	
	@Autowired
	private ReUseDao reUseDao;
	
	public void getTableColumnsData(BaseDTO dto) throws NumberFormatException, ExecutionException{
		List<HashMap<String, Object>> list = reUseDao.getPageList(dto);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(int i = 0; i < list.size(); i++){
			HashMap<String, Object> hashMap = list.get(i);
			hashMap.put("START_TIME", sd.format((Date)hashMap.get("START_TIME")));
			hashMap.put("CREATETIME", sd.format((Date)hashMap.get("CREATETIME")));
			hashMap.put("FTP_SERVER", commonCache.getPolicyStrategyFTPBeanByIdTypeCache().get(hashMap.get("STRATEGY_ID").toString()+"_"+hashMap.get("STRATEGY_TYPE").toString()).getFtpName());
			hashMap.put("FTP_IDS", commonCache.getPolicyStrategyFTPBeanByIdTypeCache().get(hashMap.get("STRATEGY_ID").toString()+"_"+hashMap.get("STRATEGY_TYPE").toString()).getFtpIds());
			hashMap.put("FTP_IPS", commonCache.getPolicyStrategyFTPBeanByIdTypeCache().get(hashMap.get("STRATEGY_ID").toString()+"_"+hashMap.get("STRATEGY_TYPE").toString()).getIps());
			hashMap.put("DST_IP", commonCache.getPolicyDstIPPortBeanByIdTypeCache().get(hashMap.get("STRATEGY_ID").toString()+"_"+hashMap.get("STRATEGY_TYPE").toString()).getDstIp());
			hashMap.put("DST_PORT", commonCache.getPolicyDstIPPortBeanByIdTypeCache().get(hashMap.get("STRATEGY_ID").toString()+"_"+hashMap.get("STRATEGY_TYPE").toString()).getDstPort());
			hashMap.put("PROTOCOL", commonCache.getDicProtocolBeanCache().get(hashMap.get("PROTOCOLTYPE_ID")+"_-1").getProtocolName());
		}
		dto.setResultList(list);
		dto.setTotalCounts(reUseDao.getTotalCount(dto));
		if(dto.getTotalCounts() == null){
			dto.setTotalCounts(0);
		}
	}

	public List<HashMap<String, Object>> getPolicyStatData(int strategyType, long strategyId) {
		List<HashMap<String, Object>> list = reUseDao.getPolicyStatData(strategyType, strategyId);
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd HH:mm");
		SimpleDateFormat sdy = new SimpleDateFormat("YYYY/MM/dd HH:mm");
		for(int i = 0; i < list.size(); i++){
			HashMap<String, Object> hashMap = list.get(i);
			hashMap.put("R_STATTIME_M", sd.format((Date)hashMap.get("R_STATTIME")));
			hashMap.put("R_STATTIME_Y", sdy.format((Date)hashMap.get("R_STATTIME")));
		}
		return list;
	}
}
