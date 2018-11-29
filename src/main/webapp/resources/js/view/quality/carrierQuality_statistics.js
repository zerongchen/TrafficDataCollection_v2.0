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
        return '<a class="show_view trendAnalysis" style="cursor:pointer;">查看</a>';
    }
	window.operateEvents = {
	    'click .trendAnalysis': function (e, value, row, index) {
	    	e.preventDefault();
	    	$("#export_layer").hide();
			$("input[name='Fruit1']").get(0).checked = true;   
			$("#chart_layer_option").prop('selectedIndex',0);
			$('#chart_layer').show();
		    $('#div_layer_list').hide();
		    statisticsObject.searchLayer(row.CARRIER);
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
		mychart2.showLoading();
		getTableColumnData(getStatisticsTableColumnDataUrl);
		tableColumnData.unshift({field: 'index', title: '序号',width: 80,align: 'center',formatter: function(value, row, index){return index+1;}, switchable:false});
		tableColumnData.splice(3,0,{field: 'trendAnalysis',title: '趋势分析',width: 80,align: 'center',valign: 'middle',formatter: operateFormatter, events: operateEvents, switchable:false});
		var bootstrapTableOption = {
			method : 'post',
			url : initStatisticsTableUrl,
			queryParams : queryParams,
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
		}).on("load-success.bs.table", function(e, field, checked){
			option2.title.text = "";
			option2.xAxis[0].data = [];
			option2.series[0].data = [];
			option2.series[0].name = [];
			var resultData = $('#statisticalTable').bootstrapTable("getData");
			var objRight = new Object();//右图表的数据
			$.each(resultData, function(idx, item){
				if(item['CARRIER_ID'] == undefined) return true;
				$.each(item, function(iidx, iitem){
					if(objRight[iidx] == undefined){
						objRight[iidx] = new Object();
					}
					if(objRight[iidx]["xAxisData"] == undefined){
						objRight[iidx]["xAxisData"] = new Array();
					}
					if(objRight[iidx]["seriesData"] == undefined){
						objRight[iidx]["seriesData"] = new Array();
					}
					objRight[iidx]["xAxisData"].push(item['CARRIER_ID']);
					objRight[iidx]["seriesData"].push(iitem);
//					console.log(iitem);
				});
//				console.log(item);
				
			});
			var chartLeftOption = "";
			var paramName = "";
			var validOption = ["QUALITY_SCORE", "FLOW_UP", "FLOW_DN", "RESPONSE_DELAY", "SERVER_DELAY", "CLIENT_DELAY", "CONNECT_COUNT", "SUCCESSCONNECT_COUNT",
			                   "CONNECTTIMEOUT_COUNT", "RESPFAIL_COUNT", "SUCCESSCONNECT_RATE", "FAILCONNECT_RATE", "CONNECTTIMEOUT_RATE", "RETRANSPACKAGE_UP_RATE", 
			                   "RETRANSPACKAGE_DN_RATE", "UP_SESSION_RATE", "DN_SESSION_RATE", "MIDPACKAGEFLOW_DN_RATE", "BIGPACKAGEFLOW_DN_RATE"];
			var tableColumnData_i = 0;
			statisticsObject.objRight = objRight;
			$.each(tableColumnData, function(idx, item){
				if(validOption.indexOf(item.field) >= 0){
					if(tableColumnData_i == 0){
						$.each(objRight, function(iidx, iitem){
							if(iidx == item.field){
								paramName = item.title;
								return false;
							}
						});
						$.each(objRight, function(iidx, iitem){
							if(iidx == item.field){
								option2.title.text = item.title;
								var sortedData = sort2(iitem.xAxisData, iitem.seriesData,
										10, $("#chartOrder input:radio:checked").val());
								option2.xAxis[0].data = sortedData.xAxisData;
								option2.series[0].data = sortedData.seriesData;
								option2.series[0].name = item.title;
								return false;
							}
						});
					}
					chartLeftOption = chartLeftOption + "<option value='"+item.field+"'>"+item.title+"</option>";
					tableColumnData_i++;
				}
			});
			mychart2.clear();
			mychart2.setOption(option2, true);
			mychart2.resize();
			$("#chartleftoption").empty().append(chartLeftOption).on("change", function(e){
				mychart2.showLoading();
				mychart2.clear();
				var paramName = $(this).find("option:selected").html();
				option2.title.text = $(this).find("option:selected").html();
				var sortedData = new Object();
				if(objRight[$(this).find("option:selected").prop("value")]!=undefined){
					sortedData = sort2(objRight[$(this).find("option:selected").prop("value")].xAxisData,
							objRight[$(this).find("option:selected").prop("value")].seriesData,
							10, $("#chartOrder input:radio:checked").val());
					option2.xAxis[0].data = sortedData.xAxisData;
					option2.series[0].data = sortedData.seriesData;
				}else{
					option2.xAxis[0].data = new Array();
					option2.series[0].data = new Array();
				}
				option2.series[0].name = $(this).find("option:selected").html();
				
				mychart2.setOption(option2, true);
				mychart2.resize();
				mychart2.hideLoading();
			});
		
			$("#chartOrder input").off("click");
			$("#chartOrder input").on("click", function(e){
				mychart2.showLoading();
				mychart2.clear();
				$("#chartOrder input").prop("checked", false);
				$(this).prop("checked", true);
				var sortedData = sort2(objRight[$("#chartleftoption").find("option:selected").prop("value")].xAxisData,
						objRight[$("#chartleftoption").find("option:selected").prop("value")].seriesData,
						10, $(this).val());
				option2.xAxis[0].data = sortedData.xAxisData;
				option2.series[0].data = sortedData.seriesData;
				mychart2.setOption(option2, true);
				mychart2.resize();
				mychart2.hideLoading();
			});
			mychart2.hideLoading();
		});
	}
	
		
	//chart start
	var mychart2 = echarts.init(document.getElementById('chartStatistics'));
	//质量统计柱状
	var option2 = {
			title: {
				text: '',
				show: false      
			},
			color: ['#ffbf7a'],
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			toolbox: {
				feature: {
					saveAsImage: {}
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
					barWidth: '38',
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
	mychart2.setOption(option2,true);
	mychart2.resize();
	//chart end
	statisticsObject.search = search;
	statisticsObject.getAllNodes=getAllNodes;
	statisticsObject.refreshTable = function(){
		$('#statisticalTable').bootstrapTable("refresh", {silent: true});
	};
	statisticsObject.refreshChart = function(){
		mychart2.dispose();
		mychart2 = echarts.init(document.getElementById('chartStatistics'));
		mychart2.setOption(option2,true);
		mychart2.resize();
	}
})();