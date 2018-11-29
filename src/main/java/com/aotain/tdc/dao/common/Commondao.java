package com.aotain.tdc.dao.common;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DpiClogBean;
import com.aotain.tdc.dto.common.SelectorBean;

public interface Commondao {
	List<SelectorBean> selectProvince();
	List<SelectorBean> selectCarrier();
	List<SelectorBean> selectCatalog();
	List<SelectorBean> selectBizInfo();
	List<SelectorBean> selectBaseRePool();
	List<SelectorBean> selectBRoom();
	List<SelectorBean> selectRoomTraffic();
	List<SelectorBean> selectClass(@Param("catalogId") int catalogId);
	List<SelectorBean> selectProduct(@Param("catalogId") int catalogId, @Param("classId") int classId);
	List<SelectorBean> selectModule(@Param("catalogId") int catalogId, @Param("classId") int classId, @Param("productId") int productId);
	List<SelectorBean> selectPage(@Param("moduleId") int moduleId);
	List<SelectorBean> selectPageProduct();
	SelectorBean getDicPageProductBeanByKey(Long pageProductId);
	List<SelectorBean> selectPageModule(@Param("pageProductId") int pageProductId);
	SelectorBean getDicPageModuleBeanByKey(Long pageModuleId);
	List<SelectorBean> selectProtocol(@Param("protocolTypeStr") String protocolTypeStr);
	List<SelectorBean> selectProtocolApplication();
	List<SelectorBean> selectSysPosition();
	List<SelectorBean> selectFTPIp();
	List<SelectorBean> selectFTPServer();
	SelectorBean getDicCarrierBeanByKey(Long carrierId);
	SelectorBean getDicProtoCataLogBeanByKey(Long catalogId);
	SelectorBean getDicProtoClassBeanByKey(Long classId);
	SelectorBean getDicProtocolBeanByKey(@Param("protocolId") Long protocolId, @Param("type") int type);
	SelectorBean getDicProtoProductBeanByKey(Long productId);
	SelectorBean getDicServerBuildBeanByKey(Long serverBuildId);
	SelectorBean getDicServerRoomBeanByKey(Long serverRoomId);
	SelectorBean getDicSysPositionBeanByKey(Long positionId);
	SelectorBean getDicProtoPageBeanByKey(Long pageId);
	SelectorBean getDicProtoModuleBeanByKey(Long moduleId);
	List<SelectorBean> selectRelatedIpMapping();
	List<SelectorBean> selectKPIConfig();
	List<SelectorBean> selectServerBuild();
	List<SelectorBean> selectServerRoom(int serverBuildId);
	List<SelectorBean> selectClassNodes();
	List<SelectorBean> selectProductNodes();
	List<SelectorBean> selectModuleNodes();
	List<SelectorBean> selectPageNodes();
	
	List<SelectorBean> selectBuilding();
	List<SelectorBean> selectRoom();
	List<HashMap<String, Object>> selectBuildingRoom();
	
	List<SelectorBean> selectStrategyName();
	List<SelectorBean> selectStrategyReName();
	
	List<SelectorBean> selectStatStrategyName();
	
	List<SelectorBean> getPolicyDstIPPortBeanByKey(@Param("strategyId") Long strategyId);
	List<SelectorBean> getPolicyDstIPPortBeanByIdType(@Param("strategyId") Long strategyId, @Param("strategyType") long strategyType);
	List<SelectorBean> getColPolicyDstIPPortBeanByKey(@Param("strategyId") Long strategyId);
	List<SelectorBean> getPolicyStrategyFTPBeanByKey(@Param("strategyId") Long strategyId);
	List<SelectorBean> getColPolicyStrategyFTPBeanByKey(@Param("strategyId") Long strategyId);
	int insertClog(DpiClogBean clogBean);
	int insertBizClog(DpiClogBean clogBean);
	int getWebServiceinfoSeqNextVal();
	int getDicIpmappingSeqNextVal();
	int getDicIpportSeqNextVal();
	int insertServerBuild(BaseDTO dto);
	int insertServerRoom(BaseDTO dto);
	long selectMessageSequenceno(String string);
	int getSeqNextVal(@Param("tableName") String tableName);
	List<SelectorBean> getPolicyStrategyFTPBeanByIdType(@Param("strategyId") long strategyId, @Param("strategyType") long strategyType);
	List<SelectorBean> selectDicBizInfo(@Param("bizId") Long bizId);	
}
