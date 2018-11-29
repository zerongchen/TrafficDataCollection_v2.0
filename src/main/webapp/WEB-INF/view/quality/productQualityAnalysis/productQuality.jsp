<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_PORDUCT_STA/getTableColumnData.do"
	scope="page" />
<c:set var="updateColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_PORDUCT_STA/updateColumnUser.do"
	scope="request" />
	<c:set var="getTrendTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_PORDUCT_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="updateTrendColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_PORDUCT_TREND/updateColumnUser.do"
	scope="request" />
<c:set var="initTable"
	value="${ctx}/quality/productQualityAnalysis/productQuality/initTable.do"
	scope="request" />
<c:set var="initTrendTable"
	value="${ctx}/quality/productQualityAnalysis/productQuality/initTrendTable.do"
	scope="request" />
<c:set var="getCarrierOptionData"
	value="${ctx}/common/selectCarrier.do"
	scope="request" />
<c:set var="getPositionOptionData"
	value="${ctx}/common/selectSysPosition.do"
	scope="request" />
<c:set var="exportStatistic"
	value="${ctx}/quality/productQualityAnalysis/productQuality/exportStatistic.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/quality/productQualityAnalysis/productQuality/exportTrend.do"
	scope="request" />
<c:set var="pageurl" value="${ctx}/quality/productQualityAnalysis/productQuality/index.do" scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>
<title>中移动流量采集监测系统-质量分析-业务质量</title>
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
	pageurl = '${pageurl}';
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
					<span class="blue">质量分析</span> / <span class="blue">业务质量分析</span>
					/ <span>业务质量</span>
					<!--<sec:authorize ifAllGranted="ROLE_QUALITY_PRODUCT_ANALYSIS_PRODUCT_QUALITY_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize>
					 <button class="fr">导出</button> -->
				</div>
				 <div class="computer_room">
				<div class="c-nav" id="c-height">
				<div>
				<ul id="treeSelect" class="ztree"></ul>
				</div>
   			   </div>
                <div class="c-traffic fl" id = "c-right">
				<sec:authorize ifAllGranted="ROLE_QUALITY_PRODUCT_ANALYSIS_PRODUCT_QUALITY_QUERY">
				<form id="searchForm">
					<input type="hidden" name="queryFieldName" id="queryFieldName" value="BIZ_ID"/>
		           <input type = "hidden"  name = "queryBizIdStr"    value = "-1"  />
		            <div class="time_search" >
		                <tdc:timeSelect domId="timeSelect" queryTableName="REPORT_BIZ_FLOW" timePattern="yyyy-MM-dd HH:mm" type="3"/>
		             
		            </div>
				</form>
				</sec:authorize>
				 <div class="regional">
            <div class="c-traffic fl">
						<div class="disb" >
							<button class="statistical check">统计</button>
							<button class="trend">趋势</button>
						</div>
						<!-- 统计start -->
						<div id="statistical">
							<!--  <div class="table-title oh" >
								<a href="#">综合质量</a>
								<div class="w100">
                            	<div id="chart_a" style="width:95%;height:300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
								</div>
							</div>-->
							<div class="table-title oh">
								<a href="#">业务质量排名</a>
								<div class="indicators_two">
									<span>指标</span> 
									<select name="" id="chartleftoption">
									</select>
								</div>
								<div class="w100">
                            		<div id="chart_b" style="width:808px;height:400px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
								</div>
							</div>
							<div class="table-title">
								<a>统计报表</a>
								<div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_QUALITY_PRODUCT_ANALYSIS_PRODUCT_QUALITY_EXPORT">
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
	                                <sec:authorize ifAllGranted="ROLE_QUALITY_PRODUCT_ANALYSIS_PRODUCT_QUALITY_EXPORT">
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
		 	statisticsObject.refreshChart();
			trendObject.search();;
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
		//$("#c-right").css("width", document.documentElement.clientWidth-475);
		$("#c-right").css("width", document.documentElement.clientWidth-230-230-10-18);
			var zNodes  =  statisticsObject.getAllNodes();
			$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
			//$("#c-height").css("height","100%");
			$('.c-nav').css('height',$(window).height()-87);
			var $treeObj = $.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
			$treeObj.selectNode($treeObj.getNodes()[0], false, false);
			
		if('${queryFieldName}'.length > 0){
			$("#queryFieldName").val('${queryFieldName}');
		}
		if('${CATALOGID}'.length > 0){
			$(".time_search").show();
			selectCatalog(function(){
				$("#catalog").val('${CATALOGID}');
				if('${CLASSID}'.length > 0){
					selectClass('${CATALOGID}', function(){
						$("#class").val('${CLASSID}');
						if('${PRODUCTID}'.length > 0){
							selectProduct('${CATALOGID}','${CLASSID}', function(){
								$("#product").val('${PRODUCTID}');
								if('${MODULEID}'.length > 0){
									selectModule('${CATALOGID}','${CLASSID}','${PRODUCTID}', function(){
										$("#module").val('${MODULEID}');
									});
								}else{
									statisticsObject.search();
									trendObject.search();
								}
							});
						}else{
							statisticsObject.search();
							trendObject.search();
						}
					});
				}else{
					statisticsObject.search();
					trendObject.search();
				}
			});
		}else{
			statisticsObject.search();
			trendObject.search();
		}
		

	    
		$('#searchBtn').on('click', function() {
			if($("#catalog").val() <= 0){
				$("#queryFieldName").val("CATALOGID");
			}
			if($("#catalog").val() > 0){
				$("#queryFieldName").val("CLASSID");
			}
			if($("#class").val() > 0){
				$("#queryFieldName").val("PRODUCTID");
			}
			if($("#product").val() > 0){
				$("#queryFieldName").val("MODULEID");
			}
			statisticsObject.search();
			trendObject.search();
			trendObject.refreshChart();
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
	    });
	});
	</script>
	<script src="${scriptPath}/view/quality/productQuality_statistics.js" type="text/javascript"></script>
	<script src="${scriptPath}/view/quality/productQuality_trend.js" type="text/javascript"></script>
	<%@ include file="/WEB-INF/view/quality/productQualityAnalysis/layer/productQualityLayer.jsp"%>
</body>

</html>