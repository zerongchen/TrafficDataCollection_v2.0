<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
	<div class="head fixed">
		<!-- <div class="logo_h1 fl">
			<div class="logo">
				<i></i>
			</div>
		</div> -->
	<div class="logo_h1 fl">
   	<div class="logo"> <i></i> </div>
	<div class="sidebar-collapse"><img src="${imagePath}/icon_collapse.png" title="点击隐藏左侧菜单"></div>
 	</div>
		<c:out escapeXml="false" value="${headMenu}"/>
		<div class="log fr">
			<%-- <a onclick="javascript:window.open('${ctx}/go2passport.do')" style="cursor:pointer;"><sec:authentication property="principal.account.fullName"/></a> <a href="${ctx}/logout.do" target="_parent">退出</a> --%>
			<a href="#" style="cursor:pointer;"><sec:authentication property="principal.account.fullName"/></a> <a href="${ctx}/logout.do" target="_parent">退出</a>
		
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$(".sidebar-collapse img").css("cursor","pointer").bind('click',function(){
			if($(".sidel").css("display") == "block"){
				$(".sidel").hide();
				$(".con .frame").css("padding-left","0px");
				$("#c-right").css("width", document.documentElement.clientWidth-230-10-18);
				if ($('#statisticalTable').length > 0) {
					$("#statisticalTable").css("width", document.documentElement.clientWidth-230-10-18-50);
				}
				if ($('#trendTable').length > 0) {
					$("#trendTable").css("width", document.documentElement.clientWidth-230-10-18-50);
				}
				if ($('#appTable_up').length > 0) {
					$("#appTable_up").css("width", document.documentElement.clientWidth-230-10-18-50);
				}
				if ($('#appTable_dn').length > 0) {
					$("#appTable_dn").css("width", document.documentElement.clientWidth-230-10-18-50);
				}
				if ($('#appTable_statistical').length > 0) {
					$("#appTable_statistical").css("width", document.documentElement.clientWidth-230-10-18-50);
				}
				if ($('#appTable_trend').length > 0) {
					$("#appTable_trend").css("width", document.documentElement.clientWidth-230-10-18-50);
				}
				//statisticsObject.refreshChart();
			}else{
				$(".sidel").show();
				$(".con .frame").css("padding-left","230px");
				$("#c-right").css("width", document.documentElement.clientWidth-230-230-10-18);
				if ($('#statisticalTable').length > 0) {
					$("#statisticalTable").css("width", document.documentElement.clientWidth-230-230-10-18-50);
				}
				if ($('#trendTable').length > 0) {
					$("#trendTable").css("width", document.documentElement.clientWidth-230-230-10-18-50);
				}
				if ($('#appTable_up').length > 0) {
					$("#appTable_up").css("width", document.documentElement.clientWidth-230-230-10-18-50);
				}
				if ($('#appTable_dn').length > 0) {
					$("#appTable_dn").css("width", document.documentElement.clientWidth-230-230-10-18-50);
				}
				if ($('#appTable_statistical').length > 0) {
					$("#appTable_statistical").css("width", document.documentElement.clientWidth-230-230-10-18-50);
				}
				if ($('#appTable_trend').length > 0) {
					$("#appTable_trend").css("width", document.documentElement.clientWidth-230-230-10-18-50);
				}
				//statisticsObject.refreshChart();
			}
			return false;
		});
	});
	</script>
	<%-- <script src="${scriptPath}/view/traffic/trafficMonitor/bizTraffic_statistics.js" type="text/javascript"></script> --%>