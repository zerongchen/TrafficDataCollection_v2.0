<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData"
	value="${ctx}/common/QUALITY_REGIONCARRIER_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="updateColumnUser"
	value="${ctx}/common/QUALITY_REGIONCARRIER_TREND/updateColumnUser.do"
	scope="request" />
<c:set var="initTable"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/initTable.do"
	scope="request" />
	
<c:set var="getStatisticsTableColumnData"
	value="${ctx}/common/QUALITY_REGIONCARRIER_STA/getTableColumnData.do"
	scope="page" />
<c:set var="updateStatisticsColumnUser"
	value="${ctx}/common/QUALITY_REGIONCARRIER_STA/updateColumnUser.do"
	scope="request" />
<c:set var="initStatisticsTable"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/initStatisticsTable.do"
	scope="request" />
<c:set var="getPositionOptionData"
	value="${ctx}/common/selectSysPosition.do"
	scope="request" />
<c:set var="getCarrierMultSelectData"
	value="${ctx}/common/selectCarrier.do"
	scope="request" />
	
<c:set var="exportStatistic"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/exportStatistic.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/exportTrend.do"
	scope="request" />
<c:set var="initLayerTrendTable"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/initLayerTrendTable.do"
	scope="request" />
<c:set var="exportLayerTrend"
	value="${ctx}/quality/netQualityAnalysis/regionCarrierQuality/exportLayerTrend.do"
	scope="request" />
	<div id = "div_layer"  style = "display:none;" class="table-title">
        <div class="tabs">
            <label><input name="Fruit1"  value="0"   type="radio" />图表 </label>
            <label><input name="Fruit1"  value="1"   type="radio"  />列表 </label>
            <sec:authorize ifAllGranted="ROLE_QUALITY_NETANALYSIS_AREAOPERATORQUALITY_EXPORT">
            <button id="export_layer">导出列表</button>
        	</sec:authorize>
        </div>
        <div id="chart_layer" >
	        <div class="indicators_two">
				<span>指标</span> 
				<select name="" id="chart_layer_option">
				</select>
			</div>
	        <div id="layer_chart" style="width: 100%;margin:30px auto 0;height:500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);">
	        </div>
        </div>
        <div id = "div_layer_list" style = "display:none;">
        	<table id = "appTable_layer"  ></table>
        </div>
    </div>
    <script type="text/javascript">

    $("input[name='Fruit1']").bind("change", function(){
    	if($(this).val() == 0){
    		$('#chart_layer').show();
 	        $('#div_layer_list').hide();
 	        $("#export_layer").hide();
    	}else{
    		$('#div_layer_list').show();
 	        $('#chart_layer').hide();
 	       $("#export_layer").show();
    	}
    });
    (function(){
		$("#export_layer").on("click", function(e){
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
			$.download({url:exportLayerTrendUrl, data:data});
		});
    	function searchLayer(positionId,carrierId){
    		getTableColumnData(getTableColumnDataUrl);
    		//console.log(tableColumnData);
    		$("#sel_carrier").val(carrierId);
    		$("#sel_position").val(positionId);
    		$('#appTable_layer').bootstrapTable("destroy").bootstrapTable({
    			method : 'post',
    			url : initLayerTrendTableUrl,
    			queryParams : function(params){
    				var data = $('#searchForm').formToJSON();
    				data.offset = params.offset;
    				data.limit = params.limit;
    				data.sort = params.sort;
    				data.order = params.order;
    				data.queryCarriers = carrierId;
    				data.queryPosition = positionId;
    				data.queryTimeScale = $('#searchForm').data("queryTimeScale");
    				return data;
    			},
    			contentType : "application/x-www-form-urlencoded",
    			cache : false,
    			height : 535, 
    			striped : true,
    			pagination : false,
    			sortable: false,
    			pageSize : 10,
    			//pageList : [ 5, 10 ],
    			showColumns : true,
    			sidePagination : 'server',
    			minimumCountColumns : 1,
    			clickToSelect : true,
    			paginationFirstText : "首页",
    			paginationPreText : '上一页',
    			paginationNextText : '下一页',
    			paginationLastText : '最后一页',
    			columns : tableColumnData
    		}).on("column-switch.bs.table", function(e, field, checked) {
    			updateColumnUser(e, field, checked, updateColumnUserUrl);
    		}).on("load-success.bs.table", function(e, field, checked){			
    			optionlayer.xAxis.data = [];
    			optionlayer.series[0].data = [];
    			optionlayer.series[0].name = "";
    			optionlayer.title.text = "";
    			
    			var resultData = $('#appTable_layer').bootstrapTable("getData");
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
    								optionlayer.xAxis.data = obj.xAxis;
    								optionlayer.series[0].data = iitem;
    								optionlayer.series[0].name = item.title;
    								optionlayer.title.text = item.title;
    								return false;
    							}
    						});
    					}
    					chartDOption = chartDOption + "<option value='"+item.field+"'>"+item.title+"</option>";
    					tableColumnData_i++;
    				}
    			});
    			var mychart_3 = echarts.init(document.getElementById('layer_chart'));
    			mychart_3.showLoading();
    			$("#chart_layer_option").empty().append(chartDOption).on("change", function(e){
    				mychart_3.showLoading();
    				mychart_3.clear();
    				optionlayer.series[0].data = obj.series[$(this).find("option:selected").prop("value")];
    				var paramName = $(this).find("option:selected").html();
    				optionlayer.xAxis.data = obj.xAxis;
    				optionlayer.series[0].name = paramName;
    				optionlayer.title.text = paramName;
    				mychart_3.setOption(optionlayer);
    				mychart_3.hideLoading();
    			});
    			mychart_3.clear();
    			mychart_3.setOption(optionlayer);
    			mychart_3.hideLoading();
    		});
    	}
        var optionlayer = {
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
          	    	right : '4%',    
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
	      	    yAxis: [{
	      	    	splitLine:{  
	      	            show:true  ,
	      	            lineStyle : {
	      	            	color:'#e7e7e7'
	      	            }
	      	        },  
	      	        type: 'value'
	      	    }],  
          	    series: [
          	        {
          	        	name:'',
        				type:'line',
        				barMaxWidth:'38',
        				data:[]
          	        }
          	    ]
        	};
        statisticsObject.searchLayer = searchLayer;
	})();
    </script>