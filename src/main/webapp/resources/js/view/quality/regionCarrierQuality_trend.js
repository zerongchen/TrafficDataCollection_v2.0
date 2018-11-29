var trendObject = new Object();
(function(){
	$("#export_trend").on("click", function(e){
		tableColumnData[0] = $('#trendTable').bootstrapTable("getOptions").columns;
//		getTableColumnData(getTableColumnDataUrl);
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
		$.download({url:exportTrendUrl, data:data});
	});
	function search() {
		myChartTrend.showLoading();
		getTableColumnData(getTableColumnDataUrl);
		var bootstrapTableOption = {
			method : 'post',
			url : initTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			pagination : false,
			sortable: false,
			pageSize : 10,
//			pageList : [ 10 ],
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
		$('#trendTable').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked, updateColumnUserUrl);
		}).on("load-success.bs.table", function(e, field, checked){
			option1.xAxis.data = [];
			option1.series[0].data = [];
			option1.series[0].name = "";
			option1.title.text = "";
			var resultData = $('#trendTable').bootstrapTable("getData");
			var obj = new Object();
			obj.xAxis = new Array();
			obj.series = new Object();
			$.each(resultData, function(idx, item){
//				var date = new Date();
//				date.setTime(parseInt(item.R_STATTIME)*1000);
//				obj.xAxis.push(date.format("yyyy-MM-dd hh:mm"));
				obj.xAxis.push(item.R_STATTIME);
				option1.xAxis[0].data = obj.xAxis;
				$.each(item, function(iidx, iitem){
					if(obj.series[iidx] == undefined){
						obj.series[iidx] = new Array();
					}
					obj.series[iidx].push(iitem);
				});
				
			});
			//console.log(option1);
			
			var chartLeftOption = "";
			var paramName = "";
			var validOption = ["QUALITY_SCORE", "FLOW_UP", "FLOW_DN", "RESPONSE_DELAY", "SERVER_DELAY", "CLIENT_DELAY", "CONNECT_COUNT", "SUCCESSCONNECT_COUNT",
			                   "CONNECTTIMEOUT_COUNT", "RESPFAIL_COUNT", "SUCCESSCONNECT_RATE", "FAILCONNECT_RATE", "CONNECTTIMEOUT_RATE", "RETRANSPACKAGE_UP_RATE", 
			                   "RETRANSPACKAGE_DN_RATE", "UP_SESSION_RATE", "DN_SESSION_RATE", "MIDPACKAGEFLOW_DN_RATE", "BIGPACKAGEFLOW_DN_RATE"];
			var tableColumnData_i = 0;
			$.each(tableColumnData, function(idx, item){
					if(validOption.indexOf(item.field) >= 0){
						if(tableColumnData_i == 0){
							$.each(obj.series, function(iidx, iitem){
								if(iidx == item.field){
									
									option1.series[0].data = iitem;
									option1.series[0].name = item.title;
									option1.title.text = item.title;
									return false;
								}
							});
						}
					chartLeftOption = chartLeftOption + "<option value='"+item.field+"'>"+item.title+"</option>";
					tableColumnData_i++;
				}
			});
			
				myChartTrend.setOption(option1, true);
			//指标选择
			$("#chartTrendOption").empty().append(chartLeftOption).on("change", function(e){
				myChartTrend.showLoading();
				myChartTrend.clear();
				option1.series[0].data = obj.series[$(this).find("option:selected").prop("value")];
				var paramName = $(this).find("option:selected").html();
				option1.xAxis[0].data = obj.xAxis;
				console.log(paramName);
				option1.series[0].name = paramName;
				option1.title.text = paramName;
				myChartTrend.setOption(option1);
				myChartTrend.hideLoading();
			});
			myChartTrend.clear();
			myChartTrend.setOption(option1);

			console.log(option1);
			myChartTrend.hideLoading();
			});
	}
	
	
	//chart start
	var myChartTrend = echarts.init(document.getElementById('chartTrend'));
    option1 = {
    		title: {
    	        text: '',
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
    	    toolbox: {
    	        feature : {
    	            mark : {show: true},
    	            //dataView : {show: true, readOnly: false},
    	            magicType : {show: true, type: ['line', 'bar']},
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    xAxis: [{
    	    	splitLine:{  
    	            show:true  ,
    	            lineStyle : {
    	            	color:'#e7e7e7'
    	            }
    	        },  
    	        type: 'category',
    	        boundaryGap: false,
    	        data: []
    	    }],
    	    yAxis: [{
    	    	splitLine:{  
    	            show:true  ,
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
	        	name:'',
				type:'line',	
				barMaxWidth:'38',
				data:[]
	        }]
    };
    myChartTrend.setOption(option1);
	//chart end
    
	trendObject.search = search;
	trendObject.refreshTable = function(){
		$('#trendTable').bootstrapTable("refresh", {silent: true});
	};
	trendObject.refreshChart = function(){
		myChartTrend.dispose();
		myChartTrend = echarts.init(document.getElementById('chartTrend'));
		myChartTrend.setOption(option1,true);
	}
})();
