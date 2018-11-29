package com.aotain.tdc.controller.traffic;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.dto.common.TableColumnDTO;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.traffic.TrafficRecoveryManagementService;

@Controller
@RequestMapping(value="/strategy/management/trafficRecoveryManagement")
public class TrafficRecoveryManagementController {
	
	@Autowired TrafficRecoveryManagementService trafficRecoveryManagementService;
	@Autowired CommonService commonService;
	
	@RequestMapping(value="/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/strategy/trafficRecoveryManagement/trafficRecovery";
	}
	
	@Secured({"ROLE_TRAFFIC_RESTORE_MANAGEMENT_QUERY"})
	@RequestMapping(value = "/initTable", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResult<HashMap<String, String>> initTable(BaseDTO dto) throws ParseException, NumberFormatException, ExecutionException{
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		trafficRecoveryManagementService.getTableColumnsData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>> (list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({"ROLE_TRAFFIC_RESTORE_MANAGEMENT_EDIT"})
	@RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public MsgBean update(BaseDTO dto, HttpServletRequest request) throws ParseException, NumberFormatException, ExecutionException{
		MsgBean msg = new MsgBean();
		try{
			if(dto.getQueryDestIp()!=null && dto.getQueryDestPort()!=null){
				String[] ips = dto.getQueryDestIp().split(";");
				String[] ports = dto.getQueryDestPort().split(";");
				if(ips.length != ports.length){
					msg.setFlag(-1);
					msg.setMsg("修改失败，目的IP与目标端口长度不一致！");
					return msg;
				}
			}
			trafficRecoveryManagementService.update(dto, request);
			msg.setFlag(0);
			msg.setMsg("修改成功！");
		}catch(Exception e){
			msg.setFlag(-1);
			msg.setMsg("修改失败！");
			e.printStackTrace();
		}
		return msg;
	}
	
	@Secured({"ROLE_TRAFFIC_RESTORE_MANAGEMENT_START", "ROLE_TRAFFIC_RESTORE_MANAGEMENT_STOP"})
	@RequestMapping(value = "/updateStatus", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public MsgBean updateStatus(BaseDTO dto) throws ParseException, NumberFormatException, ExecutionException{
		MsgBean msg = new MsgBean();
		try{
			trafficRecoveryManagementService.updateStatus(dto);
			msg.setFlag(0);
			msg.setMsg("修改成功！");
		}catch(Exception e){
			msg.setFlag(-1);
			msg.setMsg("修改失败！");
			e.printStackTrace();
		}
		return msg;
	}
	
	@Secured({"ROLE_TRAFFIC_RESTORE_MANAGEMENT_DEL"})
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public MsgBean delete(BaseDTO dto, HttpServletRequest request) throws ParseException, NumberFormatException, ExecutionException{
		MsgBean msg = new MsgBean();
		try{
			trafficRecoveryManagementService.delete(dto, request);
			msg.setFlag(0);
			msg.setMsg("删除成功！");
		}catch(Exception e){
			msg.setFlag(-1);
			msg.setMsg("删除失败！");
			e.printStackTrace();
		}
		return msg;
	}
	
	@Secured({"ROLE_TRAFFIC_RESTORE_MANAGEMENT_ADD"})
	@RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public MsgBean insert(BaseDTO dto, HttpServletRequest request) throws ParseException, NumberFormatException, ExecutionException{
		MsgBean msg = new MsgBean();
		try{
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
			dto.setQueryCreateUser(userbean.getUserName());
			if(dto.getQueryDestIp()!=null && dto.getQueryDestPort()!=null){
				String[] ips = dto.getQueryDestIp().split(";");
				String[] ports = dto.getQueryDestPort().split(";");
				if(ips.length != ports.length){
					msg.setFlag(-1);
					msg.setMsg("修改失败，目的IP与目标端口长度不一致！");
					return msg;
				}
			}
			trafficRecoveryManagementService.insert(dto, request);
			msg.setFlag(0);
			msg.setMsg("添加成功！");
		}catch(Exception e){
			msg.setFlag(-1);
			msg.setMsg("新增失败！");
			e.printStackTrace();
		}
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_TRAFFIC_RESTORE_MANAGEMENT_EXPORT" })
	@RequestMapping(value = "/export", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void export(BaseModel<HashMap<String, String>> baseModel, TableColumnDTO dto, HttpServletResponse response,
			HttpServletRequest request) throws ParseException, IOException, NumberFormatException, ExecutionException {
		ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.POLICY_FLOW_RECOVERY_STRATEGY.getDescription()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + exportType.getSuffix();
		if ("FF".equals(commonService.getBrowser(request))) {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		dto.setIsPaging(0);
		dto.setIsCount(0);
		/* yours start */
		trafficRecoveryManagementService.getTableColumnsData(dto);
		/* yours end */
		baseModel.setExportType(exportType.ordinal());
		baseModel.setDatas((List<HashMap<String, String>>) dto.getResultList());
		baseModel.setOs(response.getOutputStream());
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>) dto.getResultList(),
				response);
	}
}
