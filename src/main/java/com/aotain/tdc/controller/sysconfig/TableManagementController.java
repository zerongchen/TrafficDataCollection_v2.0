package com.aotain.tdc.controller.sysconfig;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.security.SecurityUtils;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.constant.TableDict;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.dto.common.TableColumnDTO;
import com.aotain.tdc.dto.common.TableColumns;
import com.aotain.tdc.dto.common.TableParam;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.common.TableColumnService;
import com.aotain.tdc.service.common.UserColumnService;
import com.aotain.tdc.service.sysconfig.TableManagementService;

@Controller
@RequestMapping(value="/sysconfig/configmanage/tablemanage")
public class TableManagementController {
	
	@Autowired
	private TableColumnService tableColumnService;
	
	@Autowired
	private UserColumnService userColumnService;
	
	@Autowired
	private TableManagementService tableManagementService;
	
	@RequestMapping(value = "/index")
	@Secured({"ROLE_WELCOME"})
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "/sysconfig/configmanage/tablemanage";
	}
	
	@Secured({"ROLE_SYSCONFIG_CONFIGMANAGE_TABLEMANAGE_QUERY"})
	@RequestMapping(value="/getTableColumnData",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<TableColumns> getTableColumnData(HttpServletRequest request) {
		TableParam tc = new TableParam();
		tc.setOperID(Integer.valueOf(SecurityUtils.getUserId()+""));
		tc.setTableName(TableDict.DIC_TABLECOLUMN.getTableName());
		return tableColumnService.getTableColumns(tc);
	}
	
	@Secured({"ROLE_SYSCONFIG_CONFIGMANAGE_TABLEMANAGE_QUERY"})
	@RequestMapping(value="/initTable",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<TableColumnDTO> initTable(TableColumnDTO dto){
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		tableManagementService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<TableColumnDTO> list = (List<TableColumnDTO>) dto.getResultList();
		PageResult<TableColumnDTO> pageResult = new PageResult<TableColumnDTO>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({"ROLE_WELCOME"})
	@RequestMapping(value="/update",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean update(TableColumnDTO dto, HttpServletRequest request){
		MsgBean msg = new MsgBean();
		try{
			int re = tableManagementService.update(dto, request);
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
		}
		return msg;
	}
	
	@Secured({"ROLE_WELCOME"})
	@RequestMapping(value="/updateUp",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateUp(TableColumnDTO dto, HttpServletRequest request){
		MsgBean msg = new MsgBean();
		try{
			int re = tableManagementService.updateUp(dto, request);
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
		}
		return msg;
	}
	
	@Secured({"ROLE_WELCOME"})
	@RequestMapping(value="/updateDown",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateDown(TableColumnDTO dto, HttpServletRequest request){
		MsgBean msg = new MsgBean();
		try{
			int re = tableManagementService.updateDown(dto, request);
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
		}
		return msg;
	}
	
	@Secured({"ROLE_WELCOME"})
	@RequestMapping(value="/selectTableNames",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<HashMap<String, Object>> selectTableNames(TableColumnDTO dto, HttpServletRequest request){
		return  tableManagementService.selectTableNames(dto);
	}
}
