package com.aotain.iqis.constant;

/**
 * 流速类型,针对不同分流器设备采集到的流速单位进行转换
 * @author jason
 *
 */
public enum SpeedType {
	b(1),Kb(2),Mb(3);
	
	private int speed_code;
	
	private SpeedType(int speed_code) {
		this.speed_code = speed_code; 
	}
	
	public static SpeedType getSpeedTypeByCode(int speed_code){
		SpeedType[] speedTypes = SpeedType.values();
		for(SpeedType speedType : speedTypes){
			if(speedType.getSpeed_code() == speed_code){
				return speedType;
			}
		}
		return null;
	}

	public int getSpeed_code() {
		return speed_code;
	}

	public void setSpeed_code(int speed_code) {
		this.speed_code = speed_code;
	}
	
	
}
