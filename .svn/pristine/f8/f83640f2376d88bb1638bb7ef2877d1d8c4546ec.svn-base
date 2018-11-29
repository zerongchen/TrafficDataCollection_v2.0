<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="preurl" value="${ctx}/strategy/management/dataReuse" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>数据复用监控</title>
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
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con quality_regional">
			<div class="frame">
				<div class="title">
					<i></i><span class="blue">策略配置</span> / <span class="blue">策略管理</span> / <span>数据复用监控</span>
					<%-- <sec:authorize ifAllGranted="ROLE_DATA_REUSE_QUERY">
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
								<label for="">策略类别：</label>
								<select id="queryStrategyType" name="queryStrategyType">
								<option selected="selected" value="-1">请选择</option>
								<option value="1">流量采集策略</option>
								<option value="2">流量还原策略</option>
								<option value="3">统计数据策略</option>
								</select>
							</div>
							<div>
								<label for="">运行状态：</label>
								<select id="queryStatus" name="queryStatus">
								<option selected="selected" value="-1">请选择</option>
								<option value="0">未启用</option>
								<option value="1">正常运行</option>
								<!-- <option value="2">传输终止</option> -->
								<option value="3">已停用</option>
								</select>
							</div>
							<tdc:ftpServerSelect domId="ftpServerId" domName="queryFTPServerId"></tdc:ftpServerSelect>
							<tdc:strategyReNameSelect domId="queryStrategyId"  domName="queryStrategyId" ></tdc:strategyReNameSelect>
						</div>
					</div>
				</form>
				<div class="regional">
					<div class="c-traffic fl" id = "c-right">
						<div id="statistical">
							<div class="table-title">
							<div class="w951">
							<sec:authorize ifAllGranted="ROLE_DATA_REUSE_EXPORT">
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
			$('#searchBtn').on('click', function() {
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
				$.download({url:'${preurl}/export.do', data:data});
			});
		});
		function search() {
			var tableColumnData = [{field: 'START_TIME',title: '策略创建时间',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'create_user',title: '创建者',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'CREATET_TYPE',title: '创建方式',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'STRATEGY_ID',title: '策略ID',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'STRATEGY_TYPE_NAME',title: '策略类别',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'START_TIME',title: '运行开始时间',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'STATUS',title: '运行状态',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'SUCCESS_FILE_NUM',title: '成功文件数',width: 80,align: 'center',valign: 'middle', switchable:false},
			{field: 'FAIL_FILE_NUM',title: '失败文件数',width: 80,align: 'center',valign: 'middle', switchable:false}];
			tableColumnData.splice(5, 0, {
				field : 'STRATEGY_NAME',
				title : '策略名称',
				width : 100,
				align : 'center',
				switchable : false,
				formatter : 
					function(value,row,index){
			            return '<a class="strategy_view" style="cursor:pointer;">'+row.STRATEGY_NAME+'</a>';
		             },
				events : strategyNameEvents,
			});
			tableColumnData.splice(6, 0, {
				field : 'FTP_SERVER',
				title : 'FTP服务器',
				width : 100,
				align : 'center',
				switchable : false,
				formatter : 
					function(value,row,index){
					return '<a class="ftp_edit" style="cursor:pointer;">'+row.FTP_SERVER+'</a>';
				},
				events : ftpEvents,
			});
			tableColumnData.splice(17, 0, {
				field : 'operation',
				title : '趋势分析',
				width : 80,
				align : 'center',
				switchable : false,
				formatter : operateFormatter,
				events : operateEvents,
			});
			$('#appTable_01')
					.bootstrapTable("destroy")
					.bootstrapTable(
							{
								method : 'post',
								url : '${preurl}/initTable.do',
								queryParams : queryParams,
								contentType : "application/x-www-form-urlencoded",
								cache : false,
								//height : 400,
								striped : true,
								pagination : true,
								pageSize : 10,
								pageList : [ 10, 20, 50 ],
								showColumns : false,
								sortable : false,
								sidePagination : 'server',
								minimumCountColumns : 1,
								clickToSelect : true,
								//showRefresh : true,
								paginationFirstText : "首页",
								paginationPreText : '上一页',
								paginationNextText : '下一页',
								paginationLastText : '最后一页',
								columns : tableColumnData
							});
		};
		function operateFormatter(value, row, index) {
			return '<a class="oper_view" style="cursor:pointer;">查看</a>';
		}

		window.ftpEvents = {
				'click .ftp_edit' : function(e, value, row, index) {
					e.preventDefault();
					ftpLayer(row.FTP_IDS==null?"-1":row.FTP_IDS);
					layer.open({
						title : false,
						closeBtn: false,
						type : 1,
						area : [ '800px', '500px' ],
						shadeClose : true, //点击遮罩关闭
						content : $('#ftp_layer'),
						end : function(index) {
							layer.close(index);
						}
					});
				}
		};
		window.operateEvents = {
			'click .oper_view' : function(e, value, row, index) {
				e.preventDefault();
				$("#div_layer input[name='queryStrategyType']").val(row.STRATEGY_TYPE);
				$("#div_layer input[name='queryStrategyId']").val(row.STRATEGY_ID);
				layer.open({
					title : '最近24小时',
					closeBtn: false,
					type : 1,
					area : [ '1000px', '500px' ],
					shadeClose : true, //点击遮罩关闭
					content : $('#div_layer'),
					end : function(index) {
						layer.close(index);
					}
				});
				loadLayer(row.STRATEGY_TYPE, row.STRATEGY_ID);
				/*
				var postData = new Object();
				postData.queryStatus = 3;
				postData.queryMessageSequenceno = row.MESSAGE_SEQUENCENO;
				postData.queryStrategyId = row.STRATEGY_ID;
				$.ajax({
					type : "POST",
					url : "${preurl}/updateStatus.do",
					data : postData,
					dataType : "json",
					success : function(data) {
						if (data.flag == 0) {
							search();
						} else {
							$.modal.alert(data.msg);
						}
					}
				});*/
			}
		};
		window.strategyNameEvents = {
			'click .strategy_view' : function(e, value, row, index) {
				e.preventDefault();
				$("#editForm input[name='queryMessageSequenceno']").val(row.MESSAGE_SEQUENCENO);
				$("#popup03 input[name='queryStrategyType']").val(row.STRATEGY_TYPE_NAME);
				$("#popup03 input[name='queryStrategyName']").val(row.STRATEGY_NAME);
				$("#popup03 input[name='querySrcIp']").val(row.SRC_IP);
				$("#popup03 input[name='querySrcPort']").val(row.SRC_PORT);
				$("#popup03 input[name='queryDealType'][value='"+row.DEAL_TYPE+"']").click();
				
				$("#popup03 input[name='queryDomain']").val(row.DOMAIN);
				$("#popup03 input[name='queryUrl']").val(row.URL);
				$("#popup03 input[name='queryFlowType'][value='"+row.FLOW_TYPE+"']").click();
				$("#popup03 input[name='queryDestIp']").val(row.DST_IP);
				$("#popup03 input[name='queryDestPort']").val(row.DST_PORT);
				$("#popup03 input[name='queryProtocol']").val(row.PROTOCOL);
				$("#popup03 input[name='queryFTPServerIps']").val(row.FTP_IPS);
				tag_trafficRe.selectCatalog(function(){
					if(row.CATALOGID != null && row.CATALOGID > 0){
						$("#popup03 select[name='queryCatalogId']").val(row.CATALOGID);
						tag_trafficRe.selectClass(row.CATALOGID, function(){
							if(row.CLASSID!=null && row.CLASSID > 0){
								$("#popup03 select[name='queryClassId']").val(row.CLASSID);
								tag_trafficRe.selectProduct(row.CATALOGID,row.CLASSID, function(){
									if(row.PRODUCTID!=null && row.PRODUCTID > 0){
										$("#popup03 select[name='queryProductId']").val(row.PRODUCTID);
										tag_trafficRe.selectModule(row.CATALOGID,row.CLASSID,row.PRODUCTID, function(){
											$("#popup03 select[name='queryModuleId']").val(row.MODULEID);
											$.each($("#popup03 input"), function(idx, item){
												$(item).prop("readonly","readonly");
											});
											$.each($("#popup03 select"), function(idx, item){
												$(item).prop("disabled","disabled");
											});
										});
									}
								});
							}
						});
					}
				});
				popup($("#popup03"));
			}
		};
		function ftpLayer(queryFTPServerIds){
			$('#ftpTable').bootstrapTable("destroy").bootstrapTable(
					{
						method : 'post',
						url : "${ctx}/strategy/management/ftpServerManagement/initTable.do",
						queryParams : function(params){
		    				var data = new Object();
		    				data.offset = params.offset;
		    				data.limit = params.limit;
		    				data.sort = params.sort;
		    				data.order = params.order;
		    				data.queryFTPServerIds = queryFTPServerIds;
		    				return data;
		    			},
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
						columns : 
							[ {
								field : 'FTP_NAME',
								title : 'FTP服务器名称',
								align : 'center',
								valign : 'middle',
								sortable : true,
								fixed : true
							}, {
								field : 'IP',
								title : 'IP地址',
								align : 'center',
								valign : 'middle',
								sortable : true,
								fixed : true
							}, {
								field : 'PORT',
								title : '端口',
								align : 'center',
								valign : 'middle',
								sortable : true,
								fixed : true
							}, {
								field : 'USERNAME',
								title : '用户名',
								align : 'center',
								valign : 'middle',
								sortable : true,
								fixed : true
							}, {
								field : 'PASSWD',
								title : '密码',
								align : 'center',
								valign : 'middle',
								sortable : true,
								fixed : true
							}
							]
					});
			};
        function popup(popupName){
            var _scrollHeight = $(document).scrollTop(), 
                _windowHeight = $(window).height(), 
                _windowWidth = $(window).width(), 
                _popupHeight = popupName.height(),
                _popupWeight = popupName.width();
                _posiTop = (_windowHeight - _popupHeight)/2 + _scrollHeight; 
                _posiLeft = (_windowWidth - _popupWeight)/2; 
            popupName.css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block"});//设置position 
        };
	</script>
	<jsp:include page="/WEB-INF/view/strategy/management/datareuseLayer.jsp" flush="true"></jsp:include>
</body>
</html>