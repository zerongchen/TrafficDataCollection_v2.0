package com.aotain.iqis.constant;

import java.util.ArrayList;
import java.util.List;

import com.aotain.iqis.model.common.DictEntity;

/**
 * 统计周期参数值设置
 * 
 * @author jason
 * 
 */
public enum StatType {
	MIN("1", "分钟"), HOUR("2", "小时"), DAY("3", "日"), WEEK("4", "周"), MONTH("5",
			"月");
	private String value;
	private String text;

	private StatType(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static List<DictEntity> GetStatTypeList(int callType) {
		List<DictEntity> dtStatType = new ArrayList<DictEntity>();
		if (callType == 1) {
			dtStatType.add(new DictEntity(StatType.MIN.getValue(), StatType.MIN
					.getText()));
			dtStatType.add(new DictEntity(StatType.HOUR.getValue(),
					StatType.HOUR.getText()));
			dtStatType.add(new DictEntity(StatType.DAY.getValue(), StatType.DAY
					.getText()));
		}else if (callType == 2) {
			dtStatType.add(new DictEntity(StatType.HOUR.getValue(),
					StatType.HOUR.getText()));
			dtStatType.add(new DictEntity(StatType.DAY.getValue(), StatType.DAY
					.getText()));
		}else if (callType == 3) {
			dtStatType.add(new DictEntity(StatType.DAY.getValue(), StatType.DAY
					.getText()));
			dtStatType.add(new DictEntity(StatType.WEEK.getValue(),
					StatType.WEEK.getText()));
			dtStatType.add(new DictEntity(StatType.MONTH.getValue(),
					StatType.MONTH.getText()));
		}else if (callType == 4) {
			dtStatType.add(new DictEntity(StatType.MIN.getValue(), StatType.MIN
					.getText()));
			dtStatType.add(new DictEntity(StatType.HOUR.getValue(),
					StatType.HOUR.getText()));
			dtStatType.add(new DictEntity(StatType.DAY.getValue(), StatType.DAY
					.getText()));
			dtStatType.add(new DictEntity(StatType.WEEK.getValue(),
					StatType.WEEK.getText()));
			dtStatType.add(new DictEntity(StatType.MONTH.getValue(),
					StatType.MONTH.getText()));
		}else if (callType == 5) {
			dtStatType.add(new DictEntity(StatType.HOUR.getValue(),
					StatType.HOUR.getText()));
			dtStatType.add(new DictEntity(StatType.DAY.getValue(), StatType.DAY
					.getText()));
			dtStatType.add(new DictEntity(StatType.WEEK.getValue(),
					StatType.WEEK.getText()));
			dtStatType.add(new DictEntity(StatType.MONTH.getValue(),
					StatType.MONTH.getText()));
		}
		return dtStatType;
	}
	
	/**
	 * 根据value值获取枚举对象
	 * @param value
	 * @return
	 */
	public static StatType getStatTypeByValue(int value){
		for(StatType statType : StatType.values()){
			if(Integer.parseInt(statType.getValue()) == value){
				return statType;
			}
		}
		return null;
	}
}
