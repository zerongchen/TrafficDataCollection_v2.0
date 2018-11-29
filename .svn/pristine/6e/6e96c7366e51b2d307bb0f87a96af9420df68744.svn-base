<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData" value="${ctx}/common/FLOW_DIRECTION/getTableColumnData.do" scope="page" />
<c:set var="updateColumnUser" value="${ctx}/common/FLOW_DIRECTION/updateColumnUser.do" scope="request" />
<c:set var="getTrendTableColumnData" value="${ctx}/common/FLOW_DIRECTION_TREND/getTableColumnData.do" scope="page" />
<c:set var="updateTrendColumnUser" value="${ctx}/common/FLOW_DIRECTION_TREND/updateColumnUser.do" scope="request" />
<c:set var="initTable" value="${ctx}/quality/netQualityAnalysis/areaQuality/initTable.do" scope="request" />
<c:set var="initTrendTable" value="${ctx}/quality/netQualityAnalysis/areaQuality/initTrendTable.do" scope="request" />
<c:set var="getCarrierOptionData" value="${ctx}/common/selectCarrier.do" scope="request" />
<c:set var="getPositionOptionData" value="${ctx}/common/selectSysPosition.do" scope="request" />
<c:set var="exportStatistic" value="${ctx}/quality/netQualityAnalysis/areaQuality/exportStatistic.do" scope="request" />
<c:set var="exportTrend" value="${ctx}/quality/netQualityAnalysis/areaQuality/exportTrend.do" scope="request" />
<c:set var="initLayerTrendTable" value="${ctx}/quality/netQualityAnalysis/areaQuality/initLayerTrendTable.do" scope="request" />
<c:set var="exportLayerTrend" value="${ctx}/quality/netQualityAnalysis/areaQuality/exportLayerTrend.do" scope="request" />
<div id="edit_layer" style="display: none;" class="table-title">
	<div class="popup" style="display: block;">
		<form id="editForm">
			<div class="windowform">
				<input type="hidden" name="queryId"/>
				<div>
					<label>指标名称：<a id="quotaName"></a></label>
				</div>
				<div>
					<label>权重：</label> <input type="number" name="quotaWeight">%<i class="required">*</i>
				</div>
				<div>
					<label>满分阈值（100分）：</label> <input type="number" name="quotaFullPoint">ms<i class="required">*</i>
				</div>
				<div>
					<label>优良阈值（90分）：</label> <input type="number" name="quotaGoodPoint">ms<i class="required">*</i>
				</div>
				<div>
					<label>及格阈值（60分）：</label> <input type="number" name="quotaBasePoint">ms<i class="required">*</i>
				</div>
				<div>
					<label>基准阈值（0分）：</label> <input type="number" name="quotaStep">ms<i class="required">*</i>
				</div>
				<input type="reset" style="display: none;" />
				<div class="confirm">
					<label></label>
					<button type="button" class="confirmb">确定</button>
					<button type="button" class="cancel">取消</button>
				</div>
			</div>
		</form>
		<div class="bottom"></div>
	</div>
</div>

<script type="text/javascript">
	var validator1;
	(function() {
		$("#edit_layer .cancel").on("click", function(e) {
			$("#edit_layer input:reset").click();
			validator1.resetForm();
			layer.closeAll();
		});
		$("#editForm .confirmb").on("click", function(e){
			if($("#editForm").valid()) update();
		});
	    validator1 = $("#editForm").validate({
	        rules : {
	        	quotaWeight: {
	                required: true,
	                max: 100,
	                min: 0
	            },
	            quotaFullPoint: {
	                required: true,
	                max: 100000,
	                min: -100000,
	                notEqualTo: '#editForm input[name="quotaGoodPoint"],#editForm input[name="quotaStep"],#editForm input[name="quotaBasePoint"]'
	            },
	            quotaGoodPoint: {
	                required: true,
	                max: 100000,
	                min: -100000,
	                notEqualTo: '#editForm input[name="quotaFullPoint"],#editForm input[name="quotaStep"],#editForm input[name="quotaBasePoint"]'
	            },
	            quotaBasePoint: {
	                required: true,
	                max: 100000,
	                min: -100000,
	                notEqualTo: '#editForm input[name="quotaGoodPoint"],#editForm input[name="quotaStep"],#editForm input[name="quotaFullPoint"]'
	            },
	            quotaStep: {
	                required: true,
	                max: 100000,
	                min: -100000,
	                notEqualTo: '#editForm input[name="quotaGoodPoint"],#editForm input[name="quotaFullPoint"],#editForm input[name="quotaBasePoint"]'
	            }
	        },
	        messages: {
	        	quotaWeight: {
	                required: "不能为空",
	                max: "不能大于100",
	                min: "不能小于0"
	            },
	            quotaFullPoint: {
	                required: "不能为空",
	                max: "不能大于100000",
	                min: "不能小于-100000",
	                notEqualTo: "值不能相同"
	            },
	            quotaGoodPoint: {
	                required: "不能为空",
	                max: "不能大于100000",
	                min: "不能小于-100000",
	                notEqualTo: "值不能相同"
	            },
	            quotaBasePoint: {
	                required: "不能为空",
	                max: "不能大于100000",
	                min: "不能小于-100000",
	                notEqualTo: "值不能相同"
	            },
	            quotaStep: {
	                required: "不能为空",
	                max: "不能大于100000",
	                min: "不能小于-100000",
	                notEqualTo: "值不能相同"
	            }
	        },
	        errorPlacement: function(error, element) {
                error.appendTo( element.parent() );
	        }
	    });
		function update() {
			var postData = $("#editForm").formToJSON();
			$.ajax({
						type : "POST",
						url : "${ctx}/sysconfig/quota/threshold/update.do",
						data : postData,
						dataType : "json",
						success : function(data) {
							if (data.flag == 0) {
								search();
								$("#edit_layer input:reset").click();
								layer.closeAll();
							} else {
								$("#edit_layer input:reset").click();
								layer.closeAll();
								$.modal.alert(data.msg);
							}
						}
					});
		};
	})();
</script>