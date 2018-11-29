package com.aotain.tdc.dto.common;

public class PolicyDstIPPortBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private int strategyType;
	private long strategyId;
	private String dstIp;
	private String dstPort;
	public int getStrategyType() {
		return strategyType;
	}
	public long getStrategyId() {
		return strategyId;
	}
	public String getDstIp() {
		return dstIp;
	}
	public String getDstPort() {
		return dstPort;
	}
	public void setStrategyType(int strategyType) {
		this.strategyType = strategyType;
	}
	public void setStrategyId(long strategyId) {
		this.strategyId = strategyId;
	}
	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
	public void setDstPort(String dstPort) {
		this.dstPort = dstPort;
	}
}
