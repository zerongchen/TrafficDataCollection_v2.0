<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData" value="${ctx}/common/DIC_BIZ_INFO/getTableColumnData.do" scope="page" />
<c:set var="updateBizParentUrl" value="${ctx}/serviceConfiguration/bizManage/updateBizParent.do" scope="request" />
<c:set var="initStaTable" value="${ctx}/serviceConfiguration/bizManage/initStaTable.do" scope="request" />
<c:set var="insertUrl" value="${ctx}/serviceConfiguration/bizManage/insert.do" scope="request" />
<c:set var="updateUrl" value="${ctx}/serviceConfiguration/bizManage/update.do" scope="request" />
<c:set var="deleteUrl" value="${ctx}/serviceConfiguration/bizManage/delete.do" scope="request" />
<c:set var="uploadUrl" value="${ctx}/serviceConfiguration/bizManage/dataImport.do" scope="request" />
<c:set var="exportUrl" value="${ctx}/serviceConfiguration/bizManage/export.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>业务配置--业务配置--业务信息管理</title>
<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
<tdc:res form="true" table="true"></tdc:res>
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" href="${cssPath}/style-ui.min.css">

<link rel="stylesheet" type="text/css" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-subclass.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/popup.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}/style-ui.min.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/font-awesome.min.css">
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<script type="text/javascript">
var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			showLine: false,
			showIcon: showIconForTree
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
	function showIconForTree(treeId, treeNode) {
		return treeNode.isParent;
	};
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
				//alert("这里写新增节点调用方法,当前节点参数可以用treeNode.出来");
				openLayer("popup02", "800", "610");
				$("#add_PARENT_ID").val(treeNode.id);
				return false;
			});
		}
		if(treeNode.level != 0){   
			var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId + "' title='修改' onfocus='this.blur();'></span>";
			sObj.after(editStr);
			var editBtn = $("#editBtn_"+treeNode.tId);
			if (editBtn) {
				editBtn.bind("click", function(){
					//alert("这里写修改节点调用方法,当前节点参数可以用treeNode.出来");
					console.log(treeNode);
					window.location="${ctx}/serviceConfiguration/bizManage/detail.do?bizId="+treeNode.id;
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
		//alert("这里写删除调用方法 判断后台是否删除,当前节点参数可以用treeNode.出来");
		
		//这里写删除调用方法 判断后台是否删除
		if(confirm("是否确认删除？")){
			var postData = new Object();
			postData.bizId = treeNode.id;
			$.ajax({
				url : ctx + '/serviceConfiguration/bizManage/delBizInfo.do',
				type : "POST",
				data : postData,
				contentType : clientType,
				dataType : "json",
				success : function(data, flag, rep){
					layer.closeAll();
					layer.msg(data.msg);
					search();
					var zNodes  =  statisticsObject.getAllNodes();
					$.fn.zTree.destroy("treeSelect");
					$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));	
				}
			});
		}
		return false;//返回false 树不删除节点  返回true 树自动删除节点
	}
	$(function() {	
		var zNodes  =  statisticsObject.getAllNodes();
		//console.log(zNodes1);
		$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
		//$("#c-height").css("height","100%");
		//$('.c-nav').css('height',$(window).height()-140);
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
	<div class="con back_search">
		<div class="frame">
			<div class="title">
				<i></i> > <span class="blue">业务配置</span> / <span class="blue">业务配置</span> / <span>业务信息管理</span>
				<!-- 
				<button class="fr drop_down">批量导出</button>
				<button class="fr" id="add">批量导入</button> -->
				<!-- <button class="fr" id="add01">新增</button> -->
			</div>
			<div class="computer_room">
				<div class="c-nav" id="c-height">
					<div>
						<ul id="treeSelect" class="ztree"></ul>
					</div>
				</div>
				<div class="c-traffic fl" id="c-right">
				  <div class="table-title"> 
				  <a>业务配置列表</a>	
				  <div class="w951">
				  <button id="add01">新增</button>	
				  </div>			
						<table id="appTable_statistical" class="table"></table>					
				  </div>	
				</div>
			</div>
		</div>
	</div>
	<div class="popup" id="popup01">
		<div class="windowtitle">
			批量导入
			<button class="close" id="popup01close">x</button>
		</div>
		<div class="windowform">
			<div>
				<label>上传批量文件：</label> <input name="" type="file"> <i class="required">*</i>
			</div>
			<div class="confirm">
				<label></label>
				<button>确定</button>
				<button class="btnwhite">取消</button>
			</div>
		</div>
		<div class="bottom"></div>
	</div>
	<div class="popup" id="popup02" style="width: 810px; height: 610px;">
		<div class="windowtitle">
			新增
			<button class="close" id="popup02close">x</button>
		</div>
		<form>
			<input type="reset" style="display:none;"/>
			<div class="windowform">
				<div>
					<label>父类业务名：</label> 
					<select name="PARENT_ID" id="add_PARENT_ID">
						<option selected="selected" value="0">请选择</option>
					</select> <i>可以为空</i>
				</div>
				<div>
					<label>业务名称：</label> <input type="text" placeholder="支持#$@!等特殊字符" name='bizName' id='addBizName'/> <i class="required">*</i>
				</div>
				<div>
					<label>机房：</label>
					<input type="text" class="w140" id="add_SERVERBUILD_SERVERROOM"><i>可以为空</i>					
				</div>
				<div>
					<label>IP信息：</label>
				</div>
				<div id="content">
					<ul>
						<li class="active ip"><a href="#">IP</a></li>
						<li class="ipsegment"><a href="#">IP段</a></li>
						<li class="ipport"><a href="#">IP+端口</a></li>
						<li class="ipportlocation"><a href="#">IP+端口+Location</a></li>
					</ul>
					<p class="ip">
						<label>IP:</label> <input type="text" class="w140"> <a href="#"><i class="fa fa-plus-circle fa-2x green"></i></a>
					</p>
					<p class="ipsegment" style="display:none;">
						<label>IP段:</label> <input type="text" class="w140"> / <input type="text" class="w60"> <a href="#"><i
							class="fa fa-plus-circle fa-2x green"></i></a>
					</p>
					<p class="ipport" style = "display:none;">
						<label>IP+端口:</label> <input type="text" class="w140"> ： <input type="text" class="w60"> <a href="#"><i
							class="fa fa-plus-circle fa-2x green"></i></a>
					</p>
					<p class="ipportlocation" style = "display:none;">
						<label>IP+端口+Loction:</label> <input type="text" class="w140"> ： <input type="text" class="w60"> / <input type="text"> <a
							href="#"><i class="fa fa-plus-circle fa-2x green"></i></a>
					</p>
				</div>
				<div class="confirm">
					<label></label>
					<button type="button">确定</button>
					<button class="btnwhite" type="button">取消</button>
				</div>
			</div>
		</form>
		<div class="bottom"></div>
	</div>
	<elements style="display:none;"> 
		<element id="addiphtml">
			<p class="ip">
				<label>IP:</label> <input type="text" class="w140"> <a href="#"><i class="fa fa-minus-circle fa-2x red"></i></a>
			</p>
		</element> 
		<element id="addipsegmenthtml">
			<p class="ipsegment">
				<label>IP段:</label> <input type="text" class="w140"> / <input type="text" class="w60"> 
				<a href="#"><i class="fa fa-minus-circle fa-2x red"></i></a>
			</p>
		</element>
		<element id="addipporthtml">
			<p class="ipport">
		        <label>IP+端口:</label>
		        <input type="text" class="w140">
		        ：
		        <input type="text" class="w60">
		        <a href="#"><i class="fa fa-minus-circle fa-2x red"></i></a>
			</p>
		</element>
		<element id="addipportlocationhtml">
			<p class="ipportlocation">
				<label>IP+端口+loction:</label> <input type="text" class="w140"> ： <input type="text" class="w60"> / <input type="text"> <a
					href="#"><i class="fa fa-minus-circle fa-2x red"></i></a>
			</p>
		</element>
	</elements>
	<!--公用js-->
	<script type="text/javascript">
		var getTableStaColumnDataUrl = '${getTableStaColumnData}',
		initStaTableUrl = '${initStaTable}',
		exportUrl = '${exportUrl}',
		insertUrl = '${insertUrl}',
		updateUrl = '${updateUrl}',
		deleteUrl = '${deleteUrl}',
		uploadUrl = '${uploadUrl}';
		$(function() {
			//$("#c-height").css("height", "78%");
			$('.c-nav').css('height',$(window).height()-70);
			//$("#c-right").css("width", document.documentElement.clientWidth-250-225);
			$("#c-right").css("width", document.documentElement.clientWidth-230-230-10-18);
			$("#appTable_statistical").css("width", document.documentElement.clientWidth-230-230-10-18-43);
		});
		$("#add").click(function() {
			openLayer("popup01", "504", "174");
			//popup($("#popup01"));
		})
		//$("#popup01close").click(function(){
		//    $("#popup01").hide();
		//})
		$("#add01").click(function() {
			openLayer("popup02", "810", "610");
			//popup($("#popup02"));
		})
		//$("#popup02close").click(function(){
		//   $("#popup02").hide();
		//})
		$("#add02").click(function() {
			openLayer("popup03", "818", "610");
			//popup($("#popup03"));
		})
		//$("#popup03close").click(function(){
		//    $("#popup03").hide();
		//})
		$(".close").on("click", function(e) {
			$(".new").remove();
			$("#popup02 form input:reset").click();
			layer.closeAll();
		});
		function openLayer(objId, weight, height) {
			var layerIndex = layer.open({
				title : false,
				type : 1,
				closeBtn : false,
				skin : 'layui-layer-nobg',
				area : [ weight + 'px', height + 'px' ],
				scrollbar : false,
				shadeClose : false, //点击遮罩关闭
				content : $("#" + objId)
			});
		}
		function popup(popupName) {
			var _scrollHeight = $(document).scrollTop(), _windowHeight = $(
					window).height(), _windowWidth = $(window).width(), _popupHeight = popupName
					.height(), _popupWeight = popupName.width();
			_posiTop = (_windowHeight - _popupHeight) / 2 + _scrollHeight;
			_posiLeft = (_windowWidth - _popupWeight) / 2;
			popupName.css({
				"left" : _posiLeft + "px",
				"top" : _posiTop + "px",
				"display" : "block"
			});//设置position 
		};
		$("#t01").click(function() {
			$("#page01").show();
			$("#page02").hide();
		})
		$("#t02").click(function() {
			$("#page01").hide();
			$("#page02").show();
		})
		
		$(function(){
			//初始化业务选项
			var reqData = new Object();
			$.ajax({
				url : ctx + '/common/selectBizInfo.do',
				type : "POST",
				data : reqData,
				contentType : clientType,
				dataType : "json",
				success : function(data, flag, rep){
					$.each(data, function(idx, item){
						$("#add_PARENT_ID").append('<option value="'+item.bizId+'">'+item.bizName+'</option>');
						$("#edit_PARENT_ID").append('<option value="'+item.bizId+'">'+item.bizName+'</option>');
					})
				}
			});
					
			
			$("#popup02 .windowform li").on("click", function(e){
				$("#popup02 .windowform li").removeClass("active");
				var classname = $(this).prop("class");
				$(this).addClass("active");
				$("#popup02 .windowform p").hide();
				$.each($("#popup02 p"), function(idx, item){
					if($(item).hasClass(classname)){
						$(item).show();
					}
				});
			});
			$("#popup02 .windowform .confirm button:eq(0)").on("click", function(e){
				//添加单个业务
				var info = new Object();
				info.bizName = $("#addBizName").val();
				if($.trim(info.bizName).length <= 0){
					layer.msg("业务名称不能为空！");
					return ;
				}
				info.parentId = $("#add_PARENT_ID").val();
				info.serverbuildroom = $("#add_SERVERBUILD_SERVERROOM").val();
				info.resourcemark = 0;				
				var flag = 0;
				var isbreak = false;
				var index_all = 0;
				$.each($("#popup02 #content p"), function(idx, item){
					if($(this).hasClass("ip")){
						if($(this).find("input:eq(0)").val().length > 0){
							flag++;
							if($(this).find("input:eq(0)").val().IsIPAddress()){
								info["bizConfigs["+index_all+"].confType"] = 2;
								info["bizConfigs["+index_all+"].ip"] = $(this).find("input").val();
								index_all++;
							}else{
								layer.msg("ip格式不正确！");
								isbreak = true;
								return false;
							}
						}
					}else if($(this).hasClass("ipsegment")){
						if($(this).find("input:eq(0)").val().length > 0 && $(this).find("input:eq(1)").val().length > 0){
							flag++;
							if(!$(this).find("input:eq(0)").val().IsIPAddress()){
								layer.msg("ip格式不正确！");
								isbreak = true;
								return false;
							} else if(!$(this).find("input:eq(1)").val().IsNumber()){
								layer.msg("掩码需为数字！");
								isbreak = true;
								return false;
							}else {
								info["bizConfigs["+index_all+"].confType"] = 1;
								info["bizConfigs["+index_all+"].ip"] = $(this).find("input:eq(0)").val();
								info["bizConfigs["+index_all+"].mask"] = $(this).find("input:eq(1)").val();
								index_all++;
							}
						}
					}else if($(this).hasClass("ipport")){
						if($(this).find("input:eq(0)").val().length > 0 && $(this).find("input:eq(1)").val().length > 0){
							flag++;
							if(!$(this).find("input:eq(0)").val().IsIPAddress()){
								layer.msg("ip格式不正确！");
								isbreak = true;
								return false;
							} else if(!$(this).find("input:eq(1)").val().IsNumber()){
								layer.msg("端口号需为数字！");
								isbreak = true;
								return false;
							}else {
								info["bizConfigs["+index_all+"].confType"] = 3;
								info["bizConfigs["+index_all+"].ip"] = $(this).find("input:eq(0)").val();
								info["bizConfigs["+index_all+"].port"]  = $(this).find("input:eq(1)").val();
								index_all++;
							}
						}
					}else if($(this).hasClass("ipportlocation")){
						if($(this).find("input:eq(0)").val().length > 0 && $(this).find("input:eq(1)").val().length > 0 && $(this).find("input:eq(2)").val().length > 0){
							flag++;
							if(!$(this).find("input:eq(0)").val().IsIPAddress()){
								layer.msg("ip格式不正确！");
								isbreak = true;
								return false;
							} else if(!$(this).find("input:eq(1)").val().IsNumber()){
								layer.msg("端口号需为数字！");
								isbreak = true;
								return false;
							} /*else if(!$(this).find("input:eq(2)").val().IsWebSite()){
								layer.msg("url地址格式不正确！");
								isbreak = true;
								return false;
							} */else {
								info["bizConfigs["+index_all+"].confType"] = 4;
								info["bizConfigs["+index_all+"].ip"] = $(this).find("input:eq(0)").val();
								info["bizConfigs["+index_all+"].port"] = $(this).find("input:eq(1)").val();
								info["bizConfigs["+index_all+"].url"] = $(this).find("input:eq(2)").val();
								index_all++;
							}
						}
					}
				});
				if(isbreak) return;
				if(flag == 0){
					layer.msg("IP信息不能为空！");
					return ;
				}
				$.ajax({
					type : "POST",
					data : info,
					dataType : 'json',
					url : '${ctx}/serviceConfiguration/bizManage/insertBizInfo.do',
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(data) {
						
						if(data.flag == 0){
						$(".new").remove();
						$("#popup02 form input:reset").click();
						layer.closeAll();						
						search();
						var zNodes  =  statisticsObject.getAllNodes();
						$.fn.zTree.destroy("treeSelect");
						$.fn.zTree.init($("#treeSelect"), setting, eval(zNodes));
						}
						layer.msg(data.msg);
					}
				});
			});
			$("#popup02 .windowform #content p a").on("click", function(e){
				if($(this).parent("p:eq(0)").hasClass("ip")){
					var $addiphtml = $("#addiphtml p").clone().addClass("new");
					$addiphtml.find("a").on("click", function(e){
						$(this).parent().remove();
					});
					$("#popup02 #content").append($addiphtml);
				}else
				if($(this).parent("p:eq(0)").hasClass("ipsegment")){
					var $addiphtml = $("#addipsegmenthtml p").clone().addClass("new");
					$addiphtml.find("a").on("click", function(e){
						$(this).parent().remove();
					});
					$("#popup02 #content").append($addiphtml);
				}else
				if($(this).parent("p:eq(0)").hasClass("ipport")){
					var $addiphtml = $("#addipporthtml p").clone().addClass("new");
					$addiphtml.find("a").on("click", function(e){
						$(this).parent().remove();
					});
					$("#popup02 #content").append($addiphtml);
				}else
				if($(this).parent("p:eq(0)").hasClass("ipportlocation")){
					var $addiphtml = $("#addipportlocationhtml p").clone().addClass("new");
					$addiphtml.find("a").on("click", function(e){
						$(this).parent().remove();
					});
					$("#popup02 #content").append($addiphtml);
				}
			});
			$("#popup02 .confirm .btnwhite").on("click", function(e){
				$("#popup02 form input:reset").click();
				$(".new").remove();				
				layer.closeAll();  
			});
			search();
		});
		
		function search() {
			getTableColumnData(getTableStaColumnDataUrl);
			/*
			tableColumnData.splice(19, 0, {
				field : 'operation',
				title : '操作',
				width : 80,
				align : 'center',
				switchable : false,
				formatter : operateFormatter,
				events : operateEvents,
			});*/
			tableColumnData.splice(19, 0,{field : 'valid', title : '有效', width : 180, align : 'center', switchable : false, formatter : validFormatter});
			tableColumnData.splice(20, 0,{field : 'not_valid', title : '无效', width : 180, align : 'center', switchable : false, formatter : notvalidFormatter});
			tableColumnData.splice(1, 1,{align:"center", field:"BIZ_NAME", order:1,sortable :false,switchable:true,title :"业务名称",valign:"bottom",visible:true, formatter : operFormatter, events:operateEvents});
			
			$('#appTable_statistical').bootstrapTable("destroy").bootstrapTable({
				method : 'post',
				url : initStaTableUrl,
				queryParams : queryParams,
				contentType : "application/x-www-form-urlencoded",
				cache : false,
				//height : 400,
				striped : true,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 25 ],
				showColumns : false,
				sortable: false, 
				sidePagination : 'server',
				minimumCountColumns : 1,
				clickToSelect : true,
				paginationFirstText : "首页",
				paginationPreText : '上一页',
				paginationNextText : '下一页',
				paginationLastText : '最后一页',
				columns : tableColumnData
			}).on("column-switch.bs.table", function(e, field, checked) {
				updateColumnUser(e, field, checked, updateStaColumnUserUrl);
			});
			$('#appTable_statistical').bootstrapTable("refresh", {silent: true});
		}
		function validFormatter(value, row, index) {
			if(row.DELETE_FLAG == 0){
				return '<i class="fa fa-check fa-2x green"></i>';
			}
		};
		function notvalidFormatter(value, row, index) {
			if(row.DELETE_FLAG == 1){
				return '<i class="fa fa-times-circle fa-2x red"></i>';
			}
		};
		function operFormatter(value, row, index){
			return '<a class="oper_edit" style="cursor:pointer;"　 target="_Self">'+row.BIZ_NAME+'</a>';
		}
		window.operateEvents = {
				'click .oper_edit' : function(e, value, row, index) {
					e.preventDefault();
					window.location="${ctx}/serviceConfiguration/bizManage/detail.do?bizId="+row.BIZ_ID;
				}
			};
	</script>
	<script src="${scriptPath}/view/serviceConfiguration/bizMange.js" type="text/javascript"></script> 
</body>
</html>