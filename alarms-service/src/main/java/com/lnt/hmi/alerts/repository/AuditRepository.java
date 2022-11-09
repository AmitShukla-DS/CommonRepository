/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lnt.hmi.alerts.entity.AuditLog;

@Repository
@Transactional
public interface AuditRepository extends CrudRepository<AuditLog, Long> {

	@Query(value = "select * from lntds_audit_log where DATE(audit_datetime) <=:date ORDER BY id ASC;", nativeQuery = true)
	public List<AuditLog> getLastSevenDaysRecord(@Param("date") String date);

	@Modifying
	@Query(value = "delete from lntds_audit_log where DATE(audit_datetime) <=:date ORDER BY id ASC", nativeQuery = true)
	public void deleteAudit(@Param("date") String date);

}
