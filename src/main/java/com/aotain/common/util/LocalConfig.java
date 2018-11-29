package com.aotain.common.util;

import java.util.ResourceBundle;

public class LocalConfig {
	private String rightUri;
	private int deployid;
	private int isQueryDateTime;
	private String passPort;
	private int jobflag;
	private static LocalConfig instance;
	private int fileSize;
	
	private String areaKpiExl;
	private String carrierKpiExl;
	private String roomKpiExl;
	private String productKpiExl;
	private String ipKpiExl;
	
	private String areaKpiTotalScoreExl;
	private String carrierKpiTotalScoreExl;
	private String roomKpiTotalScoreExl;
	private String productKpiTotalScoreExl;
	private String ipKpiTotalScoreExl;
	
	private int opCodeRe;
	
	private String bizIDs;
	private String hostName;
	private String delayTime;
	private String saveSendFilePath;

	public LocalConfig(){
		ResourceBundle config = ResourceBundle.getBundle("config/config");
		rightUri = config.getString("uri");
		deployid = Integer.parseInt(config.getString("deployid"));
		isQueryDateTime = Integer.parseInt(config.getString("isQueryDateTime"));
		passPort = config.getString("passPort");
		
		areaKpiExl = config.getString("AREA_KPI_EXL");
		carrierKpiExl = config.getString("CARRIER_KPI_EXL");
		roomKpiExl = config.getString("ROOM_KPI_EXL");
		productKpiExl = config.getString("PRODUCT_KPI_EXL");
		ipKpiExl = config.getString("IP_KPI_EXL");
		
		areaKpiTotalScoreExl = config.getString("AREA_KPI_TOTAL_SCORE_EXL");
		carrierKpiTotalScoreExl = config.getString("CARRIER_KPI_TOTAL_SCORE_EXL");
		roomKpiTotalScoreExl = config.getString("ROOM_KPI_TOTAL_SCORE_EXL");
		productKpiTotalScoreExl = config.getString("PRODUCT_KPI_TOTAL_SCORE_EXL");
		ipKpiTotalScoreExl = config.getString("IP_KPI_TOTAL_SCORE_EXL");
		
		fileSize = Integer.parseInt(config.getString("upload.maxFileSize"));
		
		opCodeRe = Integer.parseInt(config.getString("opCode.re"));
		
		bizIDs = config.getString("BIZ_IDS");
		hostName = config.getString("HOSTNAME");
		delayTime = config.getString("DELAYTIME");
		saveSendFilePath = config.getString("SAVESENDFILEPATH");
	}
	
	public int getFileSize(){
		return fileSize;
	}
	
	public int getJobflag(){
		return jobflag;
	}
	
	public String getRightUri() {
		return rightUri;
	}
	public int getDeployid() {
		return deployid;
	}
	public int getIsQueryDateTime() {
		return isQueryDateTime;
	}
	public static LocalConfig getInstance() {
		if(instance == null){
			instance = new LocalConfig();
		}
		return instance;
	}

	public String getPassPort() {
		return passPort;
	}

	public String getAreaKpiExl() {
		return areaKpiExl;
	}

	public String getCarrierKpiExl() {
		return carrierKpiExl;
	}

	public String getRoomKpiExl() {
		return roomKpiExl;
	}

	public String getProductKpiExl() {
		return productKpiExl;
	}

	public String getIpKpiExl() {
		return ipKpiExl;
	}

	public String getAreaKpiTotalScoreExl() {
		return areaKpiTotalScoreExl;
	}

	public String getCarrierKpiTotalScoreExl() {
		return carrierKpiTotalScoreExl;
	}

	public String getRoomKpiTotalScoreExl() {
		return roomKpiTotalScoreExl;
	}

	public String getProductKpiTotalScoreExl() {
		return productKpiTotalScoreExl;
	}

	public String getIpKpiTotalScoreExl() {
		return ipKpiTotalScoreExl;
	}

	public int getOpCodeRe() {
		return opCodeRe;
	}
	
	public String getBizIDs(){
		return bizIDs;
	}
	
	public String getHostName(){
		return hostName;
	}

	public String getDelayTime() {
		return delayTime;
	}

	public String getSaveSendFilePath() {
		return saveSendFilePath;
	}

}
