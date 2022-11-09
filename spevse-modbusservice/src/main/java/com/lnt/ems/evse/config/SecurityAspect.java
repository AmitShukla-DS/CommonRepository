/**
* EVSE is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.ems.evse.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lnt.ems.evse.constants.AuditCodes;
import com.lnt.ems.evse.dao.AuditRepository;
import com.lnt.ems.evse.entity.AuditLog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;


@Aspect
@Component
public class SecurityAspect {

	@Autowired
	private AuditRepository auditLongsRepo;

	@Pointcut("@annotation(com.lnt.ems.evse.config.SecureActionDefinition)")
	public void securedMethods() {
	};

	@Autowired
	HttpServletRequest httpRequest;

	@Around("securedMethods()")
	public Object secureAndLogExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String classname = signature.getDeclaringType().getSimpleName();
		AuditCodes code = org.springframework.core.annotation.AnnotationUtils
				.findAnnotation(signature.getMethod(), SecureActionDefinition.class).code();
		Object retval = joinPoint.proceed();
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		Map<String, Object> map = mapper.convertValue(retval, Map.class);
		Map<String, Object> body =(Map<String, Object>) map.get("body");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		AuditLog audit = new AuditLog();
		audit.setLntds_process_name(classname);
		audit.setAudit_category(code.toString());
		if(null==body.get("message")) {
			audit.setAudit_message("null");
		}else {
			audit.setAudit_message(body.get("message").toString());
		}   
		audit.setUser_id(auth.getName());
		audit.setAudit_datetime(LocalDateTime.now());
		auditLongsRepo.save(audit);
		return retval;
	}

}