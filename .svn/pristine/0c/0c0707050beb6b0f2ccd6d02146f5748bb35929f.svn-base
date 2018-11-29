package com.aotain.tdc.service.traffic;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.security.UserDetail;
import com.aotain.common.security.Userbean;
import com.aotain.common.util.LocalConfig;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.annotation.OperatLogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dao.common.Commondao;
import com.aotain.tdc.dao.traffic.TrafficRecoveryManagementDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DpiClogBean;

@Service("trafficRecoveryManagementService")
public class TrafficRecoveryManagementService {
	
	@Autowired
	TrafficRecoveryManagementDao trafficRecoveryManagementDao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	CommonCache commonCache;
	
	public void getTableColumnsData(BaseDTO dto) throws NumberFormatException, ExecutionException {
		List<HashMap<String, Object>> list = trafficRecoveryManagementDao.getTableColumnsData(dto);
		for(int i = 0; i < list.size(); i++){
			HashMap<String, Object> hashMap = list.get(i);
			hashMap.put("CREATETIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hashMap.get("CREATETIME")));
			hashMap.put("CATALOGNAME", commonCache.getDicProtoCataLogBeanCache().get(Long.parseLong(hashMap.get("CATALOGID").toString())).getCataName());
			hashMap.put("CLASSNAME", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(hashMap.get("CLASSID").toString())).getClassName());
			hashMap.put("PRODUCTNAME", commonCache.getDicProtoProductBeanCache().get(Long.parseLong(hashMap.get("PRODUCTID").toString())).getProductName());
			//hashMap.put("MODULENAME", commonCache.getDicProtoModuleBeanCache().get(Long.parseLong(hashMap.get("MODULEID").toString())).getModuleName());
			hashMap.put("MODULENAME", "");
			hashMap.put("FTP_SERVER", commonCache.getPolicyStrategyFTPBeanCache().get(Long.parseLong(hashMap.get("STRATEGY_ID").toString())).getFtpName());
			hashMap.put("FTP_IDS", commonCache.getPolicyStrategyFTPBeanCache().get(Long.parseLong(hashMap.get("STRATEGY_ID").toString())).getFtpIds());
			hashMap.put("FTP_IPS", commonCache.getPolicyStrategyFTPBeanCache().get(Long.parseLong(hashMap.get("STRATEGY_ID").toString())).getIps());
			hashMap.put("DST_IP", commonCache.getPolicyDstIPPortBeanCache().get(Long.parseLong(hashMap.get("STRATEGY_ID").toString())).getDstIp());
			hashMap.put("DST_PORT", commonCache.getPolicyDstIPPortBeanCache().get(Long.parseLong(hashMap.get("STRATEGY_ID").toString())).getDstPort());
		}
		dto.setResultList(list);
		Integer totalCount = trafficRecoveryManagementDao.getTableColumnsTotalCounts(dto);
		if(totalCount == null) totalCount = 0;
		dto.setTotalCounts(totalCount);
	}
	@ClogAop(opType=OpType.MOD)
	@Transactional
	@OperatLogAop(opType=OpType.MOD, opModule="策略配置_流量还原管理")
	public int update(BaseDTO dto, HttpServletRequest request) {
		
		int i = 0;
		i = i + trafficRecoveryManagementDao.deleteFtp(dto);
		if(dto.getQueryFTPServerIds()!=null){
			for(String ftpServerId : dto.getQueryFTPServerIds().split(",")){
				dto.setQueryFTPServerId(ftpServerId);
				i = i + trafficRecoveryManagementDao.insertFtp(dto);
			}
		}
		i = i + trafficRecoveryManagementDao.deleteDestIpPort(dto);
		if(dto.getQueryDestIp()!=null && dto.getQueryDestPort() != null){
			String[] ips = dto.getQueryDestIp().split(";");
			String[] ports = dto.getQueryDestPort().split(";");
			for(int j = 0; j < ips.length; j++){
				dto.setQueryDestIp(ips[j]);
				dto.setQueryDestPort(ports[j]);
				i = i + trafficRecoveryManagementDao.insertDestIpPort(dto);
			}
		}
		i = i + trafficRecoveryManagementDao.update(dto);
		
		for (String strategyId : dto.getQueryStrategyId().split(",")) {
			BaseDTO queryDto = new BaseDTO();
			queryDto.setQueryStrategyId(strategyId);
			queryDto.setIsPaging(0);
			queryDto.setQueryStatus(-1);
			HashMap<String, Object> hm = (HashMap<String, Object>) trafficRecoveryManagementDao.getTableColumnsData(queryDto).get(0);
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(LocalConfig.getInstance().getOpCodeRe());
			clogBean.setRECID1(Integer.parseInt(hm.get("STRATEGY_ID").toString()));
			clogBean.setRECID2(Integer.parseInt(hm.get("MESSAGE_SEQUENCENO").toString()));
			clogBean.setOP_TYPE(OpType.MOD.getType());
			commondao.insertClog(clogBean);
		}
		
		commonCache.getPolicyStrategyFTPBeanCache().invalidateAll();
		commonCache.getPolicyDstIPPortBeanCache().invalidateAll();
		return i;
	}
	@ClogAop(opType=OpType.DEL)
	@OperatLogAop(opType=OpType.DEL, opModule="策略配置_流量还原管理")
	public int delete(BaseDTO dto, HttpServletRequest request) {
		for (String strategyId : dto.getQueryStrategyIds().split(",")) {
			BaseDTO queryDto = new BaseDTO();
			queryDto.setQueryStrategyId(strategyId);
			queryDto.setIsPaging(0);
			queryDto.setQueryStatus(-1);
			HashMap<String, Object> hm = (HashMap<String, Object>) trafficRecoveryManagementDao.getTableColumnsData(queryDto).get(0);
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(LocalConfig.getInstance().getOpCodeRe());
			clogBean.setRECID1(Integer.parseInt(hm.get("STRATEGY_ID").toString()));
			clogBean.setRECID2(Integer.parseInt(hm.get("MESSAGE_SEQUENCENO").toString())+1);
			clogBean.setOP_TYPE(OpType.DEL.getType());
			// MYCAT_SEQ_NEXTVAL('MESSAGE_SEQUENCENO_RE')
			commondao.selectMessageSequenceno("MESSAGE_SEQUENCENO_RE");
			commondao.insertClog(clogBean);
		}
		return trafficRecoveryManagementDao.delete(dto);
	}
	@ClogAop(opType=OpType.ADD)
	@OperatLogAop(opType=OpType.ADD, opModule="策略配置_流量还原管理")
	public int insert(BaseDTO dto, HttpServletRequest request) {
		int i = 0;
		i = i + trafficRecoveryManagementDao.insert(dto);
		if(dto.getQueryFTPServerIds()!=null){
			for(String ftpServerId : dto.getQueryFTPServerIds().split(",")){
				dto.setQueryFTPServerId(ftpServerId);
				i = i + trafficRecoveryManagementDao.insertFtp(dto);
			}
		}
		if(dto.getQueryDestIp()!=null && dto.getQueryDestPort() != null){
			String[] ips = dto.getQueryDestIp().split(";");
			String[] ports = dto.getQueryDestPort().split(";");
			for(int j = 0; j < ips.length; j++){
				dto.setQueryDestIp(ips[j]);
				dto.setQueryDestPort(ports[j]);
				i = i + trafficRecoveryManagementDao.insertDestIpPort(dto);
			}
		}
		
		for (String strategyId : dto.getQueryStrategyId().split(",")) {
			BaseDTO queryDto = new BaseDTO();
			queryDto.setQueryStrategyId(strategyId);
			queryDto.setIsPaging(0);
			queryDto.setQueryStatus(-1);
			HashMap<String, Object> hm = (HashMap<String, Object>) trafficRecoveryManagementDao.getTableColumnsData(queryDto).get(0);
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(LocalConfig.getInstance().getOpCodeRe());
			clogBean.setRECID1(Integer.parseInt(hm.get("STRATEGY_ID").toString()));
			clogBean.setRECID2(Integer.parseInt(hm.get("MESSAGE_SEQUENCENO").toString()));
			clogBean.setOP_TYPE(OpType.ADD.getType());
			commondao.insertClog(clogBean);
		}
		
		commonCache.refreshCache();
		return i;
	}
	@ClogAop(opType=OpType.STOP_OR_PLAY)
	public int updateStatus(BaseDTO dto) {		
		int i = 0;
		i = i + trafficRecoveryManagementDao.updateStatus(dto);
		commonCache.getPolicyStrategyFTPBeanCache().invalidateAll();
		commonCache.getPolicyDstIPPortBeanCache().invalidateAll();
		
		for (String strategyId : dto.getQueryStrategyId().split(",")) {
			BaseDTO queryDto = new BaseDTO();
			queryDto.setQueryStrategyId(strategyId);
			queryDto.setIsPaging(0);
			queryDto.setQueryStatus(-1);
			
			HashMap<String, Object> hm = (HashMap<String, Object>) trafficRecoveryManagementDao.getTableColumnsData(queryDto).get(0);
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(LocalConfig.getInstance().getOpCodeRe());
			clogBean.setRECID1(Integer.parseInt(hm.get("STRATEGY_ID").toString()));
			clogBean.setRECID2(Integer.parseInt(hm.get("MESSAGE_SEQUENCENO").toString()));
			if(dto.getQueryStatus() == 1) clogBean.setOP_TYPE(OpType.PLAY.getType());
			if(dto.getQueryStatus() == 3) clogBean.setOP_TYPE(OpType.STOP.getType());
			commondao.insertClog(clogBean);
		}
		return i;
	}

}