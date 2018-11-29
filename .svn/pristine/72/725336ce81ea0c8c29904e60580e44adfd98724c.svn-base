package com.aotain.tdc.controller.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.service.common.CommonService;

@Controller
@RequestMapping("/common")
public class CommonViewController {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/{catalogPath}/{menuPath}/{pagePath}/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/quality/productQualityAnalysis/productQuality";
	}
	
	@Secured({ "ROLE_ROOM_TRAFFIC_QUERY" })
	@RequestMapping(value = "/{catalog}/{serviceName}/{methodName}/initTable", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, Object>> initTrendTable(BaseDTO dto, @PathVariable String catalog,
			@PathVariable String serviceName, @PathVariable String methodName) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		Object obj = applicationContext.getBean(serviceName);
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		Method m = Class.forName("com.aotain.tdc.service."+catalog+"."+StringUtil.capitalize(serviceName)).getMethod(methodName, BaseDTO.class);
		m.invoke(obj, dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) dto.getResultList();
		PageResult<HashMap<String, Object>> pageResult = new PageResult<HashMap<String, Object>>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_ROOM_TRAFFIC_QUERY" })
	@RequestMapping(value = "/{fileName}/{catalog}/{serviceName}/{methodName}/export", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void exportStatistic(
			BaseModel<HashMap<String, String>> baseModel,  BaseDTO dto, 
			HttpServletResponse response, HttpServletRequest request,
			@PathVariable String fileName, @PathVariable String catalog,
			@PathVariable String serviceName, @PathVariable String methodName) throws UnsupportedEncodingException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
    		ExportType exportType = ExportType.valueOf("EXCEL");
    		Object obj = applicationContext.getBean(serviceName);
			fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + exportType.getSuffix();
			if ("FF".equals(commonService.getBrowser(request))) {  
		        // 针对火狐浏览器处理方式不一样了  
		    	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
		    }  else{
		    	fileName = URLEncoder.encode( fileName, "UTF-8");
		    }
			dto.setIsPaging(0);
			dto.setIsCount(0);
			dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
			Method m = Class.forName("com.aotain.tdc.service."+catalog+"."+StringUtil.capitalize(serviceName)).getMethod(methodName, BaseDTO.class);
			m.invoke(obj, dto);
			commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
	}
}
