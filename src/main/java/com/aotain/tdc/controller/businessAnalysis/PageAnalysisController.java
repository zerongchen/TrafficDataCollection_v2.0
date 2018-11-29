package com.aotain.tdc.controller.businessAnalysis;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.service.businessAnalysis.PageAnalysisService;
import com.aotain.tdc.service.businessAnalysis.TopPageAnalysisService;
import com.aotain.tdc.service.common.CommonService;

@Controller
@RequestMapping(value = "/traffic/businessAnalysis/pageAnalysis")
public class PageAnalysisController {
	@Autowired
	private PageAnalysisService pageAnalysisService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "businessAnalysis/pageAnalysis/pageAnalysis";
	}
	
	@Secured({"ROLE_PRODUCT_ANALYSIS_PAGE_QUERY"})
	@RequestMapping(value="/initTable", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResult<HashMap<String,String>> initTable(BaseDTO dto){
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		pageAnalysisService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({"ROLE_PRODUCT_ANALYSIS_PAGE_EXPORT"})
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportTable", method = RequestMethod.POST)
	@ResponseBody
	public void exportTable(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
    	ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.BUSINESSANALYSIS_TOPPAGEANALYSIS.getDescription()
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
		pageAnalysisService.getTableColumns(dto);
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
	}
}
