package com.aotain.tdc.dao.traffic;

import java.util.HashMap;
import java.util.List;

import com.aotain.tdc.dto.common.BaseDTO;

public interface TrafficOverviewDao {
	public List<HashMap<String, Object>> getTrafficTrendData(BaseDTO dto);
	public List<HashMap<String, Object>> getServerbuildTrafficData(BaseDTO dto);
	public List<HashMap<String, Object>> getProtocolTrafficData(BaseDTO dto);
	/**
	 * 
	* @Title: getIPTrafficData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param  @param dto
	* @param  @return    设定文件 
	* @author zhenggf
	* @return List<HashMap<String,Object>>    返回类型 
	* @throws
	 */
	public List<HashMap<String, Object>> getIPTrafficData(BaseDTO dto);
	public List<HashMap<String, Object>> getServiceTrafficData(BaseDTO dto);
	public List<HashMap<String, Object>> getRegionTrafficData(BaseDTO dto);
	public List<HashMap<String, Object>> getCarrierTrafficData(BaseDTO dto);
	
	
}
