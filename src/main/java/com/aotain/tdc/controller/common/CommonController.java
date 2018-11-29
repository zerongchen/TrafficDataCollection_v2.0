package com.aotain.tdc.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.security.SecurityUtils;
import com.aotain.common.util.Tools;
import com.aotain.tdc.constant.CacheType;
import com.aotain.tdc.dto.common.ResultEntity;
import com.aotain.tdc.dto.common.SelectorBean;
import com.aotain.tdc.dto.common.TableColumns;
import com.aotain.tdc.dto.common.TableParam;
import com.aotain.tdc.dto.common.UserColumnDTO;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.common.TableColumnService;
import com.aotain.tdc.service.common.UserColumnService;

@Controller
@RequestMapping("/common")
public class CommonController {
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private TableColumnService tableColumnService;
	
	@Autowired
	private UserColumnService userColumnService;
	
	@Autowired
	private CommonCache commonCache;
	
	@Secured({"ROLE_WELCOME"})
	@RequestMapping(value="/{tableName}/getTableColumnData",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<TableColumns> getTableColumnData(HttpServletRequest request, @PathVariable String tableName) {
		TableParam tc = new TableParam();
		tc.setOperID(Integer.valueOf(SecurityUtils.getUserId()+""));
		tc.setTableName(tableName);
		return tableColumnService.getTableColumns(tc);
	}
	
	@RequestMapping(value="selectBizInfo")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectBizInfo(HttpServletRequest request){
		return commonService.selectBizInfo();
	}
	
	@RequestMapping(value="selectBaseRePool")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectBaseRePool(HttpServletRequest request){
		return commonService.selectBaseRePool();
	}
	
	// 更新隐藏列表
    @RequestMapping(value = "/{tableName}/updateColumnUser", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultEntity updateColumnUser(HttpServletRequest request, @PathVariable String tableName) {
		ResultEntity result = new ResultEntity();
		String field = request.getParameter("field");
		TableParam param = new TableParam();
		param.setColumn_value(field.toUpperCase());
		param.setTableName(tableName);
		int dictId = tableColumnService.getDictId(param);
		//状态值 1表示显示 2表示隐藏
		int tag = Tools.GetInt32(request.getParameter("tag"), -1);
		int userId = Integer.valueOf(SecurityUtils.getUserId()+"");	
    	UserColumnDTO userColumnDTO = new UserColumnDTO(userId,dictId,tag);
    	userColumnService.updateColumnUser(userColumnDTO);
		result.resultData = 0;
		return result;
    }
	
	@RequestMapping(value="selectProvince")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectProvince(HttpServletRequest request){
		return commonService.selectProvince();
	}
	/*
	@RequestMapping(value="selectCarrier")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectCarrier(HttpServletRequest request){
		return commonService.selectCarrier();
	}*/
	@RequestMapping(value="selectCatalog")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectCatalog(HttpServletRequest request){
		return commonService.selectCatalog();
	}
	@RequestMapping(value="selectClass")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectClass(HttpServletRequest request, int catalogId){
		return commonService.selectClass(catalogId);
	}
	@RequestMapping(value="selectProduct")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectProduct(HttpServletRequest request, int catalogId, int classId){
		return commonService.selectProduct(catalogId, classId);
	}
	@RequestMapping(value="selectModule")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectModule(HttpServletRequest request, int catalogId, int classId, int productId){
		return commonService.selectModule(catalogId, classId, productId);
	}
	@RequestMapping(value="selectPage")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectPage(HttpServletRequest request, int moduleId){
		return commonService.selectPage(moduleId);
	}
	
	@RequestMapping(value="selectPageProduct")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectPageProduct(HttpServletRequest request){
		return commonService.selectPageProduct();
	}
	@RequestMapping(value="selectPageModule")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectPageModule(HttpServletRequest request, int pageProductId){
		return commonService.selectPageModule(pageProductId);
	}
	
	@RequestMapping(value="selectProtocol")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectProtocol(HttpServletRequest request, String protocolTypeStr){
		return commonService.selectProtocol(protocolTypeStr);
	}
	@RequestMapping(value="selectProtocolApplication")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectProtocolApplication(HttpServletRequest request, String protocolTypeStr){
		return commonService.selectProtocolApplication();
	}
	
	@RequestMapping(value="selectCarrier")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectCarrier(HttpServletRequest request) throws ExecutionException{
		return commonCache.getDicArrayListCache().get(CacheType.CARRIER);
	}
	
	@RequestMapping(value="selectSysPosition")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectSysPosition(HttpServletRequest request) throws ExecutionException{
        return commonCache.getDicArrayListCache().get(CacheType.SYSPOSITION);
	}
	/**
	 * up zhenggf 机房查询
	 * @param request
	 * @return
	 * @throws ExecutionException
	 */
	@RequestMapping(value="selectTreeData")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectTreeData(HttpServletRequest request) throws ExecutionException{
        return commonCache.getDicArrayListCache().get(CacheType.IPMAPPING_RELATED);
	}
	
	@RequestMapping(value="selectFTPServer")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectFTPServer(HttpServletRequest request){
		return commonService.selectFTPServer();
	}
	
	@RequestMapping(value="selectFTPIp")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectFTPIp(HttpServletRequest request){
		return commonService.selectFTPIp();
	}
	@RequestMapping(value="selectStrategyName")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectStrategyName(HttpServletRequest request){
		return commonService.selectStrategyName();
	}
	
	@RequestMapping(value="selectStatStrategyName")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectStatStrategyName(HttpServletRequest request){
		return commonService.selectStatStrategyName();
	}
	
	@RequestMapping(value="selectStrategyReName")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectStrategyReName(HttpServletRequest request){
		return commonService.selectStrategyReName();
	}
	@RequestMapping(value="selectServerBuild")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectServerBuild(HttpServletRequest request){
		return commonService.selectServerBuild();
	}
	@RequestMapping(value="selectServerRoom")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectServerRoom(HttpServletRequest request, int serverBuildId){
		return commonService.selectServerRoom(serverBuildId);
	}
	
	@RequestMapping(value="selectBuildingRoom")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<HashMap<String, Object>> selectBuildingRoom(HttpServletRequest request){
		return commonService.selectBuildingRoom();
	}
	
	@RequestMapping(value="selectBRoom")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectBRoom(HttpServletRequest request){
		return commonService.selectBRoom();
	}
	
	@RequestMapping(value="selectRoomtraffic")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectRoomtraffic(HttpServletRequest request){
		return commonService.selectRoomtraffic();
	}
	
	@RequestMapping(value="selectClassNodes")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectClassNodes(HttpServletRequest request){
		return commonService.selectClassNodes();
	}
	
	@RequestMapping(value="selectProductNodes")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectProductNodes(HttpServletRequest request){
		return commonService.selectProductNodes();
	}
	
	@RequestMapping(value="selectModuleNodes")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectModuleNodes(HttpServletRequest request){
		return commonService.selectModuleNodes();
	}
	
	@RequestMapping(value="selectPageNodes")
	@Secured({"ROLE_WELCOME"})
	@ResponseBody
	public List<SelectorBean> selectPageNodes(HttpServletRequest request){
		return commonService.selectPageNodes();
	}
}
