<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData"  value="${ctx}/common/PROTOCOL_FLOW_STA/getTableColumnData.do"  scope="page" />
<c:set var="getTableTrendColumnData"  value="${ctx}/common/PROTOCOL_FLOW_TREND/getTableColumnData.do"  scope="page" />
<c:set var="updateStaColumnUser"  value="${ctx}/common/PROTOCOL_FLOW_STA/updateColumnUser.do" scope="request" />
<c:set var="updateTrendColumnUser"  value="${ctx}/common/PROTOCOL_FLOW_TREND/updateColumnUser.do" scope="request" />
<c:set var="initStaTable" value="${ctx}/traffic/trafficMonitor/protocolTraffic/initStaTable.do" scope="request" />
<c:set var="initTrendTable" value="${ctx}/traffic/trafficMonitor/protocolTraffic/initTrendTable.do" scope="request" />
<c:set var="exportStatistic" value="${ctx}/traffic/trafficMonitor/protocolTraffic/exportStatistic.do" scope="request" />
<c:set var="exportTrend" value="${ctx}/traffic/trafficMonitor/protocolTraffic/exportTrend.do" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-流量分析-协议流量</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
</head>
<script type="text/javascript">
var getTableStaColumnDataUrl = '${getTableStaColumnData}',
getTableTrendColumnDataUrl = '${getTableTrendColumnData}',
initStaTableUrl = '${initStaTable}',
initTrendTableUrl = '${initTrendTable}',
updateStaColumnUserUrl = '${updateStaColumnUser}',
updateTrendColumnUserUrl = '${updateTrendColumnUser}',
exportStatisticUrl = '${exportStatistic}',
exportTrendUrl = '${exportTrend}',
queryTableName = "REPORT_PROTOCOL_FLOW";
</script>
<body>
	<!-- #BeginLibraryItem "/Library/header.lbi" -->
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
	<!-- #EndLibraryItem -->
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<!-- #EndLibraryItem -->
		<div class="sider">
			<div class="con protocol">
		  <div class="frame">
            <div class="title">
                <i></i>
                <span class="blue">流量分析</span> /
                <span class="blue">流量监控</span> /
                <span>协议流量</span>
                <!--<sec:authorize ifAllGranted="ROLE_PROTOCOL_TRAFFIC_QUERY">
				<button class="fr drop_down">查询</button>
				</sec:authorize>
                 <button class="fr">导出</button> -->
            </div>
            <div class="c-traffic fl" id = "c-right" >
            <sec:authorize ifAllGranted="ROLE_PROTOCOL_TRAFFIC_QUERY">
            <form id="searchForm">
            <div class="time_search">
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
                        <input type = "hidden" name = "queryTableName"  value = ""  />
                        <input type = "hidden"  id = "hid_sort"     value = "FLOW"  />
                    </div>
                </div>
                <!-- <div class="fr searchBtn" >
                    <button class="search_button fr"  id="searchBtn"  type="button">查询</button>
                </div> -->
                <div class="fl option">
               <%--  <tdc:selector switches = "2"></tdc:selector> --%>
                    <div>
                        <label for="">协议类型：</label>
                        <select  id="sel_protocoltype"  class="operator" name = "queryProtocolType">
                            <!-- <option value="-1">请选择</option> -->
                            <option value="0">传输层协议</option>
                            <option value="1">应用层协议</option>
                        </select>
                    </div> 
                    <div>
                        <label for="">协议名称：</label>
                            <select id = "sel_protocol"  multiple="multiple"   name = "queryProtocol" ></select> 
                            <input type = "hidden" id = "hid_queryDetailsId" name = "queryDetailsId"  value = "-1"  />
                    </div>
                    <div class="ml10 searchBtn" >
                    <button class="search_button fr"  id="searchBtn"  type="button">查询</button>
                </div>
                </div>
            </div>
            	</form>
            </sec:authorize>	
            <div class="regional">
            <div class="c-traffic fl">
                <div class="disb">
                	<button  class="trend check">统计</button>
                    <button class="statistical" >趋势</button>
                </div>
                <div id="trend">
                <div class="table-title">
                    <a>协议流量排名</a>
                    <div class="tabs">
                        <span>指标:</span>
                        <form action="" method="get">
                            <label><input name="Fruit"  value="FLOWALL" class="traffic_b" checked="checked" type="radio"  >总流量 </label>
                            <label><input name="Fruit"  value="FLOWUP" class="traffic_c"  type="radio" >上行流量 </label>
                            <label><input name="Fruit"  value="FLOWDN" class="traffic_d"  type="radio" >下行流量 </label>
                        </form>
                    </div>
                    <div id="chart_1" style="width: 95%;margin:0 auto;height:300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                    
                    
                </div>
                <div class="table-title">
                    <a>协议流量统计</a>
                    <div class="w95">
                    <sec:authorize ifAllGranted="ROLE_PROTOCOL_TRAFFIC_EXPORT">
                    <button id = "export_statistic" type = "button">导出列表</button>
                    </sec:authorize>
                    </div>
                    <table id="appTable_statistical"  class="table"></table>
                 </div>   
                </div>
                
                <div id="statistical">
                <div class="table-title">
                    <a>趋势图表</a>
                     <div class="w100">
                    <div id="chart_2" style="width:100%;margin:20px auto 0;height:300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                    </div>   
                        
                </div>
                <div class="table-title">
                    <a>趋势报表</a>
                     <div class="w95">
                     <sec:authorize ifAllGranted="ROLE_PROTOCOL_TRAFFIC_EXPORT">
                            <button id = "export_trend" type = "button">导出列表</button>
                     </sec:authorize>       
                        </div>
                   <table id="appTable_trend"></table> 
                
                </div>
                </div>
                </div>
            </div>
        </div>
    </div>
			</div>
		</div>
	</div>
	
	<div id = "div_layer"  style = "display:none;" class="table-title">
                                                <div class="tabs">
                                                        <label><input name="Fruit1"  value="0"   type="radio" />图表 </label>
                                                        <label><input name="Fruit1"  value="1"   type="radio"  />列表 </label>
                                                        <sec:authorize ifAllGranted="ROLE_PROTOCOL_TRAFFIC_EXPORT">
                                                    <button id="export_layer" type = "button" >导出列表</button>
                                                    </sec:authorize>
                                                </div>
                                                <div id="chart_3" style="width: 100%;margin:30px auto 0;height:500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);">
                                                </div><div id = "div_list" style = "display:none;">
                                                <table id = "appTable_layer"  ></table></div>
                                            </div>
    <script>
        //$("#chart_2").css('width', $("#chart_1").width()); 
        //$("#chart_3").css('width', "1100px"); 
    </script>
    <!--图表js-->
   <script type="text/javascript">
        $(function() {
        	$("#c-right").css("width", document.documentElement.clientWidth-230-10-18);
        	$("#appTable_statistical").css("width", document.documentElement.clientWidth-230-10-18-50);
    		$("#appTable_trend").css("width", document.documentElement.clientWidth-230-10-18-50);
        	$("#sel_protocol").multiselect({
        	        noneSelectedText: "全部协议",
        	        checkAllText: "全选",
        	        uncheckAllText: '全不选',
        	        selectedList:4
       	    });
       		$("#sel_protocol").multiselect(); 
       		$("input[name='queryTableName']").val(queryTableName+"_MIN");
       		$("#q_sdate").val("");
       		$("#q_edate").val("");
       		$("#hid_sdate").val(getDate(0,0,-2));
    		$("#hid_edate").val(getDate(0,0,0));
       		//$("#class").val(-1);  
       		//$("#catalog").val(-1);  
       		//$("#product").val(-1);  
       		$("#hid_queryDetailsId").val(-1);
       		$("#sel_protocoltype").val(1); 
       		statisticsObject.selectProtocol();
       		
       		$("input[name='Fruit']").get(0).checked = true; 
       		$("#hid_sort").val("FLOW");
       		
       		$("#sel_protocoltype").bind("change", function(){
       			statisticsObject.selectProtocol($(this).val());
       		});
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
    				querySearch();
    			}
       		}); 
       		$('#searchBtn').on('click', function() {
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
       				statisticsObject.search();
       				statisticsObject.searchCharts();
	       			trendObject.search();
       			}
       		});
       	    $('.trend').click(function() {
       	        $(this).addClass('check').siblings().removeClass('check');
       	        $('#trend').show();
       	        $('#statistical').hide();
       	     	statisticsObject.refreshChart();
       	    });
       	    $('.statistical').click(function() {
       	        $(this).addClass('check').siblings().removeClass('check');
       	        $('#statistical').show();
       	        $('#trend').hide();
       	     	trendObject.refreshChart();
       	    });
       	    $("input[name='Fruit1']").bind("change", function(){
       	    	if($(this).val() == 0){
       	    		$('#chart_3').show();
           	        $('#div_list').hide();
           	        $("#export_layer").hide();
       	    	}else{
       	    		$('#div_list').show();
           	        $('#chart_3').hide();
           	       	$("#export_layer").show();
       	    	}
       	    });
       	    
	       	function querySearch(){
	       		$("#hid_queryDetailsId").val(-1);
	     		statisticsObject.search();
	     	 	statisticsObject.searchCharts();
	     		trendObject.search();
	     	} 	
       	    
       	 	$("input[name='Fruit']").bind("change", function(){
				switch ($(this).val()){
					case "FLOWALL":$("#hid_sort").val("FLOW");break;
					case "FLOWUP":$("#hid_sort").val("FLOW_UP");break;
					case "FLOWDN":$("#hid_sort").val("FLOW_DN");break;
				}
				statisticsObject.searchCharts();
			});
       	    
       	    statisticsObject.search();
       	 	statisticsObject.searchCharts();
       		trendObject.search();
        });
    </script>
    <script src="${scriptPath}/view/traffic/trafficMonitor/protocolTraffic_statistics.js" type="text/javascript"></script>
    <script src="${scriptPath}/view/traffic/trafficMonitor/protocolTraffic_trend.js" type="text/javascript"></script>
</body>
</html>