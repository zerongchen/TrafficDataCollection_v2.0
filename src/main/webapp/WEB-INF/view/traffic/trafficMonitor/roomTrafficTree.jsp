<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION/getTableColumnData.do" scope="page" />
<c:set var="updateColumnUser"
	value="${ctx}/common/FLOW_DIRECTION/updateColumnUser.do"
	scope="request" />
<c:set var="getTrendTableColumnData"
	value="${ctx}/common/FLOW_DIRECTION_TREND/getTableColumnData.do"
	scope="page" />
<c:set var="updateTrendColumnUser"
	value="${ctx}/common/FLOW_DIRECTION_TREND/updateColumnUser.do"
	scope="request" />
<c:set var="initTable"
	value="${ctx}/quality/netQualityAnalysis/areaQuality/initTable.do"
	scope="request" />
<div class="c-nav" id="c-height">
	<ul id="treeSelect" class="ztree"></ul>
</div>
<script type="text/javascript">
	(function() {
		$(function() {
			$("#c-height").css("height", document.body.scrollHeight-70);
			//$('.c-nav').css('height',$(window).height()-70);
			$("#c-right").css("width", document.documentElement.clientWidth-230-230-10-18);
			$("#appTable_up").css("width", document.documentElement.clientWidth-230-230-10-18-50);
			$("#appTable_dn").css("width", document.documentElement.clientWidth-230-230-10-18-50);
			$.getJSON(getTreeDataUrl, function(data){
				var zNodes = new Array();
				var nodeData1 = new Object();//机楼节点数据
				var nodeData2 = new Object();//机房节点数据
				var openNode = new Object();
				// >> 拼接二级业务节点
				$.each(data, function(idx, item){
					if(item.serverBuildName == null) return true;
					nodeData1[item.serverBuildId] = item.serverBuildName;
					if(item.serverRoomName == null) return true;
					nodeData2[item.serverBuildId+"-"+item.serverRoomId] = item.serverRoomName;
					if(item.className == null) return true;
					var zNode = new Object();
					zNode.id = item.serverBuildId+"-"+item.serverRoomId+"-"+item.classId;
					zNode.name = item.className;
					zNode.pId = item.serverBuildId+"-"+item.serverRoomId;
					if(idx == 0) {
						openNode.node2Id = zNode.pId;
						openNode.node1Id = item.serverBuildId;
					}
					var flag = 0;
					$.each(zNodes, function(idx, item){
						if(item.id == zNode.id){
							flag = 1;
							return false;
						}
					});
					if(flag == 1) return true;
					zNodes.push(zNode);
				});
				// << 
				// >> 拼接机房节点
				$.each(nodeData2, function(idx, item){
					var zNode = new Object();
					//if(openNode.node2Id == idx) zNode.open = true;
					zNode.id = idx;
					zNode.name = item;
					zNode.pId = idx.split("-")[0];
					zNode.isParent = false;
					zNodes.push(zNode);
				});
				// <<
				// >> 拼接机楼节点
				$.each(nodeData1, function(idx, item){
					var zNode = new Object();
					//if(openNode.node1Id == idx) zNode.open = true;
					zNode.id = idx;
					zNode.name = item;
					zNode.pId = "-1";
					zNode.isParent = true;
					zNodes.push(zNode);
				});
				// <<
				zNodes.push({id:"-1", name:"全部节点", isParent:true, open:true, checked:true, check_Focus:true});
				var $treeObj = $.fn.zTree.init($("#treeSelect"), setting, zNodes);
				$treeObj.selectNode($treeObj.getNodes()[0], false, false);
				statisticsObject.search($treeObj);
				trendObject.search($treeObj);
				trendObject.searchCharts($treeObj);
				//$("#c-height").css("height","100%");
				$('.c-nav').css('height',$(window).height()-140);
			});
		});
		var setting = {
			check : {
				enable : false,
				chkboxType : {
					"Y" : "",
					"N" : ""
				}
			},
			view : {
				selectedMulti : false,
				showLine: false,
				showIcon: showIconForTree
			},
			edit : {
				enable : false,
				editNameSelectAll : false,
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				beforeDrag : false,
				onClick : onClick
			}
		};
		
		function showIconForTree(treeId, treeNode) {
			return treeNode.isParent;
		};
		
		function onClick(e, treeId, treeNode){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var field = ""
			$("#queryServerBuildId_1").val("");
			$("#queryServerRoomId_1").val("");
			//$("#queryClassId_1").val("");
		
			if(treeObj.getSelectedNodes()[0].level == 0){
				$("#queryServerBuildId_1").val("");
				$("#queryServerRoomId_1").val("");
				//$("#queryClassId_1").val("");
			}else if(treeObj.getSelectedNodes()[0].level == 1){
				$("#queryServerBuildId_1").val(treeObj.getSelectedNodes()[0].id);
			}else if(treeObj.getSelectedNodes()[0].level == 2){
				$("#queryServerBuildId_1").val(treeObj.getSelectedNodes()[0].id.split("-")[0]);
				$("#queryServerRoomId_1").val(treeObj.getSelectedNodes()[0].id.split("-")[1]);
			}/*else if(treeObj.getSelectedNodes()[0].level == 3){
				$("#queryServerBuildId_1").val(treeObj.getSelectedNodes()[0].id.split("-")[0]);
				$("#queryServerRoomId_1").val(treeObj.getSelectedNodes()[0].id.split("-")[1]);
				//$("#queryClassId_1").val(treeObj.getSelectedNodes()[0].id.split("-")[2]);
			}*/
			treeObj.expandNode(treeObj.getSelectedNodes()[0], true, false, false);
			statisticsObject.search(treeObj);
			trendObject.search(treeObj);
			trendObject.searchCharts(treeObj);
		}
	})();
</script>