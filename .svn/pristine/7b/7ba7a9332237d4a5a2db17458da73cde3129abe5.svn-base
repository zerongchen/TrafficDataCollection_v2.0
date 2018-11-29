<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="domName" type="java.lang.String" required="false"%>
<c:if test="${empty domId}"><c:set var="domId" value="ftpSelectId" scope="request"/></c:if>
<c:if test="${empty domName}"><c:set var="domName" value="queryFTPServerIds" scope="request"/></c:if>
<div class="ftpMultiSelect">
    <label for="">FTP服务器：</label>
    <select id = "${domId}" name = "${domName}" multiple="multiple"></select> 
</div>
<script type="text/javascript">
var ftpMultiSelect = new Object();
(function(){
	$(function(){
		$(".ftpMultiSelect #${domId}").multiselect({
			noneSelectedText : "请选择FTP服务器",
			checkAllText : "全选",
			uncheckAllText : '全不选'
		});
		selectFTPIp();
	});
	function selectFTPIp(){
		var params = new Object();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectFTPServer.do',
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				var selectvalue = [];
				$(".ftpMultiSelect #${domId}").html("");
				var opt;
				$.each(data, function(i, n) {
						opt = $('<option />', {
						value : n.ftpId,
						text : n.ftpName
					});
					opt.appendTo($("#${domId}").multiselect());
					selectvalue.push(n.ftpId);
				});
				//$(".ftpMultiSelect #${domId}").val(selectvalue);
				$(".ftpMultiSelect #${domId}").multiselect('refresh');
			}
		});
	}
	ftpMultiSelect.selectFTPIp = selectFTPIp;
})();
</script>
