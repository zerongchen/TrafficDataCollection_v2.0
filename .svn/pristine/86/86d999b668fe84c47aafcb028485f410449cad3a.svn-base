Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"H+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};
/* 得到日期年月日等加数字后的日期 */
Date.prototype.add = function(interval, number){
	var d = this;
	var k = {
		'y' : 'FullYear',
		'q' : 'Month',
		'm' : 'Month',
		'w' : 'Date',
		'd' : 'Date',
		'h' : 'Hours',
		'H' : 'Hours',
		'n' : 'Minutes',
		's' : 'Seconds',
		'ms' : 'MilliSeconds'
	};
	var n = {
		'q' : 3,
		'w' : 7
	};
	eval('d.set' + k[interval] + '(d.get' + k[interval] + '()+'
			+ ((n[interval] || 1) * number) + ')');
	return d;
}

/* 计算两日期相差的日期年月日等 */
Date.prototype.diff = function(interval, objDate2){
	var d = this, i = {}, t = d.getTime(), t2 = objDate2.getTime();
	i['y'] = objDate2.getFullYear() - d.getFullYear();
	i['q'] = i['y'] * 4 + Math.floor(objDate2.getMonth() / 4)
			- Math.floor(d.getMonth() / 4);
	i['m'] = i['y'] * 12 + objDate2.getMonth() - d.getMonth();
	i['ms'] = objDate2.getTime() - d.getTime();
	i['w'] = Math.floor((t2 + 345600000) / (604800000))
			- Math.floor((t + 345600000) / (604800000));
	i['d'] = Math.floor(t2 / 86400000) - Math.floor(t / 86400000);
	i['h'] = Math.floor(t2 / 3600000) - Math.floor(t / 3600000);
	i['H'] = Math.floor(t2 / 3600000) - Math.floor(t / 3600000);
	i['n'] = Math.floor(t2 / 60000) - Math.floor(t / 60000);
	i['s'] = Math.floor(t2 / 1000) - Math.floor(t / 1000);
	return i[interval];
}
