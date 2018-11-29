package com.aotain.tdc.dao.businessAnalysis;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface RegionProductAnalysisDao {
	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);
}
