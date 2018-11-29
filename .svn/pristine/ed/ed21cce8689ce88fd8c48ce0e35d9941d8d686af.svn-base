package com.aotain.tdc.service.traffic;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.traffic.ServiceTrafficDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.service.common.CommonServiceImpl;

@Service("serviceTrafficService")
public class ServiceTrafficService {
	
	 @Autowired
	 CommonCache commonCache;
	
    @Autowired
    ServiceTrafficDao serviceTrafficDao;
    
    public void getStatisticsTableColumns(BaseDTO dto) {
		dto.setResultList(serviceTrafficDao.getStatisticsTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("CATALOGNAME", commonCache.getDicProtoCataLogBeanCache().get(Long.parseLong(result.get("CATALOGID")+"")).getCataName() );                          
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
		dto.setTotalCounts(serviceTrafficDao.getStatisticsTableColumnsTotalCounts(dto));
	}
	
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(serviceTrafficDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("R_STATTIME", result.get("R_STATTIME")) ;
				result.put("CATALOGNAME", "全部") ;
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
		dto.setTotalCounts(serviceTrafficDao.getTableColumnsTotalCounts(dto));
	}
	
	public void getLayerTrendTableColumns(BaseDTO dto) {
		dto.setResultList(serviceTrafficDao.getLayerTrenTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("R_STATTIME", result.get("R_STATTIME")) ;
				result.put("CATALOGNAME", "全部");
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
		dto.setTotalCounts(serviceTrafficDao.getLayerTrendTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
}
