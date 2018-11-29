package com.aotain.tdc.service.serviceConfiguration;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.serviceConfiguration.BusinessInformationDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.IpPortDTO;
import com.aotain.tdc.service.common.CommonService;


@Service("businessInformationService")
public class BusinessInformationService {
	
	@Autowired
	BusinessInformationDao businessInformationDao;
	 
	@Autowired
	CommonCache commonCache;
	
	@Autowired
	private CommonService commonService;
	    
	public void getTableColumns(BaseDTO dto) {
		dto.setResultList(businessInformationDao.getTableColumns(dto));
		
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				
				result.put("PARENT_NAME", commonCache.getDicBizInfo().get(Long.parseLong(result.get("PARENT_ID")+"")).getBizName());
				if(Integer.parseInt(result.get("PARENT_ID").toString()) == 0 &&
						Integer.parseInt(result.get("RESOURCE_MARK").toString()) == 1)
				result.put("PARENT_NAME","基地资源池业务总览");	
				//result.put("SERVERBUILD_NAME", commonCache.getDicServerBuildBeanCache().get(Long.parseLong(result.get("SERVERBUILD_ID")+"")).getServerBuildName());
				//result.put("SERVERROOM_NAME", commonCache.getDicServerRoomBeanCache().get(Long.parseLong(result.get("SERVERROOM_ID")+"")).getServerRoomName());
				//result.put("SERVERROOM", result.get("SERVERBUILD_NAME")+"-"+result.get("SERVERROOM_NAME"));
				//result.put("SERVERROOM_NAME",result.get("SERVERBUILD_ROOM"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(businessInformationDao.getTableColumnsTotalCounts(dto));
	}
	
	public void getroomColumns(BaseDTO dto) {
		dto.setResultList(businessInformationDao.getroomColumns(dto));			
		dto.setTotalCounts(businessInformationDao.getroomColumnsTotalCounts(dto));
	}
	
	@Transactional
	public int insert(BaseDTO dto) { 
		int operNum = 0;
		//dto.setQueryServiceInfoId(commonService.getWebServiceinfoSeqNextVal());
		//dto.setQueryIpMappingId(commonService.getDicIpmappingSeqNextVal());
		//dto.setQueryIpPortId(commonService.getDicIpportSeqNextVal());
		
		dto.setQueryServiceInfoId(commonService.getSeqNextVal("WEB_SERVICEINFO"));
		dto.setQueryIpMappingId(commonService.getSeqNextVal("DIC_IPMAPPING"));
		dto.setQueryIpPortId(commonService.getSeqNextVal("DIC_IPPORT"));
		
		operNum = businessInformationDao.insert(dto);
		businessInformationDao.insertIpMapping(dto);
		businessInformationDao.insertIpPort(dto);
		return operNum;
	}
		
	@Transactional
	public int update(BaseDTO dto) {
		dto.setQueryServiceInfoId(dto.getQueryId());
		int operNum = 0;
		operNum = businessInformationDao.update(dto);
		businessInformationDao.updateIpMapping(dto);
		businessInformationDao.updateIpPort(dto);
		return operNum;
	}

	@Transactional
	public int delete(BaseDTO dto) {
		int operNum = 0;
		operNum = businessInformationDao.deleteIpPort(dto);
		String [] serviceInfoIdsArray = dto.getQueryIds().split(",");
		for(String serviceInfoId : serviceInfoIdsArray){
			if(businessInformationDao.getIpPortCounts(serviceInfoId) == 0){
				dto.setQueryIds(serviceInfoId);
				businessInformationDao.delete(dto);
				businessInformationDao.deleteIpMapping(dto);
			}
		}
		return operNum;
	}
	
	@Transactional
	public int batInsert(List<BaseDTO> list, List<IpPortDTO> ipportList) {
		int result = 0;
		for(BaseDTO dto: list){
			result += businessInformationDao.insert(dto);
			result += businessInformationDao.insertIpMapping(dto);
		}
		for(BaseDTO dto:ipportList){
			result += businessInformationDao.insertIpPort(dto);
		}
		return result;
	}
}
