<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="pre" value="${ctx}/serviceConfiguration/businessArchitecture" scope="page" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-业务配置-业务架构配置</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<style type="text/css">
.error {
	color: red
}
</style>
</head>
<script type="text/javascript">
	var selectBusinessTree = '${pre}/selectBusinessTree.do';
</script>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
	<div class="con">
		<div class="frame">
			<div class="title">
				<i></i> <span class="blue">业务配置</span> / <span>业务架构配置</span>
				<sec:authorize ifAllGranted="ROLE_QUERY_WEB_BUSINESS_ARCHITECTURE">
					<button class="fr drop_down">查询</button>
				</sec:authorize>
			</div>
			<div class="computer_room">
				<div class="c-nav">
					<ul id="treeSelect" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			var reqData = new Object();
			$.ajax({
				url : selectBusinessTree,
				type : "POST",
				data : reqData,
				contentType : clientType,
				dataType : "json"
			});
		});
	</script>
</body>

</html>
