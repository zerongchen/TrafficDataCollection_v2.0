<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="domName" type="java.lang.String" required="false"%>
<%@ attribute name="getCarrierOptionDataUrl" type="java.lang.String" required="true"%>
<div>
    <label for="">运营商：</label>
    <select <c:if test="${not empty domId}">id = "${domId}"</c:if> <c:if test="${not empty domName}">name = "${domName}"</c:if>></select> 
</div>
<script type="text/javascript">
(function(){
	$(function(){
		selectCarrier();
	});
	
	function selectCarrier(){
		var params = new Object();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : '${getCarrierOptionDataUrl}',
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				var opt;
				opt = opt+"<option value='-1'>请选择</option>";
				$("#${domId}").html("");
				$.each(data, function(idx, item) {
					if(item.carrierId == 0) return true;
					opt = opt+"<option value='"+item.carrierId+"'>"+item.carrierName+"</option>";
				});
				$("#${domId}").append(opt);
			}
		});
	}
})();
</script>
