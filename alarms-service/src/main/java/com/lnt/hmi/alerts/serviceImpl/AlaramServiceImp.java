/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.hmi.alerts.dto.AckClearAlerts;
import com.lnt.hmi.alerts.entity.AlertNotifications;
import com.lnt.hmi.alerts.entity.AlertNotificationsHistory;
import com.lnt.hmi.alerts.repository.AlarmDao;
import com.lnt.hmi.alerts.repository.AlarmHistoryDao;
import com.lnt.hmi.alerts.services.AlarmService;


@Service
public class AlaramServiceImp implements AlarmService {

	@Autowired
	private AlarmDao alarmDao;
	
	@Autowired
	private AlarmHistoryDao alarmHistoryDao;
	
	@Override
	public List<AlertNotifications> getAlarmsByIsClearedAndIsAcknowledgeAndSeverity(String isCleared, String isAcknowledge, String severity) {
		List<AlertNotifications> listAlertNotifications = new ArrayList<AlertNotifications>();
		if(isCleared.equalsIgnoreCase("All") && isAcknowledge.equalsIgnoreCase("All") && severity.equalsIgnoreCase("All")) {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findAll();
		}else if(isCleared.equalsIgnoreCase("All") && isAcknowledge.equalsIgnoreCase("All") && (! severity.equalsIgnoreCase("All"))) {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findBySeverityOrderByRecordTimestampDesc(severity);
		}else if(isCleared.equalsIgnoreCase("All") && (! isAcknowledge.equalsIgnoreCase("All")) && (! severity.equalsIgnoreCase("All"))) {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findByIsAcknowledgeAndSeverityOrderByRecordTimestampDesc(isAcknowledge, severity);
		}else if((isCleared.equalsIgnoreCase("All")) && (! isAcknowledge.equalsIgnoreCase("All")) && severity.equalsIgnoreCase("All")) {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findByIsAcknowledgeOrderByRecordTimestampDesc(isAcknowledge);
		}else if((! isCleared.equalsIgnoreCase("All")) && isAcknowledge.equalsIgnoreCase("All") && severity.equalsIgnoreCase("All")) {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findByIsClearedOrderByRecordTimestampDesc(isCleared);
		}else if((! isCleared.equalsIgnoreCase("All")) && (! isAcknowledge.equalsIgnoreCase("All")) && severity.equalsIgnoreCase("All")) {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findByIsClearedAndIsAcknowledgeOrderByRecordTimestampDesc(isCleared, isAcknowledge);
		}else if((! isCleared.equalsIgnoreCase("All")) && (isAcknowledge.equalsIgnoreCase("All")) && (! severity.equalsIgnoreCase("All"))) {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findByIsClearedAndSeverityOrderByRecordTimestampDesc(isCleared, severity);
		}else {
			listAlertNotifications = (List<AlertNotifications>) alarmDao.findByIsClearedAndIsAcknowledgeAndSeverityOrderByRecordTimestampDesc(isCleared, isAcknowledge, severity);
		}
		return listAlertNotifications;
	}

	
	@Override
	public String cleareAndAcknowldgeAlarams(AckClearAlerts ackClearAlerts ) {
		String response = "Success";
		try{
			
			List<Integer> ackIdsList = alarmDao.findIdByIsAcknowledgeAndIdIn("N", ackClearAlerts.getIds());

			alarmDao.acknowldgeAlarms("Y", new Timestamp(new Date().getTime()),ackClearAlerts.getUserId(), ackIdsList);
			
			if(ackClearAlerts.getIsCleared() != null &&  ackClearAlerts.getIsCleared().equalsIgnoreCase("Y")) {
				List<Integer> idsList = alarmDao.findByIsClearedAndIdIn("N", ackClearAlerts.getIds());
				List<AlertNotifications> alertsFound = alarmDao.findById(idsList);
				List<AlertNotificationsHistory> alertNotificationsHistory = new ArrayList<>(alertsFound.size());
				for(AlertNotifications alertNotification : alertsFound) {
					AlertNotificationsHistory alertNotificationHistory = new AlertNotificationsHistory(); 
					alertNotificationHistory.setAckBy(alertNotification.getAckBy());
					alertNotificationHistory.setAckTimestamp(alertNotification.getAckTimestamp());
					alertNotificationHistory.setAlertCount(alertNotification.getAlertCount());
					alertNotificationHistory.setAlertMessage(alertNotification.getAlertMessage());
					alertNotificationHistory.setAlertName(alertNotification.getAlertName());
					alertNotificationHistory.setAlertSource(alertNotification.getAlertSource());
					alertNotificationHistory.setAlertType(alertNotification.getAlertType());
					alertNotificationHistory.setAssetId(alertNotification.getAssetId());
					alertNotificationHistory.setAssetTypes(alertNotification.getAssetTypes());
					alertNotificationHistory.setConditionId(alertNotification.getConditionId());
					alertNotificationHistory.setId(alertNotification.getId());
					alertNotificationHistory.setIsAcknowledge(alertNotification.getIsAcknowledge());
					alertNotificationHistory.setSeverity(alertNotification.getSeverity());
					alertNotificationHistory.setRecordTimestamp(alertNotification.getRecordTimestamp());					
					alertNotificationHistory.setClearedTimestamp(new Timestamp(new Date().getTime()));
					alertNotificationHistory.setIsCleared("Y");
					alertNotificationHistory.setClearedBy(ackClearAlerts.getUserId());
					alertNotificationsHistory.add(alertNotificationHistory);
				}
				alarmHistoryDao.saveAll(alertNotificationsHistory);
				alarmDao.deleteAllById(idsList);
				//alarmDao.clearAlarms("Y", new Timestamp(new Date().getTime()), ackClearAlerts.getUserId(), idsList);
			}
		}catch(Exception e) {
			response  = "Error";
		}
	 return	response;
	}

	@Override
	public List<AlertNotifications> getNonNotifiedAlert() {
		List<AlertNotifications> alertNotificationsList =  alarmDao.findByIsNotified("N");
		alarmDao.notifyAlerts();
		alarmDao.toasterAlerts();
		return alertNotificationsList;
	}


	
}
