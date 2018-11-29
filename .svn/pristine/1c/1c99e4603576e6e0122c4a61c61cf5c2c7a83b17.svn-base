package com.aotain.tdc.service.traffic;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.dao.traffic.BizTrafficDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.service.common.CommonServiceImpl;

@Service("bizTrafficService")
public class BizTrafficService {
	
	 @Autowired
	 CommonCache commonCache;
	
    @Autowired
    BizTrafficDao bizTrafficDao;
    
    public void getStatisticsTableColumns(BaseDTO dto) {
		dto.setResultList(bizTrafficDao.getStatisticsTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("BIZ_NAME", commonCache.getDicBizInfo().get(Long.parseLong(result.get("BIZ_ID")+"")).getBizName() );                          
				result.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/(CommonServiceImpl.getSecondScale(dto)*Long.parseLong(result.get("COUNTNUM").toString()))/1024/1024*8));
				result.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/(CommonServiceImpl.getSecondScale(dto)*Long.parseLong(result.get("COUNTNUM").toString()))/1024/1024*8));
				result.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN").toString())/(CommonServiceImpl.getSecondScale(dto)*Long.parseLong(result.get("COUNTNUM").toString()))/1024/1024*8));
				result.put("FLOW",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/1024/1024/1024));
				result.put("FLOW_UP",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/1024/1024/1024));
				result.put("FLOW_DN",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN").toString())/1024/1024/1024));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(bizTrafficDao.getStatisticsTableColumnsTotalCounts(dto));
	}
	
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(bizTrafficDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				String t = StringUtil.formatDateString(result.get("R_STATTIME").toString());
				result.put("R_STATTIME", t) ;
				result.put("BIZ_NAME", "全部") ;
				result.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/(CommonServiceImpl.getSecondScale(dto)*Long.parseLong(result.get("COUNTNUM").toString()))/1024/1024*8));
				result.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/(CommonServiceImpl.getSecondScale(dto)*Long.parseLong(result.get("COUNTNUM").toString()))/1024/1024*8));
				result.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN").toString())/(CommonServiceImpl.getSecondScale(dto)*Long.parseLong(result.get("COUNTNUM").toString()))/1024/1024*8));
				result.put("FLOW_UP",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/1024/1024/1024));
				result.put("FLOW_DN",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN").toString())/1024/1024/1024));
				result.put("FLOW",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/1024/1024/1024));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(bizTrafficDao.getTableColumnsTotalCounts(dto));
	}
	
	public void getLayerTrendTableColumns(BaseDTO dto) {
		dto.setResultList(bizTrafficDao.getLayerTrenTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				String t = StringUtil.formatDateString(result.get("R_STATTIME").toString());
				result.put("R_STATTIME", t) ;
				result.put("BIZ_NAME", "全部");
				result.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/(CommonServiceImpl.getSecondScale(dto))/1024/1024*8));
				result.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/(CommonServiceImpl.getSecondScale(dto))/1024/1024*8));
				result.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN").toString())/(CommonServiceImpl.getSecondScale(dto))/1024/1024*8));
				result.put("FLOW_UP",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/1024/1024/1024));
				result.put("FLOW_DN",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN").toString())/1024/1024/1024));
				result.put("FLOW",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/1024/1024/1024));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(bizTrafficDao.getLayerTrendTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
}
