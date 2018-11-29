<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="product" type="java.lang.String" required="false"%>
<%@ attribute name="module" type="java.lang.String" required="false"%>

            <div>
                <label >产品：</label>
                <select name="queryProductId" id="productId" class="operator">
                <option selected="" value="-1">请选择</option>
            	</select>
            </div>
            <div>
                <label >业务板块：</label>
                <select name="queryModuleId" id="moduleId" class="operator">
    		    <option value="-1">请选择</option>
  			    </select>
            </div>

<script type="text/javascript">
	$(document).ready(function() {
		selectPageProduct();
		$("#productId").bind("change", function(){
			selectPageModule($(this).val());
			$("#moduleId").empty().append("<option value=\"-1\">请选择</option>");
		});
	});
	//产品
	function selectPageProduct() {
		$("#productId").empty();
		$("#productId").append('<option selected="" value="-1">请选择</option>');
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectPageProduct.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#productId").append(
							"<option value=\"" + n.pageProductId + "\">"
									+ n.pageProductName + "</option>");
				});
			}
		});
	}
	//功能模块
	function selectPageModule(pageProductId) {
		$("#moduleId").empty();
		$("#moduleId").append('<option selected="" value="-1">请选择</option>');
		if(pageProductId == -1) return;
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			data: {pageProductId:pageProductId},
			url : ctx + '/common/selectPageModule.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#moduleId").append(
							"<option value=\"" + n.pageModuleId + "\">"
									+ n.pageModuleName + "</option>");
				});
			}
		});
	}

</script>
