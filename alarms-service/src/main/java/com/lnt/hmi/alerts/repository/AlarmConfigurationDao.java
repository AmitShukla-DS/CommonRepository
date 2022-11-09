package com.lnt.hmi.alerts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lnt.hmi.alerts.entity.AlarmConfiguration;


@Repository
public interface AlarmConfigurationDao extends CrudRepository<AlarmConfiguration, Integer>{
	
	@Query(value = "SELECT register_no FROM lntds_alert_register_map where register_no=:id" ,nativeQuery = true)
	public Integer findByRegisterNo(Integer id);

}

