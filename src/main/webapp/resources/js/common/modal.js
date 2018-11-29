$.extend({
	modal : {}
});
$.extend($.modal, {
	alert : function(msg) {
		$('#'+modal_alert_small_id).find(".modal-body").html(msg);
		$('#'+modal_alert_small_id).modal({});
	},
	lalert : function(msg) {
		$('#'+modal_alert_large_id).find(".modal-body").html(msg);
		$('#'+modal_alert_large_id).modal({});
	},
	confirm : function(msg, func) {
		$('#'+modal_confirm_id).find(".modal-body").html(msg);
		$('#'+modal_confirm_id).modal('show');
		$('#'+modal_confirm_id).find(".modal-footer button:eq(1)").off("click");
		$('#'+modal_confirm_id).find(".modal-footer button:eq(1)").on("click", function(){
			func();
			$('#'+modal_confirm_id).modal('hide');
		});
		$('#'+modal_confirm_id).find(".modal-footer button:eq(0)").off("click");
		$('#'+modal_confirm_id).find(".modal-footer button:eq(0)").on("click", function(){
			$('#'+modal_confirm_id).modal('hide');
		});
	}
})