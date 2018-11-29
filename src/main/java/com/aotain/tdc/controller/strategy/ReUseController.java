package com.aotain.tdc.controller.strategy;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.dto.common.TableColumnDTO;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.strategy.ReUseService;

@Controller
@RequestMapping(value="/strategy/management/dataReuse")
public class ReUseController {
	
	@Autowired
	private ReUseService reUseService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/strategy/management/dataReuse";
	}
	
	@Secured({"ROLE_DATA_REUSE_QUERY"})
	@RequestMapping(value = "/initTable", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResult<HashMap<String, Object>> initTable(BaseDTO dto) throws ParseException, NumberFormatException, ExecutionException{
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		reUseService.getTableColumnsData(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) dto.getResultList();
		PageResult<HashMap<String, Object>> pageResult = new PageResult<HashMap<String, Object>> (list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({"ROLE_DATA_REUSE_QUERY"})
	@RequestMapping(value = "/getPolicyStatData", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResult<HashMap<String, Object>> getPolicyStatData(int strategyType, long strategyId) throws ParseException, NumberFormatException, ExecutionException{
		List<HashMap<String, Object>> result = reUseService.getPolicyStatData(strategyType, strategyId);
		PageResult<HashMap<String, Object>> pageResult = new PageResult<HashMap<String, Object>> (result, result.size());
		return pageResult;
	}
	
	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_DATA_REUSE_EXPORT" })
	@RequestMapping(value = "/export", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void export(BaseModel<HashMap<String, String>> baseModel, TableColumnDTO dto, HttpServletResponse response,
			HttpServletRequest request) throws ParseException, IOException, NumberFormatException, ExecutionException {
		ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.DATA_REUSE.getDescription()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + exportType.getSuffix();
		if ("FF".equals(commonService.getBrowser(request))) {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		dto.setIsPaging(0);
		dto.setIsCount(0);
		/* yours start */
		reUseService.getTableColumnsData(dto);
		/* yours end */
		baseModel.setExportType(exportType.ordinal());
		baseModel.setDatas((List<HashMap<String, String>>) dto.getResultList());
		baseModel.setOs(response.getOutputStream());
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>) dto.getResultList(),
				response);
	}
	
	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_DATA_REUSE_EXPORT" })
	@RequestMapping(value = "/exportLayer", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void exportLayer(BaseModel<HashMap<String, String>> baseModel, TableColumnDTO dto, HttpServletResponse response,
			HttpServletRequest request) throws ParseException, IOException, NumberFormatException, ExecutionException {
		ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.DATA_REUSE.getDescription()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + exportType.getSuffix();
		if ("FF".equals(commonService.getBrowser(request))) {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		dto.setIsPaging(0);
		dto.setIsCount(0);
		/* yours start */
		dto.setResultList(reUseService.getPolicyStatData(dto.getQueryStrategyType(), Long.parseLong(dto.getQueryStrategyId())));
		/* yours end */
		baseModel.setExportType(exportType.ordinal());
		baseModel.setDatas((List<HashMap<String, String>>) dto.getResultList());
		baseModel.setOs(response.getOutputStream());
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>) dto.getResultList(),
				response);
	}
}