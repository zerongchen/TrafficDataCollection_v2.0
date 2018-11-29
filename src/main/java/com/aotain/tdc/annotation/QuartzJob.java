package com.aotain.tdc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用注解来反射读取注解中的cron表达式，用来在web启动的时候自动录入数据库
 * @author zhy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface QuartzJob {
	String cron();
	String groupName();
	String desc();
	
}
