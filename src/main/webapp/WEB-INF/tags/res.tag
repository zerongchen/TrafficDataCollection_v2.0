<%@tag pageEncoding="UTF-8" body-content="empty" %>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<%@ attribute name="form" type="java.lang.Boolean" %>
<%@ attribute name="table" type="java.lang.Boolean" %>

<!-- 公共部分 -->
<script src="${scriptPath}/jQuery-2.1.4.min.js" type="text/javascript"></script>
<script src="${scriptPath}/jquery.validate.min.js" type="text/javascript"></script>
<script src="${scriptPath}/validate-util.js" type="text/javascript"></script>
<script src="${scriptPath}/jquery-ui.min.js"></script>
<script src="${scriptPath}/date.format.js"></script>
<script src="${scriptPath}/jquery.page.js" type="text/javascript"></script>
<script src="${scriptPath}/jquery.pagination.js" type="text/javascript"></script>
<script src="${scriptPath}/jquery.combo.select.js" type="text/javascript"></script>
<script src="${scriptPath}/echarts.min.js" type="text/javascript"></script>
<script src="${scriptPath}/china.js"></script>
<script src="${scriptPath}/datePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${scriptPath}/areacode-util.js" type="text/javascript"></script>
<script src="${scriptPath}/String.js" type="text/javascript"></script>
<script src="${scriptPath}/util.js" type="text/javascript"></script>
<script src="${scriptPath}/RSA.js" type="text/javascript"></script>
<script src="${scriptPath}/BigInt.js" type="text/javascript"></script>
<script src="${scriptPath}/Barrett.js" type="text/javascript"></script>
<script src="${scriptPath}/md5.js" type="text/javascript"></script>
<script src="${scriptPath}/common-util.js" type="text/javascript"></script>
<script src="${scriptPath}/ajaxfileupload.js" type="text/javascript"></script>
<script src="${scriptPath}/common/modal.js" type="text/javascript"></script>
<script src="${scriptPath}/postDownload.js" type="text/javascript"></script>
<script src="${scriptPath}/all.js" type="text/javascript"></script>
<script src="${scriptPath}/layer/layer.js" type="text/javascript"></script>
<script src="${res}/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<link href="${res}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="${res}/js/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${res}/js/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${res}/js/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${res}/js/zTree_v3-master/js/jquery.ztree.exedit.js"></script>

<script>
	/**
	*js全局变量
	*/
	/**
	*url
	*/
	var ctx = "${ctx}",
		url_login = "${ctx}/login.do",
		url_welcome = "${ctx}/welcome.do";
	/**
	*模态框id
	*/
	var modal_confirm_id = "common_modal_confirm_code00",
		modal_alert_large_id = "common_modal_alert_code01",
		modal_alert_small_id = "common_modal_alert_code02";
	/*
	*ajax请求的contentType
	*/
	var clientType = "application/x-www-form-urlencoded; charset=utf-8";
</script>

<!-- 表单 -->
<c:if test="${form}">
	<script src="${scriptPath}/datePicker/WdatePicker.js"></script>
	<script src="${scriptPath}/ajaxfileupload.js"></script>
	<link rel="stylesheet" type="text/css" href="${scriptPath}/mutiselect/jquery.multiselect.css" />
	<script type="text/javascript" src="${scriptPath}/mutiselect/jquery.multiselect.js"></script>
</c:if>
<!-- 列表 -->
<c:if test="${table}">
	<script src="${scriptPath}/bootstrap-table.js"></script> 
	<script src="${scriptPath}/bootstrap-table-zh-CN.js"></script>
	<link href="${cssPath}/bootstrap-table.css" rel="stylesheet" type="text/css" />
	<script src="${scriptPath}/table.js"></script>
	<script src="${scriptPath}/tdcTable.js"></script>
</c:if>
