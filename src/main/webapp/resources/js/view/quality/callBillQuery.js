var billObject = new Object();
(function(){
	$("#export").on("click", function(e){
		var tableColumnData = $('#appTable_callbill').bootstrapTable("getOptions").columns[0];
		var data = new Object();
		data.headers = new Array();
		data.fields = new Array();		
		data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
		$.each(tableColumnData, function(idx, item){
			if(item.visible == true){
				data.headers.push(item.title);
				data.fields.push(item.field);
			}
		});
		$.download({url:exportUrl, data:data});
	});
	
	function search() {
		var tableColumnData = getTableColumnData(getTableColumnDataUrl);
		console.log(tableColumnData);
		var bootstrapTableOption = {
			method : 'post',
			url : initTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			showColumns : true,
			sidePagination : 'server',
			showRefresh : false,
			minimumCountColumns : 1,
			clickToSelect : true,
			paginationFirstText : "首页",
			paginationPreText : '上一页',
			paginationNextText : '下一页',
			paginationLastText : '最后一页',
			columns : tableColumnData
		};
		$('#appTable_callbill').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked, updateColumnUserUrl);
		});
	}

	billObject.search = search;
})();
