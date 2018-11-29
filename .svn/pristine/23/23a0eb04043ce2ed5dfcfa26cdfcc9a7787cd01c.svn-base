var buildObject = new Object();
(function(){
function getBuildTraffic(){
	mychart1.showLoading();
	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		data : $('#searchForm').formToJSON(),
		url : getServerbuildTrafficDataUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success: function(resultData) {
            	option1.legend.data = new Array();
            	option1.xAxis[0].data = new Array();
    			option1.series[0].data = new Array();
        		$.each(resultData, function(idx, item){
        			option1.legend.data.push(item.SERVERBUILD_ID);
        			option1.xAxis[0].data.push(item.SERVERBUILD_ID);
    				option1.series[0].data.push({value:item.FLOW_TOTAL, name:item['SERVERBUILD_ID']});
        		});
        		mychart1.setOption(option1,true);
        		mychart1.hideLoading();
        }
	});
}

var mychart1 = echarts.init(document.getElementById('chartBuild'));
var option1 = {
		/*
		title : {
			text: '机房流量（GB）',
			x:'center',
		},*/
		color: ["#b6a2de", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#5ab1ef", "#f5994e", "#07a2a4"],
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
			right: '4%',
			bottom: '7%',
			top:'30',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				data : [],
				splitLine:{  
		            show:true  ,
		            lineStyle : {
		            	color:'#e7e7e7'
		            }
		        },  
				axisLabel: {
					 interval:0,
					rotate: 10,
					},
				axisTick: {
					alignWithLabel: true
				}
			}
		],
		yAxis : [
			{
				splitLine:{  
		            show:true  ,
		            lineStyle : {
		            	color:'#e7e7e7'
		            }
		        },  
				type : 'value'
			}
		],
		series : [
			{
				name:'',
				type:'bar',
				barWidth: '40',
				data:[],
				label:{
					normal:{
						show: true,
						position: 'top'
					}
				}
			}
		]
	};
mychart1.setOption(option1, true);
buildObject.getBuildTraffic = getBuildTraffic;
})();
//chart end