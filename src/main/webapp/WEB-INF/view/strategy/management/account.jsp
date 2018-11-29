<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>接口账号管理</title>
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
					<i></i><span class="blue">策略配置</span> / <span class="blue">配置管理</span> / <span>接口帐号管理</span>
					<%-- <sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize> --%>
					
				</div>
				<form id="searchForm">
					<div class="time_search" style="display: block;">
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div class="fl option">
							<div>
								<label for="">策略创建时间：</label> <input id="q_sdate" type="text" name="queryStartTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\')}'});" />
								<input id="q_edate" type="text" name="queryEndTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\')}'});" />
							</div>
							<div>
								<label for="">调用用户名：</label> <input id="queryUserName" name="queryUserName" type="text" />
							</div>
							<div>
								<label for="">IP地址：</label> <input id="queryDestIp" name="queryDestIp" type="text" />
							</div>
							<div>
								<label for="">联系人姓名：</label> <input id="queryContact" name="queryContact" type="text" />
							</div>
							<div>
								<label for="">手机号码：</label> <input id="queryMobile" name="queryMobile" type="text" />
							</div>
							<div>
								<label for="">邮箱：</label> <input id="queryEmail" name="queryEmail" type="text" />
							</div>
							<div>
								<label for="">第三方系统名称：</label> <input id="querySystemId" name="querySystemId" type="text" />
							</div>
						</div>
					</div>
				</form>
				<div class="regional">
					<div class="c-traffic fl" id = "c-right">
						<div id="statistical">
							<div class="table-title">
								<div class="w951">
									<sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_ADD">
									<button id="add">新增</button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_DEL">
									<button id="del">删除</button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_EXPORT">
					<button id="export">导出</button>
					</sec:authorize>
								</div>
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
				$.download({url:'${ctx}/strategy/management/account/export.do', data:data});
			});
			$("#add").on("click", function(e) {
				layer.open({
					title : '新增',
					type : 1,
					area : [ '500px', '550px' ],
					shadeClose : true, //点击遮罩关闭
					content : $('#div_layer'),
					end : function(index){
						$("#div_layer input:reset").click();
						validator1.resetForm();
						layer.close(index);
					}
				});
			});
			$("#del").on("click", function(e) {
				var resultData = $('#appTable_01').bootstrapTable("getAllSelections");
				if(resultData.length <= 0){
					$.modal.alert("请选择要删除的数据！");
					return;
				}
				var postData = new Object();
				postData.queryIds = "";
				var queryNames = "";
				$.each(resultData, function(idx, item) {
					postData.queryIds = postData.queryIds
							+ (postData.queryIds.length > 0 ? ","
									: "")
							+ item.ID;
					queryNames = queryNames
							+ (queryNames.length > 0 ? ","
									: "")
							+ item.USERNAME;
				});
				$.modal.confirm("您确定要删除调用帐号【"+queryNames+"】及其用户信息？", function(){
					
					$.ajax({
						type : "POST",
						url : "${ctx}/strategy/management/account/delete.do",
						data : postData,
						dataType : "json",
						success : function(data) {
							if (data.flag == 0) {
								search();
							} else {
								$.modal.alert(data.msg);
							}
							$("#edit_layer input:reset")
									.click();
							layer.closeAll();
						}
					});
				});
			});
			$('#searchBtn').on('click', function() {
				search();
			});
		});
		function operateFormatter(value, row, index) {
			if (row.STATUS == '使用中') {
				return '<sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_STOP"><a class="oper_stop" style="cursor:pointer;">停用</a></sec:authorize>';
			} else if (row.STATUS == '已停用') {
				return '<sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
			} else {
				return '<sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_STOP"><a class="oper_stop" style="cursor:pointer;">停用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_INTERFACE_USER_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
			}
		}
		function pwdFormatter(value, row, index){
			return value.replace(/[\s\S]/g,"*");
		}
		window.operateEvents = {
			'click .oper_stop' : function(e, value, row, index) {
				e.preventDefault();
				var postData = new Object();
				postData.queryStatus = 1;
				postData.queryId = row.ID;
				$.ajax({
					type : "POST",
					url : "${ctx}/strategy/management/account/update.do",
					data : postData,
					dataType : "json",
					success : function(data) {
						if (data.flag == 0) {
							search();
						} else {
							$.modal.alert(data.msg);
						}
					}
				});
			},
			'click .oper_start' : function(e, value, row, index) {
				e.preventDefault();
				var postData = new Object();
				postData.queryStatus = 0;
				postData.queryId = row.ID;
				$.ajax({
					type : "POST",
					url : "${ctx}/strategy/management/account/update.do",
					data : postData,
					dataType : "json",
					success : function(data) {
						if (data.flag == 0) {
							search();
						} else {
							$.modal.alert(data.msg);
						}
					}
				});
			},
			'click .oper_edit' : function(e, value, row, index) {
				e.preventDefault();
				$("#editForm input[name='queryUserName']").val(row.USERNAME);
				$("#editForm input[name='password']").val(row.PASSWD);
				$("#editForm input[name='queryDestIp']").val(row.IP);
				$("#editForm input[name='querySystemId']").val(row.SYSTEM_ID);
				$("#editForm input[name='queryContact']").val(row.CONTACT);
				$("#editForm input[name='queryMobile']").val(row.MOBILE);
				$("#editForm input[name='queryEmail']").val(row.EMAIL);
				$("#editForm input[name='queryId']").val(row.ID);
				$("#editForm input[name='queryStatus']").val((row.STATUS == '使用中'?0:1));
				layer.open({
					title : '修改',
					type : 1,
					area : [ '500px', '550px' ],
					shadeClose : true, //点击遮罩关闭
					content : $('#edit_layer'),
					end : function(index) {
						$("#edit_layer input:reset").click();
						validator2.resetForm();
						layer.close(index);
					}
				});
			}
		};
		function search() {
			var tableColumnData = getTableColumnData('${ctx}/common/POLICY_INTERFACE_ACCOUT/getTableColumnData.do');
			tableColumnData.splice(0, 0, {
				field : 'checkbox',
				title : '选择框',
				width : 80,
				align : 'center',
				switchable : false,
				checkbox : true
			});
			tableColumnData.splice(10, 0, {
				field : 'operation',
				title : '操作',
				width : 80,
				align : 'center',
				switchable : false,
				formatter : operateFormatter,
				events : operateEvents,
			});
			$.each(tableColumnData, function(idx, item){
				if(item.field == 'PASSWD'){
					item.formatter = pwdFormatter;
				}
			});
			$('#appTable_01')
					.bootstrapTable("destroy")
					.bootstrapTable(
							{
								method : 'post',
								url : '${ctx}/strategy/management/account/initTable.do',
								queryParams : queryParams,
								contentType : "application/x-www-form-urlencoded",
								cache : false,
								//height : 400,
								striped : true,
								pagination : true,
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
							})
					.on(
							"column-switch.bs.table",
							function(e, field, checked) {
								updateColumnUser(e, field, checked,
										'${ctx}/common/POLICY_INTERFACE_ACCOUT/updateColumnUser.do');
							});
			$('#appTable_01').bootstrapTable("refresh", {
				silent : true
			});
		}
	</script>
	<jsp:include page="/WEB-INF/view/strategy/management/accountLayer.jsp" flush="true"></jsp:include>
</body>

</html>