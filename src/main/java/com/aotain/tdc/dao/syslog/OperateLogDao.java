package com.aotain.tdc.dao.syslog;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.LogDTO;

public interface OperateLogDao {
	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);
	public Integer getTableColumnsTotalCounts(BaseDTO dto);
	public void insertOperateLog(LogDTO log);
}
