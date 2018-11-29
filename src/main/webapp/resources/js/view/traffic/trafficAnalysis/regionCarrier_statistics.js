var statisticsObject = new Object();
function openlayer(id){
	$("#hid_queryProvinceId").val(id.substring(0,id.lastIndexOf('_')));
	$("#hid_queryCarrierId").val(id.substring(id.lastIndexOf('_')+1));
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
	data = $.extend(true,queryParams({offset:0, limit:0, sort:"FLOW", order:"desc"}), data);
	getTableColumnData(getTableStaColumnDataUrl);
	$.each(tableColumnData, function(idx, item){
		if(item.visible ==  true){
			data.headers.push(item.title);
			data.fields.push(item.field);
		}
	});
	$.download({url:exportStatisticUrl, data:data});
});

$("#export_trend").on("click", function(e){
	$("#hid_queryDetailsIdStr").val(0);
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
    return '<a class="show_view " style="cursor:pointer;" onclick = "openlayer(\''+row.PC_ID+'\')">查看</a>';
}
function search() {
	getTableColumnData(getTableStaColumnDataUrl);
	tableColumnData.unshift({field: 'index', title: '序号',width: 80,align: 'center',formatter: function(value, row, index){var page = $('#appTable_statistical').bootstrapTable('getPage'); return page.pageSize*(page.pageNumber-1)+ index+1;}, switchable:false});
	tableColumnData.splice(3,0,{field: '',title: '趋势分析',width: 80,align: 'center',valign: 'middle',formatter: operateFormatter , switchable:false});
	$('#appTable_statistical').bootstrapTable("destroy").bootstrapTable({
		method : 'post',
		url : initStaTableUrl,
		queryParams : pageQueryParams,
		contentType : "application/x-www-form-urlencoded",
		cache : false,
		//height : 400,
		striped : true,
		pagination : true,
		pageSize : 10,
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
						xAxisFlowData.push(item['PC_NAME']);
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
	data.limit =  params.limit;
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

statisticsObject.search = search;
statisticsObject.searchCharts = searchCharts;
statisticsObject.searchLayer = searchLayer;
statisticsObject.getAllNodes = getAllNodes;

// table相关函数end


//chart start
var myChart_1 = echarts.init(document.getElementById('chart_1'));
var myChart_4 = echarts.init(document.getElementById('chart_4'));
var option_1 = {
		legend: {
	        //data: ['TOP10应用层协议流量（GB）']
			 data: [],
			 show : true
	    },
		title: {
			text: '',
			x:'center',
			show : false,
			top:'3%'
		},
		color: ['#70c1b3'],
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
	            magicType : {show: true, type: ['line', 'bar']},
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
		/*dataZoom: [{
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
	    }],*/
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
	myChart_1.dispose();
	myChart_1 = echarts.init(document.getElementById('chart_1'));
	myChart_1.setOption(option_1,true);
	myChart_1.resize();
}

statisticsObject.refreshLayerChart = function(){
	myChart_4.dispose();
	myChart_4 = echarts.init(document.getElementById('chart_4'));
	myChart_4.setOption(option_4,true);
	myChart_4.resize();
}

})();
//chart end