/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lnt.hmi.alerts.entity.AlertNotifications;

@Repository
@Transactional
public interface AlarmDao extends CrudRepository<AlertNotifications, Integer>{

	List<AlertNotifications> findByIsClearedOrderByRecordTimestampDesc(String isCleared);
	
	List<AlertNotifications> findByIsAcknowledgeOrderByRecordTimestampDesc(String isAcknowledge);
	
	List<AlertNotifications> findBySeverityOrderByRecordTimestampDesc(String severity);
	
	List<AlertNotifications> findByIsClearedAndIsAcknowledgeOrderByRecordTimestampDesc(String isCleared, String isAcknowledge);
	
	List<AlertNotifications> findByIsClearedAndSeverityOrderByRecordTimestampDesc(String isCleared, String severity);
	
	List<AlertNotifications> findByIsAcknowledgeAndSeverityOrderByRecordTimestampDesc(String isAcknowledge, String severity);
	
	List<AlertNotifications> findByIsClearedAndIsAcknowledgeAndSeverityOrderByRecordTimestampDesc(String isCleared, String isAcknowledge, String severity);
	
	@Query("SELECT a.id from AlertNotifications a where a.isCleared=?1 AND a.id in (?2) ")
	List<Integer> findByIsClearedAndIdIn(String isCleared, List<Integer> ids);
	
	@Query("SELECT a.id from AlertNotifications a where a.isAcknowledge=?1 AND a.id in (?2) ")
	List<Integer> findIdByIsAcknowledgeAndIdIn(String isAcknowledge, List<Integer> ids);
	
	@Modifying
	@Query("UPDATE AlertNotifications a SET isAcknowledge=?1, a.ackTimestamp=?2, a.ackBy=?3 WHERE a.id in (?4)")
	void acknowldgeAlarms(String isAcknowledge, Timestamp ackTimestamp, String ackBy, List<Integer> ids);
	
	@Query("UPDATE AlertNotifications a SET isCleared=?1, a.clearedTimestamp=?2, a.clearedBy=?3 WHERE a.id in (?4)")
	@Modifying
	void clearAlarms(String isCleared, Timestamp ackTimestamp, String ackBy, List<Integer> ids);

	@Query("SELECT a from AlertNotifications a WHERE a.id in (?1) ORDER BY recordTimestamp DESC")
	List<AlertNotifications> findById(List<Integer> idsList);
	
	
	List<AlertNotifications> findByIsNotified(String isNotified);

	@Query("UPDATE AlertNotifications a SET isNotified='Y' WHERE a.isNotified = 'N'")
	@Modifying
	void notifyAlerts();
	

	
	
	@Query(value="update lntds_alert_notifications as a \r\n"
			+ "inner join lntds_alert_register_map b\r\n"
			+ " on b.id=a.condition_id\r\n"
			+ " set a.is_toaster=b.is_toaster;",nativeQuery = true)
	@Modifying
	void toasterAlerts();
	
	
	@Query("SELECT a from AlertNotifications a INNER JOIN AlarmConfiguration m on a.conditionId = m.id WHERE a.isNotified = 'N' AND m.isToaster = 1")
	
	//@Query("SELECT a from AlertNotifications where a.isAcknowledge=='N' AND a.isToaster=='1' AND a.isNotified = 'N'")
	List<AlertNotifications> findByIsNotifiedToaster(String string);
}
