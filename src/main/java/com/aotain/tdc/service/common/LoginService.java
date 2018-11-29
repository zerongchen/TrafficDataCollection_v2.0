package com.aotain.tdc.service.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aotain.common.authService.UserserviceStub.ServiceReturnDTO;
import com.aotain.common.security.SecurityUtils;
import com.aotain.common.security.Userbean;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.common.util.MD5Util;
import com.aotain.common.util.Tools;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dto.common.LogDTO;
import com.aotain.tdc.service.syslog.LoginLogService;

@Service(value="loginService")
public class LoginService {
	@Autowired
	private LoginLogService loginLogService;
	
	
	@ClogAop(opType = OpType.LOGIN)
	public void saveSecurityLogin(Userbean userbean, ServiceReturnDTO serviceReturnDTO, String userName, String password, HttpServletRequest request, AuthenticationManager am) {
		HttpSession session = request.getSession(false);
		userbean = LoginInfoUtil.userInfoXML2Model(serviceReturnDTO.getUserinfoxml());
		userbean.setRightList(LoginInfoUtil.privilegeXML2List(serviceReturnDTO.getUserprivilegexml()));
		userbean.setMenuList(LoginInfoUtil.menuXML2List(serviceReturnDTO.getUserprivilegexml()));
		userbean.setUserName(userName);
		userbean.setPassword(password);
		userbean.setClientIP(Tools.getRemortIP(request));
		userbean.setLastLoginDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		userbean.setServiceReturnDTO(serviceReturnDTO);
		
		session.invalidate();
		Cookie cookie = request.getCookies()[0];
		cookie.setMaxAge(0);
		session = request.getSession(true);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userbean, MD5Util.passwordEncoder(userbean.getPassword()));
		Authentication result = am.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(result);
	}
	/*@ClogAop(opType = OpType.LOGOUT)*/
	public void LogOut(HttpServletRequest request) {
		LogDTO logout = new LogDTO();
		logout.setActionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		logout.setUserName(SecurityUtils.getAuthenticatedUser().getUsername());
		logout.setUserId(Long.parseLong(SecurityUtils.getAuthenticatedUser().getUserId() + ""));
		logout.setServerName(request.getServerName());
		logout.setIpAddressPort(request.getRemoteAddr() + request.getRemotePort());
		logout.setRealName(SecurityUtils.getAuthenticatedUser().getAccount().getFullName());
		logout.setActionType(2);// 操作类型为2，退出
		loginLogService.insertLoginLog(logout);
	}

}
