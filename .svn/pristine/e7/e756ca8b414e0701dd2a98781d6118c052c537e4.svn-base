<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="updateColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_TREND/updateColumnUser.do"
	scope="request" />
<c:set var="initTable"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/initTable.do"
	scope="request" />
	
<c:set var="getStatisticsTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_STA/getTableColumnData.do"
	scope="page" />
<c:set var="updateStatisticsColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_SERVICE_FLOW_STA/updateColumnUser.do"
	scope="request" />
<c:set var="initStatisticsTable"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/initStatisticsTable.do"
	scope="request" />

<c:set var="exportStatistic"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/exportStatistic.do"
	scope="request" />
<c:set var="exportTrend"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/exportTrend.do"
	scope="request" />
<c:set var="initLayerTrendTable"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/initLayerTrendTable.do"
	scope="request" />
<c:set var="exportLayerTrend"
	value="${ctx}/traffic/trafficAnalysis/serviceTraffic/exportLayerTrend.do"
	scope="request" />
	<div id = "div_layer"  style = "display:none;" class="table-title">
        <div class="tabs">
            <label><input name="Fruit1"  value="0"   type="radio" />图表 </label>
            <label><input name="Fruit1"  value="1"   type="radio"  />列表 </label>
            <sec:authorize ifAllGranted="ROLE_BUSINESS_DIRECTION_EXPORT">
            <button id="export_layer">导出列表</button>
            </sec:authorize>
        </div>
        <div id="chart_layer" >
	        <div id="layer_chart" style="width: 100%;margin:0 auto;height:500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);">
	        </div>
        </div>
        <div id = "div_layer_list" style = "display:none;">
        	<table id = "appTable_layer"  ></table>
        </div>
    </div>
    <script type="text/javascript">
    $("#layer_chart").css('width', "1100px"); 
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
				if(item.visible = true){
					data.headers.push(item.title);
					data.fields.push(item.field);
				}
			});
			$.download({url:exportLayerTrendUrl, data:data});
		});
    	function searchLayer(catalog){
    		getTableColumnData(getTableColumnDataUrl);
    		//$("#catalog").val(catalog);
    		$('#appTable_layer').bootstrapTable("destroy").bootstrapTable({
    			method : 'post',
    			url : initLayerTrendTableUrl,
    			queryParams : function(params){
    				var data = $('#searchForm').formToJSON();
    				data.offset = params.offset;
    				data.limit = params.limit;
    				data.sort = params.sort;
    				data.order = params.order;
    				data.queryCatalogId = catalog;
    				data.queryTimeScale = $('#searchForm').data("queryTimeScale");
    				return data;
    			},
    			contentType : "application/x-www-form-urlencoded",
    			cache : false,
    			//height : 400,
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
    			var resultData = $('#appTable_layer').bootstrapTable("getData");
    			var xAxisFlowData = [];  
    			var seriesFlowAllData = []; 
    			var seriesFlowUpData = [];  
    			var seriesFlowDnData = [];  
    			$.each(resultData, function(i, item){
    				xAxisFlowData.push(item['R_STATTIME']);
    				seriesFlowAllData.push( item['FLOW_RATE']);
    				seriesFlowUpData.push( item['FLOWUP_RATE']);
    				seriesFlowDnData.push( item['FLOWDN_RATE']);
    			});
//    			console.log(xAxisFlowData);
//    			console.log(seriesFlowAllData);
//    			console.log(seriesFlowUpData);
//    			console.log(seriesFlowDnData);
    			optionlayer.xAxis[0].data  = xAxisFlowData;
    			optionlayer.series[0].data  = seriesFlowUpData;
    			optionlayer.series[1].data  = seriesFlowDnData;
    			optionlayer.series[2].data  = seriesFlowAllData;
    			mychart_3.setOption(optionlayer, true);
    			mychart_3.hideLoading();
    		});
    	}
    	
    	var mychart_3 = echarts.init(document.getElementById('layer_chart'));
        var optionlayer = {
        	    legend: {
        	        data: ['上行速率（Mbps）', '下行速率（Mbps）', '总速率（Mbps）']
        	    },
        	    xAxis: [{
        	        type: 'category',
        	        boundaryGap: false,
        	        data: []
        	    }],
        	    tooltip: {
        	        trigger: 'axis'
        	    },
        	    yAxis: [{
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
        	        name: '上行速率（Mbps）',
        	        type: 'line',
        	        data: []
        	    }, {
        	        name: '下行速率（Mbps）',
        	        type: 'line',
        	        data: []
        	    }, {
        	        name: '总速率（Mbps）',
        	        type: 'line',
        	        data: []
        	    }]
        	};
        statisticsObject.searchLayer = searchLayer;
	})();
    </script>