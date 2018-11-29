package com.aotain.tdc.dao.traffic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aotain.tdc.dto.common.BaseDTO;

public interface RoomTrafficDao {

	public List<HashMap<String, Object>> getTrendTableDatas(BaseDTO dto);

	public Integer getTrendTableDatasTotalCounts(BaseDTO dto);
/**
 * up zhenggf 
 * @param dto
 * @return
 */
	public List<HashMap<String, Object>> getStatisticTableDatas(BaseDTO dto);
	
	public Integer getStatisticTableDatasTotalCounts(BaseDTO dto);
}
