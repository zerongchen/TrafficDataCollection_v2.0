package com.aotain.tdc.service.strategy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.tdc.annotation.OperatLogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dao.strategy.FTPServerManagementDao;
import com.aotain.tdc.dto.common.BaseDTO;

@Service("ftpManagementService")
public class FTPServerManagementService {
	@Autowired
	FTPServerManagementDao ftpServiceManagementDao;
	
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(ftpServiceManagementDao.getTableColumns(dto));
		dto.setTotalCounts(ftpServiceManagementDao.getTableColumnsTotalCounts(dto));
		if (dto.getTotalCounts() == null)
			dto.setTotalCounts(0);
	}
	
	@OperatLogAop(opType=OpType.ADD, opModule="策略配置_FTP服务器管理")
	public void insert(BaseDTO dto, HttpServletRequest request) {
		ftpServiceManagementDao.insert(dto);
	}
	
	@OperatLogAop(opType=OpType.MOD, opModule="策略配置_FTP服务器管理")
	public void update(BaseDTO dto, HttpServletRequest request) {
		ftpServiceManagementDao.update(dto);
		
	}

	@OperatLogAop(opType=OpType.DEL, opModule="策略配置_FTP服务器管理")
	public void delete(BaseDTO dto, HttpServletRequest request) {
		ftpServiceManagementDao.delete(dto);
		
	}
	
}
