/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.constants;

public class RESTUrls {

	private RESTUrls() {
		throw new IllegalStateException("RESTUrls class");
	}

	// Base url path
	public static final String BASE_URL = "/ess/alertService";

	// AlertNotificationsController
	public static final String ALERT_NOTIFICATION = BASE_URL+"/alertNotification";

	
	
	public static final String ALARM_CONFIGURATION = BASE_URL+"/alarmConfiguration";


}