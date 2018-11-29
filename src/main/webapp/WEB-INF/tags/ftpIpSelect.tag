<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="domName" type="java.lang.String" required="false"%>
<div>
    <label for="">IP地址：</label>
    <select <c:if test="${not empty domId}">id = "${domId}"</c:if> <c:if test="${not empty domName}">name = "${domName}"</c:if>></select> 
</div>
<script type="text/javascript">
(function(){
	$(function(){
		selectFTPIp();
	});
	function selectFTPIp(){
		var params = new Object();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectFTPIp.do',
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				var opt;
				opt = opt+"<option value='-1'>请选择</option>";
				$("#${domId}").html("");
				$.each(data, function(idx, item) {
					opt = opt+"<option value='"+item.ip+"'>"+item.ip+"</option>";
				});
				$("#${domId}").append(opt);
			}
		});
	}
})();
</script>
