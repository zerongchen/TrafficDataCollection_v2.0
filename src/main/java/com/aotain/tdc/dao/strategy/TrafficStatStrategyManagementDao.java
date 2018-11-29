package com.aotain.tdc.dao.strategy;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface TrafficStatStrategyManagementDao {
	public List<HashMap<String, Object>> getTableColumnsData(BaseDTO dto);
	public Integer getTableColumnsDataTotalCounts(BaseDTO dto);
	public int insert(BaseDTO dto);
	public int update(BaseDTO dto);
	public int delete(BaseDTO dto);
	public int updateStatus(BaseDTO dto);
}
