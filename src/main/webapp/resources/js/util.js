// 改变省份选择
function mychange(provalue) {
	// 先将unit_city下拉框清空                   
    document.form1.unit_city.length = 0;             
    // 下面先添加提示行
    document.form1.unit_city.options[0] = new Option('请选择','0');
    // 只有provalue不等于空才执行,也就是说选择了空省份这里就不执行了
    if (provalue != "" && provalue != "0") {
        for (var i = 0; i < cityArr.length; i++) { 
            // 这里要判断一下,只有是本省的城市或区, 才会添加进去
            if (cityArr[i][0] == provalue) {  
                // 下面语句将在city下拉框最下边添加一个新项
                // 这里之所以不能如上面函数用i + 1, 主要因为并不是每个区县都会添加.如果用i+1,就会出现一些空白项.
                // 因此这里用了document.myform.dropcity.length,表示每次循环都会放在下拉框最下面.
                document.form1.unit_city.options[document.form1.unit_city.length] = new Option(cityArr[i][1],cityArr[i][2]); 
            }         
        } 
    }
}
// 改变城市选择
function mychange2(cityvalue) {   
	// 先将unit_county下拉框清空           
    document.form1.unit_county.length = 0;             
    document.form1.unit_county.options[0] = new Option('请选择','0');
    if (cityvalue != "" && cityvalue != "0") {
        for (var i = 0; i < countyArr.length; i++) { 
            if (countyArr[i][0] == cityvalue) {  
                document.form1.unit_county.options[document.form1.unit_county.length] = new Option(countyArr[i][1],countyArr[i][2]); 
            }         
        } 
    }
}
function selectPro(val){
	var opts = document.form1.unit_province.options;
	for(var i = 0; i < opts.length; i++){
		if(opts[i].value == val){
			opts[i].selected = true;
			break;
		}
	}
}
function selectCity(val){
	var opts = document.form1.unit_city.options;
	for(var i = 0; i < opts.length; i++){
		if(opts[i].value == val){
			opts[i].selected = true;
			break;
		}
	}
}
function selectCoun(val){
	var opts = document.form1.unit_county.options;
	for(var i = 0; i < opts.length; i++){
		if(opts[i].value == val){
			opts[i].selected = true;
			break;
		}
	}
}
function initPro(){
	for (var i = 0; i < provinceArr.length; i++) { 
		document.form1.unit_province.options[document.form1.unit_province.length] = new Option(provinceArr[i][1],provinceArr[i][2]); 
	} 
}