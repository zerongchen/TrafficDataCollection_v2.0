package com.aotain.tdc.dao.serviceConfiguration;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface BusinessInformationDao {
	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);
	
	public Integer getTableColumnsTotalCounts(BaseDTO dto);
	
    public List<HashMap<String, Object>> getroomColumns(BaseDTO dto);
	
	public Integer getroomColumnsTotalCounts(BaseDTO dto);
	
	public int insert(BaseDTO dto);
	
	public int insertIpMapping(BaseDTO dto);
	
	public int insertIpPort(BaseDTO dto);
	
	public int delete(BaseDTO dto);
	
	public int deleteIpMapping(BaseDTO dto);
	
	public int deleteIpPort(BaseDTO dto);
	
	public int update(BaseDTO dto);
	
	public int updateIpMapping(BaseDTO dto);
	
	public int updateIpPort(BaseDTO dto);
	
	public Integer getIpPortCounts(String id);
}
