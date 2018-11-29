var statisticsObject = new Object();
(function(){
$("#export_statistic").on("click", function(e){
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
	$.download({url:exportStatisticUrl, data:data});
});

function search() {
	getTableColumnData(getTableStaColumnDataUrl);
	$('#appTable_statistical').bootstrapTable("destroy").bootstrapTable({
		method : 'post',
		url : initStaTableUrl,
		queryParams : pageQueryParams,
		contentType : "application/x-www-form-urlencoded",
		cache : false,
		//height : 400,
		striped : true,
		pagination : false,
		pageSize : 10000,
		pageList : [ 5000, 10000 ],
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
	tableRefresh();
}

function searchCharts(){
	myChart_1.showLoading();
	var params = $('#searchForm').formToJSON();
	params.offset = params.offset;
	params.limit =  10000;
	params.sort = $("#hid_sort").val();
	params.order = "desc";
	
	$.ajax({
		type : "POST",
		async : true,
		dataType : 'json',
		url : initStaTableUrl,
		data : params,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			if(data != null && data.rows != null){
				var resultData = data.rows;
				var tooltipStr = "";
				var seriePieShowData = []; 
				var serieMapShowData = [];
				var pieLegendData = [];
				
				var seriesPieFlowData = []; 
				var seriesPiePageViewData = [];  
				var seriesPiePageTimeData = [];  
				var seriesPieSuccessconnectPreData = [];
				
				$.each(resultData, function(i, item){
					if(i<10){
						pieLegendData.push(item['CARRIER_NAME']);
						seriesPieFlowData.push({name:item['CARRIER_NAME'],value:item['FLOW']});
						seriesPiePageViewData.push({name:item['CARRIER_NAME'],value:item['PAGE_VIEW']});
						seriesPiePageTimeData.push({name:item['CARRIER_NAME'],value:item['PAGE_TIME']});
						seriesPieSuccessconnectPreData.push({name:item['CARRIER_NAME'],value:item['SUCCESSCONNECT_PRE']});
					}else{
						if(i == 10){
							pieLegendData.push("其他运营商");
							seriesPieFlowData.push({name:"其他运营商",value:item['FLOW']});
							seriesPiePageViewData.push({name:"其他运营商",value:item['PAGE_VIEW']});
							seriesPiePageTimeData.push({name:"其他运营商",value:item['PAGE_TIME']});
							seriesPieSuccessconnectPreData.push({name:"其他运营商",value:item['SUCCESSCONNECT_PRE']});
						}else{
							seriesPieFlowData['其他运营商'] += item['FLOW'];
							seriesPiePageViewData['其他运营商'] += item['PAGE_VIEW'];
							seriesPiePageTimeData['其他运营商'] += item['PAGE_TIME'];
							seriesPieSuccessconnectPreData['其他运营商'] += item['SUCCESSCONNECT_PRE'];
						}
					}
				});
				
				switch ($("input[name='Fruit']:checked").val()){
					case "FLOW":seriePieShowData = seriesPieFlowData;tooltipStr = "总流量";break;
					case "PAGEVIEW":seriePieShowData = seriesPiePageViewData;tooltipStr = "访问量";break;
					case "PAGETIME":seriePieShowData = seriesPiePageTimeData;tooltipStr = "访问时长";break;
					case "SUCCESSCONNECTPRE":seriePieShowData = seriesPieSuccessconnectPreData;tooltipStr = "连接成功率";break;
				}	
				
				option_1.legend.data = pieLegendData;
				option_1.series[0].data = seriePieShowData;
				//option_1.title.subtext = tooltipStr;
				option_1.title.text = '业务运营商占比：' + tooltipStr;
				myChart_1.setOption(option_1);
				myChart_1.hideLoading();
			}
		}
	});
}

//table相关函数queryParams/tableColumnData/updateColumnUser/operateFormatter/events start 
//查询参数
function pageQueryParams(params) {
	var data = $('#searchForm').formToJSON();
	data.offset = params.offset;
	data.limit =  10000;
	if(params.sort == null){
		data.sort = $("#hid_sort").val();
	}else{
		data.sort = params.sort;
	}
	if( params.order == "desc")
		data.order = "asc";
	else
		data.order = "desc";
	console.log(data);
	return data;
}


function tableRefresh(){
	$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
}

statisticsObject.search = search;
statisticsObject.searchCharts = searchCharts;
statisticsObject.tableRefresh = tableRefresh;

// table相关函数end
var myChart_1 = echarts.init(document.getElementById('chart_1'));
var option_1 = {  
        title: {
        	  text: '业务运营商占比',
              subtext: '',
              x: 'center',
              textStyle : {
              	color: '#428bca',
              	fontSize : 14
              }
        },
        color: ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffbf7a", "#eb547c", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#07a2a4"],
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox : {
			show : true,
			orient : 'horizontal',
			right : '4%',   
			top : 'top',	
			feature : {
				//dataView : {
				//	readOnly : false
				//},
				restore : {},
				saveAsImage : {}
			}
		},
        legend: {
        	orient: 'vertical',
            x: 'left',
            padding: [50, 10, 15, 260],
            formatter: function (name) {
            	return name;
            },
            data: []
        },
        calculable: true,
        series: [{
        	 //name: '访问来源',
        	type: 'pie',
            radius: '65%',
            center: ['50%', '55%'],
            data: []
        }]
    };
//chart end

})();