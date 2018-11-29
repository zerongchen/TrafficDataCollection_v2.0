package com.aotain.tdc.aop;

import java.util.HashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aotain.common.security.UserDetail;
import com.aotain.common.security.Userbean;
import com.aotain.common.util.LocalConfig;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DpiClogBean;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.traffic.TrafficRecoveryManagementService;

public class ClogAdviceBefore implements MethodInterceptor {
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	TrafficRecoveryManagementService trafficRecoveryManagementService;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		ClogAop clogAop = invocation.getMethod().getAnnotation(ClogAop.class);
		BaseDTO baseDTO = (BaseDTO) invocation.getArguments()[0];
		for (String strategyId : baseDTO.getQueryStrategyId().split(",")) {
			BaseDTO queryDto = new BaseDTO();
			queryDto.setQueryStrategyId(strategyId);
			queryDto.setIsPaging(0);
			trafficRecoveryManagementService.getTableColumnsData(queryDto);
			@SuppressWarnings("unchecked")
			HashMap<String, Object> hm = (HashMap<String, Object>) queryDto.getResultList().get(0);
			DpiClogBean clogBean = new DpiClogBean();
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			clogBean.setCREATE_OPER(userbean.getUserId());
			clogBean.setOP_CODE(LocalConfig.getInstance().getOpCodeRe());
			clogBean.setRECID1(Integer.parseInt(hm.get("STRATEGY_ID").toString()));
			clogBean.setRECID2(Integer.parseInt(hm.get("MESSAGE_SEQUENCENO").toString()));
			switch (clogAop.opType()) {
			case DEL:
				clogBean.setOP_TYPE(clogAop.opType().getType());
				commonService.insertClog(clogBean);
				break;
			default:
				break;
			}
		}
		Object result = null;
		try {
			result = invocation.proceed();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
