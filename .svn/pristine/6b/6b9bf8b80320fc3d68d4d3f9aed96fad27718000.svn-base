var statisticsObject = new Object();
(function(){
function getAllNodes(){
	//var zNodestemp = "[{ id:\"0\", name:\"\" ,open:true,catalogId:0,classId:0,productId:0,moduleId:0},";
	var zNodestemp = "[";
	$.ajax({
		type : "POST",
		async : false,
		dataType : 'json',
		url : ctx + '/common/selectBaseRePool.do',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			$.each(data, function(i, n) {
				zNodestemp += "{ id:\""+n.bizId+"\", pId:\""+n.parentId+"\", name:\""+n.bizName+"\",open:true,serverBuildId:"+n.serverBuildId+",serverRoomId:"+n.serverRoomId+"},";
			});
		}
	});
	zNodestemp += "]";

	
	return zNodestemp;
}

function updateBizParent(updateBizParentUrl,bizId,targetNodeId) {
	var params = new Object();
	params.queryParentId = targetNodeId;
	params.queryBizID = bizId;
	$.ajax({
		type : "POST",
		url : updateBizParentUrl,
		data : params,
		dataType : "json",
		success : function(data) {
			return data.flag >= 0;
		}
	});
}

statisticsObject.getAllNodes = getAllNodes;
statisticsObject.updateBizParent = updateBizParent;
// table相关函数end



})();
//chart end