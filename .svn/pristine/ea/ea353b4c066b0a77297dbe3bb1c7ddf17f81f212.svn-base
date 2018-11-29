
/**
 * 将表格元素序列化并转换成json对象，
 * 相同name的值用英文半角逗号隔开
 */
(function($){
	$.fn.extend({
		formToJSON: function(){
			var $this = $(this);
			var obj = {};
			var formJson = $this.serializeArray();
			$.each(formJson, function(i, n){
				var val = obj[n.name];
				if(val){
					obj[n.name] = val + ',' + n.value;
				}else{
					obj[n.name] = n.value;
				}
			});
			return obj;
		}
	});
})(jQuery);


//扩展Date的format方法 
Date.prototype.format = function (format) { 
	var o = { 
		'M+': this.getMonth() + 1, 
		'd+': this.getDate(), 
		'h+': this.getHours(), 
		'm+': this.getMinutes(), 
		's+': this.getSeconds(), 
		'q+': Math.floor((this.getMonth() + 3) / 3), 
		'S': this.getMilliseconds() 
	};
	if (/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length)); 
	} 
	for (var k in o) { 
		if (new RegExp('(' + k + ')').test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length)); 
		} 
	} 
	return format; 
};

/**
 * 此方法用于隐藏多个div块
 * 
 * @param divIdArr divId组成数组对象
 */
function hideDivs(divIdArr) {
	for(i=0;i<divIdArr.length;i++){
		$("#" + divIdArr[i]).slideUp(200);
	}
}

/**
 * 根据行索引获取行对象
 * @param $table 表对象
 * @returns
 */
function getSelectedRow($table){
	var index = $table.jqGrid('getGridParam','selrow');
	return $table.jqGrid('getRowData',index);
}

/**
 * 设置$containerPanel下面的input的值
 * 
 * @param fieldArray $containerPanel下面的左右input所组成的数组
 * @param currentRow  待设置的一行数据
 * @param $containerPanel 指定的区域，可以使from或者某个DIV下面
 * @return
 */
function setFormdata(fieldArray, currentRow, $containerPanel){
	var selector = '';
	for(var i = 0 ; i < fieldArray.length ; i++){
		var template = '*[name="{0}"]';
		if(i != (fieldArray.length -1)){
			template += ',';
		}
		selector += $.format(template, fieldArray[i]);
	}
	$(selector, $containerPanel).each(function(){
		var $this = $(this);
		var element = $this.get(0);
		if(element == undefined){
			return;
		}
		switch(element.tagName){
		case 'TEXTAREA':
		case 'SELECT':
		case 'INPUT':
			var _name = $this.attr('name');
			var _value = currentRow[_name];
			$this.val(_value);
			break;
		}
	});
}

/**
 * 用于格式化日期的处理方法
 * 
 * @param cellValue 表格单元格的内容
 * @param options
 * @param rowObject
 * @return
 */
	
	function dateFormatter(cellValue, options, rowObject) {
		if (!$.trim(cellValue)) {
			//alert(11);
			return "";
		}
		
		var str = "" + cellValue;	
		var arr = str.split("-");
		if (arr.length == 3) {
			return cellValue;
		}
		var d = new Date(cellValue);
		var day = d.getDate();
		var mon = d.getMonth() + 1;
		var year = d.getFullYear();
		if (day < 10) {
			day = "0" + day;
		}
		if (mon < 10) {
			mon = "0" + mon;
		}
		var formattedDate = year + "-" + mon + "-" + day;
		return formattedDate;
	}
	
	


/**
 * 用于格式化日期的处理方法(格式：yyyy-MM-dd HH:mm:ss)
 * 
 * @param cellValue 表格单元格的内容
 * @param options
 * @param rowObject
 * @return
 */
function dateFormatterOther(cellValue, options, rowObject) {
	if (!$.trim(cellValue)) {
		return "";
	}	
	var d = new Date(cellValue);
	var day = d.getDate();
	var mon = d.getMonth() + 1;
	var year = d.getFullYear();
	var hours = d.getHours();
	var minutes = d.getMinutes();
	var seconds = d.getSeconds();
	if (day < 10) {
		day = "0" + day;
	}
	if (mon < 10) {
		mon = "0" + mon;
	}
	if(hours < 10){
		hours = "0" + hours;
	}
	if(minutes < 10){
		minutes = "0" + minutes;
	}
	if(seconds < 10){
		seconds = "0" + seconds;
	}
	var formattedDate = year + "-" + mon + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
	return formattedDate;
}

function dateFormat(cellValue) {
	console.log(cellValue);
	if (!$.trim(cellValue)) {
		//alert(11);
		return "";
	}
	
	var str = "" + cellValue;	
	var arr = str.split("-");
	if (arr.length == 3) {
		return cellValue;
	}
	var d = new Date(cellValue);
	var day = d.getDate();
	console.log('day:' + day);
	var mon = d.getMonth() + 1;
	var year = d.getFullYear();
	if (day < 10) {
		day = "0" + day;
	}
	if (mon < 10) {
		mon = "0" + mon;
	}
	var formattedDate = year + "-" + mon + "-" + day;
	return formattedDate;
}

/**
 * 清除表单数据
 * @param fieldArray
 * @param $containerPanel
 * @return
 */
function clearFormData(fieldArray, $containerPanel){
	var selector = getSelector(fieldArray);
	$(selector, $containerPanel).each(function(){
		var $this = $(this);
		var element = $this.get(0);
		if(element == undefined){
			return;
		}
		switch(element.tagName){
		case 'INPUT':
			var isSkip = false;
			switch( element.type ){
			case 'checkbox':
				$this.attr("checked",false);
			case 'radio':
				$this.attr("checked",false);
			case 'hidden':
				isSkip = true;
				break;
			}
			if(isSkip){
				break;
			}
		case 'TEXTAREA':				
		case 'SELECT':
			$this.val('');
			break;
		}
	});
}

/**
 * 
 * @param $containerPanel
 * @return
 */
function clearDIVData($containerPanel){
	$containerPanel.find(':input').each(function(){
		var $this = $(this);
		var element = $this.get(0);
		if(element == undefined){
			return;
		}
		switch(element.tagName){
		case 'INPUT':
			var isSkip = false;
			switch( element.type ){
			case 'text' :
				$this.val('');
			case 'checkbox':
				$this.attr("checked",false);
			case 'radio':
				$this.attr("checked",false);
			case 'hidden':
				isSkip = true;
				break;
			case 'button':
				isSkip = true;
				break;
			}
			if(isSkip){
				break;
			}
		case 'TEXTAREA':				
		case 'SELECT':
			$this.val('');
			break;
		}
	});
}

function getOneSelector(fieldName){
	var template = '*[name="{0}"]';
	return $.format(template, fieldName);
}

function getSelector(fieldArray){
	var selector = '';
	for(var i = 0 ; i < fieldArray.length ; i ++ ){
		var template = getOneSelector( fieldArray[i]);
		if(i != (fieldArray.length -1)){
			template += ',';
		}
		selector += template;
	}
	return selector;
}

/**
 * 设置下拉列表的选中值
 * @param sel
 * @param values
 * @return
 */
function setSelect(sel, values) {
	var option = sel.find("option[value='"+values+"']");
	//option是jQuery对象，即使上面的表达式没有匹配值，option也不会为null,因此需要转换成js对象，或者用option.length
	if(option[0]) {
		option.attr("selected","true"); 
	} else {
		sel.get(0).selectedIndex=0;
	} 
}

function setSelectV2(sel,val){
	var $options = sel.find('option');
	$options.each(function(i){	
		$this = $(this);
		var optionValue = $this.text();
		if(optionValue == val){
			$this.attr('selected',true);
			return false;
		}
	});
}


function setCheckBox(obj, values){
	if(values==obj.val()){
		obj.attr("checked","checked");
	}else {
		obj.removeAttr("checked");
	}
}

function setMultCheckbox(sel,values){
	var selector = "";
	sel.find("input[type='checkbox']").each(function() {
		$(this).removeAttr("checked");
	});
	var vals = (values + '').split(",");
	for(var i=0;i<vals.length;i++){
		selector = $("#text_" + vals[i]);
		setCheckBox(selector,vals[i]);
	}
}

function setTextArea(obj,text){
	obj.text(text);
}

function trimSpace(field){
	return field.replace(/(^\s*)|(\s*$)/g, "");
}

function trimPlus(field){
	return field.replace(/\+/g, " ").replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.endWith=function(str){  
	if(str==null||str==""||this.length==0||str.length>this.length)  
		return false;  
	if(this.substring(this.length-str.length)==str)  
	    return true;  
	else  
	    return false;  
	return true;  
};
 
String.prototype.startWith=function(str){  
	if(str==null||str==""||this.length==0||str.length>this.length)  
	    return false;  
	if(this.substr(0,str.length)==str)  
	    return true;  
	else  
	    return false;  
	return true;  
};

String.prototype.replaceAll = function(content, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(content)) {
		return this.replace(new RegExp(content, (ignoreCase ? "gi" : "g")), replaceWith);
	} else {
		return this.replace(content, replaceWith);
	}
}

function paramString2obj (serializedParams) { 
    var obj={};
    function evalThem (str) {
        var attributeName = trimPlus(str.split("=")[0]);
        var attributeValue = trimPlus(str.split("=")[1]);
        if(attributeName.startWith('jqg_') || attributeName.startWith('_')){
        	return ;
        }
        var array = attributeName.split(".");
        for (var i = 1; i < array.length; i++) {
            var tmpArray = Array();
            tmpArray.push("obj");
            for (var j = 0; j < i; j++) {
                tmpArray.push(array[j]);
            };
            var evalString = tmpArray.join(".");
            if(!eval(evalString)){
                eval(evalString+"={};");                
            }
        };
        eval("obj."+attributeName+"='"+attributeValue+"';");
    };
    var properties = serializedParams.split("&");
    for (var i = 0; i < properties.length; i++) {
        evalThem(properties[i]);
    };
    return obj;
}

/**
 * 将form表单数据转换为json对象的方法
 */
$.fn.form2json = function(){
    var serializedParams = decodeURIComponent(this.fixedSerialize(), true);
    while(serializedParams.match("'")){
    	serializedParams=serializedParams.replace(/'/,"");
	} 
    var obj = paramString2obj(serializedParams);
    var jsonStr = "["+JSON.stringify(obj)+"]";
    if(arguments.length>0){
    	for(var i=0; i<arguments.length; i++){
			var arr = arguments[i].split("@");
			jsonStr = jsonStr.substring(0, jsonStr.lastIndexOf("}]"))+',"'+arr[0]+'":'+arr[1]+',';
		}
    	jsonStr = jsonStr.substring(0, jsonStr.lastIndexOf(","))+"}]";
    }
    return jsonStr;
};

function setParityLineStyle($container,indexs){
	for(var i = 0; i< indexs.length; i++){
		var rowId = indexs[i];
		if(rowId % 2 != 0){
			$('#'+rowId,$container).addClass('odd');
		} else {
			$('#'+rowId,$container).addClass('even');
		}
	}
}

/**
 * 隔行变色
 */
function tableStriped($table){
	$table.find('tr:even').addClass('even').end()
		  .find('tr:odd').addClass('odd');
}

function showOrHideQueryArea(divId){
	var $divId = $('#' +divId);
	var temp = $divId.css('display');
	if (temp == 'none') {
		$divId.slideDown(200);
	} else {
		$divId.slideUp(200);
		clearDIVData($divId);
	}
}

function dateValidation(dateQueryStart, dateQueryEnd){
	var s = (dateQueryStart+"").replace(/-/g,"").replace(" ","").replace(":","");
	var e = (dateQueryStart+"").replace(/-/g,"").replace(" ","").replace(":","");
	if(s==null||s==""||e==null||e==""){
		alert("请填写查询起止时间");
		return false;
	}
	if(s>e){
		alert("请选择正确的时间范围");
		return false;
	}else return true;
}


function otherOperationMouseOver(obj){		
	var selector = '#otherOperationDiv_' + obj;

	$(selector).show();
	$('div.operate').not(selector).hide();
	$(selector).mouseout(function(){
		$(this).hide();
	});
	$(selector).mouseover(function(){
		$(this).show();
	});
}

function otherOperationMouseOut(obj){		
	$('#otherOperationDiv_' + obj).hide();
}


function loadSelect(selId, url, flag, val){
	$.ajax({
		url:url,
		type:'POST',
		dataType: 'json', 
		success : function(data){
			var $selId = $('#' + selId);
			$selId.children().remove();
			var option = '';
			if(flag){
				option = '<option value="">请选择</option>';
			}
			$.each(data,function(i,n){
				if(val == n.itemValue){
					option += '<option selected="selected" value="' + n.itemValue + '">' + n.itemLabel+ '</option>';
				}else{
					option += '<option value="' + n.itemValue + '">' + n.itemLabel + '</option>';
				}
			});
			$selId.append(option);
		}
	});
}

function loadSelects(selName, url, flag, val){
	$.ajax({
		url: url,
		type: 'POST',
		dataType: 'json', 
		success: function(result){
			var $sel = $('select[name*="' + selName + '"]');
			$sel.each(function(i){
				$this = $(this);
				$this.children().remove();
				var option = '';
				if(flag){
					option = '<option value="">请选择</option>';
				}
				$.each(result,function(i, n){
					if(val == n.itemValue){
						option += '<option selected="selected" value="' + n.itemValue + '">' + n.itemLabel+ '</option>';
					}else{
						option += '<option value="' + n.itemValue + '">' + n.itemLabel + '</option>';
					}
				});
				$this.append(option);
			});
		}
	});
}

// 验证只能是数字
function checkNum(number) {
	var reg = /[^0-9]/g;

	if (reg.exec(number) == null) {
		return true;
	}
	return false;
}

// 支持IPV4&IPV6
function checkIPv4v6(s) {
	obj = s;

	var exp = /^\s*((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4})|:))|(([0-9A-Fa-f]{1,4}:){6}(:|((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})|(:[0-9A-Fa-f]{1,4})))|(([0-9A-Fa-f]{1,4}:){5}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:)(:[0-9A-Fa-f]{1,4}){0,4}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(:(:[0-9A-Fa-f]{1,4}){0,5}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})))(%.+)?\s*$/;

	var reg = obj.match(exp);
	if (reg == null) {
		return false;
	}
	return true;
}
  
//不完整的信息高亮显示
function highlightView($table,curRow,rowId){	
  	if (curRow.infoComplete == '否') {
  		$table.jqGrid('setRowData',rowId,false,'rowClass');
  	}
}


//改变table的样式，解决数据过大显示问题
function changeTableStyle(id){
	$('#' + id +  '_center')[0].style.width = '100px';
}

//改table父节点样式，解决div浮动问题
function changeTableParentStyle($table){
	$table.parent()[0].style.position = 'static';
}

function setMouseEvent($containerPanel){
	$containerPanel.find('img').each(function(){
		var $this = $(this);
		var element = $this.get(0);
		if(element == undefined){
			return;
		}
	});
}

/**
 * 通过key获取对应的值
 * @param result json数组
 * @param val
 * @returns
 */
function findValueByKey(result, val){
	var itemLable = val;
	$.each(result,function(i,n){
		if(val == n.itemValue){
			itemLable = n.itemLabel;
			return false;
		}
	});
	return itemLable;
}

/*
 * 格式化日期字符串
 * @value 日期对应的长整形值
 * @pattern 日期格式，默认为yyyy-MM-dd hh:mm:ss
 */
function getFormatDate(value, pattern) { 
	if (pattern == undefined) { 
		pattern = "yyyy-MM-dd hh:mm:ss"; 
	} 
	var d = new Date();
	d.setTime(value);
	return d.format(pattern); 
} 







