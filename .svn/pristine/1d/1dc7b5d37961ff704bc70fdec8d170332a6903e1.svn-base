<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="preurl" value="${ctx}/strategy/management/trafficRecoveryManagement" scope="page" />

<div class="popup" id="popup01" style="left: 417px; top: 142px; display: none;">
	<div class="windowtitle">
		新增
		<button class="close" id="popup01close">x</button>
	</div>
	<form id="insertForm">
		<div class="windowform">
			<div>
				<label>策略名称：</label> <input type="text" name="queryStrategyName"><i class="required">*</i>
			</div>
			<div>
				<tdc:ftpMultiSelect domId="ftpServerAdd" />
			</div>
			<div>
				<label>源IP地址：</label><input type="text" name="querySrcIp" placeholder="例如 192.168.1.1">
			</div>
			<div>
				<label>源端口：</label> <input type="text" name="querySrcPort" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d]/g,'')" placeholder="例如 8080">
			</div>
			<div>
				<label class="sublabel" for="t01"><input type="radio" name="queryDealType" id="queryDealType_01" value="1">按业务</label> <label
					class="sublabel" for="t02"><input type="radio" name="queryDealType" id="queryDealType_02" value="2">按目的IP</label>
				<tdc:trafficRecoverSelector catalogId="insert_catalogId" classId="insert_classId" productId="insert_productId" moduleId="insert_moduleId" />
				<div class="subform" id="page02" style="display: none">
					<div>
						<label>目的IP：</label> <input type="text" placeholder="例如：192.168.13.34" name="queryDestIp"> <label>目的端口：</label> <input type="text"
							placeholder="例如：192.168.13.34" name="queryDestPort"  onkeyup="this.value=this.value.replace(/[^\d,]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d,]/g,'')" >
						<button type="button" class="smallbtn" name="smalladdbtn">+</button>
					</div>
				</div>
			</div>
			<div>
				<label>域名（HOST）：</label> <input type="text" name="queryDomain" placeholder="例如 baidu.com">
			</div>
			<div>
				<label>URL：</label> <input type="text" name="queryUrl" placeholder="例如 http://baidu.com">
			</div>
			<div>
				<label>流量：</label> <label class="sublabel" for="t01"><input type="radio" name="queryFlowType" id="queryFlowType_01" value="0">上下行流量</label>
				<label class="sublabel" for="t02"><input type="radio" name="queryFlowType" id="queryFlowType_02" value="1">上行流量</label> <label
					class="sublabel" for="t02"><input type="radio" name="queryFlowType" id="queryFlowType_03" value="2">下行流量</label>
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
<div class="popup" id="popup02" style="left: 417px; top: 142px; display: none;">
	<div class="windowtitle">
		修改
		<button class="close" id="popup02close">x</button>
	</div>
	<form id="editForm">
		<div class="windowform">
			<input type="hidden" name="queryStrategyId"/>
			<input type="hidden" name="queryMessageSequenceno"/>
			<div>
				<label>策略ID：</label> <label id="queryStrategyId"></label> 
			</div>
			<div>
				<label>策略名称：</label> <input type="text" name="queryStrategyName"><i class="required">*</i>
			</div>
			<div>
				<tdc:ftpMultiSelect domId="ftpServerEdit" />
			</div>
			<div>
				<label>源IP地址：</label><input type="text" name="querySrcIp" placeholder="例如 192.168.1.1">
			</div>
			<div>
				<label>源端口：</label> <input type="text" name="querySrcPort" placeholder="例如 8080" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d]/g,'')" >
			</div>
			<div>
				<label class="sublabel" for="t01"><input type="radio" name="queryDealType" id="queryDealType_01" value="1">按业务</label> <label
					class="sublabel" for="t02"><input type="radio" name="queryDealType" id="queryDealType_02" value="2">按目的IP</label>
				<tdc:trafficRecoverSelector1 catalogId="edit_catalogId" classId="edit_classId" productId="edit_productId" moduleId="edit_moduleId" />
				<div class="subform" id="page02" style="display: none">
					<div>
						<label>目的IP：</label> <input type="text" placeholder="例如：192.168.13.34" name="queryDestIp"> <label>目的端口：</label> <input type="text"
							placeholder="例如：192.168.13.34" name="queryDestPort" onkeyup="this.value=this.value.replace(/[^\d,]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d,]/g,'')" >
						<button type="button" class="smallbtn" name="smalladdbtn">+</button>
					</div>
				</div>
			</div>
			<div>
				<label>域名（HOST）：</label> <input type="text" name="queryDomain" placeholder="例如 baidu.com">
			</div>
			<div>
				<label>URL：</label> <input type="text" name="queryUrl" placeholder="例如 http://baidu.com">
			</div>
			<div>
				<label>流量：</label> <label class="sublabel" for="t01"><input type="radio" name="queryFlowType" id="queryFlowType_01" value="0">上下行流量</label>
				<label class="sublabel" for="t02"><input type="radio" name="queryFlowType" id="queryFlowType_02" value="1">上行流量</label> <label
					class="sublabel" for="t02"><input type="radio" name="queryFlowType" id="queryFlowType_03" value="2">下行流量</label>
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
<div class="popup" id="ftp_layer" style="width:500px;">
        <div class="windowtitle">FTP服务器详情<button class="close" id="popup04close">x</button></div>
		<table id="ftpTable"></table>
</div>
<div class="popup" id="popup03" style="left: 417px; top: 142px; display: none;">
	<div class="windowtitle">
		策略详情
		<button class="close" id="popup03close">x</button>
	</div>
	<form>
		<div class="windowform">
			<div>
				<label>策略ID：</label> <label id="queryStrategyId"></label> 
			</div>
			<div>
				<label>策略名称：</label> <input type="text" name="queryStrategyName" >
			</div>
			<div>
				<label>FTP服务器：</label><input type="text" name="queryFTPServerIps">
			</div>
			<div>
				<label>源IP地址：</label><input type="text" name="querySrcIp">
			</div>
			<div>
				<label>源端口：</label> <input type="text" name="querySrcPort">
			</div>
			<div>
				<label>业务：</label> <tdc:trafficRecoverSelector catalogId="sview_catalogId" classId="sview_classId" productId="sview_productId" moduleId="sview_moduleId" />
			</div>
			<div>
				<label>目的IP：</label> <input type="text" name="queryDestIp" > 
			</div>
			<div>
				<label>目的端口：</label> <input type="text" name="queryDestPort" >
			</div>
			<div>
				<label>域名（HOST）：</label> <input type="text" name="queryDomain" >
			</div>
			<div>
				<label>URL：</label> <input type="text" name="queryUrl" >
			</div>
			<div>
				<label>流量：</label> <label class="sublabel" for="t01"><input type="radio" name="queryFlowType" id="queryFlowType_01" value="0" >上下行流量</label>
				<label class="sublabel" for="t02"><input type="radio" name="queryFlowType" id="queryFlowType_02" value="1" >上行流量</label> <label
					class="sublabel" for="t02"><input type="radio" name="queryFlowType" id="queryFlowType_03" value="2" >下行流量</label>
			</div>
			<input type="reset" style="display: none;" />
		</div>
	</form>
	<div class="bottom"></div>
</div>
<script type="text/javascript">
	var validator1;
	var validator2;
	(function() {
        $("#add").click(function () {
        	closeLayer();
        	$("#popup01 #queryDealType_01").click();
        	$("#popup01 #queryFlowType_01").click();
        	$("#popup01").show();
        })
        $("#popup01close").click(function(){
        	closeLayer();
        })
        $("#popup03close").click(function(){
        	closeLayer();
        })
        $(".popup .cancel").click(function(){
        	closeLayer();
        });
        $("#popup02close").click(function(){
        	closeLayer();
        });
        $("#popup04close").click(function(){
        	closeLayer();
        })
        
		$("#insertForm button[name='smalladdbtn']").on("click", function(e){
			var addhtml = "<div><label>目的IP：</label> <input type=\"text\" placeholder=\"例如：192.168.13.34\" name=\"queryDestIp\">";
			addhtml += "<label>目的端口：</label> <input type=\"text\" placeholder=\"例如：192.168.13.34\"  name=\"queryDestPort\" onkeyup=\"this.value=this.value.replace(/[^\\d,]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d,]/g,'')\" >";
			addhtml += "<button type=\"button\" class=\"smallbtn\" name='smalldelbtn'>-</button></div>";
			var $addobj = $(addhtml);
			$addobj.find("button[name='smalldelbtn']").on("click", function(e){
				$(this).parent().remove();
			});
			$(this).parents(".subform:eq(0)").append($addobj);
		});
		$("#editForm button[name='smalladdbtn']").on("click", function(e){
			var addhtml = "<div><label>目的IP：</label> <input type=\"text\" placeholder=\"例如：192.168.13.34\" name=\"queryDestIp\">";
			addhtml += "<label>目的端口：</label> <input type=\"text\" placeholder=\"例如：192.168.13.34\" name=\"queryDestPort\" onkeyup=\"this.value=this.value.replace(/[^\\d,]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d,]/g,'')\" >";
			addhtml += "<button type=\"button\" class=\"smallbtn\" name='smalldelbtn'>-</button></div>";
			var $addobj = $(addhtml);
			$addobj.find("button[name='smalldelbtn']").on("click", function(e){
				$(this).parent().remove();
			});
			$(this).parents(".subform:eq(0)").append($addobj);
		});
		$("#insertForm #queryDealType_01").on("click", function(e){
			$("#insertForm #queryDealType_02").prop("checked", false);
			$("#insertForm #page02").hide();
			$("#insertForm #page01").show();
		});
		$("#insertForm #queryDealType_02").on("click", function(e){
			$("#insertForm #queryDealType_01").prop("checked", false);
			$("#insertForm #page01").hide();
			$("#insertForm #page02").show();
		});
		$("#editForm #queryDealType_01").on("click", function(e){
			$("#editForm #queryDealType_02").prop("checked", false);
			$("#editForm #page02").hide();
			$("#editForm #page05").show();
		});
		$("#editForm #queryDealType_02").on("click", function(e){
			$("#editForm #queryDealType_01").prop("checked", false);
			$("#editForm #page05").hide();
			$("#editForm #page02").show();
		});
		$("#insertForm .confirmb").on("click", function(){
			if($("#insertForm").valid()) insert();
		});
		$("#editForm .confirmb").on("click", function(e){
			if($("#editForm").valid()) update();
		});
		validator1 = $("#insertForm").validate({
		        rules : {
		        	queryStrategyName: {
		                required: true,
		                maxlength:50
		            },
		            queryFTPServerIds: {
		            	required: true
		            },
		            querySrcIp: {
		            	isIP:true,
		            	maxlength:20
		            },
		            querySrcPort: {
		            	maxlength:20
		            },
		            queryDestIp: {
		            	isIP:true,
		            	maxlength:20
		            },
		            queryDestPort: {
		            	maxlength:20
		            },
		            queryDomain: {
		            	IsDomain : true,
		            	maxlength:50
		            },
		            queryUrl:{
		            	IsURL : true,
		            	maxlength:50
		            }
		        },
		        messages: {
		            queryStrategyName: {
		                required: "请输入策略名",
		                maxlength:$.validator.format("不能超过{0}个字符")
		            },
		            queryFTPServerIds: {
		            	required: "请选择FTP服务器",
		            },
		            querySrcIp: {
		            	maxlength:$.validator.format("不能超过{0}个字符")
		            },
		            querySrcPort: {
		            	maxlength:$.validator.format("不能超过{0}个字符")
		            },
		            queryDestIp: {
		            	maxlength:$.validator.format("不能超过{0}个字符")
		            },
		            queryDestPort: {
		            	maxlength:$.validator.format("不能超过{0}个字符")
		            },
		            queryDomain: {
		            	maxlength:$.validator.format("不能超过{0}个字符")
		            },
		            queryUrl:{
		            	maxlength:$.validator.format("不能超过{0}个字符")
		            }
		        },
		        errorPlacement: function(error, element) {
	                error.appendTo( element.parent() );
		        }
	    });
		validator2 = $("#editForm").validate({
	        rules : {
	        	queryStrategyName: {
	                required: true,
	                maxlength:50
	            },
	            queryFTPServerIds: {
	            	required: true
	            },
	            querySrcIp: {
	            	isIP:true,
	            	maxlength:20
	            },
	            querySrcPort: {
	            	maxlength:20
	            },
	            queryDestIp: {
	            	isIP:true,
	            	maxlength:20
	            },
	            queryDestPort: {
	            	maxlength:20
	            },
	            queryDomain: {
	            	IsDomain : true,
	            	maxlength:50
	            },
	            queryUrl:{
	            	IsURL : true,
	            	maxlength:50
	            }
	        },
	        messages: {
	            queryStrategyName: {
	                required: "请输入策略名",
	                maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryFTPServerIds: {
	            	required: "请选择FTP服务器",
	            },
	            querySrcIp: {
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            querySrcPort: {
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryDestIp: {
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryDestPort: {
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryDomain: {
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryUrl:{
	            	maxlength:$.validator.format("不能超过{0}个字符")
	            }
	        },
	        errorPlacement: function(error, element) {
                error.appendTo( element.parent() );
	        }
	    });
		function insert() {
			var postData = $("#insertForm").formToJSON();
			postData.queryDestIp = "";
			postData.queryDestPort = "";
			$.each($("#insertForm input[name='queryDestIp']"), function(idx, item){
				postData.queryDestIp = postData.queryDestIp + (postData.queryDestIp.length==0?"":";") + ($(item).val()==null?"":$(item).val());
			});
			$.each($("#insertForm input[name='queryDestPort']"), function(idx, item){
				postData.queryDestPort = postData.queryDestPort + (postData.queryDestPort.length==0?"":";") + ($(item).val()==null?"":$(item).val());
			});
			$.ajax({
				type : "POST",
				url : "${preurl}/insert.do",
				data : postData,
				dataType : "json",
				success : function(data) {
					if (data.flag == 0) {
						search();
						stategyReNameSelect.selectStrategyName();
						closeLayer();
					} else {
						closeLayer();
						$.modal.alert(data.msg);
					}
				}
			});
		}
		function update() {
			var postData = $("#editForm").formToJSON();
			postData.queryDestIp = "";
			postData.queryDestPort = "";
			$.each($("#editForm input[name='queryDestIp']"), function(idx, item){
				postData.queryDestIp = postData.queryDestIp + (postData.queryDestIp.length==0?"":";") + ($(item).val()==null?"":$(item).val());
			});
			$.each($("#editForm input[name='queryDestPort']"), function(idx, item){
				postData.queryDestPort = postData.queryDestPort + (postData.queryDestPort.length==0?"":";") + ($(item).val()==null?"":$(item).val());
			});
			$.ajax({
						type : "POST",
						url : "${preurl}/update.do",
						data : postData,
						dataType : "json",
						success : function(data) {
							if (data.flag == 0) {
								search();
								closeLayer();
								stategyReNameSelect.selectStrategyName();
							} else {
								closeLayer();
								$.modal.alert(data.msg);
							}
						}
					});
		};
		function closeLayer(){
			$.each($("#popup03 input"), function(idx, item){
				$(item).removeAttr("readonly");
			});
			$.each($("#popup03 select"), function(idx, item){
				$(item).removeAttr("disabled");
			});
			$("#popup01 input:reset").click();
			$("#popup02 input:reset").click();
			$("#popup03 input:reset").click();
			$("#popup01").hide();
			$("#popup02").hide();
			$("#popup03").hide();
			$("button[name='smalldelbtn']").parent().remove();
			validator1.resetForm();
			validator2.resetForm();
			layer.closeAll();
		}
	})();
</script>