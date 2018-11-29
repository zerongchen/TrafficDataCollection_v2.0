<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="domName" type="java.lang.String" required="false"%>
<%@ attribute name="getCarrierMultSelectUrl" type="java.lang.String" required="true"%>
<div>
    <label for="">运营商：</label>
    <select <c:if test="${not empty domId}">id = "${domId}"</c:if> <c:if test="${not empty domName}">name = "${domName}"</c:if> multiple="multiple"></select> 
</div>

<script type="text/javascript">
(function(){
	$(function(){
		$("#sel_carrier").multiselect({
			noneSelectedText : "全部运营商",
			checkAllText : "全选",
			uncheckAllText : '全不选'
		});
		selectCarrier();
	});
	function selectCarrier() {
		var params = new Object();
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : '${getCarrierMultSelectUrl}',
			data : params,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				var selectvalue = [];
				$("#${domId}").html("");
				var opt;
				$.each(data, function(i, n) {
					if(n.carrierId != 0 && n.carrierId != 999){
						opt = $('<option />', {
						value : n.carrierId,
						text : n.carrierName
						
					});
					opt.appendTo($("#${domId}").multiselect());
					selectvalue.push(n.carrierId);
					}
				});
				console.log(opt);
				$("#${domId}").val(selectvalue);
				$("#${domId}").multiselect('refresh');
			}
		});
	}
})();
</script>
