package com.aotain.tdc.service.strategy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.annotation.OperatLogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dao.strategy.AccountManagementDao;
import com.aotain.tdc.dto.common.BaseDTO;

@Service("accountManagementService")
public class AccountManagementService {

	@Autowired
	AccountManagementDao accountManagementDao;

	@Autowired
	CommonCache commonCache;

	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(accountManagementDao.getTableColumns(dto));
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) dto.getResultList();
		for(int i=0; i< list.size();i++){
			list.get(i).put("PASSWD", list.get(i).get("PASSWD").toString().replaceAll("[\\S\\s]", "*"));
		}
		dto.setTotalCounts(accountManagementDao.getTableColumnsTotalCounts(dto));
		if (dto.getTotalCounts() == null)
			dto.setTotalCounts(0);
	}

	@Transactional
	@OperatLogAop(opType = OpType.ADD, opModule="策略配置_接口账号管理")
	public int insert(BaseDTO dto, HttpServletRequest request) {
		return accountManagementDao.insert(dto);
	}

	@OperatLogAop(opType = OpType.MOD, opModule="策略配置_接口账号管理")
	public int update(BaseDTO dto, HttpServletRequest request) {
		return accountManagementDao.update(dto);
	}

	@OperatLogAop(opType = OpType.DEL, opModule="策略配置_接口账号管理")
	public int delete(BaseDTO dto, HttpServletRequest request) {
		return accountManagementDao.delete(dto);
	}

	public int isExsit(BaseDTO one) {
		return accountManagementDao.isExsit(one);
	}
}