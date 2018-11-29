<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!-- 调用方法：$.modal.alert(msg); $.modal.lalert(msg); $.modal.confirm(msg, function(){}); -->

<!-- confirm start -->
<div class="modal fade" id="common_modal_confirm_code00" tabindex="-1" role="dialog" aria-labelledby="common_modal_confirm_code00_title" aria-hidden="true">
  <div class="modal-dialog">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		<h4 class="modal-title" id="common_modal_confirm_code00_title">提示</h4>
	  </div>
	  <div class="modal-body"></div>
	  <div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		<button type="button" class="btn btn-primary">确认</button>
	  </div>
	</div>
  </div>
</div>
<!-- confirm end -->

<!-- alert start -->
<!-- Large modal -->
<div class="modal fade bs-example-modal-lg in" id="common_modal_alert_code01" tabindex="-1" role="dialog" aria-labelledby="common_modal_alert_code01_title" aria-hidden="true">
	<div class="modal-backdrop fade in"></div>
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title" id="common_modal_alert_code01_title">提示</h4>
        </div>
        <div class="modal-body"></div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
  
<!-- Small modal -->
<div class="modal fade bs-example-modal-sm in" id="common_modal_alert_code02" tabindex="-1" role="dialog" aria-labelledby="common_modal_alert_code02_title" aria-hidden="true">
	<div class="modal-backdrop fade in"></div>
    <div class="modal-dialog modal-sm">
      <div class="modal-content">

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title" id="common_modal_alert_code02_title">提示</h4>
        </div>
        <div class="modal-body"></div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div>
<!-- alert end -->
