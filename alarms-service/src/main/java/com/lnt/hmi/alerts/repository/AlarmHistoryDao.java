package com.lnt.hmi.alerts.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.lnt.hmi.alerts.entity.AlertNotificationsHistory;

@Repository
@Transactional
public interface AlarmHistoryDao extends CrudRepository<AlertNotificationsHistory, Integer> {

	
	
}
