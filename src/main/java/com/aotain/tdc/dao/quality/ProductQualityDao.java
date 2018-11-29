package com.aotain.tdc.dao.quality;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface ProductQualityDao {

	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);

	public Integer getTableColumnsTotalCounts(BaseDTO dto);

	public List<HashMap<String, Object>> getTrenTableColumns(BaseDTO dto);

	public Integer getTrendTableColumnsTotalCounts(BaseDTO dto);

	public List<HashMap<String, Object>> getLayerTrenTableColumns(BaseDTO dto);

	public Integer getLayerTrendTableColumnsTotalCounts(BaseDTO dto);

}
