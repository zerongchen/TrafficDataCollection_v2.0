var cityArr = new Array();
var districtArr = new Array();

function getProvinceArray(){
	return provinceArr;
}

function getCityArray(){
	return cityArr;
}

function getCountyArray(){
	return countyArr;
}

function getProvinceNameByCode(area_code){
	if(area_code ==9999) return "全国";
	if(area_code ==0) return "全国";
	for (var i = 0; i < provinceArr.length; i++) { 
		if(area_code==provinceArr[i][2]){
			return provinceArr[i][1];
			break;
		}
	} 
	return "未知";
}

function getCityNameByCode(area_code){
	if(area_code ==9999) return "全部";
	if(area_code ==0) return "全部";
	for (var i = 0; i < cityArr.length; i++) { 
		if(area_code==cityArr[i][2]){
			return cityArr[i][1];
			break;
		}
	} 
	return "未知";
}

function getAreaNameByCode(provinceId,cityId){
	if(provinceId ==9999) return "全国";
	if(provinceId ==0) return "全国";
	var s = "";
	for (var i = 0; i < provinceArr.length; i++) { 
		if(provinceId==provinceArr[i][2]){
			s = provinceArr[i][1];
			break;
		}
	}
	
	if(s =="") return "未知";
	if(cityId=null) return s;
	
	if(cityId ==9999) return s;
	if(cityId ==0) return s;
	for (var i = 0; i < cityArr.length; i++) { 
		if(cityId==cityArr[i][2]){
			s = s + "-" + cityArr[i][1];
			break;
		}
	} 	
	return s;
}

function getAreaNameByCode(provinceId,cityId,countyId){
	if(provinceId ==9999) return "全国";
	if(provinceId ==0) return "全国";
	var s = "";
	for (var i = 0; i < provinceArr.length; i++) { 
		if(provinceId==provinceArr[i][2]){
			s = provinceArr[i][1];
			break;
		}
	}
	if(s =="") return "未知";
	if(cityId ==9999) return s;
	if(cityId ==0) return s;
	for (var i = 0; i < cityArr.length; i++) { 
		if(cityId==cityArr[i][2]){
			s = s + "-" + cityArr[i][1];
			break;
		}
	} 	
	
	if(countyId ==9999) return s;
	if(countyId ==0) return s;
	for (var i = 0; i < countyArr.length; i++) { 
		if(countyId==countyArr[i][2]){
			s = s + "-" + countyArr[i][1];
			break;
		}
	} 	
	return s;
}

function mychange(provalue,cityId) {
	// 先将unit_city下拉框清空                   
    document.getElementById(cityId).length = 0;             
    // 下面先添加提示行
    document.getElementById(cityId).options[0] = new Option('请选择','');
    // 只有provalue不等于空才执行,也就是说选择了空省份这里就不执行了
    if (provalue != "" && provalue != "0") {
        for (var i = 0; i < cityArr.length; i++) { 
            // 这里要判断一下,只有是本省的城市或区, 才会添加进去
            if (cityArr[i][0] == provalue ) {  
                // 下面语句将在city下拉框最下边添加一个新项
                // 这里之所以不能如上面函数用i + 1, 主要因为并不是每个区县都会添加.如果用i+1,就会出现一些空白项.
                // 因此这里用了document.myform.dropcity.length,表示每次循环都会放在下拉框最下面.
                document.getElementById(cityId).options[document.getElementById(cityId).length] = new Option(cityArr[i][1],cityArr[i][2]); 
            }         
        } 
    } 
}

function mychange2(cityvalue,countyId) {   
	// 先将unit_county下拉框清空           
    document.getElementById(countyId).length = 0;             
    document.getElementById(countyId).options[0] = new Option('请选择','');
    if (cityvalue != "" && cityvalue != "0") {
        for (var i = 0; i < countyArr.length; i++) { 
            if (countyArr[i][0] == cityvalue) {  
                document.getElementById(countyId).options[document.getElementById(countyId).length] = new Option(countyArr[i][1],countyArr[i][2]); 
            }         
        } 
    }
}

function selectProvince(val, provinceId){
	var opts = document.getElementById(provinceId).options;
	var obj = document.getElementById(provinceId);
	for(var i = 0; i < opts.length; i++){
		if(opts[i].value == val){
			opts[i].selected = true;
			if (obj.fireEvent){
				obj.fireEvent('onchange');
			} else {
				obj.onchange();
			}
			break;
		}
	}
}

function selectCity(val, cityId){
	var opts = document.getElementById(cityId).options;
	var obj = document.getElementById(cityId);
	for(var i = 0; i < opts.length; i++){
		if(opts[i].value == val){
			opts[i].selected = true;
			if (obj.fireEvent){
				obj.fireEvent('onchange');
			} else {
				obj.onchange();
			}
			break;
		}
	}
}

function selectCounty(val, countyId){
	var opts = document.getElementById(countyId).options;
	for(var i = 0; i < opts.length; i++){
		if(opts[i].value == val){
			opts[i].selected = true;
			break;
		}
	}
}

function changeProvince(provinceCode,cityId){
	// 先将city下拉框清空                   
    document.getElementById(cityId).length = 0;
    // 只有provinceCode不等于空才执行,也就是说选择了空省份这里就不执行了
    if (provinceCode != "") {
        for (var i = 0; i < cityArr.length; i++) { 
            // 这里要判断一下,只有是本省的城市或区, 才会添加进去
            if (cityArr[i][0] == provinceCode ) {  
                document.getElementById(cityId).options[document.getElementById(cityId).length] = new Option(cityArr[i][1],cityArr[i][2]); 
            }         
        } 
    } 
}

function changeTempClass(tempClassId,tempDomId){
    document.getElementById(tempDomId).length = 0;
    if (tempClassId != "") {
        for (var i = 0; i < tempArr.length; i++) { 
            // 这里要判断一下,只有是本省的城市或区, 才会添加进去
            if (tempArr[i][0] == tempClassId ) {  
                document.getElementById(tempDomId).options[document.getElementById(tempDomId).length] = new Option(tempArr[i][1],tempArr[i][2]); 
            }         
        } 
    } 
}

function changeCity(cityCode,districtDomId){
	if(document.getElementById(districtDomId)!=null){
		// 先将type下拉框清空                   
	    document.getElementById(districtDomId).length = 0;
	    document.getElementById(districtDomId).options[document.getElementById(districtDomId).length] = new Option("请选择","0"); 
	    // 只有cityCode不等于空才执行,也就是说选择了空市这里就不执行了
	    if (cityCode != "") {
	        for (var i = 0; i < districtArr.length; i++) { 
	            // 这里要判断一下,只有是本省的城市或区, 才会添加进去
	            if ((districtArr[i][0]+'').substring(0, 4) == (cityCode+'').substring(0, 4) ) {  
	                document.getElementById(districtDomId).options[document.getElementById(districtDomId).length] = new Option(districtArr[i][1],districtArr[i][0]); 
	            }         
	        } 
	    } 
    }
}

function initArea(cityDomId, districtDomId){
	for (var i = 0; i < cityArr.length; i++) { 
		document.getElementById(cityDomId).options[document.getElementById(cityDomId).length] = new Option(cityArr[i][1],cityArr[i][0]); 
	} 
	changeCity($("#"+cityDomId).val(),districtDomId);
}

function initProvinceCityType(provinceId, cityId, typeId){
	for (var i = 0; i < provinceArr.length; i++) { 
		document.getElementById(provinceId).options[document.getElementById(provinceId).length] = new Option(provinceArr[i][1],provinceArr[i][2]); 
	} 
	changeProvince($("#"+provinceId).val(),cityId);
	changeCity($("#"+cityId).val(),typeId);
}

function initTempClass(tempClassDomId,tempDomId){
	for (var i = 0; i < tempClassArr.length; i++) { 
		document.getElementById(tempClassDomId).options[document.getElementById(tempClassDomId).length] = new Option(tempClassArr[i][1],tempClassArr[i][2]); 
	} 
	changeTempClass($("#"+tempClassDomId).val(),tempDomId);
}

function changeProvinceCity(provinceId,cityId,typeId){
	if(document.getElementById(typeId)!=null){
		document.getElementById(typeId).length = 0;
		if ($("#"+cityId).val() != "") {
	        for (var i = 0; i < typeArr.length; i++) { 
	            if (typeArr[i][0] == $("#"+cityId).val() ) {  
	                document.getElementById(typeId).options[document.getElementById(typeId).length] = new Option(typeArr[i][1],typeArr[i][2]); 
	            }         
	        } 
	    } 
	}
    document.getElementById(cityId).length = 0;
    
    if ($("#"+provinceId).val() != "") {
        for (var i = 0; i < cityArr.length; i++) { 
            if (cityArr[i][0] == $("#"+provinceId).val() ) {  
                document.getElementById(cityId).options[document.getElementById(cityId).length] = new Option(cityArr[i][1],cityArr[i][2]); 
            }         
        } 
    } 
   
}