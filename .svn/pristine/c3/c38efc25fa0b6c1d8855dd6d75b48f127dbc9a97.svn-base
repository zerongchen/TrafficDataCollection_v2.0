package com.aotain.tdc.service.traffic;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.traffic.TrafficOverviewDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.service.common.CommonServiceImpl;

@Service("trafficOverviewService")
public class TrafficOverviewService {
	@Autowired
	TrafficOverviewDao trafficOverviewDao;
	@Autowired
	CommonCache commonCache;
	
	//全网流量趋势分布
	public void getTrafficTrendData(BaseDTO dto){
		dto.setResultList(trafficOverviewDao.getTrafficTrendData(dto));
		for(int i = 0; i< dto.getResultList().size(); i ++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>)dto.getResultList().get(i);
			try{
				if(dto.getQueryTableName_R_Serverbuild().endsWith("_MIN")){
					result.put("R_STATTIME", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(result.get("R_STATTIME")).toString());
				}else{
					result.put("R_STATTIME", new SimpleDateFormat("yyyy-MM-dd HH").format(new SimpleDateFormat("yyyyMMddHH").parse(String.valueOf(result.get("R_STATTIME")))));
				}
				result.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW")+"")/(CommonServiceImpl.getServerbuildRSecondScale(dto))/1024/1024*8));
				result.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP")+"")/(CommonServiceImpl.getServerbuildRSecondScale(dto))/1024/1024*8));
				result.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN")+"")/(CommonServiceImpl.getServerbuildRSecondScale(dto))/1024/1024*8));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//机房流量
	public void getServerbuildTrafficData(BaseDTO dto){
		dto.setResultList(trafficOverviewDao.getServerbuildTrafficData(dto));
		for(int i = 0; i< dto.getResultList().size(); i ++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>)dto.getResultList().get(i);
			try{
				result.put("SERVERBUILD_ID", commonCache.getDicServerBuildBeanCache().get(Long.parseLong(result.get("SERVERBUILD_ID")+"")).getServerBuildName());
				result.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(result.get("FLOW_UP")+"")+Double.parseDouble(result.get("FLOW_DN")+""))/1024/1024/1024)) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//应用层协议流量
	public void getProtocolTrafficData(BaseDTO dto) {
		dto.setResultList(trafficOverviewDao.getProtocolTrafficData(dto));
		for(int i = 0; i< dto.getResultList().size(); i ++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>)dto.getResultList().get(i);
			try{
				result.put("PROTOCOL_ID", commonCache.getDicProtocolBeanCache().get(result.get("PROTOCOL_ID")+"_"+ "1").getProtocolName());
//				result.put("PROTOCOL_ID", commonCache.getDicProtocolBeanCache().get(result.get("PROTOCOL_ID")+"_"+result.get("PROTOCOL_TYPE")).getProtocolName());
				result.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(result.get("FLOW_UP")+"")+Double.parseDouble(result.get("FLOW_DN")+""))/1024/1024/1024)) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//IP地址流量
	public void getIPTrafficData(BaseDTO dto) {
		dto.setResultList(trafficOverviewDao.getIPTrafficData(dto));
		//for(int i = 0; i< dto.getResultList().size(); i ++){
		//	@SuppressWarnings("unchecked")
		//	HashMap<String, Object> result = (HashMap<String, Object>)dto.getResultList().get(i);
		//	try{
				//result.put("DESTIP", result.get("DESTIP"));
				//result.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(result.get("FLOW_UP")+"")+Double.parseDouble(result.get("FLOW_DN")+""))/1024/1024/1024)) );
		//	}catch(Exception e){
		//		e.printStackTrace();
		//	}
		//}
	}
	public void getServiceTrafficData(BaseDTO dto) {
		dto.setResultList(trafficOverviewDao.getServiceTrafficData(dto));
		for(int i = 0; i< dto.getResultList().size(); i ++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>)dto.getResultList().get(i);
			try{
				//result.put("CATALOGNAME", commonCache.getDicProtoCataLogBeanCache().get(Long.parseLong(result.get("CATALOGID")+"")).getCataName() );
				result.put("CATALOGNAME", commonCache.getDicBizInfo().get(Long.parseLong(result.get("BIZ_ID")+"")).getBizName() );
				result.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(result.get("FLOW_UP")+"")+Double.parseDouble(result.get("FLOW_DN")+""))/1024/1024/1024)) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void getRegionTrafficData(BaseDTO dto) {
		dto.setResultList(trafficOverviewDao.getRegionTrafficData(dto));
		for(int i = 0; i< dto.getResultList().size(); i ++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>)dto.getResultList().get(i);
			try{
				result.put("PROVINCE_NAME", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(result.get("PROVINCE_ID")+"")).getPositionName());
				result.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(result.get("FLOW_UP")+"")+Double.parseDouble(result.get("FLOW_DN")+""))/1024/1024/1024)) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void getCarrierTrafficData(BaseDTO dto) {
		dto.setResultList(trafficOverviewDao.getCarrierTrafficData(dto));
		for(int i = 0; i< dto.getResultList().size(); i ++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>)dto.getResultList().get(i);
			try{
				result.put("CARRIER_NAME", commonCache.getDicCarrierBeanCache().get(Long.parseLong(result.get("CARRIER_ID")+"")).getCarrierName());
				result.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(result.get("FLOW_UP")+"")+Double.parseDouble(result.get("FLOW_DN")+""))/1024/1024/1024)) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
}
	
		
	
		