package com.aotain.tdc.controller.common;

import java.net.URLEncoder;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.authService.UserserviceStub.ServiceReturnDTO;
import com.aotain.common.security.AuthenticationManagerImpl;
import com.aotain.common.security.SecurityUtils;
import com.aotain.common.security.UserDetail;
import com.aotain.common.security.Userbean;
import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.common.util.StringUtil;
import com.aotain.common.util.Tools;
import com.aotain.tdc.service.common.LoginService;

@Controller
public class LoginController {
	private AuthenticationManager am = new AuthenticationManagerImpl();
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login", produces="text/html;charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest request){
		try{
			String sk = request.getParameter("sk") == null ? "" : request.getParameter("sk");
			String ck = Tools.getRsaClearText(sk);
			String [] arr = ck.split("\\*\\|\\*"); 
			String userName = arr[0].substring(4);
			String password = arr[2];
			String checkCode = arr[1];
			
			String errorMessage = "";
			HttpSession session = request.getSession(false);
			String code = null;
			if( checkCode == null || checkCode.length() < 1){
				errorMessage = "请填写完整登陆信息";
				return errorMessage;
			}
			if(session.getAttribute("checkCode") == null){
				errorMessage = "验证码失效";
				return errorMessage;
			}
			code = session.getAttribute("checkCode").toString();
			if(!checkCode.equalsIgnoreCase(code)){
				errorMessage = "验证码错误";
				return errorMessage;
			}

			Userbean userbean = new Userbean();
			userbean.setUserName(userName);
			userbean.setPassword(password);
			userbean.setClientIP(Tools.getRemortIP(request));

			ServiceReturnDTO serviceReturnDTO = LoginInfoUtil.CheckUserDetailLogin(userbean.getUserName(), userbean.getPassword(), userbean.getClientIP());
			errorMessage = StringUtil.trim(serviceReturnDTO.getMessage());
			if(StringUtil.isEmptyString(errorMessage)){
				
				try {
					loginService.saveSecurityLogin(userbean, serviceReturnDTO, userName, password, request, am);
				} catch (AuthenticationException e) {
					errorMessage = e.getMessage();
				}
			}
			return errorMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return "认证失败！";
		}
	}	
	
	@RequestMapping(value = "/welcome")
	@Secured({"ROLE_WELCOME"})
	public String welcome(HttpServletRequest request) throws ParseException {
		String[] menus = LoginInfoUtil.getMenu(null);
		if(menus[2] != null && menus[2].length() > 0){
			return "forward:"+menus[2];
		}else{
			return "forward:/";
		}
	}

	@RequestMapping(value = "/go2passport")
	@Secured({"ROLE_WELCOME"})
	public String authLogin(HttpServletRequest request) throws Exception {
		
		String strAuthUrl = "";
		
        if (request.getParameter("AuthUrl") != null){
            strAuthUrl = request.getParameter("AuthUrl");
        }else{
        	strAuthUrl = LocalConfig.getInstance().getPassPort();
        }
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetail userDetail = (UserDetail)authentication.getPrincipal();
        Long userid = userDetail.getUserId();       
        String userName = userDetail.getUsername();
        if (userid == 0)
        {
        	return "forward:/login.do";
        }
        else
        {
            String token = StringUtil.getToken(userid, userName);
            return "redirect:http://"+strAuthUrl + "?token=" + URLEncoder.encode(token, "UTF-8");
        }
	}
	
	@RequestMapping(value = "/path/*")
	@Secured({"ROLE_WELCOME"})
	public String sysconfig(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		if(menus[2] != null && menus[2].length() > 0){
			return "forward:"+menus[2];
		}else{
			return "forward:/";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		loginService.LogOut(request);
		request.getSession().invalidate();
		return "forward:/";
	}
}
