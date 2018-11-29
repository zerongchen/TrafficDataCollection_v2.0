package com.aotain.tdc.controller.traffic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.traffic.IpAddressService;

@Controller
@RequestMapping(value = "/traffic/trafficAnalysis/ipAddress")
public class IpAddressController {
	
	@Autowired
	private IpAddressService ipAddressService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "traffic/trafficAnalysis/ipAddress";
	}
	
	 //查询统计数据
    @Secured({"ROLE_IP_ADDRESS_QUERY"})
	@RequestMapping(value="/initStaTable",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initStaTable(BaseDTO dto){
    	dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		ipAddressService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
    
    //查询趋势数据
    @Secured({"ROLE_IP_ADDRESS_QUERY"})
	@RequestMapping(value="/initTrendTable",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initTrendTable(BaseDTO dto) throws ExecutionException{
    	dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
    	ipAddressService.getTableTrendColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, list.size());
		return pageResult;
	}
    
    @Secured({"ROLE_IP_ADDRESS_EXPORT"})
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportStatistic", method = RequestMethod.POST)
	@ResponseBody
	public void exportStatistic(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
    	ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.QUALITY_IPADDRESS_FLOW_STATISTIC.getDescription()
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
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		ipAddressService.getTableColumns(dto);
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
	}
    
    @Secured({"ROLE_IP_ADDRESS_EXPORT"})
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportTrend", method = RequestMethod.POST)
	@ResponseBody
	public void exportTrend(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException, ExecutionException{
    	ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.QUALITY_IPADDRESS_FLOW_TREND.getDescription()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ exportType.getSuffix();
		if ("FF".equals(commonService.getBrowser(request))) {  
            // 针对火狐浏览器处理方式不一样了  
        	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
        }  else{
        	fileName = URLEncoder.encode( fileName, "UTF-8");
        }
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		ipAddressService.getTableTrendColumns(dto);
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
	}
	
}
