package com.aotain.tdc.controller.strategy;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.security.UserDetail;
import com.aotain.common.security.Userbean;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.strategy.TrafficStatStrategyManagementService;

@Controller
@RequestMapping(value="/strategy/management/trafficStatStrategyManagement")
public class TrafficStatStrategyManagementController {
	@Autowired
	private TrafficStatStrategyManagementService trafficStatStrategyManagementService;
	
	private MsgBean msg = new MsgBean();
	
	@RequestMapping(value="/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/strategy/trafficStatStrategyManagement/trafficStatStrategy";
	}
	
	@Secured({"ROLE_TRAFFIC_COLLECTION_MANAGEMENT_QUERY"})
	@RequestMapping(value = "/initTable", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResult<HashMap<String, String>> initTable(BaseDTO dto) throws ParseException{
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		trafficStatStrategyManagementService.getTableColumnsData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>> (list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({ "ROLE_TRAFFIC_COLLECTION_MANAGEMENT_ADD" })
	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean insert(BaseDTO dto, HttpServletRequest request) throws ParseException {
		try {
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			dto.setQueryCreateUser(userbean.getUserName());
			trafficStatStrategyManagementService.insert(dto, request);
			msg.setFlag(0);
			msg.setMsg("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("添加失败！");
		}
		return msg;
	}
	@Secured({ "ROLE_TRAFFIC_COLLECTION_MANAGEMENT_EDIT" })
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean update(BaseDTO dto, HttpServletRequest request) throws ParseException {
		try {
			trafficStatStrategyManagementService.update(dto, request);
			msg.setFlag(0);
			msg.setMsg("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
	
	
	@Secured({ "ROLE_TRAFFIC_COLLECTION_MANAGEMENT_DEL" })
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delete(BaseDTO dto, HttpServletRequest request) throws ParseException {
		try {
			trafficStatStrategyManagementService.delete(dto, request);
			msg.setFlag(0);
			msg.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("删除失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_TRAFFIC_COLLECTION_MANAGEMENT_START", "ROLE_TRAFFIC_COLLECTION_MANAGEMENT_STOP" })
	@RequestMapping(value = "/updateStatus", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateStatus(BaseDTO dto) throws ParseException {
		try {
			trafficStatStrategyManagementService.updateStatus(dto);
			msg.setFlag(0);
			msg.setMsg("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
	
}

