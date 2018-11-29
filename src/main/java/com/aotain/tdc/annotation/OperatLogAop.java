package com.aotain.tdc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.aotain.tdc.constant.OpType;

/**
  * @ClassName: OperatLogAop
  * @author 凯哥
  * @date 2016年11月18日 上午11:23:37
  *
  */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperatLogAop {
	public OpType opType();
	public String opModule();
}
