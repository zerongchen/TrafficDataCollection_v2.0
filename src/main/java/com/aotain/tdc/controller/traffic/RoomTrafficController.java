package com.aotain.tdc.controller.traffic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.util.CommonLog;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.common.ExportService;
import com.aotain.tdc.service.traffic.RoomTrafficService;

@Controller
@RequestMapping(value = "/traffic/trafficMonitor/roomTraffic")
public class RoomTrafficController {
	
	@Autowired
	RoomTrafficService roomTrafficService;
	
	@Autowired
	CommonService commonService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	ExportService exportService;
	
	@RequestMapping(value = "/index")
	@Secured({"ROLE_WELCOME"})
	public String roomTraffic(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "traffic/trafficMonitor/roomTraffic";
	}
	
	@Secured({"ROLE_ROOM_TRAFFIC_EXPORT"})
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportStat", method = RequestMethod.POST)
	@ResponseBody
	public void exportStat(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response, HttpServletRequest request) throws ParseException, ExecutionException {
		try {
			ExportType exportType = ExportType.valueOf("EXCEL");
			String fileName = ModuleType.TRAFFIC_ROOMTRAFFIC_STATISTIC.getDescription()
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ exportType.getSuffix();
			if ("FF".equals(commonService.getBrowser(request))) {  
		        // 针对火狐浏览器处理方式不一样了  
		    	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
		    }  else{
		    	fileName = URLEncoder.encode( fileName, "UTF-8");
		    }
			dto.setIsPaging(0);
			dto.setIsCount(0);
			/*yours start*/
			roomTrafficService.getStatisticTableDatas(dto);
			/*yours end*/
			baseModel.setExportType(exportType.ordinal());
			baseModel.setDatas((List<HashMap<String, String>>)dto.getResultList());
			baseModel.setOs(response.getOutputStream());
			commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
		} catch (UnsupportedEncodingException e) {
			CommonLog.jspLog.error(e);
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
	}
	@Secured({"ROLE_ROOM_TRAFFIC_EXPORT"})
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportTrend", method = RequestMethod.POST)
	@ResponseBody
	public void exportTrend(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response, HttpServletRequest request) throws ParseException {
		try {
			ExportType exportType = ExportType.valueOf("EXCEL");
			String fileName = ModuleType.TRAFFIC_ROOMTRAFFIC_STATISTIC.getDescription()
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ exportType.getSuffix();
			if ("FF".equals(commonService.getBrowser(request))) {  
		        // 针对火狐浏览器处理方式不一样了  
		    	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
		    }  else{
		    	fileName = URLEncoder.encode( fileName, "UTF-8");
		    }
			dto.setIsPaging(0);
			dto.setIsCount(0);
			/*yours start*/
			roomTrafficService.getTrendTableDatas(dto);
			/*yours end*/
			baseModel.setExportType(exportType.ordinal());
			baseModel.setDatas((List<HashMap<String, String>>)dto.getResultList());
			baseModel.setOs(response.getOutputStream());
			commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
		} catch (UnsupportedEncodingException e) {
			CommonLog.jspLog.error(e);
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
	}
}
