var statisticsObject = new Object();
function openlayer(id){
	$("#hid_queryProvinceId").val(id);
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
	//console.log(getTableStaColumnDataUrl);
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
	$("#hid_queryProvinceId").val(0);
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
    return '<a class="show_view " style="cursor:pointer;" onclick = "openlayer(\''+row.PROVINCE_ID+'\')">查看</a>';
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
		striped : true,
		pagination : false,
		//pageSize : 10000,
		//pageList : [ 5000, 10000 ],
		showColumns : true,
		sortOrder:'desc',
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
	myChart_1.showLoading();
	myChart_3.showLoading();
	$.ajax({
		type : "POST",
		async : true,
		dataType : 'json',
		url : initStaTableUrl,
		//url : getPositionOptionDataUrl,
		data : params,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			//data = $.parseJSON('{"total":36,"rows":[{"FLOW_PRE":21.56,"FLOW_RATE":"499.91","FLOWUP_RATE":"126.15","PROVINCE_ID":440000,"FLOW_UP":2550.06,"FLOWDN_RATE":"373.76","PROVINCE_NAME":"广东省","FLOW_DN":7555.46,"FLOW":10105.52,"COUNTNUM":46},{"FLOW_PRE":11.10,"FLOW_RATE":"257.42","FLOWUP_RATE":"139.59","PROVINCE_ID":110000,"FLOW_UP":2821.78,"FLOWDN_RATE":"117.83","PROVINCE_NAME":"北京市","FLOW_DN":2381.92,"FLOW":5203.69,"COUNTNUM":46},{"FLOW_PRE":8.52,"FLOW_RATE":"197.50","FLOWUP_RATE":"54.43","PROVINCE_ID":330000,"FLOW_UP":1100.28,"FLOWDN_RATE":"143.07","PROVINCE_NAME":"浙江省","FLOW_DN":2892.09,"FLOW":3992.37,"COUNTNUM":46},{"FLOW_PRE":6.63,"FLOW_RATE":"153.74","FLOWUP_RATE":"57.31","PROVINCE_ID":320000,"FLOW_UP":1158.49,"FLOWDN_RATE":"96.43","PROVINCE_NAME":"江苏省","FLOW_DN":1949.27,"FLOW":3107.76,"COUNTNUM":46},{"FLOW_PRE":3.99,"FLOW_RATE":"92.58","FLOWUP_RATE":"35.35","PROVINCE_ID":370000,"FLOW_UP":714.64,"FLOWDN_RATE":"57.23","PROVINCE_NAME":"山东省","FLOW_DN":1156.86,"FLOW":1871.51,"COUNTNUM":46},{"FLOW_PRE":3.71,"FLOW_RATE":"86.13","FLOWUP_RATE":"36.52","PROVINCE_ID":360000,"FLOW_UP":738.34,"FLOWDN_RATE":"49.60","PROVINCE_NAME":"江西省","FLOW_DN":1002.69,"FLOW":1741.03,"COUNTNUM":46},{"FLOW_PRE":3.26,"FLOW_RATE":"75.57","FLOWUP_RATE":"26.92","PROVINCE_ID":350000,"FLOW_UP":544.28,"FLOWDN_RATE":"48.65","PROVINCE_NAME":"福建省","FLOW_DN":983.39,"FLOW":1527.67,"COUNTNUM":46},{"FLOW_PRE":3.15,"FLOW_RATE":"73.00","FLOWUP_RATE":"29.95","PROVINCE_ID":410000,"FLOW_UP":605.44,"FLOWDN_RATE":"43.05","PROVINCE_NAME":"河南省","FLOW_DN":870.28,"FLOW":1475.72,"COUNTNUM":46},{"FLOW_PRE":2.82,"FLOW_RATE":"65.31","FLOWUP_RATE":"27.20","PROVINCE_ID":130000,"FLOW_UP":549.77,"FLOWDN_RATE":"38.12","PROVINCE_NAME":"河北省","FLOW_DN":770.49,"FLOW":1320.26,"COUNTNUM":46},{"FLOW_PRE":2.69,"FLOW_RATE":"62.41","FLOWUP_RATE":"27.70","PROVINCE_ID":510000,"FLOW_UP":560.05,"FLOWDN_RATE":"34.70","PROVINCE_NAME":"四川省","FLOW_DN":701.52,"FLOW":1261.57,"COUNTNUM":46},{"FLOW_PRE":2.59,"FLOW_RATE":"60.09","FLOWUP_RATE":"27.99","PROVINCE_ID":340000,"FLOW_UP":565.75,"FLOWDN_RATE":"32.10","PROVINCE_NAME":"安徽省","FLOW_DN":648.89,"FLOW":1214.64,"COUNTNUM":46},{"FLOW_PRE":2.28,"FLOW_RATE":"52.84","FLOWUP_RATE":"21.70","PROVINCE_ID":310000,"FLOW_UP":438.59,"FLOWDN_RATE":"31.15","PROVINCE_NAME":"上海市","FLOW_DN":629.61,"FLOW":1068.20,"COUNTNUM":46},{"FLOW_PRE":2.17,"FLOW_RATE":"50.42","FLOWUP_RATE":"31.38","PROVINCE_ID":0,"FLOW_UP":634.33,"FLOWDN_RATE":"19.04","PROVINCE_NAME":"国外","FLOW_DN":384.81,"FLOW":1019.14,"COUNTNUM":46},{"FLOW_PRE":2.15,"FLOW_RATE":"49.94","FLOWUP_RATE":"21.90","PROVINCE_ID":420000,"FLOW_UP":442.71,"FLOWDN_RATE":"28.04","PROVINCE_NAME":"湖北省","FLOW_DN":566.76,"FLOW":1009.47,"COUNTNUM":46},{"FLOW_PRE":2.12,"FLOW_RATE":"49.10","FLOWUP_RATE":"18.68","PROVINCE_ID":430000,"FLOW_UP":377.69,"FLOWDN_RATE":"30.41","PROVINCE_NAME":"湖南省","FLOW_DN":614.81,"FLOW":992.50,"COUNTNUM":46},{"FLOW_PRE":2.00,"FLOW_RATE":"46.47","FLOWUP_RATE":"18.24","PROVINCE_ID":530000,"FLOW_UP":368.76,"FLOWDN_RATE":"28.23","PROVINCE_NAME":"云南省","FLOW_DN":570.59,"FLOW":939.35,"COUNTNUM":46},{"FLOW_PRE":1.89,"FLOW_RATE":"43.81","FLOWUP_RATE":"17.86","PROVINCE_ID":210000,"FLOW_UP":360.99,"FLOWDN_RATE":"25.95","PROVINCE_NAME":"辽宁省","FLOW_DN":524.66,"FLOW":885.65,"COUNTNUM":46},{"FLOW_PRE":1.87,"FLOW_RATE":"43.45","FLOWUP_RATE":"19.06","PROVINCE_ID":230000,"FLOW_UP":385.24,"FLOWDN_RATE":"24.40","PROVINCE_NAME":"黑龙江省","FLOW_DN":493.16,"FLOW":878.40,"COUNTNUM":46},{"FLOW_PRE":1.85,"FLOW_RATE":"43.02","FLOWUP_RATE":"17.08","PROVINCE_ID":450000,"FLOW_UP":345.26,"FLOWDN_RATE":"25.94","PROVINCE_NAME":"广西壮族自治区","FLOW_DN":524.29,"FLOW":869.56,"COUNTNUM":46},{"FLOW_PRE":1.81,"FLOW_RATE":"41.91","FLOWUP_RATE":"17.36","PROVINCE_ID":100000,"FLOW_UP":350.86,"FLOWDN_RATE":"24.55","PROVINCE_NAME":"国内其他","FLOW_DN":496.33,"FLOW":847.19,"COUNTNUM":46},{"FLOW_PRE":1.68,"FLOW_RATE":"39.07","FLOWUP_RATE":"13.93","PROVINCE_ID":140000,"FLOW_UP":281.57,"FLOWDN_RATE":"25.14","PROVINCE_NAME":"山西省","FLOW_DN":508.27,"FLOW":789.83,"COUNTNUM":46},{"FLOW_PRE":1.54,"FLOW_RATE":"35.75","FLOWUP_RATE":"13.94","PROVINCE_ID":500000,"FLOW_UP":281.83,"FLOWDN_RATE":"21.80","PROVINCE_NAME":"重庆市","FLOW_DN":440.78,"FLOW":722.61,"COUNTNUM":46},{"FLOW_PRE":1.27,"FLOW_RATE":"29.48","FLOWUP_RATE":"12.53","PROVINCE_ID":220000,"FLOW_UP":253.35,"FLOWDN_RATE":"16.95","PROVINCE_NAME":"吉林省","FLOW_DN":342.55,"FLOW":595.90,"COUNTNUM":46},{"FLOW_PRE":1.24,"FLOW_RATE":"28.77","FLOWUP_RATE":"11.44","PROVINCE_ID":610000,"FLOW_UP":231.26,"FLOWDN_RATE":"17.33","PROVINCE_NAME":"陕西省","FLOW_DN":350.32,"FLOW":581.59,"COUNTNUM":46},{"FLOW_PRE":1.21,"FLOW_RATE":"27.96","FLOWUP_RATE":"14.56","PROVINCE_ID":620000,"FLOW_UP":294.34,"FLOWDN_RATE":"13.40","PROVINCE_NAME":"甘肃省","FLOW_DN":270.78,"FLOW":565.12,"COUNTNUM":46},{"FLOW_PRE":1.18,"FLOW_RATE":"27.26","FLOWUP_RATE":"12.04","PROVINCE_ID":150000,"FLOW_UP":243.36,"FLOWDN_RATE":"15.22","PROVINCE_NAME":"内蒙古自治区","FLOW_DN":307.61,"FLOW":550.97,"COUNTNUM":46},{"FLOW_PRE":1.02,"FLOW_RATE":"23.74","FLOWUP_RATE":"10.21","PROVINCE_ID":520000,"FLOW_UP":206.47,"FLOWDN_RATE":"13.53","PROVINCE_NAME":"贵州省","FLOW_DN":273.43,"FLOW":479.90,"COUNTNUM":46},{"FLOW_PRE":0.84,"FLOW_RATE":"19.41","FLOWUP_RATE":"8.18","PROVINCE_ID":120000,"FLOW_UP":165.28,"FLOWDN_RATE":"11.24","PROVINCE_NAME":"天津市","FLOW_DN":227.14,"FLOW":392.42,"COUNTNUM":46},{"FLOW_PRE":0.49,"FLOW_RATE":"11.35","FLOWUP_RATE":"4.36","PROVINCE_ID":460000,"FLOW_UP":88.19,"FLOWDN_RATE":"6.98","PROVINCE_NAME":"海南省","FLOW_DN":141.16,"FLOW":229.35,"COUNTNUM":46},{"FLOW_PRE":0.47,"FLOW_RATE":"10.90","FLOWUP_RATE":"4.57","PROVINCE_ID":650000,"FLOW_UP":92.30,"FLOWDN_RATE":"6.33","PROVINCE_NAME":"新疆维吾尔自治区","FLOW_DN":128.00,"FLOW":220.30,"COUNTNUM":46},{"FLOW_PRE":0.35,"FLOW_RATE":"8.06","FLOWUP_RATE":"3.10","PROVINCE_ID":640000,"FLOW_UP":62.66,"FLOWDN_RATE":"4.96","PROVINCE_NAME":"宁夏回族自治区","FLOW_DN":100.30,"FLOW":162.96,"COUNTNUM":46},{"FLOW_PRE":0.32,"FLOW_RATE":"7.51","FLOWUP_RATE":"2.24","PROVINCE_ID":630000,"FLOW_UP":45.25,"FLOWDN_RATE":"5.28","PROVINCE_NAME":"青海省","FLOW_DN":106.65,"FLOW":151.90,"COUNTNUM":46},{"FLOW_PRE":0.09,"FLOW_RATE":"2.12","FLOWUP_RATE":"0.91","PROVINCE_ID":540000,"FLOW_UP":18.42,"FLOWDN_RATE":"1.21","PROVINCE_NAME":"西藏自治区","FLOW_DN":24.41,"FLOW":42.83,"COUNTNUM":46},{"FLOW_PRE":0.07,"FLOW_RATE":"1.56","FLOWUP_RATE":"0.74","PROVINCE_ID":810000,"FLOW_UP":14.92,"FLOWDN_RATE":"0.82","PROVINCE_NAME":"香港特别行政区","FLOW_DN":16.67,"FLOW":31.59,"COUNTNUM":46},{"FLOW_PRE":0.06,"FLOW_RATE":"1.37","FLOWUP_RATE":"0.53","PROVINCE_ID":710000,"FLOW_UP":10.74,"FLOWDN_RATE":"0.84","PROVINCE_NAME":"台湾省","FLOW_DN":17.02,"FLOW":27.76,"COUNTNUM":46},{"FLOW_PRE":0.00,"FLOW_RATE":"0.11","FLOWUP_RATE":"0.05","PROVINCE_ID":820000,"FLOW_UP":0.92,"FLOWDN_RATE":"0.06","PROVINCE_NAME":"澳门特别行政区","FLOW_DN":1.25,"FLOW":2.16,"COUNTNUM":46}]}');
			if(data != null && data.rows != null){
				var resultData = data.rows;
				var tooltipStr = "";
				var seriePieShowData = []; 
				var serieMapShowData = [];
				var pieLegendData = [];
				
				var seriesPieFlowAllData = []; 
				var seriesPieFlowUpData = [];  
				var seriesPieFlowDnData = [];  
				
				var seriesMapFlowAllData = []; 
				var seriesMapFlowUpData = [];  
				var seriesMapFlowDnData = [];  
				$.each(resultData, function(i, item){
					/*
					if(i<10){
						pieLegendData.push( item['PROVINCE_NAME'] == null ?"":formatterProvinceName(item['PROVINCE_NAME']));
						seriesPieFlowAllData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW']});
						seriesPieFlowUpData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW_UP']});
						seriesPieFlowDnData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW_DN']});
					}else{
						if(i == 10){
							pieLegendData.push("其他地域");
							seriesPieFlowAllData.push({name:"其他地域",value:item['FLOW']});
							seriesPieFlowUpData.push({name:"其他地域",value:item['FLOW_UP']});
							seriesPieFlowDnData.push({name:"其他地域",value:item['FLOW_DN']});
						}else{
							seriesPieFlowAllData['其他地域'] += item['FLOW'];
							seriesPieFlowUpData['其他地域'] += item['FLOW_UP'];
							seriesPieFlowDnData['其他地域'] += item['FLOW_DN'];
						}
					}*/

					pieLegendData.push( item['PROVINCE_NAME'] == null ?"":formatterProvinceName(item['PROVINCE_NAME']));
					seriesPieFlowAllData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW']});
					seriesPieFlowUpData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW_UP']});
					seriesPieFlowDnData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW_DN']});
					
					
					seriesMapFlowAllData.push({name:item['PROVINCE_NAME'] == null?"": formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW']});
					seriesMapFlowUpData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW_UP']});
					seriesMapFlowDnData.push({name:item['PROVINCE_NAME'] == null?"":formatterProvinceName(item['PROVINCE_NAME']),value:item['FLOW_DN']});
				});
				pieLegendData.reverse();
				seriesPieFlowAllData.reverse();
				seriesPieFlowUpData.reverse();
				seriesPieFlowDnData.reverse();
				switch ($("input[name='Fruit']:checked").val()){
					case "FLOWALL":seriePieShowData  = seriesPieFlowAllData;serieMapShowData = seriesMapFlowAllData;tooltipStr = "总流量";break;
					case "FLOWUP":seriePieShowData  = seriesPieFlowUpData;serieMapShowData = seriesMapFlowUpData;tooltipStr = "上行流量";break;
					case "FLOWDN":seriePieShowData  = seriesPieFlowDnData;serieMapShowData = seriesMapFlowDnData;tooltipStr = "下行流量";break;
				}	
				
				option_1.tooltip.formatter = function(params, ticket, callback) { return ((params.value + "").match(/[0-9.]*/g)[0].length > 0?(params.name + "</br>" + tooltipStr +"：" + params.value):""); };
				option_1.series[0].data = serieMapShowData;
				//option_3.legend.data = pieLegendData;
				option_3.yAxis[0].data = pieLegendData;
				option_3.series[0].data = seriePieShowData;
				//option_3.title.subtext = tooltipStr;
				option_3.title.text = '地域流量排名：' + tooltipStr + '(GB)';
				//console.log(pieLegendData);
				
				//window.onresize = myChart_1.resize;
				myChart_1.setOption(option_1);
				myChart_1.resize();
				myChart_3.setOption(option_3);
				//window.onresize = myChart_3.resize
				myChart_3.resize();
				myChart_1.hideLoading();
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
		case "香港特别行政区":provinceName = "香港";break;
		case "澳门特别行政区":provinceName = "澳门";break;
	}
	return provinceName;
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
		pageSize : 5,
		pageList : [ 5, 10 ],
		showColumns : true,		
		divshow :true,
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
	myChart_4.hideLoading();
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



//statisticsObject.resize = resize;
statisticsObject.search = search;
statisticsObject.searchLayer = searchLayer;
statisticsObject.searchCharts = searchCharts;
statisticsObject.getAllNodes = getAllNodes;

// table相关函数end


//chart start
var myChart_1 = echarts.init(document.getElementById('chart_1'));
var myChart_3 = echarts.init(document.getElementById('chart_3'));
var myChart_4 = echarts.init(document.getElementById('chart_4'));

var option_1 = {
		title : {
			text : '',
			left : 'center',
			show : false
		},
		grid : {
			
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params, ticket, callback) {}
		},
		visualMap : {
			inRange : {
				color: ['#b2dbbf', '#2ec7c9']
			},
			left : 'center',
			top : 'top',
			orient : 'horizontal',
			text : [ '高 (GB)', '低 (GB)' ], // 文本，默认为数值文本
			calculable : true
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
		series : [ {
			name : '',
			type : 'map',
			left : '20',
			right: '20',
			itemStyle : {
				normal : {
					borderColor : '#fff',
					areaColor: '#e7e7e7'
				},
				emphasis : {
					areaColor  : '#07a2a4'
				}
			},
			layoutCenter: ['50%', '49%'],
			// 如果宽高比大于 1 则宽度为 100，如果小于 1 则高度为 100，保证了不超过 100x100 的区域
			layoutSize: 600,
			mapType : 'china',
			label : {
				normal : {
					show : true,
					textStyle:{
		                    color:'#247ba0'
		                }
				},
				emphasis : {
					textStyle:{
						color: '#fff'
					},
					show : true
				}
			},
			data : []
		} ]
	};
var option_3 = {
		title: {
            text: '',
            subtext: '',
            x: 'center',
            textStyle : {
            	color: '#428bca',
            	fontSize : 14
            }
        },
        color: ["#b6a2de", "#70c1b3", "#247ba0", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#5ab1ef", "#ffbf7a", "#07a2a4"],
        tooltip : {
			trigger: 'axis',
			formatter: "{b} : {c}",
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid: {
			left: '-15',
			right: '8%',
			bottom: '0',
			top:'50',
			containLabel: true
		},
        toolbox : {
			show : true,
			orient : 'horizontal',
			//left : 'right',
			right : '5',
			top : 'top',
			padding:1,  
			feature : {
				//dataView : {
				//	readOnly : false
				//},
				restore : {},
				saveAsImage : {}
			}
		},
		xAxis : [
			{
		    	splitLine:{  
		            show:true  ,
		            lineStyle : {
		            	color:'#e7e7e7'
		            }
		        },  
				type : 'value',
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
				type : 'category',
				data : []
			}
		],
        calculable: true,
        series: [{
            //name: '访问来源',
        	type:'bar',
			barWidth: '20',
            radius: '50%',
            center: ['50%', '50%'],
            data: [],
			label:{
				normal:{
					show: true,
					position: 'right'
				}
			}
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
	myChart_1.dispose();
	myChart_3.dispose();
	myChart_1 = echarts.init(document.getElementById('chart_1'));
	myChart_3 = echarts.init(document.getElementById('chart_3'));
	myChart_1.setOption(option_1,true);
	myChart_3.setOption(option_3,true);
	
	myChart_1.resize();
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