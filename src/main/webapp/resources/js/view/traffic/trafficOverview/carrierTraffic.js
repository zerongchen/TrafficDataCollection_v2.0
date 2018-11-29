var carrierObject = new Object();
(function(){
function getCarrierTraffic(){
	mychart1.showLoading();
	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		data : $('#searchForm').formToJSON(),
		url : getCarrierTrafficDataUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success: function(resultData) {
//			var rowData = resultData.rows;
			var tooltipStr = "总流量";
        	option1.legend.data = new Array();
			option1.series[0].data = new Array();
    		$.each(resultData, function(idx, item){
    			option1.legend.data.push(item['CARRIER_NAME'] == null?"":item['CARRIER_NAME']);
				option1.series[0].data.push({name:item['CARRIER_NAME'] == null?"":item['CARRIER_NAME'],value:item['FLOW_TOTAL']});
    		});
    		mychart1.setOption(option1,true);
    		mychart1.hideLoading();
        }
	});
}
var mychart1 = echarts.init(document.getElementById('chartCarrier'));
var option1 = {
		/*
		title: {
			text: '运营商流量（GB）',
			x:'center',
        },		*/
		color: ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffbf7a", "#eb547c", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#07a2a4"],
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            padding: [35, 10, 15, 80],
            formatter: function (name) {
            	return name;
            },
            data: []
        },
        calculable: true,
        series: [{
            type: 'pie',
            radius: '60%',
            center: ['55%', '57%'],
            data: []
        }]
	};
mychart1.setOption(option1, true);
carrierObject.getCarrierTraffic = getCarrierTraffic;
})();
//chart end