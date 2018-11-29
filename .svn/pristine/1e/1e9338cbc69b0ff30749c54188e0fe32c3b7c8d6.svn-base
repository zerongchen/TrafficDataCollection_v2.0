<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumn" value="${ctx}/common/POLICY_STATS_STRATEGY/getTableColumnData.do" scope="page" />
<c:set var="getTableColumnData" value="${ctx}/strategy/management/trafficStatStrategyManagement/initTable.do" scope="page" />
<c:set var="insert" value="${ctx}/strategy/management/trafficStatStrategyManagement/insert.do" scope="page" />
<c:set var="update" value="${ctx}/strategy/management/trafficStatStrategyManagement/update.do" scope="page" />
<c:set var="delete" value="${ctx}/strategy/management/trafficStatStrategyManagement/delete.do" scope="page" />
<c:set var="updateStatus" value="${ctx}/strategy/management/trafficStatStrategyManagement/updateStatus.do" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>统计数据管理</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<style type="text/css">
.error {
	color: red
}

</style>
</head>
<script type="text/javascript">
var getTableColumnUrl = '${getTableColumn}',
    getTableColumnDataUrl = '${getTableColumnData}';
var insertUrl = '${insert}',
     updateUrl = '${update}',
     deleteUrl = '${delete}',
     updateStatusUrl = '${updateStatus}';
var getFTPUrl = '${getFTPUrlData}';
</script>
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con quality_regional">
			<div class="frame">
				<div class="title">
					<i></i><span class="blue">策略配置</span> / <span class="blue">策略管理</span> / <span>统计数据</span>
					<%-- <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize> --%>
					<!-- <button class="fr" id="export">导出</button> -->
				</div>
				<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_QUERY">
				<form id="searchForm">
				<div class="time_search " style="display: block;">
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div class="fl option">
							<tdc:statStrategyNameSelect domId="queryStrategyId"  domName="queryStrategyId" ></tdc:statStrategyNameSelect>
							<div>
								<label for="">策略创建时间：</label>
								<input id="q_sdate" type="text" name="queryStartTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\')}'});" />
								<input id="q_edate" type="text" name="queryEndTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\')}'});" />
							</div>
							<tdc:ftpServerSelect domId="ftpServerId" domName="queryFTPServerId"></tdc:ftpServerSelect>
							<div>
								<label for="">运行状态：</label>
								<select id="queryStatus" name="queryStatus">
								<option selected="" value="-1">请选择</option>
								<option value="0">未启用</option>
								<option value="1">正常运行</option>
								<option value="2">传输终止</option>
								<option value="3">已停用</option>
								</select>
							</div>
							<div>
								<label for="">报表类型：</label>
								<select id="queryReportType" name="queryReportType">
								<option selected="" value="-1">请选择</option>
								<option value="1">流量流向统计数据</option>
								<option value="2">流量监测统计数据</option>
								<option value="3">用户感知分析数据</option>
								<option value="4">业务引用分析数据</option>
								</select>
							</div>
						</div>
					</div>
				</form>
				</sec:authorize>
					<div class="regional">
						<div class="c-traffic fl" id = "c-right">
							<div id="statistical">
								<div class="table-title">
									<div class="w951">
									<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_ADD">
									<button id="add">新增</button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_DEL">
									<button id="del">删除</button>
									</sec:authorize>
									</div>
									<div class="box box-success"><div class="box-body"><table id="appTable"></table></div></div>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	
	<div class="popup cloaseD" id="div_layer_add">
        <div class="windowtitle" >新增策略<button class="close" id="popup02close">x</button></div>
        <div class="windowform">
        <form id="editForm">
        	<input type="hidden" id = "radioId" value = "-1"/>
        	<input type="hidden" id = "rowIds" name="queryStrategyIds" value = ""/>
        	<input type="hidden" id = "editQueryStatus" name="queryStatus" value = "0"/>
            <div id="strategyIdHide">
                <label >策略ID：</label><label id="editQueryStrategy" ></label><input type="hidden" name="queryStrategyId"/>
            </div>
            <div>
                <label >策略名称：</label><input type="text" id="editQueryStrategyName" name="queryStrategyName"><!-- <i class="required">*</i> -->
            </div>
            <div>
				<tdc:ftpServerSelect domId="editQueryFTPServerId" domName="queryFTPServerId"></tdc:ftpServerSelect>
			</div>
            
        	<div>
                <label >报表类型:</label>
				<select id="editQueryReportType" name="queryReportType">
				<option selected="" value="-1">请选择</option>
				<option value="1">流量流向统计数据</option>
				<option value="2">流量监测统计数据</option>
				<option value="3">用户感知分析数据</option>
				<option value="4">业务引用分析数据</option>
				</select>
		
            </div>
            <div class="confirm">
                <label></label>
                <button type="button" id ="btnOper">确定</button>
				<button type="button" class="btnwhite" id ="btnCancel">取消</button>
            </div>
        </form>
        </div>
        <div class="bottom"></div>
     </div>
     
     <div class="popup" id="strategy_layer">
        <div class="windowtitle" style="width:97%;">策略详情<button class="close" id="popup03close">x</button></div>
        <div class="windowform">
        <form id="strategyForm">
            <div >
                <label >策略ID：</label><label id="strategyQueryStrategyId" name="queryStrategyId"></label>
            </div> 
            <div>
                <label >策略名称：</label><input type="text" id="strategyQueryStrategyName" name="queryStrategyName">
            </div>
            <div>
                <label >FTP服务器：</label><input type="text" id="strategyQueryFTPServerId" name="queryFTPServerId">
            </div>
            <div>
                <label >报表类型：</label><input type="text" id="strategyQueryReportType"  name="queryReportType">
            </div>
            <input type="reset" style="display:none;"/>
          </form>
          </div>
      </div>
      <div class="popup" id="ftp_layer"  style="width:550px">
        <div class="windowtitle">FTP服务器详情<button class="close" id="popup04close">x</button></div>
			<table id="ftpTable"></table>
      </div>
<script type="text/javascript">
	$(function(){
		$("#c-right").css("width", document.documentElement.clientWidth-230-10-18);
		$("#appTable").css("width", document.documentElement.clientWidth-230-10-18-50);
		statisticsObject.search();
		$('#searchBtn').on('click', function() {
			statisticsObject.search();
		});
	});
		
		$("#add").on("click", function(e) {
			$("#strategyIdHide").hide();
			statisticsObject.initLayer();
			layer.open({
				title : false,
				closeBtn: false,
				type : 1,
				area : ['550px', '350px'],
				shadeClose : false, //点击遮罩关闭
				content : $('#div_layer_add')
			});
			$(".layui-layer").css("z-index", "100");
			$(".layui-layer-shade").css("z-index", "99");
		});	
		$("#btnOper").on("click", function(e){
    		  if($("#radioId").val() > 0){
    			statisticsObject.update();
    		}else{
    			statisticsObject.insert();
    		}
		});	
		$("#btnCancel").on("click", function(e) {
			statisticsObject.validator.resetForm();
			layer.closeAll();
		});
		$("#popup02close").on("click", function(e) {
			statisticsObject.validator.resetForm();
			layer.closeAll();
		});
		$("#popup03close").on("click", function(e) {
			layer.closeAll();
		});
		$("#popup04close").on("click", function(e) {
			layer.closeAll();
		});
        $("#del").on("click",function(e){
    		var resultData = $('#appTable').bootstrapTable("getAllSelections");
			if(resultData.length <= 0){
				layer.msg("请选择要删除的数据！");
				return;
			}else{
				var queryStrategyIds = "";
				$.each(resultData, function(idx, item) {
					queryStrategyIds = queryStrategyIds + (queryStrategyIds.length > 0 ? ",": "") + item.STRATEGY_ID;
				});
				$("#rowIds").val(queryStrategyIds);
				layer.confirm('您确定要删除选中的数据吗？', {
						title: '提示',
					  	btn: ['确定','取消'] //按钮
					}, function(){
						statisticsObject.deleteData();
						statisticsObject.search();
					}
				);
			}
    	});
        function operateFormatter(value, row, index) {
        	if (row.STATUS == '未启用') {
        		return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
        	} else if (row.STATUS == '正常运行' || row.STATUS == '传输终止') {
        		return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_STOP"><a class="oper_stop" style="cursor:pointer;">停用</a></sec:authorize>';
        	} else if (row.STATUS == '已停用'){
        		return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
        	}
        	return;
        }
</script>
	<%-- <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_START">
       <input type="hidden" id="authorStart" value="1"/>
    </sec:authorize>
	<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_STOP">
       <input type="hidden" id="authorStop" value="2"/>
    </sec:authorize>
	<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_EDIT">
       <input type="hidden" id="authorEdit" value="3"/>
    </sec:authorize> --%>
	<script type="text/javascript" src="${scriptPath}/view/strategy/trafficStatStrategyManagement.js"></script> 
</body>
</html>