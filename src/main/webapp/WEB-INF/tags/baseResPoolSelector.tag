<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="catalogId" type="java.lang.String" required="false"%>
<%@ attribute name="classId" type="java.lang.String" required="false"%>
<%@ attribute name="productId" type="java.lang.String" required="false"%>
<%@ attribute name="moduleId" type="java.lang.String" required="false"%>
<%@ attribute name="pageId" type="java.lang.String" required="false"%>
<%@ attribute name="provinceId" type="java.lang.String" required="false"%>
<%@ attribute name="carrierId" type="java.lang.String" required="false"%>
<%@ attribute name="switches" type="java.lang.Integer" required="false"%>


        <div id ="firstSelector">
            <label for="">业务类别：</label>
            <select name="queryCatalogId" id="catalog" class="maps">
                <option selected="" value="-1">全部</option>
            </select>
        </div>
        <div id ="secondSelector">
            <label for="">业务：</label>
            <select name="queryClassId" id="class" class="operator">
                <option selected="" value="-1">全部</option>
            </select>
        </div>
        <div id ="thirdSelector">
            <label for="">产品：</label>
            <select name="queryProductId" id="product" class="operator">
                <option selected="" value="-1">全部</option>
            </select>
        </div>
         <div id ="fourthSelector">
       		<label>功能模块：</label>
 			 <select name="queryModuleId" id="module" class="operator">
    		  <option value="-1">请选择</option>
  			</select>
 	      </div>
 	      <div id ="fifthSelector" style = "display:none;" >
		  <label >页面：</label>
     		 <select name="queryPageId" id="page" class="operator">
      		    <option value="-1">请选择</option>
    		 </select>
    	 </div>
         <div id="regionId">
            <label for="">地域：</label>
                <select name="queryProvinceId" id="province" >
                    <option value="-1">全部</option>
                </select>
        </div>
        <div id="carrierId">
            <label for="">运营商：</label>
            <select name="queryCarrierId" id="carrier">
                <option selected="" value="-1">全部</option>
            </select>
        </div>

<script type="text/javascript">
var selector_001 = new Object();
(function(){
	$(document).ready(function() {
		selector_001.selectCatalog = selectCatalog;
		if('${switches}' == '1'){
			$("#fourthSelector").hide();
			$("#fifthSelector").hide();
		} else if('${switches}' == '2'){
			$("#fourthSelector").hide();
			$("#fifthSelector").hide();
			$("#regionId").hide();
			$("#carrierId").hide();
		} else if('${switches}' == '3'){
			$("#fourthSelector").hide();
			$("#fifthSelector").hide();
			//$("#regionId").hide();
			$("#carrierId").hide();
		} else if('${switches}' == '4'){
			$("#fourthSelector").hide();
			$("#fifthSelector").hide();
			$("#regionId").hide();
			//$("#carrierId").hide();
		} else if('${switches}' == '5'){
			$("#fourthSelector").hide();
			$("#fifthSelector").hide();
			//$("#regionId").hide();
			$("#carrierId").hide();
		}else if('${switches}' == '6'){
			$("#fifthSelector").hide();
		}else if('${switches}' == '7'){
			$("#regionId").hide();
			$("#carrierId").hide();
		}else if('${switches}' == '8'){
			$("#fifthSelector").hide();
			$("#regionId").hide();
			$("#carrierId").hide();
		}else if('${switches}' == '9'){
			$("#firstSelector").hide();
			$("#secondSelector").hide();
			$("#thirdSelector").hide();
			$("#fourthSelector").hide();
			$("#fifthSelector").hide();
		}
		selectProvince();
		selectCarrier();
		selectCatalog();
		
		$("#catalog").bind("change", function catalogValue(){
			catalogId = $(this).val();
			$("#class").empty().append("<option value=\"-1\">全部</option>");
			$("#product").empty().append("<option value=\"-1\">全部</option>");
			$("#module").empty().append("<option value=\"-1\">全部</option>");
			//$("#page").empty().append("<option value=\"-1\">全部</option>");
			selectClass(catalogId);
			return catalogId;
		});
		$("#class").bind("change", function classValue(){
			classId = $(this).val();
			$("#product").empty().append("<option value=\"-1\">全部</option>");
			$("#module").empty().append("<option value=\"-1\">全部</option>");
			//$("#page").empty().append("<option value=\"-1\">全部</option>");
			selectProduct(catalogId, classId);
			return classId;
		});
		$("#product").bind("change", function productValue(){
			productId = $(this).val();
			$("#module").empty().append("<option value=\"-1\">全部</option>");
			//$("#page").empty().append("<option value=\"-1\">全部</option>");
			selectModule(catalogId, classId, productId);
			return productId;
		});
		/* $("#module").bind("change", function(){
			selectPage($(this).val());
			$("#page").empty().append("<option value=\"-1\">全部</option>");
		}); */
		
	});
	//地域
	function selectProvince() {
		$("#province").empty();
		$("#province").append('<option selected="" value="-1">全部</option>');
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectProvince.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#province").append(
							"<option value=\"" + n.positionId + "\">"
									+ n.positionName + "</option>");
				});
			}
		});
	}
	//运营商
	function selectCarrier() {
		$("#carrier").empty();
		$("#carrier").append('<option selected="" value="-1">全部</option>');
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectCarrier.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#carrier").append(
							"<option value=\"" + n.carrierId + "\">"
									+ n.carrierName + "</option>");
				});
			}
		});
	}
	//业务类别
	function selectCatalog(callback) {
		$("#catalog").empty();
		$("#catalog").append('<option selected="" value="-1">全部</option>');
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			url : ctx + '/common/selectCatalog.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					if(n.catalogId == 2 || n.catalogId == 4){
						$("#catalog").append(
								"<option value=\"" + n.catalogId + "\">"
										+ n.cataName + "</option>");
					}
				});
				if(callback != undefined && callback!=null){
					callback();
				}
			}
		});
	}
	//业务
	function selectClass(catalogId, callback) {
		$("#class").empty();
		$("#class").append('<option selected="" value="-1">全部</option>');
		if(catalogId == -1) return;
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			data: {catalogId:catalogId},
			url : ctx + '/common/selectClass.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#class").append(
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
		$("#product").empty();
		$("#product").append('<option selected="" value="-1">全部</option>');
		if(classId == -1) return;
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
					$("#product").append(
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
		$("#module").empty();
		$("#module").append('<option selected="" value="-1">全部</option>');
		if(productId == -1) return;
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
					$("#module").append(
							"<option value=\"" + n.moduleId + "\">"
									+ n.moduleName + "</option>");
				});
				if(callback != undefined && callback!=null){
					callback();
				}
			}
		});
	}
	//页面
	function selectPage(moduleId, callback) {
		$("#page").empty();
		$("#page").append('<option selected="" value="-1">全部</option>');
		if(moduleId == -1) return;
		$.ajax({
			type : "POST",
			async : true,
			dataType : 'json',
			data: {moduleId:moduleId},
			url : ctx + '/common/selectPage.do',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$.each(data, function(i, n) {
					$("#page").append(
							"<option value=\"" + n.pageId + "\">"
									+ n.pageName + "</option>");
				});
				if(callback != undefined && callback!=null){
					callback();
				}
			}
		});
	}
})();

	
</script>
