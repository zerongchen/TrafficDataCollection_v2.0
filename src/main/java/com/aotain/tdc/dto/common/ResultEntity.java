package com.aotain.tdc.dto.common;

public class ResultEntity {

	public int resultCode;
	public String errorMsg;
	public Object resultData;
	
	public ResultEntity() {
		resultCode = 0;
		errorMsg = "";
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

}