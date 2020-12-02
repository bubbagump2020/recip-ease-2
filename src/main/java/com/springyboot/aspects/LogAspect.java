package com.springyboot.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Point cut that matches all repositories, services, and web Rest endpoints
	 */
	@Pointcut("within(@org.springframework.stereotype.Repository *)" +
		" || within(@org.springframework.stereotype.Service *) " +
		" || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointCut() { }
	
	/**
	 * Point cut that matches all Spring Beans in the application's main packages
	 */
	@Pointcut("within(com.springyboot..*)" +
	 " || within(com.springyboot.services..*)" +
	 " || within(com.springyboot.controllers..*)")
	public void applicationPackagePointcut() { }
	
	/**
	 * Advice that logs methods throwing exceptions
	 * @param joinPoint join point for advice
	 * @param e exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowException(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
	}
}
