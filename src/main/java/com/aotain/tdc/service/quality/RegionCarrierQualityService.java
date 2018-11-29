package com.aotain.tdc.service.quality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.KPIUtil;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.constant.CacheType;
import com.aotain.tdc.constant.KPIType;
import com.aotain.tdc.dao.quality.RegionCarrierQualityDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.KPIBean;
import com.aotain.tdc.service.common.CommonServiceImpl;

@Service("regionCarrierQualityService")
public class RegionCarrierQualityService {
	@Autowired
	RegionCarrierQualityDao regionCarrierQualityDao;
	@Autowired
	CommonCache commonCache;
	
	public void getStatisticsTableColumns(BaseDTO dto) {
		dto.setResultList(regionCarrierQualityDao.getStatisticsTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("PC", result.get("PC_ID"));
				String [] pcids  = result.get("PC_ID").toString().split("_");
				result.put("PC_ID", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(pcids[0])).getPositionName()+commonCache.getDicCarrierBeanCache().get(Long.parseLong(pcids[1])).getCarrierName());
				//result.put("CATALOGNAME", commonCache.getDicProtoCataLogBeanCache().get(Long.parseLong(result.get("CATALOGID")+"")).getCataName() );                             
				//result.put("CLASSNAME", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(result.get("CLASSID")+"")).getClassName() );                          
				//result.put("PRODUCTNAME", commonCache.getDicProtoProductBeanCache().get(Long.parseLong(result.get("PRODUCTID")+"")).getProductName() );        
				
				//TCP_CONNECT_COUNT ,RESPONSE_DELAY_COUNT, SERVER_DELAY_COUNT,CLIENT_DELAY_COUNT
				result.put("RESPONSE_DELAY", String.format("%.2f", Double.parseDouble(result.get("RESPONSE_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("SERVER_DELAY", String.format("%.2f", Double.parseDouble(result.get("SERVER_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("CLIENT_DELAY", String.format("%.2f", Double.parseDouble(result.get("CLIENT_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				
				
//				result.put("MODULENAME", commonCache.getDicProtoModuleBeanCache().get(Long.parseLong(result.get("MODULEID")+"")).getModuleName() );         
//				result.put("PAGENAME", commonCache.getDicProtoPageBeanCache().get(Long.parseLong(result.get("PAGEID")+"")).getPageName() );           
				result.put("CONNECTTIMEOUT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("RESPFAIL_COUNT", String.format("%.2f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				result.put("UP_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("UP_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("DN_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("DN_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("MIDPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("MIDPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("BIGPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("BIGPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("FLOW_UP", String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/Double.parseDouble(result.get("COUNTNUM")+"")/1024/1024*8 ));
				result.put("FLOW_DN", String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/Double.parseDouble(result.get("COUNTNUM")+"")/1024/1024*8 ));
				/**
				 * 计算质量分数 start
				 */
				KPIBean kpiBean = new KPIBean();
				kpiBean.setClientDelay(Float.parseFloat(result.get("CLIENT_DELAY").toString()));
				kpiBean.setDownRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_DN_RATE").toString()));
				kpiBean.setRespDelay(Float.parseFloat(result.get("RESPONSE_DELAY").toString()));
				kpiBean.setServerDelay(Float.parseFloat(result.get("SERVER_DELAY").toString()));
				kpiBean.setSuccessPercent(Float.parseFloat(result.get("SUCCESSCONNECT_RATE").toString()));
				kpiBean.setTimeoutPercent(Float.parseFloat(result.get("CONNECTTIMEOUT_RATE").toString()));
				kpiBean.setUpRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_UP_RATE").toString()));
				
				result.put("QUALITY_SCORE", String.format("%.2f", KPIUtil.computeScore(kpiBean, KPIType.ROOM_KPI, commonCache.getDicArrayListCache().get(CacheType.KPICONFIG))));
				/**
				 * 计算质量分数 end
				 */
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(regionCarrierQualityDao.getStatisticsTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
	
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(regionCarrierQualityDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				String strTime=StringUtil.formatDateString(result.get("R_STATTIME").toString());
				result.put("R_STATTIME", strTime);
				result.put("PC_ID", "全部");
				result.put("CONNECTTIMEOUT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("RESPFAIL_COUNT", String.format("%.2f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				result.put("UP_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("UP_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("DN_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("DN_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("MIDPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("MIDPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("BIGPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("BIGPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("FLOW_UP", String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
				result.put("FLOW_DN", String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
				
				//TCP_CONNECT_COUNT ,RESPONSE_DELAY_COUNT, SERVER_DELAY_COUNT,CLIENT_DELAY_COUNT
				result.put("RESPONSE_DELAY", String.format("%.2f", Double.parseDouble(result.get("RESPONSE_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("SERVER_DELAY", String.format("%.2f", Double.parseDouble(result.get("SERVER_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("CLIENT_DELAY", String.format("%.2f", Double.parseDouble(result.get("CLIENT_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
			
				
				/**
				 * 计算质量分数 start
				 */
				KPIBean kpiBean = new KPIBean();
				kpiBean.setClientDelay(Float.parseFloat(result.get("CLIENT_DELAY").toString()));
				kpiBean.setDownRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_DN_RATE").toString()));
				kpiBean.setRespDelay(Float.parseFloat(result.get("RESPONSE_DELAY").toString()));
				kpiBean.setServerDelay(Float.parseFloat(result.get("SERVER_DELAY").toString()));
				kpiBean.setSuccessPercent(Float.parseFloat(result.get("SUCCESSCONNECT_RATE").toString()));
				kpiBean.setTimeoutPercent(Float.parseFloat(result.get("CONNECTTIMEOUT_RATE").toString()));
				kpiBean.setUpRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_UP_RATE").toString()));
				
				result.put("QUALITY_SCORE", String.format("%.2f", KPIUtil.computeScore(kpiBean, KPIType.ROOM_KPI, commonCache.getDicArrayListCache().get(CacheType.KPICONFIG))));
				/**
				 * 计算质量分数 end
				 */
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(regionCarrierQualityDao.getTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
	
	public void getLayerTrendTableColumns(BaseDTO dto) {
		dto.setResultList(regionCarrierQualityDao.getLayerTrenTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				String strTime=StringUtil.formatDateString(result.get("R_STATTIME").toString());
				result.put("R_STATTIME", strTime);
				String [] pcids  = result.get("PC_ID").toString().split("_");
				result.put("PC_ID", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(pcids[0])).getPositionName().replace("省","")+commonCache.getDicCarrierBeanCache().get(Long.parseLong(pcids[1])).getCarrierName());
				result.put("CONNECTTIMEOUT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("RESPFAIL_COUNT", String.format("%.2f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				result.put("UP_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("UP_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("DN_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("DN_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("MIDPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("MIDPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("BIGPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("BIGPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("FLOW_UP", String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
				result.put("FLOW_DN", String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
				
				//TCP_CONNECT_COUNT ,RESPONSE_DELAY_COUNT, SERVER_DELAY_COUNT,CLIENT_DELAY_COUNT
				result.put("RESPONSE_DELAY", String.format("%.2f", Double.parseDouble(result.get("RESPONSE_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("SERVER_DELAY", String.format("%.2f", Double.parseDouble(result.get("SERVER_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("CLIENT_DELAY", String.format("%.2f", Double.parseDouble(result.get("CLIENT_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				/**
				 * 计算质量分数 start
				 */
				KPIBean kpiBean = new KPIBean();
				kpiBean.setClientDelay(Float.parseFloat(result.get("CLIENT_DELAY").toString()));
				kpiBean.setDownRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_DN_RATE").toString()));
				kpiBean.setRespDelay(Float.parseFloat(result.get("RESPONSE_DELAY").toString()));
				kpiBean.setServerDelay(Float.parseFloat(result.get("SERVER_DELAY").toString()));
				kpiBean.setSuccessPercent(Float.parseFloat(result.get("SUCCESSCONNECT_RATE").toString()));
				kpiBean.setTimeoutPercent(Float.parseFloat(result.get("CONNECTTIMEOUT_RATE").toString()));
				kpiBean.setUpRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_UP_RATE").toString()));
				
				result.put("QUALITY_SCORE", String.format("%.2f", KPIUtil.computeScore(kpiBean, KPIType.ROOM_KPI, commonCache.getDicArrayListCache().get(CacheType.KPICONFIG))));
				/**
				 * 计算质量分数 end
				 */
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(regionCarrierQualityDao.getLayerTrendTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
	
	
	
}
