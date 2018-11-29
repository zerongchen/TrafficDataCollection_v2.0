<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
	<div class="head fixed">
		<!-- <div class="logo_h1 fl">
			<div class="logo">
				<i></i>
			</div>
		</div> -->
	<div class="logo_h1 fl">
   	<div class="logo"> <i></i> </div>
	<div class="sidebar-collapse"><img src="${imagePath}/icon_collapse.png" title="点击隐藏左侧菜单"></div>
 	</div>
		<c:out escapeXml="false" value="${headMenu}"/>
		<div class="log fr">
			<a onclick="javascript:window.open('${ctx}/go2passport.do')" style="cursor:pointer;"><sec:authentication property="principal.account.fullName"/></a> <a href="${ctx}/logout.do" target="_parent">退出</a>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){ 
		$(".sidebar-collapse img").css("cursor","pointer").bind('click',function(){
			if($(".sidel").css("display") == "block"){
				$(".sidel").hide();
				$(".con .frame").css("padding-left","0px");
				$(".div1").css("width", document.documentElement.clientWidth-10-18);
			    $(".div2").css("width", (document.documentElement.clientWidth-10-18-30)/2);
			}else{
				$(".sidel").show();
				$(".con .frame").css("padding-left","230px");
				$(".div1").css("width", document.documentElement.clientWidth-230-10-18);
			    $(".div2").css("width", (document.documentElement.clientWidth-230-10-18-30)/2);
			}
			return false;
		});
	});
	</script>