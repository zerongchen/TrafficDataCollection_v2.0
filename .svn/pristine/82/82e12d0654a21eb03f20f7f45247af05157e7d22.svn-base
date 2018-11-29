<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<c:set var="getTableStaColumnData" value="${ctx}/common/DIC_BIZ_INFO/getTableColumnData.do" scope="page" />
<c:set var="updateBizParentUrl" value="${ctx}/serviceConfiguration/baseResPool/updateBizParent.do" scope="request" />
<c:set var="initStaTable" value="${ctx}/serviceConfiguration/baseResPool/initStaTable.do" scope="request" />
<c:set var="insertUrl" value="${ctx}/serviceConfiguration/baseResPool/insert.do" scope="request" />
<c:set var="updateUrl" value="${ctx}/serviceConfiguration/baseResPool/update.do" scope="request" />
<c:set var="deleteUrl" value="${ctx}/serviceConfiguration/baseResPool/delete.do" scope="request" />
<c:set var="uploadUrl" value="${ctx}/serviceConfiguration/baseResPool/dataImport.do" scope="request" />
<c:set var="exportUrl" value="${ctx}/serviceConfiguration/baseResPool/export.do" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>业务配置--业务配置--基地资源池信息管理_查看基地资源池信息</title>
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
    <div class="title"> <i></i> > <span class="blue">业务配置</span> / <span class="blue">业务配置</span> / <span class="blue"><a href="#" onclick="backhome()">业务信息管理</a></span>/ <span id="Spandetail"></span> </div>
    <div class="protocol">
      <div class="c-traffic fl">
        <div class="table-title"> <a>查看业务服务名</a>
          <div class="form" id="editForm">
            <div>
              <label >父类业务名：</label>
				<select name="" id="edit_PARENT_ID">
					<option selected="selected" value="0">请选择</option>
				</select> <i>可以为空</i>
			</div>
            <div>
				<label>业务名称：</label> <input type="text" placeholder="支持#$@!等特殊字符" name='bizName' id='editBizName'/> <i class="required">*</i>
            </div>
            <div>
                <label >机房：</label> 
                <input type="text" class="w140" id="edit_SERVERBUILD_SERVERROOM"><i>可以为空</i>                
			</div>
			<!-- 
            <div>
              <label >有效性：</label>
              <label class="sublabel" for="s01">
                <input type="radio" name="service" id="s01" value="" checked="checked">
                有效</label>
              <label class="sublabel" for="s02">
                <input type="radio" name="service" id="s02" value="">
                无效</label>
            </div>
             -->
            <div>
              <label >IP信息：</label>
            </div>
            <div id="typeTtab">
              <ul>
                <li class="active ip"><a href="#">IP</a></li>
                <li class="ipsegment"><a href="#">IP段</a></li>
                <li class="ipport" ><a href="#">IP+端口</a></li>
                <li class="ipportlocation" ><a href="#">IP+端口+Location</a></li>
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
              <table class="table ipsegment" width="100%" style="display:none;">
              	<tr>
                  <th><input name="" type="checkbox" value=""></th>
                  <th>IP</th>
                  <th>掩码</th>
                </tr>
              </table>
              <table class="table ipport" width="100%" style="display:none;">
				<tr>
                  <th><input name="" type="checkbox" value=""></th>
                  <th>IP</th>
                  <th>端口</th>
                </tr>
              </table>
              <table class="table ipportlocation" width="100%" style="display:none;">
				<tr>
                  <th><input name="" type="checkbox" value=""></th>
                  <th>IP</th>
                  <th>端口</th>
                  <th>URL</th>
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
				<label>IP:</label> <input type="text" class="w140"> <a href="#"><i class="fa fa-plus-circle fa-2x green"></i></a>
			</p>
			<p class="ipsegment" style="display:none;">
				<label>IP段:</label> <input type="text" class="w140"> / <input type="text" class="w60"> <a href="#"><i
					class="fa fa-plus-circle fa-2x green"></i></a>
			</p>
			<p class="ipport" style = "display:none;">
				<label>IP+端口:</label> <input type="text" class="w140"> ： <input type="text" class="w60"> <a href="#"><i class="fa fa-plus-circle fa-2x green"></i></a>
			</p>
			<p class="ipportlocation" style = "display:none;">
				<label>IP+端口+Loction:</label> <input type="text" class="w140"> ： <input type="text" class="w60"> / <input type="text"> <a
					href="#"><i class="fa fa-plus-circle fa-2x green"></i></a>
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
        
        function backhome()
		{
			window.location=ctx + "/serviceConfiguration/baseResPool/index.do";
		}
        
		$(function(){
			//初始化业务选项
			var reqData = new Object();
			$.ajax({
				url : ctx + '/common/selectBaseRePool.do',
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
	    			postData.bizIds = "";
	    			$.each($("#editarea input:checkbox:checked"), function(idx, item){
	    				if($(item).val() != null && $(item) != undefined && $(item).length > 0){
	    					postData.bizIds = postData.bizIds + (postData.bizIds.length == 0?postData.bizIds:",") + $(item).val();
	    				}
	    			});
	    			$.ajax({
	    				url : ctx + '/serviceConfiguration/baseResPool/delBizInfos.do',
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
				var tabClass = '';
				if($("#typeTtab .active").hasClass("ip")){
					tabClass = 'ip';
				}else if($("#typeTtab .active").hasClass("ipsegment")){
					tabClass = 'ipsegment';
				}else if($("#typeTtab .active").hasClass("ipport")){
					tabClass = 'ipport';
				}else if($("#typeTtab .active").hasClass("ipportlocation")){
					tabClass = 'ipportlocation';
				}
				$.each($("#editarea ."+tabClass+" input:checkbox:checked"), function(idx, item){
					if($(item).val() != null && $(item).val().length > 0){//排除全选框
						if($("#popup02 .windowform ."+tabClass+" input:eq(0)").val().length > 0){//第一个已经被使用
							if($("#typeTtab .active").hasClass("ip")){
								var $addiphtml = $("#addiphtml p").clone().addClass("new");
								$addiphtml.find("a").on("click", function(e){
									$(this).parent().remove();
								});
								$addiphtml.find("input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$addiphtml.find("input:eq(0)").data("id",$(item).parents("tr:eq(0)").find("td:eq(0) input").val());
								$("#popup02 .windowform div:eq(0)").append($addiphtml);
								$("#popup02 .windowform p a").hide();
							}else if($("#typeTtab .active").hasClass("ipsegment")){
								var $addiphtml = $("#addipsegmenthtml p").clone().addClass("new");
								$addiphtml.find("a").on("click", function(e){
									$(this).parent().remove();
								});
								$addiphtml.find("input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$addiphtml.find("input:eq(1)").val($(item).parents("tr:eq(0)").find("td:eq(2)").html());
								$addiphtml.find("input:eq(0)").data("id",$(item).parents("tr:eq(0)").find("td:eq(0) input").val());
								$("#popup02 .windowform div:eq(0)").append($addiphtml);
								$("#popup02 .windowform p a").hide();
							}else if($("#typeTtab .active").hasClass("ipport")){
								var $addiphtml = $("#addipporthtml p").clone().addClass("new");
								$addiphtml.find("a").on("click", function(e){
									$(this).parent().remove();
								});
								$addiphtml.find("input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$addiphtml.find("input:eq(1)").val($(item).parents("tr:eq(0)").find("td:eq(2)").html());
								$("#popup02 .windowform div:eq(0)").append($addiphtml);
								$("#popup02 .windowform p a").hide();
							}else if($("#typeTtab .active").hasClass("ipportlocation")){
								$addiphtml.find("input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$addiphtml.find("input:eq(1)").val($(item).parents("tr:eq(0)").find("td:eq(2)").html());
								$addiphtml.find("input:eq(2)").val($(item).parents("tr:eq(0)").find("td:eq(3)").html());
								var $addiphtml = $("#addipportlocationhtml p").clone().addClass("new");
								$addiphtml.find("a").on("click", function(e){
									$(this).parent().remove();
								});
								$("#popup02 .windowform div:eq(0)").append($addiphtml);
								$("#popup02 .windowform p a").hide();
							}
						}else{
							if($("#typeTtab .active").hasClass("ip")){
								$("#popup02 .windowform .ip input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$("#popup02 .windowform .ip input:eq(0)").data("id",$(item).parents("tr:eq(0)").find("td:eq(0) input").val());
							}else if($("#typeTtab .active").hasClass("ipsegment")){
								$("#popup02 .windowform .ipsegment input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$("#popup02 .windowform .ipsegment input:eq(1)").val($(item).parents("tr:eq(0)").find("td:eq(2)").html());
								$("#popup02 .windowform .ipsegment input:eq(0)").data("id",$(item).parents("tr:eq(0)").find("td:eq(0) input").val());
							}else if($("#typeTtab .active").hasClass("ipport")){
								$("#popup02 .windowform .ipport input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$("#popup02 .windowform .ipport input:eq(1)").val($(item).parents("tr:eq(0)").find("td:eq(2)").html());
								$("#popup02 .windowform .ipport input:eq(0)").data("id",$(item).parents("tr:eq(0)").find("td:eq(0) input").val());
							}else if($("#typeTtab .active").hasClass("ipportlocation")){
								$("#popup02 .windowform .ipportlocation input:eq(0)").val($(item).parents("tr:eq(0)").find("td:eq(1)").html());
								$("#popup02 .windowform .ipportlocation input:eq(1)").val($(item).parents("tr:eq(0)").find("td:eq(2)").html());
								$("#popup02 .windowform .ipportlocation input:eq(2)").val($(item).parents("tr:eq(0)").find("td:eq(3)").html());
								$("#popup02 .windowform .ipportlocation input:eq(0)").data("id",$(item).parents("tr:eq(0)").find("td:eq(0) input").val());
							}
						}
					}
				});
				
	        	$("#popup02 p").hide();
	        	if($("#typeTtab .active").hasClass("ip") ){
	        		$("#popup02 .windowform .ip").show();
	        	}else if($("#typeTtab .active").hasClass("ipsegment") ){
	        		$("#popup02 .windowform .ipsegment").show();
	        	}else if($("#typeTtab .active").hasClass("ipport") ){
	        		$("#popup02 .windowform .ipport").show();
	        	}else{
	        		$("#popup02 .windowform .ipportlocation").show();
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
	        	}else if($("#typeTtab .active").hasClass("ipsegment") ){
	        		$("#popup02 .windowform .ipsegment").show();
	        	}else if($("#typeTtab .active").hasClass("ipport") ){
	        		$("#popup02 .windowform .ipport").show();
	        	}else{
	        		$("#popup02 .windowform .ipportlocation").show();
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
				}else if($("#typeTtab .active").hasClass("ipsegment")){
					var $addiphtml = $("#addipsegmenthtml p").clone().addClass("new");
					$addiphtml.find("a").on("click", function(e){
						$(this).parent().remove();
					});
					$("#popup02 .windowform div:eq(0)").append($addiphtml);
				}else if($("#typeTtab .active").hasClass("ipport")){
					var $addiphtml = $("#addipporthtml p").clone().addClass("new");
					$addiphtml.find("a").on("click", function(e){
						$(this).parent().remove();
					});
					$("#popup02 .windowform div:eq(0)").append($addiphtml);
				}else if($("#typeTtab .active").hasClass("ipportlocation")){
					var $addiphtml = $("#addipportlocationhtml p").clone().addClass("new");
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
				info.bizId = '${bizId}';
				if($(".addTitle").is(":hidden"))//修改 
				    info.flag = 2;
				else
					info.flag=1;
				var flag = 0;
				var isbreak = false;
				var index_all = 0;
				$.each($("#popup02 .windowform div:eq(0) p"), function(idx, item){
					if($(this).hasClass("ip")){
						if($(this).find("input:eq(0)").val().length > 0){
							flag++;
							if($(this).find("input:eq(0)").val().IsIPAddress()){
								info["bizConfigs["+index_all+"].confType"] = 2;
								info["bizConfigs["+index_all+"].ip"] = $(this).find("input:eq(0)").val();
								if($(this).find("input:eq(0)").data("id") != undefined && $(this).find("input:eq(0)").data("id") != null && $(this).find("input:eq(0)").data("id").length>0){
									info["bizConfigs["+index_all+"].id"] = $(this).find("input:eq(0)").data("id");
								}
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
								if($(this).find("input:eq(0)").data("id") != undefined && $(this).find("input:eq(0)").data("id") != null && $(this).find("input:eq(0)").data("id").length>0){
									info["bizConfigs["+index_all+"].id"] = $(this).find("input:eq(0)").data("id");
								}
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
								if($(this).find("input:eq(0)").data("id") != undefined && $(this).find("input:eq(0)").data("id") != null && $(this).find("input:eq(0)").data("id").length>0){
									info["bizConfigs["+index_all+"].id"] = $(this).find("input:eq(0)").data("id");
								}
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
								if($(this).find("input:eq(0)").data("id") != undefined && $(this).find("input:eq(0)").data("id") != null && $(this).find("input:eq(0)").data("id").length>0){
									info["bizConfigs["+index_all+"].id"] = $(this).find("input:eq(0)").data("id");
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
					url : '${ctx}/serviceConfiguration/baseResPool/updateBizInfo.do',
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
				info.bizId = '${bizId}';
				info.parentId = $("#edit_PARENT_ID").val();
				info.bizName = $("#editBizName").val();
				info.serverbuildroom = $("#edit_SERVERBUILD_SERVERROOM").val();
				info.resourcemark = 1;
				
				$.ajax({
					type : "POST",
					data : info,
					dataType : 'json',
					url : '${ctx}/serviceConfiguration/baseResPool/updateBizInfo.do',
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
				window.location="${ctx}/serviceConfiguration/baseResPool/index.do";
			});
		});
        function search(){
        	$("#edit_bizId").val('${bizId}');
			var postData = new Object();
			postData.bizId = '${bizId}';
			$.ajax({
				url : ctx + '/serviceConfiguration/baseResPool/getBizInfo.do',
				type : "POST",
				data : postData,
				contentType : clientType,
				dataType : "json",
				success : function(data, flag, rep){
					$("#edit_PARENT_ID").val(data.parentId);
					if( $("#edit_PARENT_ID").val()==null){
						$("#edit_PARENT_ID").val(0);
					}
					$("#editBizName").val(data.bizName);
					$("#edit_SERVERBUILD_SERVERROOM").val(data.serverbuildroom);
					$("#Spandetail").html(data.bizName);

					if( $("#editBizName").val()==null){
						$("#edit_PARENT_ID").val(0);
					}
					var iphtml = "";
					var ipporthtml = "";
					var ipseghtml = "";
					var ipporturlhtml = "";
					$.each(data.bizConfigs, function(idx, item){
						if(item.confType == 1){
							ipseghtml += '<tr><td><input name="id" type="checkbox" value="'+item.id+'"></td><td>'+item.ip+'</td><td>'+item.mask+'</td></tr>';
						}
						if(item.confType == 2){
							iphtml += '<tr><td><input name="id" type="checkbox" value="'+item.id+'"></td><td>'+item.ip+'</td></tr>';
						}
						if(item.confType == 3){
							ipporthtml += '<tr><td><input name="id" type="checkbox" value="'+item.id+'"></td><td>'+item.ip+'</td><td>'+item.port+'</td></tr>';
						}
						if(item.confType == 4){
							ipporturlhtml += '<tr><td><input name="id" type="checkbox" value="'+item.id+'"></td><td>'+item.ip+'</td><td>'+item.port+'</td><td>'+item.url+'</td></tr>';
						}
					});
					$("#editarea .ip").empty().append('<tr><th><input name="" type="checkbox" value=""></th><th>IP</th></tr>').append(iphtml.length>0?iphtml:'<tr><td colspan="2">没有相关记录</td></tr>');
					$("#editarea .ipsegment").empty().append('<tr><th><input name="" type="checkbox" value=""></th><th>IP</th><th>掩码</th></tr>').append(ipseghtml.length>0?ipseghtml:'<tr><td colspan="3">没有相关记录</td></tr>');
					$("#editarea .ipport").empty().append('<tr><th><input name="" type="checkbox" value=""></th><th>IP</th><th>端口</th></tr>').append(ipporthtml.length>0?ipporthtml:'<tr><td colspan="3">没有相关记录</td></tr>');
					$("#editarea .ipportlocation").empty().append('<tr><th><input name="" type="checkbox" value=""></th><th>IP</th><th>端口</th><th>URL</th></tr>').append(ipporturlhtml.length>0?ipporturlhtml:'<tr><td colspan="4">没有相关记录</td></tr>');
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
					$("#editarea .ipsegment input:checkbox").on("click", function(e){
						if($(this).val() == null || $(this).val() == undefined || $(this).val().length == 0){
							if($(this).prop("checked") == true){
								$("#editarea .ipsegment input:checkbox:gt(0)").prop("checked", true);
							}else{
								$("#editarea .ipsegment input:checkbox:gt(0)").prop("checked", false);
							}
						}else{
							if($("#editarea .ipsegment input:checkbox:gt(0):checked").length >= $("#editarea .ipsegment input:checkbox:gt(0)").length){
								$("#editarea .ipsegment input:checkbox:eq(0)").prop("checked", true);
							}else{
								$("#editarea .ipsegment input:checkbox:eq(0)").prop("checked", false);
							}
						}
					});
					$("#editarea .ipport input:checkbox").on("click", function(e){
						if($(this).val() == null || $(this).val() == undefined || $(this).val().length == 0){
							if($(this).prop("checked") == true){
								$("#editarea .ipport input:checkbox:gt(0)").prop("checked", true);
							}else{
								$("#editarea .ipport input:checkbox:gt(0)").prop("checked", false);
							}
						}else{
							if($("#editarea .ipport input:checkbox:gt(0):checked").length >= $("#editarea .ipport input:checkbox:gt(0)").length){
								$("#editarea .ipport input:checkbox:eq(0)").prop("checked", true);
							}else{
								$("#editarea .ipport input:checkbox:eq(0)").prop("checked", false);
							}
						}
					});

					$("#editarea .ipportlocation input:checkbox").on("click", function(e){
						if($(this).val() == null || $(this).val() == undefined || $(this).val().length == 0){
							if($(this).prop("checked") == true){
								$("#editarea .ipportlocation input:checkbox:gt(0)").prop("checked", true);
							}else{
								$("#editarea .ipportlocation input:checkbox:gt(0)").prop("checked", false);
							}
						}else{
							if($("#editarea .ipportlocation input:checkbox:gt(0):checked").length >= $("#editarea .ipportlocation input:checkbox:gt(0)").length){
								$("#editarea .ipportlocation input:checkbox:eq(0)").prop("checked", true);
							}else{
								$("#editarea .ipportlocation input:checkbox:eq(0)").prop("checked", false);
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