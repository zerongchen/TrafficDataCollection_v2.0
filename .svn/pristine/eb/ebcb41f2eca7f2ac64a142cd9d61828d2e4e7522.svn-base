package com.aotain.tdc.service.common;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.authentication.AuthenticationManager;

import com.aotain.common.authService.UserserviceStub.ServiceReturnDTO;
import com.aotain.common.security.Userbean;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.constant.OpType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DpiClogBean;
import com.aotain.tdc.dto.common.SelectorBean;
import com.aotain.tdc.model.common.BaseModel;

public interface CommonService {
	public List<SelectorBean> selectProvince();
	public List<SelectorBean> selectCarrier();
	public List<SelectorBean> selectCatalog();
	public List<SelectorBean> selectClass(int catalogId);
	public List<SelectorBean> selectProduct(int catalogId, int classId);
	public List<SelectorBean> selectModule(int catalogId, int classId, int productId);
	public List<SelectorBean> selectPage(int moduleId);
	public List<SelectorBean> selectPageProduct();
	public SelectorBean getDicPageProductBeanByKey(Long pageProductId);
	public List<SelectorBean> selectPageModule(int pageProductId);
	public SelectorBean getDicPageModuleBeanByKey(Long pageModuleId);
	public List<SelectorBean> selectProtocol(String protocolTypeStr);
	public List<SelectorBean> selectProtocolApplication();
	public List<SelectorBean> selectRelatedIpMapping();
	public List<SelectorBean> selectSysPosition();
	public SelectorBean getDicCarrierBeanByKey(Long carrierId);
	public SelectorBean getDicProtoCataLogBeanByKey(Long catalogId);
	public SelectorBean getDicProtoClassBeanByKey(Long classId);
	public SelectorBean getDicProtocolBeanByKey(Long protocolId, int type);
	public SelectorBean getDicProtoProductBeanByKey(Long productId);
	public SelectorBean getDicServerBuildBeanByKey(Long serverBuildId);
	public SelectorBean getDicServerRoomBeanByKey(Long serverRoomId);
	public SelectorBean getDicSysPositionBeanByKey(Long positionId);
	public SelectorBean getDicProtoPageBeanByKey(Long pageId);
	public SelectorBean getDicProtoModuleBeanByKey(Long moduleId);
	public void exportData(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, String fileName,
			List<HashMap<String, String>> list, HttpServletResponse response);
	String getBrowser(HttpServletRequest request);
	public List<SelectorBean> selectFTPServer();
	public List<SelectorBean> selectFTPIp();
	public List<SelectorBean> selectKPIConfig();
	public List<SelectorBean> selectServerBuild();
	public List<SelectorBean> selectServerRoom(int serverBuildId);
	public List<SelectorBean> selectClassNodes();
	public List<SelectorBean> selectProductNodes();
	public List<SelectorBean> selectModuleNodes();
	public List<SelectorBean> selectPageNodes();
	public List<SelectorBean> selectBuilding();
	public List<SelectorBean> selectRoom();
	public List<HashMap<String, Object>> selectBuildingRoom();	
	public List<SelectorBean> selectStrategyName();
	public List<SelectorBean> getPolicyDstIPPortBeanByKey(Long strategyId);
	public List<SelectorBean> getColPolicyDstIPPortBeanByKey(Long strategyId);
	public List<SelectorBean> getPolicyStrategyFTPBeanByKey(Long ftpid);
	public List<SelectorBean> getColPolicyStrategyFTPBeanByKey(Long strategyId);
	List<SelectorBean> selectStrategyReName();
	public int insertClog(DpiClogBean clogBean);
	public int insertBizClog(DpiClogBean clogBean);
	public int getWebServiceinfoSeqNextVal();
	public int getDicIpmappingSeqNextVal();
	public int getDicIpportSeqNextVal();
	public int insertServerBuild(BaseDTO dto);
	public int insertServerRoom(BaseDTO dto);
	public int getSeqNextVal(String tableName);
	public List<SelectorBean> getPolicyStrategyFTPBeanByIdType(long strategyId, long strategyType);
	public List<SelectorBean> getPolicyDstIPPortBeanByIdType(long parseLong, long parseLong2);
	public SelectorBean selectDicBizInfo(Long bizId);	
	public List<SelectorBean> selectBizInfo();
	public List<SelectorBean> selectBaseRePool();
	public List<SelectorBean> selectBRoom();
	public List<SelectorBean> selectRoomtraffic();
	List<SelectorBean> selectStatStrategyName();
}
