package com.aotain.tdc.controller.traffic;

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
import com.aotain.tdc.service.traffic.TrafficOverviewService;

import java.text.ParseException;

@Controller
@RequestMapping("/traffic/trafficOverview")
public class TrafficOverviewController {
	@Autowired
	private TrafficOverviewService trafficOverviewService;
	
	@RequestMapping(value = "/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "traffic/trafficOverview/trafficOverview";
	}
	
	@Secured({"ROLE_PROFILE_QUERY"})
	@RequestMapping(value="/getTrafficTrendData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<HashMap<String, String>> getTrafficTrendData(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		trafficOverviewService.getTrafficTrendData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		return list;
	}
	@Secured({"ROLE_PROFILE_QUERY"})
	@RequestMapping(value="/getServerbuildTrafficData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<HashMap<String, String>> getServerbuildTrafficData(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		trafficOverviewService.getServerbuildTrafficData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		return list;
	}
	@Secured({"ROLE_PROFILE_QUERY"})
	@RequestMapping(value="/getServiceTrafficData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<HashMap<String, String>> getServiceTrafficData(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		trafficOverviewService.getServiceTrafficData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		return list;
	}
	@Secured({"ROLE_PROFILE_QUERY"})
	@RequestMapping(value="/getRegionTrafficData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<HashMap<String, String>> getRegionTrafficData(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		trafficOverviewService.getRegionTrafficData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		return list;
	}
	@Secured({"ROLE_PROFILE_QUERY"})
	@RequestMapping(value="/getCarrierTrafficData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<HashMap<String, String>> getCarrierTrafficData(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		trafficOverviewService.getCarrierTrafficData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		return list;
	}
	@Secured({"ROLE_PROFILE_QUERY"})
	@RequestMapping(value="/getProtocolTrafficData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<HashMap<String, String>> getProtocolTrafficData(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		trafficOverviewService.getProtocolTrafficData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		return list;
	}
	@Secured({"ROLE_PROFILE_QUERY"})
	@RequestMapping(value="/getIPTrafficData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<HashMap<String, String>> getIPTrafficData(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		trafficOverviewService.getIPTrafficData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		return list;
	}
	
}
