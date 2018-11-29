package com.aotain.trafficDataCollection.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class POITest {
	// 適当なディレクトリに書き換えてください
	static final String INPUT_DIR = "F:/poi/";

	public static void main(String[] args) {
		FileInputStream in = null;
		Workbook wb = null;
		try {
			String xlsxFileAddress = INPUT_DIR + "机房流量趋势报表20161027202625.xlsx";

			in = new FileInputStream(xlsxFileAddress);
			// 共通インターフェースを扱える、WorkbookFactoryで読み込む
			wb = WorkbookFactory.create(in);

			// 全セルを表示する
			int numberOfSheets = wb.getNumberOfSheets();
			for (int i = 0; i < numberOfSheets; i++) {
				for (Row row : wb.getSheetAt(i)) {
					for (Cell cell : row) {
						System.out.print(getCellValue(cell));
						System.out.print(" , ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().getString();
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return cell.getNumericCellValue();
			}
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		default:
			return null;
		}

	}
}
