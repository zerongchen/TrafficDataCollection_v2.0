<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION/getTableColumnData.do"
	scope="page" />
<c:set var="updateColumnUser"
	value="${ctx}/common/FLOW_DIRECTION/updateColumnUser.do"
	scope="request" />
<c:set var="getTrendTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="updateTrendColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_TREND/updateColumnUser.do"
	scope="request" />
<c:set var="initTable"
	value="${ctx}/quality/netQualityAnalysis/areaQuality/initTable.do"
	scope="request" />
<c:set var="initTrendTable"
	value="${ctx}/quality/netQualityAnalysis/areaQuality/initTrendTable.do"
	scope="request" />
<c:set var="getCarrierOptionData"
	value="${ctx}/common/selectCarrier.do"
	scope="request" />
<c:set var="getPositionOptionData"
	value="${ctx}/common/selectSysPosition.do"
	scope="request" />
<c:set var="exportStatistic"
	value="${ctx}/quality/netQualityAnalysis/areaQuality/exportStatistic.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/quality/netQualityAnalysis/areaQuality/exportTrend.do"
	scope="request" />
<c:set var="initLayerTrendTable"
	value="${ctx}/quality/netQualityAnalysis/areaQuality/initLayerTrendTable.do"
	scope="request" />
<c:set var="exportLayerTrend"
	value="${ctx}/quality/netQualityAnalysis/areaQuality/exportLayerTrend.do"
	scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>
<title>中移动流量采集监测系统-质量分析-地域质量</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<script type="text/javascript">
	var initTableUrl = '${initTable}',
	getTableColumnDataUrl = '${getTableColumnData}',
	updateColumnUserUrl = '${updateColumnUser}',
	exportStatisticUrl = '${exportStatistic}',
	
	getCarrierOptionDataUrl = '${getCarrierOptionData}',
	getPositionOptionDataUrl = '${getPositionOptionData}',
	
	initTrendTableUrl = '${initTrendTable}',
	getTrendTableColumnDataUrl = '${getTrendTableColumnData}',
	updateTrendColumnUserUrl = '${updateTrendColumnUser}',
	exportTrendUrl = '${exportTrend}',
	initLayerTrendTableUrl = '${initLayerTrendTable}';
	exportLayerTrendUrl = '${exportLayerTrend}';
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
					<i></i>
					<span class="blue">质量分析</span> / <span class="blue">网络质量分析</span>
					/ <span>地域质量</span>
				</div>
				<div class="computer_room">
      <div class="c-nav" id="c-height">
        <div>
		<ul id="treeSelect" class="ztree"></ul>
		</div>
      </div>
      <div class="c-traffic fl" id = "c-right" >
      <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAQUALITY_QUERY">
				<form id="searchForm">
				
					<input type="reset" style="display:none;" id="resetSearchForm"/>
					<input type = "hidden"  name = "queryBizIdStr"    value = "-1"  />
		            <div class="time_search"   >
						<tdc:timeSelect domId="timeSelect" queryTableName="REPORT_PROVINCE_QUALITY" timePattern="yyyy-MM-dd HH:mm" type="3"/>
		            </div>
				</form>
				</sec:authorize>
			 <div class="regional">
            <div class="c-traffic fl">
						<div class="disb">
							<button class="statistical check">统计</button>
							<button class="trend">趋势</button>
						</div>
						<!-- 统计start -->
						<div id="statistical">
							<div class="table-title oh">
								<a href="#">质量统计</a>
							<div class="indicators_two"> <span>指标:</span>
									<select name="" id="chartleftoption">
									</select>
									 <div class="fr">
										<form action="" id="chartOrder">
											上升 <input type="radio" name="trend" value="asc" checked="checked"> 下降 <input
												type="radio" name="trend" value="desc">
										</form>
									</div> 
								</div>
								
							<!-- 	<div class="w100">
									<div id="chart_left"
										style="float: left; margin: 10px auto; width: 65%; height: 520px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
									<div 
										style="float: left; margin: 10px auto; width: 35%; height: 500px; overflow-y:auto;">
										<div id="chart_right"
										style="float: left; margin: 10px auto; width: 100%; height: 1230px;-webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);">
									</div>
									</div>
								</div> -->
								   <div id = "div_01">
                            <div id="page01">
                                <div id="chart_left" style="float:left;width:65%;height:520px; top:10px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                                <div style="float:left;width:35%;height:500px;  overflow-y:auto;">
                                	<div id="chart_right" style="float: left; margin: 10px auto; width: 100%; height: 1230px;-webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);">
									</div>
                                </div>
                            </div>
                            <div id="page02" class="w95" style="display:none">
                                chart02
                            </div>
                            <div id="page03" class="w95" style="display:none">
                                chart03
                            </div>
                        </div>
								
								
							</div>
							<div class="table-title">
								<a>统计报表</a>
								<div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAQUALITY_EXPORT">
	                                    <button id="export_statistic">导出</button>
	                                </sec:authorize>
	                                </div>
	                            </div>
								<div class="box box-success">
									<div class="box-body">
										<table id="appTable_statistical"></table>
									</div>
								</div>
							</div>
						</div>
						<!-- 统计end -->
						<!-- 趋势start -->
						<div id="trend" style="display: none;">
							<div class="table-title">
								<a>质量趋势</a>
								<div class="indicators_two">
									<span>指标</span> 
									<select name="" id="chartdoption">
									</select>
								</div>
								<div id="chart_d" style="width: 95%; height: 350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
							</div>
							<div class="table-title">
								<a>趋势报表</a>
								<div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAQUALITY_EXPORT">
	                                    <button id="export_trend">导出</button>
	                                </sec:authorize>
	                                </div>
	                            </div>
								<div class="box box-success">
									<div class="box-body">
										<table id="appTable_trend"></table>
									</div>
								</div>
							</div>
						</div>
						<!-- 趋势end -->
					</div>
				</div>
				</div>
		</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	var setting = {
			edit: {
				enable: false,
				showRenameBtn: false
			},
			view:{
				showLine: false,
				showIcon: showIconForTree
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick : treeOnClick
			}
		};
		function showIconForTree(treeId, treeNode) {
			return treeNode.isParent;
		};
		function treeOnClick(e, treeId, treeNode){
			var ids=[];
			if(treeNode.level == 0 || treeNode.isParent){
				var nodes = treeNode.children;
				for(var i = 0;i< nodes.length;i++){
					ids.push(nodes[i].id);
				}
			}else{
				ids.push(treeNode.id);	
			}
			$("input[name='queryBizIdStr']").val(ids);
			
			statisticsObject.search();
		 	statisticsObject.searchCharts();
			trendObject.search();
		}
		
		function getChildren(ids,treeNode){
			ids.push(treeNode.id);
			if (treeNode.isParent){
				for(var obj in treeNode.children){
					getChildren(ids,treeNode.children[obj]);
				}
			}
			return ids;
		}

	</script>
	<script type="text/javascript">
	$(function() {
		$("input[name='queryBizIdStr']").val("-1");
		//$("#c-right").css("width", document.documentElement.clientWidth-475);
		$("#c-right").css("width", document.documentElement.clientWidth-230-230-10-18);
			var zNodes  =  statisticsObject.getAllNodes();
			$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
			//$("#c-height").css("height","100%");
			$('.c-nav').css('height',$(window).height()-87);
			var $treeObj = $.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
			$treeObj.selectNode($treeObj.getNodes()[0], false, false);
			
		statisticsObject.search();
		trendObject.search();
		$('#searchBtn').on('click', function() {
			statisticsObject.search();
			statisticsObject.refreshTable();
			trendObject.search();
		});
	    $('.trend').click(function() {
	        $(this).addClass('check').siblings().removeClass('check');
	        $('#trend').show();
	        $('#statistical').hide();
	        statisticsObject.refreshTable();
	        trendObject.refreshChart();
	    });
	    $('.statistical').click(function() {
	        $(this).addClass('check').siblings().removeClass('check');
	        $('#statistical').show();
	        $('#trend').hide();
	        statisticsObject.refreshTable();
	        statisticsObject.refreshChart();
	    });
	});
	</script>
	<script src="${scriptPath}/view/quality/areaQuality_statistics.js" type="text/javascript"></script>
	<script src="${scriptPath}/view/quality/areaQuality_trend.js" type="text/javascript"></script>
	<jsp:include page="/WEB-INF/view/quality/netQualityAnalysis/layer/areaQualityLayer.jsp" flush="true"></jsp:include>
</body>

</html>