package com.aotain.tdc.service.quality;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.KPIUtil;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.constant.CacheType;
import com.aotain.tdc.constant.KPIType;
import com.aotain.tdc.dao.quality.AreaQualityDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.KPIBean;
import com.aotain.tdc.service.common.CommonServiceImpl;

@Service("areaQualityService")
public class AreaQualityService {
	
    @Autowired
    AreaQualityDao areaQualityDao;
    
    @Autowired
    CommonCache commonCache;
    /**
     * up zhenggf
     * @param dto
     */
	public void getTableColumns(BaseDTO dto) {		
		dto.setResultList(areaQualityDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("PROVINCE", result.get("PROVINCE_ID") );
				result.put("PROVINCE_ID", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(result.get("PROVINCE_ID")+"")).getPositionName() );          
				//result.put("CATALOGNAME", commonCache.getDicProtoCataLogBeanCache().get(Long.parseLong(result.get("CATALOGID")+"")).getCataName() );                             
				//result.put("CLASSNAME", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(result.get("CLASSID")+"")).getClassName() );                          
				//result.put("PRODUCTNAME", commonCache.getDicProtoProductBeanCache().get(Long.parseLong(result.get("PRODUCTID")+"")).getProductName() );           
				//result.put("MODULENAME", commonCache.getDicProtoModuleBeanCache().get(Long.parseLong(result.get("MODULEID")+"")).getModuleName() );         
				//result.put("PAGENAME", commonCache.getDicProtoPageBeanCache().get(Long.parseLong(result.get("PAGEID")+"")).getPageName() );           
				result.put("CONNECTTIMEOUT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("RESPFAIL_COUNT", String.format("%.2f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				result.put("UP_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("UP_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("DN_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("DN_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("MIDPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("MIDPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("BIGPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("BIGPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("FLOW_UP", String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/ Double.parseDouble(result.get("COUNTNUM")+"")/1024/1024*8 ));
				result.put("FLOW_DN", String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/ Double.parseDouble(result.get("COUNTNUM")+"")/1024/1024*8 ));
				
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
		dto.setTotalCounts(areaQualityDao.getTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}

	public void getTrendTableColumns(BaseDTO dto) {
		dto.setResultList(areaQualityDao.getTrenTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("R_STATTIME", StringUtil.formatDateString(result.get("R_STATTIME").toString())) ;
				result.put("PROVINCE_ID", "全部") ;
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
		dto.setTotalCounts(areaQualityDao.getTrendTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}

	public void getLayerTrendTableColumns(BaseDTO dto) {
		dto.setResultList(areaQualityDao.getLayerTrenTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("R_STATTIME", StringUtil.formatDateString(result.get("R_STATTIME").toString())) ;
				result.put("PROVINCE_ID", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(result.get("PROVINCE_ID")+"")).getPositionName()) ;
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
		dto.setTotalCounts(areaQualityDao.getLayerTrendTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
}
