package com.aotain.common.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.aotain.tdc.constant.FileType;
import com.aotain.tdc.exception.ExcelUnsupportedException;

public class ExcelUtils {
	
    /**
     * 读取文件
     * @param input 输入流
     * @return 数据集
     */
    public static Map<Integer,Map<Integer, String[]>> readExcelFromStream(InputStream inputStream) {
    	BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
        	//long s1 = System.nanoTime();  
        	Workbook wb = null;
		    if(POIFSFileSystem.hasPOIFSHeader(bis)){
		    	wb = new HSSFWorkbook(bis); //支持xls
		    }else if(POIXMLDocument.hasOOXMLHeader(bis)){
				wb = new XSSFWorkbook(bis); //支持xlsx
		    }else{
		    	throw new ExcelUnsupportedException("This version of Excel will not support!");
		    }
            Map<Integer,Map<Integer, String[]>> sheetDataMap = new HashMap<Integer,Map<Integer, String[]>>(); //每个sheet对应数据集
            for(int i = 0; i < wb.getNumberOfSheets(); i++){
            	Sheet sheet = wb.getSheetAt(i);  
            	int totalRow = sheet.getPhysicalNumberOfRows();
            	if(totalRow <= 1){
            		continue;
            	}
            	Map<Integer, String[]> rowDataMap = new HashMap<Integer, String[]>(); //每行对应的数据集
        		int cells = 0;
	            for(Row row : sheet){
	            	int rowNum = row.getRowNum();
	            	//跳过第0行(表头)
	            	if(rowNum == 0){
	            		cells = row.getPhysicalNumberOfCells();
	            		continue;
	            	}	       
	            	String[] cellDatas = new String[cells];
	            	int j = 0;
	                for(Cell cell : row){
	                	int colNum = cell.getColumnIndex();
	                	//如果数据行的单元格数大于表头的单元格数则舍弃
	                	if(j >= cells){
	                		break;
	                	}
	                    switch (cell.getCellType()) {
		                    case Cell.CELL_TYPE_NUMERIC: 
	                    		BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
	                    		bd.setScale(0,BigDecimal.ROUND_HALF_UP);
	                    		cellDatas[colNum] = StringUtils.trim(bd + "");
	                    		j++;
		                        break;
		                    case Cell.CELL_TYPE_STRING:
		                    	cellDatas[colNum] = StringUtils.trim(cell.getStringCellValue());  
		                    	j++;
		                        break;
		                    default:  
		                    	j++;
		                        break;  
	                    }
	                }
	                rowDataMap.put(rowNum, cellDatas);
	            }  
	            sheetDataMap.put(i, rowDataMap);
            }
            return sheetDataMap;
            //long s2 = System.nanoTime();  
            //System.out.println(" takes time:" + (s2-s1));  
        } catch (FileNotFoundException e) {  
        	CommonLog.jspLog.error(e);
    		return null;
        } catch (IOException e) {
        	CommonLog.jspLog.error(e);
    		return null;
        }finally{
        	IOUtils.closeQuietly(bis);
        }
    }  
    
    /**
     * 获取文件类型
     * @param input 输入流
     * @return
     */
    public static FileType getFileType(InputStream input){
    	FileType fileType = null;
		try {
	    	input.mark(1024);
			fileType = FileTypeValidateUtils.getType(input);
			input.reset();
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
		return fileType;
    }
    
    /**
     * 获取文件类型
     * @param input 输入流
     * @param FileType 文件类型
     * @return
     */
    public static FileType getFileType(InputStream input, FileType[] fileTypes){
    	FileType fileType = null;
		try {
	    	input.mark(1024);
			fileType = FileTypeValidateUtils.getType(input, fileTypes);
			input.reset();
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
		return fileType;
    }
   
    /**
     * 获取输入流
     * @param request 请求对象
     * @return
     */
    public static InputStream getInputStream(MultipartFile file){
    	InputStream input = null;
		try {
			input = new BufferedInputStream(file.getInputStream());
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
		return input;
    }
    
//    private static Map<String, Object> mapForAnnotition(Class<?> entityClass){
//		Map<String, Object> map = new HashMap<String, Object>();
//    	List<String> fieldNames = new ArrayList<String>();
//		List<String> titles = new ArrayList<String>();
//		Field[] fields = entityClass.getDeclaredFields();
//		List<Field> list = new ArrayList<Field>();
//		for(Field field : fields){
//			Export export = field.getAnnotation(Export.class);
//			if(export != null){
//				list.add(field); //过滤掉没有注解的字段
//			}
//		}
//		Field[] secondFields = list.toArray(new Field[list.size()]);
//		Arrays.sort(secondFields, new FieldComparator()); //将secondFields中的元素以注解@Export中id属性排序
//		for(Field field : secondFields){
//			Export export = field.getAnnotation(Export.class);
//			fieldNames.add(field.getName());
//			titles.add(export.title());			
//		}
//		map.put("fieldNames", fieldNames);
//		map.put("titles", titles);
//		map.put("fields", secondFields);
//		return map;
//	}
    
    public static Map<String, Object> getObjectRejectMap(Object object, Field[] fields) {
		Map<String, Object> map = new HashMap<String, Object>();
		for(Field field : fields){
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(object));
			} catch (IllegalArgumentException e) {
				CommonLog.jspLog.error(e);
			} catch (IllegalAccessException e) {
				CommonLog.jspLog.error(e);
			}
		}
		return map;
	}
    
    @SuppressWarnings("unchecked")
//	public static SXSSFWorkbook createExcel(List<?> list, Class<?> entityClass){
////    	long startTime = System.currentTimeMillis();
//    	Map<String, Object> map = mapForAnnotition(entityClass);
//    	List<String> titles = (List<String>) map.get("titles");
//    	List<String> fieldNames = (List<String>) map.get("fieldNames");
//    	Field[] fields = (Field[]) map.get("fields");
//		SXSSFWorkbook book = new SXSSFWorkbook(100); 
//		book.setCompressTempFiles(true);
//		Sheet sheet = book.createSheet();
//		CellStyle contentCellStyle = ExcelUtils.createContentCellStyle(book);
//		CellStyle dateStyle = ExcelUtils.createDateStyle(book);
//		//创建表头
//		Row firstRow = sheet.createRow(0);
//		for(int i = 0; i < titles.size(); i++){
//			Cell cell = firstRow.createCell(i);
//			cell.setCellValue(titles.get(i));
//			cell.setCellStyle(ExcelUtils.createHeadCellStyle(book));
//			sheet.autoSizeColumn(i);
//		}
//		for(int j = 0; j < list.size(); j++){
//			Row row = sheet.createRow(j + 1);
//			Map<String, Object> fieldValueMap = getObjectRejectMap(list.get(j), fields);
//			for(int k = 0; k < titles.size(); k++){
//				CellStyle cellStyle = contentCellStyle;
//				Cell cell = row.createCell(k);
//				Object obj = fieldValueMap.get(fieldNames.get(k));
//				if(obj instanceof String){
//					cell.setCellValue((String)obj);
//				}else if(obj instanceof Integer){
//					cell.setCellValue((Integer)obj);
//				}else if(obj instanceof Long){
//					cell.setCellValue((Long)obj);
//				}else if(obj instanceof Date){
//					cellStyle = dateStyle;
//					cell.setCellValue((Date)obj);
//				}else if(obj instanceof Boolean){
//					cell.setCellValue((Boolean)obj);
//				}else if(obj instanceof Double){
//					cell.setCellValue((Double)obj);
//				}else if(obj instanceof Float){
//					cell.setCellValue((Float)obj);
//				}
//				cell.setCellStyle(cellStyle);
//			}
//		}
//		long endTime = System.currentTimeMillis();
//		long spendTime = (endTime - startTime)/1000l;
//		System.out.println("Export spend " + spendTime + " seconds");
//		return book;
//    }
    
//    public static void createExcel(List<?> list, Class<?> entityClass, String filePath, String fileName){
//    	SXSSFWorkbook book = createExcel(list, entityClass);
//    	File file = new File(filePath);
//		if(!file.exists() && !file.isDirectory()){
//			file.mkdirs();
//		}
//		String path = filePath + fileName;
//		BufferedOutputStream bos = null;
//		try {
//			bos = new BufferedOutputStream(new FileOutputStream(path));
//			book.write(bos);
//			bos.flush();
//			book.dispose();
//		} catch (FileNotFoundException e) {
//			DamsLog.opLog.error("filePath:" + path, e);
//		} catch (IOException e) {
//			DamsLog.opLog.error(e);
//		} finally{
//			IOUtils.closeQuietly(bos);
//		}
//    }
    
    /**
     * 创建工作表表头单元格样式
     * @param book 工作簿
     * @return
     */
    public static CellStyle createHeadCellStyle(Workbook book){
    	CellStyle cellStyle = book.createCellStyle();
    	cellStyle.setAlignment(CellStyle.ALIGN_CENTER); //居中对齐
		Font font = book.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
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
    public static CellStyle createContentCellStyle(Workbook book){
    	DataFormat dataFormat = book.createDataFormat();
    	CellStyle cellStyle = book.createCellStyle();
    	cellStyle.setDataFormat(dataFormat.getFormat("text"));
    	cellStyle.setAlignment(CellStyle.ALIGN_LEFT); //左对齐
    	Font font = book.createFont();
    	font.setFontName("宋体");
    	font.setFontHeightInPoints((short)10);
	    cellStyle.setFont(font);
    	return cellStyle;
    }
    
    /**
     * 创建日期样式
     * @param book 工作簿
     * @return
     */
    public static CellStyle createDateStyle(Workbook book){
    	DataFormat dataFormat = book.createDataFormat();
    	CellStyle cellStyle = book.createCellStyle();
    	cellStyle.setDataFormat(dataFormat.getFormat("yyyy-mm-dd"));
    	cellStyle.setAlignment(CellStyle.ALIGN_LEFT); //左对齐
    	Font font = book.createFont();
    	font.setFontName("宋体");
    	font.setFontHeightInPoints((short)10);
	    cellStyle.setFont(font);
    	return cellStyle;
    }
    
    /**
     * 获取与obj值相同的对象
     * @param map
     * @param index 单元格索引
     * @param obj
     * @return
     */
	public static List<String> list(Map<Integer,Map<Integer, String[]>> map, int index, String obj){
		Assert.notNull(obj, "The given obj must not be null!");
		List<String> list = new ArrayList<String>();
		Iterator<Map.Entry<Integer,Map<Integer, String[]>>> sheetIterator = map.entrySet().iterator();
		while(sheetIterator.hasNext()){
			Map.Entry<Integer,Map<Integer, String[]>> sheetEntry = sheetIterator.next();
			Map<Integer, String[]> rowMap = sheetEntry.getValue();
			Iterator<Map.Entry<Integer, String[]>> rowIterator = rowMap.entrySet().iterator();
			while(rowIterator.hasNext()){
				Map.Entry<Integer, String[]> rowEntry = rowIterator.next();
				String[] datas = rowEntry.getValue();
				String item = datas[index];
				if(obj.equals(item)){
					list.add(item);
				}
			}
		}
		list.remove(obj);
		return list;
	}
	
	/**
	 * 删除导入数据验证
	 * @param map
	 * @return
	 */
//	private static ImportResult validateForDel(Map<Integer,Map<Integer, String>> map){
//		ImportResult importResult = new ImportResult();
//		Iterator<Map.Entry<Integer,Map<Integer, String>>> sheetIterator = map.entrySet().iterator();
//		first:while(sheetIterator.hasNext()){
//			Map.Entry<Integer,Map<Integer, String>> sheetEntry = sheetIterator.next();
//			int sheet = sheetEntry.getKey();
//			Map<Integer, String> rowMap = sheetEntry.getValue();
//			Iterator<Map.Entry<Integer, String>> rowIterator = rowMap.entrySet().iterator();
//			while(rowIterator.hasNext()){
//				Map.Entry<Integer, String> rowEntry = rowIterator.next();
//				int row = rowEntry.getKey();
//				String jyzId = rowEntry.getValue();
//				if(StringUtils.isBlank(jyzId)){
//					importResult.setDataErrorInfo(new DataErrorInfo(sheet, row, 0, ErrorType.NULL.getDescription()));
//					break first;
//				}else{
//					boolean flag = DataValidateUtils.validate(DataValidateUtils.POSITIVE_NUMBER_REGEXP, jyzId);
//					if(!flag){
//						importResult.setDataErrorInfo(new DataErrorInfo(sheet, row, 0, ErrorType.ILLEGAL.getDescription()));
//						break first;
//					}
//				}
//			}
//		}
//		return importResult;
//	}
	
	/**
	 * 获取ID集
	 * @param map
	 * @return
	 */
//	public static Map<String, Object> getIDs(InputStream input){
//		Map<Integer,Map<Integer, String>> map = ExcelUtils.readExcel(input);
//		ImportResult importResult = ExcelUtils.validateForDel(map);
//		List<String> ids = new ArrayList<String>();
//		if(importResult.getDataErrorInfo() == null){
//			Iterator<Map.Entry<Integer,Map<Integer, String>>> sheetIterator = map.entrySet().iterator();
//			while(sheetIterator.hasNext()){
//				Map.Entry<Integer,Map<Integer, String>> sheetEntry = sheetIterator.next();
//				Map<Integer, String> rowMap = sheetEntry.getValue();
//				Iterator<Map.Entry<Integer, String>> rowIterator = rowMap.entrySet().iterator();
//				while(rowIterator.hasNext()){
//					Map.Entry<Integer, String> rowEntry = rowIterator.next();
//					ids.add(rowEntry.getValue());
//				}
//			}
//		}
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//		returnMap.put("ids", ids);
//		returnMap.put("importResult", importResult);
//		return returnMap;
//	}

}
