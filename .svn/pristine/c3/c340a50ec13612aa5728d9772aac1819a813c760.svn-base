package com.aotain.tdc.service.common;

import java.util.concurrent.SynchronousQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.common.security.SecurityUtils;
import com.aotain.tdc.dao.common.TableColumnDao;
import com.aotain.tdc.dao.common.UserColumnDao;
import com.aotain.tdc.dto.common.TableParam;
import com.aotain.tdc.dto.common.UserColumnDTO;

@Service
@Transactional(readOnly=true)
public class UserColumnService {
	
	@Autowired
	private UserColumnDao userColumnDao;
	
	@Autowired
	private TableColumnDao tableColumnDao;
	
//	private static SynchronousQueue<Integer> updateQueue = new SynchronousQueue<Integer>();
	
	@Transactional
	public int updateColumnUser(UserColumnDTO userColumnDTO) {
		try{
			synchronized(userColumnDTO){
//				updateQueue.offer(1);
			
				int count = validateRepeat(userColumnDTO);
				if(count == 0){
//					updateQueue.take();
					return insertUserColumnData(userColumnDTO);
				}else {
//					updateQueue.take();
					return userColumnDao.updateColumnUser(userColumnDTO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 更新用户对应列的信息
	 * @param tableName 表名
	 * @param fieldName 字段（列）名
	 * @param showType 显示类型（1-显示、2-隐藏）
	 * @return
	 */
	@Transactional
	public int updateColumnUser(String tableName, String fieldName, int showType){
		TableParam tableParam = new TableParam(tableName, fieldName);
		int dictId = tableColumnDao.getDictId(tableParam); //通过表名及列名获取列对应ID
		int userId = Integer.parseInt(SecurityUtils.getUserId()+"");
		UserColumnDTO userColumnDTO = new UserColumnDTO(userId, dictId, showType);
		int count = validateRepeat(userColumnDTO); //判断列是否已经存在
		if(count == 0){ //不存在时做新增操作
			return insertUserColumnData(userColumnDTO);
		}else { //存在时做更新操作
			return userColumnDao.updateColumnUser(userColumnDTO);
		}
	}
	
	public int validateRepeat(UserColumnDTO userColumnDTO){
		return userColumnDao.validateRepeat(userColumnDTO);
	}
	
	@Transactional
	public int insertUserColumnData(UserColumnDTO userColumnDTO){
		return userColumnDao.insertUserColumnData(userColumnDTO);
	}
}
