<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="preurl" value="${ctx}/strategy/management/trafficRecoveryManagement" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>流量还原管理</title>
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
					<i></i><span class="blue">策略配置</span> / <span class="blue">策略管理</span> / <span>流量还原</span>
					<%--<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize>
					 <sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_EXPORT">
					<button class="fr" id="export">导出</button>
					</sec:authorize> --%>
				</div>
				<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_QUERY">
				<form id="searchForm">
					<div class="time_search" style="display: block;height:130px">
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div class="fl option">
							
							<tdc:strategyReNameSelect domId="queryStrategyId"  domName="queryStrategyId" ></tdc:strategyReNameSelect>
							<div>
								<label for="">运行状态：</label>
								<select id="queryStatus" name="queryStatus">
								<option selected="selected" value="-1">请选择</option>
								<option value="0">未启用</option>
								<option value="1">正常运行</option>
								<option value="2">传输终止</option>
								<option value="3">已停用</option>
								</select>
							</div>
							<tdc:selector switches="8"></tdc:selector>
							<div>
								<label for="">源IP地址：</label> <input id="querySrcIp" name="querySrcIp" type="text" />
							</div>
							<div>
								<label for="">源端口：</label> <input id="querySrcPort" name="querySrcPort" type="text" />
							</div>
							<div>
								<label for="">目的IP地址：</label> <input id="queryDestIp" name="queryDestIp" type="text" />
							</div>
							<div>
								<label for="">目的端口：</label> <input id="queryDestPort" name="queryDestPort" type="text" />
							</div>
							<div>
								<label for="">域名：</label> <input id="queryDomain" name="queryDomain" type="text" />
							</div>
							<div>
								<label for="">URL：</label> <input id="queryUrl" name="queryUrl" type="text" />
							</div>
							<div>
								<label for="">流量：</label> 
								<select id="queryFlowType" name="queryFlowType"><option value="-1">请选择</option><option value="0">上下行流量</option><option value="1">上行流量</option><option value="2">下行流量</option></select>
							</div>
							<tdc:ftpServerSelect domId="ftpServerId" domName="queryFTPServerId"></tdc:ftpServerSelect>
						
						<div>
								<label for="">策略创建时间：</label> <input id="q_sdate" type="text" name="queryStartTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\')}'});" />
								<input id="q_edate" type="text" name="queryEndTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\')}'});" />
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
									<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_ADD">
									<button id="add">新增</button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_DEL">
									<button id="del">删除</button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_EXPORT">
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
	        
			$("#del").on("click", function(e) {
				var resultData = $('#appTable_01').bootstrapTable("getAllSelections");
				if(resultData.length <= 0){
					$.modal.alert("请选择要删除的数据！");
					return;
				}
				var postData = new Object();
				postData.queryStrategyIds = "";
				var queryStrategyNames = "";
				$.each(resultData, function(idx, item) {
					postData.queryStrategyIds = postData.queryStrategyIds
							+ (postData.queryStrategyIds.length > 0 ? ","
									: "")
							+ item.STRATEGY_ID;
					queryStrategyNames = queryStrategyNames
							+ (queryStrategyNames.length > 0 ? ","
									: "")
							+ item.STRATEGY_NAME;
				});
				$.modal.confirm("您确定要删除策略【策略ID "+postData.queryStrategyIds+" "+queryStrategyNames+"】及其策略配置信息？", function(){
					$.ajax({
						type : "POST",
						url : "${preurl}/delete.do",
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
		});
		function search() {
			var tableColumnData = getTableColumnData('${ctx}/common/POLICY_FLOW_RECOVERY_STRATEGY/getTableColumnData.do');
			tableColumnData.splice(0, 0, {
				field : 'checkbox',
				title : '选择框',
				width : 80,
				align : 'center',
				switchable : false,
				checkbox : true
			});
			tableColumnData.splice(30, 0, {
				field : 'operation',
				title : '操作',
				width : 80,
				align : 'center',
				switchable : false,
				formatter : operateFormatter,
				events : operateEvents,
			});
			tableColumnData.splice(3, 1, {
				field : 'STRATEGY_NAME',
				title : '策略名称',
				width : 100,
				align : 'center',
				switchable : false,
				formatter : 
					function(value,row,index){
			            return '<a class="strategy_edit" style="cursor:pointer;">'+row.STRATEGY_NAME+'</a>';
		             },
				events : strategyNameEvents,
			});
			tableColumnData.splice(16, 1, {
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
			if (row.STATUS == '未启用') {
				return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
			} else if (row.STATUS == '正常运行' || row.STATUS == '传输终止') {
				return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_STOP"><a class="oper_stop" style="cursor:pointer;">停用</a></sec:authorize>';
			} else if (row.STATUS == '已停用') {
				return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_TRAFFIC_RESTORE_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
			}
			return;
		}
		window.operateEvents = {
			'click .oper_stop' : function(e, value, row, index) {
				e.preventDefault();
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
				});
			},
			'click .oper_start' : function(e, value, row, index) {
				e.preventDefault();
				var postData = new Object();
				postData.queryStatus = 1;
				postData.queryMessageSequenceno = row.MESSAGE_SEQUENCENO;
				postData.queryStrategyId = row.STRATEGY_ID;
				$.ajax({
					type : "POST",
					url :  "${preurl}/updateStatus.do",
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
				$("#editForm input[name='queryStrategyId']").val(row.STRATEGY_ID);
				$("#editForm input[name='queryMessageSequenceno']").val(row.MESSAGE_SEQUENCENO);
				$("#editForm #queryStrategyId").html(row.STRATEGY_ID);
				$("#editForm input[name='queryStrategyName']").val(row.STRATEGY_NAME);
				$("#editForm input[name='querySrcIp']").val(row.SRC_IP);
				$("#editForm input[name='querySrcPort']").val(row.SRC_PORT);
				$("#editForm input[name='queryDealType'][value='"+row.DEAL_TYPE+"']").click();
				$("#editForm input[name='queryStrategyName']").val(row.STRATEGY_NAME);
				tag_trafficRe1.selectCatalog(function(){
					if(row.CATALOGID!=null && row.CATALOGID > 0){
						$("#editForm select[name='queryCatalogId']").val(row.CATALOGID);
						tag_trafficRe1.selectClass(row.CATALOGID, function(){
							if(row.CLASSID!=null && row.CLASSID > 0){
								$("#editForm select[name='queryClassId']").val(row.CLASSID);
								tag_trafficRe1.selectProduct(row.CATALOGID,row.CLASSID, function(){
									if(row.PRODUCTID!=null && row.PRODUCTID > 0){
										$("#editForm select[name='queryProductId']").val(row.PRODUCTID);
										tag_trafficRe1.selectModule(row.CATALOGID,row.CLASSID,row.PRODUCTID, function(){
											$("#editForm select[name='queryModuleId']").val(row.MODULEID);
										});
									}
								});
							}
						});
					}
				});
				
				$("#editForm input[name='queryDomain']").val(row.DOMAIN);
				$("#editForm input[name='queryUrl']").val(row.URL);
				$("#editForm input[name='queryFlowType'][value='"+row.FLOW_TYPE+"']").click();
				if(row.DST_IP != null){
					$.each(row.DST_IP.split(';'), function(idx, item){
						if(idx == 0){
							$("#editForm input[name='queryDestIp']:eq(0)").val(item);
						}else{
							var addhtml = "<div><label>目的IP：</label> <input type=\"text\" name=\"queryDestIp\" placeholder=\"例如：192.168.13.34\">";
							addhtml += "<label>目的端口：</label> <input type=\"text\" name=\"queryDestPort\" placeholder=\"例如：192.168.13.34\">";
							addhtml += "<button type=\"button\" class=\"smallbtn\" name='smalldelbtn'>-</button></div>";
							var $addobj = $(addhtml);
							$addobj.find("button[name='smalldelbtn']").on("click", function(e){
								$(this).parent().remove();
							});
							$addobj.find("input[name='queryDestIp']").val(item);
							$("#editForm #page02").append($addobj);
						}
					});
				}
				if(row.DST_PORT != null){
					$.each(row.DST_PORT.split(';'), function(idx, item){
						$("#editForm input[name='queryDestPort']:eq("+idx+")").val(item);
					});
				}
				if(row.FTP_IDS != null){
					console.log(row.FTP_IDS.split(","));
					$("#editForm .ftpMultiSelect #ftpServerEdit").val(row.FTP_IDS.split(","));
					$("#editForm .ftpMultiSelect #ftpServerEdit").multiselect('refresh');
				}
				popup($("#popup02"));
			}
		};
		window.ftpEvents = {
				'click .ftp_edit' : function(e, value, row, index) {
					e.preventDefault();
					ftpLayer(row.FTP_IDS==null?"-1":row.FTP_IDS);
					layer.open({
						title : false,
						closeBtn: false,
						type : 1,
						area : [ '500px', '500px' ],
						shadeClose : true, //点击遮罩关闭
						content : $('#ftp_layer'),
						end : function(index) {
							$("#ftp_layer input:reset").click();
							layer.close(index);
						}
					});
				}
		};
		window.strategyNameEvents = {
				'click .strategy_edit' : function(e, value, row, index) {
					e.preventDefault();
					$("#editForm input[name='queryMessageSequenceno']").val(row.MESSAGE_SEQUENCENO);
					$("#popup03 #queryStrategyId").html(row.STRATEGY_ID);
					$("#popup03 input[name='queryStrategyName']").val(row.STRATEGY_NAME);
					$("#popup03 input[name='querySrcIp']").val(row.SRC_IP);
					$("#popup03 input[name='querySrcPort']").val(row.SRC_PORT);
					$("#popup03 input[name='queryDealType'][value='"+row.DEAL_TYPE+"']").click();
					
					$("#popup03 input[name='queryDomain']").val(row.DOMAIN);
					$("#popup03 input[name='queryUrl']").val(row.URL);
					$("#popup03 input[name='queryFlowType'][value='"+row.FLOW_TYPE+"']").click();
					$("#popup03 input[name='queryDestIp']").val(row.DST_IP);
					$("#popup03 input[name='queryDestPort']").val(row.DST_PORT);
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
	<jsp:include page="/WEB-INF/view/strategy/trafficRecoveryManagement/trafficRecoveryLayer.jsp" flush="true"></jsp:include>
</body>
</html>