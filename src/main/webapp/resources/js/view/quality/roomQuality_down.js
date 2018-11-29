var trendObject = new Object();
(function(){
	$("#export1").on("click", function(e){
		var tableColumnData = $('#appTable_dn').bootstrapTable("getOptions").columns[0];
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
		$.download({url:exportStatisticUrl, data:data});
	});
	function search() {
	    var tableColumnData = getTableColumnData(getTableColumnDataUrl);
		var bootstrapTableOption = {
			method : 'post',
			url : initDnTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
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
			tableColumnData.splice(1,0,{field: 'SERVERBUILD_ID',title: '节点',width: 80,align: 'center',valign: 'middle', events: operateEvents, switchable:false});
		}else if($("#queryFieldName_1").val()=="SERVERROOM_ID"){
			tableColumnData.splice(1,0,{field: 'SERVERROOM_ID',title: '节点',width: 80,align: 'center',valign: 'middle', events: operateEvents, switchable:false});
		}else if($("#queryFieldName_1").val()=="CLASSID"){
			tableColumnData.splice(1,0,{field: 'CLASSID',title: '节点',width: 80,align: 'center',valign: 'middle', events: operateEvents, switchable:false});
		}
		
		$('#appTable_dn').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked,updateTrendColumnUserUrl);
		}).on("load-success.bs.table", function(e, field, checked){
			//获取table data
			var resultData = $('#appTable_dn').bootstrapTable("getData");
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
								option1.xAxis[0].data = chartObject.SERVERBUILD_ID.seriesData;
								//option1.xAxis[1].data = chartObject.SERVERBUILD_ID.seriesData;
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
			mychart1 = echarts.init(document.getElementById('chart_add_dn'));
			mychart1.setOption(option1);
			mychart1.hideLoading();
			
			$("#chartleftoption_1").off("change");
			$("#chartleftoption_1").empty();
			$("#chartleftoption_1").append(chartLeftOption).on("change", function(e){
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
				option1.xAxis[0].data = chartObject.SERVERBUILD_ID.seriesData;
				option1.xAxis[1].data = chartObject.SERVERBUILD_ID.seriesData;
				$.each(chartObject, function(idx, item){
					if(idx == selectedField){
						//option1.xAxis.data = item.xAxisData;
						option1.series[0].data = item.seriesData;
						option1.series[0].name = selectedFieldName;
					}
				});
				mychart1.clear();
				mychart1.dispose();
				mychart1 = echarts.init(document.getElementById('chart_add_dn'));
				mychart1.setOption(option1);
				mychart1.hideLoading();
			});
		});
	}
		
var mychart1 = echarts.init(document.getElementById('chart_add_dn'));

	var option1 = {
			title: {
				text: '机房质量-统计',
				show: false
			},
			  tooltip : {
			        trigger: 'axis'
			    },
			    
			    toolbox: {
			        show : true,
			        feature : {
			           
			            saveAsImage : {show: true}
			        }
			    },
			   
			    xAxis : [
			        {
			            type : 'category',
			            data : []
			        },
			        {
			            type : 'category',
			            axisLine: {show:false},
			            axisTick: {show:false},
			            axisLabel: {show:false},
			            splitArea: {show:false},
			            splitLine: {show:false},
			            data : []
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel:{formatter:'{value}'}
			        }
			    ],
			    series : [
			        {
			        	  type:'bar',
			              barWidth : 30,//柱图宽度
			               itemStyle: {normal: {color:'rgba(193,35,43,1)', label:{show:true,position: 'top'}}},

			            data:[]
			        }
			    ]
			};
		
		mychart1.setOption(option1,true);
		trendObject.refreshChart = function(){
			mychart1.dispose();
		    mychart1 = echarts.init(document.getElementById('chart_add_dn'));
			mychart1.setOption(option1,true);
		}
		trendObject.search = search;
})();
