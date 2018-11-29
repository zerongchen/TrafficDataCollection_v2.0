package com.aotain.tdc.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.SpringUtil;
import com.aotain.tdc.dao.quartz.SendZabbixDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.service.common.CommonService;

@Service("sendZabbixService")
public class SendZabbixService {
	
	public void getTableColumns(BaseDTO dto) {
		ApplicationContext ac = SpringUtil.getApplicationContext();
		SendZabbixDao sendZabbixDao = (SendZabbixDao)ac.getBean("sendZabbixDao");
		dto.setResultList(sendZabbixDao.getTableColumns(dto));
		/*for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				//result.put("FLOW",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/1024/1024/1024));
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
		//dto.setTotalCounts(sendZabbixDao.getTableColumnsTotalCounts(dto));
	}
}
