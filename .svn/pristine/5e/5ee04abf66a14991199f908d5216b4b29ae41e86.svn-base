var trendObject = new Object();
(function(){
//上行速率 = 上行流量/表颗粒度（如小时表即除以3600s）
	$("#export_trend").on("click", function(e){
//		tableColumnData = $('#trendTable').bootstrapTable("getOptions").columns;
		getTableColumnData(getTableColumnDataUrl);
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
		$.download({url:exportTrendUrl, data:data});
	});
	function operateFormatter(value, row, index) {
        return '全部';
    }
function search() {
	getTableColumnData(getTableColumnDataUrl);
	tableColumnData.splice(1,0,{field: 'trendAnalysis',title: '业务类别',width: 80,align: 'center',valign: 'middle',formatter: operateFormatter, events: operateEvents, switchable:false});
	$('#trendTable').bootstrapTable("destroy").bootstrapTable({
		method : 'post',
		url : initTableUrl,
		queryParams : queryParams,
		contentType : "application/x-www-form-urlencoded",
		cache : false,
		//height : 900,
		striped : true,
		pagination : false,
		sortable: false,
		//pageSize : 10,
//		pageList : [ 5, 10 ],
		showColumns : true,
		sidePagination : 'server',
		minimumCountColumns : 1,
		clickToSelect : true,
		paginationFirstText : "首页",
		paginationPreText : '上一页',
		paginationNextText : '下一页',
		paginationLastText : '最后一页',
		columns : tableColumnData
	}).on("column-switch.bs.table", function(e, field, checked) {
		updateColumnUser(e, field, checked, updateColumnUserUrl);
	}).on("load-success.bs.table", function(e, field, checked){
		var resultData = $('#trendTable').bootstrapTable("getData");
		var xAxisFlowData = [];  
		var seriesFlowAllData = []; 
		var seriesFlowUpData = [];  
		var seriesFlowDnData = [];  
		$.each(resultData, function(i, item){
			xAxisFlowData.push(item['R_STATTIME']);
			seriesFlowAllData.push( item['FLOW_RATE']);
			seriesFlowUpData.push( item['FLOWUP_RATE']);
			seriesFlowDnData.push( item['FLOWDN_RATE']);
		});
		option_2.xAxis[0].data  = xAxisFlowData;
		option_2.series[0].data  = seriesFlowUpData;
		option_2.series[1].data  = seriesFlowDnData;
		option_2.series[2].data  = seriesFlowAllData;
		mychart_2.setOption(option_2, true);
		mychart_2.resize();
		mychart_2.hideLoading();
//		console.log(resultData);
//		console.log(xAxisFlowData);
//		console.log(seriesFlowAllData);
//		console.log(seriesFlowUpData);
//		console.log(seriesFlowDnData);
	});
}


// table相关函数end

var mychart_2 = echarts.init(document.getElementById('chartTrend'));
var option_2 = {
    legend: {
        data: ['上行速率（Mbps）', '下行速率（Mbps）', '均值速率（Mbps）']
    },
    color: ["#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#b6a2de", "#5ab1ef", "#f5994e", "#07a2a4"],
    tooltip : {
		trigger: 'axis',
		axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		}
	},
	grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [{
    	splitLine:{  
            show:true  ,
            lineStyle : {
            	color:'#e7e7e7'
            }
        }, 
        type: 'category',
        boundaryGap: false,
        data: []
    }],
    yAxis: [{
    	splitLine:{  
            show:true  ,
            lineStyle : {
            	color:'#e7e7e7'
            }
        }, 
        type: 'value'
    }],
    dataZoom: [{
        type: 'inside',
        start: 0,
        end: 100
    }, {
    	//backgroundColor : 'rgba(252,250,211,0.7)',
    	dataBackground : {
    		areaStyle : {
    			color  : 'rgba(235,84,124,1)'
    		}
    	},
    	fillerColor:'rgba(178,212,219,0.5)',
    	showDataShadow : false,
        start: 0,
        end: 10
    }],
    series: [
		        {
		            name:'上行速率（Mbps）',
		            type:'line',
		            symbol:'none',
		            smooth:true,
					itemStyle : {
						normal : {
							areaStyle : {
								type : "default"
							}
						}
					},
		            data:[]
		        },
		        {
		            name:'下行速率（Mbps）',
		            type:'line',
		            symbol:'none',
		            smooth:true,
					itemStyle : {
						normal : {
							areaStyle : {
								type : "default"
							}
						}
					},
		            data:[]
		        },
		        {
		            name:'均值速率（Mbps）',
		            type:'line',
		            symbol:'none',
		            smooth:true,
					itemStyle : {
						normal : {
							areaStyle : {
								type : "default"
							}
						}
					},
		            data:[]
		        }
		    ]
		};
mychart_2.setOption(option_2, true);
mychart_2.resize();

trendObject.search = search;
trendObject.refreshTable = function(){
	$('#trendTable').bootstrapTable("refresh", {silent: true});
};
trendObject.refreshChart = function(){
	mychart_2.dispose();
	mychart_2 = echarts.init(document.getElementById('chartTrend'));
	mychart_2.setOption(option_2,true);
	mychart_2.resize();
}
})();
//chart end