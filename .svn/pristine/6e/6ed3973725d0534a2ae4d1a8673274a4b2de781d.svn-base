// 日期选择的(整各Tr)背景颜色
var DataBgColor		= "#C8E3FF";		
// 日期的Over颜色(单个Td)
var HeadOverColor	= "#FFD700";		
// 日期的颜色(单个Td)
var HeadColor		= "#C8E3FF";		
// 星期的背景颜色						   
var WeekBgcolor		= "#0080FF";		
// 最大年份
var maxYear			= 2100;
// 最小年份			
var minYear			= 1900;
// 当前时间
var today = new Date();
document.write("<div id='CalendarLayer' style='position: absolute; z-index: 9999; width: 172px; height: 232px;top:10px;left:10px; display: none'>");
document.write("<iframe name='CalendarIframe' scrolling=no frameborder=0 width='100%' height='100%'></iframe></div>");
function Calendar()                     //定义日历对象
{
    this.daysMonth  = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);      //定义每月的天数(初始定义平年的)
    this.day        = new Array(42);                                                  //定义日历展示用的数组
    this.dayobj     = new Array(42);                                                  //定义日期展示控件数组
	this.dayback    = new Array(42);                                                  //定义日期背景
	this.bgcolor    = new Array(42);                                                  //定义日期展示控件背景数组
    this.dateStyle  = null;                                                           //保存格式化后日期数组
    this.exportobj  = null;                                                           //日历回传的显示控件
    this.eventobj   = null;                                                           //显示日历的触发控件
    this.inputDate  = null;                                                           //转化后的输入的日期(d/m/yyyy)
    this.thisYear   = new Date().getFullYear();                                       //定义年的变量的初始值
    this.thisMonth  = new Date().getMonth()+ 1;                                       //定义月的变量的初始值
    this.thisDay    = new Date().getDate();                                           //定义日的变量的初始值
    this.iframe     = null;                                                           //日历的 iframe 载体
    this.calendar   = null;                                                           //日历层的对象
    this.dateReg    = /^(\w{4})(-|\/|.|)(\w{1,2})\2(\w{1,2})$/;                       //日历回传日期格式的正则表达式                                      //日历格式验证的正则表达式
    this.format     = "yyyy-mm-dd";                                                   //回传日期的格式                                                      //是否返回时间
}

var myCalendar = new Calendar();                                                      //构造一个日历实例

function showCalendar(obj,id)                                                         //主调函数(显示日历)
{
	if(obj != undefined&&obj != null){
		myCalendar.eventobj = obj;                                                    //触发的事件源
	} else {
		return false;    
	}

	if(id != undefined&&id != null){                                                  //如果有回显的id参数
		myCalendar.exportobj = document.getElementById(id);
	}else{
		myCalendar.exportobj = obj;                                                   //日历显示的控件
	}
	myCalendar.iframe = window.frames["CalendarIframe"];                      //获取日历的 iframe 载体
	myCalendar.calendar = document.getElementById("CalendarLayer");           //日历层的对象
	var calendarstyle = myCalendar.calendar.style;                                    //日历层的style	
	if(document.all){                                                                 //如果是IE
		calendarstyle.height = "180px";
	}else{                                                                           
		calendarstyle.height = "208px";
	}
	writeIframe();                                                                   //写框架
	calendarstyle.display = "block";
	var tempobj = myCalendar.exportobj; 
	var objtop = tempobj.offsetTop;        
	var objleft = tempobj.offsetLeft;
	var objheight = tempobj.clientHeight;	 
	while (tempobj = tempobj.offsetParent){
		objtop += tempobj.offsetTop;              
		objleft += tempobj.offsetLeft;
	}
	calendarstyle.top = (objtop + objheight + 5) + "px";
	calendarstyle.left = objleft + "px";
	
	try{
		myCalendar.thisYear  = parseInt(myCalendar.dateStyle[1], 10);   //使用十进制来解析将字符串转化成数字
		myCalendar.thisMonth = parseInt(myCalendar.dateStyle[3], 10);
		myCalendar.thisDay   = parseInt(myCalendar.dateStyle[4], 10);
		myCalendar.inputDate = parseInt(myCalendar.thisDay, 10) +"/"+ parseInt(myCalendar.thisMonth, 10) +"/"+ parseInt(myCalendar.thisYear, 10); 
		writeCalendar();
    }  catch(e){
		writeCalendar();
	}
}

function dayMouseOver(obj){                                                            //鼠标放上日期事件
	var backgroundcolor = obj.style.background;   
	var clickid = obj.id.substring(3,obj.id.length);
	if(myCalendar.dayback[clickid] != 0){
		obj.style.background = "green";
	}
}

function dayMouseOut(obj){                                                              //鼠标移出日期事件
	var clickid = obj.id.substring(3,obj.id.length);
	obj.style.background = myCalendar.bgcolor[clickid];
}

function returnDate(obj){                                                                //鼠标点击日期事件
	if(myCalendar.exportobj)
	{
		var clickid = obj.id.substring(3,obj.id.length);
		var datetime = myCalendar.day[clickid].split("/");
		var dateformat = myCalendar.format.match(myCalendar.dateReg);
		var returnValue = datetime[2] +dateformat[2]+ addZero(datetime[1]) +dateformat[2]+ addZero(datetime[0]); 
		myCalendar.exportobj.value = returnValue;
		hiddenCalendar();
	}else{
		alert("未定义输出框,无法输出!");
	}
}

function writeIframe()
{
	var str ="<html><head><title></title><meta http-equiv='Content-Type' content='text/html; charset=utf-8'><style>"+
    "*{font-size: 12px; font-family: 宋体}"+
	".myCalendardiv{z-index:9999;position: absolute; left:0; top:0;}"+
	".yearspan,.monthspan,.hourspan,.minutespan{z-index: 9999;position: absolute;display: none}"+
	".button,.yearbutton,.monthbutton{background-color: #FFF7E7;border:1px solid #78acff;font-size:12.8px;cursor:pointer;cursor:hand;FONT-SIZE: 9pt;font-family:宋体;font-size: 12px; height: 20px;}"+
	".yearbutton{width:18px;margin:0}"+
	".monthbutton{width:12px;margin:0}"+
	".weektd{font-size:12px;color:#FFFFFF;}"+
	".dayclass{background-color:#FFFFFF;cursor:pointer;cursor:hand}"+
	".select{background-color:#C8E3FF;font-size:12px;cursor:pointer;cursor:hand}"+
	"</style></head><body onselectstart='return false' style='margin: 0px' oncontextmenu='return false'>"+
	"<div id=\"myCalendar\" class=\"myCalendardiv\"> "+ 
	"<span id=Yearinner class=\"yearspan\"><select id=yearSelect onclick='parent.hiddenSelectHead(this)' onblur='parent.hiddenSelect(this)' onchange='parent.selectChange(this)'></select></span> "+ 
	"<span id=Monthinner class=\"monthspan\"><select id=monthSelect onclick='parent.hiddenSelectHead(this)' onblur='parent.hiddenSelect(this)' onchange='parent.selectChange(this)'></select></span> "+ 
	"<span id=Hourinner class=\"hourspan\"><select id=hourSelect onclick='parent.hiddenSelectHead(this)' onblur='parent.hiddenSelect(this)' onchange='parent.selectChange(this)'></select></span> "+ 
	"<span id=Minuteinner class=\"minutespan\"><select id=minuteSelect onclick='parent.hiddenSelectHead(this)' onblur='parent.hiddenSelect(this)' onchange='parent.selectChange(this)'></select></span> "+ 
	"<table border=\"0\" cellspacing=\"0\" cellpadding=\"2\" bgcolor=\"#0080FF\">\n "+ 
	  "<tr>\n "+ 
		"<td>\n "+ 
			"<table border=\"0\" cellspacing=\"1\" cellpadding=\"2\" bgcolor=\"Silver\">\n "+ 
				"<tr>"+
					"<td colspan=\"7\" bgcolor=\"#C8E3FF\" align=\"center\" >\n "+ 
						"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" ><tr>\n "+ 
							"<td height='14' width=40><a href='javascript:;' title=\"向前翻 1 年\" onclick='parent.prevYear()'>&lt;&lt;</a>&nbsp;<a href='javascript:;' title=\"向前翻 1 月\" onclick='parent.prevMonth()'>&lt;</a></td>\n "+ 
							"<td width=58 align='center' class='select' onmouseover=\"style.backgroundColor=\'#FFD700\'\" onmouseout=\"style.backgroundColor=\'#C8E3FF\'\" id=\"Year\" name=\"Year\" title=\"点击这里选择年份\" onclick='parent.selectYear()'>"+myCalendar.thisYear+" 年</td>\n "+ 
							"<td width=46 align='center' class='select' onmouseover=\"style.backgroundColor=\'#FFD700\'\" onmouseout=\"style.backgroundColor=\'#C8E3FF\'\" id=\"Month\" name=\"Month\"  title=\"点击这里选择月份\" onclick='parent.selectMonth()'>"+addZero(myCalendar.thisMonth+1)+" 月</td>\n "+ 
							"<td width=40 align='right'><a href='javascript:;' title=\"向后翻 1 月\" onclick='parent.nextMonth()'>&gt;</a>&nbsp;<a href='javascript:;' title=\"向后翻 1 年\" onclick='parent.nextYear()'>&gt;&gt;</a></td>\n "+ 
						"</tr></table>\n "+ 
					"</td>"+		
				"</tr>\n ";
		str +=	//"<tr><td colspan=\"7\" align=\"center\" valign=\"top\"></td></tr>\n "+ 
				"<tr bgcolor=\""+WeekBgcolor+"\" name=\"Move\">\n "+ 
					"<td width='20' align='center' class='weektd'>日</td>\n "+ 
					"<td width='20' align='center' class='weektd'>一</td>\n "+ 
					"<td width='20' align='center' class='weektd'>二</td>\n "+ 
					"<td width='20' align='center' class='weektd'>三</td>\n "+ 
					"<td width='20' align='center' class='weektd'>四</td>\n "+ 
					"<td width='20' align='center' class='weektd'>五</td>\n "+ 
					"<td width='20' align='center' class='weektd'>六</td>\n "+ 
				"</tr>\n ";
				//+ "<tr><td colspan=\"7\" align=\"center\" valign=\"top\"></td></tr>\n ";
	
    for(var i=0; i<6; i++){
		str += "<tr class=\"dayclass\" align='right'>";
		for(var j=0;j<7;j++){
			str += "<td class='checkday' id='day"+ (i*7+j) +"' onclick='parent.returnDate(this)' onmouseover ='parent.dayMouseOver(this)' onmouseout  = 'parent.dayMouseOut(this)'></td>";
		}
		str += "</tr>";
	}
	str +=		"<tr><td colspan=\"7\" bgcolor=\""+DataBgColor+"\" width=\"100%\">\n "+ //当前日期选择
					"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr>\n "+ 
					"<td align='center' width=30><input type='button' class='button' title=\"当前日期\" value=\"今天\" name=\"Today\" onclick='parent.selectToday()'></td>\n "+ 	
					"<td align='center' bgcolor='#ffffff' style='font-size: 12px;'>"+today.getFullYear()+"-"+addZero((today.getMonth()+1))+
					"-"+addZero(today.getDate())+"</td>\n "+
					"<td align='center' width=30><input type='button' class='button' title=\"清除日期\" value=\"清除\" name=\"Clear\" onclick='parent.clearSelect()'></td>\n "+ 
				"</tr></table>\n "+ 
				"</td></tr></table>\n "+ 
			"</td>" +
		"</tr>"+
	"</table>"+
  "</div></body></html>";
  with(myCalendar.iframe)
  {
		document.open();
		document.writeln(str); 
		document.close();
   }
}

function writeCalendar()         
{
	var year = myCalendar.thisYear;         //今年
    var month = myCalendar.thisMonth;       //这个月
    // 计算闰年
	if(year % 4 ==0 && (year % 100!=0 || year % 400==0)){
		myCalendar.daysMonth[1] = 29;
	}else{
		myCalendar.daysMonth[1] = 28;
	}
	with(myCalendar.iframe){
		if(document.all){
			document.getElementById("Year").innerText  = year +" 年";
			document.getElementById("Month").innerText = addZero(parseInt(month, 10)) +" 月";
		}else{
			document.getElementById("Year").textContent  = year +" 年";
			document.getElementById("Month").textContent = addZero(parseInt(month, 10)) +" 月";	
		}
	}
	//这个月1号的星期
	var startDay = new Date(year, month-1, 1).getDay();     
	var prevDays;
	if(month==1){
		prevDays = myCalendar.daysMonth[11];
	} else {
		prevDays = myCalendar.daysMonth[month-2];
	}
	//上个月
    for(var i = (startDay-1); i >= 0; i--)                            
    {
        myCalendar.day[i] = prevDays +"/"+ (parseInt(month, 10)-1) +"/"+ year;
        if(month==1) {
			myCalendar.day[i] = prevDays +"/"+ 12 +"/"+ (parseInt(year, 10)-1);
		}
        prevDays--;
    }
    //这个月
	for(var i=1; i<=myCalendar.daysMonth[month-1]; i++){           
		myCalendar.day[startDay-1+i] = i +"/"+ parseInt(month, 10) +"/"+ year;
	}
	//上个月
    for(var i=1; i<42-startDay-myCalendar.daysMonth[month-1]+1; i++)   
    {
		myCalendar.day[parseInt(myCalendar.daysMonth[month-1],10)+startDay-1+i] = i +"/"+ (parseInt(month, 10)+1) +"/"+ year;
        if(month==12) {
			myCalendar.day[myCalendar.daysMonth[month-1]+startDay-1+i] = i +"/"+ 1 +"/"+ (parseInt(year, 10)+1);
		}
    }
	for(var i=0; i<42; i++)    //这个循环是根据源数组写到日历里显示
    {
		var datetime = myCalendar.day[i].split("/");
		myCalendar.dayobj[i] = myCalendar.iframe.document.getElementById("day"+ i);
		if(document.all){
			myCalendar.dayobj[i].innerText = datetime[0];
		} else {
			myCalendar.dayobj[i].textContent = datetime[0];
		}
        myCalendar.dayobj[i].title = datetime[2] +"-"+ addZero(datetime[1]) +"-"+ addZero(datetime[0]);
		if(i%7==0)
			myCalendar.dayobj[i].style.color = "red";
		if(i%7==6)
			myCalendar.dayobj[i].style.color = "blue";
		if(i<startDay||i>=startDay+parseInt(myCalendar.daysMonth[month-1])){
			myCalendar.dayobj[i].style.background = "#FFFFFF";
			myCalendar.bgcolor[i] = "#FFFFFF";
			myCalendar.dayback[i] = -1;
		}else if(i==startDay+today.getDate()-1){
			myCalendar.dayobj[i].style.background = "#FF8040";
			myCalendar.bgcolor[i] = "#FF8040";
			myCalendar.dayback[i] = 0;
		}else{
			myCalendar.dayobj[i].style.background = "#C8E3FF";
			myCalendar.bgcolor[i] = "#C8E3FF";
			myCalendar.dayback[i] = 1;
		}
    }
}

function addZero(num)
{
	if(num.toString().length==1){
		return ("0"+num);
	} else {
		return num;
	}
}

document.onclick = function(event)
{
	var clickobj;
	if(document.all){
		clickobj = window.event.srcElement;
	} else {
		clickobj = event.target;
	}
	if(myCalendar.eventobj != clickobj) {
		hiddenCalendar();
	}
}

function hiddenCalendar(){
	document.getElementById("CalendarLayer").style.display = "none";
}

function prevMonth()  //往前翻月份
{
    myCalendar.thisDay = 1;
    if (myCalendar.thisMonth==1)
    {
        myCalendar.thisYear--;
        myCalendar.thisMonth=13;
    }
    myCalendar.thisMonth--; 
	writeCalendar();
}

function nextMonth()  //往后翻月份
{
    myCalendar.thisDay = 1;
    if (myCalendar.thisMonth==12)
    {
        myCalendar.thisYear++;
        myCalendar.thisMonth=0;
    }
    myCalendar.thisMonth ++; 
	writeCalendar();
}

function prevYear(){        //往前翻 Year
	myCalendar.thisDay = 1;
	myCalendar.thisYear--; 
	writeCalendar();
}

function nextYear(){       //往后翻 Year
	myCalendar.thisDay = 1;
	myCalendar.thisYear++; 
	writeCalendar();
}

function hiddenSelectHead(obj){
	if(document.all){
		obj.style.display = "none";
	}
}

function clearSelect(){
	myCalendar.exportobj.value = "";
	hiddenCalendar();
}

function selectYear(){
    var target = myCalendar.iframe.document.getElementById("Year");
	var yearInner = myCalendar.iframe.document.getElementById("Yearinner");
	var tempSelect = myCalendar.iframe.document.getElementById("yearSelect");
	for (var i=minYear; i<=maxYear; i++)
    {
		var tempOption = myCalendar.iframe.document.createElement("option");
		tempOption.text =i;
		tempOption.value=i;	
		tempSelect.options.add(tempOption);
    }
	tempSelect.options[myCalendar.thisYear - minYear].selected = true;
	yearInner.style.left = getAbsPosition(target).left;
    yearInner.style.top	= getAbsPosition(target).top;
	tempSelect.style.display="block";
    yearInner.style.display="block";
	tempSelect.focus();
}

function hiddenSelect(obj){      //隐藏选择框
	obj.style.display = "none";
}

function selectToday(){
	myCalendar.exportobj.value = today.getFullYear()+"-"+addZero((today.getMonth()+1))+"-"+addZero(today.getDate());	
	hiddenCalendar();
}

function selectMonth(){
	var target = myCalendar.iframe.document.getElementById("Month");
	var monthInner = myCalendar.iframe.document.getElementById("Monthinner");
	var tempSelect = myCalendar.iframe.document.getElementById("monthSelect");
    for (var i=1; i<=12; i++)
    {
		var tempOption = myCalendar.iframe.document.createElement("option");
		tempOption.text = addZero(i);
		tempOption.value = i;	
		tempSelect.options.add(tempOption);
    }
	tempSelect.options[myCalendar.thisMonth].selected = true;
	monthInner.style.left = getAbsPosition(target).left;
    monthInner.style.top = getAbsPosition(target).top;
	tempSelect.style.display = "block";
    monthInner.style.display = "block";
	tempSelect.focus();
}

function selectChange(obj)
{
	var yearSelect = myCalendar.iframe.document.getElementById("yearSelect");
	var monthSelect = myCalendar.iframe.document.getElementById("monthSelect");
	if(obj==yearSelect){
		var years= obj.value;
		var yearInner = myCalendar.iframe.document.getElementById("Yearinner");	
		myCalendar.thisYear = years;
		yearInner.style.display="none";
	}else if(obj==monthSelect){
		var months= obj.value;
		var monthInner = myCalendar.iframe.document.getElementById("Monthinner");	
		myCalendar.thisMonth = months;
		monthInner.style.display="none";
	}
	writeCalendar();
}

function getAbsPosition(obj){
	var r = {
		left: obj.offsetLeft,
		top: obj.offsetTop
	};
	r.left=obj.offsetLeft;
	r.top=obj.offsetTop;
	if (obj.offsetParent) {
		var temp = getAbsPosition(obj.offsetParent);
		r.left += temp.left;
		r.top += temp.top;
	}
	return r;
}