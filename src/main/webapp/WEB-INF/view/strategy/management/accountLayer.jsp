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
<div id="div_layer" style="display: none;" class="table-title">
	<div class="popup" style="display: block;">
		<form id="insertForm">
			<div class="windowform">
				<div>
					<label>调用用户名：</label> <input type="text" name="queryUserName"><i class="required">*</i>
				</div>
				<div>
					<label>密码：</label> <input type="password" name="queryPassword"><i class="required">*</i>
				</div>
				<div>
					<label>IP地址：</label><i> ( IP地址段起始IP的末尾用/连接，不同IP地址用,隔开）</i><input type="text" name="queryDestIp" placeholder="例如 192.168.1.1/24,192.168.5.1"><i
						class="required">*</i>
				</div>
				<div>
					<label>第三方系统名称：</label> <input type="text" name="querySystemId">
				</div>
				<div>
					<label>联系人姓名：</label> <input type="text" name="queryContact">
				</div>
				<div>
					<label>手机号码：</label> <input type="text" name="queryMobile">
				</div>
				<div>
					<label>邮箱：</label> <input type="text" name="queryEmail">
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
<div id="edit_layer" style="display: none;" class="table-title">
	<div class="popup" style="display: block;">
		<form id="editForm">
			<div class="windowform">
				<input type="hidden" name="queryId"/>
				<input type="hidden" name="queryStatus"/>
				<div>
					<label>调用用户名：</label> <input type="text" name="queryUserName"><i class="required">*</i>
				</div>
				<div>
					<label>密码：</label> <input type="password" name="queryPassword" placeholder="********"><input type="hidden" name="password"><i class="required">*</i>
				</div>
				<div>
					<label>IP地址：</label><i> ( IP地址段起始IP的末尾用/连接，不同IP地址用,隔开）</i><input type="text" name="queryDestIp" placeholder="例如 192.168.1.1/24,192.168.5.1"><i
						class="required">*</i>
				</div>
				<div>
					<label>第三方系统名称：</label> <input type="text" name="querySystemId">
				</div>
				<div>
					<label>联系人姓名：</label> <input type="text" name="queryContact">
				</div>
				<div>
					<label>手机号码：</label> <input type="text" name="queryMobile">
				</div>
				<div>
					<label>邮箱：</label> <input type="text" name="queryEmail">
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
	var validator2;
	(function() {
		$("#div_layer .cancel").on("click", function(e) {
			$("#div_layer input:reset").click();
			validator1.resetForm();
			layer.closeAll();
		});
		$("#edit_layer .cancel").on("click", function(e) {
			$("#edit_layer input:reset").click();
			validator2.resetForm();
			layer.closeAll();
		});
		$("#insertForm .confirmb").on("click", function(){			
			if($("#insertForm").valid()) insert();
		});
		$("#editForm .confirmb").on("click", function(e){
			if($("#editForm").valid()) update();
		});		 
		validator1 = $("#insertForm").validate({
	        rules : {
	        	queryUserName: {
	                required: true,
	                maxlength:16,
	                minlength:1,
	                validNickName:true
	            },
	            queryPassword: {
	            	maxlength:16,
	            	minlength:6
	            },
	            queryDestIp: {
	            	required: true,
	            	isIPS:true
	            },
	            queryMobile: {
	            	isMobile: true,
	            	maxlength:20
	            },
	            queryEmail: {
	            	isEmail: true,
	            	maxlength:50
	            },
	            querySystemId:{
	            	maxlength:20
	            },
	            queryContact:{
	            	maxlength:20
	            }
	        },
	        messages: {
	        	queryUserName: {
	                required: "请输入调用用户名",
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	            queryPassword: {
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	            queryDestIp: {
	                required: "请输入IP地址"
	            },
	            querySystemId:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryContact:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryMobile:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryEmail:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            }
	        },
	        errorPlacement: function(error, element) {
                error.appendTo( element.parent() );
	        }
	    });
		validator2 = $("#editForm").validate({
	        rules : {
	        	queryUserName: {
	                required: true,
	                maxlength:16,
	                minlength:1,
	                validNickName:true
	            },
	            queryPassword: {
	            	maxlength:16,
	            	minlength:6
	            },
	            queryDestIp: {
	            	required: true,
	            	isIPS:true
	            },
	            queryMobile: {
	            	isMobile: true,
	            	maxlength:20
	            },
	            queryEmail: {
	            	isEmail: true,
	            	maxlength:50
	            },
	            querySystemId:{
	            	maxlength:20
	            },
	            queryContact:{
	            	maxlength:20
	            }
	        },
	        messages: {
	        	queryUserName: {
	                required: "请输入调用用户名",
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	            queryPassword: {
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	            queryDestIp: {
	                required: "请输入IP地址"
	            },
	            querySystemId:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryContact:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryMobile:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryEmail:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            }
	        },
	        errorPlacement: function(error, element) {
                error.appendTo( element.parent() );
	        }
	    });
		function insert() {
			var postData = $("#insertForm").formToJSON();
			postData.querySystemId = encodeURIComponent(encodeURIComponent(postData.querySystemId));
			postData.queryContact = encodeURIComponent(encodeURIComponent(postData.queryContact));
			postData.queryPassword = hex_md5(postData.queryPassword);
			$.ajax({
				type : "POST",
				url : "${ctx}/strategy/management/account/insert.do",
				data : postData,
				dataType : "json",
				success : function(data) {
					if (data.flag == 0) {
						search();
						$("#div_layer input:reset").click();
						layer.closeAll();
					} else {
						$("#div_layer input:reset").click();
						layer.closeAll();
						$.modal.alert(data.msg);
					}
				}
			});
		}
		function update() {
			var postData = $("#editForm").formToJSON();
			postData.querySystemId = encodeURIComponent(encodeURIComponent(postData.querySystemId));
			postData.queryContact = encodeURIComponent(encodeURIComponent(postData.queryContact));
			postData.queryPassword = hex_md5(postData.queryPassword);
			if(postData.queryPassword == postData.password){
				postData.queryPassword = "";
			}
			$
					.ajax({
						type : "POST",
						url : "${ctx}/strategy/management/account/update.do",
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