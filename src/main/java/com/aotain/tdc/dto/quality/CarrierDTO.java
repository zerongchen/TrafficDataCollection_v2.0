package com.aotain.tdc.dto.quality;

public class CarrierDTO {
private Long statTime;//统计时间
private Long carrierId;//运营商
private String carrierName;
private double qualityScore;//质量分数
private double flowUpSpeed;//上行速率
private double flowDnSpeed;//下行速率
private Long delayTimeConnect;//连接时延
private Long serverDelay;//服务端时延
private Long clientDelay;//客户端时延
private Long connectCount;//连接次数
private Long successConnectCount;//连接成功次数
private Long failConnectCount;//连接失败数
private Long overtiemConnectCount;//连接超时数
private double successConnectRate;//连接成功率
private double failConnectRate;//连接失败率
private double overtimeConnectRate;//连接超时率
private double retranspackageUpRate;//上行重传率
private double retranspackageDnRate;//下行重传率
private double sessionUpSpeed;//上行会话速率
private double sessionDnSpeed;//上行会话速率
private double midpackageSpeedDn;//中包下行速率
private double bigpackageSpeedDn;//大包下行速率
public Long getStatTime() {
	return statTime;
}
public void setStatTime(Long statTime) {
	this.statTime = statTime;
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
public double getQualityScore() {
	return qualityScore;
}
public void setQualityScore(double qualityScore) {
	this.qualityScore = qualityScore;
}
public double getFlowUpSpeed() {
	return flowUpSpeed;
}
public void setFlowUpSpeed(double flowUpSpeed) {
	this.flowUpSpeed = flowUpSpeed;
}
public double getFlowDnSpeed() {
	return flowDnSpeed;
}
public void setFlowDnSpeed(double flowDnSpeed) {
	this.flowDnSpeed = flowDnSpeed;
}
public Long getDelayTimeConnect() {
	return delayTimeConnect;
}
public void setDelayTimeConnect(Long delayTimeConnect) {
	this.delayTimeConnect = delayTimeConnect;
}
public Long getServerDelay() {
	return serverDelay;
}
public void setServerDelay(Long serverDelay) {
	this.serverDelay = serverDelay;
}
public Long getClientDelay() {
	return clientDelay;
}
public void setClientDelay(Long clientDelay) {
	this.clientDelay = clientDelay;
}
public Long getConnectCount() {
	return connectCount;
}
public void setConnectCount(Long connectCount) {
	this.connectCount = connectCount;
}
public Long getSuccessConnectCount() {
	return successConnectCount;
}
public void setSuccessConnectCount(Long successConnectCount) {
	this.successConnectCount = successConnectCount;
}
public double getSuccessConnectRate() {
	return successConnectRate;
}
public void setSuccessConnectRate(double successConnectRate) {
	this.successConnectRate = successConnectRate;
}
public double getFailConnectRate() {
	return failConnectRate;
}
public void setFailConnectRate(double failConnectRate) {
	this.failConnectRate = failConnectRate;
}
public double getOvertimeConnectRate() {
	return overtimeConnectRate;
}
public void setOvertimeConnectRate(double overtimeConnectRate) {
	this.overtimeConnectRate = overtimeConnectRate;
}
public double getRetranspackageUpRate() {
	return retranspackageUpRate;
}
public void setRetranspackageUpRate(double retranspackageUpRate) {
	this.retranspackageUpRate = retranspackageUpRate;
}
public double getRetranspackageDnRate() {
	return retranspackageDnRate;
}
public void setRetranspackageDnRate(double retranspackageDnRate) {
	this.retranspackageDnRate = retranspackageDnRate;
}
public double getMidpackageSpeedDn() {
	return midpackageSpeedDn;
}
public void setMidpackageSpeedDn(double midpackageSpeedDn) {
	this.midpackageSpeedDn = midpackageSpeedDn;
}
public double getBigpackageSpeedDn() {
	return bigpackageSpeedDn;
}
public void setBigpackageSpeedDn(double bigpackageSpeedDn) {
	this.bigpackageSpeedDn = bigpackageSpeedDn;
}
public Long getFailConnectCount() {
	return failConnectCount;
}
public void setFailConnectCount(Long failConnectCount) {
	this.failConnectCount = failConnectCount;
}
public Long getOvertiemConnectCount() {
	return overtiemConnectCount;
}
public void setOvertiemConnectCount(Long overtiemConnectCount) {
	this.overtiemConnectCount = overtiemConnectCount;
}
public double getSessionUpSpeed() {
	return sessionUpSpeed;
}
public void setSessionUpSpeed(double sessionUpSpeed) {
	this.sessionUpSpeed = sessionUpSpeed;
}
public double getSessionDnSpeed() {
	return sessionDnSpeed;
}
public void setSessionDnSpeed(double sessionDnSpeed) {
	this.sessionDnSpeed = sessionDnSpeed;
}


}
