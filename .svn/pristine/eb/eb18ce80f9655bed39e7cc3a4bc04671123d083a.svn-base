package com.aotain.tdc.service.syslog;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.tdc.dao.syslog.OperateLogDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.LogDTO;

@Service("operateLogService")
public class OperateLogService {
	@Autowired
	OperateLogDao operatelogDao;
	
	public void getTableColumns(BaseDTO dto){
		dto.setResultList(operatelogDao.getTableColumns(dto));
		/*for(int i=0; i<dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("ACTION_TIME", result.get("ACTION_TIME").toString());
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}*/
		dto.setTotalCounts(operatelogDao.getTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null){
			dto.setTotalCounts(0);
		}
	}
	
	public void insertOperateLog(LogDTO log){
		operatelogDao.insertOperateLog(log);
	}
}
