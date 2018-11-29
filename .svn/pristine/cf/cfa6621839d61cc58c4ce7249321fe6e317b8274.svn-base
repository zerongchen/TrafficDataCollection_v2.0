var overviewObject = new Object();
(function(){
//上行速率 = 上行流量/表颗粒度（如小时表即除以3600s）
function getTrafficTrend() {
	mychart_1.showLoading();
	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		data : $('#searchForm').formToJSON(),
		url : getTrafficTrendDataUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success: function(resultData) {	  
        		var xAxisFlowData = new Array();  
        		var seriesFlowAllData = new Array(); 
        		var seriesFlowUpData = new Array();  
        		var seriesFlowDnData = new Array();  
        		$.each(resultData, function(i, item){
        			xAxisFlowData.push(item['R_STATTIME']);
        			seriesFlowAllData.push( item['FLOW_RATE']);
        			seriesFlowUpData.push( item['FLOWUP_RATE']);
        			seriesFlowDnData.push( item['FLOWDN_RATE']);
        		});
        		option_1.xAxis[0].data  = xAxisFlowData;
        		option_1.series[0].data  = seriesFlowUpData;
        		option_1.series[1].data  = seriesFlowDnData;
        		option_1.series[2].data  = seriesFlowAllData;
        		mychart_1.setOption(option_1, true);
        		mychart_1.hideLoading();
        }
		
	});
}

var mychart_1 = echarts.init(document.getElementById('chartTrend'));
var option_1 = {
		tooltip : {
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
    color: ["#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#b6a2de", "#5ab1ef", "#f5994e", "#07a2a4"],
    legend: {
        data: ['上行速率（Mbps）', '下行速率（Mbps）', '均值速率（Mbps）'],
        top:'2%'
    },
    grid: {
        left: '3%',
        top: '40',
        right: '6%',
        bottom: '3%',
        containLabel: true
    },
    toolbox : {
		show : true,
		orient : 'horizontal',
		right : '5%',
		top : '2%',
        feature : {
        	mark:{show:true},
            //dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar'] },
            restore : {show: true},
            saveAsImage : {show: true}
        }
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
				name : '上行速率（Mbps）',
				type : 'line',
				symbol : 'none',
				smooth: true,
				itemStyle : {
					normal : {
						areaStyle : {
							type : "default"
						}
					}
				},
				data: []

			},
 	        {
 	            name:'下行速率（Mbps）',
 	            type:'line', 
 	           symbol:'none',
 	           smooth: true,
				itemStyle : {
					normal : {
						areaStyle : {
							type : "default"
						}
					}
				},
				data: []

 	        },
 	        {
 	            //name:'均值速率（Mbps）',
 	        	name:'均值速率（Mbps）',
 	            type:'line',
 	            symbol:'none',
 	            smooth: true,
				itemStyle : {
					normal : {
						areaStyle : {
							type : "default"
						}
					}
				},
				data: []
 	        }
 	    ]
};
mychart_1.setOption(option_1, true);
overviewObject.getTrafficTrend = getTrafficTrend;
})();
//chart end