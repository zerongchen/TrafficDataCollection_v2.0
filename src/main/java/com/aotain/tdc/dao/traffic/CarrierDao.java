package com.aotain.tdc.dao.traffic;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface CarrierDao {
	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);

	public Integer getTableColumnsTotalCounts(BaseDTO dto);
	
	public List<HashMap<String, Object>> getTimeParticleCounts(BaseDTO dto);
	
	public List<HashMap<String, Object>> getTimeParticleList(BaseDTO dto);
	
	public List<HashMap<String, Object>> getFlowTrendByIdList(BaseDTO dto);
}
