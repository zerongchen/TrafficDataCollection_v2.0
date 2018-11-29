package com.aotain.tdc.service.sysconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.tdc.dao.sysconfig.ThresholdManagementDao;
import com.aotain.tdc.dto.common.BaseDTO;

@Service("thresholdManagementService")
public class ThresholdManagementService {
	
    @Autowired
    ThresholdManagementDao thresholdManagementDao;

	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(thresholdManagementDao.getTableColumns(dto));
		dto.setTotalCounts(thresholdManagementDao.getTableColumnsTotalCounts(dto));
	}
	public int update(BaseDTO dto) {
		return thresholdManagementDao.update(dto);
	}
}
