/*===================下载文件
 * options:{
 * url:'',  //下载地址
 * data:{name:value}, //要发送的数据
 * method:'post'
 * }
 */
$.extend({
	download : function (options) {
	    var config = $.extend(true, { method: 'post' }, options);
	    var $iframe = $('<iframe id="down-file-iframe" />');
	    var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
	    $form.attr('action', config.url);
	    for (var key in config.data) {
	        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
	    }
	    $iframe.append($form);
	    $(document.body).append($iframe);
	    $form[0].submit();
	    $iframe.remove();
	}
});