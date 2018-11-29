package com.aotain.tdc.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.aotain.tdc.annotation.QuartzJob;
import com.aotain.tdc.model.common.SchedulerJob;


//import com.spdb.hs.timer.service.iservice.ISchedulerJobService;

public class QuartzJobSchedulerListener implements ApplicationListener<ContextRefreshedEvent>  {
	
	private Logger log = Logger.getLogger(QuartzJobSchedulerListener.class);
	
	@Autowired
	private Scheduler scheduler;
    //@Autowired
	//private ISchedulerJobService jobService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			ApplicationContext applicationContext = event.getApplicationContext();	
		    loadCronTriggers(applicationContext,scheduler);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadCronTriggers(ApplicationContext applicationContext, Scheduler scheduler2) {
		//获取所有有注解QuartzJob的Job
		  Map<String,Object> quartzJobBeans = applicationContext.getBeansWithAnnotation(QuartzJob.class);
		  //获取所有job名字
		  Set<String> jobNames = quartzJobBeans.keySet();
		  List<SchedulerJob> jobList = new ArrayList<SchedulerJob>();
		  try {
					for (String jobName : jobNames) {
						     //反射获取任务类对象，并获取注解信息,入库
						      Object obj = quartzJobBeans.get(jobName);
						      if(obj.getClass().isAnnotationPresent(QuartzJob.class)){
						    	  QuartzJob quartzJob = obj.getClass().getAnnotation(QuartzJob.class);
						    	  String cronExp = quartzJob.cron();
						    	  String groupName = quartzJob.groupName();
						    	  //String desc = quartzJob.desc();
						    	//检查数据库存在性，不存在则添加
							      //if(jobService.getByNameAndGroup(jobName,groupName)==null){
							    	//  jobList.add(addJob(jobName, quartzJob, desc));
							      //}
							      
							      //判断执行器中此任务是否存在不存在则添加到执行器中
							     if(!isJobExist(jobName, groupName)){
							      JobKey jobKey = new JobKey(jobName, groupName);
							      JobDetail job = JobBuilder.newJob((Class<? extends Job>)obj.getClass())
							    		                    .withIdentity(jobKey)
							    		                    .build();
							      CronTrigger cronTrigger = TriggerBuilder.newTrigger()
														                  .withIdentity(jobName + "_trigger", groupName)
														                  .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
														                  .build();
							      scheduler.scheduleJob(job, cronTrigger);
							      // 启动    
						          if (!scheduler.isShutdown()){    
						        	  scheduler.start();    
						          }  
							    }
					      }
					  }
					
					
				 	 //入库定时任务
					/* if(jobList.size()>0){
				    	  try {
					    		  //Integer count = jobService.addLst(jobList);
					    		  //if(count==0){
					    			  //插入失败
					    			  log.error("定时任务入库失败.......");
					    		  //}
							} catch (Exception e) {
								log.error(e.getMessage(), e);
							}
				      }*/
				} catch (SchedulerException e) {
					log.error(e.getMessage());
				}		      
		                   
	}

	private boolean isJobExist(String jobName, String groupName) {
		try {
			return  scheduler.checkExists(new JobKey(jobName,groupName));
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/*private SchedulerJob addJob(String jobName, QuartzJob quartzJob, String desc) {
		  SchedulerJob schedulerJob = new SchedulerJob();
		  schedulerJob.setCronExp(quartzJob.cron());
		  schedulerJob.setJobGroup(quartzJob.groupName());
		  schedulerJob.setJobName(jobName);
		  schedulerJob.setJobDesc(StringUtils.isNotBlank(desc)?desc:null);
		  return schedulerJob;
	}*/

}
