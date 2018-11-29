var statisticsObject = new Object();
(function(){
$("#exprot").on("click", function(e){
	var resultData = $('#appTable_statistical').bootstrapTable("getAllSelections");
	var queryIds = "";
	$.each(resultData, function(idx, item) {
		queryIds = queryIds + (queryIds.length > 0 ? ",": "") + item.ID;
	});
	var data = new Object();
	data.headers = new Array();
	data.fields = new Array();		
	data = $.extend(true,queryParamslocal({offset:0, limit:0, sort:"", order:"",queryIds:queryIds}), data);
	console.log(data);
	getTableColumnData(getTableStaColumnDataUrl);
	$.each(tableColumnData, function(idx, item){
		if(item.visible == true){
			data.headers.push(item.title);
			data.fields.push(item.field);
		}
	});
	$.download({url:exportUrl, data:data});
});

function queryParamslocal(params) {
	var data = $('#searchForm').formToJSON();
	data.offset = params.offset;
	data.limit = params.limit;
	data.sort = params.sort;
	data.order = params.order;
	data.queryIds = params.queryIds;
	return data;
}

window.operateEvents = {
	'click .oper_edit' : function(e, value, row, index) {
		e.preventDefault();
		initLayer(row);
		var layerIndex = layer.open({
    		title: false,
            type: 1,
            closeBtn:false,
            skin: 'layui-layer-nobg',
            area: ['534px', '440px'],
            shadeClose: false, //点击遮罩关闭
            content: $('#popup01')
        });
		$("#hid_layerIndex").val(layerIndex);
	}
};

function initLayer(row){
	if(row == null){
		$("#catalogLayer").val(-1);
		$("#classLayer").empty().append("<option value=\"-1\">请选择</option>");
		$("#productLayer").empty().append("<option value=\"-1\">请选择</option>");
		$("#moduleLayer").empty().append("<option value=\"-1\">请选择</option>");
		$("#serverBuild").val(-1);
		$("#serverRoom").empty().append("<option value=\"-1\">请选择</option>");
		$("#operServiceInfoId").val("0");
		//$("#operDestIp").val("");
		//$("#operDestPort").val("");
		
		$("#operStartIp").val("");
		$("#operEndIp").val("");
		$("#operStartPort").val("");
		$("#operEndPort").val("");
		
		$("#operIpMappingId").val("0");
		$("#operIpPortId").val("0");
		
	}else{
		$("#catalogLayer").val(row.CATALOGID);
		selectLayerClass(row.CATALOGID,false);
		$("#classLayer").val(row.CLASSID);
		selectLayerProduct(row.CATALOGID,row.CLASSID,false);
		$("#productLayer").val(row.PRODUCTID);
		selectLayerModule(row.CATALOGID,row.CLASSID,row.PRODUCTID,false);
		$("#moduleLayer").val(row.MODULEID);
		$("#serverBuild").val(row.SERVERBUILD_ID);
		selectServerRoom(row.SERVERBUILD_ID,false);
		$("#serverRoom").val(row.SERVERROOM_ID);
		$("#operServiceInfoId").val(row.ID);
		//$("#operDestIp").val(row.DEST_IP);
		//$("#operDestPort").val(row.DESC_PORT);
		
		
		$("#operStartIp").val(row.START_IP);
		$("#operEndIp").val(row.END_IP);
		$("#operStartPort").val(row.START_PORT);
		$("#operEndPort").val(row.END_PORT);
		
		$("#operIpMappingId").val(row.IPMAPPING_ID);
		$("#operIpPortId").val(row.IPPORT_ID);
		
	}
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

function insert() {
	if(!$("#operForm").valid()) return;
	var postData = $("#operForm").formToJSON();
	$.ajax({
		type : "POST",
		url : insertUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			layer.msg(data.msg);
			//if(data.flag >=0)
			//btnSearch();
		}
	});
}

function update() {
	if(!$("#operForm").valid()) return;
	var postData = $("#operForm").formToJSON();
	$.ajax({
		type : "POST",
		url : updateUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			layer.msg(data.msg);
			//if(data.flag >=0)
			//btnSearch();
		}
	});
}

function deleteData() {
	var postData = $("#operForm").formToJSON();
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


statisticsObject.validator = $("#operForm").validate({
    rules : {
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
    	queryServerBuildId: {
    		min: 0
    	},
    	queryServerRoomId: {
    		min: 0
    	},
    	queryStartIp:{
    		required: true,
        	isIP:true
    	},
    	queryEndIp:{
    		required: true,
        	isIP:true,
        	ipComparison:true
    	},
    	queryStartPort: {
    		//required: true,
    		onlyDigit:true,
    		min: 0,
    		max:65535
    		//portComparison:true
    	},
    	queryEndPort: {
    		//required: true,
    		onlyDigit:true,
    		min: 0,
    		max:65535,
    		portComparison:true
    	}
        
    },
    messages: {
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
    	queryServerBuildId: {
    		min: $.validator.format("请选择所属机楼")
    	},
    	queryServerRoomId: {
    		min: $.validator.format("请选择所属机房")
    	},
    	queryStartIp: {
        	required: $.validator.format("请填写开始IP地址")
        },
        queryEndIp: {
        	required: $.validator.format("请填写结束IP地址")
        },
        queryStartPort: {
        	//required: $.validator.format("请填写开始端口"),
        	//digits: $.validator.format("只能输入数字"),
    		min: $.validator.format("开始端口最小为0"),
    		max: $.validator.format("开始端口最大为65535")
    	},
    	queryEndPort: {
    		//required: $.validator.format("请填写结束端口"),
    		//digits: $.validator.format("只能输入数字"),
    		min: $.validator.format("结束端口最小为0"),
    		max: $.validator.format("结束端口最大为65535")
    	}
    },
    errorPlacement: function(error, element) {
        error.appendTo( element.parent() );
    }
});


statisticsObject.initLayer = initLayer;
statisticsObject.search = search;
statisticsObject.insert = insert;
statisticsObject.update = update;
statisticsObject.deleteData = deleteData;
// table相关函数end



})();
//chart end