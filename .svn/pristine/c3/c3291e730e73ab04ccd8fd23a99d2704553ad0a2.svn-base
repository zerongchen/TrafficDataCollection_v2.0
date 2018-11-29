package com.aotain.tdc.service.strategy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.tdc.annotation.OperatLogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dao.strategy.TrafficStatStrategyManagementDao;
import com.aotain.tdc.dto.common.BaseDTO;

@Service("TrafficStatStrategyManagementService")
public class TrafficStatStrategyManagementService {
	@Autowired
	TrafficStatStrategyManagementDao trafficStatStrategyManagementDao;
	
	public void getTableColumnsData(BaseDTO dto){
		dto.setResultList(trafficStatStrategyManagementDao.getTableColumnsData(dto));
		dto.setTotalCounts(trafficStatStrategyManagementDao.getTableColumnsDataTotalCounts(dto));
		if(dto.getTotalCounts() == null){
			dto.setTotalCounts(0);
		}
	}
	
	@Transactional
	@OperatLogAop(opType=OpType.ADD, opModule="策略配置_流量采集管理")
	public int insert(BaseDTO dto, HttpServletRequest request) {
		int i = 0;
		i = i + trafficStatStrategyManagementDao.insert(dto);
		return i;
	}
	
	@Transactional
	@OperatLogAop(opType=OpType.MOD, opModule="策略配置_流量采集管理")
	public int update(BaseDTO dto, HttpServletRequest request) {
		int i = 0;
		i = i + trafficStatStrategyManagementDao.update(dto);
		return i;
	}
	
	@Transactional
	@OperatLogAop(opType=OpType.DEL, opModule="策略配置_流量采集管理")
	public void delete(BaseDTO dto, HttpServletRequest request) {
		trafficStatStrategyManagementDao.delete(dto);
	}
	
	@Transactional
	public void updateStatus(BaseDTO dto) {
		trafficStatStrategyManagementDao.updateStatus(dto);
	}
}
