var regionObject = new Object();
(function(){
function getRegionTraffic(){
	mychart1.showLoading();
	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		data : $('#searchForm').formToJSON(),
		url : getRegionTrafficDataUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success: function(resultData) {
//				var rowData = resultData.rows;
				var tooltipStr = "总流量";
            	//option1.legend.data = new Array();
    			option1.series[0].data = new Array();
        		$.each(resultData, function(idx, item){
        			option1.xAxis[0].data.push( item['PROVINCE_NAME'] == null?"":item['PROVINCE_NAME'].replace("省",""));
    				option1.series[0].data.push({name:item['PROVINCE_NAME'] == null?"":item['PROVINCE_NAME'].replace("省",""),value:item['FLOW_TOTAL']});
        		});
        		mychart1.setOption(option1,true);
        		mychart1.hideLoading();
        }
	});
}

var mychart1 = echarts.init(document.getElementById('chartRegion'));
var option1 = {
		/*
		title: {
			text: '地域来源流量（GB）',
			x:'center',
        },*/
		color: ["#ffbf7a", "#2ec7c9", "#b2dbbf", "#247ba0", "#70c1b3", "#d6e593", "#eb547c", "#b6a2de", "#5ab1ef", "#ffbf7a", "#07a2a4"],
		tooltip : {
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
        grid: {
			top: '30',
			bottom: '7%',
			left: '3%',
			right: '4%',
			containLabel: true
		},
		xAxis : [
			{
				splitLine:{  
		            show:true  ,
		            lineStyle : {
		            	color:'#e7e7e7'
		            }
		        },  
				type : 'category',
				data : [],
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
        calculable: true,
        series: [{
			name:'',
			type:'bar',
			barWidth: '26',
			data:[],
			label:{
				normal:{
					show: true,
					position: 'top'
				}
			}
        }]
	};
mychart1.setOption(option1, true);
regionObject.getRegionTraffic = getRegionTraffic;
})();