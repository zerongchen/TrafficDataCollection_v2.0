package com.aotain.tdc.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.service.quartz.SendZabbixService;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.aotain.common.util.IOUtil;
import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.SpringUtil;
import com.aotain.common.util.StringUtil;
import com.aotain.tdc.annotation.QuartzJob;
import com.aotain.tdc.constant.QuartzJobGroupConstants;
import com.aotain.tdc.dao.quartz.SendZabbixDao;


@QuartzJob( cron="0 0/5 * * * ?",groupName=QuartzJobGroupConstants.GROUP_GET, desc = "第三方获取数据")
@Component
public class CreateSendDataTxt extends QuartzJobBean {

	//@Autowired
	private SendZabbixService sendZabbixService;
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String queryStartTime = StringUtil.getTimeByMinute(-(Integer.parseInt(LocalConfig.getInstance().getDelayTime())+5));
		String queryEndTime = StringUtil.getTimeByMinute(-(Integer.parseInt(LocalConfig.getInstance().getDelayTime())));
		String timestamp = StringUtil.getTimestamp(queryStartTime).substring(0, 10);
		String fileName = queryStartTime.replace("-", "").replace(":", "").replace(" ", "");
		
		BaseDTO dto = new BaseDTO();
		//dto.setQueryStartTime("2017-08-28 10:00:00");
		//dto.setQueryEndTime("2017-08-28 10:05:00");
		dto.setQueryStartTime(queryStartTime);
		dto.setQueryEndTime(queryEndTime);
		dto.setQueryBizIdStr(LocalConfig.getInstance().getBizIDs());
		dto.setQueryHostName(LocalConfig.getInstance().getHostName());
		sendZabbixService = new SendZabbixService();
		sendZabbixService.getTableColumns(dto);
		fileName = LocalConfig.getInstance().getSaveSendFilePath() + "DPI_FLOW_" + fileName + ".txt";
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) dto.getResultList();
		IOUtil.writeToTxt(list, fileName ,timestamp);
		System.out.println("queryStartTime :" + queryStartTime + "|" +
				   "queryEndTime :" + queryEndTime + "|" +
				   "timestamp :" + timestamp + "|" +
				   "fileName :" + fileName);
		//System.out.println("TotalCounts: " + list.size());
	}

}
