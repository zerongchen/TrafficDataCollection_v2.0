package com.aotain.tdc.service.quality;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.CsvWriter;
import com.aotain.common.util.DataSource;
import com.aotain.tdc.dao.quality.CallBillDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.model.common.BaseModel;

@Service("callBillService")
public class CallBillService {
	
	@Autowired
	CallBillDao callBillDao;
    
    @Autowired
    CommonCache commonCache;
    @DataSource(name=DataSource.dataSource2)
	public void getTableColumns(BaseDTO dto) throws IOException {		
		
		List<HashMap<String, Object>> resultList = callBillDao.getTableColumns(dto);

		Date wtfDate = new Date();
		for(int i=0; i < resultList.size(); i++){
			wtfDate.setTime(Long.valueOf(resultList.get(i).get("uistarttime")+""));
			resultList.get(i).put("uistarttime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wtfDate));
			resultList.get(i).put("uiupflow", String.format("%.2f", Double.parseDouble(resultList.get(i).get("uiupflow")+"")/1024/1024 ) );
			resultList.get(i).put("uidownflow", String.format("%.2f", Double.parseDouble(resultList.get(i).get("uidownflow")+"")/1024/1024 ) );
			resultList.get(i).put("uiwtfdelay", resultList.get(i).get("uitcpconsuccdelay"));
			resultList.get(i).put("totalflow", String.format("%.2f",(Double.parseDouble(resultList.get(i).get("uiupflow").toString())+Double.parseDouble(resultList.get(i).get("uidownflow").toString()))/1024/1024 ));
			
			resultList.get(i).put("surl", resultList.get(i).get("stopdomainname"));
		}
		dto.setResultList(resultList);
		dto.setTotalCounts(callBillDao.getTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
	}
	@SuppressWarnings("unchecked")
	@DataSource(name=DataSource.dataSource2)
	public void exportCsv(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response, String fileName) throws IOException {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
		CsvWriter csv = new CsvWriter(System.getProperty("java.io.tmpdir")+fileName);
		csv.writeRecord(baseModel.getFields());
		int limit = 5000;
		dto.setIsPaging(1);
		dto.setStartRow(0);
		dto.setEndRow(limit);
		this.getTableColumns(dto);
		int totalCount = dto.getTotalCounts();
		while(dto.getStartRow()+dto.getEndRow() < totalCount){
			List<HashMap<String, Object>> resultList = (List<HashMap<String, Object>>) dto.getResultList();
			for(HashMap<String, Object> result: resultList){
				String line = "";
				for(String field : baseModel.getFields()){
					line = line + (line.length()==0?"":",") + result.get(field).toString();
				}
				csv.writeRecord(line.split(","));
				csv.flush();
			}
			dto.setStartRow(dto.getStartRow() + dto.getEndRow());
			dto.setEndRow(limit);
			this.getTableColumns(dto);
		}
		List<HashMap<String, Object>> resultList = (List<HashMap<String, Object>>) dto.getResultList();
		for(HashMap<String, Object> result: resultList){
			String line = "";
			for(String field : baseModel.getFields()){
				line = line + (line.length()==0?"":",") + result.get(field).toString();
			}
			csv.writeRecord(line.split(","));
			csv.flush();
		}
		csv.close();
		
		OutputStream out = new BufferedOutputStream(response.getOutputStream());
		Path path = Paths.get(System.getProperty("java.io.tmpdir")+fileName);
		InputStream in = new BufferedInputStream(Files.newInputStream(path));
		byte[] buffer = new byte[8192];
	    int i;
	    while (-1 != (i = in.read(buffer))) {
	    	out.write(buffer, 0, i);
	    }
		out.close();
		in.close();
	}
}
