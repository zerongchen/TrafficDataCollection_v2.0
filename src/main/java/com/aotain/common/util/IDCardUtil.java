package com.aotain.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证号码的工具类
 * 中国大陆的身份证号码的组成：
 * 由十七位数字本体码和一位数字校验码组成。
 * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
 * <p>
 * (1-6)六位数字地址码：表示编码对象常住户口所在县（市、镇、区）的行政区划代码。
 * 	1-2位：省、自治区、直辖市代码； 3-4位：地级市、盟、自治州代码； 5-6位：县、县级市、区代码。
 * </p>
 * <p>
 * (7-14)八位数字出生日期码：编码对象出生的年、月、日，其中年份用四位数字表示
 * </p>
 * <p>
 * (15-17)三位数字顺序码：对同年、月、日出生的人员编定的顺序号。其中第十七位奇数分给男性，偶数分给女性。
 * </p>
 * <p>
 * (18)一位数字校验码：是根据前面十七位数字码计算出来的检验码
 * </p>
 * @author Jason.CW.Cheung
 */
public class IDCardUtil {
	
	private static Map<Integer, String> areaCodeMap = new HashMap<Integer, String>();
	private static int[] modulusArray = new int[]{7,9,10,5,8,4,2,1,6,3};
	private static String checkCodeArray = "10X98765432";
	private static String leapYearRegularExpressionForOld = "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$";
	private static String averageYearRegularExpressionForOld = "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$";
	private static String leapYearRegularExpressionForNew = "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$";
	private static String averageYearRegularExpressionForNew = "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$";
	
	static {
		areaCodeMap.put(11, "北京市");
		areaCodeMap.put(12, "天津市");
		areaCodeMap.put(13, "河北省");
		areaCodeMap.put(14, "山西省");
		areaCodeMap.put(15, "内蒙古自治区");
		areaCodeMap.put(21, "辽宁省");
		areaCodeMap.put(22, "吉林省");
		areaCodeMap.put(23, "黑龙江省");
		areaCodeMap.put(31, "上海市");
		areaCodeMap.put(31, "江苏省");
		areaCodeMap.put(33, "浙江省");
		areaCodeMap.put(34, "安徽省");
		areaCodeMap.put(35, "福建省");
		areaCodeMap.put(36, "江西省");
		areaCodeMap.put(37, "山东省");
		areaCodeMap.put(41, "河南省");
		areaCodeMap.put(42, "湖北省");
		areaCodeMap.put(43, "湖南省");
		areaCodeMap.put(44, "广东省");
		areaCodeMap.put(45, "广西壮族自治区");
		areaCodeMap.put(46, "海南省");
		areaCodeMap.put(50, "重庆市");
		areaCodeMap.put(51, "四川省");
		areaCodeMap.put(52, "贵州省");
		areaCodeMap.put(53, "云南省");
		areaCodeMap.put(54, "西藏自治区");
		areaCodeMap.put(61, "陕西省");
		areaCodeMap.put(62, "甘肃省");
		areaCodeMap.put(63, "青海省");
		areaCodeMap.put(64, "宁夏回族自治区");
		areaCodeMap.put(65, "新疆维吾尔自治区");
	}
	
	public static boolean chcekIdCardNumber(String idCard){
		Pattern pattern = null;
		Matcher matcher = null;
		boolean flag = false;
		if(StringUtil.isEmptyString(idCard)){
			flag = false;
		}
		flag = checkAreaCode(idCard);
		switch(idCard.length()){
		case 15:
			if(parseInt((idCard.substring(6, 8) + "1900")) % 4 == 0 || 
					( parseInt((idCard.substring(6, 8)) + "1900")) % 100 == 0 && parseInt((idCard.substring(6, 8) + "1900")) % 4 == 0){
				pattern = Pattern.compile(leapYearRegularExpressionForOld);
			} else {
				pattern = Pattern.compile(averageYearRegularExpressionForOld);
			}
			matcher = pattern.matcher(idCard);
			if(matcher.matches()){
				flag = true;
			} else {
				flag = false;
			}
			break;
		case 18:
			/*if(parseInt(idCard.substring(6, 10)) % 4 == 0 || 
					( parseInt(idCard.substring(6, 10)) % 100 == 0 && parseInt(idCard.substring(6, 10)) % 4 == 0)){
				pattern = Pattern.compile(leapYearRegularExpressionForNew);
			} else {
				pattern = Pattern.compile(averageYearRegularExpressionForNew);
			}
			matcher = pattern.matcher(idCard);
			if(matcher.matches()){
				//计算校验位
				flag = checkIdCheckCode(idCard);
			} else {
				flag = false;
			}*/
			if(checkIdBirthday(idCard)){
				if(checkIdCheckCode(idCard)){
					flag = true;
				} 
			} else {
				flag = false;
			}
			break;
		default:
			return false;
		}
		return flag;
	}
	
	public static boolean checkIdBirthday(String idCard) {
		Pattern pattern = null;
		Matcher matcher = null;
		boolean flag = false;
		if(checkIdCardLength(idCard)){
			if(parseInt(idCard.substring(6, 10)) % 4 == 0 || 
					( parseInt(idCard.substring(6, 10)) % 100 == 0 && parseInt(idCard.substring(6, 10)) % 4 == 0)){
				pattern = Pattern.compile(leapYearRegularExpressionForNew);
			} else {
				pattern = Pattern.compile(averageYearRegularExpressionForNew);
			}
			matcher = pattern.matcher(idCard);
			if(matcher.matches()){
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	
	public static boolean checkIdCheckCode(String idCard) {
		boolean flag = false;
		if(checkIdCardLength(idCard)){
			String[] splitArray = StringUtil.trimAll(idCard).split("");
			Object[] idCardArray = removeEmptyElement(splitArray);
			int total = 0;
			for (int i = 0; i < idCardArray.length - 1; i++) {
				total += parseInt((String) idCardArray[i]) * modulusArray[i % 10];
			}
			int remainder = total % 11;
			String checkCode = checkCodeArray.substring(remainder, remainder + 1);
			if(idCardArray[17].equals(checkCode)){
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean checkAreaCode(String idCard) {
		boolean flag = false;
		if(checkIdCardLength(idCard)){
			if(null == areaCodeMap.get(parseInt(idCard.substring(0, 2)))){
				flag = false;
			} else {
				flag = true;
			}
		}
		return flag;
	}
	
	public static boolean checkIdCardLength(String idCard) {
		boolean flag = false;
		if(idCard != null && idCard.length() == 18){
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	private static Integer parseInt(String s){
		if(StringUtil.isAllDigit(s)){
			return Integer.valueOf(s);
		} else {
			return 0;
		}
	}
	
	private static Object[] removeEmptyElement(String[] data) {
		List<String> list = new ArrayList<String>(0);
		for (int i = 0; i < data.length; i++) {
			if(!"".equals(data[i])){
				list.add(data[i]);
			}
		}
		return list.toArray();
	}
	
	public static void main(String[] args) {
		System.out.println(chcekIdCardNumber("420821198709136117"));
	}
	

}
