<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData" value="${ctx}/common/REPORT_URL_QUALITY/getTableColumnData.do" scope="page" />
<c:set var="updateColumnUser" value="${ctx}/common/REPORT_URL_QUALITY/updateColumnUser.do" scope="request" />
<c:set var="getTrendTableColumnData" value="${ctx}/common/REPORT_URL_QUALITY_TREND/getTableColumnData.do" scope="page" />
<c:set var="updateTrendColumnUser" value="${ctx}/common/REPORT_URL_QUALITY_TREND/updateColumnUser.do" scope="request" />
<c:set var="initTable" value="${ctx}/traffic/businessAnalysis/urlQuality/initTable.do" scope="request" />
<c:set var="exportStatistic" value="${ctx}/traffic/businessAnalysis/urlQuality/exportStatistic.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-业务分析_url分析</title>
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
	queryTableName = "REPORT_URL_QUALITY";
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
					<span class="blue">业务分析</span> / <span class="blue">页面分析</span>
					/ <span>热门url</span>
					<!-- <<sec:authorize ifAllGranted="ROLE_QUALITY_PRODUCT_ANALYSIS_IP_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize>
					button class="fr">导出</button> -->
				</div>
				 <div class="computer_room">
				<!-- <div class="c-nav" id="c-height">
				<div>
				<ul id="treeSelect" class="ztree"></ul>
				</div>
   			   </div> -->
                <div class="c-traffic fl" id = "c-right">
				<sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_URL_QUERY">
				<form id="searchForm">
				<div class="time_search">
                <div class="fl tsa">
                    <a href="#" class="active">最近24小时</a>
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
                        <input type = "hidden"  id = "hid_sort"     value = "VISIT_COUNT"  />
                    </div>
                </div>
                <div class="fl option">
              	<div>
                 <label for="">url：</label>
                    <input type = "text" style="width: 350px;" name = "queryUrl"  />
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
							<button class="statistical check">统计</button>
						</div>
						<!-- 统计start -->
						<div id="statistical">
							<div class="table-title oh">
								<a href="#">热门URL TOP10排名</a>
								<div class="indicators_two">
									<span>指标</span> 
									<select name="" id="chartleftoption">
									</select>
									<div class="fr">
										<form action="" id="chartOrder">
											上升 <input type="radio" name="trend" value="desc" checked="checked"> 下降 <input
												type="radio" name="trend" value="asc">
										</form> 
									</div>
								</div>
								<div class="w100">
									<!-- <div id="chart_left"
										style="float: left; margin: 10px auto; width: 40%; height: 350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div> -->
									<!-- <div id="chart_right" style="float: left; margin: 10px auto; width: 851px; height: 350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div> -->
									 <div id="chart_right" style="width:100%;margin:20px auto 0;height:380px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
								</div>
							</div>
							<div class="table-title">
								<a>URL统计</a>
								<div class="w95">
	                                <div class="block_sel">
	                                <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_URL_EXPORT">
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
					
					</div>
						</div>
			</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
		$("#c-right").css("width", document.documentElement.clientWidth-230-10-18); 
		$("#c-right").css("margin-left",0);
		$("input[name='trend']").get(1).checked = true;  
		$("input[name='queryTableName']").val(queryTableName+"_H");
   		$("#q_sdate").val("");
   		$("#q_edate").val("");
   		$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));
		$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));
		$(".tsa a").bind("click",function(){
   			$(".tsa a").each(function(i){
   				$(this).attr("class","");
   			});
   			$(this).attr("class","active");
   			switch ($(this).html()){
       			case "最近24小时" :$(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
				case "最近7天" : $(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-7),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
				case "最近30天" : $(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_D");$("#hid_sdate").val(formatDate(getDate(0,0,-30),"D"));$("#hid_edate").val(formatDate(getDate(0,0,0),"D"));break;
				case "自定义范围" :$(".time_scope").show();$(".searchBtn").show();break;
   			}
   			if($(this).html() != "自定义范围"){
   				statisticsObject.search();
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
   							$("input[name='queryTableName']").val(queryTableName+"_H");
   							formattype = "H";
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
   			}
   		});
		statisticsObject.search();
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
	<script src="${scriptPath}/view/businessAnalysis/urlQuality_statistics.js" type="text/javascript"></script>
</body>

</html>