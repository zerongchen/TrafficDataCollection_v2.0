var statisticsObject = new Object();
function openlayer(id){
	$("#hid_queryCarrierId").val(id);
	$("#export_layer").hide();
	$("input[name='Fruit1']").get(0).checked = true;   
	$('#chart_4').show();
    $('#div_list').hide();
	statisticsObject.searchLayer();
	layer.open({
		title: '趋势分析',
        type: 1,
        area: ['1100px', '625px'],
        shadeClose: true, //点击遮罩关闭
        content: $('#div_layer')
    });
	statisticsObject.refreshLayerChart();
}
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
					zNodestemp += "{ id:\""+n.bizId+"\", pId:\""+n.parentId+"\", name:\""+n.bizName+"\",open:true,serverBuildId:"+n.serverBuildId+",serverRoomId:"+n.serverRoomId+"},";
					//zNodestemp += "{ id:\""+n.bizId+"\", pId:\""+n.parentId+"\", name:\""+n.bizName+"\",open:true},";
				});
			}
		});
		zNodestemp += "]";

		return zNodestemp;
	}	
	
$("#export_statistic").on("click", function(e){
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
	$.download({url:exportStatisticUrl, data:data});
});

$("#export_trend").on("click", function(e){
	$("#hid_queryCarrierId").val(0);
	var data = new Object();
	data.headers = new Array();
	data.fields = new Array();		
	data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
	getTableColumnData(getTableTrendColumnDataUrl);
	$.each(tableColumnData, function(idx, item){
		if(item.visible == true){
			data.headers.push(item.title);
			data.fields.push(item.field);
		}
	});
	$.download({url:exportTrendUrl, data:data});
});

$("#export_layer").on("click", function(e){
	var data = new Object();
	data.headers = new Array();
	data.fields = new Array();		
	data = $.extend(true,queryParams({offset:0, limit:0, sort:"FLOW", order:"DESC"}), data);
	getTableColumnData(getTableTrendColumnDataUrl);
	$.each(tableColumnData, function(idx, item){
		if(item.visible == true){
			data.headers.push(item.title);
			data.fields.push(item.field);
		}
	});
	$.download({url:exportTrendUrl, data:data});
});

function operateFormatter(value, row, index) {
    return '<a class="show_view " style="cursor:pointer;" onclick = "openlayer(\''+row.CARRIER_ID+'\')">查看</a>';
}
function search() {
	getTableColumnData(getTableStaColumnDataUrl);
	tableColumnData.unshift({field: 'index', title: '序号',width: 80,align: 'center',formatter: function(value, row, index){return index+1;}, switchable:false});
	tableColumnData.splice(3,0,{field: '',title: '趋势分析',width: 80,align: 'center',valign: 'middle',formatter: operateFormatter , switchable:false});
	$('#appTable_statistical').bootstrapTable("destroy").bootstrapTable({
		method : 'post',
		url : initStaTableUrl,
		queryParams : pageQueryParams,
		contentType : "application/x-www-form-urlencoded",
		cache : false,
		//height : 400,
		striped : true,
		pagination : false,
		//pageSize : 10,
		//pageList : [ 5, 10 ],
		sortOrder:'desc',
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
		updateColumnUser(e, field, checked, updateStaColumnUserUrl);
	});
}

function searchCharts(){
	var params = $('#searchForm').formToJSON();
	params.offset = params.offset;
	params.limit =  10000;
	params.sort = $("#hid_sort").val();
	params.order = "desc";
	myChart_3.showLoading();
	$.ajax({
		type : "POST",
		async : true,
		dataType : 'json',
		url : initStaTableUrl,
		//url:getPositionOptionDataUrl,
		data : params,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			//data = $.parseJSON('{"total":6,"rows":[{"FLOW_PRE":47.80,"FLOW_RATE":"1128.90","CARRIER_NAME":"移动","FLOWUP_RATE":"453.76","FLOW_UP":9172.64,"FLOWDN_RATE":"675.15","FLOW_DN":13647.95,"CARRIER_ID":1,"FLOW":22820.60,"COUNTNUM":46},{"FLOW_PRE":29.53,"FLOW_RATE":"697.33","CARRIER_NAME":"电信","FLOWUP_RATE":"238.14","FLOW_UP":4814.03,"FLOWDN_RATE":"459.19","FLOW_DN":9282.39,"CARRIER_ID":2,"FLOW":14096.42,"COUNTNUM":46},{"FLOW_PRE":15.49,"FLOW_RATE":"365.86","CARRIER_NAME":"联通","FLOWUP_RATE":"129.26","FLOW_UP":2613.04,"FLOWDN_RATE":"236.60","FLOW_DN":4782.76,"CARRIER_ID":3,"FLOW":7395.80,"COUNTNUM":46},{"FLOW_PRE":2.48,"FLOW_RATE":"58.55","CARRIER_NAME":"铁通","FLOWUP_RATE":"21.28","FLOW_UP":430.12,"FLOWDN_RATE":"37.27","FLOW_DN":753.45,"CARRIER_ID":4,"FLOW":1183.57,"COUNTNUM":46},{"FLOW_PRE":4.63,"FLOW_RATE":"109.38","CARRIER_NAME":"其它","FLOWUP_RATE":"55.76","FLOW_UP":1127.24,"FLOWDN_RATE":"53.61","FLOW_DN":1083.79,"CARRIER_ID":5,"FLOW":2211.03,"COUNTNUM":46},{"FLOW_PRE":0.07,"FLOW_RATE":"1.66","CARRIER_NAME":"教育网","FLOWUP_RATE":"0.64","FLOW_UP":12.92,"FLOWDN_RATE":"1.02","FLOW_DN":20.62,"CARRIER_ID":6,"FLOW":33.54,"COUNTNUM":46}]}');
			if(data != null && data.rows != null){
				var resultData = data.rows;
				var tooltipStr = "";
				var seriePieShowData = []; 
				var pieLegendData = [];
				
				var seriesPieFlowAllData = []; 
				var seriesPieFlowUpData = [];  
				var seriesPieFlowDnData = [];  
				 
				$.each(resultData, function(i, item){
					if(i<10){
						pieLegendData.push(item['CARRIER_NAME']);
						seriesPieFlowAllData.push({name:item['CARRIER_NAME'],value:item['FLOW']});
						seriesPieFlowUpData.push({name:item['CARRIER_NAME'],value:item['FLOW_UP']});
						seriesPieFlowDnData.push({name:item['CARRIER_NAME'],value:item['FLOW_DN']});
						
					}else{
						if(i == 10){
							pieLegendData.push("其他运营商");
							seriesPieFlowAllData.push({name:"其他运营商",value:item['FLOW']});
							seriesPieFlowUpData.push({name:"其他运营商",value:item['FLOW_UP']});
							seriesPieFlowDnData.push({name:"其他运营商",value:item['FLOW_DN']});
						}else{
							seriesPieFlowAllData['其他运营商'] += item['FLOW'];
							seriesPieFlowUpData['其他运营商'] += item['FLOW_UP'];
							seriesPieFlowDnData['其他运营商'] += item['FLOW_DN'];
						}
					}
				});
				
				switch ($("input[name='Fruit']:checked").val()){
					case "FLOWALL":seriePieShowData  = seriesPieFlowAllData;tooltipStr = "总流量";break;
					case "FLOWUP":seriePieShowData  = seriesPieFlowUpData;tooltipStr = "上行流量";break;
					case "FLOWDN":seriePieShowData  = seriesPieFlowDnData;tooltipStr = "下行流量";break;
				}	
				
				option_3.legend.data = pieLegendData;
				option_3.series[0].data = seriePieShowData;
				option_3.title.text = '运营商流量占比：' + tooltipStr;
				myChart_3.setOption(option_3);
				myChart_3.resize();
				myChart_3.hideLoading();
			}
		}
	});
}


function formatterProvinceName(provinceName){
	provinceName   = provinceName.replace("省","").replace("市","");
	switch (provinceName){
		case "西藏自治区":provinceName = "西藏";break;
		case "新疆维吾尔自治区":provinceName = "新疆";break;
		case "内蒙古自治区": provinceName = "内蒙古";break;
		case "广西壮族自治区":provinceName = "广西";break;
		case "宁夏回族自治区":provinceName = "宁夏";break;
	}
	return provinceName;
}

function searchLayer() {
	getTableColumnData(getTableTrendColumnDataUrl);
	$('#appTable_layer').bootstrapTable("destroy").bootstrapTable({
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
		divshow:true,
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
		
		var resultData = $('#appTable_layer').bootstrapTable("getData");
		//console.log(resultData);
		var xAxisFlowData = [];  
		var seriesFlowAllData = []; 
		var seriesFlowUpData = [];  
		var seriesFlowDnData = [];  
		$.each(resultData, function(i, item){
			xAxisFlowData.push(item['R_STATTIME']);
			seriesFlowAllData.push( item['FLOW_RATE']   );
			seriesFlowUpData.push( item['FLOWUP_RATE']);
			seriesFlowDnData.push( item['FLOWDN_RATE']);
		});
		option_4.xAxis.data  = xAxisFlowData;
		option_4.series[0].data  = seriesFlowUpData;
		option_4.series[1].data  = seriesFlowDnData;
		option_4.series[2].data  = seriesFlowAllData;
		myChart_4.setOption(option_4, true);
		myChart_4.resize();
	});
}

function pageQueryParams(params) {
	var data = $('#searchForm').formToJSON();
	data.offset = params.offset;
	//alert($("#hid_sort").val());
	data.limit =  10000;
	if(params.sort == null){
		data.sort = $("#hid_sort").val();
	}else{
		data.sort = params.sort;
	}
	if(params.order == null){
		data.order = "desc";
	}else{
		data.order = params.order;
	}
	//if( params.order == "desc")
	//	data.order = "asc";
	//else
	//	data.order = "desc";
	//alert(data.order);
	//console.log(data);
	return data;
}

statisticsObject.search = search;
statisticsObject.searchLayer = searchLayer;
statisticsObject.searchCharts = searchCharts;
statisticsObject.getAllNodes = getAllNodes;

// table相关函数end


//chart start
var myChart_3 = echarts.init(document.getElementById('chart_3'));
var myChart_4 = echarts.init(document.getElementById('chart_4'));
var option_3 = {  
		color: ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffbf7a", "#eb547c", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#07a2a4"],
        title: {
            text: '运营商流量占比',
            subtext: '',
            x: 'center',
            textStyle : {
            	color: '#428bca',
            	fontSize : 14
            },
            show : false
        },
        tooltip: {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        toolbox : {
			show : true,
			orient : 'horizontal',
			right : '30',
			top : 'top',
			feature : {
				//dataView : {
				//	readOnly : false
				//},
				restore : {},
				saveAsImage : {}
			}
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
            //name: '访问来源',
            type: 'pie',
            radius: '65%',
            center: ['50%', '57%'],
            data: []
        }]
    };
var option_4 = {
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
 		color: ["#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#b6a2de", "#5ab1ef", "#f5994e", "#07a2a4"],
		    legend: {
		        data:['上行速率（Mbps）','下行速率（Mbps）','均值速率（Mbps）']
		    },
		    grid: {        	        
   	    	left: '3%',        	        
   	        right: '4%',
   	        bottom: '3%',
   	        containLabel: true
   	    },
			toolbox : {
				show : true,
				orient : 'horizontal',
				right : '5%',
				top : '2%',
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
		            name:'上行速率（Mbps）',
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
		            name:'均值速率（Mbps）',
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
		        }
		    ]
		};
statisticsObject.refreshChart = function(){
	myChart_3.dispose();
	myChart_3 = echarts.init(document.getElementById('chart_3'));
	myChart_3.setOption(option_3,true);
	myChart_3.resize();
}

statisticsObject.refreshLayerChart = function(){
	myChart_4.dispose();
	myChart_4 = echarts.init(document.getElementById('chart_4'));
	myChart_4.setOption(option_4,true);
	myChart_4.resize();
}


})();
//chart end