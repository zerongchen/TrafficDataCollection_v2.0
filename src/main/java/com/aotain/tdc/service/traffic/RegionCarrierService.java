package com.aotain.tdc.service.traffic;

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
import com.aotain.tdc.dao.traffic.RegionCarrierDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("regionCarrierService")
public class RegionCarrierService {
	
	 @Autowired
	 CommonCache commonCache;
	
	 @Autowired
	 RegionCarrierDao regionCarrierDao;

	    public int getParticle(BaseDTO dto){
	    	int particle = 0 ;
			switch (dto.getQueryTableName().substring(dto.getQueryTableName().lastIndexOf("_")+1)){
				case "MIN":particle = 60 * 5;break;
				case "H":particle = 60 * 60 ;break;
				case "D":particle = 60 * 60 * 24;break;
				case "M":particle = 60 * 60 * 30;break;
			}
			return particle;
	    }
	    
		public void getTableColumns(BaseDTO dto) {
			dto.setResultList(regionCarrierDao.getTableColumns(dto));
			List<HashMap<String,Object>> listTimeParticleCounts  = regionCarrierDao.getTimeParticleCounts(dto);
			int particle = getParticle(dto) ;
			for(int i = 0; i < dto.getResultList().size(); i++){
				@SuppressWarnings("unchecked")
				HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
				int timeParticleCounts = 0;
				
				String [] pcids  = result.get("PC_ID").toString().split("_");
				String provinceId = pcids[0];
				String carrierId = pcids[1];
				for(HashMap<String,Object> hashmap : listTimeParticleCounts){  
		            if(hashmap.get("PC_ID").toString().equals(provinceId+"_"+carrierId)){  
		            	timeParticleCounts = Integer.parseInt(hashmap.get("COUNTNUMS")+""); 
		            }  
		        }  
				try{
					result.put("PC_NAME", commonCache.getDicSysPositionBeanCache().get(Long.parseLong(pcids[0])).getPositionName()+commonCache.getDicCarrierBeanCache().get(Long.parseLong(pcids[1])).getCarrierName());
					result.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_RATE").toString())/(particle*timeParticleCounts)));
					result.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOWUP_RATE").toString())/(particle*timeParticleCounts)));
					result.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOWDN_RATE").toString())/(particle*timeParticleCounts)));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			dto.setTotalCounts(regionCarrierDao.getTableColumnsTotalCounts(dto));
		}
		
		@SuppressWarnings("rawtypes")
		public void getTableTrendColumns(BaseDTO dto) throws ExecutionException {
			//if(!dto.getQueryDetailsIdStr().equals("0")){
			//	String [] pcids = dto.getQueryDetailsIdStr().split("_");
			//	dto.setQueryPosition(pcids[0]);
			//	dto.setQueryCarriers(pcids[1]);
			//}
			int particle = getParticle(dto) ;
			List<HashMap<String,Object>> listMapTimeP1  = regionCarrierDao.getTimeParticleList(dto);
			List<HashMap<String,Object>> listMapTimeP2 = new LinkedList<HashMap<String,Object>>();
			List<HashMap<String,Object>> listMap = new LinkedList<HashMap<String,Object>>();
			Set<HashMap> setMap = new HashSet<HashMap>();  
			for(HashMap<String,Object> hashmap : listMapTimeP1){  
	            if(setMap.add(hashmap)){  
	            	listMapTimeP2.add(hashmap);  
	            }  
	        }  
			for(HashMap<String,Object> hashmap : listMapTimeP2){  
				long flowup = 0;
				long flowdn = 0;
				long flow = 0;
				String rtime = hashmap.get("R_STATTIME").toString();
				HashMap<String,Object> map = new HashMap<String, Object>();  
				List<HashMap<String,Object>> listPFS = regionCarrierDao.getFlowTrendByIdList(dto);
				for(int i = 0; i < listPFS.size(); i++){
					HashMap<String, Object> result = listPFS.get(i);
					if(rtime.equals(result.get("R_STATTIME").toString())){
						flowup += Long.parseLong(result.get("FLOW_UP").toString());
						flowdn += Long.parseLong(result.get("FLOW_DN").toString());
						flow += Long.parseLong(result.get("FLOW").toString());
					}
				}
				map.put("R_STATTIME", StringUtil.formatDateString(rtime));
				map.put("FLOW_UP",String.format("%.2f",Double.parseDouble(flowup+"")/1024/1024/1024));
				map.put("FLOW_DN",String.format("%.2f",Double.parseDouble(flowdn+"")/1024/1024/1024));
				map.put("FLOW",String.format("%.2f",Double.parseDouble(flow+"")/1024/1024/1024));
				map.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(flow+"")/particle/1024/1024*8));
				map.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(flowup+"")/(particle)/1024/1024*8));
				map.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(flowdn+"")/(particle)/1024/1024*8));
				listMap.add(map);
			}
			dto.setResultList(listMap);
		}
}
