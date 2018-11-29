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
	myChart_2.showLoading();
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
				var seriesMapFlowData = []; 
				var seriesMapPageViewData = [];  
				var seriesMapPageTimeData = [];  
				var seriesMapSuccessconnectPreData = [];
				$.each(resultData, function(i, item){
					if(i<10){
						pieLegendData.push( item['PROVINCE_NAME'] == null ?"":formatterProvinceName(item['PROVINCE_NAME']));
						seriesPieFlowData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW']});
						seriesPiePageViewData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['PAGE_VIEW']});
						seriesPiePageTimeData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['PAGE_TIME']});
						seriesPieSuccessconnectPreData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['SUCCESSCONNECT_PRE']});
					}else{
						if(i == 10){
							pieLegendData.push("其他地域");
							seriesPieFlowData.push({name:"其他地域",value:item['FLOW']});
							seriesPiePageViewData.push({name:"其他地域",value:item['PAGE_VIEW']});
							seriesPiePageTimeData.push({name:"其他地域",value:item['PAGE_TIME']});
							seriesPieSuccessconnectPreData.push({name:"其他地域",value:item['SUCCESSCONNECT_PRE']});
						}else{
							seriesPieFlowData['其他地域'] += item['FLOW'];
							seriesPiePageViewData['其他地域'] += item['PAGE_VIEW'];
							seriesPiePageTimeData['其他地域'] += item['PAGE_TIME'];
							seriesPieSuccessconnectPreData['其他地域'] += item['SUCCESSCONNECT_PRE'];
						}
					}
					seriesMapFlowData.push({name:item['PROVINCE_NAME'] == null?"": formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW']});
					seriesMapPageViewData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['PAGE_VIEW']});
					seriesMapPageTimeData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['PAGE_TIME']});
					seriesMapSuccessconnectPreData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['SUCCESSCONNECT_PRE']});
				});
				
				switch ($("input[name='Fruit']:checked").val()){
					case "FLOW":seriePieShowData = seriesPieFlowData;serieMapShowData = seriesMapFlowData;tooltipStr = "总流量";break;
					case "PAGEVIEW":seriePieShowData = seriesPiePageViewData;serieMapShowData = seriesMapPageViewData;tooltipStr = "访问量";break;
					case "PAGETIME":seriePieShowData = seriesPiePageTimeData;serieMapShowData = seriesMapPageTimeData;tooltipStr = "访问时长";break;
					case "SUCCESSCONNECTPRE":seriePieShowData = seriesPieSuccessconnectPreData;serieMapShowData = seriesMapSuccessconnectPreData;tooltipStr = "TCP连接成功率";break;
				}	
				
				option_1.tooltip.formatter = function(params, ticket, callback) { return ((params.value + "").match(/[0-9.]*/g)[0].length > 0?(params.name + "</br>" + tooltipStr +"：" + params.value):""); };
				option_1.series[0].data = serieMapShowData;
				option_2.legend.data = pieLegendData;
				option_2.series[0].data = seriePieShowData;
				option_2.title.subtext = tooltipStr;
				myChart_1.setOption(option_1);
				myChart_2.setOption(option_2);
				myChart_1.hideLoading();
				myChart_2.hideLoading();
				
			}
		}
	});
	myChart_1.hideLoading();
	myChart_2.hideLoading();
}

function formatterProvinceName(provinceName){
	provinceName   = provinceName.replace("省","").replace("市","");
	switch (provinceName){
		case "西藏自治区":provinceName = "西藏";break;
		case "新疆维吾尔自治区":provinceName = "新疆";break;
		case "内蒙古自治区": provinceName = "内蒙古";break;
		case "广西壮族自治区":provinceName = "广西";break;
		case "宁夏回族自治区":provinceName = "宁夏";break;
	}
	return provinceName;
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
var myChart_2 = echarts.init(document.getElementById('chart_2'));
var option_1 = {
		title : {
			text : '',
			left : 'center',
			show : false
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params, ticket, callback) {}
		},
		visualMap : {
			//inverse : true,
			//type : 'piecewise',
			inRange : {
				color: ['#b2dbbf', '#2ec7c9']
			},
			left : 'center',
			top : 'top',
			orient : 'horizontal',
			text : [ '高', '低' ], // 文本，默认为数值文本
			calculable : true
		},
		toolbox : {
			show : true,
			orient : 'horizontal',
			right : '30',
			top : 'top',
	        feature : {
	            //mark : {show: true},
	            //dataView : {show: true, readOnly: false},
	            //magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
		},
		series : [ {
			name : '',
			type : 'map',
			mapType : 'china',
			left : '20',
			right: '20',
			itemStyle : {
				normal : {
					borderColor : '#fff',
					areaColor: '#e7e7e7'
				},
				emphasis : {
					areaColor  : '#07a2a4'
				}
			},
			label : {
				normal : {
					show : true,
					textStyle:{
		                    color:'#247ba0'
		            }
				},
				emphasis : {
					textStyle:{
						color: '#fff'
					},
					show : true
				}
			},
			data : []
		} ]
	};
var option_2 = {  
        title: {
            text: '',
            subtext: '',
            x: 'center'
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
            padding: [50, 10, 15, 280],
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
