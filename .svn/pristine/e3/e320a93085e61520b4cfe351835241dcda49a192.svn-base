var protocolObject = new Object();
(function(){
function getProtocolTraffic(){
	mychart1.showLoading();
	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		data : $('#searchForm').formToJSON(),
		url : getProtocolTrafficDataUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success: function(resultData) {
            	option1.legend.data = new Array();
            	option1.yAxis[0].data = new Array();
    			option1.series[0].data = new Array();
    			for(var j = resultData.length-1; j >-1; j--){
					//option1.legend.data.push(resultData[j].PROTOCOL_ID);
					option1.yAxis[0].data.push(resultData[j].PROTOCOL_ID);
				}
        		$.each(resultData, function(idx, item){
    				option1.series[0].data.push({value:item.FLOW_TOTAL, name:item['PROTOCOL_ID']});
        		});
        		option1.series[0].data = sort1(option1.series[0].data, 10, 'asc');
        		mychart1.setOption(option1,true);
        		mychart1.hideLoading();
        }
	});
}

var mychart1 = echarts.init(document.getElementById('chartProtocol'));
var option1 = {
		/*
		title : {
			text: '应用层协议流量（GB）',
			x:'center',
		},*/
		color: ["#5ab1ef", "#b6a2de", "#70c1b3", "#247ba0", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#ffbf7a", "#07a2a4"],
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
				data : []
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
protocolObject.getProtocolTraffic = getProtocolTraffic;
})();
//chart end