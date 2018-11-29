package com.aotain.common.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.aotain.common.authService.UserserviceStub.ServiceReturnDTO;

public class Userbean implements Serializable{
	private static final long serialVersionUID = -5518352562943645686L;
	// 用户ID
	private Long userId;
	// 用户名
	private String userName;
	// 用户角色(与roleid关联)
	private Integer userType;
	// 用户真实姓名
	private String fullName;
	// 密码
	private String password;
	// 描述
	private String userDescription;
	// 电子邮件
	private String email;
	// 手机号码
	private String mobile;
	// 状态:0-暂停；1-正常
	private Integer status;
	// 创建时间
	private Date creationDate;
	// 最近修改时间
	private Date updateDate;
	// 所属IDCID
	private String idcId;
	// 创建者id
	private Long parentUserId;
	// 所属省份ID
	private Integer provinceId;
	// 所属城市ID
	private Integer cityId;
	// 所属县ID
	private Integer countyId;
	// 成功登陆前失败次数(超过5次锁定，没有超过5次成功时清0)
	private Integer loginFailureNumbers;
	// 最后登录时间
	private Integer dealFlag;
	// 是否删除：1-是；0-否
	private String lastLoginDate;
	private String clientIP;
	private String department;
	private Integer userlevel;
	private Integer loginCount;

	private List<String> rightList;
	
	private List<String> menuList;
	
	private ServiceReturnDTO serviceReturnDTO;

	public ServiceReturnDTO getServiceReturnDTO() {
		return serviceReturnDTO;
	}

	public void setServiceReturnDTO(ServiceReturnDTO serviceReturnDTO) {
		this.serviceReturnDTO = serviceReturnDTO;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(Integer userlevel) {
		this.userlevel = userlevel;
	}
	
	public List<String> getRightList() {
		return rightList;
	}

	public void setRightList(List<String> rightList) {
		this.rightList = rightList;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getIdcId() {
		return idcId;
	}

	public void setIdcId(String idcId) {
		this.idcId = idcId;
	}

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Integer getLoginFailureNumbers() {
		return loginFailureNumbers;
	}

	public void setLoginFailureNumbers(Integer loginFailureNumbers) {
		this.loginFailureNumbers = loginFailureNumbers;
	}

	public Integer getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(Integer dealFlag) {
		this.dealFlag = dealFlag;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

}
