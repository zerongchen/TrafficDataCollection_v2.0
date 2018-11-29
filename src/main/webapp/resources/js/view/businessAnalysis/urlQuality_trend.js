var trendObject = new Object();
(function(){
	$("#export_trend").on("click", function(e){
		tableColumnData = $('#appTable_trend').bootstrapTable("getOptions").columns;
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
        return '全部';
    }
	function search() {
		mychart1.showLoading();
		getTableColumnData(getTrendTableColumnDataUrl);
		var bootstrapTableOption = {
			method : 'post',
			url : initTrendTableUrl,
			/*queryParams : queryParams,*/
			queryParams:function(params){
				var data = $('#searchForm').formToJSON();
				data.offset = params.offset;
				data.limit = params.limit;
				data.sort = params.sort;
				data.order = params.order;
				data.isPaging = 0;
				return data;
			},
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			pagination : false,
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
		$('#appTable_trend').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked,updateTrendColumnUserUrl);
		}).on("load-success.bs.table", function(e, field, checked){
			option1.xAxis.data = [];
			option1.series[0].data = [];
			option1.series[0].name = "";
			option1.title.text = "";
			
			var resultData = $('#appTable_trend').bootstrapTable("getData");
			var obj = new Object();
			obj.xAxis = new Array();
			obj.series = new Object();
			$.each(resultData, function(idx, item){
				obj.xAxis.push(item.R_STATTIME);
				$.each(item, function(iidx, iitem){
					if(obj.series[iidx] == undefined){
						obj.series[iidx] = new Array();
					}
					obj.series[iidx].push(iitem);
				});
			});
			
			var chartDOption = "";
			var validOption = ["QUALITY_SCORE", "FLOW_UP", "FLOW_DN", "RESPONSE_DELAY", "SERVER_DELAY", "CLIENT_DELAY", "CONNECT_COUNT", "SUCCESSCONNECT_COUNT",
			                   "CONNECTTIMEOUT_COUNT", "RESPFAIL_COUNT", "SUCCESSCONNECT_RATE", "FAILCONNECT_RATE", "CONNECTTIMEOUT_RATE", "RETRANSPACKAGE_UP_RATE", 
			                   "RETRANSPACKAGE_DN_RATE", "UP_SESSION_RATE", "DN_SESSION_RATE", "MIDPACKAGEFLOW_DN_RATE", "BIGPACKAGEFLOW_DN_RATE"];
			var tableColumnData_i = 0;
			$.each(tableColumnData, function(idx, item){
				if(validOption.indexOf(item.field) >= 0){
					if(tableColumnData_i == 0){
						$.each(obj.series, function(iidx, iitem){
							if(iidx == item.field){
								option1.xAxis.data = obj.xAxis;
								option1.series[0].data = iitem;
								option1.series[0].name = item.title;
								option1.title.text = item.title;
								return false;
							}
						});
					}
					chartDOption = chartDOption + "<option value='"+item.field+"'>"+item.title+"</option>";
					tableColumnData_i++;
				}
			});
			$("#chartdoption").html("");
			$("#chartdoption").append(chartDOption).on("change", function(e){
				mychart1.showLoading();
				mychart1.clear();
				option1.series[0].data = obj.series[$(this).find("option:selected").prop("value")];
				var paramName = $(this).find("option:selected").html();
				option1.xAxis.data = obj.xAxis;
				option1.series[0].name = paramName;
				option1.title.text = paramName;
				mychart1.setOption(option1);
				mychart1.hideLoading();
			});
			mychart1.clear();
			mychart1.setOption(option1);
			mychart1.hideLoading();
		});
	}
	
	
	//chart start
    var mychart1 = echarts.init(document.getElementById('chart_d'));
    var option1 = {
    	    title: {
    	        text: '',
    	        show: false
    	    },
    	    tooltip : {
    			trigger: 'axis',
    			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    			}
    		},
    	    color: ["#ffbf7a"],
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
	    	    series: [{
	    	    	name:'',
					type:'line',	
					barMaxWidth:'38',
					data:[]
	        } ]
	    	};

    mychart1.setOption(option1);
	//chart end
	trendObject.search = search;
	trendObject.refreshTable = function(){
		$('#appTable_trend').bootstrapTable("refresh", {silent: true});
	};
	trendObject.refreshChart = function(){
		mychart1.dispose();
	    mychart1 = echarts.init(document.getElementById('chart_d'));
		mychart1.setOption(option1,true);
	}
})();
