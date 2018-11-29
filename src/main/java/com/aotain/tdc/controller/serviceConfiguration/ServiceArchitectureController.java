package com.aotain.tdc.controller.serviceConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.serviceConfiguration.BusinessInformationService;
import com.aotain.tdc.service.serviceConfiguration.ServiceArchitectureService;

@Controller
@RequestMapping(value = "/serviceConfiguration/serviceArchitecture")
public class ServiceArchitectureController {
	
	@Autowired
	private ServiceArchitectureService serviceArchitectureService;
	
	@Autowired
	private CommonCache commonCache;
	
	private MsgBean msg = new MsgBean();
	
	@RequestMapping("/index")
	@Secured({"ROLE_QUERY_WEB_SERVICEARCHITECTURE"})
	public String index(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "serviceConfiguration/serviceArchitecture";
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/insertCatalog", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean insertCatalog(BaseDTO dto) throws ParseException {
		try {
			if(serviceArchitectureService.insertCatalog(dto) == -1){
				msg.setFlag(-1);
				msg.setMsg("业务重复");
			}else{
				msg.setFlag(0);
				msg.setMsg("添加成功！");
			}
			commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("添加失败！");
		}
		return msg;
	}

	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/insertClass", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean insertClass(BaseDTO dto) throws ParseException {
		try {
			if(serviceArchitectureService.insertClass(dto) == -1){
				msg.setFlag(-1);
				msg.setMsg("业务重复");
			}else{
				msg.setFlag(0);
				msg.setMsg("添加成功！");
			}
			commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("添加失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/insertProduct", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean insertProduct(BaseDTO dto) throws ParseException {
		try {
			if(serviceArchitectureService.insertProduct(dto) == -1){
				msg.setFlag(-1);
				msg.setMsg("业务重复");
			}else{
				msg.setFlag(0);
				msg.setMsg("添加成功！");
			}
			commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("添加失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/insertModule", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean insertModule(BaseDTO dto) throws ParseException {
		try {
			serviceArchitectureService.insertModule(dto);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("添加失败！");
		}
		return msg;
	}
	
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/updateCatalog", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateCatalog(BaseDTO dto) throws ParseException {
		try {
			if(serviceArchitectureService.updateCatalog(dto) == -1){
				msg.setFlag(-1);
				msg.setMsg("业务重复");
			}else{
				msg.setFlag(0);
				msg.setMsg("更新成功！");
			}
			commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/updateClass", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateClass(BaseDTO dto) throws ParseException {
		try {
			if(serviceArchitectureService.updateClass(dto) == -1){
				msg.setFlag(-1);
				msg.setMsg("业务重复");
			}else{
				msg.setFlag(0);
				msg.setMsg("更新成功！");
			}
			commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/updateProduct", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateProduct(BaseDTO dto) throws ParseException {
		try {
			if(serviceArchitectureService.updateProduct(dto) == -1){
				msg.setFlag(-1);
				msg.setMsg("业务重复");
			}else{
				msg.setFlag(0);
				msg.setMsg("更新成功！");
			}
			commonCache.refreshCache();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-2);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/updateModule", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateModule(BaseDTO dto) throws ParseException {
		try {
			serviceArchitectureService.updateModule(dto);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("更新失败！");
		}
		return msg;
	}
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/deleteCatalog", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean deleteCatalog(BaseDTO dto) throws ParseException {
		try {
			serviceArchitectureService.deleteCatalog(dto);
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
	@RequestMapping(value = "/deleteClass", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean deleteClass(BaseDTO dto) throws ParseException {
		try {
			serviceArchitectureService.deleteClass(dto);
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
	@RequestMapping(value = "/deleteProduct", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean deleteProduct(BaseDTO dto) throws ParseException {
		try {
			serviceArchitectureService.deleteProduct(dto);
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
	@RequestMapping(value = "/deleteModule", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean deleteModule(BaseDTO dto) throws ParseException {
		try {
			serviceArchitectureService.deleteModule(dto);
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
