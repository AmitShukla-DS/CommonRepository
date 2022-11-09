/**
* EVSE is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.ems.evse.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "lntds_audit_log")
public class AuditLog implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	protected Long id;

	@Column(name = "lntds_process_name", nullable = false)
	private String lntds_process_name;

	@Column(name = "audit_category", nullable = false)
	private String audit_category;

	@Column(name = "audit_message",nullable = true)
	private String audit_message;

	@Column(name = "user_id", nullable = false)
	private String user_id;

	@Column(name = "audit_datetime", nullable = false)
	private LocalDateTime audit_datetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLntds_process_name() {
		return lntds_process_name;
	}

	public void setLntds_process_name(String lntds_process_name) {
		this.lntds_process_name = lntds_process_name;
	}

	public String getAudit_category() {
		return audit_category;
	}

	public void setAudit_category(String audit_category) {
		this.audit_category = audit_category;
	}

	public String getAudit_message() {
		return audit_message;
	}

	public void setAudit_message(String audit_message) {
		this.audit_message = audit_message;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public LocalDateTime getAudit_datetime() {
		return audit_datetime;
	}

	public void setAudit_datetime(LocalDateTime audit_datetime) {
		this.audit_datetime = audit_datetime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
