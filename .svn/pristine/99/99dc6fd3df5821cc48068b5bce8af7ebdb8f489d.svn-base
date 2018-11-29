package com.aotain.tdc.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.aotain.common.util.DataSource;
import com.aotain.common.util.DataSourceHolder;

@Component
public class DataSourceExchange implements org.aopalliance.intercept.MethodInterceptor {
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		DataSource dataSource = invocation.getMethod().getAnnotation(DataSource.class);
		/**
		 * 数据源切换
		 */
		if (dataSource == null) {
			DataSourceHolder.setDataSource(DataSource.dataSourceDefault);
		} else {
			DataSourceHolder.setDataSource(dataSource.name());
		}
		Object result = null;
		try {
			result = invocation.proceed();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/**
		 * 各种日志
		 */
		/*
		System.out.println(invocation.getMethod().getName());
		ClogAop clogAop = invocation.getMethod().getAnnotation(ClogAop.class);
		if(clogAop != null){
			ServiceReturnDTO serviceReturnDTO = (ServiceReturnDTO) object[1];
			Userbean userbean = LoginInfoUtil.userInfoXML2Model(serviceReturnDTO.getUserinfoxml());
			HttpServletRequest request = (HttpServletRequest)object[4];
			System.out.println(clogAop.opType().getType());
			switch(clogAop.opType()){
			case LOGIN:
				LogDTO login = new LogDTO();
				login.setUserId(Long.parseLong(serviceReturnDTO.getUserId()+""));
				login.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				login.setUserName((String) object[2]);
				login.setServerName(request.getServerName());
				login.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
				login.setRealName(userbean.getFullName());
				login.setActionType(1);//操作类型为1，登录
				loginLogService.insertLoginLog(login);
				break;
			case LOGOUT:
				LogDTO logout = new LogDTO();
				logout.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				logout.setUserName((String) object[2]);
				logout.setUserId(Long.parseLong(serviceReturnDTO.getUserId()+""));
				logout.setServerName(request.getServerName());
				logout.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
				logout.setRealName(userbean.getFullName());
				logout.setActionType(2);//操作类型为2，退出
				loginLogService.insertLoginLog(logout);
				break;
			case OPERATE_ADD:
				LogDTO opAdd = new LogDTO();
				opAdd.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				opAdd.setUserName((String) object[2]);
				opAdd.setServerName(request.getServerName());
				opAdd.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
				opAdd.setRealName(userbean.getFullName());
				opAdd.setOpModule(9999999);
				opAdd.setActionType(1);//操作类型为1，添加
				operateLogService.insertOperateLog(opAdd);
				break;
			default:
				break;
			}
		}*/
		return result;
	}

}
