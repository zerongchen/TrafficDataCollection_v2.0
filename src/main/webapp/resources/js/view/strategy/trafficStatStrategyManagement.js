var statisticsObject = new Object();
(function(){
function initLayer(row){
	$("#strategyForm input:reset").click();
	if(row == null){
		$("#editQueryStrategy").html("");
		$("#editForm input[name='queryStrategyId']").val("");
		$("#editQueryStrategyName").val("");
		$("#editQueryFTPServerId").val(-1);
		$("#editQueryReportType").val(-1);
		$("#radioId").val(-1);
	}else{
		$("#editQueryStrategy").html(row.STRATEGY_ID);
		$("#editForm input[name='queryStrategyId']").val(row.STRATEGY_ID);
		$("#editQueryStrategyName").val(row.STRATEGY_NAME);
		$("#editQueryFTPServerId").val(row.FTP_ID);
		$("#editQueryReportType option:contains('"+row.REPORT_TYPE+"')").attr("selected", true);
		$("#radioId").val(1);
	}
}

function insert() {
	if(!$("#editForm").valid()) return;
	$("#editForm input:reset").click();
	var postData = $("#editForm").formToJSON();
	$.ajax({
		type : "POST",
		url : insertUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			if (data.flag == 0) {
				search();
				$("#div_layer_add input:reset").click();
				layer.closeAll();
			} else {
				$("#div_layer_add input:reset").click();
				layer.closeAll();
				$.modal.alert(data.msg);
			}
		}
	});
}

function update() {
	if(!$("#editForm").valid()) return;
	var postData = $("#editForm").formToJSON();
	postData.queryDestIp = "";
	postData.queryDestPort = "";
	$.each($("#editForm input[name='queryDestIp']"), function(idx, item){
		postData.queryDestIp = postData.queryDestIp + (postData.queryDestIp.length==0?"":";") + ($(item).val()==null?"":$(item).val());
	});
	$.each($("#editForm input[name='queryDestPort']"), function(idx, item){
		postData.queryDestPort = postData.queryDestPort + (postData.queryDestPort.length==0?"":";") + ($(item).val()==null?"":$(item).val());
	});
	$.ajax({
		type : "POST",
		url : updateUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			if (data.flag == 0) {
				search();
				$("#div_layer input:reset").click();
				layer.closeAll();
			} else {
				$("#div_layer input:reset").click();
				layer.closeAll();
				$.modal.alert(data.msg);
			}
		}
	});
}

function deleteData() {
	var postData = $("#editForm").formToJSON();
	$.ajax({
		type : "POST",
		url : deleteUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			layer.msg(data.msg);
		}
	});
}

window.operateEvents = {
		'click .oper_stop' : function(e, value, row, index) {
			e.preventDefault();
			var postData = new Object;
			postData.queryStatus = 3;
			postData.queryStrategyId = row.STRATEGY_ID;
			$.ajax({
				type : "POST",
				url : updateStatusUrl,
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
			var postData = new Object;
			postData.queryStatus = 1;
			postData.queryStrategyId = row.STRATEGY_ID;
			$.ajax({
				type : "POST",
				url : updateStatusUrl,
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
			initLayer(row);
			layer.open({
				title : false,
				type : 1,
				closeBtn : false,
				area : ['550px', '350px'],
				shadeClose : false, //点击遮罩关闭
				content : $('#div_layer_add'),
			});
			$(".layui-layer").css("z-index", "100");
			$(".layui-layer-shade").css("z-index", "99");
		}
	};
window.strategyNameEvents = {
		'click .strategy_edit' : function(e, value, row, index) {
			e.preventDefault();
			$("#strategyForm label[name='queryStrategyId']").html(row.STRATEGY_ID);
			$("#strategyForm input[name='queryStrategyName']").val(row.STRATEGY_NAME);
			$("#strategyForm input[name='queryFTPServerId']").val(row.FTP_NAME);
			$("#strategyForm input[name='queryReportType']").val(row.REPORT_TYPE);
			layer.open({
				title : false,
				closeBtn: false,
				type : 1,
				area : [ '526px', '220px' ],
				shadeClose : false, //点击遮罩关闭
				content : $('#strategy_layer'),
				end : function(index) {
					$("#strategy_layer input:reset").click();
					layer.close(index);
				}
			});
		}
	};
window.ftpEvents = {
		'click .ftp_edit' : function(e, value, row, index) {
			e.preventDefault();
			ftpLayer(row.FTP_ID == null ? "-1":row.FTP_ID);
			layer.open({
				title : false,
				closeBtn: false,
				type : 1,
				area : [ '550px', '500px' ],
				shadeClose : false, //点击遮罩关闭
				content : $('#ftp_layer'),
				end : function(index) {
					$("#ftp_layer input:reset").click();
					layer.close(index);
				}
			});
		}
}
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
	tableColumnData.splice(5, 1, {
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
	tableColumnData.splice(7, 1, {
		field : 'FTP_NAME',
		title : 'FTP服务器',
		width : 100,
		align : 'center',
		switchable : false,
		formatter : 
		  function(value,row,index){
			return '<a class="ftp_edit" style="cursor:pointer;">'+row.FTP_NAME+'</a>';
		  },
		  events : ftpEvents,
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
	
};
function tableRefresh(){
	$('#appTable').bootstrapTable("refresh", {silent: true});
}
function ftpLayer(queryFTPServerIds){
$('#ftpTable').bootstrapTable("destroy").bootstrapTable(
		{
			method : 'post',
			url : getFTPUrl,
			queryParams : function(params){
				var postData = new Object();
				postData.offset = params.offset;
				postData.limit = params.limit;
				postData.sort = params.sort;
				postData.order = params.order;
				postData.queryFTPServerIds = queryFTPServerIds;
				return postData;
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

statisticsObject.initLayer = initLayer;
statisticsObject.search = search;
statisticsObject.tableRefresh = tableRefresh;
statisticsObject.insert = insert;
statisticsObject.update = update;
statisticsObject.deleteData = deleteData;

statisticsObject.validator = $("#editForm").validate({
	rules : {
    	queryStrategyName: {
            required: true,
            maxlength:16
        },
        queryFTPServerId: {
        	min: 0
        },
        queryReportType: {
        	min: 0
        }
    },
    messages: {
    	queryStrategyName: {
    		required: "请输入策略名称",
            maxlength:$.validator.format("不能超过{0}个字符")
    	},
    	queryFTPServerId: {
    		min: $.validator.format("请选择FTP服务器")
        },
        queryReportType: {
        	min: $.validator.format("请选择报表类型")
    	}
    },
    errorPlacement: function(error, element) {
        error.appendTo( element.parent() );
    }
});

})();
//chart end