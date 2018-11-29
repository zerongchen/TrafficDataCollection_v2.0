package com.aotain.tdc.dao.sysconfig;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aotain.tdc.dto.common.TableColumnDTO;

public interface TableManagementDao {

	public List<HashMap<String, Object>> getTableColumns(TableColumnDTO dto);

	public Integer getTableColumnsTotalCounts(TableColumnDTO dto);

	public int update(TableColumnDTO dto);

	public Integer selectIdByColumnOder(@Param("queryColumnOrder") Integer queryColumnOrder, @Param("queryTableName") String queryTableName);

	public int updateUp(@Param("queryColumnOrder") int queryColumnOrder, @Param("id") Integer id, @Param("queryId") long queryId);

	public int updateDown(@Param("queryColumnOrder") int queryColumnOrder, @Param("id") Integer id, @Param("queryId") long queryId);

	public List<HashMap<String, Object>> selectTableNames(TableColumnDTO dto);

}
