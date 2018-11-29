<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData"
	value="${ctx}/common/QUALITY_REGIONCARRIER_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="updateColumnUser"
	value="${ctx}/common/QUALITY_REGIONCARRIER_TREND/updateColumnUser.do"
	scope="request" />
<c:set var="initTable"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/initTable.do"
	scope="request" />
	
<c:set var="getStatisticsTableColumnData"
	value="${ctx}/common/QUALITY_REGIONCARRIER_STA/getTableColumnData.do"
	scope="page" />
<c:set var="updateStatisticsColumnUser"
	value="${ctx}/common/QUALITY_REGIONCARRIER_STA/updateColumnUser.do"
	scope="request" />
<c:set var="initStatisticsTable"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/initStatisticsTable.do"
	scope="request" />
<c:set var="getPositionOptionData"
	value="${ctx}/common/selectSysPosition.do"
	scope="request" />
<c:set var="getCarrierMultSelectData"
	value="${ctx}/common/selectCarrier.do"
	scope="request" />

<c:set var="exportStatistic"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/exportStatistic.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/exportTrend.do"
	scope="request" />
<c:set var="initLayerTrendTable"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/initLayerTrendTable.do"
	scope="request" />
<c:set var="exportLayerTrend"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/exportLayerTrend.do"
	scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>地域运营商质量</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<script type="text/javascript">
	var getStatisticsTableColumnDataUrl = '${getStatisticsTableColumnData}',
	initStatisticsTableUrl = '${initStatisticsTable}',
	updateStatisticsColumnUserUrl = '${updateStatisticsColumnUser}',
	exportStatisticUrl = '${exportStatistic}';
	var getTableColumnDataUrl = '${getTableColumnData}',
	initTableUrl = '${initTable}',
	updateColumnUserUrl = '${updateColumnUser}',
	exportTrendUrl = '${exportTrend}';
	
	var initLayerTrendTableUrl = '${initLayerTrendTable}',
	exportLayerTrendUrl = '${exportLayerTrend}';
	
	var getPositionOptionDataUrl = '${getPositionOptionData}',
	getCarrierMultSelectUrl = '${getCarrierMultSelectData}';
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
                <span class="blue">质量分析</span> /
                <span class="blue">网络质量分析</span> /
                <span>地域运营商质量</span>
                <!-- <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAOPERATORQUALITY_QUERY">
                <button class="fr drop_down">查询</button>
                </sec:authorize>
                <button class="fr">导出</button> -->
            </div>
            	<div class="computer_room">
      <div class="c-nav" id="c-height">
        <div>
		<ul id="treeSelect" class="ztree"></ul>
		</div>
      </div>  <div class="c-traffic fl" id = "c-right" >
            <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAOPERATORQUALITY_QUERY">
            <form id="searchForm">
            	<input type="reset" style="display:none;" id="resetSearchForm"/>
            	<input type = "hidden"  name = "queryBizIdStr"    value = "-1"  />
		        <div class="time_search" >
                <tdc:timeSelect domId="timeSelect" queryTableName="REPORT_CARRIER_PROVINCE_QUALITY" timePattern="yyyy-MM-dd HH:mm" type="3"/>
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
                            <a>质量统计</a>
                            <div class="indicators_two">
									<span>指标</span> 
									<select name="" id="chartleftoption">
									</select>
									<div class="fr">
										<form action="" id="chartOrder">
											上升 <input type="radio" name="trend" value="asc" checked="checked"> 下降 <input
												type="radio" name="trend" value="desc">
										</form>
									</div>
								</div>
                            	<div class="w100">
                            		<div id="chartStatistics" style="width:808px;height:350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
								</div>
                        </div>
                        <div class="table-title">
                            <a>统计报表</a>
                            <div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAOPERATORQUALITY_EXPORT">
	                                    <button id="export_statistic">导出</button>
	                                </sec:authorize>
	                                </div>
	                          </div>
                            <div class="box box-success">
									<div class="box-body">
										<table id="statisticalTable"></table>
									</div>
                    	 	</div>
                        </div>
                        </div>
                        <!-- 统计end -->    
                        <!-- 趋势start -->
                    	<div id="trend" style="display:none;">
                    		<div class="table-title">
                            <a>质量趋势</a>
                            <div class="indicators_two">
									<span>指标</span> 
									<select name="" id="chartTrendOption">
									</select>
								</div>
                            <div class="w100">
                            <div id="chartTrend" style="width:95%;height:350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                            </div>
                            </div>
                            <div class="table-title">
                            <a>趋势报表</a>
                            	<div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAOPERATORQUALITY_EXPORT">
	                                    <button id="export_trend">导出</button>
	                                </sec:authorize>
	                                </div>
	                            </div>
                            	<div class="box box-success">
									<div class="box-body">
										<table id="trendTable"></table>
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
		 	//statisticsObject.searchCharts();
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
	//$("#chart_d").css('width', $("#div_01").width()); 
	//$("#chart_4").css('width', "1100px"); 
	$(function() {
		
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
			trendObject.search();
		});
	    $('.trend').click(function() {
	        $(this).addClass('check').siblings().removeClass('check');
	        $('#trend').show();
	        $('#statistical').hide();
	        trendObject.refreshChart();
	        trendObject.refreshTable();
	    });
	    $('.statistical').click(function() {
	        $(this).addClass('check').siblings().removeClass('check');
	        $('#statistical').show();
	        $('#trend').hide();
	        statisticsObject.refreshChart();
	        statisticsObject.refreshTable();
	    })
		
		
	    
	});
	</script>
	<script src="${scriptPath}/view/quality/regionCarrierQuality_statistics.js" type="text/javascript"></script>
	<script src="${scriptPath}/view/quality/regionCarrierQuality_trend.js" type="text/javascript"></script>
	<jsp:include page="/WEB-INF/view/quality/netQualityAnalysis/layer/regionCarrierQualityLayer.jsp" flush="true"></jsp:include>
</body>
</html>