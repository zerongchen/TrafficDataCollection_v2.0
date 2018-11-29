<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData" value="${ctx}/common/DIC_SERVERROOM/getTableColumnData.do" scope="page" />
<c:set var="updateBizParentUrl" value="${ctx}/serviceConfiguration/roomTraffic/updateRoomParent.do" scope="request" />
<c:set var="initStaTable" value="${ctx}/serviceConfiguration/roomTraffic/initStaTable.do" scope="request" />
<c:set var="uploadUrl" value="${ctx}/serviceConfiguration/roomTraffic/dataImport.do" scope="request" />
<c:set var="exportUrl" value="${ctx}/serviceConfiguration/roomTraffic/export.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>业务配置--机房配置--机房信息管理_查看机房信息</title>
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
</head>

<body>
<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
<!-- #EndLibraryItem -->
<div class="con protocol">
  <div class="frame">
    <div class="title"> <i></i> > <span class="blue">业务配置</span> / <span class="blue">机房配置</span> / <span class="blue"><a href="#" onclick="backhome()">机房信息管理</a>
    
    </span>/ <span id="SpanSERVERROOM"></span> </div>
    <div class="protocol">
      <div class="c-traffic fl">
        <div class="table-title"> <a>查看机房信息</a>
          <div class="form" id="editForm">
            <div>
				<label>机楼：</label> 
					<select name="edit_PARENT_ID" id="edit_PARENT_ID">
						<option selected="selected" value="-1">请选择</option>
					</select>					 
					<input type="text" class="w140" id="txtadd_firstmenu" name="txtadd_firstmenu" style="display:none" />
                    <a id="aadd_firstmenu" href="javascript:;">新增</a>
				</div>          
            <div>
                <label >机房：</label> 
                <input type="text" class="w140" id="edit_SERVERBUILD_SERVERROOM">
                <input type = "hidden" name = "hideditroomid"  value = ""  />                
			</div>			
            <div>
              <label >IP信息：</label>
            </div> 
            <div id="typeTtab">
              <ul>
                <li class="active ip"><a href="#">IP</a></li>                
              </ul>
              <div class="w100 mb10">
                <button id="add01">新增</button>
                <button id="del001">删除</button>
                <button id="edit001">修改</button>
                <!-- <button>批量导入</button> -->
              </div>
            </div>          
            <div class="wrap300" id="editarea">
              <table class="table ip" width="100%">
              	<tr>
                  <th><input name="" type="checkbox" value=""></th>
                  <th>IP</th>
                </tr>
              </table>            
              </div>
	            <div class="confirm">
	              <label></label>
	              <button>确定</button>
	              <button class="btnwhite">返回</button>
	            </div>
          </div>
          <div class="bottom"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="popup" id="popup02" style=" width:800px; height:250px;">
  <div class="windowtitle addTitle"> 新增
    <button class="close" id="popup02close">x</button>
  </div>
  <div class="windowtitle editTitle"> 修改
    <button class="close" id="popup02close">x</button>
  </div>
  <div class="windowform">
  <form>
  <input type="reset" style="display:none;">
    <div>
			<p class="ip">
			    <label>开始IP+结束IP:</label> <input type="text" class="w140"> ： <input type="text" class="w140"> <a href="#"><i class="fa fa-plus-circle fa-2x green"></i></a>				
			</p>			
    </div>
    <div class="confirm">
      <label></label>
      <button type="button">确定</button>
      <button type="button" class="btnwhite">取消</button>
    </div>
    </form>
  </div>
  <div class="bottom"></div>
</div>
	<elements style="display:none;"> 
		<element id="addiphtml">
			<p class="ip">			
		        <label>开始IP+结束IP:</label>
		        <input type="text" class="w140">：
		        <input type="text" class="w140">
		        <a href="#"><i class="fa fa-minus-circle fa-2x red"></i></a>			
			</p>
		</element>
	</elements>
<!--公用js--> 
<script>
        $("#add").click(function () {
			openLayer("popup01","504","174");
            //popup($("#popup01"));
        })
        //$("#popup01close").click(function(){
        //    $("#popup01").hide();
        //})
        //$("#popup02close").click(function(){
        //   $("#popup02").hide();
        //})
		$("#add02").click(function () {
			openLayer("popup03","800","250");
            //popup($("#popup03"));
        })
        //$("#popup03close").click(function(){
        //    $("#popup03").hide();
        //})
		$(".close").on("click", function(e) {
    		layer.closeAll();			  
		});
		function openLayer(objId,weight,height){
			var layerIndex = layer.open({
        		title: false,
                type: 1,
                closeBtn:false,
                skin: 'layui-layer-nobg',
				area: [weight+'px', height+'px'],
                scrollbar: false,
                shadeClose: false, //点击遮罩关闭
                content: $("#"+objId)
            });
		}
		function backhome()
		{
			window.location=ctx + "/serviceConfiguration/roomTraffic/index.do";
		}
        function popup(popupName){
            var _scrollHeight = $(document).scrollTop(), 
                _windowHeight = $(window).height(), 
                _windowWidth = $(window).width(), 
                _popupHeight = popupName.height(),
                _popupWeight = popupName.width();
                _posiTop = (_windowHeight - _popupHeight)/2 + _scrollHeight; 
                _posiLeft = (_windowWidth - _popupWeight)/2; 
            popupName.css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block"});//设置position 
        };
        $("#t01").click(function () {
            $("#page01").show();
            $("#page02").hide();
        })
        $("#t02").click(function(){
            $("#page01").hide();
            $("#page02").show();
        })
        
		$(function(){
			//初始化业务选项			
			initbulid();			
			$("#edit_PARENT_ID").show();
			$("#txtadd_firstmenu").hide();
			
			$("#aadd_firstmenu").click(function() {
				$("#edit_PARENT_ID").hide();
				$("#txtadd_firstmenu").show();
				//$("#edit_PARENT_ID").show();
	    	}); 
			
			$("#txtadd_firstmenu").bind("blur", function() {
				if($.trim("#txtadd_firstmenu").length <= 0){
					layer.msg("机楼信息不能为空！");
					return ;
				}
				if($.trim("#txtadd_firstmenu").length >40 ){
					layer.msg("机楼信息限40个字符！");
					return ;
				}
				if($.trim("#txtadd_firstmenu").length >0 && 
				   $.trim("#txtadd_firstmenu").length < 40){
					var info = new Object();
					info.serverbuildname = $("#txtadd_firstmenu").val();
					
					$.ajax({
						type : "POST",
						data : info,
						dataType : 'json',
						url : '${ctx}/serviceConfiguration/roomTraffic/insertbulidInfo.do',
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						success : function(data) {
							$("#txtadd_firstmenu").val();
							$("#txtadd_firstmenu").hide();
							$("#edit_PARENT_ID").show();						
							initbulid();
							layer.msg(data.msg);
						}
					});	
				}							
				$("#edit_PARENT_ID").show();
				$("#txtadd_firstmenu").hide();
				$("#txtadd_firstmenu").val();
				initbulid();
	    	});
			
			//获取详细信息
			search();
			
			//tab切换事件
			$("#typeTtab li").on("click", function(e){
				$("#typeTtab li").removeClass("active");
				var classname = $(this).prop("class");
				$(this).addClass("active");
				$("#editarea table").hide();
				$.each($("#editarea table"), function(idx, item){
					if($(item).hasClass(classname)){
						$(item).show();
					}
				});
			});
			//批量删除按钮事件
	        $("#del001").on("click", function(e){
	        	if(confirm("你确定要删除所选数据吗？")){
	    			var postData = new Object();
	    			postData.ids = "";
	    			$.each($("#editarea input:checkbox:checked"), function(idx, item){
	    				if($(item).val() != null && $(item) != undefined && $(item).length > 0){
	    					postData.ids = postData.ids + (postData.ids.length == 0?postData.ids:",") + $(item).val();
	    				}
	    			});
	    			$.ajax({
	    				url : ctx + '/serviceConfiguration/roomTraffic/delRoomConfigs.do',
	    				type : "POST",
	    				data : postData,
	    				contentType : clientType,
	    				dataType : "json",
	    				success : function(data, flag, rep){
	    					layer.closeAll();
	    					layer.msg(data.msg);
	    					search();
	    				}
	    			});
	        	}
	        });
	        //批量编辑按钮事件
			$("#edit001").on("click", function(e){
				$("#popup02 form input:reset").click();
				$(".new").remove();
				$("#popup02 .windowform p a").hide();
				$(".addTitle").hide();
				$(".editTitle").show();
				var tabClass = 'ip';				
				$.each($("#editarea ."+tabClass+" input:checkbox:checked"), function(idx, item){
					if($(item).val() != null && $(item).val().length > 0){//排除全选框
						if($("#popup02 .windowform ."+tabClass+" input:eq(0)").val().length > 0){//第一个已经被使用
							if($("#typeTtab .active").hasClass("ip")){
								var $addiphtml = $("#addipporthtml p").clone().addClass("new");
								$addiphtml.find("a").on("click", function(e){
									$(this).parent().remove();
								});
								$addiphtml.find("input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$addiphtml.find("input:eq(1)").val($(item).parents("tr:eq(0)").find("td:eq(2)").html());
								$("#popup02 .windowform div:eq(0)").append($addiphtml);
								$("#popup02 .windowform p a").hide();
							}
						}else{
							if($("#typeTtab .active").hasClass("ip")){
								$("#popup02 .windowform .ip input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$("#popup02 .windowform .ip input:eq(0)").data("id",$(item).parents("tr:eq(0)").find("td:eq(0) input").val());
							}
						}
					}
				});
				
	        	$("#popup02 p").hide();
	        	if($("#typeTtab .active").hasClass("ip") ){
	        		$("#popup02 .windowform .ip").show();
	        	}
				openLayer("popup02","800","250");
	        });
	        $("#add01").click(function () {
	        	$("#popup02 form input:reset").click();
				$(".new").remove();
				$(".addTitle").show();
				$(".editTitle").hide();
	        	$("#popup02 .windowform p a").show();
	        	$("#popup02 p").hide();
	        	if($("#typeTtab .active").hasClass("ip") ){
	        		$("#popup02 .windowform .ip").show();
	        	}
				openLayer("popup02","800","250");
				//popup($("#popup02"));
	        });
			//新增弹窗中的“+”、“-”按钮事件
			$("#popup02 .windowform p a").on("click", function(e){
				if($("#typeTtab .active").hasClass("ip")){
					var $addiphtml = $("#addiphtml p").clone().addClass("new");
					$addiphtml.find("a").on("click", function(e){
						$(this).parent().remove();
					});
					$("#popup02 .windowform div:eq(0)").append($addiphtml);
				}
			});
			$("#popup02 .confirm .btnwhite").on("click", function(e){
				$("#popup02 form input:reset").click();
				$(".new").remove();
				//alert("serviceArchitectureDetail.jsp");
				layer.closeAll();
				  
			});
			
			//新增业务配置信息确认按钮绑定事件
			$("#popup02 .windowform .confirm button:eq(0)").on("click", function(e){
				var info = new Object();
				info.serverroomid = '${Id}';
				info.serverbuildid = $("#edit_PARENT_ID").val();
				info.serverroomname = $("#edit_SERVERBUILD_SERVERROOM").val();
				if($(".addTitle").is(":hidden"))//修改 
				    info.flag = 2;
				else
					info.flag=1;
				var flag = 0;
				var isbreak = false;
				var index_all = 0;
				$.each($("#popup02 .windowform div:eq(0) p"), function(idx, item){
					if($(this).hasClass("ip")){
						
						if($(this).find("input:eq(0)").val().length > 0 && $(this).find("input:eq(1)").val().length > 0){
							flag++;
							if(!$(this).find("input:eq(0)").val().IsIPAddress()){
								layer.msg("开始ip格式不正确！");
								isbreak = true;
								return false;
							} else if(!$(this).find("input:eq(1)").val().IsIPAddress()){
								layer.msg("结束ip格式不正确！");
								isbreak = true;
								return false;
							}else {								
								info["roomtrafficConfigs["+index_all+"].startip"] = $(this).find("input:eq(0)").val();
								info["roomtrafficConfigs["+index_all+"].endip"]  = $(this).find("input:eq(1)").val();
								if($(this).find("input:eq(0)").data("id") != undefined && $(this).find("input:eq(0)").data("id") != null && $(this).find("input:eq(0)").data("id").length>0){
									info["roomtrafficConfigs["+index_all+"].id"] = $(this).find("input:eq(0)").data("id");
								}
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
					url : '${ctx}/serviceConfiguration/roomTraffic/updateRoomInfo.do',
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(data) {
						
						if(data.flag == 0){
						$(".new").remove();
						$("#popup02 form input:reset").click();
						layer.closeAll();						
						search();
						}
						layer.msg(data.msg);
					}
				});
			});
			
			$("#editForm .confirm button:eq(0)").on("click", function(e){
				var info = new Object();
				info.serverroomid = '${Id}';
				info.serverbuildid = $("#edit_PARENT_ID").val();				
				info.serverroomname = $("#edit_SERVERBUILD_SERVERROOM").val();
				$.ajax({
					type : "POST",
					data : info,
					dataType : 'json',
					url : '${ctx}/serviceConfiguration/roomTraffic/updateBizInfo.do',
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(data) {
						
						if(data.flag == 0){
						$(".new").remove();
						$("#popup02 form input:reset").click();
						layer.closeAll();						
						search();
						}
						layer.msg(data.msg);
					}
				});
			});
			$("#editForm .confirm button:eq(1)").on("click", function(e){
				//window.close();				
				window.location="${ctx}/serviceConfiguration/roomTraffic/index.do";
			});
		});
        function initbulid(){
        	var reqData = new Object();
			$.ajax({
				url : ctx + '/common/selectBRoom.do',
				type : "POST",
				data : reqData,
				contentType : clientType,
				dataType : "json",
				success : function(data, flag, rep){
					$.each(data, function(idx, item){
						//$("#add_PARENT_ID").append('<option value="'+item.serverBuildId+'">'+item.serverBuildName+'</option>');
						$("#edit_PARENT_ID").append('<option value="'+item.serverBuildId+'">'+item.serverBuildName+'</option>');
					})
				}
			});
        }
        function search(){
        	$("#hideditroomid").val('${Id}');
			var postData = new Object();
			postData.serverroomid = '${Id}';			
			$.ajax({
				url : ctx + '/serviceConfiguration/roomTraffic/getRoomInfo.do',
				type : "POST",
				data : postData,
				contentType : clientType,
				dataType : "json",
				success : function(data, flag, rep){
					$("#edit_PARENT_ID").val(data.serverbuildid);
					if( $("#edit_PARENT_ID").val()==null){
						$("#edit_PARENT_ID").val(0);
					}					 
					$("#edit_SERVERBUILD_SERVERROOM").val(data.serverroomname);
					$("#SpanSERVERROOM").html(data.serverroomname);

					if( $("#edit_SERVERBUILD_SERVERROOM").val()==null){
						$("#edit_PARENT_ID").val(0);
					}
					var iphtml = "";					
					$.each(data.roomtrafficConfigs, function(idx, item){
							iphtml += '<tr><td><input name="id" type="checkbox" value="'+item.id+'"></td><td>'+item.startip+'</td><td>'+item.endip+'</td></tr>';
					});
					$("#editarea .ip").empty().append('<tr><th><input name="" type="checkbox" value=""></th><th>IP</th></tr>').append(iphtml.length>0?iphtml:'<tr><td colspan="2">没有相关记录</td></tr>');
					$("#editarea .ip input:checkbox").on("click", function(e){
						if($(this).val() == null || $(this).val() == undefined || $(this).val().length == 0){
							if($(this).prop("checked") == true){
								$("#editarea .ip input:checkbox:gt(0)").prop("checked", true);
							}else{
								$("#editarea .ip input:checkbox:gt(0)").prop("checked", false);
							}
						}else{
							if($("#editarea .ip input:checkbox:gt(0):checked").length >= $("#editarea .ip input:checkbox:gt(0)").length){
								$("#editarea .ip input:checkbox:eq(0)").prop("checked", true);
							}else{
								$("#editarea .ip input:checkbox:eq(0)").prop("checked", false);
							}
						}
					});
				}
			});
        }
    </script> 
<script type="text/javascript" src="js/pageGroup.js"></script> 
<script src="js/all.js"></script>
</body>
</html>