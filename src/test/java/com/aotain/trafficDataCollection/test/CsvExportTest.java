package com.aotain.trafficDataCollection.test;

import java.util.HashMap;
import java.util.List;

import com.aotain.common.util.CsvWriter;
import com.aotain.common.util.SpringUtil;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.service.quality.CallBillService;

public class CsvExportTest {

	@SuppressWarnings("unchecked")
    public static void main(String[] arg) throws Exception {
    	CallBillService callBillService = (CallBillService) SpringUtil.getApplicationContext().getBean("callBillService");
		CsvWriter csv = new CsvWriter("F:/test/test.csv");
		BaseDTO dto = new BaseDTO();
		dto.setIsPaging(1);
		dto.setStartRow(0);
		dto.setEndRow(5000);
		int limit = 5000;
		callBillService.getTableColumns(dto);
		int totalCount = dto.getTotalCounts();
		while(dto.getStartRow()+dto.getEndRow() < totalCount){
			System.out.println(dto.getStartRow());
			List<HashMap<String, Object>> resultList = (List<HashMap<String, Object>>) dto.getResultList();
			for(HashMap<String, Object> result: resultList){
				csv.writeRecord(new String[]{result.get("uibilltype").toString(), result.get("partdate").toString()});
				csv.flush();
			}
			dto.setStartRow(dto.getStartRow()+limit);
			dto.setEndRow(limit);
			callBillService.getTableColumns(dto);
		}
		List<HashMap<String, Object>> resultList = (List<HashMap<String, Object>>) dto.getResultList();
		for(HashMap<String, Object> result: resultList){
			csv.writeRecord(new String[]{result.get("uibilltype").toString(), result.get("partdate").toString()});
			csv.flush();
		}
		csv.close();
    }
}
