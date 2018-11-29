<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData" value="${ctx}/sysconfig/configmanage/tablemanage/getTableColumnData.do" scope="page"/>
<c:set var="initTable" value="${ctx}/sysconfig/configmanage/tablemanage/initTable.do" scope="request" />
<c:set var="preurl" value="${ctx}/sysconfig/configmanage/tablemanage" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-系统配置-报表管理</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
</head>

<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con ip">
			<div class="frame">
				<div class="title">
					<i></i> > <span class="blue">系统配置</span> / <span class="blue">配置管理</span>
					/ <span> 报表管理</span>
					<%-- <sec:authorize ifAllGranted="ROLE_SYSCONFIG_CONFIGMANAGE_TABLEMANAGE_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize> --%>
				</div>
				<sec:authorize ifAllGranted="ROLE_SYSCONFIG_CONFIGMANAGE_TABLEMANAGE_QUERY">
				<form id="searchForm" >
					<div class="time_search" style="display: block;">
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div class="fl option">
							<div>
								<label for="">报表名称：</label> 
								<select name="queryTableName" id="queryTableName" class="maps">
									<option selected="selected" value="">--</option>
								</select>
							</div>
						</div>
					</div>
				</form>
				</sec:authorize>
				<div class="regional">
					<div class="c-traffic fl" id = "c-right">
						<div id="trend">
							<div class="table-title">
								<a></a>
								<div class="box box-success">
									<div class="box-body">
										<table id="appTable"></table>
									</div>
								</div>
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
		</div>
		<div class="windowform">
			<form id="operForm">
				<input type="reset" style="display:none;"/>
				<input type="hidden" name="queryId" id="operServiceInfoId" value="0" /> 
				<div>
					<label>报表名称：</label><a id="tableName"></a>
				</div>
				<div>
					<label>字段名称：</label><a id="columnText"></a>
				</div>
				<div>
					<label>显示类型：</label><input name="queryColumnType" type="radio" value="1"/>显示   <input name="queryColumnType" type="radio" value="2"/>不显示
				</div>
				<div>
					<label>显示与隐藏切换：</label><input name="querySwitchable" type="radio" value="1"/>参与   <input name="querySwitchable" type="radio" value="2"/>不参与
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
	<script>
		var queryParamSeq = 0;
		$(function() {
			$("#c-right").css("width", document.documentElement.clientWidth-230-10-18);
			$("#appTable").css("width", document.documentElement.clientWidth-230-10-18-50);
			$.ajax({
				type : 'post',
				url : '${preurl}/selectTableNames.do',
				dataType : 'json',
				success : function(result) {
					$("#queryTableName").empty();
					$.each(result, function(idx, item){
						$("#queryTableName").append("<option value=\""+item.TABLE_NAME+"\">"+item.TABLE_NAME+"</option>");
					});
					$("#queryTableName").val();
					search();
				},
				error : function(errMsg) {
					console.error("加载数据失败");
				}
			});
	    	$("#btnCancel").on("click", function(e) {
	    		$("#operForm input:reset").click();
				layer.closeAll();
			});
	    	$("#operForm input:radio").click(function(e){
	    		$(this).parent().find("input:radio").prop("checked", false);
	    		$(this).prop("checked", true);
	    	});
	    	$("#btnOper").on("click", function(e){
	    		var postData = $("#operForm").formToJSON();
				$.ajax({
					type : 'post',
					url : '${preurl}/update.do',
					data: postData,
					dataType : 'json',
					success : function(result) {
						if(result.flag == 0){
							layer.closeAll();
							layer.msg("修改成功");
							search();
						}else{
							layer.closeAll();
							layer.msg("修改失败");
						}
					},
					error : function(errMsg) {
						layer.closeAll();
						layer.msg("修改失败");
					}
				});
	    	});
			$('#searchBtn')
					.on(
							'click',
							function() {
								var $startTime = $('#startTime'), $endTime = $('#endTime'), startTime = $startTime
										.val(), endTime = $endTime.val();
								if (startTime == '') {
									$startTime.tooltip('show');
									return;
								}
								if (endTime == '') {
									$endTime.tooltip('show');
									return;
								}
								queryParamSeq = 1;
								search();
							});
		});
		// table相关函数queryParams/tableColumnData/updateColumnUser/operateFormatter/events start 
		//查询参数
		function queryParams(params) {
			var data = $('#searchForm').formToJSON();
			data.offset = params.offset;
			data.limit = params.limit;
			data.sort = params.sort;
			data.order = params.order;
			return data;
		}
		var tableColumnDatas;
		function getTableColumnDatas() {
			$.ajax({
				type : 'post',
				url : '${getTableColumnData}',
				async : false,
				dataType : 'json',
				success : function(result) {
					tableColumnDatas = result;
				},
				error : function(errMsg) {
					console.error("加载数据失败");
				}
			});
		}

		function operateFormatter(value, row, index) {
			return '<a class="oper_edit" style="cursor:pointer;">修改</a> <a class="oper_up" style="cursor:pointer;">上升</a> <a class="oper_down" style="cursor:pointer;">下降</a>';
		};

		//定义事件
		window.operateEvents = {
			'click .oper_edit' : function(e, value, row, index) {
				e.preventDefault();
				$("#tableName").html(row.TABLE_NAME);
				$("#columnText").html(row.COLUMN_TEXT);
				
				$("#operForm #operServiceInfoId").val(row.DICT_ID);
				$("#operForm input[name='queryColumnType']").prop("checked", false);
				$("#operForm input[name='querySwitchable']").prop("checked", false);
				$("#operForm input[name='queryColumnType'][value="+row.COLUMN_TYPE+"]").prop("checked", true);
				$("#operForm input[name='querySwitchable'][value="+row.SWITCHABLE+"]").prop("checked", true);
				layer.open({
					title : false,
					type : 1,
					closeBtn : true,
					skin : 'layui-layer-nobg',
					area : [ '400px', '300px' ],
					scrollbar : false,
					shadeClose : false, //点击遮罩关闭
					content : $('#popup01')
				});
			},
			'click .oper_up' : function(e, value, row, index) {
				e.preventDefault();
				var postData = new Object();
				postData.queryId = row.DICT_ID;
				postData.queryColumnOrder = row.COLUMN_ORDER;
				postData.queryTableName = row.TABLE_NAME;
				$.ajax({
					type : 'post',
					url : '${preurl}/updateUp.do',
					data: postData,
					dataType : 'json',
					success : function(result) {
						if(result.flag == 0){
							layer.closeAll();
							layer.msg("修改成功");
							search();
						}else{
							layer.closeAll();
							layer.msg("修改失败");
						}
					},
					error : function(errMsg) {
						layer.closeAll();
						layer.msg("修改失败");
					}
				});
			},
			'click .oper_down' : function(e, value, row, index) {
				e.preventDefault();
				var postData = new Object();
				postData.queryId = row.DICT_ID;
				postData.queryColumnOrder = row.COLUMN_ORDER;
				postData.queryTableName = row.TABLE_NAME;
				$.ajax({
					type : 'post',
					url : '${preurl}/updateDown.do',
					data: postData,
					dataType : 'json',
					success : function(result) {
						if(result.flag == 0){
							layer.closeAll();
							layer.msg("修改成功");
							search();
						}else{
							layer.closeAll();
							layer.msg("修改失败");
						}
					},
					error : function(errMsg) {
						layer.closeAll();
						layer.msg("修改失败");
					}
				});
			}
		};
		function search() {
			getTableColumnDatas();
			tableColumnDatas.splice(13, 0, {
				field : 'operation',
				title : '操作',
				width : 80,
				align : 'center',
				switchable : false,
				formatter : operateFormatter,
				events : operateEvents,
			});
			//tableColumnDatas
			$('#appTable').bootstrapTable("destroy").bootstrapTable({
				method : 'post',
				url : '${initTable}',
				queryParams : queryParams,
				contentType : "application/x-www-form-urlencoded",
				cache : false,
				//height : 400,
				striped : true,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 25, 50, 100, 200 ],
				showColumns : false,
				sidePagination : 'server',
				minimumCountColumns : 1,
				clickToSelect : true,
				paginationFirstText : "首页",
				paginationPreText : '上一页',
				paginationNextText : '下一页',
				paginationLastText : '最后一页',
				columns : tableColumnDatas
			});
		}
		// table相关函数end
	</script>
</body>

</html>