<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="initUpTable"
	value="${ctx}/common/traffic/roomTrafficService/getTrendTableDatas/initTable.do"
	scope="request" />
<c:set var="initDnTable"
	value="${ctx}/common/traffic/roomTrafficService/getStatisticTableDatas/initTable.do"
	scope="request" />
<c:set var="getTreeData" value="${ctx}/common/selectTreeData.do"
	scope="request" />
<c:set var="getCarrierOptionData"
	value="${ctx}/common/selectCarrier.do"
	scope="request" />
<c:set var="getPositionOptionData"
	value="${ctx}/common/selectSysPosition.do"
	scope="request" />
<c:set var="exportStatistic"
	value="${ctx}/traffic/trafficMonitor/roomTraffic/exportStat.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/traffic/trafficMonitor/roomTraffic/exportTrend.do"
	scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>
<title>中移动流量采集监测系统-流量分析-机房流量</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css"
	href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<script type="text/javascript">
	var initUpTableUrl = '${initUpTable}', getTreeDataUrl = '${getTreeData}';
	var initDnTableUrl = '${initDnTable}',
	queryTableName = "REPORT_SERVERBUILD_QUALITY",
	getCarrierOptionDataUrl = '${getCarrierOptionData}',
	getPositionOptionDataUrl = '${getPositionOptionData}',
	exportStatisticUrl = '${exportStatistic}',
	exportTrendUrl = '${exportTrend}';
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
					<i></i> <span class="blue">流量分析</span> / <span class="blue">流量监控</span>
					/ <span>机房流量</span>
				</div>
				<div class="computer_room">
					<jsp:include
						page="/WEB-INF/view/traffic/trafficMonitor/roomTrafficTree.jsp"
						flush="true"></jsp:include>
					<div class="c-traffic fl"  id="c-right">
				<form id="searchForm">
					<input type="text" style="display: none;" id="queryServerBuildId_1" name="queryServerBuildId"/>
					<input type="text" style="display: none;" id="queryServerRoomId_1" name="queryServerRoomId"/>
					<input type="text" style="display: none;" id="queryClassId_1" name="queryClassId"/>
		            <div class="time_search"  >
		                <%-- <tdc:timeSelect domId="timeSelect" queryTableName="REPORT_SERVERBUILD_QUALITY" timePattern="yyyy-MM-dd HH:mm" type="3"/> --%>
		                <div class="fl tsa">
                    <a href="#" class="active">最近48小时</a>
			       	<a href="#">最近7天</a>
			        <a href="#">最近30天</a> 
                    <span class="seperator">|</span>
                    <a class="scope">自定义范围</a> 
                    <div class="time_scope fl"  style = "display:none;">
                        <label for="">开始时间：</label>
                        <input id="q_sdate"  type="text"  onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\',{d:0});}'   });"/>
                        <label for="">至&nbsp;结束时间：</label>
                        <input id="q_edate"  type="text"  onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\',{d:0});}'  });"/>
                        <input id="hid_sdate" type = "hidden" name = "queryStartTime"  value = ""  />
                        <input id="hid_edate" type = "hidden" name = "queryEndTime"  value = ""  />
                        <input type = "hidden" name = "queryTableName"  value = "REPORT_SERVERBUILD_QUALITY"  />
                        <input type = "hidden"  id = "hid_sort"     value = "FLOW"  />
                        <button class="search_button fr"  id="searchBtn"  type="button">查询</button>
                    </div>
                </div>
                <!-- <div class="fr searchBtn" >
                    <button class="search_button fr"  id="searchBtn"  type="button">查询</button>
                </div> -->
		            </div>
				</form>
				
						<div class="table-title">
							<a>全部节点流量趋势</a>
							<div class="tabs">
								<form action="" method="get">
									<label><input name="Fruit" type="radio" value=""
										class="chart1" checked="checked" />图表 </label> <label><input
										name="Fruit" type="radio" value="" class="list1" />列表 </label>
								</form>
								<sec:authorize ifAllGranted="ROLE_ROOM_TRAFFIC_EXPORT">
								<button id="export" style="display:none;">导出列表</button>
								</sec:authorize>
							</div>
							
							<!-- <div id="chart_a" style="width: 90%; height: 400px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div> -->
							<div id="chart_a" style="width: 95%;margin:0 auto;height:300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>	
								
							
							<div id="bootstrap-table">
								<table id="appTable_up"></table>
							</div>
						</div>
						<div class="table-title">
						
							<a>全部节点流量占比</a>
							<div class="tabs">
								<span>指标:</span>
								<form action="" method="get">
									<label><input name="Fruit" type="radio" value=""
										class="traffic_b" checked="checked" />总流量 </label> <label><input
										name="Fruit" type="radio" value="" class="traffic_c" />上行流量 </label>
									<label><input name="Fruit" type="radio" value=""
										class="traffic_d" />下行流量 </label>
								</form>
							</div>
							<div id="chart_bb"
								style="width: 95%; margin: 0 auto; height: 300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
							<div id="chart_cc"
								style="width: 95%; margin: 0 auto; height: 300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"
								class="disn"></div>
							<div id="chart_dd"
								style="width: 95%; margin: 0 auto; height: 300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"
								class="disn"></div>
								
							<div class="w951">
                                <div class="block_sel">
                                	<sec:authorize ifAllGranted="ROLE_ROOM_TRAFFIC_EXPORT">
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
	<script src="${scriptPath}/view/traffic/trafficMonitor/roomTraffic_up.js" type="text/javascript"></script>
	<script src="${scriptPath}/view/traffic/trafficMonitor/roomTraffic_down.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$("input[name='queryTableName']").val(queryTableName+"_MIN");
			$("#q_sdate").val("");
       		$("#q_edate").val("");
       		$("#hid_sdate").val(getDate(0,0,-2));
    		$("#hid_edate").val(getDate(0,0,0));
			$(".chart1").on("click", function(){
				$(".chart1").parents(".table-title:eq(0)").find("#chart_a").show();
				$("#export").hide();
				$(".chart1").parents(".table-title:eq(0)").find("#bootstrap-table").hide();
				statisticsObject.refreshChart();
			});
			$(".list1").on("click", function(){
				$(".list1").parents(".table-title:eq(0)").find("#chart_a").hide();
				$("#export").show();
				$(".list1").parents(".table-title:eq(0)").find("#bootstrap-table").show();
				statisticsObject.refreshTable();
			});
			$(".chart1").click();
			$(".tsa a").bind("click",function(){
       			$(".tsa a").each(function(i){
       				$(this).attr("class","");
       			});
       			$(this).attr("class","active");
       			switch ($(this).html()){
	       			case "最近48小时" :$(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_MIN");$("#hid_sdate").val(getDate(0,0,-2));$("#hid_edate").val(getDate(0,0,0));break;
					case "最近7天" : $(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_MIN");$("#hid_sdate").val(getDate(0,0,-7));$("#hid_edate").val(getDate(0,0,0));break;
					case "最近30天" : $(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-30),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
					case "自定义范围" :$(".time_scope").show();$(".searchBtn").show();break;
       			}
       			if($(this).html() != "自定义范围"){
       				var treeObj = $.fn.zTree.getZTreeObj("treeSelect");
    				statisticsObject.search(treeObj);
    				trendObject.search(treeObj);
    				trendObject.searchCharts(treeObj);
    			}
       		});
			
			
			$('#searchBtn').on('click', function(){
					var searchFlag = true;
   			$(".tsa a").each(function(i){
   				if($(this).attr("class") == "active"){
   					if($(this).html() == "自定义范围"){
   						if($.trim($("#q_sdate").val()).length == 0 || $.trim($("#q_edate").val()).length == 0){
   		       				alert("请选择自定义范围时间");
   		       				searchFlag = false;
   		       				return;
   		       			}
   						var betweentime = getBetweenTime($("#q_sdate").val(),$("#q_edate").val());
   						var formattype = "";
   						if(betweentime <= 86400){
   							$("input[name='queryTableName']").val(queryTableName+"_MIN");
   						}else if(betweentime > 86400 && betweentime <= 604800){
   							$("input[name='queryTableName']").val(queryTableName+"_H");
   							formattype = "H";
   						}else if(betweentime > 604800){
   							$("input[name='queryTableName']").val(queryTableName+"_D");
   							formattype = "D";
   						}
   						if($("input[name='queryTableName']").val().substring($("input[name='queryTableName']").val().lastIndexOf("_")+1) != "MIN"){
   		       				$("#hid_sdate").val(formatDate($("#q_sdate").val(),formattype));
   		       				$("#hid_edate").val(formatDate($("#q_edate").val(),formattype));
   		       			}else{
       		       			$("#hid_sdate").val($("#q_sdate").val());
   		       				$("#hid_edate").val($("#q_edate").val());
   		       			}
   					}
   				}
   			});
   			if(searchFlag){
				var treeObj = $.fn.zTree.getZTreeObj("treeSelect");
				statisticsObject.search(treeObj);
				trendObject.search(treeObj);
				trendObject.searchCharts(treeObj);
				
   			}
				
			});
			
		});
	</script>
	<script>
		$("#chart_cc").css('width', $("#chart_bb").width());
		$("#chart_dd").css('width', $("#chart_bb").width());
		$('.traffic_b').click(function() {
			$('#chart_bb').show();
			$('#chart_cc').hide();
			$('#chart_dd').hide();
			statisticsObject.refreshChart();
			trendObject.refreshChart();
		})
		$('.traffic_c').click(function() {
			$('#chart_bb').hide();
			$('#chart_cc').show();
			$('#chart_dd').hide();
			statisticsObject.refreshChart();
			trendObject.refreshChart();
		})
		$('.traffic_d').click(function() {
			$('#chart_bb').hide();
			$('#chart_cc').hide();
			$('#chart_dd').show();
			statisticsObject.refreshChart();
			trendObject.refreshChart();
		})
	</script>
</body>

</html>