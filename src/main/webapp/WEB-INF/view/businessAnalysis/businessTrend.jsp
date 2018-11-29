<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData"  value="${ctx}/common/BUSINESS_TREND/getTableColumnData.do"  scope="page" />
<c:set var="updateStaColumnUser"  value="${ctx}/common/BUSINESS_TREND/updateColumnUser.do" scope="request" />
<c:set var="initTable" value="${ctx}/traffic/businessAnalysis/businessTrend/initTable.do" scope="request" />
<c:set var="exportTable" value="${ctx}/traffic/businessAnalysis/businessTrend/exportTable.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-业务分析-业务发展趋势</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
</head>
<script type="text/javascript">
var getTableStaColumnDataUrl = '${getTableStaColumnData}',
initTableUrl = '${initTable}',
updateStaColumnUserUrl = '${updateStaColumnUser}',
exportTableUrl = '${exportTable}',
queryTableName = "REPORT_CARRIER_PROVINCE_QUALITY";
</script>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
    <jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
    <div class="con computer_room">
        <div class="frame">
            <div class="title">
                <i></i> 
                <span class="blue">业务分析</span> /
                <span class="blue">业务热度分析</span> /
                <span>业务发展趋势</span>
                 <!-- <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_PRODUCTTREND_QUERY">
                <button class="fr drop_down">查询</button>
                </sec:authorize>
               <button id = "export_statistic" class="fr" type = "button" >导出</button> -->
            </div>
            <div class="computer_room">
      <div class="c-nav" id="c-height">
        <div>
		<ul id="treeSelect" class="ztree"></ul>
		</div>
      </div>
      <div class="c-traffic fl" id = "c-right" >
            <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_PRODUCTTREND_QUERY">
          <form id="searchForm">  
          <div class="time_search"  >
                <div class="fl tsa">
                    <a href="#" class="active">最近24小时</a>
                    <a href="#">最近7天</a>
                    <a href="#">最近30天</a>
                    <span class="seperator">|</span>
                <a class="scope">自定义范围</a> 
                	<div class="time_scope fl" style = "display:none;" >
                        <label for="">开始时间：</label>
                        <input id="q_sdate"  type="text"  onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\',{d:0});}'   });"/>
                        <label for="">至&nbsp;结束时间：</label>
                        <input id="q_edate"  type="text"  onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\',{d:0});}'  });"/>
                        <input id="hid_sdate" type = "hidden" name = "queryStartTime"  value = ""  />
                        <input id="hid_edate" type = "hidden" name = "queryEndTime"  value = ""  />
                        <input type = "hidden" name = "queryTableName"  value = ""  />
                        <input type = "hidden"  name = "queryBizIdStr"    value = "-1"  />
                         	
                    </div>
            	</div>
            	<!-- <div class="fr searchBtn" >
            	<button class="search_button fr"  id="searchBtn"  type="button">查询</button>
            </div> -->
            	<%-- <tdc:timeSelect domId="timeSelect" queryTableName="FLOW_DIRECTION" timePattern="yyyy-MM-dd HH:mm" type="2"/> --%>
            <div class="fl option">
            <tdc:baseResPoolSelector switches = "9"></tdc:baseResPoolSelector>
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
		            <button class="trend check"/>图表
		            <button class="statistical"/>列表
		            <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_PRODUCTTREND_EXPORT">
		            <button id="exportbtn" style="display:none; border-radius: 15px;"/>导出列表
		        	</sec:authorize>
		        </div>
                <div>
                    <div class="table-title">
                        <a>业务发展趋势</a>
                        <div id="trend" >
	                        <div class="tabs">
	                            <span>指标:</span>
	                             <form action="" method="get">
	                            <label><input name="Fruit"  value="FLOW" class="traffic_b" type="radio" checked="checked" >总流量 </label>
	                            <label><input name="Fruit"  value="PAGE_VIEW" class="traffic_c"  type="radio" >访问量 </label>
	                            <label><input name="Fruit"  value="SESSIONTIME" class="traffic_d"  type="radio" >访问时长 </label>
	                            <label><input name="Fruit"  value="SUCCESSCONNECT_RATE" class="traffic_e"  type="radio" >连接成功率 </label>
	                        	</form>
	                        </div>
	                        <div id="chartTrend" style="width: 100%;margin:0 auto;height:500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                        </div>
                        <div id="statistical" style="display:none;">
	                        <div id = "div_list" >
	                            <table id = "appTable_statistical"  ></table>
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
			statisticsObject.refreshTable();
			statisticsObject.refreshChart()
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
		$("#c-right").css("width", document.documentElement.clientWidth-230-230-10-18);
    	$("#appTable_statistical").css("width", document.documentElement.clientWidth-230-230-10-18-50);
			var zNodes  =  statisticsObject.getAllNodes();
			$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
			//$("#c-height").css("height","100%");
			$('.c-nav').css('height',$(window).height()-87);
			var $treeObj = $.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
			$treeObj.selectNode($treeObj.getNodes()[0], false, false);
			
       		$("input[name='queryTableName']").val(queryTableName+"_H");
       		$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));
  			$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));
       		statisticsObject.search();
       		$(".tsa a").bind("click",function(){
       			$(".tsa a").each(function(i){
       				$(this).attr("class","");
       			});
       			$(this).attr("class","active");
       			switch ($(this).html()){
       				case "最近24小时" :$(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
       				case "最近7天" : $(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_D");$("#hid_sdate").val(formatDate(getDate(0,0,-7),"D"));$("#hid_edate").val(formatDate(getDate(0,0,0),"D"));break;
       				case "最近30天" : $(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_D");$("#hid_sdate").val(formatDate(getDate(0,0,-30),"D"));$("#hid_edate").val(formatDate(getDate(0,0,0),"D"));break;
       				case "自定义范围" :$(".time_scope").show();break;
       			}
       			if($(this).html() != "自定义范围"){
       				statisticsObject.search();
       				statisticsObject.refreshTable();
       				statisticsObject.refreshChart();
    			}
       		});
       		
        	$('#searchBtn').on('click', function(e) {
   			if($(".time_scope").css("display") != "none"){
				if($("#q_sdate").val() == '' || $("#q_edate").val() == ''){
					alert("请选择自定义范围时间!");
				}
			}
   			$(".tsa a").each(function(i){
   				if($(this).attr("class") == "active"){
   					if($(this).html() == "自定义范围"){
   						var betweentime = getBetweenTime($("#q_sdate").val(),$("#q_edate").val());
   						var formattype = "";
   						if(betweentime <= 604800){
   							$("input[name='queryTableName']").val(queryTableName+"_H");
   							formattype = "H";
   						}else if(betweentime > 604800){
   							$("input[name='queryTableName']").val(queryTableName+"_D");
   							formattype = "D";
   						}
	       				$("#hid_sdate").val(formatDate($("#q_sdate").val(),formattype));
	       				$("#hid_edate").val(formatDate($("#q_edate").val(),formattype));
   					}
   				}
   			});
			statisticsObject.search();
			statisticsObject.refreshTable();
			statisticsObject.refreshChart();
   			});
        });
	$("#province ").change( function(e) {
		statisticsObject.search();
		statisticsObject.refreshTable();
		statisticsObject.refreshChart();
			
    });
	$("#carrier").change( function(e) {
		statisticsObject.search();
		statisticsObject.refreshTable();
		statisticsObject.refreshChart();
    });
        $('.trend').click(function() {
            $(this).addClass('check').siblings().removeClass('check');
            $('#trend').show();
            $('#statistical').hide();
            $("#exportbtn").hide();
            statisticsObject.refreshTable();
            statisticsObject.refreshChart();
        });
        $('.statistical').click(function() {
            $(this).addClass('check').siblings().removeClass('check');
            $('#trend').hide();
            $('#statistical').show();
            $("#exportbtn").show();
            statisticsObject.refreshTable();
            statisticsObject.refreshChart();
        });
    </script>
    <script src="${scriptPath}/view/businessAnalysis/businessTrend.js" type="text/javascript"></script>


</body>

</html>
