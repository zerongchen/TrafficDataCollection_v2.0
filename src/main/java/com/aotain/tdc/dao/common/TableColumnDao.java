package com.aotain.tdc.dao.common;

import java.util.List;

import com.aotain.tdc.dto.common.TableColumnDTO;
import com.aotain.tdc.dto.common.TableParam;

public interface TableColumnDao {

	List<TableColumnDTO> getTableColumns(TableParam tc);

	int getDictId(TableParam param);
}
