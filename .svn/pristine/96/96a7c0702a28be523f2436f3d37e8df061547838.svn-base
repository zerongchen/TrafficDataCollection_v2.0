package com.aotain.tdc.service.traffic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aotain.common.cache.CommonCache;
import com.aotain.tdc.dao.traffic.RoomTrafficDao;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.service.common.CommonServiceImpl;
@Service("roomTrafficService")
public class RoomTrafficService {
	
	@Autowired
	private RoomTrafficDao roomTrafficDao;
	
	@Autowired
	private CommonCache commonCache;
	/**
	 * 
	* @Title: getTrendTableDatas 
	* @Description: TODO(机房流量) 
	* @param  @param dto    设定文件 
	* @author 修该人：zhenggf
	* @return void    返回类型 
	* @throws
	 */
	public void getTrendTableDatas(BaseDTO dto) {
		long startTime=System.currentTimeMillis();  
		dto.setResultList(roomTrafficDao.getTrendTableDatas(dto));
		for(int i = 0; i < dto.getResultList().size(); i++){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				if(dto.getQueryTableName().endsWith("_MIN")){
					result.put("R_STATTIME", new SimpleDateFormat("yyyy-MM-dd HH:mm").format((Date)result.get("R_STATTIME")) );
				}else{
					result.put("R_STATTIME", new SimpleDateFormat("yyyy-MM-dd HH").format(new SimpleDateFormat("yyyyMMddHH").parse(String.valueOf(result.get("R_STATTIME"))))) ;
				}
				if(dto.getQueryServerBuildId()!=null && dto.getQueryServerBuildId() > 0){
					result.put("SERVERBUILD_ID", commonCache.getDicServerBuildBeanCache().get(Long.parseLong(result.get("SERVERBUILD_ID")+"")).getServerBuildName()) ;
				}else{
					result.put("SERVERBUILD_ID", "全部") ;
				}
				result.put("SERVERROOM_ID", commonCache.getDicServerRoomBeanCache().get(Long.parseLong(result.get("SERVERROOM_ID")+"")).getServerRoomName());
				//result.put("CLASSID", commonCache.getDicProtoClassBeanCache().get(Long.parseLong(result.get("CLASSID")+"")).getClassName());
				result.put("FLOW_TOTAL_RATE", String.format("%.2f", Double.parseDouble(result.get("FLOW_TOTAL")+"")/CommonServiceImpl.getSecondScale(dto)/1024/1024*8 ));
				result.put("FLOW_UP_RATE", String.format("%.2f", Double.parseDouble(result.get("FLOW_UP")+"")/CommonServiceImpl.getSecondScale(dto)/1024/1024*8 ));
				result.put("FLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("FLOW_DN")+"")/CommonServiceImpl.getSecondScale(dto)/1024/1024*8 ));
				
				result.put("FLOW_TOTAL", String.format("%.2f", (+Double.parseDouble(result.get("FLOW_TOTAL")+"")/1024/1024/1024)) );
				result.put("FLOW_UP", String.format("%.2f", (Double.parseDouble(result.get("FLOW_UP")+"")/1024/1024/1024) ) );
				result.put("FLOW_DN", String.format("%.2f", (Double.parseDouble(result.get("FLOW_DN")+"")/1024/1024/1024) ) );
				/**
				 * 计算质量分数 start
				 */
/*				KPIBean kpiBean = new KPIBean();
				kpiBean.setClientDelay(Float.parseFloat(result.get("CLIENT_DELAY").toString()));
				kpiBean.setDownRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_DN_RATE").toString()));
				kpiBean.setRespDelay(Float.parseFloat(result.get("RESPONSE_DELAY").toString()));
				kpiBean.setServerDelay(Float.parseFloat(result.get("SERVER_DELAY").toString()));
				kpiBean.setSuccessPercent(Float.parseFloat(result.get("SUCCESSCONNECT_RATE").toString()));
				kpiBean.setTimeoutPercent(Float.parseFloat(result.get("CONNECTTIMEOUT_RATE").toString()));
				kpiBean.setUpRetransPercent(Float.parseFloat(result.get("RETRANSPACKAGE_UP_RATE").toString()));
				
				result.put("QUALITY_SCORE", String.format("%.2f", KPIUtil.computeScore(kpiBean, KPIType.ROOM_KPI, commonCache.getDicArrayListCache().get(CacheType.KPICONFIG))));*/
				/**
				 * 计算质量分数 end
				 */
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		dto.setTotalCounts(roomTrafficDao.getTrendTableDatasTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
		long endTime=System.currentTimeMillis(); //获取结束时间

		System.out.println("------------getStatisticTableDatas程序运行时间： "+(endTime-startTime)+"ms");
	}
	
	@SuppressWarnings("unchecked")
	public void getStatisticTableDatas(BaseDTO dto) throws ExecutionException{
		long startTime=System.currentTimeMillis();  
		if(dto.getQueryServerBuildId()==null || dto.getQueryServerBuildId() <= 0){
			dto.setQueryFieldName("SERVERBUILD_ID");
		}else if(dto.getQueryServerRoomId()==null || dto.getQueryServerRoomId() <= 0){
			dto.setQueryFieldName("SERVERROOM_ID");
		}else if(dto.getQueryClassId()==null || dto.getQueryClassId() <= 0){
			dto.setQueryFieldName("CLASSID");
		}else{
			dto.setQueryFieldName("CLASSID");
		}
		if(dto.getOrder() == null || dto.getOrder().equals("") || dto.getSort() == null || dto.getSort().equals("")){
			dto.setOrder("DESC");
			dto.setSort("FLOW_TOTAL");
		}
		dto.setResultList(roomTrafficDao.getStatisticTableDatas(dto));
		HashMap<String, Object> total = new HashMap<String, Object>();
		total.put("SERVERBUILD_ID", "全部") ;
		total.put("CLASSID", "全部") ;
		total.put("SERVERROOM_ID", "全部") ;
		total.put("FLOW_TOTAL_PERCENT", "100.00");
		total.put("FLOW_TOTAL", 0);
		total.put("FLOW_UP", 0);
		total.put("FLOW_DN", 0);
		total.put("FLOW_TOTAL_RATE", 0.0);
		total.put("FLOW_UP_RATE", 0.0);
		total.put("FLOW_DN_RATE", 0.0);
		for(int i = 0; i < dto.getResultList().size(); i++){
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				result.put("SERVERBUILD_ID", commonCache.getDicServerBuildBeanCache().get(Long.parseLong((result.get("SERVERBUILD_ID")==null?0:result.get("SERVERBUILD_ID"))+"")).getServerBuildName()) ;
				result.put("SERVERROOM_ID", commonCache.getDicServerRoomBeanCache().get(Long.parseLong((result.get("SERVERROOM_ID")==null?0:result.get("SERVERROOM_ID"))+"")).getServerRoomName());
				//result.put("CLASSID", commonCache.getDicProtoClassBeanCache().get(Long.parseLong((result.get("CLASSID")==null?0:result.get("CLASSID"))+"")).getClassName());
				result.put("CLASSID", "全部");
				
				result.put( "FLOW_UP_RATE", String.format("%.2f", Double.parseDouble(result.get("FLOW_UP_RATE")+"") / CommonServiceImpl.getSecondScale(dto) / Double.parseDouble( result.get("COUNTNUM")+"")/1024/1024*8 ) );
				result.put( "FLOW_DN_RATE", String.format("%.2f", Double.parseDouble(result.get("FLOW_DN_RATE")+"") / CommonServiceImpl.getSecondScale(dto) / Double.parseDouble( result.get("COUNTNUM")+"")/1024/1024*8) );
				result.put( "FLOW_TOTAL_RATE", String.format("%.2f", Double.parseDouble(result.get("FLOW_TOTAL_RATE")+"") / CommonServiceImpl.getSecondScale(dto) / Double.parseDouble( result.get("COUNTNUM")+"")/1024/1024*8) );
				
				total.put( "FLOW_UP", Long.valueOf(result.get("FLOW_UP")+"") + Double.parseDouble(total.get("FLOW_UP")+"") );
				total.put( "FLOW_UP_RATE", String.format("%.2f", (Double.parseDouble(result.get("FLOW_UP_RATE")+"")+Double.parseDouble(total.get("FLOW_UP_RATE")+""))) );
				total.put( "FLOW_DN",  Double.parseDouble (result.get("FLOW_DN")+"")+Double.parseDouble (total.get("FLOW_DN")+""));
				total.put( "FLOW_DN_RATE", String.format("%.2f", (Double.parseDouble(result.get("FLOW_DN_RATE")+"") + Double.parseDouble(total.get("FLOW_DN_RATE")+""))) );
				total.put( "FLOW_TOTAL_RATE",  String.format("%.2f", (Double.parseDouble(total.get("FLOW_DN_RATE")+"") + Double.parseDouble(total.get("FLOW_UP_RATE")+""))) );
				
				result.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(result.get("FLOW_UP")+"")+Double.parseDouble(result.get("FLOW_DN")+""))/1024/1024/1024)) );
				result.put("FLOW_UP", String.format("%.2f", (Double.parseDouble(result.get("FLOW_UP")+"")/1024/1024/1024) ) );
				result.put("FLOW_DN", String.format("%.2f", (Double.parseDouble(result.get("FLOW_DN")+"")/1024/1024/1024) ) );
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		total.put("FLOW_TOTAL", String.format("%.2f", ((Double.parseDouble(total.get("FLOW_UP")+"")+Double.parseDouble(total.get("FLOW_DN")+""))/1024/1024/1024)) );
		total.put("FLOW_UP", String.format("%.2f", (Double.parseDouble(total.get("FLOW_UP")+"")/1024/1024/1024) ) );
		total.put("FLOW_DN", String.format("%.2f", (Double.parseDouble(total.get("FLOW_DN")+"")/1024/1024/1024) ) );
		for(int i = 0; i < dto.getResultList().size(); i++){
			HashMap<String, Object> result = (HashMap<String, Object>) dto.getResultList().get(i);
			try{
				String.format("%.2f",Double.parseDouble(result.get("FLOW_UP").toString())/1024/1024/1024);
				if(Double.parseDouble(total.get("FLOW_TOTAL")+"") == 0){
					result.put("FLOW_TOTAL_PERCENT","0.00");
				}else{
					result.put("FLOW_TOTAL_PERCENT", String.format("%.2f",(Double.parseDouble(result.get("FLOW_TOTAL")+"")/Double.parseDouble(total.get("FLOW_TOTAL")+"")*100.0) )) ;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(dto.getResultList().size() >0 ){
			if (dto.getQueryServerRoomId()==null||"".equals(dto.getQueryServerRoomId())) {
				((List<HashMap<String, Object>>)dto.getResultList()).add(total);
			}
		
		}
		dto.setTotalCounts(roomTrafficDao.getStatisticTableDatasTotalCounts(dto));
		if(dto.getTotalCounts() == null) dto.setTotalCounts(0);
		long endTime=System.currentTimeMillis(); //获取结束时间

		System.out.println("-------------getStatisticTableDatas程序运行时间： "+(endTime-startTime)+"ms");
	}
	
}
