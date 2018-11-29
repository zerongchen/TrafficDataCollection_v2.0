package com.aotain.tdc.dto.common;

import java.util.Date;

public class DpiClogBean {
	private int LOGID;
	private int OP_CODE;
	private int OP_TYPE;
	private int RECID1;
	private int RECID2;
	private int BIZ_SEQ;
	private long CREATE_OPER;
	private Date CREATE_TIME;
	public int getLOGID() {
		return LOGID;
	}
	public int getOP_CODE() {
		return OP_CODE;
	}
	public int getOP_TYPE() {
		return OP_TYPE;
	}
	public int getRECID1() {
		return RECID1;
	}
	public int getRECID2() {
		return RECID2;
	}
	public int getBIZ_SEQ() {
		return BIZ_SEQ;
	}
	public long getCREATE_OPER() {
		return CREATE_OPER;
	}
	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setLOGID(int lOGID) {
		LOGID = lOGID;
	}
	public void setOP_CODE(int oP_CODE) {
		OP_CODE = oP_CODE;
	}
	public void setOP_TYPE(int oP_TYPE) {
		OP_TYPE = oP_TYPE;
	}
	public void setRECID1(int rECID1) {
		RECID1 = rECID1;
	}
	public void setRECID2(int rECID2) {
		RECID2 = rECID2;
	}
	public void setBIZ_SEQ(int bIZ_SEQ) {
		BIZ_SEQ = bIZ_SEQ;
	}
	public void setCREATE_OPER(long cREATE_OPER) {
		CREATE_OPER = cREATE_OPER;
	}
	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
}
