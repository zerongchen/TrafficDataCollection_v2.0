package com.aotain.tdc.dao.serviceConfiguration;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DicRoomTrafficConfig;
import com.aotain.tdc.dto.common.DicRoomTrafficInfo;

public interface RoomTrafficManageDao {
	
	public int updateRoomTrafficParent(BaseDTO dto);
	
	public int IsexitbuildInfo(DicRoomTrafficInfo roominfo);
	public int insertRoomInfo(DicRoomTrafficInfo roominfo);
	
	public int IsexitRoomInfo(DicRoomTrafficInfo roominfo);
	public int insertRoomTrafficInfo(DicRoomTrafficInfo roominfo);

	public int IsexitRoomConfig(DicRoomTrafficConfig roomconfig);
	public int insertRoomTrafficconfig(DicRoomTrafficConfig roomconfig);

	public int delSubConfig(DicRoomTrafficInfo roominfo);

	public int delSubroom(DicRoomTrafficInfo roominfo);

	public int delConfig(DicRoomTrafficInfo roominfo);
	
	public int delConfigById(@Param("id") int id);

	public int delroom(DicRoomTrafficInfo roominfo);
	
	public int delbuild(DicRoomTrafficInfo roominfo);

	public DicRoomTrafficInfo getRoomTrafficInfo(DicRoomTrafficInfo dto);

	public List<DicRoomTrafficConfig> getRoomTrafficConfigs(DicRoomTrafficInfo dto);

	public int updateRoomTrafficInfo(DicRoomTrafficInfo roominfo);

	public int updateRoomTrafficConfig(DicRoomTrafficConfig roomConfig);

	

}
