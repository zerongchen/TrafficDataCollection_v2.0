package com.aotain.tdc.dto.common;

import java.util.Date;
import java.util.List;

public class DicRoomTrafficInfo {
	private int serverroomid;
	private String serverroomname;
	private int serverbuildid;
	private String serverbuildname;
	private Date laseupdatetime;
	private int flag;
	private List<DicRoomTrafficConfig> roomtrafficConfigs;
	
	public int getServerroomid() {
		return serverroomid;
	}
	public void setServerroomid(int serverroomid) {
		this.serverroomid = serverroomid;
	}
	public String getServerroomname() {
		return serverroomname;
	}
	public void setServerroomname(String serverroomname) {
		this.serverroomname = serverroomname;
	}
	public int getServerbuildid() {
		return serverbuildid;
	}
	public void setServerbuildid(int serverbuildid) {
		this.serverbuildid = serverbuildid;
	}
	public String getServerbuildname() {
		return serverbuildname;
	}
	public void setServerbuildname(String serverbuildname) {
		this.serverbuildname = serverbuildname;
	}
	public Date getLaseupdatetime() {
		return laseupdatetime;
	}
	public void setLaseupdatetime(Date laseupdatetime) {
		this.laseupdatetime = laseupdatetime;
	}
	public List<DicRoomTrafficConfig> getRoomtrafficConfigs() {
		return roomtrafficConfigs;
	}
	public void setRoomtrafficConfigs(List<DicRoomTrafficConfig> roomtrafficConfigs) {
		this.roomtrafficConfigs = roomtrafficConfigs;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
