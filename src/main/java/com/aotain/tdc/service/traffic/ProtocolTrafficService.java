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
import com.aotain.tdc.dao.traffic.ProtocolTrafficDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.SelectorBean;
import com.aotain.tdc.service.common.CommonService;

@Service("protocolTrafficService")
public class ProtocolTrafficService {
	
	 @Autowired
	 CommonCache commonCache;
	
    @Autowired
    ProtocolTrafficDao protocolTrafficDao;

    @Autowired
    CommonService commonService;
    
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
		if(dto.getQueryProtocol() == null || dto.getQueryProtocol().length() == 0){
			String protocolStr = "";
			List<SelectorBean>  selBeanList = commonService.selectProtocol(dto.getQueryProtocolType().toString());
			if(selBeanList.size() >0){
				for(SelectorBean  sb: selBeanList ){
					protocolStr += sb.getProtocolId() + ",";
				}
				protocolStr = protocolStr.substring(0,protocolStr.lastIndexOf(","));
			}
			dto.setQueryProtocol(protocolStr);
		}
		dto.setResultList(protocolTrafficDao.getTableColumns(dto));
		int particle = getParticle(dto) ;
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("PROTOCOL_NAME", commonCache.getDicProtocolBeanCache().get(result.get("P_ID")+"_"+dto.getQueryProtocolType()).getProtocolName());
				//result.put("FLOW_PRE",String.format("%.2f", Double.parseDouble(result.get("FLOW").toString())/Double.parseDouble(result.get("FLOW_ALL").toString())*100)); 
				result.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOW_RATE").toString())/(particle*Long.parseLong(result.get("COUNTNUM").toString()))));
				result.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOWUP_RATE").toString())/(particle*Long.parseLong(result.get("COUNTNUM").toString()))));
				result.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(result.get("FLOWDN_RATE").toString())/(particle*Long.parseLong(result.get("COUNTNUM").toString()))));
				result.put("PROTOCOL_TYPE",dto.getQueryProtocolType() == 0?"传输层":"应用层");
				//result.put("FLOW",String.format("%.2f",Double.parseDouble(result.get("FLOW").toString())/1024/1024/1024));
				//result.put("FLOW_UP",String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/1024/1024/1024));
				//result.put("FLOW_DN",String.format("%.2f",Double.parseDouble(result.get("FLOW_DN").toString())/1024/1024/1024));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(protocolTrafficDao.getTableColumnsTotalCounts(dto));
	}
	
	@SuppressWarnings("rawtypes")
	public void getTableTrendColumns(BaseDTO dto) throws ExecutionException {
		if(dto.getQueryDetailsId() != -1){
			dto.setQueryProtocol(dto.getQueryDetailsId()+"");
		}else{
			if(dto.getQueryProtocol() == null || dto.getQueryProtocol().length() == 0){
				String protocolStr = "";
				List<SelectorBean>  selBeanList = commonService.selectProtocol(dto.getQueryProtocolType().toString());
				if(selBeanList.size() >0){
					for(SelectorBean  sb: selBeanList ){
						protocolStr += sb.getProtocolId() + ",";
					}
					protocolStr = protocolStr.substring(0,protocolStr.lastIndexOf(","));
				}
				dto.setQueryProtocol(protocolStr);
			}
		}
		int particle = getParticle(dto) ;
		List<HashMap<String,Object>> listMapTimeP1  = protocolTrafficDao.getTimeParticleList(dto);
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
			List<HashMap<String,Object>> listPFS = protocolTrafficDao.getFlowTrendByIdList(dto);
			for(int i = 0; i < listPFS.size(); i++){
				HashMap<String, Object> result = listPFS.get(i);
				if(rtime.equals(result.get("R_STATTIME").toString())){
					flowup += Long.parseLong(result.get("FLOW_UP").toString());
					flowdn += Long.parseLong(result.get("FLOW_DN").toString());
					flow += Long.parseLong(result.get("FLOW").toString());
				}
			}
			map.put("R_STATTIME",  StringUtil.formatDateString(rtime));
			map.put("FLOW_UP",String.format("%.2f",Double.parseDouble(flowup+"")/1024/1024/1024));
			map.put("FLOW_DN",String.format("%.2f",Double.parseDouble(flowdn+"")/1024/1024/1024));
			map.put("FLOW",String.format("%.2f",Double.parseDouble(flow+"")/1024/1024/1024));
			map.put("FLOW_RATE",String.format("%.2f",Double.parseDouble(flow+"")/particle/1024/1024*8));
			map.put("FLOWUP_RATE",String.format("%.2f",Double.parseDouble(flowup+"")/(particle)/1024/1024*8));
			map.put("FLOWDN_RATE",String.format("%.2f",Double.parseDouble(flowdn+"")/(particle)/1024/1024*8));
			map.put("PROTOCOL_TYPE",dto.getQueryProtocolType() == 0 ? "传输层":"应用层");
			listMap.add(map);
		}
		dto.setResultList(listMap);
	}
}