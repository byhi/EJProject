package com.byhi.ejproject.ejdata.meter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;


/**
 *This is the AOP implementation for logging the time of method
 */
@Aspect
@Component
public class LoggingAspect {
    @Autowired
    FileLogging fileLogging;

    HashMap<String, MeterState> stringIntegerHashMap = new HashMap<>();


    /**This method contain time meter and logger
     * @param proceedingJoinPoint - contain join point informations
     * @return - proceed with the next advice or target method invocation
     */
    @Around("@annotation(com.byhi.ejproject.ejdata.meter.LogMethodExecutionTime)")
    public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch stopWatch = new StopWatch(String.format("%s.%s", className, methodName));

        stopWatch.start(methodName);
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        stopWatch.stop();

        MeterState actual = stringIntegerHashMap.containsKey(stopWatch.getId()) ?
                stringIntegerHashMap.get(stopWatch.getId()) : new MeterState();

        actual.addCounter(stopWatch.getLastTaskTimeMillis());
        actual.calls++;
        actual.reCalculateAvg();

        stringIntegerHashMap.put(stopWatch.getId(), actual);
        stringIntegerHashMap.forEach((k, v) -> {
                    fileLogging.writeMsgToDefault(String.format("%s : %s db : %s ms", k, actual.calls, v.avg));
                }
        );

        return result;
    }
}
