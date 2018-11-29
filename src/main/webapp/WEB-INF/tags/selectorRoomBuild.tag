<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="ServerBuildId" type="java.lang.String" required="false"%>
<%@ attribute name="serverRoomId" type="java.lang.String" required="false"%>


<div id = "div_serverBuildId">
                <label >所属机楼：</label>
                 <select name="queryServerBuildId" id="serverBuild" class="operator">
                <option selected="" value="-1">请选择</option>
            </select>
                <i class="required">*</i>
            </div>

            <div id = "div_queryServerRoomId">
                <label >所属机房：</label>
            <select name="queryServerRoomId" id="serverRoom" class="maps">
                <option selected="" value="-1">请选择</option>
            	</select>
                <i class="required">*</i>
            </div>

<script type="text/javascript">
	$(document).ready(function() {
		selectServerBuild();
		$("#serverBuild").bind("change", function(){
			selectServerRoom($(this).val(),true);
			$("#serverRoom").empty().append("<option value=\"-1\">请选择</option>");
		});
	});
	//地域
	
	//业务类别
	function selectServerBuild() {
		$("#serverBuild").empty();
		$("#serverBuild").append('<option selected="" value="-1">请选择</option>');
		$.ajax({
			type : "POST",
			async : false,
			dataType : 'json',
			url : ctx + '/common/selectServerBuild.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#serverBuild").append(
							"<option value=\"" + n.serverBuildId + "\">"
									+ n.serverBuildName + "</option>");
				});
				//if(callback != undefined && callback!=null){
				//	callback();
				//}
			}
		});
	}
	//业务
	function selectServerRoom(serverBuildId,isAsync) {
		$("#serverRoom").empty();
		$("#serverRoom").append('<option selected="" value="-1">请选择</option>');
		if(serverBuildId == -1) return;
		$.ajax({
			type : "POST",
			async : isAsync,
			dataType : 'json',
			data: {serverBuildId:serverBuildId},
			url : ctx + '/common/selectServerRoom.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#serverRoom").append(
							"<option value=\"" + n.serverRoomId + "\">"
									+ n.serverRoomName + "</option>");
				});
				//if(callback != undefined && callback!=null){
				//	callback();
				//}
			}
		});
	}
	

</script>
