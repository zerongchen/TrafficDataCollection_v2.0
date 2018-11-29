var trendObject = new Object();
(function(){
//上行速率 = 上行流量/表颗粒度（如小时表即除以3600s）
function search() {
	mychart_2.showLoading();
	getTableColumnData(getTableTrendColumnDataUrl);
	//alert(queryParams);
	//tableColumnData.unshift({field: 'index', title: '序号',width: 80,align: 'center',formatter: function(value, row, index){return index+1;}, switchable:false});
	$('#appTable_trend').bootstrapTable("destroy").bootstrapTable({
		method : 'post',
		url : initTrendTableUrl,
		queryParams : queryParams,
		contentType : "application/x-www-form-urlencoded",
		cache : false,
		//height : 400,
		striped : true,
		pagination : false,
		sortable: false,  
		pageSize : 5,
		pageList : [ 5, 10 ],
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
		updateColumnUser(e, field, checked, updateTrendColumnUserUrl);
	}).on("load-success.bs.table", function(e, field, checked){
		var resultData = $('#appTable_trend').bootstrapTable("getData");
		var xAxisFlowData = [];  
		var seriesData = []; 
		$.each(resultData, function(i, item){
			xAxisFlowData.push(item['R_STATTIME']);
			seriesData.push(item['ERRORCODE_COUNT']);
		});
		option_2.xAxis.data  = xAxisFlowData;
		option_2.series[0].data  = seriesData;
		mychart_2.setOption(option_2, true);
		mychart_2.resize();
		mychart_2.hideLoading();
	});
}
trendObject.search = search;


// table相关函数end

var mychart_2 = echarts.init(document.getElementById('chart_2'));
var option_2 = {
	    title: {
		       // text: '全部节点流量趋势',
		        show: false
		    },
		    tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},		    
		    color: ["#b6a2de"],
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
			toolbox : {
				show : true,
				orient : 'horizontal',
				right : '4%',   
				top : 'top',	
		        feature : {
		            mark : {show: true},
		            //dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
			},
		    xAxis: {
		    	splitLine:{  
    	            show:true  ,
    	            lineStyle : {
    	            	color:'#e7e7e7'
    	            }
    	        },  
		        type: 'category',
		        boundaryGap: false,
		        data: []
		    },
		    yAxis: {
		    	splitLine:{  
    	            show:true  ,
    	            lineStyle : {
    	            	color:'#e7e7e7'
    	            }
    	        }, 
		        type: 'value'
		    },
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
		            name:'错误码数量',
		            type:'line',	
		            barMaxWidth:'38',
		            data:[]
		        }
		    ]
		};
mychart_2.setOption(option_2,true);
mychart_2.resize();
trendObject.refreshChart = function(){
	mychart_2.dispose();
	mychart_2 = echarts.init(document.getElementById('chart_2'));
	mychart_2.setOption(option_2,true);
}
})();
//chart end