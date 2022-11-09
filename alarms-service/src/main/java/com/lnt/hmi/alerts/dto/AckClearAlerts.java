/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.dto;

import java.util.List;

public class AckClearAlerts {

	List<Integer> ids; 
	String isAcknowldge; 
	String isCleared; 
	String userId;
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public String getIsAcknowldge() {
		return isAcknowldge;
	}
	public void setIsAcknowldge(String isAcknowldge) {
		this.isAcknowldge = isAcknowldge;
	}
	public String getIsCleared() {
		return isCleared;
	}
	public void setIsCleared(String isCleared) {
		this.isCleared = isCleared;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
