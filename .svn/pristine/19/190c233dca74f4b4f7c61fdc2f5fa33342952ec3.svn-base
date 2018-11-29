package com.aotain.tdc.controller.quality;

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
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.quality.CallBillService;

@Controller
@RequestMapping(value="/quality/userQualityAnalysis/callBill")
public class CallBillController {
	
	@Autowired
	private CallBillService callBillService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/quality/userQualityAnalysis/callBillQuery";
	}
	
	@Secured({"ROLE_BILL_BACKTRACKING_QUERY"})
	@RequestMapping(value="/initTable",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initTable(BaseDTO dto) throws ParseException, IOException{
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		dto.setQueryHour(dto.getQueryStartTime().replace("/", "").split(" ")[1]);
		dto.setQueryStartTime(dto.getQueryStartTime().replace("/", "").split(" ")[0]);
		callBillService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({"ROLE_BILL_BACKTRACKING_EXPORT"})
	@RequestMapping(value = "export", method = RequestMethod.POST)
	@ResponseBody
	public void exportStatistic(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response, HttpServletRequest request) throws ParseException, IOException {
		try {
			String fileName = ModuleType.QUALITY_CALLBILL.getDescription()
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ ".csv";
			if ("FF".equals(commonService.getBrowser(request))) {  
	        	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
	        }  else{
	        	fileName = URLEncoder.encode( fileName, "UTF-8");
	        }
			dto.setQueryHour(dto.getQueryStartTime().replace("/", "").split(" ")[1]);
			dto.setQueryStartTime(dto.getQueryStartTime().replace("/", "").split(" ")[0]);
			callBillService.exportCsv(baseModel, dto, response, fileName);
		} catch (UnsupportedEncodingException e) {
			CommonLog.jspLog.error(e);
		}
	}
}
