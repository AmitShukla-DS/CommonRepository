/**
* EVSE is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.ems.evse.config;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lnt.ems.evse.constants.AuditCodes;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface SecureActionDefinition {

	@Enumerated(EnumType.STRING)
	AuditCodes code();
}