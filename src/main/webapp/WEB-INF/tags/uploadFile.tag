<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="targetId" type="java.lang.String" required="true"%>
<%@ attribute name="requestUrl" type="java.lang.String" required="true"%>
<%@ attribute name="callback" type="java.lang.String" required="false"%>

<div class="popup" id="popup02">
	<div class="windowtitle">
		批量导入
		<button class="close" id="popup02close">x</button>
	</div>
	<div class="windowform">
		<form id="uploadForm">
			<div>
				<label>文件：</label> <input type="file" name="dataFile" id="dataFile" value=""> <i class="required">*</i>
			</div>
			<div class="confirm">
				<label></label>
				<button type="button" id="btnOper1">确定</button>
				<button type="button" class="btnwhite" id="btnCancel1">取消</button>
			</div>
		</form>
	</div>
	<div class="bottom"></div>
</div>
<script type="text/javascript">
	$(function(){
		$("#${targetId}").click(function() {
			layer.open({
				title : false,
				type : 1,
				closeBtn : false,
				skin : 'layui-layer-nobg',
				area : [ '430px', '260px' ],
				scrollbar : false,
				shadeClose : false, //点击遮罩关闭
				content : $('#popup02')
			});
		});
    	$("#popup02 #btnCancel1").on("click", function(e) {
			layer.closeAll();
		});
    	$("#popup02 .close").on("click", function(e) {
			layer.closeAll();
		});
    	$("#popup02 #btnOper1").on("click", function(e) {
    		if($("#popup02 #dataFile").val() == undefined || $("#popup02 #dataFile").val() == ''){
        		layer.msg("请选择要导入的文件！");
    		}else{
    			$.ajaxFileUpload({
    	            url: '${requestUrl}',
    	            secureuri:false, 
    	            fileElementId: ["dataFile"],
    	            dataType: 'json',
    	            success: function (message){
    	            	if(message.flag >= 0){
        	            	layer.closeAll();
        	            	if('${callback}' != ''){
        	            		eval('${callback}()');
        	            	}
        	    			layer.msg(message.msg);
    	            	}else{
        	            	layer.closeAll();
        	            	layer.msg(message.msg);
    	            	}
    	            }, 
    	            error: function(data){
    	            	layer.closeAll();
    	            	layer.msg(data.msg);
    	            }
    	        });  
    		}
		});
	});
</script>
