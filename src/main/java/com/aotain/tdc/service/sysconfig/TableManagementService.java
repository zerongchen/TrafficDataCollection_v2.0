package com.aotain.tdc.service.sysconfig;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.tdc.dao.sysconfig.TableManagementDao;
import com.aotain.tdc.dto.common.TableColumnDTO;
import com.aotain.tdc.model.common.MsgBean;

@Service("tableManagementService")
public class TableManagementService {
	
    @Autowired
    TableManagementDao tableManagementDao;

	public void getTableColumns(TableColumnDTO dto) {
		dto.setResultList(tableManagementDao.getTableColumns(dto));
		dto.setTotalCounts(tableManagementDao.getTableColumnsTotalCounts(dto));
	}

	public int update(TableColumnDTO dto, HttpServletRequest request) {
		return tableManagementDao.update(dto);
	}
	
	public int updateUp(TableColumnDTO dto, HttpServletRequest request) {
		Integer id = tableManagementDao.selectIdByColumnOder(dto.getQueryColumnOrder()-1, dto.getQueryTableName());
		if(id != null){
			return tableManagementDao.updateUp(dto.getQueryColumnOrder(), id, dto.getQueryId());
		} else return 0;
	}
	
	public int updateDown(TableColumnDTO dto, HttpServletRequest request) {
		Integer id = tableManagementDao.selectIdByColumnOder(dto.getQueryColumnOrder()+1, dto.getQueryTableName());
		if(id != null){
			return tableManagementDao.updateDown(dto.getQueryColumnOrder(), id, dto.getQueryId());
		}else return 0;
	}

	public List<HashMap<String, Object>> selectTableNames(TableColumnDTO dto) {
		return tableManagementDao.selectTableNames(dto);
	}
}
