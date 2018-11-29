<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="domName" type="java.lang.String" required="false"%>
<%@ attribute name="getDestIpMultSelectUrl" type="java.lang.String" required="true"%>
<div>
    <label for="">目标IP地址：</label>
    <select <c:if test="${not empty domId}">id = "${domId}"</c:if> <c:if test="${not empty domName}">name = "${domName}"</c:if> multiple="multiple"></select> 
</div>

<script type="text/javascript">
(function(){
	$(function(){
		$("#sel_destIp").multiselect({
			noneSelectedText : "全部IP地址",
			checkAllText : "全选",
			uncheckAllText : '全不选'
		});
		selectDestIp();
	});
	function selectDestIp() {
		var params = new Object();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : '${getDestIpMultSelectUrl}',
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$("#${domId}").html("");
				var opt;
				$.each(data, function(i, n) {
						opt = $('<option />', {
						value : n.DEST_IP,
						text : n.DEST_IP
					});
					opt.appendTo($("#${domId}").multiselect());
				});
				console.log(opt);
				$("#${domId}").multiselect('refresh');
			}
		});
	}
})();
</script>
