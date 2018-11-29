package com.aotain.tdc.dao.serviceConfiguration;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.DicBizConfig;
import com.aotain.tdc.dto.common.DicBizInfo;

public interface BizManageDao {
	
	public int updateBizParent(BaseDTO dto);

	public int IsexitBizInfo(DicBizInfo bizinfo);
	public int insertBizInfo(DicBizInfo bizinfo);
	
	public int IsexitBizconfig(@Param("bizconfig") DicBizConfig bizconfig, @Param("bizId") int bizId);
	public int insertBizconfig(@Param("bizconfig") DicBizConfig bizconfig, @Param("bizId") int bizId);

	public int delSubConfig(DicBizInfo bizinfo);

	public int delSubInfo(DicBizInfo bizinfo);

	public int delConfig(DicBizInfo bizinfo);

	public int delInfo(DicBizInfo bizinfo);

	public DicBizInfo getBizInfo(DicBizInfo dto);

	public List<DicBizConfig> getBizConfigs(DicBizInfo dto);

	public int updateBizInfo(DicBizInfo bizinfo);

	public int updateBizConfig(DicBizConfig bizConfig);

	public int delConfigById(@Param("id") int id);

}
