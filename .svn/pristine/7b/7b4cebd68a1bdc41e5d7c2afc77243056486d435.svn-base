package com.aotain.tdc.controller.businessAnalysis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.util.CommonLog;
import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.service.businessAnalysis.UrlQualityService;
import com.aotain.tdc.service.common.CommonService;

@Controller
@RequestMapping(value="/traffic/businessAnalysis/urlQuality")
public class UrlQualityController {
	
	@Autowired
	private UrlQualityService urlQualityService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/businessAnalysis/urlAnalysis";
	}
	
	@Secured({"ROLE_PRODUCT_ANALYSIS_URL_QUERY"})
	@RequestMapping(value="/initTable",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initTable(BaseDTO dto) throws ParseException{
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		urlQualityService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
	 
	@Secured({"ROLE_PRODUCT_ANALYSIS_URL_EXPORT"})
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportStatistic", method = RequestMethod.POST)
	@ResponseBody
	public void exportStatistic(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response, HttpServletRequest request) throws ParseException {
		try {			
			ExportType exportType = ExportType.valueOf("EXCEL");
			String fileName = ModuleType.PRODUCT_ANALYSIS_URL_STATISTIC.getDescription()
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ exportType.getSuffix();
			if ("FF".equals(commonService.getBrowser(request))) {  
		        // 针对火狐浏览器处理方式不一样了  
		    	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
		    }  else{
		    	fileName = URLEncoder.encode( fileName, "UTF-8");
		    }
			dto.setIsPaging(1);
			dto.setIsCount(0);
			dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
			if(dto.getIsPaging() == 1){
				dto.setStartRow(dto.getOffset());
				dto.setEndRow(dto.getLimit());
			}
			/*yours start*/
			urlQualityService.getTableColumns(dto);
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
