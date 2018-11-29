<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="catalogId" type="java.lang.String" required="false"%>
<%@ attribute name="classId" type="java.lang.String" required="false"%>
<%@ attribute name="productId" type="java.lang.String" required="false"%>
<%@ attribute name="moduleId" type="java.lang.String" required="false"%>
<%@ attribute name="switches" type="java.lang.Integer" required="false"%>

<div>
                <label >第一级：业务类别：</label>
                <select name="queryCatalogId" id="catalogLayer" class="maps">
                <option selected="" value="-1">请选择</option>
            	</select>
                <i class="required">*</i>
            </div>

            <div>
                <label >第二级：业务：</label>
                 <select name="queryClassId" id="classLayer" class="operator">
                <option selected="" value="-1">请选择</option>
            </select>
                <i class="required">*</i>
            </div>

            <div>
                <label >第三级：产品：</label>
                <select name="queryProductId" id="productLayer" class="operator">
                <option selected="" value="-1">请选择</option>
            </select>
                <i class="required">*</i>
            </div>

            <div>
                <label >第四级：功能模块：</label>
              <select name="queryModuleId" id="moduleLayer" class="operator">
    		  <option value="-1">请选择</option>
  			</select>
    		  <i class="required">*</i>
            </div>

<script type="text/javascript">
	$(document).ready(function() {
		selectLayerCatalog();
		//$("#catalogLayer").bind("change", function(){
		//	selectLayerClass($(this).val(),true);
		//	$("#classLayer").empty().append("<option value=\"-1\">请选择</option>");
		//	$("#productLayer").empty().append("<option value=\"-1\">请选择</option>");
		//	$("#moduleLayer").empty().append("<option value=\"-1\">请选择</option>");
		//});
		//$("#classLayer").bind("change", function(){
		//	selectLayerProduct($(this).val(),true);
		//	$("#productLayer").empty().append("<option value=\"-1\">请选择</option>");
		//	$("#moduleLayer").empty().append("<option value=\"-1\">请选择</option>");
		//});
		//$("#productLayer").bind("change", function(){
		//	selectLayerModule($(this).val(),true);
		//	$("#moduleLayer").empty().append("<option value=\"-1\">请选择</option>");
		//});
		
		
		$("#catalogLayer").bind("change", function catalogValue(){
			var catalogId = $(this).val();
			selectLayerClass(catalogId,true);
			$("#classLayer").empty().append("<option value=\"-1\">全部</option>");
			$("#productLayer").empty().append("<option value=\"-1\">全部</option>");
			$("#moduleLayer").empty().append("<option value=\"-1\">全部</option>");
			
				$("#classLayer").bind("change", function(){
				var classId = $(this).val();
				selectLayerProduct(catalogId, classId,true);
				$("#productLayer").empty().append("<option value=\"-1\">全部</option>");
				$("#moduleLayer").empty().append("<option value=\"-1\">全部</option>");
				
					$("#productLayer").bind("change", function(){
						var productId = $(this).val();
						selectLayerModule(catalogId, classId, productId,true);
						$("#moduleLayer").empty().append("<option value=\"-1\">全部</option>");
						
					});
				});
		});
		
	});
	//地域
	
	//业务类别
	function selectLayerCatalog(callback) {
		$("#catalogLayer").empty();
		$("#catalogLayer").append('<option selected="" value="-1">请选择</option>');
		$.ajax({
			type : "POST",
			async : false,
			dataType : 'json',
			url : ctx + '/common/selectCatalog.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#catalogLayer").append(
							"<option value=\"" + n.catalogId + "\">"
									+ n.cataName + "</option>");
				});
				if(callback != undefined && callback!=null){
					callback();
				}
			}
		});
	}
	//业务
	function selectLayerClass(catalogId,isAsync) {
		$("#classLayer").empty();
		$("#classLayer").append('<option selected="" value="-1">请选择</option>');
		if(catalogId == -1) return;
		$.ajax({
			type : "POST",
			async : isAsync,
			dataType : 'json',
			data: {catalogId:catalogId},
			url : ctx + '/common/selectClass.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#classLayer").append(
							"<option value=\"" + n.classId + "\">"
									+ n.className + "</option>");
				});
				//if(callback != undefined && callback!=null){
				//	callback();
				//}
			}
		});
	}
	//产品
	function selectLayerProduct(catalogId, classId,isAsync) {
		$("#productLayer").empty();
		$("#productLayer").append('<option selected="" value="-1">请选择</option>');
		if(classId == -1) return;
		$.ajax({
			type : "POST",
			async : isAsync,
			dataType : 'json',
			data: {catalogId:catalogId,
				   classId:classId
				   },
			url : ctx + '/common/selectProduct.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#productLayer").append(
							"<option value=\"" + n.productId + "\">"
									+ n.productName + "</option>");
				});
				//if(callback != undefined && callback!=null){
				//	callback();
				//}
			}
		});
	}
	//功能模块
	function selectLayerModule(catalogId, classId, productId,isAsync) {
		$("#moduleLayer").empty();
		$("#moduleLayer").append('<option selected="" value="-1">请选择</option>');
		if(productId == -1) return;
		$.ajax({
			type : "POST",
			async : isAsync,
			dataType : 'json',
			data: {catalogId:catalogId,
				   classId:classId,
					productId:productId},
			url : ctx + '/common/selectModule.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#moduleLayer").append(
							"<option value=\"" + n.moduleId + "\">"
									+ n.moduleName + "</option>");
				});
				//if(callback != undefined && callback!=null){
				//	callback();
				//}
			}
		});
	}

</script>
