package com.aotain.common.util;

import org.apache.commons.lang.StringUtils;

public class SQLUtil {

	public static String wrapFuzzyQuery(String srcStr) {
		String result = srcStr;
		result = StringUtils.replace(result, "[", "\\[");
		result = StringUtils.replace(result, "_", "\\_");
		result = StringUtils.replace(result, "%", "\\%");
		result = StringUtils.replace(result, "^", "\\^");
		result = "'%" + result + "%'";
		return result;
	}
	
	public static String wrapFuzzyQuery(String... srcStr) {
		StringBuffer result = new StringBuffer("('");
		for (int i = 0; i < srcStr.length; i++) {
			result.append(srcStr[i]);
			result.append("','");
		}
		result.replace(result.length() - 2, result.length(), "");		
		result.append(")");
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(wrapFuzzyQuery("20130902","20130903","20130904","20130905"));
	}
	
	
}
