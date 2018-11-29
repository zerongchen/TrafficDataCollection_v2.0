<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
		<!-- #BeginLibraryItem "/Library/sidel.lbi" -->
		<div class="sidel">
			<div class="left fixed">
				<c:out value="${leftMenu}" escapeXml="false"/>
			</div>
			<script>
				$('.left .title').click(function() {
					$(this).siblings().toggle();
					if($(this).find('.icon_e').length > 0){
						$(this).find('.icon_e').toggleClass('icon_f');
					}else{
						$(this).find('.icon_f').toggleClass('icon_e');
					}
				})
			</script>
		</div>