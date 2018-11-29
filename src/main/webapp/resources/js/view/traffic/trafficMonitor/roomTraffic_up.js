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
	function search(treeObj) {
		mychart1.showLoading();
		var columns = [{align:'right',field:"R_STATTIME",order:0,sortable:false,switchable:true,title:"统计时间",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_TOTAL",order:2,sortable:false,switchable:true,title:"总流量（GB）",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_UP",order:3,sortable:false,switchable:true,title:"上行流量（GB)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_DN",order:4,sortable:false,switchable:true,title:"下行流量（GB)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_TOTAL_RATE",order:5,sortable:false,switchable:true,title:"总速率（Mbps)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_UP_RATE",order:6,sortable:false,switchable:true,title:"上行速率（Mbps)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_DN_RATE",order:7,sortable:false,switchable:true,title:"下行速率（Mbps)",valign:"bottom",visible:true}
			];
		var field = "";
		var fieldname = "";
		if(treeObj.getSelectedNodes()[0].level == 0){
			field = "SERVERBUILD_ID";
			fieldname = "节点";
		}else if(treeObj.getSelectedNodes()[0].level == 1){
			field = "SERVERBUILD_ID";
			fieldname = "节点";
		}else if(treeObj.getSelectedNodes()[0].level == 2){
			field = "SERVERROOM_ID";
			fieldname = "机房";
		}else if(treeObj.getSelectedNodes()[0].level == 3){
			field = "SERVERROOM_ID";
			fieldname = "业务";
		}
		columns.splice(1,0,{align:'right',field:field,order:1,sortable:false,switchable:true,title:fieldname,valign:"bottom",visible:true});
		var bootstrapTableOption = {
			method : 'post',
			url : initUpTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			height : 400,
			striped : true,
			showColumns : false,
			sidePagination : 'server',
			minimumCountColumns : 1,
			clickToSelect : true,
			columns : columns
		}
		$('#appTable_up').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("load-success.bs.table", function(e, field, checked){
			var resultData = $('#appTable_up').bootstrapTable("getData");
			var chartData = new Object();
			chartData.up = new Array(), chartData["dn"] = new Array(), chartData["total"] = new Array(), chartData["x"] = new Array();
			$.each(resultData, function(idx, item){
				chartData.x.push(item.R_STATTIME);
				chartData.up.push(item.FLOW_UP_RATE);
				chartData.dn.push(item.FLOW_DN_RATE);
				chartData.total.push(item.FLOW_TOTAL_RATE);
			});
			option1.xAxis.data = chartData.x;
			option1.series[0].data = chartData.up;
			option1.series[1].data = chartData.dn;
			option1.series[2].data = chartData.total;
			mychart1.clear();
			mychart1.setOption(option1,true);
			mychart1.resize();
			mychart1.hideLoading();
		});
		mychart1.hideLoading();
	}
	var mychart1 = echarts.init(document.getElementById('chart_a'));
	//机房流量

	var option1  = {
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},
		    color: ["#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#b6a2de", "#5ab1ef", "#f5994e", "#07a2a4"],
		    legend: {
		        data: ['上行速率（Mbps）', '下行速率（Mbps）', '均值速率（Mbps）'],
		        top:'2%'
		    },
		    grid: {
		        left: '3%',
		        top: '40',
		        right: '6%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox : {
				show : true,
				orient : 'horizontal',
				right : '5%',
				top : '2%',
		        feature : {
		        	mark:{show:true},
		            //dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar'] },
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
						name : '上行速率（Mbps）',
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
		 	            type:'line', 
		 	           symbol:'none',
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
		 	            //name:'总速率（Mbps）',
		 	        	name:'均值速率（Mbps）',
		 	            type:'line',
		 	            symbol:'none',
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
	mychart1.setOption(option1,true);
	mychart1.resize();
	statisticsObject.search = search;
	statisticsObject.refreshTable = function(){
		$('#appTable_up').bootstrapTable("refresh", {silent: true});
	};
	statisticsObject.refreshChart = function(){
		mychart1.dispose();
		mychart1 = echarts.init(document.getElementById('chart_a'));
		mychart1.setOption(option1,true);
		mychart1.resize();
	}
})();
