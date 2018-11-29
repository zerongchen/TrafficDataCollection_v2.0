<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumn" value="${ctx}/common/POLICY_FLOW_GATHER_STRATEGY/getTableColumnData.do" scope="page" />
<c:set var="getTableColumnData" value="${ctx}/strategy/management/trafficCollectionManagement/initTable.do" scope="page" />
<c:set var="insert" value="${ctx}/strategy/management/trafficCollectionManagement/insert.do" scope="page" />
<c:set var="update" value="${ctx}/strategy/management/trafficCollectionManagement/update.do" scope="page" />
<c:set var="delete" value="${ctx}/strategy/management/trafficCollectionManagement/delete.do" scope="page" />
<c:set var="updateStatus" value="${ctx}/strategy/management/trafficCollectionManagement/updateStatus.do" scope="page" />
<c:set var="export" value="${ctx}/strategy/management/trafficCollectionManagement/export.do" scope="page" />
<c:set var="getFTPUrlData" value="${ctx}/strategy/management/ftpServerManagement/initTable.do" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>流量采集管理</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<style type="text/css">
.error {
	color: red
}

</style>
</head>
<script type="text/javascript">
var getTableColumnUrl = '${getTableColumn}',
    getTableColumnDataUrl = '${getTableColumnData}';
var insertUrl = '${insert}',
     updateUrl = '${update}',
     deleteUrl = '${delete}',
     updateStatusUrl = '${updateStatus}';
var exportUrl = '${export}';
var getFTPUrl = '${getFTPUrlData}';
</script>
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/headerNoTree.jsp" flush="true"></jsp:include>
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con quality_regional">
			<div class="frame">
				<div class="title">
					<i></i><span class="blue">策略配置</span> / <span class="blue">策略管理</span> / <span>流量采集</span>
					<%-- <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_QUERY">
					<button class="fr drop_down">查询</button>
					</sec:authorize> --%>
					<!-- <button class="fr" id="export">导出</button> -->
				</div>
				<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_QUERY">
				<form id="searchForm">
				<div class="time_search " style="display: block;height:120px">
						<div class="fr">
							<button class="search_button fr" id="searchBtn" type="button">查询</button>
						</div>
						<div class="fl option">
							
							<tdc:strategyNameSelect domId="queryStrategyId"  domName="queryStrategyId" ></tdc:strategyNameSelect>
								<tdc:selector switches = "8"></tdc:selector>
							<div>
								<label for="">源IP地址：</label> <input id="querySrcIp" name="querySrcIp" type="text" />
							</div>
							<div>
								<label for="">源端口：</label> <input id="querySrcPort" name="querySrcPort" type="text" />
							</div>
							<div>
								<label for="">目的IP地址：</label> <input id="queryDestIp" name="queryDestIp" type="text" />
							</div>
							<div>
								<label for="">目的端口：</label> <input id="queryDestPort" name="queryDestPort" type="text" />
							</div>
							<tdc:protocolApplicationSelect domId="protocol" domName="queryProtocol"></tdc:protocolApplicationSelect>
							<div>
								<label for="">域名：</label> <input id="queryDomain" name="queryDomain" type="text" />
							</div>
							<div>
								<label for="">URL：</label> <input id="queryUrl" name="queryUrl" type="text" />
							</div>
							<div>
								<label for="">策略创建时间：</label>
								<input id="q_sdate" type="text" name="queryStartTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_edate\')}'});" />
								<input id="q_edate" type="text" name="queryEndTime"
									onfocus="WdatePicker({isShowClear: true,readOnly: true,errDealMode: 2,dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_sdate\')}'});" />
							</div>
							<tdc:ftpServerSelect domId="ftpServerId" domName="queryFTPServerId"></tdc:ftpServerSelect>
							<div>
								<label for="">运行状态：</label>
								<select id="queryStatus" name="queryStatus">
								<option selected="" value="-1">请选择</option>
								<option value="0">未启用</option>
								<option value="1">正常运行</option>
								<option value="2">传输终止</option>
								<option value="3">已停用</option>
								</select>
							</div>
						</div>
					</div>
				</form>
				</sec:authorize>
					<div class="regional">
						<div class="c-traffic fl" id = "c-right">
							<div id="statistical">
								<div class="table-title">
									<div class="w951">
									<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_ADD">
									<button id="add">新增</button>
									</sec:authorize>
									<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_DEL">
									<button id="del">删除</button>
									</sec:authorize>
									</div>
									<div class="box box-success"><div class="box-body"><table id="appTable"></table></div></div>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	
	<div class="popup cloaseD" id="div_layer">
        <div class="windowtitle" >策略<button class="close" id="popup02close">x</button></div>
        <div class="windowform">
        <form id="editForm">
        	<input type="hidden" id = "radioId" value = "-1"/>
        	<input type="hidden" id = "rowIds" name="queryStrategyIds" value = ""/>
        	<input type="hidden" id = "editQueryStatus" name="queryStatus" value = "0"/>
            <div id="strategyIdHide">
                <label >策略ID：</label><label id="editQueryStrategy" ></label><input type="hidden" name="queryStrategyId"/>
            </div>
            <div>
                <label >策略名称：</label><input type="text" id="editQueryStrategyName" name="queryStrategyName"><i class="required">*</i>
            </div>
            <div>
				<tdc:ftpMultiSelect domId="editFTPServerId" />
			</div>
            <div>
                <label >源IP地址：</label><input id="editQuerySrcIp" type="text" name="querySrcIp" placeholder="例如：192.168.13.34">
            </div>
            <div>
                <label >源端口：</label><input id="editQuerySrcPort" name="querySrcPort" type="text" placeholder="例如：8080">
            </div>
            <div>
                <label class="sublabel" for="t01"><input type="radio" id="t01" value="" checked="checked">按业务</label>
                <label class="sublabel" for="t02"><input type="radio" id="t02" value="">按目的IP</label>
                <input type="hidden" id="queryDealType" name="queryDealType" value="1"/>
                <!-- <div class="subform" id="page01"> -->
					<tdc:trafficRecoverSelector catalogId="edit_catalogId" classId="edit_classId" productId="edit_productId" moduleId="edit_moduleId" />                    
                <!-- </div> -->
                	
                <div class="subform" id="page02" style="display: none">
                    <div>
					<label>目的IP：</label> <input type="text" placeholder="例如：192.168.13.34" name="queryDestIp"> 
					<label>目的端口：</label> <input type="text" placeholder="例如：192.168.13.34" name="queryDestPort" onkeyup="this.value=this.value.replace(/[^\d,]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d,]/g,'')" >
					<button type="button" class="smallbtn" name="smalladdbtn">+</button>
					</div>
                </div>
            </div>
            <tdc:protocolApplicationSelect domId="editProtocol" domName="queryProtocol"></tdc:protocolApplicationSelect>
            <div>
                <label >域名(HOST):</label><input input id="editQueryDomain" name="queryDomain" type="text" placeholder="例如：baidu.com http"><i> (协议类必填）</i>
            </div>
            <div>
                <label >URL:</label><input id="editQueryUrl" name="queryUrl" type="text" placeholder="例如：http://baidu.com http"><i> (协议类必填）</i>
            </div>
            <div class="confirm">
                <label></label>
                <button type="button" id ="btnOper">确定</button>
				<button type="button" class="btnwhite" id ="btnCancel">取消</button>
            </div>
        </form>
        </div>
        <div class="bottom"></div>
     </div>
     
     <div class="popup" id="strategy_layer">
        <div class="windowtitle" style="width:97%;">策略详情<button class="close" id="popup03close">x</button></div>
        <div class="windowform">
        <form id="strategyForm">
            <div >
                <label >策略ID：</label><label id="strategyQueryStrategy" name="queryStrategyId"></label>
            </div> 
           <!--  <div>
                <label >策略类别：</label><input type="text" id="strategyQueryStrategyType" name="queryStrategyType">
            </div> -->
            <div>
                <label >策略名称：</label><input type="text" id="strategyQueryStrategyName" name="queryStrategyName">
            </div>
            <div>
                <label >FTP服务器：</label><input type="text" id="strategyqueryFTPServerId" name="queryFTPServerIds">
            </div>
            <div>
                <label >源IP地址：</label><input id="" type="text" name="querySrcIp">
            </div>
            <div>
                <label >源端口：</label><input id="" name="querySrcPort" type="text">
            </div>
            <div>
            	 <tdc:trafficRecoverSelector1 catalogId="layer_catalogId" classId="layer_classId" productId="layer_productId" moduleId="layer_moduleId" />
            </div>
            <div>
                <label >目的IP地址：</label><input type="text" id="" name="queryDestIp">
            </div>
             <div>
                <label >目的端口：</label><input type="text" id="" name="queryDestPort">
            </div>
            <div>
                <label >协议类型：</label><input type="text" id="" name="queryProtocol">
            </div>
             <div>
                <label >域名（HOST）：</label><input type="text" id="" name="queryDomain">
            </div>
             <div>
                <label >URL：</label><input type="text" id="" name="queryUrl">
            </div>
            <input type="reset" style="display:none;"/>
          </form>
          </div>
      </div>
      <div class="popup" id="ftp_layer"  style="width:550px">
        <div class="windowtitle">FTP服务器详情<button class="close" id="popup04close">x</button></div>
			<table id="ftpTable"></table>
      </div>
<script type="text/javascript">
	$(function(){
		$("#c-right").css("width", document.documentElement.clientWidth-230-10-18);
		$("#appTable").css("width", document.documentElement.clientWidth-230-10-18-50);
		statisticsObject.search();
		$('#searchBtn').on('click', function() {
			statisticsObject.search();
		});
	});
		
		$("#add").on("click", function(e) {
			$("#strategyIdHide").hide();
			statisticsObject.initLayer();
			layer.open({
				title : false,
				closeBtn: false,
				type : 1,
				area : ['600px', '500px'],
				shadeClose : false, //点击遮罩关闭
				content : $('#div_layer')
			});
			$(".layui-layer").css("z-index", "100");
			$(".layui-layer-shade").css("z-index", "99");
		});	
		$("#btnOper").on("click", function(e){
    		  if($("#radioId").val() > 0){
    			statisticsObject.update();
    		}else{
    			statisticsObject.insert();
    		}
		});	
		$("#btnCancel").on("click", function(e) {
			statisticsObject.validator.resetForm();
			layer.closeAll();
		});
		$("#popup02close").on("click", function(e) {
			statisticsObject.validator.resetForm();
			layer.closeAll();
		});
		$("#popup03close").on("click", function(e) {
			layer.closeAll();
		});
		$("#popup04close").on("click", function(e) {
			layer.closeAll();
		});
		$("#editForm button[name='smalladdbtn']").on("click", function(e){
			var addhtml = "<div><label>目的IP：</label> <input type=\"text\" placeholder=\"例如：192.168.13.34\" name=\"queryDestIp\">";
			addhtml += "<label>目的端口：</label> <input type=\"text\" placeholder=\"例如：192.168.13.34\"  name=\"queryDestPort\" onkeyup=\"this.value=this.value.replace(/[^\\d,]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d,]/g,'')\" >";
			addhtml += "<button type=\"button\" class=\"smallbtn\" name='smalldelbtn'>-</button></div>";
			var $addobj = $(addhtml);
			$addobj.find("button[name='smalldelbtn']").on("click", function(e){
				$(this).parent().remove();
			});
			$(this).parents(".subform:eq(0)").append($addobj);
		});
        $("#t01").click(function () {
        	$("#editForm #t02").prop("checked", false);
            $("#page01").show();
            $("#page02").hide();
            $("#queryDealType").val("1");
        });
        $("#t02").click(function(){
        	$("#editForm #t01").prop("checked", false);
            $("#page01").hide();
            $("#page02").show();
            $("#queryDealType").val("2");
        });
        $("#del").on("click",function(e){
    		var resultData = $('#appTable').bootstrapTable("getAllSelections");
			if(resultData.length <= 0){
				layer.msg("请选择要删除的数据！");
				return;
			}else{
				var queryStrategyIds = "";
				$.each(resultData, function(idx, item) {
					queryStrategyIds = queryStrategyIds + (queryStrategyIds.length > 0 ? ",": "") + item.STRATEGY_ID;
				});
				$("#rowIds").val(queryStrategyIds);
				layer.confirm('您确定要删除选中的数据吗？', {
						title: '提示',
					  	btn: ['确定','取消'] //按钮
					}, function(){
						statisticsObject.deleteData();
						statisticsObject.search();
					}
				);
			}
    	});
        /* flagArr = [];
        if($("#authorStart").length>0) flagStart = $("#authorStart").val();
        flagArr.push(flagStart); */
        function operateFormatter(value, row, index) {
        	if (row.STATUS == '未启用') {
        		return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
        	} else if (row.STATUS == '正常运行' || row.STATUS == '传输终止') {
        		return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_STOP"><a class="oper_stop" style="cursor:pointer;">停用</a></sec:authorize>';
        	} else if (row.STATUS == '已停用'){
        		return '<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_START"><a class="oper_start" style="cursor:pointer;">启用</a></sec:authorize> <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_EDIT"><a class="oper_edit" style="cursor:pointer;">修改</a></sec:authorize>';
        	}
        	return;
        }
</script>
	<%-- <sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_START">
       <input type="hidden" id="authorStart" value="1"/>
    </sec:authorize>
	<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_STOP">
       <input type="hidden" id="authorStop" value="2"/>
    </sec:authorize>
	<sec:authorize ifAllGranted="ROLE_TRAFFIC_COLLECTION_MANAGEMENT_EDIT">
       <input type="hidden" id="authorEdit" value="3"/>
    </sec:authorize> --%>
	<script type="text/javascript" src="${scriptPath}/view/strategy/trafficCollectionManagement.js"></script> 
</body>
</html>