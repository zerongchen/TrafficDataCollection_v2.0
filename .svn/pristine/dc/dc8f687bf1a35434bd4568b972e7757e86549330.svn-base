<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="domId" type="java.lang.String" required="true"%>
<%@ attribute name="sdomId" type="java.lang.String" required="false"%>
<%@ attribute name="edomId" type="java.lang.String" required="false"%>
<%@ attribute name="sdomName" type="java.lang.String" required="false"%>
<%@ attribute name="edomName" type="java.lang.String" required="false"%>
<%@ attribute name="timePattern" type="java.lang.String" required="false"%>
<%@ attribute name="queryTableName" type="java.lang.String" required="true"%>
<%@ attribute name="btnId" type="java.lang.String" required="false"%>
<%@ attribute name="type" type="java.lang.Integer" required="true"%>

<c:if test="${not empty sdomId}"><c:set var="sdomId" value="${sdomId}" scope="request"/></c:if>
<c:if test="${not empty edomId}"><c:set var="edomId" value="${edomId}" scope="request"/></c:if>
<c:if test="${not empty sdomName}"><c:set var="sdomName" value="${sdomName}" scope="request"/></c:if>
<c:if test="${not empty edomName}"><c:set var="edomName" value="${edomName}" scope="request"/></c:if>
<c:if test="${not empty timePattern}"><c:set var="timePattern" value="${timePattern}" scope="request"/></c:if>
<c:if test="${not empty btnId}"><c:set var="btnId" value="${btnId}" scope="request"/></c:if>
<c:if test="${empty timePattern}">
	<c:if test="${type == 1}"><c:set var="timePattern" value="yyyy-MM-dd HH:mm" scope="request"/></c:if>
	<c:if test="${type == 2}"><c:set var="timePattern" value="yyyy-MM-dd HH" scope="request"/></c:if>
</c:if>
<c:if test="${empty sdomId}"><c:set var="sdomId" value="q_sdate" scope="request"/></c:if>
<c:if test="${empty edomId}"><c:set var="edomId" value="q_edate" scope="request"/></c:if>
<c:if test="${empty sdomName}"><c:set var="sdomName" value="queryStartTime" scope="request"/></c:if>
<c:if test="${empty edomName}"><c:set var="edomName" value="queryEndTime" scope="request"/></c:if>
<c:if test="${empty btnId}"><c:set var="btnId" value="searchBtn" scope="request"/></c:if>

<div class="fl" id="${domId}">
	<c:if test="${type == 1}"><a href="#" class="active">最近1小时</a><a href="#">最近24小时</a> <a href="#">最近7天</a> <a href="#">最近30天</a></c:if>
	<c:if test="${type == 2}"><a href="#" class="active">最近24小时</a> <a href="#">最近7天</a> <a href="#">最近30天</a></c:if>
	<c:if test="${type == 3}"><a href="#" class="active">最近48小时</a> <a href="#">最近7天</a> <a href="#">最近1个月</a></c:if>
	<span class="seperator">|</span> 
	<a class="scope">自定义范围</a>
	<div class="time_scope fl" style="display: none;">
		<label for="">开始时间：</label> 
		<input id="${sdomId}" type="text" onfocus="WdatePicker({isShowClear: false,readOnly: true,errDealMode: 2,dateFmt: '${timePattern}',maxDate:'#F{$dp.$D(\'${edomId}\')}'});" />
		<label for="">至&nbsp;结束时间：</label> 
		<input id="${edomId}" type="text" onfocus="WdatePicker({isShowClear: false,readOnly: true,errDealMode: 2,dateFmt: '${timePattern}',minDate:'#F{$dp.$D(\'${sdomId}\')}'});" />
		<input id="hid_${sdomId}" type = "hidden" name = "${sdomName}"  value = ""  />
        <input id="hid_${edomId}" type = "hidden" name = "${edomName}"  value = ""  />
        <input id="hid_queryTableName" type = "hidden" name = "queryTableName"  value = ""  />
         <button class="search_button fr" id="searchBtn" type="button">查询</button>
	</div>
</div>
<script type="text/javaScript">
(function(){
	var queryTableName = "${queryTableName}";
	$(function(){
		<c:if test="${type == 1}">
		$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
		</c:if>
		<c:if test="${type == 2}">
		$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
		</c:if>
		<c:if test="${type == 3}">
		$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
		</c:if>
		$("#${sdomId}").val("");
   		$("#${edomId}").val("");
		<c:if test="${type == 1}">
   		$("#hid_${sdomId}").val(getDate(-60,0,0));
   		$("#hid_${edomId}").val(getDate(0,0,0));
   		</c:if>
   		<c:if test="${type == 2}">
   		$("#hid_${sdomId}").val(formatDate(getDate(0,0,-1),"H"));
   		$("#hid_${edomId}").val(formatDate(getDate(0,0,0),"H"));
   		</c:if>
   		<c:if test="${type == 3}">
   		$("#hid_${sdomId}").val(getDate(0,0,-2));
   		$("#hid_${edomId}").val(getDate(0,0,0));
   		</c:if>
		$("#${domId} a").on("click", function(e) {
			$("#${domId} a").each(function(i) {
				$(this).attr("class", "");
			});
			$(this).attr("class", "active");
			switch ($(this).html()){
				case "自定义范围" : 
					$(".time_scope").show();
					break;
				<c:if test="${type ne 3}">
				case "最近1小时" : $(".time_scope").hide();
					$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
					$("#hid_${sdomId}").val(getDate(-60,0,0));
					console.log(getDate(-60,0,0));
					$("#hid_${edomId}").val(getDate(0,0,0));
					break;
				case "最近24小时" :
					$(".time_scope").hide();
					$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
					$("#hid_${sdomId}").val(getDate(0,0,-1));
					$("#hid_${edomId}").val(getDate(0,0,0));
					break;
				case "最近7天" : 
					$(".time_scope").hide();
					/*$("#${domId} input[name='queryTableName']").val(queryTableName+"_D");
					$("#hid_${sdomId}").val(formatDate(getDate(0,0,-7),"D"));
					console.log(formatDate(getDate(0,0,-7),"D"));
					$("#hid_${edomId}").val(formatDate(getDate(0,0,0),"D"));*/
					$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
					$("#hid_${sdomId}").val(getDate(0,0,-7));
					console.log(getDate(-60,0,0));
					$("#hid_${edomId}").val(getDate(0,0,0));
					break;
				case "最近30天" : 
					$(".time_scope").hide();
					$("#${domId} input[name='queryTableName']").val(queryTableName+"_H");
					/*$("#hid_${sdomId}").val(formatDate(getDate(0,0,-30),"D"));
					console.log(formatDate(getDate(0,0,-30),"D"));
					$("#hid_${edomId}").val(formatDate(getDate(0,0,0),"D"));*/
					$("#hid_${sdomId}").val(formatDate(getDate(0,0,-30),"H"));
					console.log(formatDate(getDate(0,0,-30),"H"));
					$("#hid_${edomId}").val(formatDate(getDate(0,0,0),"H"));
					break;
				</c:if>
				<c:if test="${type eq 3}">
				case "最近48小时" : 
					$(".time_scope").hide();
					$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
					$("#hid_${sdomId}").val(getDate(0,0,-2));
					$("#hid_${edomId}").val(getDate(0,0,0));
					break;
				case "最近7天" : 
					$(".time_scope").hide();
					$("#${domId} input[name='queryTableName']").val(queryTableName+"_MIN");
					$("#hid_${sdomId}").val(getDate(0,0,-7));
					$("#hid_${edomId}").val(getDate(0,0,0));
					break;
				case "最近1个月" : 
					$(".time_scope").hide();
					$("#${domId} input[name='queryTableName']").val(queryTableName+"_H");
					$("#hid_${sdomId}").val(formatDate(getDateInIndex(0,0,0,-1),"H"));
					$("#hid_${edomId}").val(formatDate(getDateInIndex(0,0,0,0),"H"));
					break;
				</c:if>
			}
			
				if($(this).html() != "自定义范围"){
					statisticsObject.search();
					trendObject.search();
				}
		
			
		});

		$("#${btnId}").on("click", function(e){
			if($(".time_scope").css("display") != "none"){
				if($("#${sdomId}").val() == '' || $("#${edomId}").val() == ''){
					$.modal.alert("自定义查询时间不能为空");
					return;
				}
			}
   			$("#${domId} a").each(function(i){
   				if($(this).attr("class") == "active"){
   					if($(this).html() == "自定义范围"){
   						var betweentime = getBetweenTime($("#${sdomId}").val(),$("#${edomId}").val());
   						var formattype = "";
   						<c:if test="${type == 1}">
	   						if(betweentime <= 86400){
	   							$("input[name='queryTableName']").val(queryTableName+"_MIN");
	   						}else if(betweentime > 86400 && betweentime <= 604800){
	   							$("input[name='queryTableName']").val(queryTableName+"_H");
	   							formattype = "H"
	   						}else if(betweentime > 604800){
	   							$("input[name='queryTableName']").val(queryTableName+"_D");
	   							formattype = "D"
	   						}
   						</c:if>
   						<c:if test="${type == 2}">
	   						if(betweentime <= 86400){
       							$("input[name='queryTableName']").val(queryTableName+"_MIN");
       						}else if(betweentime > 86400 && betweentime <= 604800){
       							$("input[name='queryTableName']").val(queryTableName+"_H");
       							formattype = "H";
       						}else if(betweentime > 604800){
       							$("input[name='queryTableName']").val(queryTableName+"_D");
       							formattype = "D";
       						}
   						</c:if>
   						<c:if test="${type == 3}">
   						var month3 = getBetweenTime(getDateInIndex(0,0,0,-1), getDateInIndex(0,0,0,0));//获取3个月的分钟数
   						if(betweentime <=  604800){
   							$("input[name='queryTableName']").val(queryTableName+"_MIN");
   							formattype = "MIN"
   						}else if(betweentime > 604800 && betweentime <= month3){
   							$("input[name='queryTableName']").val(queryTableName+"_H");
   							formattype = "H"
   						}else if(betweentime > month3){
   							$("input[name='queryTableName']").val(queryTableName+"_D");
   							formattype = "D"
   						}
						</c:if>
   						if($("input[name='queryTableName']").val().substring($("input[name='queryTableName']").val().lastIndexOf("_")+1) != "MIN"){
   		       				$("#hid_${sdomId}").val(formatDate($("#${sdomId}").val(),formattype));
   		       				$("#hid_${edomId}").val(formatDate($("#${edomId}").val(),formattype));
   		       			}else{
       		       			$("#hid_${sdomId}").val($("#${sdomId}").val());
   		       				$("#hid_${edomId}").val($("#${edomId}").val());
   		       			}
   					}
   				}
   			});
		});
	});
})();
</script>
