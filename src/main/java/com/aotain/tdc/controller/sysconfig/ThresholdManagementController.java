package com.aotain.tdc.controller.sysconfig;

import java.io.IOException;
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

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.dto.common.TableColumnDTO;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.sysconfig.ThresholdManagementService;

@Controller
@RequestMapping(value="/sysconfig/quota/threshold")
public class ThresholdManagementController {
	
	@Autowired
	private ThresholdManagementService thresholdManagementService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CommonCache commonCache;
	
	private MsgBean msg = new MsgBean();
	
	@RequestMapping(value = "/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/sysconfig/quota/threshold";
	}
	
	@Secured({"ROLE_SYSCONFIG_THRESHOLD_QUERY"})
	@RequestMapping(value="/initTable",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initTable(BaseDTO dto) throws ParseException{
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		thresholdManagementService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({"ROLE_SYSCONFIG_THRESHOLD_EDIT"})
	@RequestMapping(value="/update",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean update(BaseDTO dto) throws ParseException{
		try{
			thresholdManagementService.update(dto);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("");
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("修改失败！");
		}
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	@Secured({"ROLE_SYSCONFIG_THRESHOLD_EXPORT"})
	@RequestMapping(value="/export",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void export(BaseModel<HashMap<String, String>> baseModel, TableColumnDTO dto, HttpServletResponse response, HttpServletRequest request) throws ParseException, IOException{
		ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.WEB_MODULE_QUOTA.getDescription()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ exportType.getSuffix();
		if ("FF".equals(commonService.getBrowser(request))) { 
	    	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
	    }  else{
	    	fileName = URLEncoder.encode( fileName, "UTF-8");
	    }
		dto.setIsPaging(0);
		dto.setIsCount(0);
		/*yours start*/
		thresholdManagementService.getTableColumns(dto);
		/*yours end*/
		baseModel.setExportType(exportType.ordinal());
		baseModel.setDatas((List<HashMap<String, String>>)dto.getResultList());
		baseModel.setOs(response.getOutputStream());
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
	}
}
