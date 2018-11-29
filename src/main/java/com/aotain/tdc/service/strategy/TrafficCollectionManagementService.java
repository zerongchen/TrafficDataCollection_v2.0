package com.aotain.tdc.service.strategy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.annotation.OperatLogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dao.strategy.TrafficCollectionManagementDao;
import com.aotain.tdc.dto.common.BaseDTO;

@Service("TrafficCollectionManagementService")
public class TrafficCollectionManagementService {
	@Autowired
	TrafficCollectionManagementDao trafficCollectionManagementDao;
	@Autowired
	private CommonCache commonCache;
	
	public void getTableColumnsData(BaseDTO dto){
		dto.setResultList(trafficCollectionManagementDao.getTableColumnsData(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("CATALOGNAME", commonCache.getDicProtoCataLogBeanCache().get(Long.parseLong(result.get("CATALOGID")+"")).getCataName());                          
				result.put("CLASSNAME", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(result.get("CLASSID")+"")).getClassName());
				result.put("PRODUCTNAME", commonCache.getDicProtoProductBeanCache().get(Long.parseLong(result.get("PRODUCTID")+"")).getProductName());
				result.put("MODULENAME", commonCache.getDicProtoModuleBeanCache().get(Long.parseLong(result.get("MODULEID")+"")).getModuleName());
				result.put("PROTOCOLTYPE_NAME", commonCache.getDicProtocolBeanCache().get(result.get("PROTOCOLTYPE_ID")+"_"+"1").getProtocolName());
				result.put("FTP_SERVER", commonCache.getColPolicyStrategyFTPBeanByKey().get(Long.parseLong(result.get("STRATEGY_ID").toString())).getFtpName());
				result.put("FTP_IDS", commonCache.getColPolicyStrategyFTPBeanByKey().get(Long.parseLong(result.get("STRATEGY_ID").toString())).getFtpIds());
				result.put("DST_IP", commonCache.getColPolicyDstIPPortBeanCache().get(Long.parseLong(result.get("STRATEGY_ID").toString())).getDstIp());
				result.put("DST_PORT", commonCache.getColPolicyDstIPPortBeanCache().get(Long.parseLong(result.get("STRATEGY_ID").toString())).getDstPort());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(trafficCollectionManagementDao.getTableColumnsDataTotalCounts(dto));
		if(dto.getTotalCounts() == null){
			dto.setTotalCounts(0);
		}
	}
	@OperatLogAop(opType=OpType.ADD, opModule="策略配置_流量采集管理")
	public int insert(BaseDTO dto, HttpServletRequest request) {
		int i = 0;
		i = i + trafficCollectionManagementDao.insert(dto);
		if(dto.getQueryFTPServerIds()!=null){
			for(String ftpServerId : dto.getQueryFTPServerIds().split(",")){
				dto.setQueryFTPServerId(ftpServerId);
				i = i + trafficCollectionManagementDao.insertFtp(dto);
			}
		}
		if(dto.getQueryDestIp()!=null && dto.getQueryDestPort() != null){
			String[] ips = dto.getQueryDestIp().split(";");
			String[] ports = dto.getQueryDestPort().split(";");
			for(int j = 0; j < ips.length; j++){
				dto.setQueryDestIp(ips[j]);
				dto.setQueryDestPort(ports[j]);
				i = i + trafficCollectionManagementDao.insertDestIp(dto);
			}
		}
		commonCache.refreshCache();
		return i;
	}
	@Transactional
	@OperatLogAop(opType=OpType.DEL, opModule="策略配置_流量采集管理")
	public void delete(BaseDTO dto, HttpServletRequest request) {
		trafficCollectionManagementDao.delete(dto);
//		trafficCollectionManagementDao.deleteFtp(dto);
//		trafficCollectionManagementDao.deleteDestIp(dto);
	}
	@Transactional
	@OperatLogAop(opType=OpType.MOD, opModule="策略配置_流量采集管理")
	public int update(BaseDTO dto, HttpServletRequest request) {
		int i = 0;
		i = i + trafficCollectionManagementDao.deleteFtp(dto);
		if(dto.getQueryFTPServerIds()!=null){
			for(String ftpServerId : dto.getQueryFTPServerIds().split(",")){
				dto.setQueryFTPServerId(ftpServerId);
				i = i + trafficCollectionManagementDao.insertFtp(dto);
			}
		}
		i = i + trafficCollectionManagementDao.deleteDestIp(dto);
		if(dto.getQueryDestIp()!=null && dto.getQueryDestPort() != null){
			String[] ips = dto.getQueryDestIp().split(";");
			String[] ports = dto.getQueryDestPort().split(";");
			for(int j = 0; j < ips.length; j++){
				dto.setQueryDestIp(ips[j]);
				dto.setQueryDestPort(ports[j]);
				i = i + trafficCollectionManagementDao.insertDestIp(dto);
			}
		}
//		if(dto.getQueryStrategyVer()!=null)
//		dto.setQueryStrategyVer(dto.getQueryStrategyVer()+1);
		i = i + trafficCollectionManagementDao.update(dto);
		commonCache.refreshCache();
		return i;
	}

	public void updateStatus(BaseDTO dto) {
		trafficCollectionManagementDao.updateStatus(dto);
	}
}
