<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>中移动流量采集监测系统-系统配置-阈值管理</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<style type="text/css">
.error {color:red}
</style>
</head>

<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con quality_regional">
			<div class="frame">
				<div class="title">
					<i></i><span class="blue">系统配置</span> / <span class="blue">指标管理</span> / <span>阈值管理</span>
					<%-- <sec:authorize ifAllGranted="ROLE_SYSCONFIG_THRESHOLD_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize> --%>
					<%-- <sec:authorize ifAllGranted="ROLE_SYSCONFIG_THRESHOLD_EXPORT">
					<button class="fr" id="export">导出</button>
					</sec:authorize> --%>
				</div>
				<sec:authorize ifAllGranted="ROLE_SYSCONFIG_THRESHOLD_QUERY">
				<form id="searchForm">
					<div class="time_search" style="display: block;">
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div class="fl option">
							<div>
								<label for="">指标名称：</label> <input id="quotaName" name="quotaName" type="text" />
							</div>
						</div>
					</div>
				</form>
				</sec:authorize>
				<div class="regional">
					<div class="c-traffic fl" id = "c-right">
						<div id="statistical">
							<div class="table-title">
								<div class="box box-success">
									<div class="box-body">
										<table id="appTable_01"></table>
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
			$("#appTable_01").css("width", document.documentElement.clientWidth-230-10-18-50);
			search();
			$('#searchBtn').on('click', function(e) {
				search();
			});
			$("#export").on('click', function(e){
				tableColumnData = $('#appTable_01').bootstrapTable("getOptions").columns[0];
				var data = new Object();
				data.headers = new Array();
				data.fields = new Array();		
				data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
				$.each(tableColumnData, function(idx, item){
					if(item.visible == true && item.field!='operation' && item.field != 'checkbox'){
						data.headers.push(item.title);
						data.fields.push(item.field);
					}
				});
				$.download({url:'${ctx}/sysconfig/quota/threshold/export.do', data:data});
			});
		});
		function operateFormatter(value, row, index) {
			return '<sec:authorize ifAllGranted="ROLE_SYSCONFIG_THRESHOLD_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
		}
		window.operateEvents = {
				'click .oper_edit' : function(e, value, row, index) {
					e.preventDefault();
					$("#editForm a[id='quotaName']").html(row.QUOTA_NAME);
					$("#editForm input[name='quotaWeight']").val(row.QUOTA_WEIGHT);
					$("#editForm input[name='quotaFullPoint']").val(row.QUOTA_FULL_POINT);
					$("#editForm input[name='quotaGoodPoint']").val(row.QUOTA_GOOD_POINT);
					$("#editForm input[name='quotaBasePoint']").val(row.QUOTA_BASE_POINT);
					$("#editForm input[name='quotaStep']").val(row.QUOTA_STEP);
					$("#editForm input[name='queryId']").val(row.QUOTA_ID);
					layer.open({
						title : '修改',
						type : 1,
						area : [ '500px', '460px' ],
						shadeClose : true, //点击遮罩关闭
						content : $('#edit_layer'),
						end : function(index) {
							$("#edit_layer input:reset").click();
							validator1.resetForm();
							layer.close(index);
						}
					});
				}
		}
		function search(){
			var tableColumnData = getTableColumnData('${ctx}/common/WEB_MODULE_QUOTA/getTableColumnData.do');
			tableColumnData.splice(8, 0, {
				field : 'operation',
				title : '操作',
				width : 80,
				align : 'center',
				switchable : false,
				formatter : operateFormatter,
				events : operateEvents,
			});
			$('#appTable_01').bootstrapTable("destroy").bootstrapTable({
				method : 'post',
				url : '${ctx}/sysconfig/quota/threshold/initTable.do',
				queryParams : queryParams,
				contentType : "application/x-www-form-urlencoded",
				cache : false,
				//height : 400,
				striped : true,
				pagination : false,
				pageSize : 10,
				pageList : [ 10, 20, 50 ],
				showColumns : false,
				sidePagination : 'server',
				minimumCountColumns : 1,
				clickToSelect : true,
				showRefresh : false,
				paginationFirstText : "首页",
				paginationPreText : '上一页',
				paginationNextText : '下一页',
				paginationLastText : '最后一页',
				columns : tableColumnData
			}).on("column-switch.bs.table", function(e, field, checked) {
				updateColumnUser(e, field, checked, '${ctx}/common/WEB_MODULE_QUOTA/updateColumnUser.do');
			});
		}
	</script>
	<jsp:include page="/WEB-INF/view/sysconfig/quota/thresholdLayer.jsp" flush="true"></jsp:include>
</body>

</html>