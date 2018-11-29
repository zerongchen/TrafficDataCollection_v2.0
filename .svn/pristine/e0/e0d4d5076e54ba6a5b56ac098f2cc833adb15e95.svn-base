var statisticsObject = new Object();
(function(){
function getAllNodes(){
	var zNodestemp = "[{ id:\"-1\", name:\"业务总览\" ,open:true,catalogId:0,classId:0,productId:0,moduleId:0},";
	$.ajax({
		type : "POST",
		async : false,
		dataType : 'json',
		url : ctx + '/common/selectCatalog.do',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			$.each(data, function(i, n) {
				zNodestemp += "{ id:\""+n.catalogId+"\", pId:\"-1\", name:\""+n.cataName+"\",open:true,catalogId:"+n.catalogId+",classId:0,productId:0,moduleId:0},";
			});
		}
	});
	$.ajax({
		type : "POST",
		async : false,
		dataType : 'json',
		url : ctx + '/common/selectClassNodes.do',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			$.each(data, function(i, n) {
				zNodestemp += "{ id:\""+n.classId+"_"+n.catalogId+"\", pId:\""+n.catalogId+"\", name:\""+n.className+"\", open:true,catalogId:"+n.catalogId+",classId:"+n.classId+",productId:0,moduleId:0},";
			});
		}
	});
	$.ajax({
		type : "POST",
		async : false,
		dataType : 'json',
		url : ctx + '/common/selectProductNodes.do',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			$.each(data, function(i, n) {
				zNodestemp += "{ id:\""+n.classId+"_"+n.catalogId+"_"+n.productId+"\", pId:\""+n.classId+"_"+n.catalogId+"\", name:\""+n.productName+"\", open:true,catalogId:"+n.catalogId+",classId:"+n.classId+",productId:"+n.productId+",moduleId:0,serverBuildId:"+n.serverBuildId+",serverRoomId:"+n.serverRoomId+"},";
			});
		}
	});
	//$.ajax({
	//	type : "POST",
	//	async : false,
	//	dataType : 'json',
	//	url : ctx + '/common/selectModuleNodes.do',
	//	contentType : "application/x-www-form-urlencoded; charset=utf-8",
	//	success : function(data) {
	//		$.each(data, function(i, n) {
	//			zNodestemp += "{ id:\""+n.classId+"_"+n.catalogId+"_"+n.productId+"_"+n.moduleId+"\", pId:\""+n.classId+"_"+n.catalogId+"_"+n.productId+"\", name:\""+n.moduleName+"\", open:true,catalogId:"+n.catalogId+",classId:"+n.classId+",productId:"+n.productId+",moduleId:"+n.moduleId+"},";
	//		});
	//	}
	//});
	zNodestemp += "]";
	return zNodestemp;
}

function insert(operUrl) {
	//if(!$("#operForm").valid()) return;
	var postData = $("#operForm").formToJSON();
	$.ajax({
		type : "POST",
		url : operUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			if(data.flag >= 0){
				layer.closeAll();
				layer.msg(data.msg)
				setTimeout(function(){window.location.reload();}, 2000);
			}else{
				layer.msg(data.msg)
			}
		}
	});
}

function update(operUrl) {
	//if(!$("#operForm").valid()) return;
	var postData = $("#operForm").formToJSON();
	$.ajax({
		type : "POST",
		url : operUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			if(data.flag >= 0){
				layer.closeAll();
				layer.msg(data.msg)
				setTimeout(function(){window.location.reload();}, 2000);
			}else{
				layer.msg(data.msg)
			}
		}
	});
}

function deleteData(operUrl) {
	var postData = $("#operForm").formToJSON();
	$.ajax({
		type : "POST",
		url : operUrl,
		data : postData,
		dataType : "json",
		success : function(data) {
			layer.msg(data.msg);
		}
	});
}

statisticsObject.getAllNodes = getAllNodes;
statisticsObject.insert = insert;
statisticsObject.update = update;
statisticsObject.deleteData = deleteData;
// table相关函数end



})();
//chart end