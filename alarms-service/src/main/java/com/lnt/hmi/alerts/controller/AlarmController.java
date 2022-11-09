/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lnt.hmi.alerts.constants.CommonMessages;
import com.lnt.hmi.alerts.constants.RESTUrls;
import com.lnt.hmi.alerts.dto.AckClearAlerts;
import com.lnt.hmi.alerts.dto.AggridStateDto;
import com.lnt.hmi.alerts.dto.ResultModel;
import com.lnt.hmi.alerts.entity.AlertNotifications;
import com.lnt.hmi.alerts.serviceImpl.AlaramServiceImp;
import com.lnt.hmi.alerts.util.ProjectConstants;

@RestController
@RequestMapping(value = RESTUrls.ALERT_NOTIFICATION)
public class AlarmController {

	private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

	@Autowired
	private AlaramServiceImp alaramServiceImp;
	
	@ResponseBody
	@GetMapping(value = ProjectConstants.ALL, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultModel> getAlertNotifications(@RequestParam Map<String, String> params) {
		String isCleared = params.get("isCleared");
		String isAcknowledge = params.get("isAcknowledge");
		String severity = params.get("severity");
		
		ResultModel resultModel = new ResultModel();
		logger.info("Getting all alarms...");
		try {
			List<AlertNotifications> response = alaramServiceImp.getAlarmsByIsClearedAndIsAcknowledgeAndSeverity(isCleared, isAcknowledge, severity); 
			resultModel.setData(response);
			resultModel.setMessage(CommonMessages.MSG_SUCCESS);
			logger.info("Got all alarams successfully...");
		} catch (Exception e) {
			resultModel.setMessage(CommonMessages.MSG_ERROR);
			logger.error(e.toString());
			return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}

	@ResponseBody
	@PutMapping(value = ProjectConstants.ACK_CLEAR, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultModel> acknowldgeOrClearAlerts(@RequestBody AckClearAlerts ackClearAlerts) {
		ResultModel resultModel = new ResultModel();
		logger.info("Getting all alarms...");
		try {
			String response = alaramServiceImp.cleareAndAcknowldgeAlarams(ackClearAlerts); 
			if(response.equalsIgnoreCase("Success")) {
				resultModel.setData(response);
				resultModel.setMessage(CommonMessages.MSG_SUCCESS);
				logger.info("Got all alarams successfully...");
			}else {
				resultModel.setMessage(CommonMessages.MSG_ERROR);
				logger.error("Error updating the alerts");
			}			
		} catch (Exception e) {
			resultModel.setMessage(CommonMessages.MSG_ERROR);
			logger.error(e.toString());
			return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = ProjectConstants.NON_NOTIFIED, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultModel> getAlertNotificationsNonNotified() {


		ResultModel resultModel = new ResultModel();
		logger.info("Getting non notified alarms...");
		try {
			List<AlertNotifications> response = alaramServiceImp.getNonNotifiedAlert();
			resultModel.setData(response);
			resultModel.setMessage(CommonMessages.MSG_SUCCESS);
			logger.info("Got all alarams successfully...");
		} catch (Exception e) {
			resultModel.setMessage(CommonMessages.MSG_ERROR);
			logger.error(e.toString());
			return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}

}
