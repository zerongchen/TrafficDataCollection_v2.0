<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="catalogId" type="java.lang.String" required="false"%>
<%@ attribute name="classId" type="java.lang.String" required="false"%>
<%@ attribute name="productId" type="java.lang.String" required="false"%>
<%@ attribute name="moduleId" type="java.lang.String" required="false"%>

<c:if test="${empty catalogId}"><c:set var="catalogId" value="catalog" scope="request"/></c:if>
<c:if test="${empty classId}"><c:set var="classId" value="class" scope="request"/></c:if>
<c:if test="${empty productId}"><c:set var="productId" value="product" scope="request"/></c:if>
<c:if test="${empty moduleId}"><c:set var="moduleId" value="module" scope="request"/></c:if>

<div class="subform" id="page05" >
	<label>业务类别：</label> 
	<select name="queryCatalogId" id="${catalogId}">
		<option selected="selected" value="0">全部</option>
	</select> 
	<label>业务：</label> 
	<select name="queryClassId" id="${classId}">
		<option selected="selected" value="0">全部</option>
	</select><br> 
	<label>产品：</label> 
	<select name="queryProductId" id="${productId}">
		<option selected="selected" value="0">全部</option>
	</select> 
	<label>功能模块：</label> 
	<select name="queryModuleId" id="${moduleId}">
		<option selected="selected" value="0">全部</option>
	</select>
</div>
<script type="text/javascript">
var tag_trafficRe1 = new Object();
(function(){
	$(function(){
		selectCatalog();
		$("#page05 #${catalogId}").bind("change", function catalogValue(){
			catalogId = $(this).val();
			$("#page05 #${classId}").empty().append("<option value=\"0\">全部</option>");
			$("#page05 #${productId}").empty().append("<option value=\"0\">全部</option>");
			$("#page05 #${moduleId}").empty().append("<option value=\"0\">全部</option>");
			selectClass(catalogId);
			/* return catalogId; */
		});
			$("#page05 #${classId}").bind("change", function classValue(){
				classId = $(this).val();
				$("#page05 #${productId}").empty().append("<option value=\"0\">全部</option>");
				$("#page05 #${moduleId}").empty().append("<option value=\"0\">全部</option>");
				selectProduct(catalogId, classId);
				return classId;
			});
				$("#page05 #${productId}").bind("change", function productValue(){
					productId = $(this).val();
					$("#page05 #${moduleId}").empty().append("<option value=\"0\">全部</option>");
					selectModule(catalogId, classId, productId);
					return productId;
				});
	});

	//业务类别
	function selectCatalog(callback) {
		$("#page05 #${catalogId}").empty();
		$("#page05 #${catalogId}").append('<option selected="" value="0">全部</option>');
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectCatalog.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#page05 #${catalogId}").append(
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
	function selectClass(catalogId, callback) {
		$("#page05 #${classId}").empty();
		$("#page05 #${classId}").append('<option selected="" value="0">全部</option>');
		if(catalogId == 0) return;
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			data: {catalogId:catalogId},
			url : ctx + '/common/selectClass.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#page05 #${classId}").append(
							"<option value=\"" + n.classId + "\">"
									+ n.className + "</option>");
				});
				if(callback != undefined && callback!=null){
					callback();
				}
			}
		});
	}
	//产品
	function selectProduct(catalogId, classId, callback) {
		$("#page05 #${productId}").empty();
		$("#page05 #${productId}").append('<option selected="" value="0">全部</option>');
		if(classId == 0) return;
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			data: {catalogId:catalogId,
				   classId:classId
			   	  },
			url : ctx + '/common/selectProduct.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#page05 #${productId}").append(
							"<option value=\"" + n.productId + "\">"
									+ n.productName + "</option>");
				});
				if(callback != undefined && callback!=null){
					callback();
				}
			}
		});
	}
	//功能模块
	function selectModule(catalogId, classId, productId, callback) {
		$("#page05 #${moduleId}").empty();
		$("#page05 #${moduleId}").append('<option selected="" value="0">全部</option>');
		if(productId == 0) return;
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			data: {catalogId:catalogId,
				   classId:classId,
					productId:productId},
			url : ctx + '/common/selectModule.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#page05 #${moduleId}").append(
							"<option value=\"" + n.moduleId + "\">"
									+ n.moduleName + "</option>");
				});
				if(callback != undefined && callback!=null){
					callback();
				}
			}
		});
	}
	tag_trafficRe1.selectCatalog = selectCatalog;
	tag_trafficRe1.selectClass = selectClass;
	tag_trafficRe1.selectProduct = selectProduct;
	tag_trafficRe1.selectModule = selectModule;
})()
	
</script>
