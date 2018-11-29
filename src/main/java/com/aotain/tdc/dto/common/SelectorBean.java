package com.aotain.tdc.dto.common;

import java.util.Date;

public class SelectorBean {
	private Long provinceId;//省份ID
	private String provinceName;
	private Long carrierId;//运营商ID
	private String carrierName;
	private String descInfo;
	private Long catalogId;//业务类别ID
	private String cataName;
	private int orderId;
	private Long classId;//业务ID
	private String className;
	private Long protocolId;
	private String protocolName;
	private int protocalType;
	private Long productId;//产品ID
	private String productName;
	private Long serverBuildId;
	private String serverBuildName;	
	private Long serverRoomId;
	private String serverRoomName;
	private Date lastUpdateTime;
	private Long moduleId;//功能模块ID
	private String moduleName;
	private Long pageId;//页面ID
	private String pageName;
	private Long pageProductId;//页面产品ID
	private String pageProductName;
	private Long pageModuleId;//页面板块ID
	private String pageModuleName;
	private String pageUrl;
	private Long positionId;
	private String positionName;
	private Long parentId;
	private int positionType;
	private int protocolType;
	private int strategyId;
	private String strategyName;
	private String quotaName;
	private long quotaId;
	private float quotaWeight;
	private float quotaFullPoint;
	private float quotaGoodPoint;
	private float quotaOkPoint;
	private float quotaBasePoint;
	
	private int strategyType;
	private int ftpId;
	private String ftpName;
	private String ip;
	private int port;
	private String userName;
	private String passwd;
	private Date createTime;
	private String createUser;
	
	private String dstIp;
	private String dstPort;
	private Long bizId;
	private String bizName;
	private int deleteFlag;
	
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public int getStrategyType() {
		return strategyType;
	}
	public int getStrategyId() {
		return strategyId;
	}
	public int getFtpId() {
		return ftpId;
	}
	public String getFtpName() {
		return ftpName;
	}
	public String getIp() {
		return ip;
	}
	public int getPort() {
		return port;
	}
	public String getUserName() {
		return userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setStrategyType(int strategyType) {
		this.strategyType = strategyType;
	}
	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}
	public void setFtpId(int ftpId) {
		this.ftpId = ftpId;
	}
	public void setFtpName(String ftpName) {
		this.ftpName = ftpName;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getQuotaName() {
		return quotaName;
	}
	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Long getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	public String getCataName() {
		return cataName;
	}
	public void setCataName(String cataName) {
		this.cataName = cataName;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getDescInfo() {
		return descInfo;
	}
	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Long getProtocolId() {
		return protocolId;
	}
	public String getProtocolName() {
		return protocolName;
	}
	public int getProtocalType() {
		return protocalType;
	}
	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
	}
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
	public void setProtocalType(int protocalType) {
		this.protocalType = protocalType;
	}
	public Long getServerBuildId() {
		return serverBuildId;
	}
	public void setServerBuildId(Long serverBuildId) {
		this.serverBuildId = serverBuildId;
	}
	public Long getServerRoomId() {
		return serverRoomId;
	}
	public void setServerRoomId(Long serverRoomId) {
		this.serverRoomId = serverRoomId;
	}
	public String getServerBuildName() {
		return serverBuildName;
	}
	public void setServerBuildName(String serverBuildName) {
		this.serverBuildName = serverBuildName;
	}
	public String getServerRoomName() {
		return serverRoomName;
	}
	public void setServerRoomName(String serverRoomName) {
		this.serverRoomName = serverRoomName;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public int getPositionType() {
		return positionType;
	}
	public void setPositionType(int positionType) {
		this.positionType = positionType;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public int getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(int protocolType) {
		this.protocolType = protocolType;
	}
	public float getQuotaWeight() {
		return quotaWeight;
	}
	public float getQuotaFullPoint() {
		return quotaFullPoint;
	}
	public float getQuotaGoodPoint() {
		return quotaGoodPoint;
	}
	public float getQuotaOkPoint() {
		return quotaOkPoint;
	}
	public float getQuotaBasePoint() {
		return quotaBasePoint;
	}
	public void setQuotaWeight(float quotaWeight) {
		this.quotaWeight = quotaWeight;
	}
	public void setQuotaFullPoint(float quotaFullPoint) {
		this.quotaFullPoint = quotaFullPoint;
	}
	public void setQuotaGoodPoint(float quotaGoodPoint) {
		this.quotaGoodPoint = quotaGoodPoint;
	}
	public void setQuotaOkPoint(float quotaOkPoint) {
		this.quotaOkPoint = quotaOkPoint;
	}
	public void setQuotaBasePoint(float quotaBasePoint) {
		this.quotaBasePoint = quotaBasePoint;
	}
	public long getQuotaId() {
		return quotaId;
	}
	public void setQuotaId(long quotaId) {
		this.quotaId = quotaId;
	}
	public String getDstPort() {
		return dstPort;
	}
	public void setDstPort(String dstPort) {
		this.dstPort = dstPort;
	}
	public String getDstIp() {
		return dstIp;
	}
	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
	public Long getPageProductId() {
		return pageProductId;
	}
	public void setPageProductId(Long pageProductId) {
		this.pageProductId = pageProductId;
	}
	public String getPageProductName() {
		return pageProductName;
	}
	public void setPageProductName(String pageProductName) {
		this.pageProductName = pageProductName;
	}
	public Long getPageModuleId() {
		return pageModuleId;
	}
	public void setPageModuleId(Long pageModuleId) {
		this.pageModuleId = pageModuleId;
	}
	public String getPageModuleName() {
		return pageModuleName;
	}
	public void setPageModuleName(String pageModuleName) {
		this.pageModuleName = pageModuleName;
	}
	public Long getBizId() {
		return bizId;
	}
	public String getBizName() {
		return bizName;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}
