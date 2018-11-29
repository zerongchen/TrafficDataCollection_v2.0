package com.aotain.tdc.dao.common;

import com.aotain.tdc.dto.common.UserColumnDTO;

public interface UserColumnDao {

	int updateColumnUser(UserColumnDTO userColumnDTO);

	int validateRepeat(UserColumnDTO userColumnDTO);

	int insertUserColumnData(UserColumnDTO userColumnDTO);

}
