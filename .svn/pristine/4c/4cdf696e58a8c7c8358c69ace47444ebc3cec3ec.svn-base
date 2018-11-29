var serviceObject = new Object();
(function(){
function getServiceTraffic(){
	mychart1.showLoading();
	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		data : $('#searchForm').formToJSON(),
		url : getServiceTrafficDataUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success: function(resultData) {
//				var rowData = resultData.rows;
            	option1.legend.data = new Array();
            	option1.xAxis[0].data = new Array();
    			option1.series[0].data = new Array();
        		$.each(resultData, function(idx, item){
        			if(item.CATALOGID == 0 || item.CATALOGID == 999) return true;
        			//if(idx == resultData.length-1) return false;
        			option1.legend.data.push(item.CATALOGNAME);
        			option1.xAxis[0].data.push(item.CATALOGNAME);
    				option1.series[0].data.push({value:item.FLOW_TOTAL, name:item['CATALOGNAME']});
        		});
        		mychart1.setOption(option1,true);
        		mychart1.resize();
        		window.onresize = mychart1.resize;
        		mychart1.hideLoading();
        }
	});
}

var mychart1 = echarts.init(document.getElementById('chartService'));
var option1 = {
		/*
		title : {
			text: '业务流量（GB）',
			x:'center',
		},*/
		color: ["#2ec7c9", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#b6a2de", "#5ab1ef", "#ffbf7a", "#07a2a4"],
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
			top: '30',
			bottom: '7%',
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
					rotate: 15,
					},
				axisTick: {
					alignWithLabel: true
				}
			}
		],
		yAxis : [
			{
				splitLine:{  
		            show:true,
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
mychart1.resize();
serviceObject.getServiceTraffic = getServiceTraffic;

serviceObject.resize = function(){
	mychart1.resize();
};

})();