var statisticsObject = new Object();
(function(){
function search(){
	getTableColumnData(getTableStaColumnDataUrl);
	tableColumnData.splice(0,0,{
		field:'index',
		title:"序号",
		width:80,
		align:'center',
		switchble:false,
		formatter: function(value, row, index){return index+1;}
	});
	$('#appTable').bootstrapTable("destroy").bootstrapTable({
		method : 'post',
		url : initTableUrl,
		queryParams : pageQueryParams,
		contentType : "application/x-www-form-urlencoded",
		cache : false,
		//height : 400,
		striped : true,
		pagination : false,
		//pageSize : 10,
		//pageList : [ 20, 50 ],
		showColumns : false,
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
}

function pageQueryParams(params){
	var data = $('#searchForm').formToJSON();
	data.offset = params.offset;
	data.order = params.order;
	data.limit = $('#hid_limit').val();
	if(params.sort == null){
		data.sort = "RESPONSE_DELAY";
		data.order = "DESC";
	}else{
		data.sort = params.sort;
	}
	return data;
};


function refreshTable(){
	$('#appTable').bootstrapTable("refresh", {silent: true});
}
statisticsObject.search = search;
statisticsObject.refreshTable = refreshTable;

$("#exportbtn").on("click", function(e){
	var data = new Object();
	data.headers = new Array();
	data.fields = new Array();		
	data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
	getTableColumnData(getTableStaColumnDataUrl);
	$.each(tableColumnData, function(idx, item){
		if(item.visible == true){
			data.headers.push(item.title);
			data.fields.push(item.field);
		}
	});
	$.download({url:exportTableUrl, data:data});
});
})();
//chart end