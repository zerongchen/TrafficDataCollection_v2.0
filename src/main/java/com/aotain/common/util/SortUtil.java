package com.aotain.common.util;

import java.util.ArrayList;
import java.util.List;

public class SortUtil {
	public static int compareTo(String value1, String value2){
		if(value1 == null){
			return -1;
		}
		if(StringUtil.isAllDigit(value1) 
				&& StringUtil.isAllDigit(value1)){
			Long l1 = Long.parseLong(value1);
			Long l2 = Long.parseLong(value2);
			return l1.compareTo(l2);
		}
		return value1.compareTo(value2);
	}

	@SuppressWarnings("unchecked")
	public static List getPagerList(int currentPage, int avgRows, int totalRows, List list) {
		if(list == null
				|| list.size() < 0 ){
			return list;
		}
		List newList = new ArrayList();
		for (int i = (currentPage - 1) * avgRows; i < totalRows
				&& i < currentPage * avgRows; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}	
}
