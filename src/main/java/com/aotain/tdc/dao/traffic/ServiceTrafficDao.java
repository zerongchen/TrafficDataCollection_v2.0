package com.aotain.tdc.dao.traffic;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface ServiceTrafficDao {

	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);
	public Integer getTableColumnsTotalCounts(BaseDTO dto);
	
	public List<HashMap<String, Object>> getStatisticsTableColumns(BaseDTO dto);
	public Integer getStatisticsTableColumnsTotalCounts(BaseDTO dto);

	public List<HashMap<String, Object>> getLayerTrenTableColumns(BaseDTO dto);
	public Integer getLayerTrendTableColumnsTotalCounts(BaseDTO dto);
}
