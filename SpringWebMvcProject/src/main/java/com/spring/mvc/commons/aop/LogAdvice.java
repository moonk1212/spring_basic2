package com.spring.mvc.commons.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

	
	@Around("execution(* com.spring.mvc..*Controller.*(..))"
            + " or execution(* com.spring.mvc..service..*Service.*(..))"
            + " or execution(* com.spring.mvc..repository..*DAO.*(..))")
	public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		//공통기능을 적용한 결과를 담는 변수.
		Object result = proceedingJoinPoint.proceed();
		
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String name = "";

        if (type.contains("Controller")) {
            name = "Controller : ";
        } else if (type.contains("Service")) {
            name = "Service : ";
        } else if (type.contains("DAO")) {
            name = "Repository : ";
        }
		
		long end = System.currentTimeMillis();
		
		logger.info(name + type + "."+proceedingJoinPoint.getSignature().getName() + "()");
        logger.info("Argument/Parameter : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        if (result != null) {
            logger.info("Return Value : " + result.toString());
        } else {
            logger.info("Return Type : void");
        }
        logger.info("Running Time : " + (end-start) + "ms");
        logger.info("----------------------------------------------------------------");
		
		return result;
	}
	
}







