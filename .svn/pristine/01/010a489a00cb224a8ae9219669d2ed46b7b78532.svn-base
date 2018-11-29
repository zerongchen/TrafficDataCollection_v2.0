package com.aotain.tdc.service.serviceConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.serviceConfiguration.RoomTrafficManageDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DicBizConfig;
import com.aotain.tdc.dto.common.DicBizInfo;
import com.aotain.tdc.dto.common.DicRoomTrafficConfig;
import com.aotain.tdc.dto.common.DicRoomTrafficInfo;


@Service("roomtrafficManageService")
public class RoomTrafficManageService {
	
	@Autowired
	RoomTrafficManageDao roomtrafficManageDao;
	
	@Autowired
	CommonCache commonCache;
	
	public int updateRoomTrafficParent(BaseDTO dto) {
		return roomtrafficManageDao.updateRoomTrafficParent(dto);
	}

	public int insertRoomInfo(DicRoomTrafficInfo roominfo) {
		int d = 0;
		//判断是否重复
		d = roomtrafficManageDao.IsexitbuildInfo(roominfo);
		if(d >=1 ) return 2;
		d = roomtrafficManageDao.insertRoomInfo(roominfo);		
		return d;
	}
	
	public int insertRoomTrafficInfo(DicRoomTrafficInfo roominfo) {
		int d = 0;
		//判断是否重复
		d = roomtrafficManageDao.IsexitRoomInfo(roominfo);
		if(d >= 1) return 2;
		for(DicRoomTrafficConfig roomconfig : roominfo.getRoomtrafficConfigs()){
			d += roomtrafficManageDao.IsexitRoomConfig(roomconfig);
			if(d >= 1) { d=3 ;break;}			
		}
		if(d != 0) return d;
		d = roomtrafficManageDao.insertRoomTrafficInfo(roominfo);
		for(DicRoomTrafficConfig roomconfig : roominfo.getRoomtrafficConfigs()){
			roomconfig.setServerroomid(roominfo.getServerroomid());
			roomconfig.setServerbuildid(roominfo.getServerbuildid());
			d = roomtrafficManageDao.insertRoomTrafficconfig(roomconfig);
		}
		return d;
	}
	

	public int delRoomTrafficInfo(DicRoomTrafficInfo roominfo) {
		int d = 0;
		//删除机房对应的配置信息
		d += roomtrafficManageDao.delConfig(roominfo);
		//删除机房信息
		d += roomtrafficManageDao.delroom(roominfo);		
		return d;
	}
	
	public int delBuildTrafficInfo(DicRoomTrafficInfo roominfo) {
		int d = 0;
		//删除机楼对应的配置信息
		d += roomtrafficManageDao.delSubConfig(roominfo);
		//删除机楼对应的机房信息
		d += roomtrafficManageDao.delSubroom(roominfo);		
		return d;
	}

	public DicRoomTrafficInfo getRoomTrafficInfo(DicRoomTrafficInfo dto) {
		DicRoomTrafficInfo info = roomtrafficManageDao.getRoomTrafficInfo(dto);
		info.setRoomtrafficConfigs(roomtrafficManageDao.getRoomTrafficConfigs(dto));
		return info;
	}

	public int updateRoomTrafficInfo(DicRoomTrafficInfo roominfo) {
		int d = 0;		
		//更新业务信息
		if( roominfo.getServerroomname() != null && roominfo.getServerroomname().length() > 0 ){
			d=  roomtrafficManageDao.IsexitRoomInfo(roominfo);
			if(d >= 1) return 2;
			else
			d += roomtrafficManageDao.updateRoomTrafficInfo(roominfo);
		}
		//更新业务配置信息
		if(roominfo.getRoomtrafficConfigs() == null) return d;
		for(DicRoomTrafficConfig roomConfig : roominfo.getRoomtrafficConfigs()){
			  roomConfig.setServerroomid(roominfo.getServerroomid());
			  d = roomtrafficManageDao.IsexitRoomConfig(roomConfig);
			   if(d >= 1) { d=3 ;break;}	
		}
		if(d != 0) return d;			
		for(DicRoomTrafficConfig roomConfig : roominfo.getRoomtrafficConfigs()){
			//--如果存在，更新
			roomConfig.setServerroomid(roominfo.getServerroomid());
			roomConfig.setServerbuildid(roominfo.getServerbuildid());
			if(roomConfig.getId() > 0){
				d += roomtrafficManageDao.updateRoomTrafficConfig(roomConfig);
			}else{
				//--如果不存在，添加				
				d += roomtrafficManageDao.insertRoomTrafficconfig(roomConfig);
			}
		}
		return d;
	}
	
	public int delRoomConfigInfos(DicRoomTrafficConfig bizinfo) {
		int d = 0;
		for(String id : bizinfo.getIds().split(",")){
			d += roomtrafficManageDao.delConfigById(Integer.parseInt(id));
		}
		return d;
	}
	
}
