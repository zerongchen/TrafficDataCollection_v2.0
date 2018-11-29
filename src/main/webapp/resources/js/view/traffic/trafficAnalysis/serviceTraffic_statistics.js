var statisticsObject = new Object();

(function(){
	$("#export_statistic").on("click", function(e){
//		tableColumnData = $('#statisticalTable').bootstrapTable("getOptions").columns;
		//console.log(tableColumnData);
		getTableColumnData(getStatisticsTableColumnDataUrl);
		var data = new Object();
		data.headers = new Array();
		data.fields = new Array();		
		data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
		$.each(tableColumnData, function(idx, item){
			if(item.visible == true){
				data.headers.push(item.title);
				data.fields.push(item.field);
			}
		});
		$.download({url:exportStatisticUrl, data:data});
//		console.log(data);
	});
	function operateFormatter(value, row, index) {
        return '<a class="show_view trendAnalysis">查看</a>';
    }
	window.operateEvents = {
	    'click .trendAnalysis': function (e, value, row, index) {
	    	e.preventDefault();
	    	$("#export_layer").hide();
			$("input[name='Fruit1']").get(0).checked = true;   
			$('#chart_layer').show();
		    $('#div_layer_list').hide();
		    statisticsObject.searchLayer(row.CATALOGID);
			layer.open({
				title: '趋势分析',
		        type: 1,
		        area: ['1100px', '625px'],
		        shadeClose: true, //点击遮罩关闭
		        content: $('#div_layer')
		    });
	    }
	};
	
	function search() {
		getTableColumnData(getStatisticsTableColumnDataUrl);
		tableColumnData.unshift({field: 'index', title: '序号',width: 80,align: 'center',formatter: function(value, row, index){var page = $('#appTable_statistical').bootstrapTable('getPage'); return page.pageSize*(page.pageNumber-1)+ index+1;}, switchable:false});
		tableColumnData.splice(3,0,{field: 'trendAnalysis',title: '趋势分析',width: 80,align: 'center',valign: 'middle',formatter: operateFormatter, events: operateEvents, switchable:false});
		var bootstrapTableOption = {
			method : 'post',
			url : initStatisticsTableUrl,
			queryParams : pageQueryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			pagination : true,
			pageSize : 10,
//			pageList : [ 10, 25 ],
			showColumns : true,
			sidePagination : 'server',
			minimumCountColumns : 1,
			clickToSelect : true,
			paginationFirstText : "首页",
			paginationPreText : '上一页',
			paginationNextText : '下一页',
			paginationLastText : '最后一页',
			columns : tableColumnData
		};
		$('#statisticalTable').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked, updateStatisticsColumnUserUrl);
		});
	};
	
	function pageQueryParams(params) {
		var data = $('#searchForm').formToJSON();
		data.offset = params.offset;
		data.limit =  params.limit;
		if(params.sort == null){
			data.sort = $("#hid_sort").val();
		}else{
			data.sort = params.sort;
		}
		if( params.order == "desc")
			data.order = "asc";
		else
			data.order = "desc";
		return data;
	}
	
	function searchcharts() {
		myChart1.showLoading();
		var params = $('#searchForm').formToJSON();
		params.offset = params.offset;
		params.limit =  params.limit;
		params.sort = $("#hid_sort").val();
		params.order = "desc";
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : initStatisticsTableUrl,
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				if(data != null && data.rows != null){
					var resultData = data.rows;
					var xAxisFlowData = [];
					var tooltipStr = "";
					var serieShowData = []; 
					var seriesFlowAllData = []; 
					var seriesFlowUpData = [];  
					var seriesFlowDnData = [];  
					$.each(resultData, function(i, item){
						xAxisFlowData.push(item['CATALOGNAME']);
						seriesFlowAllData.push({name:item['CATALOGNAME'],value:item['FLOW']});
						seriesFlowUpData.push({name:item['CATALOGNAME'],value:item['FLOW_UP']});
						seriesFlowDnData.push({name:item['CATALOGNAME'],value:item['FLOW_DN']});
					});
					switch ($("input[name='Fruit']:checked").val()){
						case "FLOWALL":serieShowData  = seriesFlowAllData;tooltipStr = "总流量";break;
						case "FLOWUP":serieShowData  = seriesFlowUpData;tooltipStr = "上行流量";break;
						case "FLOWDN":serieShowData  = seriesFlowDnData;tooltipStr = "下行流量";break;
					}
					option1.xAxis[0].data = xAxisFlowData;
					option1.series[0].data = serieShowData;
					option1.title.text = tooltipStr;
					myChart1.setOption(option1);
					myChart1.resize();
					myChart1.hideLoading();
				}
			}
		});
	};
		
	//chart start
	var myChart1 = echarts.init(document.getElementById('chartStatistics'));
	//业务流量统计柱状
	var option1 = {
			legend: {
		        //data: ['TOP10应用层协议流量（GB）']
				show : false
		    },
			title: {
				text: '',
				x:'center',
				show : false
			},
			color: ['#70c1b3'],
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},
		    toolbox: {
		        show : true,
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
					type : 'category',
					axisLabel: {
						 interval:0,
						rotate: 8,
						},
					data : [],
					axisTick: {
						alignWithLabel: true
					}
				}
			],
			yAxis : [
				{
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
	myChart1.setOption(option1,true);
	myChart1.resize();
	//chart end
	statisticsObject.search = search;
	statisticsObject.searchcharts = searchcharts;
	statisticsObject.refreshTable = function(){
		$('#statisticalTable').bootstrapTable("refresh", {silent: true});
	};
	statisticsObject.refreshChart = function(){
		myChart1.dispose();
		myChart1 = echarts.init(document.getElementById('chartStatistics'));
		myChart1.setOption(option1,true);
		myChart1.resize();
	}
})();