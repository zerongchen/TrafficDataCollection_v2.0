package com.aotain.tdc.service.serviceConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.security.UserDetail;
import com.aotain.common.security.Userbean;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dao.serviceConfiguration.BizManageDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DicBizConfig;
import com.aotain.tdc.dto.common.DicBizInfo;
import com.aotain.tdc.dto.common.DpiClogBean;
import com.aotain.tdc.service.common.CommonService;


@Service("bizManageService")
public class BizManageService {
	
	@Autowired
	BizManageDao bizManageDao;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	CommonCache commonCache;
	
	public int updateBizParent(BaseDTO dto) {
		//int count = serviceArchitectureDao.catalogIsExist(dto);
		return bizManageDao.updateBizParent(dto);
	}

	public int insertBizInfo(DicBizInfo bizinfo) {
		int d = 0;
		d=  bizManageDao.IsexitBizInfo(bizinfo);
		if(d >= 1) return 2;
		for(DicBizConfig bizconfig : bizinfo.getBizConfigs()){
			d = bizManageDao.IsexitBizconfig(bizconfig, bizinfo.getBizId());
			if(d >= 1) { d=3 ;break;}	
		}
		if(d != 0) return d;
		d = bizManageDao.insertBizInfo(bizinfo);
		for(DicBizConfig bizconfig : bizinfo.getBizConfigs()){
			d = bizManageDao.insertBizconfig(bizconfig, bizinfo.getBizId());
		}
		if(d > 0){
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(30003);
			clogBean.setOP_TYPE(OpType.ADD.getType());
			
			commonService.insertBizClog(clogBean);
		}
		return d;
	}

	public int delBizInfo(DicBizInfo bizinfo) {
		int d = 0;
		//删除该业务下的业务的配置信息
		d += bizManageDao.delSubConfig(bizinfo);
		//删除该业务下的业务
		d += bizManageDao.delSubInfo(bizinfo);
		//删除该业务的配置信息
		d += bizManageDao.delConfig(bizinfo);
		//删除该业务
		d += bizManageDao.delInfo(bizinfo);
		
		if(d > 0){
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(30003);
			clogBean.setOP_TYPE(OpType.DEL.getType());
			
			commonService.insertBizClog(clogBean);
		}
		return d;
	}

	public DicBizInfo getBizInfo(DicBizInfo dto) {
		DicBizInfo info = bizManageDao.getBizInfo(dto);
		info.setBizConfigs(bizManageDao.getBizConfigs(dto));
		return info;
	}

	public int updateBizInfo(DicBizInfo bizinfo) {
		int d = 0;
		//更新业务信息
		if( bizinfo.getBizName() != null && bizinfo.getBizName().length() > 0 ){
			d=  bizManageDao.IsexitBizInfo(bizinfo);
			if(d >= 1) return 2;
			else
			   d += bizManageDao.updateBizInfo(bizinfo);
		}
		//更新业务配置信息
		if(bizinfo.getBizConfigs() == null) return d;
		for(DicBizConfig bizConfig : bizinfo.getBizConfigs()){
		  d = bizManageDao.IsexitBizconfig(bizConfig, bizinfo.getBizId());
		   if(d >= 1) { d=3 ;break;}	
		}
		if(d != 0) return d;
		for(DicBizConfig bizConfig : bizinfo.getBizConfigs()){
			//--如果存在，更新
			if(bizConfig.getId() > 0){
				d += bizManageDao.updateBizConfig(bizConfig);
			}else{
				//--如果不存在，添加
				d += bizManageDao.insertBizconfig(bizConfig, bizinfo.getBizId());
			}
		}
		if(d > 0){
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(30003);
			clogBean.setOP_TYPE(OpType.MOD.getType());
			
			commonService.insertBizClog(clogBean);
		}
		return d;
	}

	public int delBizInfos(DicBizInfo bizinfo) {
		int d = 0;
		for(String id : bizinfo.getBizIds().split(",")){
			d += bizManageDao.delConfigById(Integer.parseInt(id));
		}
		
		if(d > 0){
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(30003);
			clogBean.setOP_TYPE(OpType.DEL.getType());
			
			commonService.insertBizClog(clogBean);
		}
		return d;
	}
}
