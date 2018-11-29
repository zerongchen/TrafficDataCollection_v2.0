package com.aotain.tdc.service.quality;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.dao.quality.ErrorCodeDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("errorCodeService")
public class ErrorCodeService {
	
	 @Autowired
	 CommonCache commonCache;
	
	 @Autowired
	 ErrorCodeDao errorCodeDao;

	   public void getTableColumns(BaseDTO dto) {
			dto.setResultList(errorCodeDao.getTableColumns(dto));
			for(int i = 0; i < dto.getResultList().size(); i++){
				@SuppressWarnings("unchecked")
				HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
				try{
					//result.put("PROTOCOL_NAME", commonCache.getDicProtocolBeanCache().get(result.get("PROTOCOL_ID")+"_"+result.get("PROTOCOL_TYPE")).getProtocolName());
					result.put("PROTOCOL_NAME", commonCache.getDicProtocolBeanCache().get(result.get("PROTOCOL_ID")+"_1").getProtocolName());
					result.put("ERRORCODE_COUNT_PRE",String.format("%.2f", Double.parseDouble(result.get("ERRORCODE_COUNT").toString())/Double.parseDouble(result.get("ERRORCODE_COUNT_ALL").toString())*100)); 
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			dto.setTotalCounts(errorCodeDao.getTableColumnsTotalCounts(dto));
		}
		
		@SuppressWarnings("rawtypes")
		public void getTableTrendColumns(BaseDTO dto) throws ExecutionException {
			List<HashMap<String,Object>> listMapTimeP1  = errorCodeDao.getTimeParticleList(dto);
			List<HashMap<String,Object>> listMapTimeP2 = new LinkedList<HashMap<String,Object>>();
			List<HashMap<String,Object>> listMap = new LinkedList<HashMap<String,Object>>();
			Set<HashMap> setMap = new HashSet<HashMap>();  
			for(HashMap<String,Object> hashmap : listMapTimeP1){  
	            if(setMap.add(hashmap)){  
	            	listMapTimeP2.add(hashmap);  
	            }  
	        }  
			for(HashMap<String,Object> hashmap : listMapTimeP2){  
				long errorcodeCount = 0;
				String rtime = hashmap.get("R_STATTIME").toString();
				HashMap<String,Object> map = new HashMap<String, Object>();  
				List<HashMap<String,Object>> listPFS = errorCodeDao.getFlowTrendByIdList(dto);
				for(int i = 0; i < listPFS.size(); i++){
					HashMap<String, Object> result = listPFS.get(i);
					if(rtime.equals(result.get("R_STATTIME").toString())){
						errorcodeCount += Long.parseLong(result.get("ERRORCODE_COUNT").toString());
					}
				}
				map.put("R_STATTIME",StringUtil.formatDateString(rtime));
				map.put("ERRORCODE_COUNT",String.format("%.2f",Double.parseDouble(errorcodeCount+"")));
				listMap.add(map);
			}
			dto.setResultList(listMap);
		}
}
