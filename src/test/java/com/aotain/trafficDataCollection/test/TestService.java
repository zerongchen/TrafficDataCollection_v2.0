package com.aotain.trafficDataCollection.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.tdc.dao.common.TestDao;
@Service
public class TestService {
	@Autowired
	TestDao testdao;

	public void insertColumn(String tableName, String columnname, int j) {
		testdao.insertColumn(tableName, columnname, j);
	}

	public void insertFlowDerection(long l, int province_id, int carrier_id) {
		testdao.insertFlowDerection(l, province_id, carrier_id);
	}

	public void insertSERVERBUILD_FLOW_H(long l, int serverroom, int classid) {
		testdao.insertSERVERBUILD_FLOW_H(l, serverroom, classid);
	}

	public void insertChinese(String c) {
		testdao.insertChinese(c);
	}
	
}
