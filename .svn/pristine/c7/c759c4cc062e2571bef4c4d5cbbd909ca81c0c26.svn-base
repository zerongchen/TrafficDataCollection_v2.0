var statisticsObject = new Object();
(function(){
	$("#export").on("click", function(e){
		var tableColumnData = $('#appTable_up').bootstrapTable("getOptions").columns[0];
		var data = new Object();
		data.headers = new Array();
		data.fields = new Array();		
		data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
		$.each(tableColumnData, function(idx, item){
			if(item.visible == true && item.field!='trendAnalysis' && item.field != 'index'){
				data.headers.push(item.title);
				data.fields.push(item.field);
			}
		});
		$.download({url:exportTrendUrl, data:data});
	});
	function operateFormatter(value, row, index) {
        return '<a class="show_view trendAnalysis" style="cursor:pointer;">查看</a>';
    }
	window.operateEvents = {
	    'click .trendAnalysis': function (e, value, row, index) {
	    	e.preventDefault();
			//$.modal.alert("PROVINCE_ID : " + row.PROVINCE);
			$("#export_layer").hide();
			$("input[name='Fruit1']").get(0).checked = true;   
			$("#chart_layer_option").prop('selectedIndex',0);
			$('#chart_layer').show();
		    $('#div_layer_list').hide();
		    statisticsObject.searchLayer(row.PROVINCE);
			layer.open({
				title: '趋势分析',
		        type: 1,
		        area: ['1200px', '700px'],
		        shadeClose: true, //点击遮罩关闭
		        content: $('#div_layer')
		    });
	    }
	};
	function search() {
		mychart1.showLoading();
		var tableColumnData = getTableColumnData(getTrendTableColumnDataUrl);
		var bootstrapTableOption = {
			method : 'post',
			url : initUpTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			height : 400,
			striped : true,
			pagination : false,
			showColumns : false,
			sidePagination : 'server',
			minimumCountColumns : 1,
			clickToSelect : true,
			paginationFirstText : "首页",
			paginationPreText : '上一页',
			paginationNextText : '下一页',
			paginationLastText : '最后一页',
			columns : tableColumnData
		};
		if($("#queryFieldName_1").val()=="SERVERBUILD_ID"){
			tableColumnData.splice(1,0,{field: 'TOTAL',title: '节点',width: 80,align: 'center',valign: 'middle', events: operateEvents, switchable:false});
		}else if($("#queryFieldName_1").val()=="SERVERROOM_ID"){
			tableColumnData.splice(1,0,{field: 'SERVERBUILD_ID',title: '机楼',width: 80,align: 'center',valign: 'middle', events: operateEvents, switchable:false});
		}else if($("#queryFieldName_1").val()=="CLASSID" && $("#queryClassId_1").val() == ""){
			tableColumnData.splice(1,0,{field: 'SERVERROOM_ID',title: '机房',width: 80,align: 'center',valign: 'middle', events: operateEvents, switchable:false});
		}else if($("#queryFieldName_1").val()=="CLASSID"){
			tableColumnData.splice(1,0,{field: 'CLASSID',title: '业务',width: 80,align: 'center',valign: 'middle', events: operateEvents, switchable:false});
		}
		$('#appTable_up').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked, updateColumnUserUrl);
		}).on("load-success.bs.table", function(e, field, checked){
			//获取table data
			var resultData = $('#appTable_up').bootstrapTable("getData");
			//按table column分组数据
			var chartObject = new Object();
			$.each(resultData, function(idx, item){
				$.each(item, function(iidx, iitem){
					if( chartObject[iidx] == null ){
						chartObject[iidx] = new Object();
					}
					if(chartObject[iidx].xAxisData == null){
						chartObject[iidx].xAxisData = new Array();
					}
					chartObject[iidx].xAxisData.push(item.R_STATTIME);
					if(chartObject[iidx].seriesData == null){
						chartObject[iidx].seriesData = new Array();
					}
					chartObject[iidx].seriesData.push(iitem);
				});
			});
			//拼装18个指标的option
			var chartLeftOption = "";
			var paramName = "";
			var validOption = ["QUALITY_SCORE", "FLOW_UP", "FLOW_DN", "RESPONSE_DELAY", "SERVER_DELAY", "CLIENT_DELAY", "CONNECT_COUNT", "SUCCESSCONNECT_COUNT",
			                   "CONNECTTIMEOUT_COUNT", "RESPFAIL_COUNT", "SUCCESSCONNECT_RATE", "FAILCONNECT_RATE", "CONNECTTIMEOUT_RATE", "RETRANSPACKAGE_UP_RATE", 
			                   "RETRANSPACKAGE_DN_RATE", "UP_SESSION_RATE", "DN_SESSION_RATE", "MIDPACKAGEFLOW_DN_RATE", "BIGPACKAGEFLOW_DN_RATE"];
			var tableColumnData_i = 0;
			option1.xAxis.data = new Array();
			option1.series[0].data = new Array();
			option1.series[0].name = '';
			$.each(tableColumnData, function(idx, item){
				if(validOption.indexOf(item.field) >= 0){
					//按第一个选项初始化图表
					if(tableColumnData_i == 0){
						$.each(chartObject, function(iidx, iitem){
							if(iidx == item.field){
								if(iidx == 'QUALITY_SCORE'){
									option1.yAxis.max = 100;
									option1.yAxis.min = 0;
								}else{
									option1.yAxis.max = 'auto';
									option1.yAxis.min = 'auto';
								}
								option1.xAxis.data = iitem.xAxisData;
								option1.series[0].data = iitem.seriesData;
								option1.series[0].name = item.title;
							}
						})
					}
					//拼option
					chartLeftOption = chartLeftOption + "<option value='"+item.field+"'>"+item.title+"</option>";
					tableColumnData_i++;
				}
			});
			mychart1.clear();
			mychart1.dispose();
			mychart1 = echarts.init(document.getElementById('chart_a'));
			mychart1.setOption(option1);
			mychart1.hideLoading();
			
			$("#chartleftoption").off("change");
			$("#chartleftoption").empty();
			$("#chartleftoption").append(chartLeftOption).on("change", function(e){
				mychart1.showLoading();
				if($(this).val() == 'QUALITY_SCORE'){
					option1.yAxis.max = 100;
					option1.yAxis.min = 0;
				}else{
					option1.yAxis.max = 'auto';
					option1.yAxis.min = 'auto';
				}
				var selectedField = $(this).find("option:selected").prop("value");
				var selectedFieldName = $(this).find("option:selected").html();
				console.log(chartObject);
				$.each(chartObject, function(idx, item){
					if(idx == selectedField){
						option1.xAxis.data = item.xAxisData;
						option1.series[0].data = item.seriesData;
						option1.series[0].name = selectedFieldName;
					}
				});
				mychart1.clear();
				mychart1.dispose();
				mychart1 = echarts.init(document.getElementById('chart_a'));
				mychart1.setOption(option1);
				mychart1.hideLoading();
			});
		});
	}
	var mychart1 = echarts.init(document.getElementById('chart_a'));

	var option1 = {
			title: {
				text: '机房质量-流量趋势',
				show: false
			},
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			color: ['#2ec7c9'],
			grid: {
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
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
			toolbox: {
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
			series: [
				{
					name:'',
					type:'line',
					barMaxWidth:'38',
					data:[]
				}
			]
		};
		mychart1.setOption(option1,true);
	//chart end
		statisticsObject.refreshTable = function(){
		$('#appTable_up').bootstrapTable("refresh", {silent: true});
	};
	statisticsObject.refreshChart = function(){
		mychart1.dispose();
	    mychart1 = echarts.init(document.getElementById('chart_a'));
		mychart1.setOption(option1,true);
	}
	statisticsObject.search = search;
})();
