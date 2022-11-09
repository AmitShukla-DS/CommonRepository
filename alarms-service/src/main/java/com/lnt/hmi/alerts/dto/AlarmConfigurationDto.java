package com.lnt.hmi.alerts.dto;

public class AlarmConfigurationDto {
	
	private String alertName;

	private String alertType;

	private String assets;
	
	private String severity;
	
	private String alertMessage;
	
	private Integer registerNo;
	
	private Integer value;
	
	private Boolean isToaster;

	public Boolean getIsToaster() {
		return isToaster;
	}

	public void setIsToaster(Boolean isToaster) {
		this.isToaster = isToaster;
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getAlertType() {
		return alertType;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
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
	


}
