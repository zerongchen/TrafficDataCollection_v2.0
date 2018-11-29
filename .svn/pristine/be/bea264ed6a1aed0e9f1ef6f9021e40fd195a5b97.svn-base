var statisticsObject = new Object();
function openlayer(id,str){
	$("#hid_queryProtocolId").val(id);
	$("#hid_queryErrorCode").val(str);
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
}
(function(){
$("#export_statistic").on("click", function(e){
	var data = new Object();
	data.headers = new Array();
	data.fields = new Array();		
	data = $.extend(true,queryParams({offset:0, limit:0, sort:"ERRORCODE_COUNT", order:"DESC"}), data);
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
	$("#hid_queryDetailsId").val(0);
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
    return '<a class="show_view " style="cursor:pointer;" onclick = "openlayer(\''+row.PROTOCOL_ID+'\',\''+row.ERRORCODE+'\')">查看</a>';
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
		pageList : [ 5, 10 ],
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
	myChart_3.showLoading();
	var params = $('#searchForm').formToJSON();
	params.offset = params.offset;
	params.limit =  10000;
	params.sort = $("#hid_sort").val();
	params.order = "desc";
	
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
				var tooltipStr = "";
				var serieShowData = []; 
				var pieLegendData = [];
				$.each(resultData, function(i, item){
					if(i<10){
						pieLegendData.push(item['PROTOCOL_NAME']+"_"+item['ERRORCODE']);
						serieShowData.push({name:item['PROTOCOL_NAME']+"_"+item['ERRORCODE'],value:item['ERRORCODE_COUNT']});
					}else{
						if(i == 10){
							pieLegendData.push("其他");
							serieShowData.push({name:"其他",value:item['ERRORCODE_COUNT']});
						}else{
							serieShowData['其他'] += item['ERRORCODE_COUNT'];
						}
					}
				});
				option_3.legend.data = pieLegendData;
				option_3.series[0].data = serieShowData;
				option_3.title.subtext = tooltipStr;
				myChart_3.resize();
				myChart_3.setOption(option_3);
				myChart_3.hideLoading();
			}
		}
	});
}

function pageQueryParams(params) {
	var data = $('#searchForm').formToJSON();
	data.offset = params.offset;
	data.limit =  1000000;
	//data.queryBizIdStr = $("input[name='queryBizID']").val();
	if(params.sort == null){
		data.sort = $("#hid_sort").val();
	}else{
		data.sort = params.sort;
	}
	if( params.order == "desc")
		data.order = "asc";
	else
		data.order = "desc";
	console.log(data);
	return data;
}

function searchLayer() {
	myChart_4.showLoading();
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
		var seriesData = [];  
		$.each(resultData, function(i, item){
			xAxisFlowData.push(item['R_STATTIME']);
			seriesData.push(item['ERRORCODE_COUNT']);
		});
		option_4.xAxis.data  = xAxisFlowData;
		option_4.series[0].data  = seriesData;
		myChart_4.resize();
		myChart_4.setOption(option_4, true);
		myChart_4.hideLoading();
	});
}


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
				zNodestemp += "{ id:\""+n.bizId+"\", pId:\""+n.parentId+"\", name:\""+n.bizName+"\",open:true"+"},";
			});
		}
	});
	zNodestemp += "]";

	return zNodestemp;
}	

statisticsObject.search = search;
statisticsObject.searchCharts = searchCharts;
statisticsObject.searchLayer = searchLayer;
statisticsObject.getAllNodes = getAllNodes;
// table相关函数end


//chart start
var myChart_3 = echarts.init(document.getElementById('chart_3'));
var myChart_4 = echarts.init(document.getElementById('chart_4'));
var option_3 = {  
        title: {
        	 text: '',
  	        show: false     
        },
        color: ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffbf7a", "#eb547c", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#07a2a4"],
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },        
        toolbox : {
			show : true,
			orient : 'horizontal',
			right : '4%',   
			top : '2%',	
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
	            padding: [40, 10, 15, 100],
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
             center: ['50%', '56%'],
             data: []
        }]
    };
var option_4 = {
	    title: {
		       // text: '全部节点流量趋势',
		        show: false
		    },
		    color: ["#b6a2de"],
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
		    toolbox : {
				show : true,
				orient : 'horizontal',
				right : '4%',   
				top : 'top',	
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
		            name:'错误码数量',
		            type:'line',
		            barMaxWidth:'38',
		            data:[]
		        }
		    ]
		};
})();
//chart end