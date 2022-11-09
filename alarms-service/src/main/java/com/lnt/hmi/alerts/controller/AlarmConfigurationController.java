package com.lnt.hmi.alerts.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.lnt.hmi.alerts.constants.CommonMessages;
import com.lnt.hmi.alerts.constants.RESTUrls;
import com.lnt.hmi.alerts.dto.ResultModel;
import com.lnt.hmi.alerts.entity.AlarmConfiguration;

import com.lnt.hmi.alerts.serviceImpl.AlarmConfigurationServiceImpl;
import com.lnt.hmi.alerts.util.ProjectConstants;

@RestController
@RequestMapping(value = RESTUrls.ALARM_CONFIGURATION)
public class AlarmConfigurationController {
	private static final Logger logger = LoggerFactory.getLogger(AlarmConfigurationController.class);
	
	@Autowired
	private AlarmConfigurationServiceImpl alarmConfigurationServiceImpl;
	
	
	
	@ResponseBody
	@GetMapping(value = ProjectConstants.ALL_ALERT_REGISTER_MAP, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultModel> getAllAlertRegisterMap() {
		
		
		
		ResultModel resultModel = new ResultModel();
		logger.info("Getting all alarms...");
		try {
			List<AlarmConfiguration> response =alarmConfigurationServiceImpl.getAllAlertRegisterMap();
			resultModel.setData(response);
			resultModel.setMessage(CommonMessages.MSG_SUCCESS);
			logger.info("Got all alert register map  successfully...");
		} catch (Exception e) {
			resultModel.setMessage(CommonMessages.MSG_ERROR);
			logger.error(e.toString());
			return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = ProjectConstants.DELETE_ALERT_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	// @SecureActionDefinition(code = AuditCodes.DELETE_DEVICE_ATTRIBUTES)
	public ResponseEntity<ResultModel> deleteAlertConfiguration(@PathVariable("id") Integer id) {
		System.out.println("IDDDDD"+id);
		ResultModel resultModel = new ResultModel();
		logger.info("Deleting Alert Alarm Configuration  ......");
		try {
			Boolean response=alarmConfigurationServiceImpl.deleteAlertConfiguration(id);
			
			 if(Boolean.TRUE.equals(response))
			 {
				 resultModel.setData("Deleted Successfully");
				 resultModel.setMessage(CommonMessages.MSG_SUCCESS);
			 }
			 else
			 {
				 resultModel.setData("Failed TO Delete");
				 resultModel.setMessage(CommonMessages.FAILED);
			 }
			 
			
			logger.info("Deleted Successfully.......");
		} catch (Exception e) {
			resultModel.setMessage(CommonMessages.MSG_ERROR);
			logger.error(e.toString());
			return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}
	
	@PostMapping(value = ProjectConstants.SAVE_ALERT_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<ResultModel> saveAlertConfiguration(@RequestBody AlarmConfiguration dto) {
		ResultModel resultModel = new ResultModel();
		logger.info("Creating Alert Configuration  .......");
		try {
                String response = alarmConfigurationServiceImpl.saveAlarmConfiguration(dto);
                if(response=="Successfully Added")
                {
                	resultModel.setData(response);
                	 resultModel.setMessage("Successfully Added");
                }
                else
                {
                	 resultModel.setData(response);
    				 resultModel.setMessage(CommonMessages.FAILED);
                }
				
				 logger.info("Alarm Configuration created Successfully.......");
			 } catch (Exception e) {
			resultModel.setMessage(CommonMessages.MSG_ERROR);
			logger.error( e.toString());
			return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@PutMapping(value = ProjectConstants.UPDATE_ALERT_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<ResultModel> updateAlertConfiguration(@RequestBody AlarmConfiguration dto,@PathVariable Integer id) {
	 ResultModel resultModel = new ResultModel();
	 logger.info("Updating Alert Configuration ...");
	 try {
		 AlarmConfiguration response = alarmConfigurationServiceImpl.updateAlertConfigurationById(dto, id);
	 resultModel.setData(response);
	 resultModel.setMessage(CommonMessages.MSG_SUCCESS);
	 logger.info("Update Alert Configuration.......");
	 }catch(Exception e){
	 resultModel.setMessage(CommonMessages.MSG_ERROR);
	 logger.info("Error Occure"+e);
	 return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
	 }
	 return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
	 }
	

}
