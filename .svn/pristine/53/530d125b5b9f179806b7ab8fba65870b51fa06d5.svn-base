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
	var pageIndex = 0;
	$("#export_statistic").on("click", function(e){
		var tableColumnData = $('#appTable_statistical').bootstrapTable("getOptions").columns[0];
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
	function operateFormatter(value, row, index) {
        return '<a class="show_view trendAnalysis" style="cursor:pointer;">查看</a>';
    }
	function classFormatter(value, row, index){
		var tableColumnData = $('#appTable_statistical').bootstrapTable("getOptions").columns;
		if(row[tableColumnData[0][3].field] != 'PAGENAME' && row[tableColumnData[0][3].field] != 'MODULENAME'){
			return '<a class="show_view classOper" style="cursor:pointer;">'+row[tableColumnData[0][3].field]+'</a>';
		}
	}
	window.operateEvents = {
		    'click .trendAnalysis': function (e, value, row, index) {		    	
		    	e.preventDefault();
		    	var tableColumnData = $('#appTable_statistical').bootstrapTable("getOptions").columns;
				//$.modal.alert("PROVINCE_ID : " + row.PROVINCE);
				$("#export_layer").hide();
				$("input[name='Fruit1']").get(0).checked = true;   
				$("#chart_layer_option").prop('selectedIndex',0);
				$('.time_search').show();
				$('#chart_layer').show();
			    $('#div_layer_list').hide();
			    //$("#catalog").val(row.CATALOGID);
			    //$("#catalog").val(row.BIZ_ID);
			    //alert(row.BIZ_ID);
    			statisticsObject.searchLayer(tableColumnData[0][3],row.BIZ_ID);
				layer.open({
					title: '趋势分析',
			        type: 1,
			        area: ['1200px', '700px'],
			        shadeClose: true, //点击遮罩关闭
			        content: $('#div_layer')
			    });
		    },
		    'click .classOper': function (e, value, row, index) {
		    	e.preventDefault();
		    	var tableColumnData = $('#appTable_statistical').bootstrapTable("getOptions").columns;
		    	var urlparam = '';
		    	if(tableColumnData[0][3].field == 'CATALOGNAME'){
		    		urlparam = "queryFieldName=CLASSID&&CATALOGID="+row.CATALOGID;
		    	}else if(tableColumnData[0][3].field == 'CLASSNAME'){
		    		urlparam = "queryFieldName=PRODUCTID&&CATALOGID="+row.CATALOGID+"&&CLASSID="
	    			+row.CLASSID;
		    	}else if(tableColumnData[0][3].field == 'PRODUCTNAME'){
		    		urlparam = "queryFieldName=MODULEID&&CATALOGID="+row.CATALOGID+"&&CLASSID="
	    			+row.CLASSID+"&&PRODUCTID="+row.PRODUCTID;
		    	}
		    	window.open(pageurl+"?"+urlparam);
		    }
		};
	function search() {
	//	myChart_a.showLoading();
		myChart_b.showLoading();
		var tableColumnData = getTableColumnData(getTableColumnDataUrl);
		tableColumnData.unshift({field: 'index', title: '序号',width: 80,align: 'center',formatter: function(value, row, index){return index+1;}, switchable:false});
		//改成按queryfield判断
		
		if($("#queryFieldName").val()=='MODULEID'){
			tableColumnData.splice(3,0,{field: 'MODULENAME',title: '模块',width: 80,align: 'center',valign: 'middle',/*formatter: classFormatter, */events: operateEvents, switchable:false});
		}else if($("#queryFieldName").val()=='PRODUCTID'){
			tableColumnData.splice(3,0,{field: 'PRODUCTNAME',title: '产品',width: 80,align: 'center',valign: 'middle',/*formatter: classFormatter, */events: operateEvents, switchable:false});
		}else if($("#queryFieldName").val()=='CLASSID'){
			tableColumnData.splice(3,0,{field: 'BIZ_NAME',title: '业务',width: 80,align: 'center',valign: 'middle',/*formatter: classFormatter, */events: operateEvents, switchable:false});
		}else if($("#queryFieldName").val()=='CATALOGID'){
			tableColumnData.splice(3,0,{field: 'BIZ_NAME',title: '业务类别',width: 80,align: 'center',valign: 'middle',/*formatter: classFormatter,*/ events: operateEvents, switchable:false});
		}else{
			tableColumnData.splice(3,0,{field: 'BIZ_NAME',title: '业务类别',width: 80,align: 'center',valign: 'middle',/*formatter: classFormatter,*/ events: operateEvents, switchable:false});
		}
		
		tableColumnData.splice(4,0,{field: 'trendAnalysis',title: '趋势分析',width: 80,align: 'center',valign: 'middle',formatter: operateFormatter, events: operateEvents, switchable:false});
		/*
		if($("#queryFieldName").val() == 'CLASSID'){
			$.each(tableColumnData, function(idx, item){
				if(item.field == 'CLASSID'){
					item.formatter = function(value, row, index){
						return '<a class="show_view productview" style="cursor:pointer;">'+row.title+'</a>';
					}
				}
			});
		}*/
		var bootstrapTableOption = {
			method : 'post',
			url : initTableUrl,
			queryParams : function (params) {
				var data = $('#searchForm').formToJSON();
				data.offset = params.offset;
				data.limit = params.limit;
				data.sort = params.sort;
				data.order = params.order;
				pageIndex = params.offset/params.limit;
				return data;
			},
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			pagination : false,
			pageSize : 10,
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
		$('#appTable_statistical').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("column-switch.bs.table", function(e, field, checked) {
			updateColumnUser(e, field, checked, updateColumnUserUrl);
		}).on("load-success.bs.table", function(e, field, checked){
			var resultData = $('#appTable_statistical').bootstrapTable("getData");
			//myChart_a.clear();
			myChart_b.dispose();
			myChart_b = echarts.init(document.getElementById('chart_b'));
			myChart_b.clear();
			console.log(resultData);
			var piechartObject = new Object();
			var pillarObject = new Object();
			piechartObject.series = new Array();
			$.each(resultData, function(idx, item){
				if(item[$("#queryFieldName").val().replace("ID","NAME")] == undefined) return true;
				var serie = {
						type: 'pie',
		                center: [((idx%8)+1)*20+'%', '50%'],
		                radius: radius,
		                x: ((idx%4)+1)*20+'%', // for funnel
		                itemStyle: labelFromatter,
		                data: []
		            };
				serie.data = new Array();
				serie.data.push({
					itemStyle: labelTop,
					value: item.QUALITY_SCORE,
					name: item[$("#queryFieldName").val().replace("ID","NAME")]                    
                    
                });
				serie.data.push({
					name: 'other',
                    value: 100-item.QUALITY_SCORE,
                    itemStyle: labelBottom,
                    color: "#e7e7e7"
                });				
				piechartObject.series.push(serie);
				
				$.each(item, function(iidx, iitem){
					if(pillarObject[iidx] == undefined){
						pillarObject[iidx] = new Object();
					}
					if(pillarObject[iidx].yAxisData == undefined){
						pillarObject[iidx].yAxisData = new Array();
					}
					if(pillarObject[iidx].seriesData == undefined){
						pillarObject[iidx].seriesData = new Array();
					}
					pillarObject[iidx].yAxisData.push(item[$("#queryFieldName").val().replace("ID","NAME")]);
					pillarObject[iidx].seriesData.push(iitem);
				});
			});
			var chartLeftOption = "";
			var paramName = "";
			var validOption = ["QUALITY_SCORE", "FLOW_UP", "FLOW_DN", "RESPONSE_DELAY", "SERVER_DELAY", "CLIENT_DELAY", "CONNECT_COUNT", "SUCCESSCONNECT_COUNT",
			                   "CONNECTTIMEOUT_COUNT", "RESPFAIL_COUNT", "SUCCESSCONNECT_RATE", "FAILCONNECT_RATE", "CONNECTTIMEOUT_RATE", "RETRANSPACKAGE_UP_RATE", 
			                   "RETRANSPACKAGE_DN_RATE", "UP_SESSION_RATE", "DN_SESSION_RATE", "MIDPACKAGEFLOW_DN_RATE", "BIGPACKAGEFLOW_DN_RATE"];
			var tableColumnData_i = 0;
			
			$.each(tableColumnData, function(idx, item){
				if(validOption.indexOf(item.field) >= 0){
					if(tableColumnData_i == 0){
						$.each(pillarObject, function(iidx, iitem){
							if(iidx == item.field){
								var sortedData = sort3(iitem.yAxisData, iitem.seriesData,
										10, 'asc');
								option_b.yAxis.data = sortedData.yAxisData;
								option_b.series[0].data = sortedData.seriesData;

							}
						});
					}
					chartLeftOption = chartLeftOption + "<option value='"+item.field+"'>"+item.title+"</option>";
					tableColumnData_i++;
				}
			});

			myChart_b.setOption(option_b);
			myChart_b.hideLoading();
			$("#chartleftoption").html("");
			$("#chartleftoption").append(chartLeftOption).on("change", function(e){
				myChart_b.showLoading();
				myChart_b.clear();
				if(pillarObject[$(this).find("option:selected").prop("value")]!=undefined){
					var sortedData = sort3(pillarObject[$(this).find("option:selected").prop("value")].yAxisData,
							pillarObject[$(this).find("option:selected").prop("value")].seriesData,
							10, 'asc');
					option_b.yAxis.data = sortedData.yAxisData;
					option_b.series[0].data = sortedData.seriesData;
				}else{
					option_b.yAxis.data = new Array();
					option_b.series[0].data = new Array();
				}
				
				myChart_b.setOption(option_b);
				myChart_b.hideLoading();
			});
			
			//option_a.series = piechartObject.series;
			//myChart_a.setOption(option_a);
			//myChart_a.hideLoading();
			
			
		});
	}
	
   // var myChart_a = echarts.init(document.getElementById('chart_a'));
    var myChart_b = echarts.init(document.getElementById('chart_b'));
    var labelTop = {
        normal: {
            label: {
                show: true,
                position: 'center',
                formatter: '{b}',
                textStyle: {
                    baseline: 'bottom'
                }
            },
            labelLine: {
                show: false
            }
        }
    };
    var labelFromatter = {
        normal: {
            label: {
                formatter: function(params) {
                    return 100 - params.value + '分'
                },
                textStyle: {
                	textBaseline: 'center'
                }
            }
        },
    }
    var labelBottom = {
        normal: {
            color: '#e7e7e7',
            position: 'center',
            label: {
                show: true,
                position: 'center'
            }            
        },
        emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    var radius = [50, 80];
    /*var option_a = {
    		toolbox: {
    			feature : {
    	            //mark : {show: true},
    	            //dataView : {show: false, readOnly: true},
    	            //magicType : {show: true, type: ['line', 'bar']},
    				restore : {show: true},    	            
    	            saveAsImage : {show: true}
    	        },
    	        
    		},
    		calculable : true,
    		color: ["#eb547c", "#2ec7c9", "#b6a2de", "#5ab1ef"],
            title: {
                text: '综合质量',
                show: false,
                x: 'center'
            },
            series : []
        };*/

	//TOP5出口流量(GB)
	var option_b = {
		title: {
			text: '业务质量排名',
			show: false,
		},
		color: ["#2ec7c9"],
		toolbox: {						
			feature : {
	            mark : {show: true},
	            //dataView : {show: true, readOnly: false},
	            //magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow',
				lineStyle: {
					width: 30,
					}
			}
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '8%',
			containLabel: true
		},
		xAxis :{
						splitLine : {
							show : true,
				            lineStyle : {
				            	color:'#e7e7e7'
				            }
						},
						type : 'value'
			},
		yAxis : {
				splitLine : {
					show : true,
		            lineStyle : {
		            	color:'#e7e7e7'
		            }
				},
				type : 'category',
				data : []
			}
		,
		series: [
			{
				name:'',
				type:'bar',
				barWidth: '20',
				data:[],
				label:{
					normal:{
						show: true,
						position: 'right'
					}
				}
			}
		]
	};
	myChart_b.setOption(option_b,true);

  //  myChart_a.setOption(option_a);
	//chart end
    
	statisticsObject.search = search;
	statisticsObject.getAllNodes=getAllNodes;
	statisticsObject.refreshTable = function(){
		$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
	};
	statisticsObject.refreshChart = function(){
		myChart_b.dispose();
		myChart_b = echarts.init(document.getElementById('chart_b'));
		//myChart_b.setOption(option_a,true);
		//myChart_a.dispose();
		//myChart_a = echarts.init(document.getElementById('chart_a'));
		//myChart_a.setOption(option_a,true);
	}
})();
