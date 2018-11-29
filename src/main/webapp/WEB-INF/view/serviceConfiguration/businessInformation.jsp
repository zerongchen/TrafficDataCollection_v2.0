<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData" value="${ctx}/common/DIC_BIZ_INFO/getTableColumnData.do" scope="page" />
<c:set var="initStaTable" value="${ctx}/serviceConfiguration/businessInformation/initStaTable.do" scope="request" />
<c:set var="insertUrl" value="${ctx}/serviceConfiguration/businessInformation/insert.do" scope="request" />
<c:set var="updateUrl" value="${ctx}/serviceConfiguration/businessInformation/update.do" scope="request" />
<c:set var="deleteUrl" value="${ctx}/serviceConfiguration/businessInformation/delete.do" scope="request" />
<c:set var="uploadUrl" value="${ctx}/serviceConfiguration/businessInformation/dataImport.do" scope="request" />

<c:set var="exportUrl" value="${ctx}/serviceConfiguration/businessInformation/export.do" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-业务配置-业务信息管理</title>
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
var getTableStaColumnDataUrl = '${getTableStaColumnData}',
initStaTableUrl = '${initStaTable}',
exportUrl = '${exportUrl}',
insertUrl = '${insertUrl}',
updateUrl = '${updateUrl}',
deleteUrl = '${deleteUrl}',
uploadUrl = '${uploadUrl}';
</script>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
	<div class="con">
		<div class="frame">
			<div class="title">
				<i></i> <span class="blue">业务配置</span> / <span>业务信息管理</span>
				<sec:authorize ifAllGranted="ROLE_QUERY_WEB_SERVICEINFO">
					<button class="fr drop_down">查询</button>
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_QUERY_WEB_SERVICEINFO_EXPORT">
					<button class="fr" id="exprot" type="button">导出</button>
				</sec:authorize>
			</div>
			<sec:authorize ifAllGranted="ROLE_QUERY_WEB_SERVICEINFO">
				<form id="searchForm">
					<div class="time_search" style="display: none;">
						<div class="fl option">
							<tdc:selector switches="8"></tdc:selector>
							<input type="hidden" id="hid_layerIndex" />
						</div>
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div style="clear: both"></div>
					</div>
				</form>
			</sec:authorize>
			<div class="regional">
				<div class="c-traffic fl">
					<div id="trend">
						<div class="table-title">
							<!--  <a>业务流量排名</a> -->
							<div class="w951">
								<sec:authorize ifAllGranted="ROLE_QUERY_WEB_SERVICEINFO_DEL">
									<button id="del" type="button">删除</button>
								</sec:authorize>
								<sec:authorize ifAllGranted="ROLE_QUERY_WEB_SERVICEINFO_ADD">
									<button id="add" type="button">新增业务</button>
								</sec:authorize>
								<sec:authorize ifAllGranted="ROLE_QUERY_WEB_SERVICEINFO_IMPORT">
									<button id="import" type="button">批量导入</button>
								</sec:authorize>
								<!-- <button>导出</button> -->
							</div>
							<div class="wrap">
								<table id="appTable_statistical" class="table"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="popup" id="popup01">
		<div class="windowtitle">
			业务
			<button class="close" id="popup01close">x</button>
		</div>
		<div class="windowform">
			<form id="operForm">
				<input type="hidden" name="queryId" id="operServiceInfoId" value="0" /> <input type="hidden" name="queryIds" id="operServiceInfoIds" value="" /> <input
					type="hidden" name="queryIpPortId" id="operIpPortId" value="0" /> <input type="hidden" name="queryIpMappingId" id="operIpMappingId" value="0" />
				<input type="hidden" name="queryIpPortIds" id="operIpPortIds" value="" />
				<tdc:selectorLayer></tdc:selectorLayer>
				<tdc:selectorRoomBuild></tdc:selectorRoomBuild>
				<div>
					<label>开始IP：</label> <input type="text" name="queryStartIp" id="operStartIp" value=""> <i class="required">*</i>
				</div>
				<div>
					<label>结束IP：</label> <input type="text" name="queryEndIp" id="operEndIp" value=""> <i class="required">*</i>
				</div>
				<div>
					<label>开始端口：</label> <input type="number" name="queryStartPort" id="operStartPort" value=""> <i class="required">*</i>
				</div>
				<div>
					<label>结束端口：</label> <input type="number" name="queryEndPort" id="operEndPort" value=""> <i class="required">*</i>
				</div>
				<div class="confirm">
					<label></label>
					<button type="button" id="btnOper">确定</button>
					<button type="button" class="btnwhite" id="btnCancel">取消</button>
				</div>
			</form>

		</div>
		<div class="bottom"></div>
	</div>
	<tdc:uploadFile targetId="import" requestUrl="${uploadUrl}" callback="btnSearch" />
	<script type="text/javascript">
     $(function() {
    	$("#add").click(function () {
        	var layerIndex = layer.open({
        		title: false,
                type: 1,
                closeBtn:false,
                skin: 'layui-layer-nobg',
                area: ['534px', '440px'],
                scrollbar: false,
                shadeClose: false, //点击遮罩关闭
                content: $('#popup01')
            });
        	$("#hid_layerIndex").val(layerIndex);
        	statisticsObject.initLayer();
        });
    	$("#btnCancel").on("click", function(e) {
    		statisticsObject.validator.resetForm();
			layer.closeAll();
		});
    	$(".close").on("click", function(e) {
    		statisticsObject.validator.resetForm();
			layer.closeAll();
		});
    	$('#searchBtn').on('click', function() {
    		btnSearch();
   		});
    	$("#btnOper").on("click", function(e){
    		if($("#operServiceInfoId").val() > 0){
    			statisticsObject.update();
    			layer.close($("#hid_layerIndex").val());
    		}else{
    			statisticsObject.insert();
    		}
    		//tableRefresh();
			search();
		});	
    	$("#del").on("click",function(e){
    		var resultData = $('#appTable_statistical').bootstrapTable("getAllSelections");
			if(resultData.length <= 0){
				layer.msg("请选择要删除的数据！");
				return;
			}else{
				var queryIds = "";
				var queryIpPortIds = "";
				$.each(resultData, function(idx, item) {
					queryIds = queryIds + (queryIds.length > 0 ? ",": "") + item.ID;
					queryIpPortIds = queryIpPortIds + (queryIpPortIds.length > 0 ? ",": "") + item.IPPORT_ID;
				});
				$("#operServiceInfoIds").val(queryIds);
				$("#operIpPortIds").val(queryIpPortIds);
				layer.confirm('您确定要删除选中的数据吗？', {
						title: '提示',
					  	btn: ['确定','取消'] //按钮
					}, function(){
						statisticsObject.deleteData();
						btnSearch();
					}
				);
			}
    	});
    	search();
     });   
 	function btnSearch(){
		tableRefresh();
		search();
		}
	function tableRefresh(){
    	$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
    }
	function search() {
		getTableColumnData(getTableStaColumnDataUrl);
		tableColumnData.splice(0, 0, {
			field : 'checkbox',
			title : '选择框',
			width : 80,
			align : 'center',
			switchable : false,
			checkbox : true
		});
		tableColumnData.splice(19, 0, {
			field : 'operation',
			title : '操作',
			width : 80,
			align : 'center',
			switchable : false,
			formatter : '<sec:authorize ifAllGranted="ROLE_QUERY_WEB_SERVICEINFO_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>',
			events : operateEvents,
		});
		$('#appTable_statistical').bootstrapTable("destroy").bootstrapTable({
			method : 'post',
			url : initStaTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 25 ],
			showColumns : false,
			sortable: false, 
			sidePagination : 'server',
			minimumCountColumns : 1,
			clickToSelect : true,
			paginationFirstText : "首页",
			paginationPreText : '上一页',
			paginationNextText : '下一页',
			paginationLastText : '最后一页',
			columns : tableColumnData
		}).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked, updateStaColumnUserUrl);
		});
		$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
	}
    </script>
	<script src="${scriptPath}/view/serviceConfiguration/businessInformation.js" type="text/javascript"></script>

</body>

</html>
