/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.services;

import java.util.List;

import com.lnt.hmi.alerts.dto.AckClearAlerts;
import com.lnt.hmi.alerts.entity.AlertNotifications;

public interface AlarmService {

	public List<AlertNotifications> getAlarmsByIsClearedAndIsAcknowledgeAndSeverity(String isCleared, String isAcknowledge, String severity);

	public String cleareAndAcknowldgeAlarams(AckClearAlerts ackClearAlerts);

	public List<AlertNotifications> getNonNotifiedAlert();

}
