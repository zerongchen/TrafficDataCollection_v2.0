<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData" value="${ctx}/common/CALL_BILL/getTableColumnData.do" scope="page" />
<c:set var="updateColumnUser" value="${ctx}/common/CALL_BILL/updateColumnUser.do" scope="request" />
<c:set var="initTable" value="${ctx}/quality/userQualityAnalysis/callBill/initTable.do" scope="request" />
<c:set var="export" value="${ctx}/quality/userQualityAnalysis/callBill/export.do" scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>
	<title>中移动流量采集监测系统-质量分析-话单回溯查询</title>
	<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
	<tdc:res form="true" table="true"></tdc:res>
	<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
	<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
	<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
	<script type="text/javascript">
		var initTableUrl = '${initTable}',
		getTableColumnDataUrl = '${getTableColumnData}',
		updateColumnUserUrl = '${updateColumnUser}',
		exportUrl = '${export}';
	</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con quality_regional">
			<div class="frame">
				<div class="title">
					<i></i><span class="blue">CMNET质量</span> / <span class="blue">用户质量分析</span> / <span>话单回溯查询</span>
					<sec:authorize ifAllGranted="ROLE_BILL_BACKTRACKING_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize>
					<sec:authorize ifAllGranted="ROLE_BILL_BACKTRACKING_EXPORT">
					<button class="fr" id="export">导出</button>
					</sec:authorize>
				</div>
				<sec:authorize ifAllGranted="ROLE_BILL_BACKTRACKING_QUERY">
				<form id="searchForm">
		            <div class="time_search"   style = "display:none;">
		                <div class="fr">
		                    <button class="search_button fr" id="searchBtn" type="button">查询</button>
		                </div>
		                <div class="fl option">
							<div>
					            <label for="">查询日期：</label>
					            <input id="queryStartTime" name="queryStartTime" type="text" onfocus="WdatePicker({isShowClear: false,readOnly: true,errDealMode: 2,dateFmt: 'yyyy/MM/dd HH'});" />
					        </div>
					        <div>
					            <label for="">源IP：</label>
					            <input id="srcIp" name="querySrcIp" type="text"/>
					        </div>
					        <div>
					            <label for="">目的IP：</label>
					            <input id="destIp" name="queryDestIp" type="text"/>
					        </div>
					        <div>
					            <label for="">源端口：</label>
					            <input id="srcPort" name="querySrcPort" type="number"/>
					        </div>
					        <div>
					            <label for="">目的端口：</label>
					            <input id="destPort" name="queryDestPort" type="number"/>
					        </div>
					        <div>
					            <label for="">域名：</label>
					            <input id="domain" name="queryDomain" type="text"/>
					        </div>
					        <div>
					            <label for="">URL：</label>
					            <input id="url" name="queryUrl" type="text"/>
					        </div>
		                </div>
		            </div>
				</form>
				</sec:authorize>
				<div class="regional">
					<div class="c-traffic fl">
						<!-- 统计start -->
						<div id="statistical">
							<div class="table-title">
								<div class="box box-success">
									<div class="box-body">
										<table id="appTable_callbill"></table>
									</div>
								</div>
							</div>
						</div>
						<!-- 统计end -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
		var date = new Date();
		date.setDate(date.getDate()-1);
		$("#queryStartTime").val(date.format("yyyy/MM/dd h"));
		billObject.search();
		$('#searchBtn').on('click', function() {
			if ( (!$("#srcIp").val().IsIPAddress) || (!$("#destIp").val().IsIPAddress) ) {$.modal.alert("请填写正确的IP地址");return;}
			if (!$("#url").val().IsURL) {$.modal.alert("请填写正确的url");return;}
			if (!$("#domain").val().IsDomain) {$.modal.alert("请填写正确的域名");return;}
			billObject.search();
		});
	});
	</script>
	<script src="${scriptPath}/view/quality/callBillQuery.js" type="text/javascript"></script>
</body>

</html>