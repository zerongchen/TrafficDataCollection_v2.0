package com.aotain.tdc.dao.traffic;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface TrafficRecoveryManagementDao {

	public List<HashMap<String, Object>> getTableColumnsData(BaseDTO dto);
	
	public Integer getTableColumnsTotalCounts(BaseDTO dto);

	public int update(BaseDTO dto);

	public int delete(BaseDTO dto);
	
	public int deleteFtp(BaseDTO dto);
	
	public int insertFtp(BaseDTO dto);

	public int insert(BaseDTO dto);

	public int deleteDestIpPort(BaseDTO dto);

	public int insertDestIpPort(BaseDTO dto);

	public int updateStatus(BaseDTO dto);

}
