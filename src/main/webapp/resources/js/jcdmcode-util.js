/**	统计类型数组	*/
var monitorTypeArray = getMonitorTypeArray();
/**	数据来源类型数组	*/
var datasourceTypeArray = getDatasourceTypeArray();
/**	处理过程类型数组	*/
var processTypeArray = getProcessTypeArray();

/**	初始化统计类型下拉列表	*/
function initMonitorType(id){
	document.getElementById(id).length = 0; 
	document.getElementById(id).options[0] = new Option('请选择','');
	for (var i = 0; i < monitorTypeArray.length; i++) { 
		document.getElementById(id).options[document.getElementById(id).length] = new Option(monitorTypeArray[i][1], monitorTypeArray[i][0]); 
	} 
}

/**	初始化数据来源类型下拉列表	*/
function initDatasourceType(id){
	document.getElementById(id).length = 0; 
	document.getElementById(id).options[0] = new Option('请选择','');
	for (var i = 0; i < datasourceTypeArray.length; i++) { 
		document.getElementById(id).options[document.getElementById(id).length] = new Option(datasourceTypeArray[i][1], datasourceTypeArray[i][0]); 
	} 
}

/**	初始化处理过程类型下拉列表	*/
function initProcessType(id){
	document.getElementById(id).length = 0; 
	document.getElementById(id).options[0] = new Option('请选择','');
	for (var i = 0; i < processTypeArray.length; i++) { 
		document.getElementById(id).options[document.getElementById(id).length] = new Option(processTypeArray[i][1], processTypeArray[i][0]); 
	} 
}
