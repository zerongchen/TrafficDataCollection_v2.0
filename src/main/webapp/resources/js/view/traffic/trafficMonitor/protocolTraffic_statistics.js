var statisticsObject = new Object();
function openlayer(id){
	$("#hid_queryDetailsId").val(id);
	$("#export_layer").hide();
	$("input[name='Fruit1']").get(0).checked = true;   
	$('#chart_3').show();
    $('#div_list').hide();
	statisticsObject.searchLayer();
	layer.open({
		title: '趋势分析',
        type: 1,
        area: ['1100px', '650px'],
        shadeClose: true, //点击遮罩关闭
        content: $('#div_layer')
    });
	statisticsObject.refreshLayerChart();
}
(function(){
//协议下拉框
function selectProtocol() {
	var params = new Object();
    params.protocolTypeStr = $("#sel_protocoltype").val()+ ",2";
	$.ajax({
		type : "POST",
		async : true,
		dataType : 'json',
		url : ctx + '/common/selectProtocol.do',
		data: params,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			var selectvalue = [];
			$("#sel_protocol").html("");
			$.each(data, function(i, n) {
				opt = $('<option />', {value: n.protocolId ,text: n.protocolName});
				opt.appendTo($("#sel_protocol").multiselect());
				selectvalue.push(n.protocolId);
			});
			$("#sel_protocol").val(selectvalue);
			$("#sel_protocol").multiselect('refresh');
		}
	});
}
statisticsObject.selectProtocol = selectProtocol;

$("#export_statistic").on("click", function(e){
	var data = new Object();
	data.headers = new Array();
	data.fields = new Array();		
	data = $.extend(true,queryParams({offset:0, limit:0, sort:"FLOW", order:"desc"}), data);
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
	$("#hid_queryDetailsId").val(-1);
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

function operateFormatter(value, row, index) {
    return '<a class="show_view " style="cursor:pointer;" onclick = "openlayer(\''+row.P_ID+'\')">查看</a>';
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
		pageSize : 1000000,
		//pageList : [ 10, 3 ],
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
	//$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
}

function searchCharts(){
	var params = $('#searchForm').formToJSON();
	params.offset = params.offset;
	params.limit =  10;
	params.sort = $("#hid_sort").val();
	params.order = "desc";
	myChart_1.showLoading();
	$.ajax({
		type : "POST",
		async : true,
		dataType : 'json',
		url : initStaTableUrl,
		data : params,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			if(data != null && data.rows != null){
				var resultData = data.rows;
				var xAxisFlowData = [];  
				var seriesFlowAllData = []; 
				var seriesFlowUpData = [];  
				var seriesFlowDnData = [];  
				var echartsTitle = [];
				$.each(resultData, function(i, item){
					if(i<=9){
						xAxisFlowData.push(item['PROTOCOL_NAME']);
						seriesFlowAllData.push( item['FLOW']   );
						seriesFlowUpData.push( item['FLOW_UP']);
						seriesFlowDnData.push( item['FLOW_DN']);
					}else {
						return false;
					}
				});
				option_1.xAxis[0].data  = xAxisFlowData;
				switch ($("input[name='Fruit']:checked").val()){
					case "FLOWALL":option_1.series[0].data  = seriesFlowAllData;option_1.series[0].name = "总流量（GB）";echartsTitle.push("总流量（GB）");break;
					case "FLOWUP":option_1.series[0].data  = seriesFlowUpData;option_1.series[0].name = "上行流量（GB）";echartsTitle.push("上行流量（GB）");break;
					case "FLOWDN":option_1.series[0].data  = seriesFlowDnData;option_1.series[0].name = "下行流量（GB）";echartsTitle.push("下行流量（GB）");break;
				}
				option_1.legend.data = echartsTitle;
				myChart_1.setOption(option_1, true);
				myChart_1.resize();
				myChart_1.hideLoading();
			}
		}
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

function searchLayer() {
	getTableColumnData(getTableTrendColumnDataUrl);
	myChart_3.showLoading();
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
		option_3.xAxis.data  = xAxisFlowData;
		option_3.series[0].data  = seriesFlowUpData;
		option_3.series[1].data  = seriesFlowDnData;
		option_3.series[2].data  = seriesFlowAllData;
		myChart_3.setOption(option_3, true);
		myChart_3.resize();
		myChart_3.hideLoading();
	});
	
	$('#appTable_layer').bootstrapTable("refresh", {silent: true});
}

statisticsObject.search = search;
statisticsObject.searchCharts = searchCharts;
statisticsObject.searchLayer = searchLayer;

// table相关函数end


//chart start
var myChart_1 = echarts.init(document.getElementById('chart_1'));
var myChart_3 = echarts.init(document.getElementById('chart_3'));
var option_3 = {
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
var option_1 = {
		legend: {
	        data: [],
			show : true,
			top:'3%'
	    },
		title: {
			text: '',
			x:'center',
			show : false
		},
		color: ['#b6a2de'],
		tooltip : {
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		toolbox : {
			show : true,
			orient : 'horizontal',
			right : '5%',
			top : '2%',
	        feature : {
	            mark : {show: true},
	            //dataView : {show: true, readOnly: false},
	            //magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
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
				barWidth: '38',
				data:[],
				symbol : 'none',
				smooth: true,
				itemStyle : {
					normal : {
						areaStyle : {
							type : "default"
						}
					}
				},
				label:{
					normal:{
						show: true,
						position: 'top'
					}
				}			
			}
		]
	};
myChart_1.setOption(option_1,true);
myChart_1.resize();
myChart_3.setOption(option_3,true);
myChart_3.resize();
statisticsObject.refreshChart = function(){
	myChart_1.dispose();
	myChart_1 = echarts.init(document.getElementById('chart_1'));
	myChart_1.setOption(option_1,true);
	myChart_1.resize();
}

statisticsObject.refreshLayerChart = function(){
	myChart_3.dispose();
	myChart_3 = echarts.init(document.getElementById('chart_3'));
	myChart_3.setOption(option_3,true);
	myChart_3.resize();
}


})();
//chart end