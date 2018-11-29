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
		tableColumnData = $('#appTable_statistical').bootstrapTable("getOptions").columns[0];
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
	window.operateEvents = {
	    'click .trendAnalysis': function (e, value, row, index) {
	    	e.preventDefault();
			//$.modal.alert("PROVINCE_ID : " + row.PROVINCE);
			$("#export_layer").hide();
			$("input[name='Fruit1']").get(0).checked = true;   
			$("#chart_layer_option").prop('selectedIndex',0);
			$('#chart_layer').show();
		    $('#div_layer_list').hide();
		   
		    statisticsObject.searchLayer(row.DEST_IP);
		    $("#hidden_queryDestIp").val(row.DEST_IP);
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
		//mychart1.showLoading();
		mychart2.showLoading();
		getTableColumnData(getTableColumnDataUrl);
		tableColumnData.unshift({field: 'index', title: '序号',width: 80,align: 'center',formatter: function(value, row, index){var page = $('#appTable_statistical').bootstrapTable('getPage'); return page.pageSize*(page.pageNumber-1)+ index+1;}, switchable:false});
		tableColumnData.splice(3,0,{field: 'trendAnalysis',title: '趋势分析',width: 80,align: 'center',valign: 'middle',formatter: operateFormatter, events: operateEvents, switchable:false});
		var bootstrapTableOption = {
			method : 'post',
			url : initTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			pagination : true,
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
			option2.title.text = "";
			option2.xAxis[0].data = [];
			option2.series[0].data = [];
			option2.series[0].name = [];
			//option1.series[0].data = [];
			var resultData = $('#appTable_statistical').bootstrapTable("getData");
			var obj = new Object();//左图表的数据
			var objRight = new Object();//右图表的数据
			$.each(resultData, function(idx, item){
				if(item['DEST_IP'] == undefined) return true;
				$.each(item, function(iidx, iitem){
					if(obj[iidx] == undefined){
						obj[iidx] = new Array();
					}
					obj[iidx][obj[iidx].length] = new Object();
					obj[iidx][obj[iidx].length-1].name = item['DEST_IP'];
					obj[iidx][obj[iidx].length-1].value = iitem;
					
					if(objRight[iidx] == undefined){
						objRight[iidx] = new Object();
					}
					if(objRight[iidx]["xAxisData"] == undefined){
						objRight[iidx]["xAxisData"] = new Array();
					}
					if(objRight[iidx]["seriesData"] == undefined){
						objRight[iidx]["seriesData"] = new Array();
					}
					objRight[iidx]["xAxisData"].push(item['DEST_IP']);
					objRight[iidx]["seriesData"].push(iitem);
				});
			});
			var chartLeftOption = "";
			//var paramName = "";
			var validOption = ["QUALITY_SCORE", "FLOW_UP", "FLOW_DN", "RESPONSE_DELAY", "SERVER_DELAY", "CLIENT_DELAY", "CONNECT_COUNT", "SUCCESSCONNECT_COUNT",
			                   "CONNECTTIMEOUT_COUNT", "RESPFAIL_COUNT", "SUCCESSCONNECT_RATE", "FAILCONNECT_RATE", "CONNECTTIMEOUT_RATE", "RETRANSPACKAGE_UP_RATE", 
			                   "RETRANSPACKAGE_DN_RATE", "UP_SESSION_RATE", "DN_SESSION_RATE", "MIDPACKAGEFLOW_DN_RATE", "BIGPACKAGEFLOW_DN_RATE"];
			var tableColumnData_i = 0;
			statisticsObject.objRight = objRight;
			$.each(tableColumnData, function(idx, item){
				if(validOption.indexOf(item.field) >= 0){
					if(tableColumnData_i == 0){
						//$.each(obj, function(iidx, iitem){
							//if(iidx == item.field){
								//paramName = item.title;
								//option1.series[0].data = iitem;
								//return false;
							//}
						//});
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
			//option1.tooltip.formatter = function(params, ticket, callback) { return ((params.value + "").match(/[0-9.]*/g)[0].length > 0?(params.name + "</br>" + paramName+"：" + params.value):""); };
			//mychart1.clear();
			mychart2.clear();
			//mychart1.setOption(option1, true);
			mychart2.setOption(option2, true);
			mychart2.resize();
			$("#chartleftoption").html("");
			$("#chartleftoption").append(chartLeftOption).on("change", function(e){
				//mychart1.showLoading();
				mychart2.showLoading();
				//mychart1.clear();
				mychart2.clear();
				//option1.series[0].data = obj[$(this).find("option:selected").prop("value")];
				//var paramName = $(this).find("option:selected").html();
				//option1.tooltip.formatter = function(params, ticket, callback) { return ((params.value + "").match(/[0-9.]*/g)[0].length > 0?(params.name + "</br>" + paramName+"：" + params.value):""); };
				//mychart1.setOption(option1, true);
				
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
				//mychart1.hideLoading();
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
			//mychart1.hideLoading();
			mychart2.hideLoading();
		});
	}
	
	
	//chart start
	//var mychart1 = echarts.init(document.getElementById('chart_left'));
	var mychart2 = echarts.init(document.getElementById('chart_right'));
	//质量统计地域
	/*var option1 = {
		title : {
			text : '',
			left : 'center',
			show : false
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params, ticket, callback) {}
		},
		visualMap : {
			inverse : true,
			type : 'piecewise',
			left : 'center',
			top : 'top',
			orient : 'horizontal',
			inRange : {
				color : [ 'white', 'lightskyblue' ]
			},
			text : [ '高', '低' ], // 文本，默认为数值文本
			calculable : true
		},
		toolbox : {
			show : true,
			orient : 'vertical',
			left : 'left',
			top : 'center',
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
		},
		series : [ {
			name : '',
			type : 'map',
			mapType : 'china',
			label : {
				normal : {
					show : false
				},
				emphasis : {
					show : true
				}
			},
			data : []
		} ]
	};
	mychart1.setOption(option1, true);*/
	
	//质量统计柱状
	var option2 = {
			title: {
				text: '',
				show: false      
			},
			color: ["#ffbf7a"],
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
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
			grid: {
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			xAxis : [
				{
					splitLine : {
						show : true,
			            lineStyle : {
			            	color:'#e7e7e7'
			            }
					},
					type : 'category',
					data : [],
					axisTick: {
						alignWithLabel: true
					}
				}
			],
			yAxis : [
				{
					splitLine : {
						show : true,
			            lineStyle : {
			            	color:'#e7e7e7'
			            }
					},
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
	mychart2.setOption(option2,true);
	mychart2.resize();
	//chart end
	statisticsObject.search = search;
	statisticsObject.getAllNodes=getAllNodes;
	statisticsObject.refreshTable = function(){
		$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
	};
	statisticsObject.refreshChart = function(){
		//mychart1.dispose();
		mychart2.dispose();
		//mychart1 = echarts.init(document.getElementById('chart_left'));
		mychart2 = echarts.init(document.getElementById('chart_right'));
		//mychart1.setOption(option1,true);
		mychart2.setOption(option2,true);
		mychart2.resize();
	}
})();
