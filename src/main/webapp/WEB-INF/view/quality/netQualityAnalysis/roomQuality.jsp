<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="initUpTable"
	value="${ctx}/common/quality/roomQualityService/getTrendTableColumns/initTable.do"
	scope="request" />
<c:set var="initDnTable"
	value="${ctx}/common/quality/roomQualityService/getTableColumns/initTable.do"
	scope="request" />
<c:set var="getTrendTableColumnData"
	value="${ctx}/common/SERVERBUILD_FLOW_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="getTreeData" value="${ctx}/common/selectTreeData.do"
	scope="request" />
<c:set var="getCarrierOptionData"
	value="${ctx}/common/selectCarrier.do"
	scope="request" />
<c:set var="getPositionOptionData"
	value="${ctx}/common/selectSysPosition.do"
	scope="request" />
<c:set var="exportStatistic"
	value="${ctx}/quality/netQualityAnalysis/roomQuality/exportStatistic.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/quality/netQualityAnalysis/roomQuality/exportTrend.do"
	scope="request" />
<c:set var="initTrendTable"
	value="${ctx}/quality/netQualityAnalysis/roomQuality/initTrendTable.do"
	scope="request" />
<c:set var="getTableColumnData"
	value="${ctx}/common/SERVERBUILD_FLOW_STA/getTableColumnData.do"
	scope="page" />
<!DOCTYPE html>
<html lang="en">

<head>
<title>中移动流量采集监测系统-质量分析-机房质量</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css"
	href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<script type="text/javascript">
	var initUpTableUrl = '${initUpTable}', getTreeDataUrl = '${getTreeData}';
	var initDnTableUrl = '${initDnTable}',
	getTableColumnDataUrl = '${getTableColumnData}',
	getCarrierOptionDataUrl = '${getCarrierOptionData}',
	getPositionOptionDataUrl = '${getPositionOptionData}',
	initTrendTableUrl = '${initTrendTable}',

	updateColumnUserUrl = '${updateColumnUser}',
	exportStatisticUrl = '${exportStatistic}',
	
	getCarrierOptionDataUrl = '${getCarrierOptionData}',
	getPositionOptionDataUrl = '${getPositionOptionData}',
	
	getTrendTableColumnDataUrl = '${getTrendTableColumnData}',
	updateTrendColumnUserUrl = '${updateTrendColumnUser}',
	exportTrendUrl = '${exportTrend}',
	initLayerTrendTableUrl = '${initLayerTrendTable}',
	exportLayerTrendUrl = '${exportLayerTrend}';
</script>

</head>

<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con computer_room">
			<div class="frame">
				<div class="title">
					<i></i>
					<span class="blue">质量分析</span> / <span class="blue">网络质量分析</span>
					/ <span>机房质量</span>	
				</div>
				<div class="computer_room">
				<jsp:include page="/WEB-INF/view/quality/netQualityAnalysis/roomQualityTree.jsp" flush="true"></jsp:include>
				
					<div class="c-traffic fl" id="c-right">
				<form id="searchForm">
					<input type="text" style="display: none;" id="queryServerBuildId_1" name="queryServerBuildId"/>
					<input type="text" style="display: none;" id="queryServerRoomId_1" name="queryServerRoomId"/>
					<input type="text" style="display: none;" id="queryClassId_1" name="queryClassId"/>
					<input type="text" style="display: none;" id="queryFieldName_1" name="queryFieldName"/>
		            <div class="time_search">
		                <tdc:timeSelect domId="timeSelect" queryTableName="REPORT_SERVERBUILD_QUALITY" timePattern="yyyy-MM-dd HH:mm" type="3"/>
		                <!--  <div class="fr">
		                    <button class="search_button fr" id="searchBtn" type="button">查询</button>
		                </div>
		               <div class="fl option">
		                	<tdc:carrierSelect domId="sel_carrier" domName="queryCarrier" getCarrierOptionDataUrl="${getCarrierOptionData}"/>
		                    <tdc:sysPositionSelect domId="sel_position" domName="queryPosition" getPositionOptionDataUrl="${getPositionOptionData}"/>
                		</div> -->
		            </div>
				 </form>
				 <div class="regional">
            <div class="c-traffic fl">
					
						<div class="table-title">
							<a>全部节点质量趋势</a>
							 <div class="tabs">
								<form action="" method="get">
									<label><input name="Fruit" type="radio" value="" class="chart1" checked="checked" />图表 </label> 
									<label><input name="Fruit" type="radio" value="" class="list1" />列表 </label>
								</form>
								<sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_ROOMQUALITY_EXPORT">
								<button id="export" style="display:none;">导出列表</button>
								</sec:authorize>
							</div>
							<div class="indicators_two div1">
								<span>指标</span> 
								<select name="" id="chartleftoption">
								</select>
								
							</div>
							<div id="chart_a" style="width: 90%; height: 400px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
							<div id="bootstrap-table" >
								<table id="appTable_up"></table>
							</div>
						</div>
						
						
						<div class="table-title">
							<a>全部节点质量占比</a>	
							<div class="indicators_two div2">
								<span>指标</span> 
								<select name="" id="chartleftoption_1">
								</select>
							</div>	
							<div id="chart_add_dn" style=" margin-top:30px; width: 90%; height: 400px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>						
							<div class="w951">
                                <div class="block_sel">
                                	<sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_ROOMQUALITY_EXPORT">
                                    <button id="export1">导出列表</button>
                                    </sec:authorize>
                                </div>
                            </div>
							<table id="appTable_dn"></table>
						</div>
					</div>
						</div>
			</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${scriptPath}/view/quality/roomQuality_up.js" type="text/javascript"></script>
	<script src="${scriptPath}/view/quality/roomQuality_down.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$(".indicators_two").css("margin",'0 0 0 0');
			$(".chart1").on("click", function(){
				$(".chart1").parents(".table-title:eq(0)").find("#chart_a").show();
				$("#export").hide();
				//$(".indicators_two").show();
				$(".div1").show();
				$(".chart1").parents(".table-title:eq(0)").find("#bootstrap-table").hide();
				statisticsObject.refreshChart();
			});
			$(".list1").on("click", function(){
				$(".list1").parents(".table-title:eq(0)").find("#chart_a").hide();
				$("#export").show();
				//$(".indicators_two").hide();
				$(".div1").hide();
				$(".list1").parents(".table-title:eq(0)").find("#bootstrap-table").show();
				statisticsObject.refreshTable();
			});
			$(".chart1").click();
			$('#searchBtn').on('click', function() {
				var treeObj = $.fn.zTree.getZTreeObj("treeSelect");
				statisticsObject.search(treeObj);
				trendObject.search(treeObj);
			});
			
			$('#searchBtn').on('click', function() {
				var treeObj = $.fn.zTree.getZTreeObj("treeSelect");
				statisticsObject.search(treeObj);
				trendObject.search(treeObj);
			});
			
		/*	$("#${domId} a").on("click", function(e) {
				if($(this).html() != "自定义范围"){
					statisticsObject.search(treeObj);
					trendObject.search(treeObj);
				}
			});*/
			
			
		});
	</script>
	<script>
		$("#chart_cc").css('width', $("#chart_bb").width());
		$("#chart_dd").css('width', $("#chart_bb").width());
		$('.traffic_b').click(function() {
			$('#chart_bb').show();
			$('#chart_cc').hide();
			$('#chart_dd').hide();
			trendObject.refreshChart();
		})
		$('.traffic_c').click(function() {
			$('#chart_bb').hide();
			$('#chart_cc').show();
			$('#chart_dd').hide();
			trendObject.refreshChart();
		})
		$('.traffic_d').click(function() {
			$('#chart_bb').hide();
			$('#chart_cc').hide();
			$('#chart_dd').show();
			trendObject.refreshChart();
		})
	</script>
</body>

</html>