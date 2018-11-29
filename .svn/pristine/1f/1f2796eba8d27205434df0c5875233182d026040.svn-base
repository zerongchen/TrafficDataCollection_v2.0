<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<div id="div_layer" style="display: none;" class="table-title">
	<div class="popup" style="display: block;">
		<form id="insertForm">
			<div class="windowform">
				<div>
					<label>FTP服务器名称：</label> <input type="text" name="queryFTPServerName"><i class="required">*</i>
				</div>
				<div>
					<label>IP地址：</label><input type="text" name="queryFTPServerIp" placeholder="例如 192.168.1.1"><i class="required">*</i>
				</div>
				<div>
					<label>端口：</label> <input type="text" name="queryPort" placeholder="例如 8080"><i class="required">*</i>
				</div>
				<div>
					<label>用户名：</label> <input type="text" name="queryUserName"><i class="required">*</i>
				</div>
				<div>
					<label>密码：</label> <input type="password" name="queryPassword"><i class="required">*</i>
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
				<div>
					<label>FTP服务器名称：</label> <input type="text" name="queryFTPServerName"><input type="hidden" name="queryFTPServerId"><i class="required">*</i>
				</div>
				<div>
					<label>IP地址：</label><input type="text" name="queryFTPServerIp" ><i class="required">*</i>
				</div>
				<div>
					<label>端口：</label> <input type="text" name="queryPort" ><i class="required">*</i>
				</div>
				<div>
					<label>用户名：</label> <input type="text" name="queryUserName"><i class="required">*</i>
				</div>
				<div>
					<label>密码：</label> <input type="password"  placeholder="********" name="queryPassword"><input type="hidden" name="password"><i class="required">*</i>
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
	(function(){
		$("#div_layer .cancel").on("click", function(e) {
			$("#div_layer input:reset").click();
			layer.closeAll();
		});
		$("#edit_layer .cancel").on("click", function(e) {
			$("#edit_layer input:reset").click();
			layer.closeAll();
		});
		$("#insertForm .confirmb").on("click", function(){			
			if($("#insertForm").valid()) insert();
		});
		$("#editForm .confirmb").on("click", function(e){
			if($("#editForm").valid()) update();
		});
		
		$("#insertForm").validate({
	        rules : {
	        	queryFTPServerName: {
	            	required: true,
	            	maxlength:50
	            },
	            queryFTPServerIp: {
	            	required: true,
	            	isIP:true
	            },
	            queryPort: {
	            	required: true,
	            	maxlength:20
	            },
	        	queryUserName: {
	                required: true,
	                maxlength:16,
	                minlength:1,
	                validNickName:true
	            },
	            queryPassword: {
	            	required: true,
	            	maxlength:16,
	            	minlength:6
	            },
	        },
	        messages: {
	        	queryFTPServerName: {
	                required: "请输入FTP服务器名称",
	                maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryFTPServerIp: {
	                required: "请输入IP地址"
	            },
	            queryPort: {
	                required: "请输入端口",
	                maxlength:$.validator.format("不能超过{0}个字符")
	            },
	        	queryUserName: {
	                required: "请输入用户名",
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	            queryPassword: {
	                required: "请输入密码",
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	        },
	        errorPlacement: function(error, element) {
                error.appendTo( element.parent() );
	        }
	    });
		$("#editForm").validate({
	        rules : {
	        	queryFTPServerName: {
	            	required: true,
	            	maxlength:50
	            },
	            queryFTPServerIp: {
	            	required: true,
	            	isIP:true
	            },
	            queryPort: {
	            	required: true,
	            	maxlength:20
	            },
	        	queryUserName: {
	                required: true,
	                maxlength:16,
	                minlength:1,
	                validNickName:true
	            },
	            queryPassword: {
	            	required: true,
	            	maxlength:16,
	            	minlength:6
	            },
	        },
	        messages: {
	        	queryFTPServerName: {
	                required: "请输入FTP服务器名称",
	                maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            queryFTPServerIp: {
	                required: "请输入IP地址"
	            },
	            queryPort: {
	                required: "请输入端口",
	                maxlength:$.validator.format("不能超过{0}个字符")
	            },
	        	queryUserName: {
	                required: "请输入用户名",
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	            queryPassword: {
	                required: "请输入密码",
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("不能小于{0}个字符")
	            },
	        },
	        errorPlacement: function(error, element) {
                error.appendTo( element.parent() );
	        }
	    });
		
		function insert(){
			var postData = $("#insertForm").formToJSON();
			//postData.queryPassword = hex_md5(postData.queryPassword);
			$.ajax({
				type : "POST",
				url : "${ctx}/strategy/management/ftpServerManagement/insert.do",
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
		};
		
		function update() {
			  var postData = $("#editForm").formToJSON();
			  //postData.queryPassword = hex_md5(postData.queryPassword);
			//console.log(postData.queryPassword);
			//console.log(postData.password);
			//若密码未改变，则不传入更新
			if(postData.queryPassword == postData.password){
				postData.queryPassword = "";
			}
			$.ajax({
					type : "POST",
					url : "${ctx}/strategy/management/ftpServerManagement/update.do",
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