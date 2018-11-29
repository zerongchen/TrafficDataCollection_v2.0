package com.aotain.tdc.service.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.security.SecurityUtils;
import com.aotain.tdc.dao.common.TableColumnDao;
import com.aotain.tdc.dto.common.TableColumnDTO;
import com.aotain.tdc.dto.common.TableColumns;
import com.aotain.tdc.dto.common.TableParam;

@Service
public class TableColumnService {
	
	@Autowired
	private TableColumnDao tableColumnDao;
	
	public List<TableColumns> getTableColumns(TableParam tc){
		List<TableColumnDTO> tableColumns = tableColumnDao.getTableColumns(tc);
		List<TableColumns> result = new ArrayList<TableColumns>();
		for(TableColumnDTO t : tableColumns){
			TableColumns c = new TableColumns();
			c.setField(t.getField());
			c.setTitle(t.getTitle());
			if(t.getColumn_type() == 2 || t.getUsercolumn_type() == 2){
				c.setVisible(false);
			}
			if(t.getOrder_type() == 0){
				c.setSortable(false);
			}
			if(t.getSwitchable() == 2){
				c.setSwitchable(false);
			}
			System.out.println(c.getTitle()+c.isSwitchable()+t.getSwitchable());
			c.setOrder(t.getColumn_order());
			result.add(c);
		}
		Collections.sort(result, new Comparator<TableColumns>(){
			@Override
			public int compare(TableColumns o1, TableColumns o2) {
				return o1.getOrder() - o2.getOrder();
			}
		});
		return result;
	}
	

	/**
	 * 获取某表名下所有字段
	 * @param tableName 表名
	 * @return
	 */
	public List<TableColumns> getTableColumns(String tableName){
		int userId = Integer.parseInt(SecurityUtils.getUserId()+"");
		TableParam tableParam = new TableParam(tableName, userId);
		List<TableColumnDTO> tableColumnDTOes = tableColumnDao.getTableColumns(tableParam);
		List<TableColumns> tableColumnList = new ArrayList<TableColumns>();
		for(TableColumnDTO item : tableColumnDTOes){
			TableColumns tableColumns = new TableColumns();
			tableColumns.setField(item.getField());
			tableColumns.setTitle(item.getTitle());
			tableColumns.setAlign("center");
			tableColumns.setValign("middle");
			if(item.getSwitchable() == 2) tableColumns.setSwitchable(false);
			if(item.getColumn_type() == 2 || item.getUsercolumn_type() == 2){
				tableColumns.setVisible(false);
			}
			tableColumnList.add(tableColumns);
		}
		return tableColumnList;
	}

	public int getDictId(TableParam param) {
		return tableColumnDao.getDictId(param);
	}
}
