package com.aotain.tdc.controller.syslog;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.service.syslog.OperateLogService;

@Controller
@RequestMapping(value = "/syslog/operateLog/operateLog")
public class OperateLogController {
	@Autowired
	private OperateLogService operateLogService;
	
	@RequestMapping("/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "syslog/operateLog";
	}
	@Secured({"ROLE_SYSTEM_OPERATE_LOG_QUERY"})
	@RequestMapping(value="/initTable", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResult<HashMap<String, String>> initTable(BaseDTO dto){
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		operateLogService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult= new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
}
