package com.aotain.tdc.dao.quartz;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aotain.tdc.dto.common.BaseDTO;

@Service("sendZabbixDao")
public interface SendZabbixDao {

	public List<HashMap<String, Object>> getTableColumns(BaseDTO dto);

	public Integer getTableColumnsTotalCounts(BaseDTO dto);

}
