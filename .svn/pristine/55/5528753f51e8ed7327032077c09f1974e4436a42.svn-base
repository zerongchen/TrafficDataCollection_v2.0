package com.aotain.tdc.dto.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class BaseDTO implements Serializable {

	private static final long serialVersionUID = -207075340275527653L;

	// 是否分页(0-不分页、1-分页)
	private Integer isPaging = 1;

	// 页索引
	private Integer offset = 0;

	// 页大小，即每页显示的记录数量
	private Integer limit = 10;

	// 排序字段
	private String sort;

	// 排序类型
	private String order;

	// 是否需要统计记录数(0-不 统计，1-统计)，默认统计
	private Integer isCount = 1;

	// 统计记录数
	private Integer totalCounts = 0;

	private Integer startRow;

	private Integer endRow;

	private int queryResourceMark;

	private String queryFieldName;

	private Long queryCatalogId;

	private Long queryClassId;

	private Long queryProductId;

	private Long queryModuleId;

	private Long queryPageId;
	
	private String queryBizIdStr;
	
	private String queryPageName;
	
	private Long queryProvinceId;

	private Long queryCarrierId;

	private Long queryProtocolId;

	private String queryCarriers;

	private String queryErrorCode;

	private Long queryCarrier;

	private String queryPosition;

	private String queryTableName;
	private String queryTableName_Serverbuild;
	private String queryTableName_R_Serverbuild;
	private String queryTableName_Protocol;
	private String queryTableName_IP;
	private String queryTableName_Biz;
	
	private String queryTableName_Province;

	private Long queryProtocolType;

	private String queryProtocol;

	private String queryStartTime;

	private String queryEndTime;

	private String queryStartTimeR;

	private String queryEndTimeR;
	
	private Long queryServerBuildId;

	private Long queryServerRoomId;

	private List<?> resultList;

	private int isQueryDateTime;

	private Long queryDetailsId;

	private String queryDetailsIdStr;

	private String queryGroupByColumnName;

	private String querySrcIp;
	private String querySrcPort;
	private String queryDestIp;
	private String queryDestPort;
	private String queryDomain;
	private String queryUrl;

	private String queryUserName;

	private String queryCreateUser;

	private String queryContact;

	private String queryMobile;

	private String queryEmail;

	private String querySystemId;

	private String queryPassword;

	private String queryName;

	private long queryId;

	private long queryStatus;

	private String queryIds;

	private String queryFTPServerId;

	private String queryFTPServerIds;

	private String queryFTPServerName;

	private String queryFTPServerIp;

	private String queryFTPServerIps;

	private String queryPort;

	private String queryStrategyId;
	
	private int queryStrategyType;

	private String queryStrategyIds;

	private String queryStrategyName;

	private String queryDealType;

	private Integer queryFlowType;

	private Integer queryStrategyVer;

	private Integer queryMessageSequenceno;
	
	private Integer queryOperateType;
	
	private Integer queryOperateModule;
	
	private Integer queryColumnType;
	
	private Integer querySwitchable;
	
	private Integer queryColumnOrder;
	
	private String queryHour;
	
	private Integer queryReportType;
	
	private String queryHostName;
	
	@Override
	public boolean equals(Object obj) {
		BaseDTO dto = (BaseDTO) obj;
		return (this.getQueryCatalogId() == dto.getQueryCatalogId() && this.getQueryClassId() == dto.getQueryClassId()
				&& this.getQueryProductId() == dto.getQueryProductId() && this.getQueryModuleId() == dto.getQueryModuleId()
				&& this.getQueryServerBuildId() == dto.getQueryServerBuildId() && this.getQueryServerRoomId() == dto.getQueryServerRoomId()
				&& (this.getQueryDestIp().equals(dto.getQueryDestIp()) || (this.getQueryDestIp() == null && dto.getQueryDestIp() == null)) && (this
				.getQueryDestPort().equals(dto.getQueryDestPort()) || (this.getQueryDestPort() == null && dto.getQueryDestPort() == null)));
	}

	@Override
	public int hashCode() {
		return (int) (((((((31 * this.getQueryCatalogId() + this.getQueryClassId()) * 31 + this.getQueryProductId()) * 31 + this.getQueryModuleId()) * 31 + this
				.getQueryServerBuildId()) * 31 + this.getQueryServerRoomId()) + this.getQueryDestIp().hashCode()) * 31 + this.getQueryDestPort()
				.hashCode());
	}

	/**
	 * 指标权重
	 */
	private float quotaWeight;
	/**
	 * 指标满分阈值
	 */
	private float quotaFullPoint;
	/**
	 * 指标优良阈值
	 */
	private float quotaGoodPoint;
	/**
	 * 指标及格阈值
	 */
	private float quotaBasePoint;
	/**
	 * 指标基准值
	 */
	private float quotaStep;
	/**
	 * 指标单位
	 */
	private String quotaUnit;
	/**
	 * 指标名称
	 */
	private String quotaName;

	private String queryStartIp;

	private String queryEndIp;

	private String queryStartPort;

	private String queryEndPort;

	private long queryServiceInfoId;

	private long queryIpMappingId;

	private long queryIpPortId;

	private String queryIpPortIds;
	
	private long queryBizID;
	
	private long queryParentId;
	
	public float getQuotaWeight() {
		return quotaWeight;
	}

	public float getQuotaFullPoint() {
		return quotaFullPoint;
	}

	public float getQuotaGoodPoint() {
		return quotaGoodPoint;
	}

	public float getQuotaBasePoint() {
		return quotaBasePoint;
	}

	public float getQuotaStep() {
		return quotaStep;
	}

	public String getQuotaUnit() {
		return quotaUnit;
	}

	public String getQuotaName() {
		return quotaName;
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

	public void setQuotaBasePoint(float quotaBasePoint) {
		this.quotaBasePoint = quotaBasePoint;
	}

	public void setQuotaStep(float quotaStep) {
		this.quotaStep = quotaStep;
	}

	public void setQuotaUnit(String quotaUnit) {
		this.quotaUnit = quotaUnit;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public String getQueryPassword() {
		return queryPassword;
	}

	public void setQueryPassword(String queryPassword) {
		this.queryPassword = queryPassword;
	}

	public String getQuerySystemId() {
		return querySystemId;
	}

	public void setQuerySystemId(String querySystemId) {
		this.querySystemId = querySystemId;
	}

	public String getQueryEmail() {
		return queryEmail;
	}

	public void setQueryEmail(String queryEmail) {
		this.queryEmail = queryEmail;
	}

	public String getQueryMobile() {
		return queryMobile;
	}

	public void setQueryMobile(String queryMobile) {
		this.queryMobile = queryMobile;
	}

	public String getQueryContact() {
		return queryContact;
	}

	public void setQueryContact(String queryContact) {
		this.queryContact = queryContact;
	}

	public String getQuerySrcIp() {
		return querySrcIp;
	}

	public void setQuerySrcIp(String querySrcIp) {
		this.querySrcIp = querySrcIp;
	}

	public String getQuerySrcPort() {
		return querySrcPort;
	}

	public void setQuerySrcPort(String querySrcPort) {
		this.querySrcPort = querySrcPort;
	}

	public String getQueryDestPort() {
		return queryDestPort;
	}

	public void setQueryDestPort(String i) {
		this.queryDestPort = i;
	}

	public String getQueryDomain() {
		return queryDomain;
	}

	public void setQueryDomain(String queryDomain) {
		this.queryDomain = queryDomain;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}

	public Long getQueryCatalogId() {
		return queryCatalogId;
	}

	public Long getQueryClassId() {
		return queryClassId;
	}

	public Long getQueryProductId() {
		return queryProductId;
	}

	public Long getQueryModuleId() {
		return queryModuleId;
	}

	public Long getQueryPageId() {
		return queryPageId;
	}
    
	public String getQueryPageName() {
		return queryPageName;
	}

	public void setQueryPageName(String queryPageName) {
		this.queryPageName = queryPageName;
	}

	public Long getQueryProvinceId() {
		return queryProvinceId;
	}

	public Long getQueryCarrierId() {
		return queryCarrierId;
	}

	public Long getQueryCarrier() {
		return queryCarrier;
	}

	public String getQueryPosition() {
		return queryPosition;
	}

	public void setQueryCatalogId(Long queryCatalogId) {
		this.queryCatalogId = queryCatalogId;
	}

	public void setQueryClassId(Long queryClassId) {
		this.queryClassId = queryClassId;
	}

	public void setQueryProductId(Long queryProductId) {
		this.queryProductId = queryProductId;
	}

	public void setQueryModuleId(Long queryModuleId) {
		this.queryModuleId = queryModuleId;
	}

	public void setQueryPageId(Long queryPageId) {
		this.queryPageId = queryPageId;
	}

	public void setQueryProvinceId(Long queryProvinceId) {
		this.queryProvinceId = queryProvinceId;
	}

	public void setQueryCarrierId(Long queryCarrierId) {
		this.queryCarrierId = queryCarrierId;
	}

	public void setQueryCarrier(Long queryCarrier) {
		this.queryCarrier = queryCarrier;
	}

	public void setQueryPosition(String queryPosition) {
		this.queryPosition = queryPosition;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public Integer getIsPaging() {
		return isPaging;
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public String getSort() {
		return sort;
	}

	public String getOrder() {
		return order;
	}

	public Integer getIsCount() {
		return isCount;
	}

	public Integer getTotalCounts() {
		return totalCounts;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setIsPaging(Integer isPaging) {
		this.isPaging = isPaging;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setIsCount(Integer isCount) {
		this.isCount = isCount;
	}

	public void setTotalCounts(Integer totalCounts) {
		this.totalCounts = totalCounts;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public Long getQueryProtocolType() {
		return queryProtocolType;
	}

	public void setQueryProtocolType(Long queryProtocolType) {
		this.queryProtocolType = queryProtocolType;
	}

	public String getQueryProtocol() {
		return queryProtocol;
	}

	public void setQueryProtocol(String queryProtocol) {
		this.queryProtocol = queryProtocol;
	}

	public String getQueryTableName() {
		return queryTableName;
	}

	public void setQueryTableName(String queryTableName) {
		this.queryTableName = queryTableName;
	}

	public String getQueryTableName_Serverbuild() {
		return queryTableName_Serverbuild;
	}

	public void setQueryTableName_Serverbuild(String queryTableName_Serverbuild) {
		this.queryTableName_Serverbuild = queryTableName_Serverbuild;
	}

	public String getQueryTableName_Protocol() {
		return queryTableName_Protocol;
	}

	public void setQueryTableName_Protocol(String queryTableName_Protocol) {
		this.queryTableName_Protocol = queryTableName_Protocol;
	}

	public String getQueryTableName_IP() {
		return queryTableName_IP;
	}

	public void setQueryTableName_IP(String queryTableName_IP) {
		this.queryTableName_IP = queryTableName_IP;
	}

	public String getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public String getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public String getQueryCarriers() {
		return queryCarriers;
	}

	public void setQueryCarriers(String queryCarriers) {
		this.queryCarriers = queryCarriers;
	}

	public int getIsQueryDateTime() {
		return isQueryDateTime;
	}

	public void setIsQueryDateTime(int isQueryDateTime) {
		// this.isQueryDateTime =
		// LocalConfig.getInstance().getIsQueryDateTime();
		this.isQueryDateTime = isQueryDateTime;
	}

	public Long getQueryDetailsId() {
		return queryDetailsId;
	}

	public void setQueryDetailsId(Long queryDetailsId) {
		this.queryDetailsId = queryDetailsId;
	}

	public String getQueryDetailsIdStr() {
		return queryDetailsIdStr;
	}

	public void setQueryDetailsIdStr(String queryDetailsIdStr) {
		this.queryDetailsIdStr = queryDetailsIdStr;
	}

	public Long getQueryServerBuildId() {
		return queryServerBuildId;
	}

	public void setQueryServerBuildId(Long queryServerBuildId) {
		this.queryServerBuildId = queryServerBuildId;
	}

	public Long getQueryServerRoomId() {
		return queryServerRoomId;
	}

	public void setQueryServerRoomId(Long queryServerRoomId) {
		this.queryServerRoomId = queryServerRoomId;
	}

	public String getQueryDestIp() {
		return queryDestIp;
	}

	public void setQueryDestIp(String queryDestIp) {
		this.queryDestIp = queryDestIp;
	}

	public String getQueryFieldName() {
		return queryFieldName;
	}

	public void setQueryFieldName(String queryFieldName) {
		this.queryFieldName = queryFieldName;
	}

	public String getQueryGroupByColumnName() {
		return queryGroupByColumnName;
	}

	public void setQueryGroupByColumnName(String queryGroupByColumnName) {
		this.queryGroupByColumnName = queryGroupByColumnName;
	}

	public String getQueryUserName() {
		return queryUserName;
	}

	public void setQueryUserName(String queryUserName) {
		this.queryUserName = queryUserName;
	}

	public long getQueryId() {
		return queryId;
	}

	public void setQueryId(long queryId) {
		this.queryId = queryId;
	}

	public long getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(long queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getQueryIds() {
		return queryIds;
	}

	public void setQueryIds(String queryIds) {
		this.queryIds = queryIds;
	}

	public String getQueryCreateUser() {
		return queryCreateUser;
	}

	public void setQueryCreateUser(String queryCreateUser) {
		this.queryCreateUser = queryCreateUser;
	}

	public String getQueryFTPServerId() {
		return queryFTPServerId;
	}

	public void setQueryFTPServerId(String queryFTPServerId) {
		this.queryFTPServerId = queryFTPServerId;
	}

	public String getQueryFTPServerIds() {
		return queryFTPServerIds;
	}

	public void setQueryFTPServerIds(String queryFTPServerIds) {
		this.queryFTPServerIds = queryFTPServerIds;
	}

	public String getQueryFTPServerName() {
		return queryFTPServerName;
	}

	public void setQueryFTPServerName(String queryFTPServerName) {
		this.queryFTPServerName = queryFTPServerName;
	}

	public String getQueryFTPServerIp() {
		return queryFTPServerIp;
	}

	public void setQueryFTPServerIp(String queryFTPServerIp) {
		this.queryFTPServerIp = queryFTPServerIp;
	}

	public String getQueryPort() {
		return queryPort;
	}

	public void setQueryPort(String queryPort) {
		this.queryPort = queryPort;
	}

	public Long getQueryProtocolId() {
		return queryProtocolId;
	}

	public void setQueryProtocolId(Long queryProtocolId) {
		this.queryProtocolId = queryProtocolId;
	}

	public String getQueryErrorCode() {
		return queryErrorCode;
	}

	public void setQueryErrorCode(String queryErrorCode) {
		this.queryErrorCode = queryErrorCode;
	}

	public String getQueryStrategyId() {
		return queryStrategyId;
	}

	public void setQueryStrategyId(String queryStrategyId) {
		this.queryStrategyId = queryStrategyId;
	}

	public String getQueryStrategyIds() {
		return queryStrategyIds;
	}

	public void setQueryStrategyIds(String queryStrategyIds) {
		this.queryStrategyIds = queryStrategyIds;
	}

	public String getQueryStrategyName() {
		return queryStrategyName;
	}

	public void setQueryStrategyName(String queryStrategyName) {
		this.queryStrategyName = queryStrategyName;
	}

	public String getQueryDealType() {
		return queryDealType;
	}

	public void setQueryDealType(String queryDealType) {
		this.queryDealType = queryDealType;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Integer getQueryFlowType() {
		return queryFlowType;
	}

	public void setQueryFlowType(Integer queryFlowType) {
		this.queryFlowType = queryFlowType;
	}

	public Integer getQueryStrategyVer() {
		return queryStrategyVer;
	}

	public void setQueryStrategyVer(Integer queryStrategyVer) {
		this.queryStrategyVer = queryStrategyVer;
	}

	public String getQueryFTPServerIps() {
		return queryFTPServerIps;
	}

	public void setQueryFTPServerIps(String queryFTPServerIps) {
		this.queryFTPServerIps = queryFTPServerIps;
	}

	public Integer getQueryMessageSequenceno() {
		return queryMessageSequenceno;
	}

	public void setQueryMessageSequenceno(Integer queryMessageSequenceno) {
		this.queryMessageSequenceno = queryMessageSequenceno;
	}

	public String getQueryStartIp() {
		return queryStartIp;
	}

	public void setQueryStartIp(String queryStartIp) {
		this.queryStartIp = queryStartIp;
	}

	public String getQueryEndIp() {
		return queryEndIp;
	}

	public void setQueryEndIp(String queryEndIp) {
		this.queryEndIp = queryEndIp;
	}

	public String getQueryStartPort() {
		return queryStartPort;
	}

	public void setQueryStartPort(String queryStartPort) {
		this.queryStartPort = queryStartPort;
	}

	public String getQueryEndPort() {
		return queryEndPort;
	}

	public void setQueryEndPort(String queryEndPort) {
		this.queryEndPort = queryEndPort;
	}

	public long getQueryServiceInfoId() {
		return queryServiceInfoId;
	}

	public void setQueryServiceInfoId(long queryServiceInfoId) {
		this.queryServiceInfoId = queryServiceInfoId;
	}

	public long getQueryIpMappingId() {
		return queryIpMappingId;
	}

	public void setQueryIpMappingId(long queryIpMappingId) {
		this.queryIpMappingId = queryIpMappingId;
	}

	public long getQueryIpPortId() {
		return queryIpPortId;
	}

	public void setQueryIpPortId(long queryIpPortId) {
		this.queryIpPortId = queryIpPortId;
	}

	public String getQueryIpPortIds() {
		return queryIpPortIds;
	}

	public void setQueryIpPortIds(String queryIpPortIds) {
		this.queryIpPortIds = queryIpPortIds;
	}

	public int getQueryResourceMark() {
		return queryResourceMark;
	}

	public void setQueryResourceMark(int queryResourceMark) {
		this.queryResourceMark = queryResourceMark;
	}

	public Integer getQueryOperateType() {
		return queryOperateType;
	}

	public void setQueryOperateType(Integer queryOperateType) {
		this.queryOperateType = queryOperateType;
	}

	public Integer getQueryOperateModule() {
		return queryOperateModule;
	}

	public void setQueryOperateModule(Integer queryOperateModule) {
		this.queryOperateModule = queryOperateModule;
	}

	public int getQueryStrategyType() {
		return queryStrategyType;
	}

	public void setQueryStrategyType(int queryStrategyType) {
		this.queryStrategyType = queryStrategyType;
	}

	public Integer getQueryColumnType() {
		return queryColumnType;
	}

	public void setQueryColumnType(Integer queryColumnType) {
		this.queryColumnType = queryColumnType;
	}

	public Integer getQuerySwitchable() {
		return querySwitchable;
	}

	public void setQuerySwitchable(Integer querySwitchable) {
		this.querySwitchable = querySwitchable;
	}

	public Integer getQueryColumnOrder() {
		return queryColumnOrder;
	}

	public void setQueryColumnOrder(Integer queryColumnOrder) {
		this.queryColumnOrder = queryColumnOrder;
	}

	public String getQueryHour() {
		return queryHour;
	}

	public void setQueryHour(String queryHour) {
		this.queryHour = queryHour;
	}

	public String getQueryTableName_R_Serverbuild() {
		return queryTableName_R_Serverbuild;
	}

	public void setQueryTableName_R_Serverbuild(String queryTableName_R_Serverbuild) {
		this.queryTableName_R_Serverbuild = queryTableName_R_Serverbuild;
	}

	public String getQueryStartTimeR() {
		return queryStartTimeR;
	}

	public void setQueryStartTimeR(String queryStartTimeR) {
		this.queryStartTimeR = queryStartTimeR;
	}

	public String getQueryEndTimeR() {
		return queryEndTimeR;
	}

	public void setQueryEndTimeR(String queryEndTimeR) {
		this.queryEndTimeR = queryEndTimeR;
	}

	public long getQueryBizID() {
		return queryBizID;
	}

	public long getQueryParentId() {
		return queryParentId;
	}

	public void setQueryBizID(long queryBizID) {
		this.queryBizID = queryBizID;
	}

	public void setQueryParentId(long queryParentId) {
		this.queryParentId = queryParentId;
	}

	public String getQueryBizIdStr() {
		return queryBizIdStr;
	}

	public void setQueryBizIdStr(String queryBizIdStr) {
		this.queryBizIdStr = queryBizIdStr;
	}

	public String getQueryTableName_Province() {
		return queryTableName_Province;
	}

	public void setQueryTableName_Province(String queryTableName_Province) {
		this.queryTableName_Province = queryTableName_Province;
	}

	public String getQueryTableName_Biz() {
		return queryTableName_Biz;
	}

	public void setQueryTableName_Biz(String queryTableName_Biz) {
		this.queryTableName_Biz = queryTableName_Biz;
	}

	public Integer getQueryReportType() {
		return queryReportType;
	}

	public void setQueryReportType(Integer queryReportType) {
		this.queryReportType = queryReportType;
	}

	public String getQueryHostName() {
		return queryHostName;
	}

	public void setQueryHostName(String queryHostName) {
		this.queryHostName = queryHostName;
	}

}
