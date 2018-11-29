var statisticsObject = new Object();
(function(){
	function getAllNodes(){
		//var zNodestemp = "[{ id:\"0\", name:\"\" ,open:true,catalogId:0,classId:0,productId:0,moduleId:0},";
		var zNodestemp = "[";
		$.ajax({
			type : "POST",
			async : false,
			dataType : 'json',
			url : ctx + '/common/selectBizInfo.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					//alert(n.bizId);
					//zNodestemp += "{ id:\""+n.bizId+"\", pId:\""+n.parentId+"\", name:\""+n.bizName+"\",open:true,serverBuildId:"+n.serverBuildId+",serverRoomId:"+n.serverRoomId+"},";
					zNodestemp += "{ id:\""+n.bizId+"\", pId:\""+n.parentId+"\", name:\""+n.bizName+"\",open:true},";
				});
			}
		});
		zNodestemp += "]";

		return zNodestemp;
	}	
function search(){
	myChart1.showLoading();
	getTableColumnData(getTableStaColumnDataUrl);
	$('#appTable_statistical').bootstrapTable("destroy").bootstrapTable({
		method : 'post',
		url : initTableUrl,
		queryParams : queryParams,
		contentType : "application/x-www-form-urlencoded",
		cache : false,
		//height : 400,
		striped : true,
		pagination : false,
//		pageSize : 10,
//		pageList : [ 20, 50 ],
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
	}).on("load-success.bs.table", function(e, field, checked){
		var resultData = $('#appTable_statistical').bootstrapTable("getData");
		var titleData = new Array();
    	var xAxisData = new Array();
		var flowSeriesData = new Array();
		var pageViewSeriesData = new Array();
		var sessiontimeSeriesData = new Array();
		var successconnectRateSeriesData = new Array();
		$.each(resultData, function(i, item){
			xAxisData.push(item.R_STATTIME);
			flowSeriesData.push(item.FLOW);
			pageViewSeriesData.push(item.PAGE_VIEW);
			sessiontimeSeriesData.push(item.SESSIONTIME);
			successconnectRateSeriesData.push(item.SUCCESSCONNECT_RATE);
		});
		switch($("input[name='Fruit']:checked").val()){
		case "FLOW": option1.title.text="总流量(GB)"; option1.series[0].data = flowSeriesData;option1.series[0].name = "总流量(GB)";break;
		case "PAGE_VIEW": option1.title.text="访问量(万次)"; option1.series[0].data = pageViewSeriesData;option1.series[0].name = "访问量(万次)";break;
		case "SESSIONTIME": option1.title.text="访问时长(小时)";option1.series[0].data = sessiontimeSeriesData;option1.series[0].name = "访问时长(小时)";break;
		case "SUCCESSCONNECT_RATE": option1.title.text="TCP连接成功率(%)";option1.series[0].data = successconnectRateSeriesData;option1.series[0].name = "TCP连接成功率(%)";break;
	}
		option1.xAxis[0].data  = xAxisData;
		myChart1.setOption(option1);
		myChart1.resize();
		myChart1.hideLoading();
		$("input[name='Fruit']").bind("change", function(){
			option1.xAxis[0].data  = xAxisData;
			switch($(this).val()){
				case "FLOW": option1.title.text="总流量(GB)"; option1.series[0].data = flowSeriesData;option1.series[0].name = "总流量(GB)";break;
				case "PAGE_VIEW": option1.title.text="访问量(万次)"; option1.series[0].data = pageViewSeriesData;option1.series[0].name = "访问量(万次)";break;
				case "SESSIONTIME": option1.title.text="访问时长(小时)";option1.series[0].data = sessiontimeSeriesData;option1.series[0].name = "访问时长(小时)";break;
				case "SUCCESSCONNECT_RATE": option1.title.text="TCP连接成功率(%)";option1.series[0].data = successconnectRateSeriesData;option1.series[0].name = "TCP连接成功率(%)";break;
			}
			myChart1.clear();
			myChart1.setOption(option1,true);
			myChart1.resize();
			myChart1.hideLoading();
		});
	});
}

var myChart1 = echarts.init(document.getElementById('chartTrend'));
var option1 = {
		title: {
	        text: '',
	        show: false
	    },
	    color: ['#2ec7c9'],
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
	    toolbox: {
	    	show : true,
			orient : 'horizontal',
			right : '4%',   
			top : 'top',	
	        feature: {
	        	    mark : {show: true},
		            //dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
	        }
	    },
        xAxis: [{
        	splitLine : {
				show : true,
	            lineStyle : {
	            	color:'#e7e7e7'
	            }
			},
            type: 'category',
            boundaryGap: false,
            data: []
        }],
        yAxis: [{
        	splitLine : {
				show : true,
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
        series: [{
            name: '',
            type: 'line',
            data: []
        }, ]
	};

function refreshTable(){
	$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
}
function refreshChart(){
	myChart1.dispose();
	myChart1 = echarts.init(document.getElementById("chartTrend"));
	myChart1.setOption(option1, true);
	myChart1.resize();
}
statisticsObject.search = search;
statisticsObject.refreshTable = refreshTable;
statisticsObject.refreshChart = refreshChart;
statisticsObject.getAllNodes=getAllNodes;

$("#exportbtn").on("click", function(e){
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
	$.download({url:exportTableUrl, data:data});
});
})();
//chart end