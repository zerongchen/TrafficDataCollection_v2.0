<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="updateColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_TREND/updateColumnUser.do"
	scope="request" />
<c:set var="initTable"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/initTable.do"
	scope="request" />
	
<c:set var="getStatisticsTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_STA/getTableColumnData.do"
	scope="page" />
<c:set var="updateStatisticsColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_STA/updateColumnUser.do"
	scope="request" />
<c:set var="initStatisticsTable"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/initStatisticsTable.do"
	scope="request" />
	
<c:set var="exportStatistic"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/exportStatistic.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/exportTrend.do"
	scope="request" />
<c:set var="initLayerTrendTable"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/initLayerTrendTable.do"
	scope="request" />
<c:set var="exportLayerTrend"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/exportLayerTrend.do"
	scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-流向分析-业务流向</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
</head>
<script type="text/javascript">
//趋势
var getTableColumnDataUrl = '${getTableColumnData}',
initTableUrl = '${initTable}',
updateColumnUserUrl = '${updateColumnUser}';
//统计
var getStatisticsTableColumnDataUrl = '${getStatisticsTableColumnData}',
initStatisticsTableUrl = '${initStatisticsTable}',
updateStatisticsColumnUserUrl = '${updateStatisticsColumnUser}';
//导出
var exportStatisticUrl = '${exportStatistic}',
exportTrendUrl = '${exportTrend}';
exportLayerTrendUrl = '${exportLayerTrend}';
//趋势层
var initLayerTrendTableUrl = '${initLayerTrendTable}';
var queryTableName = "FLOW_DIRECTION";
</script>
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con quality_regional">
		  <div class="frame">
            <div class="title">
                <i></i>
                <span class="blue">CMNET流量</span> /
                <span class="blue">流向分析</span> /
                <span>业务流向</span>
                <sec:authorize ifAllGranted="ROLE_BUSINESS_DIRECTION_QUERY">
                <button class="fr drop_down">查询</button>
                </sec:authorize>
                <!-- <button class="fr">导出</button> -->
            </div>
            <sec:authorize ifAllGranted="ROLE_BUSINESS_DIRECTION_QUERY">
            <form id="searchForm">
            	<input type="reset" style="display:none;" id="resetSearchForm"/>
		        <div class="time_search"   style = "display:none;">
                <%-- <tdc:timeSelect domId="timeSelect" queryTableName="FLOW_DIRECTION" timePattern="yyyy-MM-dd HH:mm" type="2"/> --%>
                
                <div class="fl">
                    <!-- <a href="" class="active">最近1小时</a> -->
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
                        <input type = "hidden" name = "queryTableName"  value = ""  />
                        
                    </div>
            	</div>
                <div class="fr">
                    <button class="search_button fr" id="searchBtn" type="button">查询</button>
                </div>
                <div class="fl option">
                <tdc:selector switches="0"></tdc:selector>
                <input type = "hidden"  id = "hid_queryDetailsId"  name = "queryDetailsId"  value = "0"  /> 
                </div>
                <input type = "hidden"  id = "hid_sort"  value = "FLOW"  />
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
                            <a>业务流量排名</a>
                            	<div class="tabs">
		                            <span>指标:</span>
		                             <form action="" method="get">
		                            <label><input name="Fruit"  value="FLOWALL" class="traffic_b" checked="checked" type="radio"  >总流量 </label>
		                            <label><input name="Fruit"  value="FLOWUP" class="traffic_c"  type="radio" >上行流量 </label>
		                            <label><input name="Fruit"  value="FLOWDN" class="traffic_d"  type="radio" >下行流量 </label>
		                        	</form>
		                        </div>
                            	<div class="w100">
                            		<div id="chartStatistics" style="width:95%;height:350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
								</div>
                        </div>
                        <div class="table-title">
                            <a>业务流量统计</a>
                            <div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_BUSINESS_DIRECTION_EXPORT">
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
                            <a>趋势图表</a>
                            <div class="w100">
                            <div id="chartTrend" style="width:95%;height:350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                            </div>
                            </div>
                            <div class="table-title">
                            <a>趋势报表</a>
                            <div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_BUSINESS_DIRECTION_EXPORT">
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
    <!--图表js-->
   <script type="text/javascript">
	$(function(){
		$("input[name='queryTableName']").val(queryTableName+"_H");
   		$("#q_sdate").val("");
   		$("#q_edate").val("");
   		$("#hid_sdate").val(formatDate(getDate(0,0,-2),"H"));
		$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));
		$("#hid_queryDetailsId").val(0);
		$("input[name='Fruit']").get(0).checked = true; 
   		$("#hid_sort").val("FLOW");
   		
   		
   		$(".fl a").bind("click",function(){
   			$(".fl a").each(function(i){
   				$(this).attr("class","");
   			});
   			$(this).attr("class","active");
   			switch ($(this).html()){
   				case "最近1小时" : $(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_MIN");$("#hid_sdate").val(getDate(-60,0,0));$("#hid_edate").val(getDate(0,0,0));break;
   				case "最近24小时" :$(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
   				case "最近48小时" :$(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-2),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
   				case "最近7天" : $(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-7),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
   				case "最近30天" : $(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-30),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
   				case "自定义范围" :$(".time_scope").show();break;
   			}
   		});
   		
		statisticsObject.search();
		statisticsObject.searchcharts();
		trendObject.search();
		/* $('#searchBtn').on('click', function() {
			statisticsObject.search();
			statisticsObject.searchcharts();
			trendObject.search();
		}); */
		
		$('#searchBtn').on('click', function() {
   			var searchFlag = true;
   			$("#hid_queryDetailsId").val(0);
   			$(".fl a").each(function(i){
   				if($(this).attr("class") == "active"){
   					if($(this).html() == "自定义范围"){
   						if($.trim($("#q_sdate").val()).length == 0 || $.trim($("#q_edate").val()).length == 0){
   		       				alert("请选择自定义范围时间");
   		       				searchFlag = false;
   		       				return;
   		       			}
   						var betweentime = getBetweenTime($("#q_sdate").val(),$("#q_edate").val());
   						var formattype = "";
   						if(betweentime <= 7776000){
   							$("input[name='queryTableName']").val(queryTableName+"_H");
   							formattype = "H";
   						}else if(betweentime > 7776000){
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
   				statisticsObject.search();
   				statisticsObject.searchcharts();
   				trendObject.search();
       			//tableRefresh();
   			}
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
	    $("input[name='Fruit']").bind("change", function(){
			switch ($(this).val()){
				case "FLOWALL":$("#hid_sort").val("FLOW");break;
				case "FLOWUP":$("#hid_sort").val("FLOW_UP");break;
				case "FLOWDN":$("#hid_sort").val("FLOW_DN");break;
			}
			statisticsObject.searchcharts();
		});
	});
	</script>
	<script src="${scriptPath}/view/traffic/trafficAnalysis/serviceTraffic_statistics.js" type="text/javascript"></script>
	<script src="${scriptPath}/view/traffic/trafficAnalysis/serviceTraffic_trend.js" type="text/javascript"></script>
	<jsp:include page="/WEB-INF/view/traffic/trafficAnalysis/serviceTrafficLayer.jsp" flush="true"></jsp:include>
</body>
</html>