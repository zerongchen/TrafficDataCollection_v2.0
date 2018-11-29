package com.aotain.tdc.service.common;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.model.common.ExcelModel;
/**
 * 导出(excel、word、pdf)
 * @author yinzf 
 * @createtime 2015年9月7日 上午9:57:40
 */
@Service
@Transactional(readOnly = true)
public class ExportServiceImpl<T> implements ExportService<T>{
	
	@Autowired
	private com.aotain.common.util.ExportUtils<T> exportUtils;
	
	@Override
	public void export(BaseModel<T> baseModel) {
		ExportType exportType = ExportType.valueOf(baseModel.getExportType());
		switch(exportType){
			case EXCEL:
				ExcelModel<T> excelModel = new ExcelModel<T>();
				excelModel.setDatas(baseModel.getDatas());
				excelModel.setFields(baseModel.getFields());
				excelModel.setHeaders(baseModel.getHeaders());
				excelModel.setDataURLs(baseModel.getDataURLs());
				excelModel.setOs(baseModel.getOs());
				//excelModel.setColWidths(baseModel.getColWidths());
				exportUtils.exportExcel(excelModel);
				break;
			default:
				break;
		}
	}
	/**
	 * add 郑高峰
	 * 不含图表导出导出 (excel、word、pdf)
	 * @param baseModel
	 */
	@Override
	public void exportForExcel(BaseModel<T> baseModel) {
		ExportType exportType = ExportType.valueOf(baseModel.getExportType());
		switch(exportType){
			case EXCEL:
				ExcelModel<T> excelModel = new ExcelModel<T>();
				excelModel.setDatas(baseModel.getDatas());
				excelModel.setFields(baseModel.getFields());
				excelModel.setHeaders(baseModel.getHeaders());
				excelModel.setDataURLs(baseModel.getDataURLs());
				excelModel.setOs(baseModel.getOs());
				exportUtils.exportForExcel(excelModel);
				break;
			default:
				break;
		}
		
	}
	
	/**
	 * 
	  * exportMapExcel
	  * TODO(hashmap替代实体类的导出方法，不含图片导出)
	  * @author zck
	  * @Title: exportMapExcel
	  * @Description: TODO
	  * @param BaseModel<T> baseModel
	  * @return void    返回类型
	  * @throws
	 */
	@Override
	public void exportMapExcel(BaseModel<T> baseModel) {
		ExportType exportType = ExportType.valueOf(baseModel.getExportType());
		switch(exportType){
			case EXCEL:
				ExcelModel<T> excelModel = new ExcelModel<T>();
				excelModel.setDatas(baseModel.getDatas());
				excelModel.setFields(baseModel.getFields());
				excelModel.setHeaders(baseModel.getHeaders());
				excelModel.setDataURLs(baseModel.getDataURLs());
				excelModel.setOs(baseModel.getOs());
				exportUtils.exportMapExcel(excelModel);
				break;
			default:
				break;
		}
	}
}
