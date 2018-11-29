<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData"  value="${ctx}/common/TOP_BUSINESS_ANALYSIS/getTableColumnData.do"  scope="page" />
<c:set var="updateStaColumnUser"  value="${ctx}/common/TOP_BUSINESS_ANALYSIS/updateColumnUser.do" scope="request" />
<c:set var="initStaTable" value="${ctx}/traffic/businessAnalysis/topBusinessAnalysis/initStaTable.do" scope="request" />
<c:set var="exportStatistic" value="${ctx}/traffic/businessAnalysis/topBusinessAnalysis/exportStatistic.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-业务分析-TOP业务分析</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
</head>
<script type="text/javascript">
var getTableStaColumnDataUrl = '${getTableStaColumnData}',
initStaTableUrl = '${initStaTable}',
updateStaColumnUserUrl = '${updateStaColumnUser}',
exportStatisticUrl = '${exportStatistic}',
queryTableName = "REPORT_CARRIER_PROVINCE_QUALITY";
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
		/* if(treeNode.level == 0){
			$("input[name='queryBizIdStr']").val(-1);
		}else{
			var ids=[];
			ids = getChildren(ids,treeNode);
			$("input[name='queryBizIdStr']").val(ids);
		} */
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
						if(betweentime <= 604800){
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
	$(function() {	
		var zNodes  =  statisticsObject.getAllNodes();
		//$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
		var $treeObj = $.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
		$treeObj.selectNode($treeObj.getNodes()[0], false, false);
		var nodes = $treeObj.getNodeByParam("name", "业务总览", null).children;
		var ids=[];
		if(nodes.length > 0){
			for(var i=0;i<nodes.length;i++){
				ids.push(nodes[i].id);
			}
		}else{
			ids.push(0);
		}
		$("input[name='queryBizIdStr']").val(ids);	
		//$("#c-height").css("height","100%");
		$('.c-nav').css('height',$(window).height()-70);
	});



</script>
<body>
    <!-- #BeginLibraryItem "/Library/header.lbi" -->
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<!-- #EndLibraryItem -->
    <jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
    <div class="con computer_room">
        <div class="frame">
            <div class="title">
                <i></i> 
                <span class="blue">内容分析</span> /
                <span class="blue">业务分析</span> /
                <span>热门业务</span>
                <%-- <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_TOPPRODUCT_QUERY">
                <button class="fr drop_down">查询</button>
                </sec:authorize> --%>
                <%-- <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_TOPPRODUCT_EXPORT">
                <button id = "export_statistic" class="fr" type = "button" >导出</button>
            	</sec:authorize> --%>
            </div>
            
             <div class="computer_room">
            <div class="c-nav" id="c-height">
        <div>
		<ul id="treeSelect" class="ztree"></ul>
		</div>
      </div>
            <div class="c-traffic fl" id = "c-right" >
            <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_TOPPRODUCT_QUERY">
          <form id="searchForm">  
          <div class="time_search" >
                <div class="fl tsa">
                    <!-- <a href="" class="active">最近1小时</a> -->
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
                        <input type = "hidden"  id = "hid_gbcn"   name = "queryGroupByColumnName"  value = ""  />
                        <input type = "hidden"  id = "hid_sort"     value = "FLOW"  />
                        <input type = "hidden"  id = "hid_limit"     value = "10"  />
                        <input type = "hidden"  name = "queryBizIdStr"    value = "-1"  />
                        <!-- <button class="search_button fr"  id="searchBtn"  type="button">查询</button> -->
                    </div>
            </div>
          <!--   <div class="fr searchBtn" >
            	<button class="search_button fr"  id="searchBtn"  type="button">查询</button>
            </div> -->
            <div class="fl option">
                <%-- <tdc:selector switches = "9"></tdc:selector> --%>
                 <tdc:baseResPoolSelector switches = "9"></tdc:baseResPoolSelector>
                  <div class="ml10 searchBtn" >
                    <button class="search_button fr"  id="searchBtn"  type="button">查询</button>
                </div>
               <input type = "hidden"  id = "hid_queryDetailsId"  name = "queryDetailsId"  value = "0"  /> 
            </div>
        </div>
       	  </form>
       	  </sec:authorize>
        <div class="regional">
            <div class="c-traffic fl">
               <!--  <div class="disb">
                    <button class="trend check">统计</button>
                    <button class="statistical">趋势</button>
                </div> -->
                <div id="trend">
                    <div class="table-title">
                        <a>TOP业务分析</a>
                      <!--   <div class="tabs">
                            <span>指标:</span>
                             <form action="" method="get">
                            <label><input name="Fruit"  value="FLOWALL" class="traffic_b" checked="checked" type="radio"  >总流量 </label>
                            <label><input name="Fruit"  value="FLOWUP" class="traffic_c"  type="radio" >上行流量 </label>
                            <label><input name="Fruit"  value="FLOWDN" class="traffic_d"  type="radio" >下行流量 </label>
                        </form>
                        </div> -->
                     <!--    <div>
                            <div id="page01">
                                <div id="chart_1" style="float:left;width:100%;height:350px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                            </div>
                            <div id="page02" class="w95" style="display:none">
                                chart02
                            </div>
                            <div id="page03" class="w95" style="display:none">
                                chart03
                            </div>
                        </div> -->
                     <div class="w951">
                           <!--  <button id = "export_statistic" type = "button">导出列表</button> -->
                            <select id = "sel_topSize" >
                           <option value = "10">TOP10</option>
                           <option value = "20">TOP20</option>
                           <option value = "20">TOP50</option>
                           </select> 
                           <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_TOPPRODUCT_EXPORT">
                <button id = "export_statistic" class="fr" type = "button" >导出</button>
            	</sec:authorize>
                        </div>
                      <table id="appTable_statistical"  class="table"></table>
                    </div>
                </div>
          <!--  <div class="table-title" id="statistical" style = "display:none;" >
                    <a>趋势图表&报表</a>
                    <div id="chart_2" style="width:100%;margin:20px auto 0;height:300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
                        <div class="w95">
                            <button id = "export_trend" type = "button">导出列表</button>
                        </div>
                        <table id="appTable_trend"></table>
                </div> -->
            </div>
            </div>
             </div>
        </div>
    </div>
    </div>
<!-- <div id = "div_layer"  style = "display:none;" class="table-title">
                                                <div class="tabs">
                                                        <label><input name="Fruit1"  value="0"   type="radio" />图表 </label>
                                                        <label><input name="Fruit1"  value="1"   type="radio"  />列表 </label>
                                                    <button id="export_layer" type = "button" >导出列表</button>
                                                </div>
                                                <div id="chart_4" style="width: 100%;margin:0 auto;height:500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);">
                                                </div><div id = "div_list" style = "display:none;">
                                                <table id = "appTable_layer"  ></table></div>
                                            </div>
 -->

    <script>
    $("#chart_2").css('width', $(".c-traffic").width()); 
    $("#chart_4").css('width', "1100px"); 
    </script>
    <!--图表js-->
    <script type="text/javascript">
        $(function() {
        	//$("#c-right").css("width", document.documentElement.clientWidth-500);
        	$("#c-right").css("width", document.documentElement.clientWidth-230-230-10-18);
        	$("#appTable_statistical").css("width", document.documentElement.clientWidth-230-230-10-18-50);
        	$("input[name='queryBizIdStr']").val("-1");
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
       				case "最近1小时" : $(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_MIN");$("#hid_sdate").val(getDate(-60,0,0));$("#hid_edate").val(getDate(0,0,0));break;
       				case "最近24小时" :$(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
       				case "最近7天" : $(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_D");$("#hid_sdate").val(formatDate(getDate(0,0,-7),"D"));$("#hid_edate").val(formatDate(getDate(0,0,0),"D"));break;
       				case "最近30天" : $(".time_scope").hide();$(".searchBtn").show();$("input[name='queryTableName']").val(queryTableName+"_D");$("#hid_sdate").val(formatDate(getDate(0,0,-30),"D"));$("#hid_edate").val(formatDate(getDate(0,0,0),"D"));break;
       				case "自定义范围" :$(".time_scope").show();$(".searchBtn").show();break;
       			}
       			if($(this).html() != "自定义范围"){
       				statisticsObject.search();
    			}
       		});
       		$("#sel_topSize").on('change',function(){
       			$("#hid_limit").val($(this).val());
       			butsearch();
       		});
       		$('#searchBtn').on('click', function() {
       			butsearch();
       		});
       		
       		function butsearch(){
       			var searchFlag = true;
       			$("#hid_queryDetailsId").val(0);
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
       						if(betweentime <= 604800){
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
       				tableRefresh();
       				statisticsObject.search();
       			}
       		}
       		function tableRefresh(){
    	    	$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
    	    }
       	    statisticsObject.search();
        });
    </script>
    <script src="${scriptPath}/view/businessAnalysis/topBusinessAnalysis.js" type="text/javascript"></script>


</body>

</html>
