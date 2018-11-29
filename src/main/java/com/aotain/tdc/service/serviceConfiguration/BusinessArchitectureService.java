package com.aotain.tdc.service.serviceConfiguration;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.tdc.dao.serviceConfiguration.BusinessArchitectureDao;


@Service("businessArchitectureService")
public class BusinessArchitectureService {
	
	@Autowired
	private BusinessArchitectureDao businessArchitectureDao;
	
	public List<HashMap<String, Object>> selectBusinessTree(){
		return businessArchitectureDao.selectBusinessTree();
	}
}
