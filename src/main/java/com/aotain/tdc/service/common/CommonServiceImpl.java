package com.aotain.tdc.service.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aotain.common.authService.UserserviceStub.ServiceReturnDTO;
import com.aotain.common.cache.CommonCache;
import com.aotain.common.security.Userbean;
import com.aotain.common.util.CommonLog;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.common.util.MD5Util;
import com.aotain.common.util.Tools;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dao.common.Commondao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DpiClogBean;
import com.aotain.tdc.dto.common.SelectorBean;
import com.aotain.tdc.model.common.BaseModel;

@Service("CommonService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	private Commondao commondao;
	
	@Autowired
	private CommonCache commonCache;

	@Autowired
	private ExportService<HashMap<String, String>> exportService;

	@Override
	public List<SelectorBean> selectProvince() {
		return commondao.selectProvince();
	}

	@Override
	public List<SelectorBean> selectCarrier() {
		return commondao.selectCarrier();
	}

	@Override
	public List<SelectorBean> selectCatalog() {
		return commondao.selectCatalog();
	}

	@Override
	public List<SelectorBean> selectProduct(int catalogId, int classId) {
		return commondao.selectProduct(catalogId, classId);
	}

	@Override
	public List<SelectorBean> selectModule(int catalogId, int classId, int productId) {
		return commondao.selectModule(catalogId, classId, productId);
	}

	@Override
	public List<SelectorBean> selectPage(int moduleId) {
		return commondao.selectPage(moduleId);
	}
	
	@Override
	public List<SelectorBean> selectPageProduct() {
		return commondao.selectPageProduct();
	}
	@Override
	public SelectorBean getDicPageProductBeanByKey(Long pageProductId){
		return commondao.getDicPageProductBeanByKey(pageProductId);
	}
	@Override
	public List<SelectorBean> selectPageModule(int pageProductId) {
		return commondao.selectPageModule(pageProductId);
	}
	@Override
	public SelectorBean getDicPageModuleBeanByKey(Long pageModuleId){
		return commondao.getDicPageModuleBeanByKey(pageModuleId);
	}
	@Override
	public List<SelectorBean> selectClass(int catalogId) {
		return commondao.selectClass(catalogId);
	}

	@Override
	public List<SelectorBean> selectProtocol(String protocolTypeStr) {
		return commondao.selectProtocol(protocolTypeStr);
	}
	@Override
	public List<SelectorBean> selectProtocolApplication(){
		return commondao.selectProtocolApplication();
	}

	@Override
	public List<SelectorBean> selectFTPServer() {
		return commondao.selectFTPServer();
	}

	@Override
	public List<SelectorBean> selectFTPIp() {
		return commondao.selectFTPIp();
	}
	
	@Override
	public List<SelectorBean> selectStrategyName() {
		return commondao.selectStrategyName();
	}
	
	@Override
	public List<SelectorBean> selectStatStrategyName() {
		return commondao.selectStatStrategyName();
	}
	
	@Override
	public List<SelectorBean> selectStrategyReName() {
		return commondao.selectStrategyReName();
	}
	@Override
	public SelectorBean getDicCarrierBeanByKey(Long carrierId) {
		return commondao.getDicCarrierBeanByKey(carrierId);
	}

	@Override
	public SelectorBean getDicProtoCataLogBeanByKey(Long catalogId) {
		return commondao.getDicProtoCataLogBeanByKey(catalogId);
	}

	@Override
	public SelectorBean getDicProtoClassBeanByKey(Long classId) {
		return commondao.getDicProtoClassBeanByKey(classId);
	}

	@Override
	public SelectorBean getDicProtocolBeanByKey(Long protocolId, int type) {
		return commondao.getDicProtocolBeanByKey(protocolId, type);
	}

	@Override
	public SelectorBean getDicProtoProductBeanByKey(Long productId) {
		return commondao.getDicProtoProductBeanByKey(productId);
	}

	@Override
	public SelectorBean getDicServerBuildBeanByKey(Long serverBuildId) {
		return commondao.getDicServerBuildBeanByKey(serverBuildId);
	}

	@Override
	public SelectorBean getDicServerRoomBeanByKey(Long serverRoomId) {
		return commondao.getDicServerRoomBeanByKey(serverRoomId);
	}

	@Override
	public SelectorBean getDicSysPositionBeanByKey(Long positionId) {
		return commondao.getDicSysPositionBeanByKey(positionId);
	}

	@Override
	public SelectorBean getDicProtoPageBeanByKey(Long pageId) {
		return commondao.getDicProtoPageBeanByKey(pageId);
	}

	@Override
	public SelectorBean getDicProtoModuleBeanByKey(Long moduleId) {
		return commondao.getDicProtoModuleBeanByKey(moduleId);
	}

	@Override
	public List<SelectorBean> selectSysPosition() {
		return commondao.selectSysPosition();
	}

	@Override
	public void exportData(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, String fileName, List<HashMap<String, String>> list,
			HttpServletResponse response) {
		try {
			ExportType exportType = ExportType.valueOf("EXCEL");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			baseModel.setExportType(exportType.ordinal());
			baseModel.setDatas(list);
			baseModel.setOs(response.getOutputStream());
			exportService.exportMapExcel(baseModel);
		} catch (UnsupportedEncodingException e) {
			CommonLog.jspLog.error(e);
		} catch (IOException e) {
			CommonLog.jspLog.error(e);
		}
	}

	public String getBrowser(HttpServletRequest request) {
		String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (UserAgent != null) {
			if (UserAgent.indexOf("msie") >= 0)
				return "IE";
			if (UserAgent.indexOf("firefox") >= 0)
				return "FF";
			if (UserAgent.indexOf("safari") >= 0)
				return "SF";
		}
		return null;
	}

	@Override
	public List<SelectorBean> selectRelatedIpMapping() {
		return commondao.selectRelatedIpMapping();
	}

	/**
	 * 
	 * getSecondScale TODO(根据表名后缀获取妙为单位的时间颗粒度) TODO(jdk版本需要1.6以上)
	 *
	 * @Title: getParticle
	 * @Description: TODO
	 * @param
	 * @return int 返回类型
	 * @throws
	 */
	public static int getSecondScale(BaseDTO dto) {
		int particle = 0;
		switch (dto.getQueryTableName().substring(dto.getQueryTableName().lastIndexOf("_") + 1)) {
		case "MIN":
			particle = 60 * 5;
			break;
		case "H":
			particle = 60 * 60;
			break;
		case "D":
			particle = 60 * 60 * 24;
			break;
		case "M":
			particle = 60 * 60 * 30;
			break;
		}
		return particle;
	}

	public static int getQualitySecondScale(BaseDTO dto) {
		int particle = 0;
		switch (dto.getQueryTableName().substring(dto.getQueryTableName().lastIndexOf("_") + 1)) {
		case "MIN":
			particle = 60 * 5;
			break;
		case "H":
			particle = 60 * 60;
			break;
		case "D":
			particle = 60 * 60 * 24;
			break;
		case "M":
			particle = 60 * 60 * 30;
			break;
		}
		return particle;
	}
	
	// 流量总览-全网流量趋势分布（queryTableName_Serverbuild）
	public static int getServerbuildRSecondScale(BaseDTO dto) {
		int particle = 0;
		switch (dto.getQueryTableName_R_Serverbuild().substring(dto.getQueryTableName_R_Serverbuild().lastIndexOf("_") + 1)) {
		case "MIN":
			particle = 60 * 5;
			break;
		case "H":
			particle = 60 * 60;
			break;
		case "D":
			particle = 60 * 60 * 24;
			break;
		case "M":
			particle = 60 * 60 * 30;
			break;
		}
		return particle;
	}
	
	// 流量总览-全网流量趋势分布（queryTableName_Serverbuild）
	public static int getServerbuildSecondScale(BaseDTO dto) {
		int particle = 0;
		switch (dto.getQueryTableName_Serverbuild().substring(dto.getQueryTableName_Serverbuild().lastIndexOf("_") + 1)) {
		case "MIN":
			particle = 60 * 5;
			break;
		case "H":
			particle = 60 * 60;
			break;
		case "D":
			particle = 60 * 60 * 24;
			break;
		case "M":
			particle = 60 * 60 * 30;
			break;
		}
		return particle;
	}

	@Override
	public List<SelectorBean> selectKPIConfig() {
		return commondao.selectKPIConfig();
	}

	@Override
	public List<SelectorBean> selectServerBuild() {
		return commondao.selectServerBuild();
	}

	@Override
	public List<SelectorBean> selectServerRoom(int serverBuildId) {
		return commondao.selectServerRoom(serverBuildId);
	}

	@Override
	public List<SelectorBean> selectClassNodes() {
		return commondao.selectClassNodes();
	}
	
	@Override
	public List<SelectorBean> selectBizInfo() {
		return commondao.selectBizInfo();
	}
	
	
	@Override
	public List<SelectorBean> selectProductNodes() {
		return commondao.selectProductNodes();
	}
	
	@Override
	public List<SelectorBean> selectModuleNodes() {
		return commondao.selectModuleNodes();
	}
	
	@Override
	public List<SelectorBean> selectPageNodes() {
		return commondao.selectPageNodes();
	}

	@Override
	public List<SelectorBean> selectBuilding() {
		return commondao.selectBuilding();
	}

	@Override
	public List<SelectorBean> selectRoom() {
		return commondao.selectRoom();
	}
	
	@Override
	public List<HashMap<String, Object>> selectBuildingRoom(){
		return commondao.selectBuildingRoom();
	}
	
	@Override
	public List<SelectorBean> getPolicyDstIPPortBeanByKey(Long strategyId) {
		return commondao.getPolicyDstIPPortBeanByKey(strategyId);
	}
	
	@Override
	public List<SelectorBean> getColPolicyDstIPPortBeanByKey(Long strategyId) {
		return commondao.getColPolicyDstIPPortBeanByKey(strategyId);
	}
	@Override
	public List<SelectorBean> getPolicyStrategyFTPBeanByKey(Long strategyId) {
		return commondao.getPolicyStrategyFTPBeanByKey(strategyId);
	}
	
	@Override
	public List<SelectorBean> getColPolicyStrategyFTPBeanByKey(Long strategyId) {
		return commondao.getColPolicyStrategyFTPBeanByKey(strategyId);
	}

	@Override
	public int insertClog(DpiClogBean clogBean) {
		return commondao.insertClog(clogBean);
	}
	
	@Override
	public int insertBizClog(DpiClogBean clogBean) {
		return commondao.insertBizClog(clogBean);
	}
	
	@Override
	public int getWebServiceinfoSeqNextVal() {
		return commondao.getWebServiceinfoSeqNextVal();
	}	
	
	@Override
	public int getDicIpmappingSeqNextVal() {
		return commondao.getDicIpmappingSeqNextVal();
	}
	
	@Override
	public int getDicIpportSeqNextVal() {
		return commondao.getDicIpportSeqNextVal();
	}
	
	@Override
	public int insertServerBuild(BaseDTO dto) {
		int re = commondao.insertServerBuild(dto);
		commonCache.getDicServerBuildBeanCache().invalidateAll();
		return re;
	}

	@Override
	public int insertServerRoom(BaseDTO dto) {
		int re = commondao.insertServerRoom(dto);
		commonCache.getDicServerRoomBeanCache().invalidateAll();
		return re;
	}

	@Override
	public int getSeqNextVal(String tableName) {
		return commondao.getSeqNextVal(tableName);
	}

	@Override
	public List<SelectorBean> getPolicyStrategyFTPBeanByIdType(long strategyId, long strategyType) {
		return commondao.getPolicyStrategyFTPBeanByIdType(strategyId, strategyType);
	}

	@Override
	public List<SelectorBean> getPolicyDstIPPortBeanByIdType(long strategyId, long strategyType) {
		return commondao.getPolicyStrategyFTPBeanByIdType(strategyId, strategyType);
	}

	@Override
	public SelectorBean selectDicBizInfo(Long bizId) {
		List<SelectorBean> s = commondao.selectDicBizInfo(bizId);
		if(s.size() == 0) return null;
		else
		return commondao.selectDicBizInfo(bizId).get(0);
	}
	

	@Override
	public List<SelectorBean> selectBaseRePool() {
		return commondao.selectBaseRePool();
	}

	@Override
	public List<SelectorBean> selectBRoom() {
		return commondao.selectBRoom();
	}

	@Override
	public List<SelectorBean> selectRoomtraffic() { 
		return commondao.selectRoomTraffic();
	}	
	
}
