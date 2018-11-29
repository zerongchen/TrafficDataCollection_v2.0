
/* multiselect plugin */
(function($){
	var settings = { //默认参数
		placeholder: '请输入查询条件', //placeholder	
		maxHeight: 200 //下拉框最大高度，超过时显示滚动条
	};
	var methods = {		
		init: function(options){ //初始化
			var params = {
				//url: '', //请求地址
				data: {}, //请求数据（级联下拉用）
				childIds: [] //下级下拉ID		
			};
			var $this = this;	
			$this.settings = $.extend({}, settings, params, options);
			$this.settings.inputName = $this.data('input_name'); //下拉框名称
			$this.settings.targetName = $this.data('target_name'); //下拉框的隐藏域名称
			$this.settings.parentTargetName = $this.data('parent_target_name'); //上一级下拉框隐藏域名称
			var $form = $this.parents('form'),
				$inputTarget = $form.find('input[name="' + $this.settings.targetName + '"]');
			if(!$inputTarget.length){				
				$form.append('<input type="hidden" name="' + $this.settings.targetName + '" />');
			}	
			$this.multiselect({						
				templates: {
					button: '<input name="' + $this.settings.inputName + '" type="text" placeholder="' + $this.settings.placeholder + '" class="multiselect dropdown-toggle form-control" data-toggle="dropdown" />'					
				},	
				buttonWidth: $this.settings.buttonWidth + 'px',	//下拉框宽度
				maxHeight: $this.settings.maxHeight,
				onChange: function(option, checked, select) {										
								var $input = $this.parents('form').find('input[name="' + $this.settings.inputName  + '"]');
								$input.val($input.attr('title'));
								var selectedData = $this.find('option:selected').map(function(a, item){									
									return item.value;
								}).get().join(',');										
								var $target = $this.parents('form').find('input[name="' + $this.settings.targetName + '"]');
								$target.val(selectedData); //将选中值赋给隐藏域	
								clearChildrenData($this.settings.childIds);					
			    }				
			 });
			var $input = $this.parents('form').find('input[name="' + $this.settings.inputName + '"]');
			var $target = $this.parents('form').find('input[name="' + $this.settings.targetName + '"]');
			$input.on('input click', function(e){ //input&click事件	
				var curInputVal = $input.val(); //当前下拉框输入的值
				$this.settings.data.parentId = $this.parents('form').find('[name="' + $this.settings.parentTargetName + '"]').val();
				console.log($this.settings.data.parentId);
				if(curInputVal != ''){
					$this.settings.data.searchValue = curInputVal;
					$.ajax({
						url: $this.settings.url,
						type: 'POST',
						dataType: 'json',
						data: $this.settings.data,
						success: function(result){
							console.log("result:" + result);
							if(result == '') $target.val($input.val());
							$this.multiselect('dataprovider', result);
							$this.multiselect('rebuild');
						}
					});		
				}else{
					$this.multiselectExtend('empty');
				}
				if(e.type == 'input')
				clearChildrenData($this.settings.childIds);
			});
			$input.on('blur', function(){ //blur事件
				var $thiss = $(this);
				var selectedData = $this.find('option:selected').map(function(a, item){									
					return item.value;
				}).get().join(',');										
				var $target = $this.parents('form').find('input[name="' + $this.settings.targetName + '"]');
				console.log('selectedData:' + selectedData);
				selectedData == '' ? $target.val($thiss.val()) : $target.val(selectedData); //将选中值赋给隐藏域
				selectedData == '' ? $thiss.attr('placeholder', $this.settings.placeholder) : $thiss.val($input.attr('title'));
			});		
		},
		empty: function(){ //清空options
			var $this = this;
			$this.multiselect('dataprovider', []);
			$this.multiselect('rebuild');
			$this.parents('form').find('input[name="' + $this.settings.targetName + '"]').val('');
			$this.parents('form').find('input[name="' + $this.settings.inputName + '"]').val('');
		},
		destroy: function(){ //解除multiselect绑定
			this.multiselect('destroy');
		},
		hide: function(){ //隐藏
			var $this = this,
				$input = $this.parents('form').find('input[name="' + $this.settings.inputName  + '"]');
			$input.hide();
		},
		show: function(){ //显示
			var $this = this,
			$input = $this.parents('form').find('input[name="' + $this.settings.inputName  + '"]');
			$input.show();
		}
	};
	var clearChildrenData = function(params){ //清空子孙级下拉
		$.each(params, function(i, n){
			var $child = $('#' + n);
			$child.multiselect('dataprovider', []); //子孙下拉框置空
			$child.multiselect('rebuild');
			$child.parents('form').find('input[name="' + $child.data('target_name') + '"]').val(''); //子孙下拉框对应的隐藏域置空
		});
	};
	$.fn.multiselectExtend = function(method){
		if (methods[method]) {
			methods[method].apply(this, Array.prototype.slice
					.call(arguments, 1));
		} else {
			methods.init.apply(this, arguments);
		}
		return this; //返回this对象，以便支持链式操作。
	};
})(jQuery);