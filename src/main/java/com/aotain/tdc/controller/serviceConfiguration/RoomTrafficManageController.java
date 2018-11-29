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
import com.aotain.tdc.dto.common.DicRoomTrafficConfig;
import com.aotain.tdc.dto.common.DicRoomTrafficInfo;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.serviceConfiguration.BizManageService;
import com.aotain.tdc.service.serviceConfiguration.BusinessInformationService;
import com.aotain.tdc.service.serviceConfiguration.RoomTrafficManageService;

@Controller
@RequestMapping(value = "/serviceConfiguration/roomTraffic")
public class RoomTrafficManageController {
	
	@Autowired
	private RoomTrafficManageService roomtrafficManageService;	
	
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
		return "serviceConfiguration/roomTrafficManage";
	}
	
	@RequestMapping("/detail")	@Secured({ "ROLE_WELCOME" })
	public String detail(HttpServletRequest request){
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI().replace("/detail.", "/index."));
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		request.setAttribute("Id", request.getParameter("Id"));
		return "serviceConfiguration/roomTrafficDetail";
	}
	// 查询数据
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/initStaTable", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initStaTable(BaseDTO dto) {
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		dto.setQueryIds("");		
		businessInformationService.getroomColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	// 查询数据
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/getRoomInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public DicRoomTrafficInfo getRoomInfo(DicRoomTrafficInfo dto) {
		DicRoomTrafficInfo result = roomtrafficManageService.getRoomTrafficInfo(dto);
		return result;
	}
		
	
	@Secured({ "ROLE_WELCOME" })
	@RequestMapping(value = "/updateRoomParent", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean updateRoomParent(BaseDTO dto) throws ParseException {
		try {
				if(roomtrafficManageService.updateRoomTrafficParent(dto) >= 0){
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
	
	@RequestMapping(value = "/insertRoomInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@Secured({ "ROLE_WELCOME" })
	public MsgBean insertRoomInfo(DicRoomTrafficInfo bizinfo) throws ParseException {
		try{
			int result = roomtrafficManageService.insertRoomTrafficInfo(bizinfo);
			if(result == 2){
				msg.setFlag(-2);
				msg.setMsg("新增失败，已存在相同的机房名称！");
			}
			else if(result == 3){
				msg.setFlag(-3);
				msg.setMsg("新增失败，已存在相同的开始IP+结束IP！");
			}
			else{
				msg.setFlag(0);
				msg.setMsg("新增成功！");
			}
			//commonCache.refreshCache();		
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("新增失败！");
		}
		return msg;
	}
	
	@RequestMapping(value = "/insertbulidInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@Secured({ "ROLE_WELCOME" })
	public MsgBean insertbulidInfo(DicRoomTrafficInfo bizinfo) throws ParseException {
		try{
			int result = roomtrafficManageService.insertRoomInfo(bizinfo);
			if(result == 2){
				msg.setFlag(-2);
				msg.setMsg("新增失败，已存在相同的机楼名称！");
			}
			else{
				msg.setFlag(0);
				msg.setMsg("新增成功！");
			}
			//commonCache.refreshCache();
			
		}catch(Exception e){
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("新增失败！");
		}
		return msg;
	}
	
	@RequestMapping(value = "/updateRoomInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@Secured({ "ROLE_WELCOME" })
	public MsgBean updateRoomInfo(DicRoomTrafficInfo bizinfo) throws ParseException {
		try{
			int result = roomtrafficManageService.updateRoomTrafficInfo(bizinfo);
			if(result == 2){
				msg.setFlag(-2);
				msg.setMsg("操作失败，已存在相同机房名称！");				
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
	@RequestMapping(value = "/delBuildInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delBuildInfo(DicRoomTrafficInfo bizinfo) throws ParseException {
		try {
			DicRoomTrafficInfo tmpinfo = new DicRoomTrafficInfo();
			tmpinfo.setServerbuildid(bizinfo.getServerbuildid()%100000);
			roomtrafficManageService.delBuildTrafficInfo(tmpinfo);
			//commonCache.refreshCache();
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
	@RequestMapping(value = "/delRoomInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delRoomInfo(DicRoomTrafficInfo bizinfo) throws ParseException {
		try {
			roomtrafficManageService.delRoomTrafficInfo(bizinfo);
			//commonCache.refreshCache();
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
	@RequestMapping(value = "/delRoomConfigs", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delRoomConfigs(DicRoomTrafficConfig bizinfo) throws ParseException {
		try {
			roomtrafficManageService.delRoomConfigInfos(bizinfo);
			//commonCache.refreshCache();
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
