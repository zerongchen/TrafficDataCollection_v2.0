var statisticsObject = new Object();
(function(){
function initLayer(row){
	$("#strategyForm input:reset").click();
	if(row == null){
		$("#editQueryStrategy").html("");
		$("#editQueryStrategyName").val("");
		$("#editForm .ftpMultiSelect #editFTPServerId").val("");
		$("#editForm .ftpMultiSelect #editFTPServerId").multiselect('refresh');
		$("#radioId").val(-1);
		$("#editQuerySrcIp").val("");
		$("#editQuerySrcPort").val("");
		$("#editForm select[name='queryCatalogId']").val(-1);
		$("#editForm select[name='queryClassId']").val(-1);
		$("#editForm select[name='queryProductId']").val(-1);
		$("#editForm select[name='queryModuleId']").val(-1);
		$("#editForm input[name='queryDestIp']").val("");
		$("#editForm input[name='queryDestPort']").val("");
		$("#editProtocol").val(-1);
		$("#editQueryDomain").val("");
		$("#editQueryUrl").val("");
	}else{
		$("#editQueryStrategy").html(row.STRATEGY_ID);
		$("#editForm input[name='queryStrategyId']").val(row.STRATEGY_ID);
		$("#editQueryStrategyName").val(row.STRATEGY_NAME);
		if(row.FTP_IDS != null){
			console.log(row.FTP_IDS.split(","));
			$("#editForm .ftpMultiSelect #editFTPServerId").val(row.FTP_IDS.split(","));
			$("#editForm .ftpMultiSelect #editFTPServerId").multiselect('refresh');
		};
		$("#radioId").val(1);
		$("#editQuerySrcIp").val(row.SRC_IP);
		$("#editQuerySrcPort").val(row.SRC_PORT);
		tag_trafficRe.selectCatalog(function(){
			if(row.CATALOGID!=null && row.CATALOGID > 0){
				$("#editForm select[name='queryCatalogId']").val(row.CATALOGID);
				tag_trafficRe.selectClass(row.CATALOGID, function(){
					if(row.CLASSID!=null && row.CLASSID > 0){
						$("#editForm select[name='queryClassId']").val(row.CLASSID);
						tag_trafficRe.selectProduct(row.CATALOGID,row.CLASSID, function(){
							if(row.PRODUCTID!=null && row.PRODUCTID > 0){
								$("#editForm select[name='queryProductId']").val(row.PRODUCTID);
								tag_trafficRe.selectModule(row.CATALOGID,row.CLASSID,row.PRODUCTID, function(){
									$("#editForm select[name='queryModuleId']").val(row.MODULEID);
								});
							}
						});
					}
				});
			}
		});
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
		$("#editProtocol").val(row.PROTOCOLTYPE_ID);
		$("#editQueryDomain").val(row.DOMAIN);
		$("#editQueryUrl").val(row.URL);
	}
}

function insert() {
	if(!$("#editForm").valid()) return;
	$("#editForm input:reset").click();
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
		url : insertUrl,
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
				area : [ '600px', '500px' ],
				shadeClose : false, //点击遮罩关闭
				content : $('#div_layer'),
			});
			$(".layui-layer").css("z-index", "100");
			$(".layui-layer-shade").css("z-index", "99");
		}
	};
window.strategyNameEvents = {
		'click .strategy_edit' : function(e, value, row, index) {
			e.preventDefault();
			$("#strategyForm label[name='queryStrategyId']").html(row.STRATEGY_ID);
			$("#strategyForm input[name='queryStrategyType']").val('流量采集策略');
			$("#strategyForm input[name='queryStrategyName']").val(row.STRATEGY_NAME);
			$("#strategyForm input[name='queryFTPServerIds']").val(row.FTP_SERVER);
			$("#strategyForm input[name='querySrcIp']").val(row.SRC_IP);
			$("#strategyForm input[name='querySrcPort']").val(row.SRC_PORT);
			$("#strategyForm input[name='queryDestIp']").val(row.DST_IP);
			$("#strategyForm input[name='queryDestPort']").val(row.DST_PORT);
			tag_trafficRe1.selectCatalog(function(){
				if(row.CATALOGID!=null && row.CATALOGID > 0){
					$("#strategyForm select[name='queryCatalogId']").val(row.CATALOGID);
					tag_trafficRe1.selectClass(row.CATALOGID, function(){
						if(row.CLASSID!=null && row.CLASSID > 0){
							$("#strategyForm select[name='queryClassId']").val(row.CLASSID);
							tag_trafficRe1.selectProduct(row.CATALOGID,row.CLASSID, function(){
								if(row.PRODUCTID!=null && row.PRODUCTID > 0){
									$("#strategyForm select[name='queryProductId']").val(row.PRODUCTID);
									tag_trafficRe1.selectModule(row.CATALOGID,row.CLASSID,row.PRODUCTID, function(){
										$("#strategyForm select[name='queryModuleId']").val(row.MODULEID);
									});
								}
							});
						}
					});
				}
			});
			
			$("#strategyForm input[name='queryDestIp']").val(row.DST_IP);
			$("#strategyForm input[name='queryDestPort']").val(row.DST_PORT);
			$("#strategyForm input[name='queryProtocol']").val(row.PROTOCOLTYPE_NAME);
			$("#strategyForm input[name='queryDomain']").val(row.DOMAIN);
			$("#strategyForm input[name='queryUrl']").val(row.URL);
			layer.open({
				title : false,
				closeBtn: false,
				type : 1,
				area : [ '565px', '500px' ],
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
			ftpLayer(row.FTP_IDS == null ? "-1":row.FTP_IDS);
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
	tableColumnData.splice(21, 0, { 
		field : 'operation',
		title : '操作',
		width : 80,
		align : 'center',
		switchable : false,
		formatter : operateFormatter,
		events : operateEvents,
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
	tableColumnData.splice(19, 0, {
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
$("#export").on("click", function(e){
	var data = new Object();
	data.headers = new Array();
	data.fields = new Array();		
	data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
	getTableColumnData(getTableColumnData);
	$.each(tableColumnData, function(idx, item){
		if(item.visible == true){
			data.headers.push(item.title);
			data.fields.push(item.field);
		}
	});
	$.download({url:exportUrl, data:data});
});

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
        queryFTPServerIds: {
        	min: 0
        },
        querySrcIp: {
        	maxlength:20,
        	isIP:true
        },
        querySrcPort: {
        	min: 0
        },
        queryCatalogId: {
    		min: 0
    	},
    	queryClassId: {
    		min: 0
    	},
    	queryProductId: {
    		min: 0
    	},
    	queryModuleId: {
    		min: 0
    	},
    	queryProtocol: {
    		min: 0
    	},
    	queryDomain: {
        	maxlength:16,
        	minlength:1
        },
        queryUrl: {
        	maxlength:16,
        	minlength:1
        },
        queryDestIp: {
        	maxlength:20,
        	isIP:true
        },
        queryDestPort: {
        	min: 0
        }
    },
    messages: {
    	queryStrategyName: {
    		required: "请输入策略名称",
            maxlength:$.validator.format("不能超过{0}个字符")
    	},
    	queryFTPServerIds: {
    		min: $.validator.format("请选择FTP服务器名称")
        },
    	querySrcIp: {
    		maxlength:$.validator.format("不能超过{0}个字符")
    	},
    	querySrcPort: {
    		min: $.validator.format("请输入源端口")
    	},
    	queryCatalogId: {
    		min: $.validator.format("请选择业务类别")
    	},
    	queryClassId: {
    		min: $.validator.format("请选择业务")
    	},
    	queryProductId: {
    		min: $.validator.format("请选择产品")
    	},
    	queryModuleId: {
    		min: $.validator.format("请选择功能模块")
    	},
    	queryProtocol: {
    		min: $.validator.format("请选择协议类型")
    	},
    	queryDomain: {
    		maxlength:$.validator.format("不能超过{0}个字符"),
            minlength:$.validator.format("不能小于{0}个字符")
    	},
    	queryUrl: {
    		maxlength:$.validator.format("不能超过{0}个字符"),
    		minlength:$.validator.format("不能小于{0}个字符")
    	},
    	queryDestIp: {
    		maxlength:$.validator.format("不能超过{0}个字符")
        },
        queryDestPort: {
        	min: $.validator.format("请输入源端口")
        }
    },
    errorPlacement: function(error, element) {
        error.appendTo( element.parent() );
    }
});

})();
//chart end