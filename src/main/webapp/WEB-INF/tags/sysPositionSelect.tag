<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="domName" type="java.lang.String" required="false"%>
<%@ attribute name="getPositionOptionDataUrl" type="java.lang.String" required="true"%>
<div>
    <label for="">地域：</label>
    <select <c:if test="${not empty domId}">id = "${domId}"</c:if> <c:if test="${not empty domName}">name = "${domName}"</c:if> multiple="multiple"></select> 
</div>

<script type="text/javascript">
(function(){
	$(function(){
		$("#sel_position").multiselect({
			noneSelectedText : "全部地域",
			checkAllText : "全选",
			uncheckAllText : '全不选'
		});
		selectPosition();
	});
	function selectPosition() {
		var params = new Object();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : '${getPositionOptionDataUrl}',
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				var selectvalue = [];
				$("#${domId}").html("");
				var opt;
				$.each(data, function(i, n) {
					if(n.positionId != 0 && n.positionId != 999){
						opt = $('<option />', {
							value : n.positionId,
							text : n.positionName
						});
						opt.appendTo($("#${domId}").multiselect());
						selectvalue.push(n.positionId);
					}
				});
				$("#${domId}").val(selectvalue);
				$("#${domId}").multiselect('refresh');
			}
		});
	}
})();
</script>
