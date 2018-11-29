<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTrafficTrendData" value="${ctx}/traffic/trafficOverview/getTrafficTrendData.do" scope="request"></c:set>
<c:set var="getServerbuildTrafficData" value="${ctx}/traffic/trafficOverview/getServerbuildTrafficData.do" scope="request"></c:set>
<c:set var="getServiceTrafficData" value="${ctx}/traffic/trafficOverview/getServiceTrafficData.do" scope="request"></c:set>
<c:set var="getRegionTrafficData" value="${ctx}/traffic/trafficOverview/getRegionTrafficData.do" scope="request"></c:set>
<c:set var="getCarrierTrafficData" value="${ctx}/traffic/trafficOverview/getCarrierTrafficData.do" scope="request"></c:set>
<c:set var="getProtocolTrafficData" value="${ctx}/traffic/trafficOverview/getProtocolTrafficData.do" scope="request"></c:set>
<c:set var="getIPTrafficData" value="${ctx}/traffic/trafficOverview/getIPTrafficData.do" scope="request"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<title>流量总览概况</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
</head>
<script type="text/javascript">
var getTrafficTrendDataUrl = '${getTrafficTrendData}';
var getServerbuildTrafficDataUrl = '${getServerbuildTrafficData}';
var getServiceTrafficDataUrl = '${getServiceTrafficData}';
var getRegionTrafficDataUrl = '${getRegionTrafficData}';
var getCarrierTrafficDataUrl = '${getCarrierTrafficData}';
var getProtocolTrafficDataUrl = '${getProtocolTrafficData}';
var getIPTrafficDataUrl = '${getIPTrafficData}';
</script>
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/headerHomePage.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
	<div class="con traffic">
        <div class="frame">
            <div class="title">
                <i></i>
                <span class="blue">流量分析</span> /
                <span class="blue">总览概况</span> /
                <span>流量总览概况</span>
                <sec:authorize ifAllGranted="ROLE_PROFILE_QUERY">
                <!-- <button class="fr drop_down">查询</button> -->
                </sec:authorize>
                <!-- <button class="fr">
                    导出
                </button> -->
            </div>
            <sec:authorize ifAllGranted="ROLE_PROFILE_QUERY">
                <form id="searchForm">
				<div class="time_search" style = "display:block;">
                <div class="fl">
                    <a href="#" class="active">最近48小时</a>
                    <a href="#">最近7天</a>
                    <a href="#">最近1个月</a>
                    <a href="#">最近3个月</a>         
                    <input id="hid_sdate" type = "hidden" name = "queryStartTime"  value = ""  />
                    <input id="hid_edate" type = "hidden" name = "queryEndTime"  value = ""  />  
                    <input id="hid_sdate_R" type = "hidden" name = "queryStartTimeR"  value = ""  />
                    <input id="hid_edate_R" type = "hidden" name = "queryEndTimeR"  value = ""  />    	
                    <input type = "hidden" name = "queryTableName_Serverbuild" value = ""  />
                    <input type = "hidden" name = "queryTableName_R_Serverbuild" value = ""  />
                    <input type = "hidden" name = "queryTableName_Protocol"  value = ""  />
                    <input type = "hidden" name = "queryTableName_IP"  value = ""  />
                    <input type = "hidden" name = "queryTableName_Province"  value = ""  />
                     <input type = "hidden" name = "queryTableName_Biz"  value = ""  />
                </div>
                </div>
                </form>
                </sec:authorize>
           	<div class="traffic_chart">
                <div class="table-title div1">
                    <a>全网流量趋势分布</a>
                    <div id="chartTrend" style="width:100%;height:300px;"></div>
                </div>
                <div class="table-title mr30 div2">
                    <a>机房流量（GB）</a>
                    <div id="chartBuild" style="width:100%;height:300px;"></div>
                </div>
                <div class="table-title div2">
                    <a>业务流量（GB）</a>
                    <div id="chartService" style="width:100%;height:300px;"></div>
                </div>
                <div class="table-title mr30 div2">
                    <a>TOP10地域来源流量（GB）</a>
                    <div id="chartRegion" style="width:100%;height:300px;"></div>
                </div>
                <div class="table-title div2">
                    <a>运营商流量占比（GB）</a>
                    <div id="chartCarrier" style="width:100%;height:300px;"></div>
                </div>
                <div class="table-title div1">
                    <a>TOP10应用层协议流量（GB）</a>
                    <div id="chartProtocol" style="width:100%;height:420px;"></div>
                </div>
                <div class="table-title div1">
                    <a>TOP10目的IP地址流量（GB）</a>
                    <div id="chartIP" style="width:100%;height:420px;"></div>
                </div>
            </div>
            </div>
            </div>
           
     <script type="text/javascript">
     $(".div1").css("width", document.documentElement.clientWidth-230-10-18);
     $(".div2").css("width", (document.documentElement.clientWidth-230-10-18-30)/2);
     $(".div1").css("width", $(".div2").width()*2+30);
		 var queryTableName_Serverbuild = "REPORT_SERVERBUILD_QUALITY";
		 var queryTableName_Protocol = "REPORT_PROTOCOL_FLOW";
		 var queryTableName_IP = "REPORT_DESTIP_FLOW";
		 var queryTableName_Province = "REPORT_CARRIER_PROVINCE_FLOW";
		 var queryTableName_Biz = "REPORT_BIZ_FLOW";
		 function tableName_MIN(){
	    	 $("input[name='queryTableName_Serverbuild']").val(queryTableName_Serverbuild+"_H");
	    	 $("input[name='queryTableName_Protocol']").val(queryTableName_Protocol+"_H");
	    	 $("input[name='queryTableName_IP']").val(queryTableName_IP+"_H");
	    	 $("input[name='queryTableName_Province']").val(queryTableName_Province+"_H");
	    	 $("input[name='queryTableName_Biz']").val(queryTableName_Biz+"_H");
	    	 $("input[name='queryTableName_R_Serverbuild']").val("REPORT_FLOW_MIN");
	     };
	     function tableName_H(){
	    	 $("input[name='queryTableName_Serverbuild']").val(queryTableName_Serverbuild+"_H");
	    	 $("input[name='queryTableName_Protocol']").val(queryTableName_Protocol+"_H");
	    	 $("input[name='queryTableName_IP']").val(queryTableName_IP+"_H");
	    	 $("input[name='queryTableName_Province']").val(queryTableName_Province+"_H");
	    	 $("input[name='queryTableName_Biz']").val(queryTableName_Biz+"_H");
	    	 $("input[name='queryTableName_R_Serverbuild']").val(queryTableName_Serverbuild+"_H");
	     };
	     function tableName_D(){
	    	 $("input[name='queryTableName_Serverbuild']").val(queryTableName_Serverbuild+"_D");
	    	 $("input[name='queryTableName_Protocol']").val(queryTableName_Protocol+"_D");
	    	 $("input[name='queryTableName_IP']").val(queryTableName_IP+"_D");
	     };
	     
	  	$(function(){
		  $("input[name='queryTableName_Serverbuild']").val(queryTableName_Serverbuild+"_H");
		  $("input[name='queryTableName_Protocol']").val(queryTableName_Protocol+"_H");
		  $("input[name='queryTableName_IP']").val(queryTableName_IP+"_H");
		  $("input[name='queryTableName_R_Serverbuild']").val("REPORT_FLOW_MIN");
		  $("input[name='queryTableName_Province']").val(queryTableName_Province+"_H");
		  $("input[name='queryTableName_Biz']").val(queryTableName_Biz+"_H");
		  $("#hid_sdate_R").val(getDateInIndex(0,0,-2,0));
		  $("#hid_edate_R").val(getDateInIndex(0,0,0,0));
		  $("#hid_sdate").val(formatDate(getDateInIndex(0,0,-2,0),"H"));
		  $("#hid_edate").val(formatDate(getDateInIndex(0,0,0,0),"H"));
     	  startDraw();
		  
		  $(".fl a").bind("click",function(){
			  $(".fl a").each(function(i){
     				$(this).attr("class","");
     			});
     		  $(this).attr("class","active");
			  switch ($(this).html()){
 				case "最近48小时" : tableName_MIN();$("#hid_sdate").val(formatDate(getDateInIndex(0,0,-2,0),"H"));$("#hid_edate").val(formatDate(getDateInIndex(0,0,0,0),"H"));$("#hid_sdate_R").val(getDateInIndex(0,0,-2,0));$("#hid_edate_R").val(getDateInIndex(0,0,0,0)); startDraw();break;
 				case "最近7天" :tableName_MIN();$("#hid_sdate").val(formatDate(getDateInIndex(0,0,-7,0),"H"));$("#hid_edate").val(formatDate(getDateInIndex(0,0,0,0),"H"));$("#hid_sdate_R").val(getDateInIndex(0,0,-7,0));$("#hid_edate_R").val(getDateInIndex(0,0,0,0)); startDraw(); break;
 				case "最近1个月" : tableName_H();$("#hid_sdate").val(formatDate(getDateInIndex(0,0,0,-1),"H"));$("#hid_edate").val(formatDate(getDateInIndex(0,0,0,0),"H"));$("#hid_sdate_R").val(formatDate(getDateInIndex(0,0,0,-1),"H"));$("#hid_edate_R").val(formatDate(getDateInIndex(0,0,0,0),"H")); startDraw(); break;
 				case "最近3个月" : tableName_H();$("#hid_sdate").val(formatDate(getDateInIndex(0,0,0,-3),"H"));$("#hid_edate").val(formatDate(getDateInIndex(0,0,0,0),"H"));$("#hid_sdate_R").val(formatDate(getDateInIndex(0,0,0,-3),"H"));$("#hid_edate_R").val(formatDate(getDateInIndex(0,0,0,0),"H")); startDraw(); break;
 			}
			//alert($("#hid_sdate").val()+"|"+$("#hid_edate").val());  
		  });
		});
	  function startDraw(){
		  //alert($("#hid_sdate").val()+"|"+$("#hid_edate").val());
		  //alert($("#hid_sdate_R").val()+"|"+$("#hid_edate_R").val());
		  overviewObject.getTrafficTrend();
		  IPObject.getIPTraffic();
		  buildObject.getBuildTraffic();
		  serviceObject.getServiceTraffic();
		  regionObject.getRegionTraffic();
		  carrierObject.getCarrierTraffic();
		  protocolObject.getProtocolTraffic();
		  
	  }
	 </script>
     <script src="${scriptPath}/view/traffic/trafficOverview/trafficTrend.js" type="text/javascript"></script>
     <script src="${scriptPath}/view/traffic/trafficOverview/buildTraffic.js" type="text/javascript"></script>
     <script src="${scriptPath}/view/traffic/trafficOverview/serviceTraffic.js" type="text/javascript"></script>
     <script src="${scriptPath}/view/traffic/trafficOverview/regionTraffic.js" type="text/javascript"></script>
     <script src="${scriptPath}/view/traffic/trafficOverview/carrierTraffic.js" type="text/javascript"></script>
     <script src="${scriptPath}/view/traffic/trafficOverview/applicationProtocolTraffic.js" type="text/javascript"></script>
     <script src="${scriptPath}/view/traffic/trafficOverview/ipTraffic.js" type="text/javascript"></script>
</body>
</html>