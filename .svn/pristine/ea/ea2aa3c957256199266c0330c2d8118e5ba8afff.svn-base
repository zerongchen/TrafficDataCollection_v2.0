<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="domName" type="java.lang.String" required="false"%>
<div>
    <label for="">FTP服务器名称：</label>
    <select <c:if test="${not empty domId}">id = "${domId}"</c:if> <c:if test="${not empty domName}">name = "${domName}"</c:if>></select> 
</div>
<script type="text/javascript">
(function(){
	$(function(){
		selectFTPServer();
	});
	function selectFTPServer(){
		var params = new Object();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectFTPServer.do',
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				var opt;
				opt = opt+"<option value='-1'>请选择</option>";
				$("#${domId}").html("");
				$.each(data, function(idx, item) {
					opt = opt+"<option value='"+item.ftpId+"'>"+item.ftpName+"</option>";
				});
				$("#${domId}").append(opt);
			}
		});
	}
})();
</script>
