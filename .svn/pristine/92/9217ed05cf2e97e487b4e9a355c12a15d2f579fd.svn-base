function sort1(data, limit, order){
	for (var i = 0 ; i< data.length; i++){
		for(var j = data.length-1; j >i; j--){
			if(parseFloat(data[i].value) < parseFloat(data[j].value) && order == "desc"){
				var temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}else if(parseFloat(data[i].value) > parseFloat(data[j].value) && order == "asc"){
				var temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
	}
	data.slice(0, limit);
	return data;
}

function sort2(xAxisData, seriesData, limit, order){
	var result = new Object();
	for (var i = 0 ; i< seriesData.length; i++){
		for(var j = seriesData.length-1; j >i; j--){
			if(parseFloat(seriesData[i]) < parseFloat(seriesData[j]) && order == "desc"){
				var temp1 = seriesData[i];
				seriesData[i] = seriesData[j];
				seriesData[j] = temp1;
				
				var temp2 = xAxisData[i];
				xAxisData[i] = xAxisData[j];
				xAxisData[j] = temp2;
			}else if(parseFloat(seriesData[i])> parseFloat(seriesData[j]) && order == "asc"){
				var temp1 = seriesData[i];
				seriesData[i] = seriesData[j];
				seriesData[j] = temp1;
				
				var temp2 = xAxisData[i];
				xAxisData[i] = xAxisData[j];
				xAxisData[j] = temp2;
			}
		}
	}
	result['xAxisData'] = xAxisData.slice(0, limit);
	result['seriesData'] = seriesData.slice(0, limit);
	return result;
}

function sort3(xAxisData, seriesData, limit, order){
	var result = new Object();
	for (var i = 0 ; i< seriesData.length; i++){
		for(var j = seriesData.length-1; j >i; j--){
			if(parseFloat(seriesData[i]) < parseFloat(seriesData[j]) && order == "desc"){
				var temp1 = seriesData[i];
				seriesData[i] = seriesData[j];
				seriesData[j] = temp1;
				
				var temp2 = xAxisData[i];
				xAxisData[i] = xAxisData[j];
				xAxisData[j] = temp2;
			}else if(parseFloat(seriesData[i]) > parseFloat(seriesData[j]) && order == "asc"){
				var temp1 = seriesData[i];
				seriesData[i] = seriesData[j];
				seriesData[j] = temp1;
				
				var temp2 = xAxisData[i];
				xAxisData[i] = xAxisData[j];
				xAxisData[j] = temp2;
			}
		}
	}
	result['yAxisData'] = xAxisData.slice(0, limit);
	result['seriesData'] = seriesData.slice(0, limit);
	return result;
}

// table相关函数queryParams/tableColumnData/updateColumnUser/operateFormatter/events start 
//查询参数
function queryParams(params) {
	var data = $('#searchForm').formToJSON();
	data.offset = params.offset;
	data.limit = params.limit;
	data.sort = params.sort;
	data.order = params.order;
	return data;
}
var tableColumnData;
function getTableColumnData(url) {
	$.ajax({
		type : 'post',
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			tableColumnData = result;
		},
		error : function(errMsg) {
			console.error("加载数据失败");
		}
	});
	return tableColumnData;
}

//更新用户列数据
function updateColumnUser(e, field, checked, url) {
	var postData = {};
	postData.field = field;
	if (checked) {
		postData.tag = 1;
	} else if (!checked) {
		postData.tag = 2;
	}
	$.ajax({
		type : 'post',
		url : url,
		dataType : 'json',
		data : postData,
		success : function(result) {
			if (result) {
				console.error("隐藏列成功");
			}
		},
		error : function(errMsg) {
			console.error("隐藏列失败");
		}
	});
}
//table相关函数end
$(function() {
    $('.block_sel button').click(function() {
        $(this).siblings().toggle();
    })
    $('.view').hide();
    /*
    $('body').on('click', '.show_view', function() {
        var parent_tr = $(this).parents('tr');
        if ($(this).hasClass('addtxt')) {
            $(this).text('查看').removeClass('addtxt');
            parent_tr.next().remove();
        } else {
            $(this).text('收起').addClass('addtxt');
            parent_tr.after('<tr></tr>');
            parent_tr.next().append($('.view').html());
        }
        //fillchart_dynamic();
    })*/
    $('body').on('click', '.list', function() {
        $(this).parents('form').next().show();
        $(this).parents('.tabs').nextAll('div').hide();
        $(this).parents('.tabs').nextAll('table').show();
        if($(".bootstrap-table").length > 0){
        	$(".bootstrap-table").show();
        }
    })
    $('body').on('click', '.chart', function() {
        $(this).parents('form').next().hide();
        $(this).parents('.tabs').nextAll('div').show();
        $(this).parents('.tabs').nextAll('table').hide();
    })
    $('.drop_down').click(function() {
    	if($("#resetSearchForm").length > 0){
    		$("#resetSearchForm").click();
    	}
    	if($(".fl a:eq(0)").length > 0){
    		$(".fl a:eq(0)").prop("class", "active");
    	}
        $('.time_search').toggle(function() {});
    })
    $('.computer_room .all').click(function() {
        $('.computer_room .sub').toggle();
        $(this).find('.down').toggleClass('right');
    })
    $('.sub .header-toggle').click(function() {
        $(this).siblings('li').toggle();
        $(this).find('.down').toggleClass('right');
    })
    function pageselectCallback(page_index, jq) {
        var new_content = $("#hiddenresult div.result:eq(" + page_index + ")").clone();
        $("#Searchresult").empty().append(new_content); //装载对应分页的内容
        return false;
    }
    $('.hide').hide();

})

function getDate(addMinutes,addHours,addDays){
	var now = new Date();
	if(addMinutes != 0){
		now.setMinutes (now.getMinutes () + addMinutes);
	}
	if(addHours != 0){
		now.setHours (now.getHours () + addHours);
	}
	if(addDays != 0){
		now.setDate (now.getDate() + addDays);
	}
	var year = now.getFullYear();       //年
    var month = now.getMonth()+1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();       //分
    var ss = now.getSeconds();       //秒
    var remainder = mm%5;
    if(remainder > 0){
    	mm = mm - remainder;
    }
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day;
    clock += " ";
    if(hh < 10)
        clock += "0";
    clock += hh + ":";
    if(mm < 10)
        clock += "0";
    clock += mm + ":";
    //if(ss < 10)
    //    clock += "0";
    clock += "00";
    return clock;  
}


function getDateInIndex(addMinutes,addHours,addDays,addMonths){
	var now = new Date();
	if(addMinutes != 0){
		now.setMinutes (now.getMinutes () + addMinutes);
	}
	if(addHours != 0){
		now.setHours (now.getHours () + addHours);
	}
	if(addDays != 0){
		now.setDate (now.getDate() + addDays);
		now.setMinutes(0);
		now.setHours(0);
	}
	if(addMonths != 0){
		now.setMonth (now.getMonth() + addMonths);
		now.setDate (1);
		now.setMinutes(0);
		now.setHours(0);
	}
	var year = now.getFullYear();       //年
    var month = now.getMonth()+1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();       //分
    var ss = now.getSeconds();       //秒
    var remainder = mm%5;
    if(remainder > 0){
    	mm = mm - remainder;
    }
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day;
    clock += " ";
    
    if(hh < 10)
        clock += "0";
    clock += hh + ":";
    if(mm < 10)
        clock += "0";
    clock += mm + ":";
    //if(ss < 10)
    //    clock += "0";
    clock += "00";
    
    
    return clock; 
}

function formatDate(timeStr,type){
	timeStr = timeStr.replace(/-/g,"/");
	var date = new Date(timeStr);
	var year = date.getFullYear();       //年
    var month = date.getMonth()+1;     //月
    var day = date.getDate();            //日
    var hh = date.getHours();             //小时
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(type != "M"){
	    if(day < 10)
	        clock += "0";
	    clock += day + "-";
	    if(type == "H"){
		    if(hh < 10)
		        clock += "0";
		    clock += hh;
	    }
    }
    clock = clock.replace(/-/g,"");
    return clock; 
}

function getBetweenTime(strDateStart,strDateEnd){
	return (Date.parse(strDateEnd) - Date.parse(strDateStart)) / 1000;
}
