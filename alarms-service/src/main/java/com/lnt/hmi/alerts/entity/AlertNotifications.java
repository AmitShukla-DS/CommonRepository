/**
* SRS is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.hmi.alerts.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "lntds_alert_notifications")
public class AlertNotifications {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "record_timestamp")
	private Timestamp recordTimestamp;
	@Column(name="asset_type")
	private String assetTypes;
	@Column(name = "asset_id")
	private String assetId;
	@Column(name = "condition_id")
	private String conditionId;
	@Column(name = "alert_type")
	private String alertType;
		@Column(name = "alert_source")
	private String alertSource;
	@Column(name = "severity")
	private String severity;
	@Column(name = "alert_message")
	private String alertMessage;
	@Column(name = "is_acknowledge")
	private String isAcknowledge;
	@Column(name = "ack_timestamp")
	private Timestamp ackTimestamp;
	@Column(name="ack_by")
	private String ackBy;
	@Column(name = "is_cleared")
	private String isCleared;
	@Column(name = "cleared_timestamp")
	private Timestamp clearedTimestamp;
	@Column(name = "cleared_by")
	private String clearedBy;
	@Column(name = "alert_name")
	private String alertName;
		@Column(name = "alert_count")
	private Integer alertCount;

	@Column(name = "is_notified")
	private String isNotified;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(Timestamp recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	public String getAssetTypes() {
		return assetTypes;
	}

	public void setAssetTypes(String assetTypes) {
		this.assetTypes = assetTypes;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAlertSource() {
		return alertSource;
	}

	public void setAlertSource(String alertSource) {
		this.alertSource = alertSource;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public String getIsAcknowledge() {
		return isAcknowledge;
	}

	public void setIsAcknowledge(String isAcknowledge) {
		this.isAcknowledge = isAcknowledge;
	}

	public Timestamp getAckTimestamp() {
		return ackTimestamp;
	}

	public void setAckTimestamp(Timestamp ackTimestamp) {
		this.ackTimestamp = ackTimestamp;
	}

	public String getAckBy() {
		return ackBy;
	}

	public void setAckBy(String ackBy) {
		this.ackBy = ackBy;
	}

	public String getIsCleared() {
		return isCleared;
	}

	public void setIsCleared(String isCleared) {
		this.isCleared = isCleared;
	}

	public Timestamp getClearedTimestamp() {
		return clearedTimestamp;
	}

	public void setClearedTimestamp(Timestamp clearedTimestamp) {
		this.clearedTimestamp = clearedTimestamp;
	}

	public String getClearedBy() {
		return clearedBy;
	}

	public void setClearedBy(String clearedBy) {
		this.clearedBy = clearedBy;
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public Integer getAlertCount() {
		return alertCount;
	}

	public void setAlertCount(Integer alertCount) {
		this.alertCount = alertCount;
	}

	public String getIsNotified() {
		return isNotified;
	}

	public void setIsNotified(String isNotified) {
		this.isNotified = isNotified;
	}
}
