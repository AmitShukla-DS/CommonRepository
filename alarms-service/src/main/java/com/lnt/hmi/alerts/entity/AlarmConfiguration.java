package com.lnt.hmi.alerts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "lntds_alert_register_map")
public class AlarmConfiguration {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "register_no")
	private Integer registerNo;

	@Column(name = "value")
	private Integer value;

	@Column(name = "asset_id")
	private String assets;

	@Column(name = "alert_type")
	private String alertType;

	@Column(name = "alert_name")
	private String alertName;

	@Column(name = "alert_message")
	private String alertMessage;

	@Column(name = "severity")
	private String severity;
	
	@Column(name = "is_toaster")
	private Boolean isToaster;

	public Integer getId() {
		return id;
	}

	public Boolean getIsToaster() {
		return isToaster;
	}

	public void setIsToaster(Boolean isToaster) {
		this.isToaster = isToaster;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(Integer registerNo) {
		this.registerNo = registerNo;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}


	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}
 
	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getAlertMessage() {
		return alertMessage;
	}



	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

//	public Codes getSeverity() {
//		return severity;
//	}
//
//	public void setSeverity(Codes severity) {
//		this.severity = severity;
//	}

}
