package com.aotain.trafficDataCollection.test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.KPIUtil;
import com.aotain.common.util.SpringUtil;
import com.aotain.tdc.constant.CacheType;
import com.aotain.tdc.constant.KPIType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.KPIBean;
import com.aotain.tdc.dto.common.SelectorBean;
import com.aotain.tdc.service.sysconfig.ThresholdManagementService;

public class KPITest {
	public static void main(String[] args) throws ExecutionException {
		ThresholdManagementService dao = (ThresholdManagementService) SpringUtil.getApplicationContext().getBean("thresholdManagementService");
		CommonCache commonCache = (CommonCache) SpringUtil.getApplicationContext().getBean("commonCache");
		KPIBean kpiBean = new KPIBean();
		kpiBean.setClientDelay(800);
		kpiBean.setDownRetransPercent(40);
		kpiBean.setRespDelay(300);
		kpiBean.setServerDelay(68);
		kpiBean.setSuccessPercent(70);
		kpiBean.setTimeoutPercent(40);
		kpiBean.setUpRetransPercent(70);
		KPIType kpiType = KPIType.AREA_KPI;
		BaseDTO dto = new BaseDTO();
		dto.setIsPaging(0);
		dao.getTableColumns(dto);
		List<SelectorBean> kpiConfigList = commonCache.getDicArrayListCache().get(CacheType.KPICONFIG);
		System.out.println(String.format("%.2f", KPIUtil.computeScore(kpiBean, kpiType, kpiConfigList)));
	}
}
