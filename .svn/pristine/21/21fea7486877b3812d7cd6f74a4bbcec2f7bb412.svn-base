<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData"  value="${ctx}/common/SYSLOG_OPERATE/getTableColumnData.do"  scope="page" />
<c:set var="initTable" value="${ctx}/syslog/operateLog/operateLog/initTable.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-系统日志-操作日志</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
</head>
<script type="text/javascript">
var getTableStaColumnDataUrl = '${getTableStaColumnData}',
initTableUrl = '${initTable}';
</script>
<body>
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
    <jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
    <div class="con regional">
        <div class="frame">
            <div class="title">
                <i></i> 
                <span class="blue">系统日志</span> /
                <span>操作日志</span>
                <%-- <sec:authorize ifAllGranted="ROLE_SYSTEM_OPERATE_LOG_QUERY">
                <button class="fr drop_down">查询</button>
                </sec:authorize> --%>
            </div>
            <sec:authorize ifAllGranted="ROLE_SYSTEM_OPERATE_LOG_QUERY">
          <form id="searchForm">  
          <div class="time_search" style = "display:block;">
       		    <div>
	            	<label>操作类型：</label>
	            	<select id="operateType" name="queryOperateType">
	            		<option value="-1">全部</option>
	            		<option value="1">添加</option>
	            		<option value="2">修改</option>
	            		<option value="3">删除</option>
	            	</select>
	            </div>
       		    <div>
	            	<label>操作模块：</label>
	            	<select id="operateModule" name="queryOperateModule">
	            		<option value="-1">全部</option>
	            		<option value="1">1</option>
	            		<option value="2">2</option>
	            	</select>
	            </div>
	            <div>
	            	<label>用户帐号</label><input type="text" id="userName" name="queryUserName"/>
	            </div>
                <div class="fl">
                	<div class="time_scope fl">
                        <label for="">开始时间：</label>
                        <input id="q_sdate" name = "queryStartTime" type="text"  onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\',{d:0});}'   });"/>
                        <label for="">至&nbsp;结束时间：</label>
                        <input id="q_edate" name = "queryEndTime"  type="text"  onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\',{d:0});}'  });"/>
                    </div>
            	</div>
	            <div class="fr">
	            	<button class="search_button fr"  id="searchBtn"  type="button">查询</button>
	            </div>
        	</div>
       	  </form>
       	  </sec:authorize>
        <div class="regional">
            <div class="c-traffic fl"  id = "c-right">
                <div class="table-title">
                    <table id = "appTable"  ></table>
                </div>
            </div>
        </div>
    </div>
    </div>

   <script type="text/javascript">
        $(function() {
        	$("#c-right").css("width", document.documentElement.clientWidth-230-10-18);
        	$("#appTable").css("width", document.documentElement.clientWidth-230-10-18-50);
        	search();
        });
        $("#searchBtn").on("click", function(){
        	search();
        });
        function search(){
    		getTableColumnData(getTableStaColumnDataUrl);
    		$('#appTable').bootstrapTable("destroy").bootstrapTable({
    			method : 'post',
    			url : initTableUrl,
    			queryParams : queryParams,
    			contentType : "application/x-www-form-urlencoded",
    			cache : false,
    			/* height : 400, */
    			striped : true,
    			pagination : true,
    			pageSize : 10,
    			pageList : [ 10, 20, 50 ],
    			showColumns : false,
    			sidePagination : 'server',
    			minimumCountColumns : 1,
    			clickToSelect : true,
    			paginationFirstText : "首页",
    			paginationPreText : '上一页',
    			paginationNextText : '下一页',
    			paginationLastText : '最后一页',
    			columns : tableColumnData
    		});
    	};
    </script>
</body>
</html>