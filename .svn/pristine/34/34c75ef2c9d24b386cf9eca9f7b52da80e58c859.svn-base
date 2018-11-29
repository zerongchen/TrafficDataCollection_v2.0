package com.aotain.tdc.service.syslog;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.tdc.dao.syslog.LoginLogDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.LogDTO;

@Service("loginLogService")
public class LoginLogService {
	@Autowired
	LoginLogDao loginlogDao;
	
	public void getTableColumns(BaseDTO dto){
		dto.setResultList(loginlogDao.getTableColumns(dto));
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
		dto.setTotalCounts(loginlogDao.getTableColumnsTotalCounts(dto));
		if(dto.getTotalCounts() == null){
			dto.setTotalCounts(0);
		}
	}
	
	public void insertLoginLog(LogDTO log){
		loginlogDao.insertLoginLog(log);
	}
}
