package com.ssslinppp.aop;


import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.ssslinppp.model.ResultEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Description：<br/>
 * User: ssslinppp <br/>
 * Date: 2017/9/27 <br/>
 * Time: 18:12 <br/>
 * To change this template use File | Settings | File Templates.
 */
@Component
@Aspect
public class ControllerAspect {

  public static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

  @Around("execution(public com.ssslinppp.model.ResultEntity com..*.controller..*.*(..))")
  public Object handleControllerMethod(ProceedingJoinPoint pjp) {
    Stopwatch stopwatch = Stopwatch.createStarted();

    ResultEntity<?> resultEntity;
    try {
      logger.info("执行Controller开始: " + pjp.getSignature() + " 参数：" + Lists.newArrayList(pjp.getArgs()).toString());
      resultEntity = (ResultEntity<?>) pjp.proceed(pjp.getArgs());
      logger.info("执行Controller结束: " + pjp.getSignature() + "， 返回值：" + resultEntity.toString());
      logger.info("耗时：" + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "(毫秒).");
    } catch (Throwable throwable) {
      resultEntity = handlerException(pjp, throwable);
    }

    return resultEntity;
  }

  private ResultEntity<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
    ResultEntity<?> resultEntity = null;
    if (e instanceof RuntimeException) {
      logger
          .error("RuntimeException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}",
              e);
      resultEntity = ResultEntity.fail(e.getMessage());
    } else {
      logger.error("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
      resultEntity = ResultEntity.fail(e.getMessage());
    }

    return resultEntity;
  }
}
