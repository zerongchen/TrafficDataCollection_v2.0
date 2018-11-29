//统计类型
var monitorTypeArray = new Array();
monitorTypeArray[monitorTypeArray.length] = new Array('0','分钟');
monitorTypeArray[monitorTypeArray.length] = new Array('1','小时');
monitorTypeArray[monitorTypeArray.length] = new Array('2','日');
monitorTypeArray[monitorTypeArray.length] = new Array('3','周');
monitorTypeArray[monitorTypeArray.length] = new Array('4','月');

function getMonitorTypeArray(){
	return monitorTypeArray;
}

function getMonitorTypeById(id){
	var result = "";
	for(var i = 0; i < monitorTypeArray.length; i++){
		if(id == monitorTypeArray[i][0]){
			result = monitorTypeArray[i][1];
			break;
		}
	}
	return result;
}

//数据来源类型
var datasourceTypeArray = new Array();
datasourceTypeArray[datasourceTypeArray.length] = new Array('1','手机流量');
datasourceTypeArray[datasourceTypeArray.length] = new Array('2','AAA验证');
datasourceTypeArray[datasourceTypeArray.length] = new Array('3','WLAN');
datasourceTypeArray[datasourceTypeArray.length] = new Array('4','融合防火墙NAT');
datasourceTypeArray[datasourceTypeArray.length] = new Array('5','WAP NAT');
datasourceTypeArray[datasourceTypeArray.length] = new Array('6','CTNET');
datasourceTypeArray[datasourceTypeArray.length] = new Array('7','CTWAP');
datasourceTypeArray[datasourceTypeArray.length] = new Array('8','WAP网关');

function getDatasourceTypeArray(){
	return datasourceTypeArray;
}

function getDatasourceTypeById(id){
	var result = "";
	for(var i = 0; i < datasourceTypeArray.length; i++){
		if(id == datasourceTypeArray[i][0]){
			result = datasourceTypeArray[i][1];
			break;
		}
	}
	return result;
}

//处理过程类型
var processTypeArray = new Array();
processTypeArray[processTypeArray.length] = new Array('1','数据分离');
processTypeArray[processTypeArray.length] = new Array('2','数据匹配');
processTypeArray[processTypeArray.length] = new Array('3','入库统计');

function getProcessTypeArray(){
	return processTypeArray;
}

function getProcessTypeById(id){
	var result = "";
	for(var i = 0; i < processTypeArray.length; i++){
		if(id == processTypeArray[i][0]){
			result = processTypeArray[i][1];
			break;
		}
	}
	return result;
}

