package com.aotain.tdc.service.serviceConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.serviceConfiguration.ServiceArchitectureDao;
import com.aotain.tdc.dto.common.BaseDTO;


@Service("serviceArchitectureService")
public class ServiceArchitectureService {
	
	@Autowired
	ServiceArchitectureDao serviceArchitectureDao;
	
	@Autowired
	CommonCache commonCache;
	
	public int insertCatalog(BaseDTO dto) { 
		int count = serviceArchitectureDao.catalogIsExist(dto);
		if(count == 0){
			int re = serviceArchitectureDao.insertCatalog(dto);
			commonCache.getDicProtoCataLogBeanCache().invalidateAll();
			return re;
		}else{
			return -1;
		}
	}
		
	public int insertClass(BaseDTO dto) { 
		int count = serviceArchitectureDao.classIsExist(dto);
		if(count == 0){
			int re = serviceArchitectureDao.insertClass(dto);
			commonCache.getDicProtoClassBeanCache().invalidateAll();
			return re;
		}else{
			return -1;
		}
	}
	
	public int insertProduct(BaseDTO dto) {
		int count = serviceArchitectureDao.productIsExist(dto);
		if(count == 0){
			int re = serviceArchitectureDao.insertProduct(dto);
			commonCache.getDicProtoProductBeanCache().invalidateAll();
			return re;
		}else{
			return -1;
		}
	}
	
	public int insertModule(BaseDTO dto) { 
		int re = serviceArchitectureDao.insertModule(dto);
		commonCache.getDicProtoModuleBeanCache().invalidateAll();
		return re;
	}
	
	public int updateCatalog(BaseDTO dto) {
		int count = serviceArchitectureDao.catalogIsExist(dto);
		if(count > 0){
			return -1;
		}else{
			return serviceArchitectureDao.updateCatalog(dto);
		}
	}

	public int updateClass(BaseDTO dto) {
		int count = serviceArchitectureDao.classIsExist(dto);
		if(count > 0){
			return -1;
		}else{
			return serviceArchitectureDao.updateClass(dto);
		}
	}
	
	public int updateProduct(BaseDTO dto) {
		int count = serviceArchitectureDao.productIsExist(dto);
		if(count > 0){
			return -1;
		}else{
			return serviceArchitectureDao.updateProduct(dto);
		}
	}
	
	public int updateModule(BaseDTO dto) {
		return serviceArchitectureDao.updateModule(dto);
	}
	
	public void deleteCatalog(BaseDTO dto) {
		serviceArchitectureDao.deleteCatalog(dto);
	}
	
	public void deleteClass(BaseDTO dto) {
		serviceArchitectureDao.deleteClass(dto);
	}
	
	public void deleteProduct(BaseDTO dto) {
		serviceArchitectureDao.deleteProduct(dto);
	}
	
	public void deleteModule(BaseDTO dto) {
		serviceArchitectureDao.deleteModule(dto);
	}
	
}
