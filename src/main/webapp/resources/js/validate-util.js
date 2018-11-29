/**
 * 系统公用js
 */
$(function() {
	jQuery.validator
			.addMethod(
					"isMobile",
					function(value, element) {
						var length = value.length;
						var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(14[1-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
						return this.optional(element)
								|| (length == 11 && mobile.test(value));
					}, "请正确填写手机号码");
	jQuery.validator
			.addMethod(
					"TelorMobile",
					function(value, element) {
						var length = value.length;
						var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(14[1-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
						var tel = /^0+\d{10,11}$/;
						// var tel = /^\d{3,4}-?\d{7,9}$/;
						return this.optional(element)
								|| (tel.test(value) || mobile.test(value));
					}, "请正确填写您的固话或手机号码");
	jQuery.validator
	.addMethod(
			"portComparison",
			function(value, element) {
				if($("#operStartPort").val() > $("#operEndPort").val()){
					return false;
				}else{
					return true;
				}
			}, "开始端口必须小于等于结束端口");
	jQuery.validator
	.addMethod(
			"ipComparison",
			function(value, element) {
				if(ip2int($("#operStartIp").val()) > ip2int($("#operEndIp").val())){
					return false;
				}else{
					return true;
				}
			}, "开始IP必须小于等于结束IP");
	jQuery.validator
	.addMethod(
			"ProductValidate",
			function(value, element) {
				
				var test = true;
				return this.optional(element)
						|| test;
			}, "请正确填写您的固话或手机号码");
	jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
		var flag = 1;
		$.each(param.split(","), function(idx, item){
			if(value == $(item).val()){
				flag = (value != $(item).val());
				return false;
			}
		});
		return flag;
	}, $.validator.format("两次输入不能相同!"));
	jQuery.validator.addMethod("onlyLetterAndDigit", function(value, element,
			params) {
		var regex = new RegExp('^[A-Za-z0-9]+$');
		return regex.test(value);
	}, "只能输入字母或数字");
	jQuery.validator.addMethod("onlyLetterAndChinese", function(value, element,
			params) {
		var regex = new RegExp('^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]');
		return regex.test(value);
	}, "只能输入字母或汉字");
	jQuery.validator.addMethod("isContainDig", function(value, element){
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？]"); 
		return this.optional(element)||!pattern.test(value) ;
	}, "不允许包含特殊符号!");
	jQuery.validator.addMethod("mustContainChinese", function(value, element){
		return /[\u4e00-\u9fa5]/g.test(value);
	}, "必须至少有一个汉字!");
	jQuery.validator.addMethod("stringCheck", function(value, element) {
		return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
	}, "只能包括中文字、英文字母、数字和下划线");
	// 电话号码验证
	jQuery.validator.addMethod("isTel", function(value, element) {
		var tel = /^\d{3,4}-?\d{7,9}$/; // 电话号码格式010-12345678
		return this.optional(element) || (tel.test(value)); 
	}, "请正确填写您的电话号码");
	jQuery.validator.addMethod("onlyDigit", function(value, element, params) {
		var regex = new RegExp('^[0-9]+$');
		return regex.test(value);
	}, "只能输入数字");
	jQuery.validator
			.addMethod(
					"MobileOrTel",
					function(value, element) {
						var length = value.length;
						var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(14[1-9]{1})|(17[1-9]{1})|(18[0-9]{1}))+\d{8})$/;
						// var tel = /^0+\d{10,11}$/;
						var tel = /^\d{3,4}-?\d{7,9}$/;
						return this.optional(element)
								|| (tel.test(value) || mobile.test(value));
					}, "请正确填写您的固话或手机号码");
	jQuery.validator.addMethod("byteRangeLength", function(value, element,
			param) {
		var length = value.length;
		for ( var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			}
		}
		return this.optional(element)
				|| (length >= param[0] && length <= param[1]);
	}, jQuery.validator.format("请确保输入的值在{0}-{1}个汉字之间(一个中文字算2个字节)"));
	// 邮政编码验证
	jQuery.validator.addMethod("isZipCode", function(value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "请正确填写您的邮政编码");
	jQuery.validator.addMethod("isMoney", function(value, element) {
		// var money=/^\d+(\.\d{1,2})$|^\d*$/;
		var money = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
		return this.optional(element) || money.test(value);
	}, "请输入正确的金额");
	// 输入是否含有中文
	jQuery.validator.addMethod("isNotChinese",
			function(value, element, params) {
				var patrn = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
				if (!patrn.exec(value)) {
					return true;
				} else {
					return false;
				}

			}, "输入不能含有汉字");
	jQuery.validator.addMethod("validNickName",
			function(value, element, params) {
				// var regex = new RegExp('^[A-Za-z0-9_@]+$');
				var regex = new RegExp(
						'^[\u4e00-\u9fa5]{2,6}$|^[a-zA-Z]+[A-Za-z0-9-_@]+$');
				return regex.test(value);
			}, "只能输入字母、数字、_和@");

	// 手机或邮箱可以登录
	jQuery.validator
			.addMethod(
					"isEmailOrMobel",
					function(value, element, params) {
						var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(14[1-9]{1})|(17[1-9]{1})|(18[0-9]{1}))+\d{8})$/;
						var email = /^([a-zA-Z0-9_\.\-])+@([a-zA-Z0-9_\.\-])+\.[a-zA-Z]{2,4}$/;
						return (email.test(value) || mobile.test(value));
					}, "输入错误");
	// 邮箱
	jQuery.validator
			.addMethod(
					"isEmail",
					function(value, element, params) {
						return this.optional(element) || /^([a-zA-Z0-9_\.\-])+@([a-zA-Z0-9_\.\-])+\.[a-zA-Z]{2,4}$/.test(value);
					}, "邮箱格式错误");
	// 后台验证邮箱的有效性
	jQuery.validator.addMethod("checkValidEmail", function(value, element,
			params) {
		var result = false;
		$.ajax({
			url : ctx + "/user/reg/checkValidEmail",
			async : false,
			type : "POST",
			data : {
				email : value
			},
			dataType : "json",
			success : function(data) {
				result = data;
			}
		});
		return result;
	});
	// IP
	jQuery.validator
			.addMethod(
					"isIP",
					function(value, element, params) {
						return this.optional(element) || /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(value);
					}, "请输入正确的IP地址");
	// IPS
	jQuery.validator
			.addMethod(
					"isIPS",
					function(value, element, params) {
						if(value != undefined && value != null && value !=''){
							var result = true;
							var regxp = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)((\/(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))?)$/);
							if(value.indexOf(",") >= 0){
								$.each(value.split(","), function(idx, item){
									result = result && regxp.test(item);
									if(result == false) return false;
									if(item.indexOf("/") >= 0 && parseInt(item.split("/")[0].split(".")[3]) > parseInt(item.split("/")[1])){
										result = false;
										return false;
									}
								});
							}else result = result && regxp.test(value);
							return this.optional(element) || result;
						}else{
							return this.optional(element);
						}
					}, "请输入正确的IP地址");
	//isURL
	jQuery.validator
	.addMethod(
			"IsURL",
			function(value, element, params) {
				return IsURL(value) || this.optional(element);
			}, "请输入正确的URL");
	//IsDomain
	jQuery.validator
	.addMethod(
			"IsDomain",
			function(value, element, params) {
				return IsDomain(value) || this.optional(element);
			}, "请输入正确的域名");
});

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
};

function closeMe() {
	// $("#cboxClose").click();
	$.fn.colorbox.close();
}

function closeMeWithinIframe() {
	parent.$.fn.colorbox.close();
}

function IsURL(str_url) {
	var strRegex = "^((https|http|ftp|rtsp|mms)://)"
	// + "(([0-9a-zA-Z_!~*'().&=+$%-]+: )?[0-9a-zA-Z_!~*'().&=+$%-]+@)?"
	// //ftp的user@
	+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-zA-Z_!~*'()-]+\.)*" // 域名- www.
			+ "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\." // 二级域名
			+ "[a-zA-Z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?))";
	var re = new RegExp(strRegex);
	// re.test()
	if (re.test(str_url)) {
		return (true);
	} else {
		return (false);
	}
}

function IsDomain(str_url) {
	var re=new RegExp(/^[a-zA-Z0-9\u4E00-\u9FA5][-a-zA-Z0-9\u4E00-\u9FA5]{0,62}\.(com|ac|net|cn|org|edu|gov|biz|cc|info|tv|name|其它|hk|co.uk|中国|公司|网络|政务|公益|中國|com.cn|net.cn|org.cn|gov.cn|edu.cn|ac.cn|bj.cn|sh.cn|tj.cn|cq.cn|he.cn|sx.cn|nm.cn|ln.cn|jl.cn|hl.cn|js.cn|zj.cn|ah.cn|fj.cn|jx.cn|sd.cn|ha.cn|hb.cn|hn.cn|gd.cn|gx.cn|hi.cn|sc.cn|gz.cn|yn.cn|xz.cn|sn.cn|gs.cn|qh.cn|nx.cn|xj.cn|tw.cn|hk.cn|mo.cn|其它.cn|mil.cn|政務|網絡|政务.cn|公益.cn|政務.中國|公益.中國|政务.中国|公益.中国|政務.cn|政务.中國|政務.中国|cn|com|hk|biz|tv|net|cc|ad|ae|aero|af|ag|ai|al|am|an|ao|aq|ar|arpa|as|asia|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cat|cd|cf|cg|ch|ci|ck|cl|cm|co|coop|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hm|hn|hr|ht|hu|id|ie|il|im|in|int|io|iq|ir|is|it|je|jm|jo|jobs|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mil|mk|ml|mm|mn|mo|mobi|mp|mq|mr|ms|mt|mu|museum|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|pro|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tel|tf|tg|th|tj|tk|tl|tn|to|tp|tr|travel|tt|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|za|zm|zw|com.hk|com.tw|net.ru|org.ru|com.sg|com.au|co.kr|co.uk|cn.com|co.za|co.nz|or.kr|edu.hk|edu.mo|net.au|org.au|org.uk|网络.cn|公司.cn|網絡.cn|co.jp|com.nz|com.nz|to|com.sc|co.il|co.th|com.my|com.ph|com.mo|tm|tm|com.eg|br|org|xn--fiQs8S|xn--fiQz9S|xn--55Qx5D|xn--io0A7I|xn--od0AlG|xn--55Qw42G|xn--zfR164B|net.tw|xn--55Qw42G|xn--zfR164B|xn--od0AlG|xn--io0A7I|xn--55Qx5D|xn--fiQz9S|xn--fiQs8S|co.ao|com.ec|com.do|com.py|com.sa|com.vn|com.bo|name|wang|我爱你|集团|xn--3bSt00M|xn--3bSt00M|xn--6qQ986B3xL|xn--6qQ986B3xL|co.id|香港|xn--j6w193g|公司.香港|公司.hk|xn--55qx5d.xn--j6w193g|xn--55qx5d.hk|pw|com.de|商城|xn—czru2d|kr.com|com.ru|com.br|cn.net|com.co|wang|wang|xn--czRu2D|ren|sohu|citic|网址|xn--ses554g|中信|xn--fiq64b|asia|ren|top|top|or.kr|or.kr|xn--czRu2D|商城|商标|or.id|or.id|org.zw|org.tr|org.nz|org.sg|org.sa|org.ro|org.pl|org.pg|org.np|org.mt|org.mt|org.kw|org.eg|or.th|or.jp|org.nz|khb.ru|gov.hk|or.jp|gov.mo|gov.hk|org.br|prchina.org|focacsummit.org|focacsummit.org|org.pe|org.mx|chinaconsulatesf.org|khb.ru|tw|cn.com|tel|信息|广东|佛山|xn--vuq861b|xn--xhq521b|xn--1qqw23a|xin|xin|网店|sohu|手机)$/);
	if (re.test(str_url)) {
		return (true);
	} else {
		return (false);
	}
}
function isTelOrMobel(number) {
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(14[1-9]{1})|(17[1-9]{1})|(18[0-9]{1}))+\d{8})$/;
	var tel = /^0+\d{10,11}$/;
	return (tel.test(number) || mobile.test(number));
}

/**
 * 判断是否包含全角字符
 * 
 * @param str
 * @returns {Boolean}
 */
function chkHalf(str) {
	for ( var i = 0; i < str.length; i++) {
		strCode = str.charCodeAt(i);
		if ((strCode > 65248) || (strCode == 12288)) {
			/*
			 * alert("有全角字符"); break;
			 */
			return false;
		}
	}
	return true;
}

// 全角转换为半角函数
function toCDB(str) {
	var tmp = "";
	for ( var i = 0; i < str.length; i++) {
		if ("，。！".indexOf(str.charCodeAt(i))<0&&str.charCodeAt(i) > 65248 && str.charCodeAt(i) < 65375) {
			tmp += String.fromCharCode(str.charCodeAt(i) - 65248);
		} else {
			tmp += String.fromCharCode(str.charCodeAt(i));
		}
	}
	return tmp;
}

//IP转成整型
function ip2int(ip)
{
    var num = 0;
    ip = ip.split(".");
    num = Number(ip[0]) * 256 * 256 * 256 + Number(ip[1]) * 256 * 256 + Number(ip[2]) * 256 + Number(ip[3]);
    num = num >>> 0;
    return num;
} 
