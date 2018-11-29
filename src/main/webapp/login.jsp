<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
	<tdc:res form="false" table="false"></tdc:res>
	<link href="${cssPath}/style-login.css" rel="stylesheet" type="text/css" />
	<script src="${scriptPath}/view/login.js" type="text/javascript"></script>
    <title>流量采集监测系统</title>
</head>
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
    <div class="fixed login">
        <div class="title">
            <img src="${imagePath}/logo.png">
            <p>中移动互联网公司</p>
        </div>
        <div class="frame">
        	<form id="loginform">
	            <div class="head">
	                <p>欢迎，请先登录</p>
	                <i class="logo"></i>
	            </div>
	            <div class="con">
	                <div class="login_text">
	                    <label><i></i></label>
	                    <span>账号</span>
	                    <input name="username" id="j_username" type="text" class="w210" placeholder="username">
	                </div>
	                <p></p>
	                <div class="login_password">
	                    <label><i></i></label>
	                    <span>密码</span>
	                    <input name="password" id="j_password" type="password" class="w210" placeholder="password">
	                </div>
	                <p></p>
	                <div class="login_validation">
	                    <label><i></i></label>
	                    <span>验证码</span>
	                    <input name="checkCode" id="checkCode" type="text" class="w120" placeholder="Captcha">
	                    <img  onclick="reloadImage()" src="${imagePath}/login_validation.jpg" alt="加载中..." id="checkcode">
	                    <a href=""  onclick="reloadImage();return false;">换一张</a>
	                </div>
	                <p id="error"></p>
	            </div>
	            <input class="login_button"  id="loginButton" type="submit" value="登录"/>
            </form>
        </div>
    </div>
    <div class="footer">
       
    </div>
</body>
</html>