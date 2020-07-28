package org.egov.nulm.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class ApplicationAspectLogs {

	// Controller
	@Before("execution(* org.egov.nulm.web.controller.*.*(..))")
	public void beforeController(JoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: beforeController = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
		log.debug("nulm-services logs :: beforeController = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
	}

	@After("execution(* org.egov.nulm.web.controller.*.*(..))")
	public void afterController(JoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: afterController = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
		log.debug("nulm-services logs :: afterController = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
	}

	@AfterReturning(value = "execution(* org.egov.nulm.web.controller.*.*(..))", returning = "result")
	public void afterReturningController(JoinPoint joinPoint, Object result) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: afterReturningController = Method::{}, Execution Time::{}, Results::{}",
				joinPoint.getSignature(), startTime, result);
		log.debug("nulm-services logs :: afterReturningController = Method::{}, Execution Time::{}, Results::{}",
				joinPoint.getSignature(), startTime, result);
	}

	@AfterThrowing(value = "execution(* org.egov.nulm.web.controller.*.*(..))", throwing = "exception")
	public void afterThrowingController(JoinPoint joinPoint, Throwable exception) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: afterThrowingController = Method::{}, Execution Time::{}, Exception::{}",
				joinPoint.getSignature(), startTime, exception.getMessage());
		log.debug("nulm-services logs :: afterThrowingController = Method::{}, Execution Time::{}, Exception::{}",
				joinPoint.getSignature(), startTime, exception.getMessage());
	}



	// Repository
	@Before("execution(* org.egov.nulm.repository.*.*(..))")
	public void beforeRepository(JoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: beforeRepository = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
		log.debug("nulm-services logs :: beforeRepository = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
	}

	@After("execution(* org.egov.nulm.repository.*.*(..))")
	public void afterRepository(JoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: afterRepository = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
		log.debug("nulm-services logs :: afterRepository = Method::{}, Execution Time::{}", joinPoint.getSignature(),
				startTime);
	}

	@AfterReturning(value = "execution(* org.egov.nulm.repository.*.*(..))", returning = "result")
	public void afterReturningRepository(JoinPoint joinPoint, Object result) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: afterReturningRepository = Method::{}, Execution Time::{}, Results::{}",
				joinPoint.getSignature(), startTime, result);
		log.debug("nulm-services logs :: afterReturningRepository = Method::{}, Execution Time::{}, Results::{}",
				joinPoint.getSignature(), startTime, result);
	}

	@AfterThrowing(value = "execution(* org.egov.nulm.repository.*.*(..))", throwing = "exception")
	public void afterThrowingRepository(JoinPoint joinPoint, Throwable exception) {
		long startTime = System.currentTimeMillis();
		log.info("nulm-services logs :: afterThrowingRepository = Method::{}, Execution Time::{}, Exception::{}",
				joinPoint.getSignature(), startTime, exception.getMessage());
		log.debug("nulm-services logs :: afterThrowingRepository = Method::{}, Execution Time::{}, Exception::{}",
				joinPoint.getSignature(), startTime, exception.getMessage());
	}

}
