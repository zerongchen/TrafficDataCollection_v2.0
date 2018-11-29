var trendObject = new Object();
(function(){
	$("#export1").on("click", function(e){
		var tableColumnData = $('#appTable_dn').bootstrapTable("getOptions").columns[0];
		var data = new Object();
		data.headers = new Array();
		data.fields = new Array();		
		data = $.extend(true,queryParams({offset:0, limit:0, sort:"", order:""}), data);
		console.log(data);
		$.each(tableColumnData, function(idx, item){
			if(item.visible == true && item.field!='trendAnalysis' && item.field != 'index'){
				data.headers.push(item.title);
				data.fields.push(item.field);
			}
		});
		$.download({url:exportStatisticUrl, data:data});
	});
	function search(treeObj) {
		var columns = [{align:'right',field:"FLOW_TOTAL_PERCENT",order:1,sortable:false,switchable:true,title:"总流量占比（%）",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_TOTAL",order:2,sortable:true,switchable:true,title:"总流量（GB）",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_UP",order:3,sortable:true,switchable:true,title:"上行流量（GB)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_DN",order:4,sortable:true,switchable:true,title:"下行流量（GB)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_TOTAL_RATE",order:5,sortable:true,switchable:true,title:"均值速率（Mbps)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_UP_RATE",order:6,sortable:true,switchable:true,title:"上行速率（Mbps)",valign:"bottom",visible:true},
			           {align:'right',field:"FLOW_DN_RATE",order:7,sortable:true,switchable:true,title:"下行速率（Mbps)",valign:"bottom",visible:true}
			];
		var field = "";
		var fieldname = "";
		if(treeObj.getSelectedNodes()[0].level == 0){
			field = "SERVERBUILD_ID";
			fieldname = "节点";
		}else if(treeObj.getSelectedNodes()[0].level == 1){
			field = "SERVERROOM_ID";
			fieldname = "机房";
		}else if(treeObj.getSelectedNodes()[0].level == 2){
			field = "SERVERROOM_ID";
			fieldname = "机房";
		}else if(treeObj.getSelectedNodes()[0].level == 3){
			field = "SERVERROOM_ID";
			fieldname = "机房";
		}
		columns.splice(0,0,{align:'right',field:field,order:0,sortable:false,switchable:true,title:fieldname,valign:"bottom",visible:true});
		var bootstrapTableOption = {
			method : 'post',
			url : initDnTableUrl,
			queryParams : queryParams,
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			//height : 400,
			striped : true,
			showColumns : false,
			sidePagination : 'server',
			minimumCountColumns : 1,
			clickToSelect : true,
			columns : columns
		}
		$('#appTable_dn').bootstrapTable("destroy").bootstrapTable(bootstrapTableOption).on("load-success.bs.table", function(e, field, checked){
			$(".c-traffic .table-title:eq(1) .tabs:eq(0)").show();
			//var chartdata = new Object();
			
		});
	}
	
	function searchCharts(treeObj){
		var params = $('#searchForm').formToJSON();
		//params.offset = params.offset;
		//params.limit =  1000;
		//params.sort = $("#hid_sort").val();
		//params.order = "desc";
		mychart1.showLoading();
		mychart2.showLoading();
		mychart3.showLoading();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : initDnTableUrl,
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				if(data != null && data.rows != null){
					var resultData = data.rows;
					var title = treeObj.getSelectedNodes()[0].name;
					var field = ""
					if(treeObj.getSelectedNodes()[0].level == 0){
						field = "SERVERBUILD_ID";
					}else if(treeObj.getSelectedNodes()[0].level == 1){
						field = "SERVERROOM_ID";
					}else if(treeObj.getSelectedNodes()[0].level == 2){
						field = "SERVERROOM_ID";
						$(".c-traffic .table-title:eq(1) .tabs:eq(0)").hide();
						$("#chart_bb").hide();
						$("#chart_cc").hide();
						$("#chart_dd").hide();
					}else if(treeObj.getSelectedNodes()[0].level == 3){
						$(".c-traffic .table-title:eq(1) .tabs:eq(0)").hide();
						$("#chart_bb").hide();
						$("#chart_cc").hide();
						$("#chart_dd").hide();
					}
					$(".c-traffic .table-title:eq(0) a:eq(0)").html(title+"流量趋势");
					$(".c-traffic .table-title:eq(1) a:eq(0)").html(title+"流量占比");

					/*option1.legend.data = new Array();
					option2.legend.data = new Array();
					option3.legend.data = new Array();*/
					option1.series[0].data = new Array();
					option2.series[0].data = new Array();
					option3.series[0].data = new Array();
					console.log(resultData);
					$.each(resultData, function(idx, item){
						if(idx == resultData.length-1) return false;
						/*option1.legend.data.push(item[field]);
						option2.legend.data.push(item[field]);
						option3.legend.data.push(item[field]);*/ 
						option1.series[0].data.push({value:item.FLOW_TOTAL, name:item[field]});
						option2.series[0].data.push({value:item.FLOW_UP, name:item[field]})
						option3.series[0].data.push({value:item.FLOW_DN, name:item[field]})
					});
					mychart1.setOption(option1,true);
					mychart1.resize();
					mychart2.setOption(option2,true);
					mychart2.resize();
					mychart3.setOption(option3,true);
					mychart3.resize();
					mychart1.hideLoading();
					mychart2.hideLoading();
					mychart3.hideLoading();
				}
			}
		});
		$('.traffic_b').click();
	}
	
	var mychart1 = echarts.init(document.getElementById('chart_bb'));
	var mychart2 = echarts.init(document.getElementById('chart_cc'));
	var mychart3 = echarts.init(document.getElementById('chart_dd'));
	var option1 = {
			title : {
				text: '总流量pie',
				x:'center',
				show: false
			},			
			color: ["#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#2ec7c9", "#b6a2de", "#5ab1ef", "#ffbf7a", "#07a2a4"],
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
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
				right : '4%',   
				top : 'top',	
		        feature : {
		            mark : {show: true},
		            //dataView : {show: true, readOnly: false},
		            //magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
			},
			

			series : [
				{
					name: '总流量',
					type: 'pie',
					radius: '65%',
					center: ['50%', '46%'],
					data:[],
					itemStyle: {
                        normal:{ 
                            label:{ 
                               show: true, 
                               formatter: '{b} : {c} ({d}%)' 
                            }, 
                            labelLine :{show:true}
                        },
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}
			]
		};
	var option2 = {
			title : {
				text: '上行流量pie',
				x:'center',
				show: false
			},			
			color: ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffbf7a", "#eb547c", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#07a2a4"],
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			toolbox : {
				show : true,
				orient : 'horizontal',
				right : '4%',   
				top : 'top',	
		        feature : {
		            mark : {show: true},
		            //dataView : {show: true, readOnly: false},
		            //magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
			},
			// color: ['rgb(254,67,101)','rgb(252,157,154)','rgb(249,205,173)','rgb(200,200,169)','rgb(131,175,155)'],
			series : [
				{
					name: '上行流量',
					type: 'pie',
					radius: '65%',
					center: ['50%', '46%'],
					data:[],
					itemStyle: {
                        normal:{ 
                            label:{ 
                               show: true, 
                               formatter: '{b} : {c} ({d}%)' 
                            }, 
                            labelLine :{show:true}
                        },
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}
			]
		};
	var option3 = {
			title : {
				text: '下行流量pie',
				x:'center',
				show: false
			},			
			//color: ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffbf7a", "#eb547c", "#247ba0", "#70c1b3", "#b2dbbf", "#d6e593", "#eb547c", "#07a2a4"],
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			toolbox : {
				show : true,
				orient : 'horizontal',
				right : '4%',   
				top : 'top',	
		        feature : {
		            mark : {show: true},
		            //dataView : {show: true, readOnly: false},
		            //magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
			},
			/*legend: {
	            orient: 'vertical',
	            x: 'left',
	            padding: [40, 10, 15, 150],
	            formatter: function (name) {
	            	return name;
	            },
	            data: []
	        },*/
			// color: ['rgb(254,67,101)','rgb(252,157,154)','rgb(249,205,173)','rgb(200,200,169)','rgb(131,175,155)'],

			series : [
				{
					name: '下行流量',
					type: 'pie',
					radius: '65%',
					center: ['50%', '46%'],
					data:[],
					itemStyle: {
                        normal:{ 
                            label:{ 
                               show: true, 
                               formatter: '{b} : {c} ({d}%)' 
                            }, 
                            labelLine :{show:true}
                        },
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}
			]
		};
	mychart1.setOption(option1,true);
	mychart1.resize();
	mychart2.setOption(option2,true);
	mychart2.resize();
	mychart3.setOption(option3,true);
	mychart3.resize();
	trendObject.search = search;
	trendObject.searchCharts = searchCharts;
	trendObject.refreshChart = function(){
		mychart1.dispose();
		mychart1 = echarts.init(document.getElementById('chart_bb'));
		mychart1.setOption(option1,true);
		mychart1.resize();
		mychart2.dispose();
		mychart2 = echarts.init(document.getElementById('chart_cc'));
		mychart2.setOption(option2,true);
		mychart2.resize();
		mychart3.dispose();
		mychart3 = echarts.init(document.getElementById('chart_dd'));
		mychart3.setOption(option3,true);
		mychart3.resize();
	};
})();
