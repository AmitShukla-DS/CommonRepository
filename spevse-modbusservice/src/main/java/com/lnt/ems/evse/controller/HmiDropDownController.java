/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lnt.ems.evse.config.SecureActionDefinition;
import com.lnt.ems.evse.constants.AuditCodes;
import com.lnt.ems.evse.constants.CommonMessages;
import com.lnt.ems.evse.constants.RESTUrls;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.entity.HMIDropdownData;
import com.lnt.ems.evse.services.HmiDropdownDataService;

import java.util.List;


@Controller
@RequestMapping(value = RESTUrls.HMI_DROPDOWN)
public class HmiDropDownController {
    private static final Logger logger = LoggerFactory.getLogger(HmiDropDownController.class);

    @Autowired
    HmiDropdownDataService hmiDropdownDataService;


    @ResponseBody
    @GetMapping(value = RESTUrls.GET_HMI_DROPDOWN_DATA, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_HMI_DROPDOWN_DATA)
    public ResponseEntity<ResultModel> getHmiDropdownData() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All HmiRegisters dropdown Data .......");
        try {
            List<HMIDropdownData> response = hmiDropdownDataService.getAllHmiDropdownData();
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info("Getting HmiRegisters dropdown Data .......");
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

}