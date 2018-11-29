<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData"  value="${ctx}/common/PAGE_ANALYSIS/getTableColumnData.do"  scope="page" />
<c:set var="updateStaColumnUser"  value="${ctx}/common/PAGE_ANALYSIS/updateColumnUser.do" scope="request" />
<c:set var="initTable" value="${ctx}/traffic/businessAnalysis/pageAnalysis/initTable.do" scope="request" />
<c:set var="exportTable" value="${ctx}/traffic/businessAnalysis/pageAnalysis/exportTable.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-业务分析-质差页面分析</title>
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
queryTableName = "PAGE_QUALITY";
</script>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
    <jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
    <div class="con regional">
        <div class="frame">
            <div class="title">
                <i></i> 
                <span class="blue">业务分析</span> /
                <span class="blue">页面访问量分析</span> /
                <span>质差页面分析</span>
                <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_PAGE_QUERY">
                <button class="fr drop_down">查询</button>
                </sec:authorize>
                <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_PAGE_EXPORT">
                <button id = "exportbtn" class="fr" type = "button" >导出</button>
            	</sec:authorize>
            </div>
            <sec:authorize ifAllGranted="ROLE_PRODUCT_ANALYSIS_PAGE_QUERY">
          <form id="searchForm">
          <div class="time_search" style = "display:none;">
                <div class="fl">
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
                        <input type = "hidden"  id = "hid_limit" value = "10"  />
                    </div>
            	</div>
            	<%-- <tdc:timeSelect domId="timeSelect" queryTableName="FLOW_DIRECTION" timePattern="yyyy-MM-dd HH:mm" type="2"/> --%>
            <div class="fr">
            	<button class="search_button fr"  id="searchBtn"  type="button">查询</button>
            </div>
            <div class="fl option">
                <tdc:selectorProductModule></tdc:selectorProductModule>
                <div>
                	<label>页面名称：</label><input type="text" id="pageName" name="queryPageName" />
                </div>
             	<div>
					<label>URL：</label><input type="text" id="url" name="queryUrl" />
				</div>
            </div>
          </div>
       	  </form>
       	  </sec:authorize>
        <div class="regional">
            <div class="c-traffic fl">
                  <div class="table-title">
                      <a>质差页面分析</a>
                      <div class="w951">
                           <select id = "sel_topSize" >
                           <option value = "10">TOP10</option>
                           <option value = "20">TOP20</option>
                           <option value = "50">TOP50</option>
                           <option value = "0">全部</option>
                           </select>
                        </div>
                      <div id="statistical">
                           <table id = "appTable"  ></table>
                      </div>
                  </div>
            </div>
        </div>
    </div>
    </div>

<script type="text/javascript">
        $(function() {
       		$("input[name='queryTableName']").val(queryTableName+"_H");
       		$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));
  			$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));
       		statisticsObject.search();
       		$(".fl a").bind("click",function(){
       			$(".fl a").each(function(i){
       				$(this).attr("class","");
       			});
       			$(this).attr("class","active");
       			switch ($(this).html()){
       				case "最近24小时" :$(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_H");$("#hid_sdate").val(formatDate(getDate(0,0,-1),"H"));$("#hid_edate").val(formatDate(getDate(0,0,0),"H"));break;
       				case "最近7天" : $(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_D");$("#hid_sdate").val(formatDate(getDate(0,0,-7),"D"));$("#hid_edate").val(formatDate(getDate(0,0,0),"D"));break;
       				case "最近30天" : $(".time_scope").hide();$("input[name='queryTableName']").val(queryTableName+"_D");$("#hid_sdate").val(formatDate(getDate(0,0,-30),"D"));$("#hid_edate").val(formatDate(getDate(0,0,0),"D"));break;
       				case "自定义范围" :$(".time_scope").show();break;
       			}
       		});
       		
        	$('#searchBtn').on('click', function(e) {
   			if($(".time_scope").css("display") != "none"){
				if($("#q_sdate").val() == '' || $("#q_edate").val() == ''){
					alert("请选择自定义范围时间!");
				}
			}
   			$(".fl a").each(function(i){
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
   			});
        	$("#sel_topSize").on('change',function(){
       			$("#hid_limit").val($(this).val());
       			statisticsObject.search();
       		});
        });
    </script>
    <script src="${scriptPath}/view/businessAnalysis/pageAnalysis.js" type="text/javascript"></script>
</body>

</html>
