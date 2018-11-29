package com.aotain.tdc.service.quality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.KPIUtil;
import com.aotain.tdc.constant.CacheType;
import com.aotain.tdc.constant.KPIType;
import com.aotain.tdc.dao.quality.RoomQualityDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.KPIBean;
import com.aotain.tdc.dto.common.SelectorBean;
import com.aotain.tdc.service.common.CommonServiceImpl;

@Service("roomQualityService")
public class RoomQualityService {
	
    @Autowired
    RoomQualityDao roomQualityDao;
    
    @Autowired
    CommonCache commonCache;

	public void getTableColumns(BaseDTO dto) {		
		dto.setResultList(roomQualityDao.getTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("TOTAL", "全部") ;
				result.put("SERVERBUILD_ID", commonCache.getDicServerBuildBeanCache().get(Long.parseLong(result.get("SERVERBUILD_ID")+"")).getServerBuildName()) ;
				result.put("SERVERROOM_ID", commonCache.getDicServerRoomBeanCache().get(Long.parseLong(result.get("SERVERROOM_ID")+"")).getServerRoomName()) ;
				//result.put("CLASSID", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(result.get("CLASSID")+"")).getClassName()) ;                        
				//result.put("PROVINCE_ID", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(result.get("PROVINCE_ID")+"")).getPositionName() );
				result.put("CONNECTTIMEOUT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("RESPFAIL_COUNT", String.format("%.2f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				result.put("UP_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("UP_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("DN_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("DN_SESSION_RATE")+"")*1000/1024/1024*8) );
				
				//TCP_CONNECT_COUNT ,RESPONSE_DELAY_COUNT, SERVER_DELAY_COUNT,CLIENT_DELAY_COUNT
				result.put("RESPONSE_DELAY", String.format("%.2f", Double.parseDouble(result.get("RESPONSE_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("SERVER_DELAY", String.format("%.2f", Double.parseDouble(result.get("SERVER_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("CLIENT_DELAY", String.format("%.2f", Double.parseDouble(result.get("CLIENT_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				
				result.put("MIDPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("MIDPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("BIGPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("BIGPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("FLOW_UP", String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/ Double.parseDouble(result.get("COUNTNUM")+"") /1024/1024*8 ));
				result.put("FLOW_DN", String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/ Double.parseDouble(result.get("COUNTNUM")+"")/1024/1024*8 ));
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
		dto.setTotalCounts(roomQualityDao.getTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}

	public void getTrendTableColumns(BaseDTO dto) {
		dto.setResultList(roomQualityDao.getTrenTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
//				if(dto.getQueryTableName().endsWith("_MIN")){
//					result.put("R_STATTIME", new SimpleDateFormat("yyyyMMddHHmm").format((Date)result.get("R_STATTIME")) ) ;
//				}
				result.put("TOTAL", "全部") ;
				String strTime=null;
				if(dto.getQueryTableName().endsWith("_MIN")){
					strTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format((Date)result.get("R_STATTIME")) ;
				}else{
					strTime = new SimpleDateFormat("yyyy-MM-dd HH").format(new SimpleDateFormat("yyyyMMddHH").parse(String.valueOf(result.get("R_STATTIME")))) ;
				}
				result.put("R_STATTIME", strTime);
				//TCP_CONNECT_COUNT ,RESPONSE_DELAY_COUNT, SERVER_DELAY_COUNT,CLIENT_DELAY_COUNT
				result.put("RESPONSE_DELAY", String.format("%.2f", Double.parseDouble(result.get("RESPONSE_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("SERVER_DELAY", String.format("%.2f", Double.parseDouble(result.get("SERVER_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("CLIENT_DELAY", String.format("%.2f", Double.parseDouble(result.get("CLIENT_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				
				result.put("SERVERBUILD_ID", commonCache.getDicServerBuildBeanCache().get(Long.parseLong(result.get("SERVERBUILD_ID")+"")).getServerBuildName()) ;
				result.put("SERVERROOM_ID", commonCache.getDicServerRoomBeanCache().get(Long.parseLong(result.get("SERVERROOM_ID")+"")).getServerRoomName()) ;
				//result.put("CLASSID", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(result.get("CLASSID")+"")).getClassName()) ;
				result.put("CONNECTTIMEOUT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("RESPFAIL_COUNT", String.format("%.2f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				result.put("UP_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("UP_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("DN_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("DN_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("MIDPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("MIDPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("BIGPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("BIGPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("FLOW_UP", String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
				result.put("FLOW_DN", String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
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
		dto.setTotalCounts(roomQualityDao.getTrendTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}

	public void getLayerTrendTableColumns(BaseDTO dto) {
		dto.setResultList(roomQualityDao.getLayerTrenTableColumns(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				
				//TCP_CONNECT_COUNT ,RESPONSE_DELAY_COUNT, SERVER_DELAY_COUNT,CLIENT_DELAY_COUNT
				result.put("RESPONSE_DELAY", String.format("%.2f", Double.parseDouble(result.get("RESPONSE_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("SERVER_DELAY", String.format("%.2f", Double.parseDouble(result.get("SERVER_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
				result.put("CLIENT_DELAY", String.format("%.2f", Double.parseDouble(result.get("CLIENT_DELAY")+"") / Double.parseDouble(result.get("TCP_CONNECT_COUNT")+"")) );
					
				String strTime=result.get("R_STATTIME").toString();
				result.put("R_STATTIME", strTime);
				//result.put("PROVINCE_ID", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(result.get("PROVINCE_ID")+"")).getPositionName()) ;
				result.put("CONNECTTIMEOUT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECTTIMEOUT_COUNT")+"")/10000) );
				result.put("RESPFAIL_COUNT", String.format("%.2f", Double.parseDouble(result.get("RESPFAIL_COUNT")+"")/10000) );
				result.put("CONNECT_COUNT", String.format("%.2f", Double.parseDouble(result.get("CONNECT_COUNT")+"")/10000) );
				result.put("UP_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("UP_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("DN_SESSION_RATE", String.format("%.2f", Double.parseDouble(result.get("DN_SESSION_RATE")+"")*1000/1024/1024*8) );
				result.put("MIDPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("MIDPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("BIGPACKAGEFLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("BIGPACKAGEFLOW_DN_RATE")+"")*1000/1024/1024*8) );
				result.put("FLOW_UP", String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
				result.put("FLOW_DN", String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/Double.parseDouble(CommonServiceImpl.getSecondScale(dto)+"")/1024/1024*8 ));
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
		dto.setTotalCounts(roomQualityDao.getLayerTrendTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
}
