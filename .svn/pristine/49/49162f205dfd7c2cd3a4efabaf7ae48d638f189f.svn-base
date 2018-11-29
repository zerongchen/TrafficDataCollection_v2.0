var tdcTable = {};
(function(global){
	tdcTable.blockSelOption = {
		filedNames : [],
		filedIds : []
	};
	tdcTable.createBlockSel = function(targetId, blockSelOption){
		if(blockSelOption != undefined) tdcTable.blockSelOption = blockSelOption;
		var blockSelHtml = '<button>显示字段</button><div class="field"><form action="">';
		$.each(tdcTable.blockSelOption.filedNames, function(idx, item){
			blockSelHtml = blockSelHtml + '<p><input value="'+tdcTable.blockSelOption.filedIds[idx]+'" name="'+item+'" type="checkbox">'+item+'</p>';
		});
		blockSelHtml = blockSelHtml + '<button type="submit">提交</button></form></div>';
	    return $("#"+targetId).append(blockSelHtml).find("button").on("click", function() {
	        $(this).siblings().toggle();
	    });
	}
	tdcTable.tableOption = {
		dataList : {}
		
	}
	tdcTable.createTable = function(targetId, tableOption){
	}
})(this);
/**
 * 删除
 * @param url 删除地址
 * @param data 表单数据
 * @param $table 列表对象
 * @param initTableURL 加载列表地址
 */
function del(url, data, $table, initTableURL){
	if(confirm('确定删除吗？')){
		$.ajax({
			type: 'POST',
			cache: false,
			url: url,
			data: data,
			success: function(){
				$table.bootstrapTable('refresh', {silent: true, url: initTableURL}); //刷新列表
			},
			error: function(){
				alert('删除失败！');
			}
		});
	}
}

/**
 * 编辑
 * @param $modal 编辑模态对象
 * @param $table 列表对象
 * @param editUrl 编辑地址
 * @param data 表单数据
 * @param initTableUrl 加载列表地址
 */
function edit($modal, $table, editUrl, data, initTableUrl){
	$.ajax({
		type: 'POST',
		url: editUrl,
		data: data,
		success: function(){
			$modal.modal('hide');
			$table.bootstrapTable('refresh', {silent: true, url: initTableUrl}); //刷新列表
		}
	});
}

/**
 * 列的隐藏或显示
 * @param $table 列表对象
 * @param url 请求地址
 */
function showOrHideColumn($table, url){
	$table.on('column-switch.bs.table', function(e, field, checked){
		var showType = checked ? 1 : 2;
		$.ajax({
	        type: 'POST',
	        url: url,
            data: {"showType": showType, "fieldName": field},
            success:function(result){
             },
            error:function(errMsg){
                console.error("操作失败！");
            }
	    });
	});
}