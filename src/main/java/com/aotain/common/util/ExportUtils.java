package com.aotain.common.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.apache.axiom.util.base64.Base64Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.springframework.stereotype.Repository;

import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.model.common.ExcelModel;
/**
 * 导出工具类
 * @author yinzf 
 * @createtime 2015年9月5日 下午10:48:25
 */
@Repository
public class ExportUtils<T> {
	/**
	 * 添加图片
	 * @param baseModel
	 * @param book 工作薄
	 */
	private void addImageForExcel(BaseModel<T> baseModel, SXSSFWorkbook book){
		List<String> dataURLs = baseModel.getDataURLs();
		if(CollectionUtils.isNotEmpty(dataURLs)){
			Sheet sheet = book.createSheet("报表");
			XSSFDrawing dr = (XSSFDrawing) sheet.createDrawingPatriarch();
			int row1 = 2, row2 = 25;
			for(int i = 0; i < dataURLs.size(); i++){
				XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 100, 50, 2, row1, 15, row2);
				dr.createPicture(anchor, book.addPicture(Base64Utils.decode(dataURLs.get(i)), SXSSFWorkbook.PICTURE_TYPE_PNG));
				row1 += row2;
				row2 += row2;
			}
		}
	}
	
	/**
	 * 添加列表
	 * @param baseModel
	 * @param book 工作薄
	 */
	@SuppressWarnings("unchecked")
	private void addTableForExcel(BaseModel<T> baseModel, SXSSFWorkbook book){
		String[] headers = baseModel.getHeaders();
		String[] fields = baseModel.getFields();
		List<T> datas = baseModel.getDatas();
		int dataSize = datas.size();
		int sheetNum = dataSize % 1048575 == 0 ? dataSize / 1048575 : dataSize / 1048575 + 1;
		CellStyle headStyle = createHeadCellStyle(book);
		CellStyle contentStyle = createContentCellStyle(book);
		for(int i = 0; i < sheetNum; i++){
			Sheet sheet = book.createSheet("列表" + (i + 1));
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(0);
			for(int j = 0; j < headers.length; j++){
				Cell cell = row.createCell(j);
				cell.setCellValue(headers[j]);
				cell.setCellStyle(headStyle);
				sheet.autoSizeColumn(j);
			}
		}
		int k = 1, j = 1;
		for(int i = 0; i < datas.size(); i++,j++){
			if(i == (1048575 * k + 1)){
				j = 1; //数据行从第一行开始
				k++; //第k个sheet
			}
			Sheet sheet = book.getSheetAt(k);
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(j);
			T t = datas.get(i);
			Class<T> tClass = (Class<T>) t.getClass();
			for(int m = 0; m < fields.length; m++){
				try {
					String field = fields[m];
					Cell cell = row.createCell(m);
					String getMethodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1); 
					Method getMethod = tClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(t, new Object[]{});
					value = value == null ? "" : value; 
					cell.setCellValue(value + ""); 
					cell.setCellStyle(contentStyle);
				} catch (IllegalAccessException e) {
					CommonLog.jspLog.error(e);
				} catch (IllegalArgumentException e) {
					CommonLog.jspLog.error(e);
				} catch (InvocationTargetException e) {
					CommonLog.jspLog.error(e);
				} catch (NoSuchMethodException e) {
					CommonLog.jspLog.error(e);
				} catch (SecurityException e) {
					CommonLog.jspLog.error(e);
				}
			}
		}
	}
	
	/**
	 * 添加列表
	 * @param baseModel
	 * @param book 工作薄
	 */
	@SuppressWarnings("unchecked")
	private void addMapExcelOfTable(BaseModel<T> baseModel, SXSSFWorkbook book){
		String[] headers = baseModel.getHeaders();
		String[] fields = baseModel.getFields();
		List<HashMap<String, Object>> datas = (List<HashMap<String, Object>>) baseModel.getDatas();
		int dataSize = datas.size();
		int sheetNum = dataSize % 1048575 == 0 ? dataSize / 1048575 : dataSize / 1048575 + 1;
		CellStyle headStyle = createHeadCellStyle(book);
		CellStyle contentStyle = createContentCellStyle(book);
		for(int i = 0; i < sheetNum; i++){
			Sheet sheet = book.createSheet("列表" + (i + 1));
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(0);
			for(int j = 0; j < headers.length; j++){
				Cell cell = row.createCell(j);
				cell.setCellValue(headers[j]);
				cell.setCellStyle(headStyle);
				sheet.autoSizeColumn(j);
			}
		}
		int k = 0, j = 1;
		for(int i = 0; i < datas.size(); i++,j++){
			if(i == (1048575 * (k + 1) + 1)){
				j = 1; //数据行从第一行开始
				k++; //第k个sheet
			}
			Sheet sheet = book.getSheetAt(k);
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(j);
			HashMap<String, Object> t = datas.get(i);
			for(int m = 0; m < fields.length; m++){
				try {
					String field = fields[m];
					Cell cell = row.createCell(m);
					Object value = t.get(field);
					value = value == null ? "" : value; 
					cell.setCellValue(value + ""); 
					cell.setCellStyle(contentStyle);
				} catch (SecurityException e) {
					CommonLog.jspLog.error(e);
				}
			}
		}
	}
	
	/**
	 * 添加列表
	 * @param baseModel
	 * @param book 工作薄
	 */
	@SuppressWarnings("unchecked")
	private void addExcelOfTable(BaseModel<T> baseModel, SXSSFWorkbook book){
		String[] headers = baseModel.getHeaders();
		String[] fields = baseModel.getFields();
		List<T> datas = baseModel.getDatas();
		int dataSize = datas.size();
		int sheetNum = dataSize % 1048575 == 0 ? dataSize / 1048575 : dataSize / 1048575 + 1;
		CellStyle headStyle = createHeadCellStyle(book);
		CellStyle contentStyle = createContentCellStyle(book);
		for(int i = 0; i < sheetNum; i++){
			Sheet sheet = book.createSheet("列表" + (i + 1));
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(0);
			for(int j = 0; j < headers.length; j++){
				Cell cell = row.createCell(j);
				cell.setCellValue(headers[j]);
				cell.setCellStyle(headStyle);
				sheet.autoSizeColumn(j);
			}
		}
		int k = 0, j = 1;
		for(int i = 0; i < datas.size(); i++,j++){
			if(i == (1048575 * (k + 1) + 1)){
				j = 1; //数据行从第一行开始
				k++; //第k个sheet
			}
			Sheet sheet = book.getSheetAt(k);
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(j);
			T t = datas.get(i);
			Class<T> tClass = (Class<T>) t.getClass();
			for(int m = 0; m < fields.length; m++){
				try {
					String field = fields[m];
					Cell cell = row.createCell(m);
					String getMethodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1); 
					Method getMethod = tClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(t, new Object[]{});
					value = value == null ? "" : value; 
					cell.setCellValue(value + ""); 
					cell.setCellStyle(contentStyle);
				} catch (IllegalAccessException e) {
					CommonLog.jspLog.error(e);
				} catch (IllegalArgumentException e) {
					CommonLog.jspLog.error(e);
				} catch (InvocationTargetException e) {
					CommonLog.jspLog.error(e);
				} catch (NoSuchMethodException e) {
					CommonLog.jspLog.error(e);
				} catch (SecurityException e) {
					CommonLog.jspLog.error(e);
				}
			}
		}
	}
	
	 /**
     * 创建工作表表头单元格样式
     * @param book 工作簿
     * @return
     */
    private CellStyle createHeadCellStyle(Workbook book){
    	CellStyle cellStyle = book.createCellStyle();
    	cellStyle.setAlignment(CellStyle.ALIGN_CENTER); //居中对齐
		org.apache.poi.ss.usermodel.Font font = book.createFont();
		font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
	    font.setFontHeightInPoints((short)12);
	    cellStyle.setFont(font);
	    //cellStyle.setFillBackgroundColor(HSSFColor.ORANGE.index);
	    //cellStyle.setFillForegroundColor(HSSFColor.ORANGE.index);
	    return cellStyle;
    }
    
    /**
     * 创建工作表内容单元格样式
     * @param book 工作簿
     * @return
     */
    private CellStyle createContentCellStyle(Workbook book){
    	DataFormat dataFormat = book.createDataFormat();
    	CellStyle cellStyle = book.createCellStyle();
    	cellStyle.setDataFormat(dataFormat.getFormat("text"));
    	cellStyle.setAlignment(CellStyle.ALIGN_LEFT); //左对齐
    	org.apache.poi.ss.usermodel.Font font = book.createFont();
    	font.setFontName("宋体");
    	font.setFontHeightInPoints((short)10);
	    cellStyle.setFont(font);
    	return cellStyle;
    }
    
    public static CellStyle createCellStyle(Workbook workbook, short groundColor){
    	CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(groundColor);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
    }
	
	/**
	 * 导出EXCEL
	 * @param baseModel
	 */
	public void exportExcel(BaseModel<T> baseModel){
		ExcelModel<T> excelModel = (ExcelModel<T>)baseModel;
		BufferedOutputStream bos = new BufferedOutputStream(baseModel.getOs());
		try {
			SXSSFWorkbook book = new SXSSFWorkbook(excelModel.getRowAccessWindowSize()); // keep excelModel.getRowAccessWindowSize() rows in memory, exceeding rows will be flushed to disk
			book.setCompressTempFiles(true);
			addImageForExcel(excelModel, book);
			addTableForExcel(baseModel, book);
			book.write(bos);
			IOUtils.closeQuietly(bos);
			book.dispose(); // dispose of temporary files backing this workbook on disk
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
	}
	
	/**
	 * 导出EXCEL
	 * @param baseModel
	 */
	public void exportForExcel(BaseModel<T> baseModel){
		ExcelModel<T> excelModel = (ExcelModel<T>)baseModel;
		BufferedOutputStream bos = new BufferedOutputStream(baseModel.getOs());
		try {
			SXSSFWorkbook book = new SXSSFWorkbook(excelModel.getRowAccessWindowSize()); // keep excelModel.getRowAccessWindowSize() rows in memory, exceeding rows will be flushed to disk
			book.setCompressTempFiles(true);
			addExcelOfTable(baseModel, book);
			book.write(bos);
			IOUtils.closeQuietly(bos);
			book.dispose(); // dispose of temporary files backing this workbook on disk
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
	}

	public void exportMapExcel(ExcelModel<T> baseModel) {
		ExcelModel<T> excelModel = (ExcelModel<T>)baseModel;
		BufferedOutputStream bos = new BufferedOutputStream(baseModel.getOs());
		try {
			SXSSFWorkbook book = new SXSSFWorkbook(excelModel.getRowAccessWindowSize()); // keep excelModel.getRowAccessWindowSize() rows in memory, exceeding rows will be flushed to disk
			book.setCompressTempFiles(true);
			addMapExcelOfTable(baseModel, book);
			book.write(bos);
			IOUtils.closeQuietly(bos);
			book.dispose(); // dispose of temporary files backing this workbook on disk
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
	}
	
}
