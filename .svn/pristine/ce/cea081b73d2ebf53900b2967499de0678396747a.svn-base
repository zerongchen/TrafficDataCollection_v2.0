var IPObject = new Object();
(function(){
function getIPTraffic(){
	mychart1.showLoading();
	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		data : $('#searchForm').formToJSON(),
		url : getIPTrafficDataUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success: function(resultData) {
				var xAxisFlowData = [];
				var serieShowData = []; 
            	option1.legend.data = new Array();
            	option1.yAxis[0].data = new Array();
    			option1.series[0].data = new Array();
        		$.each(resultData, function(idx, item){
        			option1.legend.data.push(item.DESTIP);
        			xAxisFlowData.push(item.DESTIP);
        			serieShowData.push({value:item.FLOW_TOTAL, name:item['DESTIP']});
        			//option1.yAxis[0].data.push(item.DESTIP);
    				//option1.series[0].data.push({value:item.FLOW_TOTAL, name:item['DESTIP']});
        		});
        		//option1.series[0].data = sort1(option1.series[0].data, 10, 'asc');
        		option1.series[0].data = serieShowData.reverse();
        		option1.yAxis[0].data = xAxisFlowData.reverse();
        		mychart1.setOption(option1,true);
        		mychart1.hideLoading();
        }
	});
}

var mychart1 = echarts.init(document.getElementById('chartIP'));
var option1 = {
		/*
		title : {
			text: '目的IP地址流量（GB）',
			x:'center',
		},*/
		color: ["#eb547c", "#d6e593", "#2ec7c9", "#247ba0", "#70c1b3", "#b2dbbf", "#b6a2de", "#5ab1ef", "#ffbf7a", "#07a2a4"],
		tooltip : {
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
		},
		grid: {
			left: '3%',
			right: '8%',
			bottom: '7%',
			top:'30',
			containLabel: true
		},
		xAxis : [
			{
				splitLine : {
					show : true,
		            lineStyle : {
		            	color:'#e7e7e7'
		            }
				},
				type : 'value'
			}
		],
		yAxis : [
			{
				splitLine : {
					show : true,
		            lineStyle : {
		            	color:'#e7e7e7'
		            }
				},
				type : 'category',
				axisLabel: {
					 interval:0
					},
				data : [],
				axisTick: {
					alignWithLabel: true
				}
			}
		],
		series : [
			{
				name:'',
				type:'bar',
				barWidth: '26',
				data:[],
				label:{
					normal:{
						show: true,
						position: 'right'
					}
				}
			}
		]
	};
mychart1.setOption(option1, true);
IPObject.getIPTraffic = getIPTraffic;
})();
//chart end