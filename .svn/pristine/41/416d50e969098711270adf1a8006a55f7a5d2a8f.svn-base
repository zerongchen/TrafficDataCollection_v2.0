package com.aotain.trafficDataCollection.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.aotain.tdc.model.common.MsgBean;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GuavaTest {
	static LoadingCache<String, MsgBean> msgBeans = CacheBuilder.newBuilder()
		       .maximumSize(1000)
		       .expireAfterWrite(10, TimeUnit.MINUTES)
		       .recordStats()
		       .removalListener(new RemovalListener<Object, Object>() {
                 @Override
                 public void onRemoval(RemovalNotification<Object, Object> notification) {
                     System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                 }
             })
		       .build(
		    		   new CacheLoader<String, MsgBean>() {
		    			   public MsgBean load(String key) {
		    				   MsgBean msgBean = new MsgBean();
		    				   msgBean.setFlag(1);
		    				   msgBean.setMsg(key);
		    				   System.out.println("new");
		    				   return msgBean;
		    			   }
		    		   });
	public static void main(String[] args) throws ExecutionException, InterruptedException{
        for (int i=0;i<20;i++) {
        	MsgBean msgBean = msgBeans.get(""+i);
            System.out.println(msgBean);
        }
        for (int i=0;i<20;i++) {
        	MsgBean msgBean = msgBeans.get(""+i);
            System.out.println(msgBean);
        }
        for(MsgBean entry : msgBeans.asMap().values()){
        	System.out.println("e : "+ entry);
        }
        System.out.println("cache stats:");
        System.out.println(msgBeans.stats().toString());
	}
}
