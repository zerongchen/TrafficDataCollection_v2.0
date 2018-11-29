<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<c:set var="updateBizParentUrl" value="${ctx}/serviceConfiguration/bizManage/updateBizParent.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>中移动流量采集监测系统-业务配置-业务架构配置</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">
<%-- <link rel="stylesheet" href="${scriptPath}/zTree_v3-master/css/zTreeStyle/zTreeStyle.css"> --%>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<script type="text/javascript">
var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			//selectedMulti: true
		},
		edit: {
			enable: true,
			removeTitle:"删除",
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeRemove: beforeRemove,
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop
		}
	};
	var targetNodeId = 0;
	var dragNodeId = 0;
	var dragNodePId = 0;
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}else{
				dragNodeId = treeNodes[i].id;
				dragNodePId = treeNodes[i].pId;
			}
		}
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		targetNodeId = targetNode.id;
		if(dragNodePId == targetNodeId){
			layer.msg("无效操作");
			return false;
		}else{
			return statisticsObject.updateBizParent('${updateBizParentUrl}',dragNodeId,targetNodeId);
		}
		//return targetNode ? targetNode.drop !== false : true;
	}
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0|| $("#editBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var addBtn = $("#addBtn_"+treeNode.tId);
		if (addBtn) {
			addBtn.bind("click", function(){
				alert("这里写新增节点调用方法,当前节点参数可以用treeNode.出来");
				return false;
			});
		}
		if(treeNode.level != 0){   
			var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId + "' title='修改' onfocus='this.blur();'></span>";
			sObj.after(editStr);
			var editBtn = $("#editBtn_"+treeNode.tId);
			if (editBtn) {
				editBtn.bind("click", function(){
					alert("这里写修改节点调用方法,当前节点参数可以用treeNode.出来");
					return false;
				});
			}
		}
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
		$("#editBtn_"+treeNode.tId).unbind().remove();
	};
	function showRemoveBtn(treeId, treeNode){
		if(treeNode.level == 0)
			return false;
		else
			return true;
	}
	function beforeRemove(treeId, treeNode) {
		alert("这里写删除调用方法 判断后台是否删除,当前节点参数可以用treeNode.出来");
		//这里写删除调用方法 判断后台是否删除
		return false;//返回false 树不删除节点  返回true 树自动删除节点
	}
	$(function() {	
		var zNodes  =  statisticsObject.getAllNodes();
		//console.log(zNodes1);
		$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));	
		$("#c-height").css("height","100%");
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<div class="con computer_room">
			<div class="frame">
				<div class="title">
					<i></i> <span class="blue">业务配置</span> / <span>业务架构配置</span>
					<button class="fr drop_down">查询</button>
				</div>
				<div class="time_search" style="display: none;">
					<div class="fl option">
						<div>
							<label for="">业务名称：</label> <input type="text" id="text_name" value="" />
						</div>
					</div>
					<div class="fr">
						<button class="search_button fr" id="searchBtn" type="button">查询</button>
					</div>
					<div style="clear: both"></div>
				</div>
				<div class="computer_room">
					
					<div class="c-nav" style="height:100%;">
						<div>
							<ul id="treeSelect" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="popup" id="popup01">
        <div class="windowtitle">业务<button class="close" id="popup01close">x</button></div>
        <div class="windowform">
        <form id="operForm">
        	<input type="hidden" name="queryCatalogId" id = "hid_queryCatalogId" />
            <input type="hidden" name="queryClassId" id = "hid_queryClassId" />
            <input type="hidden" name="queryProductId" id = "hid_queryProductId" />
            <input type="hidden" name="queryModuleId" id = "hid_queryModuleId" />
            
            <input type="hidden"  id = "hid_operType" value = "0"/>
            <input type="hidden"  id = "hid_operTable" value = ""/>
           <tdc:selectorRoomBuild></tdc:selectorRoomBuild>
            <div>
                <label >名称：</label>
                <input type="text" name="queryName" id = "text_queryName" value = "">
                 <i class="required">*</i>
            </div>
            <div class="confirm">
                <label></label>
                <button type="button" id = "btnOper">确定</button>
				<button type="button" class="btnwhite" id = "btnCancel">取消</button>
			</div>
          </form>
            
        </div>
        <div class="bottom"></div>
    </div>
  <script type="text/javascript">	
	
	
	</script>
	<script src="${scriptPath}/view/serviceConfiguration/bizMange.js" type="text/javascript"></script> 
</body>
</html>