package com.aotain.tdc.dao.strategy;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aotain.tdc.dto.common.BaseDTO;

public interface ReUseDao {
	/**
	 * 获取分页列表数据
	 */
	public List<HashMap<String, Object>> getPageList(BaseDTO dto);
	/**
	 * 获取数据总数
	 */
	public int getTotalCount(BaseDTO dto);
	/**
	 * 获取policy stat data
	 */
	public List<HashMap<String, Object>> getPolicyStatData(@Param("strategyType") int strategyType, @Param("strategyId") long strategyId);
	
}
