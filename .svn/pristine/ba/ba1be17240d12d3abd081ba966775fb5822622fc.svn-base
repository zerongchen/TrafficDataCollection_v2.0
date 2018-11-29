<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumn" value="${ctx}/common/POLICY_FTP_SERVER/getTableColumnData.do" scope="page" />
<c:set var="getTableColumnData" value="${ctx}/strategy/management/ftpServerManagement/initTable.do" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>FTP服务器管理</title>
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
<script type="text/javascript">
var getTableColumnUrl = '${getTableColumn}';
var getTableColumnDataUrl = '${getTableColumnData}';
</script>
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con quality_regional">
			<div class="frame">
				<div class="title">
					<i></i><span class="blue">策略配置</span> / <span class="blue">策略管理</span> / <span>FTP服务器</span>
					<%-- <sec:authorize ifAllGranted="ROLE_FTP_SERVER_MANAGEMENT_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize> --%>
					<!-- <button class="fr" id="export">导出</button> -->
				</div>
				<sec:authorize ifAllGranted="ROLE_FTP_SERVER_MANAGEMENT_QUERY">
				<form id="searchForm">
				<div class="time_search" style="display: block;">
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div class="fl option">
							<div>
								<label for="">创建时间：</label>
								<input id="q_sdate" type="text" name="queryStartTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\')}'});" />
								<input id="q_edate" type="text" name="queryEndTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\')}'});" />
							</div>
							<tdc:ftpServerSelect domId="ftpServerId" domName="queryFTPServerId" ></tdc:ftpServerSelect>
							<tdc:ftpIpSelect domId="ftpServerIp" domName="queryFTPServerIp"></tdc:ftpIpSelect>
							<!-- <div>
								<label for="">IP地址：</label> <input id="ftpServerIp" name="queryFTPServerIp" type="text" />
							</div> -->
							<div>
								<label for="">端口：</label> <input id="queryPort" name="queryPort" type="text" />
							</div>
							<div>
								<label for="">用户名：</label> <input id="queryUserName" name="queryUserName" type="text" />
							</div>
						</div>
					</div>
				</form>
				</sec:authorize>
				<div class="regional">
					<div class="c-traffic fl" id = "c-right">
						<!-- <div id="statistical"> -->
							<div class="table-title">
								<div class="w951">
									<sec:authorize ifAllGranted="ROLE_FTP_SERVER_MANAGEMENT_ADD">
									<button id="add">新增</button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_FTP_SERVER_MANAGEMENT_DEL">
									<button id="del">删除</button>
									</sec:authorize>
								</div>
								<!-- <div class="box box-success">
									<div class="box-body"> -->
										<table id="appTable"></table>
								<!-- 	</div>
								</div> -->
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$("#c-right").css("width", document.documentElement.clientWidth-230-10-18);
		$("#appTable").css("width", document.documentElement.clientWidth-230-10-18-50);
		search();
		$('#searchBtn').on('click', function() {
			search();
		});
	});
	$("#add").on("click", function(e) {
			layer.open({
				title : '新增',
				type : 1,
				area : [ '500px', '450px' ],
				shadeClose : true, //点击遮罩关闭
				content : $('#div_layer'),
				end : function(index){
					$("#div_layer input:reset").click();
					layer.close(index);
				}
			});
		});	
	$("#del").on("click", function(e) {
				var resultData = $('#appTable').bootstrapTable("getAllSelections");
				if(resultData.length <= 0){
					$.modal.alert("请选择要删除的数据！");
					return;
				}
				$.modal.confirm("确认删除？", function(){
					var postData = new Object();
					postData.queryFTPServerIds = "";
					$.each(resultData, function(idx, item) {
						postData.queryFTPServerIds = postData.queryFTPServerIds
								+ (postData.queryFTPServerIds.length > 0 ? "," : "")
								+ item.FTP_ID
					});
					$.ajax({
						type : "POST",
						url : "${ctx}/strategy/management/ftpServerManagement/delete.do",
						data : postData,
						dataType : "json",
						success : function(data) {
							if (data.flag == 0) {
								search();
							} else {
								$.modal.alert(data.msg);
							}
							$("#edit_layer input:reset").click();
							layer.closeAll();
						}
					});
				});
			});
	function pwdFormatter(value, row, index){
		return value.replace(/[\s\S]/g,"*");
	}
	function operateFormatter(value, row, index) {
		 return '<sec:authorize ifAllGranted="ROLE_FTP_SERVER_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
	};
	window.operateEvents = {
			'click .oper_edit' : function(e, value, row, index) {
				e.preventDefault();
				$("#editForm input[name='queryFTPServerId']").val(row.FTP_ID);
				$("#editForm input[name='queryFTPServerName']").val(row.FTP_NAME);
				$("#editForm input[name='queryFTPServerIp']").val(row.IP);
				$("#editForm input[name='queryPort']").val(row.PORT);
				$("#editForm input[name='queryUserName']").val(row.USERNAME);
				$("#editForm input[name='password']").val(row.PASSWD);
				layer.open({
					title : '修改',
					type : 1,
					area : [ '500px', '450px' ],
					shadeClose : true, //点击遮罩关闭
					content : $('#edit_layer'),
					end : function(index) {
						$("#edit_layer input:reset").click();
						layer.close(index);
					}
				});
			}
		};
		
	function search(){
		var tableColumnData = getTableColumnData(getTableColumnUrl);
		tableColumnData.splice(0, 0, {
			field : 'checkbox',
			title : '选择框',
			width : 80,
			align : 'center',
			switchable : false,
			checkbox : true
		});
		tableColumnData.splice(9, 0, {
			field : 'operation',
			title : '操作',
			width : 80,
			align : 'center',
			switchable : false,
			formatter : operateFormatter,
			events : operateEvents,
		});
		/* $.each(tableColumnData, function(idx, item){
			if(item.field == 'PASSWD'){
				item.formatter = pwdFormatter;
			}
		}); */
		$('#appTable').bootstrapTable("destroy").bootstrapTable(
						{
							method : 'post',
							url : getTableColumnDataUrl,
							queryParams : queryParams,
							contentType : "application/x-www-form-urlencoded",
							cache : false,
							//height : 400,
							striped : true,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 50 ],
							showColumns : false,
							sortable: false,
							sidePagination : 'server',
							minimumCountColumns : 1,
							clickToSelect : true,
							//showRefresh : true,
							paginationFirstText : "首页",
							paginationPreText : '上一页',
							paginationNextText : '下一页',
							paginationLastText : '最后一页',
							columns : tableColumnData
						})
						.on("column-switch.bs.table", function(e, field, checked) {
							updateColumnUser(e, field, checked, getTableColumnUrl);
						});
		/* $('#appTable').bootstrapTable("refresh", {
			silent : true
		}); */
	};
	
	$("#export").on('click', function(e){
		tableColumnData = $('#appTable').bootstrapTable("getOptions").columns[0];
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
		$.download({url:'${ctx}/strategy/management/ftpServerManagement/export.do', data:data});
	});
	</script>
	<jsp:include page="/WEB-INF/view/strategy/ftpServerManagement/ftpServerLayer.jsp" flush="true"></jsp:include>
</body>
</html>