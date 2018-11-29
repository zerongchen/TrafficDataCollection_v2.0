package com.aotain.tdc.dto.common;

public class DicIpDbBean extends BaseDTO{
	private static final long serialVersionUID = -1179658348168445155L;
	private String startIp;
	private String endIp;
	private int countryId;
	private int provinceId;
	private int cityId;
	private int supplier;
	private int flowDirectionId;
	private int ipLevel;
	public String getStartIp() {
		return startIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public int getCountryId() {
		return countryId;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public int getSupplier() {
		return supplier;
	}
	public int getFlowDirectionId() {
		return flowDirectionId;
	}
	public int getIpLevel() {
		return ipLevel;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}
	public void setFlowDirectionId(int flowDirectionId) {
		this.flowDirectionId = flowDirectionId;
	}
	public void setIpLevel(int ipLevel) {
		this.ipLevel = ipLevel;
	}
}
