package com.aotain.tdc.controller.strategy;

import java.io.IOException;
import java.net.URLDecoder;
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
import com.aotain.tdc.service.strategy.AccountManagementService;

@Controller
@RequestMapping(value = "/strategy/management/account")
public class AccountManagementController {

	@Autowired
	private AccountManagementService accountManagementService;

	@Autowired
	private CommonService commonService;

	private MsgBean msg = new MsgBean();

	@RequestMapping(value = "/index")
	@Secured({ "ROLE_WELCOME" })
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/strategy/management/account";
	}

	@Secured({ "ROLE_INTERFACE_USER_MANAGEMENT_QUERY" })
	@RequestMapping(value = "/initTable", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initTable(BaseDTO dto) throws ParseException {
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		accountManagementService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list,
				dto.getTotalCounts());
		return pageResult;
	}

	@Secured({ "ROLE_INTERFACE_USER_MANAGEMENT_ADD" })
	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean insert(BaseDTO dto, HttpServletRequest request) throws ParseException {
		try {
			Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAccount();
			dto.setQueryCreateUser(userbean.getUserName());
			dto.setQuerySystemId(URLDecoder.decode(dto.getQuerySystemId() == null ? "" : dto.getQuerySystemId(), "utf-8"));
			dto.setQuerySystemId(URLDecoder.decode(dto.getQuerySystemId() == null ? "" : dto.getQuerySystemId(), "utf-8"));
			dto.setQueryContact(URLDecoder.decode(dto.getQueryContact() == null ? "" : dto.getQueryContact(), "utf-8"));
			dto.setQueryContact(URLDecoder.decode(dto.getQueryContact() == null ? "" : dto.getQueryContact(), "utf-8"));
			BaseDTO one = new BaseDTO();
			one.setQueryUserName(dto.getQueryUserName());
			one.setQuerySystemId(dto.getQuerySystemId());
			int count = accountManagementService.isExsit(one);
			if (count > 0) {
				msg.setFlag(1);
				msg.setMsg("该帐号已存在！");
			} else {
				accountManagementService.insert(dto,request);
				msg.setFlag(0);
				msg.setMsg("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("添加失败！");
		}
		return msg;
	}

	@Secured({ "ROLE_INTERFACE_USER_MANAGEMENT_DEL" })
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delete(BaseDTO dto,HttpServletRequest request) throws ParseException {
		try {
			accountManagementService.delete(dto, request);
			msg.setFlag(0);
			msg.setMsg("");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("删除失败！");
		}
		return msg;
	}

	@Secured({ "ROLE_INTERFACE_USER_MANAGEMENT_EDIT" })
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean update(BaseDTO dto, HttpServletRequest request) throws ParseException {
		try {
			if (dto.getQuerySystemId() != null) {
				dto.setQuerySystemId(URLDecoder.decode(dto.getQuerySystemId(), "utf-8"));
				dto.setQuerySystemId(URLDecoder.decode(dto.getQuerySystemId(), "utf-8"));
			}
			if (dto.getQueryContact() != null) {
				dto.setQueryContact(URLDecoder.decode(dto.getQueryContact(), "utf-8"));
				dto.setQueryContact(URLDecoder.decode(dto.getQueryContact(), "utf-8"));
			}
			accountManagementService.update(dto, request);
			msg.setFlag(0);
			msg.setMsg("");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("更新失败！");
		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_INTERFACE_USER_MANAGEMENT_EXPORT" })
	@RequestMapping(value = "/export", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void export(BaseModel<HashMap<String, String>> baseModel, TableColumnDTO dto, HttpServletResponse response,
			HttpServletRequest request) throws ParseException, IOException {
		ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.POLICY_INTERFACE_ACCOUT.getDescription()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + exportType.getSuffix();
		if ("FF".equals(commonService.getBrowser(request))) {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		dto.setIsPaging(0);
		dto.setIsCount(0);
		/* yours start */
		accountManagementService.getTableColumns(dto);
		/* yours end */
		baseModel.setExportType(exportType.ordinal());
		baseModel.setDatas((List<HashMap<String, String>>) dto.getResultList());
		baseModel.setOs(response.getOutputStream());
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>) dto.getResultList(),
				response);
	}
}
