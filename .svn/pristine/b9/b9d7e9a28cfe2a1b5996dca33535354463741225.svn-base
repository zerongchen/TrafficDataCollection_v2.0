package com.aotain.tdc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.Signature;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aotain.common.authService.UserserviceStub.ServiceReturnDTO;
import com.aotain.common.security.Userbean;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.annotation.OperatLogAop;
import com.aotain.tdc.dto.common.LogDTO;
import com.aotain.tdc.service.syslog.LoginLogService;
import com.aotain.tdc.service.syslog.OperateLogService;

/**
 * 日志记录，添加、删除、修改方法AOP
 * 
 * @author HotStrong
 * 
 */
@Aspect
@Component
public class LogAspect {

	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private OperateLogService operateLogService;

	/**
	 * 添加业务逻辑方法切入点
	 */
	@Pointcut(" execution(* com.aotain.tdc.service.common..LoginService.saveSecurityLogin(..)) or execution(* com.aotain.tdc.service..*.update(..)) or execution(* com.aotain.tdc.service..*.insert(..)) or execution(* com.aotain.tdc.service..*.get*(..))")
	public void insertServiceCall() {
		
	}

	/**
	 * 管理员添加操作日志(后置通知)
	 * 
	 * @param joinPoint
	 * @param rtv
	 * @throws Throwable
	 */
	@AfterReturning(value = "insertServiceCall()", argNames = "rtv", returning = "rtv")
	public void insertServiceCallCalls(JoinPoint joinPoint, Object rtv)
			throws Throwable {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		System.out.println("===========================" + method.getName() + "==========================test");
		System.out
				.println("=====================================================test");
		System.out
				.println("=====================================================test");
		System.out
				.println("=====================================================test");
		System.out
				.println("=====================================================test");
		System.out
				.println("=====================================================test");
		System.out
				.println("=====================================================test");
		System.out
				.println("=====================================================test");
		Object[] args = joinPoint.getArgs();
		OperatLogAop operateLogAop = methodSignature.getMethod().getAnnotation(OperatLogAop.class);
		ClogAop clogAop = methodSignature.getMethod().getAnnotation(ClogAop.class);
			// LoginService saveSecurity
			ServiceReturnDTO serviceReturnDTO = (ServiceReturnDTO) args[1];
			Userbean userbean = LoginInfoUtil.userInfoXML2Model(serviceReturnDTO.getUserinfoxml());
			HttpServletRequest request = (HttpServletRequest) args[4];
			System.out.println(clogAop.opType().getType());
			if (clogAop != null) {
				switch (clogAop.opType()) {
				case LOGIN:
					LogDTO login = new LogDTO();
					login.setUserId(Long.parseLong(serviceReturnDTO.getUserId() + ""));
					login.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					login.setUserName((String) args[2]);
					login.setServerName(request.getServerName());
					login.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
					login.setRealName(userbean.getFullName());
					login.setActionType(1);// 操作类型为1，登录
					loginLogService.insertLoginLog(login);
					break;
				case LOGOUT:
					LogDTO logout = new LogDTO();
					logout.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					logout.setUserName((String) args[2]);
					logout.setUserId(Long.parseLong(serviceReturnDTO.getUserId() + ""));
					logout.setServerName(request.getServerName());
					logout.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
					logout.setRealName(userbean.getFullName());
					logout.setActionType(2);// 操作类型为2，退出
					loginLogService.insertLoginLog(logout);
					break;
				default:
					break;
				}
			}
			/*if (operateLogAop != null) {
			switch (operateLogAop.opType()) {
			case ADD:
				LogDTO opAdd = new LogDTO();
				opAdd.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				opAdd.setUserName((String) args[2]);
				opAdd.setServerName(request.getServerName());
				opAdd.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
				opAdd.setRealName(userbean.getFullName());
				opAdd.setOpModule(operateLogAop.opModule());
				opAdd.setActionType(1);// 操作类型为1，添加
				operateLogService.insertOperateLog(opAdd);
				break;
			case MOD:
				LogDTO opMod = new LogDTO();
				opMod.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				opMod.setUserName((String) args[2]);
				opMod.setServerName(request.getServerName());
				opMod.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
				opMod.setRealName(userbean.getFullName());
				opMod.setOpModule(operateLogAop.opModule());
				opMod.setActionType(2);// 操作类型为2，修改
				operateLogService.insertOperateLog(opMod);
				break;
			case DEL:
				LogDTO opDel = new LogDTO();
				opDel.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				opDel.setUserName((String) args[2]);
				opDel.setServerName(request.getServerName());
				opDel.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
				opDel.setRealName(userbean.getFullName());
				opDel.setOpModule(operateLogAop.opModule());
				opDel.setActionType(3);// 操作类型为3，删除
				operateLogService.insertOperateLog(opDel);
				break;
			default:
				break;
			}
		}*/
	}
}
