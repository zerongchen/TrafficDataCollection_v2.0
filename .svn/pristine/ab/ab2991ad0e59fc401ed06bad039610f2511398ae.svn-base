package com.aotain.tdc.controller.serviceConfiguration;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DicBizInfo;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.serviceConfiguration.BizManageService;
import com.aotain.tdc.service.serviceConfiguration.BusinessInformationService;

@Controller
@RequestMapping(value = "/serviceConfiguration/bizManage")
public class BizManageController {
	
	@Autowired
	private BizManageService bizManageService;

	@Autowired
	private BusinessInformationService businessInformationService;
	
	@Autowired
	private CommonCache commonCache;
	
	private MsgBean msg = new MsgBean();
	
	@RequestMapping("/index")
	@Secured({ "ROLE_WELCOME" })
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "serviceConfiguration/serviceArchitecture";
	}
	
	
	@RequestMapping("/detail")
	@Secured({ "ROLE_WELCOME" })
	public String detail(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI().replace("/detail.", "/index."));
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		request.setAttribute("bizId", request.getParameter("bizId"));
		return "serviceConfiguration/serviceArchitectureDetail";
	}	
	
	// 查询数据
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/initStaTable", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initStaTable(BaseDTO dto) {
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		dto.setQueryIds("");
		dto.setQueryResourceMark(0);
		businessInformationService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	// 查询数据
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/getBizInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public DicBizInfo getBizInfo(DicBizInfo dto) {
		DicBizInfo result = bizManageService.getBizInfo(dto);
		return result;
	}
		
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/updateBizParent", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateBizParent(BaseDTO dto) throws ParseException {
		try {
				if(bizManageService.updateBizParent(dto) >= 0){
					msg.setFlag(0);
					msg.setMsg("更新成功！");
				}else{
					msg.setFlag(-1);
					msg.setMsg("更新失败");
				}
				commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
	
	@RequestMapping(value = "/insertBizInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@Secured({ "ROLE_WELCOME" })
	public MsgBean insertBizInfo(DicBizInfo bizinfo) throws ParseException {
		try{
			int result = bizManageService.insertBizInfo(bizinfo);
			if(result == 2){
				msg.setFlag(-2);
				msg.setMsg("新增失败，已存在相同业务名称！");				
			}
			else if(result == 3){
				msg.setFlag(-3);
				msg.setMsg("新增失败，已存在相同的配置！");
			}			
			else{
				msg.setFlag(0);
			    commonCache.refreshCache();
			    msg.setMsg("新增成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("新增失败！");
		}
		return msg;
	}
	
	@RequestMapping(value = "/updateBizInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@Secured({ "ROLE_WELCOME" })
	public MsgBean insertBizConfig(DicBizInfo bizinfo) throws ParseException {
		try{
			int result = bizManageService.updateBizInfo(bizinfo);
			if(result == 2){
				msg.setFlag(-2);
				msg.setMsg("操作失败，已存在相同业务名称！");				
			}
			else if(result == 3){
				msg.setFlag(-3);
				msg.setMsg("操作失败，已存在相同的配置！");
			}			
			else{
				msg.setFlag(0);
				commonCache.refreshCache();
				if(bizinfo.getFlag() == 2)
					msg.setMsg("修改成功！");
				else
					msg.setMsg("新增成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("修改失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/delBizInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delBizInfo(DicBizInfo bizinfo) throws ParseException {
		try {
			bizManageService.delBizInfo(bizinfo);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("删除失败！");
		}
		return msg;
	}
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/delBizInfos", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delBizInfos(DicBizInfo bizinfo) throws ParseException {
		try {
			bizManageService.delBizInfos(bizinfo);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("删除失败！");
		}
		return msg;
	}
}
