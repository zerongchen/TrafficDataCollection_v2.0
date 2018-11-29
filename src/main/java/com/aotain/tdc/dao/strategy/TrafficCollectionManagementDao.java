package com.aotain.tdc.dao.strategy;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface TrafficCollectionManagementDao {
	public List<HashMap<String, Object>> getTableColumnsData(BaseDTO dto);
	public Integer getTableColumnsDataTotalCounts(BaseDTO dto);
	public int insert(BaseDTO dto);
	public int insertFtp(BaseDTO dto);
	public int insertDestIp(BaseDTO dto);
	public int delete(BaseDTO dto);
	public int deleteFtp(BaseDTO dto);
	public int deleteDestIp(BaseDTO dto);
	public int update(BaseDTO dto);
	public int updateFtp(BaseDTO dto);
	public int updateDestIp(BaseDTO dto);
	public int updateStatus(BaseDTO dto);
	
}
