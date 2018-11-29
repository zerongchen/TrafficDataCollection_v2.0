package com.aotain.tdc.dao.businessAnalysis;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.TableColumnDTO;

public interface UrlQualityDao {

	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);

	public Integer getTableColumnsTotalCounts(BaseDTO dto);

}
