/*
    功能描述:除去字符串对象值尾后的空格
    返回类型:返回除去字符串对象值尾后空格后的值
*/
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
/*
功能描述:计算字符长度，区分中英文
返回类型:返回长度值
*/
String.prototype.gblen = function() {
	var len = 0;
	for (var i = 0; i < this.length; i++) {
		if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
			len += 2;
		} else {
			len++;
		}
	}
	return len;
}

String.prototype.IsDomain=function(){
	var _Reg=new RegExp(/^[a-zA-Z0-9\u4E00-\u9FA5][-a-zA-Z0-9\u4E00-\u9FA5]{0,62}\.(com|ac|net|cn|org|edu|gov|biz|cc|info|tv|name|其它|hk|co.uk|中国|公司|网络|政务|公益|中國|com.cn|net.cn|org.cn|gov.cn|edu.cn|ac.cn|bj.cn|sh.cn|tj.cn|cq.cn|he.cn|sx.cn|nm.cn|ln.cn|jl.cn|hl.cn|js.cn|zj.cn|ah.cn|fj.cn|jx.cn|sd.cn|ha.cn|hb.cn|hn.cn|gd.cn|gx.cn|hi.cn|sc.cn|gz.cn|yn.cn|xz.cn|sn.cn|gs.cn|qh.cn|nx.cn|xj.cn|tw.cn|hk.cn|mo.cn|其它.cn|mil.cn|政務|網絡|政务.cn|公益.cn|政務.中國|公益.中國|政务.中国|公益.中国|政務.cn|政务.中國|政務.中国|cn|com|hk|biz|tv|net|cc|ad|ae|aero|af|ag|ai|al|am|an|ao|aq|ar|arpa|as|asia|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cat|cd|cf|cg|ch|ci|ck|cl|cm|co|coop|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hm|hn|hr|ht|hu|id|ie|il|im|in|int|io|iq|ir|is|it|je|jm|jo|jobs|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mil|mk|ml|mm|mn|mo|mobi|mp|mq|mr|ms|mt|mu|museum|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|pro|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tel|tf|tg|th|tj|tk|tl|tn|to|tp|tr|travel|tt|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|za|zm|zw|com.hk|com.tw|net.ru|org.ru|com.sg|com.au|co.kr|co.uk|cn.com|co.za|co.nz|or.kr|edu.hk|edu.mo|net.au|org.au|org.uk|网络.cn|公司.cn|網絡.cn|co.jp|com.nz|com.nz|to|com.sc|co.il|co.th|com.my|com.ph|com.mo|tm|tm|com.eg|br|org|xn--fiQs8S|xn--fiQz9S|xn--55Qx5D|xn--io0A7I|xn--od0AlG|xn--55Qw42G|xn--zfR164B|net.tw|xn--55Qw42G|xn--zfR164B|xn--od0AlG|xn--io0A7I|xn--55Qx5D|xn--fiQz9S|xn--fiQs8S|co.ao|com.ec|com.do|com.py|com.sa|com.vn|com.bo|name|wang|我爱你|集团|xn--3bSt00M|xn--3bSt00M|xn--6qQ986B3xL|xn--6qQ986B3xL|co.id|香港|xn--j6w193g|公司.香港|公司.hk|xn--55qx5d.xn--j6w193g|xn--55qx5d.hk|pw|com.de|商城|xn—czru2d|kr.com|com.ru|com.br|cn.net|com.co|wang|wang|xn--czRu2D|ren|sohu|citic|网址|xn--ses554g|中信|xn--fiq64b|asia|ren|top|top|or.kr|or.kr|xn--czRu2D|商城|商标|or.id|or.id|org.zw|org.tr|org.nz|org.sg|org.sa|org.ro|org.pl|org.pg|org.np|org.mt|org.mt|org.kw|org.eg|or.th|or.jp|org.nz|khb.ru|gov.hk|or.jp|gov.mo|gov.hk|org.br|prchina.org|focacsummit.org|focacsummit.org|org.pe|org.mx|chinaconsulatesf.org|khb.ru|tw|cn.com|tel|信息|广东|佛山|xn--vuq861b|xn--xhq521b|xn--1qqw23a|xin|xin|网店|sohu|手机)$/);
	return _Reg.test(this.toString());			
}

/*
 * 功能描述:判断字符串对象值是否是web网址域名格式 返回类型:返回布尔类型
 */
String.prototype.IsWebSite=function(){
	var _Reg=new RegExp(/[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/);
	return _Reg.test(this.toString());			
}

/*
功能描述:判断字符串对象值是否是web网址域名格式
返回类型:返回布尔类型
*/
String.prototype.IsURL=function(){
	if(this.toString().length>=4){
		return true;
	}else return false;
}

/*
功能描述:判断字符串对象值是否是web网址域名格式
返回类型:返回布尔类型
*/
String.prototype.IsPort=function(){
	if(parseInt(this)>=0&&parseInt(this)<=65535){
		return true;
	}else	return false;
}

/*
    功能描述:判断字符串对象值是否是html格式
    返回类型:返回布尔类型
*/
String.prototype.IsHTML=function(){
	var _Reg=new RegExp(/<(.*)>.*<\/\1>|<(.*) \/>/);
	return _Reg.test(this.toString());			
}


/*
    功能描述:判断字符串对象值是否是电话号码格式
    返回类型:返回布尔类型
*/
String.prototype.IsPhone=function(){
	//var _Reg=new RegExp(/(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{5,8})?$)|(^\([0][1-9]{2,3}\)\d{5,8}(\(\d{1,8}\))?$)|(^\d{3,14}$)/);
	//return _Reg.test(this.toString());	
	var str = this.toString();
	if(str==null||str==''){
		return false;
	} 

	var exp1 = /^[0-9\-\;]*$/;
	var exp2 = /^(086-(0\d{3}|0\d{2})-(\d{8}|\d{7}))+(-(\d{4}|\d{3}))?$/;
	
	if(!(exp1.test(str) && exp2.test(str))){
		return false;
	}

	return true;		
}

/*
    功能描述:判断字符串对象值是否是手机号码格式
    返回类型:返回布尔类型
*/
String.prototype.IsMobile=function(){
	 var reg=new RegExp(/^[0]?(130|131|132|153|156|182|185|186|188|134|135|136|137|138|139|150|151|152|155|157|158|159|133|153|180|181|187|189|183|147|170|177)[0-9]{8}$/);
	 return (reg.test(this));
}

/*
功能描述:判断字符串对象值是否是支持的手机号码格式
返回类型:返回布尔类型
*/
String.prototype.IsValidMobile=function(){
 var reg=new RegExp(/^[0]?(177|170|133|153|189|181|180)[0-9]{8}$/);
 return (reg.test(this));
}

/*
    功能描述:判断字符串对象值是否是mail格式
    返回类型:返回布尔类型
*/
String.prototype.IsMail=function(){
	var _Reg=new RegExp(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+(info|biz|com|edu|gov|net|am|bz|cn|cx|hk|jp|tw|vc|vn)$/);
	//var _Reg=new RegExp(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);
	return _Reg.test(this.toString());
}

/*
    功能描述:判断字符串对象值是否是ip地址格式
    返回类型:返回布尔类型
*/
String.prototype.IsIPAddress=function(){
	var _Reg=new RegExp(/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/);
	return _Reg.test(this.toString());
}		

/*
    功能描述:判断字符串对象值是否是正整数格式
    返回类型:返回布尔类型
*/
String.prototype.IsNumber=function(){
	var _Reg=new RegExp(/^[0-9]+$/);
	return _Reg.test(this.toString());
}		


/*
    功能描述:用户名验证：只能是英文字符，数字，下滑线和减号  首字符必须为英文或数字 不允许汉字 

    返回类型:返回布尔类型
*/
String.prototype.IsUserName=function(){
	var _Reg=new RegExp(/^[A-Za-z0-9][A-Za-z0-9_\-]{3,15}$/);
	return _Reg.test(this.toString());
}	

/*
    -----------------------------------------------------
*/