package com.aotain.tdc.controller.serviceConfiguration;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.serviceConfiguration.BizManageService;
import com.aotain.tdc.service.serviceConfiguration.BusinessArchitectureService;

@Controller
@RequestMapping(value = "/serviceConfiguration/businessArchitecture")
public class BusinessArchitectureController {
	
	@Autowired
	BusinessArchitectureService businessArchitectureService;
	
	@Autowired
	private BizManageService bizManageService;
	
	@Autowired
	private CommonCache commonCache;
	
	private MsgBean msg = new MsgBean();
	
	@RequestMapping(value="/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "serviceConfiguration/businessArchitecture";
	}
	
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/updateBizParent", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateBizParent(BaseDTO dto) throws ParseException {
		try {
				if(bizManageService.updateBizParent(dto) >= 0){
					msg.setFlag(0);
					msg.setMsg("更新成功！");
				}else{
					msg.setFlag(-1);
					msg.setMsg("更新失败");
				}
				commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
}
